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

import org.eclipse.graphiti.mm.algorithms.Polygon;
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
  public static final int HALF_PORT_SIZE = PORT_SIZE/2;
  
  public static final IColorConstant PORT_FOREGROUND = IColorConstant.BLACK;
  public static final IColorConstant PORT_BACKGROUND_MULTIPORT = IColorConstant.WHITE;
  public static final IColorConstant PORT_BACKGROUND_SINGLEPORT = IColorConstant.BLACK;

  public static final int[] DEFAULT_PORTSHAPE = new int[] { 0, 0, PORT_SIZE, HALF_PORT_SIZE, 0, PORT_SIZE };
  
  public static Anchor createAnchor(Diagram diagram, ContainerShape containerShape, Direction direction, Port p, int i, int portCount) {
    ICreateService createService = Graphiti.getCreateService();
    IGaService gaService = Graphiti.getGaService();

    FixPointAnchor anchor = createService.createFixPointAnchor(containerShape);
    anchor.setReferencedGraphicsAlgorithm(containerShape.getGraphicsAlgorithm());
    anchor.setLocation(ActorShapes.determineAnchorLocation(containerShape, direction, i, portCount));

    int portX = -HALF_PORT_SIZE;
    int portY = -HALF_PORT_SIZE;

    final Polygon portShape = gaService.createPlainPolygon(anchor, DEFAULT_PORTSHAPE);
    portShape.setForeground(gaService.manageColor(diagram, PORT_FOREGROUND));
    IColorConstant portColour = p.isMultiPort() ? PORT_BACKGROUND_MULTIPORT : PORT_BACKGROUND_SINGLEPORT;
    portShape.setBackground(gaService.manageColor(diagram, portColour));
    portShape.setLineWidth(1);
    gaService.setLocationAndSize(portShape, portX, portY, PORT_SIZE, PORT_SIZE);
    
    return anchor;
  }
}
