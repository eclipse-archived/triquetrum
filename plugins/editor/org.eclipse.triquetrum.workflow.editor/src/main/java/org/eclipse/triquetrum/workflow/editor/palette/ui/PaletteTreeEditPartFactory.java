/*******************************************************************************
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.palette.ui;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;

public class PaletteTreeEditPartFactory implements EditPartFactory {

  /**
   * @see org.eclipse.gef.EditPartFactory#createEditPart(EditPart, Object)
   */
  @Override
  public EditPart createEditPart(EditPart parentEditPart, Object model) {
    if (model instanceof PaletteContainer)
      return new PaletteTreeNodeEditPart((PaletteContainer) model);
    if (model instanceof PaletteEntry)
      return new PaletteEntryEditPart((PaletteEntry) model);
    return null;
  }

}
