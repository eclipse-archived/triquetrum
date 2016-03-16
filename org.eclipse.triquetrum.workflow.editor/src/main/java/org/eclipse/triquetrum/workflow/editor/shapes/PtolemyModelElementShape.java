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
package org.eclipse.triquetrum.workflow.editor.shapes;

import java.net.URI;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRenderer;
import org.eclipse.triquetrum.workflow.util.WorkflowUtils;

import ptolemy.kernel.util.NamedObj;
import ptolemy.vergil.icon.EditorIcon;

public class PtolemyModelElementShape extends RectangleFigure implements IGraphicsAlgorithmRenderer {

  private String iconURI;
  private int translateX;
  private int translateY;

  /**
   * @param iconURI
   */
  PtolemyModelElementShape(String iconURI, int translateX, int translateY) {
    this.iconURI = iconURI;
    this.translateX = translateX;
    this.translateY = translateY;
  }

  @Override
  protected void fillShape(Graphics graphics) {
    try {
      EditorIcon iconDef = (EditorIcon) WorkflowUtils.readFrom(URI.create(iconURI));
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
