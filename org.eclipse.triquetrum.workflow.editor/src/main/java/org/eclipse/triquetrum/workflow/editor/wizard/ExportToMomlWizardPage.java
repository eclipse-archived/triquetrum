/*******************************************************************************
 * Copyright (c) 2015-2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.wizard;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.dialogs.WizardExportResourcesPage;

public class ExportToMomlWizardPage extends WizardExportResourcesPage {

  private Text exportFolderPathField;
  private Button browseButton;

  public ExportToMomlWizardPage(String pageName, IStructuredSelection selection) {
    super(pageName, selection);
  }

  public boolean finish() {
    if (!ensureTargetIsValid(getAbsoluteExportFolderPath().toFile())) {
      return false;
    }
    List<?> resourcesToExport = getWhiteCheckedResources();

    // Save dirty editors if possible but do not stop if not all are saved
    saveDirtyEditors();
    // about to invoke the operation so save our state
    saveWidgetValues();

    return executeExportOperation(new MomlExportOperation(null, resourcesToExport, exportFolderPathField.getText().trim(), this));
  }

  /**
   *  Set up and execute the passed Operation.  Answer a boolean indicating success.
   *
   *  @return boolean
   */
  protected boolean executeExportOperation(MomlExportOperation op) {
      op.setCreateLeadupStructure(false);
      op.setOverwriteFiles(false);

      try {
          getContainer().run(true, true, op);
      } catch (InterruptedException e) {
          return false;
      } catch (InvocationTargetException e) {
          displayErrorDialog(e.getTargetException());
          return false;
      }

      IStatus status = op.getStatus();
      if (!status.isOK()) {
          ErrorDialog.openError(getContainer().getShell(),
                  "Export Problems",
                  null, // no special message
                  status);
          return false;
      }

      return true;
  }

  /**
   * If the target for export does not exist then attempt to create it. Answer a boolean indicating whether the target exists (ie.- if it either pre-existed or
   * this method was able to create it)
   *
   * @return boolean
   */
  protected boolean ensureTargetIsValid(File targetDirectory) {
    if (targetDirectory.exists() && !targetDirectory.isDirectory()) {
      displayErrorDialog("Target directory already exists as a file");
      exportFolderPathField.setFocus();
      return false;
    }
    return ensureDirectoryExists(targetDirectory);
  }

  /**
   * Attempts to ensure that the specified directory exists on the local file system. Answers a boolean indicating success.
   *
   * @return boolean
   * @param directory
   *          java.io.File
   */
  protected boolean ensureDirectoryExists(File directory) {
    if (!directory.exists()) {
      if (!queryYesNoQuestion("Target directory does not exist.  Would you like to create it?")) {
        return false;
      }
      if (!directory.mkdirs()) {
        displayErrorDialog("Target directory could not be created.");
        exportFolderPathField.setFocus();
        return false;
      }
    }
    return true;
  }

  @Override
  public void handleEvent(Event event) {
    Widget source = event.widget;
    if (source == browseButton) {
      IPath path = browse(getAbsoluteExportFolderPath());
      if (path == null)
        return;
      IPath rootLoc = ResourcesPlugin.getWorkspace().getRoot().getLocation();
      if (rootLoc.isPrefixOf(path))
        path = path.setDevice(null).removeFirstSegments(rootLoc.segmentCount());
      exportFolderPathField.setText(path.toString());
    }
    updatePageCompletion();
  }

  @Override
  protected void createDestinationGroup(Composite parent) {
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
    momlPathLabel.setText("Select the MOML export destination");

    final Label momlPathBoxLabel = new Label(container, SWT.NONE);
    final GridData momlPathBoxLabelLayout = new GridData(GridData.HORIZONTAL_ALIGN_END);
    momlPathBoxLabel.setLayoutData(momlPathBoxLabelLayout);
    momlPathBoxLabel.setText("Export folder:");

    exportFolderPathField = new Text(container, SWT.BORDER);
    exportFolderPathField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    browseButton = new Button(container, SWT.PUSH);
    browseButton.addListener(SWT.Selection, this);
    browseButton.setText("Browse...");
  }

  /**
   * Sets up a dialog box allowing the user to browse the file system and select a file.
   *
   * @param path
   *          the path to be investigated
   * @return the chosen path from the dialog box
   */
  private IPath browse(IPath path) {
    DirectoryDialog dialog = new DirectoryDialog(getContainer().getShell(), SWT.SAVE | SWT.SHEET);
    if (path != null) {
      if (path.segmentCount() > 1)
        dialog.setFilterPath(path.toOSString());
    }
    String result = dialog.open();
    if (result == null)
      return null;
    return new Path(result);
  }

  /**
   * Tries to resolve the contents of the export folder path field to an IPath, and returns it. If the field contains a relative path it will be resolved
   * relative to the Eclipse workspace folder.
   *
   * @return the absolute IPath to the export folder, or null
   */
  protected IPath getAbsoluteExportFolderPath() {
    String text = exportFolderPathField.getText().trim();
    if (text.length() == 0)
      return null;
    return getAbsolutePath(new Path(text));
  }

  private IPath getAbsolutePath(IPath path) {
    if (!path.isAbsolute()) {
      // relative paths are relative to the Eclipse workspace
      path = ResourcesPlugin.getWorkspace().getRoot().getLocation().append(path);
    }
    return path;
  }
}
