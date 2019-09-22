/*******************************************************************************
 * Copyright (c) 2015,2019 iSencia Belgium NV.
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
package org.eclipse.triquetrum.workflow.execution.impl.debug;

import org.eclipse.triquetrum.workflow.execution.impl.executor.WorkflowExecutionTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.actor.Actor;
import ptolemy.actor.FiringEvent;
import ptolemy.actor.FiringEvent.FiringEventType;
import ptolemy.kernel.util.DebugEvent;
import ptolemy.kernel.util.DebugListener;

public class ActorBreakpointListener implements DebugListener {
  private final static Logger LOGGER = LoggerFactory.getLogger(ActorBreakpointListener.class);
  private final static FiringEventType BREAKPOINT_EVENT_TYPE = FiringEvent.BEFORE_FIRE;

  private WorkflowExecutionTask fet;
  private String name;

  public ActorBreakpointListener(String name, WorkflowExecutionTask fet) {
    this.fet = fet;
    this.name = name;
  }

  @Override
  public void event(DebugEvent event) {
    if (event instanceof FiringEvent) {
      FiringEvent fe = (FiringEvent) event;
      if (BREAKPOINT_EVENT_TYPE.equals(fe.getType())) {
        Actor a = fe.getActor();
        LOGGER.info("Suspend on breakpoint {}", name);
        if (fet != null) {
          fet.addSuspendedElement(name);
        }
        a.getManager().pauseOnBreakpoint(name);
      }
    }
  }

  @Override
  public void message(String message) {
    // ignore in this listener
  }
}
