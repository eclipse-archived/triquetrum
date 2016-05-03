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
package org.eclipse.triquetrum.workflow.editor;

import java.io.File;
import java.util.Arrays;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.jface.util.TransferDragSourceListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorActionBarContributor;

public class TriqDiagramEditor extends DiagramEditor {
  public final static String EDITOR_ID = "org.eclipse.triquetrum.workflow.editor.TriqDiagramEditor";
  public final static String DIAGRAM_FILE_EXTENSION = "tdml";


  @Override
  protected DiagramBehavior createDiagramBehavior() {
    return new TriqDiagramBehavior(this);
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

    String[] fileExtensionFilters = new String[] { "*."+DIAGRAM_FILE_EXTENSION };
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

  public class SelectionDragAdapter extends DragSourceAdapter implements TransferDragSourceListener {
    private TreeViewer fViewer;

    public SelectionDragAdapter(TreeViewer viewer) {
      Assert.isNotNull(viewer);
      fViewer = viewer;
    }

    public Transfer getTransfer() {
      return TemplateTransfer.getInstance();
    }

    public void dragStart(DragSourceEvent event) {
      ISelection _selection = fViewer.getSelection();
      boolean doit = !_selection.isEmpty();
      if (doit) {
        if (_selection instanceof ITreeSelection) {
          ITreeSelection selection = (ITreeSelection) _selection;
          final Object selected = selection.getFirstElement();
          if (selected instanceof PaletteEntry) {
            TemplateTransfer.getInstance().setTemplate(new PaletteItemDefCreationFactory((PaletteEntry) selected));
          }
        } else {
          doit = false;
        }
      }
      event.doit = doit;
    }

    public void dragSetData(DragSourceEvent event) {
      event.data = TemplateTransfer.getInstance().getTemplate();
    }

    public void dragFinished(DragSourceEvent event) {
      TemplateTransfer.getInstance().setTemplate(null);
    }
  }

  public class PaletteItemDefCreationFactory implements CreationFactory {
    PaletteEntry selected;

    public PaletteItemDefCreationFactory(PaletteEntry selected) {
      this.selected = selected;
    }

    public Object getObjectType() {
      return ICreateFeature.class;
    }

    public Object getNewObject() {
//      return new ModelElementCreateFeature(selected, getDiagramTypeProvider().getFeatureProvider());
      return null;
    }
  }
}
