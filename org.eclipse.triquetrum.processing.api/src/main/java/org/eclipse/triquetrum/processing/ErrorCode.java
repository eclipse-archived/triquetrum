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
package org.eclipse.triquetrum.processing;

import org.eclipse.triquetrum.ErrorCategory;

/**
 * Adds Error Codes for task processing
 *
 */
public class ErrorCode extends org.eclipse.triquetrum.ErrorCode {
  private static final long serialVersionUID = 2721384108182472678L;

  public static final ErrorCode TASK_INIT_ERROR = new ErrorCode("TASK_INIT_ERROR", "5000", "task/ERROR", ErrorCategory.FUNCTIONAL, Severity.ERROR,
      "Task initialisation error");
  public static final ErrorCode TASK_ERROR = new ErrorCode("TASK_ERROR", "5010", "task/ERROR", ErrorCategory.FUNCTIONAL, Severity.ERROR,
      "Task processing error");
  public static final ErrorCode TASK_SLOW = new ErrorCode("TASK_SLOW", "5011", "task/SLOW", ErrorCategory.FUNCTIONAL, Severity.WARNING, "Task processing slow");
  public static final ErrorCode TASK_TIMEOUT = new ErrorCode("TASK_TIMEOUT", "5012", "task/TIME_OUT", ErrorCategory.FUNCTIONAL, Severity.WARNING,
      "Task processing timed out");
  public static final ErrorCode TASK_UNHANDLED = new ErrorCode("TASK_UNHANDLED", "5020", "task/ERROR", ErrorCategory.FUNCTIONAL, Severity.ERROR,
      "No service found to handle the task");

  /**
   * @param name
   * @param code
   * @param category
   * @param severity
   * @param description
   */
  public ErrorCode(String name, String code, ErrorCategory category, Severity severity, String description) {
    super(name, code, category, severity, description);
  }

  /**
   * @param name
   * @param code
   * @param topic
   * @param category
   * @param severity
   * @param description
   */
  public ErrorCode(String name, String code, String topic, ErrorCategory category, Severity severity, String description) {
    super(name, code, topic, category, severity, description);
  }
}
