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

import static org.eclipse.triquetrum.workflow.editor.shapes.ActorShapes.*;

import org.apache.commons.lang.StringUtils;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.triquetrum.workflow.ErrorCode;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.TriqFeatureProvider;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompositeActorAddFeature extends ActorAddFeature {

  private final static Logger LOGGER = LoggerFactory.getLogger(CompositeActorAddFeature.class);

  public static final IColorConstant COMPACTOR_NAME_FOREGROUND = IColorConstant.BLACK;
  public static final IColorConstant COMPACTOR_FOREGROUND = IColorConstant.BLACK;
  public static final IColorConstant COMPACTOR_BACKGROUND = IColorConstant.LIGHT_LIGHT_GRAY;

  public CompositeActorAddFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canAdd(IAddContext context) {
    // check if user wants to add an actor
    return (context.getNewObject() instanceof CompositeActor);
  }

  @Override
  public PictogramElement add(IAddContext context) {
    Entity addedActor = (Entity) context.getNewObject();
    ContainerShape targetContainer = context.getTargetContainer();

    int xLocation = context.getX();
    int yLocation = context.getY();

    IPeCreateService peCreateService = Graphiti.getPeCreateService();
    IGaService gaService = Graphiti.getGaService();
    ContainerShape containerShape = peCreateService.createContainerShape(targetContainer, true);
    link(context, containerShape, addedActor, BoCategory.CompositeActor);

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
    gaService.setLocationAndSize(invisibleRectangle, xLocation, yLocation, width + 15, height);

    layoutPictogramElement(containerShape);

    return containerShape;
  }

  @Override
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
      polyline.setForeground(manageColor(COMPACTOR_FOREGROUND));
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
      text.setForeground(manageColor(COMPACTOR_NAME_FOREGROUND));
      text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
      // vertical alignment has as default value "center"
      text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
      gaService.setLocationAndSize(text, ACTOR_TEXT_X_MARGIN, ACTOR_Y_MARGIN, ACTOR_TEXT_WIDTH, ACTOR_TEXT_HEIGHT);

      // create link and wire it
      link(context, shape, addedActor, BoCategory.CompositeActor);

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
}