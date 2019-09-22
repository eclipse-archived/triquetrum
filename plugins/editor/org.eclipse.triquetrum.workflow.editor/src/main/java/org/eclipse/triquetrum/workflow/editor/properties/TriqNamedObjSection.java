/*******************************************************************************
 * Copyright (c) 2016,2019 iSencia Belgium NV.
 *  
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.properties;

import static org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants.HSPACE;
import static org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants.VSPACE;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class TriqNamedObjSection extends GFPropertySection {
  private Text nameField;
  private Text classField;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
    super.createControls(parent, tabbedPropertySheetPage);

    TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
    Composite composite = factory.createFlatFormComposite(parent);

    nameField = createTextField("Name", composite, null);
    classField = createTextField("Ptolemy Class", composite, nameField);
  }

  public Text createTextField(String label, Composite composite, Control previous) {
    TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
    Text newField = factory.createText(composite, "");
    FormData data = new FormData();
    data.left = new FormAttachment(0, 125);
    data.right = new FormAttachment(100, 0);
    if (previous != null) {
      data.top = new FormAttachment(previous, 0, VSPACE);
    } else {
      data.top = new FormAttachment(0, VSPACE);
    }
    newField.setLayoutData(data);
    newField.setEditable(false);
    newField.setEnabled(false);

    CLabel valueLabel = factory.createCLabel(composite, label + ":");
    data = new FormData();
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(newField, -HSPACE);
    data.top = new FormAttachment(newField, 0, SWT.CENTER);
    valueLabel.setLayoutData(data);

    return newField;
  }

  @Override
  public void refresh() {
    PictogramElement pe = getSelectedPictogramElement();
    if (pe != null) {
      Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
      // the filter assured, that it is a EClass
      if (bo == null)
        return;
      NamedObj no = (NamedObj) bo;
      String name = no.getName();
      nameField.setText(name == null ? "" : name);
      String clazz = no.getWrappedType();
      classField.setText(clazz == null ? "" : clazz);
    }
  }
}
