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
package org.eclipse.triquetrum.processing.model;

import java.io.Serializable;

/**
 * Factory implementations determine which specific implementation classes are used when constructing instances of a processing model entity.
 *
 */
public interface TriqFactory {

  /**
   *
   * @param holder
   * @param name
   * @param value
   * @return
   */
  <V extends Serializable> Attribute<V> createAttribute(AttributeHolder holder, String name, V value);

  /**
   *
   * @param parentTask
   * @param initiator
   * @param type
   * @param correlationId
   * @param externalRef
   * @return
   */
  Task createTask(Task parentTask, String initiator, String type, String correlationId, String externalRef);

  /**
   *
   * @param context
   * @param topic
   * @param message
   * @return
   */
  <T> ProcessingEvent<T> createContextEvent(T context, String topic, String message);

  /**
   *
   * @param task
   * @param type
   * @return
   */
  ResultBlock createResultBlock(Task task, String type);

  /**
   *
   * @param resultBlock
   * @param name
   * @param value
   * @return
   */
  <V extends Serializable> ResultItem<V> createResultItem(ResultBlock resultBlock, String name, V value);
}
