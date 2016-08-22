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

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.triquetrum.workflow.ErrorCode;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.TriqFeatureProvider;
import org.eclipse.triquetrum.workflow.model.Actor;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO refactor ActorAddFeature for its handling of custom/external icon definitions
public class ActorAddFeature extends AbstractAddShapeFeature {

  private final static Logger LOGGER = LoggerFactory.getLogger(ActorAddFeature.class);

  public static final int SHAPE_X_OFFSET = 8;
  private static final int ICON_X_OFFSET = SHAPE_X_OFFSET + 3;
  private static final int ICON_Y_OFFSET = 3;
  private static final int ICON_SIZE = 16;
  public static final int PORT_SIZE = 12;

  private static final IColorConstant ACTOR_NAME_FOREGROUND = IColorConstant.BLACK;
  private static final IColorConstant PARAM_FOREGROUND = IColorConstant.DARK_GRAY;

  private static final IColorConstant ACTOR_FOREGROUND = new ColorConstant(98, 131, 167);
  private static final IColorConstant ACTOR_BACKGROUND = new ColorConstant(187, 218, 247);
  public static final IColorConstant PORT_FOREGROUND = IColorConstant.BLACK;
  public static final IColorConstant PORT_BACKGROUND_MULTIPORT = IColorConstant.WHITE;
  public static final IColorConstant PORT_BACKGROUND_SINGLEPORT = IColorConstant.BLACK;

  public ActorAddFeature(IFeatureProvider fp) {
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
    // check if user wants to add an actor
    if ((context.getNewObject() instanceof Actor) || (context.getNewObject() instanceof CompositeActor)) {
      // check if user wants to add to a diagram
      if (context.getTargetContainer() instanceof Diagram) {
        return true;
      }
    }
    return false;
  }

  public PictogramElement add(IAddContext context) {
    Entity addedActor = (Entity) context.getNewObject();
    ContainerShape targetContainer = context.getTargetContainer();

    Object topLevelForDiagram = getBusinessObjectForPictogramElement(getDiagram());
    if (topLevelForDiagram == null) {
      link(getDiagram(), addedActor.getContainer());
    }

    int xLocation = context.getX();
    int yLocation = context.getY();

    IPeCreateService peCreateService = Graphiti.getPeCreateService();
    ICreateService createService = Graphiti.getCreateService();
    IGaService gaService = Graphiti.getGaService();
    ContainerShape containerShape = peCreateService.createContainerShape(targetContainer, true);
    link(containerShape, addedActor, BoCategory.Actor);

    GraphicsAlgorithm invisibleRectangle = null;
    invisibleRectangle = gaService.createInvisibleRectangle(containerShape);

    GraphicsAlgorithm actorShapeGA = null;

    String iconResource = (String) context.getProperty("icon");
    String iconType = (String) context.getProperty("iconType");

    switch (iconType) {
    case TriqFeatureProvider.ICONTYPE_SVG:
    case TriqFeatureProvider.ICONTYPE_PTOLEMY:
      actorShapeGA = buildExternallyDefinedShape(gaService, invisibleRectangle, containerShape, iconType, iconResource);
      break;
    default:
      actorShapeGA = buildDefaultShape(gaService, invisibleRectangle, containerShape, addedActor, iconResource);
    }

    int width = actorShapeGA.getWidth();
    int height = actorShapeGA.getHeight();
    gaService.setLocationAndSize(invisibleRectangle, xLocation, yLocation, width + 15, height);

    // SHAPES FOR PORTS; added both on default shapes and on custom/externally-defined icons (SVG, ptolemy icons)
    {
      int halfPortSize = PORT_SIZE / 2;
      // add output port anchor
      int pIndex = 0;
      int pCount = addedActor.getOutputPorts().size();
      if (pCount > 0) {
        int yOffsetForPorts = height / (2 * pCount);
        yOffsetForPorts = yOffsetForPorts > halfPortSize ? yOffsetForPorts : halfPortSize;
        for (Port p : (List<Port>) addedActor.getOutputPorts()) {
          FixPointAnchor anchor = peCreateService.createFixPointAnchor(containerShape);
          anchor.setLocation(createService.createPoint(15 + width, yOffsetForPorts + (pIndex++) * PORT_SIZE));
          anchor.setReferencedGraphicsAlgorithm(invisibleRectangle);
          link(anchor, p, BoCategory.Output);

          final Polygon portShape = gaService.createPlainPolygon(anchor, new int[] { 0, 0, PORT_SIZE, halfPortSize, 0, PORT_SIZE });
          portShape.setForeground(manageColor(PORT_FOREGROUND));
          IColorConstant portColour = p.isMultiPort() ? PORT_BACKGROUND_MULTIPORT : PORT_BACKGROUND_SINGLEPORT;
          portShape.setBackground(manageColor(portColour));
          portShape.setLineWidth(1);
          gaService.setLocationAndSize(portShape, -PORT_SIZE, -halfPortSize, PORT_SIZE, PORT_SIZE);
          // TODO find a way to get the full name from our Triq NamedObj,
          // then we don't need to depend on the presence of the wrapped object.
          Map<String, Anchor> anchorMap = (Map<String, Anchor>) context.getProperty(FeatureConstants.ANCHORMAP_NAME);
          if(anchorMap != null && p.getWrappedObject() != null) {
            anchorMap.put(p.getWrappedObject().getFullName(), anchor);
          }
        }
      }
      pIndex = 0;
      pCount = addedActor.getInputPorts().size();
      if (pCount > 0) {
        int yOffsetForPorts = height / (2 * pCount);
        yOffsetForPorts = yOffsetForPorts > halfPortSize ? yOffsetForPorts : halfPortSize;
        for (Port p : (List<Port>) addedActor.getInputPorts()) {
          FixPointAnchor anchor = peCreateService.createFixPointAnchor(containerShape);
          anchor.setUseAnchorLocationAsConnectionEndpoint(true);
          anchor.setReferencedGraphicsAlgorithm(invisibleRectangle);
          link(anchor, p, BoCategory.Input);

          final Polygon portShape = gaService.createPlainPolygon(anchor, new int[] { 0, 0, PORT_SIZE, halfPortSize, 0, PORT_SIZE });
          portShape.setForeground(manageColor(PORT_FOREGROUND));
          IColorConstant portColour = p.isMultiPort() ? PORT_BACKGROUND_MULTIPORT : PORT_BACKGROUND_SINGLEPORT;
          portShape.setBackground(manageColor(portColour));
          portShape.setLineWidth(1);
          gaService.setLocationAndSize(portShape, 0, -halfPortSize, PORT_SIZE, PORT_SIZE);
          anchor.setLocation(createService.createPoint(0, yOffsetForPorts + (pIndex++) * PORT_SIZE));
          // TODO find a way to get the full name from our Triq NamedObj,
          // then we don't need to depend on the presence of the wrapped object.
          Map<String, Anchor> anchorMap = (Map<String, Anchor>) context.getProperty(FeatureConstants.ANCHORMAP_NAME);
          if(anchorMap != null && p.getWrappedObject() != null) {
            anchorMap.put(p.getWrappedObject().getFullName(), anchor);
          }
        }
      }
    }

    layoutPictogramElement(containerShape);

    return containerShape;
  }

  protected GraphicsAlgorithm buildDefaultShape(IGaService gaService, GraphicsAlgorithm invisibleRectangle, ContainerShape containerShape,
      Entity addedActor,
      String iconResource) {

    IPeCreateService peCreateService = Graphiti.getPeCreateService();
    int width = 100;
    int height = 61;

    // create and set graphics algorithm
    RoundedRectangle actorShapeGA = gaService.createRoundedRectangle(invisibleRectangle, 5, 5);
    actorShapeGA.setForeground(manageColor(ACTOR_FOREGROUND));
    actorShapeGA.setBackground(manageColor(ACTOR_BACKGROUND));
    actorShapeGA.setLineWidth(2);
    gaService.setLocationAndSize(actorShapeGA, SHAPE_X_OFFSET, 0, width, height);

      // add the actor's icon
      if (!StringUtils.isBlank(iconResource)) {
        try {
          final Shape imageShape = peCreateService.createShape(containerShape, false);
          final Image image = gaService.createImage(imageShape, iconResource);
          addedActor.setIconId(iconResource);
          gaService.setLocationAndSize(image, ICON_X_OFFSET, ICON_Y_OFFSET, ICON_SIZE, ICON_SIZE);
        } catch (Exception e) {
          LOGGER.error(ErrorCode.MODEL_CONFIGURATION_ERROR + " - Error trying to add actor icon for " + addedActor, e);
        }
      }

    // SHAPE WITH LINE
    {
      // create shape for line
      Shape shape = peCreateService.createShape(containerShape, false);

      // create and set graphics algorithm
      Polyline polyline = gaService.createPolyline(shape, new int[] { SHAPE_X_OFFSET, 20, SHAPE_X_OFFSET + width, 20 });
      polyline.setForeground(manageColor(ACTOR_FOREGROUND));
      polyline.setLineWidth(2);
    }

    // SHAPE WITH actor name as TEXT
    {
      // create shape for text
      Shape shape = peCreateService.createShape(containerShape, false);

      // create and set text graphics algorithm
      Text text = gaService.createText(shape, addedActor.getName());
      text.setForeground(manageColor(ACTOR_NAME_FOREGROUND));
      text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
      // vertical alignment has as default value "center"
      text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
      gaService.setLocationAndSize(text, SHAPE_X_OFFSET + 20, 0, width - 25, 20);

      // create link and wire it
      link(shape, addedActor, BoCategory.Actor);

      // provide information to support direct-editing directly
      // after object creation (must be activated additionally)
      IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
      // set container shape for direct editing after object creation
      directEditingInfo.setMainPictogramElement(containerShape);
      // set shape and graphics algorithm where the editor for
      // direct editing shall be opened after object creation
      directEditingInfo.setPictogramElement(shape);
      directEditingInfo.setGraphicsAlgorithm(text);
    }

    // SHAPES for basic configurable parameters (other parameters will be in the
    // Configure dialogue, but not shown by default in the actor shape)
//    {
//      int pIndex = 0;
//      for (Parameter param : addedActor.getParameters()) {
//        // create shape for text
//        Shape shape = peCreateService.createShape(containerShape, false);
//
//        // create and set text graphics algorithm
//        String pName = param.getName();
//        String pVal = param.getExpression();
//        pName = (pName.length() > 12) ? pName.substring(0, 12) : pName;
//        pVal = (pVal.length() > 12) ? pVal.substring(0, 12) : pVal;
//
//        Text text = gaService.createText(shape, pName + " : " + pVal);
//        // IUiLayoutService uil = GraphitiUi.getUiLayoutService();
//        // IDimension dim = uil.calculateTextSize(text.getValue(),
//        // text.getFont());
//
//        text.setForeground(manageColor(PARAM_FOREGROUND));
//        text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
//        // vertical alignment has as default value "center"
//        text.setFont(gaService.manageFont(getDiagram(), IGaService.DEFAULT_FONT, 8));
//        gaService.setLocationAndSize(text, SHAPE_X_OFFSET + 5, 22 + 15 * pIndex++, width, 20);
//
//        // create link and wire it
//        link(shape, param, BoCategories.Parameter);
//        Graphiti.getPeService().setPropertyValue(shape, "__BO_VALUE", param.getExpression());
//      }
//    }

    return actorShapeGA;
  }

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
    gaService.setLocationAndSize(extFigure, SHAPE_X_OFFSET, 0, 40, 40);
    return extFigure;
  }
}