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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.TypedNone;
import org.eclipse.triquetrum.scisoft.analysis.rpc.internal.TypeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Abstract implementation of {@link IAnalysisRpcHandler} that uses reflection to resolve method name to call.
 * <p>
 * Concrete classes can:
 * <ul>
 * <li>filter methods that are legal by overriding {@link #isMethodOk(Method, String, Class[])}.</li>
 * <li>provide an instance to invoke method on by overriding {@link #getInvokeObject()}</li>
 * <li>provide an implementation for {@link #invoke(Method, Object, Object[])} if some code wants to be run before or after the method invocation, or if the
 * method invocation wants to be wrapped in some other way.</li>
 * </ul>
 */
abstract public class AbstractAnalysisRpcGenericDispatcher implements IAnalysisRpcHandler {
  private static final Logger logger = LoggerFactory.getLogger(AbstractAnalysisRpcGenericDispatcher.class);
  private Class<?> delegate;

  /**
   * Create a new dispatcher
   *
   * @param delegate
   *          the type of class to delegate to.
   * @throws NullPointerException
   *           if delegate is <code>null</code>
   */
  public AbstractAnalysisRpcGenericDispatcher(Class<?> delegate) {
    if (delegate == null)
      throw new NullPointerException("Instance must be non-null");
    this.delegate = delegate;
  }

  /**
   * Override to do custom or additional checks on whether method is legal.
   *
   * @param method
   *          Method to check
   * @return true if method is ok to use, false otherwise.
   */
  protected boolean isMethodOk(Method method, String dispatchMethodName, Class<?>[] dispatchArgTypes) {
    if (!method.getName().equals(dispatchMethodName)) {
      return false;
    }
    Class<?>[] parameterTypes = method.getParameterTypes();
    if (parameterTypes.length != dispatchArgTypes.length) {
      return false;
    }

    boolean methodOk = true;
    for (int i = 0; i < parameterTypes.length; i++) {
      if (dispatchArgTypes[i] == null && !TypeHelper.isPrimitive(parameterTypes[i])) {
        // good
      } else if (dispatchArgTypes[i] != null && parameterTypes[i].isAssignableFrom(dispatchArgTypes[i])) {
        // good
      } else if (dispatchArgTypes[i] != null && TypeHelper.isBoxed(dispatchArgTypes[i], parameterTypes[i])) {
        // good
      } else {
        // no match
        methodOk = false;
        break;
      }
    }
    return methodOk;
  }

  /**
   * Return the object to invoke on. e.g. static methods can return null, others should return an instance of the delegate passed at construction time.
   *
   * @return object to invoke
   */
  protected abstract Object getInvokeObject();

  private Object dispatch(String methodName, Object[] args) throws AnalysisRpcException {

    Class<?>[] types = new Class<?>[args.length];
    for (int i = 0; i < args.length; i++) {
      if (args[i] == null) {
        types[i] = null;
      } else if (args[i] instanceof TypedNone) {
        types[i] = ((TypedNone) args[i]).getType();
        args[i] = null;
      } else {
        types[i] = args[i].getClass();
      }
    }
    Method m = null;

    for (Method method : delegate.getMethods()) {
      if (isMethodOk(method, methodName, types)) {
        m = method;
        break;
      }
    }

    if (m == null) {
      String msg = "Failed to find method " + delegate.toString() + "." + methodName + "(" + Arrays.toString(types) + ")";
      logger.error(msg);
      throw new AnalysisRpcException(msg);
    }

    try {
      return invoke(m, getInvokeObject(), args);
    } catch (Exception e) {
      String msg = "Failed to invoke method " + delegate.toString() + "." + methodName + "(" + Arrays.toString(types) + ")";
      logger.error(msg, e);
      if (e instanceof InvocationTargetException) {
        throw new AnalysisRpcException(((InvocationTargetException) e).getTargetException());
      }
      throw new AnalysisRpcException(e);
    }
  }

  /**
   * Invoke the actual method reflexively
   *
   * @param method
   *          method to invoke
   * @param instance
   *          instance to invoke method on
   * @param args
   *          arguments to pass
   * @return what the method invokes
   * @throws Exception
   */
  protected Object invoke(Method method, Object instance, Object[] args) throws Exception {
    return method.invoke(instance, args);
  }

  @Override
  public Object run(Object[] args) throws AnalysisRpcException {
    String methodName = (String) args[0];
    Object[] methodArgs = new Object[args.length - 1];
    System.arraycopy(args, 1, methodArgs, 0, methodArgs.length);
    return dispatch(methodName, methodArgs);
  }
}
