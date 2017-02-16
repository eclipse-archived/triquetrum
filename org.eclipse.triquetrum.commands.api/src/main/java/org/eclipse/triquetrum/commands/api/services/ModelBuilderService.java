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
package org.eclipse.triquetrum.commands.api.services;

import java.util.Map;


public interface ModelBuilderService<CompositeActor,Actor> {
	
	public Class<?> getSupportedModelClass();
	
	public CompositeActor createNewModel(String modelName, String folderPath);
	
	public boolean insertActor(CompositeActor parent,String actorName,String actorclass,Map<String,String> parameters);
	
	public boolean insertDirector(CompositeActor actor,String directorName, String entityClass, Map<String, String> params);
	
	public boolean insertPort(Actor actor, String portClass, String entityClass, Map<String, String> params);
	
	public boolean insertParameter(Actor actor, String parameterClass, String entityClass, Map<String, String> params);
	
	public CompositeActor getParent(Actor actor);

	public Actor getChild(CompositeActor parent, String name);

	public boolean connect(CompositeActor currentActor, String from, String to);



}
