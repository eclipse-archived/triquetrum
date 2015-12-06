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
package org.eclipse.triquetrum.workflow.execution.impl.debug;

import org.eclipse.triquetrum.workflow.execution.impl.executor.WorkflowExecutionTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.actor.CompositeActor;
import ptolemy.actor.IOPortEvent;
import ptolemy.kernel.Port;
import ptolemy.kernel.util.DebugEvent;
import ptolemy.kernel.util.DebugListener;

public class PortBreakpointListener implements DebugListener {
  private final static Logger LOGGER = LoggerFactory.getLogger(PortBreakpointListener.class);
  private static final int BREAKPOINT_EVENT_TYPE_INPUT_PORT = IOPortEvent.GET_END;
  private static final int BREAKPOINT_EVENT_TYPE_OUTPUT_PORT = IOPortEvent.SEND_BEGIN;

  private WorkflowExecutionTask fet;
  private String name;
  
  public PortBreakpointListener(String name, WorkflowExecutionTask fet) {
    this.fet = fet;
    this.name = name;
  }

  @Override
  public void event(DebugEvent event) {
    if (event instanceof IOPortEvent) {
      IOPortEvent pe = (IOPortEvent) event;
      if ((BREAKPOINT_EVENT_TYPE_INPUT_PORT == pe.getEventType()) 
          || (BREAKPOINT_EVENT_TYPE_OUTPUT_PORT == pe.getEventType())) {
        Port p = pe.getPort();
        LOGGER.info("Suspend on breakpoint {}", p.getFullName());
        if(fet!=null) {
          fet.addSuspendedElement(name);
        }
        ((CompositeActor)p.toplevel()).getManager().pauseOnBreakpoint(p.getFullName());
      }
    }
  }

  @Override
  public void message(String message) {
    // ignore in this listener
  }
}
