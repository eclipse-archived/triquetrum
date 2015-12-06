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
package org.eclipse.triquetrum.workflow.execution.impl.event;


public class BreakpointSuspendEvent extends SuspendEvent {
  private static final long serialVersionUID = -1482109949639212381L;

  public BreakpointSuspendEvent(String processContextId, String breakpointName) {
    super(processContextId);
    this.putProperty(BREAKPOINTS, breakpointName);
  }
}
