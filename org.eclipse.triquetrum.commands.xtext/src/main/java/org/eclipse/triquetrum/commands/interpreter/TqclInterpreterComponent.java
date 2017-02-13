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
