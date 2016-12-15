/*******************************************************************************
 * Copyright (c)  2016 Totaro Rodolfo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Rodolfo Totaro - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.commands.ui.editor;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

public class TriquetrumCommandEditor extends XtextEditor {

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		getSourceViewer().getTextWidget().addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(((e.stateMask & SWT.CTRL) == SWT.CTRL) && (e.keyCode == SWT.CR))
                {
					IXtextDocument document = getDocument();
					
					
                }
				
			}
		});
		
	}
	
}
