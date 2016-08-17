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
import java.util.Map.Entry;

import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IRootFlattener;

import java.util.Set;

@SuppressWarnings("rawtypes")
public class MapHelper extends MapFlatteningHelper<Map> {

  public static final String KEYS = "keys";
  public static final String VALUES = "values";

  public MapHelper() {
    super(Map.class);
  }

  @Override
  public Object flatten(Object obj, IRootFlattener rootFlattener) {
    Map<?, ?> inMap = (Map<?, ?>) obj;

    Object[] keys = new Object[inMap.size()];
    Object[] values = new Object[inMap.size()];

    int i = 0;
    for (Object entryObj : (Set<?>) inMap.entrySet()) {
      Entry<?, ?> entry = (Entry<?, ?>) entryObj;
      keys[i] = rootFlattener.flatten(entry.getKey());
      values[i] = rootFlattener.flatten(entry.getValue());
      i++;
    }

    Map<String, Object> outMap = createMap(getTypeCanonicalName());
    outMap.put(KEYS, keys);
    outMap.put(VALUES, values);
    return outMap;
  }

  @Override
  public Map<?, ?> unflatten(Map<?, ?> inMap, IRootFlattener rootFlattener) {
    Map<Object, Object> returnMap = new HashMap<Object, Object>();
    Object[] keys = (Object[]) inMap.get(KEYS);
    Object[] values = (Object[]) inMap.get(VALUES);
    if (keys.length != values.length) {
      throw new UnsupportedOperationException("Cannot unflatten when keys and values are different lengths");
    }
    for (int i = 0; i < keys.length; i++) {
      returnMap.put(rootFlattener.unflatten(keys[i]), rootFlattener.unflatten(values[i]));
    }
    return returnMap;
  }

  @Override
  public boolean canFlatten(Object obj) {
    return obj instanceof Map;
  }
}
