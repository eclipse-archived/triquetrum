/*******************************************************************************
 *  Copyright (c) 2015 iSencia Belgium NV.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *  Contributors:
 *     Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.views;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecp.edit.spi.ReferenceService;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.triquetrum.workflow.model.NamedObj;

import org.eclipse.emf.ecp.edit.spi.swt.util.ECPDialogExecutor;

public class TriqReferenceService implements ReferenceService {

  public TriqReferenceService() {
  }

  @Override
  public void instantiate(ViewModelContext context) {
  }

  @Override
  public void dispose() {
  }

  @Override
  public int getPriority() {
    return 0;
  }

  @Override
  public void addNewModelElements(EObject eObject, EReference eReference) {
  }

  @Override
  public void addExistingModelElements(EObject eObject, EReference eReference) {
  }

  @Override
  public void openInNewContext(EObject eObject) {
    final Dialog dialog = new NamedObjDialog(Display.getDefault().getActiveShell(), (NamedObj)eObject);

    new ECPDialogExecutor(dialog) {
      @Override
      public void handleResult(int codeResult) {

      }
    }.execute();
  }

}
