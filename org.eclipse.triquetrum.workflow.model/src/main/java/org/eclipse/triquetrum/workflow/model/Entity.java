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
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.Entity#getInputPorts <em>Input Ports</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.Entity#getOutputPorts <em>Output Ports</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getEntity()
 * @model
 * @generated
 */
public interface Entity extends NamedObj {
  /**
   * Returns the value of the '<em><b>Input Ports</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.triquetrum.workflow.model.Port}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Input Ports</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Input Ports</em>' containment reference list.
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getEntity_InputPorts()
   * @model containment="true"
   * @generated
   */
  EList<Port> getInputPorts();

  /**
   * Returns the value of the '<em><b>Output Ports</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.triquetrum.workflow.model.Port}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output Ports</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output Ports</em>' containment reference list.
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getEntity_OutputPorts()
   * @model containment="true"
   * @generated
   */
  EList<Port> getOutputPorts();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  EList<Parameter> getParameters();

} // Entity
