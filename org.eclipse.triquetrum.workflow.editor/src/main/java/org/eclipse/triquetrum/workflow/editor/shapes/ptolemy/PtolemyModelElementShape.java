/*******************************************************************************
 * Copyright (c) 2015-2016 iSencia Belgium NV.
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
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRenderer;
import org.eclipse.triquetrum.workflow.util.WorkflowUtils;

import ptolemy.vergil.icon.EditorIcon;
import ptolemy.vergil.kernel.attributes.LineAttribute;
import ptolemy.vergil.kernel.attributes.RectangleAttribute;
import ptolemy.vergil.kernel.attributes.TextAttribute;
import ptolemy.vergil.kernel.attributes.VisibleAttribute;

public class PtolemyModelElementShape extends RectangleFigure implements IGraphicsAlgorithmRenderer {

  private static Map<Class<? extends VisibleAttribute>, DrawingStrategy<? extends VisibleAttribute>> drawingStrategies = new HashMap<>();
  static {
    drawingStrategies.put(RectangleAttribute.class, new RectangleDrawingStrategy());
    drawingStrategies.put(LineAttribute.class, new LineDrawingStrategy());
    drawingStrategies.put(TextAttribute.class, new TextDrawingStrategy());
  }

  private String iconURI;

  /**
   * @param iconURI
   */
  public PtolemyModelElementShape(String iconURI, int translateX, int translateY) {
    this.iconURI = iconURI;
  }

  @Override
  protected void fillShape(Graphics graphics) {

    graphics.drawRectangle(getBounds());
    graphics.translate(getLocation());
    try {
      EditorIcon iconDef = (EditorIcon) WorkflowUtils.readFrom(URI.create(iconURI));
      for(VisibleAttribute a : iconDef.attributeList(VisibleAttribute.class)) {
        DrawingStrategy drawingStrategy = drawingStrategies.get(a.getClass());
        if(drawingStrategy != null) {
          drawingStrategy.draw(a, graphics);
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
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
}
