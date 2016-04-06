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
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionReconnectFeature extends DefaultReconnectionFeature {
  private final static Logger LOGGER = LoggerFactory.getLogger(ConnectionReconnectFeature.class);

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
    unlink(relation, oldBO);
    link(relation, newBO);
    super.postReconnect(context);
  }

  private void unlink(Relation relation, NamedObj anchorBO) {
    LOGGER.trace("unlink {} - {}", relation, anchorBO);
    if(anchorBO instanceof Port) {
      relation.getLinkedPorts().remove(anchorBO);
    } else if(anchorBO instanceof Vertex) {
      relation.getLinkedRelations().remove(anchorBO.getContainer());
    }
  }
  private void link(Relation relation, NamedObj anchorBO) {
    LOGGER.trace("link {} - {}", relation, anchorBO);
    if(anchorBO instanceof Port) {
      relation.getLinkedPorts().add((Port) anchorBO);
    } else if(anchorBO instanceof Vertex) {
      relation.getLinkedRelations().add((Relation) anchorBO.getContainer());
    }
  }

}
