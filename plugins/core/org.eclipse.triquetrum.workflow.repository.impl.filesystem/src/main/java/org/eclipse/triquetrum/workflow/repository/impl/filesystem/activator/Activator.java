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
package org.eclipse.triquetrum.workflow.repository.impl.filesystem.activator;


import static org.eclipse.triquetrum.workflow.repository.impl.filesystem.WorkflowRepositoryPreferencesSupplier.REPOSITORY_LOCATION_DEFVALUE;
import static org.eclipse.triquetrum.workflow.repository.impl.filesystem.WorkflowRepositoryPreferencesSupplier.REPOSITORY_LOCATION_PREFNAME;
import static org.eclipse.triquetrum.workflow.repository.impl.filesystem.WorkflowRepositoryPreferencesSupplier.getPreferencesRootNode;

import java.io.File;
import java.util.Hashtable;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.triquetrum.workflow.WorkflowRepositoryService;
import org.eclipse.triquetrum.workflow.repository.impl.filesystem.WorkflowRepositoryServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
  public static final String BUNDLE_ID = "org.eclipse.triquetrum.workflow.repository.impl.filesystem";

  private WorkflowRepositoryServiceImpl repoSvc;
  private ServiceRegistration<WorkflowRepositoryService> repoSvcReg;

  private IPreferenceChangeListener preferenceChangeListener;

  @Override
  public void start(BundleContext context) throws Exception {
    final IEclipsePreferences node = getPreferencesRootNode();
    repoSvc = new WorkflowRepositoryServiceImpl(node.get(REPOSITORY_LOCATION_PREFNAME, REPOSITORY_LOCATION_DEFVALUE));
    preferenceChangeListener = new IPreferenceChangeListener() {
      @Override
      public void preferenceChange(PreferenceChangeEvent event) {
        if(REPOSITORY_LOCATION_PREFNAME.equals(event.getKey()) && (repoSvc != null)) {
          // it seems that when you Restore Defaults for preferences, this gives a new value null i.o. the default value!
          String newValue = (event.getNewValue()!=null)? (String) event.getNewValue() : REPOSITORY_LOCATION_DEFVALUE;
          repoSvc.setRootFolder(new File(newValue));
        }
      }
    };
    node.addPreferenceChangeListener(preferenceChangeListener);
    Hashtable<String, String> svcProps = new Hashtable<>();
    svcProps.put("type", "FILE");
    repoSvcReg = context.registerService(WorkflowRepositoryService.class, repoSvc, svcProps);
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    getPreferencesRootNode().removePreferenceChangeListener(preferenceChangeListener);
    repoSvcReg.unregister();
    repoSvc = null;
  }

  public WorkflowRepositoryService getRepositoryService() {
    return repoSvc;
  }
}
