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
package org.eclipse.triquetrum.processing.model.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.triquetrum.processing.model.Attribute;
import org.eclipse.triquetrum.processing.model.AttributeHolder;
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
    return new AttributeImpl<V>(holder, attrKeyGenerator.getAndIncrement(), new Date(), name, value);
  }

  @Override
  public Task createTask(Task parentTask, String initiator, String type, String correlationId, String externalRef) {
    return new TaskImpl(parentTask, taskKeyGenerator.getAndIncrement(), new Date(), initiator, type, correlationId, externalRef);
  }

  @Override
  public <T> ProcessingEvent<T> createContextEvent(T context, String topic, String message) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResultBlock createResultBlock(Task task, String type) {
    return new ResultBlockImpl(task, resultBlockKeyGenerator.getAndIncrement(), new Date(), type);
  }

  @Override
  public <V extends Serializable> ResultItem<V> createResultItem(ResultBlock resultBlock, String name, V value) {
    return new ResultItemImpl<V>(resultBlock, resultItemKeyGenerator.getAndIncrement(), new Date(), name, value);
  }

}
