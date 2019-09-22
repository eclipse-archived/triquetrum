/*******************************************************************************
 *  Copyright (c) 2012-2016 Diamond Light Source Ltd.,
 *                           Kichwa Coders & iSencia Belgium NV.
 *                           
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0.
 *  
 *  SPDX-License-Identifier: EPL-2.0
 *  
 *  Contributors:
 *      DLS, Kichwa Coders - initial API and implementation and/or initial documentation
 *      Erwin De Ley - extraction from DAWN to ease reuse in other contexts
 *******************************************************************************/

package org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.helpers;

import java.util.Map;

import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IRootFlattener;

abstract public class SortOfEnumHelper<T> extends MapFlatteningHelper<T> {
  public SortOfEnumHelper(Class<T> type) {
    super(type);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Map<String, Object> flatten(Object obj, IRootFlattener rootFlattenerj) {
    T typedObj = (T) obj;
    Map<String, Object> outMap = super.createMap(getTypeCanonicalName());
    outMap.put(CONTENT, typedObj.toString());
    return outMap;
  }

  @Override
  public abstract T unflatten(Map<?, ?> inMap, IRootFlattener rootFlattener);

}
