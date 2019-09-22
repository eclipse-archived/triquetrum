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

public class SingleArgumentPrimitives {
  public Class<Boolean> call(boolean param) {
    return Boolean.TYPE;
  }

  public Class<Byte> call(byte param) {
    return Byte.TYPE;
  }

  public Class<Character> call(char param) {
    return Character.TYPE;
  }

  public Class<Double> call(double param) {
    return Double.TYPE;
  }

  public Class<Float> call(float param) {
    return Float.TYPE;
  }

  public Class<Integer> call(int param) {
    return Integer.TYPE;
  }

  public Class<Long> call(long param) {
    return Long.TYPE;
  }

  public Class<Short> call(short param) {
    return Short.TYPE;
  }
}
