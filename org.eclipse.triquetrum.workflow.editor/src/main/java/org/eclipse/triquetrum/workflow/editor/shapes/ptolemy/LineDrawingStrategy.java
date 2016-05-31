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
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.data.DoubleToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.vergil.kernel.attributes.LineAttribute;

public class LineDrawingStrategy extends AbstractDrawingStrategy<LineAttribute> {
  private final static Logger LOGGER = LoggerFactory.getLogger(LineDrawingStrategy.class);

  @Override
  public void draw(LineAttribute lineAttr, Graphics graphics, ResourceManager resourceManager) {
    Color fgColor = graphics.getForegroundColor();
    java.awt.Color color = lineAttr.lineColor.asColor();
    if (color != null) {
        Color rgb = resourceManager.createColor(new RGB(color.getRed(), color.getGreen(), color.getBlue()));
        graphics.setForegroundColor(rgb);
    }

    try {
      float lineWidth = (float) ((DoubleToken)lineAttr.lineWidth.getToken()).doubleValue();
      graphics.setLineWidthFloat(lineWidth);
      int x2_step = (int) ((DoubleToken)lineAttr.x.getToken()).doubleValue();
      int y2_step = (int) ((DoubleToken)lineAttr.y.getToken()).doubleValue();
      Point tlp = getTopLeftLocation(lineAttr, graphics);
      graphics.drawLine(tlp, tlp.getTranslated(x2_step, y2_step));
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for "+lineAttr.getFullName(), e);
    }
    graphics.setForegroundColor(fgColor);
  }

  @Override
  protected Dimension getDimension(LineAttribute lineAttr, Graphics graphics, ResourceManager resourceManager) {
    try {
      int x2_step = (int) ((DoubleToken)lineAttr.x.getToken()).doubleValue();
      int y2_step = (int) ((DoubleToken)lineAttr.y.getToken()).doubleValue();
      return new Dimension(x2_step, y2_step);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for "+lineAttr.getFullName(), e);
      return new Dimension(0,0);
    }
  }
}
