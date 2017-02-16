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
package org.eclipse.triquetrum.commands.scoping;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.triquetrum.commands.tqcl.Insert;
import org.eclipse.triquetrum.commands.validation.TqCLUtils;
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;

public class TqclQualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider {

	@Override
	public QualifiedName apply(EObject eObject) {
//		if (eObject instanceof Insert) {
//			return getFullyQualifiedName(eObject);
//		}
		return super.apply(eObject);
	}

	@Override
	public QualifiedName getFullyQualifiedName(EObject eObject) {
//		if (eObject instanceof Insert) {
//			Insert insert = (Insert) eObject;
//			return QualifiedName.create(TqCLUtils.cleanEntityName(insert.getName()));
//		}
		return super.getFullyQualifiedName(eObject);
	}

}
