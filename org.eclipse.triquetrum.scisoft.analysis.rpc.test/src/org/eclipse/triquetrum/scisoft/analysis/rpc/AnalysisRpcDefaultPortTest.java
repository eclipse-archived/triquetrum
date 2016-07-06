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

package org.eclipse.triquetrum.scisoft.analysis.rpc;

import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcClient;
import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcException;
import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcServer;
import org.eclipse.triquetrum.scisoft.analysis.rpc.IAnalysisRpcHandler;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * This is basically the same test as {@link AnalysisRpcBasicTest}, but with dynamically allocated port to listen on. This is achieved by creating a server
 * listening to port 0 and then calling {@link AnalysisRpcServer#getPort()} to find out what port was selected.
 */
public class AnalysisRpcDefaultPortTest {
  @Rule
  public Timeout globalTimeout = Timeout.seconds(2);

  private static final String CAT_TWO_STRINGS = "cat";

  /**
   * This is the class that will handle the RPC method call
   */
  private final class CatTwoStringsHandler implements IAnalysisRpcHandler {
    @Override
    public Object run(Object[] unflattened) {
      return (String) unflattened[0] + (String) unflattened[1];
    }
  }

  @Test
  public void testBasicOperation() throws AnalysisRpcException {
    // Create a server
    AnalysisRpcServer analysisRpcServer = new AnalysisRpcServer(0);
    try {
      // Register a handler with it, with the given name
      analysisRpcServer.addHandler(CAT_TWO_STRINGS, new CatTwoStringsHandler());
      // Start the server
      analysisRpcServer.start();

      // Create a new client to connect to the server (note that the ports match)
      AnalysisRpcClient analysisRpcClient = new AnalysisRpcClient(analysisRpcServer.getPort());

      // Set up arguments to pass
      Object[] args = new Object[] { "Hello, ", "World!" };
      // make the remote call
      String result = (String) analysisRpcClient.request(CAT_TWO_STRINGS, args);

      // Check our results
      Assert.assertEquals("Hello, World!", result);
    } finally {
      // Shutdown the server (in a finally block just in case the test fails)
      analysisRpcServer.shutdown();
    }
  }
}
