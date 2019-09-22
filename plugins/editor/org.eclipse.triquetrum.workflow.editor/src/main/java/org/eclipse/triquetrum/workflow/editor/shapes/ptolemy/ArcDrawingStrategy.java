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
import org.eclipse.draw2d.geometry.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.data.DoubleToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.vergil.kernel.attributes.ArcAttribute;

public class ArcDrawingStrategy extends FilledShapeDrawingStrategy<ArcAttribute> {
  private final static Logger LOGGER = LoggerFactory.getLogger(ArcDrawingStrategy.class);

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  public void doFillShape(ArcAttribute arcAttr, Graphics graphics, Rectangle bounds) throws IllegalActionException {
    int offset = (int) ((DoubleToken) arcAttr.start.getToken()).doubleValue();
    int length = (int) ((DoubleToken) arcAttr.extent.getToken()).doubleValue();
    // Don't know how to have a "closed" arc (i.e. a chord) in draw2d Graphics,
    // so we handle it as a simple "open" arc.
    String arcType = arcAttr.type.stringValue();
    switch (arcType) {
    case "open":
    case "chord":
      break;
    default:
      graphics.fillArc(bounds, offset, length);
    }
  }

  @Override
  public void doDrawBorder(ArcAttribute arcAttr, Graphics graphics, Rectangle bounds) throws IllegalActionException {
    int offset = (int) ((DoubleToken) arcAttr.start.getToken()).doubleValue();
    int length = (int) ((DoubleToken) arcAttr.extent.getToken()).doubleValue();
    graphics.drawArc(bounds, offset, length);
  }
  // take into account the "centered" attribute : when true, use the location as center of the arc i.o. as the tlp
  @Override
  protected Point getCenteringTranslation(ArcAttribute arcAttr) throws IllegalActionException {
    Dimension dim = getDimension(arcAttr, null);
    // TODO validate that this strange translation is the right one for how arcs are assumed to work in PtII.
    // This weird expression was obtained by trial-and-error to support the DictionaryIcon.xml and its arcs.
    double xSign = Math.signum(((DoubleToken) arcAttr.extent.getToken()).doubleValue());
    return new Point((int) Math.rint(-dim.width() / 2.0 - xSign * dim.width() / 4.0), (int) Math.rint(-dim.height() / 4.0));
  }
}
