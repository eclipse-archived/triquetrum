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
package org.eclipse.triquetrum.workflow.editor.form.controls;

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.spi.model.VElement;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedException;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedReport;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.swt.core.AbstractSWTRenderer;
import org.eclipse.emfforms.spi.swt.core.di.EMFFormsDIRendererService;
import org.eclipse.triquetrum.workflow.model.TriqPackage;

/**
 * The RendererService which provides the ColorControlRenderer.
 * <p>
 * Derived from the EmailControlRenderer example of EMF Forms MakeItHappen.
 * </p>
 */
public class ColorControlSWTRendererService implements EMFFormsDIRendererService<VControl> {

  private EMFFormsDatabinding databindingService;
  private ReportService reportService;

  /**
   * Called by the initializer to set the EMFFormsDatabinding.
   *
   * @param databindingService
   *          The EMFFormsDatabinding
   */
  protected void setEMFFormsDatabinding(EMFFormsDatabinding databindingService) {
    this.databindingService = databindingService;
  }

  /**
   * Called by the initializer to set the ReportService.
   *
   * @param reportService
   *          The ReportService
   */
  protected void setReportService(ReportService reportService) {
    this.reportService = reportService;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.eclipse.emfforms.spi.swt.core.di.EMFFormsDIRendererService#isApplicable(VElement,ViewModelContext)
   */
  @Override
  public double isApplicable(VElement vElement, ViewModelContext viewModelContext) {
    if (!VControl.class.isInstance(vElement)) {
      return NOT_APPLICABLE;
    }
    final VControl control = (VControl) vElement;
    if (control.getDomainModelReference() == null) {
      return NOT_APPLICABLE;
    }
    IValueProperty valueProperty;
    try {
      valueProperty = databindingService.getValueProperty(control.getDomainModelReference(), viewModelContext.getDomainModel());
    } catch (final DatabindingFailedException ex) {
      reportService.report(new DatabindingFailedReport(ex));
      return NOT_APPLICABLE;
    }
    final EStructuralFeature eStructuralFeature = EStructuralFeature.class.cast(valueProperty.getValueType());
    if (TriqPackage.eINSTANCE.getAnnotation_Color().equals(eStructuralFeature)) {
      return 10;
    }
    return NOT_APPLICABLE;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.eclipse.emfforms.spi.swt.core.di.EMFFormsDIRendererService#getRendererClass()
   */
  @Override
  public Class<? extends AbstractSWTRenderer<VControl>> getRendererClass() {
    return ColorControlRenderer.class;
  }

}
