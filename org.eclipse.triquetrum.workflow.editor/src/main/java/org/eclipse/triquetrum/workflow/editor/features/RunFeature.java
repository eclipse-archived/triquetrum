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
import org.eclipse.triquetrum.workflow.ProcessHandle;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService.StartMode;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.triquetrum.workflow.editor.TriqToolBehaviorProvider;
import org.eclipse.triquetrum.workflow.model.CompositeActor;

public class RunFeature extends AbstractExecutionManagementFeature {

  public static final String HINT = "run"; //$NON-NLS-1$

  public RunFeature(TriqToolBehaviorProvider tbp, IFeatureProvider fp) {
    super(tbp, fp);
  }

  @Override
  public String getName() {
    return "Run Triq workflow"; //$NON-NLS-1$
  }

  @Override
  protected boolean isAvailableForSelection(CompositeActor selection) {
    return (getProcessHandleForSelection(selection)==null);
  }

  @Override
  protected void doExecute(WorkflowExecutionService executionService, CompositeActor selection) {
    // make sure that logging is shown in the console view
    TriqEditorPlugin.getDefault().initConsoleLogging();

    ptolemy.actor.CompositeActor ptolemyModel = (ptolemy.actor.CompositeActor) selection.getWrappedObject();
    ProcessHandle workflowExecutionHandle = executionService.start(StartMode.RUN, ptolemyModel, null, null, null);
    try {
      storeProcessHandle(selection, workflowExecutionHandle);
    } catch (IllegalStateException e) {
      // TODO stop the model immediately?
    }
  }
}