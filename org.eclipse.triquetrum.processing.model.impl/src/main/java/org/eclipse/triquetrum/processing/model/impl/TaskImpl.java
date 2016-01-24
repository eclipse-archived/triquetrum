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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.eclipse.triquetrum.processing.ProcessingException;
import org.eclipse.triquetrum.processing.model.Attribute;
import org.eclipse.triquetrum.processing.model.AttributeHolder;
import org.eclipse.triquetrum.processing.model.ProcessingEvent;
import org.eclipse.triquetrum.processing.model.ProcessingStatus;
import org.eclipse.triquetrum.processing.model.ResultBlock;
import org.eclipse.triquetrum.processing.model.Task;

public class TaskImpl extends AbstractIdentifiable implements Task {
  private static final long serialVersionUID = 185724688544851359L;

  private Date endTS;
  private String type;
  private String correlationId;
  private String externalRef;
  private String initiator;
  private String executor;
  private String processId;
  private ProcessingStatus status;

  private AttributeHolder attributeHolder = new AttributeHolderImpl();
  private Task parentTask;
  private List<Task> subTasks = new ArrayList<>();
  private List<ResultBlock> results = new ArrayList<>();

  /**
   * @param parentTask
   * @param id
   * @param creationTS
   * @param initiator
   * @param type
   * @param correlationId
   * @param externalRef
   */
  public TaskImpl(Task parentTask, Long id, Date creationTS, String initiator, String type, String correlationId, String externalRef) {
    super(id, creationTS);
    this.type = type;
    this.initiator = initiator;
    this.correlationId = correlationId;
    this.externalRef = externalRef;
    this.status = ProcessingStatus.IDLE;
    this.parentTask = parentTask;
    if (parentTask != null) {
      parentTask.addSubTask(this);
    }
  }

  @Override
  public String getCorrelationId() {
    return correlationId;
  }

  @Override
  public String getExternalReference() {
    return externalRef;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public String getInitiator() {
    return initiator;
  }

  @Override
  public String getExecutor() {
    return executor;
  }

  @Override
  public void setExecutor(String executor) {
    this.executor = executor;
  }

  @Override
  public String getProcessId() {
    return processId;
  }

  @Override
  public void setProcessId(String id) {
    this.processId = id;
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
  public ProcessingStatus getStatus() {
    return status;
  }

  @Override
  public void setStatus(ProcessingStatus status, String... extraInfos) throws IllegalStateException {
    if (!this.status.isFinalStatus()) {
      this.status = status;
      // TODO send out event incl. the extraInfos
    } else {
      throw new IllegalStateException("Task " + getId() + " in final status " + status);
    }
  }

  @Override
  public void setErrorStatus(ProcessingException cause, String... extraInfos) {
    if (!this.status.isFinalStatus()) {
      this.status = ProcessingStatus.ERROR;
      // TODO send out event incl. the exception info and the extraInfos
    } else {
      throw new IllegalStateException("Task " + getId() + " in final status " + status);
    }
  }

  @Override
  public Date getEndTS() {
    return endTS;
  }

  @Override
  public Stream<ProcessingEvent<Task>> getEvents() {
    // TODO Implement getEvents
    return null;
  }

  @Override
  public void addResult(ResultBlock resultBlock) throws IllegalStateException {
    if (!this.status.isFinalStatus()) {
      results.add(resultBlock);
    } else {
      throw new IllegalStateException("Task " + getId() + " in final status " + status);
    }
  }

  @Override
  public Stream<ResultBlock> getResults() {
    return results.stream();
  }

  @Override
  public Task getParentTask() {
    return parentTask;
  }

  @Override
  public void addSubTask(Task task) throws IllegalStateException {
    if (!this.status.isFinalStatus()) {
      subTasks.add(task);
    } else {
      throw new IllegalStateException("Task " + getId() + " in final status " + status);
    }
  }

  @Override
  public Stream<Task> getSubTasks() {
    return subTasks.stream();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(19, 23).appendSuper(super.hashCode())
        .append(type).append(correlationId).append(externalRef).append(status)
        .append(initiator).append(executor).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TaskImpl other = (TaskImpl) obj;
    return new EqualsBuilder().appendSuper(super.equals(obj))
        .append(type, other.type)
        .append(correlationId, other.correlationId)
        .append(externalRef, other.externalRef)
        .append(status, other.status)
        .append(initiator, other.initiator)
        .append(executor, other.executor).isEquals();
  }

  @Override
  public String toString() {
    return "TaskImpl [getId()=" + getId() + ", getCreationTS()=" + getCreationTS()
        + ", correlationId=" + correlationId + ", externalRef=" + externalRef
        + ", type=" + type + ", status=" + status + ", initiator=" + initiator
        + ", executor=" + executor + ", processId=" + processId + ", endTS=" + endTS + "]";
  }
}
