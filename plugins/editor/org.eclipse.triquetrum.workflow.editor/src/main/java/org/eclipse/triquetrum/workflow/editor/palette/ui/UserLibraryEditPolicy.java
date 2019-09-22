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

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.TreeContainerEditPolicy;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

public class UserLibraryEditPolicy extends TreeContainerEditPolicy {

  @Override
  protected Command getAddCommand(ChangeBoundsRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Command getCreateCommand(CreateRequest request) {
    PaletteEntry child = (PaletteEntry) request.getNewObject();
    
    UserLibraryCreateCommand cmd = new UserLibraryCreateCommand();
    cmd.setChild(child);
    cmd.setParent((PaletteTreeNodeEditPart) getHost());
    return cmd;
  }

  @Override
  protected Command getMoveChildrenCommand(ChangeBoundsRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

}
