/*******************************************************************************
 * Copyright (c) 2000, 2016 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.palette.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.AbstractTransferDragSourceListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.DragSourceEvent;

/**
 * duplicated from org.eclipse.gef.ui.parts
 */
public class TreeViewerTransferDragListener extends AbstractTransferDragSourceListener {

  private List modelSelection;

  public TreeViewerTransferDragListener(EditPartViewer viewer) {
    super(viewer, TreeViewerTransfer.getInstance());
  }

  @Override
  public void dragSetData(DragSourceEvent event) {
    event.data = getViewer().getSelectedEditParts();
  }

  @Override
  public void dragStart(DragSourceEvent event) {
    TreeViewerTransfer.getInstance().setViewer(getViewer());
    List selection = getViewer().getSelectedEditParts();
    TreeViewerTransfer.getInstance().setObject(selection);
    saveModelSelection(selection);
  }

  @Override
  public void dragFinished(DragSourceEvent event) {
    TreeViewerTransfer.getInstance().setObject(null);
    TreeViewerTransfer.getInstance().setViewer(null);
    if (event.doit)
      revertModelSelection();
    else
      modelSelection = null;
  }

  protected void revertModelSelection() {
    List list = new ArrayList();
    Object editpart;
    for (int i = 0; i < modelSelection.size(); i++) {
      editpart = getViewer().getEditPartRegistry().get(modelSelection.get(i));
      if (editpart != null)
        list.add(editpart);
    }
    getViewer().setSelection(new StructuredSelection(list));
    modelSelection = null;
  }

  protected void saveModelSelection(List editPartSelection) {
    modelSelection = new ArrayList();
    for (int i = 0; i < editPartSelection.size(); i++) {
      EditPart editpart = (EditPart) editPartSelection.get(i);
      modelSelection.add(editpart.getModel());
    }
  }

}