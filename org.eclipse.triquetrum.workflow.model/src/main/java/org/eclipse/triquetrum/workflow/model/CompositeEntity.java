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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Composite Entity</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.triquetrum.workflow.model.CompositeEntity#getEntities <em>Entities</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.CompositeEntity#getRelations <em>Relations</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getCompositeEntity()
 * @model
 * @generated
 */
public interface CompositeEntity extends Entity {
  /**
   * Returns the value of the '<em><b>Entities</b></em>' containment reference list. The list contents are of type
   * {@link org.eclipse.triquetrum.workflow.model.Entity}. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entities</em>' containment reference list isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Entities</em>' containment reference list.
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getCompositeEntity_Entities()
   * @model containment="true"
   * @generated
   */
  EList<Entity> getEntities();

  /**
   * Returns the value of the '<em><b>Relations</b></em>' containment reference list. The list contents are of type
   * {@link org.eclipse.triquetrum.workflow.model.Relation}. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Relations</em>' containment reference isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Relations</em>' containment reference list.
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getCompositeEntity_Relations()
   * @model containment="true"
   * @generated
   */
  EList<Relation> getRelations();

} // CompositeEntity
