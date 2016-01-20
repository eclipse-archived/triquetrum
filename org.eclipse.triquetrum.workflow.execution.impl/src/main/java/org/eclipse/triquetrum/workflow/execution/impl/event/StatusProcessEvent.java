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

import org.eclipse.triquetrum.processing.model.ProcessingStatus;

public class StatusProcessEvent extends ProcessEvent {

  private static final long serialVersionUID = 1L;

  private ProcessingStatus status;
  private Throwable throwable;

  public StatusProcessEvent(String processContextId, ProcessingStatus status, Throwable throwable) {
    super(processContextId, Kind.UNSPECIFIED, Detail.UNSPECIFIED);
    this.status = status;
    this.throwable = throwable;
  }

  public ProcessingStatus getStatus() {
    return status;
  }

  public Throwable getThrowable() {
    return throwable;
  }

}
