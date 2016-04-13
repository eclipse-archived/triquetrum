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
import org.eclipse.triquetrum.workflow.editor.BoCategories;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;

public class ModelElementLayoutFeature extends AbstractLayoutFeature {

	private static final int MIN_HEIGHT = 30;

	private static final int MIN_WIDTH = 50;

	public ModelElementLayoutFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canLayout(ILayoutContext context) {
    String boCategory = Graphiti.getPeService().getPropertyValue(context.getPictogramElement(), BoCategories.BO_CATEGORY_PROPNAME);
    return BoCategories.ACTOR.equals(boCategory) || BoCategories.DIRECTOR.equals(boCategory);
	}

	public boolean layout(ILayoutContext context) {
		boolean anythingChanged = false;
    ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
		// the containerGa is the invisible rectangle
		// containing the visible rectangle as its (first and only) child
		GraphicsAlgorithm rectangle = containerGa.getGraphicsAlgorithmChildren().get(0);

    String boCategory = Graphiti.getPeService().getPropertyValue(context.getPictogramElement(), BoCategories.BO_CATEGORY_PROPNAME);
    boolean isActor = BoCategories.ACTOR.equals(boCategory);

		boolean containsExtFigure = EditorUtils.containsExternallyDefinedFigure(containerShape);

		// Manual resizing is currently blocked, so only resizing that can trigger
		// this layout feature is the one based on determining size of externally defined figures.
		// and for those we don't set minimum sizes...

		// height of invisible rectangle
		if (!containsExtFigure && containerGa.getHeight() < MIN_HEIGHT) {
			containerGa.setHeight(MIN_HEIGHT);
			anythingChanged = true;
		}

		// height of visible rectangle (same as invisible rectangle)
		double heightChangeRatio = 1;
		if (rectangle.getHeight() != containerGa.getHeight()) {
		  heightChangeRatio = containerGa.getHeight() / rectangle.getHeight();
			rectangle.setHeight(containerGa.getHeight());
			anythingChanged = true;
		}

		// width of invisible rectangle
		if (!containsExtFigure && containerGa.getWidth() < MIN_WIDTH) {
			containerGa.setWidth(MIN_WIDTH);
			anythingChanged = true;
		}

		// width of visible rectangle (smaller than invisible rectangle)
		int rectangleWidth = isActor ? containerGa.getWidth() - 15 : containerGa.getWidth();
		if (rectangle.getWidth() != rectangleWidth) {
			rectangle.setWidth(rectangleWidth);
			anythingChanged = true;
		}
    IGaService gaService = Graphiti.getGaService();

    if(!containsExtFigure) {
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

    for(Anchor anchor : containerShape.getAnchors()) {
      FixPointAnchor fpa = (FixPointAnchor) anchor;
      boCategory = Graphiti.getPeService().getPropertyValue(anchor, BoCategories.BO_CATEGORY_PROPNAME);
      // TODO determine rescaled port Y position in a better way
      // this Y-rescaling works for increasing heights
      // but may lead to disappearing ports when shrinking a shape along Y
      if(BoCategories.OUTPUT_PORT.equals(boCategory)) {
        fpa.setLocation(gaService.createPoint(15 + rectangleWidth, (int) (fpa.getLocation().getY() * heightChangeRatio)));
        anythingChanged = true;
      } else if(BoCategories.INPUT_PORT.equals(boCategory)) {
        fpa.setLocation(gaService.createPoint(fpa.getLocation().getX(), (int) (fpa.getLocation().getY() * heightChangeRatio)));
        anythingChanged = true;
      }
    }

		return anythingChanged;
	}
}
