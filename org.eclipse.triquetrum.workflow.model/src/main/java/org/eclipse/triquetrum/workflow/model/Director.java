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
package org.eclipse.triquetrum.workflow.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Director</b></em>'. <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getDirector()
 * @model
 * @generated
 */
public interface Director extends Attribute {

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @model kind="operation"
   * @generated
   */
  EList<Parameter> getParameters();
} // Director
