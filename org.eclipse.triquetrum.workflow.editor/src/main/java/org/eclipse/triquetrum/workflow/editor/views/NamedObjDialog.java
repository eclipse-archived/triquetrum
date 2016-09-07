/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.views;

import org.eclipse.emf.ecp.ui.view.ECPRendererException;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTView;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTViewRenderer;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.triquetrum.workflow.model.NamedObj;

public class NamedObjDialog extends Dialog {

  private NamedObj namedObj;
  private ECPSWTView view;

  public NamedObjDialog(Shell shell, NamedObj namedObj) {
    super(shell);
    setShellStyle(SWT.RESIZE | SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
    this.namedObj = namedObj;
  }

  @Override
  protected Control createDialogArea(Composite parent) {

    final GridData parentData = new GridData(SWT.FILL, SWT.FILL, true, true);
    parent.setLayout(new GridLayout(1, true));
    parent.setLayoutData(parentData);

    final ScrolledComposite wrapper = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
    wrapper.setExpandHorizontal(true);
    wrapper.setExpandVertical(true);
    final FillLayout wrapperLayout = new FillLayout();
    wrapperLayout.marginHeight = 10;
    wrapperLayout.marginWidth = 10;
    wrapper.setLayout(wrapperLayout);
    wrapper.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    final Composite emfFormsParent = new Composite(wrapper, SWT.NONE);
    wrapper.setContent(emfFormsParent);
    emfFormsParent.setLayout(new GridLayout());

    try {
      view = ECPSWTViewRenderer.INSTANCE.render(emfFormsParent, namedObj);
    } catch (final ECPRendererException e) {
    }

    wrapper.setMinSize(wrapper.computeSize(SWT.DEFAULT, SWT.DEFAULT));

    return parent;
  }

  @Override
  public void create() {
    super.create();
    getShell().setText("Edit Attributes of '" + namedObj.getName() + "'");
  }

  @Override
  public boolean close() {
    view.getSWTControl().forceFocus();
    view.dispose();
    return super.close();
  }
}
