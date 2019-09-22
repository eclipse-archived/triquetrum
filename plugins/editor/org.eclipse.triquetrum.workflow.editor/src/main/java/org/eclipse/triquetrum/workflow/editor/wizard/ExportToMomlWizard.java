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
package org.eclipse.triquetrum.workflow.editor.wizard;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.ide.IDE;

/**
 *
 */
public class ExportToMomlWizard extends Wizard implements IExportWizard {

  private ExportToMomlWizardPage page;
  private IStructuredSelection selection;

  @Override
  public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
    this.selection = currentSelection;
    List<?> selectedResources = IDE.computeSelectedResources(currentSelection);
    if (!selectedResources.isEmpty()) {
      this.selection = new StructuredSelection(selectedResources);
    }

    // look it up if current selection (after resource adapting) is empty
    if (selection.isEmpty() && workbench.getActiveWorkbenchWindow() != null) {
      IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
      if (page != null) {
        IEditorPart currentEditor = page.getActiveEditor();
        if (currentEditor != null) {
          Object selectedResource = currentEditor.getEditorInput().getAdapter(IResource.class);
          if (selectedResource != null) {
            selection = new StructuredSelection(selectedResource);
          }
        }
      }
    }
  }

  @Override
  public void addPages() {
    setWindowTitle("Export to Ptolemy II MOML file");
    page = new ExportToMomlWizardPage("", selection);
    addPage(page);
  }

  @Override
  public boolean performFinish() {
    return page.finish();
  }

}
