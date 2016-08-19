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
package org.eclipse.triquetrum;

import ptolemy.data.Token;
import ptolemy.kernel.util.Nameable;

/**
 *
 * TODO : evaluate if can replace the simplistic assignment to one input Token, with a more complete assignment to an actor's "input set"?
 *
 */
@SuppressWarnings("serial")
public class TriqException extends Exception {

  /**
   * The main token that was being processed when the exception was generated. In general, an actor's processing can
   * depend on multiple received messages. But in many cases one of the received messages can be designated as
   * "the most important one". This one could then be selected for alternative flow continuations ico errors.
   */
  private Token dataContext;
  /**
   * The model element to which the exception is related, or that has generated it. Typically an actor.
   */
  private Nameable modelElement;

  private ErrorCode errorCode;

  // the place where the full msg is kept stored once it has been built,
  // after the first call to getMessage()
  private String detailedMessage;

  public TriqException(ErrorCode errorCode) {
    this(errorCode, null, null, null, null);
  }

  public TriqException(ErrorCode errorCode, String message) {
    this(errorCode, message, null, null, null);
  }

  public TriqException(ErrorCode errorCode, String message, Throwable rootException) {
    this(errorCode, message, null, null, rootException);
  }

  public TriqException(ErrorCode errorCode, Throwable rootException) {
    this(errorCode, null, null, null, rootException);
  }

  public TriqException(ErrorCode errorCode, Nameable modelElement, Throwable rootException) {
    this(errorCode, null, modelElement, null, rootException);
  }

  public TriqException(ErrorCode errorCode, String message, Nameable modelElement, Throwable rootException) {
    this(errorCode, message, modelElement, null, rootException);
  }

  public TriqException(ErrorCode errorCode, String message, Nameable modelElement, Token dataContext, Throwable rootException) {
    super(message);
    if (errorCode == null) {
      throw new IllegalArgumentException("error code can not be null");
    }
    if(rootException!=null) {
      initCause(rootException);
    }
    this.errorCode = errorCode;
    this.modelElement = modelElement;
    this.dataContext = dataContext;
  }

  /**
   * @return the dataContext
   */
  public Token getDataContext() {
    return dataContext;
  }

  /**
   * @return the modelElement
   */
  public Nameable getModelElement() {
    return modelElement;
  }

  /**
   * @return the error code of this exception
   */
  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public ErrorCode.Severity getSeverity() {
    return errorCode.getSeverity();
  }

  /**
   * @return a string with the full info about the exception, incl severity, context etc.
   */
  public String getMessage() {
    if (detailedMessage == null) {
      StringBuilder msgBldr = new StringBuilder(getErrorCode() + " - " + super.getMessage());
      msgBldr.append("\n - Context:");
      if (modelElement != null) {
        msgBldr.append("\n\t -- element:" + modelElement);
      }
      if (dataContext != null) {
        msgBldr.append("\n\t -- token:" + dataContext);
      }
      if(getCause()!=null) {
        msgBldr.append("\n - RootException:" + getCause());
      }
      detailedMessage = msgBldr.toString();
    }
    return detailedMessage;
  }

  /**
   * @return just the simple message, as passed in the exception's constructor
   */
  public String getSimpleMessage() {
    return super.getMessage();
  }
}
