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

import java.util.List;

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
import org.eclipse.triquetrum.workflow.editor.PortCategory;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.Entity;

public class ModelElementLayoutFeature extends AbstractLayoutFeature {

  private static final int MIN_HEIGHT = 20;

  private static final int MIN_WIDTH = 60;

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

    BoCategory boCategory = BoCategory.retrieveFrom(context.getPictogramElement());
    boolean isActor = BoCategory.Actor.equals(boCategory);
    Entity relatedBO = (Entity) getBusinessObjectForPictogramElement(containerShape);
    int nrInputs = relatedBO.getInputPorts().size();
    int nrOutputs = relatedBO.getOutputPorts().size();

    boolean containsExtFigure = EditorUtils.containsExternallyDefinedFigure(containerShape);

    // Manual resizing is currently blocked, so only resizing that can trigger
    // this layout feature is the one based on determining size of externally defined figures.
    // and for those we don't set minimum sizes...

    int minHeight = Math.max(MIN_HEIGHT, Math.max(MIN_HEIGHT + (nrInputs+1)*ActorAddFeature.PORT_SIZE, MIN_HEIGHT + (nrOutputs+1)*ActorAddFeature.PORT_SIZE));
    // height of invisible rectangle
    if (!containsExtFigure && invisibleRectangle.getHeight() < minHeight) {
      invisibleRectangle.setHeight(minHeight);
      anythingChanged = true;
    }

    // height of visible rectangle (same as invisible rectangle)
    if (rectangle.getHeight() != invisibleRectangle.getHeight()) {
      rectangle.setHeight(invisibleRectangle.getHeight());
      anythingChanged = true;
    }

    // width of invisible rectangle
    if (!containsExtFigure && invisibleRectangle.getWidth() < MIN_WIDTH) {
      invisibleRectangle.setWidth(MIN_WIDTH);
      anythingChanged = true;
    }

    // width of visible rectangle (smaller than invisible rectangle)
    int xOffset = isActor ? 15 : 0;
    int rectangleWidth = invisibleRectangle.getWidth() - xOffset;
    int rectangleHeight = invisibleRectangle.getHeight();
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
            int shapeXOffset = isActor ? ActorAddFeature.ACTOR_X_OFFSET : 0;
            if (ga instanceof Polyline) {
              Polyline polyline = (Polyline) ga;
              Point secondPoint = polyline.getPoints().get(1);
              Point newSecondPoint = gaService.createPoint(shapeXOffset + rectangleWidth, secondPoint.getY());
              polyline.getPoints().set(1, newSecondPoint);
              anythingChanged = true;
            } else if (ga instanceof Text) {
              gaService.setLocationAndSize(ga, shapeXOffset + 20, ga.getY(), rectangleWidth - 25, ga.getHeight());
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

    List<Anchor> anchors = containerShape.getAnchors();
    int yStart = containsExtFigure ? 0 : MIN_HEIGHT;
    int yOffsetForInputPorts = (rectangleHeight - yStart) / (1 + (nrInputs>0?nrInputs:1));
    int yOffsetForOutputPorts = (rectangleHeight - yStart) / (1 + (nrOutputs>0?nrOutputs:1));
    yOffsetForInputPorts = yOffsetForInputPorts > ActorAddFeature.PORT_SIZE ? yOffsetForInputPorts : ActorAddFeature.PORT_SIZE;
    yOffsetForOutputPorts = yOffsetForOutputPorts > ActorAddFeature.PORT_SIZE ? yOffsetForOutputPorts : ActorAddFeature.PORT_SIZE;
    int inCnt = 1;
    int outCnt = 1;
    for (int i = 0; i < anchors.size(); ++i) {
      FixPointAnchor fpa = (FixPointAnchor) anchors.get(i);
      PortCategory portCategory = PortCategory.retrieveFrom(fpa);
      if (PortCategory.EAST.equals(portCategory)) {
        fpa.setLocation(gaService.createPoint(xOffset + rectangleWidth, yStart +  yOffsetForOutputPorts*(outCnt++)));
        anythingChanged = true;
      } else if (PortCategory.WEST.equals(portCategory)) {
        fpa.setLocation(gaService.createPoint(0, yStart + yOffsetForInputPorts*(inCnt++)));
        anythingChanged = true;
      }
    }

    return anythingChanged;
  }
}
