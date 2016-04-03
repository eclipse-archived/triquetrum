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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Obj</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.NamedObj#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.NamedObj#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.NamedObj#getWrappedType <em>Wrapped Type</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.NamedObj#getWrappedObject <em>Wrapped Object</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.NamedObj#isDeepComplete <em>Deep Complete</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.NamedObj#getIconId <em>Icon Id</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getNamedObj()
 * @model
 * @generated
 */
public interface NamedObj extends EObject {
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * The default value is <code>"new"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getNamedObj_Name()
   * @model default="new" required="true"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.triquetrum.workflow.model.Attribute}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attributes</em>' containment reference list.
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getNamedObj_Attributes()
   * @model containment="true"
   * @generated
   */
  EList<Attribute> getAttributes();

  /**
   * Returns the value of the '<em><b>Wrapped Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wrapped Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Wrapped Type</em>' attribute.
   * @see #setWrappedType(String)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getNamedObj_WrappedType()
   * @model
   * @generated
   */
  String getWrappedType();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getWrappedType <em>Wrapped Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Wrapped Type</em>' attribute.
   * @see #getWrappedType()
   * @generated
   */
  void setWrappedType(String value);

  /**
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Container</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  NamedObj getContainer();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  NamedObj topLevel();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model nameRequired="true"
   * @generated
   */
  void setProperty(String name, String value, String className);

  /**
   * Returns the value of the '<em><b>Wrapped Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Wrapped Object</em>' attribute.
   * @see #setWrappedObject(ptolemy.kernel.util.NamedObj)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getNamedObj_WrappedObject()
   * @model dataType="org.eclipse.triquetrum.workflow.model.PtolemyNamedObj" transient="true"
   * @generated
   */
  ptolemy.kernel.util.NamedObj getWrappedObject();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getWrappedObject <em>Wrapped Object</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Wrapped Object</em>' attribute.
   * @see #getWrappedObject()
   * @generated
   */
  void setWrappedObject(ptolemy.kernel.util.NamedObj value);

  /**
   * Returns the value of the '<em><b>Deep Complete</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Deep Complete</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Deep Complete</em>' attribute.
   * @see #setDeepComplete(boolean)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getNamedObj_DeepComplete()
   * @model default="false"
   * @generated
   */
  boolean isDeepComplete();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#isDeepComplete <em>Deep Complete</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Deep Complete</em>' attribute.
   * @see #isDeepComplete()
   * @generated
   */
  void setDeepComplete(boolean value);

  /**
   * Returns the value of the '<em><b>Icon Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Icon Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Icon Id</em>' attribute.
   * @see #setIconId(String)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getNamedObj_IconId()
   * @model
   * @generated
   */
  String getIconId();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getIconId <em>Icon Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Icon Id</em>' attribute.
   * @see #getIconId()
   * @generated
   */
  void setIconId(String value);

  void buildWrappedObject();
  
  /**
   * 
   * @param name
   * @return the contained element with the given simple name or null if not present
   */
  NamedObj getChild(String name);

} // NamedObj
