/*******************************************************************************
 * Copyright (c) 2014, 2019  Diamond Light Source Ltd.,
 *                          Kichwa Coders & iSencia Belgium NV.
 *  
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    DLS, Kichwa Coders - initial API and implementation and/or initial documentation
 *    Erwin De Ley - extraction from DAWN to ease reuse in other contexts
 *******************************************************************************/
package org.eclipse.triquetrum.python.service.util;

import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcException;
import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcRemoteException;

public class PythonUtils {

  public static String formatPythonStackInfo(AnalysisRpcException e) {
    StringBuilder bldr = new StringBuilder("<br/><div style=\"margin-left: 20px; color:RED\">");
    bldr.append(((AnalysisRpcRemoteException) e.getCause()).getPythonFormattedStackTrace(null).replace("\n", "<br/>"));
    // bldr.append(((AnalysisRpcRemoteException) e.getCause()).getPythonFormattedStackTrace("python_service_runscript.py").replace("\n", "<br/>"));
    bldr.append("</div><br/>");
    return bldr.toString();
  }

}
