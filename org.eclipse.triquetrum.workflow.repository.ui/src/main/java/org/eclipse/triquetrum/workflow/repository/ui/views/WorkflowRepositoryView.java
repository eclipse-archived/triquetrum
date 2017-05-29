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

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
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
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
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
      dialog.open();
      String modelName = dialog.modelName;
      String modelClass = dialog.modelClass;
      String elementType = "CompositeActor";

      Map<String, String> properties = new HashMap<>();
      properties.put("displayName", modelName);
      properties.put("class", modelClass);
      properties.put("type", elementType);

      Event event = new Event("org/eclipse/triquetrum/workflow/userlibrary/add", properties);
      try {
        RepositoryPlugin.getDefault().getEventAdminService().postEvent(event);
      } catch (NullPointerException e) {
        StatusManager.getManager()
        .handle(new Status(IStatus.ERROR, RepositoryPlugin.PLUGIN_ID,
            "Event bus not available, impossible to trigger an addition event for the user library."),
            StatusManager.BLOCK);
      }
    }
  }

  private class AddToUserLibraryDialog extends Dialog {
    private AbstractTreeNode selectedNode;
    private Text modelNameField;
    private Text modelClassField;

    String modelName;
    String modelClass;

    protected AddToUserLibraryDialog(Shell parentShell, AbstractTreeNode selectedNode) {
      super(parentShell);
      this.selectedNode = selectedNode;
      setShellStyle(SWT.RESIZE | SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      if (selectedNode instanceof ModelCodeTreeNode) {
        modelName = ((ModelCodeTreeNode) selectedNode).getModelCode();
        modelClass = ((ModelCodeTreeNode) selectedNode).getModelCode();
      }
    }

    @Override
    protected Point getInitialSize() {
      return new Point(300, 200);
    }

    @Override
    protected void configureShell(Shell shell) {
      super.configureShell(shell);
      shell.setText("Add to User Library");
    }

    // TODO add Ok disable/enable depending on text field contents
    @Override
    protected Control createDialogArea(Composite parent) {
      Composite container = new Composite(parent, SWT.NULL);
      final GridLayout gridLayout = new GridLayout();
      gridLayout.numColumns = 2;
      container.setLayout(gridLayout);
      container.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
      container.setFont(parent.getFont());

      final Label modelNameBoxLabel = new Label(container, SWT.NONE);
      final GridData modelCodeBoxLabelLayout = new GridData(GridData.HORIZONTAL_ALIGN_END);
      modelNameBoxLabel.setLayoutData(modelCodeBoxLabelLayout);
      modelNameBoxLabel.setText("Model name:");

      modelNameField = new Text(container, SWT.BORDER);
      modelNameField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      modelNameField.setText(modelName);

      final Label modelClassBoxLabel = new Label(container, SWT.NONE);
      final GridData modelClassBoxLabelLayout = new GridData(GridData.HORIZONTAL_ALIGN_END);
      modelClassBoxLabel.setLayoutData(modelClassBoxLabelLayout);
      modelClassBoxLabel.setText("Model class:");

      modelClassField = new Text(container, SWT.BORDER);
      modelClassField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      modelClassField.setText(modelClass);
      modelClassField.setEditable(false);

      return container;
    }

    @Override
    protected void buttonPressed(int buttonId) {
      if (buttonId == IDialogConstants.OK_ID) {
        modelName = modelNameField.getText().trim();
        modelClass = modelClassField.getText().trim();
      } else {
        modelName = null;
        modelClass = null;
      }
      super.buttonPressed(buttonId);
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

  private class ImportDialog extends Dialog {
    private Text momlPathField;
    private AbstractTreeNode selectedNode;
    private Text modelCodeField;

    private String momlPath;
    private String modelCode;

    protected ImportDialog(Shell parentShell, AbstractTreeNode selectedNode) {
      super(parentShell);
      this.selectedNode = selectedNode;
      setShellStyle(SWT.RESIZE | SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      if (selectedNode instanceof ModelCodeTreeNode) {
        modelCode = ((ModelCodeTreeNode) selectedNode).getModelCode();
      }
    }

    @Override
    protected Point getInitialSize() {
      return new Point(300, 200);
    }

    @Override
    protected void configureShell(Shell shell) {
      super.configureShell(shell);
      shell.setText("Import a new model");
    }

    // TODO add Ok disable/enable depending on text field contents
    @Override
    protected Control createDialogArea(Composite parent) {
      Composite container = new Composite(parent, SWT.NULL);
      final GridLayout gridLayout = new GridLayout();
      gridLayout.numColumns = 3;
      container.setLayout(gridLayout);
      container.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
      container.setFont(parent.getFont());

      final Label momlPathLabel = new Label(container, SWT.NONE);
      final GridData momlPathLabelLayout = new GridData();
      momlPathLabelLayout.horizontalSpan = 3;
      momlPathLabel.setLayoutData(momlPathLabelLayout);
      momlPathLabel.setText("Select the Model file to import");

      final Label momlPathBoxLabel = new Label(container, SWT.NONE);
      final GridData momlPathBoxLabelLayout = new GridData(GridData.HORIZONTAL_ALIGN_END);
      momlPathBoxLabel.setLayoutData(momlPathBoxLabelLayout);
      momlPathBoxLabel.setText("Model file:");

      momlPathField = new Text(container, SWT.BORDER);
      momlPathField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

      final Button browseButton = new Button(container, SWT.NONE);
      browseButton.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e) {
          IPath path = browse(getAbsoluteMomlPath());
          if (path == null)
            return;
          IPath rootLoc = ResourcesPlugin.getWorkspace().getRoot().getLocation();
          if (rootLoc.isPrefixOf(path))
            path = path.setDevice(null).removeFirstSegments(rootLoc.segmentCount());
          momlPathField.setText(path.toString());
          if (modelCodeField != null && modelCodeField.getText().isEmpty()) {
            String defaultCode = path.removeFileExtension().lastSegment();
            modelCodeField.setText(defaultCode);
          }
        }
      });
      browseButton.setText("Browse...");

      if (selectedNode instanceof WorkflowRepositoryTreeNode) {
        // No specific asset code selected yet, so the user must be able to select an existing one
        // or create a new one.
        WorkflowRepositoryService repoSvc = ((WorkflowRepositoryTreeNode) selectedNode).getRepository();
        final Label modelCodeBoxLabel = new Label(container, SWT.NONE);
        final GridData modelCodeBoxLabelLayout = new GridData(GridData.HORIZONTAL_ALIGN_END);
        modelCodeBoxLabel.setLayoutData(modelCodeBoxLabelLayout);
        modelCodeBoxLabel.setText("Model name:");

        modelCodeField = new Text(container, SWT.BORDER);
        modelCodeField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        final Button selectButton = new Button(container, SWT.NONE);
        selectButton.addSelectionListener(new SelectionAdapter() {
          @Override
          public void widgetSelected(SelectionEvent e) {
            String modelCode = selectModelCode(repoSvc);
            if (modelCode == null)
              return;
            modelCodeField.setText(modelCode.toString());
          }
        });
        selectButton.setText("Select...");
      }
      return container;
    }

    @Override
    protected void buttonPressed(int buttonId) {
      if (buttonId == IDialogConstants.OK_ID) {
        momlPath = momlPathField.getText().trim();
        if (modelCodeField != null) {
          modelCode = modelCodeField.getText().trim();
        }
      } else {
        momlPath = null;
        modelCode = null;
      }
      super.buttonPressed(buttonId);
    }

    /**
     * Tries to resolve the contents of the moml path field to an IPath, and returns it. If the field contains a relative path it will be resolved relative to
     * the Eclipse workspace folder.
     *
     * @return the absolute IPath to the MOML file, or null
     */
    protected IPath getAbsoluteMomlPath() {
      if (momlPath != null && momlPath.length() > 0) {
        return getAbsolutePath(new Path(momlPath));
      } else {
        return null;
      }
    }

    protected String getRepositoryNode() {
      return modelCode;
    }

    private IPath getAbsolutePath(IPath path) {
      if (!path.isAbsolute()) {
        // relative paths are relative to the Eclipse workspace
        path = ResourcesPlugin.getWorkspace().getRoot().getLocation().append(path);
      }
      return path;
    }

    /**
     * Sets up a dialog box allowing the user to browse the file system and select a file.
     *
     * @param path
     *          the path to be investigated
     * @return the chosen path from the dialog box
     */
    private IPath browse(IPath path) {
      FileDialog dialog = new FileDialog(getShell());
      // TODO check if these file extensions work as expected on our supported platforms
      // (win,lin,mac)
      dialog.setFilterExtensions(new String[] { "*.moml;*.xml" });
      if (path != null) {
        if (path.segmentCount() > 1)
          dialog.setFilterPath(path.toOSString());
      }
      String result = dialog.open();
      if (result == null)
        return null;
      return new Path(result);
    }

    private String selectModelCode(WorkflowRepositoryService repoSvc) {
      ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), new LabelProvider());
      dialog.setTitle("Repository import location");
      dialog.setMessage("Select the model's location in the repository (* = any string, ? = any char):");
      dialog.setElements(repoSvc.getAllModelCodes());
      dialog.open();
      return (String) dialog.getFirstResult();
    }
  }
}
