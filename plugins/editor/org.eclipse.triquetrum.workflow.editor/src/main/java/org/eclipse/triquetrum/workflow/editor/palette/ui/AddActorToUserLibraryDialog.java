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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.triquetrum.workflow.ModelHandle;

/**
 * This dialog is shown when an actor is drag-n-dropped in a user library,
 * to allow to define basis properties like the actor's name in the library.
 *
 */
class AddActorToUserLibraryDialog extends Dialog {
  private Text modelNameField;
  private Text modelClassField;

  String modelName;
  String modelClass;

  protected AddActorToUserLibraryDialog(Shell parentShell, ModelHandle selectedNode) {
    super(parentShell);
    setShellStyle(SWT.RESIZE | SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
    modelClass = selectedNode.getCode();
    modelName = modelClass.substring(modelClass.lastIndexOf('.') + 1);
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