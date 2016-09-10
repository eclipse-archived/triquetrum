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
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.triquetrum.workflow.model.Attribute;
import org.eclipse.triquetrum.workflow.model.CompositeEntity;
import org.eclipse.triquetrum.workflow.model.Linkable;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.TriqPackage;
import org.eclipse.triquetrum.workflow.model.Vertex;
import ptolemy.kernel.ComponentRelation;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Relation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.impl.RelationImpl#getLinkedRelations <em>Linked Relations</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.impl.RelationImpl#getLinkingRelations <em>Linking Relations</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.impl.RelationImpl#getLinkedPorts <em>Linked Ports</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelationImpl extends NamedObjImpl implements Relation {
  /**
   * The cached value of the '{@link #getLinkedRelations() <em>Linked Relations</em>}' reference list.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getLinkedRelations()
   * @generated
   * @ordered
   */
  protected EList<Relation> linkedRelations;

  /**
   * The cached value of the '{@link #getLinkingRelations() <em>Linking Relations</em>}' reference list.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getLinkingRelations()
   * @generated
   * @ordered
   */
  protected EList<Relation> linkingRelations;

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
   * reference to the optional vertex in the collection of attributes
   * TODO investigate if EMF does not provide a way to model this as well,
   * i.o. now using manual coding.
   */
  protected Vertex vertex;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  protected RelationImpl() {
    super();
  }

  @Override
  protected void eBasicSetContainer(InternalEObject newContainer) {
    super.eBasicSetContainer(newContainer);
    if(newContainer==null && wrappedObject!=null) {
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
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.RELATION;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public EList<Relation> getLinkedRelations() {
    if (linkedRelations == null) {
      linkedRelations = new EObjectWithInverseResolvingEList.ManyInverse<Relation>(Relation.class, this, TriqPackage.RELATION__LINKED_RELATIONS, TriqPackage.RELATION__LINKING_RELATIONS);
    }
    return linkedRelations;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public EList<Relation> getLinkingRelations() {
    if (linkingRelations == null) {
      linkingRelations = new EObjectWithInverseResolvingEList.ManyInverse<Relation>(Relation.class, this, TriqPackage.RELATION__LINKING_RELATIONS, TriqPackage.RELATION__LINKED_RELATIONS);
    }
    return linkingRelations;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  public void link(Relation relation) {
      if (this != relation && !getLinkedRelations().contains(relation)) {
        getLinkedRelations().add(relation);
      }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  public void unlink(Relation relation) {
      if (relation != this) {
        getLinkedRelations().remove(relation);
        getLinkingRelations().remove(relation);
      }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isPotentialStart() {
    // TODO: implement this method
    // Ensure that you remove @generated or mark it @generated NOT
    throw new UnsupportedOperationException();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isPotentialEnd(Linkable start) {
    // TODO: implement this method
    // Ensure that you remove @generated or mark it @generated NOT
    throw new UnsupportedOperationException();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  public boolean isConnected() {

    return !getLinkedPorts().isEmpty() || !getLinkedRelations().isEmpty() || !getLinkingRelations().isEmpty()
        || !getWrappedObject().attributeList(ptolemy.moml.Vertex.class).isEmpty();

  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  public Vertex getVertex() {
    return vertex;
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

  @Override
  public CompositeEntity getContainer() {
    return (CompositeEntity) super.getContainer();
  }

  // TODO evaluate if it's worth the trouble to go for a full-blown EMF adapter + adapterfactory setup
  /**
   * This is where we react to changes in linked ports and relations, to keep the wrapped Ptolemy model up-to-date.
   */
  @Override
  public void eNotify(Notification notification) {
    super.eNotify(notification);
    switch (notification.getFeatureID(Relation.class)) {
    case TriqPackage.RELATION__LINKED_RELATIONS:
      switch (notification.getEventType()) {
      case Notification.ADD:
        Relation addedRelation = (Relation) notification.getNewValue();
        try {
          if (!getWrappedObject().linkedObjectsList().contains(addedRelation.getWrappedObject())) {
            getWrappedObject().link((ptolemy.kernel.Relation) addedRelation.getWrappedObject());
          }
        } catch (IllegalActionException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        break;
      case Notification.ADD_MANY:
        List<Relation> addedRelations = (List<Relation>) notification.getNewValue();
        for (Relation r : addedRelations) {
          try {
            if (!getWrappedObject().linkedObjectsList().contains(r.getWrappedObject())) {
              getWrappedObject().link((ptolemy.kernel.Relation) r.getWrappedObject());
            }
          } catch (IllegalActionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        break;
      case Notification.REMOVE:
        Relation removedRelation = (Relation) notification.getOldValue();
        getWrappedObject().unlink((ptolemy.kernel.Relation) removedRelation.getWrappedObject());
        break;
      case Notification.REMOVE_MANY:
        List<Relation> removedRelations = (List<Relation>) notification.getOldValue();
        for (Relation r : removedRelations) {
          getWrappedObject().unlink((ptolemy.kernel.Relation) r.getWrappedObject());
        }
        break;
      }
      break;
    case TriqPackage.RELATION__ATTRIBUTES:
      switch (notification.getEventType()) {
      case Notification.ADD:
        Attribute addedAttribute = (Attribute) notification.getNewValue();
        if(addedAttribute instanceof Vertex) {
          vertex = (Vertex) addedAttribute;
        }
        break;
      case Notification.ADD_MANY:
        List<Attribute> addedAttributes = (List<Attribute>) notification.getNewValue();
        for(Attribute a : addedAttributes) {
          if(a instanceof Vertex) {
            vertex = (Vertex) a;
          }
        }
        break;
      case Notification.REMOVE:
        Attribute removedAttribute = (Attribute) notification.getOldValue();
        if(removedAttribute==vertex) {
          vertex = null;
        }
        break;
      case Notification.REMOVE_MANY:
        List<Attribute> removedAttributes = (List<Attribute>) notification.getOldValue();
        for(Attribute a : removedAttributes) {
          if(a==vertex) {
            vertex = null;
          }
        }
        break;
      }
      break;
    default:
    }
  }

  @Override
  public void buildWrappedObject() {
    try {
      ptolemy.kernel.CompositeEntity container = (ptolemy.kernel.CompositeEntity) (getContainer() != null ? getContainer().getWrappedObject() : null);
      wrappedObject = container.newRelation(getName());
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
  public void buildWrappedLinks() {
    try {
      for (Relation r : getLinkedRelations()) {
        getWrappedObject().link((ptolemy.kernel.Relation) r.getWrappedObject());
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public ComponentRelation getWrappedObject() {
    return (ComponentRelation) super.getWrappedObject();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case TriqPackage.RELATION__LINKED_RELATIONS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getLinkedRelations()).basicAdd(otherEnd, msgs);
      case TriqPackage.RELATION__LINKING_RELATIONS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getLinkingRelations()).basicAdd(otherEnd, msgs);
      case TriqPackage.RELATION__LINKED_PORTS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getLinkedPorts()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case TriqPackage.RELATION__LINKED_RELATIONS:
        return ((InternalEList<?>)getLinkedRelations()).basicRemove(otherEnd, msgs);
      case TriqPackage.RELATION__LINKING_RELATIONS:
        return ((InternalEList<?>)getLinkingRelations()).basicRemove(otherEnd, msgs);
      case TriqPackage.RELATION__LINKED_PORTS:
        return ((InternalEList<?>)getLinkedPorts()).basicRemove(otherEnd, msgs);
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
      case TriqPackage.RELATION__LINKED_RELATIONS:
        return getLinkedRelations();
      case TriqPackage.RELATION__LINKING_RELATIONS:
        return getLinkingRelations();
      case TriqPackage.RELATION__LINKED_PORTS:
        return getLinkedPorts();
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
      case TriqPackage.RELATION__LINKED_RELATIONS:
        getLinkedRelations().clear();
        getLinkedRelations().addAll((Collection<? extends Relation>)newValue);
        return;
      case TriqPackage.RELATION__LINKING_RELATIONS:
        getLinkingRelations().clear();
        getLinkingRelations().addAll((Collection<? extends Relation>)newValue);
        return;
      case TriqPackage.RELATION__LINKED_PORTS:
        getLinkedPorts().clear();
        getLinkedPorts().addAll((Collection<? extends Port>)newValue);
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
      case TriqPackage.RELATION__LINKED_RELATIONS:
        getLinkedRelations().clear();
        return;
      case TriqPackage.RELATION__LINKING_RELATIONS:
        getLinkingRelations().clear();
        return;
      case TriqPackage.RELATION__LINKED_PORTS:
        getLinkedPorts().clear();
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
      case TriqPackage.RELATION__LINKED_RELATIONS:
        return linkedRelations != null && !linkedRelations.isEmpty();
      case TriqPackage.RELATION__LINKING_RELATIONS:
        return linkingRelations != null && !linkingRelations.isEmpty();
      case TriqPackage.RELATION__LINKED_PORTS:
        return linkedPorts != null && !linkedPorts.isEmpty();
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
      case TriqPackage.RELATION___IS_CONNECTED:
        return isConnected();
      case TriqPackage.RELATION___GET_VERTEX:
        return getVertex();
      case TriqPackage.RELATION___LINK__RELATION:
        link((Relation)arguments.get(0));
        return null;
      case TriqPackage.RELATION___UNLINK__RELATION:
        unlink((Relation)arguments.get(0));
        return null;
      case TriqPackage.RELATION___IS_POTENTIAL_START:
        return isPotentialStart();
      case TriqPackage.RELATION___IS_POTENTIAL_END__LINKABLE:
        return isPotentialEnd((Linkable)arguments.get(0));
      case TriqPackage.RELATION___BUILD_WRAPPED_LINKS:
        buildWrappedLinks();
        return null;
    }
    return super.eInvoke(operationID, arguments);
  }

} // RelationImpl
