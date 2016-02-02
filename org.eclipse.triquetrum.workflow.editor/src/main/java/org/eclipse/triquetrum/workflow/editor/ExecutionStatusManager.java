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

import org.eclipse.swt.widgets.Display;
import org.eclipse.triquetrum.ProcessingStatus;
import org.eclipse.triquetrum.workflow.ProcessHandle;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;
import org.eclipse.ui.PlatformUI;

public class ExecutionStatusManager extends AbstractSourceProvider {

  public final static String MY_STATE = "org.eclipse.triquetrum.workflow.executionStatus";

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
    if (handle != null && handle.getExecutionStatus().isFinalStatus()) {
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
   * @param handle
   * @throws IllegalStateException
   *           when the model is already registered as executing.
   */
  public static void putWorkflowExecutionHandle(String modelCode, ProcessHandle handle) {
    if (workflowExecutionHandles.containsKey(modelCode)) {
      throw new IllegalStateException("Model " + modelCode + " is already executing");
    }
    workflowExecutionHandles.put(modelCode, handle);
  }

  /**
   *
   * @param modelCode
   * @return the removed handle if it was present before the invocation of this method, null otherwise
   */
  public static ProcessHandle removeWorkflowExecutionHandle(String modelCode) {
    return workflowExecutionHandles.remove(modelCode);
  }

  /**
   *
   * @param modelCode
   * @param processHandle
   *          if no handle was registered yet for this code, and the model is in an ongoing execution, use and register this handle.
   */
  public static synchronized void fireStatusChanged(String modelCode, ProcessHandle processHandle, boolean manageThread) {
    ProcessHandle handle = getWorkflowExecutionHandle(modelCode);

    ProcessingStatus status = getExecutionStatusForHandle((handle != null ? handle : processHandle));

    if ((ProcessingStatus.ACTIVE.equals(status)) && (handle == null && processHandle != null)) {
      putWorkflowExecutionHandle(modelCode, processHandle);
    } else if (ProcessingStatus.IDLE.equals(status)) {
      removeWorkflowExecutionHandle(modelCode);
    }

    final String value = status.name();

    // TODO check if this is acceptable for SWT coding?
    // we somehow need to take hold of the display instance, to handle UI thread issues correctly in the eventlistener callback below.
    if(manageThread) {
      final Display display = PlatformUI.getWorkbench().getDisplay();
      display.asyncExec(new Runnable() {
        @Override
        public void run() {
          instance.fireSourceChanged(ISources.WORKBENCH, MY_STATE, value);
        }
      });
    } else {
      instance.fireSourceChanged(ISources.WORKBENCH, MY_STATE, value);
    }
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
    ProcessHandle handle = null;
    if (selection != null) {
      handle = getWorkflowExecutionHandle(selection.getName());
    }
    map.put(MY_STATE, getExecutionStatusForHandle(handle));
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
