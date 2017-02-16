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
package org.eclipse.triquetrum.commands.interpreter;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * Interface implements by interpreter components. Each component interpret one
 * or more syntax structure
 * 
 * @author rtotaro
 *
 */
public interface TqclInterpreterComponent {

	/**
	 * Interpret the element adding result to the context
	 * 
	 * @param element
	 * @param context
	 */
	@SuppressWarnings("rawtypes")
	void interpret(EObject element, InterpretContext context);

	/**
	 * Interpreted EClasses, this information is used to choose which component
	 * call during interpretation. An EClass can be interpreted by only one
	 * component
	 * 
	 * @return the list of interpreted classes
	 */
	List<EClass> intepretedEClasses();
}
