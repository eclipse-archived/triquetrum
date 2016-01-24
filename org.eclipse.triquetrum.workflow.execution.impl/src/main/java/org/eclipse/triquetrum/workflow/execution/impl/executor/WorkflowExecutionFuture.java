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
package org.eclipse.triquetrum.workflow.execution.impl.executor;

import java.util.concurrent.FutureTask;

import org.eclipse.triquetrum.processing.model.ProcessingStatus;
import org.eclipse.triquetrum.workflow.ErrorCode;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author erwin
 *
 */
public final class WorkflowExecutionFuture extends FutureTask<ProcessingStatus> {
  
  private final static Logger LOGGER = LoggerFactory.getLogger(WorkflowExecutionFuture.class);
  
  private WorkflowExecutionTask wfet;
  
  public WorkflowExecutionFuture(WorkflowExecutionTask callable) {
    super(callable);
    this.wfet = callable;
  }

  public boolean cancel(boolean mayInterruptIfRunning) {
    try {
      wfet.cancel();
    } catch (Throwable t) {
      LOGGER.error(ErrorCode.ERROR+" - Failed to cancel a FlowExecutionTask for "+wfet.getModelHandle(),t);
    }
    return super.cancel(mayInterruptIfRunning);
  }
  
  public boolean suspend() {
    return wfet.suspend();
  }
  
  public boolean resume() {
    return wfet.resume();
  }
  
  public String getProcessId() {
    return wfet.getProcessId();
  }
  
  public ProcessingStatus getStatus() {
    return wfet.getStatus();
  }
  
  public String[] getSuspendedElements() {
    return wfet.getSuspendedElements();
  }

  
  public ModelHandle getModelHandle() {
    return wfet.getModelHandle();
  }
}