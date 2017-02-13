package org.eclipse.triquetrum.commands.interpreter;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.triquetrum.commands.tqcl.Command;
import org.eclipse.triquetrum.commands.tqcl.CompositeCommand;
import org.eclipse.triquetrum.commands.tqcl.Go;
import org.eclipse.triquetrum.commands.tqcl.TqclPackage;

/**
 * Interpreter component for {@link CompositeCommand}
 * @author rtotaro
 */
public class CompositeCommandInterpreterComponent implements TqclInterpreterComponent {

	/* (non-Javadoc)
	 * @see org.eclipse.triquetrum.commands.interpreter.TqclInterpreterComponent#interpret(org.eclipse.emf.ecore.EObject, org.eclipse.triquetrum.commands.interpreter.InterpretContext)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void interpret(EObject element, InterpretContext context) {
		if (element instanceof CompositeCommand) {
			CompositeCommand compositeCommand = (CompositeCommand) element;
			context.getInterpreter().interpret(compositeCommand.getStart(),context);
			for (Command command : compositeCommand.getCommands()) {
				context.getInterpreter().interpret(command,context);
			}
			Go end = compositeCommand.getEnd();
			if(end!=null)
			{
				context.getInterpreter().interpret(end,context);
			}
		}

	}

	/* (non-Javadoc)
	 * @see org.eclipse.triquetrum.commands.interpreter.TqclInterpreterComponent#intepretedEClasses()
	 */
	@Override
	public List<EClass> intepretedEClasses() {
		return Arrays.asList(TqclPackage.Literals.COMPOSITE_COMMAND);
	}

}
