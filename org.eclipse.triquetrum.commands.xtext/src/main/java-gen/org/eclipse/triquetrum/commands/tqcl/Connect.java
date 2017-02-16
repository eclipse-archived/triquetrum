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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connect</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.Connect#getFrom <em>From</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.Connect#getTo <em>To</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getConnect()
 * @model
 * @generated
 */
public interface Connect extends Command
{
  /**
   * Returns the value of the '<em><b>From</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.triquetrum.commands.tqcl.ConnectionPort}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>From</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>From</em>' containment reference list.
   * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getConnect_From()
   * @model containment="true"
   * @generated
   */
  EList<ConnectionPort> getFrom();

  /**
   * Returns the value of the '<em><b>To</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.triquetrum.commands.tqcl.ConnectionPort}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>To</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>To</em>' containment reference list.
   * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getConnect_To()
   * @model containment="true"
   * @generated
   */
  EList<ConnectionPort> getTo();

} // Connect
