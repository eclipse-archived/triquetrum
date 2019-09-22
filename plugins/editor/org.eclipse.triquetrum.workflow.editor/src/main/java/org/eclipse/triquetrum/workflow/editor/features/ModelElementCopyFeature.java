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
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.AbstractCopyFeature;

public class ModelElementCopyFeature extends AbstractCopyFeature {

  public ModelElementCopyFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canCopy(ICopyContext context) {
    final PictogramElement[] pes = context.getPictogramElements();
    if (pes == null || pes.length == 0) { // nothing selected
      return false;
    }
    // apparently the Diagram can end up in the copy context, when no concrete model element is selected
    if (pes.length == 1 && pes[0] instanceof Diagram) {
      return false;
    }
    return true;
  }

  @Override
  public void copy(ICopyContext context) {
    // We need to pass the PEs as we need to be able to determine original location info during the paste.
    putToClipboard(context.getPictogramElements());
  }
}
