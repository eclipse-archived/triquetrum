package org.eclipse.triquetrum.workflow.editor.shapes.ptolemy;

import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.graphics.Color;

import ptolemy.data.DoubleToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.Location;
import ptolemy.vergil.kernel.attributes.RectangleAttribute;

public class RectangleDrawingStrategy implements DrawingStrategy<RectangleAttribute> {

  @Override
  public void draw(RectangleAttribute rectangleAttr, Graphics graphics) {
    Color fgColor = graphics.getBackgroundColor();
    // graphics.setForegroundColor(new Color(null, 0,0,0));
    java.awt.Color color = rectangleAttr.fillColor.asColor();
    if (color != null) {
      // TODO figure out if and how such colors must be managed and disposed etc
      Color rgb = new Color(null, color.getRed(), color.getGreen(), color.getBlue());
      graphics.setBackgroundColor(rgb);
    }

    try {
      int width = (int) ((DoubleToken) rectangleAttr.width.getToken()).doubleValue();
      int height = (int) ((DoubleToken) rectangleAttr.height.getToken()).doubleValue();
      Location location = (Location) rectangleAttr.getAttribute("_location");
      graphics.fillRectangle((int) location.getLocation()[0], (int) location.getLocation()[1], width, height);
    } catch (IllegalActionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    graphics.setBackgroundColor(fgColor);
  }

}
