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
package org.eclipse.triquetrum.workflow.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.triquetrum.workflow.model.Parameter;
import org.eclipse.triquetrum.workflow.model.TriqPackage;

import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.Settable;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Parameter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.impl.ParameterImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ParameterImpl extends AttributeImpl implements Parameter {
  /**
   * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getExpression()
   * @generated
   * @ordered
   */
  protected static final String EXPRESSION_EDEFAULT = null;
  /**
   * The cached value of the '{@link #getExpression() <em>Expression</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getExpression()
   * @generated
   * @ordered
   */
  protected String expression = EXPRESSION_EDEFAULT;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  protected ParameterImpl() {
    super();
    // this is the default type from Ptolemy that we'll be using
    setWrappedType("ptolemy.data.expr.StringParameter");
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.PARAMETER;
  }

  @Override
  public void buildWrappedObject() {
    super.buildWrappedObject();
    try {
      if (!(wrappedObject instanceof Settable)) {
        throw new Exception("Property cannot be assigned a value: " + getName() + " (instance " + wrappedObject + " is not of " + wrappedType + ")");
      } else if (getExpression() != null) {
        Settable settable = (Settable) wrappedObject;
        settable.setExpression(getExpression());
        // Propagate. This has the side effect of marking the object overridden.
        wrappedObject.propagateValue();
      }
    } catch (IllegalActionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void applyWrappedObject() {
    if (!isDeepComplete()) {
      super.applyWrappedObject();
      setExpression(getWrappedObject().getExpression());
    }
  }

  @Override
  public ptolemy.data.expr.Parameter getWrappedObject() {
    return (ptolemy.data.expr.Parameter) super.getWrappedObject();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public String getExpression() {
    return expression;
  }

  /**
   * <!-- begin-user-doc --> TODO refactor this : via a listener? or subclassing all generated impls to allow overriding/extending in there? <!-- end-user-doc
   * -->
   *
   * @generated NOT
   */
  public void setExpression(String newExpression) {
    String oldExpression = expression;
    expression = newExpression;
    if (getWrappedObject() != null) {
      ptolemy.data.expr.Parameter ptObject = (ptolemy.data.expr.Parameter) getWrappedObject();
      ptObject.setExpression(newExpression);
      try {
        ptObject.validate();
      } catch (IllegalActionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.PARAMETER__EXPRESSION, oldExpression, expression));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case TriqPackage.PARAMETER__EXPRESSION:
        return getExpression();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case TriqPackage.PARAMETER__EXPRESSION:
        setExpression((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case TriqPackage.PARAMETER__EXPRESSION:
        setExpression(EXPRESSION_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case TriqPackage.PARAMETER__EXPRESSION:
        return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (expression: ");
    result.append(expression);
    result.append(')');
    return result.toString();
  }

} // ParameterImpl
