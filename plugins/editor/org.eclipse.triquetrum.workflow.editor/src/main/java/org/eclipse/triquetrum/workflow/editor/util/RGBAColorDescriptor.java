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
package org.eclipse.triquetrum.workflow.editor.util;

import org.eclipse.jface.resource.ColorDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGBA;

/**
 * Variation of org.eclipse.jface.resource.RGBColorDescriptor to add support for transparency via the alpha property,
 * as represented in an RGBA instance i.o. a plain RGB.
 *
 */
public class RGBAColorDescriptor extends ColorDescriptor {

  private RGBA color;

  /**
   * Color being copied, or null if none
   */
  private Color originalColor = null;

  /**
   * Creates a new RGBAColorDescriptor given some RGBA values
   *
   * @param color
   *          RGBA values (not null)
   */
  public RGBAColorDescriptor(RGBA color) {
    this.color = color;
  }

  /**
   * Creates a new RGBAColorDescriptor that describes an existing color.
   *
   * @param originalColor
   *          a color to describe
   */
  public RGBAColorDescriptor(Color originalColor) {
    this(originalColor.getRGBA());
    this.originalColor = originalColor;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof RGBAColorDescriptor) {
      RGBAColorDescriptor other = (RGBAColorDescriptor) obj;
      return other.color.equals(color) && other.originalColor == originalColor;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return color.hashCode();
  }

  @Override
  public Color createColor(Device device) {
    // If this descriptor is wrapping an existing color, then we can return the original color
    // if this is the same device.
    if (originalColor != null) {
      // If we're allocating on the same device as the original color, return the original.
      if (originalColor.getDevice() == device) {
        return originalColor;
      }
    }

    return new Color(device, color);
  }

  @Override
  public void destroyColor(Color toDestroy) {
    if (toDestroy == originalColor) {
      return;
    }

    toDestroy.dispose();
  }
}