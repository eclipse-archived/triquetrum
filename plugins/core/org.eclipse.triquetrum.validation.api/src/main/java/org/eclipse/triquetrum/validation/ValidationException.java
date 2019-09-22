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
