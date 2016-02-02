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

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.RunnableFuture;

import org.eclipse.triquetrum.EventListener;
import org.eclipse.triquetrum.ProcessingStatus;
import org.eclipse.triquetrum.processing.ProcessingException;
import org.eclipse.triquetrum.workflow.ErrorCode;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.eclipse.triquetrum.workflow.ProcessEvent;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService.StartMode;
import org.eclipse.triquetrum.workflow.execution.impl.debug.ActorBreakpointListener;
import org.eclipse.triquetrum.workflow.execution.impl.debug.PortBreakpointListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.actor.CompositeActor;
import ptolemy.actor.ExecutionListener;
import ptolemy.actor.Manager;
import ptolemy.actor.Manager.State;
import ptolemy.data.expr.Parameter;
import ptolemy.kernel.ComponentEntity;
import ptolemy.kernel.Entity;
import ptolemy.kernel.Port;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.Workspace;


/**
 * @author erwin
 */
public class WorkflowExecutionTask implements CancellableTask<ProcessingStatus>, ExecutionListener {

  private final static Logger LOGGER = LoggerFactory.getLogger(WorkflowExecutionTask.class);

  private final static Map<State, ProcessingStatus> STATUS_MAPPING = new HashMap<State, ProcessingStatus>();

  private final ModelHandle modelHandle;
  private final StartMode mode;
  private final Map<String, String> parameterOverrides;
  private final Set<String> breakpointNames;
  private final String processId;
  private volatile ProcessingStatus status;
  private volatile boolean canceled;
  private volatile boolean busy;
  private volatile boolean suspended;
  private volatile Set<String> suspendedElements = new ConcurrentSkipListSet<String>();
  private Manager manager;
  private EventListener listener;

  public WorkflowExecutionTask(StartMode mode, ModelHandle modelHandle, String processId,
      Map<String, String> parameterOverrides, EventListener listener,
      String... breakpointNames) {
    this.mode = mode;
    if (modelHandle == null)
      throw new IllegalArgumentException("ModelHandle can not be null");
    this.modelHandle = modelHandle;
    this.processId = processId;
    status = ProcessingStatus.IDLE;
    this.parameterOverrides = (parameterOverrides != null) ? new HashMap<String, String>(parameterOverrides) : null;
    this.breakpointNames = (breakpointNames != null) ? new HashSet<String>(Arrays.asList(breakpointNames)) : null;
    this.listener = listener;
  }

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
        CompositeActor model = (CompositeActor) modelHandle.getModel().clone(new Workspace());
        applyParameterSettings(modelHandle, model, parameterOverrides);
        if (StartMode.DEBUG.equals(mode)) {
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
        throw new ProcessingException(ErrorCode.MODEL_EXECUTION_ERROR, "Error running "+modelHandle, null, e);
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
   * Updates the model execution status to <code>ProcessStatus.FINISHED</code>, or <code>ProcessStatus.INTERRUPTED</code>
   * if the execution finished due to a cancel.
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
      }
      if (status.isFinalStatus()) {
        busy = false;
      }
    }
  }

  protected void applyParameterSettings(ModelHandle flowHandle, CompositeActor flow, Map<String, String> props) throws ProcessingException {
    if (props != null) {
      Iterator<Entry<String, String>> propsItr = props.entrySet().iterator();
      while (propsItr.hasNext()) {
        Entry<String, String> element = propsItr.next();
        String propName = element.getKey();
        String propValue = element.getValue();
        String[] nameParts = propName.split("[\\.]");

        // set model parameters
        if (nameParts.length == 1 && !flow.attributeList().isEmpty()) {
          try {
            final Parameter p = (Parameter) flow.getAttribute(nameParts[0], Parameter.class);
            setParameter(flowHandle, p, propName, propValue);
          } catch (final IllegalActionException e1) {
            throw new ProcessingException(ErrorCode.MODEL_CONFIGURATION_ERROR, "Inconsistent parameter definition " + propName, flow, e1);
          }
        }
        // parts[parts.length-1] is the parameter name
        // all the parts[] before that are part of the nested Parameter name
        Entity<?> e = flow;
        for (int j = 0; j < nameParts.length - 1; j++) {
          if (e instanceof CompositeActor) {
            Entity<?> test = ((CompositeActor) e).getEntity(nameParts[j]);
            if (test == null) {
              try {
                // maybe it is a director
                ptolemy.actor.Director d = ((CompositeActor) e).getDirector();
                if (d != null) {
                  Parameter p = (Parameter) d.getAttribute(nameParts[nameParts.length - 1], Parameter.class);
                  setParameter(flowHandle, p, propName, propValue);
                }
              } catch (IllegalActionException e1) {
                throw new ProcessingException(ErrorCode.MODEL_CONFIGURATION_ERROR, "Inconsistent parameter definition " + propName, flow, e1);
              }
            } else {
              e = ((CompositeActor) e).getEntity(nameParts[j]);
              if (e != null) {
                try {
                  Parameter p = (Parameter) e.getAttribute(nameParts[nameParts.length - 1], Parameter.class);
                  setParameter(flowHandle, p, propName, propValue);
                } catch (IllegalActionException e1) {
                  throw new ProcessingException(ErrorCode.MODEL_CONFIGURATION_ERROR, "Inconsistent parameter definition " + propName, flow, e1);
                }
              }
            }
          } else {
            break;
          }
        }
      }
    }
  }

  private void setParameter(ModelHandle modelHandle, final Parameter p, String propName, String propValue) throws ProcessingException {
    if (p != null) {
      p.setExpression(propValue);
      p.setPersistent(true);
      LOGGER.info("Context {} - Flow {} - Override parameter {} : {}", new Object[] { processId, modelHandle.getCode(), propName, propValue });
    } else if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Context {} - Flow {} - Unknown parameter, no override : {} ", new Object[] { processId, modelHandle.getCode(), propName });
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
