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
package org.eclipse.triquetrum.workflow.execution.impl;

import java.net.URI;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.ptolemy.commons.VersionSpecification;

import ptolemy.actor.CompositeActor;

/**
 * This is an internal ModelHandle implementation, for usage in the workflow execution service.
 *
 */
class ModelHandleImpl implements ModelHandle {
	private static final long serialVersionUID = 1L;

	private URI resourceLocator;
	private String code;
	private VersionSpecification version;
	private String rawModelDefinition;
	private transient CompositeActor model;

	public ModelHandleImpl(CompositeActor model) {
		code = model.getName();
		this.model = model;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public CompositeActor getModel() {
		return model;
	}

	@Override
	public String getRawModelDefinition() {
		return rawModelDefinition;
	}

	@Override
	public URI getResourceLocation() {
		return resourceLocator;
	}

	@Override
	public VersionSpecification getVersion() {
		return version;
	}

  @Override
  public int hashCode() {
    return new HashCodeBuilder(13, 23).append(code).append(version).append(resourceLocator).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ModelHandleImpl other = (ModelHandleImpl) obj;
    return new EqualsBuilder().append(code, other.code).append(version, other.version).append(resourceLocator, other.resourceLocator).isEquals();
  }

  @Override
  public String toString() {
    return "ModelHandleImpl [resourceLocator=" + resourceLocator + ", code=" + code + ", version=" + version + "]";
  }
}
