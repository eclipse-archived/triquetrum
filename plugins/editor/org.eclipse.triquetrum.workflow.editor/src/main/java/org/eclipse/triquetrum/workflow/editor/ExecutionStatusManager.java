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
package org.eclipse.triquetrum.workflow.editor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.triquetrum.ProcessingStatus;
import org.eclipse.triquetrum.workflow.ProcessHandle;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bridges workflow execution status with the activation of run/pause/resume/stop buttons in the editor toolbar, and the corresponding feature instances.
 * <p>
 * The status management is linked to the active editor.
 * </p>
 *
 */
public class ExecutionStatusManager extends AbstractSourceProvider {

  private final static Logger LOGGER = LoggerFactory.getLogger(ExecutionStatusManager.class);

  /**
   * the name of the variable that is used by eclipse's core expressions in the plugin.xml to activate/desactivate our toolbar buttons
   */
  public final static String MY_STATE = "org.eclipse.triquetrum.workflow.executionStatus";

  /**
   * the unique instance that we want to be able to access from inside our graphiti features etc
   */
  private static ExecutionStatusManager instance;

  /**
   * This map maintains the process handles for each running model. For the moment we only support 1 execution at a time for a given model. (but we already want
   * to support the concurrent execution of different models)
   */
  private static Map<String, ProcessHandle> workflowExecutionHandles = new ConcurrentHashMap<>();

  /**
   * We assume that this constructor will only be invoked once in a Triquetrum editor RCP application! The construction is driven from an extension defined in
   * the plugin.xml :
   * 
   * <pre>
   *  &lt;extension point="org.eclipse.ui.services">
   *    &lt;sourceProvider provider="org.eclipse.triquetrum.workflow.editor.ExecutionStatusManager">
   *      &lt;variable
   *            name="org.eclipse.triquetrum.workflow.executionStatus"
   *            priorityLevel="workbench">
   *      &lt;/variable>
   *    &lt;/sourceProvider>
   *  &lt;/extension>
   * </pre>
   */
  public ExecutionStatusManager() {
    // TODO check if this singleton-like approach works, i.e. that an RCP only creates 1 instance of this class
    if (instance != null) {
      LOGGER.error("You can't trust these guys! duplicate ExecutionStatusManagers");
    }
    instance = this;
  }

  /**
   *
   * @return the single instance of the ExecutionStatusManager, created by the Triq editor as defined in its plugin.xml
   */
  public static ExecutionStatusManager getInstance() {
    return instance;
  }

  /**
   *
   * @param modelCode
   * @return the process handle of the running instance of the given model, or null if it was not registered as being executed
   */
  public static synchronized ProcessHandle getWorkflowExecutionHandle(String modelCode) {
    ProcessHandle handle = workflowExecutionHandles.get(modelCode);
    if (handle != null && handle.getExecutionStatus().isFinalStatus()) {
      // this is a handle that should no longer be here, take it out!
      removeWorkflowExecutionHandle(modelCode);
      handle = null;
    }
    return handle;
  }

  /**
   * Stores the handle to a running workflow.
   * <p>
   * Only 1 instance of a workflow model can be running at a given time, in the current Triq editor version. When an extra handle registration is attempted for
   * a same model, an IllegalStateException is thrown.
   * </p>
   *
   * @param handle
   * @throws IllegalStateException
   *           when the model is already registered as executing.
   */
  public static void putWorkflowExecutionHandle(String modelCode, ProcessHandle handle) {
    LOGGER.trace("putWorkflowExecutionHandle() - entry : {} -> {}", modelCode, handle);
    if (workflowExecutionHandles.containsKey(modelCode)) {
      throw new IllegalStateException("Model " + modelCode + " is already executing");
    }
    workflowExecutionHandles.put(modelCode, handle);
    instance.fireSourceChanged(ISources.WORKBENCH, MY_STATE, ProcessingStatus.ACTIVE.name());
    LOGGER.trace("putWorkflowExecutionHandle() - exit : {} -> {}", modelCode, handle);
  }

  /**
   *
   * @param modelCode
   * @return the removed handle
   */
  public static ProcessHandle removeWorkflowExecutionHandle(String modelCode) {
    LOGGER.trace("removeWorkflowExecutionHandle() - entry : {}", modelCode);
    ProcessHandle handle = workflowExecutionHandles.remove(modelCode);
    LOGGER.trace("removeWorkflowExecutionHandle() - exit : {} removed {}", modelCode, handle);
    return handle;
  }

  /**
   * Fire a status change event for the given handle.
   * <p>
   * Remark that this method has no clue if an effective status change occurred or not. That decision should already have been made by the component invoking
   * this method.
   * </p>
   * 
   * @param handle
   */
  public void fireStatusChanged(ProcessHandle handle) {
    LOGGER.trace("fireStatusChanged() - entry : {}", handle);
    String statusValue = getExecutionStatusForHandle(handle).name();
    fireSourceChanged(ISources.WORKBENCH, MY_STATE, statusValue);
    LOGGER.debug("fireStatusChanged() - setting status : {}", statusValue);
    LOGGER.trace("fireStatusChanged() - exit : {}", handle);
  }

  /**
   * Fire a status change event for the model associated with the given model.
   * <p>
   * Remark that this method has no clue if an effective status change occurred or not. That decision should already have been made by the component invoking
   * this method.
   * </p>
   * 
   * @param modelCode
   */
  public void fireStatusChanged(String modelCode) {
    LOGGER.trace("fireStatusChanged() - entry : {}", modelCode);
    ProcessHandle handle = getWorkflowExecutionHandle(modelCode);
    LOGGER.debug("fireStatusChanged() - {} -> {}", modelCode, handle);
    String statusValue = getExecutionStatusForHandle(handle).name();
    fireSourceChanged(ISources.WORKBENCH, MY_STATE, statusValue);
    LOGGER.debug("fireStatusChanged() - setting status : {}", statusValue);
    LOGGER.trace("fireStatusChanged() - exit : {}", modelCode);
  }

  @Override
  public void dispose() {
  }

  @Override
  public String[] getProvidedSourceNames() {
    return new String[] { MY_STATE };
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Map getCurrentState() {
    LOGGER.trace("getCurrentState() - entry");
    Map<String, Object> map = new HashMap<>(1);
    CompositeActor selection = EditorUtils.getSelectedModel();
    ProcessHandle handle = null;
    if (selection != null) {
      handle = getWorkflowExecutionHandle(selection.getName());
    }
    map.put(MY_STATE, getExecutionStatusForHandle(handle));
    LOGGER.trace("getCurrentState() - exit : {}", map);
    return map;
  }

  protected static ProcessingStatus getExecutionStatusForHandle(ProcessHandle handle) {
    ProcessingStatus status = ProcessingStatus.IDLE;
    if (handle != null) {
      switch (handle.getExecutionStatus()) {
      case STARTING:
      case ACTIVE:
      case STOPPING:
        status = ProcessingStatus.ACTIVE;
        break;
      case SUSPENDED:
        status = ProcessingStatus.SUSPENDED;
        break;
      case FINISHED:
      case INTERRUPTED:
      case ERROR:
      default:
        status = ProcessingStatus.IDLE;
      }
    }
    return status;
  }
}
