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
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.graphics.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.data.ArrayToken;
import ptolemy.data.BooleanToken;
import ptolemy.data.DoubleToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.vergil.kernel.attributes.ResizablePolygonAttribute;

public class ResizablePolygonDrawingStrategy extends AbstractDrawingStrategy<ResizablePolygonAttribute> {
  private final static Logger LOGGER = LoggerFactory.getLogger(ResizablePolygonDrawingStrategy.class);

  @Override
  public void draw(ResizablePolygonAttribute polygonAttr, Graphics graphics, ResourceManager resourceManager) {
    Color fgColor = graphics.getForegroundColor();
    Color bgColor = graphics.getBackgroundColor();
    Color lineColor = getSwtColor(polygonAttr.lineColor, resourceManager);
    if (lineColor!=null) {
      graphics.setForegroundColor(lineColor);
    }
    Color fillColor = getSwtColor(polygonAttr.fillColor, resourceManager);
    if (fillColor != null) {
      graphics.setBackgroundColor(fillColor);
    } else {
      graphics.setBackgroundColor(fgColor);
    }

    try {
      float lineWidth = (float) ((DoubleToken) polygonAttr.lineWidth.getToken()).doubleValue();
      graphics.setLineWidthFloat(lineWidth);

      Point tlp = getTopLeftLocation(polygonAttr, graphics);
      Dimension dim = getDimension(polygonAttr, graphics, resourceManager);
      
      ArrayToken vertices = (ArrayToken) polygonAttr.vertices.getToken();
      PointList pList = new PointList();
      for (int j = 0; j < vertices.length() / 2; j++) {
        pList.addPoint((int)((DoubleToken) vertices.getElement(2 * j)).doubleValue()
                      ,(int)((DoubleToken) vertices.getElement(2 * j + 1)).doubleValue());
      }
      
      Dimension rawDim = getRawDimension(pList);
      
      // Ptolemy scales x and y potentially differently, depending on the ratios
      // of dim width & height and rawDim width & height respectively.
      // But let's try to keep the aspect ratio here by scaling with only 1 common factor for x & y...
      double scaleWidth = dim.width / rawDim.width;
      double scaleHeight = dim.height / rawDim.height;
      pList.performScale(Math.min(scaleWidth, scaleHeight));
      // alternatively, if we want to scale differently along x & y :
//      Transform transform = new Transform();
//      transform.setScale(scaleWidth, scaleHeight);
//      pList = getTransformedPolygon(transform, pList);
      pList.translate(tlp);
      graphics.fillPolygon(pList);
      graphics.drawPolygon(pList);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for " + polygonAttr.getFullName(), e);
    }
    graphics.setForegroundColor(fgColor);
    graphics.setBackgroundColor(bgColor);
  }

  // take into account the "centered" attribute : when true, use the location as center of the polygon i.o. as the tlp
  @Override
  protected Point getTopLeftLocation(ResizablePolygonAttribute polygonAttr, Graphics graphics) {
    Point tlp = super.getTopLeftLocation(polygonAttr, graphics);
    try {
      boolean centered = ((BooleanToken) polygonAttr.centered.getToken()).booleanValue();
      if (centered) {
        Dimension dim = getDimension(polygonAttr, graphics, null);
        tlp = tlp.translate(-dim.width() / 2.0, -dim.height() / 2.0);
      }
    } catch (IllegalActionException e) {
      // ignore and let's just try to assume it's not centered
    }
    return tlp;
  }

  @Override
  protected Dimension getDimension(ResizablePolygonAttribute polygonAttr, Graphics graphics, ResourceManager resourceManager) {
    try {
      int width = (int) ((DoubleToken) polygonAttr.width.getToken()).doubleValue();
      int height = (int) ((DoubleToken) polygonAttr.height.getToken()).doubleValue();
      int lineWidth = (int) ((DoubleToken) polygonAttr.lineWidth.getToken()).doubleValue();
      return new Dimension(width+lineWidth, height+lineWidth);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for " + polygonAttr.getFullName(), e);
      return new Dimension(0, 0);
    }
  }
  
  private Dimension getRawDimension(PointList pointList) {
    Point tlp = new Point(0, 0);
    Point brp = new Point(0, 0);
    for (int i=0; i<pointList.size(); ++i ) {
      Point p = pointList.getPoint(i);
      tlp.x = Math.min(tlp.x, p.x);
      tlp.y = Math.min(tlp.y, p.y);
      brp.x = Math.max(brp.x, p.x);
      brp.y = Math.max(brp.y, p.y);
    }
    return new Dimension(brp.x - tlp.x, brp.y - tlp.y);
  }
}
