/*******************************************************************************
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.processing.service.impl.example;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.eclipse.triquetrum.ProcessingStatus;
import org.eclipse.triquetrum.processing.ErrorCode;
import org.eclipse.triquetrum.processing.ProcessingException;
import org.eclipse.triquetrum.processing.model.ResultBlock;
import org.eclipse.triquetrum.processing.model.Task;
import org.eclipse.triquetrum.processing.model.TriqFactory;
import org.eclipse.triquetrum.processing.model.TriqFactoryTracker;
import org.eclipse.triquetrum.processing.service.TaskProcessingService;

/**
 * An example task processing service implementation for sum and average calculations.
 * <p>
 * This shows the typical responsibilities of a service implementation :
 * <ul>
 * <li>specify if a given task can be handled, in the canProcess method implementation</li>
 * <li>perform whatever logic is needed to handle an accepted task</li>
 * <li>optionally create a resultblock with items to represent the results of the task processing</li>
 * <li>set the task status to finished when done or error when there was a failure, and set the result future accordingly</li>
 * </ul>
 * TODO add persistence handling to store the task, results, ... in some DB or other persistent storage.
 * </p>
 */
public class MathsProcessingService implements TaskProcessingService {

  private final static String SUM = "sum";
  private final static String AVG = "average";
  private final static Set<String> SUPPORTED_OPERATIONS = Stream.of(SUM, AVG).collect(Collectors.toCollection(HashSet::new));

  @Override
  public String getName() {
    return "Maths service for sum,average";
  }

  @Override
  public CompletableFuture<Task> process(Task task, Long timeout, TimeUnit unit) {
    if (!canProcess(task)) {
      return null;
    } else {
      CompletableFuture<Task> fut = new CompletableFuture<>();
      String taskType = task.getType();
      try {
        Double result = null;
        DoubleStream dStr = task.getAttributes().filter(a -> a.getValue() instanceof Double).mapToDouble(a -> (Double)a.getValue());
        switch (taskType) {
        case SUM :
          result = dStr.sum();
          break;
        case AVG :
          result = dStr.average().getAsDouble();
        }
        TriqFactory entityFactory = TriqFactoryTracker.getDefaultFactory();
        ResultBlock rb = entityFactory.createResultBlock(task, taskType);
        entityFactory.createResultItem(rb, taskType, result);
        task.setStatus(ProcessingStatus.FINISHED);
        fut.complete(task);
      } catch (Exception e) {
        task.setErrorStatus(new ProcessingException(ErrorCode.TASK_ERROR, e));
        fut.completeExceptionally(e);
      }
      return fut;
    }
  }

  @Override
  public boolean canProcess(Task task) {
    return task != null && SUPPORTED_OPERATIONS.contains(task.getType());
  }
}
