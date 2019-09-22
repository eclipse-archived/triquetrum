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
package org.eclipse.triquetrum.workflow.editor.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;
import org.eclipse.triquetrum.workflow.model.NamedObj;

public class TriqNamedObjFilter extends AbstractPropertySectionFilter {

  @Override
  protected boolean accept(PictogramElement pe) {
    EObject eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
    if (eObject instanceof NamedObj) {
      return true;
    }
    return false;
  }

}
