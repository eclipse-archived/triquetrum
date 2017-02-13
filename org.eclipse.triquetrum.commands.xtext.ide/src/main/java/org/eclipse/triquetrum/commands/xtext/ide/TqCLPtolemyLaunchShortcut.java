package org.eclipse.triquetrum.commands.xtext.ide;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.triquetrum.commands.interpreter.TqclInterpreter;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService.StartMode;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;

import ptolemy.actor.CompositeActor;

/**
 * Launcher to create and start a ptolemy model
 * 
 * @author rtotaro
 *
 */
public class TqCLPtolemyLaunchShortcut implements ILaunchShortcut {

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchShortcut#launch(org.eclipse.jface.viewers.ISelection, java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void launch(ISelection selection, String mode) {
		Object firstElement = ((IStructuredSelection) selection).getFirstElement();
		if (firstElement instanceof IFile) {
			try {
				IFile file = (IFile) firstElement;

				IConsoleManager consoleManager = ConsolePlugin.getDefault().getConsoleManager();
				MessageConsole messageConsole = new MessageConsole(file.getName(), "TqCL", null, false);
				consoleManager.addConsoles(new IConsole[] { messageConsole });

				TqclInterpreter interpreter = new TqclInterpreter();
				CompositeActor model = (CompositeActor) interpreter.interpret(file.getName(), file.getContents(),
						file.getParent().getLocationURI(), CompositeActor.class);

				
				//workflow service could be exposed by a different plugin; not workflow editor
				WorkflowExecutionService executionService = TriqEditorPlugin.getDefault().getWorkflowExecutionService();
				executionService.start(StartMode.RUN, model, null, null, null);

			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchShortcut#launch(org.eclipse.ui.IEditorPart, java.lang.String)
	 */
	@Override
	public void launch(IEditorPart editor, String mode) {
		// TODO Auto-generated method stub
		IEditorInput firstElement = editor.getEditorInput();
		if (firstElement instanceof IFile) {
			IFile file = (IFile) firstElement;

		}
	}

}
