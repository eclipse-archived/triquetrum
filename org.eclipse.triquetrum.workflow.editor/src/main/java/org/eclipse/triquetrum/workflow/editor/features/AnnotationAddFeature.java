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
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.Annotation;
import org.eclipse.triquetrum.workflow.model.NamedObj;

/**
 * This feature allows to add annotations (TextAttributes) on a CompositeActor
 *
 */
public class AnnotationAddFeature extends AbstractAddShapeFeature {
  private static final IColorConstant ANNOTATION_BACKGROUND = new ColorConstant(255, 255, 200);

  private static final int WIDTH = 160;
  private static final int HEIGHT_LINES = 5;
  private static final int SPACING_INTERLINE = 5;
  private static final int X_OFFSET_TEXT = 5;

  public AnnotationAddFeature(IFeatureProvider fp) {
    super(fp);
  }

  protected void link(PictogramElement pe, Object businessObject, BoCategory category) {
    super.link(pe, businessObject);
    // add property on the graphical model element, identifying the associated triq model element
    // so we can easily distinguish and identify them later on for updates etc
    category.storeIn(pe);
    if (businessObject instanceof NamedObj) {
      Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.BO_NAME, ((NamedObj) businessObject).getName());
    }
    Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.BO_CLASS, businessObject.getClass().getName());
  }

  public boolean canAdd(IAddContext context) {
    // check if user wants to add an Annotation
    if (context.getNewObject() instanceof Annotation) {
      // check if user wants to add to a diagram
      if (context.getTargetContainer() instanceof Diagram) {
        return true;
      }
    }
    return false;
  }

  public PictogramElement add(IAddContext context) {
    Annotation addedAnnotation = (Annotation) context.getNewObject();
    int textSize = addedAnnotation.getTextSize();
    String pVal = addedAnnotation.getText();

    Diagram targetDiagram = (Diagram) context.getTargetContainer();
    int xLocation = context.getX();
    int yLocation = context.getY();

    IPeCreateService peCreateService = Graphiti.getPeCreateService();
    IGaService gaService = Graphiti.getGaService();
    ContainerShape containerShape = peCreateService.createContainerShape(targetDiagram, true);

    Rectangle rectangle = gaService.createRectangle(containerShape);
    gaService.setLocationAndSize(rectangle, xLocation, yLocation, WIDTH, (textSize+SPACING_INTERLINE) * HEIGHT_LINES);
    rectangle.setBackground(manageColor(ANNOTATION_BACKGROUND));

    // create shape for text
    Shape shape = peCreateService.createShape(containerShape, false);
    // create and set text graphics algorithm
    MultiText text = gaService.createMultiText(shape, pVal);
    text.setForeground(EditorUtils.buildColorFromString(getDiagram(), addedAnnotation.getColor()));
    text.setHeight(HEIGHT_LINES);
    text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
    text.setVerticalAlignment(Orientation.ALIGNMENT_TOP);
    text.setFont(gaService.manageFont(getDiagram(), addedAnnotation.getFontFamily(), textSize, addedAnnotation.isItalic(), addedAnnotation.isBold()));
    gaService.setLocationAndSize(text, X_OFFSET_TEXT, 0, WIDTH - X_OFFSET_TEXT, (textSize+SPACING_INTERLINE)*HEIGHT_LINES);

    link(containerShape, addedAnnotation, BoCategory.Annotation);

    layoutPictogramElement(containerShape);

    return containerShape;
  }
}