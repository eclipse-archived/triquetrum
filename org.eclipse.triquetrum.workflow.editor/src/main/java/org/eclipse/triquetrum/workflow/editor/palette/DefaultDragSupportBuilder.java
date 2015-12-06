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
package org.eclipse.triquetrum.workflow.editor.palette;

import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.triquetrum.workflow.editor.palette.ui.DragTargetListener;

public class DefaultDragSupportBuilder implements DragSupportBuilder {

  @Override
  public void addDragSupport(TreeViewer treeViewer) {
    int ops = DND.DROP_MOVE | DND.DROP_COPY;
    Transfer[] transfers = new Transfer[] { TemplateTransfer.getInstance() };
    transfers = new Transfer[] { TemplateTransfer.getInstance() };
    DragTargetListener dragListener = new DragTargetListener(treeViewer);
    treeViewer.addDragSupport(ops, transfers, dragListener);
  }
}
