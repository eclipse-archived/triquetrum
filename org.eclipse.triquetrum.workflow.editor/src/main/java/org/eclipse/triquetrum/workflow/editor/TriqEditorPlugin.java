/*******************************************************************************
 * Copyright (c) 2015 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor;

import org.eclipse.triquetrum.workflow.WorkflowExecutionService;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class TriqEditorPlugin extends AbstractUIPlugin {

  private static BundleContext context;
  private static TriqEditorPlugin pluginInstance;
  
  private ServiceTracker<WorkflowExecutionService, WorkflowExecutionService> wfExecSvcTracker;
  private WorkflowExecutionService executionService;
  
  public TriqEditorPlugin() {
    pluginInstance = this;
  }
  
  public static TriqEditorPlugin getDefault() {
    return pluginInstance;
  }

  /**
   * Returns the Plugin-ID.
   * 
   * @return The Plugin-ID.
   */
  public static String getID() {
    return getDefault().getBundle().getSymbolicName();
  }
  
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    TriqEditorPlugin.context = context;
    wfExecSvcTracker = new ServiceTracker<>(context, WorkflowExecutionService.class, createSvcTrackerCustomizer());
    wfExecSvcTracker.open();
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    wfExecSvcTracker.close();
    super.stop(context);
  }
  
  public WorkflowExecutionService getWorkflowExecutionService() {
    return executionService;
  }
  
  private ServiceTrackerCustomizer<WorkflowExecutionService, WorkflowExecutionService> createSvcTrackerCustomizer() {
    return new ServiceTrackerCustomizer<WorkflowExecutionService, WorkflowExecutionService>() {
      public void removedService(ServiceReference<WorkflowExecutionService> ref, WorkflowExecutionService svc) {
        synchronized (TriqEditorPlugin.this) {
          if(svc == TriqEditorPlugin.this.executionService) {
            TriqEditorPlugin.this.executionService = null;
          } else {
            return;
          }
          context.ungetService(ref);
        }
      }

      public void modifiedService(ServiceReference<WorkflowExecutionService> ref, WorkflowExecutionService svc) {
      }

      public WorkflowExecutionService addingService(ServiceReference<WorkflowExecutionService> ref) {
        WorkflowExecutionService svc = context.getService(ref);
        synchronized (TriqEditorPlugin.this) {
          if (TriqEditorPlugin.this.executionService == null) {
            TriqEditorPlugin.this.executionService = (WorkflowExecutionService) svc;
          } 
        }
        return svc;
      }
    };
  }

}
