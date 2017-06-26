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
package org.eclipse.triquetrum.workflow.execution.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.triquetrum.Event;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.eclipse.triquetrum.workflow.ProcessEvent;
import org.eclipse.triquetrum.workflow.ProcessEventListener;
import org.eclipse.triquetrum.workflow.ProcessHandle;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService;
import org.eclipse.triquetrum.workflow.WorkflowNotExecutingException;
import org.eclipse.triquetrum.workflow.execution.impl.executor.WorkflowExecutionFuture;
import org.eclipse.triquetrum.workflow.execution.impl.executor.WorkflowExecutionTask;
import org.eclipse.triquetrum.workflow.execution.impl.executor.WorkflowExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.actor.CompositeActor;

public class WorkflowExecutionServiceImpl implements WorkflowExecutionService {

  private final static Logger LOGGER = LoggerFactory.getLogger(WorkflowExecutionServiceImpl.class);

  // the thread pool to launch flow execution tasks
  private ExecutorService workflowExecutor;

  public WorkflowExecutionServiceImpl() {
    this(Runtime.getRuntime().availableProcessors());
  }

  /**
   *
   * @param maxConcurrentProcesses
   */
  public WorkflowExecutionServiceImpl(int maxConcurrentProcesses) {
    LOGGER.info("Creating WorkflowExecutionServiceImpl for {} max concurrent processes", maxConcurrentProcesses);
    workflowExecutor = new WorkflowExecutor(maxConcurrentProcesses, maxConcurrentProcesses);
  }

  @Override
  public ProcessHandle start(StartMode mode, ModelHandle modelHandle, String processContextId, Map<String, String> parameterOverrides,
      ProcessEventListener listener, String... breakpointNames) {

    if (processContextId == null || processContextId.trim().length() == 0) {
      processContextId = UUID.randomUUID().toString();
    }

    LOGGER.debug("Context {} - Submitting execution of workflow {}", processContextId, modelHandle.getCode());

    WorkflowExecutionTask fet = new WorkflowExecutionTask(mode, modelHandle, processContextId, parameterOverrides, listener, breakpointNames);
    WorkflowExecutionFuture fetFuture = (WorkflowExecutionFuture) workflowExecutor.submit(fet);
    ProcessHandle procHandle = new ProcessHandleImpl(fetFuture);

    return procHandle;
  }

  @Override
  public ProcessHandle start(StartMode mode, CompositeActor model, String processId, Map<String, String> parameterOverrides, ProcessEventListener listener,
      String... breakpointNames) {
    return start(mode, new ModelHandleImpl(model), processId, parameterOverrides, listener, breakpointNames);
  }

  @Override
  public ProcessHandle addBreakpoints(ProcessHandle processHandle, String... extraBreakpoints) {
    // TODO implement signalEvent()
    throw new UnsupportedOperationException();
  }

  @Override
  public ProcessHandle removeBreakpoints(ProcessHandle processHandle, String... breakpointsToRemove) {
    // TODO implement removeBreakpoints()
    throw new UnsupportedOperationException();
  }

  @Override
  public ProcessHandle getHandle(String processId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ProcessHandle refresh(ProcessHandle processHandle) {
    // NOP here, as our handle impl contains the future that has the up-to-date status info
    return processHandle;
  }

  @Override
  public ProcessHandle waitUntilFinished(ProcessHandle processHandle, long time, TimeUnit unit)
      throws TimeoutException, InterruptedException, WorkflowNotExecutingException, ExecutionException {
    WorkflowExecutionFuture fet = ((ProcessHandleImpl) processHandle).getFet();
    if (fet != null) {
      try {
        fet.get(time, unit);
      } catch (CancellationException e) {
        // ignore, it will be reflected in the status of the handle
      }
      return processHandle;
    } else {
      throw new WorkflowNotExecutingException(processHandle.getModelHandle());
    }
  }

  /**
   * Does not wait for the execution to have terminated!
   */
  @Override
  public ProcessHandle terminate(ProcessHandle processHandle) throws WorkflowNotExecutingException {
    WorkflowExecutionFuture fet = ((ProcessHandleImpl) processHandle).getFet();
    if (fet == null) {
      throw new WorkflowNotExecutingException(processHandle.getModelHandle());
    } else {
      fet.cancel(true);
      return processHandle;
    }
  }

  @Override
  public ProcessHandle suspend(ProcessHandle processHandle) throws WorkflowNotExecutingException {
    WorkflowExecutionFuture fet = ((ProcessHandleImpl) processHandle).getFet();
    if (fet == null) {
      throw new WorkflowNotExecutingException(processHandle.getModelHandle());
    } else {
      // TODO check if we can/need to do something with the boolean result...
      fet.suspend();
      return processHandle;
    }
  }

  @Override
  public ProcessHandle resume(ProcessHandle processHandle) throws WorkflowNotExecutingException {
    WorkflowExecutionFuture fet = ((ProcessHandleImpl) processHandle).getFet();
    if (fet == null) {
      throw new WorkflowNotExecutingException(processHandle.getModelHandle());
    } else {
      // TODO check if we can/need to do something with the boolean result...
      fet.resume();
      return processHandle;
    }
  }

  /**
   * TODO : implement partial suspensions (cfr doc for <code>start()</code> method).
   *
   * Until then this method just delegates to the plain <code>resume(processHandle)</code>
   */
  @Override
  public ProcessHandle resume(ProcessHandle processHandle, String suspendedElement) throws WorkflowNotExecutingException {
    return resume(processHandle);
  }

  @Override
  public ProcessHandle step(ProcessHandle processHandle) throws WorkflowNotExecutingException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ProcessHandle signalEvent(ProcessHandle processHandle, Event event) throws WorkflowNotExecutingException {
    // TODO implement signalEvent()
    throw new UnsupportedOperationException();
  }

  @Override
  public List<ProcessEvent> getProcessEvents(ProcessHandle processHandle, int maxCount) {
    // TODO implement getProcessEvents()
    throw new UnsupportedOperationException();
  }

  @Override
  public List<ProcessEvent> getProcessEvents(String processId, int maxCount) {
    // TODO implement getProcessEvents()
    throw new UnsupportedOperationException();
  }
}
