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
package org.eclipse.triquetrum.workflow.editor.views;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecp.edit.spi.swt.util.ECPDialogExecutor;
import org.eclipse.emf.ecp.ui.view.swt.DefaultReferenceService;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.triquetrum.workflow.model.NamedObj;

public class TriqReferenceService extends DefaultReferenceService {

  public TriqReferenceService() {
  }

  @Override
  public void openInNewContext(EObject eObject) {
    final Dialog dialog = new NamedObjDialog(Display.getDefault().getActiveShell(), (NamedObj) eObject);

    new ECPDialogExecutor(dialog) {
      @Override
      public void handleResult(int codeResult) {

      }
    }.execute();
  }

}
