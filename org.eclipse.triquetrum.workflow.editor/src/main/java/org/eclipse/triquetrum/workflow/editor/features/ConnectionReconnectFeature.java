/*******************************************************************************
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.features;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.impl.DefaultReconnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.Linkable;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.TriqFactory;
import org.eclipse.triquetrum.workflow.model.Vertex;

// TODO build logic for the special case where we reconnect away from a vertex.
// in that case we typically need create a new relation instance as well, not just unlink&link anchors&ports,
// or we end up with a relation that is not totally connected in the diagram.
// E.g. when starting with a const connected to a vertex that is then connected to a display actor, and then moving the const connection away from the vertex,
// to directly connect to another display actor, we can end up with the vertex still connected to one port as before,
// combined with a plain 2-port connection between the two other actors (const and 2nd display).
// When running this model it still behaves as though all are connected via the vertex!
public class ConnectionReconnectFeature extends DefaultReconnectionFeature {

  boolean hasDoneChanges = false;

  public ConnectionReconnectFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public void postReconnect(IReconnectionContext context) {
    Anchor oldAnchor = context.getOldAnchor();
    Anchor newAnchor = context.getNewAnchor();
    if (newAnchor != oldAnchor) {
      Connection connection = context.getConnection();
      Relation relation = (Relation) getBusinessObjectForPictogramElement(connection);
      NamedObj oldBO = (NamedObj) getBusinessObjectForPictogramElement(oldAnchor);
      // Now we need to figure out if the reconnected connection can remain in the original relation,
      // or if it needs to create a new relation.
      // We do this by looking at both connection ends :
      // - If they're both ports, we need a new relation.
      // - If one is a vertex, we can link to that one's existing relation.

      Relation newRelation = null;
      Anchor endAnchor = connection.getEnd();
      NamedObj endBO = (NamedObj) getBusinessObjectForPictogramElement(endAnchor);
      Anchor startAnchor = connection.getStart();
      NamedObj startBO = (NamedObj) getBusinessObjectForPictogramElement(startAnchor);

      try {
        ((Linkable)oldBO).unlink(relation);
        ((Linkable)startBO).unlink(relation);
        ((Linkable)endBO).unlink(relation);
        // Only check this after all the unlink/link actions, as relation and newRelation might be the same instance,
        // so we don't want to delete relation yet before doing the new links!
        if(!relation.isConnected()) {
          // TODO check if/how we might want keep an unconnected Vertex around after all links were deleted/removed
          // I guess with the check above, such vertex would be deleted as well at the moment the last connection/link is removed/deleted.
          EcoreUtil.delete(relation, true);
        }

        if (endBO instanceof Vertex) {
          newRelation = (Relation) endBO.getContainer();
        } else {
          if (startBO instanceof Vertex) {
            newRelation = (Relation) startBO.getContainer();
          } else {
            newRelation = EditorUtils.createRelation(startBO, endBO, null);
          }
        }
        link(connection, newRelation);
        hasDoneChanges = true;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    super.postReconnect(context);
  }

  @Override
  public boolean hasDoneChanges() {
    return hasDoneChanges;
  }
}
