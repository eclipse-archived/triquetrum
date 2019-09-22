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
package org.eclipse.triquetrum.workflow.repository.impl.filesystem;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.triquetrum.workflow.repository.impl.filesystem.activator.Activator;

/**
 * Inspired by a structure described by Philip Wenig on the OpenChrom blog.
 * 
 * @see <a href="https://openchrom.wordpress.com/2014/01/11/how-to-handle-preferences-consistently/">How to handle preferences</a>
 *
 */
public class WorkflowRepositoryPreferencesSupplier {

  /**
   * the name of the system property that can be used to set a custom default root folder.
   * If the property is not set, <code>"${user.home}/.triquetrum/workflow-repository"</code> is used as default.
   */
  public static final String REPOSITORY_LOCATION_PROPNAME = "org.eclipse.triquetrum.workflow.repository.root";

  /**
   * the scope of the preferences is InstanceScope
   */
  public static final IScopeContext SCOPE_CONTEXT = InstanceScope.INSTANCE;
  /**
   * the node within the scope is the symbolic name of this bundle.
   */
  public static final String PREFERENCES_NODE = Activator.BUNDLE_ID;

  /**
   * the name of the preference to store the repository location
   */
  public static final String REPOSITORY_LOCATION_PREFNAME = "repository.location";
  /**
   * the default value for the repository location.
   * By default it is <code>"${user.home}/.triquetrum/workflow-repository"</code>, but this can be overridden via a system property.
   * 
   * @see REPOSITORY_LOCATION_PROPNAME
   */
  public static final String REPOSITORY_LOCATION_DEFVALUE;

  private static final Map<String, String> INITIALIZATIONS = new HashMap<>();

  static {
    File userHome = new File(System.getProperty("user.home"));
    File defaultRootFolderPath = new File(userHome, ".triquetrum/workflow-repository");
    REPOSITORY_LOCATION_DEFVALUE = System.getProperty(REPOSITORY_LOCATION_PROPNAME, defaultRootFolderPath.getAbsolutePath());
    INITIALIZATIONS.put(REPOSITORY_LOCATION_PREFNAME, REPOSITORY_LOCATION_DEFVALUE);
  }

  /**
   * Supplies the initialization entries for the preferences of the repository implementation.
   * <p>
   * Each map entry has a key for a preference name, and the corresponding default value that must be set.
   * </p>
   * 
   * @return the entries that must be used by a preferences initializer.
   */
  public static Map<String, String> getPreferenceInitializations() {
    return Collections.unmodifiableMap(INITIALIZATIONS);
  }

  /**
   * 
   * @return the root node for managing preferences of the repository implementation.
   */
  public static IEclipsePreferences getPreferencesRootNode() {
    return SCOPE_CONTEXT.getNode(PREFERENCES_NODE);
  }
}
