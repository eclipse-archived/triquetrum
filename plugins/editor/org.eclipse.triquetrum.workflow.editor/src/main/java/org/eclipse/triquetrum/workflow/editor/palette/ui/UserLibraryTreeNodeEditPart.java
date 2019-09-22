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
