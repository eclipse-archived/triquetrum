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

package org.eclipse.triquetrum.scisoft.analysis.rpc;

/**
 * Wrapper exception for any one of a number of possible failures. Call {@link #getCause()} for the underlying exception
 */
public class AnalysisRpcException extends Exception {
  private static final long serialVersionUID = 7922312403076295234L;

  public AnalysisRpcException() {
    super();
  }

  public AnalysisRpcException(String s) {
    super(s);
  }

  public AnalysisRpcException(String message, Throwable cause) {
    super(message, cause);
  }

  public AnalysisRpcException(Throwable cause) {
    super(cause);
  }

}
