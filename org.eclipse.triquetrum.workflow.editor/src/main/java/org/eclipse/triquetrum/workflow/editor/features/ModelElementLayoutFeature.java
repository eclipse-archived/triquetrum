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
package org.eclipse.triquetrum.workflow.editor.features;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static org.eclipse.triquetrum.workflow.editor.shapes.ActorShapes.ACTOR_TEXT_WIDTH;
import static org.eclipse.triquetrum.workflow.editor.shapes.ActorShapes.ACTOR_TEXT_X_MARGIN;
import static org.eclipse.triquetrum.workflow.editor.shapes.ActorShapes.ACTOR_X_MARGIN;
import static org.eclipse.triquetrum.workflow.editor.shapes.ActorShapes.ACTOR_Y_MARGIN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.shapes.ActorShapes;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.editor.util.PortAnchorPair;
import org.eclipse.triquetrum.workflow.model.Direction;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.Port;

public class ModelElementLayoutFeature extends AbstractLayoutFeature {

  public ModelElementLayoutFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canLayout(ILayoutContext context) {
    BoCategory boCategory = BoCategory.retrieveFrom(context.getPictogramElement());
    return BoCategory.CompositeActor.equals(boCategory) || BoCategory.Actor.equals(boCategory);
  }

  @Override
  public boolean layout(ILayoutContext context) {
    boolean anythingChanged = false;
    ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
    // the invisible rectangle contains the visible rectangle as its (first and only) child
    GraphicsAlgorithm invisibleRectangle = containerShape.getGraphicsAlgorithm();
    GraphicsAlgorithm rectangle = invisibleRectangle.getGraphicsAlgorithmChildren().get(0);

    Entity relatedBO = (Entity) getBusinessObjectForPictogramElement(containerShape);

    List<Port> ports = relatedBO.getPorts();
    List<Anchor> anchors = containerShape.getAnchors();
    List<PortAnchorPair> paList = new ArrayList<>();

    anchors.forEach(anchor -> {
      Object bo = getBusinessObjectForPictogramElement(anchor);
      if (bo != null && bo instanceof Port) {
        paList.add(new PortAnchorPair((Port) bo, anchor));
      }
    });
    paList.sort((pa1, pa2) -> Integer.compare(ports.indexOf(getBusinessObjectForPictogramElement(pa1.anchor)),
        ports.indexOf(getBusinessObjectForPictogramElement(pa2.anchor))));
    Map<Direction, List<PortAnchorPair>> categorizedPorts = paList.stream()
        .collect(groupingBy(PortAnchorPair::getPortDirection, mapping(Function.identity(), toList())));

    int nrPortsWest = categorizedPorts.getOrDefault(Direction.WEST, Collections.emptyList()).size();
    int nrPortsEast = categorizedPorts.getOrDefault(Direction.EAST, Collections.emptyList()).size();
    int nrPortsNorth = categorizedPorts.getOrDefault(Direction.NORTH, Collections.emptyList()).size();
    int nrPortsSouth = categorizedPorts.getOrDefault(Direction.SOUTH, Collections.emptyList()).size();

    boolean containsExtFigure = EditorUtils.containsExternallyDefinedFigure(containerShape);

    // Manual resizing is currently blocked, so only resizing that can trigger
    // this layout feature is the one based on determining size of externally defined figures.
    // and for those we don't set minimum sizes...

    int minHeight = ActorShapes.calculateMinimalHeight(nrPortsWest, nrPortsEast);
    // height of invisible rectangle
    if (!containsExtFigure && invisibleRectangle.getHeight() != minHeight) {
      invisibleRectangle.setHeight(minHeight);
      anythingChanged = true;
    }

    int minWidth = ActorShapes.calculateMinimalWidth(nrPortsNorth, nrPortsSouth);
    // width of invisible rectangle
    if (!containsExtFigure && invisibleRectangle.getWidth() != minWidth) {
      invisibleRectangle.setWidth(minWidth);
      anythingChanged = true;
    }

    // height of visible rectangle (smaller than invisible rectangle)
    int rectangleHeight = invisibleRectangle.getHeight() - 2 * ACTOR_Y_MARGIN;
    if (rectangle.getHeight() != rectangleHeight) {
      rectangle.setHeight(rectangleHeight);
      anythingChanged = true;
    }

    // width of visible rectangle (smaller than invisible rectangle)
    int rectangleWidth = invisibleRectangle.getWidth() - 2 * ACTOR_X_MARGIN;
    if (rectangle.getWidth() != rectangleWidth) {
      rectangle.setWidth(rectangleWidth);
      anythingChanged = true;
    }

    IGaService gaService = Graphiti.getGaService();

    if (!containsExtFigure) {
      // width of text and line (same as visible rectangle)
      for (Shape shape : containerShape.getChildren()) {
        GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
        Object gaBO = getBusinessObjectForPictogramElement(shape);
        if (gaBO != relatedBO) {
          // this is to ensure that only the shapes related to the main BO get resized etc.
          // e.g. when expanding/collapsing a composite actor, we only want to layout the icon, name and horizontal line underneath the name,
          // and not all child shapes related to contained entities, relations etc.
          continue;
        } else {
          IDimension size = gaService.calculateSize(ga);
          if (rectangleWidth != size.getWidth()) {
            if (ga instanceof Polyline) {
              Polyline polyline = (Polyline) ga;
              Point secondPoint = polyline.getPoints().get(1);
              Point newSecondPoint = gaService.createPoint(ACTOR_X_MARGIN + rectangleWidth, secondPoint.getY());
              polyline.getPoints().set(1, newSecondPoint);
              anythingChanged = true;
            } else if (ga instanceof Text) {
              gaService.setLocationAndSize(ga, ACTOR_TEXT_X_MARGIN, ga.getY(), ACTOR_TEXT_WIDTH, ga.getHeight());
            } else if (ga instanceof Image) {
              // remain unchanged
            } else {
              gaService.setWidth(ga, rectangleWidth);
              anythingChanged = true;
            }
          }
        }
      }
    }

    categorizedPorts.forEach((direction, pairs) -> updateForDirection(containerShape, direction, pairs));

    return anythingChanged;
  }

  private void updateForDirection(ContainerShape containerShape, Direction direction, List<PortAnchorPair> pairList) {
    int portCount = pairList.size();
    for (int i = 0; i < portCount; ++i) {
      FixPointAnchor fpa = (FixPointAnchor) pairList.get(i).anchor;
      fpa.setLocation(ActorShapes.determineAnchorLocation(containerShape, direction, i, portCount));
    }
  }

}
