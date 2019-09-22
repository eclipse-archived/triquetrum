/*******************************************************************************
 * Copyright (c) 2017,2019 iSencia Belgium NV.
 *  
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.repository.ui.views;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.triquetrum.workflow.WorkflowRepositoryService;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

class ImportDialog extends Dialog {
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