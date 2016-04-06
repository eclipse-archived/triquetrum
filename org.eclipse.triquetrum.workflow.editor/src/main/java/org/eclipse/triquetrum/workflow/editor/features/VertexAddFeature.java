/*******************************************************************************
 * Copyright (c) 2016 iSencia Belgium NV.
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
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.triquetrum.workflow.editor.BoCategories;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Vertex;

/**
 * This feature allows to add a Vertex on a CompositeActor,
 * that can then be used to draw multi-link relations/connections.
 *
 */
public class VertexAddFeature extends AbstractAddShapeFeature {

  private static final IColorConstant VERTEX_BACKGROUND = IColorConstant.BLACK;

  public VertexAddFeature(IFeatureProvider fp) {
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
    // check if user wants to add a port
    if (context.getNewObject() instanceof Vertex) {
      // check if user wants to add to a diagram
      if (context.getTargetContainer() instanceof Diagram) {
        return true;
      }
    }
    return false;
  }

  public PictogramElement add(IAddContext context) {
    Vertex addedVertex = (Vertex) context.getNewObject();
    ContainerShape targetContainer = context.getTargetContainer();

    int xLocation = context.getX();
    int yLocation = context.getY();

    IPeCreateService peCreateService = Graphiti.getPeCreateService();
    IGaService gaService = Graphiti.getGaService();
    ContainerShape containerShape = peCreateService.createContainerShape(targetContainer, true);


    int xy[] = new int[] { 6,0, 12,10, 6,20, 0,10 };
    Polygon diamond = gaService.createPolygon(containerShape, xy);
    diamond.setForeground(manageColor(VERTEX_BACKGROUND));
    diamond.setBackground(manageColor(VERTEX_BACKGROUND));
    gaService.setLocationAndSize(diamond, xLocation, yLocation, 12, 30);

    // add an anchor in the Vertex
    // TODO check if we need multiple anchors?
    ChopboxAnchor anchor = peCreateService.createChopboxAnchor(containerShape);
    anchor.setReferencedGraphicsAlgorithm(diamond);
    link(anchor, addedVertex, BoCategories.RELATION);
    link(containerShape, addedVertex, BoCategories.RELATION);

    layoutPictogramElement(containerShape);

    return containerShape;
  }
}