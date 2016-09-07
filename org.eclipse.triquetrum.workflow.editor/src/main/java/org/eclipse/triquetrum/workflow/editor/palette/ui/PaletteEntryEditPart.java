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
package org.eclipse.triquetrum.workflow.editor.palette.ui;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.triquetrum.workflow.editor.ImageConstants;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramTypeProvider;

/**
 * EditPart for components in the Tree.
 */
public class PaletteEntryEditPart extends org.eclipse.gef.editparts.AbstractTreeEditPart {

  /**
   * Constructor initializes this with the given model.
   *
   * @param model
   *          The underlying palette entry model object
   */
  public PaletteEntryEditPart(PaletteEntry model) {
    super(model);
  }

  @Override
  protected String getText() {
    PaletteEntry entry = (PaletteEntry) getModel();
    return entry.getLabel();
  }

  @Override
  protected Image getImage() {
    PaletteEntry entry = (PaletteEntry) getModel();
    ImageDescriptor smallIcon = entry.getSmallIcon();
    if (smallIcon != null) {
      return ExtendedImageRegistry.INSTANCE.getImage(smallIcon);
    } else {
      return GraphitiUi.getImageService().getImageForId(TriqDiagramTypeProvider.ID, getDefaultImageID());
    }
  }

  protected String getDefaultImageID() {
    return ImageConstants.IMG_ACTOR;
  }
}
