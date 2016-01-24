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
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.eclipse.triquetrum.processing.model.Attribute;
import org.eclipse.triquetrum.processing.model.AttributeHolder;

public class AttributeImpl<V extends Serializable> extends NamedValueImpl<V> implements Attribute<V> {
  private static final long serialVersionUID = -7244080822044727139L;

  private Long id;
  private Date creationTS;

  /**
   * @param attributeHolder
   * @param id
   * @param creationTS
   * @param name
   * @param value
   */
  public AttributeImpl(AttributeHolder attributeHolder, Long id, Date creationTS, String name, V value) {
    super(name, value);
    this.id = id;
    this.creationTS = creationTS;
    if (attributeHolder != null) {
      attributeHolder.putAttribute(this);
    }
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public Date getCreationTS() {
    return creationTS;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(13, 23).appendSuper(super.hashCode()).append(id).append(creationTS).toHashCode();
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
    AttributeImpl other = (AttributeImpl) obj;
    return new EqualsBuilder().appendSuper(super.equals(obj)).append(id, other.id).append(creationTS, other.creationTS).isEquals();
  }

  @Override
  public String toString() {
    return "AttributeImpl [id=" + id + ", creationTS=" + creationTS + ", name=" + getName() + ", value=" + getValue() + "]";
  }
}
