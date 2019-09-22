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
package org.eclipse.triquetrum.workflow.editor.features;

import java.text.MessageFormat;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IMultiDeleteInfo;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.triquetrum.workflow.editor.Messages;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.ui.PlatformUI;

public class TriqDefaultDeleteFeature extends DefaultDeleteFeature {
  
  public static final String DELETE_CONFIRMATION_PREFNAME = "";
  public static final boolean DELETE_CONFIRMATION_DEFVALUE = true;

  public TriqDefaultDeleteFeature(IFeatureProvider fp) {
    super(fp);
  }

  /**
   * Shows a dialog which asks the user to confirm the deletion of one or more elements.
   * (if the preference is not set to not show the confirmation dialog)
   * 
   * @param context
   *            delete context
   * @return <code>true</code> to delete element(s); <code>false</code> to
   *         cancel delete
   */
  @Override
  protected boolean getUserDecision(IDeleteContext context) {
    IPreferenceStore store= TriqEditorPlugin.getDefault().getPreferenceStore();
    if (!store.getBoolean(DELETE_CONFIRMATION_PREFNAME)) {
      return true;
    } else {
      String msg;
      IMultiDeleteInfo multiDeleteInfo = context.getMultiDeleteInfo();
      if (multiDeleteInfo != null) {
        msg = MessageFormat.format(Messages.DeleteFeature_2_xmsg, multiDeleteInfo.getNumber());
      } else {
        String deleteName = getDeleteName(context);
        if (deleteName != null && deleteName.length() > 0) {
          msg = MessageFormat.format(Messages.DeleteFeature_3_xmsg, deleteName);
        } else {
          msg = Messages.DeleteFeature_4_xmsg;
        }
      }
      MessageDialogWithToggle toggleDialog= MessageDialogWithToggle.openYesNoQuestion(
          PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
          Messages.DeleteFeature_5_xfld,
          msg,
          Messages.DeleteFeature_6_xtgl,
          false,
          null,
          null);
  
      boolean deleteIsConfirmed = toggleDialog.getReturnCode() == IDialogConstants.YES_ID;
      if(deleteIsConfirmed) {
        store.setValue(DELETE_CONFIRMATION_PREFNAME, !toggleDialog.getToggleState());
      }
      return deleteIsConfirmed;
    }
  }
}
