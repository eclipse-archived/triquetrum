/*******************************************************************************
 * Copyright (c) 2012-2016 Diamond Light Source Ltd.,
 *                         Kichwa Coders & iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    DLS, Kichwa Coders - initial API and implementation and/or initial documentation
 *    Erwin De Ley - extraction from DAWN to ease reuse in other contexts
 *******************************************************************************/

package org.eclipse.triquetrum.scisoft.analysis.rpc.internal;

import org.eclipse.triquetrum.scisoft.analysis.rpc.IAnalysisRpcHandler;

/**
 * This interface is responsible for the actual flattening and unflattening on the server side.
 */
public interface AnalysisRpcServerHandler {

  /**
   * Direct a call to the handler registered for the given destination passing it args after unflattening them.
   *
   * @param destination
   *          name of the handler to call
   * @param args
   *          arguments to pass to the handler
   * @return flattened return value from registered handler
   * @see IAnalysisRpcHandler
   */
  public Object handler(String destination, Object[] args);

  /**
   * Call handler, and if supported by server, attach debugger, optionally inserting temporary breakpoint at handler entry point.
   *
   * @see #handler(String, Object[])
   */
  public Object handler_debug(String destination, Object[] args, boolean suspend);

  /**
   * Runs without exception if the server is running. Can be used by the client to poll the server to see if it is up yet.
   */
  public Object is_alive();

}
