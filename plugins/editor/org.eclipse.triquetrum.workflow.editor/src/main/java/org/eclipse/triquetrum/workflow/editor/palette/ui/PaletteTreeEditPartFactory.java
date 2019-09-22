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
package org.eclipse.triquetrum.workflow.editor.palette.ui;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.triquetrum.workflow.editor.palette.UserLibraryTreeNode;

public class PaletteTreeEditPartFactory implements EditPartFactory {

  /**
   * @see org.eclipse.gef.EditPartFactory#createEditPart(EditPart, Object)
   */
  @Override
  public EditPart createEditPart(EditPart parentEditPart, Object model) {
    if (model instanceof UserLibraryTreeNode) {
      return new UserLibraryTreeNodeEditPart((PaletteContainer) model);
    }
    if (model instanceof PaletteContainer) {
      if (parentEditPart instanceof UserLibraryTreeNodeEditPart) {
        return new UserLibraryTreeNodeEditPart((PaletteContainer) model);
      } else {
        return new PaletteTreeNodeEditPart((PaletteContainer) model);
      }
    }
    if (model instanceof PaletteEntry) {
      return new PaletteEntryEditPart((PaletteEntry) model);
    }
    return null;
  }

}
