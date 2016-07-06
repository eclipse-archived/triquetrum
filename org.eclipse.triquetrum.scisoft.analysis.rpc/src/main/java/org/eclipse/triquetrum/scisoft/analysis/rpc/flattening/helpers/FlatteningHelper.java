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

abstract public class FlatteningHelper<T> implements IFlattener<T> {

  final private Class<T> type;

  public FlatteningHelper(Class<T> type) {
    this.type = type;
  }

  @Override
  public abstract Object flatten(Object obj, IRootFlattener rootFlattener);

  @Override
  public abstract T unflatten(Object obj, IRootFlattener rootFlattener);

  @Override
  public boolean canFlatten(Object obj) {
    return type.isAssignableFrom(obj.getClass());
  }

  protected Map<String, Object> getFlattenedOutMap(Class<T> clazz) {
    Map<String, Object> returnMap = new HashMap<String, Object>();
    returnMap.put(IFlattener.TYPE_KEY, clazz.getCanonicalName());
    return returnMap;

  }

  final protected String getTypeCanonicalName() {
    return type.getCanonicalName();
  }

}
