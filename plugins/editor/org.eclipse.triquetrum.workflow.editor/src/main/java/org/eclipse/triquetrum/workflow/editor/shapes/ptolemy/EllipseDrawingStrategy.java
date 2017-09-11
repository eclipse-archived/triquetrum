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
import ptolemy.vergil.kernel.attributes.EllipseAttribute;

public class EllipseDrawingStrategy extends AbstractDrawingStrategy<EllipseAttribute> {
  private final static Logger LOGGER = LoggerFactory.getLogger(EllipseDrawingStrategy.class);

  @Override
  public void draw(EllipseAttribute ellipseAttr, Graphics graphics, ResourceManager resourceManager) {
    Color fgColor = graphics.getBackgroundColor();

    try {
      Point tlp = getTopLeftLocation(ellipseAttr, graphics);
      Dimension dim = getDimension(ellipseAttr, graphics, resourceManager);
      
      double lineWidth = ((DoubleToken) ellipseAttr.lineWidth.getToken()).doubleValue();
      Color rgb = getSwtColor(ellipseAttr.lineColor, resourceManager);
      if (rgb != null) {
        graphics.setBackgroundColor(rgb);
      }

      Rectangle rectWithBorder = new Rectangle(tlp.x, tlp.y, dim.width(), dim.height());
      Rectangle rect = new Rectangle(rectWithBorder).shrink(lineWidth, lineWidth);
      graphics.fillOval(rectWithBorder);

      rgb = getSwtColor(ellipseAttr.fillColor, resourceManager);
      if (rgb != null) {
        graphics.setBackgroundColor(rgb);
      } else {
        graphics.setBackgroundColor(fgColor);
      }
      graphics.fillOval(rect);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for " + ellipseAttr.getFullName(), e);
    }
    graphics.setBackgroundColor(fgColor);
  }

  // take into account the "centered" attribute : when true, use the location as center of the ellipse i.o. as the tlp
  @Override
  protected Point getTopLeftLocation(EllipseAttribute ellipseAttr, Graphics graphics) {
    Point tlp = super.getTopLeftLocation(ellipseAttr, graphics);
    try {
      boolean centered = ((BooleanToken) ellipseAttr.centered.getToken()).booleanValue();
      if (centered) {
        Dimension dim = getDimension(ellipseAttr, graphics, null);
        tlp = tlp.translate(-dim.width() / 2.0, -dim.height() / 2.0);
      }
    } catch (IllegalActionException e) {
      // ignore and let's just try to assume it's not centered
    }
    return tlp;
  }

  @Override
  protected Dimension getDimension(EllipseAttribute ellipseAttr, Graphics graphics, ResourceManager resourceManager) {
    try {
      int width = (int) ((DoubleToken) ellipseAttr.width.getToken()).doubleValue();
      int height = (int) ((DoubleToken) ellipseAttr.height.getToken()).doubleValue();
      int lineWidth = (int) ((DoubleToken) ellipseAttr.lineWidth.getToken()).doubleValue();
      return new Dimension(width+lineWidth, height+lineWidth);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for " + ellipseAttr.getFullName(), e);
      return new Dimension(0, 0);
    }
  }
}
