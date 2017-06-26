/*******************************************************************************
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.shapes.ptolemy;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ResourceManager;

import ptolemy.vergil.kernel.attributes.VisibleAttribute;

public interface DrawingStrategy<A extends VisibleAttribute> {

  /**
   * <p>
   * Contrary to what is often done in the SVG-based definitions, a MOML-defined icon does not define its overall size and location in the root element. Each
   * element/attribute just defines its own location and size. <br/>
   * Furthermore, Ptolemy II icon definitions often use negative coordinates, while draw2d graphics assumes a top-left corner at (0,0).
   * </p>
   * <p>
   * This method provides the defined size & location info for a given attribute/element in the icon definition.
   * </p>
   * 
   * @param visibleAttribute
   * @param graphics
   *          needed for some shapes to obtain info on sizes of text etc
   * @param resourceManager
   *          to be used to manage the creation of new SWT resources such as images, fonts, colours
   * @return
   */
  Rectangle getBounds(A visibleAttribute, Graphics graphics, ResourceManager resourceManager);

  /**
   *
   * @param visibleAttribute
   *          the thing defining what must be drawn
   * @param graphics
   *          the utility to actually get something drawn on the canvas
   * @param resourceManager
   *          to be used to manage the creation of new SWT resources such as images, fonts, colours
   */
  void draw(A visibleAttribute, Graphics graphics, ResourceManager resourceManager);

}
