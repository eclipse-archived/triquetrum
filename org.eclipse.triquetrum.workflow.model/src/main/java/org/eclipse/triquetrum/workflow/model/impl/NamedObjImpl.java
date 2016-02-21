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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.triquetrum.workflow.model.Attribute;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Parameter;
import org.eclipse.triquetrum.workflow.model.TriqFactory;
import org.eclipse.triquetrum.workflow.model.TriqPackage;

import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Named Obj</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl#getWrappedType <em>Wrapped Type</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl#getWrappedObject <em>Wrapped Object</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl#isDeepComplete <em>Deep Complete</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl#getIconId <em>Icon Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NamedObjImpl extends MinimalEObjectImpl.Container implements NamedObj {
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getAttributes()
   * @generated
   * @ordered
   */
  protected EList<Attribute> attributes;

  /**
   * The default value of the '{@link #getWrappedType() <em>Wrapped Type</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getWrappedType()
   * @generated
   * @ordered
   */
  protected static final String WRAPPED_TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getWrappedType() <em>Wrapped Type</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getWrappedType()
   * @generated
   * @ordered
   */
  protected String wrappedType = WRAPPED_TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getWrappedObject() <em>Wrapped Object</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getWrappedObject()
   * @generated
   * @ordered
   */
  protected static final ptolemy.kernel.util.NamedObj WRAPPED_OBJECT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getWrappedObject() <em>Wrapped Object</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getWrappedObject()
   * @generated
   * @ordered
   */
  protected ptolemy.kernel.util.NamedObj wrappedObject = WRAPPED_OBJECT_EDEFAULT;

  /**
   * The default value of the '{@link #isDeepComplete() <em>Deep Complete</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDeepComplete()
   * @generated
   * @ordered
   */
  protected static final boolean DEEP_COMPLETE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isDeepComplete() <em>Deep Complete</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDeepComplete()
   * @generated
   * @ordered
   */
  protected boolean deepComplete = DEEP_COMPLETE_EDEFAULT;

  /**
   * The default value of the '{@link #getIconId() <em>Icon Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIconId()
   * @generated
   * @ordered
   */
  protected static final String ICON_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIconId() <em>Icon Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIconId()
   * @generated
   * @ordered
   */
  protected String iconId = ICON_ID_EDEFAULT;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  protected NamedObjImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.NAMED_OBJ;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public String getName() {
    return name;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName) {
    String oldName = name;
    name = newName;
    if(getWrappedObject() != null) {
      ptolemy.kernel.util.NamedObj ptObject = (ptolemy.kernel.util.NamedObj)getWrappedObject();
      try {
        ptObject.setName(newName);
        ptObject.setDisplayName(newName);
      } catch (IllegalActionException | NameDuplicationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.NAMED_OBJ__NAME, oldName, name));
  }

  /**
   * FIXME : this must be optimized with a name-based map lookup or similar secondly, all children of all types of any subclasses should all end up in a child
   * collection in NamedObjImpl
   */
  @Override
  public NamedObj getChild(String name) {
    NamedObj child = null;
    for (Attribute attr : getAttributes()) {
      if (name.equals(attr.getName())) {
        child = attr;
        break;
      }
    }
    return child;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public EList<Attribute> getAttributes() {
    if (attributes == null) {
      attributes = new EObjectContainmentEList<Attribute>(Attribute.class, this, TriqPackage.NAMED_OBJ__ATTRIBUTES);
    }
    return attributes;
  }

  /**
   * <!-- begin-user-doc -->
   *
   * @return the fully qualified class name of the wrapped Ptolemy II type. <!-- end-user-doc -->
   * @generated
   */
  public String getWrappedType() {
    return wrappedType;
  }

  /**
   * <!-- begin-user-doc -->
   *
   * @param newWrappedType
   *          <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public void setWrappedType(String newWrappedType) {
    // only allow changing the wrapped type when no instance is wrapped yet
    if (getWrappedObject() == null) {
      String oldWrappedType = wrappedType;
      wrappedType = newWrappedType;
      if (eNotificationRequired())
        eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.NAMED_OBJ__WRAPPED_TYPE, oldWrappedType, wrappedType));
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public NamedObj getContainer() {
    return (NamedObj) eContainer();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public NamedObj topLevel() {
    NamedObj result = this;
    while (result.getContainer() != null) {
      result = result.getContainer();
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * By default this creates a Parameter (if value is not null) or an Attribute (if value is null) on this NamedObj.
   * Sub-classes may override this method to implement custom behaviour for particular properties.
   * <p>
   * Currently the className is ignored!
   * </p>
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setProperty(String name, String value, String className) {
    if(value==null) {
      Attribute attribute = TriqFactory.eINSTANCE.createAttribute();
      attribute.setName(name);
      this.getAttributes().add(attribute);
    } else {
      Parameter parameter = TriqFactory.eINSTANCE.createParameter();
      // TODO check if we need to add handling of non-string values somehow?
      parameter.setName(name);
      parameter.setExpression(value);
      this.getAttributes().add(parameter);
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public ptolemy.kernel.util.NamedObj getWrappedObject() {
    return wrappedObject;
  }

  /**
   * <!-- begin-user-doc --> This can be used to create a Triq EMF model that wraps an existing Ptolemy II model, visiting all model elements and wrapping them
   * one-by-one.
   *
   * Another approach, mutually exclusive with this setter, is to create a Triq EMF model from scratch. Then the wrappedType can be set with the class name of
   * the underlying Ptolemy II element, which will be used to create the Ptolemy II element when needed. <!-- end-user-doc -->
   * @generated
   */
  public void setWrappedObject(ptolemy.kernel.util.NamedObj newWrappedObject) {
    ptolemy.kernel.util.NamedObj oldWrappedObject = wrappedObject;
    wrappedObject = newWrappedObject;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.NAMED_OBJ__WRAPPED_OBJECT, oldWrappedObject, wrappedObject));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isDeepComplete() {
    return deepComplete;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDeepComplete(boolean newDeepComplete) {
    boolean oldDeepComplete = deepComplete;
    deepComplete = newDeepComplete;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.NAMED_OBJ__DEEP_COMPLETE, oldDeepComplete, deepComplete));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIconId() {
    return iconId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIconId(String newIconId) {
    String oldIconId = iconId;
    iconId = newIconId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.NAMED_OBJ__ICON_ID, oldIconId, iconId));
  }

  // This is where we can hook in a ptolemy object construction, including its container
  // It seems EMF does not build its model (e.g. while parsing the xmi file) as would be easiest for us.
  // I.e. the root model element (a CompositeActor) never gets its eBasicSetContainer(null) called (or too late?),
  // and doesn't get the chance to set its wrappedObject at the right moment.
  // So we need to hack something here to get that resolved
  @Override
  protected void eBasicSetContainer(InternalEObject newContainer) {
    super.eBasicSetContainer(newContainer);
    if (newContainer != null) {
      ptolemy.kernel.util.NamedObj container = (ptolemy.kernel.util.NamedObj) getContainer().getWrappedObject();
      if (container == null) {
        // it seems our container doesn't know its wrapped object yet
        ((NamedObjImpl)newContainer).buildWrappedObject();
      }
      buildWrappedObject();
    }
  }

  @Override
  public void buildWrappedObject() {
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case TriqPackage.NAMED_OBJ__ATTRIBUTES:
        return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case TriqPackage.NAMED_OBJ__NAME:
        return getName();
      case TriqPackage.NAMED_OBJ__ATTRIBUTES:
        return getAttributes();
      case TriqPackage.NAMED_OBJ__WRAPPED_TYPE:
        return getWrappedType();
      case TriqPackage.NAMED_OBJ__WRAPPED_OBJECT:
        return getWrappedObject();
      case TriqPackage.NAMED_OBJ__DEEP_COMPLETE:
        return isDeepComplete();
      case TriqPackage.NAMED_OBJ__ICON_ID:
        return getIconId();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case TriqPackage.NAMED_OBJ__NAME:
        setName((String)newValue);
        return;
      case TriqPackage.NAMED_OBJ__ATTRIBUTES:
        getAttributes().clear();
        getAttributes().addAll((Collection<? extends Attribute>)newValue);
        return;
      case TriqPackage.NAMED_OBJ__WRAPPED_TYPE:
        setWrappedType((String)newValue);
        return;
      case TriqPackage.NAMED_OBJ__WRAPPED_OBJECT:
        setWrappedObject((ptolemy.kernel.util.NamedObj)newValue);
        return;
      case TriqPackage.NAMED_OBJ__DEEP_COMPLETE:
        setDeepComplete((Boolean)newValue);
        return;
      case TriqPackage.NAMED_OBJ__ICON_ID:
        setIconId((String)newValue);
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
      case TriqPackage.NAMED_OBJ__NAME:
        setName(NAME_EDEFAULT);
        return;
      case TriqPackage.NAMED_OBJ__ATTRIBUTES:
        getAttributes().clear();
        return;
      case TriqPackage.NAMED_OBJ__WRAPPED_TYPE:
        setWrappedType(WRAPPED_TYPE_EDEFAULT);
        return;
      case TriqPackage.NAMED_OBJ__WRAPPED_OBJECT:
        setWrappedObject(WRAPPED_OBJECT_EDEFAULT);
        return;
      case TriqPackage.NAMED_OBJ__DEEP_COMPLETE:
        setDeepComplete(DEEP_COMPLETE_EDEFAULT);
        return;
      case TriqPackage.NAMED_OBJ__ICON_ID:
        setIconId(ICON_ID_EDEFAULT);
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
      case TriqPackage.NAMED_OBJ__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case TriqPackage.NAMED_OBJ__ATTRIBUTES:
        return attributes != null && !attributes.isEmpty();
      case TriqPackage.NAMED_OBJ__WRAPPED_TYPE:
        return WRAPPED_TYPE_EDEFAULT == null ? wrappedType != null : !WRAPPED_TYPE_EDEFAULT.equals(wrappedType);
      case TriqPackage.NAMED_OBJ__WRAPPED_OBJECT:
        return WRAPPED_OBJECT_EDEFAULT == null ? wrappedObject != null : !WRAPPED_OBJECT_EDEFAULT.equals(wrappedObject);
      case TriqPackage.NAMED_OBJ__DEEP_COMPLETE:
        return deepComplete != DEEP_COMPLETE_EDEFAULT;
      case TriqPackage.NAMED_OBJ__ICON_ID:
        return ICON_ID_EDEFAULT == null ? iconId != null : !ICON_ID_EDEFAULT.equals(iconId);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
    switch (operationID) {
      case TriqPackage.NAMED_OBJ___GET_CONTAINER:
        return getContainer();
      case TriqPackage.NAMED_OBJ___TOP_LEVEL:
        return topLevel();
      case TriqPackage.NAMED_OBJ___SET_PROPERTY__STRING_STRING_STRING:
        setProperty((String)arguments.get(0), (String)arguments.get(1), (String)arguments.get(2));
        return null;
    }
    return super.eInvoke(operationID, arguments);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(", wrappedType: ");
    result.append(wrappedType);
    result.append(", wrappedObject: ");
    result.append(wrappedObject);
    result.append(", deepComplete: ");
    result.append(deepComplete);
    result.append(", iconId: ");
    result.append(iconId);
    result.append(')');
    return result.toString();
  }

} // NamedObjImpl
