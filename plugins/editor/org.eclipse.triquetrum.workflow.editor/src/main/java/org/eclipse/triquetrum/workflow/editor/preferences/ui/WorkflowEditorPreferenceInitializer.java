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

import static org.eclipse.triquetrum.workflow.editor.features.TriqDefaultDeleteFeature.DELETE_CONFIRMATION_DEFVALUE;
import static org.eclipse.triquetrum.workflow.editor.features.TriqDefaultDeleteFeature.DELETE_CONFIRMATION_PREFNAME;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;

/**
 * Initializes editor-related preferences to their default value.
 * <p>
 * Currently this handles :
 * <li>should a Delete confirmation dialog be shown or not : default is true</li>
 * </p>
 *
 */
public class WorkflowEditorPreferenceInitializer extends AbstractPreferenceInitializer {

  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore store = TriqEditorPlugin.getDefault().getPreferenceStore();
    store.setDefault(DELETE_CONFIRMATION_PREFNAME, DELETE_CONFIRMATION_DEFVALUE);
  }
}
