/**
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 */
package org.eclipse.triquetrum.workflow.model.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.triquetrum.workflow.model.TriqPackage;
import org.eclipse.triquetrum.workflow.model.Vertex;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vertex</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class VertexImpl extends LocationImpl implements Vertex {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VertexImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.VERTEX;
  }

} //VertexImpl
