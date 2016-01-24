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

import java.io.Serializable;
import java.util.Date;

import org.eclipse.triquetrum.processing.model.ProcessingStatus;

/**
 * A light-weight handle on a workflow model-based process execution.
 * 
 */
public interface ProcessHandle extends Serializable {
  
  /**
   * 
   * @return the timestamp of creation of this handle
   */
  Date getCreationTS();

  /**
   * 
   * @return the handle of the model that is used to run the process
   */
  ModelHandle getModelHandle();
  
  /**
   * For context-aware executions, this can be used to retrieve 
   * the <code>ProcessManager</code> from the <code>ProcessManagerService</code> if needed.
   * <b>Remark that such retrieval can be a heavy operation and should only be attempted when really necessary.</b> 
   * <br/>
   * For the rare process executions without assigned <code>Context</code>s, this returns an id that can be used to
   * uniquely identify the execution in any related actions, e.g. to obtain execution logs, pause/resume it etc.
   * 
   * @return the UUID of the process execution;
   * 
   */
  String getProcessId();
  
  /**
   * 
   * @return the current execution status
   */
  ProcessingStatus getExecutionStatus();
  
  /**
   * Suspensions can  be caused by breakpoints and/or the end of a step execution.
   * @return the names of the currently suspended workflow elements (typically actors)
   */
  String[] getSuspendedElements();
}
