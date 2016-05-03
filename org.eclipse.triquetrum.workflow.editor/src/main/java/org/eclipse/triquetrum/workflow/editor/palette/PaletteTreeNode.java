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
package org.eclipse.triquetrum.workflow.editor.palette;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * A node in a Triq tree-based palette that may contain entries and/or containers (such as nodes) as children.
 * <p>
 * Derived from GEF's PaletteDrawer, but allowing a deep hierarchy i.o. just a single level.
 * </p>
 *
 */
public class PaletteTreeNode extends PaletteDrawer {
  /**
   * The type for this PaletteEntry.
   *
   * @see PaletteEntry#getType()
   */
  public static final Object PALETTE_TYPE_NODE = "$Palette Node"; //$NON-NLS-1$

  /**
   * Constructor
   *
   * @param label
   *            The name/label for this entry
   */
  public PaletteTreeNode(String label) {
    this(label, (ImageDescriptor) null);
  }

  /**
   * Constructor
   *
   * @param label
   *            The name/label for this entry
   * @param icon
   *            An icon for this node
   */
  public PaletteTreeNode(String label, ImageDescriptor icon) {
    super(label, icon);
//    setType(PALETTE_TYPE_NODE);
  }

  /**
   * Returns true if this type can be a child of this container
   *
   * @param type
   *            the type being requested
   * @return true if this can be a child of this container
   */
  public boolean acceptsType(Object type) {
     return true;
  }

}
