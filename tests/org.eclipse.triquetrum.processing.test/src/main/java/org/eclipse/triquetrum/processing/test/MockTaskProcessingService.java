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
package org.eclipse.triquetrum.processing.test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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
  public static final String DELAY_ATTRIBUTE = "delay-seconds";

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
  @SuppressWarnings("unchecked")
  @Override
  public CompletableFuture<Task> process(Task task, Long timeout, TimeUnit unit) {
    if (!canProcess(task)) {
      return null;
    } else {
      return CompletableFuture.supplyAsync(() -> doProcess(task));
    }
  }

  protected Task doProcess(Task task) {
    try {
      Attribute<String> exceptionAttribute = (Attribute<String>) task.getAttribute(EXCEPTION_CLASS_ATTRIBUTE);
      Attribute<Integer> delayAttribute = (Attribute<Integer>) task.getAttribute(DELAY_ATTRIBUTE);
      if (exceptionAttribute == null) {
        if (delayAttribute != null) {
          Thread.sleep(delayAttribute.getValue() * 1000);
        }
        try {
          TriqFactory entityFactory = TriqFactoryTracker.getDefaultFactory();
          ResultBlock rb = entityFactory.createResultBlock(task, name);
          for (Entry<String, Serializable> item : resultItems.entrySet()) {
            entityFactory.createResultItem(rb, item.getKey(), item.getValue());
          }
        } catch (IllegalStateException e) {
          // ignore as it means that the task was already in a final state, probably due to time out.
        }
        return task;
      } else {
        throw (Exception) Class.forName(exceptionAttribute.getValue()).newInstance();
      }
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String getName() {
    return name;
  }
}
