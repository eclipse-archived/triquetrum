/*******************************************************************************
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.form.controls;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecp.edit.internal.swt.controls.TableViewerColumnBuilder;
import org.eclipse.emf.ecp.edit.spi.DeleteService;
import org.eclipse.emf.ecp.edit.spi.EMFDeleteServiceImpl;
import org.eclipse.emf.ecp.edit.spi.ReferenceService;
import org.eclipse.emf.ecp.view.model.common.edit.provider.CustomReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.core.swt.AbstractControlSWTRenderer;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.spi.renderer.NoPropertyDescriptorFoundExeption;
import org.eclipse.emf.ecp.view.spi.renderer.NoRendererFoundException;
import org.eclipse.emf.ecp.view.spi.swt.reporting.RenderingFailedReport;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedException;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.emfforms.spi.core.services.label.NoLabelFoundException;
import org.eclipse.emfforms.spi.swt.core.layout.GridDescriptionFactory;
import org.eclipse.emfforms.spi.swt.core.layout.SWTGridCell;
import org.eclipse.emfforms.spi.swt.core.layout.SWTGridDescription;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;

public class AttributesControlSWTRenderer extends AbstractControlSWTRenderer<VControl> {

  /**
   * Default constructor.
   *
   * @param vElement
   *          the view model element to be rendered
   * @param viewContext
   *          the view context
   * @param emfFormsDatabinding
   *          The {@link EMFFormsDatabinding}
   * @param emfFormsLabelProvider
   *          The {@link EMFFormsLabelProvider}
   * @param reportService
   *          The {@link ReportService}
   * @param vtViewTemplateProvider
   *          The {@link VTViewTemplateProvider}
   */
  @Inject
  public AttributesControlSWTRenderer(VControl vElement, ViewModelContext viewContext, ReportService reportService, EMFFormsDatabinding emfFormsDatabinding,
      EMFFormsLabelProvider emfFormsLabelProvider, VTViewTemplateProvider vtViewTemplateProvider) {
    super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider);
    viewModelDBC = new EMFDataBindingContext();
  }

  private Label validationIcon;
  private AdapterFactoryLabelProvider labelProvider;
  private ComposedAdapterFactory composedAdapterFactory;
  private TableViewer tableViewer;
  private final EMFDataBindingContext viewModelDBC;

  /**
   * {@inheritDoc}
   *
   * @see org.eclipse.emfforms.spi.swt.core.AbstractSWTRenderer#getGridDescription(org.eclipse.emfforms.spi.swt.core.layout.SWTGridDescription)
   */
  @Override
  public SWTGridDescription getGridDescription(SWTGridDescription gridDescription) {
    return GridDescriptionFactory.INSTANCE.createSimpleGrid(1, 1, this);
  }

  /**
   * {@inheritDoc}
   *
   * @see org.eclipse.emfforms.spi.swt.core.AbstractSWTRenderer#renderControl(org.eclipse.emfforms.spi.swt.core.layout.SWTGridCell,
   *      org.eclipse.emf.ecp.view.spi.swt.Composite)
   */
  @Override
  protected Control renderControl(SWTGridCell cell, Composite parent) throws NoRendererFoundException, NoPropertyDescriptorFoundExeption {
    if (cell.getRow() != 0 || cell.getColumn() != 0 || cell.getRenderer() != this) {
      throw new IllegalArgumentException("Wrong parameter passed!"); //$NON-NLS-1$
    }

    final Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(1, false));
    composite.setBackgroundMode(SWT.INHERIT_FORCE);

    try {
      createTitleComposite(composite);
    } catch (final DatabindingFailedException ex) {
      getReportService().report(new RenderingFailedReport(ex));
      return createErrorLabel(parent, ex);
    }

    createLabelProvider();

    final Composite controlComposite = createControlComposite(composite);
    try {
      createContent(controlComposite);
    } catch (final DatabindingFailedException ex) {
      getReportService().report(new RenderingFailedReport(ex));
      return createErrorLabel(parent, ex);
    }

    return composite;
  }

  /**
   * Creates the composite which will be the parent for the table.
   *
   * @param composite
   *          the parent composite
   * @return the table composite
   */
  protected Composite createControlComposite(final Composite composite) {
    final Composite controlComposite = new Composite(composite, SWT.NONE);
    GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).hint(1, getTableHeightHint()).applyTo(controlComposite);
    GridLayoutFactory.fillDefaults().numColumns(1).applyTo(controlComposite);
    return controlComposite;
  }

  /**
   * Returns the height for the table that will be created.
   *
   * @return the height hint
   */
  protected int getTableHeightHint() {
    return 300;
  }

  /**
   * Gives access to the tableViewer used to display the attributes.
   *
   * @return the viewer
   */
  protected TableViewer getTableViewer() {
    return tableViewer;
  }

  /**
   * Creates an error label for the given {@link Exception}.
   *
   * @param parent
   *          The parent of the {@link Label}
   * @param ex
   *          The {@link Exception} causing the error
   * @return The error {@link Label}
   */
  protected Control createErrorLabel(Composite parent, final Exception ex) {
    final Label errorLabel = new Label(parent, SWT.NONE);
    errorLabel.setText(ex.getMessage());
    return errorLabel;
  }

  private void createLabelProvider() {
    composedAdapterFactory = new ComposedAdapterFactory(new AdapterFactory[] { new CustomReflectiveItemProviderAdapterFactory(),
        new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE) });
    labelProvider = new AdapterFactoryLabelProvider(composedAdapterFactory);
    labelProvider.setFireLabelUpdateNotifications(true);
  }

  /**
   * {@inheritDoc}
   *
   * @see org.eclipse.emf.ecp.view.spi.core.swt.AbstractControlSWTRenderer#dispose()
   */
  @Override
  protected void dispose() {
    composedAdapterFactory.dispose();
    labelProvider.dispose();
    viewModelDBC.dispose();
    super.dispose();
  }

  private void createTitleComposite(Composite composite) throws NoPropertyDescriptorFoundExeption, DatabindingFailedException {
    final Composite titleComposite = new Composite(composite, SWT.NONE);
    titleComposite.setBackgroundMode(SWT.INHERIT_FORCE);
    GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.BEGINNING).applyTo(titleComposite);
    GridLayoutFactory.fillDefaults().numColumns(3).equalWidth(false).applyTo(titleComposite);

    final Label filler = new Label(titleComposite, SWT.NONE);
    GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.BEGINNING).applyTo(filler);

    // VALIDATION
    // // set the size of the label to the size of the image
    validationIcon = createValidationIcon(titleComposite);
    GridDataFactory.fillDefaults().hint(16, 17).grab(false, false).applyTo(validationIcon);
  }

  private void createContent(Composite composite) throws DatabindingFailedException {
    tableViewer = new TableViewer(composite, SWT.MULTI | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
    tableViewer.getTable().setData(CUSTOM_VARIANT, "org_eclipse_emf_ecp_control_multireference"); //$NON-NLS-1$
    tableViewer.getTable().setHeaderVisible(true);
    tableViewer.getTable().setLinesVisible(true);

    final ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(tableViewer) {
      @Override
      protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
        return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL || event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION
            || event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.CR
            || event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
      }
    };

    TableViewerEditor.create(tableViewer, null, actSupport, ColumnViewerEditor.TABBING_HORIZONTAL | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
        | ColumnViewerEditor.TABBING_VERTICAL | ColumnViewerEditor.KEYBOARD_ACTIVATION);
    ColumnViewerToolTipSupport.enableFor(tableViewer);

    final ECPTableViewerComparator comparator = new ECPTableViewerComparator();
    tableViewer.setComparator(comparator);

    final ObservableListContentProvider cp = new ObservableListContentProvider();

    final EMFFormsLabelProvider labelService = getEMFFormsLabelProvider();

    final TableViewerColumn column = TableViewerColumnBuilder.create().setResizable(false).setMoveable(false).setStyle(SWT.NONE).build(tableViewer);

    final IObservableValue textObservableValue = WidgetProperties.text().observe(column.getColumn());
    final IObservableValue tooltipObservableValue = WidgetProperties.tooltipText().observe(column.getColumn());
    try {
      viewModelDBC.bindValue(textObservableValue, labelService.getDisplayName(getVElement().getDomainModelReference(), getViewModelContext().getDomainModel()));

      viewModelDBC.bindValue(tooltipObservableValue,
          labelService.getDescription(getVElement().getDomainModelReference(), getViewModelContext().getDomainModel()));
    } catch (final NoLabelFoundException e) {
      // FIXME Expectations?
      getReportService().report(new RenderingFailedReport(e));
    }

    column.getColumn().addSelectionListener(getSelectionAdapter(tableViewer, comparator, column.getColumn(), 0));

    tableViewer.setLabelProvider(labelProvider);
    tableViewer.setContentProvider(cp);
    final IObservableList list = getEMFFormsDatabinding().getObservableList(getVElement().getDomainModelReference(), getViewModelContext().getDomainModel());
    tableViewer.setInput(list);

    final TableColumnLayout layout = new TableColumnLayout();
    composite.setLayout(layout);
    layout.setColumnData(column.getColumn(), new ColumnWeightData(1, false));

    tableViewer.addDoubleClickListener(new IDoubleClickListener() {

      @Override
      public void doubleClick(DoubleClickEvent event) {
        final EObject selectedObject = (EObject) IStructuredSelection.class.cast(event.getSelection()).getFirstElement();
        handleDoubleClick(selectedObject);
      }

    });
  }

  private SelectionAdapter getSelectionAdapter(final TableViewer tableViewer, final ECPTableViewerComparator comparator, final TableColumn column,
      final int index) {
    final SelectionAdapter selectionAdapter = new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        comparator.setColumn(index);
        final int dir = comparator.getDirection();
        tableViewer.getTable().setSortDirection(dir);
        tableViewer.getTable().setSortColumn(column);
        tableViewer.refresh();
      }
    };
    return selectionAdapter;
  }

  /**
   * Method for handling a double click.
   *
   * @param selectedObject
   *          the selected {@link EObject}
   */
  protected void handleDoubleClick(EObject selectedObject) {
    final ReferenceService referenceService = getViewModelContext().getService(ReferenceService.class);
    referenceService.openInNewContext(selectedObject);
  }

  /**
   * Method for adding an existing element.
   *
   * @param tableViewer
   *          the {@link TableViewer}
   * @param eObject
   *          The {@link EObject} to add to
   * @param structuralFeature
   *          The corresponding {@link EStructuralFeature}
   */
  protected void handleAddExisting(TableViewer tableViewer, EObject eObject, EStructuralFeature structuralFeature) {
    final ReferenceService referenceService = getViewModelContext().getService(ReferenceService.class);
    referenceService.addExistingModelElements(eObject, (EReference) structuralFeature);
  }

  /**
   * Method for adding a new element.
   *
   * @param tableViewer
   *          the {@link TableViewer}
   * @param eObject
   *          The {@link EObject} to add to
   * @param structuralFeature
   *          The corresponding {@link EStructuralFeature}
   */
  protected void handleAddNew(TableViewer tableViewer, EObject eObject, EStructuralFeature structuralFeature) {
    final ReferenceService referenceService = getViewModelContext().getService(ReferenceService.class);
    referenceService.addNewModelElements(eObject, (EReference) structuralFeature);

  }

  /**
   * Method for deleting elements.
   *
   * @param tableViewer
   *          the {@link TableViewer}
   * @param eObject
   *          The {@link EObject} to delete from
   * @param structuralFeature
   *          The corresponding {@link EStructuralFeature}
   */
  protected void handleDelete(TableViewer tableViewer, EObject eObject, EStructuralFeature structuralFeature) {

    @SuppressWarnings("unchecked")
    final List<Object> deletionList = IStructuredSelection.class.cast(tableViewer.getSelection()).toList();
    final EditingDomain editingDomain = getEditingDomain(eObject);

    /* assured by #isApplicable */
    final EReference reference = EReference.class.cast(structuralFeature);

    if (reference.isContainment()) {
      DeleteService deleteService = getViewModelContext().getService(DeleteService.class);
      if (deleteService == null) {
        /*
         * #getService(Class<?>) will report to the reportservice if it could not be found Use Default
         */
        deleteService = new EMFDeleteServiceImpl();
      }
      deleteService.deleteElements(deletionList);
    } else {
      removeElements(editingDomain, eObject, reference, deletionList);
    }
  }

  private void removeElements(EditingDomain editingDomain, Object source, EStructuralFeature feature, Collection<Object> toRemove) {
    final Command removeCommand = RemoveCommand.create(editingDomain, source, feature, toRemove);
    if (removeCommand.canExecute()) {
      if (editingDomain.getCommandStack() == null) {
        removeCommand.execute();
      } else {
        editingDomain.getCommandStack().execute(removeCommand);
      }
    }
  }

  /**
   * The {@link ViewerComparator} for this table which allows 3 states for sort order: none, up and down.
   *
   * @author Eugen Neufeld
   *
   */
  private class ECPTableViewerComparator extends ViewerComparator {
    private int propertyIndex;
    private static final int NONE = 0;
    private int direction = NONE;

    public ECPTableViewerComparator() {
      propertyIndex = 0;
      direction = NONE;
    }

    public int getDirection() {
      switch (direction) {
      case 0:
        return SWT.NONE;
      case 1:
        return SWT.UP;
      case 2:
        return SWT.DOWN;
      default:
        return SWT.NONE;
      }

    }

    public void setColumn(int column) {
      if (column == propertyIndex) {
        // Same column as last sort; toggle the direction
        direction = (direction + 1) % 3;
      } else {
        // New column; do an ascending sort
        propertyIndex = column;
        direction = 1;
      }
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
      if (direction == 0) {
        return 0;
      }
      int rc = 0;
      final EObject object1 = (EObject) e1;
      final EObject object2 = (EObject) e2;
      final EStructuralFeature feat1 = object1.eClass().getEAllStructuralFeatures().get(propertyIndex);
      final EStructuralFeature feat2 = object2.eClass().getEAllStructuralFeatures().get(propertyIndex);

      final Object value1 = object1.eGet(feat1);
      final Object value2 = object2.eGet(feat2);

      if (value1 == null) {
        rc = 1;
      } else if (value2 == null) {
        rc = -1;
      } else {
        rc = value1.toString().compareTo(value2.toString());
      }
      // If descending order, flip the direction
      if (direction == 2) {
        rc = -rc;
      }
      return rc;
    }
  }
}