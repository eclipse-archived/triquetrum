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
package org.eclipse.triquetrum.workflow.editor.preferences.ui;

import static org.eclipse.triquetrum.workflow.editor.features.TriqDefaultDeleteFeature.DELETE_CONFIRMATION_PREFNAME;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preferences page to configure the WorkflowRepositoryService.
 * <p>
 * Currently we only support a plain file-based repository. This page allows to configure the location of the repository root folder.
 * </p>
 */
public class WorkflowEditorPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

  public WorkflowEditorPreferencePage() {
    super(GRID);
  }

  @Override
  public void init(IWorkbench workbench) {
    setPreferenceStore(TriqEditorPlugin.getDefault().getPreferenceStore());
    setDescription("Configure the workflow editor\n\n");
  }

  @Override
  protected void createFieldEditors() {
    addField(new BooleanFieldEditor(DELETE_CONFIRMATION_PREFNAME, "Deleting model elements requires confirmation", getFieldEditorParent()));
  }

}
