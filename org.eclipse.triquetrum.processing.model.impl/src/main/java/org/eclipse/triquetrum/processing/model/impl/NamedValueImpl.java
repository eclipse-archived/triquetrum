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
package org.eclipse.triquetrum.processing.model.impl;

import java.io.Serializable;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.eclipse.triquetrum.processing.model.DataType;
import org.eclipse.triquetrum.processing.model.NamedValue;

public class NamedValueImpl<V extends Serializable> implements NamedValue<V> {
  private static final long serialVersionUID = 1189536629039819207L;

  private String name;
  private V value;

  /**
   * @param name
   * @param value
   */
  public NamedValueImpl(String name, V value) {
    this.name = name;
    this.value = value;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public V getValue() {
    return value;
  }

  @Override
  public DataType getDataType() {
    return DataType.fromJavaType(value);
  }

  @Override
  public String getValueAsString() {
    return String.valueOf(value);
  }

  @Override
  public int compareTo(NamedValue<V> o) {
    return new CompareToBuilder().append(name, o.getName()).append(value, o.getValue()).toComparison();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(11, 31).toHashCode();
  }

  @SuppressWarnings("rawtypes")
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    NamedValueImpl other = (NamedValueImpl) obj;
    return new EqualsBuilder().append(name, other.name).append(value, other.value).isEquals();
  }

  @Override
  public String toString() {
    return "NamedValueImpl [name=" + name + ", value=" + value + "]";
  }
}
