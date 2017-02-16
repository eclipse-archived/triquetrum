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

public class PortDescriptor extends EntityDescriptor {

	private boolean input;
	
	private boolean output;
	
	
	public PortDescriptor(String sourceLibrary,String displayName, String clazz, String icon,boolean input,boolean output) {
		super(sourceLibrary,displayName, clazz, icon);
		this.input = input;
		this.output = output;
	}

	@Override
	public String getCategory() {
		return "Port";
	}
	
	
	
	public boolean isInput() {
		return input;
	}
	
	public boolean isOutput() {
		return output;
	}

}
