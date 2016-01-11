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

import org.eclipse.triquetrum.processing.ProcessingException;
import org.eclipse.triquetrum.processing.model.Attribute;
import org.eclipse.triquetrum.processing.model.AttributeHolder;
import org.eclipse.triquetrum.processing.model.ProcessingEvent;
import org.eclipse.triquetrum.processing.model.ProcessingStatus;
import org.eclipse.triquetrum.processing.model.ResultBlock;
import org.eclipse.triquetrum.processing.model.Task;

public class TaskImpl implements Task {
  private static final long serialVersionUID = 185724688544851359L;

  private Long id;
  private Date creationTS;
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
    this.id = id;
    this.creationTS = creationTS;
    this.type = type;
    this.initiator = initiator;
    this.correlationId = correlationId;
    this.externalRef = externalRef;
    this.status = ProcessingStatus.IDLE;
    this.parentTask = parentTask;
    if(parentTask!=null) {
      parentTask.addSubTask(this);
    }
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public Date getCreationTS() {
    return creationTS;
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
      throw new IllegalStateException("Task "+id+" in final status "+status);
    }
  }

  @Override
  public void setErrorStatus(ProcessingException cause, String... extraInfos) {
    if (!this.status.isFinalStatus()) {
      this.status = ProcessingStatus.ERROR;
      // TODO send out event incl. the exception info and the extraInfos
    } else {
      throw new IllegalStateException("Task "+id+" in final status "+status);
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
      throw new IllegalStateException("Task " + id + " in final status " + status);
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
      throw new IllegalStateException("Task " + id + " in final status " + status);
    }
  }

  @Override
  public Stream<Task> getSubTasks() {
    return subTasks.stream();
  }
}
