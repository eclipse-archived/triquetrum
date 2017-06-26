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
