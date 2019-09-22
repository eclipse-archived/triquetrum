/*******************************************************************************
 * Copyright (c) 2016,2019 iSencia Belgium NV.
 *  
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.triquetrum.workflow.editor.BoCategory;

public class AttributeLayoutFeature extends AbstractLayoutFeature {

  public AttributeLayoutFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canLayout(ILayoutContext context) {
    BoCategory boCategory = BoCategory.retrieveFrom(context.getPictogramElement());
    return BoCategory.Attribute.equals(boCategory) || BoCategory.Parameter.equals(boCategory);
  }

  @Override
  public boolean layout(ILayoutContext context) {
    boolean anythingChanged = false;
    ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
    // the invisible rectangle contains the visible rectangle as its (first and only) child
    GraphicsAlgorithm invisibleRectangle = containerShape.getGraphicsAlgorithm();
    GraphicsAlgorithm rectangle = invisibleRectangle.getGraphicsAlgorithmChildren().get(0);

    // height of visible rectangle
    int rectangleHeight = invisibleRectangle.getHeight();
    if (rectangle.getHeight() != rectangleHeight) {
      rectangle.setHeight(rectangleHeight);
      anythingChanged = true;
    }

    // width of visible rectangle
    int rectangleWidth = invisibleRectangle.getWidth();
    if (rectangle.getWidth() != rectangleWidth) {
      rectangle.setWidth(rectangleWidth);
      anythingChanged = true;
    }

    return anythingChanged;
  }
}
