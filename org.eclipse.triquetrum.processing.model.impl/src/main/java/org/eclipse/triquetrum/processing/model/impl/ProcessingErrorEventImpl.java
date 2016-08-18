/*******************************************************************************
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.processing.model.impl;

import java.util.Date;
import java.util.List;

import org.eclipse.triquetrum.ErrorCategory;
import org.eclipse.triquetrum.ErrorCode;
import org.eclipse.triquetrum.ErrorCode.Severity;
import org.eclipse.triquetrum.processing.model.ProcessingErrorEvent;
import org.eclipse.triquetrum.processing.model.ProcessingEvent;

public class ProcessingErrorEventImpl<T> extends ProcessingEventImpl<T> implements ProcessingErrorEvent<T> {

  private Severity severity;
  private ErrorCategory category;
  private String code;
  private String shortDescription;
  private String description;
  private List<String> details;


  /**
   * @param context
   * @param id
   * @param creationTS
   * @param duration
   * @param topic
   * @param message
   * @param properties
   * @param severity
   * @param category
   * @param code
   * @param shortDescription
   * @param description
   * @param details
   */
  public ProcessingErrorEventImpl(T context, Long id, Date creationTS,
      Severity severity, ErrorCategory category, String code, String shortDescription, String description, List<String> details) {
    super(context, id, creationTS, 0L, "ERROR");
    this.severity = severity;
    this.category = category;
    this.code = code;
    this.shortDescription = shortDescription;
    this.description = description;
    this.details = details;
  }

  public ProcessingErrorEventImpl(T context, Long id, Date creationTS, ErrorCode errorCode, String description, List<String> details) {
    this(context, id, creationTS, errorCode.getSeverity(), errorCode.getCategory(), errorCode.getFormattedCode(), errorCode.getDescription(),
        description, details);
  }

  public ProcessingErrorEventImpl(T context, Long id, Date creationTS, ErrorCode errorCode, String description, Throwable cause) {
    this(context, id, creationTS, errorCode, description, (List<String>)null);
    // TODO add cause contents as details
  }

  @Override
  public int compareTo(ProcessingEvent<T> o) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Severity getSeverity() {
    return severity;
  }

  @Override
  public ErrorCategory getCategory() {
    return category;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getShortDescription() {
    return shortDescription;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public List<String> getDetails() {
    return details;
  }

}
