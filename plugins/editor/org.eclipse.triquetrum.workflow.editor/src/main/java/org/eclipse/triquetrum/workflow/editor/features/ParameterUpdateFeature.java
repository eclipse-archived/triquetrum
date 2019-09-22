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
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.Parameter;

/**
 *
 */
public class ParameterUpdateFeature extends AbstractUpdateFeature {

  public ParameterUpdateFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canUpdate(IUpdateContext context) {
    BoCategory boCategory = BoCategory.retrieveFrom(context.getPictogramElement());
    return (BoCategory.Parameter.equals(boCategory));
  }

  @Override
  public IReason updateNeeded(IUpdateContext context) {
    PictogramElement pictogramElement = context.getPictogramElement();
    if (EditorUtils.containsExternallyDefinedFigure(pictogramElement)) {
      return Reason.createFalseReason();
    } else {
      Object bo = getBusinessObjectForPictogramElement(pictogramElement);
      boolean parameterNameChanged = false;
      boolean parameterValueChanged = false;
      Parameter p = null;
      if (bo instanceof Parameter && pictogramElement instanceof Shape) {
        p = (Parameter) bo;
        Shape shape = (Shape) pictogramElement;
        BoCategory boCategory = BoCategory.retrieveFrom(shape);
        if (BoCategory.Parameter.equals(boCategory)) {
          String boName = Graphiti.getPeService().getPropertyValue(shape, FeatureConstants.BO_NAME);
          String boValue = Graphiti.getPeService().getPropertyValue(shape, "__BO_VALUE");
          parameterValueChanged = (p.getExpression() != null && !p.getExpression().equals(boValue));
          parameterNameChanged = (!p.getName().equals(boName));
        }
      }
      if (parameterValueChanged || parameterNameChanged) {
        StringBuilder diffResultBldr = new StringBuilder();
        if(parameterNameChanged) {
          diffResultBldr.append("Parameter name changed; ");
          context.putProperty("PARAMETERNAME_CHANGED", "true");
        }
        if(parameterValueChanged) {
          diffResultBldr.append("Parameter value changed; ");
          context.putProperty("PARAMETERVALUE_CHANGED", "true");
        }
        return Reason.createTrueReason(diffResultBldr.toString());
      } else {
        return Reason.createFalseReason();
      }
    }
  }

  @Override
  public boolean update(IUpdateContext context) {
    boolean result = false;
    PictogramElement pictogramElement = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    if (bo instanceof Parameter && pictogramElement instanceof Shape) {
      Parameter param = (Parameter) bo;
      Shape shape = (Shape) pictogramElement;
      BoCategory boCategory = BoCategory.retrieveFrom(shape);
      if (BoCategory.Parameter.equals(boCategory)) {
        Text text = null;
        if (shape.getGraphicsAlgorithm() instanceof Text) {
          text = (Text) shape.getGraphicsAlgorithm();
        } else if (shape instanceof ContainerShape) {
          for (Shape childShape : ((ContainerShape) shape).getChildren()) {
            boCategory = BoCategory.retrieveFrom(childShape);
            if (BoCategory.Parameter.equals(boCategory)) {
              if (childShape.getGraphicsAlgorithm() instanceof Text) {
                text = (Text) childShape.getGraphicsAlgorithm();
                break;
              }
            }
          }
        }
        if (text != null) {
          String pName = param.getName();
          String pVal = param.getExpression();
          pVal = pVal != null ? pVal : "";
          text.setValue(pName + " : " + pVal);
          Graphiti.getPeService().setPropertyValue(shape, "__BO_VALUE", param.getExpression());
          Graphiti.getPeService().setPropertyValue(shape, FeatureConstants.BO_NAME, param.getName());
          result = true;
        } else {
          // TODO report an error here?
        }
      }
    }
    return result;
  }
}
