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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.Director;
import org.eclipse.triquetrum.workflow.model.TriqPackage;
import org.eclipse.triquetrum.workflow.model.util.Visitor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Composite Actor</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.CompositeActorImpl#getDirector <em>Director</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompositeActorImpl extends CompositeEntityImpl implements CompositeActor {
  /**
   * The cached value of the '{@link #getDirector() <em>Director</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getDirector()
   * @generated
   * @ordered
   */
  protected Director director;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  protected CompositeActorImpl() {
    super();
    setWrappedType("ptolemy.actor.TypedCompositeActor");
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.COMPOSITE_ACTOR;
  }

  @Override
  public void welcome(Visitor visitor, boolean deep) {
    super.welcome(visitor, deep);
    if (deep) {
      if (getDirector() != null) {
        getDirector().welcome(visitor, deep);
      }
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Director getDirector() {
    return director;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public NotificationChain basicSetDirector(Director newDirector, NotificationChain msgs) {
    Director oldDirector = director;
    director = newDirector;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TriqPackage.COMPOSITE_ACTOR__DIRECTOR, oldDirector, newDirector);
      if (msgs == null)
        msgs = notification;
      else
        msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  @Override
  public void setDirector(Director newDirector) {
    if (newDirector != director) {
      NotificationChain msgs = null;
      if (director != null) {
        msgs = ((InternalEObject) director).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TriqPackage.COMPOSITE_ACTOR__DIRECTOR, null, msgs);
        // TODO check if this is the right approach,
        // or if this should somehow be chained in the msgs and notifications of this level?
        getAttributes().remove(director);
      }
      if (newDirector != null) {
        getAttributes().add(newDirector);
        msgs = ((InternalEObject) newDirector).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TriqPackage.COMPOSITE_ACTOR__DIRECTOR, null, msgs);
      }
      msgs = basicSetDirector(newDirector, msgs);
      if (msgs != null)
        msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.COMPOSITE_ACTOR__DIRECTOR, newDirector, newDirector));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
    case TriqPackage.COMPOSITE_ACTOR__DIRECTOR:
      return basicSetDirector(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case TriqPackage.COMPOSITE_ACTOR__DIRECTOR:
      return getDirector();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case TriqPackage.COMPOSITE_ACTOR__DIRECTOR:
      setDirector((Director) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
    case TriqPackage.COMPOSITE_ACTOR__DIRECTOR:
      setDirector((Director) null);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case TriqPackage.COMPOSITE_ACTOR__DIRECTOR:
      return director != null;
    }
    return super.eIsSet(featureID);
  }

} // CompositeActorImpl
