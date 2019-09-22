/*******************************************************************************
 * Copyright (c) 2016,2019 iSencia Belgium NV.
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
package org.eclipse.triquetrum.workflow.editor.palette.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

class AddFolderToUserLibraryDialog extends Dialog {
  private Text folderNameField;

  String folderName = "new";

  protected AddFolderToUserLibraryDialog(Shell parentShell) {
    super(parentShell);
    setShellStyle(SWT.RESIZE | SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
  }

  @Override
  protected org.eclipse.swt.graphics.Point getInitialSize() {
    return new org.eclipse.swt.graphics.Point(300, 120);
  }

  @Override
  protected void configureShell(Shell shell) {
    super.configureShell(shell);
    shell.setText("Add folder to User Library");
  }

  // TODO add Ok disable/enable depending on text field contents
  @Override
  protected Control createDialogArea(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    final GridLayout gridLayout = new GridLayout();
    gridLayout.numColumns = 2;
    container.setLayout(gridLayout);
    container.setLayoutData(
        new org.eclipse.swt.layout.GridData(org.eclipse.swt.layout.GridData.HORIZONTAL_ALIGN_FILL | org.eclipse.swt.layout.GridData.GRAB_HORIZONTAL));
    container.setFont(parent.getFont());

    final Label folderNameBoxLabel = new Label(container, SWT.NONE);
    final org.eclipse.swt.layout.GridData folderNameBoxLabelLayout = new org.eclipse.swt.layout.GridData(
        org.eclipse.swt.layout.GridData.HORIZONTAL_ALIGN_END);
    folderNameBoxLabel.setLayoutData(folderNameBoxLabelLayout);
    folderNameBoxLabel.setText("Folder name:");

    folderNameField = new Text(container, SWT.BORDER);
    folderNameField.setLayoutData(new org.eclipse.swt.layout.GridData(org.eclipse.swt.layout.GridData.FILL_HORIZONTAL));
    folderNameField.setText(folderName);

    return container;
  }

  @Override
  protected void buttonPressed(int buttonId) {
    if (buttonId == IDialogConstants.OK_ID) {
      folderName = folderNameField.getText().trim();
    } else {
      folderName = null;
    }
    super.buttonPressed(buttonId);
  }
}