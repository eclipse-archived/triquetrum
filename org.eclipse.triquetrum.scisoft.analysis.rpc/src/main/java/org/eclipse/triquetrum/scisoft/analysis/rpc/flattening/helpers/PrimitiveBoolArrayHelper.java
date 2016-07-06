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

package org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.helpers;

import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IRootFlattener;

public class PrimitiveBoolArrayHelper extends PrimitiveArrayHelper {

  @Override
  public Object unflatten(Object obj, IRootFlattener rootFlattener) {
    Object[] array = (Object[]) obj;
    boolean[] boolArray = new boolean[array.length];
    for (int i = 0; i < boolArray.length; i++) {
      boolArray[i] = (Boolean) array[i];
    }
    return boolArray;
  }

  @Override
  public boolean canUnFlatten(Object obj) {
    if (!(obj instanceof Object[])) {
      return false;
    }
    Object[] array = (Object[]) obj;
    for (int i = 0; i < array.length; i++) {
      if (!(array[i] instanceof Boolean)) {
        return false;
      }
    }
    return array.length > 0;

  }
}
