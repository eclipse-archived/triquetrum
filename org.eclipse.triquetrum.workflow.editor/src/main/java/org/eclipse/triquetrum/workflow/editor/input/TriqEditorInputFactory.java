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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.graphiti.ui.internal.util.ReflectionUtil;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

public class TriqEditorInputFactory implements IElementFactory {

  @Override
  public IAdaptable createElement(IMemento memento) {
    // get diagram URI
    final String diagramUriString = memento.getString(TriqEditorInput.KEY_URI);
    if (diagramUriString == null) {
      return null;
    }
    // get diagram type provider id
    final String providerID = memento.getString(TriqEditorInput.KEY_PROVIDER_ID);
    URI diagramUri = URI.createURI(diagramUriString);
    return new TriqEditorInput(diagramUri, providerID);
  }

  public static DiagramEditorInput adaptToDiagramEditorInput(IEditorInput otherInput) {
    if (otherInput instanceof DiagramEditorInput) {
      DiagramEditorInput input = (DiagramEditorInput) otherInput;
      return input;
    }
    IFile file = ReflectionUtil.getFile(otherInput);
    if (file != null) {
      URI diagramFileUri = GraphitiUiInternal.getEmfService().getFileURI(file);
      return createDiagramEditorInput(diagramFileUri);
    }
    if (otherInput instanceof URIEditorInput) {
      final URIEditorInput uriInput = (URIEditorInput) otherInput;
      URI diagramFileUri = uriInput.getURI();
      return createDiagramEditorInput(diagramFileUri);
    }

    return null;
  }

  private static DiagramEditorInput createDiagramEditorInput(URI diagramFileUri) {
    if (diagramFileUri != null) {
      // the file's first base node has to be a diagram
      URI diagramUri = GraphitiUiInternal.getEmfService().mapDiagramFileUriToDiagramUri(diagramFileUri);
      // take the first installed provider for this diagram type
      return new TriqEditorInput(diagramUri, null);
    }
    return null;
  }
}
