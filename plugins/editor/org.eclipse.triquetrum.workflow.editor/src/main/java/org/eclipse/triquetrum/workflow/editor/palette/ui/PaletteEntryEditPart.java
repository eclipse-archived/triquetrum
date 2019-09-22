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

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.triquetrum.workflow.editor.ImageConstants;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramTypeProvider;

/**
 * EditPart for components in the Tree.
 */
public class PaletteEntryEditPart extends AbstractTreeEditPart {

  /**
   * Constructor initializes this with the given model.
   *
   * @param model
   *          The underlying palette entry model object
   */
  public PaletteEntryEditPart(PaletteEntry model) {
    super(model);
  }

  /**
   * Returns the full path in the palette tree, where this entry can be found, 
   * including the name/text/label of this entry itself.
   * 
   * @param separator
   *          (optional) separator char. Default is '/'
   * @return the hierarchical path in the palette tree, including the edit part's own text/label.
   */
  public String getFullTreePath(Character separator) {
    StringBuilder path = new StringBuilder(getParentTreePath(separator));
    path.append((separator != null ? separator : '/') + getText());
    return path.toString();
  }

  /**
   * Returns the parent path in the palette tree, where this entry can be found. I.e. it returns the complete parent
   * structure, but excludes the name/text/label of this entry itself.
   * 
   * @param separator
   *          (optional) separator char. Default is '/'
   * @return the hierarchical path in the palette tree, excluding the edit part's own text/label.
   */
  public String getParentTreePath(Character separator) {
    StringBuilder path = new StringBuilder();
    EditPart parent = getParent();
    while (parent instanceof PaletteEntryEditPart) {
      String parentLabel = ((PaletteEntryEditPart) parent).getText();
      if (parentLabel != null) {
        path.insert(0, (separator != null ? separator : '/') + parentLabel);
      }
      parent = parent.getParent();
    }
    return path.toString();
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

  @Override
  protected void removeChildVisual(EditPart childEditPart) {
    TreeEditPart treeEditPart = (TreeEditPart) childEditPart;
    if (treeEditPart.getWidget() != null) {
      treeEditPart.getWidget().dispose();
      treeEditPart.setWidget(null);
    }
  }

  protected String getDefaultImageID() {
    return ImageConstants.IMG_ACTOR;
  }
}
