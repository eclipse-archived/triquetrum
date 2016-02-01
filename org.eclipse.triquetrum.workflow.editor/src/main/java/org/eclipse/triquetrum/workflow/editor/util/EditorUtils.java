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
package org.eclipse.triquetrum.workflow.editor.util;

import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.parts.DiagramEditPart;
import org.eclipse.graphiti.ui.platform.GraphitiShapeEditPart;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.CompositeEntity;
import org.eclipse.triquetrum.workflow.model.NamedObj;

public class EditorUtils {

  /**
   * Creates a unique name for a new model element, in its container.
   * The relatedObj should be either the container or a sibling of the to-be-created element,
   * i.e. it should be in the same container as the new element.
   * <p>
   * This method delegates to ptolemy.kernel.CompositeEntity.uniqueName(String)
   * </p>
   * @param relatedObj a model element in the same container as the new element, or the container itself.
   * @param prefix a prefix for the generated unique name.
   * @return a unique name constructed from the prefix.
   */
  public static String buildUniqueName(NamedObj relatedObj, String prefix) {
    NamedObj container = relatedObj;
    while((container != null) && !(container instanceof CompositeEntity)) {
      container = container.getContainer();
    }
    if(container==null) {
      return prefix;
    } else {
      return ((ptolemy.kernel.CompositeEntity)container.getWrappedObject()).uniqueName(prefix);
    }
  }

  /**
   * Find the model from the selected editor part and its contained diagram.
   * @return the model from the selected diagram editor
   */
  @SuppressWarnings("restriction")
  public static CompositeActor getSelectedModel() {
    NamedObj result = null;
    final ISelection sel = EclipseUtils.getPage().getSelection();
    if (sel != null && sel instanceof IStructuredSelection) {
      final IStructuredSelection str = (IStructuredSelection) sel;
      Object res = str.getFirstElement();
      result = getModelObjectForSelection(res);
      if (result == null && res instanceof DiagramEditPart) {
        // FIXME a temp hack here to get the ptolemy model from the Diagram
        // don't know how to get the linked emf business model root from a Diagram, as the linkmodel doesn't seem to contain that one?
        // so we pass via a contained entity (i.e. via it's editpart)
        List<?> diagramChildren = ((DiagramEditPart) res).getChildren();
        if (!diagramChildren.isEmpty()) {
          result = getModelObjectForSelection(diagramChildren.get(0));
        }
      }
    }
    result = (result != null) ? result.topLevel() : null;
    return (CompositeActor) result;
  }

  /**
   * Find the model element for the selected edit part
   * @param editPart
   * @return
   */
  public static NamedObj getModelObjectForSelection(Object editPart) {
    NamedObj result = null;
    if (editPart instanceof GraphitiShapeEditPart) {
      GraphitiShapeEditPart shapeEditPart = (GraphitiShapeEditPart) editPart;
      IFeatureProvider fp = shapeEditPart.getFeatureProvider();
      Object bo = fp.getBusinessObjectForPictogramElement(shapeEditPart.getPictogramElement());
      if (bo instanceof NamedObj) {
        result = (NamedObj) bo;
      }
    }
    return result;
  }
}
