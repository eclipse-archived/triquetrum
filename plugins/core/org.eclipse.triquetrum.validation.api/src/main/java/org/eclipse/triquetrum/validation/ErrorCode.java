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
package org.eclipse.triquetrum.validation;

import org.eclipse.triquetrum.ErrorCategory;

/**
 * ErrorCodes should be used for all Triquetrum-related exceptions. They group info on :
 * <ul>
 * <li>a unique code to identify the type of error, which can be referred to in operational docs etc</li>
 * <li>a severity indicator</li>
 * <li>a topic to which the error is related, which can be used e.g. to publish error info on an event bus</li>
 * </ul>
 */
public class ErrorCode extends org.eclipse.triquetrum.ErrorCode {
  private static final long serialVersionUID = 1L;

  public static final ErrorCode MODEL_VALIDATION_WARNING = new ErrorCode("MODEL_VALIDATION_WARNING", "0110", ErrorCategory.FUNCTIONAL,
      ErrorCode.Severity.WARNING, "Warning validating Model");
  public static final ErrorCode MODEL_VALIDATION_ERROR = new ErrorCode("MODEL_VALIDATION_ERROR", "0111", ErrorCategory.FUNCTIONAL, ErrorCode.Severity.ERROR,
      "Error validating Model");

  public ErrorCode(String name, String code, ErrorCategory category, Severity severity, String description) {
    super(name, code, category, severity, description);
  }

  public ErrorCode(String name, String code, String topic, ErrorCategory category, Severity severity, String description) {
    super(name, code, topic, category, severity, description);
  }
}
