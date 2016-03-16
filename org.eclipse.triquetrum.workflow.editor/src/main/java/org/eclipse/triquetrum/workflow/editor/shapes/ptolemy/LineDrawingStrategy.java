package org.eclipse.triquetrum.workflow.editor.shapes.ptolemy;

import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

import ptolemy.data.DoubleToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.Location;
import ptolemy.vergil.kernel.attributes.LineAttribute;

public class LineDrawingStrategy implements DrawingStrategy<LineAttribute> {

  @Override
  public void draw(LineAttribute lineAttr, Graphics graphics) {
    Color fgColor = graphics.getForegroundColor();
    graphics.setAntialias(SWT.ON);
    java.awt.Color color = lineAttr.lineColor.asColor();
    if (color != null) {
        // TODO figure out if and how such colors must be managed and disposed etc
        Color rgb = new Color(null, color.getRed(), color.getGreen(), color.getBlue());
        graphics.setForegroundColor(rgb);
    }

    try {
      float lineWidth = (float) ((DoubleToken)lineAttr.lineWidth.getToken()).doubleValue();
      graphics.setLineWidthFloat(lineWidth);
      int x2_step = (int) ((DoubleToken)lineAttr.x.getToken()).doubleValue();
      int y2_step = (int) ((DoubleToken)lineAttr.y.getToken()).doubleValue();
      Location location = (Location) lineAttr.getAttribute("_location");
      int x1 = (int)location.getLocation()[0];
      int y1 = (int)location.getLocation()[1];
      graphics.drawLine(x1+30, y1+20, x1+x2_step+30, y1+y2_step+20);
    } catch (IllegalActionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    graphics.setForegroundColor(fgColor);
  }

}
