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
package org.eclipse.triquetrum.workflow.execution.impl.event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author erwin
 *
 */
public class ProcessEvent extends EventObject implements org.eclipse.triquetrum.workflow.ProcessEvent {
  private static final long serialVersionUID = 411059590003641225L;
  
  public final static String BREAKPOINTS = "___breakpoints";
  
  private String processId;
  private Date timeStamp;
  private String topic;
  
  private Map<String, String> eventProperties =  new HashMap<String, String>();
  private Kind kind;
  private Detail detail;
  
  public ProcessEvent(String processId, Kind kind, Detail detail) {
    super(processId);
    this.processId = processId;
    this.topic = TOPIC_PREFIX + processId + "/" + kind.name()+"/"+detail.name();
    this.timeStamp = new Date();
    this.kind = kind;
    this.detail = detail;
  }
  
  @Override
  public String getProcessId() {
    return processId;
  }

  public String getTopic() {
    return topic;
  }
  
  public Date getCreationTS() {
    return timeStamp;
  }

  /**
   * @return 0L as default duration
   */
  public Long getDuration() {
    return 0L;
  }
  
  public String getProperty(String propName) {
    return eventProperties.get(propName);
  }
  
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
  
  @Override
  public Kind getKind() {
    return kind;
  }

  @Override
  public Detail getDetail() {
    return detail;
  }

  /**
   * 
   * @param dateFormat
   * @return a toString representation of the event, 
   * where dates are formatted with the given dateFormat.
   */
  public String toString(DateFormat dateFormat) {
    return dateFormat.format(getCreationTS()) + " ProcessEvent [topic=" + getTopic() + "]";
  }
}
