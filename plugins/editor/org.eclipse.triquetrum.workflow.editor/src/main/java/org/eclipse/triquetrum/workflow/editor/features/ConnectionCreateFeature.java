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
package org.eclipse.triquetrum.workflow.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.Linkable;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.Vertex;

import ptolemy.actor.IORelation;

/**
 *
 * Remark : this is a class that has some manual formatting, for the complex boolean expressions in canCreate() etc.
 *
 */
public class ConnectionCreateFeature extends AbstractCreateConnectionFeature {

  private IORelation wrappedObject;

  public ConnectionCreateFeature(IFeatureProvider fp) {
    // provide name and description for the UI, e.g. the palette
    super(fp, "Relation", "Create Connection");
  }

  public IORelation getWrappedObject() {
    return wrappedObject;
  }

  public void setWrappedObject(IORelation wrappedObject) {
    this.wrappedObject = wrappedObject;
  }

  /**
   * This is true when :
   * <ul>
   * <li>the src is an atomic actor's output port, or is a port inside a submodel CompositeActor. For that last case, the condition to effectively allow the
   * complete creation of a new connection, depends on the target port :
   * <ul>
   * <li>if the src port is an input port of a submodel, the target port must be an input port of an actor inside that submodel or a vertex or an output port of
   * the submodel itself</li>
   * <li>if the src port is an output port of a submodel, the target port must be a vertex or an input port of an actor in the parent model.</li>
   * </ul>
   * </li>
   * <li>the src is a vertex. Then the target must be in the same (sub)model level and must be either :
   * <ul>
   * <li>a vertex</li>
   * <li>an input port of an actor</li>
   * <li>an output port in the current submodel CompositeActor</li>
   * </ul>
   * </li>
   * </ul>
   *
   */
  @Override
  public boolean canCreate(ICreateConnectionContext context) {
    // return true if both anchors belong to a Port or Vertex and can accept the connection
    Linkable source = getAnchorBO(context.getSourceAnchor());
    Linkable target = getAnchorBO(context.getTargetAnchor());
    if (target != null) {
      if (source.isPotentialStart() && target.isPotentialEnd(source)) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method checks if the src is either a vertex or a port that can be the src for a new connection. For ports, this is true when the port is an atomic
   * actor's output port, or is a port inside a submodel CompositeActor.
   *
   * @param context
   *          containing the selected source anchor
   * @return true if the src can be a starting point for a new connection
   */
  @Override
  public boolean canStartConnection(ICreateConnectionContext context) {
    // return true if start anchor belongs to a Port or Vertex
    NamedObj anchorBO = getAnchorBO(context.getSourceAnchor());
    return (anchorBO instanceof Linkable) && ((Linkable)anchorBO).isPotentialStart();
  }


  @Override
  public Connection create(ICreateConnectionContext context) {
    try {
      Connection newConnection = null;

      // get Ports or Vertices which should be connected
      NamedObj source = getAnchorBO(context.getSourceAnchor());
      NamedObj target = getAnchorBO(context.getTargetAnchor());

      if (source != null && target != null) {
        // create new business object
        Relation relation = EditorUtils.createRelation(source, target, wrappedObject);
        // add connection for business object
        AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
        addContext.setNewObject(relation);
        newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
      }

      return newConnection;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Returns the Port or Vertex belonging to the anchor, or null if not available.
   */
  private Linkable getAnchorBO(Anchor anchor) {
    if (anchor != null) {
      Object object = getBusinessObjectForPictogramElement(anchor);
      if (object instanceof Port) {
        return (Port) object;
      }
      if (object instanceof Vertex) {
        return (Vertex) object;
      } else {
        throw new IllegalArgumentException("Anchor " + anchor + " linked to invalid object " + object);
      }
    }
    return null;
  }
}
