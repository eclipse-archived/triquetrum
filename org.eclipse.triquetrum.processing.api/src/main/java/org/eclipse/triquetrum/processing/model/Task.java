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
import java.util.Iterator;
import java.util.List;


/**
 * A Task specifies an item of work to be performed. It defines WHAT must be done, not how it must be achieved.
 * <p>
 * Simple work can be handled as one "unit of work" that can be executed by a <code>TaskProcessingService</code>.
 * Complex tasks could be handled by a workflow execution, where actors launch sub-tasks for each meaningful step to get the work done.
 * </p>
 * <p>
 * The definition consists of following main elements :
 * <ul>
 * <li>type : the main differentiator on the kind of work to be performed</li>
 * <li>a set of parameters : these define the data needed to perform the given task</li>
 * <li>results : the internal structure is a set of result blocks containing result items. 
 * In specialized domains, like science, raw results may not be fit for internal storage but could e.g. be stored in special/huge files.
 * Then the triq result items may be used to represent references to such files/datasets, and/or derived data obtained during the workflow execution.</li>
 * </ul>
 * </p>
 */
public interface Task extends Serializable, Identifiable, AttributeHolder {

  /**
   * @return the type of this task, that typically is the main key to determine by which service this task must be processed
   */
  String getType();

  /**
   * @return a unique identifier/name of the initiator, i.e. the party or system component
   * responsible for the initiation of this task. 
   * 
   */
  String getInitiator();
  
  /**
   * @return a unique identifier/name of the executor, i.e. the party or system component
   * that (was) determined to be responsible for the handling of this task. 
   * 
   */
  String getExecutor();
  
  /**
   * 
   * @param executor
   */
  void setExecutor(String executor);
  
  /**
   * A correlation ID can be specified by the initiator. 
   * Triquetrum will then ensure that in any notifications, acknowledgements or other kinds of feedback, the correlation ID will be available.
   * This can be used by external systems to facilitate correlating their original requests with later asynchronous responses from the triq runtime.
   * 
   * @return the correlation Id that was received from the initiator.
   */
  String getCorrelationId();

  /**
   * @return current status of this context
   */
  ProcessStatus getStatus();

  /**
   * Set the new status of the context. There is currently no formally enforced state transition model. 
   * The only assumption is that once a <code>Context</code> has been set to a "final" state 
   * the setter will fail if any more state change is attempted.
   * 
   * @param status
   * @return true if the state was successfully set, false if not
   */
  boolean setStatus(ProcessStatus status);

  /**
   * @return the end time stamp
   */
  Date getEndTS();

   /**
   * @return the list of all events that have happened in this context's lifecycle up-to "now"
   */
  List<TaskEvent> getEvents();

  /**
   * Store some named entry in the context. 
   * This is a context-wide storage, not linked to a specific task or its results.
   * 
   * @param name
   * @param value
   */
  void putEntry(String name, Serializable value);

  /**
   * Remove the entry with the given name
   * 
   * @param name
   * @return the entry that was present for the given name (or null if none was there)
   */
  Serializable removeEntry(String name);
  
  /**
   * 
   * @param name
   * @return the entry stored under the given name in the context, or null if not present.
   */
  Serializable getEntryValue(String name);

  /**
   * 
   * @return the names of all stored context entries
   */
  Iterator<String> getEntryNames();

  /**
   * 
   * @return all associated sub-tasks
   */
  List<Task> getSubTasks();

  /**
   * @return the results that have been gathered by this task
   */
  Collection<ResultBlock> getResults();
}
