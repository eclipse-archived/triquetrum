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

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IFlattener;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IRootFlattener;

abstract public class PrimitiveArrayHelper implements IFlattener<Object> {

  @Override
  public Object flatten(Object obj, IRootFlattener rootFlattener) {
    if (obj instanceof int[]) {
      return ArrayUtils.toObject((int[]) obj);
    } else if (obj instanceof boolean[]) {
      return ArrayUtils.toObject((boolean[]) obj);
    } else if (obj instanceof double[]) {
      return ArrayUtils.toObject((double[]) obj);
    }
    throw new AssertionError();
  }

  @Override
  public boolean canFlatten(Object obj) {
    return obj instanceof int[] || obj instanceof boolean[] || obj instanceof double[];
  }
}
