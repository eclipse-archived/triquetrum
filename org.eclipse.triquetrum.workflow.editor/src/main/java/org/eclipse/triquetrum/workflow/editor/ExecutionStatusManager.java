/*******************************************************************************
 * Copyright (c) 2015-2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.triquetrum.workflow.ProcessHandle;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

public class ExecutionStatusManager extends AbstractSourceProvider {

  public final static String MY_STATE = "org.eclipse.triquetrum.workflow.executionStatus";
  public final static String RUNNING = "RUNNING";
  public final static String IDLE = "IDLE";
  public final static String NONE = "NONE";

  private static ExecutionStatusManager instance;

  /**
   * This map maintains the process handles for each running model. For the moment we only support 1 execution at a time for a given model. (but we want to
   * support concurrent executions of different models)
   */
  private static Map<String, ProcessHandle> workflowExecutionHandles = new ConcurrentHashMap<>();

  public ExecutionStatusManager() {
    // TODO check if this singleton-like approach works, i.e. that an RCP only creates 1 instance of this class
    if (instance != null) {
      System.err.println("You can't trust these guys! duplicate ExecutionStatusManagers");
    }
    instance = this;
  }

  /**
   *
   * @param modelCode
   * @return the process handle of the running instance of the given model or null, if it was not registered as being executed
   */
  public static synchronized ProcessHandle getWorkflowExecutionHandle(String modelCode) {
    ProcessHandle handle = workflowExecutionHandles.get(modelCode);
    if (handle!=null && handle.getExecutionStatus().isFinalStatus()) {
      // this is a handle that should no longer be here, take it out!
      removeWorkflowExecutionHandle(modelCode);
      handle = null;
    }
    return handle;
  }

  /**
   * Stores the handle to a running workflow. Only 1 instance of a workflow model can be running at a given time, in the current Triq editor version. When an
   * extra handle registration is attempted for a same model, an IllegalStateException is thrown.
   *
   * @param workflowExecutionHandle
   * @throws IllegalStateException
   *           when the model is already registered as executing.
   */
  public static synchronized void putWorkflowExecutionHandle(String modelCode, ProcessHandle workflowExecutionHandle) {
    if (workflowExecutionHandles.containsKey(modelCode)) {
      throw new IllegalStateException("Model " + modelCode + " is already executing");
    }
    workflowExecutionHandles.put(modelCode, workflowExecutionHandle);

    instance.fireSourceChanged(ISources.WORKBENCH, MY_STATE, RUNNING);
  }

  /**
   *
   * @param modelCode
   * @return the removed handle if it was present before the invocation of this method, null otherwise
   */
  public static synchronized ProcessHandle removeWorkflowExecutionHandle(String modelCode) {
    ProcessHandle result = workflowExecutionHandles.remove(modelCode);
    if (result != null) {
      instance.fireSourceChanged(ISources.WORKBENCH, MY_STATE, IDLE);
    }
    return result;
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
    Map<String, Object> map = new HashMap<>(1);
    CompositeActor selection = EditorUtils.getSelectedModel();
    if (selection != null) {
      boolean running = (getWorkflowExecutionHandle(selection.getName()) != null);
      String value = running ? RUNNING : IDLE;
      map.put(MY_STATE, value);
    } else {
      map.put(MY_STATE, IEvaluationContext.UNDEFINED_VARIABLE);
    }
    return map;
  }
}
