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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.triquetrum.commands.tqcl.Category;
import org.eclipse.triquetrum.commands.tqcl.Insert;
import org.eclipse.triquetrum.commands.tqcl.Parameter;
import org.eclipse.triquetrum.commands.tqcl.TqclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Insert</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.impl.InsertImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.impl.InsertImpl#getEntityClass <em>Entity Class</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.impl.InsertImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.impl.InsertImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InsertImpl extends CommandImpl implements Insert
{
  /**
   * The default value of the '{@link #getCategory() <em>Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategory()
   * @generated
   * @ordered
   */
  protected static final Category CATEGORY_EDEFAULT = Category.ACTOR;

  /**
   * The cached value of the '{@link #getCategory() <em>Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategory()
   * @generated
   * @ordered
   */
  protected Category category = CATEGORY_EDEFAULT;

  /**
   * The default value of the '{@link #getEntityClass() <em>Entity Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntityClass()
   * @generated
   * @ordered
   */
  protected static final String ENTITY_CLASS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEntityClass() <em>Entity Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntityClass()
   * @generated
   * @ordered
   */
  protected String entityClass = ENTITY_CLASS_EDEFAULT;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameters()
   * @generated
   * @ordered
   */
  protected EList<Parameter> parameters;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected InsertImpl()
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
    return TqclPackage.Literals.INSERT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Category getCategory()
  {
    return category;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCategory(Category newCategory)
  {
    Category oldCategory = category;
    category = newCategory == null ? CATEGORY_EDEFAULT : newCategory;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TqclPackage.INSERT__CATEGORY, oldCategory, category));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEntityClass()
  {
    return entityClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEntityClass(String newEntityClass)
  {
    String oldEntityClass = entityClass;
    entityClass = newEntityClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TqclPackage.INSERT__ENTITY_CLASS, oldEntityClass, entityClass));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TqclPackage.INSERT__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Parameter> getParameters()
  {
    if (parameters == null)
    {
      parameters = new EObjectContainmentEList<Parameter>(Parameter.class, this, TqclPackage.INSERT__PARAMETERS);
    }
    return parameters;
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
      case TqclPackage.INSERT__PARAMETERS:
        return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
      case TqclPackage.INSERT__CATEGORY:
        return getCategory();
      case TqclPackage.INSERT__ENTITY_CLASS:
        return getEntityClass();
      case TqclPackage.INSERT__NAME:
        return getName();
      case TqclPackage.INSERT__PARAMETERS:
        return getParameters();
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
      case TqclPackage.INSERT__CATEGORY:
        setCategory((Category)newValue);
        return;
      case TqclPackage.INSERT__ENTITY_CLASS:
        setEntityClass((String)newValue);
        return;
      case TqclPackage.INSERT__NAME:
        setName((String)newValue);
        return;
      case TqclPackage.INSERT__PARAMETERS:
        getParameters().clear();
        getParameters().addAll((Collection<? extends Parameter>)newValue);
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
      case TqclPackage.INSERT__CATEGORY:
        setCategory(CATEGORY_EDEFAULT);
        return;
      case TqclPackage.INSERT__ENTITY_CLASS:
        setEntityClass(ENTITY_CLASS_EDEFAULT);
        return;
      case TqclPackage.INSERT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case TqclPackage.INSERT__PARAMETERS:
        getParameters().clear();
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
      case TqclPackage.INSERT__CATEGORY:
        return category != CATEGORY_EDEFAULT;
      case TqclPackage.INSERT__ENTITY_CLASS:
        return ENTITY_CLASS_EDEFAULT == null ? entityClass != null : !ENTITY_CLASS_EDEFAULT.equals(entityClass);
      case TqclPackage.INSERT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case TqclPackage.INSERT__PARAMETERS:
        return parameters != null && !parameters.isEmpty();
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
    result.append(" (category: ");
    result.append(category);
    result.append(", entityClass: ");
    result.append(entityClass);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //InsertImpl
