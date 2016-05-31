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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ResourceManager;

import ptolemy.kernel.util.Location;
import ptolemy.vergil.kernel.attributes.VisibleAttribute;

public abstract class AbstractDrawingStrategy<A extends VisibleAttribute> implements DrawingStrategy<A> {

  @Override
  public Rectangle getBounds(A visibleAttribute, Graphics graphics, ResourceManager resourceManager) {
    return new Rectangle(getTopLeftLocation(visibleAttribute, graphics), getDimension(visibleAttribute, graphics, resourceManager));
  }

  protected Point getTopLeftLocation(A visibleAttribute, Graphics graphics) {
    Location location = (Location) visibleAttribute.getAttribute("_location");
    int x1 = (int)location.getLocation()[0];
    int y1 = (int)location.getLocation()[1];
    return new Point(x1, y1);
  }

  protected abstract Dimension getDimension(A visibleAttribute, Graphics graphics, ResourceManager resourceManager);
}
