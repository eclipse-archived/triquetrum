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
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.model.TestTimedOutException;

public class AnalysisRpcAdvancedTest {

  @Rule
  public Timeout globalTimeout = Timeout.seconds(5);

  @Test
  public void testMultipleHandlers() throws AnalysisRpcException {
    AnalysisRpcServer analysisRpcServer = null;
    try {
      analysisRpcServer = new AnalysisRpcServer(0);
      analysisRpcServer.start();
      analysisRpcServer.addHandler("cat", new IAnalysisRpcHandler() {

        @Override
        public Object run(Object[] unflattened) {
          return (String) unflattened[0] + (String) unflattened[1];
        }
      });
      analysisRpcServer.addHandler("len", new IAnalysisRpcHandler() {

        @Override
        public Object run(Object[] unflattened) {
          return ((String) unflattened[0] + (String) unflattened[1]).length();
        }
      });

      AnalysisRpcClient analysisRpcClient = new AnalysisRpcClient(analysisRpcServer.getPort());
      String catResult = (String) analysisRpcClient.request("cat", new Object[] { "Hello, ", "World!" });
      Assert.assertEquals("Hello, World!", catResult);
      int lenResult = (Integer) analysisRpcClient.request("len", new Object[] { "Hello, ", "World!" });
      Assert.assertEquals("Hello, World!".length(), lenResult);
    } finally {
      if (analysisRpcServer != null) {
        // XXX: On Windows this test sometimes timesout shutting down, I think this
        // may be down to older versions of servers in Orbit, time to get them
        // upgraded. The orbit versions are older than Diamond is using.
        analysisRpcServer.shutdown();
      }
    }
  }

  @Test
  public void testExceptionOnHandlerFlattening() throws AnalysisRpcException {
    AnalysisRpcServer analysisRpcServer = null;
    try {
      analysisRpcServer = new AnalysisRpcServer(0);
      analysisRpcServer.start();
      analysisRpcServer.addHandler("flaterror", new IAnalysisRpcHandler() {

        @Override
        public Object run(Object[] unflattened) {
          // return unflattanble type
          return new Object();
        }
      });

      AnalysisRpcClient analysisRpcClient = new AnalysisRpcClient(analysisRpcServer.getPort());
      // force a flattening exception on the call side
      try {
        analysisRpcClient.request("flaterror", new Object[] { "Hello" });
        Assert.fail("No exception raised");
      } catch (AnalysisRpcException e) {
        Assert.assertFalse(e.getCause() instanceof UnsupportedOperationException);
        Assert.assertTrue(e.getCause() instanceof AnalysisRpcRemoteException);
      }
    } finally {
      if (analysisRpcServer != null)
        analysisRpcServer.shutdown();
    }
  }

  // As all comms are to the localhost, we want to make sure we time out
  // pretty quickly
  @Test(expected = AnalysisRpcException.class, timeout = 2000)
  public void testConnectionTimesOutQuicklyEnough() throws AnalysisRpcException {
    int port = NetUtils.getFreePort(20010);
    AnalysisRpcClient analysisRpcClient = new AnalysisRpcClient(port);
    analysisRpcClient.request("doesnotexist", new Object[] { "Hello" });
  }

  @Test
  public void testIsAlive() throws AnalysisRpcException {
    int port = NetUtils.getFreePort(20020);
    AnalysisRpcClient analysisRpcClient = new AnalysisRpcClient(port);
    Assert.assertFalse(analysisRpcClient.isAlive());
    AnalysisRpcServer analysisRpcServer = new AnalysisRpcServer(port);
    try {
      analysisRpcServer.start();
      Assert.assertTrue(analysisRpcClient.isAlive());
    } finally {
      analysisRpcServer.shutdown();
    }
  }

  @Test
  public void waitIsAlive() throws AnalysisRpcException {
    int port = NetUtils.getFreePort(20020);
    AnalysisRpcClient analysisRpcClient = new AnalysisRpcClient(port);
    Assert.assertFalse(analysisRpcClient.isAlive());
    try {
      analysisRpcClient.waitUntilAlive(250);
      Assert.fail();
    } catch (AnalysisRpcException e) {
      // test passes, waitUntilAlive has raised exception saying not ready
      // yet
    }
    AnalysisRpcServer analysisRpcServer = new AnalysisRpcServer(port);
    try {
      analysisRpcServer.start();
      analysisRpcClient.waitUntilAlive(250);
      Assert.assertTrue(analysisRpcClient.isAlive());
    } finally {
      analysisRpcServer.shutdown();
    }
  }

  interface TestProxy_Interface {
    public String cat(String a1, String a2) throws AnalysisRpcException;
  }

  @Test
  public void testProxy() throws AnalysisRpcException {
    AnalysisRpcServer analysisRpcServer = null;
    try {
      analysisRpcServer = new AnalysisRpcServer(0);
      analysisRpcServer.start();
      analysisRpcServer.addHandler("cat", new IAnalysisRpcHandler() {

        @Override
        public Object run(Object[] unflattened) {
          return (String) unflattened[0] + (String) unflattened[1];
        }
      });

      AnalysisRpcClient analysisRpcClient = new AnalysisRpcClient(analysisRpcServer.getPort());
      TestProxy_Interface catObject = analysisRpcClient.newProxyInstance(TestProxy_Interface.class);
      String catResult = catObject.cat("Hello, ", "World!");
      Assert.assertEquals("Hello, World!", catResult);
    } finally {
      if (analysisRpcServer != null)
        analysisRpcServer.shutdown();
    }
  }

  interface TestBadProxy_Interface {
    // Missing throws AnalysisRpcException
    public String cat(String a1, String a2);
  }

  /**
   * This test makes sure that methods on the interface throws AnalysisRpcException. However, it may be desirable to change this restriction to a new
   * RuntimeException that does not have this requirement. The advantage would be that any interface could be implemented in Python (across the server divide)
   * but that would add an implication to the user of the interface that the call does not have to worry about such issues.
   */
  @Test
  public void testBadProxy() {
    // don't need a real server because this test makes sure the bad proxy
    // fails
    int port = NetUtils.getFreePort(20010);
    AnalysisRpcClient analysisRpcClient = new AnalysisRpcClient(port);
    TestBadProxy_Interface catObject = analysisRpcClient.newProxyInstance(TestBadProxy_Interface.class);
    try {
      catObject.cat("Hello, ", "World!");
      Assert.fail("Exception not thrown as expected");
    } catch (RuntimeException e) {
    }

    try {
      catObject.equals(null);
      Assert.fail("Exception not thrown as expected");
    } catch (RuntimeException e) {
    }

  }

}
