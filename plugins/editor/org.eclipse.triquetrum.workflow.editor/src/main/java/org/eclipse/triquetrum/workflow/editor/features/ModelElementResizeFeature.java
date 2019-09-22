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
import org.eclipse.graphiti.features.IResizeConfiguration;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;

public class ModelElementResizeFeature extends DefaultResizeShapeFeature {

  public ModelElementResizeFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public IResizeConfiguration getResizeConfiguration(IResizeShapeContext context) {
    PictogramElement pe = context.getPictogramElement();
    BoCategory boCategory = BoCategory.retrieveFrom(pe);
    if (BoCategory.CompositeActor.equals(boCategory) && !EditorUtils.isCollapsed(pe)) {
      return super.getResizeConfiguration(context);
    } else {
      return new NoResizeConfiguration();
    }
  }

  private static class NoResizeConfiguration implements IResizeConfiguration {
    @Override
    public boolean isVerticalResizeAllowed() {
      return false;
    }

    @Override
    public boolean isHorizontalResizeAllowed() {
      return false;
    }
  }

}
