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
 * @author delerw
 *
 */
public class EntryNotFoundException extends TriqException {
  private static final long serialVersionUID = -7997428299294386373L;

  /**
   * 
   * @param modelCode
   */
  public EntryNotFoundException(String modelCode) {
    super(ErrorCode.MODEL_LOADING_ERROR, "Model "+modelCode+" not found in the repository", null);
  }
}
