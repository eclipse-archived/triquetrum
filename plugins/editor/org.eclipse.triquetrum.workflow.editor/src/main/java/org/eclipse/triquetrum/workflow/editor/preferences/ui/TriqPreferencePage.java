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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The empty root preferences page for Triquetrum.
 *
 */
public class TriqPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

  protected IPreferenceStore doGetPreferenceStore() {
    return TriqEditorPlugin.getDefault().getPreferenceStore();
  }

  @Override
  public void init(IWorkbench workbench) {
    setDescription("Preferences for editing and running Triquetrum workflows");
    noDefaultAndApplyButton();
  }

  @Override
  protected Control createContents(Composite parent) {
    return new Composite(parent, SWT.NULL);
  }
}
