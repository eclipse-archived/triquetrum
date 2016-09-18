/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.processing.model.impl;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.eclipse.triquetrum.processing.model.Identifiable;

public abstract class AbstractIdentifiable implements Identifiable {

  private Long id;
  private Date creationTS;

  /**
   * @param id
   * @param creationTS
   */
  protected AbstractIdentifiable(Long id, Date creationTS) {
    this.id = id;
    this.creationTS = creationTS;
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
    return new HashCodeBuilder(11, 23).appendSuper(super.hashCode()).append(id).append(creationTS).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AbstractIdentifiable other = (AbstractIdentifiable) obj;
    return new EqualsBuilder().append(id, other.id).append(creationTS, other.creationTS).isEquals();
  }

}
