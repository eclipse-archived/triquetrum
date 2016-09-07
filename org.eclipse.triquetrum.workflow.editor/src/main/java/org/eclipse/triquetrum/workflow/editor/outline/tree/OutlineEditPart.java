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
package org.eclipse.triquetrum.workflow.editor.outline.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.swt.graphics.Image;
import org.eclipse.triquetrum.workflow.editor.ImageConstants;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramTypeProvider;
import org.eclipse.triquetrum.workflow.model.Actor;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.CompositeEntity;
import org.eclipse.triquetrum.workflow.model.Director;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Parameter;
import org.eclipse.triquetrum.workflow.model.Port;

/**
 * EditPart for components in the Tree.
 */
public class OutlineEditPart extends org.eclipse.gef.editparts.AbstractTreeEditPart {

  /**
   * Constructor initializes this with the given model.
   *
   * @param model
   *          The underlying flow model object (e.g. an actor)
   */
  public OutlineEditPart(NamedObj model) {
    super(model);
  }

  /**
   * Returns <code>null</code> as a Tree EditPart holds no children under it.
   *
   * @return <code>null</code>
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected List getModelChildren() {
    List children = new ArrayList();
    NamedObj flowModel = (NamedObj) getModel();
    children.addAll(flowModel.getAttributes());
    if (flowModel instanceof Entity) {
      Entity actor = (Entity) flowModel;
      children.addAll(actor.getInputPorts());
      children.addAll(actor.getOutputPorts());
      if (flowModel instanceof CompositeEntity) {
        CompositeEntity composite = (CompositeEntity) flowModel;
        children.addAll(composite.getEntities());
      }
    }
    return children;
  }

  @Override
  protected Image getImage() {
    NamedObj modelObject = (NamedObj) getModel();
    if (!StringUtils.isBlank(modelObject.getIconId())) {
      return GraphitiUi.getImageService().getImageForId(TriqDiagramTypeProvider.ID, modelObject.getIconId());
    } else {
      if (modelObject instanceof Director)
        return GraphitiUi.getImageService().getImageForId(TriqDiagramTypeProvider.ID, ImageConstants.IMG_DIRECTOR);
      else if (modelObject instanceof Parameter)
        return GraphitiUi.getImageService().getImageForId(TriqDiagramTypeProvider.ID, ImageConstants.IMG_PARAMETER);
      else if (modelObject instanceof Port) {
        Port port = (Port) modelObject;
        if (port.isInput()) {
          return GraphitiUi.getImageService().getImageForId(TriqDiagramTypeProvider.ID, ImageConstants.IMG_INPUTPORT);
        } else {
          return GraphitiUi.getImageService().getImageForId(TriqDiagramTypeProvider.ID, ImageConstants.IMG_OUTPUTPORT);
        }
      } else if (modelObject instanceof CompositeActor) {
        return GraphitiUi.getImageService().getImageForId(TriqDiagramTypeProvider.ID, ImageConstants.IMG_COMPOSITE);
      } else if (modelObject instanceof Actor) {
        return GraphitiUi.getImageService().getImageForId(TriqDiagramTypeProvider.ID, ImageConstants.IMG_ACTOR);
      } else {
        return super.getImage();
      }
    }
  }

  @Override
  protected String getText() {
    NamedObj flowModel = (NamedObj) getModel();
    if (flowModel instanceof Parameter) {
      Parameter param = (Parameter) flowModel;
      String name = param.getName();
      String value = param.getExpression();
      return (name + "=" + (value == null ? "" : value));
    } else {
      return flowModel.getName();
    }
  }

  @Override
  public void refresh() {
    super.refresh();
    refreshChildrenContents();
  }

  protected void refreshChildrenContents() {
    List children = getChildren();
    int size = children.size();
    if (size > 0) {
      for (int i = 0; i < size; i++) {
        EditPart editPart = (EditPart) children.get(i);
        editPart.refresh();
      }
    }
  }
}
