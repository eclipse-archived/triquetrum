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
    }
    super.remove(context);
  }
}
