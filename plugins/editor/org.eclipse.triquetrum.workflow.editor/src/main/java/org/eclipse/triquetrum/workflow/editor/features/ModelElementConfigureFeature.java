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

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.ImageConstants;
import org.eclipse.triquetrum.workflow.editor.TriqFeatureProvider;
import org.eclipse.triquetrum.workflow.editor.views.NamedObjDialog;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.rcp.EclipseUtils;

public class ModelElementConfigureFeature extends AbstractCustomFeature {

  public ModelElementConfigureFeature(TriqFeatureProvider fp) {
    super(fp);
  }

  @Override
  public TriqFeatureProvider getFeatureProvider() {
    return (TriqFeatureProvider) super.getFeatureProvider();
  }

  @Override
  public String getName() {
    return "Configure";
  }

  @Override
  public String getDescription() {
    return "Configure a model element";
  }

  @Override
  public String getImageId() {
    return ImageConstants.IMG_CONFIGURE;
  }

  @Override
  public boolean canExecute(ICustomContext context) {
    boolean ret = false;
    PictogramElement pe = context.getInnerPictogramElement();
    if (pe == null) {
      PictogramElement[] pes = context.getPictogramElements();
      if (pes != null && pes.length == 1) {
        pe = pes[0];
      }
    }
    // prevent double click action on actor's name's Text field
    if (pe != null) {
      BoCategory boCategory = BoCategory.retrieveFrom(pe);
      ret = ((BoCategory.CompositeActor.equals(boCategory) || BoCategory.Actor.equals(boCategory) || BoCategory.Director.equals(boCategory) || (BoCategory.Annotation.equals(boCategory))
          || (BoCategory.Port.equals(boCategory))) && !(pe.getGraphicsAlgorithm() instanceof Text)) || BoCategory.Parameter.equals(boCategory);
    }
    return ret;
  }

  @Override
  public void execute(ICustomContext context) {
    PictogramElement[] pes = context.getPictogramElements();
    if (pes != null && pes.length == 1) {
      Object bo = getBusinessObjectForPictogramElement(pes[0]);
      if (bo instanceof NamedObj) {
        NamedObj modelElement = (NamedObj) bo;
        Shell shell = EclipseUtils.getActivePage().getActivePart().getSite().getShell();
        NamedObjDialog dialog = new NamedObjDialog(shell, modelElement);
        dialog.open();
        if (dialog.getReturnCode() != Window.OK) {
          throw new OperationCanceledException();
        }
      }
    }
  }

  @Override
  public boolean hasDoneChanges() {
    return getDiagram().eResource().isModified();
//    return ((TriqDiagramEditor) getDiagramBehavior().getDiagramContainer()).getCommandStack().isDirty();
  }
}