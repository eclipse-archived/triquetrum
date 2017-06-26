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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.triquetrum.workflow.model.CompositeEntity;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.TriqPackage;
import org.eclipse.triquetrum.workflow.model.util.Visitor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Composite Entity</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.CompositeEntityImpl#getEntities <em>Entities</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.CompositeEntityImpl#getRelations <em>Relations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompositeEntityImpl extends EntityImpl implements CompositeEntity {
  /**
   * The cached value of the '{@link #getEntities() <em>Entities</em>}' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getEntities()
   * @generated
   * @ordered
   */
  protected EList<Entity> entities;

  /**
   * The cached value of the '{@link #getRelations() <em>Relations</em>}' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getRelations()
   * @generated
   * @ordered
   */
  protected EList<Relation> relations;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected CompositeEntityImpl() {
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
    return TriqPackage.Literals.COMPOSITE_ENTITY;
  }

  @Override
  public void welcome(Visitor visitor, boolean deep) {
    super.welcome(visitor, deep);
    if (deep) {
      for (Relation relation : getRelations()) {
        relation.welcome(visitor, deep);
      }
      for (Entity entity : getEntities()) {
        entity.welcome(visitor, deep);
      }
    }
  }

  @Override
  public NamedObj getChild(String name) {
    NamedObj child = super.getChild(name);
    if (child == null) {
      for (Entity entity : getEntities()) {
        if (name.equals(entity.getName())) {
          child = entity;
          break;
        }
      }
    }
    if (child == null) {
      for (Relation relation : getRelations()) {
        if (name.equals(relation.getName())) {
          child = relation;
          break;
        }
      }
    }
    return child;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public EList<Entity> getEntities() {
    if (entities == null) {
      entities = new EObjectContainmentEList<Entity>(Entity.class, this, TriqPackage.COMPOSITE_ENTITY__ENTITIES);
    }
    return entities;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public EList<Relation> getRelations() {
    if (relations == null) {
      relations = new EObjectContainmentEList<Relation>(Relation.class, this, TriqPackage.COMPOSITE_ENTITY__RELATIONS);
    }
    return relations;
  }

  @Override
  public ptolemy.kernel.CompositeEntity getWrappedObject() {
    return (ptolemy.kernel.CompositeEntity) super.getWrappedObject();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
    case TriqPackage.COMPOSITE_ENTITY__ENTITIES:
      return ((InternalEList<?>) getEntities()).basicRemove(otherEnd, msgs);
    case TriqPackage.COMPOSITE_ENTITY__RELATIONS:
      return ((InternalEList<?>) getRelations()).basicRemove(otherEnd, msgs);
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
    case TriqPackage.COMPOSITE_ENTITY__ENTITIES:
      return getEntities();
    case TriqPackage.COMPOSITE_ENTITY__RELATIONS:
      return getRelations();
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
    case TriqPackage.COMPOSITE_ENTITY__ENTITIES:
      getEntities().clear();
      getEntities().addAll((Collection<? extends Entity>) newValue);
      return;
    case TriqPackage.COMPOSITE_ENTITY__RELATIONS:
      getRelations().clear();
      getRelations().addAll((Collection<? extends Relation>) newValue);
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
    case TriqPackage.COMPOSITE_ENTITY__ENTITIES:
      getEntities().clear();
      return;
    case TriqPackage.COMPOSITE_ENTITY__RELATIONS:
      getRelations().clear();
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
    case TriqPackage.COMPOSITE_ENTITY__ENTITIES:
      return entities != null && !entities.isEmpty();
    case TriqPackage.COMPOSITE_ENTITY__RELATIONS:
      return relations != null && !relations.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} // CompositeEntityImpl
