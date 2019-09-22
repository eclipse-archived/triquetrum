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
package org.eclipse.triquetrum.workflow.editor.shapes.ptolemy;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.data.DoubleToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.vergil.kernel.attributes.ImageAttribute;

public class ImageDrawingStrategy extends AbstractDrawingStrategy<ImageAttribute> {
  private final static Logger LOGGER = LoggerFactory.getLogger(ImageDrawingStrategy.class);

  @Override
  public void draw(ImageAttribute imageAttr, Graphics graphics, ResourceManager resourceManager) {
    try {
      Image img = resourceManager.createImage(buildImageDescriptor(imageAttr));
      Point tlp = getTopLeftLocation(imageAttr);
      graphics.drawImage(img, tlp);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading image for " + imageAttr.getFullName(), e);
    }
  }

  @Override
  protected Dimension getDimension(ImageAttribute imageAttr, ResourceManager resourceManager) {
    try {
      Image img = resourceManager.createImage(buildImageDescriptor(imageAttr));
      return new Dimension(img.getBounds().width, img.getBounds().height);
    } catch (IllegalActionException e) {
      LOGGER.error("Error reading dimensions for " + imageAttr.getFullName(), e);
      return new Dimension(0, 0);
    }
  }
  
  private ImageDescriptor buildImageDescriptor(ImageAttribute imageAttr) throws IllegalActionException {
    ImageDescriptor imgDescr = ImageDescriptor.createFromURL(imageAttr.source.asURL());
    double scale = ((DoubleToken) imageAttr.scale.getToken()).doubleValue();
    // Use GC.drawImage to scale which gives better result on Mac
    
    Image oldImg = imgDescr.createImage();
    int oldWidth = oldImg.getBounds().width;
    int oldHeight = oldImg.getBounds().height;
    int newWidth = (int)Math.round(oldWidth*(scale/100));
    int newHeight = (int)Math.round(oldHeight*(scale/100));
    Image newImg = new Image(Display.getCurrent(), newWidth, newHeight);

    GC gc = new GC(newImg);
    gc.drawImage(oldImg, 0, 0, oldWidth, oldHeight, 0, 0, newWidth, newHeight);

    ImageDescriptor result = ImageDescriptor.createFromImage(newImg);

    oldImg.dispose();
    gc.dispose();
    return result;
  }
}
