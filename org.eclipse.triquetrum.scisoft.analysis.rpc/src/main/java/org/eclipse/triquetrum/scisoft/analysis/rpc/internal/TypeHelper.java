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

package org.eclipse.triquetrum.scisoft.analysis.rpc.internal;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to help map primitive and boxed types.
 * 
 * @see <a href="http://en.wikipedia.org/wiki/Primitive_wrapper_class">Primitive_wrapper_class</a>
 */
public class TypeHelper {
  private static Map<Class<?>, Class<?>> mapPrimitiveToBoxed;

  static {
    // Add all 8 primitive types to the map
    mapPrimitiveToBoxed = new HashMap<Class<?>, Class<?>>();
    mapPrimitiveToBoxed.put(Boolean.TYPE, Boolean.class);
    mapPrimitiveToBoxed.put(Byte.TYPE, Byte.class);
    mapPrimitiveToBoxed.put(Character.TYPE, Character.class);
    mapPrimitiveToBoxed.put(Double.TYPE, Double.class);
    mapPrimitiveToBoxed.put(Float.TYPE, Float.class);
    mapPrimitiveToBoxed.put(Integer.TYPE, Integer.class);
    mapPrimitiveToBoxed.put(Long.TYPE, Long.class);
    mapPrimitiveToBoxed.put(Short.TYPE, Short.class);
  }

  /**
   * @param boxedClass
   * @param primitiveClass
   * @return true if primitiveClass represents the primitive version of boxedClass
   */
  public static boolean isBoxed(Class<? extends Object> boxedClass, Class<?> primitiveClass) {
    Class<?> clazz = mapPrimitiveToBoxed.get(primitiveClass);
    if (clazz == null) {
      // Not a primitive type
      return false;
    }
    return clazz.equals(boxedClass);
  }

  public static boolean isPrimitive(Class<?> clazz) {
    return mapPrimitiveToBoxed.containsKey(clazz);
  }

}
