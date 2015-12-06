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

import org.eclipse.graphiti.ui.editor.DefaultPaletteBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.triquetrum.workflow.editor.palette.TriqPaletteBehavior;

public class TriqDiagramBehavior extends DiagramBehavior {

  public TriqDiagramBehavior(IDiagramContainerUI diagramContainer) {
    super(diagramContainer);
  }
  
  @Override
  protected DefaultPaletteBehavior createPaletteBehaviour() {
    return new TriqPaletteBehavior(this);
  }
}
