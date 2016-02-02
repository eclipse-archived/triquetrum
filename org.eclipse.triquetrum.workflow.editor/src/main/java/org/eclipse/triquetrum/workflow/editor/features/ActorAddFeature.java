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

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
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
import org.eclipse.triquetrum.workflow.model.Actor;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Parameter;
import org.eclipse.triquetrum.workflow.model.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActorAddFeature extends AbstractAddShapeFeature {

  private final static Logger LOGGER = LoggerFactory.getLogger(ActorAddFeature.class);

  private static final int SHAPE_X_OFFSET = 8;
  private static final int ICON_X_OFFSET = SHAPE_X_OFFSET + 3;
  private static final int ICON_Y_OFFSET = 3;
  private static final int ICON_SIZE = 16;
  private static final int PORT_Y_OFFSET = 28;

  private static final IColorConstant ACTOR_NAME_FOREGROUND = IColorConstant.BLACK;
  private static final IColorConstant PARAM_FOREGROUND = IColorConstant.DARK_GRAY;

  private static final IColorConstant ACTOR_FOREGROUND = new ColorConstant(98, 131, 167);
  private static final IColorConstant ACTOR_BACKGROUND = new ColorConstant(187, 218, 247);
  private static final IColorConstant PORT_FOREGROUND = IColorConstant.BLACK;
  private static final IColorConstant PORT_BACKGROUND_MULTIPORT = IColorConstant.WHITE;
  private static final IColorConstant PORT_BACKGROUND_SINGLEPORT = IColorConstant.BLACK;

  public ActorAddFeature(IFeatureProvider fp) {
    super(fp);
  }

  protected void link(PictogramElement pe, Object businessObject, String category) {
    super.link(pe, businessObject);
    // add property on the graphical model element, identifying the associated triq model element
    // so we can easily distinguish and identify them later on for updates etc
    if (businessObject instanceof NamedObj) {
      Graphiti.getPeService().setPropertyValue(pe, "__BO_NAME", ((NamedObj) businessObject).getName());
    }
    Graphiti.getPeService().setPropertyValue(pe, "__BO_CATEGORY", category);
    Graphiti.getPeService().setPropertyValue(pe, "__BO_CLASS", businessObject.getClass().getName());
  }

  public boolean canAdd(IAddContext context) {
    // check if user wants to add an actor
    if (context.getNewObject() instanceof Actor) {
      // check if user wants to add to a diagram
      if (context.getTargetContainer() instanceof Diagram) {
        return true;
      }
    }

    // Object newObject = context.getNewObject();
    // if (newObject instanceof Actor) {
    // // need to check that the actor belongs to the same CompositeActor as the one associated with the diagram
    // Actor actor = (Actor) newObject;
    // Object topLevelForDiagram = getBusinessObjectForPictogramElement(getDiagram());
    // return (topLevelForDiagram == null || topLevelForDiagram.equals(actor.getContainer()));
    // }
    return false;
  }

  public PictogramElement add(IAddContext context) {
    Actor addedActor = (Actor) context.getNewObject();
    ContainerShape targetContainer = context.getTargetContainer();

    Object topLevelForDiagram = getBusinessObjectForPictogramElement(getDiagram());
    if (topLevelForDiagram == null) {
      link(getDiagram(), addedActor.getContainer());
    }

    int xLocation = context.getX();
    int yLocation = context.getY();

    // CONTAINER SHAPE WITH ROUNDED RECTANGLE
    IPeCreateService peCreateService = Graphiti.getPeCreateService();
    ICreateService createService = Graphiti.getCreateService();
    IGaService gaService = Graphiti.getGaService();
    ContainerShape containerShape = peCreateService.createContainerShape(targetContainer, true);

    // define a default size for the shape
    int width = 100;
    int height = 60;

    Rectangle invisibleRectangle; // need to access it later

    {
      invisibleRectangle = gaService.createInvisibleRectangle(containerShape);
      gaService.setLocationAndSize(invisibleRectangle, xLocation, yLocation, width + 15, height);

      // create and set graphics algorithm
      RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(invisibleRectangle, 5, 5);
      roundedRectangle.setForeground(manageColor(ACTOR_FOREGROUND));
      roundedRectangle.setBackground(manageColor(ACTOR_BACKGROUND));
      roundedRectangle.setLineWidth(2);
      gaService.setLocationAndSize(roundedRectangle, SHAPE_X_OFFSET, 0, width, height);

      // if added Class has no resource we add it to the resource
      // of the diagram
      // in a real scenario the business model would have its own resource
      // if (addedActor.eResource() == null) {
      // getDiagram().eResource().getContents().add(addedActor);
      // }
      // create link and wire it
      link(containerShape, addedActor, "ACTOR");

      // add the actor's icon
      String iconId = (String) context.getProperty("icon");
      if (!StringUtils.isBlank(iconId)) {
        try {
          final Shape imageShape = peCreateService.createShape(containerShape, false);
          final Image image = gaService.createImage(imageShape, iconId);
          addedActor.setIconId(iconId);
          gaService.setLocationAndSize(image, ICON_X_OFFSET, ICON_Y_OFFSET, ICON_SIZE, ICON_SIZE);
        } catch (Exception e) {
          LOGGER.error("Error trying to add actor icon in it shape", e);
        }
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
      gaService.setLocationAndSize(text, SHAPE_X_OFFSET+20, 0, width-25, 20);

      // create link and wire it
      link(shape, addedActor, "ACTOR");

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
    {
      int pIndex = 0;
      for (Parameter param : addedActor.getParameters()) {
        // create shape for text
        Shape shape = peCreateService.createShape(containerShape, false);

        // create and set text graphics algorithm
        String pName = param.getName();
        String pVal = param.getExpression();
        pName = (pName.length() > 12) ? pName.substring(0, 12) : pName;
        pVal = (pVal.length() > 12) ? pVal.substring(0, 12) : pVal;

        Text text = gaService.createText(shape, pName + " : " + pVal);
        // IUiLayoutService uil = GraphitiUi.getUiLayoutService();
        // IDimension dim = uil.calculateTextSize(text.getValue(),
        // text.getFont());

        text.setForeground(manageColor(PARAM_FOREGROUND));
        text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
        // vertical alignment has as default value "center"
        text.setFont(gaService.manageFont(getDiagram(), IGaService.DEFAULT_FONT, 8));
        gaService.setLocationAndSize(text, SHAPE_X_OFFSET + 5, 22 + 15 * pIndex++, width, 20);

        // create link and wire it
        link(shape, param, "PARAMETER");
        Graphiti.getPeService().setPropertyValue(shape, "__BO_VALUE", param.getExpression());
      }
    }

    // SHAPES FOR PORTS
    {
      // add output port anchor
      int pIndex = 0;
      for (Port p : (List<Port>) addedActor.getOutputPorts()) {
        FixPointAnchor anchor = peCreateService.createFixPointAnchor(containerShape);
        anchor.setLocation(createService.createPoint(15 + width, PORT_Y_OFFSET + (pIndex++) * 12));
        anchor.setReferencedGraphicsAlgorithm(invisibleRectangle);
        link(anchor, p, "OUTPUT");
        // assign a rectangle graphics algorithm for the box relative anchor
        // note, that the rectangle is inside the border of the rectangle shape
        final Polygon portShape = gaService.createPlainPolygon(anchor, new int[] { 0, 0, 12, 6, 0, 12 });
        portShape.setForeground(manageColor(PORT_FOREGROUND));
        IColorConstant portColour = p.isMultiPort() ? PORT_BACKGROUND_MULTIPORT : PORT_BACKGROUND_SINGLEPORT;
        portShape.setBackground(manageColor(portColour));
        portShape.setLineWidth(1);
        gaService.setLocationAndSize(portShape, -12, -6, 12, 12);
      }
      pIndex = 0;
      for (Port p : (List<Port>) addedActor.getInputPorts()) {
        FixPointAnchor anchor = peCreateService.createFixPointAnchor(containerShape);
        anchor.setLocation(createService.createPoint(0, PORT_Y_OFFSET + (pIndex++) * 12));
        anchor.setUseAnchorLocationAsConnectionEndpoint(true);
        anchor.setReferencedGraphicsAlgorithm(invisibleRectangle);
        link(anchor, p, "INPUT");
        // assign a rectangle graphics algorithm for the box relative anchor
        // note, that the rectangle is inside the border of the rectangle shape
        final Polygon portShape = gaService.createPlainPolygon(anchor, new int[] { 0, 0, 12, 6, 0, 12 });
        portShape.setForeground(manageColor(PORT_FOREGROUND));
        IColorConstant portColour = p.isMultiPort() ? PORT_BACKGROUND_MULTIPORT : PORT_BACKGROUND_SINGLEPORT;
        portShape.setBackground(manageColor(portColour));
        portShape.setLineWidth(1);
        gaService.setLocationAndSize(portShape, 0, -6, 12, 12);
      }

      layoutPictogramElement(containerShape);
    }

    return containerShape;
  }
}