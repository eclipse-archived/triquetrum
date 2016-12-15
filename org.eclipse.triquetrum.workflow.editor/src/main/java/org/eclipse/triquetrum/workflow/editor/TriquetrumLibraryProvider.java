package org.eclipse.triquetrum.workflow.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.triquetrum.commands.api.services.ActorDescriptor;
import org.eclipse.triquetrum.commands.api.services.DirectorDescriptor;
import org.eclipse.triquetrum.commands.api.services.EntityDescriptor;
import org.eclipse.triquetrum.commands.api.services.ParameterDescriptor;
import org.eclipse.triquetrum.commands.api.services.PortDescriptor;
import org.eclipse.triquetrum.commands.api.services.TqCLLibraryException;
import org.eclipse.triquetrum.commands.api.services.TqCLLibraryProvider;
import org.eclipse.triquetrum.workflow.model.util.PtolemyUtil;
import org.osgi.framework.Version;
import org.ptolemy.commons.ThreeDigitVersionSpecification;
import org.ptolemy.commons.VersionSpecification;

import ptolemy.actor.CompositeActor;
import ptolemy.actor.IOPort;
import ptolemy.actor.TypedIOPort;
import ptolemy.data.expr.Parameter;
import ptolemy.kernel.Entity;
import ptolemy.kernel.Port;
import ptolemy.kernel.util.Attribute;

public class TriquetrumLibraryProvider implements TqCLLibraryProvider {

	private static final String TRIQUETRUM_LIBRARY_NAME = "Triquetrum";
	private Map<String, ActorDescriptor> actors = new HashMap<>();
	private Map<String, DirectorDescriptor> directors = new HashMap<>();
	private Map<String, ParameterDescriptor> parameters = new HashMap<>();
	private Map<String, PortDescriptor> ports = new HashMap<>();

	private boolean initialized = false;

	private void init() {
		if (initialized) {
			return;
		}
		IExtensionPoint extPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(TriqFeatureProvider.PALETTE_CONTRIBUTION_EXTENSION_ID);
		IExtension[] extensions = extPoint.getExtensions();
		for (IExtension extension : extensions) {
			IConfigurationElement[] configurationElements = extension.getConfigurationElements();
			for (IConfigurationElement group : configurationElements) {
				fillElementsFromGroup(group);
			}
		}

		PortDescriptor port = new PortDescriptor(TRIQUETRUM_LIBRARY_NAME, "port", TypedIOPort.class.getName(), null,true,true);
		ParameterDescriptor inputParameter = new ParameterDescriptor(TRIQUETRUM_LIBRARY_NAME, "input", Parameter.class.getName(), null);
		inputParameter.setDefaultValue("false");
		port.addParameter(inputParameter);

		ParameterDescriptor outputParameter = new ParameterDescriptor(TRIQUETRUM_LIBRARY_NAME, "output", Parameter.class.getName(), null);
		outputParameter.setDefaultValue("false");
		port.addParameter(outputParameter);
		
		ParameterDescriptor multiParameter = new ParameterDescriptor(TRIQUETRUM_LIBRARY_NAME, "multi", Parameter.class.getName(), null);
		multiParameter.setDefaultValue("false");
		port.addParameter(multiParameter);
		
		ports.put(TypedIOPort.class.getName(),
				port);

		initialized = true;
	}

	public void fillElementsFromGroup(IConfigurationElement group) {
		String groupName = group.getAttribute("displayName");

		IConfigurationElement[] children = group.getChildren();
		for (IConfigurationElement element : children) {
			if (element.getName().equals("entry")) {
				String clazz = element.getAttribute("class");
				String displayName = element.getAttribute("displayName");
				String icon = element.getAttribute("icon");
				String type = element.getAttribute("type");
				if (type != null) {
					BoCategory boCategory = BoCategory.valueOf(type);
					switch (boCategory) {
					case Actor:
						ActorDescriptor actorDescriptor = new ActorDescriptor(TRIQUETRUM_LIBRARY_NAME, displayName,
								clazz, icon);
						fillParameters(actorDescriptor);
						actors.put(actorDescriptor.getClazz(), actorDescriptor);
						break;
					case Director:
						DirectorDescriptor directorDescriptor = new DirectorDescriptor(TRIQUETRUM_LIBRARY_NAME,
								displayName, clazz, icon);
						fillParameters(directorDescriptor);
						directors.put(directorDescriptor.getClazz(), directorDescriptor);
						break;
					case Parameter:
						ParameterDescriptor parameterDescriptor = new ParameterDescriptor(TRIQUETRUM_LIBRARY_NAME,
								displayName, clazz, icon);
						fillParameters(parameterDescriptor);
						parameters.put(parameterDescriptor.getClazz(), parameterDescriptor);
						break;
					case Port:
//						PortDescriptor port = new PortDescriptor(TRIQUETRUM_LIBRARY_NAME, "input", clazz, null);
//						ports.put(clazz, port);
						break;
					default:
						System.out.println("unsupported category " + boCategory.name());
						break;
					}
				}
			} else if (element.getName().equals("group")) {
				fillElementsFromGroup(element);
			}

		}
	}

	private void fillParameters(EntityDescriptor entityDescriptor) {
		CompositeActor ptolemyModel = new CompositeActor();

		Version bundleVersion = TriqEditorPlugin.getDefault().getBundle().getVersion();
		VersionSpecification providerVersion = new ThreeDigitVersionSpecification(bundleVersion.getMajor(),
				bundleVersion.getMinor(), bundleVersion.getMicro(), bundleVersion.getQualifier());
		try {
			List<Parameter> paramList = new ArrayList<>();
			if (entityDescriptor instanceof ActorDescriptor) {
				ActorDescriptor actorDescriptor = (ActorDescriptor) entityDescriptor;
				Entity<?> entity = PtolemyUtil._createEntity(ptolemyModel, actorDescriptor.getClazz(), providerVersion,
						"parameterfilling");
				paramList = entity.attributeList(Parameter.class);
				fillPorts(actorDescriptor, entity);
			} else {
				Attribute attribute = PtolemyUtil._createAttribute(ptolemyModel, entityDescriptor.getClazz(),
						"parameterfilling");
				paramList = attribute.attributeList(Parameter.class);
			}
			for (Parameter ptolemyParameter : paramList) {
				ParameterDescriptor parameter = new ParameterDescriptor(TRIQUETRUM_LIBRARY_NAME,
						ptolemyParameter.getName(), ptolemyParameter.getClassName(), null);
				parameter.setDefaultValue(ptolemyParameter.getDefaultExpression());
				entityDescriptor.addParameter(parameter);
			}
			if (entityDescriptor instanceof ParameterDescriptor) {
				ParameterDescriptor parameter = new ParameterDescriptor(TRIQUETRUM_LIBRARY_NAME, "expression",
						String.class.getName(), null);
				entityDescriptor.addParameter(parameter);
			}
		} catch (

		Exception e) {
			// LOG
			e.printStackTrace();
		}
	}

	public void fillPorts(ActorDescriptor actorDescriptor, Entity<?> entity) {
		List<?> portList = entity.portList();
		for (Object object : portList) {
			if (object instanceof IOPort) {
				IOPort port = (IOPort) object;
				PortDescriptor portDescriptor = new PortDescriptor(TRIQUETRUM_LIBRARY_NAME, port.getName(),
						port.getClassName(), null,port.isInput(),port.isOutput());
				actorDescriptor.addPort(portDescriptor);
			} else {
				System.out.println("");
			}
		}
	}

	@Override
	public List<ActorDescriptor> getActors(String library) throws TqCLLibraryException {
		init();
		return new ArrayList<>(actors.values());
	}

	@Override
	public List<DirectorDescriptor> getDirectors(String library) throws TqCLLibraryException {
		init();
		return new ArrayList<>(directors.values());
	}

	@Override
	public List<ParameterDescriptor> getParameterTypes(String library) throws TqCLLibraryException {

		init();
		return new ArrayList<>(parameters.values());
	}

	@Override
	public List<PortDescriptor> getPortTypes(String library) throws TqCLLibraryException {
		init();
		return new ArrayList<>(ports.values());
	}

	@Override
	public List<PortDescriptor> getActorPorts(String actor) throws TqCLLibraryException {
		init();
		return actors.get(actor).getPorts();
	}

	@Override
	public List<String> getActorParameters(String actor) throws TqCLLibraryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getLibraryNames() {
		HashSet<String> libraries = new HashSet<String>();
		libraries.add(TRIQUETRUM_LIBRARY_NAME);
		return libraries;
	}

	@Override
	public boolean hasElement(String element) {
		return actors.containsKey(element) || directors.containsKey(element) || parameters.containsKey(element);
	}

	@Override
	public ActorDescriptor getActor(String library, String actorClass) throws TqCLLibraryException {
		if (getLibraryNames().contains(library)) {
			return actors.get(actorClass);
		}
		return null;
	}

	@Override
	public ActorDescriptor getActor(String actorClass) throws TqCLLibraryException {
		return actors.get(actorClass);
	}

	@Override
	public DirectorDescriptor getDirector(String directorClass) throws TqCLLibraryException {
		return directors.get(directorClass);
	}

	@Override
	public boolean hasElementInLibrary(String element, String library, String category) {
		init();
		if (getLibraryNames().contains(library)) {
			BoCategory boCategory = BoCategory.valueOf(StringUtils.capitalize(category));
			switch (boCategory) {
			case Actor:
				return actors.containsKey(element);
			case Director:
				return directors.containsKey(element);
			case Parameter:
				return parameters.containsKey(element);
			case Port:
				return ports.containsKey(element);
			// TODO: remove default when switch is completed
			default:
				return true;
			}
		}
		return false;
	}

	@Override
	public ParameterDescriptor getParameterType(String parameterClass) {
		return parameters.get(parameterClass);
	}

	@Override
	public PortDescriptor getPortType(String portClass) throws TqCLLibraryException {
		return ports.get(portClass);
	}

}
