/*******************************************************************************
 * Copyright (c) 2014, 2016  Diamond Light Source Ltd., 
 *                          Kichwa Coders & iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
