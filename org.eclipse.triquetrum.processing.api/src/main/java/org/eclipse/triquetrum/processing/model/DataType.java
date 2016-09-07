/*******************************************************************************
 * Copyright (c) 2015 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.processing.model;

import java.util.Date;
import java.util.Map;
import java.util.function.Predicate;

/**
 * A type enumerator for data items that are used in the Triquetrum processing model.
 */
public enum DataType {
  BOOLEAN(Boolean.class),
  STRING(String.class),
  DATE(Date.class),
  MAP(Map.class),
  SHORT(Short.class),
  INTEGER(Integer.class),
  LONG(Long.class),
  FLOAT(Float.class),
  DOUBLE(Double.class),
  ANY(o -> o!=null),
  NULL(o -> o==null);

  /**
   *
   * @param value
   * @return the enum value matching the type of the given value
   */
  public static DataType fromJavaType(Object value) {
    for (DataType dt : DataType.values()) {
      if (dt.matchesJavaTypeOf(value)) {
        return dt;
      }
    }
    return ANY;
  }

  /**
   *
   * @param value
   * @return whether the enum instance matches the Java type of the given value.
   */
  public boolean matchesJavaTypeOf(Object value) {
    return predicate.test(value);
  }

  // private elements for enum construction

  private Predicate<Object> predicate;

  private DataType(Predicate<Object> predicate) {
    this.predicate = predicate;
  }

  private DataType(final Class<?> javaType) {
    predicate = new Predicate<Object>() {
      private final Class<?> myJavaType = javaType;

      @Override
      public boolean test(Object obj) {
        return myJavaType == null || myJavaType.isInstance(obj);
      }
    };
  }

}
