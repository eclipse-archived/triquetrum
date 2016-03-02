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

import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.triquetrum.workflow.editor.Messages;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramTypeProvider;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.triquetrum.workflow.util.WorkflowUtils;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

import ptolemy.actor.CompositeActor;

/**
 * A wizard to import a model that was prepared in Ptolemy II and stored in MOML/XML.
 *
 */
public class ImportFromMomlWizard extends Wizard implements IImportWizard {

  private ImportFromMomlWizardPage page;
  private IStructuredSelection selection;

  @Override
  public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
    this.selection = currentSelection;

    List<?> selectedResources = IDE.computeSelectedResources(currentSelection);
    if (!selectedResources.isEmpty()) {
        this.selection = new StructuredSelection(selectedResources);
    }
  }

  @Override
  public void addPages() {
    setWindowTitle("Import Ptolemy II MOML file");
    page = new ImportFromMomlWizardPage("", selection);
    addPage(page);
  }

  @Override
  public boolean performFinish() {
    IPath momlPath = page.getAbsoluteMomlPath();
    IPath destPath = page.getDestinationContainerPath();
    System.out.println(destPath);

    IResource destResource = ResourcesPlugin.getWorkspace().getRoot().findMember(destPath);
    IFolder destFolder = null;
    IProject destProject = null;
    if(destResource instanceof IFolder) {
      destFolder = (IFolder) destResource;
    } else if(destResource instanceof IProject) {
      destProject = (IProject )destResource;
      destFolder = destProject.getFolder("workflows"); //$NON-NLS-1$
    }
    try {
      CompositeActor ptolemyModel = WorkflowUtils.readFrom(momlPath.toFile().toURI());
      // create an empty diagram with the correct type and name
      Diagram diagram = Graphiti.getPeCreateService().createDiagram(TriqDiagramTypeProvider.DIAGRAMTYPE, momlPath.removeFileExtension().lastSegment(), true);
      // now create diagram elements for all moml model elements
      WizardUtils.fillDiagramFromPtolemyModel(destFolder, diagram, ptolemyModel);
      // finally, show the imported model in the Triq editor
      WizardUtils.openDiagramInEditor(diagram);
    } catch (PartInitException e) {
      String error = Messages.CreateDiagramWizard_OpeningEditorError;
      IStatus status = new Status(IStatus.ERROR, TriqEditorPlugin.getID(), error, e);
      ErrorDialog.openError(getShell(), Messages.CreateDiagramWizard_ErrorOccuredTitle, null, status);
      return false;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return true;
  }
}
