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
package org.eclipse.triquetrum.workflow.editor.actions;

import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.ui.internal.parts.DiagramEditPart;
import org.eclipse.graphiti.ui.platform.GraphitiShapeEditPart;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService.StartMode;
import org.eclipse.triquetrum.workflow.editor.ImageConstants;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramTypeProvider;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.triquetrum.workflow.editor.util.EclipseUtils;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.NamedObj;

public class RunAction extends Action {

  public RunAction() {
    setId(getClass().getName());
    setText("Run Triq workflow");
    ImageDescriptor runImg = GraphitiUi.getImageService().getImageDescriptorForId(TriqDiagramTypeProvider.ID, ImageConstants.IMG_RUN_WORKFLOW);
    setImageDescriptor(runImg);
  }

  @Override
  public void run() {
    super.run();
    try {
      WorkflowExecutionService executionService = TriqEditorPlugin.getDefault().getWorkflowExecutionService();
      if (executionService != null) {
        CompositeActor selection = null;
        try {
          // FIXME we assume that the diagram/editor is the selected gui element, or an entity in there. Is this a robust assumption?
          selection = getSelectedModel();
          if (selection != null) {
            ptolemy.actor.CompositeActor ptolemyModel = (ptolemy.actor.CompositeActor) selection.getWrappedObject();
            executionService.start(StartMode.RUN, ptolemyModel, null, null, null);
          } else {
            // TODO ignore or add error handling?
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        // TODO add error handling for configuration problem with missing execution service
      }
    } catch (Exception e) {
      // TODO add error handling (e.g. eclipse error log? error popup?)
    }
  }

  @SuppressWarnings("restriction")
  private CompositeActor getSelectedModel() {
    NamedObj result = null;
    final ISelection sel = EclipseUtils.getPage().getSelection();
    if (sel != null && sel instanceof IStructuredSelection) {
      final IStructuredSelection str = (IStructuredSelection) sel;
      Object res = str.getFirstElement();
      result = getModelObjectForSelection(res);
      if(result == null && res instanceof DiagramEditPart) {
        // FIXME a temp hack here to get the ptolemy model from the Diagram
        // don't know how to get the linked emf business model root from a Diagram, as the linkmodel doesn't seem to contain that one?
        // so we pass via a contained entity (i.e. via it's editpart)
        List<?> diagramChildren = ((DiagramEditPart) res).getChildren();
        if (!diagramChildren.isEmpty()) {
          result = getModelObjectForSelection(diagramChildren.get(0));
        }
      }
    }
    result = (result!=null) ? result.topLevel() : null;
    return (CompositeActor) result;
  }

  private NamedObj getModelObjectForSelection(Object res) {
    NamedObj result = null;
    if (res instanceof GraphitiShapeEditPart) {
      GraphitiShapeEditPart editPart = (GraphitiShapeEditPart) res;
      IFeatureProvider fp = editPart.getFeatureProvider();
      result = (NamedObj) fp.getBusinessObjectForPictogramElement(editPart.getPictogramElement());
    }
    return result;
  }

}
