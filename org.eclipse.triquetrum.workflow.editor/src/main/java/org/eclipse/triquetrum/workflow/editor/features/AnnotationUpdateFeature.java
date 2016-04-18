/*******************************************************************************
 * Copyright (c) 2015 iSencia Belgium NV.
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
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.triquetrum.workflow.editor.BoCategories;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.Annotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class AnnotationUpdateFeature extends AbstractUpdateFeature {
  private final static Logger LOGGER = LoggerFactory.getLogger(AnnotationUpdateFeature.class);

  public AnnotationUpdateFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canUpdate(IUpdateContext context) {
    BoCategories boCategory = BoCategories.retrieveFrom(context.getPictogramElement());
    return (BoCategories.Annotation.equals(boCategory));
  }

  @Override
  public IReason updateNeeded(IUpdateContext context) {
    PictogramElement pictogramElement = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    boolean annotationChanged = false;
    Annotation annotation = null;
    if (bo instanceof Annotation && pictogramElement instanceof Shape) {
      annotation = (Annotation) bo;
      Shape shape = (Shape) pictogramElement;
      BoCategories boCategory = BoCategories.retrieveFrom(shape);
      if (BoCategories.Annotation.equals(boCategory)) {
        MultiText text = EditorUtils.getGraphicsAlgorithmOfShape(shape, MultiText.class);
        if (text != null) {
          String boValue = text.getValue();
          Color currentTextColor = text.getForeground();
          int currentTextSize = text.getFont().getSize();
          String currentTextFont = text.getFont().getName();
          annotationChanged = (annotation.getText() != null && !annotation.getText().equals(boValue))
              || (annotation.getTextSize() != currentTextSize)
              || (annotation.getColor() != null && !annotation.getColor().equals(EditorUtils.toString(currentTextColor)))
              || (!annotation.getFontFamily().equals(currentTextFont));
        } else {
          // should not happen
          LOGGER.error("Inconsistent shape tree in " + shape);
        }
      }
    }
    if (annotationChanged) {
      context.putProperty("ANNOTATION_CHANGED", annotation.getName());
      return Reason.createTrueReason("Annotation change");
    } else {
      return Reason.createFalseReason();
    }
  }

  @Override
  public boolean update(IUpdateContext context) {
    boolean result = false;
    PictogramElement pictogramElement = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    if (bo instanceof Annotation && pictogramElement instanceof Shape) {
      Annotation annotation = (Annotation) bo;
      Shape shape = (Shape) pictogramElement;
      BoCategories boCategory = BoCategories.retrieveFrom(shape);
      if (BoCategories.Annotation.equals(boCategory)) {
        MultiText text = EditorUtils.getGraphicsAlgorithmOfShape(shape, MultiText.class);
        if (text != null) {
          text.setValue(annotation.getText());
          Color newColor = EditorUtils.buildColorFromString(getDiagram(), annotation.getColor());
          text.setForeground(manageColor(newColor.getRed(), newColor.getGreen(), newColor.getBlue()));
          text.setFont(Graphiti.getGaService().manageFont(getDiagram(), annotation.getFontFamily(), annotation.getTextSize(), annotation.isItalic(),
              annotation.isBold()));
          result = true;
        } else {
          // TODO report an error here?
        }
      }
    }
    return result;
  }
}
