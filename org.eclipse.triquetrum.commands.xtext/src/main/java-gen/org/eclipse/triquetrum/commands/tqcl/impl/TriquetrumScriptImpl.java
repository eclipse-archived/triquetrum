/*******************************************************************************
 * Copyright (c)  2017 Totaro Rodolfo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Rodolfo Totaro - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.commands.tqcl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.triquetrum.commands.tqcl.Command;
import org.eclipse.triquetrum.commands.tqcl.Library;
import org.eclipse.triquetrum.commands.tqcl.TqclPackage;
import org.eclipse.triquetrum.commands.tqcl.TriquetrumScript;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Triquetrum Script</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.impl.TriquetrumScriptImpl#getLibraries <em>Libraries</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.commands.tqcl.impl.TriquetrumScriptImpl#getCommands <em>Commands</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TriquetrumScriptImpl extends MinimalEObjectImpl.Container implements TriquetrumScript
{
  /**
   * The cached value of the '{@link #getLibraries() <em>Libraries</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLibraries()
   * @generated
   * @ordered
   */
  protected EList<Library> libraries;

  /**
   * The cached value of the '{@link #getCommands() <em>Commands</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCommands()
   * @generated
   * @ordered
   */
  protected EList<Command> commands;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TriquetrumScriptImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return TqclPackage.Literals.TRIQUETRUM_SCRIPT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Library> getLibraries()
  {
    if (libraries == null)
    {
      libraries = new EObjectContainmentEList<Library>(Library.class, this, TqclPackage.TRIQUETRUM_SCRIPT__LIBRARIES);
    }
    return libraries;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Command> getCommands()
  {
    if (commands == null)
    {
      commands = new EObjectContainmentEList<Command>(Command.class, this, TqclPackage.TRIQUETRUM_SCRIPT__COMMANDS);
    }
    return commands;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case TqclPackage.TRIQUETRUM_SCRIPT__LIBRARIES:
        return ((InternalEList<?>)getLibraries()).basicRemove(otherEnd, msgs);
      case TqclPackage.TRIQUETRUM_SCRIPT__COMMANDS:
        return ((InternalEList<?>)getCommands()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case TqclPackage.TRIQUETRUM_SCRIPT__LIBRARIES:
        return getLibraries();
      case TqclPackage.TRIQUETRUM_SCRIPT__COMMANDS:
        return getCommands();
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
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case TqclPackage.TRIQUETRUM_SCRIPT__LIBRARIES:
        getLibraries().clear();
        getLibraries().addAll((Collection<? extends Library>)newValue);
        return;
      case TqclPackage.TRIQUETRUM_SCRIPT__COMMANDS:
        getCommands().clear();
        getCommands().addAll((Collection<? extends Command>)newValue);
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
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case TqclPackage.TRIQUETRUM_SCRIPT__LIBRARIES:
        getLibraries().clear();
        return;
      case TqclPackage.TRIQUETRUM_SCRIPT__COMMANDS:
        getCommands().clear();
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
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case TqclPackage.TRIQUETRUM_SCRIPT__LIBRARIES:
        return libraries != null && !libraries.isEmpty();
      case TqclPackage.TRIQUETRUM_SCRIPT__COMMANDS:
        return commands != null && !commands.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //TriquetrumScriptImpl
