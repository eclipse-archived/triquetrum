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

import java.util.ArrayList;
import java.util.List;

public class ActorDescriptor extends EntityDescriptor {

	private List<PortDescriptor> ports = new ArrayList<>();
	
	public ActorDescriptor(String sourceLibrary,String displayName, String clazz, String icon) {
		super(sourceLibrary,displayName, clazz, icon);
	}

	@Override
	public String getCategory() {
		return "Actor";
	}
	
	public void addPort(PortDescriptor port)
	{
		ports.add(port);
	}
	
	public List<PortDescriptor> getPorts() {
		return ports;
	}

}
