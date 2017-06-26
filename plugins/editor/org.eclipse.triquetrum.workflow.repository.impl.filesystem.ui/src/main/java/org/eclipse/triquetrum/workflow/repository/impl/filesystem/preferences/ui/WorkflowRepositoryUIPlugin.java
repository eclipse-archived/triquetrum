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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.triquetrum.workflow.repository.impl.filesystem.WorkflowRepositoryPreferencesSupplier;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.BundleContext;

public class WorkflowRepositoryUIPlugin extends AbstractUIPlugin {

  private static WorkflowRepositoryUIPlugin pluginInstance;

  /**
   * Storage for preferences.
   */
  private ScopedPreferenceStore preferenceStore;

  public WorkflowRepositoryUIPlugin() {
    pluginInstance = this;
  }

  public static WorkflowRepositoryUIPlugin getDefault() {
    return pluginInstance;
  }
  
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    // cfr https://www.eclipse.org/forums/index.php/t/105270/
    // for some reason, after having moved the prefs UI stuff out of the main editor bundle, 
    new WorkflowRepositoryPreferenceInitializer().initializeDefaultPreferences();
  }

  /**
   * Uses the preferences store of the repository impl bundle so that the repository impl can use std preferences APIs to access the preference values, i.e.
   * without becoming dependent on Eclipse Plugin APIs and even without needing to be aware of the existence of preference pages etc.
   */
  @Override
  public IPreferenceStore getPreferenceStore() {
    if (preferenceStore == null) {
      preferenceStore = new ScopedPreferenceStore(WorkflowRepositoryPreferencesSupplier.SCOPE_CONTEXT, WorkflowRepositoryPreferencesSupplier.PREFERENCES_NODE);
    }
    return preferenceStore;
  }
}
