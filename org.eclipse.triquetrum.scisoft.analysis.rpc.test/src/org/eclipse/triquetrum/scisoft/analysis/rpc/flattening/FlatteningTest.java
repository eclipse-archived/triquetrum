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

package org.eclipse.triquetrum.scisoft.analysis.rpc.flattening;

import org.junit.Assert;
import org.junit.Test;

public class FlatteningTest extends ExplicitFlatteningTestAbstract {

  @Override
  protected Object doAdditionalWorkOnFlattendForm(Object flat) {
    // Nothing else to do for basic flattening test
    return flat;
  }

  @Test
  public void testCheckFlattanableFalse() {
    Assert.assertFalse(flattener.canFlatten(new Object()));
  }

  @Test
  public void testCheckUnFlattanableFalse() {
    Assert.assertFalse(flattener.canUnFlatten(new Object()));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testFlattanableUnsupported() {
    flattener.flatten(new Object());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testUnFlattanableUnsupported() {
    flattener.unflatten(new Object());
  }
}
