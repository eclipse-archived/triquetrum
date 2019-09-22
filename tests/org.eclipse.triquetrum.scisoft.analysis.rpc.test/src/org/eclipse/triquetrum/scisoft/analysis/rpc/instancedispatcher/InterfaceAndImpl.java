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

package org.eclipse.triquetrum.scisoft.analysis.rpc.instancedispatcher;

public interface InterfaceAndImpl {
  public String interfacecall(int a);

  public String implcalloverloaded(int a);

  public class Impl implements InterfaceAndImpl {

    @Override
    public String interfacecall(int a) {
      return "interfacecall";
    }

    public String implcall(int a) {
      return "implcall";
    }

    @Override
    public String implcalloverloaded(int a) {
      return "implcalloverloaded - interface";
    }

    public String implcalloverloaded(String a) {
      return "implcalloverloaded - impl";
    }
  }
}
