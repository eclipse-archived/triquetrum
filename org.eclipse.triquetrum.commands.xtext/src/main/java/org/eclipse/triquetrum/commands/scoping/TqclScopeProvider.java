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
/*
 * generated by Xtext 2.10.0
 */
package org.eclipse.triquetrum.commands.scoping;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.triquetrum.commands.tqcl.Category;
import org.eclipse.triquetrum.commands.tqcl.Connect;
import org.eclipse.triquetrum.commands.tqcl.Go;
import org.eclipse.triquetrum.commands.tqcl.Insert;
import org.eclipse.triquetrum.commands.tqcl.TqclPackage;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.FilteringScope;

import com.google.common.base.Predicate;

/**
 * This class contains custom scoping description.
 * 
 * See
 * https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
public class TqclScopeProvider extends AbstractTqclScopeProvider {

	private Predicate<IEObjectDescription> actorInsertPredicate = new Predicate<IEObjectDescription>() {

		@Override
		public boolean apply(IEObjectDescription objectDescription) {
			EObject eObjectOrProxy = objectDescription.getEObjectOrProxy();
			if (eObjectOrProxy instanceof Insert) {
				Insert insert = (Insert) eObjectOrProxy;
				return Category.ACTOR.equals(insert.getCategory());
			}
			return false;
		}
	};

	/* (non-Javadoc)
	 * @see org.eclipse.xtext.scoping.impl.DelegatingScopeProvider#getScope(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference)
	 */
	@Override
	public IScope getScope(EObject context, EReference reference) {
		if (context instanceof Go && reference == TqclPackage.Literals.GO__ACTOR) {
			EObject eContainer = context.eContainer().eContainer();
			Iterable<Insert> candidates = EcoreUtil2.getAllContentsOfType(eContainer, Insert.class);
			IScope scope = Scopes.scopeFor(candidates);
			return new FilteringScope(scope, actorInsertPredicate);
		}
		if (context instanceof Connect && reference == TqclPackage.Literals.CONNECTION_PORT__ACTOR) {
			// Collect a list of candidates by going through the model
			// EcoreUtil2 provides useful functionality to do that
			// For example searching for all elements within the root Object's
			// tree
			EObject rootElement = context.eContainer();
			Iterable<Insert> candidates = EcoreUtil2.getAllContentsOfType(rootElement, Insert.class);
			// Create IEObjectDescriptions and puts them into an IScope instance
			IScope scope = Scopes.scopeFor(candidates);
			return new FilteringScope(scope, actorInsertPredicate);

		}
		return super.getScope(context, reference);
	}

}
