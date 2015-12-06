/*******************************************************************************
 * Copyright (c) 2015 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.input;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;

public class TriqEditorInput extends DiagramEditorInput {

  public TriqEditorInput(URI diagramUri, String providerId) {
    super(diagramUri, providerId);
  }
  
  @Override
  public String getFactoryId() {
    return TriqEditorInputFactory.class.getName();
  }

  @Override
  public Object getAdapter(Class adapter) {
    if (IResource.class.isAssignableFrom(adapter)) {
      return GraphitiUiInternal.getEmfService().getFile(getUri());
      } 
    return null;

  }
}
