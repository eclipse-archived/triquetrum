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

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramEditor;
import org.eclipse.triquetrum.workflow.editor.TriqFeatureProvider;
import org.eclipse.triquetrum.workflow.editor.util.EclipseUtils;
import org.eclipse.triquetrum.workflow.editor.views.NamedObjDialog;
import org.eclipse.triquetrum.workflow.model.NamedObj;

public class ActorConfigureFeature extends AbstractCustomFeature {

  public ActorConfigureFeature(TriqFeatureProvider fp) {
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
    return "Configure an actor or director";
  }

  @Override
  public boolean canExecute(ICustomContext context) {
    boolean ret = false;
    PictogramElement pe = context.getInnerPictogramElement();
    GraphicsAlgorithm ga = context.getInnerGraphicsAlgorithm();
    // prevent double click action on actor's name's Text field
    if (pe != null) {
      String boCategory = Graphiti.getPeService().getPropertyValue(pe, "__BO_CATEGORY");
      if ("ACTOR".equals(boCategory) || "DIRECTOR".equals(boCategory)) {
        ret = !(ga instanceof Text);
      } else {
        ret = ("PARAMETER".equals(boCategory)) || ("PORT".equals(boCategory));
      }
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
        if(dialog.getReturnCode()!=NamedObjDialog.OK) {
          throw new OperationCanceledException();
        }
      }
    }
  }

  @Override
  public boolean hasDoneChanges() {
    return ((TriqDiagramEditor) getDiagramBehavior().getDiagramContainer()).getCommandStack().isDirty();
  }
}