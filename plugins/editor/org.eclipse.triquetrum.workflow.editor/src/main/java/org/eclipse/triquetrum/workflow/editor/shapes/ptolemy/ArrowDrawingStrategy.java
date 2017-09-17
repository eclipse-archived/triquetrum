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
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Transform;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.graphics.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.data.DoubleToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.vergil.kernel.attributes.ArrowAttribute;

public class ArrowDrawingStrategy extends AbstractDrawingStrategy<ArrowAttribute> {
  private final static Logger LOGGER = LoggerFactory.getLogger(ArrowDrawingStrategy.class);

  @Override
  public void draw(ArrowAttribute arrowAttr, Graphics graphics, ResourceManager resourceManager) {
    Color fgColor = graphics.getForegroundColor();
    Color bgColor = graphics.getBackgroundColor();
    Color rgb = getSwtColor(arrowAttr.lineColor, resourceManager);
    if (rgb != null) {
      graphics.setForegroundColor(rgb);
      graphics.setBackgroundColor(rgb);
    }

    try {
      float lineWidth = (float) ((DoubleToken) arrowAttr.lineWidth.getToken()).doubleValue();
      graphics.setLineWidthFloat(lineWidth);
      int x = (int) ((DoubleToken) arrowAttr.x.getToken()).doubleValue();
      int y = (int) ((DoubleToken) arrowAttr.y.getToken()).doubleValue();
      int width = (int) ((DoubleToken) arrowAttr.arrowWidth.getToken()).doubleValue();
      int length = (int) ((DoubleToken) arrowAttr.arrowLength.getToken()).doubleValue();
      int halfWidth = width/2;
      Point tlp = getTopLeftLocation(arrowAttr);
      Transform transform = new Transform();
      transform.setRotation(Math.atan2(y, x));
      PointList pList = new PointList();
      pList.addPoint(0, halfWidth);
      pList.addPoint(length + 3,  0);
      pList.addPoint(length,  halfWidth);
      pList.addPoint((int) Math.sqrt(x*x + y*y),  halfWidth);
      pList.addPoint(length,  halfWidth);
      pList.addPoint(length + 3,  width);
      pList = getTransformedPolygon(transform, pList);
      pList.translate(tlp);
      graphics.fillPolygon(pList);
      graphics.drawPolygon(pList);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for " + arrowAttr.getFullName(), e);
    }
    graphics.setForegroundColor(fgColor);
    graphics.setBackgroundColor(bgColor);
  }

  @Override
  protected Dimension getDimension(ArrowAttribute arrowAttr, ResourceManager resourceManager) {
    try {
      int length = (int) ((DoubleToken) arrowAttr.arrowLength.getToken()).doubleValue();
      int width = (int) ((DoubleToken) arrowAttr.arrowWidth.getToken()).doubleValue();
      return new Dimension(length, width);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for " + arrowAttr.getFullName(), e);
      return new Dimension(0, 0);
    }
  }
}
