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
 * A representation of the model object '<em><b>Insert</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.Insert#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.Insert#getEntityClass <em>Entity Class</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.Insert#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.Insert#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getInsert()
 * @model
 * @generated
 */
public interface Insert extends Command
{
  /**
   * Returns the value of the '<em><b>Category</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.triquetrum.commands.tqcl.Category}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Category</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Category</em>' attribute.
   * @see org.eclipse.triquetrum.commands.tqcl.Category
   * @see #setCategory(Category)
   * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getInsert_Category()
   * @model
   * @generated
   */
  Category getCategory();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.commands.tqcl.Insert#getCategory <em>Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Category</em>' attribute.
   * @see org.eclipse.triquetrum.commands.tqcl.Category
   * @see #getCategory()
   * @generated
   */
  void setCategory(Category value);

  /**
   * Returns the value of the '<em><b>Entity Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity Class</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entity Class</em>' attribute.
   * @see #setEntityClass(String)
   * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getInsert_EntityClass()
   * @model
   * @generated
   */
  String getEntityClass();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.commands.tqcl.Insert#getEntityClass <em>Entity Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Entity Class</em>' attribute.
   * @see #getEntityClass()
   * @generated
   */
  void setEntityClass(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getInsert_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.commands.tqcl.Insert#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.triquetrum.commands.tqcl.Parameter}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters</em>' containment reference list.
   * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#getInsert_Parameters()
   * @model containment="true"
   * @generated
   */
  EList<Parameter> getParameters();

} // Insert
