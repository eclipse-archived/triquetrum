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

package org.eclipse.triquetrum.scisoft.analysis.rpc.staticdispatchertypes;

import java.io.IOException;

import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcException;
import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcGenericStaticClassDispatcher;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.TypedNone;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


// a bunch of casts aren't needed, but I find readability is nicer with them all being consistent
@SuppressWarnings("cast")
public class StaticClassDispatcherTest {
  @Rule
  public Timeout globalTimeout = Timeout.seconds(2);

  @Test
  public void testPrimitiveTypes() throws AnalysisRpcException {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(SingleArgumentPrimitives.class);
    Assert.assertEquals(Boolean.TYPE, dispatcher.run(new Object[] { "call", false }));
    Assert.assertEquals(Byte.TYPE, dispatcher.run(new Object[] { "call", (byte) 0 }));
    Assert.assertEquals(Character.TYPE, dispatcher.run(new Object[] { "call", (char) 0 }));
    Assert.assertEquals(Double.TYPE, dispatcher.run(new Object[] { "call", (double) 0 }));
    Assert.assertEquals(Float.TYPE, dispatcher.run(new Object[] { "call", (float) 0 }));
    Assert.assertEquals(Integer.TYPE, dispatcher.run(new Object[] { "call", (int) 0 }));
    Assert.assertEquals(Long.TYPE, dispatcher.run(new Object[] { "call", (long) 0 }));
    Assert.assertEquals(Short.TYPE, dispatcher.run(new Object[] { "call", (short) 0 }));
  }

  @Test
  public void testPrimitiveArrayTypes() throws AnalysisRpcException {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(SingleArgumentPrimitiveArrays.class);
    Assert.assertEquals(boolean[].class, dispatcher.run(new Object[] { "call", new boolean[] { false } }));
    Assert.assertEquals(byte[].class, dispatcher.run(new Object[] { "call", new byte[] { (byte) 0 } }));
    Assert.assertEquals(char[].class, dispatcher.run(new Object[] { "call", new char[] { (char) 0 } }));
    Assert.assertEquals(double[].class, dispatcher.run(new Object[] { "call", new double[] { (double) 0 } }));
    Assert.assertEquals(float[].class, dispatcher.run(new Object[] { "call", new float[] { (float) 0 } }));
    Assert.assertEquals(int[].class, dispatcher.run(new Object[] { "call", new int[] { (int) 0 } }));
    Assert.assertEquals(long[].class, dispatcher.run(new Object[] { "call", new long[] { (long) 0 } }));
    Assert.assertEquals(short[].class, dispatcher.run(new Object[] { "call", new short[] { (short) 0 } }));
  }

  @Test
  public void testBoxedPrimitiveTypes() throws AnalysisRpcException {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(SingleArgumentBoxedPrimitives.class);
    Assert.assertEquals(Boolean.class, dispatcher.run(new Object[] { "call", false }));
    Assert.assertEquals(Byte.class, dispatcher.run(new Object[] { "call", (byte) 0 }));
    Assert.assertEquals(Character.class, dispatcher.run(new Object[] { "call", (char) 0 }));
    Assert.assertEquals(Double.class, dispatcher.run(new Object[] { "call", (double) 0 }));
    Assert.assertEquals(Float.class, dispatcher.run(new Object[] { "call", (float) 0 }));
    Assert.assertEquals(Integer.class, dispatcher.run(new Object[] { "call", (int) 0 }));
    Assert.assertEquals(Long.class, dispatcher.run(new Object[] { "call", (long) 0 }));
    Assert.assertEquals(Short.class, dispatcher.run(new Object[] { "call", (short) 0 }));
  }

  @Test
  public void testBoxedPrimitiveArrayTypes() throws AnalysisRpcException {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(SingleArgumentBoxedPrimitiveArrays.class);

    Assert.assertEquals(Boolean[].class, dispatcher.run(new Object[] { "call", new Boolean[] { false } }));
    Assert.assertEquals(Byte[].class, dispatcher.run(new Object[] { "call", new Byte[] { (byte) 0 } }));
    Assert.assertEquals(Character[].class, dispatcher.run(new Object[] { "call", new Character[] { (char) 0 } }));
    Assert.assertEquals(Double[].class, dispatcher.run(new Object[] { "call", new Double[] { (double) 0 } }));
    Assert.assertEquals(Float[].class, dispatcher.run(new Object[] { "call", new Float[] { (float) 0 } }));
    Assert.assertEquals(Integer[].class, dispatcher.run(new Object[] { "call", new Integer[] { (int) 0 } }));
    Assert.assertEquals(Long[].class, dispatcher.run(new Object[] { "call", new Long[] { (long) 0 } }));
    Assert.assertEquals(Short[].class, dispatcher.run(new Object[] { "call", new Short[] { (short) 0 } }));
  }

  @Test
  public void testNoArgs() throws AnalysisRpcException {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(NoArgument.class);
    Assert.assertEquals("no argument", dispatcher.run(new Object[] { "call" }));
  }

  @Test
  public void testVoidReturn() throws AnalysisRpcException {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(VoidReturn.class);
    Assert.assertEquals(null, dispatcher.run(new Object[] { "call", 0 }));
  }

  @Test
  public void testManyArguments() throws AnalysisRpcException {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(ManyArguments.class);
    Assert.assertEquals(8, dispatcher.run(new Object[] { "call", 1, 2, 3, 4, 5, 6, 7, 8 }));
    Assert.assertEquals(7, dispatcher.run(new Object[] { "call", 1, 2, 3, 4, 5, 6, 7 }));
    Assert.assertEquals(6, dispatcher.run(new Object[] { "call", 1, 2, 3, 4, 5, 6 }));
    Assert.assertEquals(5, dispatcher.run(new Object[] { "call", 1, 2, 3, 4, 5 }));
    Assert.assertEquals(4, dispatcher.run(new Object[] { "call", 1, 2, 3, 4 }));
    Assert.assertEquals(3, dispatcher.run(new Object[] { "call", 1, 2, 3 }));
    Assert.assertEquals(2, dispatcher.run(new Object[] { "call", 1, 2 }));
    Assert.assertEquals(1, dispatcher.run(new Object[] { "call", 1 }));
    Assert.assertEquals(0, dispatcher.run(new Object[] { "call" }));
  }

  @Test
  public void testNoSuchMethod() {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(UsedForVarious.class);
    try {
      dispatcher.run(new Object[] { "missing", 0 });
      Assert.fail();
    } catch (AnalysisRpcException e) {
      Assert.assertTrue(e.getMessage().indexOf("Failed to find method") != -1);
    }
  }

  @Test
  public void testOnlyMatchingMethodIsNonStatic() {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(UsedForVarious.class);
    try {
      dispatcher.run(new Object[] { "nonStaticMethod", 0 });
      Assert.fail();
    } catch (AnalysisRpcException e) {
      Assert.assertTrue(e.getMessage().indexOf("Failed to find method") != -1);
    }
  }

  @Test
  public void testSimilarSignatureStaticAndNonStatic() throws AnalysisRpcException {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(UsedForVarious.class);
    Assert.assertEquals("static", dispatcher.run(new Object[] { "similarSignatureStaticAndNonStatic", 0 }));
  }

  @Test
  public void testCallCheckedException() {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(UsedForVarious.class);
    try {
      dispatcher.run(new Object[] { "callCheckedException", 0 });
      Assert.fail();
    } catch (AnalysisRpcException e) {
      Assert.assertTrue(e.getCause() instanceof IOException);
    }
  }

  @Test
  public void testCallUnCheckedException() {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(UsedForVarious.class);
    try {
      dispatcher.run(new Object[] { "callUnCheckedException", 0 });
      Assert.fail();
    } catch (AnalysisRpcException e) {
      Assert.assertTrue(e.getCause() instanceof UnsupportedOperationException);
    }
  }

  // This test shows how overloaded, but mutually assignable types may cause the wrong one to execute
  @Test
  public void testOverloadedAssignable() throws AnalysisRpcException {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(UsedForVarious.class);
    Assert.assertEquals(Integer.class, UsedForVarious.overloaded(0));
    Assert.assertEquals(Object.class, UsedForVarious.overloaded(new Object()));
    Assume.assumeTrue(dispatcher.run(new Object[] { "overloaded", new Object() }).equals(dispatcher.run(new Object[] { "overloaded", 0 })));

  }

  @Test
  public void testNullArgument() throws AnalysisRpcException {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(UsedForVarious.class);
    Assert.assertEquals("callObjectWithNull", dispatcher.run(new Object[] { "callObjectWithNull", null }));
  }

  @Test
  public void testNullDoesntMatchPrimitives1() {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(SingleArgumentPrimitives.class);
    try {
      dispatcher.run(new Object[] { "call", null });
      Assert.fail();
    } catch (AnalysisRpcException e) {
      Assert.assertTrue(e.getMessage().indexOf("Failed to find method") != -1);
    }

  }

  @Test
  public void testNullDoesntMatchPrimitives2() throws AnalysisRpcException {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(NullPrimitive.class);
    Assert.assertEquals(Object.class, dispatcher.run(new Object[] { "call", null }));
  }

  @Test
  public void testNullReturn() throws AnalysisRpcException {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(UsedForVarious.class);
    Assert.assertEquals(null, dispatcher.run(new Object[] { "callNullReturn", 0 }));
    Assert.assertEquals(null, dispatcher.run(new Object[] { "callNullReturn", "arg" }));
  }

  @Test
  public void testTypedNullArgument() throws AnalysisRpcException {
    AnalysisRpcGenericStaticClassDispatcher dispatcher = new AnalysisRpcGenericStaticClassDispatcher(UsedForVarious.class);
    Assert.assertEquals(Integer.class, dispatcher.run(new Object[] { "typednone", new TypedNone(Integer.class) }));
    Assert.assertEquals(String.class, dispatcher.run(new Object[] { "typednone", new TypedNone(String.class) }));
  }

}
