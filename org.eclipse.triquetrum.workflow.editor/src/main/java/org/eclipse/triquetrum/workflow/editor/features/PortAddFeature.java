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

import java.util.Map;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.Port;

/**
 * This feature allows to add ports on a CompositeActor, e.g. when defining submodels.
 *
 */
public class PortAddFeature extends AbstractAddShapeFeature {

  // private static final int SHAPE_X_OFFSET = 2;
  //
  private static final IColorConstant PORT_BACKGROUND = IColorConstant.BLACK;
  // private static final IColorConstant ANCHOR_BACKGROUND = IColorConstant.RED;

  public PortAddFeature(IFeatureProvider fp) {
    super(fp);
  }

  protected void link(PictogramElement pe, Port businessObject, BoCategory category) {
    super.link(pe, businessObject);
    // add property on the graphical model element, identifying the associated triq model element
    // so we can easily distinguish and identify them later on for updates etc
    category.storeIn(pe);
    Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.BO_NAME, businessObject.getName());
    Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.BO_CLASS, businessObject.getClass().getName());
  }

  @Override
  public boolean canAdd(IAddContext context) {
    // check if user wants to add a port
    if (context.getNewObject() instanceof Port) {
      Object parentObject = getBusinessObjectForPictogramElement(context.getTargetContainer());
      return (parentObject instanceof CompositeActor);
    }
    return false;
  }

  @Override
  public PictogramElement add(IAddContext context) {
    Port addedPort = (Port) context.getNewObject();
    ContainerShape targetContainer = context.getTargetContainer();

    int xLocation = context.getX();
    int yLocation = context.getY();

    // CONTAINER SHAPE WITH ROUNDED RECTANGLE
    IPeCreateService peCreateService = Graphiti.getPeCreateService();
    ICreateService createService = Graphiti.getCreateService();
    IGaService gaService = Graphiti.getGaService();
    ContainerShape containerShape = peCreateService.createContainerShape(targetContainer, true);

    // define a default size for the shape
    int width = 20;
    int height = 20;

    Rectangle invisibleRectangle; // need to access it later
    {
      invisibleRectangle = gaService.createInvisibleRectangle(containerShape);
      gaService.setLocationAndSize(invisibleRectangle, xLocation, yLocation, width, height);

      // add anchors at the right side of the port, depending on it being input or output
      // FIXME for the moment a port can not be both input and output
      FixPointAnchor anchor = peCreateService.createFixPointAnchor(containerShape);
      if (addedPort.isInput()) {
        anchor.setLocation(createService.createPoint(18, 0));
        anchor.setReferencedGraphicsAlgorithm(invisibleRectangle);
        final Polyline rectangle = gaService.createPlainPolyline(anchor, new int[] { 0, 0, 0, 20 });
        rectangle.setLineStyle(LineStyle.DASH);
        gaService.setLocationAndSize(rectangle, 0, 0, 1, 20);
        link(anchor, addedPort, BoCategory.Input);
        // create and set graphics algorithm (we would normally call this the shape of the thing ;-) )
        int xy[] = new int[] { 10, 0, 20, 10, 10, 20, 10, 15, 0, 15, 0, 5, 10, 5 };
        Polygon portShape = gaService.createPolygon(invisibleRectangle, xy);
        portShape.setForeground(manageColor(PORT_BACKGROUND));
        gaService.setLocationAndSize(portShape, 0, 0, 20, 20);
      } else {
        anchor.setLocation(createService.createPoint(2, 0));
        anchor.setReferencedGraphicsAlgorithm(invisibleRectangle);
        final Polyline rectangle = gaService.createPlainPolyline(anchor, new int[] { 0, 0, 0, 20 });
        rectangle.setLineStyle(LineStyle.DASH);
        gaService.setLocationAndSize(rectangle, 0, 0, 1, 20);
        link(anchor, addedPort, BoCategory.Output);
        anchor.setVisible(true);
        // create and set graphics algorithm (we would normally call this the shape of the thing ;-) )
        int xy[] = new int[] { 10, 0, 20, 10, 10, 20, 10, 15, 0, 15, 0, 5, 10, 5 };
        Polygon portShape = gaService.createPolygon(invisibleRectangle, xy);
        portShape.setForeground(manageColor(PORT_BACKGROUND));
        gaService.setLocationAndSize(portShape, 0, 0, 20, 20);
      }

      // TODO find a way to get the full name from our Triq NamedObj,
      // then we don't need to depend on the presence of the wrapped object.
      Map<String, Anchor> anchorMap = (Map<String, Anchor>) context.getProperty(FeatureConstants.ANCHORMAP_NAME);
      if (anchorMap != null && addedPort.getWrappedObject() != null) {
        anchorMap.put(addedPort.getWrappedObject().getFullName(), anchor);
      }
      link(containerShape, addedPort, BoCategory.Port);
    }

    layoutPictogramElement(containerShape);

    return containerShape;
  }
}