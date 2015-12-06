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

import org.eclipse.triquetrum.workflow.ModelHandle;
import org.ptolemy.commons.VersionSpecification;

import ptolemy.actor.CompositeActor;
import ptolemy.actor.TypedCompositeActor;

public class ModelHandleImpl implements ModelHandle {
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
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setModel(TypedCompositeActor model) {
		this.model = model;
	}
	
	public void setRawModelDefinition(String rawModelDefinition) {
		this.rawModelDefinition = rawModelDefinition;
	}
	
	public void setResourceLocator(URI resourceLocator) {
		this.resourceLocator = resourceLocator;
	}
	
	public void setVersion(VersionSpecification version) {
		this.version = version;
	}
}
