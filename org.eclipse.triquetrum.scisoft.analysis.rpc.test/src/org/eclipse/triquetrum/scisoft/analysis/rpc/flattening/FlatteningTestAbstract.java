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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcRemoteException;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IRootFlattener;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.RootFlattener;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.TypedNone;
import org.eclipse.triquetrum.scisoft.analysis.rpc.internal.AnalysisRpcDoubleParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

abstract public class FlatteningTestAbstract {
  @Rule
  public Timeout globalTimeout = Timeout.seconds(2);

  /**
   * Waiting period for server to start up (in milliseconds)
   */
  public static final long SERVER_WAIT_TIME = 200;

  protected static IRootFlattener flattener;

  @BeforeClass
  public static void setUp() {
    flattener = new RootFlattener();
  }

  /**
   * Special version of Assert.assertEquals that, for some types, uses reflection or other knowledge instead of equals() to test for equality. The reason for
   * this is to work around insufficient or unsuitable equals implementations in those classes. Arrays, Lists and Maps are compared deeply.
   *
   * @param expected
   *          first object to test
   * @param actual
   *          second object to test
   */
  protected void assertFlattenEquals(Object expected, Object actual) {
    if (expected == null) {
      Assert.assertNull(actual);
    } else if (expected instanceof Exception) {
      Exception expException = (Exception) expected;
      Exception actException = (Exception) actual;
      // The message gets supplemented with extra type information, make sure the original expected
      // message is present in the supplemented info
      if (!actException.getMessage().contains(expException.getMessage()))
        Assert.fail("Expected message: " + expException.getMessage() + " actual message: " + actException.getMessage());
      StackTraceElement[] expStackTrace = expException.getStackTrace();
      StackTraceElement[] actStackTrace = actException.getStackTrace();
      Assert.assertArrayEquals(expStackTrace, actStackTrace);
    } else if (expected instanceof List) {
      List<?> expectedlist = (List<?>) expected;
      List<?> actuallist = (List<?>) actual;
      Assert.assertEquals(expectedlist.size(), actuallist.size());
      for (int i = 0; i < actuallist.size(); i++) {
        assertFlattenEquals(expectedlist.get(i), actuallist.get(i));
      }
    } else if (expected instanceof byte[]) {
      byte[] expectedarray = (byte[]) expected;
      byte[] actualarray = (byte[]) actual;
      Assert.assertArrayEquals(expectedarray, actualarray);
    } else if (expected instanceof int[]) {
      int[] expectedarray = (int[]) expected;
      int[] actualarray = (int[]) actual;
      Assert.assertArrayEquals(expectedarray, actualarray);
    } else if (expected instanceof double[]) {
      double[] expectedarray = (double[]) expected;
      double[] actualarray = (double[]) actual;
      Assert.assertArrayEquals(expectedarray, actualarray, 0.0);
    } else if (expected instanceof boolean[]) {
      boolean[] expectedarray = (boolean[]) expected;
      boolean[] actualarray = (boolean[]) actual;
      Assert.assertTrue(Arrays.equals(expectedarray, actualarray));
    } else if (expected instanceof Object[]) {
      Object[] expectedarray = (Object[]) expected;
      Object[] actualarray = (Object[]) actual;
      Assert.assertEquals(expectedarray.length, actualarray.length);
      for (int i = 0; i < actualarray.length; i++) {
        assertFlattenEquals(expectedarray[i], actualarray[i]);
      }
    } else if (expected instanceof Map) {
      Map<?, ?> expectedMap = (Map<?, ?>) expected;
      Map<?, ?> actualMap = (Map<?, ?>) actual;
      Assert.assertEquals(expectedMap.size(), actualMap.size());
      Set<?> entrySet = expectedMap.entrySet();
      for (Object object : entrySet) {
        Entry<?, ?> entry = (Entry<?, ?>) object;
        Assert.assertTrue(actualMap.containsKey(entry.getKey()));
        assertFlattenEquals(entry.getValue(), actualMap.get(entry.getKey()));
      }
    } else {
      Assert.assertEquals(expected, actual);
    }
  }

  protected Object flattenAndUnflatten(Object inObj) {
    return flattenAndUnflatten(inObj, inObj);
  }

  private Object flattenAndUnflatten(Object inObj, Object expectedObj) {
    return flattenAndUnflatten(inObj, expectedObj, expectedObj.getClass());
  }

  protected abstract Object doActualFlattenAndUnflatten(Object inObj);

  private Object flattenAndUnflatten(Object inObj, Object expectedObj, Class<?> expectedType) {
    final Object out = doActualFlattenAndUnflatten(inObj);

    assertFlattenEquals(expectedObj, out);
    if (expectedObj != null && expectedObj.getClass().equals(expectedType)) {
      Assert.assertEquals(expectedType, out.getClass());
    } else if (expectedType != null) {
      Assert.assertTrue(expectedType.isAssignableFrom(out.getClass()));
    }

    // finally, take a unflattened item and make sure it has made something
    // that is fully flattenable still
    assertFlattenEquals(out, doActualFlattenAndUnflatten(out));

    return out;
  }

  @Test
  public void testInteger() {
    flattenAndUnflatten(18);
    flattenAndUnflatten(-7);
    flattenAndUnflatten(0);
    flattenAndUnflatten(Integer.MIN_VALUE);
    flattenAndUnflatten(Integer.MAX_VALUE);
  }

  @Test
  public void testBoolean() {
    flattenAndUnflatten(true);
    flattenAndUnflatten(false);
  }

  @Test
  public void testString() {
    flattenAndUnflatten("");
    flattenAndUnflatten("bananas");
    flattenAndUnflatten("\nhello\tgoodbye");
  }

  @Test
  public void testUnicodeString() {
    flattenAndUnflatten("\u00b0");
  }

  @Test
  public void testDouble() {
    flattenAndUnflatten(0);
    flattenAndUnflatten(Math.PI);
    flattenAndUnflatten(Double.MIN_VALUE);
    flattenAndUnflatten(Double.MAX_VALUE);
  }

  /**
   * Note NaN, +/-Inf only work if AnalysisRpc is in the loop or Java is talking to Java. Therefore this method overridden by classes that don't support it.
   * Open hierarchy to see where this test really exists.
   * <p>
   * Note the subclasses that do not support it don't matter because they were written simply to identify these types of issues.
   *
   * @See {@link AnalysisRpcDoubleParser}
   */
  @Test
  public void testDoubleSpecialValues() {
    flattenAndUnflatten(Double.NaN);
    flattenAndUnflatten(Double.NEGATIVE_INFINITY);
    flattenAndUnflatten(Double.POSITIVE_INFINITY);
  }

  @Test
  public void testByeArray() {
    flattenAndUnflatten(new byte[] { 1, 5, -7 });
    flattenAndUnflatten(new byte[0]);
    flattenAndUnflatten(new byte[1000]);
  }

  @Test
  public void testMap() {
    HashMap<String, Double> hashMap = new HashMap<String, Double>();
    hashMap.put("pi", Math.PI);
    hashMap.put("One", new Double(1));
    flattenAndUnflatten(hashMap, hashMap, Map.class);

    TreeMap<String, Double> treeMap = new TreeMap<String, Double>(hashMap);
    flattenAndUnflatten(treeMap, treeMap, Map.class);

    HashMap<String, Object> hashMap2 = new HashMap<String, Object>(hashMap);
    hashMap2.put("Integer", Integer.valueOf(0));
    hashMap2.put("Integer", Integer.valueOf(100));
    flattenAndUnflatten(hashMap2, hashMap2, Map.class);

    HashMap<Object, Object> nonStringKeys = new HashMap<Object, Object>(hashMap2);
    nonStringKeys.put(Integer.valueOf(5), Integer.valueOf(0));
    nonStringKeys.put(Double.valueOf(10.0), "Filename");
    flattenAndUnflatten(nonStringKeys, nonStringKeys, Map.class);
  }

  @Test
  public void testObjectArrays() {
    flattenAndUnflatten(new Object[] { new Double(1.2), new Integer(2) });

    // arrays of things which look like arrays of integers, double or booleans come out as such
    flattenAndUnflatten(new Object[] { 0, 1, 2, 3 }, new int[] { 0, 1, 2, 3 });
    flattenAndUnflatten(
        new Object[] { new Object[] { 0, 1, 2, 3 }, new Object[] { 4, 5, 6, 7 }, new Object[] { 8, 9, 10, 11 }, new Object[] { 12, 13, 14, 15 } },
        new int[][] { new int[] { 0, 1, 2, 3 }, new int[] { 4, 5, 6, 7 }, new int[] { 8, 9, 10, 11 }, new int[] { 12, 13, 14, 15 } });
    flattenAndUnflatten(new Object[][] { { 0, 1 }, { 2, 3 } }, new int[][] { new int[] { 0, 1 }, new int[] { 2, 3 } });

    // Empty arrays come out as array of Object[]
    flattenAndUnflatten(new Object[0], new Object[0]);
    flattenAndUnflatten(new int[0], new Object[0]);
    flattenAndUnflatten(new String[0], new Object[0]);

    // arrays of other types come out as arrays if the class of each element of the array is the same
    flattenAndUnflatten(new String[] { "one", "two" }, new String[] { "one", "two" }); // String[] --> String[]
    flattenAndUnflatten(new Object[] { "one", "two" }, new String[] { "one", "two" }); // Object[] --> String[]
    flattenAndUnflatten(new Object[] { new Integer(0), "one" }, new Object[] { new Integer(0), "one" }); // Object[] --> Object[]

    // arrays with some (but not all) nulls follow the rule above
    flattenAndUnflatten(new String[] { null, "two" }, new String[] { null, "two" }); // String[] --> String[]
    flattenAndUnflatten(new String[] { "one", null }, new String[] { "one", null }); // String[] --> String[]
    flattenAndUnflatten(new Object[] { null, "two" }, new String[] { null, "two" }); // Object[] --> String[]

    // arrays with all nulls in them come out as arrays of Object[]
    flattenAndUnflatten(new String[] { null, null }, new Object[] { null, null }); // String[] --> Object[]
    flattenAndUnflatten(new Object[] { null, null }, new Object[] { null, null }); // Object[] --> Object[]
  }

  @Test
  public void testLists() {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("one");
    strings.add("two");
    strings.add("three");

    // Lists are to the same type as every element in the array if they are all the same class...
    flattenAndUnflatten(strings, strings.toArray(new String[0]));

    ArrayList<Number> nums = new ArrayList<Number>();
    nums.add(new Double(0.0));
    nums.add(new Integer(1));

    // ...otherwise they unflatten to an array of Object[]
    flattenAndUnflatten(nums, nums.toArray(new Object[0]));
  }

  @Test
  public void testNull() {
    flattenAndUnflatten(null, null, null);

    // test null within other data structures
    Map<Object, Object> map = new HashMap<Object, Object>();
    // map, null key
    map.put(null, "null");
    flattenAndUnflatten(map, map, Map.class);
    // map, null value
    map.put("null", null);
    flattenAndUnflatten(map, map, Map.class);
    // map, null key and value
    map.put(null, null);
    flattenAndUnflatten(map, map, Map.class);

    String[] array = new String[] { "null", null };
    // array, null entry
    flattenAndUnflatten(array);
    // list, null entry
    flattenAndUnflatten(Arrays.asList(array), array);
  }

  @Test
  public void testTypedNull() {
    // A typed null should come out equal
    TypedNone nullDouble = new TypedNone(Double.class);
    flattenAndUnflatten(nullDouble, nullDouble);
  }

  @Test
  public void testException() {
    // Exceptions are always unflattened as AnalysisRpcRemoteException type,
    // with original type information pre-pended to the message
    Exception[] npe_in = new Exception[] { new NullPointerException("Exceptional null happened") };
    AnalysisRpcRemoteException[] npe_out = new AnalysisRpcRemoteException[] { new AnalysisRpcRemoteException("Exceptional null happened") };
    // Make sure the stack traces are the same
    npe_out[0].setStackTrace(npe_in[0].getStackTrace());
    flattenAndUnflatten(npe_in, npe_out);
  }

  @Test
  public void testUUID() {
    flattenAndUnflatten(UUID.fromString("93dfd804-85ba-4074-afce-d621f7f2aac6"));
    flattenAndUnflatten(UUID.fromString("dd09fd5c-bb75-4c8b-854b-7f3bb2c9c399"));
    flattenAndUnflatten(UUID.fromString("00000000-0000-0000-0000-000000000000"));
    flattenAndUnflatten(UUID.fromString("ffffffff-ffff-ffff-ffff-ffffffffffff"));
    flattenAndUnflatten(UUID.fromString("FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"));
    for (int i = 0; i < 100; i++) {
      // There is a reproducibility danger in using random UUIDs in this
      // test in that if one fails to match it cannot simply be re-run to
      // reproduce. The failing UUID should be extracted from the error message
      // stack trace and added to the list above of explicit values.
      // If it fails, the message will look something like:
      // junit.framework.AssertionFailedError: expected:<93dfd804-85ba-4074-afce-d621f7f2aac6> but
      // was:<dd09fd5c-bb75-4c8b-854b-7f3bb2c9c399>
      // ...
      flattenAndUnflatten(UUID.randomUUID());
    }
  }

  // Primitive arrays and arrays of boxed primitives unflatten as primitive arrays
  @Test
  public void testPrimitiveArrays() {
    int[] ints = { 1, 5, -7 };
    flattenAndUnflatten(ints);
    flattenAndUnflatten(ArrayUtils.toObject(ints), ints);

    double[] doubles = { 1.4, 12.6, 0 };
    flattenAndUnflatten(doubles);
    flattenAndUnflatten(ArrayUtils.toObject(doubles), doubles);

    boolean[] booleans = { true, false, false, true };
    flattenAndUnflatten(booleans);
    flattenAndUnflatten(ArrayUtils.toObject(booleans), booleans);

    double[][] doubles2d = { { 1, 5, -7 }, { 1.4, 12.6, 0 } };
    flattenAndUnflatten(doubles2d, new double[][] { doubles2d[0], doubles2d[1] });
    flattenAndUnflatten(new double[][] { doubles2d[0], doubles2d[1] });
  }

}
