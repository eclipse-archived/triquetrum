/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.Annotation;

/**
 *
 */
public class AnnotationResizeFeature extends DefaultResizeShapeFeature {

  /**
   * @param fp
   */
  public AnnotationResizeFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canResizeShape(IResizeShapeContext context) {
    BoCategory boCategory = BoCategory.retrieveFrom(context.getPictogramElement());
    return (BoCategory.Annotation.equals(boCategory));
  };

  @Override
  public void resizeShape(IResizeShapeContext context) {
    int width = context.getWidth();
    int height = context.getHeight();

    PictogramElement pictogramElement = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    if (bo instanceof Annotation && pictogramElement instanceof Shape) {
      Annotation annotation = (Annotation) bo;
      Shape shape = (Shape) pictogramElement;
      BoCategory boCategory = BoCategory.retrieveFrom(shape);
      if (BoCategory.Annotation.equals(boCategory)) {
        MultiText text = EditorUtils.getGraphicsAlgorithmOfShape(shape, MultiText.class);
        if (text != null) {
          text.setWidth(width - 5);
          text.setHeight(height / (annotation.getTextSize() + 5));
          Graphiti.getGaService().setLocationAndSize(text, 5, 0, width, height);
        }
      }
    }
    super.resizeShape(context);
  }
}
