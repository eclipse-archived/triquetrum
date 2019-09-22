/*******************************************************************************
 * Copyright (c) 2015,2019 iSencia Belgium NV.
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
package org.eclipse.triquetrum.workflow.execution.impl.executor;

import java.util.concurrent.FutureTask;

import org.eclipse.triquetrum.ProcessingStatus;
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

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    try {
      wfet.cancel();
    } catch (Throwable t) {
      LOGGER.error(org.eclipse.triquetrum.ErrorCode.ERROR + " - Failed to cancel a FlowExecutionTask for " + wfet.getModelHandle(), t);
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