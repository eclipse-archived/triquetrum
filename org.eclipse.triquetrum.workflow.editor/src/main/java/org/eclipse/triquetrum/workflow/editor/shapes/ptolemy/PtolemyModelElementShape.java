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

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.platform.ga.IRendererContext;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramBehavior;
import org.eclipse.triquetrum.workflow.editor.shapes.AbstractCustomModelElementShape;
import org.eclipse.triquetrum.workflow.util.WorkflowUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.vergil.icon.EditorIcon;
import ptolemy.vergil.kernel.attributes.LineAttribute;
import ptolemy.vergil.kernel.attributes.RectangleAttribute;
import ptolemy.vergil.kernel.attributes.TextAttribute;
import ptolemy.vergil.kernel.attributes.VisibleAttribute;

public class PtolemyModelElementShape extends AbstractCustomModelElementShape {
  private final static Logger LOGGER = LoggerFactory.getLogger(PtolemyModelElementShape.class);

  private static Map<Class<? extends VisibleAttribute>, DrawingStrategy<? extends VisibleAttribute>> drawingStrategies = new HashMap<>();

  static {
    drawingStrategies.put(RectangleAttribute.class, new RectangleDrawingStrategy());
    drawingStrategies.put(LineAttribute.class, new LineDrawingStrategy());
    drawingStrategies.put(TextAttribute.class, new TextDrawingStrategy());
  }

  private Rectangle ptShapeBounds;
  private EditorIcon iconDef;
  private ResourceManager resourceManager;

  public PtolemyModelElementShape(IRendererContext rendererContext) {
    super(rendererContext);
    this.resourceManager = ((TriqDiagramBehavior) rendererContext.getDiagramTypeProvider().getDiagramBehavior()).getResourceManager();
  }

  @Override
  protected void fillShape(Graphics graphics) {
    LOGGER.trace("Ptolemy fillShape - entry - for {}", getIconURI());
    try {
      iconDef = iconDef != null ? iconDef : (EditorIcon) WorkflowUtils.readFrom(URI.create(getIconURI()));
      // As Ptolemy II icon definitions often use negative coordinates,
      // while draw2d graphics assumes a top-left corner at (0,0),
      // the overall icon shape drawing must first determine the most extreme
      // boundaries as defined in the icon MOML and translate the draw2d coordinates space
      // accordingly before starting the effective drawing.
      ptShapeBounds = ptShapeBounds != null ? ptShapeBounds : determineExtremeBounds(iconDef, graphics);
      LOGGER.debug("Extreme bounds for {} : {}", getIconURI(), ptShapeBounds);

      int width = ptShapeBounds.width;
      int height = ptShapeBounds.height;

      Rectangle bnds = getBounds();
      graphics.drawRectangle(bnds.x, bnds.y, width, height);
      graphics.translate(getLocation());
      graphics.translate(ptShapeBounds.getTopLeft().getNegated().getTranslated(1, 1));
      for (VisibleAttribute a : iconDef.attributeList(VisibleAttribute.class)) {
        DrawingStrategy drawingStrategy = drawingStrategies.get(a.getClass());
        if (drawingStrategy != null) {
          drawingStrategy.draw(a, graphics, resourceManager);
        }
      }
      setInitialSize(ga, width + 2, height + 2);
    } catch (Exception e) {
      LOGGER.error("Error drawing ptolemy shape " + getIconURI(), e);
    }
    LOGGER.trace("Ptolemy fillShape - exit - for {}", getIconURI());
  }

  @Override
  protected void outlineShape(Graphics graphics) {
    float lineInset = Math.max(1.0f, getLineWidthFloat()) / 2.0f;
    int inset1 = (int) Math.floor(lineInset);
    int inset2 = (int) Math.ceil(lineInset);

    Rectangle r = Rectangle.SINGLETON.setBounds(getBounds());
    r.x += inset1;
    r.y += inset1;
    r.width -= inset1 + inset2;
    r.height -= inset1 + inset2;

    graphics.drawRectangle(r);
  }

  private Rectangle determineExtremeBounds(EditorIcon iconDef, Graphics graphics) {
    LOGGER.trace("Ptolemy determineExtremeBounds - entry - for {}", iconDef.getName());
    Point tlp = new Point(0, 0);
    Point brp = new Point(0, 0);
    for (VisibleAttribute a : iconDef.attributeList(VisibleAttribute.class)) {
      DrawingStrategy drawingStrategy = drawingStrategies.get(a.getClass());
      if (drawingStrategy != null) {
        Rectangle aBounds = drawingStrategy.getBounds(a, graphics, resourceManager);
        LOGGER.debug("Bounds for {} : {}", a, aBounds);
        tlp.x = Math.min(tlp.x, aBounds.x);
        tlp.y = Math.min(tlp.y, aBounds.y);
        brp.x = Math.max(brp.x, aBounds.x + aBounds.width);
        brp.y = Math.max(brp.y, aBounds.y + aBounds.height);
      }
    }
    Rectangle result = new Rectangle(tlp, brp);
    LOGGER.trace("Ptolemy determineExtremeBounds - exit - for {} - bounds {}", iconDef.getName(), result);
    return result;
  }
}
