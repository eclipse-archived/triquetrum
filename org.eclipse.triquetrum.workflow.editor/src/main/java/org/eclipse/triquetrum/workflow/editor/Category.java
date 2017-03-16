/*******************************************************************************
 * Copyright (c) 2017 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
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
