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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.triquetrum.workflow.model.Direction;
import org.eclipse.triquetrum.workflow.model.Port;

/**
 * Some utilities and constants related to drawing ports.
 * 
 * TODO refactor shape handling and port creation to encapsulate different potential layout strategies in specific shape implementations.
 *
 */
public class PortShapes {

  public static final int PORT_SIZE = 12;
  static final int HALF_PORT_SIZE = PORT_SIZE/2;
  
  public static final IColorConstant PORT_FOREGROUND = IColorConstant.BLACK;
  public static final IColorConstant PORT_BACKGROUND_MULTIPORT = IColorConstant.WHITE;
  public static final IColorConstant PORT_BACKGROUND_SINGLEPORT = IColorConstant.BLACK;

  // Graphiti shapes have no rotation support, so we define the different port orientations explicitly here.
  // The point coordinates below place the top-left corner of the square boundaries of the port shape at (0,0) relative to their anchor location.
  // The coordinates start with the leftmost/topmost point and then continue clockwise.
  // The createPortShapePointsForDirection() method shifts the port in the right way to end up with a port shape that slightly crosses 
  // the actor's boundary on the side matching the port direction.
  
  // These are shapes for ports that are input OR output.
  public static final int[] LEFT_RIGHT_PORTSHAPE = new int[] { 0, 0, PORT_SIZE, HALF_PORT_SIZE, 0, PORT_SIZE };
  public static final int[] RIGHT_LEFT_PORTSHAPE = new int[] { 0, HALF_PORT_SIZE, PORT_SIZE, 0, PORT_SIZE, PORT_SIZE };
  public static final int[] TOP_DOWN_PORTSHAPE = new int[] { 0, 0, PORT_SIZE, 0, HALF_PORT_SIZE, PORT_SIZE };
  public static final int[] BOTTOM_UP_PORTSHAPE = new int[] { 0, PORT_SIZE, HALF_PORT_SIZE, 0, PORT_SIZE, PORT_SIZE };

  // These are shapes for ports that are both input AND output.
  // As they are symmetrical along their direction, we only need 2 shapes to differentiate horizontal vs vertical.
  public static final int[] HORIZONTAL_PORTSHAPE_IO = new int[] { 0, 0, HALF_PORT_SIZE, HALF_PORT_SIZE, PORT_SIZE, 0, 
                                                                  PORT_SIZE, PORT_SIZE, HALF_PORT_SIZE, HALF_PORT_SIZE, 0, PORT_SIZE };
  public static final int[] VERTICAL_PORTSHAPE_IO = new int[] { 0, 0, PORT_SIZE, 0, HALF_PORT_SIZE, HALF_PORT_SIZE, PORT_SIZE, PORT_SIZE,
                                                                  0, PORT_SIZE, HALF_PORT_SIZE, HALF_PORT_SIZE, HALF_PORT_SIZE, PORT_SIZE };

  /**
   * Create an anchor at the right location on its container shape, on the side matching the direction,
   * relative location along that side matching its index in the total count of ports there,
   * and with correct input/output behaviour.
   * 
   * @param containerShape
   * @param direction
   * @param p
   * @param i
   * @param portCount
   * @return
   */
  public static Anchor createAnchor(ContainerShape containerShape, Direction direction, Port p, int i, int portCount) {
    ICreateService createService = Graphiti.getCreateService();

    FixPointAnchor anchor = createService.createFixPointAnchor(containerShape);
    anchor.setReferencedGraphicsAlgorithm(containerShape.getGraphicsAlgorithm());
//    anchor.setUseAnchorLocationAsConnectionEndpoint(p.isInput());
    anchor.setLocation(ActorShapes.determineAnchorLocation(containerShape, direction, i, portCount));

    return anchor;
  }

  /**
   * Creates a port shape matching the direction along its actor shape, the single/multi and the input/output types.
   * 
   * @param diagram
   * @param anchor the anchor on which the port shape must be created
   * @param direction
   * @param p
   * @return
   */
  public static Polygon createPortShape(Diagram diagram, Anchor anchor, Direction direction, Port p) {
    IGaService gaService = Graphiti.getGaService();

    Collection<Point> portShapePoints = getPortShapeDefinition(direction, p);
    
    final Polygon portShape = gaService.createPlainPolygon(anchor, portShapePoints);
    portShape.setForeground(gaService.manageColor(diagram, PORT_FOREGROUND));
    IColorConstant portColour = p.isMultiPort() ? PORT_BACKGROUND_MULTIPORT : PORT_BACKGROUND_SINGLEPORT;
    portShape.setBackground(gaService.manageColor(diagram, portColour));
    portShape.setLineWidth(1);
    gaService.setLocationAndSize(portShape, 0, 0, PORT_SIZE, PORT_SIZE);
    
    return portShape;
  }

  /**
   * Rotates the anchor's port shape to match the direction and port input/output type.
   * 
   * @param diagram
   * @param anchor
   * @param direction
   * @param p
   * @return
   */
  public static Polygon rotatePortShape(Diagram diagram, Anchor anchor, Direction direction, Port p) {
    List<Point> portShapePoints = getPortShapeDefinition(direction, p);
    final Polygon portShape = (Polygon) anchor.getGraphicsAlgorithm();

    for(int i=0; i<portShape.getPoints().size(); ++i) {
      Point currPt = portShape.getPoints().get(i);
      Point newPt = portShapePoints.get(i);
      currPt.setX(newPt.getX());
      currPt.setY(newPt.getY());
    }
    
    return portShape;
  }

  /**
   * Gets the points defined for the given direction and port, that can then be used to create a correct port shape/polygon.
   * 
   * @param direction
   * @param p
   * @return
   */
  private static List<Point> getPortShapeDefinition(Direction direction, Port p) {
    int[] portShapeCoordinates = LEFT_RIGHT_PORTSHAPE;
    List<Point> portShapePoints = Collections.emptyList();
    switch (direction) {
    case WEST:
      portShapeCoordinates = p.isOutput() ? (p.isInput() ? VERTICAL_PORTSHAPE_IO : RIGHT_LEFT_PORTSHAPE) : LEFT_RIGHT_PORTSHAPE;
      break;
    case EAST:
      portShapeCoordinates = p.isInput() ? (p.isOutput() ? VERTICAL_PORTSHAPE_IO : RIGHT_LEFT_PORTSHAPE) : LEFT_RIGHT_PORTSHAPE;
      break;
    case NORTH:
      portShapeCoordinates = p.isOutput() ? (p.isInput() ? HORIZONTAL_PORTSHAPE_IO : BOTTOM_UP_PORTSHAPE) : TOP_DOWN_PORTSHAPE;
      break;
    case SOUTH:
      portShapeCoordinates = p.isInput() ? (p.isOutput() ? HORIZONTAL_PORTSHAPE_IO : BOTTOM_UP_PORTSHAPE) : TOP_DOWN_PORTSHAPE;
      break;
    default:
      // if direction is not one of the known ones, pick a default  
      direction = Direction.SOUTH;
      break;
    }
    portShapePoints = createPortShapePointsForDirection(portShapeCoordinates, direction);
    return portShapePoints;
  }
  
  /**
   * Creates the list of points that can be used to create a polygon port shape,
   * from the default coordinates and by shifting them along X and Y matching the given direction,
   * for a proper positioning relative to a port's anchor.
   * 
   * @param portShapeCoordinates
   * @param direction
   * @return
   */
  private static List<Point> createPortShapePointsForDirection(int[] portShapeCoordinates, Direction direction) {
    List<Point> points = Graphiti.getGaService().createPointList(portShapeCoordinates);
    
    int xShift;
    int yShift;
    
    switch (direction) {
    case WEST:
      xShift = - HALF_PORT_SIZE - 2;
      yShift = - HALF_PORT_SIZE;
      break;
    case EAST:
      xShift = - HALF_PORT_SIZE + 2;
      yShift = - HALF_PORT_SIZE;
      break;
    case NORTH:
      xShift = - HALF_PORT_SIZE;
      yShift = - HALF_PORT_SIZE - 2;
      break;
    case SOUTH:
    default:
      xShift = - HALF_PORT_SIZE;
      yShift = - HALF_PORT_SIZE + 2;
      break;
    }
    
    points.stream().forEach(p -> {p.setX(p.getX() + xShift);p.setY(p.getY() + yShift);});
    return points;
  }
}
