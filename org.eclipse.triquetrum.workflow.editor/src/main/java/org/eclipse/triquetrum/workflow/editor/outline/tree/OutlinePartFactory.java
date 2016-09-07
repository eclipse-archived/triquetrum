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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.NamedObj;

public class OutlinePartFactory implements EditPartFactory {
  private Set<OutlineEditPart> parts = new HashSet<OutlineEditPart>();

  public Set<OutlineEditPart> getParts() {
    return parts;
  }

  public EditPart createEditPart(EditPart context, Object model) {
    OutlineEditPart editPart = null;
    if (model instanceof Diagram) {
      PictogramLink link = ((Diagram) model).getLink();
      if (link != null) {
        EList<EObject> businessObjects = link.getBusinessObjects();
        if (businessObjects.size() == 1) {
          editPart = new OutlineContainerEditPart(context, (CompositeActor) businessObjects.get(0));
        }
      }
    } else if (model instanceof CompositeActor) {
      editPart = new OutlineContainerEditPart(context, (CompositeActor) model);
    } else if (model instanceof NamedObj) {
      editPart = new OutlineEditPart((NamedObj) model);
    }
    if (editPart != null) {
      parts.add(editPart);
    }
    return editPart;
  }

}
