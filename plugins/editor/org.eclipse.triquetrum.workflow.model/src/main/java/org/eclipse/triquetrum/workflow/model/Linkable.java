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

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Linkable</b></em>'. <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getLinkable()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Linkable extends NamedObj {
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @model
   * @generated
   */
  void link(Relation relation);

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @model
   * @generated
   */
  void unlink(Relation relation);

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @model kind="operation"
   * @generated
   */
  boolean isPotentialStart();

  /**
   * <!-- begin-user-doc --> This method checks whether a connection's src and target are of the right type and on the right model level to be a matching pair
   * to be connected. <!-- end-user-doc -->
   * 
   * @model
   * @generated
   */
  boolean isPotentialEnd(Linkable start);

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @model
   * @generated
   */
  void buildWrappedLinks();

} // Linkable
