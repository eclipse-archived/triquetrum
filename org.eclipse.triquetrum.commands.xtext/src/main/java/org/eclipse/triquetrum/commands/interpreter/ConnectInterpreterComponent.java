package org.eclipse.triquetrum.commands.interpreter;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.triquetrum.commands.tqcl.CompositeCommand;
import org.eclipse.triquetrum.commands.tqcl.Connect;
import org.eclipse.triquetrum.commands.tqcl.ConnectionPort;
import org.eclipse.triquetrum.commands.tqcl.TqclPackage;

/**
 * Interpreter component for {@link Connect}
 * @author rtotaro
 */
public class ConnectInterpreterComponent implements TqclInterpreterComponent {

	/* (non-Javadoc)
	 * @see org.eclipse.triquetrum.commands.interpreter.TqclInterpreterComponent#interpret(org.eclipse.emf.ecore.EObject, org.eclipse.triquetrum.commands.interpreter.InterpretContext)
	 */
	@Override
	public void interpret(EObject element, InterpretContext context) {
		if (element instanceof Connect) {
			Connect connect = (Connect) element;
			EList<ConnectionPort> from = connect.getFrom();
			EList<ConnectionPort> to = connect.getTo();
			for (ConnectionPort connectionPortFrom : from) {
				for (ConnectionPort connectionPortTo : to) {
					context.getModelBuilderService().connect(context.getCurrentActor(),createPortPath(connectionPortFrom),createPortPath(connectionPortTo));
				}
			}
			
		}

	}

	/**
	 * @param port
	 * @return
	 */
	private String createPortPath(ConnectionPort port) {
		return port.getActor().getName()+"."+port.getPort();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.triquetrum.commands.interpreter.TqclInterpreterComponent#intepretedEClasses()
	 */
	@Override
	public List<EClass> intepretedEClasses() {
		return Arrays.asList(TqclPackage.Literals.CONNECT);
	}

}

