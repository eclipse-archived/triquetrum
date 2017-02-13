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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.triquetrum.commands.tqcl.CompositeCommand;
import org.eclipse.triquetrum.commands.tqcl.Go;
import org.eclipse.triquetrum.commands.tqcl.Insert;
import org.eclipse.triquetrum.commands.tqcl.TqclPackage;

/**
 * Interpreter component for {@link Go}
 * @author rtotaro
 */
public class GoInterpreterComponent implements TqclInterpreterComponent {

	/* (non-Javadoc)
	 * @see org.eclipse.triquetrum.commands.interpreter.TqclInterpreterComponent#interpret(org.eclipse.emf.ecore.EObject, org.eclipse.triquetrum.commands.interpreter.InterpretContext)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void interpret(EObject element, InterpretContext context) {
		if (element instanceof Go) {
			Go go = (Go) element;
			String direction = go.getDirection();
			
			switch (direction) {
			case "into":
				Insert actor = go.getActor();
				String name = actor.getName();
				context.setCurrentActor(context.getModelBuilderService().getChild(context.getCurrentActor(),name));
				break;
			case "out":
				context.setCurrentActor(context.getModelBuilderService().getParent(context.getCurrentActor()));
				break;

			default:
				break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.triquetrum.commands.interpreter.TqclInterpreterComponent#intepretedEClasses()
	 */
	@Override
	public List<EClass> intepretedEClasses() {
		return Arrays.asList(TqclPackage.Literals.GO);
	}

}

