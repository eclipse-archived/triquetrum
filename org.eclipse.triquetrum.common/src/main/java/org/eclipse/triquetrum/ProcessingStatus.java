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
package org.eclipse.triquetrum;

/**
 * @author erwin
 */
public enum ProcessingStatus {

  IDLE, // when the process is created and not executing, not even starting to execute
  STARTING, // when the execution has been requested, and the process is going through it's initialization phases
  ACTIVE, // when the process is really doing work
  SUSPENDED, // when the process is suspended; either via a global suspend action, a breakpoint, after finishing a step when running in stepping mode etc
  STOPPING, // when the process has done its work and is going through its wrapup phases
  FINISHED, // when the execution has been completed without technical/runtime errors
  INTERRUPTED, // when the execution was interrupted/aborted before its normal completion, typically by a user action
  ERROR; // when the execution has encountered a technical/runtime error
         // Functional errors for specific tasks do not impact the ProcessStatus.

  public boolean isFinalStatus() {
    return this.compareTo(FINISHED)>=0;
  }
}
