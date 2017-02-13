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

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.triquetrum.commands.TqclStandaloneSetup;
import org.eclipse.triquetrum.commands.api.TqCLServices;
import org.eclipse.triquetrum.commands.api.services.ModelBuilderService;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.IConcreteSyntaxDiagnosticProvider.IConcreteSyntaxDiagnostic;
import org.eclipse.xtext.validation.IConcreteSyntaxValidator.IDiagnosticAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;

import ptolemy.util.StringUtilities;

/**
 * The TqCL interpreter, the type of the result is related to the {@link ModelBuilderService} set in the {@link InterpretContext}
 * @author rtotaro
 *
 * @param <CompositeActor> class used to represent the composite actor
 * @param <Actor> class used to represent the simple acto
 */
public class TqclInterpreter<CompositeActor,Actor> {

	private static Map<EClass, TqclInterpreterComponent> components = new HashMap<>(); 
	private final static Logger LOGGER = LoggerFactory.getLogger(TqclInterpreter.class);
	
	static
	{
		addComponent(new TriquetrumScriptInterpreterComponent());
		addComponent(new InsertInterpreterComponent());
		addComponent(new GoInterpreterComponent());
		addComponent(new CompositeCommandInterpreterComponent());
		addComponent(new ConnectInterpreterComponent());
	}
	
	
	
	private static void addComponent(TqclInterpreterComponent component){
		for (EClass eClass : component.intepretedEClasses()) {
			components.put(eClass, component);
		}
	}
	
	/**
	 * Interpreter the TqCL script
	 * 
	 * @param modelName the name of the result model
	 * @param script to interpret
	 * @param uri parent directory of the script
	 * @param modelClass result class, is used to select the right {@link ModelBuilderService}
	 * @return
	 */
	public CompositeActor interpret(String modelName,InputStream script, java.net.URI uri,Class<?> modelClass) {
		try {
			Injector injector = new TqclStandaloneSetup().createInjectorAndDoEMFRegistration();
			IResourceFactory resourceFactory = injector.getInstance(IResourceFactory.class);

			XtextResource resource = (XtextResource) resourceFactory.createResource(URI.createURI(uri.getPath()+"/"+modelName));
			resource.load(script, null);
			IParseResult parseResult = resource.getParseResult();
			
			if(parseResult.hasSyntaxErrors()){
				Iterable<INode> syntaxErrors = parseResult.getSyntaxErrors();
				//TODO: log them
			}
			
			if (resource instanceof LazyLinkingResource) {
				LazyLinkingResource lazyLinkingResource = (LazyLinkingResource) resource;
				lazyLinkingResource.resolveLazyCrossReferences(CancelIndicator.NullImpl);
			}
			else
			{
				EcoreUtil.resolveAll(resource);
			}
			EObject root = parseResult.getRootASTElement();

			resource.getConcreteSyntaxValidator().validateRecursive(root, new IDiagnosticAcceptor()
			{
				@Override
				public void accept(IConcreteSyntaxDiagnostic arg0) {
					// TODO Auto-generated method stub
					
				}
			}, Collections.EMPTY_MAP);
			
			ModelBuilderService<CompositeActor,Actor> modelBuilderService = TqCLServices.getInstance().getModelBuilderService(modelClass);
			CompositeActor model = modelBuilderService.createNewModel(StringUtils.removeEnd(modelName, ".tqcl"), uri.toString());
			
			interpret(root, new InterpretContext<CompositeActor,Actor>(this,model,modelBuilderService));
			
			return model;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	void interpret(EObject element, InterpretContext<CompositeActor,Actor> context)
	{
		components.get(element.eClass()).interpret(element, context);
	}
}
