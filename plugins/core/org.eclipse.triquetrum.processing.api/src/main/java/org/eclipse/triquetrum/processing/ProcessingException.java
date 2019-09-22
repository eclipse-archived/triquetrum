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
package org.eclipse.triquetrum.processing;

import org.eclipse.triquetrum.ErrorCode;
import org.eclipse.triquetrum.TriqException;

import ptolemy.data.Token;
import ptolemy.kernel.util.Nameable;

@SuppressWarnings("serial")
public class ProcessingException extends TriqException {

  public ProcessingException(ErrorCode errorCode, Throwable rootException) {
    super(errorCode, rootException);
  }

  public ProcessingException(ErrorCode errorCode, String message, Throwable rootException) {
    super(errorCode, message, rootException);
  }

  public ProcessingException(ErrorCode errorCode, Nameable modelElement, Throwable rootException) {
    super(errorCode, modelElement, rootException);
  }

  public ProcessingException(ErrorCode errorCode, String message, Nameable modelElement, Throwable rootException) {
    super(errorCode, message, modelElement, rootException);
  }

  public ProcessingException(ErrorCode errorCode, String message, Nameable modelElement, Token dataContext, Throwable rootException) {
    super(errorCode, message, modelElement, dataContext, rootException);
  }
}
