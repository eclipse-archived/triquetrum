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
package org.eclipse.triquetrum.workflow.repository.impl.filesystem.preferences.ui;

import static org.eclipse.triquetrum.workflow.repository.impl.filesystem.WorkflowRepositoryPreferencesSupplier.REPOSITORY_LOCATION_PREFNAME;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preferences page to configure the WorkflowRepositoryService.
 * <p>
 * Currently we only support a plain file-based repository. This page allows to configure the location of the repository root folder.
 * </p>
 */
public class WorkflowRepositoryPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

  public WorkflowRepositoryPreferencePage() {
    super(GRID);
  }

  @Override
  public void init(IWorkbench workbench) {
    setPreferenceStore(WorkflowRepositoryUIPlugin.getDefault().getPreferenceStore());
    setDescription("Configure the location of the workflow repository\n\n");
  }

  @Override
  protected void createFieldEditors() {
    addField(new DirectoryFieldEditor(REPOSITORY_LOCATION_PREFNAME, "&Repository location:", getFieldEditorParent()));
  }

}
