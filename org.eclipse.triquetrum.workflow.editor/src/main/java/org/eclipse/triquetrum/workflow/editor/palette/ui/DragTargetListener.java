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
package org.eclipse.triquetrum.workflow.editor.palette.ui;

import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteTemplateEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DragSourceEvent;

public class DragTargetListener extends ViewerDragAdapter {

  public DragTargetListener(TreeViewer viewer) {
    super(viewer);
    this.viewer = viewer;
  }

  @Override
  public void dragStart(DragSourceEvent event) {
    boolean doit = !getViewer().getSelection().isEmpty();
    if (doit) {
      ITreeSelection selection = (ITreeSelection) getViewer().getSelection();
      if (selection != null && !selection.isEmpty()) {
        final Object selected = selection.getFirstElement();
        if (selected instanceof CombinedTemplateCreationEntry) {
          CreationFactory factory = (CreationFactory) ((CombinedTemplateCreationEntry) selected).getToolProperty(CreationTool.PROPERTY_CREATION_FACTORY);
          event.data = selected;
          TemplateTransfer.getInstance().setTemplate(factory);
        } else {
          doit = false;
        }
      }
    }
    event.doit = doit;
  }

  protected boolean validateTransfer(Object transfer) {
    return transfer instanceof PaletteTemplateEntry;
  }

  /**
   * Returns viewer
   * 
   * @return viewer
   */
  protected TreeViewer getViewer() {
    return (TreeViewer) viewer;
  }
}