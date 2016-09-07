/*******************************************************************************
 * Copyright (c) 2015 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
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

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
