/*******************************************************************************
 *  Copyright (c) 2012-2016 Diamond Light Source Ltd.,
 *                           Kichwa Coders & iSencia Belgium NV.
 *                           
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0.
 *  
 *  SPDX-License-Identifier: EPL-2.0
 *  
 *  Contributors:
 *      DLS, Kichwa Coders - initial API and implementation and/or initial documentation
 *      Erwin De Ley - extraction from DAWN to ease reuse in other contexts
 *******************************************************************************/

package org.eclipse.triquetrum.scisoft.analysis.rpc.instancedispatcher;

import java.io.IOException;

import org.junit.Assert;

public class UsedForVarious {

  public int callCheckedException(int o) throws IOException {
    throw new IOException();
  }

  public int callUnCheckedException(int o) {
    throw new UnsupportedOperationException();
  }

  public static int staticMethod(int o) {
    return 0;
  }

  public String similarSignatureStaticAndNonStatic(int o) {
    return "non-static";
  }

  public static String similarSignatureStaticAndNonStatic(Integer o) {
    return "static";
  }

  public Class<Object> overloaded(Object o) {
    return Object.class;
  }

  public Class<Integer> overloaded(Integer o) {
    return Integer.class;
  }

  public Object callObjectWithNull(Object o) {
    Assert.assertNull(o);
    return "callObjectWithNull";
  }

  public Object callNullReturn(int o) {
    Assert.assertEquals(0, o);
    return null;
  }

  public Object callNullReturn(String o) {
    Assert.assertEquals("arg", o);
    return null;
  }

  public Class<String> typednone(String o) {
    return String.class;
  }

  public Class<Integer> typednone(Integer o) {
    return Integer.class;
  }
}
