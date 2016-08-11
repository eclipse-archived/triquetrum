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

package org.eclipse.triquetrum.scisoft.analysis.rpc.internal;

import org.apache.xmlrpc.parser.DoubleParser;
import org.apache.xmlrpc.parser.TypeParser;
import org.xml.sax.SAXException;

/**
 * While we mostly handle custom data types through flattening, one particular issue is handled by using Apache XML-RPC's builtin type system.
 * <p>
 * There is an imbalance in how NaN, -Inf and +Inf are handled between XML-RPC by apache and the one built-in to Python. This class and the referenced handlers
 * intercept the not normally conforming version and convert them to a type that is supported by XML-RPC.
 * 
 * @see AnalysisRpcServerHandlerImpl
 * @see <a href="https://issues.apache.org/jira/browse/XMLRPC-146">Bug Report in XML-RPC Jira</a>
 */
public class AnalysisRpcDoubleParser extends DoubleParser implements TypeParser {
  @Override
  protected void setResult(String pResult) throws SAXException {
    // In Python nan, inf are always all lower case and java doesn't support that
    if ("nan".equals(pResult))
      super.setResult(Double.NaN);
    else if ("inf".equals(pResult))
      super.setResult(Double.POSITIVE_INFINITY);
    else if ("-inf".equals(pResult))
      super.setResult(Double.NEGATIVE_INFINITY);
    else
      super.setResult(pResult);
  }

}
