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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.triquetrum.workflow.model.CompositeEntity;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.TriqPackage;

import ptolemy.kernel.ComponentRelation;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.impl.RelationImpl#getLinkedPorts <em>Linked Ports</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.impl.RelationImpl#getLinkedRelations <em>Linked Relations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelationImpl extends NamedObjImpl implements Relation {
  /**
   * The cached value of the '{@link #getLinkedPorts() <em>Linked Ports</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLinkedPorts()
   * @generated
   * @ordered
   */
  protected EList<Port> linkedPorts;

  /**
   * The cached value of the '{@link #getLinkedRelations() <em>Linked Relations</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLinkedRelations()
   * @generated
   * @ordered
   */
  protected EList<Relation> linkedRelations;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RelationImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.RELATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Port> getLinkedPorts() {
    if (linkedPorts == null) {
      linkedPorts = new EObjectWithInverseResolvingEList.ManyInverse<Port>(Port.class, this, TriqPackage.RELATION__LINKED_PORTS, TriqPackage.PORT__LINKED_RELATIONS);
    }
    return linkedPorts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Relation> getLinkedRelations() {
    if (linkedRelations == null) {
      linkedRelations = new EObjectResolvingEList<Relation>(Relation.class, this, TriqPackage.RELATION__LINKED_RELATIONS);
    }
    return linkedRelations;
  }

  @Override
  public CompositeEntity getContainer() {
    return (CompositeEntity) eContainer();
  }
  
  // This is where we can hook in a ptolemy object construction, including its container
  @Override
  protected void eBasicSetContainer(InternalEObject newContainer) {
    super.eBasicSetContainer(newContainer);
    ptolemy.kernel.CompositeEntity container = (ptolemy.kernel.CompositeEntity) (getContainer() != null ? getContainer().getWrappedObject() : null);
    if (wrappedObject == null) {
        buildWrappedObject();
    } else {
      try {
        ((ComponentRelation)wrappedObject).setContainer(container);
      } catch (IllegalActionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (NameDuplicationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  @Override
  public void buildWrappedObject() {
    try {
      ptolemy.kernel.CompositeEntity container = (ptolemy.kernel.CompositeEntity) (getContainer() != null ? getContainer().getWrappedObject() : null);
      wrappedObject = container.newRelation(getName());
      for(Port p : getLinkedPorts()) {
        ((ptolemy.kernel.Port)p.getWrappedObject()).link((ptolemy.kernel.Relation) wrappedObject);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case TriqPackage.RELATION__LINKED_PORTS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getLinkedPorts()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case TriqPackage.RELATION__LINKED_PORTS:
        return ((InternalEList<?>)getLinkedPorts()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case TriqPackage.RELATION__LINKED_PORTS:
        return getLinkedPorts();
      case TriqPackage.RELATION__LINKED_RELATIONS:
        return getLinkedRelations();
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
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case TriqPackage.RELATION__LINKED_PORTS:
        getLinkedPorts().clear();
        getLinkedPorts().addAll((Collection<? extends Port>)newValue);
        return;
      case TriqPackage.RELATION__LINKED_RELATIONS:
        getLinkedRelations().clear();
        getLinkedRelations().addAll((Collection<? extends Relation>)newValue);
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
  public void eUnset(int featureID) {
    switch (featureID) {
      case TriqPackage.RELATION__LINKED_PORTS:
        getLinkedPorts().clear();
        return;
      case TriqPackage.RELATION__LINKED_RELATIONS:
        getLinkedRelations().clear();
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
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case TriqPackage.RELATION__LINKED_PORTS:
        return linkedPorts != null && !linkedPorts.isEmpty();
      case TriqPackage.RELATION__LINKED_RELATIONS:
        return linkedRelations != null && !linkedRelations.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //RelationImpl
