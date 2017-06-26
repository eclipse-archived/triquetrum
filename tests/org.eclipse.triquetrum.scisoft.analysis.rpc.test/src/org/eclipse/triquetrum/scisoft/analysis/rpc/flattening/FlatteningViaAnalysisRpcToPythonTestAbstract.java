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
import org.eclipse.triquetrum.scisoft.analysis.rpc.test.PythonHelper;
import org.eclipse.triquetrum.scisoft.analysis.rpc.test.PythonHelper.PythonRunInfo;
import org.junit.AfterClass;
import org.junit.BeforeClass;

abstract public class FlatteningViaAnalysisRpcToPythonTestAbstract extends FlatteningTestAbstract {
  private static PythonRunInfo pythonRunInfo;
  protected static AnalysisRpcClient client;

  @BeforeClass
  public static void start() throws Exception {
    pythonRunInfo = PythonHelper.runPythonFileBackground(PythonHelper.getScriptsHome() + "/loopbackanalysisrpc.py",
        new String[] { PythonHelper.getSciSoftPyHome() });

    Thread.sleep(SERVER_WAIT_TIME); // wait for server to start

    client = new AnalysisRpcClient(8714);
  }

  @AfterClass
  public static void stop() {
    if (pythonRunInfo != null) {
      pythonRunInfo.terminate();
      pythonRunInfo.getStdout(true);
    }
    pythonRunInfo = null;
    client = null;
  }

  protected void checkPythonState() {
    if (pythonRunInfo != null && pythonRunInfo.hasTerminated()) {
      pythonRunInfo.getStdout(true);
      throw new RuntimeException("Python script unexpectedly terminated");
    }
  }

}
