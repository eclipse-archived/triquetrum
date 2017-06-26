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

import org.apache.commons.lang.StringUtils;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.model.Director;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectorAddFeature extends AbstractAddShapeFeature {

  private final static Logger LOGGER = LoggerFactory.getLogger(DirectorAddFeature.class);

  public static final int SHAPE_X_OFFSET = 0;
  private static final int ICON_X_OFFSET = SHAPE_X_OFFSET + 3;
  private static final int ICON_Y_OFFSET = 3;
  private static final int ICON_SIZE = 16;

  private static final IColorConstant DIRECTOR_NAME_FOREGROUND = IColorConstant.BLACK;

  private static final IColorConstant DIRECTOR_FOREGROUND = new ColorConstant(98, 131, 167);
  private static final IColorConstant DIRECTOR_BACKGROUND = IColorConstant.ORANGE;

  public DirectorAddFeature(IFeatureProvider fp) {
    super(fp);
  }

  protected void link(PictogramElement pe, Object businessObject, BoCategory category) {
    super.link(pe, businessObject);
    // add property on the graphical model element, identifying the associated Triq model element
    // so we can easily distinguish and identify them later on for updates etc
    category.storeIn(pe);
    if (businessObject instanceof NamedObj) {
      Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.BO_NAME, ((NamedObj) businessObject).getName());
    }
    Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.BO_CLASS, businessObject.getClass().getName());
  }

  @Override
  public boolean canAdd(IAddContext context) {
    // check if user wants to add an director
    Object newObject = context.getNewObject();
    if (newObject instanceof Director) {
      // need to check that the director belongs to the same CompositeActor as the one associated with the diagram
      Director director = (Director) newObject;
      Object topLevelForDiagram = getBusinessObjectForPictogramElement(getDiagram());
      return (topLevelForDiagram == null || topLevelForDiagram.equals(director.getContainer()));
    }
    return false;
  }

  @Override
  public PictogramElement add(IAddContext context) {
    Director addedDirector = (Director) context.getNewObject();
    ContainerShape targetContainer = context.getTargetContainer();

    Object topLevelForDiagram = getBusinessObjectForPictogramElement(getDiagram());
    if (topLevelForDiagram == null) {
      link(getDiagram(), addedDirector.getContainer());
    }

    int xLocation = context.getX();
    int yLocation = context.getY();

    // CONTAINER SHAPE WITH ROUNDED RECTANGLE
    IPeCreateService peCreateService = Graphiti.getPeCreateService();
    ContainerShape containerShape = peCreateService.createContainerShape(targetContainer, true);

    // define a default size for the shape
    int width = 100;
    int height = 60;
    IGaService gaService = Graphiti.getGaService();

    Rectangle invisibleRectangle; // need to access it later

    {
      invisibleRectangle = gaService.createInvisibleRectangle(containerShape);
      gaService.setLocationAndSize(invisibleRectangle, xLocation, yLocation, width, height);

      // create and set graphics algorithm
      RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(invisibleRectangle, 5, 5);
      roundedRectangle.setForeground(manageColor(DIRECTOR_FOREGROUND));
      roundedRectangle.setBackground(manageColor(DIRECTOR_BACKGROUND));
      roundedRectangle.setLineWidth(2);
      gaService.setLocationAndSize(roundedRectangle, SHAPE_X_OFFSET, 0, width, height);

      // create link and wire it
      link(containerShape, addedDirector, BoCategory.Director);

      // add the actor's icon
      String iconId = (String) context.getProperty("icon");
      if (!StringUtils.isBlank(iconId)) {
        try {
          final Shape imageShape = peCreateService.createShape(containerShape, false);
          final Image image = gaService.createImage(imageShape, iconId);
          gaService.setLocationAndSize(image, ICON_X_OFFSET, ICON_Y_OFFSET, ICON_SIZE, ICON_SIZE);
        } catch (Exception e) {
          LOGGER.error("Error trying to add director icon in it shape", e);
        }
      }
    }

    // SHAPE WITH LINE
    {
      // create shape for line
      Shape shape = peCreateService.createShape(containerShape, false);

      // create and set graphics algorithm
      Polyline polyline = gaService.createPolyline(shape, new int[] { SHAPE_X_OFFSET, 20, SHAPE_X_OFFSET + width, 20 });
      polyline.setForeground(manageColor(DIRECTOR_FOREGROUND));
      polyline.setLineWidth(2);
    }

    // SHAPE WITH director name as TEXT
    {
      // create shape for text
      Shape shape = peCreateService.createShape(containerShape, false);

      // create and set text graphics algorithm
      Text text = gaService.createText(shape, addedDirector.getName());
      text.setForeground(manageColor(DIRECTOR_NAME_FOREGROUND));
      text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
      // vertical alignment has as default value "center"
      text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
      gaService.setLocationAndSize(text, SHAPE_X_OFFSET + 20, 0, width - 25, 20);

      // create link and wire it
      link(shape, addedDirector, BoCategory.Director);
    }

    // don't show director params in graphical model

    layoutPictogramElement(containerShape);
    return containerShape;
  }
}