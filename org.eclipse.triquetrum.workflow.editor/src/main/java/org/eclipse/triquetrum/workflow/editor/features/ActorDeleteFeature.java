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

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.model.Actor;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;

public class ActorDeleteFeature extends DefaultDeleteFeature {

  public ActorDeleteFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canDelete(IDeleteContext context) {
    BoCategory boCategory = BoCategory.retrieveFrom(context.getPictogramElement());
    return (BoCategory.Actor.equals(boCategory));
  }


  @Override
  protected void deleteBusinessObject(Object bo) {
    Actor actor = (Actor) bo;
    // TODO adapt when Triq supports relations between >2 ports
    // then we should handle deletion of individual links i.o directly deleting complete relations (cfr ptolemy)
    for(Port p : actor.getInputPorts()) {
      deleteLinkedRelations(p);
    }
    for(Port p : actor.getOutputPorts()) {
      deleteLinkedRelations(p);
    }
    super.deleteBusinessObject(bo);
  }

  private void deleteLinkedRelations(Port p) {
    Collection<Relation> relations = new HashSet<>(p.getLinkedRelations());
    for(Relation r : relations) {
      super.deleteBusinessObject(r);
    }
  }

}
