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
package org.eclipse.triquetrum.workflow.execution.impl.executor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.RunnableFuture;

import org.eclipse.triquetrum.EventListener;
import org.eclipse.triquetrum.ProcessingStatus;
import org.eclipse.triquetrum.TriqException;
import org.eclipse.triquetrum.processing.ProcessingException;
import org.eclipse.triquetrum.workflow.ErrorCode;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.eclipse.triquetrum.workflow.ProcessEvent;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService.StartMode;
import org.eclipse.triquetrum.workflow.execution.impl.ModelHandleImpl;
import org.eclipse.triquetrum.workflow.execution.impl.debug.ActorBreakpointListener;
import org.eclipse.triquetrum.workflow.execution.impl.debug.PortBreakpointListener;
import org.eclipse.triquetrum.workflow.util.WorkflowUtils;
import org.ptolemy.testsupport.TestUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.actor.CompositeActor;
import ptolemy.actor.ExecutionListener;
import ptolemy.actor.Manager;
import ptolemy.actor.Manager.State;
import ptolemy.kernel.ComponentEntity;
import ptolemy.kernel.Port;

/**
 * @author erwin
 */
public class WorkflowExecutionTask implements CancellableTask<ProcessingStatus>, ExecutionListener {

  private final static Logger LOGGER = LoggerFactory.getLogger(WorkflowExecutionTask.class);

  private final static Map<State, ProcessingStatus> STATUS_MAPPING = new HashMap<>();

  private final ModelHandleImpl modelHandle;
  private final StartMode mode;
  private final Set<String> breakpointNames;
  private final String processId;
  private volatile ProcessingStatus status;
  private volatile boolean canceled;
  private volatile boolean busy;
  private volatile boolean suspended;
  private volatile Set<String> suspendedElements = new ConcurrentSkipListSet<>();
  private Manager manager;
  private EventListener listener;

  public WorkflowExecutionTask(StartMode mode, ModelHandle modelHandle, String processId, Map<String, String> parameterOverrides, EventListener listener,
      String... breakpointNames) {
    this.mode = mode;
    if (modelHandle == null)
      throw new IllegalArgumentException("ModelHandle can not be null");
    try {
      this.modelHandle = new ModelHandleImpl(WorkflowUtils.applyParameterSettings(modelHandle, processId, parameterOverrides));
    } catch (TriqException e) {
      throw new IllegalArgumentException("Invalid model handle for execution", e);
    }
    this.processId = processId;
    status = ProcessingStatus.IDLE;
    this.breakpointNames = (breakpointNames != null) ? new HashSet<>(Arrays.asList(breakpointNames)) : null;
    this.listener = listener;
  }

  @Override
  public RunnableFuture<ProcessingStatus> newFutureTask() {
    return new WorkflowExecutionFuture(this);
  }

  /**
   * @return the model that is being executed by this task
   */
  public ModelHandle getModelHandle() {
    return modelHandle;
  }

  /**
   * @return the process ID for this execution
   */
  public String getProcessId() {
    return processId;
  }

  /**
   * Performs the real model execution on the caller's thread.
   *
   * @return the final status after the model execution has terminated
   */
  @Override
  public ProcessingStatus call() throws Exception {
    LOGGER.trace("call() - Context {} - Flow {}", processId, modelHandle.getCode());
    try {
      boolean debug = false;
      synchronized (this) {
        CompositeActor model = modelHandle.getModel();
        if (StartMode.DEBUG.equals(mode)) {
          TestUtilities.enableStatistics(model);
          debug = setBreakpoints(modelHandle, model, breakpointNames);
        }
        manager = new Manager(model.workspace(), processId);
        manager.addExecutionListener(this);
        model.setManager(manager);
        busy = true;
      }
      if (!canceled) {
        if (!debug) {
          LOGGER.info("Context {} - Starting execution of model {}", processId, modelHandle.getCode());
        } else {
          LOGGER.info("Context {} - Starting DEBUG execution of model {}", processId, modelHandle.getCode());
        }
        manager.execute();
        // Just to be sure that for blocking executes,
        // we don't miss the final manager state changes before returning.
        managerStateChanged(manager);
      } else {
        LOGGER.info("Context {} - Canceled execution of model {} before start", processId, modelHandle.getCode());
      }
    } catch (Exception e) {
      executionError(manager, e);
      if (e.getCause() instanceof ProcessingException) {
        throw ((ProcessingException) e.getCause());
      } else {
        throw new ProcessingException(ErrorCode.MODEL_EXECUTION_ERROR, "Error running " + modelHandle, null, e);
      }
    }
    if (LOGGER.isTraceEnabled()) {
      LOGGER.trace("call() exit - Context {} - Flow {} - Final Status {}", new Object[] { processId, modelHandle.getCode(), status });
    }
    return status;
  }

  /**
   * Cancel the model execution in a clean way.
   */
  @Override
  public synchronized void cancel() {
    if (!status.isFinalStatus()) {
      LOGGER.trace("cancel() - Context {} - Flow {}", processId, modelHandle.getCode());
      canceled = true;
      if (busy) {
        LOGGER.info("Context {} - Canceling execution of model {}", processId, modelHandle.getCode());
        // to ensure that the status is directly returned as stopping,
        // even when the manager.finish() is done asynchronously,
        // we explicitly set the state already here.
        // TODO check if it's not better to override the finish method in our Manager
        // to set the state in there, same as for pause/resume...
        status = ProcessingStatus.STOPPING;
        manager.stop();
      } else {
        LOGGER.info("Context {} - Canceling execution of model {} before it started", processId, modelHandle.getCode());
        status = ProcessingStatus.INTERRUPTED;
        manager = null;
      }
      if (listener != null) {
        listener.handle(new ProcessEvent(processId, status));
      }
    } else {
      LOGGER.trace("Context {} - Ignoring canceling execution of model {} that is already done", processId, modelHandle.getCode());
    }
  }

  public synchronized boolean suspend() {
    if (!status.isFinalStatus()) {
      LOGGER.trace("cancel() - Context {} - Flow {}", processId, modelHandle.getCode());
      suspended = true;
      if (busy) {
        LOGGER.info("Context {} - Suspending execution of model {}", processId, modelHandle.getCode());
        manager.pause();
      } else {
        LOGGER.info("Context {} - Suspending execution of model {} before it started", processId, modelHandle.getCode());
        status = ProcessingStatus.SUSPENDED;
      }
      return true;
    } else {
      LOGGER.debug("Context {} - IGNORE suspending execution of model {}", processId, modelHandle.getCode());
      return false;
    }
  }

  public synchronized boolean resume() {
    if (busy && (Manager.PAUSED.equals(manager.getState()) || Manager.PAUSED_ON_BREAKPOINT.equals(manager.getState()))) {
      suspended = false;
      LOGGER.info("Context {} - Resuming execution of model {}", processId, modelHandle.getCode());
      manager.resume();
      return true;
    } else {
      LOGGER.debug("Context {} - IGNORE resuming execution of model {}", processId, modelHandle.getCode());
      return false;
    }
  }

  /**
   * @return the current model execution status
   */
  public ProcessingStatus getStatus() {
    return status;
  }

  public String[] getSuspendedElements() {
    return suspendedElements.toArray(new String[suspendedElements.size()]);
  };

  public boolean addSuspendedElement(String elementName) {
    return suspendedElements.add(elementName);
  }

  public boolean removeSuspendedElement(String elementName) {
    return suspendedElements.remove(elementName);
  }

  /**
   * Updates the model execution status to <code>ProcessStatus.ERROR</code>
   */
  @Override
  public void executionError(ptolemy.actor.Manager manager, Throwable throwable) {
    LOGGER.warn("Context " + processId + " - Execution error of model " + getModelHandle().getCode(), throwable);
    status = ProcessingStatus.ERROR;
    if (listener != null) {
      listener.handle(new ProcessEvent(processId, throwable));
    }
    busy = false;
  }

  /**
   * Updates the model execution status to <code>ProcessStatus.FINISHED</code>, or <code>ProcessStatus.INTERRUPTED</code> if the execution finished due to a
   * cancel.
   */
  @Override
  public void executionFinished(ptolemy.actor.Manager manager) {
    if (status == null || !status.isFinalStatus()) {
      if (!canceled) {
        LOGGER.info("Context {} - Execution finished of model {}", processId, getModelHandle().getCode());
        status = ProcessingStatus.FINISHED;
      } else {
        LOGGER.warn("Context {} - Execution interrupted of model {}", processId, getModelHandle().getCode());
        status = ProcessingStatus.INTERRUPTED;
      }
      if (listener != null) {
        listener.handle(new ProcessEvent(processId, status));
      }
      busy = false;
    }
  }

  /**
   * Changes the model execution status according to the new manager state.
   */
  @SuppressWarnings("unchecked")
  @Override
  public void managerStateChanged(ptolemy.actor.Manager manager) {
    State state = manager.getState();
    LOGGER.trace("Context {} - Manager state change of model {} : {}", new Object[] { processId, getModelHandle().getCode(), state });
    if (status == null || !status.isFinalStatus()) {
      ProcessingStatus oldStatus = status;
      status = STATUS_MAPPING.get(state);
      if (canceled && ProcessingStatus.IDLE.equals(status)) {
        status = ProcessingStatus.INTERRUPTED;
      }
      if (oldStatus != status) {
        LOGGER.info("Context {} - Execution state change of model {} : {}", new Object[] { processId, getModelHandle().getCode(), status });
        // This handles the case where a suspend() call was done right after the (asynch) start,
        // before the actual execution was effectively already started.
        if (suspended && ProcessingStatus.ACTIVE.equals(status)) {
          LOGGER.info("Context {} - Suspended at startup for Flow {}", processId, modelHandle.getCode());
          manager.pause();
        }
        if (listener != null) {
          listener.handle(new ProcessEvent(processId, status));
        }
      }
      if (status.isFinalStatus()) {
        busy = false;
      }
    }
  }

  protected boolean setBreakpoints(ModelHandle modelHandle, CompositeActor flow, Set<String> breakpointNames) {
    boolean breakpointsDefined = false;
    if (breakpointNames != null) {
      for (String breakpointName : breakpointNames) {
        ComponentEntity<?> entity = flow.getEntity(breakpointName);
        if (entity != null) {
          entity.addDebugListener(new ActorBreakpointListener(breakpointName, this));
          LOGGER.info("Context {} - Flow {} - Set breakpoint {}", new Object[] { processId, modelHandle.getCode(), breakpointName });
          breakpointsDefined = true;
        } else {
          Port port = flow.getPort(breakpointName);
          if (port != null) {
            port.addDebugListener(new PortBreakpointListener(breakpointName, this));
            LOGGER.info("Context {} - Flow {} - Set breakpoint {}", new Object[] { processId, modelHandle.getCode(), breakpointName });
            breakpointsDefined = true;
          } else {
            LOGGER.warn("Context {} - Flow {} - Breakpoint not found ", new Object[] { processId, modelHandle.getCode(), breakpointName });
          }
        }
      }
    }
    return breakpointsDefined;
  }

  static {
    STATUS_MAPPING.put(Manager.CORRUPTED, ProcessingStatus.ERROR);
    STATUS_MAPPING.put(Manager.EXITING, ProcessingStatus.STOPPING);
    STATUS_MAPPING.put(Manager.IDLE, ProcessingStatus.IDLE);
    STATUS_MAPPING.put(Manager.INFERING_WIDTHS, ProcessingStatus.STARTING);
    STATUS_MAPPING.put(Manager.INITIALIZING, ProcessingStatus.STARTING);
    STATUS_MAPPING.put(Manager.ITERATING, ProcessingStatus.ACTIVE);
    STATUS_MAPPING.put(Manager.PAUSED, ProcessingStatus.SUSPENDED);
    STATUS_MAPPING.put(Manager.PAUSED_ON_BREAKPOINT, ProcessingStatus.SUSPENDED);
    STATUS_MAPPING.put(Manager.PREINITIALIZING, ProcessingStatus.STARTING);
    STATUS_MAPPING.put(Manager.RESOLVING_TYPES, ProcessingStatus.STARTING);
    STATUS_MAPPING.put(Manager.THROWING_A_THROWABLE, ProcessingStatus.ERROR);
    STATUS_MAPPING.put(Manager.WRAPPING_UP, ProcessingStatus.STOPPING);
  }
}
