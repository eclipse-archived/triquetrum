/*******************************************************************************
 * Copyright (c) 2017 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.repository.ui.views;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.triquetrum.ErrorCode;
import org.eclipse.triquetrum.workflow.DuplicateEntryException;
import org.eclipse.triquetrum.workflow.EntryNotFoundException;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.eclipse.triquetrum.workflow.WorkflowRepositoryRegistry;
import org.eclipse.triquetrum.workflow.WorkflowRepositoryService;
import org.eclipse.triquetrum.workflow.repository.ui.RepositoryPlugin;
import org.eclipse.triquetrum.workflow.util.WorkflowUtils;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.service.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.actor.CompositeActor;

/**
 * This is a viewer on the registered WorkflowRepositoryServices and on their (versioned) contents.
 * <p>
 * It provides simple actions to add/remove workflow models and to activate specific versions.
 * </p>
 */

public class WorkflowRepositoryView extends ViewPart {
  private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowRepositoryView.class);

  private TreeViewer viewer;
  private DrillDownAdapter drillDownAdapter;

  /**
   * This is a callback that will allow us to create the viewer and initialize it.
   */
  public void createPartControl(Composite parent) {
    viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    drillDownAdapter = new DrillDownAdapter(viewer);

    viewer.setContentProvider(new ViewContentProvider());
    viewer.setLabelProvider(new ViewLabelProvider(viewer));
    viewer.setInput(getViewSite());

    getSite().setSelectionProvider(viewer);

    hookContextMenu();
  }

  /**
   * Passing the focus request to the viewer's control.
   */
  public void setFocus() {
    viewer.getControl().setFocus();
  }

  /**
   * Hooks a context menu manager to a selected tree node and adds the relevant actions for the selected node type.
   */
  private void hookContextMenu() {
    MenuManager menuMgr = new MenuManager("#PopupMenu");
    menuMgr.setRemoveAllWhenShown(true);
    menuMgr.addMenuListener(new IMenuListener() {
      public void menuAboutToShow(IMenuManager manager) {
        ISelection selection = viewer.getSelection();
        if (selection instanceof TreeSelection) {
          TreeSelection treeSelection = (TreeSelection) selection;
          if (treeSelection.size() == 1) {
            Object selObj = treeSelection.getFirstElement();
            if (selObj instanceof ModelHandleTreeNode) {
              ModelHandleTreeNode node = (ModelHandleTreeNode) selObj;
              if (!node.getRepository().isActiveModelRevision(node.getValue())) {
                manager.add(new ActivationAction(node));
              }
              manager.add(new ImportAction((AbstractTreeNode) node.getParent()));
            } else {
              manager.add(new ImportAction((AbstractTreeNode) selObj));
              if (selObj instanceof WorkflowRepositoryService) {
                drillDownAdapter.addNavigationActions(manager);
              }
            }
            if (selObj instanceof ModelCodeTreeNode) {
              manager.add(new AddToUserLibraryAction((ModelCodeTreeNode) selObj));
              manager.add(new DeleteAction((ModelCodeTreeNode) selObj));
            }
            manager.add(new Separator());
            manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
          }
        }
      }
    });
    Menu menu = menuMgr.createContextMenu(viewer.getControl());
    viewer.getControl().setMenu(menu);
    getSite().registerContextMenu(menuMgr, viewer);
  }

  /**
   * A content provider that sets all registered repository services as the top-level contents in the tree view.
   */
  private class ViewContentProvider extends TreeNodeContentProvider {
    public Object[] getElements(Object parent) {
      if (parent.equals(getViewSite())) {
        WorkflowRepositoryService[] repos = WorkflowRepositoryRegistry.getRepositories();
        return Arrays.stream(repos).map(r -> new WorkflowRepositoryTreeNode(r)).toArray(WorkflowRepositoryTreeNode[]::new);
      }
      return getChildren(parent);
    }
  }

  /**
   * A label provider for the repository, code and handle tree nodes. The active model handle node is marked in bold.
   */
  private class ViewLabelProvider extends LabelProvider implements IFontProvider {
    private Font boldFont;
    private TreeViewer viewer;
    private ResourceManager resourceManager;

    public ViewLabelProvider(TreeViewer viewer) {
      this.viewer = viewer;
    }

    @Override
    public Font getFont(Object element) {
      if (element instanceof ModelHandleTreeNode) {
        ModelHandleTreeNode handleNode = (ModelHandleTreeNode) element;
        if (handleNode.getRepository().isActiveModelRevision(handleNode.getValue())) {
          if (boldFont == null) {
            Font f = new Font(Display.getCurrent(), viewer.getControl().getFont().getFontData());
            FontDescriptor boldDescriptor = FontDescriptor.createFrom(f).setStyle(SWT.BOLD);
            boldFont = getResourceManager().createFont(boldDescriptor);
          }
          return boldFont;
        }
        return null;
      }
      return null;
    }

    public Image getImage(Object obj) {
      if (obj instanceof WorkflowRepositoryTreeNode) {
        return getResourceManager().createImage(RepositoryPlugin.getImageDescriptor("icons/repository.gif"));
      } else if (obj instanceof ModelCodeTreeNode) {
        return getResourceManager().createImage(RepositoryPlugin.getImageDescriptor("icons/model.gif"));
      } else if (obj instanceof ModelHandleTreeNode) {
        return getResourceManager().createImage(RepositoryPlugin.getImageDescriptor("icons/version.gif"));
      } else {
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
      }
    }

    protected synchronized ResourceManager getResourceManager() {
      if (resourceManager == null) {
        resourceManager = new LocalResourceManager(JFaceResources.getResources());
      }
      return resourceManager;
    }

    @Override
    public void dispose() {
      if (resourceManager != null) {
        resourceManager.dispose();
      }
      super.dispose();
    }
  }

  /**
   * An action to activate a selected model handle in its containing repository.
   */
  private class ActivationAction extends Action {
    private ModelHandleTreeNode node;

    /**
     * @param selNode
     */
    public ActivationAction(ModelHandleTreeNode selNode) {
      this.node = selNode;
      setText("Activate");
      setToolTipText("Activates the selected version");
      setImageDescriptor(RepositoryPlugin.getImageDescriptor("icons/activate.gif"));
    }

    @Override
    public void run() {
      try {
        node.getRepository().activateModelRevision(node.getValue());
        viewer.refresh(node.getParent());
      } catch (EntryNotFoundException e) {
        LOGGER.error(ErrorCode.ERROR + " - Inconsistent repository tree, selected handle no longer found in its repository :" + node.getValue(), e);
      }
    }
  }

  private class ImportAction extends Action {
    private AbstractTreeNode selectedNode;

    /**
     * @param selectedNode
     */
    public ImportAction(AbstractTreeNode selectedNode) {
      this.selectedNode = selectedNode;
      setText("Import");
      setToolTipText("Imports a new workflow model");
      setImageDescriptor(RepositoryPlugin.getImageDescriptor("icons/import.gif"));
    }

    @Override
    public void run() {
      ImportDialog dialog = new ImportDialog(viewer.getControl().getShell(), selectedNode);
      dialog.setBlockOnOpen(true);
      dialog.open();
      IPath momlPath = dialog.getAbsoluteMomlPath();
      String repoNode = dialog.getRepositoryNode();

      WorkflowRepositoryService repoSvc = selectedNode.getRepository();
      CompositeActor model = null;
      try {
        model = (CompositeActor) WorkflowUtils.readFrom(momlPath.toFile().toURI());
        repoSvc.commit(repoNode, model);
      } catch (DuplicateEntryException e) {
        try {
          ModelHandle existingModelHandle = repoSvc.getMostRecentModel(repoNode);
          repoSvc.update(existingModelHandle, model, false);
        } catch (EntryNotFoundException e1) {
          // should not happen
        }
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      viewer.refresh(selectedNode.getParent());
    }
  }

  private class AddToUserLibraryAction extends Action {
    private AbstractTreeNode selectedNode;

    /**
     * @param selectedNode
     */
    public AddToUserLibraryAction(AbstractTreeNode selectedNode) {
      this.selectedNode = selectedNode;
      setText("Add to user library");
      setToolTipText("Adds a repository workflow model to the user library, in the editor palette.");
      setImageDescriptor(RepositoryPlugin.getImageDescriptor("icons/import.gif"));
    }

    @Override
    public void run() {
      AddToUserLibraryDialog dialog = new AddToUserLibraryDialog(viewer.getControl().getShell(), selectedNode);
      dialog.setBlockOnOpen(true);
      int dialogReturnCode = dialog.open();
      if (Dialog.OK == dialogReturnCode) {
        String modelName = dialog.modelName;
        String modelClass = dialog.modelClass;
        String libraryName = dialog.libraryName;
        String elementType = "CompositeActor";

        Map<String, String> properties = new HashMap<>();
        properties.put("displayName", modelName);
        properties.put("class", modelClass);
        properties.put("type", elementType);
        properties.put("libraryName", libraryName);

        Event event = new Event("org/eclipse/triquetrum/workflow/userlibrary/add", properties);
        try {
          RepositoryPlugin.getDefault().getEventAdminService().postEvent(event);
        } catch (NullPointerException e) {
          StatusManager.getManager().handle(
              new Status(IStatus.ERROR, RepositoryPlugin.PLUGIN_ID, 
                  "Event bus not available, impossible to trigger an addition event for the user library."),
              StatusManager.BLOCK);
        }
      }
    }
  }

  private class DeleteAction extends Action {
    private ModelCodeTreeNode selectedNode;

    /**
     * @param selectedNode
     */
    public DeleteAction(ModelCodeTreeNode selectedNode) {
      this.selectedNode = selectedNode;
      setText("Delete");
      setToolTipText("Deletes the selected element");
      setImageDescriptor(RepositoryPlugin.getImageDescriptor("icons/delete.gif"));
    }

    @Override
    public void run() {
      boolean confirmed = MessageDialog.openConfirm(viewer.getControl().getShell(), "Confirm Delete",
          "Are you certain you want to delete all versions of " + selectedNode.getModelCode());
      if (confirmed) {
        try {
          selectedNode.getRepository().delete(selectedNode.getValue());
          viewer.refresh(selectedNode.getParent());
        } catch (EntryNotFoundException e) {
          LOGGER.error(ErrorCode.ERROR + " - Inconsistent repository tree, selected model no longer found in its repository :" + selectedNode.getValue(), e);
        }
      }
    }
  }
}
