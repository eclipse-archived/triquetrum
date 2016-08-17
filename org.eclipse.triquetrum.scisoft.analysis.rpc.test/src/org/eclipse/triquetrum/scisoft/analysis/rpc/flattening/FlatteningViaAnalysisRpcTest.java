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

package org.eclipse.triquetrum.scisoft.analysis.rpc.flattening;

import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcClient;
import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcException;
import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcServer;
import org.eclipse.triquetrum.scisoft.analysis.rpc.IAnalysisRpcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;


public class FlatteningViaAnalysisRpcTest extends FlatteningTestAbstract {
  private static AnalysisRpcServer server;
  private static AnalysisRpcClient client;

  @BeforeClass
  public static void start() throws AnalysisRpcException {
    server = new AnalysisRpcServer(0);
    server.addHandler("loopback", new IAnalysisRpcHandler() {
      @Override
      public Object run(Object[] args) {
        return args[0];
      }
    });
    server.start();

    client = new AnalysisRpcClient(server.getPort());
  }

  @AfterClass
  public static void stop() {
    if (server != null)
      server.shutdown();
    server = null;
    client = null;
  }

  @Override
  protected Object doActualFlattenAndUnflatten(Object inObj) {
    try {
      return client.request("loopback", new Object[] { inObj });
    } catch (AnalysisRpcException e) {
      if (e.getCause().getClass() == Exception.class)
        return e.getCause();
      throw new RuntimeException(e);
    }
  }
}
