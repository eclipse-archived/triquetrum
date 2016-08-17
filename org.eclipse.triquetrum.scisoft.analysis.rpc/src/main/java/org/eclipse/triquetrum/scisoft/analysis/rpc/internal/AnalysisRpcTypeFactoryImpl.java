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

import org.apache.ws.commons.util.NamespaceContextImpl;
import org.apache.xmlrpc.common.TypeFactoryImpl;
import org.apache.xmlrpc.common.XmlRpcController;
import org.apache.xmlrpc.common.XmlRpcStreamConfig;
import org.apache.xmlrpc.parser.TypeParser;
import org.apache.xmlrpc.serializer.DoubleSerializer;

/**
 * Custom {@link TypeFactoryImpl} that has our own type factory.
 * 
 * @see AnalysisRpcDoubleParser
 */
public class AnalysisRpcTypeFactoryImpl extends TypeFactoryImpl {

  public AnalysisRpcTypeFactoryImpl(XmlRpcController pController) {
    super(pController);
  }

  @Override
  public TypeParser getParser(XmlRpcStreamConfig pConfig, NamespaceContextImpl pContext, String pURI, String pLocalName) {
    if (DoubleSerializer.DOUBLE_TAG.equals(pLocalName)) {
      return new AnalysisRpcDoubleParser();
    }
    return super.getParser(pConfig, pContext, pURI, pLocalName);
  }

}
