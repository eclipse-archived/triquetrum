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
