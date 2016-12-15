package org.eclipse.triquetrum.commands.interpreter;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.triquetrum.commands.tqcl.Command;
import org.eclipse.triquetrum.commands.tqcl.TqclPackage;
import org.eclipse.triquetrum.commands.tqcl.TriquetrumScript;

public class TriquetrumScriptInterpreterComponent implements TqclInterpreterComponent {

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
