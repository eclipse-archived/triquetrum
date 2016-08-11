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

import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcException;
import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcServer;
import org.eclipse.triquetrum.scisoft.analysis.rpc.IAnalysisRpcHandler;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IRootFlattener;

/**
 * @see AnalysisRpcServerHandler
 */
public class AnalysisRpcServerHandlerImpl implements AnalysisRpcServerHandler {
  private final AnalysisRpcServer analysisRPC;

  public AnalysisRpcServerHandlerImpl(AnalysisRpcServer analysisRPC) {
    this.analysisRPC = analysisRPC;
  }

  private Object handler_common(String destination, Object[] args, boolean debug, boolean suspend) {
    IRootFlattener flattener = analysisRPC.getFlattener();
    try {
      IAnalysisRpcHandler handler = analysisRPC.getDestination(destination);
      if (handler == null) {
        throw new AnalysisRpcException("No handler registered for " + destination);
      }
      Object[] unflattened = new Object[args.length];
      for (int i = 0; i < args.length; i++) {
        unflattened[i] = flattener.unflatten(args[i]);
      }
      Object ret = handler.run(unflattened);
      Object flatret = flattener.flatten(ret);
      return flatret;
    } catch (Exception e) {
      return flattener.flatten(e);
    }
  }

  @Override
  public Object handler(String destination, Object[] args) {
    return handler_common(destination, args, false, false);
  }

  @Override
  public Object handler_debug(String destination, Object[] args, boolean suspend) {
    return handler_common(destination, args, true, suspend);

  }

  @Override
  public Object is_alive() {
    // this method does nothing. Simply successfully running as an RPC call
    // determines if alive
    return true;
  }
}
