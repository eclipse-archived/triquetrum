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
package org.eclipse.triquetrum.workflow.editor;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.triquetrum.workflow.editor.outline.DiagramEditorOutlinePage;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class TriqDiagramEditor extends DiagramEditor {
  public final static String EDITOR_ID = "org.eclipse.triquetrum.workflow.editor.TriqDiagramEditor";

  public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
    if (IContentOutlinePage.class.equals(type)) {
      DiagramEditorOutlinePage outlinePage = new DiagramEditorOutlinePage(this);
      return outlinePage;
    }
    return super.getAdapter(type);
  }

  @Override
  protected DiagramEditorInput convertToDiagramEditorInput(IEditorInput input) throws PartInitException {
    try {
      return super.convertToDiagramEditorInput(input);
    } catch (PartInitException e) {
      // Graphiti by default does not support opening diagram files outside of the workspace.
      // This is related to incompatibilities between EMF URIEditorInput and eclipse's FileStoreEditorInput,
      // for which the default DiagramEditor's adapting code is not adapted by default...
      URI uri = EditUIUtil.getURI(input);
      return super.convertToDiagramEditorInput(new URIEditorInput(uri));
    }
  }

  @Override
  public void setFocus() {
    super.setFocus();
    Diagram d = getDiagramTypeProvider().getDiagram();
    if(d!=null) {
      ExecutionStatusManager.getInstance().fireStatusChanged(d.getName());
    }
  }

  @Override
  public CommandStack getCommandStack() {
    return super.getCommandStack();
  }
}
