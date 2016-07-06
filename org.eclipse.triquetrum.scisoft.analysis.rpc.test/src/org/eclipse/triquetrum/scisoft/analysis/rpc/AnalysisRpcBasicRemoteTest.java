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

import org.eclipse.triquetrum.scisoft.analysis.rpc.test.NetUtils;
import org.eclipse.triquetrum.scisoft.analysis.rpc.test.PythonHelper;
import org.eclipse.triquetrum.scisoft.analysis.rpc.test.PythonHelper.PythonRunInfo;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * This test is intended to show the basic operation of the RPC Client and Server
 */
public class AnalysisRpcBasicRemoteTest {

  @Rule
  public Timeout globalTimeout = Timeout.seconds(5);

  @Test
  public void testBasicRemoteOperation() throws Exception {
    // Start the Python server
    int port = NetUtils.getFreePort(20000);
    PythonRunInfo server = PythonHelper.runPythonFileBackground(PythonHelper.getScriptsHome() + "/rpcremote.py",
        new String[] { PythonHelper.getSciSoftPyHome(), Integer.toString(port) });

    try {
      // Create a new client to connect to the server (note that the ports match)
      AnalysisRpcClient analysisRpcClient = new AnalysisRpcClient(port);

      Thread.sleep(500); // add delay to ensure client is receiving

      // Set up arguments to pass
      String input1 = "Hello, ";
      String input2 = "World!";
      // make the remote call
      String output = (String) analysisRpcClient.request("cat", new Object[] { input1, input2 });

      Assert.assertEquals("Hello, World!", output);
    } finally {
      if (server != null) {
        server.terminate();
        server.getStdout(true);
      }
    }
  }
}
