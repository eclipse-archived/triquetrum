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

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static org.eclipse.triquetrum.workflow.editor.shapes.ActorShapes.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang.StringUtils;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.triquetrum.workflow.ErrorCode;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.Category;
import org.eclipse.triquetrum.workflow.editor.PortCategory;
import org.eclipse.triquetrum.workflow.editor.TriqFeatureProvider;
import org.eclipse.triquetrum.workflow.editor.shapes.PortShapes;
import org.eclipse.triquetrum.workflow.model.Actor;
import org.eclipse.triquetrum.workflow.model.Direction;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActorAddFeature extends AbstractAddShapeFeature {

  private final static Logger LOGGER = LoggerFactory.getLogger(ActorAddFeature.class);

  public ActorAddFeature(IFeatureProvider fp) {
    super(fp);
  }

  /**
   * Extends Graphiti's default linking between a pictogram element and a business object,
   * by also storing extra properties to facilitate determining changes between business model and graphical model.
   * 
   * @param pe
   * @param businessObject
   * @param categories
   */
  protected void link(IContext context, PictogramElement pe, Object businessObject, Category... categories) {
    super.link(pe, businessObject);
    // add property on the graphical model element, identifying the associated triq model element
    // so we can easily distinguish and identify them later on for updates etc
    for (Category category : categories) {
      category.storeIn(pe);
    }
    if (businessObject instanceof NamedObj) {
      Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.BO_NAME, ((NamedObj) businessObject).getName());
      String iconResource = (String) context.getProperty("icon");
      if(iconResource!=null) {
        Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.ICON, iconResource);
      }
      String iconType = (String) context.getProperty("iconType");
      if(iconType!=null) {
        Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.ICON_TYPE, iconType);
      }
    }
    Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.BO_CLASS, businessObject.getClass().getName());
  }

  @Override
  public boolean canAdd(IAddContext context) {
    // check if user wants to add an actor
    return (context.getNewObject() instanceof Actor);
  }

  @Override
  public PictogramElement add(IAddContext context) {
    Entity addedActor = (Entity) context.getNewObject();
    ContainerShape targetContainer = context.getTargetContainer();

    // This should be a duplicate from what's in ModelElementCreateFeature,
    // to link the toplevel CompositeActor to the Diagram.
    // So let's try to do without this.
    // Object topLevelForDiagram = getBusinessObjectForPictogramElement(getDiagram());
    // if (topLevelForDiagram == null) {
    // link(getDiagram(), addedActor.getContainer());
    // }

    int xLocation = context.getX();
    int yLocation = context.getY();

    IPeCreateService peCreateService = Graphiti.getPeCreateService();
    IGaService gaService = Graphiti.getGaService();
    ContainerShape containerShape = peCreateService.createContainerShape(targetContainer, true);
    link(context, containerShape, addedActor, BoCategory.Actor);

    GraphicsAlgorithm invisibleRectangle = null;
    invisibleRectangle = gaService.createInvisibleRectangle(containerShape);

    GraphicsAlgorithm actorShapeGA = null;

    String iconResource = (String) context.getProperty("icon");
    String iconType = (String) context.getProperty("iconType");

    switch (iconType) {
    case TriqFeatureProvider.ICONTYPE_SVG:
    case TriqFeatureProvider.ICONTYPE_PTOLEMY:
      actorShapeGA = buildExternallyDefinedShape(context, gaService, invisibleRectangle, containerShape, iconType, iconResource);
      break;
    default:
      actorShapeGA = buildDefaultShape(context, gaService, invisibleRectangle, containerShape, addedActor, iconResource);
    }

    int width = actorShapeGA.getWidth();
    int height = actorShapeGA.getHeight();
    gaService.setLocationAndSize(invisibleRectangle, xLocation, yLocation, width + 2*ACTOR_X_MARGIN, height + 2*ACTOR_Y_MARGIN);

    // SHAPES FOR PORTS; added both on default shapes and on custom/externally-defined icons (SVG, ptolemy icons)
    Map<Direction, List<Port>> categorizedPorts = addedActor.getPorts().stream().collect(groupingBy(Port::getDirection, mapping(Function.identity(), toList())));
    categorizedPorts.forEach((direction, ports) -> createAnchorsAndPortShapesForDirection(context, containerShape, direction, ports));

    layoutPictogramElement(containerShape);

    return containerShape;
  }

  /**
   * Builds the default actor shape, consisting of a rounded rectangle containing a small icon and the actor's name.
   * <p>
   * Used when no specific image/icon definition has been set for a given actor.
   * </p>
   * @param gaService
   * @param invisibleRectangle
   * @param containerShape
   * @param addedActor
   * @param iconResource
   * @return
   */
  protected GraphicsAlgorithm buildDefaultShape(IAddContext context, IGaService gaService, GraphicsAlgorithm invisibleRectangle, ContainerShape containerShape, Entity addedActor,
      String iconResource) {

    IPeCreateService peCreateService = Graphiti.getPeCreateService();
    int width = ACTOR_VISIBLE_WIDTH;
    int height = ACTOR_VISIBLE_HEIGHT;

    // create and set graphics algorithm
    RoundedRectangle actorShapeGA = gaService.createRoundedRectangle(invisibleRectangle, 5, 5);
    actorShapeGA.setForeground(manageColor(ACTOR_FOREGROUND));
    actorShapeGA.setBackground(manageColor(ACTOR_BACKGROUND));
    actorShapeGA.setLineWidth(2);
    gaService.setLocationAndSize(actorShapeGA, ACTOR_X_MARGIN, ACTOR_Y_MARGIN, width, height);

    // add the actor's icon
    if (!StringUtils.isBlank(iconResource)) {
      try {
        final Shape shape = peCreateService.createShape(containerShape, false);
        final Image image = gaService.createImage(shape, iconResource);
        addedActor.setIconId(iconResource);
        gaService.setLocationAndSize(image, ACTOR_ICON_X_MARGIN, ACTOR_ICON_Y_MARGIN, ACTOR_ICON_SIZE, ACTOR_ICON_SIZE);

        // create link and wire it
        link(context, shape, addedActor, BoCategory.Actor);
      } catch (Exception e) {
        LOGGER.error(ErrorCode.MODEL_CONFIGURATION_ERROR + " - Error trying to add actor icon for " + addedActor, e);
      }
    }

    // SHAPE WITH LINE
    {
      // create shape for line
      Shape shape = peCreateService.createShape(containerShape, false);

      // create and set graphics algorithm
      Polyline polyline = gaService.createPolyline(shape, ACTOR_TEXT_UNDERLINE_SHAPE);
      polyline.setForeground(manageColor(ACTOR_FOREGROUND));
      polyline.setLineWidth(2);

      // create link and wire it
      link(context, shape, addedActor, BoCategory.Actor);
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
      gaService.setLocationAndSize(text, ACTOR_TEXT_X_MARGIN, ACTOR_Y_MARGIN, ACTOR_TEXT_WIDTH, ACTOR_TEXT_HEIGHT);

      // create link and wire it
      link(context, shape, addedActor, BoCategory.Actor);

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

    return actorShapeGA;
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
  protected GraphicsAlgorithm buildExternallyDefinedShape(IAddContext context, IGaService gaService, GraphicsAlgorithm invisibleRectangle, ContainerShape containerShape,
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
    gaService.setLocationAndSize(extFigure, ACTOR_X_MARGIN, ACTOR_Y_MARGIN, 40, 40);
    return extFigure;
  }

  /**
   * 
   * @param context
   * @param containerShape
   * @param direction
   * @param portList
   */
  private void createAnchorsAndPortShapesForDirection(IAddContext context, ContainerShape containerShape, Direction direction, List<Port> portList) {
    Map<String, Anchor> anchorMap = (Map<String, Anchor>) context.getProperty(FeatureConstants.ANCHORMAP_NAME);
    // The list should only contain pairs for which there are still ports on the actor.
    // But there may still be new ports for which no anchor is present yet in the graphical model.
    int portCount = portList.size();
    for (int i = 0; i < portCount; ++i) {
      Port p = portList.get(i);
      Anchor anchor = PortShapes.createAnchor(containerShape, direction, p, i, portCount);
      PortShapes.createPortShape(getDiagram(), anchor, direction, p);
      link(context, anchor, p, BoCategory.Port, PortCategory.valueOf(direction));
      if (anchorMap != null) {
        anchorMap.put(p.getFullName(), anchor);
      }
    }
  }
}