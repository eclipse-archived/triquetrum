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
package org.eclipse.triquetrum.processing.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.triquetrum.processing.ProcessingException;
import org.eclipse.triquetrum.processing.model.Task;

/**
 * Maintains a set of {@link TaskProcessingService}s and delegates the actual processing
 * of submitted {@link Task}s to a service instance capable of handling it.
 *
 */
public interface TaskProcessingBroker {

  /**
   * @param task the {@link Task} that must be processed
   * @param timeout the timeout period; null or <=0 values indicate : no timeout should be set.
   * @param unit the {@link TimeUnit} of the timeunit period
   * @return a Future to the task after processing is finished.
   * The future may contain a ProcessingException for several typical reasons :
   * <ul>
   * <li>when the delivery has failed of the task to a service able to process it. E.g. when no service is found for it.</li>
   * <li>when there was an error in the service's processing of the task</li>
   * </ul>
   */
  CompletableFuture<Task> process(Task task, Long timeout, TimeUnit unit);

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
