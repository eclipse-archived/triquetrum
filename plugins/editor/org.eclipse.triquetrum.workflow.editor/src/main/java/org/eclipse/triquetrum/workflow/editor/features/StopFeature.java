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
import org.eclipse.triquetrum.TriqException;
import org.eclipse.triquetrum.workflow.ProcessHandle;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService;
import org.eclipse.triquetrum.workflow.model.CompositeActor;

public class StopFeature extends AbstractExecutionManagementFeature {

  public static final String HINT = "stop"; //$NON-NLS-1$

  public StopFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public String getName() {
    return "Stop Triq workflow execution"; //$NON-NLS-1$
  }

  @Override
  protected boolean isAvailableForSelection(CompositeActor selection) {
    return getProcessHandleForSelection(selection) != null;
  }

  @Override
  protected void doExecute(WorkflowExecutionService executionService, CompositeActor selection) throws TriqException {
    ProcessHandle processHandle = getProcessHandleForSelection(selection);
    executionService.terminate(processHandle);
    removeProcessHandle(processHandle);
  };
}