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
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

public class ModelElementLayoutFeature extends AbstractLayoutFeature {

	private static final int MIN_HEIGHT = 30;

	private static final int MIN_WIDTH = 50;

	public ModelElementLayoutFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canLayout(ILayoutContext context) {
    String boCategory = Graphiti.getPeService().getPropertyValue(context.getPictogramElement(), "__BO_CATEGORY");
    return "ACTOR".equals(boCategory) || "DIRECTOR".equals(boCategory);
	}

	public boolean layout(ILayoutContext context) {
		boolean anythingChanged = false;
		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
		// the containerGa is the invisible rectangle
		// containing the visible rectangle as its (first and only) child
		GraphicsAlgorithm rectangle = containerGa.getGraphicsAlgorithmChildren().get(0);

		// height of invisible rectangle
		if (containerGa.getHeight() < MIN_HEIGHT) {
			containerGa.setHeight(MIN_HEIGHT);
			anythingChanged = true;
		}

		// height of visible rectangle (same as invisible rectangle)
		if (rectangle.getHeight() != containerGa.getHeight()) {
			rectangle.setHeight(containerGa.getHeight());
			anythingChanged = true;
		}

		// width of invisible rectangle
		if (containerGa.getWidth() < MIN_WIDTH) {
			containerGa.setWidth(MIN_WIDTH);
			anythingChanged = true;
		}

		int widthChange = 0;
		// width of visible rectangle (smaller than invisible rectangle)
		int rectangleWidth = containerGa.getWidth() - 15;
		if (rectangle.getWidth() != rectangleWidth) {
		  widthChange = rectangleWidth - rectangle.getWidth();
			rectangle.setWidth(rectangleWidth);
			anythingChanged = true;
		}

		// width of text and line (same as visible rectangle)
		for (Shape shape : containerShape.getChildren()) {
			GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
			IGaService gaService = Graphiti.getGaService();
			IDimension size = gaService.calculateSize(ga);
			if (rectangleWidth != size.getWidth()) {
				if (ga instanceof Polyline) {
					Polyline polyline = (Polyline) ga;
					Point secondPoint = polyline.getPoints().get(1);
					Point newSecondPoint = gaService.createPoint(rectangleWidth, secondPoint.getY());
					polyline.getPoints().set(1, newSecondPoint);
					anythingChanged = true;
				} else if (ga instanceof Image) {
				  // remain unchanged
				} else {
					gaService.setWidth(ga, rectangleWidth);
					anythingChanged = true;
				}
			}

			for(Anchor anchor : containerShape.getAnchors()) {
			  FixPointAnchor fpa = (FixPointAnchor) anchor;
			  GraphicsAlgorithm portGa = anchor.getGraphicsAlgorithm();
        String boCategory = Graphiti.getPeService().getPropertyValue(anchor, "__BO_CATEGORY");
        if("OUTPUT".equals(boCategory)) {
          fpa.setLocation(gaService.createPoint(15 + rectangleWidth, fpa.getLocation().getY()));

//          gaService.setLocation(portGa, widthChange, portGa.getY());
          anythingChanged = true;
        }
			}
		}

		return anythingChanged;
	}
}
