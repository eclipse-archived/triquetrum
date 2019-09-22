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
