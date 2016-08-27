/*******************************************************************************
 * Copyright (c) 2012-2016 Diamond Light Source Ltd.,
 *                         Kichwa Coders & iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    DLS, Kichwa Coders - initial API and implementation and/or initial documentation
 *    Erwin De Ley - extraction from DAWN to ease reuse in other contexts
 *******************************************************************************/

package org.eclipse.triquetrum.scisoft.analysis.rpc;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Concrete class that filters on instance methods and delegates to the instance provided.
 */
public class AnalysisRpcGenericInstanceDispatcher extends AbstractAnalysisRpcGenericDispatcher {
  private final Object instance;

  /**
   * Create a new dispatcher
   *
   * @param delegate
   *          the type of class to delegate to. By providing an interface class here a limited view on the public methods provided by instance will be exposed
   * @param instance
   *          instance of the class to delegate to
   * @throws NullPointerException
   *           if delegate or instance is <code>null</code>
   * @throws IllegalArgumentException
   *           if instance is not an instance of delegate
   */
  public AnalysisRpcGenericInstanceDispatcher(Class<?> delegate, Object instance) {
    super(delegate);
    if (instance == null)
      throw new NullPointerException("Instance must be non-null");
    if (!delegate.isInstance(instance))
      throw new IllegalArgumentException("Instance must be an instance of class represented by delegate");
    this.instance = instance;
  }

  /**
   * Create a new dispatcher
   *
   * @param instance
   *          instance of the class to delegate to, the type of the delgating class is based on the instance
   * @throws NullPointerException
   *           if instance is <code>null</code>
   */
  public static AnalysisRpcGenericInstanceDispatcher getDispatcher(Object instance) {
    return new AnalysisRpcGenericInstanceDispatcher(instance.getClass(), instance);
  }

  /**
   * Filter out static methods
   */
  @Override
  protected boolean isMethodOk(Method method, String dispatchMethodName, Class<?>[] dispatchArgTypes) {
    if (Modifier.isStatic(method.getModifiers())) {
      return false;
    }

    return super.isMethodOk(method, dispatchMethodName, dispatchArgTypes);
  }

  /**
   * Return the instance to dispatch to
   */
  @Override
  protected Object getInvokeObject() {
    return instance;
  }
}
