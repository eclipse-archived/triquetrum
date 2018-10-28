/*******************************************************************************
 * Copyright (c) 2018 Erwin De Ley.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.processing.test;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite accumulator
 */
public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite(AllTests.class.getName());
    //$JUnit-BEGIN$
    suite.addTest(new JUnit4TestAdapter(DataTypeTest.class));
    suite.addTest(new JUnit4TestAdapter(TaskConstructiontest.class));
    suite.addTest(new JUnit4TestAdapter(TaskProcessingTest.class));
    //$JUnit-END$
    return suite;
  }

}
