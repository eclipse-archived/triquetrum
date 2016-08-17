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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IRootFlattener;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.RootFlattener;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * This test is intended to be as minimal as possible to demonstrate operation of the flattening. For full testing see the {@link FlatteningTestAbstract}
 * hierarchy
 */
public class BasicFlatteningTest {
  @Rule
  public Timeout globalTimeout = Timeout.seconds(2);

  @Test
  public void basicTest() {
    final IRootFlattener f = new RootFlattener();

    Assert.assertEquals(18, f.unflatten(f.flatten(18)));
    Assert.assertEquals("hello", f.unflatten(f.flatten("hello")));

    int[] intArray = { 1, 2, 3 };
    Assert.assertArrayEquals(intArray, (int[]) f.unflatten(f.flatten(intArray)));

    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("one", 1);
    map.put("two", 2);
    map.put("three", 3);
    Assert.assertEquals(map, f.unflatten(f.flatten(map)));
  }

}
