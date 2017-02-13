package org.eclipse.triquetrum.commands.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.triquetrum.commands.api.TqCLServices;
import org.eclipse.triquetrum.commands.api.services.PortDescriptor;
import org.eclipse.triquetrum.commands.api.services.TqCLLibraryException;
import org.eclipse.triquetrum.commands.tqcl.Category;
import org.eclipse.triquetrum.commands.tqcl.Command;
import org.eclipse.triquetrum.commands.tqcl.CompositeCommand;
import org.eclipse.triquetrum.commands.tqcl.Insert;
import org.eclipse.triquetrum.commands.tqcl.Parameter;
import org.eclipse.xtext.EcoreUtil2;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * Utils class to clean qualified name used in tqcl and create valid name for ptolemy models
 * @author rtotaro
 *
 */
public class TqCLUtils {

	public static String cleanEntityName(String entityClass) {
		return entityClass.substring(1, entityClass.lastIndexOf('>'));
	}

	public static Category getCategory(Insert insert) {
		Category category = insert.getCategory();
		if (category == null) {
			category = Category.ACTOR;
		}
		return category;
	}

	public static String cleanParameterName(String id) {

		if (id.startsWith("$")) {
			return id.substring(1);
		} else if (id.startsWith("\"")) {
			return id.substring(1, id.lastIndexOf('"'));
		}

		// TODO : throw exception???
		return null;
	}

	public static String createEditorEntityName(String entityName) {
		return entityName.contains(" ") ? ("\"" + entityName + "\"") : entityName;

	}

	public static String getParameterValueByName(Insert insert, String parameterName) {
		EList<Parameter> parameters = insert.getParameters();
		for (Parameter parameter : parameters) {
			if (parameterName.equals(cleanParameterName(parameter.getId()))) {
				return parameter.getValue();
			}
		}

		return null;
	}
	
	public static List<PortDescriptor> getActorInsertPorts(Insert actor,final boolean input,final boolean output) {
		String actorClass = actor.getEntityClass();
		List<PortDescriptor> actorPorts = new ArrayList<>();
		try {
			List<PortDescriptor> candidateLibraryPorts = TqCLServices.getInstance().getTqclLibraryProvider()
					.getActorPorts(TqCLUtils.cleanEntityName(actorClass));
			
			actorPorts.addAll(Collections2.filter(candidateLibraryPorts,new Predicate<PortDescriptor>() {
				@Override
				public boolean apply(PortDescriptor candidatePort) {
					return (candidatePort.isInput()&&input)||(candidatePort.isOutput()&&output);
				}
			}));

			fillDeclaredPorts(actor, actorPorts,input,output);


		} catch (TqCLLibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actorPorts;
	}

	private static void fillDeclaredPorts(Insert actor, List<PortDescriptor> actorPorts, boolean input, boolean output) {
		EObject actorContainer = actor.eContainer();
		List<CompositeCommand> compositeCommands = EcoreUtil2.getAllContentsOfType(actorContainer,
				CompositeCommand.class);
		Collection<CompositeCommand> filteredCompositeCommand = Collections2.filter(compositeCommands,
				new Predicate<CompositeCommand>() {
					@Override
					public boolean apply(CompositeCommand compositeCommand) {
						return compositeCommand.getStart().getActor().getName().equals(actor.getName());
					}
				});

		for (CompositeCommand compositeCommand : filteredCompositeCommand) {
			EList<Command> commands = compositeCommand.getCommands();
			for (Command command : commands) {
				if (command instanceof Insert) {
					Insert insert = (Insert) command;
					if (Category.PORT.equals(insert.getCategory())) {
						Boolean inputPort = Boolean.parseBoolean(TqCLUtils.getParameterValueByName(insert, "input"));
						Boolean outputPort = Boolean.parseBoolean(TqCLUtils.getParameterValueByName(insert, "output"));

						if ((input && inputPort) || (output && outputPort)) {
							actorPorts.add(new PortDescriptor("script", insert.getName(),
									TqCLUtils.cleanEntityName(insert.getEntityClass()), null,inputPort,outputPort));
						}
					}

				}
			}
		}
	}

	public static String cleanEntityClassName(String entityClass) {
		return entityClass.substring(1, entityClass.lastIndexOf('>'));
	}

}
