/*******************************************************************************
 * Copyright (c) 2015 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class ImageProvider extends AbstractImageProvider {

  private static final String ROOT_FOLDER_FOR_IMG = "icons/";

  @Override
  protected void addAvailableImages() {
    // outline
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

  public void myAddImageFilePath(String imageId, String imageFilePath) {
    addImageFilePath(imageId, imageFilePath);
  }
}