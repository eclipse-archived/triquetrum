/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.wizard;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.triquetrum.workflow.editor.Messages;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

/**
 * The Class CreateDiagramWizard.
 */
public class CreateDiagramWizard extends BasicNewResourceWizard {

  private static final String PAGE_NAME_DIAGRAM_TYPE = Messages.CreateDiagramWizard_DiagramTypeField;
  private static final String PAGE_NAME_DIAGRAM_NAME = Messages.CreateDiagramWizard_DiagramNameField;
  private static final String WIZARD_WINDOW_TITLE = Messages.CreateDiagramWizard_WizardTitle;

  @Override
  public void addPages() {
    super.addPages();
    addPage(new DiagramTypeWizardPage(PAGE_NAME_DIAGRAM_TYPE));
    addPage(new DiagramNameWizardPage(PAGE_NAME_DIAGRAM_NAME));
  }

  @Override
  public boolean canFinish() {
    return super.canFinish();
  }

  @Override
  public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
    super.init(workbench, currentSelection);
    setWindowTitle(WIZARD_WINDOW_TITLE);
  }

  @Override
  public boolean performFinish() {
    ITextProvider typePage = (ITextProvider) getPage(PAGE_NAME_DIAGRAM_TYPE);
    final String diagramTypeId = typePage.getText();

    ITextProvider namePage = (ITextProvider) getPage(PAGE_NAME_DIAGRAM_NAME);
    final String diagramName = namePage.getText();

    IProject project = null;
    IFolder diagramFolder = null;

    Object element = getSelection().getFirstElement();
    if (element instanceof IProject) {
      project = (IProject) element;
    } else if (element instanceof IFolder) {
      diagramFolder = (IFolder) element;
      project = diagramFolder.getProject();
    }

    if (project == null || !project.isAccessible()) {
      String error = Messages.CreateDiagramWizard_NoProjectFoundError;
      IStatus status = new Status(IStatus.ERROR, TriqEditorPlugin.getID(), error);
      ErrorDialog.openError(getShell(), Messages.CreateDiagramWizard_NoProjectFoundErrorTitle, null, status);
      return false;
    }

    try {
      Diagram diagram = WizardUtils.createDiagramAndFile(diagramTypeId, diagramName, project, diagramFolder);
      WizardUtils.openDiagramInEditor(diagram);
      return true;
    } catch (PartInitException e) {
      String error = Messages.CreateDiagramWizard_OpeningEditorError;
      IStatus status = new Status(IStatus.ERROR, TriqEditorPlugin.getID(), error, e);
      ErrorDialog.openError(getShell(), Messages.CreateDiagramWizard_ErrorOccuredTitle, null, status);
      return false;
    }
  }
}
