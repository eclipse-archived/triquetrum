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
import org.eclipse.triquetrum.workflow.WorkflowExecutionService.StartMode;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.CompositeActor;

public class RunFeature extends AbstractCustomFeature {

  public static final String HINT = "run"; //$NON-NLS-1$

  private ProcessHandle workflowExecutionHandle;

  public RunFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public String getName() {
    return "Run Triq workflow"; //$NON-NLS-1$
  }

  @Override
  public boolean hasDoneChanges() {
    return false;
  }

  @Override
  public boolean isAvailable(IContext context) {
    return super.isAvailable(context) && (workflowExecutionHandle == null);
  }

  @Override
  public boolean canExecute(ICustomContext context) {
    return true;
  }

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
            ptolemy.actor.CompositeActor ptolemyModel = (ptolemy.actor.CompositeActor) selection.getWrappedObject();
            workflowExecutionHandle = executionService.start(StartMode.RUN, ptolemyModel, null, null, null);
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