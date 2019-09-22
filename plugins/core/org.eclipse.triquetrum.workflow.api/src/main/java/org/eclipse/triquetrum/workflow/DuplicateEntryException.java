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
package org.eclipse.triquetrum.workflow;

import org.eclipse.triquetrum.TriqException;

import ptolemy.actor.TypedCompositeActor;

/**
 * @author erwin
 *
 */
public class DuplicateEntryException extends TriqException {

  private static final long serialVersionUID = 7349254541339728923L;

  /**
   * @param modelCode
   */
  public DuplicateEntryException(String modelCode) {
    super(ErrorCode.MODEL_LOADING_ERROR, "Model " + modelCode + " already exists in the repository", null);
  }

  /**
   * @param modelCode
   * @param model
   */
  public DuplicateEntryException(String modelCode, TypedCompositeActor model) {
    super(ErrorCode.MODEL_LOADING_ERROR, "Model " + modelCode + " already exists in the repository", model, null);
  }
}
