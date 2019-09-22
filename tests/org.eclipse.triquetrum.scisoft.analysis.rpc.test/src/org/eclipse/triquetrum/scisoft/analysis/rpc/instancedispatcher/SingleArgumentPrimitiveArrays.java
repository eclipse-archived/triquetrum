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

public class SingleArgumentPrimitiveArrays {
  public Class<boolean[]> call(boolean[] param) {
    return boolean[].class;
  }

  public Class<byte[]> call(byte[] param) {
    return byte[].class;
  }

  public Class<char[]> call(char[] param) {
    return char[].class;
  }

  public Class<double[]> call(double[] param) {
    return double[].class;
  }

  public Class<float[]> call(float[] param) {
    return float[].class;
  }

  public Class<int[]> call(int[] param) {
    return int[].class;
  }

  public Class<long[]> call(long[] param) {
    return long[].class;
  }

  public Class<short[]> call(short[] param) {
    return short[].class;
  }
}
