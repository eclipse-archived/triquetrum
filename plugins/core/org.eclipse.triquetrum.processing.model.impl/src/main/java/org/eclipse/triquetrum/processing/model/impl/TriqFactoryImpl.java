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
package org.eclipse.triquetrum.processing.model.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.triquetrum.ErrorCode;
import org.eclipse.triquetrum.processing.model.Attribute;
import org.eclipse.triquetrum.processing.model.AttributeHolder;
import org.eclipse.triquetrum.processing.model.ProcessingErrorEvent;
import org.eclipse.triquetrum.processing.model.ProcessingEvent;
import org.eclipse.triquetrum.processing.model.ResultBlock;
import org.eclipse.triquetrum.processing.model.ResultItem;
import org.eclipse.triquetrum.processing.model.Task;
import org.eclipse.triquetrum.processing.model.TriqFactory;
import org.eclipse.triquetrum.processing.model.TriqFactoryTracker;
import org.osgi.service.component.ComponentContext;

public class TriqFactoryImpl implements TriqFactory {

  private AtomicLong taskKeyGenerator = new AtomicLong();
  private AtomicLong attrKeyGenerator = new AtomicLong();
  private AtomicLong eventKeyGenerator = new AtomicLong();
  private AtomicLong resultBlockKeyGenerator = new AtomicLong();
  private AtomicLong resultItemKeyGenerator = new AtomicLong();

  /**
   * Typically invoked by the DS component activation.
   */
  public void activate(ComponentContext cContext, Map<String, Object> properties) {
    TriqFactoryTracker.setDefaultFactory(this);
  }

  /**
   * Typically invoked by the DS component deactivation.
   */
  public void deactivate(ComponentContext cContext, int reason) {
    TriqFactoryTracker.unsetDefaultFactory(this);
  }

  @Override
  public <V extends Serializable> Attribute<V> createAttribute(AttributeHolder holder, String name, V value) {
    return new AttributeImpl<>(holder, attrKeyGenerator.getAndIncrement(), new Date(), name, value);
  }

  @Override
  public Task createTask(Task parentTask, String initiator, String type, String correlationId, String externalRef) {
    return new TaskImpl(parentTask, taskKeyGenerator.getAndIncrement(), new Date(), initiator, type, correlationId, externalRef);
  }

  @Override
  public <T> ProcessingEvent<T> createEvent(T context, String topic, String message, Map<String, String> properties) {
    return new ProcessingEventImpl<>(context, eventKeyGenerator.getAndIncrement(), new Date(), topic, message, properties);
  }

  @Override
  public <T> ProcessingErrorEvent<T> createErrorEvent(T context, ErrorCode errorCode, String description, Throwable cause, Map<String, String> properties) {
    return new ProcessingErrorEventImpl<>(context, eventKeyGenerator.getAndIncrement(), new Date(), errorCode, description, cause);
  }

  @Override
  public ResultBlock createResultBlock(Task task, String type) {
    return new ResultBlockImpl(task, resultBlockKeyGenerator.getAndIncrement(), new Date(), type);
  }

  @Override
  public <V extends Serializable> ResultItem<V> createResultItem(ResultBlock resultBlock, String name, V value) {
    return new ResultItemImpl<>(resultBlock, resultItemKeyGenerator.getAndIncrement(), new Date(), name, value);
  }

}
