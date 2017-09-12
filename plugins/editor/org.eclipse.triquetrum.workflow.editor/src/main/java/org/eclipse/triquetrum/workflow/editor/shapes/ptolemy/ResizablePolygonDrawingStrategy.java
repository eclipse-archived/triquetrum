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
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.data.ArrayToken;
import ptolemy.data.DoubleToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.vergil.kernel.attributes.ResizablePolygonAttribute;

public class ResizablePolygonDrawingStrategy extends FilledShapeDrawingStrategy<ResizablePolygonAttribute> {
  private final static Logger LOGGER = LoggerFactory.getLogger(ResizablePolygonDrawingStrategy.class);

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  // There's lots of duplicate logic in fill && draw border.
  // Maybe we need to pass a drawing context around so we can do calculations once, store them in it
  // and in that way pass results around between the consecutive method calls...
  @Override
  public void doFillShape(ResizablePolygonAttribute polygonAttr, Graphics graphics, Rectangle bounds) throws IllegalActionException {
    PointList pList = getPolygonPoints(polygonAttr);
    
    Dimension dim = bounds.getSize();
    Point tlp = bounds.getTopLeft();
    Dimension rawDim = getRawBounds(pList).getSize();
    
    // Ptolemy scales x and y potentially differently, depending on the ratios
    // of dim width & height and rawDim width & height respectively.
    double scaleWidth = dim.preciseWidth() / rawDim.preciseWidth();
    double scaleHeight = dim.preciseHeight() / rawDim.preciseHeight();
    Transform transform = new Transform();
    transform.setScale(scaleWidth, scaleHeight);
    pList = getTransformedPolygon(transform, pList);
    pList.translate(tlp);
    graphics.fillPolygon(pList);
  }

  @Override
  public void doDrawBorder(ResizablePolygonAttribute polygonAttr, Graphics graphics, Rectangle bounds) throws IllegalActionException {
    PointList pList = getPolygonPoints(polygonAttr);
    
    Dimension dim = bounds.getSize();
    Point tlp = bounds.getTopLeft();
    Dimension rawDim = getRawBounds(pList).getSize();
    
    // Ptolemy scales x and y potentially differently, depending on the ratios
    // of dim width & height and rawDim width & height respectively.
    double scaleWidth = dim.preciseWidth() / rawDim.preciseWidth();
    double scaleHeight = dim.preciseHeight() / rawDim.preciseHeight();
    Transform transform = new Transform();
    transform.setScale(scaleWidth, scaleHeight);
    pList = getTransformedPolygon(transform, pList);
    pList.translate(tlp);
    graphics.drawPolygon(pList);
  }

  private PointList getPolygonPoints(ResizablePolygonAttribute polygonAttr) throws IllegalActionException {
    PointList pList = new PointList();
    ArrayToken vertices = (ArrayToken) polygonAttr.vertices.getToken();
    for (int j = 0; j < vertices.length() / 2; j++) {
      pList.addPoint((int)((DoubleToken) vertices.getElement(2 * j)).doubleValue()
                    ,(int)((DoubleToken) vertices.getElement(2 * j + 1)).doubleValue());
    }
    return pList;
  }

  @Override
  protected Point getCenteringTranslation(ResizablePolygonAttribute polygonAttr) throws IllegalActionException {
    Dimension dim = getDimension(polygonAttr, null);
    // PtII has polygons where the points reach past the normal Top Left Point as set by the location attribute.
    // So we need to determine the sense of the translation for centering based on the raw center location 
    // relative to the expected TLP as defined by the location attribute.
    Rectangle rawBounds = getRawBounds(getPolygonPoints(polygonAttr));
    Dimension rawDim = rawBounds.getSize();
    double scaleWidth = dim.preciseWidth() / rawDim.preciseWidth();
    double scaleHeight = dim.preciseHeight() / rawDim.preciseHeight();

    Point rawCenter = rawBounds.getCenter();
    return new Point((int)-Math.rint(scaleWidth * rawCenter.preciseX()), (int)-Math.rint(scaleHeight * rawCenter.preciseY()));
  }
  
  private Rectangle getRawBounds(PointList pointList) {
    Point tlp = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
    Point brp = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
    for (int i=0; i<pointList.size(); ++i ) {
      Point p = pointList.getPoint(i);
      tlp.x = Math.min(tlp.x, p.x);
      tlp.y = Math.min(tlp.y, p.y);
      brp.x = Math.max(brp.x, p.x);
      brp.y = Math.max(brp.y, p.y);
    }
    return new Rectangle(tlp, brp);
  }
}
