/*******************************************************************************
 * Copyright (c) 2015-2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.processing.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Date;

import org.eclipse.triquetrum.processing.model.DataType;
import org.junit.Test;

/**
 * A set of unit tests on the DataType enumerator
 *
 */
public class DataTypeTest {

  @Test
  public void testBooleanPrimitiveTrue() {
    assertEquals(DataType.BOOLEAN, DataType.fromJavaType(true));
  }

  @Test
  public void testBooleanTrue() {
    assertEquals(DataType.BOOLEAN, DataType.fromJavaType(Boolean.TRUE));
  }

  @Test
  public void testString() {
    assertEquals(DataType.STRING, DataType.fromJavaType("123"));
  }

  @Test
  public void testDate() {
    assertEquals(DataType.DATE, DataType.fromJavaType(new Date()));
  }

  @Test
  public void testShort() {
    assertEquals(DataType.SHORT, DataType.fromJavaType((short) 1));
  }

  @Test
  public void testShortIsNotInteger() {
    assertFalse(DataType.INTEGER.matchesJavaTypeOf((short) 1));
  }

  @Test
  public void testInteger() {
    assertEquals(DataType.INTEGER, DataType.fromJavaType(1));
  }

  @Test
  public void testIntegerIsNotShort() {
    assertFalse(DataType.SHORT.matchesJavaTypeOf(1));
  }

  @Test
  public void testIntegerIsNotLong() {
    assertFalse(DataType.LONG.matchesJavaTypeOf(1));
  }

  @Test
  public void testLong() {
    assertEquals(DataType.LONG, DataType.fromJavaType(1L));
  }

  @Test
  public void testFloat() {
    assertEquals(DataType.FLOAT, DataType.fromJavaType(1f));
  }

  @Test
  public void testFloatIsNotDouble() {
    assertFalse(DataType.DOUBLE.matchesJavaTypeOf(1f));
  }

  @Test
  public void testDouble() {
    assertEquals(DataType.DOUBLE, DataType.fromJavaType(1d));
  }

  @Test
  public void testDoubleIsNotFloat() {
    assertFalse(DataType.FLOAT.matchesJavaTypeOf(1d));
  }

  @Test
  public void testAny() {
    assertEquals(DataType.ANY, DataType.fromJavaType(new ArrayList<>()));
  }

  @Test
  public void testNull() {
    assertEquals(DataType.NULL, DataType.fromJavaType(null));
  }

}
