/*******************************************************************************
 * Copyright (c) 2015-2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.wizard;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramEditor;
import org.eclipse.triquetrum.workflow.editor.util.FileService;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * A collection of some utilities for usage by new/import/export wizards.
 *
 */
public class WizardUtils {

  /**
   *
   * @param diagramTypeId
   * @param diagramName
   * @param project
   * @param diagramFolder
   * @return
   */
  public static Diagram createDiagramAndFile(final String diagramTypeId, final String diagramName, IProject project, IFolder diagramFolder) {
    Diagram diagram = Graphiti.getPeCreateService().createDiagram(diagramTypeId, diagramName, true);
    if (diagramFolder == null) {
      diagramFolder = project.getFolder("workflows"); //$NON-NLS-1$
    }

    String editorID = TriqDiagramEditor.EDITOR_ID;
    String editorExtension = TriqDiagramEditor.DIAGRAM_FILE_EXTENSION; //$NON-NLS-1$
    String diagramTypeProviderId = GraphitiUi.getExtensionManager().getDiagramTypeProviderId(diagramTypeId);
    String namingConventionID = diagramTypeProviderId + ".editor"; //$NON-NLS-1$
    IEditorDescriptor specificEditor = PlatformUI.getWorkbench().getEditorRegistry().findEditor(namingConventionID);

    // If there is a specific editor get the file extension
    if (specificEditor != null) {
      editorID = namingConventionID;
      IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
      IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint("org.eclipse.ui.editors"); //$NON-NLS-1$
      IExtension[] extensions = extensionPoint.getExtensions();
      for (IExtension ext : extensions) {
        IConfigurationElement[] configurationElements = ext.getConfigurationElements();
        for (IConfigurationElement ce : configurationElements) {
          String id = ce.getAttribute("id"); //$NON-NLS-1$
          if (editorID.equals(id)) {
            String fileExt = ce.getAttribute("extensions"); //$NON-NLS-1$
            if (fileExt != null) {
              editorExtension = fileExt;
              break;
            }
          }

        }
      }
    }

    IFile diagramFile = diagramFolder.getFile(diagramName + "." + editorExtension); //$NON-NLS-1$
    URI uri = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);

    FileService.createEmfFileForDiagram(uri, diagram);
    return diagram;
  }

  public static void openDiagramInEditor(Diagram diagram) throws PartInitException {
    String editorID = TriqDiagramEditor.EDITOR_ID;
    String diagramTypeProviderId = GraphitiUi.getExtensionManager().getDiagramTypeProviderId(diagram.getDiagramTypeId());
    String namingConventionID = diagramTypeProviderId + ".editor"; //$NON-NLS-1$
    IEditorDescriptor specificEditor = PlatformUI.getWorkbench().getEditorRegistry().findEditor(namingConventionID);

    // If there is a specific editor get the file extension
    if (specificEditor != null) {
      editorID = namingConventionID;
    }
    String providerId = GraphitiUi.getExtensionManager().getDiagramTypeProviderId(diagram.getDiagramTypeId());
    DiagramEditorInput editorInput = new DiagramEditorInput(EcoreUtil.getURI(diagram), providerId);

    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(editorInput, editorID);
  }

  /**
   * Iterate over all model elements in a Ptolemy II model, create corresponding elements in a Triq workflow diagram,
   * and store the diagram file in the given destination folder.
   *
   * @param destFolder
   * @param diagram
   * @param ptolemyModel
   * @return
   */
  public static Diagram fillDiagramFromPtolemyModel(IFolder destFolder, Diagram diagram, ptolemy.actor.CompositeActor ptolemyModel) {
    // Get the default resource set to hold the new resource
    ResourceSet resourceSet = new ResourceSetImpl();
    TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(resourceSet);
    if (editingDomain == null) {
      editingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet);
    }

    FillDiagramFromPtolemyModelCommand operation = new FillDiagramFromPtolemyModelCommand(destFolder, editingDomain, diagram, ptolemyModel);
    editingDomain.getCommandStack().execute(operation);
    try {
      operation.getCreatedResource().save(Collections.EMPTY_MAP);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Dispose the editing domain to eliminate memory leak
    editingDomain.dispose();

    return diagram;
  }
}
