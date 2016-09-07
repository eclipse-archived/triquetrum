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
package org.eclipse.triquetrum.workflow.execution.impl;

import java.util.Date;

import org.eclipse.triquetrum.ProcessingStatus;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.eclipse.triquetrum.workflow.ProcessHandle;
import org.eclipse.triquetrum.workflow.execution.impl.executor.WorkflowExecutionFuture;

/**
 *
 */
public class ProcessHandleImpl implements ProcessHandle {
  private static final long serialVersionUID = -1405401220946130936L;
  
  private WorkflowExecutionFuture fet;
  private Date creationTS;

  /**
   * @param fetFuture
   *          .getStatus()
   */
  public ProcessHandleImpl(WorkflowExecutionFuture fetFuture) {
    this.fet = fetFuture;
    this.creationTS = new Date();
  }

  public WorkflowExecutionFuture getFet() {
    return fet;
  }
  
  @Override
  public Date getCreationTS() {
    return creationTS;
  }
  
  @Override
  public ModelHandle getModelHandle() {
    return fet.getModelHandle();
  }

  @Override
  public String getProcessId() {
    return fet.getProcessId();
  }

  @Override
  public ProcessingStatus getExecutionStatus() {
    return fet.getStatus();
  }

  @Override
  public String[] getSuspendedElements() {
    return fet.getSuspendedElements();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getProcessId() == null) ? 0 : getProcessId().hashCode());
    result = prime * result + ((getExecutionStatus() == null) ? 0 : getExecutionStatus().hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProcessHandleImpl other = (ProcessHandleImpl) obj;
    if (getProcessId() == null) {
      if (other.getProcessId() != null)
        return false;
    } else if (!getProcessId().equals(other.getProcessId()))
      return false;
    if (getExecutionStatus() != other.getExecutionStatus())
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "ProcessHandleImpl [flowHandle=" + getModelHandle().getCode() + ", processContextID=" + getProcessId() + ", status=" + getExecutionStatus() + "]";
  }
}
