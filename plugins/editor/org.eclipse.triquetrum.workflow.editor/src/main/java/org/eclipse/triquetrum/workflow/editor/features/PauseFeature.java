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
package org.eclipse.triquetrum.workflow.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.triquetrum.TriqException;
import org.eclipse.triquetrum.workflow.ProcessHandle;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService;
import org.eclipse.triquetrum.workflow.model.CompositeActor;

public class PauseFeature extends AbstractExecutionManagementFeature {

  public static final String HINT = "pause"; //$NON-NLS-1$

  public PauseFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public String getName() {
    return "Pause Triq workflow execution"; //$NON-NLS-1$
  }

  @Override
  protected boolean isAvailableForSelection(CompositeActor selection) {
    return getProcessHandleForSelection(selection) != null;
  }

  @Override
  protected void doExecute(WorkflowExecutionService executionService, CompositeActor selection) throws TriqException {
    ProcessHandle processHandle = getProcessHandleForSelection(selection);
    executionService.suspend(processHandle);
  };
}