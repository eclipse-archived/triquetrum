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

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * @see JCIP 7.1.7, Listing 7.12
 * 
 * @author erwin
 */
public interface CancellableTask<T> extends Callable<T> {
  void cancel();
  RunnableFuture<T> newFutureTask();
}
