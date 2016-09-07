/*******************************************************************************
 * Copyright (c) 2015 iSencia Belgium NV.
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
import java.util.Date;
import java.util.stream.Stream;

import org.eclipse.triquetrum.ProcessingStatus;
import org.eclipse.triquetrum.processing.ProcessingException;

/**
 * A Task specifies an item of work to be performed. It defines WHAT must be done, not how it must be achieved.
 * <p>
 * Simple work can be handled as one "unit of work" that can be executed by a <code>TaskProcessingService</code>. Complex tasks could be handled by a workflow
 * execution, where actors launch sub-tasks for each meaningful step to get the work done. In such cases the Task also has a process ID, identifying the
 * workflow process execution instance (workflow guys often call this an "enactment").
 * </p>
 * <p>
 * The definition consists of following main elements :
 * <ul>
 * <li>type : the main differentiator on the kind of work to be performed</li>
 * <li>a set of parameters : these define the data needed to perform the given task</li>
 * <li>results : the internal structure is a set of result blocks containing result items. In specialized domains, like science, raw results may not be fit for
 * internal storage but could e.g. be stored in special/huge files. Then the triq result items may be used to represent references to such files/datasets,
 * and/or derived data obtained during the workflow execution.</li>
 * </ul>
 * </p>
 */
public interface Task extends Serializable, Identifiable, AttributeHolder {

  /**
   * A Task can be linked to an external/business entity, e.g. an experiment ID, a customer ticket or a business order etc.
   * This (optional, non-unique) identifier can refer to this external entity.
   * <p>
   * Alternatively, it can just be used to maintain a more-or-less readable key that can be used to refer to related Tasks.
   * </p>
   * @return an optional key that can be used to refer to an associated (business) entity, or can serve as a simple (non-unique) readable key,
   * to refer to this Task.
   */
  String getExternalReference();

  /**
   * A correlation ID can be specified by the initiator. Triquetrum will then ensure that in any notifications, acknowledgements or other kinds of feedback, the
   * correlation ID will be available. This can be used by external systems to facilitate correlating their original requests with later asynchronous responses
   * from the triq runtime.
   *
   * @return the correlation Id that was received from the initiator.
   */
  String getCorrelationId();

  /**
   * @return the type of this task, which typically is the main key to determine by which service this task must be processed
   */
  String getType();

  /**
   * @return a unique identifier/name of the initiator, i.e. the party or system component responsible for the initiation of this task.
   *
   */
  String getInitiator();

  /**
   * @return a unique identifier/name of the executor, i.e. the party or system component that (was) determined to be responsible for the handling of this task.
   *
   */
  String getExecutor();

  /**
   *
   * @param executor the party or system component that has been selected to become responsible for the handling of this task.
   */
  void setExecutor(String executor);

  /**
   * Complex Tasks may be processed via the execution of a workflow process. In such cases, each significant step may be represented by a Task, and such Tasks
   * are aware of the process's Id.
   *
   * @return the (optional) Id of the process in which this Task is handled.
   */
  String getProcessId();

  /**
   * Set the id of the process that is handling this task.
   *
   * @param id the (optional) Id of the process in which this Task is handled.
   */
  void setProcessId(String id);

  /**
   * @return current status of this task
   */
  ProcessingStatus getStatus();

  /**
   * Set the new status of the task.
   * <p>
   * There is currently no formally enforced state transition model.
   * The only assumption is that once a Task has been set to a "final" state,
   * the setter will fail if any more state changes are attempted.
   * </p>
   * @param status the new Task status
   * @param extraInfos optional extra info messages that may be sent out in ProcessingEvents to status change listeners.
   * @throws IllegalStateException if the current status does not allow to set the new status (e.g. the current status is final)
   */
  void setStatus(ProcessingStatus status, String... extraInfos) throws IllegalStateException;

  /**
   * Tasks may fail for different reasons. If the failure can be represented as an exception, it should be wrapped in a ProcessingException
   * and passed in here, optionally with some extra info.
   *
   * @param cause the (optional) exception that represents the cause of the error in this Task's processing.
   * @param extraInfos optional extra info messages that may be sent out in ProcessingEvents to status change listeners.
   * @throws IllegalStateException if the current status does not allow to set the new status (e.g. the current status is final)
   */
  void setErrorStatus(ProcessingException cause, String... extraInfos) throws IllegalStateException;

  /**
   * @return the end time stamp
   */
  Date getEndTS();

  /**
   * TODO check if/how we could keep such a stream open infinitely to allow adding new events as they happen.
   *
   * @return the Stream of all events that have happened in this task's lifecycle
   */
  Stream<ProcessingEvent<Task>> getEvents();

  /**
   * Adds an extra result for this Task.
   * <p>
   * Depending on the Task implementation, there may be restrictions on adding results.
   * E.g. that only one result block with a same type may be present.
   * For such cases, the method must return a boolean success indicator (cfr. the Java Collection APIs)
   * </p>
   * @param resultBlock
   * @throws IllegalStateException if a result could no longer be added to this Task, e.g. because it's already in a final status.
   */
  void addResult(ResultBlock resultBlock) throws IllegalStateException;

  /**
   * @return the Stream of results that have been gathered by this task
   */
  Stream<ResultBlock> getResults();

  /**
   *
   * @return the (optional) parent task
   */
  Task getParentTask();

  /**
   *
   * @param task
   * @throws IllegalStateException if a subtask could no longer be added to this Task, e.g. because it's already in a final status.
   */
  void addSubTask(Task task) throws IllegalStateException;

  /**
   *
   * @return the Stream of all associated sub-tasks
   */
  Stream<Task> getSubTasks();
}
