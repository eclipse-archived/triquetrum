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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Triquetrum Script</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.TriquetrumScript#getLibraries <em>Libraries</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.TriquetrumScript#getCommands <em>Commands</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getTriquetrumScript()
 * @model
 * @generated
 */
public interface TriquetrumScript extends EObject
{
  /**
   * Returns the value of the '<em><b>Libraries</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.triquetrum.commands.tqcl.Library}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Libraries</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Libraries</em>' containment reference list.
   * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getTriquetrumScript_Libraries()
   * @model containment="true"
   * @generated
   */
  EList<Library> getLibraries();

  /**
   * Returns the value of the '<em><b>Commands</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.triquetrum.commands.tqcl.Command}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Commands</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Commands</em>' containment reference list.
   * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getTriquetrumScript_Commands()
   * @model containment="true"
   * @generated
   */
  EList<Command> getCommands();

} // TriquetrumScript
