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
package org.eclipse.triquetrum.workflow.editor.outline.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.swt.graphics.Image;
import org.eclipse.triquetrum.workflow.editor.ImageConstants;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramTypeProvider;
import org.eclipse.triquetrum.workflow.model.CompositeActor;

/**
 * Tree EditPart for the Container.
 */
public class OutlineContainerEditPart extends OutlineEditPart {
  private EditPart context;

  /**
   * Constructor, which initializes this using the model given as input.
   */
  public OutlineContainerEditPart(EditPart context, CompositeActor model) {
    super(model);
    this.context = context;
  }

  /**
   * Creates and installs pertinent EditPolicies.
   */
  @Override
  protected void createEditPolicies() {
    super.createEditPolicies();
  }

  @Override
  protected Image getImage() {
    return GraphitiUi.getImageService().getImageForId(TriqDiagramTypeProvider.ID, ImageConstants.IMG_COMPOSITE);
  }

  /**
   * Returns the children of this from the model, as this is capable enough of holding EditParts.
   *
   * @return List of children.
   */
  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected List getModelChildren() {
    ArrayList children = new ArrayList();

    CompositeActor actor = (CompositeActor) getModel();
    children.addAll(actor.getParameters());
    children.addAll(actor.getInputPorts());
    children.addAll(actor.getOutputPorts());
    List entities = actor.getEntities();
    if (entities != null)
      children.addAll(entities);
    // Only show children 1 level deep
    boolean showChildren = !(context != null && context.getParent() != null);
    if (!showChildren)
      return children;

    if (actor.getDirector() != null) {
      children.add(actor.getDirector());
    }

    return children;
  }
}
