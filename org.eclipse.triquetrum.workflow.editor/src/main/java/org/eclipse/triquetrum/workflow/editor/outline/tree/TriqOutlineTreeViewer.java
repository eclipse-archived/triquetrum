/*******************************************************************************
 * Copyright (c) 2017 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
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
