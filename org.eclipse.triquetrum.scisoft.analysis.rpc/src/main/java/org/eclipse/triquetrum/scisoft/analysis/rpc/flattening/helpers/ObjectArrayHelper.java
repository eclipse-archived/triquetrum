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

import java.lang.reflect.Array;
import java.util.Arrays;

import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IFlattener;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IRootFlattener;

public class ObjectArrayHelper implements IFlattener<Object[]> {

  public ObjectArrayHelper() {
  }

  @Override
  public Object flatten(Object obj, IRootFlattener rootFlattener) {
    Object[] in = (Object[]) obj;
    Object[] out = new Object[in.length];
    for (int i = 0; i < out.length; i++) {
      out[i] = rootFlattener.flatten(in[i]);
    }
    return out;
  }

  @Override
  public Object[] unflatten(Object obj, IRootFlattener rootFlattener) {
    Object[] inObjArray = (Object[]) obj;
    Object[] unflattened = new Object[inObjArray.length];

    // If all objects are the same class, unflatten to an array of that type
    Class<?> clazz = null;
    boolean canSpecializeUnflattenClazz = true;
    Class<?> superClazz = null;
    boolean canSpecializeUnflattenSuperClazz = true;
    for (int i = 0; i < unflattened.length; i++) {
      unflattened[i] = rootFlattener.unflatten(inObjArray[i]);
      if (unflattened[i] == null) {
        // ok, keep going
      } else if (superClazz != null && !superClazz.isAssignableFrom(unflattened[i].getClass())) {
        canSpecializeUnflattenSuperClazz = false;
      } else if (superClazz == null) {
        canSpecializeUnflattenSuperClazz = false;
      }

      if (unflattened[i] == null) {
        // ok, keep going
      } else if (clazz != null && !clazz.equals(unflattened[i].getClass())) {
        canSpecializeUnflattenClazz = false;
      } else if (clazz == null) {
        clazz = unflattened[i].getClass();

      }
    }

    if (canSpecializeUnflattenClazz && clazz != null) {
      canSpecializeUnflattenSuperClazz = true;
      superClazz = clazz;
    }
    if (canSpecializeUnflattenSuperClazz && superClazz != null) {
      @SuppressWarnings("unchecked")
      Class<? extends Object[]> arrayClazz = (Class<? extends Object[]>) Array.newInstance(superClazz, 0).getClass();
      return Arrays.copyOf(unflattened, unflattened.length, arrayClazz);
    }
    return unflattened;
  }

  @Override
  public boolean canFlatten(Object obj) {
    return obj instanceof Object[];
  }

  @Override
  public boolean canUnFlatten(Object obj) {
    return canFlatten(obj);
  }
}
