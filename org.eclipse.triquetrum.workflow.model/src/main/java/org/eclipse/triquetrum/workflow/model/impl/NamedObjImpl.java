/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import org.eclipse.triquetrum.workflow.model.util.Visitor;

import ptolemy.kernel.util.Settable;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Named Obj</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl#getAttributes <em>Attributes</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl#getWrappedType <em>Wrapped Type</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl#getWrappedObject <em>Wrapped Object</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl#isDeepComplete <em>Deep Complete</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl#getIconId <em>Icon Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NamedObjImpl extends MinimalEObjectImpl.Container implements NamedObj {
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = "new";

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getAttributes()
   * @generated
   * @ordered
   */
  protected EList<Attribute> attributes;

  /**
   * The default value of the '{@link #getWrappedType() <em>Wrapped Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getWrappedType()
   * @generated
   * @ordered
   */
  protected static final String WRAPPED_TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getWrappedType() <em>Wrapped Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getWrappedType()
   * @generated
   * @ordered
   */
  protected String wrappedType = WRAPPED_TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getWrappedObject() <em>Wrapped Object</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getWrappedObject()
   * @generated
   * @ordered
   */
  protected static final ptolemy.kernel.util.NamedObj WRAPPED_OBJECT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getWrappedObject() <em>Wrapped Object</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getWrappedObject()
   * @generated
   * @ordered
   */
  protected ptolemy.kernel.util.NamedObj wrappedObject = WRAPPED_OBJECT_EDEFAULT;

  /**
   * The default value of the '{@link #isDeepComplete() <em>Deep Complete</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #isDeepComplete()
   * @generated
   * @ordered
   */
  protected static final boolean DEEP_COMPLETE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isDeepComplete() <em>Deep Complete</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #isDeepComplete()
   * @generated
   * @ordered
   */
  protected boolean deepComplete = DEEP_COMPLETE_EDEFAULT;

  /**
   * The default value of the '{@link #getIconId() <em>Icon Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getIconId()
   * @generated
   * @ordered
   */
  protected static final String ICON_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIconId() <em>Icon Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getIconId()
   * @generated
   * @ordered
   */
  protected String iconId = ICON_ID_EDEFAULT;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected NamedObjImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.NAMED_OBJ;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public void setName(String newName) {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.NAMED_OBJ__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc --> FIXME : this must be optimized with a name-based map lookup or similar secondly, all children of all types of any subclasses should
   * all end up in a child collection in NamedObjImpl <!-- end-user-doc -->
   *
   * @generated NOT
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
   *
   * @generated NOT
   */
  @Override
  public void welcome(Visitor visitor, boolean deep) {
    visitor.visit(this);
    if (deep) {
      for (Attribute attr : getAttributes()) {
        attr.welcome(visitor, true);
      }
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
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
  @Override
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
    // if (getWrappedObject() == null) {
    String oldWrappedType = wrappedType;
    wrappedType = newWrappedType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.NAMED_OBJ__WRAPPED_TYPE, oldWrappedType, wrappedType));
    // }
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
   * <!-- begin-user-doc --><!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public NamedObj getLowestCommonContainer(NamedObj other) {
    if (other == null) {
      return null;
    }
    if (this == other || this == other.getContainer()) {
      return this;
    }
    if (other == this.getContainer()) {
      return other;
    }
    NamedObj lcc = null;
    List<NamedObj> thisOnesContainers = getContainmentList();
    List<NamedObj> theOthersContainers = ((NamedObjImpl) other).getContainmentList();
    for (NamedObj ctr : thisOnesContainers) {
      if (theOthersContainers.contains(ctr)) {
        lcc = ctr;
        break;
      }
    }
    return lcc;
  }

  /**
   *
   * @return the ordered list of containment hierarchy nodes of this NamedObj, starting with itself and adding its container and that one's container etc,
   *         ending with the toplevel as the last element in the list.
   */
  protected List<NamedObj> getContainmentList() {
    List<NamedObj> thisOnesContainers = new ArrayList<>();
    NamedObj container = this;
    while (container != null) {
      thisOnesContainers.add(container);
      container = container.getContainer();
    }
    return thisOnesContainers;
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
   * <!-- begin-user-doc --> By default this creates a Parameter (if value is not null) or an Attribute (if value is null) on this NamedObj. Sub-classes may
   * override this method to implement custom behaviour for particular properties.
   * <p>
   * Currently the className is ignored!
   * </p>
   * <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public void setProperty(String name, String value, String className) {
    if (value == null) {
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
  public void applyWrappedObject() {
    if (!isDeepComplete()) {
      setName(wrappedObject.getName());
      setWrappedType(wrappedObject.getClassName());
      for (ptolemy.data.expr.Parameter parameter : wrappedObject.attributeList(ptolemy.data.expr.Parameter.class)) {
        // for the moment, only add FULLy user-visible parameters in the editor model
        if (Settable.FULL.equals(parameter.getVisibility())) {
          Parameter newParam = TriqFactory.eINSTANCE.createParameter();
          newParam.setName(parameter.getName());
          newParam.setWrappedType(parameter.getClassName());
          newParam.setExpression(parameter.getExpression());
          getAttributes().add(newParam);
        }
      }
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void buildWrappedObject() {
    // TODO: implement this method
    // Ensure that you remove @generated or mark it @generated NOT
    throw new UnsupportedOperationException();
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
   *
   * @generated NOT
   */
  @Override
  public final void setWrappedObject(ptolemy.kernel.util.NamedObj newWrappedObject) {
    ptolemy.kernel.util.NamedObj oldWrappedObject = wrappedObject;
    wrappedObject = newWrappedObject;
    if (wrappedObject != null) {
      setWrappedType(wrappedObject.getClass().getName());
      // need to postpone this until the container is set...
      // initializeFrom(wrappedObject);
    }
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.NAMED_OBJ__WRAPPED_OBJECT, oldWrappedObject, wrappedObject));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public boolean isDeepComplete() {
    return deepComplete;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void setDeepComplete(boolean newDeepComplete) {
    boolean oldDeepComplete = deepComplete;
    deepComplete = newDeepComplete;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.NAMED_OBJ__DEEP_COMPLETE, oldDeepComplete, deepComplete));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String getIconId() {
    return iconId;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void setIconId(String newIconId) {
    String oldIconId = iconId;
    iconId = newIconId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.NAMED_OBJ__ICON_ID, oldIconId, iconId));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public String getFullName() {
    if (getWrappedObject() != null) {
      return getWrappedObject().getFullName();
    } else {
      StringBuilder strB = new StringBuilder(getName());
      NamedObj ctr = getContainer();
      while (ctr != null) {
        strB.insert(0, ctr.getName() + ".");
        ctr = ctr.getContainer();
      }
      return strB.toString();
    }

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
    case TriqPackage.NAMED_OBJ__ATTRIBUTES:
      return ((InternalEList<?>) getAttributes()).basicRemove(otherEnd, msgs);
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
   * 
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case TriqPackage.NAMED_OBJ__NAME:
      setName((String) newValue);
      return;
    case TriqPackage.NAMED_OBJ__ATTRIBUTES:
      getAttributes().clear();
      getAttributes().addAll((Collection<? extends Attribute>) newValue);
      return;
    case TriqPackage.NAMED_OBJ__WRAPPED_TYPE:
      setWrappedType((String) newValue);
      return;
    case TriqPackage.NAMED_OBJ__WRAPPED_OBJECT:
      setWrappedObject((ptolemy.kernel.util.NamedObj) newValue);
      return;
    case TriqPackage.NAMED_OBJ__DEEP_COMPLETE:
      setDeepComplete((Boolean) newValue);
      return;
    case TriqPackage.NAMED_OBJ__ICON_ID:
      setIconId((String) newValue);
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
   * 
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
   * 
   * @generated
   */
  @Override
  public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
    switch (operationID) {
    case TriqPackage.NAMED_OBJ___GET_CONTAINER:
      return getContainer();
    case TriqPackage.NAMED_OBJ___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ:
      return getLowestCommonContainer((NamedObj) arguments.get(0));
    case TriqPackage.NAMED_OBJ___TOP_LEVEL:
      return topLevel();
    case TriqPackage.NAMED_OBJ___SET_PROPERTY__STRING_STRING_STRING:
      setProperty((String) arguments.get(0), (String) arguments.get(1), (String) arguments.get(2));
      return null;
    case TriqPackage.NAMED_OBJ___APPLY_WRAPPED_OBJECT:
      applyWrappedObject();
      return null;
    case TriqPackage.NAMED_OBJ___BUILD_WRAPPED_OBJECT:
      buildWrappedObject();
      return null;
    case TriqPackage.NAMED_OBJ___GET_FULL_NAME:
      return getFullName();
    case TriqPackage.NAMED_OBJ___GET_CHILD__STRING:
      return getChild((String) arguments.get(0));
    case TriqPackage.NAMED_OBJ___WELCOME__VISITOR_BOOLEAN:
      welcome((Visitor) arguments.get(0), (Boolean) arguments.get(1));
      return null;
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
