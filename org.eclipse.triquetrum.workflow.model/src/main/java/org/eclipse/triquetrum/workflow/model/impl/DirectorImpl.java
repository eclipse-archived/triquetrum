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

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.triquetrum.workflow.model.Attribute;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.Director;
import org.eclipse.triquetrum.workflow.model.Parameter;
import org.eclipse.triquetrum.workflow.model.TriqPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Director</b></em>'. <!-- end-user-doc -->
 *
 * @generated
 */
public class DirectorImpl extends AttributeImpl implements Director {
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  protected DirectorImpl() {
    super();
  }

  @Override
  public ptolemy.actor.Director getWrappedObject() {
    return (ptolemy.actor.Director) super.getWrappedObject();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.DIRECTOR;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  public EList<Parameter> getParameters() {
    // TODO: improve this with a jdk8 collection filter thing
    EList<Parameter> result = new BasicEList<>();
    for (Attribute a : getAttributes()) {
      if (Parameter.class.isInstance(a)) {
        result.add((Parameter) a);
      }
    }

    return result;
  }

  @Override
  public CompositeActor getContainer() {
    return (CompositeActor) eContainer();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
    switch (operationID) {
      case TriqPackage.DIRECTOR___GET_PARAMETERS:
        return getParameters();
    }
    return super.eInvoke(operationID, arguments);
  }

} // DirectorImpl
