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
package org.eclipse.triquetrum.workflow.editor.shapes.svg;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRenderer;

public class SvgModelElementShape extends RectangleFigure implements IGraphicsAlgorithmRenderer {

  private String svgURI;
  private int translateX;
  private int translateY;

  /**
   * @param svgURI
   */
  public SvgModelElementShape(String svgURI, int translateX, int translateY) {
    this.svgURI = svgURI;
    this.translateX = translateX;
    this.translateY = translateY;
  }

  @Override
  protected void fillShape(Graphics graphics) {
    // TODO figure out a way to get the SVG translation/moving working with some Batik API or whatever
    SVGFigure figure = new SVGFigure();
    figure.setTranslateX(translateX);
    figure.setTranslateY(translateY);
    figure.setURI(svgURI);
    figure.setBounds(this.getBounds());
    figure.paint(graphics);
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
