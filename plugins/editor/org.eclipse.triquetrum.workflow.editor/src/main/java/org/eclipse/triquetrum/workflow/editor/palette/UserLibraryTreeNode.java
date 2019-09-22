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
package org.eclipse.triquetrum.workflow.editor.palette;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;

/**
 * A node that contains a user library in the palette.
 */
public class UserLibraryTreeNode extends PaletteTreeNode {

  public UserLibraryTreeNode(String label) {
    super(label);
    setUserModificationPermission(PaletteEntry.PERMISSION_LIMITED_MODIFICATION);
  }

  /**
   * checks if the given entry is a PaletteEntry and is a (deep) child of this user library node.
   * 
   * @param entry
   * @return true if the given entry is in this user library subtree
   */
  public boolean isMember(Object entry) {
    if (!(entry instanceof PaletteEntry)) {
      return false;
    } else {
      PaletteEntry pe = (PaletteEntry) entry;
      boolean result = false;
      PaletteContainer parent = pe.getParent();
      while (!result && parent != null) {
        result = (this == parent);
        parent = parent.getParent();
      }
      return result;
    }
  }

  @Override
  public void add(PaletteEntry entry) {
    super.add(entry);
    // User library palette entries are user-editable.
    entry.setUserModificationPermission(PaletteEntry.PERMISSION_FULL_MODIFICATION);
  }
}
