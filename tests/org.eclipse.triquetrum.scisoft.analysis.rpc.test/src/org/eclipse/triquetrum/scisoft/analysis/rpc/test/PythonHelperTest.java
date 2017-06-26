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
package org.eclipse.triquetrum.scisoft.analysis.rpc.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class PythonHelperTest {
  @Rule
  public Timeout globalTimeout = Timeout.seconds(2);

  // A quick test of the test
  @Test
  public void testParseArray() {
    Assert.assertArrayEquals(new String[0], PythonHelper.parseArray("[]"));
    Assert.assertArrayEquals(new String[] { "one" }, PythonHelper.parseArray("['one']"));
    Assert.assertArrayEquals(new String[] { "one", "two" }, PythonHelper.parseArray("['one', 'two']"));
    Assert.assertArrayEquals(new String[] { "one one", "two two" }, PythonHelper.parseArray("['one one', 'two two']"));
  }

}
