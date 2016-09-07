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

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Composite Actor</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.triquetrum.workflow.model.CompositeActor#getDirector <em>Director</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getCompositeActor()
 * @model
 * @generated
 */
public interface CompositeActor extends CompositeEntity {
  /**
   * Returns the value of the '<em><b>Director</b></em>' containment reference. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Director</em>' containment reference isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Director</em>' containment reference.
   * @see #setDirector(Director)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getCompositeActor_Director()
   * @model containment="true"
   * @generated
   */
  Director getDirector();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.CompositeActor#getDirector <em>Director</em>}' containment reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Director</em>' containment reference.
   * @see #getDirector()
   * @generated
   */
  void setDirector(Director value);

} // CompositeActor
