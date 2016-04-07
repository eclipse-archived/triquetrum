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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.impl.DefaultReconnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Relation;

// TODO build logic for the special case where we reconnect away from a vertex.
// in that case we typically need create a new relation instance as well, not just unlink&link anchors&ports,
// or we end up with a relation that is not totally connected in the diagram.
// E.g. when starting with a const connected to a vertex that is then connected to a display actor, and then moving the const connection away from the vertex,
// to directly connect to another display actor, we can end up with the vertex still connected to one port as before,
// combined with a plain 2-port connection between the two other actors (const and 2nd display).
// When running this model it still behaves as though all are connected via the vertex!
public class ConnectionReconnectFeature extends DefaultReconnectionFeature {

  public ConnectionReconnectFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public void postReconnect(IReconnectionContext context) {
    Anchor oldAnchor = context.getOldAnchor();
    Anchor newAnchor = context.getNewAnchor();
    Relation relation = (Relation) getBusinessObjectForPictogramElement(context.getConnection());
    NamedObj oldBO = (NamedObj) getBusinessObjectForPictogramElement(oldAnchor);
    NamedObj newBO = (NamedObj) getBusinessObjectForPictogramElement(newAnchor);
    relation.unlink(oldBO);
    relation.link(newBO);
    super.postReconnect(context);
  }
}
