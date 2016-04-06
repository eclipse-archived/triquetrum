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
package org.eclipse.triquetrum.workflow.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.TriqFactory;
import org.eclipse.triquetrum.workflow.model.Vertex;

import ptolemy.kernel.util.IllegalActionException;

public class ConnectionCreateFeature extends AbstractCreateConnectionFeature {

  public ConnectionCreateFeature(IFeatureProvider fp) {
    // provide name and description for the UI, e.g. the palette
    super(fp, "Relation", "Create Connection");
  }

  public boolean canCreate(ICreateConnectionContext context) {
    // return true if both anchors belong to a Port or Vertex and can accept the connection
    NamedObj source = getAnchorBO(context.getSourceAnchor());
    NamedObj target = getAnchorBO(context.getTargetAnchor());
    if (isPotentialConnectionStart(source) && isPotentialConnectionTarget(target)) {
      return true;
    }
    return false;
  }

  public boolean canStartConnection(ICreateConnectionContext context) {
    // return true if start anchor belongs to a Port or Vertex
    NamedObj port = getAnchorBO(context.getSourceAnchor());
    return isPotentialConnectionStart(port);
  }

  private boolean isPotentialConnectionStart(NamedObj src) {
    if (src == null) {
      return false;
    }
    if (src instanceof Port) {
      Port p = (Port) src;
      return (p.canAcceptNewConnection())
          && ((p.isOutput() && !(p.getContainer() instanceof CompositeActor))
              || (p.isInput() && (src.getContainer() instanceof CompositeActor)));
    }
    return (src instanceof Vertex);
  }

  private boolean isPotentialConnectionTarget(NamedObj target) {
    if (target == null) {
      return false;
    }
    if (target instanceof Port) {
      Port p = (Port) target;
      return (p.canAcceptNewConnection())
          && ((p.isOutput() && (p.getContainer() instanceof CompositeActor))
              || (p.isInput() && !(p.getContainer() instanceof CompositeActor)));
    }
    return (target instanceof Vertex);
  }

  public Connection create(ICreateConnectionContext context) {
    try {
      Connection newConnection = null;

      // get Ports or Vertices which should be connected
      NamedObj source = getAnchorBO(context.getSourceAnchor());
      NamedObj target = getAnchorBO(context.getTargetAnchor());

      if (source != null && target != null) {
        // create new business object
        Relation relation = createRelation(source, target);
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
  private NamedObj getAnchorBO(Anchor anchor) {
    if (anchor != null) {
      Object object = getBusinessObjectForPictogramElement(anchor);
      if (object instanceof Port) {
        return (Port) object;
      }
      if (object instanceof Vertex) {
        return (Vertex) object;
      } else {
        throw new IllegalArgumentException("Anchor " + anchor + " linked to invalid object "+object);
      }
    }
    return null;
  }

  /**
   * Creates a relation between two ports.
   *
   * @throws IllegalActionException
   */
  private Relation createRelation(NamedObj source, NamedObj target) throws IllegalActionException {
    // TODO refactor this double instanceof chaining
    Relation relation = null;
    boolean srcIsVertex = false;
    boolean onlyTargetIsVertex = false;
    if(source instanceof Vertex) {
      // use the vertex's relation
      relation = (Relation)source.getContainer();
      srcIsVertex = true;
    } else if(target instanceof Vertex) {
      // use the vertex's relation
      relation = (Relation)target.getContainer();
      onlyTargetIsVertex = true;
    } else {
      // create a new relation directly linking 2 ports
      relation = TriqFactory.eINSTANCE.createRelation();
      NamedObj relationContainer = source.getContainer();
      while (!(relationContainer instanceof CompositeActor)) {
        relationContainer = relationContainer.getContainer();
      }
      relation.setName(EditorUtils.buildUniqueName(relationContainer, "_R"));
      ((CompositeActor) relationContainer).getRelations().add(relation);
    }
    if(srcIsVertex) {
      // no need to link to the relation as we're using the src's relation anyway
    } else {
      relation.getLinkedPorts().add((Port)source);
    }
    if(target instanceof Port) {
      relation.getLinkedPorts().add((Port)target);
    } else {
      Relation targetRelation = (Relation) target.getContainer();
      if(onlyTargetIsVertex) {
        // no need to link to the relation as we're using the target's relation anyway
      } else {
        relation.getLinkedRelations().add(targetRelation);
      }
    }
    return relation;
  }
}
