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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.triquetrum.workflow.model.CompositeEntity;
import org.eclipse.triquetrum.workflow.model.NamedObj;

public class ModelElementNameDirectEditFeature extends AbstractDirectEditingFeature {

  public ModelElementNameDirectEditFeature(IFeatureProvider fp) {
    super(fp);
  }

  public int getEditingType() {
    return TYPE_TEXT;
  }

  @Override
  public boolean canDirectEdit(IDirectEditingContext context) {
    PictogramElement pe = context.getPictogramElement();
    GraphicsAlgorithm ga = context.getGraphicsAlgorithm();
    String boCategory = Graphiti.getPeService().getPropertyValue(pe, "__BO_CATEGORY");
    // The name of an actor (or other model element) is the only
    // Text element that is linked to it as its business object
    return (("ACTOR".equals(boCategory) || "DIRECTOR".equals(boCategory) || ("PARAMETER".equals(boCategory)) || ("PORT".equals(boCategory)))
        && (ga instanceof Text));
  }

  public String getInitialValue(IDirectEditingContext context) {
    // return the current name of the EClass
    PictogramElement pe = context.getPictogramElement();
    NamedObj bo = (NamedObj) getBusinessObjectForPictogramElement(pe);
    return bo.getName();
  }

  @Override
  public String checkValueValid(String value, IDirectEditingContext context) {
    PictogramElement pe = context.getPictogramElement();
    NamedObj bo = (NamedObj) getBusinessObjectForPictogramElement(pe);
    if (!bo.getName().equals(value)) {
      if (value.length() < 1)
        return "The name should be non-empty";
      if (value.contains("."))
        return "Dots are not allowed in names.";
      if (value.contains("\n"))
        return "Line breakes are not allowed in names.";

      if (bo.getContainer() instanceof CompositeEntity && ((CompositeEntity) bo.getContainer()).getChild(value) != null) {
        return "Duplicate name";
      }
    }
    // null means, that the value is valid
    return null;
  }

  public void setValue(String value, IDirectEditingContext context) {
    // set the new name for the EClass
    PictogramElement pe = context.getPictogramElement();
    NamedObj bo = (NamedObj) getBusinessObjectForPictogramElement(pe);
    bo.setName(value);

    // Explicitly update the shape to display the new value in the diagram
    // Note, that this might not be necessary in future versions of Graphiti
    // (currently in discussion)

    // we know, that pe is the Shape of the Text, so its container is the
    // main shape of the actor
    updatePictogramElement(((Shape) pe).getContainer());
  }
}