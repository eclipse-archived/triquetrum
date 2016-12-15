/*******************************************************************************
 * Copyright (c)  2017 Totaro Rodolfo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Rodolfo Totaro - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.commands.interpreter;

import org.eclipse.triquetrum.commands.api.services.ModelBuilderService;


/**
 * Object that contains the result of interpretation
 * 
 * @author rtotaro
 *
 * @param <CompositeActor> class used to represent the composite actor
 * @param <Actor> class used to represent the simple actor
 */
public class InterpretContext<CompositeActor,Actor> {

	private ModelBuilderService<CompositeActor,Actor> modelBuilderService;
	
	private TqclInterpreter<CompositeActor,Actor> interpreter;

	private CompositeActor model;
	
	private Actor currentActor;

	/**
	 * @param interpreter is used by interpreter component 
	 * @param model root of the model to interpret
	 * @param modelBuilderService is used to build the model 
	 */
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
	
	/**
	 * Is used to set the current level in the actor hierarchy
	 * Could be also a simple actor for adding ports or parameters
	 * @param currentActor
	 */
	public void setCurrentActor(Actor currentActor) {
		this.currentActor = currentActor;
	}
	
}
