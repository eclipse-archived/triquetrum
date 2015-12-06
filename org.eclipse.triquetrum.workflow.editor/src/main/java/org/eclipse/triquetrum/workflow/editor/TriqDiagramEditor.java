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
package org.eclipse.triquetrum.workflow.editor;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.triquetrum.workflow.editor.outline.DiagramEditorOutlinePage;
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
  public CommandStack getCommandStack() {
    // TODO Auto-generated method stub
    return super.getCommandStack();
  }
}
