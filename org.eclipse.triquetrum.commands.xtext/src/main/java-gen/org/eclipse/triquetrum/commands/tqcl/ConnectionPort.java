/*******************************************************************************
 * Copyright (c)  2017 Totaro Rodolfo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Rodolfo Totaro - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.commands.tqcl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.ConnectionPort#getActor <em>Actor</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.ConnectionPort#getPort <em>Port</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getConnectionPort()
 * @model
 * @generated
 */
public interface ConnectionPort extends EObject
{
  /**
   * Returns the value of the '<em><b>Actor</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Actor</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Actor</em>' reference.
   * @see #setActor(Insert)
   * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getConnectionPort_Actor()
   * @model
   * @generated
   */
  Insert getActor();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.commands.tqcl.ConnectionPort#getActor <em>Actor</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Actor</em>' reference.
   * @see #getActor()
   * @generated
   */
  void setActor(Insert value);

  /**
   * Returns the value of the '<em><b>Port</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Port</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Port</em>' attribute.
   * @see #setPort(String)
   * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getConnectionPort_Port()
   * @model
   * @generated
   */
  String getPort();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.commands.tqcl.ConnectionPort#getPort <em>Port</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Port</em>' attribute.
   * @see #getPort()
   * @generated
   */
  void setPort(String value);

} // ConnectionPort
