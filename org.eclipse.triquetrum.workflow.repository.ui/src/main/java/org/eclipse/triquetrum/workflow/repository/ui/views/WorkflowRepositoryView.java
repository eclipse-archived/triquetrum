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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
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
import org.eclipse.triquetrum.workflow.EntryNotFoundException;
import org.eclipse.triquetrum.workflow.WorkflowRepositoryRegistry;
import org.eclipse.triquetrum.workflow.WorkflowRepositoryService;
import org.eclipse.triquetrum.workflow.repository.ui.RepositoryPlugin;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view shows data obtained from the model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model available either in this or another plug-in (e.g. the workspace). The view is connected to the model
 * using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be presented in the view. Each view can present the same model objects using different
 * labels and icons, if needed. Alternatively, a single label provider can be shared between views in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class WorkflowRepositoryView extends ViewPart {
  /**
   * The ID of the view as specified by the extension.
   */
  public static final String ID = "org.eclipse.triquetrum.workflow.repository.ui.views.WorkflowRepositoryView";

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
   * hooks a context menu manager to a selected tree node.
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
              manager.add(new ActivationAction((ModelHandleTreeNode) selObj));
            } else if (selObj instanceof WorkflowRepositoryService) {
              drillDownAdapter.addNavigationActions(manager);
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
}
