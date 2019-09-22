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

package org.eclipse.triquetrum.scisoft.analysis.rpc.staticdispatchertypes;

public class SingleArgumentPrimitives {
  public static Class<Boolean> call(boolean param) {
    return Boolean.TYPE;
  }

  public static Class<Byte> call(byte param) {
    return Byte.TYPE;
  }

  public static Class<Character> call(char param) {
    return Character.TYPE;
  }

  public static Class<Double> call(double param) {
    return Double.TYPE;
  }

  public static Class<Float> call(float param) {
    return Float.TYPE;
  }

  public static Class<Integer> call(int param) {
    return Integer.TYPE;
  }

  public static Class<Long> call(long param) {
    return Long.TYPE;
  }

  public static Class<Short> call(short param) {
    return Short.TYPE;
  }
}
