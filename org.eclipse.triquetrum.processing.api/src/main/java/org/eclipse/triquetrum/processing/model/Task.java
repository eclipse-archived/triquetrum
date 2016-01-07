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
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
   * A correlation ID can be specified by the initiator. Triquetrum will then ensure that in any notifications, acknowledgements or other kinds of feedback, the
   * correlation ID will be available. This can be used by external systems to facilitate correlating their original requests with later asynchronous responses
   * from the triq runtime.
   *
   * @return the correlation Id that was received from the initiator.
   */
  String getCorrelationId();

  /**
   * Complex Tasks may be processed via the execution of a workflow process. In such cases, each significant step may be represented by a Task, and such Tasks
   * are aware of the process's Id.
   *
   * @return the (optional) Id of the process in which this Task is handled.
   */
  String getProcessId();

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
   * @param executor
   */
  void setExecutor(String executor);

  /**
   * @return current status of this context
   */
  ProcessingStatus getStatus();

  /**
   * Set the new status of the context. There is currently no formally enforced state transition model. The only assumption is that once a <code>Context</code>
   * has been set to a "final" state the setter will fail if any more state change is attempted.
   *
   * @param status
   * @return true if the state was successfully set, false if not
   */
  boolean setStatus(ProcessingStatus status);

  /**
   * @return the end time stamp
   */
  Date getEndTS();

  /**
   * TODO analyse if we can use a Java 8 Stream here?
   *
   * @return the list of all events that have happened in this context's lifecycle up-to "now"
   */
  List<ProcessingEvent<Task>> getEvents();

  /**
   * @return the results that have been gathered by this task
   */
  Collection<ResultBlock> getResults();

  /**
   *
   * @return all associated sub-tasks
   */
  List<Task> getSubTasks();
}
