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

import java.util.Map;

import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcRemoteException;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IRootFlattener;

public class ExceptionHelper extends MapFlatteningHelper<Exception> {
  public static final String EXECTYPESTR = "exctypestr";
  public static final String EXECVALUESTR = "excvaluestr";
  public static final String TRACEBACK = "traceback";
  public static final String PYTHONTEXTS = "pythontexts";

  public ExceptionHelper() {
    super(Exception.class);
  }

  @Override
  public AnalysisRpcRemoteException unflatten(Map<?, ?> thisMap, IRootFlattener rootFlattener) {
    String exctypestr = (String) rootFlattener.unflatten(thisMap.get(EXECTYPESTR));
    String excvaluestr = (String) rootFlattener.unflatten(thisMap.get(EXECVALUESTR));
    String message = exctypestr;
    if (excvaluestr != null) {
      message += ": " + excvaluestr;
    }
    final StackTraceElement[] stackTrace;
    if (!thisMap.containsKey(TRACEBACK)) {
      StackTraceElement ste = new StackTraceElement("<ExceptionInOtherEndDuringAnalysisRpcCall>", "<unknown>", "<unknown>", -1);
      stackTrace = new StackTraceElement[] { ste };
    } else {
      stackTrace = (StackTraceElement[]) rootFlattener.unflatten(thisMap.get(TRACEBACK));
    }
    if (thisMap.containsKey(PYTHONTEXTS)) {
      final AnalysisRpcRemoteException e = new AnalysisRpcRemoteException(message);
      e.setStackTrace(stackTrace);
      Object unflatten = rootFlattener.unflatten(thisMap.get(PYTHONTEXTS));
      String[] texts = (String[]) unflatten;
      e.setStackTraceTexts(texts);
      return e;
    } else {
      final AnalysisRpcRemoteException e = new AnalysisRpcRemoteException(message);
      e.setStackTrace(stackTrace);
      return e;
    }
  }

  @Override
  public Object flatten(Object obj, IRootFlattener rootFlattener) {
    Exception thisException = (Exception) obj;
    Map<String, Object> outMap = createMap(getTypeCanonicalName());
    outMap.put(EXECTYPESTR, rootFlattener.flatten(thisException.getClass().getCanonicalName()));
    outMap.put(EXECVALUESTR, rootFlattener.flatten(thisException.getLocalizedMessage()));
    outMap.put(TRACEBACK, rootFlattener.flatten(thisException.getStackTrace()));
    if (thisException instanceof AnalysisRpcRemoteException) {
      AnalysisRpcRemoteException excWithTexts = (AnalysisRpcRemoteException) thisException;
      outMap.put(PYTHONTEXTS, rootFlattener.flatten(excWithTexts.getStackTraceTexts()));
    }
    return outMap;
  }

}
