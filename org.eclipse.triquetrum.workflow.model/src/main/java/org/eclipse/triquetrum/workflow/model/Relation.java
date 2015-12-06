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
 * A representation of the model object '<em><b>Relation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.Relation#getLinkedPorts <em>Linked Ports</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getRelation()
 * @model
 * @generated
 */
public interface Relation extends NamedObj {
  /**
   * Returns the value of the '<em><b>Linked Ports</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.triquetrum.workflow.model.Port}.
   * It is bidirectional and its opposite is '{@link org.eclipse.triquetrum.workflow.model.Port#getLinkedRelations <em>Linked Relations</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Linked Ports</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Linked Ports</em>' reference list.
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getRelation_LinkedPorts()
   * @see org.eclipse.triquetrum.workflow.model.Port#getLinkedRelations
   * @model opposite="linkedRelations"
   * @generated
   */
  EList<Port> getLinkedPorts();

} // Relation
