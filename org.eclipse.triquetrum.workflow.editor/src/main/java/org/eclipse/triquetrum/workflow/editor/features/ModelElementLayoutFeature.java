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
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;

public class ModelElementLayoutFeature extends AbstractLayoutFeature {

  private static final int MIN_HEIGHT = 30;

  private static final int MIN_WIDTH = 50;

  public ModelElementLayoutFeature(IFeatureProvider fp) {
    super(fp);
  }

  public boolean canLayout(ILayoutContext context) {
    BoCategory boCategory = BoCategory.retrieveFrom(context.getPictogramElement());
    return BoCategory.CompositeActor.equals(boCategory) || BoCategory.Actor.equals(boCategory) || BoCategory.Director.equals(boCategory);
  }

  public boolean layout(ILayoutContext context) {
    boolean anythingChanged = false;
    ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
    // the invisible rectangle contains the visible rectangle as its (first and only) child
    GraphicsAlgorithm invisibleRectangle = containerShape.getGraphicsAlgorithm();
    GraphicsAlgorithm rectangle = invisibleRectangle.getGraphicsAlgorithmChildren().get(0);

    BoCategory boCategory = BoCategory.retrieveFrom(context.getPictogramElement());
    boolean isActor = BoCategory.Actor.equals(boCategory);

    boolean containsExtFigure = EditorUtils.containsExternallyDefinedFigure(containerShape);

    // Manual resizing is currently blocked, so only resizing that can trigger
    // this layout feature is the one based on determining size of externally defined figures.
    // and for those we don't set minimum sizes...

    // height of invisible rectangle
    if (!containsExtFigure && invisibleRectangle.getHeight() < MIN_HEIGHT) {
      invisibleRectangle.setHeight(MIN_HEIGHT);
      anythingChanged = true;
    }

    // height of visible rectangle (same as invisible rectangle)
    double heightChangeRatio = 1;
    if (rectangle.getHeight() != invisibleRectangle.getHeight()) {
      heightChangeRatio = invisibleRectangle.getHeight() / rectangle.getHeight();
      rectangle.setHeight(invisibleRectangle.getHeight());
      anythingChanged = true;
    }

    // width of invisible rectangle
    if (!containsExtFigure && invisibleRectangle.getWidth() < MIN_WIDTH) {
      invisibleRectangle.setWidth(MIN_WIDTH);
      anythingChanged = true;
    }

    // width of visible rectangle (smaller than invisible rectangle)
    int rectangleWidth = isActor ? invisibleRectangle.getWidth() - 15 : invisibleRectangle.getWidth();
    if (rectangle.getWidth() != rectangleWidth) {
      rectangle.setWidth(rectangleWidth);
      anythingChanged = true;
    }
    IGaService gaService = Graphiti.getGaService();

    if (!containsExtFigure) {
      // width of text and line (same as visible rectangle)
      for (Shape shape : containerShape.getChildren()) {
        GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
        IDimension size = gaService.calculateSize(ga);
        if (rectangleWidth != size.getWidth()) {
          int shapeXOffset = isActor ? ActorAddFeature.SHAPE_X_OFFSET : DirectorAddFeature.SHAPE_X_OFFSET;
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

    for (Anchor anchor : containerShape.getAnchors()) {
      FixPointAnchor fpa = (FixPointAnchor) anchor;
      boCategory = BoCategory.retrieveFrom(anchor);
      // TODO determine rescaled port Y position in a better way
      // this Y-rescaling works for increasing heights
      // but may lead to disappearing ports when shrinking a shape along Y
      if (BoCategory.Output.equals(boCategory)) {
        fpa.setLocation(gaService.createPoint(15 + rectangleWidth, (int) (fpa.getLocation().getY() * heightChangeRatio)));
        anythingChanged = true;
      } else if (BoCategory.Input.equals(boCategory)) {
        fpa.setLocation(gaService.createPoint(fpa.getLocation().getX(), (int) (fpa.getLocation().getY() * heightChangeRatio)));
        anythingChanged = true;
      }
    }

    return anythingChanged;
  }
}
