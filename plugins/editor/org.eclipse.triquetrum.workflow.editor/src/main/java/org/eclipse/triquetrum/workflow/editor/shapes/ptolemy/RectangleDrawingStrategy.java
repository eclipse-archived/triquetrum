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
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.graphics.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.data.BooleanToken;
import ptolemy.data.DoubleToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.vergil.kernel.attributes.RectangleAttribute;

public class RectangleDrawingStrategy extends AbstractDrawingStrategy<RectangleAttribute> {
  private final static Logger LOGGER = LoggerFactory.getLogger(RectangleDrawingStrategy.class);

  @Override
  public void draw(RectangleAttribute rectangleAttr, Graphics graphics, ResourceManager resourceManager) {
    Color fgColor = graphics.getBackgroundColor();

    try {
      Point tlp = getTopLeftLocation(rectangleAttr, graphics);
      Dimension dim = getDimension(rectangleAttr, graphics, resourceManager);
      
      int rounding = (int) ((DoubleToken) rectangleAttr.rounding.getToken()).doubleValue();
      double lineWidth = ((DoubleToken) rectangleAttr.lineWidth.getToken()).doubleValue();
      Color rgb = getSwtColor(rectangleAttr.lineColor, resourceManager);
      if (rgb != null) {
        graphics.setBackgroundColor(rgb);
      }

      Rectangle rectWithBorder = new Rectangle(tlp.x, tlp.y, dim.width(), dim.height());
      Rectangle rect = new Rectangle(rectWithBorder).shrink(lineWidth, lineWidth);
      if (rounding > 0) {
        graphics.fillRoundRectangle(rectWithBorder, rounding, rounding);
      } else {
        graphics.fillRectangle(rectWithBorder);
      }

      rgb = getSwtColor(rectangleAttr.fillColor, resourceManager);
      if (rgb != null) {
        graphics.setBackgroundColor(rgb);
      } else {
        graphics.setBackgroundColor(fgColor);
      }
      graphics.fillRectangle(rect);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for " + rectangleAttr.getFullName(), e);
    }
    graphics.setBackgroundColor(fgColor);
  }

  // take into account the "centered" attribute : when true, use the location as center of the rectangle i.o. as the tlp
  @Override
  protected Point getTopLeftLocation(RectangleAttribute rectangleAttr, Graphics graphics) {
    Point tlp = super.getTopLeftLocation(rectangleAttr, graphics);
    try {
      boolean centered = ((BooleanToken) rectangleAttr.centered.getToken()).booleanValue();
      if (centered) {
        Dimension dim = getDimension(rectangleAttr, graphics, null);
        tlp = tlp.translate(-dim.width() / 2.0, -dim.height() / 2.0);
      }
    } catch (IllegalActionException e) {
      // ignore and let's just try to assume it's not centered
    }
    return tlp;
  }

  @Override
  protected Dimension getDimension(RectangleAttribute rectangleAttr, Graphics graphics, ResourceManager resourceManager) {
    try {
      int width = (int) ((DoubleToken) rectangleAttr.width.getToken()).doubleValue();
      int height = (int) ((DoubleToken) rectangleAttr.height.getToken()).doubleValue();
      int lineWidth = (int) ((DoubleToken) rectangleAttr.lineWidth.getToken()).doubleValue();
      return new Dimension(width+lineWidth, height+lineWidth);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for " + rectangleAttr.getFullName(), e);
      return new Dimension(0, 0);
    }
  }
}
