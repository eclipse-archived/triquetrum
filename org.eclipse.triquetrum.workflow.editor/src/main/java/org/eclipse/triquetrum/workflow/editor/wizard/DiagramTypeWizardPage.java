/*******************************************************************************
 * Copyright (c) 2015-2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.wizard;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.eclipse.graphiti.dt.IDiagramType;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.triquetrum.workflow.editor.Messages;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;

public class DiagramTypeWizardPage extends AbstractWizardPage implements ITextProvider {

	private static final String PAGE_DESC = Messages.DiagramTypeWizardPage_PageDescription;
	private static final String PAGE_TITLE = Messages.DiagramTypeWizardPage_PageTitle;

	private static final String DEFAULT_TYPE = "tutorial"; //$NON-NLS-1$
	private static final String SELECTED_TYPE = "selectedtype"; //$NON-NLS-1$

	Combo comboBox;

	public DiagramTypeWizardPage(String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	protected DiagramTypeWizardPage(String pageName) {
		super(pageName);
		setTitle(PAGE_TITLE);
		setDescription(PAGE_DESC);
	}

	@Override
	protected void createWizardContents(Composite parent) {
		// project specification group
		Composite projectGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		projectGroup.setLayout(layout);
		projectGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// new project label
		Label projectLabel = new Label(projectGroup, SWT.NONE);
		projectLabel.setFont(parent.getFont());
		projectLabel.setText(Messages.DiagramTypeWizardPage_DiagramTypeField);

		// new project name entry field
		comboBox = new Combo(projectGroup, SWT.READ_ONLY | SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 250;
		comboBox.setLayoutData(data);
		comboBox.setFont(parent.getFont());
		comboBox.setVisibleItemCount(12);
		comboBox.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				IDialogSettings dialogSettings = TriqEditorPlugin.getDefault().getDialogSettings();
				dialogSettings.put(SELECTED_TYPE, comboBox.getText());
			}
		});

		// set the contents of the Combo-widget
		comboBox.setItems(getAllAvailableDiagramTypes());
		if (getInitialValue() != null) {
			comboBox.setText(getInitialValue());
		}
	}

	protected String[] getAllAvailableDiagramTypes() {
		Vector<String> diagramIds = new Vector<String>();
		for (IDiagramType diagramType : GraphitiUi.getExtensionManager().getDiagramTypes()) {
			diagramIds.add(diagramType.getId());
		}

		return diagramIds.toArray(new String[] {});
	}

	protected String getInitialValue() {
		// Get last choice
		IDialogSettings dialogSettings = TriqEditorPlugin.getDefault().getDialogSettings();
		String selType = dialogSettings.get(SELECTED_TYPE);
		List<String> asList = Arrays.asList(comboBox.getItems());
		if (asList.contains(selType)) {
			return selType;
		} else if (asList.contains(DEFAULT_TYPE)) {
			return DEFAULT_TYPE;
		}
		return null;
	}

	public String getText() {
		return comboBox.getText();
	}
}
