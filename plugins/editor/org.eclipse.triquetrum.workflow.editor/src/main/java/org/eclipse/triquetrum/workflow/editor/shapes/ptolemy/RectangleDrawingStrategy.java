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
import org.eclipse.draw2d.geometry.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.data.DoubleToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.vergil.kernel.attributes.RectangleAttribute;

public class RectangleDrawingStrategy extends FilledShapeDrawingStrategy<RectangleAttribute> {
  private final static Logger LOGGER = LoggerFactory.getLogger(RectangleDrawingStrategy.class);

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  public void doFillShape(RectangleAttribute rectangleAttr, Graphics graphics, Rectangle bounds) {
    graphics.fillRectangle(bounds);
  }

  @Override
  public void doDrawBorder(RectangleAttribute rectangleAttr, Graphics graphics, Rectangle bounds) throws IllegalActionException {
    int rounding = (int) ((DoubleToken) rectangleAttr.rounding.getToken()).doubleValue();
    if (rounding > 0) {
      graphics.drawRoundRectangle(bounds, rounding, rounding);
    } else {
      graphics.drawRectangle(bounds);
    }
  }
}
