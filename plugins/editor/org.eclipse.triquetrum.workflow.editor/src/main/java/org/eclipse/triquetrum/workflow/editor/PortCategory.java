/*******************************************************************************
 * Copyright (c) 2017 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor;

import org.apache.commons.lang.StringUtils;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.triquetrum.workflow.model.Direction;
import org.eclipse.triquetrum.workflow.model.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Enum to categorize the Port types, so we can represent port types in the Triq EMF model.
 *
 * TODO analyse if this can move (partially) to the Triquetrum EMF model or should stay in the editor code.
 *
 */
public enum PortCategory implements Category {
  DEFAULT(Direction.DEFAULT), 
  NORTH(Direction.NORTH),
  WEST(Direction.WEST),
  SOUTH(Direction.SOUTH),
  EAST(Direction.EAST);

  private final static Logger LOGGER = LoggerFactory.getLogger(PortCategory.class);
  private final static String PORT_CATEGORY_PROPNAME = "__PORT_CATEGORY";
  
  private Direction direction;

  private PortCategory(Direction d) {
    this.direction = d;
  }
  
  /**
   * 
   * @return the direction enum value matching this PortCategory instance.
   */
  public Direction getDirection() {
    return direction;
  }

  /**
   * 
   * @param direction
   * @return the category matching the given direction, or DEFAULT if direction is null
   */
  public static PortCategory valueOf(Direction direction) {
    return direction!=null ? valueOf(direction.name()) : PortCategory.DEFAULT;
  }

  /**
   * 
   * @param p
   * @return
   */
  public static PortCategory retrieveFrom(Port p) {
    Direction direction = p.getDirection();
    PortCategory result = PortCategory.DEFAULT;
    if(direction!=null && Direction.DEFAULT!=direction) {
      result = valueOf(direction.name());
    } else if(p.isInput()) {
      result = PortCategory.WEST;
    } else if(p.isOutput()) {
      result = PortCategory.EAST;
    }
    
    return result;
  }

  /**
   *
   * @param pe
   * @return the category stored as a property in the given {@link PictogramElement}, or null if no valid category was found.
   */
  public static PortCategory retrieveFrom(PictogramElement pe) {
    String portCategoryStr = Graphiti.getPeService().getPropertyValue(pe, PortCategory.PORT_CATEGORY_PROPNAME);
    if (!StringUtils.isBlank(portCategoryStr)) {
      try {
        return PortCategory.valueOf(portCategoryStr);
      } catch (Exception e) {
        LOGGER.error("Invalid Port Category for " + pe, e);
      }
    }
    return null;
  }

  /**
   * Store this category as a property in the given {@link PictogramElement}.
   * 
   * @param pe
   */
  @Override
  public void storeIn(PictogramElement pe) {
    Graphiti.getPeService().setPropertyValue(pe, PortCategory.PORT_CATEGORY_PROPNAME, name());
  }
}
