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

public class ParameterDescriptor extends EntityDescriptor {

	private String defaultValue="";
	
	public ParameterDescriptor(String sourceLibrary,String displayName, String clazz, String icon) {
		super(sourceLibrary,displayName, clazz, icon);
	}

	@Override
	public String getCategory() {
		return "Parameter";
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	

}
