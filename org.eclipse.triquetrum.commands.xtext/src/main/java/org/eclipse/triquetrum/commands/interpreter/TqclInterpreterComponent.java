package org.eclipse.triquetrum.commands.interpreter;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public interface TqclInterpreterComponent {

	void interpret(EObject element, InterpretContext context);
	
	List<EClass> intepretedEClasses();
}
