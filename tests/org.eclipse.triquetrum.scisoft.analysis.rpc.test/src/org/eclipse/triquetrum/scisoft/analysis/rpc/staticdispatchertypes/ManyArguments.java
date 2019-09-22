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

public class ManyArguments {
  public static int call(int a, int b, int c, int d, int e, int f, int g, int h) {
    return 8;
  }

  public static int call(int a, int b, int c, int d, int e, int f, int g) {
    return 7;
  }

  public static int call(int a, int b, int c, int d, int e, int f) {
    return 6;
  }

  public static int call(int a, int b, int c, int d, int e) {
    return 5;
  }

  public static int call(int a, int b, int c, int d) {
    return 4;
  }

  public static int call(int a, int b, int c) {
    return 3;
  }

  public static int call(int a, int b) {
    return 2;
  }

  public static int call(int a) {
    return 1;
  }

  public static int call() {
    return 0;
  }
}
