/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.repository.impl.filesystem.activator;

import java.io.File;
import java.util.Hashtable;

import org.eclipse.triquetrum.workflow.WorkflowRepositoryService;
import org.eclipse.triquetrum.workflow.repository.impl.filesystem.WorkflowRepositoryServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

  private WorkflowRepositoryService repoSvc;
  private ServiceRegistration<WorkflowRepositoryService> repoSvcReg;

  @Override
  public void start(BundleContext context) throws Exception {
    File userHome = new File(System.getProperty("user.home"));
    File defaultRootFolderPath = new File(userHome, ".triquetrum/workflow-repository");
    String rootFolderPath = System.getProperty("org.eclipse.triquetrum.workflow.repository.root", defaultRootFolderPath.getAbsolutePath());
    repoSvc = new WorkflowRepositoryServiceImpl(rootFolderPath);
    Hashtable<String, String> svcProps = new Hashtable<>();
    svcProps.put("type", "FILE");
    repoSvcReg = context.registerService(WorkflowRepositoryService.class, repoSvc, svcProps);
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    repoSvcReg.unregister();
    repoSvc = null;
  }

  public WorkflowRepositoryService getRepositoryService() {
    return repoSvc;
  }
}
