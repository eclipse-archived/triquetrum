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
package org.eclipse.triquetrum.processing.model;

import java.io.Serializable;
import java.util.Map;

import org.eclipse.triquetrum.ErrorCode;

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
  <T> ProcessingEvent<T> createEvent(T context, String topic, String message, Map<String, String> properties);

  /**
   *
   * @param context
   * @param errorCode
   * @param description
   * @param cause
   * @param properties
   * @return
   */
  <T> ProcessingErrorEvent<T> createErrorEvent(T context, ErrorCode errorCode, String description, Throwable cause, Map<String, String> properties);

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
