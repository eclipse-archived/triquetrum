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
package org.eclipse.triquetrum.processing.test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.triquetrum.ProcessingStatus;
import org.eclipse.triquetrum.processing.ErrorCode;
import org.eclipse.triquetrum.processing.ProcessingException;
import org.eclipse.triquetrum.processing.model.Attribute;
import org.eclipse.triquetrum.processing.model.ResultBlock;
import org.eclipse.triquetrum.processing.model.ResultItem;
import org.eclipse.triquetrum.processing.model.Task;
import org.eclipse.triquetrum.processing.model.TriqFactory;
import org.eclipse.triquetrum.processing.model.TriqFactoryTracker;
import org.eclipse.triquetrum.processing.service.TaskProcessingService;

/**
 * A trivial implementation of mocked {@link TaskProcessingService}.
 * <p>
 * For {@link Task}s whose type matches the name of this service instance, the service will create one {@link ResultBlock} with the configured set of
 * {@link ResultItem}s.
 */
public class MockTaskProcessingService implements TaskProcessingService {

  public static final String EXCEPTION_CLASS_ATTRIBUTE = "exception-class";

  /**
   * The name of this service. If it's null, the service will match all Task types.
   */
  private String name;
  /**
   * A map containing the key/value pairs that will be used to create the Task's ResultItems.
   */
  private Map<String, Serializable> resultItems = new HashMap<>();

  /**
   *
   * @param name
   *          the name for this new service instance. If it's null, the service will match all Task types.
   * @param resultItems
   *          map containing the key/value pairs that will be used to create the Task's ResultItems.
   */
  public MockTaskProcessingService(String name, Map<String, Serializable> resultItems) {
    this.name = name;
    if (resultItems != null) {
      this.resultItems.putAll(resultItems);
    }
  }

  /**
   * Check the service name against the Task's type.
   */
  @Override
  public boolean canProcess(Task task) {
    return task != null && (name == null || name.equalsIgnoreCase(task.getType()));
  }

  /**
   * If this service can process the given task, it will create a ResultBlock with this service's configured ResultItems.
   */
  @Override
  public CompletableFuture<Task> process(Task task, Long timeout, TimeUnit unit) {
    if (!canProcess(task)) {
      return null;
    } else {
      CompletableFuture<Task> fut = new CompletableFuture<>();
      try {
        @SuppressWarnings("unchecked")
        Attribute<String> exceptionAttribute = (Attribute<String>) task.getAttribute(EXCEPTION_CLASS_ATTRIBUTE);
        if (exceptionAttribute == null) {
          TriqFactory entityFactory = TriqFactoryTracker.getDefaultFactory();
          ResultBlock rb = entityFactory.createResultBlock(task, name);
          for (Entry<String, Serializable> item : resultItems.entrySet()) {
            entityFactory.createResultItem(rb, item.getKey(), item.getValue());
          }
          task.setStatus(ProcessingStatus.FINISHED);
          fut.complete(task);
        } else {
          throw (Exception) Class.forName(exceptionAttribute.getValue()).newInstance();
        }
      } catch (Exception e) {
        // Should not happen in this mock implementation, unless there's an issue with the factory or so.
        // Even so, this shows how errors can be handled in code that is using the processing APIs.
        task.setErrorStatus(new ProcessingException(ErrorCode.TASK_ERROR, e));
        fut.completeExceptionally(e);
      }
      return fut;
    }
  }

  @Override
  public String getName() {
    return name;
  }
}
