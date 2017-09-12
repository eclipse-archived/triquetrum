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

import ptolemy.data.BooleanToken;
import ptolemy.data.DoubleToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.vergil.kernel.attributes.FilledShapeAttribute;

/**
 * Base class for all DrawingStrategy variations for subclasses of FilledShapeAttribute. It provides template methods to
 * enforce all common drawing behaviour, and to ensure correct cleanup of graphics properties.
 */
public abstract class FilledShapeDrawingStrategy<A extends FilledShapeAttribute> extends AbstractDrawingStrategy<A> {

  /**
   * Template method to enforce all standard logic for drawing the shape and its border.
   */
  @Override
  public final void draw(A shapeAttribute, Graphics graphics, ResourceManager resourceManager) {
    Color stdFgColor = graphics.getForegroundColor();
    Color stdBgColor = graphics.getBackgroundColor();
    float stdLineWidth = graphics.getLineWidthFloat();
    int stdAlpha = graphics.getAlpha();

    try {
      Point tlp = getTopLeftLocation(shapeAttribute);
      Dimension dim = getDimension(shapeAttribute, resourceManager);

      Color lineColor = getSwtColor(shapeAttribute.lineColor, resourceManager);
      Color fillColor = getSwtColor(shapeAttribute.fillColor, resourceManager);
      if (lineColor != null) {
        graphics.setForegroundColor(lineColor);
      }
      if (fillColor != null) {
        graphics.setBackgroundColor(fillColor);
      }

      float lineWidth = (float) ((DoubleToken) shapeAttribute.lineWidth.getToken()).doubleValue();
      graphics.setLineWidthFloat(lineWidth);

      Rectangle bounds = new Rectangle(tlp.x, tlp.y, dim.width(), dim.height());
      // do the filling of the shape
      graphics.setAlpha(fillColor.getAlpha());
      doFillShape(shapeAttribute, graphics, bounds);
      // draw the border of the shape
      graphics.setAlpha(lineColor.getAlpha());
      doDrawBorder(shapeAttribute, graphics, bounds);
    } catch (IllegalActionException e) {
      getLogger().error("Error reading properties of " + shapeAttribute.getFullName(), e);
    } finally {
      graphics.setLineWidthFloat(stdLineWidth);
      graphics.setForegroundColor(stdFgColor);
      graphics.setBackgroundColor(stdBgColor);
      graphics.setAlpha(stdAlpha);
    }
  }

  /**
   * Draw the filled shape of the right geometry, matching the type of shapeAttribute and the given bounds.
   * Implementations should not bother about colors and transparency. These have been set correctly before this method
   * will be invoked.
   * 
   * @param shapeAttribute
   * @param graphics
   * @param bounds
   * 
   * @throws IllegalActionException
   */
  protected abstract void doFillShape(A shapeAttribute, Graphics graphics, Rectangle bounds) throws IllegalActionException;

  /**
   * Draw the shape's border of the right geometry, matching the type of shapeAttribute and the given bounds.
   * Implementations should not bother about colors, transparency or line width. These have been set correctly before this
   * method will be invoked.
   * 
   * @param shapeAttribute
   * @param graphics
   * @param bounds
   * @throws IllegalActionException
   */
  protected abstract void doDrawBorder(A shapeAttribute, Graphics graphics, Rectangle bounds) throws IllegalActionException;

  /**
   * 
   * @return the right logger for the actual strategy implementation
   */
  protected abstract Logger getLogger();

  /**
   * Take into account the "centered" property : when true, use the configured location as center of the shape i.o. as its
   * top-left-point.
   * 
   * @return the adjusted TL location i.c.o. a centered shape
   */
  @Override
  protected final Point getTopLeftLocation(A shapeAttribute) {
    Point tlp = super.getTopLeftLocation(shapeAttribute);
    try {
      boolean centered = ((BooleanToken) shapeAttribute.centered.getToken()).booleanValue();
      if (centered) {
        tlp = tlp.translate(getCenteringTranslation(shapeAttribute));
      }
    } catch (IllegalActionException e) {
      // ignore and let's just try to assume it's not centered
    }
    return tlp;
  }

  /**
   * Calculates the translation that must be applied to the TL location, when centering the shape. This default
   * implementation translates by half the width and half the height of the shape's dimension.
   * <p>
   * Remark that the translation moves upwards, i.e. substracting from the normal TL location coordinates, as the
   * assumption is that the TL location is what it says it is : Top Left, i.e. the shape is located below-right of that.
   * <br/>
   * There are cases in Ptolemy II icon definitions where this is not so, i.e. where a VisibleAttribute's location is not
   * guaranteed to be Top Left. (e.g. with resizable polygons).
   * </p>
   * 
   * @param shapeAttribute
   * @return
   * @throws IllegalActionException
   */
  protected Point getCenteringTranslation(A shapeAttribute) throws IllegalActionException {
    Dimension dim = getDimension(shapeAttribute, null);
    return new Point((int) Math.rint(-dim.preciseWidth() / 2.0), (int) Math.rint(-dim.preciseHeight() / 2.0));
  }

  /**
   * @return the shape's dimension based oon the width and height of the shapeAttribute
   */
  @Override
  protected final Dimension getDimension(A shapeAttribute, ResourceManager resourceManager) {
    try {
      int width = (int) ((DoubleToken) shapeAttribute.width.getToken()).doubleValue();
      int height = (int) ((DoubleToken) shapeAttribute.height.getToken()).doubleValue();
      return new Dimension(width, height);
    } catch (IllegalActionException e) {
      getLogger().error("Error reading dimensions for " + shapeAttribute.getFullName(), e);
      return new Dimension(0, 0);
    }
  }
}
