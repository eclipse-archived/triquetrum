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
package org.eclipse.triquetrum.workflow.editor;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * Interface for different types of model element categorizations.
 * <p>
 * The main purpose of this interface is to declare the storeIn() method, besides being a marker for categories.
 * </p>
 */
public interface Category {
  /**
   * Stores the category as a property on the pe.
   * 
   * @param pe
   */
  void storeIn(PictogramElement pe);
  
  // the symmetric operation retrieveFrom(PictogramElement pe) is assumed to be implemented 
  // as a static method on the category implementation classes.
}
