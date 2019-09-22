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
package org.eclipse.triquetrum.workflow.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.triquetrum.TriqException;
import org.eclipse.triquetrum.workflow.ProcessHandle;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService;
import org.eclipse.triquetrum.workflow.editor.ExecutionStatusManager;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.CompositeActor;

public abstract class AbstractExecutionManagementFeature extends AbstractCustomFeature {

  public AbstractExecutionManagementFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean hasDoneChanges() {
    return false;
  }

  @Override
  public boolean isAvailable(IContext context) {
    boolean available = super.isAvailable(context) && (TriqEditorPlugin.getDefault().getWorkflowExecutionService() != null);
    if (available) {
      CompositeActor selection = EditorUtils.getSelectedModel();
      available = (selection != null) && isAvailableForSelection(selection);
    }
    return available;
  }

  /**
   * Implement this method to define whether the execution-management-related feature should be available for the given selection.
   * <p>
   * Typically this method checks whether there is already an execution ongoing for the selection or not.
   * </p>
   * 
   * @param selection
   * @return
   */
  protected abstract boolean isAvailableForSelection(CompositeActor selection);

  @Override
  public boolean canExecute(ICustomContext context) {
    return isAvailable(context);
  }

  @Override
  public void execute(ICustomContext context) {
    try {
      WorkflowExecutionService executionService = TriqEditorPlugin.getDefault().getWorkflowExecutionService();
      if (executionService != null) {
        // make sure that logging is shown in the console view
        TriqEditorPlugin.getDefault().initConsoleLogging();
        CompositeActor selection = null;
        try {
          // FIXME we assume that the diagram/editor is the selected gui element, or an entity in there. Is this a robust assumption?
          selection = EditorUtils.getSelectedModel();
          if (selection != null) {
            doExecute(executionService, selection);
          } else {
            // TODO ignore or add error handling?
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        // TODO add error handling for configuration problem with missing execution service
      }
    } catch (Exception e) {
      // TODO add error handling (e.g. eclipse error log? error popup?)
    }
  }

  /**
   * This method must implement the actual feature's behaviour for the given selection.
   *
   * @param executionService
   *          guaranteed to be non-null
   * @param selection
   *          guaranteed to be non-null
   * @throws TriqException
   */
  protected abstract void doExecute(WorkflowExecutionService executionService, CompositeActor selection) throws TriqException;

  protected ProcessHandle getProcessHandleForSelection(CompositeActor selection) {
    return ExecutionStatusManager.getWorkflowExecutionHandle(selection.getName());
  }

  protected void storeProcessHandle(CompositeActor selection, ProcessHandle handle) {
    ExecutionStatusManager.putWorkflowExecutionHandle(selection.getName(), handle);
  }

  protected ProcessHandle removeProcessHandle(CompositeActor selection) {
    return ExecutionStatusManager.removeWorkflowExecutionHandle(selection.getName());
  }

  protected ProcessHandle removeProcessHandle(ProcessHandle handle) {
    return ExecutionStatusManager.removeWorkflowExecutionHandle(handle.getModelHandle().getCode());
  }

  protected void fireExecutionStatusChange(ProcessHandle handle) {
    ExecutionStatusManager.getInstance().fireStatusChanged(handle);
  }
}