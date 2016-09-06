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
 *   <li>{@link org.eclipse.triquetrum.workflow.model.Relation#getLinkedRelations <em>Linked Relations</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.Relation#getLinkingRelations <em>Linking Relations</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.Relation#getLinkedPorts <em>Linked Ports</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getRelation()
 * @model
 * @generated
 */
public interface Relation extends NamedObj {
  /**
   * Returns the value of the '<em><b>Linked Relations</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.triquetrum.workflow.model.Relation}.
   * It is bidirectional and its opposite is '{@link org.eclipse.triquetrum.workflow.model.Relation#getLinkingRelations <em>Linking Relations</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Linked Relations</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Linked Relations</em>' reference list.
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getRelation_LinkedRelations()
   * @see org.eclipse.triquetrum.workflow.model.Relation#getLinkingRelations
   * @model opposite="linkingRelations"
   * @generated
   */
  EList<Relation> getLinkedRelations();

  /**
   * Returns the value of the '<em><b>Linking Relations</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.triquetrum.workflow.model.Relation}.
   * It is bidirectional and its opposite is '{@link org.eclipse.triquetrum.workflow.model.Relation#getLinkedRelations <em>Linked Relations</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Linking Relations</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Linking Relations</em>' reference list.
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getRelation_LinkingRelations()
   * @see org.eclipse.triquetrum.workflow.model.Relation#getLinkedRelations
   * @model opposite="linkedRelations"
   * @generated
   */
  EList<Relation> getLinkingRelations();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  void link(Relation relation);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  void unlink(Relation relation);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  boolean isConnected();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  Vertex getVertex();

  /**
   * Returns the value of the '<em><b>Linked Ports</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.triquetrum.workflow.model.Port}.
   * It is bidirectional and its opposite is '{@link org.eclipse.triquetrum.workflow.model.Port#getLinkedRelations <em>Linked Relations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Linked Ports</em>' reference list.
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getRelation_LinkedPorts()
   * @see org.eclipse.triquetrum.workflow.model.Port#getLinkedRelations
   * @model opposite="linkedRelations" transient="true"
   * @generated
   */
  EList<Port> getLinkedPorts();

} // Relation
