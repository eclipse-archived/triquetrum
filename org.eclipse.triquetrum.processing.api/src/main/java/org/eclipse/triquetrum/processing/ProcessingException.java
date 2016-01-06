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
