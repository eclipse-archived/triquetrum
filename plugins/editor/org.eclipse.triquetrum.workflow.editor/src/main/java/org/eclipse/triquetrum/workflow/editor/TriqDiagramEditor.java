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
package org.eclipse.triquetrum.workflow.editor;

import java.io.File;
import java.util.Arrays;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.triquetrum.workflow.editor.outline.DiagramEditorOutlinePage;
import org.eclipse.triquetrum.workflow.editor.views.TriqSelectionSynchronizer;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.util.PtObjectBuilderVisitor;
import org.eclipse.triquetrum.workflow.model.util.PtObjectLinkerVisitor;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorActionBarContributor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class TriqDiagramEditor extends DiagramEditor {
  public final static String EDITOR_ID = "org.eclipse.triquetrum.workflow.editor.TriqDiagramEditor";
  public final static String DIAGRAM_FILE_EXTENSION = "tdml";
  private SelectionSynchronizer synchronizer;

  @Override
  public void init(IEditorSite site, IEditorInput input) throws PartInitException {
    super.init(site, input);

    Diagram d = getDiagramTypeProvider().getDiagram();
    Object toplevel = getDiagramTypeProvider().getFeatureProvider().getBusinessObjectForPictogramElement(d);
    if (toplevel != null && toplevel instanceof NamedObj) {
      ((NamedObj) toplevel).welcome(new PtObjectBuilderVisitor(), true);
      ((NamedObj)toplevel).welcome(new PtObjectLinkerVisitor(), true);
    }
  }

  @Override
  protected DiagramBehavior createDiagramBehavior() {
    return new TriqDiagramBehavior(this);
  }

  @Override
  public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
    if (IContentOutlinePage.class.equals(type)) {
      DiagramEditorOutlinePage outlinePage = new DiagramEditorOutlinePage(this);
      return outlinePage;
    }
    return super.getAdapter(type);
  }

  @Override
  protected DiagramEditorInput convertToDiagramEditorInput(IEditorInput input) throws PartInitException {
    try {
      return super.convertToDiagramEditorInput(input);
    } catch (PartInitException e) {
      // Graphiti by default does not support opening diagram files outside of the workspace.
      // This is related to incompatibilities between EMF URIEditorInput and eclipse's FileStoreEditorInput,
      // for which the default DiagramEditor's adapting code is not adapted by default...
      URI uri = EditUIUtil.getURI(input);
      return super.convertToDiagramEditorInput(new URIEditorInput(uri));
    }
  }

  @Override
  public boolean isSaveAsAllowed() {
    return true;
  }

  @Override
  public void doSaveAs() {
    String[] files = selectSaveAsDestinationPath(getSite().getShell());
    if (files.length > 0) {
      URI uri = URI.createFileURI(files[0]);
      URIEditorInput editorInput = new URIEditorInput(uri);
      (getEditingDomain().getResourceSet().getResources().get(0)).setURI(uri);
      (getEditingDomain().getResourceSet().getResources().get(0)).setModified(true);
      String fileName = editorInput.getName();
      String diagramName = fileName.substring(0, fileName.lastIndexOf('.'));
      getEditingDomain().getCommandStack().execute(new AbstractCommand() {
        String oldName;

        @Override
        public boolean canExecute() {
          return true;
        }

        @Override
        public void redo() {
          if (oldName != null) {
            getDiagramTypeProvider().getDiagram().setName(oldName);
          }
        }

        @Override
        public void execute() {
          oldName = getDiagramTypeProvider().getDiagram().getName();
          getDiagramTypeProvider().getDiagram().setName(diagramName);
        }
      });
      setInputWithNotify(editorInput);
      setPartName(diagramName);
      getSite().getShell().setText(uri.toString());
      IActionBars actionBars = ((EditorActionBarContributor) getEditorSite().getActionBarContributor()).getActionBars();
      IProgressMonitor progressMonitor = actionBars.getStatusLineManager() != null ? actionBars.getStatusLineManager().getProgressMonitor()
          : new NullProgressMonitor();

      doSave(progressMonitor);
    }
  }

  public static String[] selectSaveAsDestinationPath(Shell shell) {
    FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);

    String[] fileExtensionFilters = new String[] { "*." + DIAGRAM_FILE_EXTENSION };
    ;
    fileDialog.setFilterExtensions(fileExtensionFilters);
    fileDialog.open();

    String[] filenames = fileDialog.getFileNames();
    String[] result = new String[filenames.length];
    String path = fileDialog.getFilterPath() + File.separator;
    String extension = null;

    int i = fileDialog.getFilterIndex();
    if (i != -1 && i != fileExtensionFilters.length) {
      String filter = fileExtensionFilters[i];
      int dot = filter.lastIndexOf('.');
      if (dot == 1 && filter.charAt(0) == '*') {
        extension = filter.substring(dot);
      }
    }

    // Build the result by adding the selected path and, if needed, auto-appending the extension.
    //
    if (extension != null) {
      for (i = 0; i < filenames.length; i++) {
        String filename = path + filenames[i];
        int dot = filename.lastIndexOf('.');
        if (dot == -1 || !Arrays.asList(fileExtensionFilters).contains("*" + filename.substring(dot))) {
          filename += extension;
        }
        result[i] = filename;
      }
    }
    return result;
  }

  @Override
  public void setFocus() {
    super.setFocus();
    Diagram d = getDiagramTypeProvider().getDiagram();
    if (d != null) {
      ExecutionStatusManager.getInstance().fireStatusChanged(d.getName());
    }
  }

  @Override
  public CommandStack getCommandStack() {
    return super.getCommandStack();
  }
  
  @Override
  protected SelectionSynchronizer getSelectionSynchronizer() {
    if (synchronizer == null) {
      synchronizer = new TriqSelectionSynchronizer();
    }
    return synchronizer;
  }

}
