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

abstract public class MapFlatteningHelper<T> extends FlatteningHelper<T> {
  protected static final String CONTENT = "content";

  public MapFlatteningHelper(Class<T> type) {
    super(type);
  }

  public abstract T unflatten(Map<?, ?> thisMap, IRootFlattener rootFlattener);

  public Map<String, Object> createMap(String typeName) {
    Map<String, Object> outMap = new HashMap<String, Object>();
    outMap.put(IFlattener.TYPE_KEY, typeName);
    return outMap;
  }

  @Override
  public T unflatten(Object obj, IRootFlattener rootFlattener) {
    return unflatten((Map<?, ?>) obj, rootFlattener);
  }

  @Override
  public boolean canUnFlatten(Object obj) {
    if (obj instanceof Map<?, ?>) {
      Map<?, ?> thisMap = (Map<?, ?>) obj;
      return getTypeCanonicalName().equals(thisMap.get(IFlattener.TYPE_KEY));
    }

    return false;
  }
}
