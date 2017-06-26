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
 * Concrete class that filters on static methods and delegates to the instance provided.
 */
public class AnalysisRpcGenericStaticClassDispatcher extends AbstractAnalysisRpcGenericDispatcher {

  /**
   * @see AbstractAnalysisRpcGenericDispatcher#AbstractAnalysisRpcGenericDispatcher(Class)
   */
  public AnalysisRpcGenericStaticClassDispatcher(Class<?> delegate) {
    super(delegate);
  }

  /**
   * Filter out non-static methods
   */
  @Override
  protected boolean isMethodOk(Method method, String dispatchMethodName, Class<?>[] dispatchArgTypes) {
    if (!Modifier.isStatic(method.getModifiers())) {
      return false;
    }

    return super.isMethodOk(method, dispatchMethodName, dispatchArgTypes);
  }

  @Override
  protected Object getInvokeObject() {
    // Always static methods, therefore null
    return null;
  }
}
