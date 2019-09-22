/*******************************************************************************
 * Copyright (c) 2015,2019 iSencia Belgium NV.
 *  
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.model.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.triquetrum.workflow.model.Actor;
import org.eclipse.triquetrum.workflow.model.Annotation;
import org.eclipse.triquetrum.workflow.model.Attribute;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.CompositeEntity;
import org.eclipse.triquetrum.workflow.model.Director;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.Linkable;
import org.eclipse.triquetrum.workflow.model.Location;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Parameter;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.TriqPackage;
import org.eclipse.triquetrum.workflow.model.Vertex;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)} to invoke
 * the <code>caseXXX</code> method for each class of the model, starting with the actual class of the object and proceeding up the inheritance hierarchy until a
 * non-null result is returned, which is the result of the switch. <!-- end-user-doc -->
 * 
 * @see org.eclipse.triquetrum.workflow.model.TriqPackage
 * @generated
 */
public class TriqSwitch<T> {
  /**
   * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected static TriqPackage modelPackage;

  /**
   * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public TriqSwitch() {
    if (modelPackage == null) {
      modelPackage = TriqPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  public T doSwitch(EObject theEObject) {
    return doSwitch(theEObject.eClass(), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(EClass theEClass, EObject theEObject) {
    if (theEClass.eContainer() == modelPackage) {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    } else {
      List<EClass> eSuperTypes = theEClass.getESuperTypes();
      return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
    case TriqPackage.NAMED_OBJ: {
      NamedObj namedObj = (NamedObj) theEObject;
      T result = caseNamedObj(namedObj);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case TriqPackage.ATTRIBUTE: {
      Attribute attribute = (Attribute) theEObject;
      T result = caseAttribute(attribute);
      if (result == null)
        result = caseNamedObj(attribute);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case TriqPackage.ANNOTATION: {
      Annotation annotation = (Annotation) theEObject;
      T result = caseAnnotation(annotation);
      if (result == null)
        result = caseAttribute(annotation);
      if (result == null)
        result = caseNamedObj(annotation);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case TriqPackage.PARAMETER: {
      Parameter parameter = (Parameter) theEObject;
      T result = caseParameter(parameter);
      if (result == null)
        result = caseAttribute(parameter);
      if (result == null)
        result = caseNamedObj(parameter);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case TriqPackage.DIRECTOR: {
      Director director = (Director) theEObject;
      T result = caseDirector(director);
      if (result == null)
        result = caseAttribute(director);
      if (result == null)
        result = caseNamedObj(director);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case TriqPackage.ENTITY: {
      Entity entity = (Entity) theEObject;
      T result = caseEntity(entity);
      if (result == null)
        result = caseNamedObj(entity);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case TriqPackage.COMPOSITE_ENTITY: {
      CompositeEntity compositeEntity = (CompositeEntity) theEObject;
      T result = caseCompositeEntity(compositeEntity);
      if (result == null)
        result = caseEntity(compositeEntity);
      if (result == null)
        result = caseNamedObj(compositeEntity);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case TriqPackage.ACTOR: {
      Actor actor = (Actor) theEObject;
      T result = caseActor(actor);
      if (result == null)
        result = caseEntity(actor);
      if (result == null)
        result = caseNamedObj(actor);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case TriqPackage.COMPOSITE_ACTOR: {
      CompositeActor compositeActor = (CompositeActor) theEObject;
      T result = caseCompositeActor(compositeActor);
      if (result == null)
        result = caseCompositeEntity(compositeActor);
      if (result == null)
        result = caseEntity(compositeActor);
      if (result == null)
        result = caseNamedObj(compositeActor);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case TriqPackage.PORT: {
      Port port = (Port) theEObject;
      T result = casePort(port);
      if (result == null)
        result = caseLinkable(port);
      if (result == null)
        result = caseNamedObj(port);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case TriqPackage.RELATION: {
      Relation relation = (Relation) theEObject;
      T result = caseRelation(relation);
      if (result == null)
        result = caseLinkable(relation);
      if (result == null)
        result = caseNamedObj(relation);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case TriqPackage.LOCATION: {
      Location location = (Location) theEObject;
      T result = caseLocation(location);
      if (result == null)
        result = caseAttribute(location);
      if (result == null)
        result = caseNamedObj(location);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case TriqPackage.VERTEX: {
      Vertex vertex = (Vertex) theEObject;
      T result = caseVertex(vertex);
      if (result == null)
        result = caseLocation(vertex);
      if (result == null)
        result = caseLinkable(vertex);
      if (result == null)
        result = caseAttribute(vertex);
      if (result == null)
        result = caseNamedObj(vertex);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case TriqPackage.LINKABLE: {
      Linkable linkable = (Linkable) theEObject;
      T result = caseLinkable(linkable);
      if (result == null)
        result = caseNamedObj(linkable);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    default:
      return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Named Obj</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Obj</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamedObj(NamedObj object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Attribute</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Attribute</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAttribute(Attribute object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Annotation</em>'. <!-- begin-user-doc --> This implementation returns null; returning
   * a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Annotation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAnnotation(Annotation object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseParameter(Parameter object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Director</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Director</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDirector(Director object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Entity</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Entity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEntity(Entity object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Composite Entity</em>'. <!-- begin-user-doc --> This implementation returns null;
   * returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Composite Entity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCompositeEntity(CompositeEntity object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Actor</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Actor</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseActor(Actor object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Composite Actor</em>'. <!-- begin-user-doc --> This implementation returns null;
   * returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Composite Actor</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCompositeActor(CompositeActor object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Port</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Port</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePort(Port object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Relation</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Relation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRelation(Relation object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Location</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Location</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLocation(Location object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Vertex</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Vertex</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVertex(Vertex object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Linkable</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Linkable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLinkable(Linkable object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch, but this is the last case anyway. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public T defaultCase(EObject object) {
    return null;
  }

} // TriqSwitch
