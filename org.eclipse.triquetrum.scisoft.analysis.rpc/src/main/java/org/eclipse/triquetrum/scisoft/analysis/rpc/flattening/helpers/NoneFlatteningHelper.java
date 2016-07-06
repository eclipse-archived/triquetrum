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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IFlattener;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IRootFlattener;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.TypedNone;

public class NoneFlatteningHelper implements IFlattener<Object> {

  static final String TYPE_NAME = "__None__";
  static final String TYPED_NONE_TYPE = "typedNoneType";
  static final String NULL = "null";

  @Override
  public Object flatten(Object obj, IRootFlattener rootFlattener) {
    Map<String, Object> outMap = new HashMap<String, Object>();
    outMap.put(IFlattener.TYPE_KEY, TYPE_NAME);
    if (obj == null) {
      outMap.put(TYPED_NONE_TYPE, NULL);
    } else {
      TypedNone tn = (TypedNone) obj;
      outMap.put(TYPED_NONE_TYPE, tn.getType().getCanonicalName());
    }
    return outMap;
  }

  @Override
  public Object unflatten(Object obj, IRootFlattener rootFlattener) {
    if (obj == null) {
      return null;
    }
    @SuppressWarnings("unchecked")
    String className = (String) (((Map<String, Object>) obj).get(TYPED_NONE_TYPE));
    if (className != null && !className.equals(NULL)) {
      try {
        TypedNone typedNone = new TypedNone(className);
        return typedNone;
      } catch (ClassNotFoundException e) {
        // Do nothing, just return an Object null
      }
    }
    return null;
  }

  @Override
  public boolean canFlatten(Object obj) {
    return obj == null || obj instanceof TypedNone;
  }

  @Override
  public boolean canUnFlatten(Object obj) {
    if (obj == null) {
      return true;
    }
    if (obj instanceof Map<?, ?>) {
      Map<?, ?> thisMap = (Map<?, ?>) obj;
      return TYPE_NAME.equals(thisMap.get(IFlattener.TYPE_KEY));
    }

    return false;
  }
}
