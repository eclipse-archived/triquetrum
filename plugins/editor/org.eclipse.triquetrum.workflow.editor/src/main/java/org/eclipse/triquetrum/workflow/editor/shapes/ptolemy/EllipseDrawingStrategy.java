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

import ptolemy.vergil.kernel.attributes.EllipseAttribute;

public class EllipseDrawingStrategy extends FilledShapeDrawingStrategy<EllipseAttribute> {
  private final static Logger LOGGER = LoggerFactory.getLogger(EllipseDrawingStrategy.class);

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  public void doFillShape(EllipseAttribute ellipseAttr, Graphics graphics, Rectangle bounds) {
    // minor adjustment to fix pixel errors between fill and border
    graphics.fillOval(bounds.x(), bounds.y(), bounds.width() + 1, bounds.height() +1);
  }

  @Override
  public void doDrawBorder(EllipseAttribute ellipseAttr, Graphics graphics, Rectangle bounds) {
    graphics.drawOval(bounds);
  }
}
