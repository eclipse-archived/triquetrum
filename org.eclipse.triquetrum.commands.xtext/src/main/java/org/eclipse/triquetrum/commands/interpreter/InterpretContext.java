package org.eclipse.triquetrum.commands.interpreter;

import org.eclipse.triquetrum.commands.api.services.ModelBuilderService;

public class InterpretContext<CompositeActor,Actor> {

	private ModelBuilderService<CompositeActor,Actor> modelBuilderService;
	
	private TqclInterpreter<CompositeActor,Actor> interpreter;

	private CompositeActor model;
	
	private Actor currentActor;

	public InterpretContext(TqclInterpreter<CompositeActor,Actor> interpreter,CompositeActor model,ModelBuilderService<CompositeActor,Actor> modelBuilderService) {
		super();
		this.interpreter = interpreter;
		this.model = model;
		this.currentActor = (Actor) model;
		this.modelBuilderService = modelBuilderService;
	}
	
	public TqclInterpreter<CompositeActor,Actor> getInterpreter() {
		return interpreter;
	}
	
	public ModelBuilderService<CompositeActor,?> getModelBuilderService() {
		return modelBuilderService;
	}
	public CompositeActor getModel() {
		return model;
	}
	
	public Actor getCurrentActor() {
		return currentActor;
	}
	
	public void setCurrentActor(Actor currentActor) {
		this.currentActor = currentActor;
	}
	
}
