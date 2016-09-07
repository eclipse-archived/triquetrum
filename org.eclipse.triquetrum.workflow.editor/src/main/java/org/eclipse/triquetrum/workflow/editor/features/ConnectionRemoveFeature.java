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
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.triquetrum.workflow.model.Linkable;
import org.eclipse.triquetrum.workflow.model.Relation;

public class ConnectionRemoveFeature extends DefaultRemoveFeature {

  public ConnectionRemoveFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canRemove(IRemoveContext context) {
    PictogramElement connectionPE = context.getPictogramElement();
    if (connectionPE instanceof Connection) {
      Object bo = getBusinessObjectForPictogramElement(context.getPictogramElement());
      return (bo instanceof Relation);
    } else {
      return false;
    }
  }

  @Override
  public void remove(IRemoveContext context) {
    Connection connectionPE = (Connection) context.getPictogramElement();
    Relation relation = (Relation) getBusinessObjectForPictogramElement(connectionPE);
    if (relation != null) {
      Linkable startBO = (Linkable) getBusinessObjectForPictogramElement(connectionPE.getStart());
      Linkable endBO = (Linkable) getBusinessObjectForPictogramElement(connectionPE.getEnd());
      startBO.unlink(relation);
      endBO.unlink(relation);
      if(!relation.isConnected()) {
        // TODO check if/how we might want keep an unconnected Vertex around after all links were deleted/removed
        // I guess with the check above, such vertex would be deleted as well at the moment the last connection/link is removed/deleted.
        EcoreUtil.delete(relation, true);
      }
    }
    super.remove(context);
  }
}
