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
package org.eclipse.triquetrum.workflow.editor.palette;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;

/**
 * A node that contains a user library in the palette.
 */
public class PaletteUserLibraryNode extends PaletteTreeNode {

  public PaletteUserLibraryNode(String label) {
    super(label);
    setUserModificationPermission(PaletteEntry.PERMISSION_LIMITED_MODIFICATION);
  }

  /**
   * checks if the given palette entry is a (deep) child of this user library node.
   * 
   * @param entry
   * @return true if the given entry is in this user library subtree
   */
  public boolean isMember(PaletteEntry entry) {
    boolean result = false;
    PaletteContainer parent = entry.getParent();
    while(!result && parent!=null) {
      result = (this==parent);
      parent = parent.getParent();
    }
    return result;
  }

  @Override
  public void add(PaletteEntry entry) {
    super.add(entry);
    // User library palette entries are user-editable.
    entry.setUserModificationPermission(PaletteEntry.PERMISSION_FULL_MODIFICATION);
  }
}
