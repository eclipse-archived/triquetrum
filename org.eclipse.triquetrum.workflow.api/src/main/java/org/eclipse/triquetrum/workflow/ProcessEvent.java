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
package org.eclipse.triquetrum.workflow;

import org.eclipse.triquetrum.Event;

/**
 * An event interface as a basis for all specific events related to process executions.
 * It reuses some concepts from eclipse's DebugEvent, e.g. the <i>kind</i> and <i>detail</i>.
 * <p>
 * Topics of ProcessEvents are of the format <code>org/eclipse/triquetrum/process/[PROCESS ID]/[KIND]/[DETAIL]/...</code>.
 * </p>
 * 
 * @see org.eclipse.debug.core.DebugEvent about event kind & detail
 */
public interface ProcessEvent extends Event {

  String TOPIC_PREFIX = "org/eclipse/triquetrum/process/";
  
  enum Kind {
    /**
     * Indicates a process was resumed. 
     * The detail will indicate in which way it was resumed (STEP or CLIENT_REQUEST).
     */
    RESUME(0x0001), 
    /**
     * Indicates a process was suspended. 
     * The detail will indicate in which way it was resumed (STEP_END, BREAKPOINT or CLIENT_REQUEST).
     */
    SUSPEND(0x0002), 
    /**
     * Indicates a process or a child(i.e. a Task) was created/started
     */
    CREATE(0x0004), 
    /**
     * Indicates a process or a child(i.e. a Task) was terminated on CLIENT_REQUEST (via a specific terminate() call) or naturally (with UNSPECIFIED detail)
     */
    TERMINATE(0x0008), 
    UNSPECIFIED(0);
    
    int kind;
    Kind(int kind) {
      this.kind = kind;
    }
    int getKind() {
      return kind;
    }
  }

  enum Detail {
    /**
     * Indicates a process was resumed by a step action.
     * <p>
     * In eclipse's DebugEvent, this value is used for STEP_OVER.
     * In Triquetrum this corresponds to the execution of one Task.
     * <br/>
     * In the far future we might want to differentiate stepping <i>over</i> versus <i>into</i> Tasks 
     * (i.e. to step by its sub-tasks), but for the moment this is not supported.
     * </p>
     */
    STEP(0x0002),
    /**
     * Indicates a process was suspended due to the completion of a step action.
     */
    STEP_END(0x0008),
    /**
     * Indicates a process was suspended by a breakpoint.
     */
    BREAKPOINT(0x0010),
    /**
     * Indicates a process was suspended, resumed or terminated due to a client request, i.e. an explicit suspend(), resume() or terminate() call.
     */
    CLIENT_REQUEST(0x0020),
    UNSPECIFIED(0);
    int detail;
    Detail(int detail) {
      this.detail = detail;
    }
    int getDetail() {
      return detail;
    }
  }

  /**
   * @return the kind of the event
   * @see org.eclipse.debug.core.DebugEvent.getKind()
   */
  Kind getKind();

  /**
   * @return the detail of the event
   * @see org.eclipse.debug.core.DebugEvent.getDetail()
   */
  Detail getDetail();
  
  /**
   * @return the id of the process to which this event is related
   */
  String getProcessId();
}
