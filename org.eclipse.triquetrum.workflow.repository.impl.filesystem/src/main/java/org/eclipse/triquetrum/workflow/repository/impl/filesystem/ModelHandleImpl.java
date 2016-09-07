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
package org.eclipse.triquetrum.workflow.repository.impl.filesystem;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.eclipse.triquetrum.TriqException;
import org.eclipse.triquetrum.workflow.ErrorCode;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.eclipse.triquetrum.workflow.util.WorkflowUtils;
import org.ptolemy.commons.VersionSpecification;

import ptolemy.actor.CompositeActor;

/**
 * A ModelHandle that identifies a model based on its resource location an identification code and a version.
 * <p>
 * The actual model definition is only loaded on-demand (both the raw xml definition and/or the parsed CompositeActor).
 * </p>
 *
 */
public class ModelHandleImpl implements ModelHandle {
	private static final long serialVersionUID = 1L;

	private URI resourceLocator;
	private String code;
	private VersionSpecification version;
	private transient String rawMOML;
	private transient CompositeActor model;

  /**
   * Use this constructor as the most light-weight representation of a well-defined workflow model.
   *
   * @param code
   * @param version
   * @param resourceLocator
   */
  ModelHandleImpl(String code, VersionSpecification version, URI resourceLocator) {
    this.code = code;
    this.version = version;
    this.resourceLocator = resourceLocator;
  }

  /**
   * Use this constructor when the parsed model is already available,
   * and when you expect that it must be accessed later on in its parsed shape.
   *
   * @param code
   * @param version
   * @param resourceLocator
   * @param model
   */
  ModelHandleImpl(String code, VersionSpecification version, URI resourceLocator, CompositeActor model) {
    this.code = code;
    this.version = version;
    this.resourceLocator = resourceLocator;
    this.model = model;
  }

  @Override
	public String getCode() {
		return code;
	}

	@Override
	public CompositeActor getModel() throws TriqException {
    if (model == null && getResourceLocation()!=null) {
      try {
        model = (CompositeActor) WorkflowUtils.readFrom(getResourceLocation());
      } catch (Exception e) {
        throw new TriqException(ErrorCode.MODEL_LOADING_ERROR, "Error parsing model definition for " + code + " v " + version, e);
      }
    }
    return model;
	}

	@Override
	public String getRawModelDefinition() throws TriqException {
    if (rawMOML == null) {
      try {
        rawMOML = IOUtils.toString(getResourceLocation(), StandardCharsets.UTF_8.name());
      } catch (IOException e) {
        throw new TriqException(ErrorCode.MODEL_LOADING_ERROR, "Error loading raw model definition for " + code + " v " + version, e);
      }
    }
    return rawMOML;
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
