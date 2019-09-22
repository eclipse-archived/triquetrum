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
package org.eclipse.triquetrum.workflow.editor.shapes;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.widgets.Display;

public class ShapeUtils {

  /**
   * Converts an AWT based buffered image into an SWT <code>Image</code>. This will always return an <code>Image</code> that has 24 bit depth regardless of the
   * type of AWT buffered image that is passed into the method.
   *
   * @param device
   * @param awtImage
   *          the {@link java.awt.image.BufferedImage} to be converted to an <code>Image</code>
   *          
   * @return an <code>Image</code> that represents the same image data as the AWT <code>BufferedImage</code> type.
   */
  public static org.eclipse.swt.graphics.Image toSWT(Device device, BufferedImage awtImage) {
    device = (device!=null) ? device : Display.getCurrent();
    // We can force bitdepth to be 24 bit because BufferedImage getRGB
    // allows us to always retrieve 24 bit data regardless of source color depth.
    PaletteData palette = new PaletteData(0xFF0000, 0xFF00, 0xFF);
    ImageData swtImageData = new ImageData(awtImage.getWidth(), awtImage.getHeight(), 24, palette);
    // Ensure scansize is aligned on 32 bit.
    int scansize = (((awtImage.getWidth() * 3) + 3) * 4) / 4;
    WritableRaster alphaRaster = awtImage.getAlphaRaster();
    byte[] alphaBytes = new byte[awtImage.getWidth()];
    for (int y = 0; y < awtImage.getHeight(); y++) {
      int[] buff = awtImage.getRGB(0, y, awtImage.getWidth(), 1, null, 0, scansize);
      swtImageData.setPixels(0, y, awtImage.getWidth(), buff, 0);
      if (alphaRaster != null) {
        int[] alpha = alphaRaster.getPixels(0, y, awtImage.getWidth(), 1, (int[]) null);
        for (int i = 0; i < awtImage.getWidth(); i++) {
          alphaBytes[i] = (byte) alpha[i];
        }
        swtImageData.setAlphas(0, y, awtImage.getWidth(), alphaBytes, 0);
      }
    }
    return new org.eclipse.swt.graphics.Image(device, swtImageData);
  }

}
