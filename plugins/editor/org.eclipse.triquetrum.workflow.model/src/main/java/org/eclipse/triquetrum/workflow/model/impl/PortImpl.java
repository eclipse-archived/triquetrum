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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.Direction;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.Linkable;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.TriqPackage;
import org.eclipse.triquetrum.workflow.model.Vertex;
import org.eclipse.triquetrum.workflow.model.util.PtolemyUtil;

import ptolemy.actor.IOPort;
import ptolemy.kernel.util.IllegalActionException;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Port</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl#isInput <em>Input</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl#isOutput <em>Output</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl#isMultiPort <em>Multi Port</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl#getDirection <em>Direction</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl#getLinkedRelations <em>Linked Relations</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl#getInsideLinkedRelations <em>Inside Linked
 * Relations</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl#getOutsideLinkedRelations <em>Outside Linked
 * Relations</em>}</li>
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
   * The default value of the '{@link #isOutput() <em>Output</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @see #isOutput()
   * @generated
   * @ordered
   */
  protected static final boolean OUTPUT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isOutput() <em>Output</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @see #isOutput()
   * @generated
   * @ordered
   */
  protected boolean output = OUTPUT_EDEFAULT;

  /**
   * The default value of the '{@link #isMultiPort() <em>Multi Port</em>}' attribute. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see #isMultiPort()
   * @generated
   * @ordered
   */
  protected static final boolean MULTI_PORT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isMultiPort() <em>Multi Port</em>}' attribute. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see #isMultiPort()
   * @generated
   * @ordered
   */
  protected boolean multiPort = MULTI_PORT_EDEFAULT;

  /**
   * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see #getDirection()
   * @generated
   * @ordered
   */
  protected static final Direction DIRECTION_EDEFAULT = Direction.DEFAULT;

  /**
   * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see #getDirection()
   * @generated
   * @ordered
   */
  protected Direction direction = DIRECTION_EDEFAULT;

  /**
   * This is true if the Direction attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  protected boolean directionESet;

  /**
   * The cached value of the '{@link #getLinkedRelations() <em>Linked Relations</em>}' reference list. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @see #getLinkedRelations()
   * @generated
   * @ordered
   */
  protected EList<Relation> linkedRelations;

  /**
   * The cached value of the '{@link #getInsideLinkedRelations() <em>Inside Linked Relations</em>}' reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #getInsideLinkedRelations()
   * @generated
   * @ordered
   */
  protected EList<Relation> insideLinkedRelations;

  /**
   * The cached value of the '{@link #getOutsideLinkedRelations() <em>Outside Linked Relations</em>}' reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #getOutsideLinkedRelations()
   * @generated
   * @ordered
   */
  protected EList<Relation> outsideLinkedRelations;

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
    return TriqPackage.Literals.PORT;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public boolean isInput() {
    return input;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public void setInput(boolean newInput) {
    boolean oldInput = input;
    input = newInput;
    if (getWrappedObject() != null) {
      IOPort ptObject = getWrappedObject();
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
  @Override
  public boolean isOutput() {
    return output;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public void setOutput(boolean newOutput) {
    boolean oldOutput = output;
    output = newOutput;
    if (getWrappedObject() != null) {
      IOPort ptObject = getWrappedObject();
      try {
        ptObject.setOutput(newOutput);
      } catch (IllegalActionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
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
   * @generated NOT
   */
  @Override
  public EList<Relation> getInsideLinkedRelations() {
    List<Relation> relations = new ArrayList<>();
    for (Relation r : getLinkedRelations()) {
      if (getContainer().equals(r.getContainer())) {
        relations.add(r);
      }
    }
    return new EcoreEList.UnmodifiableEList(this, TriqPackage.eINSTANCE.getPort_InsideLinkedRelations(), relations.size(), relations.toArray());
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public EList<Relation> getOutsideLinkedRelations() {
    List<Relation> relations = new ArrayList<>();
    for (Relation r : getLinkedRelations()) {
      if (getContainer().getContainer().equals(r.getContainer())) {
        relations.add(r);
      }
    }
    return new EcoreEList.UnmodifiableEList(this, TriqPackage.eINSTANCE.getPort_OutsideLinkedRelations(), relations.size(), relations.toArray());
  }

  /**
   * <!-- begin-user-doc --> Checks whether the port can accept a new outside relation connection. For single ports, this
   * means that if there is already a connection, the new request must be refused. <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public boolean canAcceptNewOutsideRelation() {
    return (isMultiPort() || getOutsideLinkedRelations().isEmpty());
  }

  /**
   * <!-- begin-user-doc --> Checks whether the port can accept a new inside relation connection. For single ports, this
   * means that if there is already an inside relation, the new request must be refused. The container must be a
   * CompositeActor, to allow inside relations at all. <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public boolean canAcceptNewInsideRelation() {
    return (isMultiPort() || getInsideLinkedRelations().isEmpty()) && (getContainer() instanceof CompositeActor);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public void link(Relation relation) {
    getLinkedRelations().add(relation);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public void unlink(Relation relation) {
    getLinkedRelations().remove(relation);
  }

  /**
   * <!-- begin-user-doc --> This method checks if this port can be the src for a new connection. For ports, this is true
   * when the port has remaining capacity to start either a new outside or new inside relation. Remark that connections
   * can be drawn starting from an output port and ending in an input port as well, similarly to what Ptolemy's Vergil
   * allows.
   * 
   * @return true if the src can be a starting point for a new connection <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public boolean isPotentialStart() {
    return canAcceptNewOutsideRelation() || canAcceptNewInsideRelation();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public boolean isPotentialEnd(Linkable start) {
    boolean result = false;
    if (isOutput()) {
      // Output ports can be a valid target when their container is a CompositeActor, and the source is an input port
      // or vertex within the same composite or an output port of an actor within that composite.
      if (canAcceptNewInsideRelation()) {
        NamedObj targetContainerModelLevel = getContainer();
        boolean isCorrectContainer = targetContainerModelLevel instanceof CompositeActor;
        if (isCorrectContainer) {
          boolean isSrcValidInSameComposite = targetContainerModelLevel.equals(start.getContainer()) && ((start instanceof Port) && ((Port) start).isInput());
          boolean isSrcValidInActorInSameComposite = targetContainerModelLevel.equals(start.getContainer().getContainer())
              && ((start instanceof Vertex) || ((start instanceof Port) && ((Port) start).isOutput()));
          result = isSrcValidInActorInSameComposite || isSrcValidInSameComposite;
        }
      }
      // But an output port can also accept a connection from an input port in the same "model level",
      // when the user is drawing a connection in reverse direction.
      if(!result && canAcceptNewOutsideRelation()) {
        NamedObj targetContainerModelLevel = getContainer().getContainer();
        boolean isSrcValidVertex = (start instanceof Vertex) && (targetContainerModelLevel.equals(start.getContainer().getContainer()));
        boolean isSrcValidPort = (start instanceof Port) && (((Port) start).isInput() && targetContainerModelLevel.equals(start.getContainer().getContainer())
            || ((Port) start).isOutput() && targetContainerModelLevel.equals(start.getContainer()));
        result = isSrcValidVertex || isSrcValidPort;
      }
    }
    if (!result && isInput()) {
      // Input ports and Vertex targets can receive connections from sources that are in the same encompassing model
      // level, i.e. in the container of the target's container.
      // For source ports we need to differentiate between plain actor output ports and input ports on a submodel CompositeActor.
      if (canAcceptNewOutsideRelation()) {
        NamedObj targetContainerModelLevel = getContainer().getContainer();
        boolean isSrcValidVertex = (start instanceof Vertex) && (targetContainerModelLevel.equals(start.getContainer().getContainer()));
        boolean isSrcValidPort = (start instanceof Port) && (((Port) start).isOutput() && targetContainerModelLevel.equals(start.getContainer().getContainer())
            || ((Port) start).isInput() && targetContainerModelLevel.equals(start.getContainer()));
        result = isSrcValidVertex || isSrcValidPort;
      }
    }
    return result;
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
  public boolean isMultiPort() {
    return multiPort;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
  public void setMultiPort(boolean newMultiPort) {
    boolean oldMultiPort = multiPort;
    multiPort = newMultiPort;
    if (getWrappedObject() != null) {
      IOPort ptObject = getWrappedObject();
      try {
        ptObject.setMultiport(newMultiPort);
      } catch (IllegalActionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.PORT__MULTI_PORT, oldMultiPort, multiPort));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public Direction getDirection() {
    if (direction != null && Direction.DEFAULT != direction) {
      return direction;
    } else if (isInput()) {
      return Direction.WEST;
    } else if (isOutput()) {
      return Direction.EAST;
    } else {
      return direction;
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setDirection(Direction newDirection) {
    Direction oldDirection = direction;
    direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
    boolean oldDirectionESet = directionESet;
    directionESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.PORT__DIRECTION, oldDirection, direction, !oldDirectionESet));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void unsetDirection() {
    Direction oldDirection = direction;
    boolean oldDirectionESet = directionESet;
    direction = DIRECTION_EDEFAULT;
    directionESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, TriqPackage.PORT__DIRECTION, oldDirection, DIRECTION_EDEFAULT, oldDirectionESet));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public boolean isSetDirection() {
    return directionESet;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public EList<Relation> getLinkedRelations() {
    if (linkedRelations == null) {
      linkedRelations = new EObjectWithInverseResolvingEList.ManyInverse<Relation>(Relation.class, this, TriqPackage.PORT__LINKED_RELATIONS,
          TriqPackage.RELATION__LINKED_PORTS);
    }
    return linkedRelations;
  }

  @Override
  public Entity getContainer() {
    return (Entity) super.getContainer();
  }

  // TODO evaluate if it's worth the trouble to go for a full-blown EMF adapter + adapterfactory setup
  /**
   * This is where we react to changes in linked ports and relations, to keep the wrapped Ptolemy model up-to-date.
   */
  @Override
  public void eNotify(Notification notification) {
    super.eNotify(notification);
    IOPort ptPort = getWrappedObject();
    if (ptPort != null) {
      switch (notification.getFeatureID(Relation.class)) {
      case TriqPackage.PORT__LINKED_RELATIONS:
        switch (notification.getEventType()) {
        case Notification.ADD: {
          Relation addedRelation = (Relation) notification.getNewValue();
          try {
            ptolemy.kernel.Relation ptRelation = (ptolemy.kernel.Relation) addedRelation.getWrappedObject();
            // TODO evaluate : this implies that in Triquetrum a port can only be linked once to a given relation.
            // Although this seems logical, Ptolemy does not impose this constraint...
            if (!ptPort.isLinked(ptRelation)) {
              ptPort.link(ptRelation);
            }
          } catch (IllegalActionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
          break;
        case Notification.ADD_MANY: {
          List<Relation> addedRelations = (List<Relation>) notification.getNewValue();
          for (Relation addedRelation : addedRelations) {
            try {
              ptolemy.kernel.Relation ptRelation = (ptolemy.kernel.Relation) addedRelation.getWrappedObject();
              // TODO evaluate : this implies that in Triquetrum a port can only be linked once to a given relation.
              // Although this seems logical, Ptolemy does not impose this constraint...
              if (!ptPort.isLinked(ptRelation)) {
                ptPort.link(ptRelation);
              }
            } catch (IllegalActionException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        }
          break;
        case Notification.REMOVE: {
          Relation removedRelation = (Relation) notification.getOldValue();
          ptolemy.kernel.Relation ptRelation = (ptolemy.kernel.Relation) removedRelation.getWrappedObject();
          ptPort.unlink(ptRelation);
        }
          break;
        case Notification.REMOVE_MANY: {
          List<Relation> removedRelations = (List<Relation>) notification.getOldValue();
          for (Relation removedRelation : removedRelations) {
            ptolemy.kernel.Relation ptRelation = (ptolemy.kernel.Relation) removedRelation.getWrappedObject();
            ptPort.unlink(ptRelation);
          }
        }
          break;
        }
        break;
      default:
      }
    }
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

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  @Override
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
  public void applyWrappedObject() {
    if (!isDeepComplete()) {
      super.applyWrappedObject();
      IOPort port = getWrappedObject();
      setInput(port.isInput());
      setOutput(port.isOutput());
      setMultiPort(port.isMultiport());
    }
  }

  @Override
  public IOPort getWrappedObject() {
    return (IOPort) super.getWrappedObject();
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
    case TriqPackage.PORT__MULTI_PORT:
      return isMultiPort();
    case TriqPackage.PORT__DIRECTION:
      return getDirection();
    case TriqPackage.PORT__LINKED_RELATIONS:
      return getLinkedRelations();
    case TriqPackage.PORT__INSIDE_LINKED_RELATIONS:
      return getInsideLinkedRelations();
    case TriqPackage.PORT__OUTSIDE_LINKED_RELATIONS:
      return getOutsideLinkedRelations();
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
    case TriqPackage.PORT__MULTI_PORT:
      setMultiPort((Boolean) newValue);
      return;
    case TriqPackage.PORT__DIRECTION:
      setDirection((Direction) newValue);
      return;
    case TriqPackage.PORT__LINKED_RELATIONS:
      getLinkedRelations().clear();
      getLinkedRelations().addAll((Collection<? extends Relation>) newValue);
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
    case TriqPackage.PORT__MULTI_PORT:
      setMultiPort(MULTI_PORT_EDEFAULT);
      return;
    case TriqPackage.PORT__DIRECTION:
      unsetDirection();
      return;
    case TriqPackage.PORT__LINKED_RELATIONS:
      getLinkedRelations().clear();
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
    case TriqPackage.PORT__MULTI_PORT:
      return multiPort != MULTI_PORT_EDEFAULT;
    case TriqPackage.PORT__DIRECTION:
      return isSetDirection();
    case TriqPackage.PORT__LINKED_RELATIONS:
      return linkedRelations != null && !linkedRelations.isEmpty();
    case TriqPackage.PORT__INSIDE_LINKED_RELATIONS:
      return insideLinkedRelations != null && !insideLinkedRelations.isEmpty();
    case TriqPackage.PORT__OUTSIDE_LINKED_RELATIONS:
      return outsideLinkedRelations != null && !outsideLinkedRelations.isEmpty();
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
    case TriqPackage.PORT___CAN_ACCEPT_NEW_OUTSIDE_RELATION:
      return canAcceptNewOutsideRelation();
    case TriqPackage.PORT___CAN_ACCEPT_NEW_INSIDE_RELATION:
      return canAcceptNewInsideRelation();
    case TriqPackage.PORT___LINK__RELATION:
      link((Relation) arguments.get(0));
      return null;
    case TriqPackage.PORT___UNLINK__RELATION:
      unlink((Relation) arguments.get(0));
      return null;
    case TriqPackage.PORT___IS_POTENTIAL_START:
      return isPotentialStart();
    case TriqPackage.PORT___IS_POTENTIAL_END__LINKABLE:
      return isPotentialEnd((Linkable) arguments.get(0));
    case TriqPackage.PORT___BUILD_WRAPPED_LINKS:
      buildWrappedLinks();
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
    result.append(" (input: ");
    result.append(input);
    result.append(", output: ");
    result.append(output);
    result.append(", multiPort: ");
    result.append(multiPort);
    result.append(", direction: ");
    if (directionESet)
      result.append(direction);
    else
      result.append("<unset>");
    result.append(')');
    return result.toString();
  }

} // PortImpl
