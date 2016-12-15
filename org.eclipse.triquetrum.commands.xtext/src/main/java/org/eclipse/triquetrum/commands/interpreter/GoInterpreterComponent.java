package org.eclipse.triquetrum.commands.interpreter;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.triquetrum.commands.tqcl.Go;
import org.eclipse.triquetrum.commands.tqcl.Insert;
import org.eclipse.triquetrum.commands.tqcl.TqclPackage;

public class GoInterpreterComponent implements TqclInterpreterComponent {

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

	@Override
	public List<EClass> intepretedEClasses() {
		return Arrays.asList(TqclPackage.Literals.GO);
	}

}
