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
package org.eclipse.triquetrum.workflow.execution.impl.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @see JCIP 7.1.7, Listing 7.12
 *
 * @author erwin
 *
 */
public class WorkflowExecutor extends ThreadPoolExecutor {

  public WorkflowExecutor(int corePoolSize, int maximumPoolSize) {
    super(corePoolSize, maximumPoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
  }

  @Override
  protected <T> java.util.concurrent.RunnableFuture<T> newTaskFor(java.util.concurrent.Callable<T> callable) {
    if (callable instanceof CancellableTask) {
      return ((CancellableTask<T>) callable).newFutureTask();
    } else {
      return super.newTaskFor(callable);
    }
  }
}
