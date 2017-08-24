/*******************************************************************************
 * Copyright (c) 2017 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.palette.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.ui.internal.editor.GFCreationTool;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramEditor;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.triquetrum.workflow.editor.TriqFeatureProvider;
import org.eclipse.triquetrum.workflow.editor.palette.LibraryManager;
import org.eclipse.triquetrum.workflow.editor.palette.TriqPaletteRoot;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.rcp.ModelHandleTransfer;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.service.event.Event;

public class UserLibraryTransferDropListener extends AbstractTransferDropTargetListener {

  private AocPaletteEntryFactory factory = new AocPaletteEntryFactory();

  public UserLibraryTransferDropListener(EditPartViewer viewer) {
    super(viewer, ModelHandleTransfer.getInstance());
  }

  protected Request createTargetRequest() {
    CreateRequest request = new CreateRequest();
    request.setFactory(factory);
    return request;
  }

  @Override
  protected void handleDrop() {
    factory.handle = (ModelHandle) getCurrentEvent().data;
    factory.targetTreeNode = (PaletteTreeNodeEditPart) getCurrentEvent().item.getData();
    // We need to override the default logic to find the target edit part for the drop,
    // as this is failing with our hybrid GEF/JFace Tree implementation.
    // So, no call to super.handleDrop() here, but a slightly modified reimplementation.
    // We need the direct setTargetEditPart as the default logic is failing.
    updateTargetRequest();
    setTargetEditPart(factory.targetTreeNode);

    if (getTargetEditPart() != null) {
      Command command = getCommand();
      if (command != null && command.canExecute())
        getViewer().getEditDomain().getCommandStack().execute(command);
      else
        getCurrentEvent().detail = DND.DROP_NONE;
    } else
      getCurrentEvent().detail = DND.DROP_NONE;
  }

  protected void handleDragOver() {
    getCurrentEvent().detail = DND.DROP_COPY;
    super.handleDragOver();
  }

  protected void updateTargetRequest() {
    ((CreateRequest) getTargetRequest()).setLocation(getDropLocation());
  }

  private static class AocPaletteEntryFactory implements CreationFactory {
    ModelHandle handle;
    PaletteTreeNodeEditPart targetTreeNode;

    @Override
    public Object getNewObject() {
      TriqDiagramEditor activeEditor = EditorUtils.getSelectedDiagramEditor();
      if (activeEditor != null) {
        Shell shell = activeEditor.getSite().getShell();
        AddActorToUserLibraryDialog dialog = new AddActorToUserLibraryDialog(shell, handle);
        dialog.setBlockOnOpen(true);
        int dialogReturnCode = dialog.open();
        if (Dialog.OK == dialogReturnCode) {
          String modelName = dialog.modelName;
          String modelClass = dialog.modelClass;
          String libraryName = targetTreeNode.getFullTreePath('.');
          String elementType = "CompositeActor";

          Map<String, String> properties = new HashMap<>();
          properties.put("displayName", modelName);
          properties.put("class", modelClass);
          properties.put("type", elementType);
          properties.put("libraryName", libraryName);

          Event event = new Event(LibraryManager.ADD_EVENT_TOPIC, properties);
          try {
            TriqEditorPlugin.getDefault().getEventAdminService().postEvent(event);
          } catch (NullPointerException e) {
            StatusManager.getManager().handle(
                new Status(IStatus.ERROR, TriqEditorPlugin.getID(), "Event bus not available, impossible to trigger an addition event for the user library."),
                StatusManager.BLOCK);
          }
          TriqFeatureProvider featureProvider = (TriqFeatureProvider) activeEditor.getDiagramTypeProvider().getFeatureProvider();
          ICreateFeature createFeature = featureProvider.buildCreateFeature(null, null, modelName, modelClass, null, null,
              BoCategory.CompositeActor, null);
          TriqPaletteRoot.DefaultCreationFactory cf = new TriqPaletteRoot.DefaultCreationFactory(createFeature, ICreateFeature.class);

          CombinedTemplateCreationEntry pe = new CombinedTemplateCreationEntry(modelName, modelClass, cf, cf, null, null);
          pe.setToolClass(GFCreationTool.class);
          return pe;
        }
      }
      return null;
    }

    @Override
    public Object getObjectType() {
      return CombinedTemplateCreationEntry.class;
    }
  }
}
