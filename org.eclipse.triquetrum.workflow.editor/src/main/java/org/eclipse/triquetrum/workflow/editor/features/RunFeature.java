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
package org.eclipse.triquetrum.workflow.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.triquetrum.ProcessingStatus;
import org.eclipse.triquetrum.workflow.ProcessEvent;
import org.eclipse.triquetrum.workflow.ProcessEventListener;
import org.eclipse.triquetrum.workflow.ProcessHandle;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService.StartMode;
import org.eclipse.triquetrum.workflow.editor.ExecutionStatusManager;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.triquetrum.workflow.editor.util.EclipseUtils;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunFeature extends AbstractExecutionManagementFeature {

  private final static Logger LOGGER = LoggerFactory.getLogger(RunFeature.class);

  public static final String HINT = "run"; //$NON-NLS-1$

  public RunFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public String getName() {
    return "Run Triq workflow"; //$NON-NLS-1$
  }

  @Override
  protected boolean isAvailableForSelection(CompositeActor selection) {
    LOGGER.trace("isAvailableForSelection() - entry : {}", selection.getName());
    boolean result = (getProcessHandleForSelection(selection) == null);
    LOGGER.trace("isAvailableForSelection() - exit : {} -> {}", selection.getName(), result);
    return result;
  }

  @Override
  protected void doExecute(WorkflowExecutionService executionService, final CompositeActor selection) {
    LOGGER.trace("doExecute() - entry : {}", selection.getName());
    // make sure that logging is shown in the console view
    TriqEditorPlugin.getDefault().initConsoleLogging();
    // TODO check if this is acceptable for SWT coding?
    // we somehow need to take hold of the display instance, to handle UI thread issues correctly in the eventlistener callback below.
    final Display display = EclipseUtils.getActivePage().getWorkbenchWindow().getShell().getDisplay();

    ptolemy.actor.CompositeActor ptolemyModel = (ptolemy.actor.CompositeActor) selection.getWrappedObject();
    ProcessHandle workflowExecutionHandle = executionService.start(StartMode.RUN, ptolemyModel, null, null, new ProcessEventListener() {
      @Override
      public void handle(ProcessEvent event) {
          LOGGER.trace("ProcessEventListener.handle() - entry : {} -> {}", selection.getName(), event);
          ProcessingStatus status = event.getStatus();
          if(status!=null) {
            // we need to update the state of process handle, and the accompanying execution commands in the toolbar
            // this requires display to help us out with UI thread handling...
            display.syncExec(new Runnable() {
              @Override
              public void run() {
                ProcessHandle handle = status.isFinalStatus() ? removeProcessHandle(selection) : getProcessHandleForSelection(selection);
                fireExecutionStatusChange(handle);
              }
            });
          }
          LOGGER.trace("ProcessEventListener.handle() - exit : {} -> {}", selection.getName(), event);
      }
    });
    // TODO there is a risk here that for very fast model executions, the listener has already been invoked before this next step is done.
    // The result would be that the process handle never gets cleared and the user will not be able to launch the same model again.
    // Current hacky fix is in ExecutionStatusManager.getWorkflowExecutionHandle(), where the process status is checked,
    // and the handle is removed when we notice that the process has finished in the meantime.
    try {
      storeProcessHandle(selection, workflowExecutionHandle);
    } catch (IllegalStateException e) {
      // TODO stop the model immediately?
    }
    LOGGER.trace("doExecute() - exit : {}", selection.getName());
  }
}