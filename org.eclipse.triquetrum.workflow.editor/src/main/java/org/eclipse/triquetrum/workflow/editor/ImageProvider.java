/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor;

import org.eclipse.emf.common.util.URI;
import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class ImageProvider extends AbstractImageProvider {

//  private final static Logger LOGGER = LoggerFactory.getLogger(ImageProvider.class);

  private static final String ROOT_FOLDER_FOR_IMG = "icons/";

  @Override
  protected void addAvailableImages() {
    addImageFilePath(ImageConstants.IMG_COLOR_CHANGE, ROOT_FOLDER_FOR_IMG + "color-palette.gif");
    addImageFilePath(ImageConstants.IMG_CONFIGURE, ROOT_FOLDER_FOR_IMG + "configure.gif");

    addImageFilePath(ImageConstants.IMG_OUTLINE_TREE, ROOT_FOLDER_FOR_IMG + "tree.gif");
    addImageFilePath(ImageConstants.IMG_OUTLINE_THUMBNAIL, ROOT_FOLDER_FOR_IMG + "thumbnail.gif");

    addImageFilePath(ImageConstants.IMG_ACTOR, ROOT_FOLDER_FOR_IMG + "actor.gif");
    addImageFilePath(ImageConstants.IMG_COMPOSITE, ROOT_FOLDER_FOR_IMG + "composite.gif");
    addImageFilePath(ImageConstants.IMG_DIRECTOR, ROOT_FOLDER_FOR_IMG + "director.gif");
    addImageFilePath(ImageConstants.IMG_INPUTPORT, ROOT_FOLDER_FOR_IMG + "input.gif");
    addImageFilePath(ImageConstants.IMG_OUTPUTPORT, ROOT_FOLDER_FOR_IMG + "output.gif");
    addImageFilePath(ImageConstants.IMG_PARAMETER, ROOT_FOLDER_FOR_IMG + "parameter.gif");

    addImageFilePath(ImageConstants.IMG_PAUSE_WORKFLOW, ROOT_FOLDER_FOR_IMG + "pause_workflow.gif");
    addImageFilePath(ImageConstants.IMG_RUN_WORKFLOW, ROOT_FOLDER_FOR_IMG + "run_workflow.gif");
    addImageFilePath(ImageConstants.IMG_STEP_WORKFLOW, ROOT_FOLDER_FOR_IMG + "step_workflow.png");
    addImageFilePath(ImageConstants.IMG_STOP_WORKFLOW, ROOT_FOLDER_FOR_IMG + "stop_workflow.gif");

  }

  /**
   * This method permits to dynamically add extra images, and is typically used for palette extensions. E.g. (extra) actor contributions to the Triquetrum
   * palette can define the desired palette icons using the org.eclipse.triquetrum.workflow.editor.paletteContribution extension point.
   *
   * Graphiti by default does not allow to add extra ImageProviders, outside of the preconfigured ones for the TriqDiagramTypeProvider. So we need to provide a
   * "bridge" here from this default Triquetrum ImageProvider, to also provide images from future extensions...
   *
   * @see suggestion in https://bugs.eclipse.org/bugs/show_bug.cgi?id=366452#c8
   * @param providerBundleName
   * @param imageId
   * @param imageFilePath
   */
  public void myAddImageFilePath(String providerBundleName, String imageId, String imageFilePath) {
    if (getImageFilePath(imageId) == null) {
      URI imageURI = URI.createPlatformPluginURI(providerBundleName + "/" + imageFilePath, true);
      addImageFilePath(imageId, imageURI.toString());
    }
//    else {
//      LOGGER.debug("Provider {} : Image ID {} already assigned. {} ignored.", new Object[] {providerBundleName, imageId, imageFilePath});
//    }
  }
}