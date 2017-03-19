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
package org.eclipse.triquetrum.workflow.editor.shapes;

import static org.eclipse.triquetrum.workflow.editor.shapes.PortShapes.PORT_SIZE;

import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.triquetrum.workflow.model.Direction;

/**
 * Default shape is a rounded rectangle inside an invisible boundary.
 * 
 * Ports can be placed on the 4 sides and are positioned mainly outside of the visible rectangle,
 * but with some pixels overlap (cfr the difference between the margins and the port sizes).
 * 
 * A header section at the top of the rounded rectangle shows an icon and the actor name as text.
 * 
 * TODO refactor shape handling and port creation to encapsulate different potential layout strategies in specific shape implementations.
 */
public class ActorShapes {

  // size info for the visible rounded rectangle
  public static final int ACTOR_VISIBLE_HEIGHT = 60;
  public static final int ACTOR_VISIBLE_WIDTH = 100;

  // margins between the visible rectangle and the surrounding invisible boundary
  public static final int ACTOR_X_MARGIN = PortShapes.PORT_SIZE - 2;
  public static final int ACTOR_Y_MARGIN = PortShapes.PORT_SIZE - 2;
  
  public static final int ACTOR_TOTAL_HEIGHT = ACTOR_VISIBLE_HEIGHT + 2*ACTOR_Y_MARGIN;
  public static final int ACTOR_TOTAL_WIDTH = ACTOR_VISIBLE_WIDTH + 2*ACTOR_X_MARGIN;
  
  public static final int ACTOR_ICON_X_MARGIN = ACTOR_X_MARGIN + 5;
  public static final int ACTOR_ICON_Y_MARGIN = ACTOR_Y_MARGIN + 3;
  public static final int ACTOR_ICON_SIZE = 16;
  
  public static final int ACTOR_TEXT_X_MARGIN = ACTOR_ICON_X_MARGIN + ACTOR_ICON_SIZE + 2;
  public static final int ACTOR_TEXT_WIDTH = 75;
  public static final int ACTOR_TEXT_HEIGHT = 20;
  public static final int[] ACTOR_TEXT_UNDERLINE_SHAPE = new int[] { ACTOR_X_MARGIN, ACTOR_Y_MARGIN + ACTOR_TEXT_HEIGHT, ACTOR_VISIBLE_WIDTH, ACTOR_Y_MARGIN + ACTOR_TEXT_HEIGHT };

  public static final IColorConstant ACTOR_NAME_FOREGROUND = IColorConstant.BLACK;
  public static final IColorConstant ACTOR_FOREGROUND = new ColorConstant(98, 131, 167);
  public static final IColorConstant ACTOR_BACKGROUND = new ColorConstant(187, 218, 247);
  
  /**
   * Calculates the minimal height of an actor shape, to be able to position ports on the west and east side.
   * 
   * @param nrPortsWest
   * @param nrPortsEast
   * @return
   */
  public static int calculateMinimalHeight(int nrPortsWest, int nrPortsEast) {
    return Math.max(ACTOR_TOTAL_HEIGHT,
        Math.max(ACTOR_Y_MARGIN + (nrPortsWest + 1) * PORT_SIZE, ACTOR_Y_MARGIN + (nrPortsEast + 1) * PORT_SIZE));
  }
  
  /**
   * Calculates the minimal width of an actor shape, to be able to position ports on the north and south side.
   * 
   * @param nrPortsNorth
   * @param nrPortsSouth
   * @return
   */
  public static int calculateMinimalWidth(int nrPortsNorth, int nrPortsSouth) {
    return Math.max(ACTOR_TOTAL_WIDTH,
        Math.max(ACTOR_X_MARGIN + (nrPortsNorth + 1) * PORT_SIZE, ACTOR_X_MARGIN + (nrPortsSouth + 1) * PORT_SIZE));
  }
  
  /**
   * If the total portCount for the given direction is 1, we place the port in the middle of the side corresponding to the given direction.
   * For higher portCounts, we try to distribute the ports a bit along that side of the actor.
   * <p>
   * If direction is null, SOUTH is picked as default.
   * </p>
   * 
   * @param containerShape
   * @param direction
   * @param portIndex
   * @param portCount
   * @return
   */
  public static Point determineAnchorLocation(ContainerShape containerShape, Direction direction, int portIndex, int portCount) {
    GraphicsAlgorithm containerGA = containerShape.getGraphicsAlgorithm();
    ICreateService createService = Graphiti.getCreateService();
  
    int height = containerGA.getHeight();
    int width = containerGA.getWidth();
  
    int actorSideLocation = 0;
    switch (direction) {
    case NORTH:
      actorSideLocation = ACTOR_Y_MARGIN;
      break;
    case EAST:
      actorSideLocation = width - ACTOR_X_MARGIN;
      break;
    case WEST:
      actorSideLocation = ACTOR_X_MARGIN;
      break;
    case SOUTH:
    default:
      actorSideLocation = height - ACTOR_Y_MARGIN;
      break;
    }
  
    int anchorX = 0;
    int anchorY = 0;
  
    // Depending on the direction, we need to place the anchors along a vertical or horizontal sequence.
    // The index in the list for the given direction, results in an offset to be applied in the vertical/horizontal positioning.
    switch (direction) {
    case EAST:
    case WEST:
      {
        int centerY = height/2;
        int step = height/(portCount + 1);
        step = step > PortShapes.PORT_SIZE ? step : PortShapes.PORT_SIZE;
        int startY = centerY - step * (portCount - 1) / 2;
        anchorY = portIndex * step + startY;
        anchorX = actorSideLocation;
      }
      break;
    case NORTH:
    case SOUTH:
    default:
      {
        int centerX = width/2;
        int step = width/(portCount + 1);
        step = step > PortShapes.PORT_SIZE ? step : PortShapes.PORT_SIZE;
        int startX = centerX - step * (portCount - 1) / 2;
        anchorX = portIndex * step + startX;
        anchorY = actorSideLocation;
      }
      break;
    }
    Point anchorPoint = createService.createPoint(anchorX, anchorY);
    return anchorPoint;
  }
}
