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
