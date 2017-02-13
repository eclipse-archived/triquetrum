package org.eclipse.triquetrum.commands.interpreter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.triquetrum.commands.api.services.TcQLException;
import org.eclipse.triquetrum.commands.tqcl.Category;
import org.eclipse.triquetrum.commands.tqcl.CompositeCommand;
import org.eclipse.triquetrum.commands.tqcl.Insert;
import org.eclipse.triquetrum.commands.tqcl.Parameter;
import org.eclipse.triquetrum.commands.tqcl.TqclPackage;
import org.eclipse.triquetrum.commands.validation.TqCLUtils;

/**
 * Interpreter component for {@link Insert}
 * @author rtotaro
 */
public class InsertInterpreterComponent implements TqclInterpreterComponent {

	/* (non-Javadoc)
	 * @see org.eclipse.triquetrum.commands.interpreter.TqclInterpreterComponent#interpret(org.eclipse.emf.ecore.EObject, org.eclipse.triquetrum.commands.interpreter.InterpretContext)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void interpret(EObject element, InterpretContext context) {
		if (element instanceof Insert) {
			Insert insert = (Insert) element;
			Category category = insert.getCategory();
			String entityClass = TqCLUtils.cleanEntityClassName(insert.getEntityClass());
			String name = insert.getName();
			Map<String, String> params = extractParameterMap(insert);
			switch (category) {
			case ACTOR:
				context.getModelBuilderService().insertActor(context.getCurrentActor(), name, entityClass, params);
				break;
			case DIRECTOR:
				context.getModelBuilderService().insertDirector(context.getCurrentActor(), name, entityClass, params);
				break;
			case PARAMETER:
				context.getModelBuilderService().insertParameter(context.getCurrentActor(), name, entityClass, params);
				break;
			case PORT:
				context.getModelBuilderService().insertPort(context.getCurrentActor(), name, entityClass, params);
				break;
			}

		}
	}

	private Map<String, String> extractParameterMap(Insert insert) {
		Map<String, String> params = new HashMap<>();
		for (Parameter parameter : insert.getParameters()) {
			params.put(TqCLUtils.cleanParameterName(parameter.getId()), parameter.getValue());
		}
		return params;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.triquetrum.commands.interpreter.TqclInterpreterComponent#intepretedEClasses()
	 */
	@Override
	public List<EClass> intepretedEClasses() {
		return Arrays.asList(TqclPackage.Literals.INSERT);
	}

}
