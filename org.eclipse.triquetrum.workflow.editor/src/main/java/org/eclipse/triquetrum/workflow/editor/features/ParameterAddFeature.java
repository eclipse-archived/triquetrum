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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.triquetrum.workflow.editor.BoCategories;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Parameter;

/**
 * This feature allows to add parameters on a CompositeActor
 *
 */
public class ParameterAddFeature extends AbstractAddShapeFeature {
  private static final IColorConstant PARAMETER_NAME_FOREGROUND = IColorConstant.BLACK;
  private static final IColorConstant PARAMETER_DOTBACKGROUND = IColorConstant.BLUE;
  private static final IColorConstant STRINGPARAMETER_DOTBACKGROUND = IColorConstant.RED;

  private static final int WIDTH = 160;
  private static final int HEIGHT = 20;

  private static final Map<String, IColorConstant> DOT_COLOURS = new HashMap<>();
  {
    DOT_COLOURS.put("ptolemy.data.expr.StringParameter", STRINGPARAMETER_DOTBACKGROUND);
    DOT_COLOURS.put("ptolemy.data.expr.Parameter", PARAMETER_DOTBACKGROUND);
  }

  public ParameterAddFeature(IFeatureProvider fp) {
    super(fp);
  }

  protected void link(PictogramElement pe, Object businessObject, String category) {
    super.link(pe, businessObject);
    // add property on the graphical model element, identifying the associated triq model element
    // so we can easily distinguish and identify them later on for updates etc
    if (businessObject instanceof NamedObj) {
      Graphiti.getPeService().setPropertyValue(pe, "__BO_NAME", ((NamedObj) businessObject).getName());
    }
    Graphiti.getPeService().setPropertyValue(pe, BoCategories.BO_CATEGORY_PROPNAME, category);
    Graphiti.getPeService().setPropertyValue(pe, "__BO_CLASS", businessObject.getClass().getName());
  }

  public boolean canAdd(IAddContext context) {
    // check if user wants to add a parameter
    if (context.getNewObject() instanceof Parameter) {
      // check if user wants to add to a diagram
      if (context.getTargetContainer() instanceof Diagram) {
        return true;
      }
    }
    return false;
  }

  public PictogramElement add(IAddContext context) {
    Parameter addedParameter = (Parameter) context.getNewObject();
    Diagram targetDiagram = (Diagram) context.getTargetContainer();
    int xLocation = context.getX();
    int yLocation = context.getY();

    // CONTAINER SHAPE WITH ROUNDED RECTANGLE
    IPeCreateService peCreateService = Graphiti.getPeCreateService();
    IGaService gaService = Graphiti.getGaService();
    ContainerShape containerShape = peCreateService.createContainerShape(targetDiagram, true);

    Rectangle invisibleRectangle = gaService.createInvisibleRectangle(containerShape);
    gaService.setLocationAndSize(invisibleRectangle, xLocation, yLocation, WIDTH+10, HEIGHT);

    Shape dotShape = peCreateService.createShape(containerShape, false);
    Ellipse dot = gaService.createEllipse(dotShape);
    dot.setBackground(manageColor(DOT_COLOURS.getOrDefault(addedParameter.getWrappedType(), PARAMETER_DOTBACKGROUND)));
    gaService.setLocationAndSize(dot, 0, 6, 8, 8);
    // create shape for text
    Shape shape = peCreateService.createShape(containerShape, false);
    // create and set text graphics algorithm
    String pName = addedParameter.getName();
    String pVal = addedParameter.getExpression();
    pVal = pVal != null ? pVal : "";

    Text text = gaService.createText(shape, pName + " : " + pVal);
    text.setForeground(manageColor(PARAMETER_NAME_FOREGROUND));
    text.setFont(gaService.manageFont(getDiagram(), IGaService.DEFAULT_FONT, 9, false, true));
//    gaService.setLocation(text, 0, 0);
    gaService.setLocationAndSize(text, 10, 0, WIDTH, HEIGHT);

    link(shape, addedParameter, "PARAMETER");
    link(containerShape, addedParameter, "PARAMETER");
    Graphiti.getPeService().setPropertyValue(shape, "__BO_VALUE", addedParameter.getExpression());

    // provide information to support direct-editing directly
    // after object creation (must be activated additionally)
    IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
    // set container shape for direct editing after object creation
    directEditingInfo.setMainPictogramElement(containerShape);
    // set shape and graphics algorithm where the editor for
    // direct editing shall be opened after object creation
    directEditingInfo.setPictogramElement(shape);
    directEditingInfo.setGraphicsAlgorithm(text);

    layoutPictogramElement(containerShape);

    return containerShape;
  }
}