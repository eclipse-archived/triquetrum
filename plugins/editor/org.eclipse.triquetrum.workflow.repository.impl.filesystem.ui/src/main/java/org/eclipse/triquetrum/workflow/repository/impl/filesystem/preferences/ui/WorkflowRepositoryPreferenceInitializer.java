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
package org.eclipse.triquetrum.workflow.repository.impl.filesystem.preferences.ui;

import static org.eclipse.triquetrum.workflow.repository.impl.filesystem.WorkflowRepositoryPreferencesSupplier.REPOSITORY_LOCATION_DEFVALUE;
import static org.eclipse.triquetrum.workflow.repository.impl.filesystem.WorkflowRepositoryPreferencesSupplier.REPOSITORY_LOCATION_PREFNAME;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class WorkflowRepositoryPreferenceInitializer extends AbstractPreferenceInitializer {

  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore store = WorkflowRepositoryUIPlugin.getDefault().getPreferenceStore();
    store.setDefault(REPOSITORY_LOCATION_PREFNAME, REPOSITORY_LOCATION_DEFVALUE);
  }
}
