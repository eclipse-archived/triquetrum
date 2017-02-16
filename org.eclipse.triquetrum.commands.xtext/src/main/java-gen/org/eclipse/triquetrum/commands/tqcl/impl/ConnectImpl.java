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
package org.eclipse.triquetrum.commands.tqcl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.triquetrum.commands.tqcl.Connect;
import org.eclipse.triquetrum.commands.tqcl.ConnectionPort;
import org.eclipse.triquetrum.commands.tqcl.TqclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connect</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.impl.ConnectImpl#getFrom <em>From</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.impl.ConnectImpl#getTo <em>To</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectImpl extends CommandImpl implements Connect
{
  /**
   * The cached value of the '{@link #getFrom() <em>From</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFrom()
   * @generated
   * @ordered
   */
  protected EList<ConnectionPort> from;

  /**
   * The cached value of the '{@link #getTo() <em>To</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTo()
   * @generated
   * @ordered
   */
  protected EList<ConnectionPort> to;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConnectImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return TqclPackage.Literals.CONNECT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConnectionPort> getFrom()
  {
    if (from == null)
    {
      from = new EObjectContainmentEList<ConnectionPort>(ConnectionPort.class, this, TqclPackage.CONNECT__FROM);
    }
    return from;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConnectionPort> getTo()
  {
    if (to == null)
    {
      to = new EObjectContainmentEList<ConnectionPort>(ConnectionPort.class, this, TqclPackage.CONNECT__TO);
    }
    return to;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case TqclPackage.CONNECT__FROM:
        return ((InternalEList<?>)getFrom()).basicRemove(otherEnd, msgs);
      case TqclPackage.CONNECT__TO:
        return ((InternalEList<?>)getTo()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case TqclPackage.CONNECT__FROM:
        return getFrom();
      case TqclPackage.CONNECT__TO:
        return getTo();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case TqclPackage.CONNECT__FROM:
        getFrom().clear();
        getFrom().addAll((Collection<? extends ConnectionPort>)newValue);
        return;
      case TqclPackage.CONNECT__TO:
        getTo().clear();
        getTo().addAll((Collection<? extends ConnectionPort>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case TqclPackage.CONNECT__FROM:
        getFrom().clear();
        return;
      case TqclPackage.CONNECT__TO:
        getTo().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case TqclPackage.CONNECT__FROM:
        return from != null && !from.isEmpty();
      case TqclPackage.CONNECT__TO:
        return to != null && !to.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ConnectImpl
