package org.eclipse.triquetrum.workflow.editor.shapes.ptolemy;

import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import ptolemy.data.BooleanToken;
import ptolemy.data.IntToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.Location;
import ptolemy.vergil.kernel.attributes.TextAttribute;

public class TextDrawingStrategy implements DrawingStrategy<TextAttribute> {

  @Override
  public void draw(TextAttribute textAttr, Graphics graphics) {
    Color fgColor = graphics.getForegroundColor();
    graphics.setAntialias(SWT.ON);
    java.awt.Color color = textAttr.textColor.asColor();
    if (color != null) {
        // TODO figure out if and how such colors must be managed and disposed etc
        Color rgb = new Color(null, color.getRed(), color.getGreen(), color.getBlue());
        graphics.setForegroundColor(rgb);
    }

    try {
      String text = textAttr.text.getExpression();
      Location location = (Location) textAttr.getAttribute("_location");
      int x1 = (int)location.getLocation()[0];
      int y1 = (int)location.getLocation()[1];

      int fontSize = ((IntToken)textAttr.textSize.getToken()).intValue();
      String fontFamily = textAttr.fontFamily.stringValue();
      boolean italic = ((BooleanToken)textAttr.italic.getToken()).booleanValue();
      boolean bold = ((BooleanToken)textAttr.bold.getToken()).booleanValue();
      // TODO handle italic and bold
      int style = SWT.NORMAL | (italic?SWT.ITALIC:SWT.NORMAL) | (bold?SWT.BOLD:SWT.NORMAL);
      Font f = new Font(null, fontFamily, fontSize, style);
      graphics.setFont(f);
      graphics.drawText(text, x1+30, y1+20);
    } catch (IllegalActionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    graphics.setForegroundColor(fgColor);
  }

}
