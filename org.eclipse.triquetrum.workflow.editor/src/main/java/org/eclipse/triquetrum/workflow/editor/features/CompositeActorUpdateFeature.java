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
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.NamedObj;

public class CompositeActorUpdateFeature extends AbstractUpdateFeature {
  public CompositeActorUpdateFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canUpdate(IUpdateContext context) {
    BoCategory boCategory = BoCategory.retrieveFrom(context.getPictogramElement());
    return (BoCategory.CompositeActor.equals(boCategory));
  }

  /**
   * The indirect update handling of graphiti means that complex diff-checking is needed here between the stuff that is currently maintained in the graphical
   * model, and the actual configuration&status of an actor.
   * <p>
   * This is different from ptolemy's Vergil where updates are done through editor components and/or parameter configuration dialogs and are then applied
   * immediately to the graphical model through MOML change requests, GEF commands etc.
   * </p>
   * <p>
   * Currently following items are checked :
   * <ul>
   * <li>Actor's name</li>
   * <li>Parameter values</li>
   * </ul>
   * Changed port names are assumed to be handled by a separate PortUpdateFeature (TBD).
   * </p>
   */
  @Override
  public IReason updateNeeded(IUpdateContext context) {
    // retrieve name from business model
    String actorName = null;
    boolean actorNameChanged = false;

    PictogramElement pictogramElement = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    if (bo instanceof CompositeActor && pictogramElement instanceof ContainerShape) {
      CompositeActor actor = (CompositeActor) bo;
      ContainerShape cs = (ContainerShape) pictogramElement;

      if (!EditorUtils.containsExternallyDefinedFigure(pictogramElement)) {
        actorName = actor.getName();

        for (Shape shape : cs.getChildren()) {
          Graphiti.getPeService().getPropertyValue(shape, FeatureConstants.BO_NAME);
          BoCategory boCategory = BoCategory.retrieveFrom(shape);
          if (shape.getGraphicsAlgorithm() instanceof Text) {
            Text text = (Text) shape.getGraphicsAlgorithm();
            if (BoCategory.CompositeActor.equals(boCategory)) {
              // it's the text field with the name of the actor
              String actorNameInGraph = text.getValue();
              actorNameChanged = !actorName.equals(actorNameInGraph);
            }
          }
        }
      }
    }
    // build diff result and store some useful info in the update context
    if (actorNameChanged) {
      StringBuilder diffResultBldr = new StringBuilder();
      diffResultBldr.append("Actor name changed; ");
      context.putProperty("ACTORNAME_CHANGED", "true");
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
    if ((pe instanceof ContainerShape) && (bo instanceof CompositeActor)) {
      ContainerShape cs = (ContainerShape) pe;
      CompositeActor actor = (CompositeActor) bo;

      for (Shape shape : cs.getChildren()) {
        BoCategory boCategory = BoCategory.retrieveFrom(shape);
        if (BoCategory.CompositeActor.equals(boCategory) && shape.getGraphicsAlgorithm() instanceof Text) {
          Text text = (Text) shape.getGraphicsAlgorithm();
          text.setValue(actor.getName());
          result = true;
          Graphiti.getPeService().setPropertyValue(shape, FeatureConstants.BO_NAME, actor.getName());
        }
      }
    }
    return result;
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
}
