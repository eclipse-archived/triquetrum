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
package org.eclipse.triquetrum.workflow;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.triquetrum.Event;
import org.eclipse.triquetrum.ProcessingStatus;

/**
 * An event implementation as a basis for all specific events related to process executions.
 * <p>
 * Topics of ProcessEvents are of the format <code>org/eclipse/triquetrum/process/[PROCESS ID]/[STATUS]/...</code>, where [STATUS] is optional, i.e. when it's
 * an event about a state change.
 * </p>
 *
 * @see org.eclipse.debug.core.DebugEvent about event kind & detail
 */
public class ProcessEvent extends EventObject implements Event {
  private static final long serialVersionUID = 411059590003641225L;

  public final static String TOPIC_PREFIX = "org/eclipse/triquetrum/process/";
  public final static String BREAKPOINTS = "___breakpoints";

  private String processId;
  private Date timeStamp;
  private String topic;
  private Map<String, String> eventProperties = new HashMap<>();

  private ProcessingStatus status;
  private Throwable throwable;

  /**
   * Event to signal an arbitrary interesting fact occurring during a process execution. That fact should be summarised as a subtopic.
   *
   * @param processId
   * @param subtopic
   *          topic fragment that gets added after the topic prefix and processId
   */
  public ProcessEvent(String processId, String subTopic) {
    super(processId);
    this.processId = processId;
    this.topic = TOPIC_PREFIX + processId + "/" + subTopic;
    this.timeStamp = new Date();
  }

  /**
   * Event to signal a process status change.
   *
   * @param processId
   * @param status
   *          the new process status
   */
  public ProcessEvent(String processId, ProcessingStatus status) {
    super(processId);
    this.processId = processId;
    this.status = status;
    this.topic = TOPIC_PREFIX + processId + "/" + status;
    this.timeStamp = new Date();
  }

  /**
   * Event to signal that a process went in ERROR, due to the given errorCause.
   *
   * @param processId
   * @param errorCause
   */
  public ProcessEvent(String processId, Throwable errorCause) {
    super(processId);
    this.processId = processId;
    this.status = ProcessingStatus.ERROR;
    this.topic = TOPIC_PREFIX + processId + "/" + status;
    this.timeStamp = new Date();
    this.throwable = errorCause;
  }

  /**
   * @return the id of the process to which this event is related
   */
  public String getProcessId() {
    return processId;
  }

  /**
   *
   * @return the throwable that caused the process to go into ERROR status; null if the event is not related to a processing error.
   */
  public Throwable getThrowable() {
    return throwable;
  }

  /**
   *
   * @return the status for this event, if it's a status-related event; null otherwise
   */
  public ProcessingStatus getStatus() {
    return status;
  }

  @Override
  public String getTopic() {
    return topic;
  }

  @Override
  public Date getCreationTS() {
    return timeStamp;
  }

  /**
   * @return 0L as default duration
   */
  @Override
  public Long getDuration() {
    return 0L;
  }

  @Override
  public String getProperty(String propName) {
    return eventProperties.get(propName);
  }

  @Override
  public Iterator<String> getPropertyNames() {
    return eventProperties.keySet().iterator();
  }

  protected String putProperty(String propName, String propValue) {
    return eventProperties.put(propName, propValue);
  }

  protected String removeProperty(String propName) {
    return eventProperties.remove(propName);
  }

  @Override
  public String toString() {
    return toString(new SimpleDateFormat("dd/MM/yy HH:mm:ss.SSS"));
  }

  /**
   *
   * @param dateFormat
   * @return a toString representation of the event, where dates are formatted with the given dateFormat.
   */
  public String toString(DateFormat dateFormat) {
    return dateFormat.format(getCreationTS()) + " ProcessEvent [topic=" + getTopic() + "]";
  }
}
