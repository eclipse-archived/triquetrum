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

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IFlattener;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IFlattens;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IRootFlattener;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.RootFlattener;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * One of three tests that demonstrate creating custom flatteners and unflatteners.
 * <ul>
 * <li>{@link AddHelperSimpleFlatteningTest} - Flattener for a simple class.
 * <li>{@link AddHelperSpecializedMapFlatteningTest} - Flattener for a class that is a specialisation of a class that flattening is already supported for, in
 * this example a specialised {@link Map}.
 * <li>{@link AddHelperSimpleWithInterfaceFlatteningTest} - Flattener for a simple class. Demonstrates use of {@link IFlattens}.
 * </ul>
 */
public class AddHelperSpecializedMapFlatteningTest {
  @Rule
  public Timeout globalTimeout = Timeout.seconds(2);

  /**
   * Dummy class that doesn't do anything, only exists to verify flattening and unflattening
   * <p>
   * This is an implementation of Map which does the silly operation of only supporting mapping a string to itself. (This is not a complete example of a custom
   * Map, only enough functionality is provided to demonstrate writing a custom flattener for it.)
   */
  private static class MySpecialMap extends AbstractMap<String, String> {

    private Map<String, String> map = new HashMap<String, String>();

    public void putIdentity(String id) {
      map.put(id, id);
    }

    @Override
    public Set<java.util.Map.Entry<String, String>> entrySet() {
      return map.entrySet();
    }

  }

  private static class AddHelperIFlattener implements IFlattener<MySpecialMap> {

    private static final String CONTENTS = "contents";

    @Override
    public Object flatten(Object obj, IRootFlattener root) {
      MySpecialMap myObj = (MySpecialMap) obj;
      Map<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put(IFlattener.TYPE_KEY, MySpecialMap.class.getCanonicalName());
      hashMap.put(CONTENTS, myObj.keySet().toArray()); // we only need to save the keys
      return hashMap;
    }

    @Override
    public MySpecialMap unflatten(Object obj, IRootFlattener root) {
      @SuppressWarnings("unchecked")
      Map<String, Object> map = (Map<String, Object>) obj;
      MySpecialMap mySpecialMap = new MySpecialMap();
      Object[] contents = (Object[]) map.get(CONTENTS);
      for (Object object : contents) {
        mySpecialMap.putIdentity((String) object);
      }
      return mySpecialMap;
    }

    @Override
    public boolean canFlatten(Object obj) {
      return obj instanceof MySpecialMap;
    }

    @Override
    public boolean canUnFlatten(Object obj) {
      if (obj instanceof Map) {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) obj;
        if (MySpecialMap.class.getCanonicalName().equals(map.get(IFlattener.TYPE_KEY))) {
          return true;
        }
      }
      return false;
    }

  }

  @Test
  public void testAddNewHelper() {
    IRootFlattener root = new RootFlattener();

    // create an instance to work with
    MySpecialMap myMap = new MySpecialMap();
    try {
      // Demonstrate that this class is writable via the putIdentity method
      myMap.put("hello", "goodbye");
      Assert.fail();
    } catch (UnsupportedOperationException e) {
    }

    myMap.putIdentity("foo");
    myMap.putIdentity("bar");

    // because the default flatteners already contain a generic map flattener, we can flatten/unflatten
    // myMap, but we don't get out a MySpecialMap, but rather just a Map
    Assert.assertTrue(root.canFlatten(myMap));
    @SuppressWarnings("unchecked")
    Map<String, String> simpleMap = (Map<String, String>) root.unflatten(root.flatten(myMap));
    // this can be demonstrated by attempting the same operation that just failed
    simpleMap.put("hello", "goodbye");

    // Now register our own flattener and check what we get out is a MySpecialMap that put throws an exception for
    root.addHelper(new AddHelperIFlattener());

    // flatten and unflatten with my new helper, it will now give us a MySpecialMap which correctly
    // doesn't support put
    @SuppressWarnings("unchecked")
    Map<String, String> mySpecialMap = (Map<String, String>) root.unflatten(root.flatten(myMap));
    Assert.assertTrue(mySpecialMap instanceof MySpecialMap);
    try {
      mySpecialMap.put("hello", "goodbye");
      Assert.fail();
    } catch (UnsupportedOperationException e) {
    }
  }
}
