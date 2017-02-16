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

public class DirectorDescriptor extends EntityDescriptor {

	public DirectorDescriptor(String sourceLibrary,String displayName, String clazz, String icon) {
		super(sourceLibrary,displayName, clazz, icon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getCategory() {
		return "Director";
	}

}
