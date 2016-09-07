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
package org.eclipse.triquetrum.workflow.editor.palette.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.triquetrum.workflow.editor.ImageConstants;

/**
 * Tree EditPart for palette tree node.
 */
public class PaletteTreeNodeEditPart extends PaletteEntryEditPart {

  public PaletteTreeNodeEditPart(PaletteContainer model) {
    super(model);
  }

  protected String getDefaultImageID() {
    return ImageConstants.IMG_FOLDER;
  }

  @Override
  public void refresh() {
    super.refresh();
    refreshChildrenContents();
  }

  protected void refreshChildrenContents() {
    List children = getChildren();
    int size = children.size();
    if (size > 0) {
      for (int i = 0; i < size; i++) {
        EditPart editPart = (EditPart) children.get(i);
        editPart.refresh();
      }
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected List getModelChildren() {
    List children = new ArrayList();
    PaletteEntry entry = (PaletteEntry) getModel();
    if (entry instanceof PaletteContainer) {
      PaletteContainer pc = (PaletteContainer) entry;
      children.addAll(pc.getChildren());
    }
    return children;
  }
}
