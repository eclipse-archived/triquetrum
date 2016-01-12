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
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.eclipse.triquetrum.processing.model.Attribute;
import org.eclipse.triquetrum.processing.model.AttributeHolder;
import org.eclipse.triquetrum.processing.model.ResultBlock;
import org.eclipse.triquetrum.processing.model.ResultItem;
import org.eclipse.triquetrum.processing.model.Task;

public class ResultBlockImpl extends AbstractIdentifiable implements ResultBlock {
  private static final long serialVersionUID = 1932134372473000309L;

  private Task task;
  private String type;
  private Map<String, ResultItem<? extends Serializable>> items = new ConcurrentHashMap<>();
  private AttributeHolder attributeHolder = new AttributeHolderImpl();


  /**
   * @param task
   * @param id
   * @param creationTS
   * @param type
   */
  public ResultBlockImpl(Task task, Long id, Date creationTS, String type) {
    super(id, creationTS);
    this.task = task;
    this.type = type;
    if(task!=null) {
      task.addResult(this);
    }
  }

  @Override
  public Attribute<? extends Serializable> getAttribute(String name) {
    return attributeHolder.getAttribute(name);
  }

  @Override
  public Attribute<? extends Serializable> putAttribute(Attribute<? extends Serializable> attribute) {
    return attributeHolder.putAttribute(attribute);
  }

  @Override
  public Stream<String> getAttributeNames() {
    return attributeHolder.getAttributeNames();
  }

  @Override
  public Stream<Attribute<? extends Serializable>> getAttributes() {
    return attributeHolder.getAttributes();
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public ResultItem<? extends Serializable> putItem(ResultItem<? extends Serializable> item) {
    return items.put(item.getName(), item);
  }

  @Override
  public Stream<ResultItem<? extends Serializable>> getAllItems() {
    return items.values().stream();
  }

  @Override
  public ResultItem<? extends Serializable> getItemForName(String name) {
    return items.get(name);
  }

  @Override
  public Task getTask() {
    return task;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(11, 29).appendSuper(super.hashCode()).append(task).append(type).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ResultBlockImpl other = (ResultBlockImpl) obj;
    return new EqualsBuilder().appendSuper(super.equals(obj)).
        append(task, other.task).
        append(type, other.type).isEquals();
  }

  @Override
  public String toString() {
    return "ResultBlockImpl [id=" + getId() + ", creationTS=" + getCreationTS() + ", type=" + type + "]";
  }
}
