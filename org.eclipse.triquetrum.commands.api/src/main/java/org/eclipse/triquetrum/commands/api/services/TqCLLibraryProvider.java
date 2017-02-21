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
package org.eclipse.triquetrum.commands.api.services;

import java.util.List;
import java.util.Set;

public interface TqCLLibraryProvider {
	
	public List<ActorDescriptor> getActors(String library) throws TqCLLibraryException;
	
	public ActorDescriptor getActor(String library,String actorClass) throws TqCLLibraryException;
	
	public ActorDescriptor getActor(String actorClass) throws TqCLLibraryException;
	
	public List<DirectorDescriptor> getDirectors(String library) throws TqCLLibraryException;

	public DirectorDescriptor getDirector(String directorClass) throws TqCLLibraryException;
	
	public List<ParameterDescriptor> getParameterTypes(String library) throws TqCLLibraryException;
	
	public ParameterDescriptor getParameterType(String parameterClass);

	public List<PortDescriptor> getPortTypes(String library) throws TqCLLibraryException;
	
	public PortDescriptor getPortType(String portClass) throws TqCLLibraryException;
	
	public List<PortDescriptor> getActorPorts(String actor) throws TqCLLibraryException;
	
	public List<String> getActorParameters(String actor) throws TqCLLibraryException;
	
	public Set<String> getLibraryNames();
	
	public boolean hasElement(String element);
	
	public boolean hasElementInLibrary(String element, String library, String category);


	
}
