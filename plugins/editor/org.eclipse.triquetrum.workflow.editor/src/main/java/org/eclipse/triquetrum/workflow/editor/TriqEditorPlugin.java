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
package org.eclipse.triquetrum.workflow.editor;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class TriqEditorPlugin extends AbstractUIPlugin {

  private static TriqEditorPlugin pluginInstance;

  private BundleContext context;
  private ServiceTracker<WorkflowExecutionService, WorkflowExecutionService> wfExecSvcTracker;
  private WorkflowExecutionService executionService;

  private MessageConsoleStream outStream = null;
  private MessageConsoleStream errStream = null;

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
    this.context = context;
    wfExecSvcTracker = new ServiceTracker<>(context, WorkflowExecutionService.class, createSvcTrackerCustomizer());
    wfExecSvcTracker.open();
  }

  /*
   * It seems the initialisation of the console msg streams, and their substitution in sysout and syserr don't work well when done too early in the application
   * startup (e.g. in the plugin.start()). So we provide this method to get this done as late as possible, e.g. only when running a workflow.
   */
  public void initConsoleLogging() {
    if (outStream == null) {
      MessageConsole myConsole = findConsole("Triq Console");
      outStream = myConsole.newMessageStream();
      outStream.setActivateOnWrite(true);
      outStream.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
      try {
        PrintStream outPS = new PrintStream(outStream, true, "UTF-8");
        System.setOut(outPS); // link standard output stream to the console
      } catch (UnsupportedEncodingException e) {
        logError("Error configuring stdOut redirection to console", e);
      }
      errStream = myConsole.newMessageStream();
      errStream.setActivateOnWrite(true);
      errStream.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
      try {
        PrintStream errPS = new PrintStream(errStream, true, "UTF-8");
        System.setErr(errPS); // link error output stream to the console
      } catch (UnsupportedEncodingException e) {
        logError("Error configuring stdErr redirection to console", e);
      }
    }
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    wfExecSvcTracker.close();
    super.stop(context);
  }
  
  public void registerEventHandler(EventHandler handler, String topic) {
    Dictionary<String, String> properties = new Hashtable<>();
    properties.put("event.topics", topic);
    context.registerService(EventHandler.class, handler, properties );
  }

  public static void log(int severity, String message, Throwable t) {
    IStatus status = new Status(severity, getID(), 0, message, t);
    getDefault().getLog().log(status);
  }

  public static void logError(String message, Throwable t) {
    log(IStatus.ERROR, message, t);
  }

  public WorkflowExecutionService getWorkflowExecutionService() {
    return executionService;
  }

  public ReportService getReportService() {
    final BundleContext bundleContext = getBundle().getBundleContext();
    final ServiceReference<ReportService> serviceReference = bundleContext.getServiceReference(ReportService.class);
    return bundleContext.getService(serviceReference);
  }
  
  public EventAdmin getEventAdminService() {
    final BundleContext bundleContext = getBundle().getBundleContext();
    final ServiceReference<EventAdmin> serviceReference = bundleContext.getServiceReference(EventAdmin.class);
    return bundleContext.getService(serviceReference);
  }

  private MessageConsole findConsole(String name) {
    IConsoleManager conMan = ConsolePlugin.getDefault().getConsoleManager();
    IConsole[] currentConsoles = conMan.getConsoles();
    for (IConsole currConsole : currentConsoles) {
      if (name.equals(currConsole.getName())) {
        return (MessageConsole) currConsole;
      }
    }
    // no console found -> create new one
    MessageConsole newConsole = new MessageConsole(name, null);
    conMan.addConsoles(new IConsole[] { newConsole });
    return newConsole;
  }

  private ServiceTrackerCustomizer<WorkflowExecutionService, WorkflowExecutionService> createSvcTrackerCustomizer() {
    return new ServiceTrackerCustomizer<WorkflowExecutionService, WorkflowExecutionService>() {
      @Override
      public void removedService(ServiceReference<WorkflowExecutionService> ref, WorkflowExecutionService svc) {
        synchronized (TriqEditorPlugin.this) {
          if (svc == TriqEditorPlugin.this.executionService) {
            TriqEditorPlugin.this.executionService = null;
          } else {
            return;
          }
          TriqEditorPlugin.this.context.ungetService(ref);
        }
      }

      @Override
      public void modifiedService(ServiceReference<WorkflowExecutionService> ref, WorkflowExecutionService svc) {
      }

      @Override
      public WorkflowExecutionService addingService(ServiceReference<WorkflowExecutionService> ref) {
        WorkflowExecutionService svc = TriqEditorPlugin.this.context.getService(ref);
        synchronized (TriqEditorPlugin.this) {
          if (TriqEditorPlugin.this.executionService == null) {
            TriqEditorPlugin.this.executionService = svc;
          }
        }
        return svc;
      }
    };
  }

}
