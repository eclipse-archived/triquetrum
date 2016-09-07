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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.triquetrum.workflow.model.Linkable;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.TriqPackage;
import org.eclipse.triquetrum.workflow.model.Vertex;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Vertex</b></em>'. <!-- end-user-doc -->
 *
 * @generated
 */
public class VertexImpl extends LocationImpl implements Vertex {
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected VertexImpl() {
    super();
  }

  @Override
  public Relation getContainer() {
    return (Relation) super.getContainer();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.VERTEX;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  @Override
  public void link(Relation relation) {
    getContainer().link(relation);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  @Override
  public void unlink(Relation relation) {
    getContainer().unlink(relation);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
    if (baseClass == Linkable.class) {
      switch (baseOperationID) {
      case TriqPackage.LINKABLE___LINK__RELATION:
        return TriqPackage.VERTEX___LINK__RELATION;
      case TriqPackage.LINKABLE___UNLINK__RELATION:
        return TriqPackage.VERTEX___UNLINK__RELATION;
      default:
        return -1;
      }
    }
    return super.eDerivedOperationID(baseOperationID, baseClass);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
    switch (operationID) {
    case TriqPackage.VERTEX___LINK__RELATION:
      link((Relation) arguments.get(0));
      return null;
    case TriqPackage.VERTEX___UNLINK__RELATION:
      unlink((Relation) arguments.get(0));
      return null;
    }
    return super.eInvoke(operationID, arguments);
  }

} // VertexImpl
