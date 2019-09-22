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
