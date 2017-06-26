/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.validation;

import org.eclipse.triquetrum.ErrorCode;
import org.eclipse.triquetrum.TriqException;

import ptolemy.kernel.util.Nameable;

@SuppressWarnings("serial")
public class ValidationException extends TriqException {

  public ValidationException(ErrorCode errorCode, Nameable modelElement) {
    super(errorCode, modelElement, null);
  }

  public ValidationException(ErrorCode errorCode, String message, Nameable modelElement) {
    super(errorCode, message, modelElement, null);
  }
}
