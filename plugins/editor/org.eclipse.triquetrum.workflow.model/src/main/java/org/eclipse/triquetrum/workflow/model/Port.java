/*******************************************************************************
 * Copyright (c) 2015,2019 iSencia Belgium NV.
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
package org.eclipse.triquetrum.workflow.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Port</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.triquetrum.workflow.model.Port#isInput <em>Input</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.Port#isOutput <em>Output</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.Port#isMultiPort <em>Multi Port</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.Port#getDirection <em>Direction</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.Port#getLinkedRelations <em>Linked Relations</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.Port#getInsideLinkedRelations <em>Inside Linked Relations</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.Port#getOutsideLinkedRelations <em>Outside Linked Relations</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getPort()
 * @model
 * @generated
 */
public interface Port extends Linkable {
  /**
   * Returns the value of the '<em><b>Input</b></em>' attribute. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Input</em>' attribute isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Input</em>' attribute.
   * @see #setInput(boolean)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getPort_Input()
   * @model
   * @generated
   */
  boolean isInput();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.Port#isInput <em>Input</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Input</em>' attribute.
   * @see #isInput()
   * @generated
   */
  void setInput(boolean value);

  /**
   * Returns the value of the '<em><b>Output</b></em>' attribute. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output</em>' attribute isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Output</em>' attribute.
   * @see #setOutput(boolean)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getPort_Output()
   * @model
   * @generated
   */
  boolean isOutput();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.Port#isOutput <em>Output</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @param value
   *          the new value of the '<em>Output</em>' attribute.
   * @see #isOutput()
   * @generated
   */
  void setOutput(boolean value);

  /**
   * Returns the value of the '<em><b>Inside Linked Relations</b></em>' reference list. The list contents are of type
   * {@link org.eclipse.triquetrum.workflow.model.Relation}. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Inside Linked Relations</em>' reference list isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Inside Linked Relations</em>' reference list.
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getPort_InsideLinkedRelations()
   * @model changeable="false" derived="true"
   * @generated
   */
  EList<Relation> getInsideLinkedRelations();

  /**
   * Returns the value of the '<em><b>Outside Linked Relations</b></em>' reference list. The list contents are of type
   * {@link org.eclipse.triquetrum.workflow.model.Relation}. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Outside Linked Relations</em>' reference list isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Outside Linked Relations</em>' reference list.
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getPort_OutsideLinkedRelations()
   * @model changeable="false" derived="true"
   * @generated
   */
  EList<Relation> getOutsideLinkedRelations();

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @model
   * @generated
   */
  boolean canAcceptNewInsideRelation();

  /**
   * Returns the value of the '<em><b>Multi Port</b></em>' attribute. The default value is <code>"false"</code>. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Multi Port</em>' attribute isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Multi Port</em>' attribute.
   * @see #setMultiPort(boolean)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getPort_MultiPort()
   * @model default="false"
   * @generated
   */
  boolean isMultiPort();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.Port#isMultiPort <em>Multi Port</em>}' attribute. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Multi Port</em>' attribute.
   * @see #isMultiPort()
   * @generated
   */
  void setMultiPort(boolean value);

  /**
   * Returns the value of the '<em><b>Direction</b></em>' attribute. The default value is <code>"DEFAULT"</code>. The literals are from the enumeration
   * {@link org.eclipse.triquetrum.workflow.model.Direction}. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Direction</em>' attribute isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Direction</em>' attribute.
   * @see org.eclipse.triquetrum.workflow.model.Direction
   * @see #isSetDirection()
   * @see #unsetDirection()
   * @see #setDirection(Direction)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getPort_Direction()
   * @model default="DEFAULT" unsettable="true"
   * @generated
   */
  Direction getDirection();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.Port#getDirection <em>Direction</em>}' attribute. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Direction</em>' attribute.
   * @see org.eclipse.triquetrum.workflow.model.Direction
   * @see #isSetDirection()
   * @see #unsetDirection()
   * @see #getDirection()
   * @generated
   */
  void setDirection(Direction value);

  /**
   * Unsets the value of the '{@link org.eclipse.triquetrum.workflow.model.Port#getDirection <em>Direction</em>}' attribute. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see #isSetDirection()
   * @see #getDirection()
   * @see #setDirection(Direction)
   * @generated
   */
  void unsetDirection();

  /**
   * Returns whether the value of the '{@link org.eclipse.triquetrum.workflow.model.Port#getDirection <em>Direction</em>}' attribute is set. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @return whether the value of the '<em>Direction</em>' attribute is set.
   * @see #unsetDirection()
   * @see #getDirection()
   * @see #setDirection(Direction)
   * @generated
   */
  boolean isSetDirection();

  /**
   * Returns the value of the '<em><b>Linked Relations</b></em>' reference list. The list contents are of type
   * {@link org.eclipse.triquetrum.workflow.model.Relation}. It is bidirectional and its opposite is '
   * {@link org.eclipse.triquetrum.workflow.model.Relation#getLinkedPorts <em>Linked Ports</em>}'. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Linked Relations</em>' reference list isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Linked Relations</em>' reference list.
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getPort_LinkedRelations()
   * @see org.eclipse.triquetrum.workflow.model.Relation#getLinkedPorts
   * @model opposite="linkedPorts"
   * @generated
   */
  EList<Relation> getLinkedRelations();

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @model
   * @generated
   */
  boolean canAcceptNewOutsideRelation();

} // Port
