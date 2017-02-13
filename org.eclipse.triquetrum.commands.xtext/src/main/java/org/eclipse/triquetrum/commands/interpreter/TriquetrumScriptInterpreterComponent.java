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

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.triquetrum.commands.tqcl.Command;
import org.eclipse.triquetrum.commands.tqcl.TqclPackage;
import org.eclipse.triquetrum.commands.tqcl.TriquetrumScript;

/**
 * Interpreter component for {@link TriquetrumScript}
 * @author rtotaro
 *
 */
public class TriquetrumScriptInterpreterComponent implements TqclInterpreterComponent {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void interpret(EObject element, InterpretContext context) {
		if (element instanceof TriquetrumScript) {
			TriquetrumScript script = (TriquetrumScript) element;
			EList<Command> commands = script.getCommands();
			
			for (Command command : commands) {
				context.getInterpreter().interpret(command, context);
			}
		}	
	}

	@Override
	public List<EClass> intepretedEClasses() {
		return Arrays.asList(TqclPackage.Literals.TRIQUETRUM_SCRIPT);
	}

}
