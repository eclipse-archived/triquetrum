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
package org.eclipse.triquetrum.processing.model.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.eclipse.triquetrum.processing.model.ProcessingEvent;

public class ProcessingEventImpl<T> extends AbstractIdentifiable implements ProcessingEvent<T> {
  private static final long serialVersionUID = 1574362785331534580L;

  private T context;
  private String topic;
  private String message;
  private Map<String, String> properties;

  private Long duration;

  public ProcessingEventImpl(T context, Long id, Date creationTS, Long duration, String topic, String message, Map<String, String> properties) {
    super(id, creationTS);
    this.duration = duration;
    this.context = context;
    this.topic = topic;
    this.message = message;
    this.properties = Collections.unmodifiableMap(properties != null ? properties : new HashMap<>());
  }

  public ProcessingEventImpl(T context, Long id, Date creationTS, String topic, String message, Map<String, String> properties) {
    this(context, id, creationTS, 0L, topic, message, properties);
  }

  @Override
  public String getTopic() {
    return topic;
  }

  @Override
  public T getContext() {
    return context;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public Long getDuration() {
    return duration;
  }

  @Override
  public String getProperty(String propName) {
    return properties.get(propName);
  }

  @Override
  public Iterator<String> getPropertyNames() {
    return properties.keySet().iterator();
  }

  @Override
  public int compareTo(ProcessingEvent<T> o) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 29).appendSuper(super.hashCode()).append(context).append(topic).append(message).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProcessingEventImpl<?> other = (ProcessingEventImpl<?>) obj;
    return new EqualsBuilder().appendSuper(super.equals(obj)).append(context, other.context).append(topic, other.topic).append(message, other.message)
        .isEquals();
  }

  @Override
  public String toString() {
    return "ProcessingEventImpl [getId()=" + getId() + ", getCreationTS()=" + getCreationTS() + ", topic=" + topic + ", message=" + message + ", duration="
        + duration + "]";
  }

  // protected methods
  protected ProcessingEventImpl(T context, Long id, Date creationTS, Long duration, String topic) {
    this(context, id, creationTS, duration, topic, null, null);
  }

  // Subclasses can set the message and properties based on their own logic/conventions
  // and this might need to be done after the super() constructor call.
  // So we need setters for that...
  protected void setMessage(String message) {
    this.message = message;
  }

  protected void addProperty(String name, String value) {
    this.properties.put(name, value);
  }
}
