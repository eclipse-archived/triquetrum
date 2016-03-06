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

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.TriqPackage;
import org.eclipse.triquetrum.workflow.model.util.PtolemyUtil;

import ptolemy.actor.IOPort;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NamedObj;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Port</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl#isInput <em>Input</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl#isOutput <em>Output</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl#getLinkedRelations <em>Linked Relations</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl#isMultiPort <em>Multi Port</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PortImpl extends NamedObjImpl implements Port {
  /**
   * The default value of the '{@link #isInput() <em>Input</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #isInput()
   * @generated
   * @ordered
   */
  protected static final boolean INPUT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isInput() <em>Input</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #isInput()
   * @generated
   * @ordered
   */
  protected boolean input = INPUT_EDEFAULT;

  /**
   * The default value of the '{@link #isOutput() <em>Output</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #isOutput()
   * @generated
   * @ordered
   */
  protected static final boolean OUTPUT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isOutput() <em>Output</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #isOutput()
   * @generated
   * @ordered
   */
  protected boolean output = OUTPUT_EDEFAULT;

  /**
   * The cached value of the '{@link #getLinkedRelations() <em>Linked Relations</em>}' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #getLinkedRelations()
   * @generated
   * @ordered
   */
  protected EList<Relation> linkedRelations;

  /**
   * The default value of the '{@link #isMultiPort() <em>Multi Port</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #isMultiPort()
   * @generated
   * @ordered
   */
  protected static final boolean MULTI_PORT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isMultiPort() <em>Multi Port</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #isMultiPort()
   * @generated
   * @ordered
   */
  protected boolean multiPort = MULTI_PORT_EDEFAULT;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  protected PortImpl() {
    super();
    // this is the default type from Ptolemy that we'll be using
    setWrappedType("ptolemy.actor.TypedIOPort");
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.PORT;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public boolean isInput() {
    return input;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  public void setInput(boolean newInput) {
    boolean oldInput = input;
    input = newInput;
    if (getWrappedObject() != null) {
      IOPort ptObject = (IOPort) getWrappedObject();
      try {
        ptObject.setInput(newInput);
      } catch (IllegalActionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.PORT__INPUT, oldInput, input));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public boolean isOutput() {
    return output;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public void setOutput(boolean newOutput) {
    boolean oldOutput = output;
    output = newOutput;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.PORT__OUTPUT, oldOutput, output));
  }

  @Override
  public void setProperty(String name, String value, String className) {
    switch (name) {
    case "input":
      setInput(value == null || "true".equals(value));
      break;
    case "output":
      setOutput(value == null || "true".equals(value));
      break;
    default:
      super.setProperty(name, value, className);
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public EList<Relation> getLinkedRelations() {
    if (linkedRelations == null) {
      linkedRelations = new EObjectWithInverseResolvingEList.ManyInverse<Relation>(Relation.class, this, TriqPackage.PORT__LINKED_RELATIONS,
          TriqPackage.RELATION__LINKED_PORTS);
    }
    return linkedRelations;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public boolean isMultiPort() {
    return multiPort;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public void setMultiPort(boolean newMultiPort) {
    boolean oldMultiPort = multiPort;
    multiPort = newMultiPort;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.PORT__MULTI_PORT, oldMultiPort, multiPort));
  }

  /**
   * <!-- begin-user-doc --> Checks whether the port can accept a new connection. For single ports, this means that if there is already a connection, the new
   * request must be refused. For multi ports there is currently no constraint. <!-- end-user-doc -->
   *
   * @generated NOT
   */
  public boolean canAcceptNewConnection() {
    return isMultiPort() || getLinkedRelations().isEmpty();
  }

  @Override
  public Entity getContainer() {
    return (Entity) eContainer();
  }

  @Override
  public void buildWrappedObject() {
    try {
      ptolemy.kernel.Entity<?> container = (ptolemy.kernel.Entity<?>) (getContainer() != null ? getContainer().getWrappedObject() : null);
      wrappedObject = PtolemyUtil._createPort(container, getWrappedType(), getName());
      getWrappedObject().setInput(isInput());
      getWrappedObject().setOutput(isOutput());
      getWrappedObject().setMultiport(isMultiPort());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void initializeFrom(NamedObj ptObject) {
    if (!isDeepComplete()) {
      if (!(ptObject instanceof IOPort)) {
        throw new IllegalArgumentException(ptObject + " should be an IOPort");
      }
      super.initializeFrom(ptObject);
      IOPort port = (IOPort) ptObject;
      setInput(port.isInput());
      setOutput(port.isOutput());
      setMultiPort(port.isMultiport());
      setDeepComplete(true);
    }
  }

  @Override
  public IOPort getWrappedObject() {
    return (IOPort) wrappedObject;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
    case TriqPackage.PORT__LINKED_RELATIONS:
      return ((InternalEList<InternalEObject>) (InternalEList<?>) getLinkedRelations()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
    case TriqPackage.PORT__LINKED_RELATIONS:
      return ((InternalEList<?>) getLinkedRelations()).basicRemove(otherEnd, msgs);
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
    case TriqPackage.PORT__INPUT:
      return isInput();
    case TriqPackage.PORT__OUTPUT:
      return isOutput();
    case TriqPackage.PORT__LINKED_RELATIONS:
      return getLinkedRelations();
    case TriqPackage.PORT__MULTI_PORT:
      return isMultiPort();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case TriqPackage.PORT__INPUT:
      setInput((Boolean) newValue);
      return;
    case TriqPackage.PORT__OUTPUT:
      setOutput((Boolean) newValue);
      return;
    case TriqPackage.PORT__LINKED_RELATIONS:
      getLinkedRelations().clear();
      getLinkedRelations().addAll((Collection<? extends Relation>) newValue);
      return;
    case TriqPackage.PORT__MULTI_PORT:
      setMultiPort((Boolean) newValue);
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
    case TriqPackage.PORT__INPUT:
      setInput(INPUT_EDEFAULT);
      return;
    case TriqPackage.PORT__OUTPUT:
      setOutput(OUTPUT_EDEFAULT);
      return;
    case TriqPackage.PORT__LINKED_RELATIONS:
      getLinkedRelations().clear();
      return;
    case TriqPackage.PORT__MULTI_PORT:
      setMultiPort(MULTI_PORT_EDEFAULT);
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
    case TriqPackage.PORT__INPUT:
      return input != INPUT_EDEFAULT;
    case TriqPackage.PORT__OUTPUT:
      return output != OUTPUT_EDEFAULT;
    case TriqPackage.PORT__LINKED_RELATIONS:
      return linkedRelations != null && !linkedRelations.isEmpty();
    case TriqPackage.PORT__MULTI_PORT:
      return multiPort != MULTI_PORT_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
    switch (operationID) {
    case TriqPackage.PORT___CAN_ACCEPT_NEW_CONNECTION:
      return canAcceptNewConnection();
    }
    return super.eInvoke(operationID, arguments);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy())
      return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (input: ");
    result.append(input);
    result.append(", output: ");
    result.append(output);
    result.append(", multiPort: ");
    result.append(multiPort);
    result.append(')');
    return result.toString();
  }

} // PortImpl
