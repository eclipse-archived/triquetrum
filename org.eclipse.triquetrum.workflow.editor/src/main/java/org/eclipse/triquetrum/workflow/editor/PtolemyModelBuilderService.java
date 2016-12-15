package org.eclipse.triquetrum.workflow.editor;

import java.util.Map;

import org.eclipse.triquetrum.commands.api.services.ModelBuilderService;
import org.eclipse.triquetrum.workflow.model.util.PtolemyUtil;
import org.osgi.framework.Version;
import org.ptolemy.commons.ThreeDigitVersionSpecification;
import org.ptolemy.commons.VersionSpecification;

import ptolemy.actor.CompositeActor;
import ptolemy.actor.Director;
import ptolemy.actor.IOPort;
import ptolemy.actor.IORelation;
import ptolemy.actor.TypedCompositeActor;
import ptolemy.actor.TypedIORelation;
import ptolemy.data.expr.Parameter;
import ptolemy.kernel.ComponentEntity;
import ptolemy.kernel.ComponentRelation;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.Entity;
import ptolemy.kernel.Port;
import ptolemy.kernel.Relation;
import ptolemy.kernel.util.Attribute;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;

public class PtolemyModelBuilderService implements ModelBuilderService<CompositeEntity, ComponentEntity<?>> {

	@Override
	public Class<?> getSupportedModelClass() {
		return CompositeActor.class;
	}

	@Override
	public CompositeEntity createNewModel(String modelName, String folderPath) {
		return new TypedCompositeActor();
	}

	private VersionSpecification getVersion() {
		Version bundleVersion = TriqEditorPlugin.getDefault().getBundle().getVersion();
		VersionSpecification providerVersion = new ThreeDigitVersionSpecification(bundleVersion.getMajor(),
				bundleVersion.getMinor(), bundleVersion.getMicro(), bundleVersion.getQualifier());
		return providerVersion;
	}

	@Override
	public boolean insertActor(CompositeEntity parent, String actorName, String actorclass,
			Map<String, String> parameters) {
		try {
			Entity<?> actor = PtolemyUtil._createEntity(parent, actorclass, /*getVersion()*/null, actorName);
			for (String paramKey : parameters.keySet()) {
				Attribute attribute = actor.getAttribute(paramKey);
				if (attribute instanceof Parameter) {
					Parameter param = (Parameter) attribute;
					param.setExpression(parameters.get(paramKey));
					param.propagateValue();
				}
			}
//			parent.entityList().add(actor);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insertDirector(CompositeEntity actor, String directorName, String entityClass,
			Map<String, String> params) {
		try {
			Attribute attribute = PtolemyUtil._createAttribute(actor, entityClass, directorName);
			if (attribute instanceof Director) {
				for (String paramKey : params.keySet()) {
					Attribute directorAttribute = attribute.getAttribute(paramKey);
					if (directorAttribute instanceof Parameter) {
						Parameter param = (Parameter) directorAttribute;
						param.setExpression(params.get(paramKey));
						param.propagateValue();
					}
				}
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insertPort(ComponentEntity<?> actor, String portName, String entityClass,
			Map<String, String> params) {
		try {
			Port port = PtolemyUtil._createPort(actor, entityClass, portName);
			
			if (port instanceof IOPort) {
				IOPort ioPort = (IOPort) port;
				if(params.containsKey("input") && Boolean.parseBoolean(params.get("input")))
				{
					ioPort.setInput(true);
				}
				if(params.containsKey("output") && Boolean.parseBoolean(params.get("output")))
				{
					ioPort.setOutput(true);
				}
				if(params.containsKey("multi") && Boolean.parseBoolean(params.get("multi")))
				{
					ioPort.setMultiport(true);
				}
				
			}
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insertParameter(ComponentEntity<?> actor, String parameterName, String entityClass,
			Map<String, String> params) {
		try {
			Attribute attribute = PtolemyUtil._createAttribute(actor, entityClass, parameterName);
			if (attribute instanceof Parameter) {
				Parameter param = (Parameter) attribute;
				param.setExpression(params.get("expression"));
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public CompositeEntity getParent(ComponentEntity<?> actor) {
		return (CompositeEntity) actor.getContainer();
	}

	@Override
	public ComponentEntity<?> getChild(CompositeEntity parent, String name) {
		return parent.getEntity(name);
	}

	@Override
	public boolean connect(CompositeEntity currentActor, String from, String to) {
		int size = currentActor.relationList().size();
		try {
			
			ComponentRelation relation = currentActor.newRelation("_R" + (size + 1));
			
			String[] splitFrom = from.split("\\.");
			String actorFrom = splitFrom[0];
			String portFrom = splitFrom[1];
			
			String[] splitTo = to.split("\\.");
			String actorTo = splitTo[0];
			String portTo = splitTo[1];
			
			Port outputport = currentActor.getEntity(actorFrom).getPort(portFrom);
			Port inputPort = currentActor.getEntity(actorTo).getPort(portTo);
			
			inputPort.link(relation);
			outputport.link(relation);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
