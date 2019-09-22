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
package org.eclipse.triquetrum.workflow.editor.views;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.triquetrum.workflow.editor.outline.tree.OutlineEditPart;
import org.eclipse.triquetrum.workflow.editor.outline.tree.TriqOutlineTreeViewer;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.NamedObj;

/**
 * This is used as a selection broker between views combined with converting
 * selected elements between the different types of parts/widgets/... as used
 * in the involved views.
 *
 */
public class TriqSelectionSynchronizer extends SelectionSynchronizer {

  @Override
  protected EditPart convert(EditPartViewer viewer, EditPart part) {
    if(viewer instanceof TriqOutlineTreeViewer) {
      // This is for when the user selects an element in the graphical editor,
      // to make sure the outline tree view reflects the same selection.
      return ((TriqOutlineTreeViewer)viewer).convert(part);
    } else if (viewer instanceof GraphicalViewer && part instanceof OutlineEditPart) {
      // This is for when the user selects an element in the outline tree view,
      // and we want to select the corresponding shape in the graphical editor.
      Object selectedBO = part.getModel();
      NamedObj modelElement = null;
      if(selectedBO instanceof NamedObj) {
        // We want to select a top-level entity or parameter in the diagram,
        // even when the user clicks on an actor port or parameter in the outline tree view.
        // So we move up the containment tree a bit, until we reach the right level.
        modelElement = (NamedObj) selectedBO;
        while (modelElement != null && (modelElement.getContainer() != modelElement.topLevel())) {
          modelElement = modelElement.getContainer();
        }
      }
      List<PictogramElement> pes = Graphiti.getLinkService().getPictogramElements(EditorUtils.getSelectedDiagram(), modelElement);
      PictogramElement pe = null;
      for (PictogramElement pictogramElement : pes) {
        if(pictogramElement instanceof ContainerShape) {
          pe = pictogramElement;
          break;
        }
      }
      return (EditPart) viewer.getEditPartRegistry().get(pe);
    }
    return super.convert(viewer, part);
  }
}
