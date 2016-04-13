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
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.data.DoubleToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.vergil.kernel.attributes.RectangleAttribute;

public class RectangleDrawingStrategy extends AbstractDrawingStrategy<RectangleAttribute> {
  private final static Logger LOGGER = LoggerFactory.getLogger(RectangleDrawingStrategy.class);

  @Override
  public void draw(RectangleAttribute rectangleAttr, Graphics graphics) {
    Color fgColor = graphics.getBackgroundColor();
    java.awt.Color color = rectangleAttr.fillColor.asColor();
    if (color != null) {
      // TODO figure out if and how such colors must be managed and disposed etc
      Color rgb = new Color(null, color.getRed(), color.getGreen(), color.getBlue());
      graphics.setBackgroundColor(rgb);
    }

    try {
      int width = (int) ((DoubleToken) rectangleAttr.width.getToken()).doubleValue();
      int height = (int) ((DoubleToken) rectangleAttr.height.getToken()).doubleValue();
      Point tlp = getTopLeftLocation(rectangleAttr, graphics);
      graphics.fillRectangle(tlp.x, tlp.y, width, height);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for "+rectangleAttr.getFullName(), e);
    }
    graphics.setBackgroundColor(fgColor);
  }

  @Override
  protected Dimension getDimension(RectangleAttribute rectangleAttr, Graphics graphics) {
    try {
      int width = (int) ((DoubleToken) rectangleAttr.width.getToken()).doubleValue();
      int height = (int) ((DoubleToken) rectangleAttr.height.getToken()).doubleValue();
      return new Dimension(width, height);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for "+rectangleAttr.getFullName(), e);
      return new Dimension(0,0);
    }
  }
}
