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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.triquetrum.processing.model.Task;

/**
 * Responsible for the actual processing of a submitted {@link Task} that matches the selection criteria of a particular service instance.
 */
public interface TaskProcessingService {

  /**
   * @return a descriptive short name for the service
   */
  String getName();

  /**
   * Process the given Task within the given timeout, if this service instance is capable of handling it. If the service is unable to handle it, it should
   * simply return <code>null</code> immediately.
   * <p>
   * Service implementations are by preference non-blocking, and should just return a {@link Future} to the finished Task. In case of errors they should also
   * set the relevant exception on the returned Future.
   * </p>
   * <p>
   * Blocking service implementations are of course possible, and must return a pre-filled Future.
   * </p>
   * 
   * @param task
   *          the Task that must be processed
   * @param timeout
   *          the timeout period; null or <=0 values indicate : no timeout should be set.
   * @param unit
   *          the {@link TimeUnit} of the timeout period
   * @return a Future to the task after processing is finished or null if this service is unable to process the given task. In case of processing errors, the
   *         future will throw a ProcessingException when trying to get its value.
   */
  CompletableFuture<Task> process(Task task, Long timeout, TimeUnit unit);

  /**
   *
   * @param task
   * @return true if the given task can be processed by this service
   */
  boolean canProcess(Task task);
}
