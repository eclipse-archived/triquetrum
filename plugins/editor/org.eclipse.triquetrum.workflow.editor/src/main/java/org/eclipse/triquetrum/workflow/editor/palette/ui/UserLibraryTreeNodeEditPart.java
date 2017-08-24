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
package org.eclipse.triquetrum.workflow.editor.palette.ui;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.palette.PaletteContainer;

/**
 * EditParts for tree nodes in the user library in the palette.
 * 
 * These support adding/removing user library entries (i.e. actor oriented classes),
 * contrary to default palette nodes that can not be changed by the user.
 *
 */
public class UserLibraryTreeNodeEditPart extends PaletteTreeNodeEditPart {

  public UserLibraryTreeNodeEditPart(PaletteContainer model) {
    super(model);
  }

  @Override
  protected void createEditPolicies() {
    super.createEditPolicies();
    installEditPolicy(EditPolicy.TREE_CONTAINER_ROLE,
        new UserLibraryEditPolicy());
  }
}
