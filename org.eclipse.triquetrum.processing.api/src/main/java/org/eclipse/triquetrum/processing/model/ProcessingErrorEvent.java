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

import java.util.List;

import javax.print.attribute.standard.Severity;

import org.eclipse.triquetrum.ErrorCategory;


/**
 * Maintains all info related to an error that occurred during
 * the processing of a task.
 * <p>
 * Such error info is typically logged in a well-formatted manner to allow monitoring tools to automatically pick-up specific error types.
 * This can be done via parsing log files, or querying DB records in case execution traces are persisted in a DB etc.
 * </p>
 */
public interface ProcessingErrorEvent<T> extends ProcessingEvent<T> {

  /**
   * @return how severe was the error
   */
  Severity getSeverity();

  /**
   * @return whether the error concerns a functional or technical issue.
   */
  ErrorCategory getCategory();

  /**
   * @return a formatted error code identifying the error type of this item, e.g. TRIQ-1234
   */
  String getCode();

  /**
   * @return short description, e.g. Missing data, Timed out
   */
  String getShortDescription();

  /**
   * @return a readable description of the error type of this item
   */
  String getDescription();

  /**
   * @return an optional list of extra error details. When no details available this should return an empty list.
   */
  List<String> getDetails();
}
