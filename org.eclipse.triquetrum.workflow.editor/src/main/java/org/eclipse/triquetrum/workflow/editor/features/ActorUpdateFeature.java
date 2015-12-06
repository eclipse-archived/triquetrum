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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.triquetrum.workflow.model.Actor;
import org.eclipse.triquetrum.workflow.model.Parameter;
import org.eclipse.triquetrum.workflow.model.Port;

/**
 * TODO support changing port counts for dynamic port actors
 *
 */
public class ActorUpdateFeature extends AbstractUpdateFeature {

  public ActorUpdateFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canUpdate(IUpdateContext context) {
    String boCategory = Graphiti.getPeService().getPropertyValue(context.getPictogramElement(), "__BO_CATEGORY");
    return ("ACTOR".equals(boCategory));
  }

  /**
   * The indirect update handling of graphiti means that complex diff-checking is needed here between the stuff that is currently maintained in the graphical
   * model, and the actual configuration&status of an actor.
   * <p>
   * This is different from ptolemy's Vergil where updates are done through editor components and/or parameter
   * configuration dialogs and are then applied immediately to the graphical model through MOML change requests, GEF commands etc.
   * </p>
   * <p>
   * Currently following items are checked :
   * <ul>
   * <li>Actor's name</li>
   * <li>Count of input and output ports</li>
   * <li>Count of parameters</li>
   * <li>Parameter values</li>
   * </ul>
   * Changed port names are assumed to be handled by a separate PortUpdateFeature (TBD).
   * </p>
   */
  @Override
  public IReason updateNeeded(IUpdateContext context) {
    // retrieve name from business model
    String actorName = null;
    int inputPortCount = 0;
    int outputPortCount = 0;
    int parameterCount = 0;
    boolean actorNameChanged = false;
    List<String> changedParameters = new ArrayList<String>();

    PictogramElement pictogramElement = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    if (bo instanceof Actor && pictogramElement instanceof ContainerShape) {
      Actor actor = (Actor) bo;
      ContainerShape cs = (ContainerShape) pictogramElement;

      actorName = actor.getName();
      inputPortCount = actor.getInputPorts().size();
      outputPortCount = actor.getOutputPorts().size();
      parameterCount = actor.getParameters().size();

      for (Shape shape : cs.getChildren()) {
        String boName = Graphiti.getPeService().getPropertyValue(shape, "__BO_NAME");
        // String boClass = Graphiti.getPeService().getPropertyValue(shape, "__BO_CLASS");
        String boCategory = Graphiti.getPeService().getPropertyValue(shape, "__BO_CATEGORY");
        if (shape.getGraphicsAlgorithm() instanceof Text) {
          Text text = (Text) shape.getGraphicsAlgorithm();
          if ("ACTOR".equalsIgnoreCase(boCategory)) {
            // it's the text field with the name of the actor
            String actorNameInGraph = text.getValue();
            actorNameChanged = !actorName.equals(actorNameInGraph);
          } else if ("PARAMETER".equalsIgnoreCase(boCategory)) {
            // parameters can not change name, only the value can change
            String boValue = Graphiti.getPeService().getPropertyValue(shape, "__BO_VALUE");
            Parameter p = (Parameter) actor.getChild(boName);
            if (p != null && !p.getExpression().equals(boValue)) {
              changedParameters.add(boName);
            }
            parameterCount--;
          }
        }
      }
      for (Anchor anchor : cs.getAnchors()) {
        String boCategory = Graphiti.getPeService().getPropertyValue(anchor, "__BO_CATEGORY");
        if ("INPUT".equals(boCategory)) {
          inputPortCount--;
        }
        if ("OUTPUT".equals(boCategory)) {
          outputPortCount--;
        }
      }
    }
    // build diff result and store some useful info in the update context
    if (actorNameChanged || (changedParameters.size() > 0) || (parameterCount != 0) || (inputPortCount != 0) || (outputPortCount != 0)) {
      StringBuilder diffResultBldr = new StringBuilder();
      if(actorNameChanged) {
        diffResultBldr.append("Actor name changed; ");
        context.putProperty("ACTORNAME_CHANGED", "true");
      }
      if(changedParameters.size()>0 || (parameterCount != 0)) {
        diffResultBldr.append("Parameter change; ");
        context.putProperty("PARAMETER_CHANGED", changedParameters);
      }
      if(inputPortCount!=0 || outputPortCount!=0) {
        diffResultBldr.append("Port change; ");
        context.putProperty("PORTS_CHANGED", "true");
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
    if((pe instanceof ContainerShape) && (bo instanceof Actor)) {
      ContainerShape cs = (ContainerShape) pe;
      Actor addedActor = (Actor) bo;
      
      List<Port> inputPortsInShape = new ArrayList<Port>();
      List<Port> outputPortsInShape = new ArrayList<Port>();
      for (Shape shape : cs.getChildren()) {
        String boCategory = Graphiti.getPeService().getPropertyValue(shape, "__BO_CATEGORY");
        if("ACTOR".equals(boCategory)) {
          Text text = (Text) shape.getGraphicsAlgorithm();
          text.setValue(addedActor.getName());
          result = true;
          Graphiti.getPeService().setPropertyValue(shape, "__BO_NAME",addedActor.getName());
        } else if("PARAMETER".equals(boCategory)) {
          Text text = (Text) shape.getGraphicsAlgorithm();
          Parameter param = (Parameter) getBusinessObjectForPictogramElement(shape);
          String pName = param.getName();
          String pVal = param.getExpression();
          pName = (pName.length()>12) ? pName.substring(0, 12) : pName;
          pVal = (pVal.length()>12) ? pVal.substring(0, 12) : pVal;
          text.setValue(pName + " : " + pVal);
          Graphiti.getPeService().setPropertyValue(shape, "__BO_VALUE",param.getExpression());
          result = true;
        } else if("INPUT".equals(boCategory)) {
          Port p = (Port) getBusinessObjectForPictogramElement(shape);
          if(p!=null) {
            inputPortsInShape.add(p);
          }
        } else if("OUTPUT".equals(boCategory)) {
          Port p = (Port) getBusinessObjectForPictogramElement(shape);
          if(p!=null) {
            outputPortsInShape.add(p);
          }
        }
      }
    }
    return result;
  }

}
