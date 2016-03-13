package org.eclipse.triquetrum.workflow.editor.shapes;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.lite.svg.SVGFigure;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRenderer;

public class SvgModelElementShape extends RectangleFigure implements IGraphicsAlgorithmRenderer {

  @Override
  protected void fillShape(Graphics graphics) {
    SVGFigure figure = new SVGFigure();
    figure.setURI("platform:/plugin/org.eclipse.triquetrum.workflow.editor/icons/src_actor.svg");
    figure.setParent(this);
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
