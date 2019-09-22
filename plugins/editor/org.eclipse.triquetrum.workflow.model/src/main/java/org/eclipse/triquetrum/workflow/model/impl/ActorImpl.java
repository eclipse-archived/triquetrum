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
package org.eclipse.triquetrum.workflow.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.triquetrum.workflow.model.Actor;
import org.eclipse.triquetrum.workflow.model.TriqPackage;

import ptolemy.actor.AtomicActor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Actor</b></em>'. <!-- end-user-doc -->
 *
 * @generated
 */
public class ActorImpl extends EntityImpl implements Actor {
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected ActorImpl() {
    super();
  }

  @Override
  protected void eBasicSetContainer(InternalEObject newContainer) {
    super.eBasicSetContainer(newContainer);
    if (newContainer == null && wrappedObject != null) {
      try {
        getWrappedObject().setContainer(null);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        // should never happen when setting a null container
        e.printStackTrace();
      }
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.ACTOR;
  }

  @Override
  public AtomicActor<?> getWrappedObject() {
    return (AtomicActor<?>) super.getWrappedObject();
  }

} // ActorImpl
