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

package org.eclipse.triquetrum.scisoft.analysis.rpc.flattening;

import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcException;

public class FlatteningViaAnalysisRpcToPythonAndRunLocalLoopbackTest extends FlatteningViaAnalysisRpcToPythonTestAbstract {

  @Override
  protected Object doActualFlattenAndUnflatten(Object inObj) {
    checkPythonState();

    try {
      return client.request("loopback_after_local", new Object[] { inObj });
    } catch (AnalysisRpcException e) {
      if (e.getCause().getClass() == Exception.class)
        return e.getCause();
      throw new RuntimeException(e);
    }
  }

}
