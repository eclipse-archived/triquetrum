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
package org.eclipse.triquetrum.workflow.editor.outline.tree;

import org.eclipse.emf.transaction.util.Adaptable;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;

public class TriqOutlineTreeViewer extends TreeViewer implements Adaptable {

  private IDiagramContainerUI diagramContainer;

  public TriqOutlineTreeViewer(IDiagramContainerUI diagramContainer) {
    this.diagramContainer = diagramContainer;
  }

  @Override
  public Object getAdapter(Class adapterType) {
    if (adapterType == IDiagramContainerUI.class)
      return diagramContainer;
    else if (diagramContainer != null)
      return diagramContainer.getAdapter(adapterType);
    return null;
  }

  public EditPart convert(EditPart part) {
    Object result = EditorUtils.getModelObjectForSelection(part.getModel());
    if (result != null) {
      return (EditPart) getEditPartRegistry().get(result);
    } else {
      return part;
    }
  }
}
