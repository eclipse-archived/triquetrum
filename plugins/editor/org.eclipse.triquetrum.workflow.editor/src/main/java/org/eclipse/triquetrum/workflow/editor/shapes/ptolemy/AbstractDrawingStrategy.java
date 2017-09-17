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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transform;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGBA;
import org.eclipse.triquetrum.workflow.editor.util.RGBAColorDescriptor;

import ptolemy.actor.gui.ColorAttribute;
import ptolemy.kernel.util.Location;
import ptolemy.vergil.kernel.attributes.VisibleAttribute;

/**
 * Abstract base class for all current drawing strategies.
 * It mainly provides some simple utility methods.
 */
public abstract class AbstractDrawingStrategy<A extends VisibleAttribute> implements DrawingStrategy<A> {

  @Override
  public final Rectangle getBounds(A visibleAttribute, ResourceManager resourceManager) {
    return new Rectangle(getTopLeftLocation(visibleAttribute), getDimension(visibleAttribute, resourceManager));
  }

  /**
   * 
   * @param visibleAttribute
   * @return the attribute's configured location, which should serve as top-left location for the shape to draw.
   */
  protected Point getTopLeftLocation(A visibleAttribute) {
    Location location = (Location) visibleAttribute.getAttribute("_location");
    int x1 = (int) location.getLocation()[0];
    int y1 = (int) location.getLocation()[1];
    return new Point(x1, y1);
  }

  /**
   * 
   * @param colorAttribute
   * @param resourceManager
   * @return the SWT color resource matching the RGBA properties of the colorAttribute
   */
  protected final Color getSwtColor(ColorAttribute colorAttribute, ResourceManager resourceManager) {
    java.awt.Color color = colorAttribute != null ? colorAttribute.asColor() : null;
    Color rgb = null;
    if (color != null) {
      rgb = resourceManager.createColor(new RGBAColorDescriptor(new RGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())));
    }
    return rgb;
  }

  /**
   * Applies the given transformation to all points in the pointList.
   * (really just filling a gap in the PointList API)
   * @param trf
   * @param pointList
   * @return the list of transformed points
   */
  protected final PointList getTransformedPolygon(Transform trf, PointList pointList) {
    PointList result = new PointList(pointList.size());
    for (int i=0; i<pointList.size(); ++i ) {
      result.addPoint(trf.getTransformed(pointList.getPoint(i)));
    }
    return result;
  }

  /**
   * Calculates the dimension of the shape for the given attribute.
   * In some special cases, this requires creating graphical resources (e.g. fonts) to be able to calculate the real size.
   * 
   * @param visibleAttribute
   * @param resourceManager
   * @return the calculated dimension of the shape that must be drawn for the given attribute
   */
  protected abstract Dimension getDimension(A visibleAttribute, ResourceManager resourceManager);
}
