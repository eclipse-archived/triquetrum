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
package org.eclipse.triquetrum.workflow.event;

import org.eclipse.triquetrum.ProcessingStatus;
import org.eclipse.triquetrum.workflow.ProcessEvent;

public class ResumeEvent extends ProcessEvent {
  private static final long serialVersionUID = 5364678627782578860L;

  public ResumeEvent(String processContextId) {
    super(processContextId, ProcessingStatus.ACTIVE);
  }

  public ResumeEvent(String processContextId, String breakpointName) {
    this(processContextId);
    this.putProperty(BREAKPOINTS, breakpointName);
  }
}
