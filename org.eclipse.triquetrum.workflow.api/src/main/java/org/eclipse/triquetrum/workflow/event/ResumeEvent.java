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
