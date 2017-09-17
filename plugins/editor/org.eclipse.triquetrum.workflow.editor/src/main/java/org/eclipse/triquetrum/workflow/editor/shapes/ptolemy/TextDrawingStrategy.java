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

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.data.BooleanToken;
import ptolemy.data.IntToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.vergil.kernel.attributes.TextAttribute;

public class TextDrawingStrategy extends AbstractDrawingStrategy<TextAttribute> {
  private final static Logger LOGGER = LoggerFactory.getLogger(TextDrawingStrategy.class);

  @Override
  public void draw(TextAttribute textAttr, Graphics graphics, ResourceManager resourceManager) {
    Color fgColor = graphics.getForegroundColor();
    java.awt.Color color = textAttr.textColor.asColor();
    if (color != null) {
      Color rgb = resourceManager.createColor(new RGB(color.getRed(), color.getGreen(), color.getBlue()));
      graphics.setForegroundColor(rgb);
    }

    try {
      String text = textAttr.text.getExpression();
      int fontSize = ((IntToken) textAttr.textSize.getToken()).intValue();
      String fontFamily = textAttr.fontFamily.stringValue();
      boolean italic = ((BooleanToken) textAttr.italic.getToken()).booleanValue();
      boolean bold = ((BooleanToken) textAttr.bold.getToken()).booleanValue();
      int style = SWT.NORMAL | (italic ? SWT.ITALIC : SWT.NORMAL) | (bold ? SWT.BOLD : SWT.NORMAL);
      Font f = resourceManager.createFont(FontDescriptor.createFrom(fontFamily, fontSize, style));
      graphics.setFont(f);

      Point tlp = getTopLeftLocation(textAttr);
      graphics.drawText(text, tlp);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading properties for " + textAttr.getFullName(), e);
    }
    graphics.setForegroundColor(fgColor);
  }

  @Override
  protected Dimension getDimension(TextAttribute textAttr, ResourceManager resourceManager) {
    try {
      String text = textAttr.text.getExpression();
      int fontSize = ((IntToken) textAttr.textSize.getToken()).intValue();
      String fontFamily = textAttr.fontFamily.stringValue();
      boolean italic = ((BooleanToken) textAttr.italic.getToken()).booleanValue();
      boolean bold = ((BooleanToken) textAttr.bold.getToken()).booleanValue();
      int style = SWT.NORMAL | (italic ? SWT.ITALIC : SWT.NORMAL) | (bold ? SWT.BOLD : SWT.NORMAL);
      Font f = resourceManager.createFont(FontDescriptor.createFrom(fontFamily, fontSize, style));
      return FigureUtilities.getTextExtents(text, f);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for " + textAttr.getFullName(), e);
      return new Dimension(0, 0);
    }
  }
}
