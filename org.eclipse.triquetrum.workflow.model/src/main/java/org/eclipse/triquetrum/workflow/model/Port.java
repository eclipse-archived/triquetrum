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
 * A representation of the model object '<em><b>Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.Port#isInput <em>Input</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.Port#isOutput <em>Output</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.Port#getLinkedRelations <em>Linked Relations</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.Port#isMultiPort <em>Multi Port</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getPort()
 * @model
 * @generated
 */
public interface Port extends NamedObj {
  /**
   * Returns the value of the '<em><b>Input</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Input</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Input</em>' attribute.
   * @see #setInput(boolean)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getPort_Input()
   * @model
   * @generated
   */
  boolean isInput();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.Port#isInput <em>Input</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Input</em>' attribute.
   * @see #isInput()
   * @generated
   */
  void setInput(boolean value);

  /**
   * Returns the value of the '<em><b>Output</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output</em>' attribute.
   * @see #setOutput(boolean)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getPort_Output()
   * @model
   * @generated
   */
  boolean isOutput();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.Port#isOutput <em>Output</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output</em>' attribute.
   * @see #isOutput()
   * @generated
   */
  void setOutput(boolean value);

  /**
   * Returns the value of the '<em><b>Linked Relations</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.triquetrum.workflow.model.Relation}.
   * It is bidirectional and its opposite is '{@link org.eclipse.triquetrum.workflow.model.Relation#getLinkedPorts <em>Linked Ports</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Linked Relations</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Linked Relations</em>' reference list.
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getPort_LinkedRelations()
   * @see org.eclipse.triquetrum.workflow.model.Relation#getLinkedPorts
   * @model opposite="linkedPorts"
   * @generated
   */
  EList<Relation> getLinkedRelations();

  /**
   * Returns the value of the '<em><b>Multi Port</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Multi Port</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Multi Port</em>' attribute.
   * @see #setMultiPort(boolean)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getPort_MultiPort()
   * @model default="false"
   * @generated
   */
  boolean isMultiPort();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.Port#isMultiPort <em>Multi Port</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Multi Port</em>' attribute.
   * @see #isMultiPort()
   * @generated
   */
  void setMultiPort(boolean value);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  boolean canAcceptNewConnection();

} // Port
