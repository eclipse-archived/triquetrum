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

import ptolemy.kernel.util.IllegalActionException;

public class ConnectionCreateFeature extends AbstractCreateConnectionFeature {

  public ConnectionCreateFeature(IFeatureProvider fp) {
    // provide name and description for the UI, e.g. the palette
    super(fp, "Relation", "Create Connection");
  }

  public boolean canCreate(ICreateConnectionContext context) {
    // return true if both anchors belong to a Port
    Port source = getPort(context.getSourceAnchor());
    Port target = getPort(context.getTargetAnchor());
    if (isPortPotentialConnectionStart(source)
        && isPortPotentialConnectionTarget(target)) {
      return true;
    }
    return false;
  }

  public boolean canStartConnection(ICreateConnectionContext context) {
    // return true if start anchor belongs to a Port
    Port port = getPort(context.getSourceAnchor());
    return isPortPotentialConnectionStart(port);
  }

  private boolean isPortPotentialConnectionStart(Port port) {
    return (port!=null) && (port.canAcceptNewConnection()) && ((port.isOutput() && !(port.getContainer() instanceof CompositeActor))
        || (port.isInput() && (port.getContainer() instanceof CompositeActor)));
  }

  private boolean isPortPotentialConnectionTarget(Port port) {
    return (port!=null) && (port.canAcceptNewConnection()) && ((port.isOutput() && (port.getContainer() instanceof CompositeActor))
        || (port.isInput() && !(port.getContainer() instanceof CompositeActor)));
  }

  public Connection create(ICreateConnectionContext context) {
    try {
      Connection newConnection = null;

      // get Ports which should be connected
      Port source = getPort(context.getSourceAnchor());
      Port target = getPort(context.getTargetAnchor());

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
   * Returns the Port belonging to the anchor, or null if not available.
   */
  private Port getPort(Anchor anchor) {
    if (anchor != null) {
      Object object = getBusinessObjectForPictogramElement(anchor);
      if (object instanceof Port) {
        return (Port) object;
      } else {
        System.out.println("what's this???? "+object);
      }
    }
    return null;
  }

  /**
   * Creates a relation between two ports.
   *
   * @throws IllegalActionException
   */
  private Relation createRelation(Port source, Port target) throws IllegalActionException {
    Relation relation = TriqFactory.eINSTANCE.createRelation();
    relation.getLinkedPorts().add(source);
    relation.getLinkedPorts().add(target);
    NamedObj portContainer = source.getContainer();
    while(!(portContainer instanceof CompositeActor)) {
      portContainer = portContainer.getContainer();
    }
    relation.setName(EditorUtils.buildUniqueName(portContainer, "_R"));
    ((CompositeActor)portContainer).getRelations().add(relation);
    return relation;
  }
}
