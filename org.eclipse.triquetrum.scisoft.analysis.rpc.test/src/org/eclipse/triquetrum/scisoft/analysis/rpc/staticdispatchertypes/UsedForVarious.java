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

import org.junit.Assert;

public class UsedForVarious {

  public static int callCheckedException(int o) throws IOException {
    throw new IOException();
  }

  public static int callUnCheckedException(int o) {
    throw new UnsupportedOperationException();
  }

  public int nonStaticMethod(int o) {
    return 0;
  }

  public String similarSignatureStaticAndNonStatic(int o) {
    return "non-static";
  }

  public static String similarSignatureStaticAndNonStatic(Integer o) {
    return "static";
  }

  public static Class<Object> overloaded(Object o) {
    return Object.class;
  }

  public static Class<Integer> overloaded(Integer o) {
    return Integer.class;
  }

  public static Object callObjectWithNull(Object o) {
    Assert.assertNull(o);
    return "callObjectWithNull";
  }

  public static Object callNullReturn(int o) {
    Assert.assertEquals(0, o);
    return null;
  }

  public static Object callNullReturn(String o) {
    Assert.assertEquals("arg", o);
    return null;
  }

  public static Class<String> typednone(String o) {
    return String.class;
  }

  public static Class<Integer> typednone(Integer o) {
    return Integer.class;
  }

}
