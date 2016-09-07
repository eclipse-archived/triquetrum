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
package org.eclipse.triquetrum.workflow;

import org.eclipse.triquetrum.TriqException;


/**
 * An exception class to indicate that a certain entry was not found in a workflow repository.
 *
 */
public class EntryNotFoundException extends TriqException {
  private static final long serialVersionUID = -7997428299294386373L;

  /**
   * Creates an exception with default error code and error message,
   * to indicate that the given model was not found in the repository
   *
   * @param modelCode
   */
  public EntryNotFoundException(String modelCode) {
    super(ErrorCode.MODEL_LOADING_ERROR, "Model "+modelCode+" not found in the repository", null);
  }

  /**
   * Creates an exception to indicate that something was not found in the repository,
   * with a custom error code and error message.
   *
   * @param errorCode
   * @param message
   */
  public EntryNotFoundException(org.eclipse.triquetrum.ErrorCode errorCode, String message) {
    super(errorCode, message, null);
  }
}
