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
package org.eclipse.triquetrum.workflow.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Entity</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.triquetrum.workflow.model.Entity#getPorts <em>Ports</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getEntity()
 * @model
 * @generated
 */
public interface Entity extends NamedObj {
  /**
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Input Ports</em>' containment reference list isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @model kind="operation"
   * @generated
   */
  EList<Port> getInputPorts();

  /**
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output Ports</em>' containment reference list isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @model kind="operation"
   * @generated
   */
  EList<Port> getOutputPorts();

  /**
   * Returns the value of the '<em><b>Ports</b></em>' containment reference list. The list contents are of type
   * {@link org.eclipse.triquetrum.workflow.model.Port}. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ports</em>' containment reference list isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Ports</em>' containment reference list.
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getEntity_Ports()
   * @model containment="true"
   * @generated
   */
  EList<Port> getPorts();

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @model kind="operation"
   * @generated
   */
  EList<Parameter> getParameters();

} // Entity
