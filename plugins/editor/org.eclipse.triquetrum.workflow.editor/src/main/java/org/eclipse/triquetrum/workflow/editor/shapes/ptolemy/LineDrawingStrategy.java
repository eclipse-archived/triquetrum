/*******************************************************************************
 * Copyright (c) 2016,2019 iSencia Belgium NV.
 *  
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.shapes.ptolemy;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.data.ArrayToken;
import ptolemy.data.DoubleToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.vergil.kernel.attributes.LineAttribute;

public class LineDrawingStrategy extends AbstractDrawingStrategy<LineAttribute> {
  private final static Logger LOGGER = LoggerFactory.getLogger(LineDrawingStrategy.class);

  @Override
  public void draw(LineAttribute lineAttr, Graphics graphics, ResourceManager resourceManager) {
    Color stdFgColor = graphics.getForegroundColor();
    int stdLineStyle = graphics.getLineStyle();

    Color lineColor = getSwtColor(lineAttr.lineColor, resourceManager);
    if (lineColor != null) {
      graphics.setForegroundColor(lineColor);
    }

    try {
      float lineWidth = (float) ((DoubleToken) lineAttr.lineWidth.getToken()).doubleValue();
      graphics.setLineWidthFloat(lineWidth);
      int x2_step = (int) ((DoubleToken) lineAttr.x.getToken()).doubleValue();
      int y2_step = (int) ((DoubleToken) lineAttr.y.getToken()).doubleValue();

      // TODO may need to move this upwards as other shape types may also have dashed lines in Ptolemy II
      ArrayToken dashArrayToken = (ArrayToken) lineAttr.dashArray.getToken();
      if (dashArrayToken != null && dashArrayToken.length() > 0) {
        graphics.setLineStyle(SWT.LINE_DASH);
        float[] dashPattern = new float[dashArrayToken.length()];
        for (int i = 0; i < dashArrayToken.length(); i++) {
          dashPattern[i] = (float) ((DoubleToken) dashArrayToken.getElement(i)).doubleValue();
        }
        graphics.setLineDash(dashPattern);
      }
      Point tlp = getTopLeftLocation(lineAttr);
      graphics.drawLine(tlp, tlp.getTranslated(x2_step, y2_step));
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for " + lineAttr.getFullName(), e);
    } finally {
      graphics.setForegroundColor(stdFgColor);
      graphics.setLineStyle(stdLineStyle);
    }
  }

  @Override
  protected Dimension getDimension(LineAttribute lineAttr, ResourceManager resourceManager) {
    try {
      int x2_step = (int) ((DoubleToken) lineAttr.x.getToken()).doubleValue();
      int y2_step = (int) ((DoubleToken) lineAttr.y.getToken()).doubleValue();
      return new Dimension(x2_step, y2_step);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for " + lineAttr.getFullName(), e);
      return new Dimension(0, 0);
    }
  }
}
