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

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.WizardResourceImportPage;

/**
 * The wizard page to select the MOML file that must be imported.
 *
 */
public class ImportFromMomlWizardPage extends WizardResourceImportPage {

  private Text momlPathField;

  public ImportFromMomlWizardPage(String name, IStructuredSelection selection) {
    super(name, selection);
    setTitle("Import MOML file");
    setDescription("Import an existing Ptolemy II MOML file");
  }

  @Override
  protected boolean allowNewContainerName() {
    return false;
  }

  /**
   * Returns a content provider for <code>FileSystemElement</code>s that returns only files as children.
   */
  @Override
  protected ITreeContentProvider getFileProvider() {
    return null;
  }

  @Override
  protected ITreeContentProvider getFolderProvider() {
    return null;
  }

  protected void createSourceGroup(Composite parent) {
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
    momlPathLabel.setText("Select the MOML file to import");

    final Label momlPathBoxLabel = new Label(container, SWT.NONE);
    final GridData momlPathBoxLabelLayout = new GridData(GridData.HORIZONTAL_ALIGN_END);
    momlPathBoxLabel.setLayoutData(momlPathBoxLabelLayout);
    momlPathBoxLabel.setText("MOML file:");

    momlPathField = new Text(container, SWT.BORDER);
    momlPathField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    final Button browseButton = new Button(container, SWT.NONE);
    browseButton.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        IPath path = browse(getAbsoluteMomlPath());
        if (path == null)
          return;
        IPath rootLoc = ResourcesPlugin.getWorkspace().getRoot().getLocation();
        if (rootLoc.isPrefixOf(path))
          path = path.setDevice(null).removeFirstSegments(rootLoc.segmentCount());
        momlPathField.setText(path.toString());
        setPageComplete(determinePageCompletion());
        updateWidgetEnablements();
      }
    });
    browseButton.setText("Browse...");
  }

  @Override
  protected boolean validateSourceGroup() {
    return super.validateSourceGroup() && validateSelectedPath();
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

  /**
   * Does some validation of the selected path before enabling the finish button.
   *
   * @return true if the currently selected moml path points to an existing moml file.
   */
  private boolean validateSelectedPath() {
    // check path first
    IPath momlPath = getAbsoluteMomlPath();
    if (momlPath == null || !momlPath.toFile().exists()) {
      setErrorMessage(null);
      setMessage("Please select a MOML file.");
      return false;
    }
    // if nothing was caught, enable the finish button
    setMessage("Press Finish to import the Ptolemy II model");
    setErrorMessage(null);
    return true;
  }

  /**
   * Tries to resolve the contents of the moml path field to an IPath, and returns it.
   * If the field contains a relative path it will be resolved relative to the Eclipse workspace folder.
   *
   * @return the absolute IPath to the MOML file, or null
   */
  protected IPath getAbsoluteMomlPath() {
    String text = momlPathField.getText().trim();
    if (text.length() == 0)
      return null;
    return getAbsolutePath(new Path(text));
  }

  /**
   *
   * @return the workspace-relative IPath to the project/container in the workspace, where the MOML file must be imported
   */
  protected IPath getDestinationContainerPath() {
    return super.getContainerFullPath();
  }

  private IPath getAbsolutePath(IPath path) {
    if (!path.isAbsolute()) {
      // relative paths are relative to the Eclipse workspace
      path = ResourcesPlugin.getWorkspace().getRoot().getLocation().append(path);
    }
    return path;
  }
}
