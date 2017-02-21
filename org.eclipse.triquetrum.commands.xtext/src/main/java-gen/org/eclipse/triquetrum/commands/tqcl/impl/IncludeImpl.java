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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.triquetrum.commands.tqcl.Include;
import org.eclipse.triquetrum.commands.tqcl.TqclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Include</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.impl.IncludeImpl#getFilename <em>Filename</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IncludeImpl extends CommandImpl implements Include
{
  /**
   * The default value of the '{@link #getFilename() <em>Filename</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFilename()
   * @generated
   * @ordered
   */
  protected static final String FILENAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFilename() <em>Filename</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFilename()
   * @generated
   * @ordered
   */
  protected String filename = FILENAME_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IncludeImpl()
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
    return TqclPackage.Literals.INCLUDE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFilename()
  {
    return filename;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFilename(String newFilename)
  {
    String oldFilename = filename;
    filename = newFilename;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TqclPackage.INCLUDE__FILENAME, oldFilename, filename));
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
      case TqclPackage.INCLUDE__FILENAME:
        return getFilename();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case TqclPackage.INCLUDE__FILENAME:
        setFilename((String)newValue);
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
      case TqclPackage.INCLUDE__FILENAME:
        setFilename(FILENAME_EDEFAULT);
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
      case TqclPackage.INCLUDE__FILENAME:
        return FILENAME_EDEFAULT == null ? filename != null : !FILENAME_EDEFAULT.equals(filename);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (filename: ");
    result.append(filename);
    result.append(')');
    return result.toString();
  }

} //IncludeImpl
