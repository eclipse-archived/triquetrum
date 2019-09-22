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
package org.eclipse.triquetrum.workflow.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.triquetrum.workflow.model.Attribute;
import org.eclipse.triquetrum.workflow.model.TriqPackage;
import org.eclipse.triquetrum.workflow.model.util.PtolemyUtil;

import ptolemy.kernel.util.NamedObj;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Attribute</b></em>'. <!-- end-user-doc -->
 *
 * @generated
 */
public class AttributeImpl extends NamedObjImpl implements Attribute {
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  protected AttributeImpl() {
    super();
    // this is the default type from Ptolemy that we'll be using
    setWrappedType("ptolemy.kernel.util.Attribute");
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

  @Override
  public void buildWrappedObject() {
    try {
      NamedObj container = getContainer() != null ? getContainer().getWrappedObject() : null;
      wrappedObject = PtolemyUtil._createAttribute(container, getWrappedType(), getName());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public ptolemy.kernel.util.Attribute getWrappedObject() {
    return (ptolemy.kernel.util.Attribute) super.getWrappedObject();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.ATTRIBUTE;
  }

} // AttributeImpl
