/*******************************************************************************
 * Copyright (c)  2017 Totaro Rodolfo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Rodolfo Totaro - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.commands.api.services.impl;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.triquetrum.commands.api.services.ActorDescriptor;
import org.eclipse.triquetrum.commands.api.services.DirectorDescriptor;
import org.eclipse.triquetrum.commands.api.services.EntityDescriptor;
import org.eclipse.triquetrum.commands.api.services.ParameterDescriptor;
import org.eclipse.triquetrum.commands.api.services.PortDescriptor;
import org.eclipse.triquetrum.commands.api.services.TqCLLibraryException;
import org.eclipse.triquetrum.commands.api.services.TqCLLibraryProvider;

public class TcQLLibraryProviderProxy implements TqCLLibraryProvider {

	private Map<String, TqCLLibraryProvider> libraryProviders = new HashMap<>();

	
	@Override
	public List<ActorDescriptor> getActors(String library) throws TqCLLibraryException {
		checkLibrary(library);
		return libraryProviders.get(library).getActors(library);
	}

	private void checkLibrary(String library) throws TqCLLibraryException {
		if (!libraryProviders.containsKey(library)) {
			String message = MessageFormat.format("Library {0} not found", library);
			throw new TqCLLibraryException(message);
		}
	}

	@Override
	public List<DirectorDescriptor> getDirectors(String library) throws TqCLLibraryException {
		checkLibrary(library);
		return libraryProviders.get(library).getDirectors(library);
	}

	@Override
	public List<ParameterDescriptor> getParameterTypes(String library) throws TqCLLibraryException {
		checkLibrary(library);
		return libraryProviders.get(library).getParameterTypes(library);
	}

	@Override
	public List<PortDescriptor> getPortTypes(String library) throws TqCLLibraryException {
		checkLibrary(library);
		return libraryProviders.get(library).getPortTypes(library);
	}

	@Override
	public List<PortDescriptor> getActorPorts(String actor) throws TqCLLibraryException {
		for (TqCLLibraryProvider provider : libraryProviders.values()) {
			boolean hasElement = provider.hasElement(actor);
			if (hasElement) {
				return provider.getActorPorts(actor);
			}
		}
		String message = MessageFormat.format("Actor {0} not found", actor);
		throw new TqCLLibraryException(message);
	}

	@Override
	public List<String> getActorParameters(String actor) throws TqCLLibraryException {
		for (TqCLLibraryProvider provider : libraryProviders.values()) {
			boolean hasElement = provider.hasElement(actor);
			if (hasElement) {
				return provider.getActorParameters(actor);
			}
		}
		String message = MessageFormat.format("Actor {0} not found", actor);
		throw new TqCLLibraryException(message);
	}

	@Override
	public Set<String> getLibraryNames() {
		return libraryProviders.keySet();
	}

	@Override
	public boolean hasElement(String element) {
		for (TqCLLibraryProvider libraryProvider : libraryProviders.values()) {
			if (libraryProvider.hasElement(element)) {
				return true;
			}
		}
		return false;
	}
	

	public void addLibraryProvider(TqCLLibraryProvider tqCLLibraryProvider) {
		Set<String> libraryNames = tqCLLibraryProvider.getLibraryNames();
		for (String library : libraryNames) {
			this.libraryProviders.put(library, tqCLLibraryProvider);
		}
	}

	@Override
	public ActorDescriptor getActor(String library, String actorClass) throws TqCLLibraryException {
		return this.libraryProviders.get(library).getActor(library, actorClass);
	}
	
	@Override
	public ActorDescriptor getActor(String actorClass) throws TqCLLibraryException {
		for (TqCLLibraryProvider provider : libraryProviders.values()) {
			ActorDescriptor actor = provider.getActor(actorClass);
			if(actor!=null)
			{
				return actor;
			}
		}
		return null;
	}
	
	@Override
	public DirectorDescriptor getDirector(String directorClass) throws TqCLLibraryException {
		for (TqCLLibraryProvider provider : libraryProviders.values()) {
			DirectorDescriptor director = provider.getDirector(directorClass);
			if(director!=null)
			{
				return director;
			}
		}
		return null;
	}
	@Override
	public ParameterDescriptor getParameterType(String parameterClass) {
		for (TqCLLibraryProvider provider : libraryProviders.values()) {
			ParameterDescriptor parameter = provider.getParameterType(parameterClass);
			if(parameter!=null)
			{
				return parameter;
			}
		}
		return null;
	}

	@Override
	public boolean hasElementInLibrary(String element, String library,String category) {
		for (TqCLLibraryProvider libraryProvider : libraryProviders.values()) {
			if (libraryProvider.hasElementInLibrary(element,library,category)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public PortDescriptor getPortType(String portClass) throws TqCLLibraryException {
		for (TqCLLibraryProvider provider : libraryProviders.values()) {
			PortDescriptor port = provider.getPortType(portClass);
			if(port!=null)
			{
				return port;
			}
		}
		return null;
	}


}
