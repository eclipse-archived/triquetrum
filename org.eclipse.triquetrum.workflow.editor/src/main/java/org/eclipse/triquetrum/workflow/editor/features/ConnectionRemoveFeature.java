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
import org.eclipse.triquetrum.workflow.model.Relation;

public class ConnectionRemoveFeature extends DefaultRemoveFeature {

  public ConnectionRemoveFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canRemove(IRemoveContext context) {
    Object bo = getBusinessObjectForPictogramElement(context.getPictogramElement());
    return (bo instanceof Relation);
  }

  @Override
  public void remove(IRemoveContext context) {
    Relation relation = (Relation) getBusinessObjectForPictogramElement(context.getPictogramElement());
    if(relation!=null) {
      EcoreUtil.delete(relation, true);
    }
    super.remove(context);
  }

}
