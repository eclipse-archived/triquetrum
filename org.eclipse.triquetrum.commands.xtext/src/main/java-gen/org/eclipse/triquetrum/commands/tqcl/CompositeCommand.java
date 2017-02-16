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
 * A representation of the model object '<em><b>Composite Command</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.CompositeCommand#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.CompositeCommand#getCommands <em>Commands</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.CompositeCommand#getEnd <em>End</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getCompositeCommand()
 * @model
 * @generated
 */
public interface CompositeCommand extends Command
{
  /**
   * Returns the value of the '<em><b>Start</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Start</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Start</em>' containment reference.
   * @see #setStart(Go)
   * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getCompositeCommand_Start()
   * @model containment="true"
   * @generated
   */
  Go getStart();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.commands.tqcl.CompositeCommand#getStart <em>Start</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Start</em>' containment reference.
   * @see #getStart()
   * @generated
   */
  void setStart(Go value);

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
   * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getCompositeCommand_Commands()
   * @model containment="true"
   * @generated
   */
  EList<Command> getCommands();

  /**
   * Returns the value of the '<em><b>End</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End</em>' containment reference.
   * @see #setEnd(Go)
   * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getCompositeCommand_End()
   * @model containment="true"
   * @generated
   */
  Go getEnd();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.commands.tqcl.CompositeCommand#getEnd <em>End</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End</em>' containment reference.
   * @see #getEnd()
   * @generated
   */
  void setEnd(Go value);

} // CompositeCommand
