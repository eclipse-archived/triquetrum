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
package org.eclipse.triquetrum.workflow.editor.form.controls;

import javax.inject.Inject;

import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.core.swt.renderer.TextControlSWTRenderer;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.spi.swt.reporting.CustomControlInitFailedReport;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.editsupport.EMFFormsEditSupport;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.triquetrum.workflow.editor.ImageConstants;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramTypeProvider;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;

/**
 * A renderer for a Triq color, combining a text field with a color picker.
 * <p>
 * Derived from the EmailControlRenderer example of EMF Forms MakeItHappen.
 * </p>
 */
public class ColorControlRenderer extends TextControlSWTRenderer {

  /**
   * Default constructor.
   *
   * @param vElement
   *          the view model element to be rendered
   * @param viewContext
   *          the view context
   * @param reportService
   *          The {@link ReportService}
   * @param emfFormsDatabinding
   *          The {@link EMFFormsDatabinding}
   * @param emfFormsLabelProvider
   *          The {@link EMFFormsLabelProvider}
   * @param vtViewTemplateProvider
   *          The {@link VTViewTemplateProvider}
   * @param emfFormsEditSupport
   *          The {@link EMFFormsEditSupport}
   */
  @Inject
  public ColorControlRenderer(VControl vElement, ViewModelContext viewContext, ReportService reportService, EMFFormsDatabinding emfFormsDatabinding,
      EMFFormsLabelProvider emfFormsLabelProvider, VTViewTemplateProvider vtViewTemplateProvider, EMFFormsEditSupport emfFormsEditSupport) {
    super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider, emfFormsEditSupport);
  }

  @Override
  protected Control createSWTControl(Composite parent) {
    final Composite main = new Composite(parent, SWT.NONE);
    GridLayoutFactory.fillDefaults().numColumns(2).applyTo(main);
    GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.BEGINNING).applyTo(main);
    final Control control = super.createSWTControl(main);
    Text textField = null;
    for (Control child : ((Composite) control).getChildren()) {
      if (child instanceof Text) {
        textField = (Text) child;
        break;
      }
    }
    if (textField != null) {
      final Button button = new Button(main, SWT.PUSH);
      Image image = GraphitiUi.getImageService().getImageForId(TriqDiagramTypeProvider.ID, ImageConstants.IMG_COLOR_CHANGE);
      button.setImage(image);
      button.setToolTipText("Change color"); //$NON-NLS-1$
      button.addSelectionListener(new ColorPicker(textField, button));
    } else {
      // if this is not the case, we're in trouble
      // it would seem that the EMF Forms magic did not render a string attribute as a text field???
      getReportService().report(new CustomControlInitFailedReport(TriqEditorPlugin.getID(), ColorControlRenderer.class.getName()));
    }
    return control;
  }

  private final class ColorPicker extends SelectionAdapter {
    private Text textField;
    private Button colorButton;

    protected ColorPicker(Text textField, Button colorButton) {
      this.textField = textField;
      this.colorButton = colorButton;
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
      String newColor = EditorUtils.editColor(EditorUtils.getSelectedDiagram(), textField.getText());
      // the focus trickery is to trigger the databinding of EMF forms
      textField.setFocus();
      textField.setText(newColor);
      colorButton.forceFocus();
    }
  }
}
