/*******************************************************************************
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.shapes;


/**
 * A simple solution to easily allow access to the single {@link TriqGraphicsAlgorithmRendererFactory}
 * implementation instance that should be available in a Triquetrum runtime.
 * <p>
 * This avoids the complexity where everything that needs to access the factory instance
 * should implement one of the OSGi service retrieval methods (Activators with ServiceTrackers, DS, ...).
 * </p>
 * <p>
 * It also allows an easy access from places where the DS component model can not be applied
 * (e.g. from inside Actors whose creation life cycle can not be under the control of DS).
 * </p>
 */
public class TriqGarFactorytracker {

  // the configured factory instance for active runtime
  private static TriqGraphicsAlgorithmRendererFactory factory = null;

  /**
   * This method provides the ease-of-access on the runtime's factory instance.
   *
   * @return the single registered factory instance of this Triquetrum runtime.
   */
  public static TriqGraphicsAlgorithmRendererFactory getGarFactory() {
    return TriqGarFactorytracker.factory;
  }

  /**
   * Set the factory instance for this Triquetrum runtime.
   * <p>
   * This method can be invoked e.g. from an activation method of the factory instance,
   * invoked by DS when the instance is created as a DS component.
   * </p>
   *
   * @param factory
   */
  public static void setGarFactory(TriqGraphicsAlgorithmRendererFactory factory) {
    TriqGarFactorytracker.factory = factory;
  }
}
