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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.model.Director;

public class DirectorUpdateFeature extends AbstractUpdateFeature {

  public DirectorUpdateFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canUpdate(IUpdateContext context) {
    BoCategory boCategory = BoCategory.retrieveFrom(context.getPictogramElement());
    return (BoCategory.Director.equals(boCategory));
  }

  @Override
  public IReason updateNeeded(IUpdateContext context) {
    // retrieve name from business model
    String elemName = null;
    boolean elemNameChanged = false;
    PictogramElement pictogramElement = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    if (bo instanceof Director && pictogramElement instanceof ContainerShape) {
      Director director = (Director) bo;
      ContainerShape cs = (ContainerShape) pictogramElement;

      elemName = director.getName();

      for (Shape shape : cs.getChildren()) {
        BoCategory boCategory = BoCategory.retrieveFrom(shape);
        if (shape.getGraphicsAlgorithm() instanceof Text) {
          Text text = (Text) shape.getGraphicsAlgorithm();
          if (BoCategory.Director.equals(boCategory)) {
            // it's the text field with the name of the actor
            String actorNameInGraph = text.getValue();
            elemNameChanged = !elemName.equals(actorNameInGraph);
          }
        }
      }
    }
    // build diff result and store some useful info in the update context
    if (elemNameChanged) {
      StringBuilder diffResultBldr = new StringBuilder();
      if (elemNameChanged) {
        diffResultBldr.append("Director name changed; ");
        context.putProperty("DIRECTORNAME_CHANGED", "true");
      }
      return Reason.createTrueReason(diffResultBldr.toString());
    } else {
      return Reason.createFalseReason();
    }
  }

  @Override
  public boolean update(IUpdateContext context) {
    boolean result = false;
    PictogramElement pe = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pe);
    if ((pe instanceof ContainerShape) && (bo instanceof Director)) {
      ContainerShape cs = (ContainerShape) pe;
      Director director = (Director) bo;

      for (Shape shape : cs.getChildren()) {
        BoCategory boCategory = BoCategory.retrieveFrom(shape);
        if (BoCategory.Director.equals(boCategory)) {
          Text text = (Text) shape.getGraphicsAlgorithm();
          text.setValue(director.getName());
          result = true;
          Graphiti.getPeService().setPropertyValue(shape, FeatureConstants.BO_NAME, director.getName());
        }
      }
    }
    return result;
  }

}
