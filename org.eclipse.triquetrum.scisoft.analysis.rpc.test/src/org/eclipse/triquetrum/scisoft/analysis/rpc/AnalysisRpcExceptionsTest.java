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
import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcRemoteException;
import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcServer;
import org.eclipse.triquetrum.scisoft.analysis.rpc.IAnalysisRpcHandler;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class AnalysisRpcExceptionsTest {
  @Rule
  public Timeout globalTimeout = Timeout.seconds(2);

  private static final class CatStringsHandler implements IAnalysisRpcHandler {
    @Override
    public Object run(Object[] unflattened) {
      return (String) unflattened[0] + (String) unflattened[1];
    }
  }

  private static final String CAT_TWO_STRINGS = "cat";
  private static AnalysisRpcServer analysisRpcServer;
  private static AnalysisRpcClient analysisRpcClient;

  @BeforeClass
  public static void setupBeforeClass() throws AnalysisRpcException {
    analysisRpcServer = new AnalysisRpcServer(0);
    analysisRpcServer.start();
    analysisRpcServer.addHandler(CAT_TWO_STRINGS, new CatStringsHandler());

    analysisRpcClient = new AnalysisRpcClient(analysisRpcServer.getPort());
  }

  @AfterClass
  public static void tearDownAfterClass() {
    analysisRpcServer.shutdown();
  }

  @Test(timeout = 2000)
  public void testBasicOperation() throws AnalysisRpcException {
    String result = (String) analysisRpcClient.request(CAT_TWO_STRINGS, new Object[] { "Hello, ", "World!" });
    Assert.assertEquals("Hello, World!", result);
  }

  @Test
  public void testHandlerExceptionOperation() {
    // force an ArrayIndexOutOfBoundsException in the handler and make sure it is raised rather than returned
    // due to flattening, it will be raised as an Exception wrapped in an AnalysisRpcException
    try {
      analysisRpcClient.request(CAT_TWO_STRINGS, new Object[] { "Hello, " });
      Assert.fail("No exception raised");
    } catch (AnalysisRpcException e) {
      // The exception type is lost, but we preserve it in the message string
      Assert.assertFalse(e.getCause() instanceof ArrayIndexOutOfBoundsException);
      Assert.assertTrue(e.getCause() instanceof AnalysisRpcRemoteException);
      Assert.assertTrue(e.getCause().getMessage().startsWith("java.lang.ArrayIndexOutOfBoundsException:"));
    }
  }

  @Test
  public void testInternalExceptionOperation() {
    // force a flattening exception on the call side
    try {
      analysisRpcClient.request(CAT_TWO_STRINGS, new Object[] { new Object() });
      Assert.fail("No exception raised");
    } catch (AnalysisRpcException e) {
      Assert.assertEquals(UnsupportedOperationException.class, e.getCause().getClass());
    }
  }

  @Test
  public void testNoMatchingHandlerException() {
    // force a not found on the call side error
    try {
      analysisRpcClient.request(CAT_TWO_STRINGS + " invalid", new Object[] { "Hello, ", "World!" });
      Assert.fail("No exception raised");
    } catch (AnalysisRpcException e) {
      Assert.assertTrue(e.getCause() instanceof AnalysisRpcRemoteException);
      Assert.assertEquals(AnalysisRpcException.class.getName() + ": No handler registered for " + CAT_TWO_STRINGS + " invalid", e.getCause().getMessage());
    }
  }

  @Test
  public void testRemoteRaisesException() {
    // have remote code raise an exception and make sure that the stack trace is preserved
    // we don this by forcing a class cast exception (ie cat String + Integer
    try {
      analysisRpcClient.request(CAT_TWO_STRINGS, new Object[] { "Hello, ", 2 });
      Assert.fail("No exception raised");
    } catch (AnalysisRpcException e) {
      Assert.assertTrue(e.getCause().getMessage().startsWith("java.lang.ClassCastException:"));
      StackTraceElement[] remoteStackTrace = e.getCause().getStackTrace();
      Assert.assertEquals(CatStringsHandler.class.getName(), remoteStackTrace[0].getClassName());
      Assert.assertEquals("run", remoteStackTrace[0].getMethodName());

    }
  }
}
