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
package org.eclipse.triquetrum.workflow.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.triquetrum.workflow.ProcessHandle;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.triquetrum.workflow.editor.TriqToolBehaviorProvider;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.CompositeActor;

public class StopFeature extends AbstractCustomFeature {

  public static final String HINT = "stop"; //$NON-NLS-1$

  private TriqToolBehaviorProvider toolProvider;

  public StopFeature(TriqToolBehaviorProvider tbp, IFeatureProvider fp) {
    super(fp);
    toolProvider = tbp;
  }

  @Override
  public String getName() {
    return "Stop Triq workflow execution"; //$NON-NLS-1$
  }

  @Override
  public boolean hasDoneChanges() {
    return false;
  }

  @Override
  public boolean isAvailable(IContext context) {
    boolean available = super.isAvailable(context) && (TriqEditorPlugin.getDefault().getWorkflowExecutionService() != null);
    if(available) {
      CompositeActor selection = EditorUtils.getSelectedModel();
      available = (selection != null) && (toolProvider.getWorkflowExecutionHandle(selection.getName())!=null);
    }
    return available;
  }

  @Override
  public boolean canExecute(ICustomContext context) {
    return isAvailable(context);
  }

  public void execute(ICustomContext context) {
    try {
      WorkflowExecutionService executionService = TriqEditorPlugin.getDefault().getWorkflowExecutionService();
      if (executionService != null) {
        CompositeActor selection = null;
        try {
          // FIXME we assume that the diagram/editor is the selected gui element, or an entity in there. Is this a robust assumption?
          selection = EditorUtils.getSelectedModel();
          if (selection != null) {
            ProcessHandle processHandle = toolProvider.getWorkflowExecutionHandle(selection.getName());
            executionService.terminate(processHandle);
            toolProvider.removeWorkflowExecutionHandle(processHandle);
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
}