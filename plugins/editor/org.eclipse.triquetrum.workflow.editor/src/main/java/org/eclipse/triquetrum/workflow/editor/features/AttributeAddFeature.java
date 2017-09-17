/*******************************************************************************
 * Copyright (c) 2017 iSencia Belgium NV.
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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.Category;
import org.eclipse.triquetrum.workflow.editor.TriqFeatureProvider;
import org.eclipse.triquetrum.workflow.model.Attribute;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Parameter;

public class AttributeAddFeature extends AbstractAddShapeFeature {

  private static final IColorConstant ATTRIBUTE_NAME_FOREGROUND = IColorConstant.BLACK;
  private static final IColorConstant ATTRIBUTE_DOTBACKGROUND = IColorConstant.BLUE;
  private static final IColorConstant STRINGPARAMETER_DOTBACKGROUND = IColorConstant.RED;

  private static final int WIDTH = 160;
  private static final int HEIGHT = 20;

  private static final Map<String, IColorConstant> DOT_COLOURS = new HashMap<>();
  {
    DOT_COLOURS.put("ptolemy.data.expr.StringParameter", STRINGPARAMETER_DOTBACKGROUND);
    DOT_COLOURS.put("ptolemy.data.expr.Parameter", ATTRIBUTE_DOTBACKGROUND);
  }

  public AttributeAddFeature(IFeatureProvider fp) {
    super(fp);
  }

  /**
   * Extends Graphiti's default linking between a pictogram element and a business object, by also storing extra
   * properties to facilitate determining changes between business model and graphical model.
   * 
   * @param pe
   * @param businessObject
   * @param categories
   */
  protected void link(PictogramElement pe, Object businessObject, Category... categories) {
    super.link(pe, businessObject);
    // add property on the graphical model element, identifying the associated triq model element
    // so we can easily distinguish and identify them later on for updates etc
    for (Category category : categories) {
      category.storeIn(pe);
    }
    if (businessObject instanceof NamedObj) {
      Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.BO_NAME, ((NamedObj) businessObject).getName());
    }
    Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.BO_CLASS, businessObject.getClass().getName());
  }

  @Override
  public boolean canAdd(IAddContext context) {
    // check if user wants to add an attribute
    if (context.getNewObject() instanceof Attribute) {
      Object parentObject = getBusinessObjectForPictogramElement(context.getTargetContainer());
      return (parentObject instanceof CompositeActor);
    }
    return false;
  }

  @Override
  public PictogramElement add(IAddContext context) {
    Attribute addedAttribute = (Attribute) context.getNewObject();
    ContainerShape targetContainer = context.getTargetContainer();

    int xLocation = context.getX();
    int yLocation = context.getY();

    IPeCreateService peCreateService = Graphiti.getPeCreateService();
    IGaService gaService = Graphiti.getGaService();
    ContainerShape containerShape = peCreateService.createContainerShape(targetContainer, true);

    GraphicsAlgorithm invisibleRectangle = null;
    invisibleRectangle = gaService.createInvisibleRectangle(containerShape);

    GraphicsAlgorithm attributeShapeGA = null;

    String iconResource = (String) context.getProperty("icon");
    String iconType = (String) context.getProperty("iconType");

    switch (iconType) {
    case TriqFeatureProvider.ICONTYPE_SVG:
    case TriqFeatureProvider.ICONTYPE_PTOLEMY:
      attributeShapeGA = buildExternallyDefinedShape(gaService, invisibleRectangle, containerShape, iconType, iconResource);
      break;
    default:
      attributeShapeGA = buildDefaultShape(gaService, invisibleRectangle, containerShape, addedAttribute, iconResource);
    }

    int width = attributeShapeGA.getWidth();
    int height = attributeShapeGA.getHeight();
    gaService.setLocationAndSize(invisibleRectangle, xLocation, yLocation, width, height);

    if (addedAttribute instanceof Parameter) {
      link(containerShape, addedAttribute, BoCategory.Parameter);
    } else {
      link(containerShape, addedAttribute, BoCategory.Attribute);
    }

    layoutPictogramElement(containerShape);

    return containerShape;
  }

  /**
   * Builds the default actor shape, consisting of a rounded rectangle containing a small icon and the actor's name.
   * <p>
   * Used when no specific image/icon definition has been set for a given actor.
   * </p>
   * 
   * @param gaService
   * @param invisibleRectangle
   * @param containerShape
   * @param addedAttribute
   * @param iconResource
   * @return
   */
  protected GraphicsAlgorithm buildDefaultShape(IGaService gaService, GraphicsAlgorithm invisibleRectangle, ContainerShape containerShape,
      Attribute addedAttribute, String iconResource) {

    // CONTAINER SHAPE WITH ROUNDED RECTANGLE
    IPeCreateService peCreateService = Graphiti.getPeCreateService();

    Shape dotShape = peCreateService.createShape(containerShape, false);
    Ellipse dot = gaService.createEllipse(dotShape);
    dot.setBackground(manageColor(DOT_COLOURS.getOrDefault(addedAttribute.getWrappedType(), ATTRIBUTE_DOTBACKGROUND)));
    gaService.setLocationAndSize(dot, 0, 6, 8, 8);
    // create shape for text
    Shape textShape = peCreateService.createShape(containerShape, false);
    // create and set text graphics algorithm
    String pName = addedAttribute.getName();
    String textValue = pName;

    if (addedAttribute instanceof Parameter) {
      String pVal = ((Parameter) addedAttribute).getExpression();
      pVal = pVal != null ? pVal : "";
      textValue += " : " + pVal;
    }

    Text text = gaService.createText(textShape, textValue);
    text.setForeground(manageColor(ATTRIBUTE_NAME_FOREGROUND));
    text.setFont(gaService.manageFont(getDiagram(), IGaService.DEFAULT_FONT, 9, false, true));
    gaService.setLocationAndSize(text, 10, 0, WIDTH, HEIGHT);
    gaService.setSize(invisibleRectangle, WIDTH + 10, HEIGHT);

    if (addedAttribute instanceof Parameter) {
      String pVal = ((Parameter) addedAttribute).getExpression();
      Graphiti.getPeService().setPropertyValue(textShape, "__BO_VALUE", pVal);
      BoCategory.Parameter.storeIn(textShape);
    } else {
      BoCategory.Attribute.storeIn(textShape);
    }

    return invisibleRectangle;
  }

  /**
   * Builds the actor shape based on an external definition, e.g. in SVG or in Ptolemy icon moml files.
   * 
   * @param gaService
   * @param invisibleRectangle
   * @param containerShape
   * @param iconType
   * @param iconResource
   * @return
   */
  protected GraphicsAlgorithm buildExternallyDefinedShape(IGaService gaService, GraphicsAlgorithm invisibleRectangle, ContainerShape containerShape,
      String iconType, String iconResource) {

    GraphicsAlgorithm extFigure = Graphiti.getGaCreateService().createPlatformGraphicsAlgorithm(invisibleRectangle, iconType);
    {
      Property property = MmFactory.eINSTANCE.createProperty();
      property.setKey("iconType");
      property.setValue(iconType);
      extFigure.getProperties().add(property);
    }
    {
      Property property = MmFactory.eINSTANCE.createProperty();
      property.setKey("iconResource");
      property.setValue(iconResource);
      extFigure.getProperties().add(property);
    }
    gaService.setLocationAndSize(extFigure, 0, 0, 40, 40);
    return extFigure;
  }
}