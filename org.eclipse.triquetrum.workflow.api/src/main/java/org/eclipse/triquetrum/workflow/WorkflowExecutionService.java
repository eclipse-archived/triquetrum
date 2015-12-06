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
package org.eclipse.triquetrum.workflow;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.triquetrum.Event;
import org.eclipse.triquetrum.EventListener;

import ptolemy.actor.CompositeActor;

/**
 * A service interface for everything related to executing a workflow,
 * including support for stopping/pausing/resuming/stepping/breakpoints etc.
 * 
 * @author erwin
 *
 */
public interface WorkflowExecutionService {
  
  enum StartMode {
    RUN, DEBUG, STEP;
  }

  /**
   * Start a workflow process in the specified mode.
   * <p>
   * This method should run the model in a non-blocking way, i.e. should return swiftly with the created <code>ProcessHandle</code>
   * while the process may keep on running in the background for a longer period of time.
   * </p>
   * <p>
   * The optional listener will be notified of all detailed <code>ProcessEvent</code>s.
   * Remark that in the absence of a listener, steps/resume may still be triggered via <code>step()</code> and <code>resume()</code>, 
   * and status info may be obtained via iteratively invoking <code>refresh(Processhandle)</code>.
   * <br/>
   * But such a "polling" approach is not desirable. A listener-based approach is almost always more efficient and more powerful.
   * </p>
   * <p>
   * In <b>RUN</b> mode, the execution will typically run in one shot until the end.
   * </p>
   * <p>
   * Via a <code>suspend()</code> request, the execution can be suspended.
   * After which it can be continued again via <code>resume()</code>, or per <code>step()</code> etc.
   * </p>
   * <p>
   * In <b>STEP</b> mode, Task executions are done one-by-one, each time a <code>step()</code> has been requested.
   * Via <code>resume()</code>, the execution can continue as in <b>NORMAL</b> mode.
   * </p>
   * <p>
   * In <b>DEBUG</b> mode, the execution may (partially) suspend on one or more of the specified break points.
   * After which it can be continued again via one of the <code>resume()</code> methods, or per <code>step()</code> etc.
   * </p>
   * <p>
   * Upon reaching a breakpoint, the model execution is completely suspended, similarly as via an explicit suspend() call.
   * </p>
   * <p>
   * Breakpoints must refer to named elements in the running process : actors and/or ports.
   * <br/> 
   * The names given should be the full hierarchic names, without the flow's name.
   * E.g. in a HelloWorld model with a Constant actor connected to a Console, valid breakpoints could be :
   * <ul>
   * <li>Constant</li>
   * <li>Console.input</li>
   * <li>etc.</li>
   * </ul>
   * </p>
   * <p>
   * The optional <code>processId</code> identifies a Process that is being executed across one or more workflow executions.
   * Such <code>Process</code> instances are typically created outside of the Passerelle runtime services. 
   * For simple processes, each workflow execution typically corresponds to one Process which ends up in a final status. 
   * For more complex processes, several consecutive workflow executions may be involved that are linked through delegation/redirection, sharing a same Process context.
   * </p>
   * <p>
   * When the <code>processId</code> is null, simple models may just run without a formal <code>Process</code>, i.e. by assigning a simple arbitrary execution ID for the <code>ProcessHandle</code>.
   * More formal execution situations may choose to generate a new <code>Process</code> internally when needed/appropriate.
   * This choice can be made either in implementations of this service, or by specialised actors used in the flows.
   * </p>
   * <p>
   * Remark that loading a complete <code>Process</code> context in memory can be a costly operation.
   * So when detailed <code>Process</code> data is not absolutely required, it is preferable to just pass the <code>processId</code> around.
   * </p>
   * @param mode
   * @param modelHandle
   * @param processId can be null : for context-aware executions, this can be used to set/share the <code>Process</code> context for a flow execution. 
   * @param parameterOverrides can be null : overridden values of flow/actor parameters
   * @param listener can be null
   * @param breakpointNames optional names of the Flow elements (ports and/or actors) where the process should place a breakpoint, if started in DEBUG mode
   * @return
   */
  ProcessHandle start(StartMode mode, ModelHandle modelHandle, String processId, Map<String, String> parameterOverrides, EventListener listener, String... breakpointNames);

  /**
   * Variation of the start-method that directly receives the model to execute i.o. a ModelHandle.
   * 
   * @param mode
   * @param model
   * @param processId
   * @param parameterOverrides
   * @param listener
   * @param breakpointNames
   * @return
   */
  ProcessHandle start(StartMode mode, CompositeActor model, String processId, Map<String, String> parameterOverrides, EventListener listener, String... breakpointNames);

  /**
   * Wait until the process has finished and return the final status.
   * <p>
   * If the process has not finished before the given maximum wait time,
   * a TimeOutException is thrown.
   * <br/>
   * If the process has already finished and its information can still be retrieved,
   * the call will return immediately with the final status.
   * <br/>
   * If no execution information can be found, a FlowNotExecutingException will be thrown.
   * </p>
   * @param processHandle
   * @param time
   * @param unit
   * @return the handle with a final status
   * 
   * @throws WorkflowNotExecutingException when no process execution information was found.
   * @throws ExecutionException when there was an error executing the process; 
   *  Typically <code>getCause()</code> will contain a <code>PasserelleException</code> with concrete error info.
   * @throws InterruptedException when the waiting thread has been interrupted
   * @throws TimeoutException when the process did not finish within the given maximum wait time
   */
  ProcessHandle waitUntilFinished(ProcessHandle processHandle, long time, TimeUnit unit) throws WorkflowNotExecutingException, ExecutionException, TimeoutException, InterruptedException;

  /**
   * Terminate a running process through a termination event.
   * <p>
   * Implementations may or may not block until the process is effectively dead, and should clearly document their choice.
   * </p>
   * @param processHandle
   * @param time
   * @param unit
   * @return the updated processHandle
   * @throws WorkflowNotExecutingException when the process identified by the handle was not (or no longer) running
   */
  ProcessHandle terminate(ProcessHandle processHandle) throws WorkflowNotExecutingException;
  
  /**
   * 
   * @param processHandle
   * @return the updated processHandle
   * @throws WorkflowNotExecutingException when the process identified by the handle was not (or no longer) running
   */
  ProcessHandle suspend(ProcessHandle processHandle) throws WorkflowNotExecutingException;
  
  /**
   * Resume all suspended elements and continue the execution until next breakpoint(s) are reached
   * or until the end if no breakpoints are encountered anymore.
   * 
   * @param processHandle
   * @return the updated processHandle
   * @throws WorkflowNotExecutingException when the process identified by the handle was not (or no longer) running
   */
  ProcessHandle resume(ProcessHandle processHandle) throws WorkflowNotExecutingException;
  
  /**
   * Resume at the given suspendedElement. Other suspended elements will remain suspended.
   * 
   * @param processHandle
   * @param suspendedElement
   * @return
   * @throws WorkflowNotExecutingException
   */
  ProcessHandle resume(ProcessHandle processHandle, String suspendedElement) throws WorkflowNotExecutingException;
  
  /**
   * 
   * @param processHandle
   * @return the updated processHandle
   * @throws WorkflowNotExecutingException when the process identified by the handle was not (or no longer) running
   */
  ProcessHandle step(ProcessHandle processHandle) throws WorkflowNotExecutingException;

  /**
   * 
   * @param extraBreakpoints
   * @return the updated processHandle with extra breakpoints
   */
  ProcessHandle addBreakpoints(ProcessHandle processHandle, String... extraBreakpoints);
  
  /**
   * 
   * @param breakpointsToRemove
   * @return the updated processHandle with removed breakpoints
   */
  ProcessHandle removeBreakpoints(ProcessHandle processHandle, String... breakpointsToRemove);

  /**
   * Signal an <code>Event</code> to the running process identified by the handle.
   * These can be pure events, or may also pass more complex data (e.g. user input) into a running process.
   * 
   * @param processHandle
   * @param event
   * @return the updated processHandle after delivering the event to the running process
   * @throws WorkflowNotExecutingException when the process identified by the handle was not (or no longer) running
   */
  ProcessHandle signalEvent(ProcessHandle processHandle, Event event) throws WorkflowNotExecutingException;
  
  /**
   * 
   * @param processHandle
   * @param maxCount
   * @return the list of processing events, from newest to oldest and limited to the given maxCount
   */
  List<Event> getProcessEvents(ProcessHandle processHandle, int maxCount);
  
  /**
   * Gets the process events for a process with given UUID.
   * <p>
   * This method is useful for processes executed in the past, 
   * or other situations where the ProcessHandle is not available.
   * </p>
   * 
   * @param processId
   * @param maxCount
   * @return the list of processing events, from newest to oldest and limited to the given maxCount
   */
  List<Event> getProcessEvents(String processId, int maxCount);

  /**
   * 
   * @param processId
   * @return the handle for the given process Id; null if no active process with given Id is found.
   */
  ProcessHandle getHandle(String processId);
  
  /**
   * TODO check if we should not remove this, as getHandle() basically does the same
   * @param processHandle
   * @return the refreshed handle, i.e. with updated status info
   */
  ProcessHandle refresh(ProcessHandle processHandle);
}
