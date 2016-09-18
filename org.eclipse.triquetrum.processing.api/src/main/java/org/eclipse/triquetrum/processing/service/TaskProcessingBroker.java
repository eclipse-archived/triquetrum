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
package org.eclipse.triquetrum.processing.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.triquetrum.processing.model.Task;

/**
 * Maintains a set of {@link TaskProcessingService}s and delegates the actual processing of submitted {@link Task}s to a service instance capable of handling
 * it.
 *
 */
public interface TaskProcessingBroker {

  /**
   * @param task
   *          the {@link Task} that must be processed
   * @param timeout
   *          the timeout period; null or <=0 values indicate : no timeout should be set.
   * @param unit
   *          the {@link TimeUnit} of the timeunit period
   * @return a Future to the task processing result. The future may contain a ProcessingException for several typical reasons :
   *         <ul>
   *         <li>when the delivery has failed of the task to a service able to process it. E.g. when no service is found for it.</li>
   *         <li>when there was an error in the service's processing of the task</li>
   *         </ul>
   */
  CompletableFuture<Task> process(Task task, Long timeout, TimeUnit unit);

  /**
   * Process a task's subtasks and optionally mark the (parent) task as done when all subtasks are done.
   * <p>
   * All subtasks are executed asynchronously and concurrently. If there are sequencing requirements, i.e. certain subtasks depend on the results of other
   * subtasks, this method is not the right one to use. For such cases the task submitter must chain invocations of the separate task process(), using the
   * CompletableFuture API as needed to define the chain.
   * </p>
   * 
   * @param task
   * @param timeout
   * @param unit
   * @param finishWhenDone
   * @return
   */
  CompletableFuture<Task> processSubTasks(Task task, Long timeout, TimeUnit unit, boolean finishWhenDone);

  /**
   * Process several tasks concurrently. The method returns a future that will return when all submitted tasks are finished.
   *
   * @param timeout
   *          the timeout period; null or <=0 values indicate : no timeout should be set.
   * @param unit
   *          the {@link TimeUnit} of the timeunit period
   * @param tasks
   *          a number of {@link Task}s that must be processed
   * @return a Future to the processing results of the tasks. The future may contain a ProcessingException when there was an error delivering (one of) the tasks
   *         to their services. Individual task processing errors will be returned in their task's error status and event(s).
   */
  CompletableFuture<List<Task>> process(Long timeout, TimeUnit unit, Task... tasks);

  /**
   *
   * @param service
   * @return <tt>true</tt> if the new service was successfully registered
   */
  boolean registerService(TaskProcessingService service);

  /**
   *
   * @param service
   * @return <tt>true</tt> if the service was registered and was successfully removed
   */
  boolean removeService(TaskProcessingService service);
}
