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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.triquetrum.workflow.EntryNotFoundException;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.eclipse.triquetrum.workflow.WorkflowRepositoryRegistry;
import org.eclipse.triquetrum.workflow.WorkflowRepositoryService;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

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

  private TreeViewer viewer;
  private DrillDownAdapter drillDownAdapter;
  private Action doubleClickAction;

  class ViewContentProvider extends TreeNodeContentProvider {
    public Object[] getElements(Object parent) {
      if (parent.equals(getViewSite())) {
        WorkflowRepositoryService[] repos = WorkflowRepositoryRegistry.getRepositories();
        return Arrays.stream(repos).map(r -> new WorkflowRepositoryTreeNode(r)).toArray(WorkflowRepositoryTreeNode[]::new);
      }
      return getChildren(parent);
    }
  }

  class ViewLabelProvider extends LabelProvider {

    public String getText(Object obj) {
      if (obj instanceof ModelHandle) {
        return ((ModelHandle) obj).getVersion().toString();
      }
      return obj.toString();
    }

    public Image getImage(Object obj) {
      String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
      if (obj instanceof WorkflowRepositoryTreeNode)
        imageKey = ISharedImages.IMG_OBJ_FOLDER;
      return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
    }
  }
  
  static class ActivationAction extends Action {
    private ModelHandleTreeNode node;

    /**
     * @param selNode
     */
    public ActivationAction(ModelHandleTreeNode selNode) {
      this.node = selNode;
      setText("Activate");
      setToolTipText("Activates the selected version");
      setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
    }
    
    @Override
    public void run() {
      try {
        node.getParent().getParent().getValue().activateModelRevision(node.getValue());
      } catch (EntryNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * The constructor.
   */
  public WorkflowRepositoryView() {
  }

  /**
   * This is a callback that will allow us to create the viewer and initialize it.
   */
  public void createPartControl(Composite parent) {
    viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    drillDownAdapter = new DrillDownAdapter(viewer);

    viewer.setContentProvider(new ViewContentProvider());
    viewer.setInput(getViewSite());
    viewer.setLabelProvider(new ViewLabelProvider());

    // Create the help context id for the viewer's control
    PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.eclipse.triquetrum.workflow.repository.ui.viewer");
    getSite().setSelectionProvider(viewer);
    makeActions();
    hookContextMenu();
    hookDoubleClickAction();
  }

  private void hookContextMenu() {
    MenuManager menuMgr = new MenuManager("#PopupMenu");
    menuMgr.setRemoveAllWhenShown(true);
    menuMgr.addMenuListener(new IMenuListener() {
      public void menuAboutToShow(IMenuManager manager) {
        WorkflowRepositoryView.this.fillContextMenu(manager);
      }
    });
    Menu menu = menuMgr.createContextMenu(viewer.getControl());
    viewer.getControl().setMenu(menu);
    getSite().registerContextMenu(menuMgr, viewer);
  }

  private void fillContextMenu(IMenuManager manager) {
    ISelection selection = viewer.getSelection();
    if (selection instanceof TreeSelection) {
      TreeSelection treeSelection = (TreeSelection) selection;
      if (treeSelection.size() == 1) {
        Object selObj = treeSelection.getFirstElement();
        if (selObj instanceof ModelHandleTreeNode) {
          ModelHandleTreeNode selNode = (ModelHandleTreeNode) selObj;
          Action action1 = new ActivationAction(selNode);
          manager.add(action1);
        } else if (selObj instanceof WorkflowRepositoryService) {
          drillDownAdapter.addNavigationActions(manager);
        }
        manager.add(new Separator());
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
      }
    }
  }

  private void makeActions() {
    doubleClickAction = new Action() {
      public void run() {
        ISelection selection = viewer.getSelection();
        Object obj = ((IStructuredSelection) selection).getFirstElement();
        showMessage("Double-click detected on " + obj.toString());
      }
    };
  }

  private void hookDoubleClickAction() {
    viewer.addDoubleClickListener(new IDoubleClickListener() {
      public void doubleClick(DoubleClickEvent event) {
        doubleClickAction.run();
      }
    });
  }

  private void showMessage(String message) {
    MessageDialog.openInformation(viewer.getControl().getShell(), "Workflow Repositories", message);
  }

  /**
   * Passing the focus request to the viewer's control.
   */
  public void setFocus() {
    viewer.getControl().setFocus();
  }
}
