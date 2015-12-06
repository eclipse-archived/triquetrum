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
package org.eclipse.triquetrum.workflow.editor.util;

import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.ui.internal.parts.DiagramEditPart;
import org.eclipse.graphiti.ui.platform.GraphitiShapeEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.NamedObj;

import ptolemy.kernel.ComponentEntity;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.Port;
import ptolemy.kernel.util.IllegalActionException;

public class EditorUtils {

  @SuppressWarnings("restriction")
  public static CompositeActor getSelectedModel() {
    NamedObj result = null;
    final ISelection sel = EclipseUtils.getPage().getSelection();
    if (sel != null && sel instanceof IStructuredSelection) {
      final IStructuredSelection str = (IStructuredSelection) sel;
      Object res = str.getFirstElement();
      result = getModelObjectForSelection(res);
      if (result == null && res instanceof DiagramEditPart) {
        // FIXME a temp hack here to get the ptolemy model from the Diagram
        // don't know how to get the linked emf business model root from a Diagram, as the linkmodel doesn't seem to contain that one?
        // so we pass via a contained entity (i.e. via it's editpart)
        List<?> diagramChildren = ((DiagramEditPart) res).getChildren();
        if (!diagramChildren.isEmpty()) {
          result = getModelObjectForSelection(diagramChildren.get(0));
        }
      }
    }
    result = (result != null) ? result.topLevel() : null;
    return (CompositeActor) result;
  }

  private static NamedObj getModelObjectForSelection(Object res) {
    NamedObj result = null;
    if (res instanceof GraphitiShapeEditPart) {
      GraphitiShapeEditPart editPart = (GraphitiShapeEditPart) res;
      IFeatureProvider fp = editPart.getFeatureProvider();
      Object bo = fp.getBusinessObjectForPictogramElement(editPart.getPictogramElement());
      if (bo instanceof NamedObj) {
        result = (NamedObj) bo;
      }
    }
    return result;
  }
  
  public static String findUniqueName(CompositeEntity parentModel, Class clazz, String startName, String actorName) {
    if (clazz == null) {
      return findUniqueActorName(parentModel, actorName);

    }
    if (clazz.getSimpleName().equals("Vertex")) {
      return generateUniqueVertexName(clazz.getSimpleName(), parentModel, 0, clazz);
    } else if (clazz.getSimpleName().equals("TextAttribute")) {
      return generateUniqueTextAttributeName(clazz.getSimpleName(), parentModel, 0, clazz);
    } else if (clazz.getSimpleName().equals("TypedIOPort")) {
      return generateUniquePortName(startName, parentModel, 0);
    } else {
      return findUniqueActorName(parentModel, actorName != null ? actorName : clazz.getSimpleName());
    }
  }

  private static String generateUniquePortName(String name, CompositeEntity parent, int index) {
    String newName = index != 0 ? (name + "_" + index) : name;
    boolean contains = false;
    List<Port> ports = parent.portList();
    for(Port p : ports) {
      String portName = p.getName();
      if (newName.equals(portName)) {
        contains = true;
        break;
      }

    }
    if (!contains) {
      return newName;
    }
    index++;
    return generateUniquePortName(name, parent, index);

  }

  public static String findUniqueActorName(CompositeEntity parentModel, String name) {
    String newName = name;
    if (parentModel == null)
      return newName;
    List entityList = parentModel.entityList();
    if (entityList == null || entityList.size() == 0)
      return newName;

    ComponentEntity entity = parentModel.getEntity(newName);
    int i = 1;
    while (entity != null) {
      newName = name + "_" + i++;
      entity = parentModel.getEntity(newName);
    }

    return newName;
  }

  private static String generateUniqueTextAttributeName(String name, ptolemy.kernel.util.NamedObj parent, int index, Class clazz) {
    try {
      String newName = index != 0 ? (name + "_" + index) : name;
      if (parent.getAttribute(newName, clazz) == null) {
        return newName;
      } else {
        index++;
        return generateUniqueTextAttributeName(name, parent, index, clazz);
      }
    } catch (IllegalActionException e) {
      return name;
    }
  }

  private static String generateUniqueVertexName(String name, ptolemy.kernel.util.NamedObj parent, int index, Class clazz) {
    return "Vertex" + System.currentTimeMillis();
  }
}
