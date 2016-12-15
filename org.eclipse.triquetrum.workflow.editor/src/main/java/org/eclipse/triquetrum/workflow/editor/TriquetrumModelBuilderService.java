package org.eclipse.triquetrum.workflow.editor;

import java.net.URI;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.CreateContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.triquetrum.commands.api.services.ModelBuilderService;
import org.eclipse.triquetrum.workflow.editor.features.ConnectionCreateFeature;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementCreateFeature;
import org.eclipse.triquetrum.workflow.editor.wizard.WizardUtils;
import org.eclipse.triquetrum.workflow.model.Actor;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.CompositeEntity;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.TriqFactory;
import org.eclipse.ui.PartInitException;
import org.osgi.framework.Version;
import org.ptolemy.commons.ThreeDigitVersionSpecification;
import org.ptolemy.commons.VersionSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriquetrumModelBuilderService implements ModelBuilderService<CompositeEntity, Entity> {

	private static final Logger LOGGER = LoggerFactory.getLogger(TriquetrumModelBuilderService.class);
	private Diagram diagram;
	private TriqDiagramEditor editor;

	@Override
	public Class<CompositeActor> getSupportedModelClass() {
		return CompositeActor.class;
	}

	@Override
	public CompositeActor createNewModel(String modelName, String folderPath) {

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		URI locationURI = root.getLocationURI();
		if (folderPath.startsWith(locationURI.toString())) {
			String relativePath = StringUtils.removeStart(folderPath, locationURI.toString());
			IResource modelContainer = root.findMember(relativePath);
			IProject project = null;
			if (modelContainer instanceof IProject) {
				project = (IProject) modelContainer;
				modelContainer = null;
			} else {
				project = modelContainer.getProject();
			}
			diagram = WizardUtils.createDiagramAndFile("Triquetrum workflow", modelName, project,
					(IFolder) modelContainer);
			
			CompositeActor model = TriqFactory.eINSTANCE.createCompositeActor();
	        model.setName(diagram.getName());
	        model.buildWrappedObject();
	        diagram.eResource().getContents().add(model);
			try {
				editor = WizardUtils.openDiagramInEditor(diagram);
				TransactionalEditingDomain editingDomain = editor.getDiagramBehavior().getEditingDomain();
				editingDomain.getCommandStack().execute(new AbstractCommand("Create model","Model Builder creting new model") {
					
					{
						isExecutable=true;
						isPrepared = true;
					}
					
					@Override
					public void redo() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void execute() {
						getFeatureProvider().link(diagram, model);
					}
				});
				
				
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// TODO exception to manage
			throw new RuntimeException("file not in workspace");
		}
		return (CompositeActor) Graphiti.getLinkService()
				.getBusinessObjectForLinkedPictogramElement(diagram);
	}

	private VersionSpecification getVersion() {
		Version bundleVersion = TriqEditorPlugin.getDefault().getBundle().getVersion();
		VersionSpecification providerVersion = new ThreeDigitVersionSpecification(bundleVersion.getMajor(),
				bundleVersion.getMinor(), bundleVersion.getMicro(), bundleVersion.getQualifier());
		return providerVersion;
	}

	private EObject createElement(String entityClass) {
		TriqFeatureProvider triqFeatureProvider = getFeatureProvider();

		ICreateFeature[] createFeatures = triqFeatureProvider.getCreateFeatures();
		for (ICreateFeature iCreateFeature : createFeatures) {
			if (iCreateFeature instanceof ModelElementCreateFeature) {
				ModelElementCreateFeature modelElementCreateFeature = (ModelElementCreateFeature) iCreateFeature;

				if (modelElementCreateFeature.getWrappedClass().equals(entityClass)) {
					CreateContext createContext = new CreateContext();
					createContext.setTargetContainer(triqFeatureProvider.getDiagramTypeProvider().getDiagram());
					
					editor.getDiagramBehavior().executeFeature(modelElementCreateFeature,createContext);
					
					EObject businessObjectForLinkedPictogramElement = Graphiti.getLinkService()
							.getBusinessObjectForLinkedPictogramElement(diagram);
					return businessObjectForLinkedPictogramElement;

				}
			}
		}
		
		return null;
	}

	public TriqFeatureProvider getFeatureProvider() {
		TriqFeatureProvider triqFeatureProvider = (TriqFeatureProvider) editor.getDiagramTypeProvider()
				.getFeatureProvider();
		return triqFeatureProvider;
	}

	@Override
	public boolean insertActor(CompositeEntity parent, String actorName, String actorclass,
			Map<String, String> parameters) {

		LOGGER.debug(MessageFormat.format("Creating actor {0} of type {1} with parameters {2}", actorName, actorclass,
				parameters.toString()));
		try {

			TransactionalEditingDomain editingDomain = editor.getDiagramBehavior().getEditingDomain();
			Actor actor = TriqFactory.eINSTANCE.createActor();
			Map<String, org.eclipse.triquetrum.workflow.model.Parameter> modelParameters = new HashMap<>();
			for (org.eclipse.triquetrum.workflow.model.Parameter parameter : actor.getParameters()) {
				modelParameters.put(parameter.getName(), parameter);
			}
			EStructuralFeature eStructuralFeatureName = actor.eClass().getEStructuralFeature("name");
			editingDomain.getCommandStack()
					.execute(new SetCommand(editingDomain, actor, eStructuralFeatureName, actorName));

			for (Entry<String, String> parameter : parameters.entrySet()) {
				String paramName = parameter.getKey();
				org.eclipse.triquetrum.workflow.model.Parameter parameterToSet = modelParameters.get(paramName);
				EStructuralFeature eStructuralFeatureExpression = parameterToSet.eClass()
						.getEStructuralFeature("expression");
				editingDomain.getCommandStack().execute(new SetCommand(editingDomain, parameterToSet,
						eStructuralFeatureExpression, parameter.getValue()));
			}

			
			AddContext context = new AddContext();
			context.setNewObject(actor);
			getFeatureProvider().getAddFeature(context);
			
			
			
			
//			EObject businessObjectForLinkedPictogramElement = parent;
			
			
//			AddContext context = new AddContext();
//			context.setNewObject(TriqFactory);
//			getFeatureProvider().getAddFeature(context)
			
//			EObject businessObjectForLinkedPictogramElement = createElement(actorclass);
//
//			if (businessObjectForLinkedPictogramElement instanceof CompositeActor) {
//				CompositeActor mainActor = (CompositeActor) businessObjectForLinkedPictogramElement;
//				org.eclipse.triquetrum.workflow.model.NamedObj child = mainActor
//						.getChild(((CompositeActor) businessObjectForLinkedPictogramElement).getName());
//				TransactionalEditingDomain editingDomain = editor.getDiagramBehavior().getEditingDomain();
//				if (child instanceof Actor) {
//					Actor actor = (Actor) child;
//					Map<String, org.eclipse.triquetrum.workflow.model.Parameter> modelParameters = new HashMap<>();
//					for (org.eclipse.triquetrum.workflow.model.Parameter parameter : actor.getParameters()) {
//						modelParameters.put(parameter.getName(), parameter);
//					}
//					EStructuralFeature eStructuralFeatureName = actor.eClass().getEStructuralFeature("name");
//					editingDomain.getCommandStack()
//							.execute(new SetCommand(editingDomain, actor, eStructuralFeatureName, actorName));
//
//					for (Entry<String, String> parameter : parameters.entrySet()) {
//						String paramName = parameter.getKey();
//						org.eclipse.triquetrum.workflow.model.Parameter parameterToSet = modelParameters.get(paramName);
//						EStructuralFeature eStructuralFeatureExpression = parameterToSet.eClass()
//								.getEStructuralFeature("expression");
//						editingDomain.getCommandStack().execute(new SetCommand(editingDomain, parameterToSet,
//								eStructuralFeatureExpression, parameter.getValue()));
//					}
//				}
//			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean insertDirector(CompositeEntity actor, String directorName, String entityClass,
			Map<String, String> params) {
		LOGGER.debug(MessageFormat.format("Creating director {0} of type {1} with parameters {2}", directorName,
				entityClass, params.toString()));
		return false;
	}

	@Override
	public boolean insertPort(Entity actor, String portName, String entityClass, Map<String, String> params) {
		LOGGER.debug(MessageFormat.format("Creating port {0} of type {1} with parameters {2}", portName, entityClass,
				params.toString()));
		return false;
	}

	@Override
	public boolean insertParameter(Entity actor, String parameterName, String entityClass, Map<String, String> params) {
		LOGGER.debug(MessageFormat.format("Creating parameter {0} of type {1} with parameters {2}", parameterName,
				entityClass, params.toString()));
		return false;
	}

	@Override
	public CompositeEntity getParent(Entity actor) {
		LOGGER.debug("go to parent");
		return (CompositeEntity) actor.getContainer();
	}

	@Override
	public Entity getChild(CompositeEntity parent,String name) {
		// TODO Auto-generated method stub
		LOGGER.debug("go into child " + name);
		return (Entity) parent.getChild(name);
	}

	@Override
	public boolean connect(CompositeEntity currentActor, String from, String to) {
		LOGGER.debug(MessageFormat.format("Connecting {0} to {1}", from, to));
		
		ConnectionCreateFeature feature = new ConnectionCreateFeature(getFeatureProvider());
		CreateConnectionContext createContext = new CreateConnectionContext();
		EList<EObject> children = currentActor.eContents();
		
//		for (Shape shape : children) {
//			EList<Anchor> anchors = shape.getAnchors();
//			for (Anchor anchor : anchors) {
//				EObject bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(anchor);
//				if (bo instanceof Port) {
//					Port port = (Port) bo;
//					String fullName = port.getFullName();
//					if(fullName.endsWith(from))
//					{
//						createContext.setSourceAnchor(anchor);
//					}
//					if(fullName.endsWith(to))
//					{
//						createContext.setTargetAnchor(anchor);
//					}
//				}
//			}
//		}
		
		editor.getDiagramBehavior().executeFeature(feature, createContext);
		return false;
	}

}
