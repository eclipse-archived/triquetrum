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
package org.eclipse.triquetrum.workflow.editor;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$
  public static String CreateDiagramWizard_DiagramNameField;
  public static String CreateDiagramWizard_DiagramTypeField;
  public static String CreateDiagramWizard_ErrorOccuredTitle;
  public static String CreateDiagramWizard_NoProjectFoundError;
  public static String CreateDiagramWizard_NoProjectFoundErrorTitle;
  public static String CreateDiagramWizard_OpeningEditorError;
  public static String CreateDiagramWizard_WizardTitle;
  public static String DiagramNameWizardPage_PageDescription;
  public static String DiagramNameWizardPage_PageTitle;
  public static String DiagramNameWizardPage_Message;
  public static String DiagramNameWizardPage_Label;
  public static String DiagramsNode_DiagramNodeTitle;
  public static String DiagramTypeWizardPage_DiagramTypeField;
  public static String DiagramTypeWizardPage_PageDescription;
  public static String DiagramTypeWizardPage_PageTitle;
  public static String FileService_ErrorMessageCause;
  public static String FileService_ErrorMessageStart;
  public static String FileService_ErrorMessageUri;
  public static String DeleteFeature_1_xfld;
  public static String DeleteFeature_2_xmsg;
  public static String DeleteFeature_3_xmsg;
  public static String DeleteFeature_4_xmsg;
  public static String DeleteFeature_5_xfld;
  public static String DeleteFeature_6_xtgl;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
