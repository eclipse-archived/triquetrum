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

import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IRootFlattener;

public class StackTraceElementHelper extends MapFlatteningHelper<StackTraceElement> {
  public static final String DECLARINGCLASS = "declaringClass";
  public static final String METHODNAME = "methodName";
  public static final String FILENAME = "fileName";
  public static final String LINENUMBER = "lineNumber";

  public StackTraceElementHelper() {
    super(StackTraceElement.class);
  }

  @Override
  public StackTraceElement unflatten(Map<?, ?> thisMap, IRootFlattener rootFlattener) {
    String declaringClass = (String) rootFlattener.unflatten(thisMap.get(DECLARINGCLASS));
    String methodName = (String) rootFlattener.unflatten(thisMap.get(METHODNAME));
    String fileName = (String) rootFlattener.unflatten(thisMap.get(FILENAME));
    int lineNumber = (Integer) rootFlattener.unflatten(thisMap.get(LINENUMBER));
    return new StackTraceElement(declaringClass, methodName, fileName, lineNumber);
  }

  @Override
  public Object flatten(Object obj, IRootFlattener rootFlattener) {
    StackTraceElement ste = (StackTraceElement) obj;
    Map<String, Object> outMap = createMap(getTypeCanonicalName());
    outMap.put(DECLARINGCLASS, rootFlattener.flatten(ste.getClassName()));
    outMap.put(METHODNAME, rootFlattener.flatten(ste.getMethodName()));
    outMap.put(FILENAME, rootFlattener.flatten(ste.getFileName()));
    outMap.put(LINENUMBER, rootFlattener.flatten(ste.getLineNumber()));
    return outMap;
  }

}
