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
package org.eclipse.triquetrum.commands;


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class TqclStandaloneSetup extends TqclStandaloneSetupGenerated {

	public static void doSetup() {
		new TqclStandaloneSetup().createInjectorAndDoEMFRegistration();
	}				
}
