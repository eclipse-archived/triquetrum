/*******************************************************************************
 * Copyright (c) 2016,2019 iSencia Belgium NV.
 *  
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.processing.service;

/**
 * A simple solution to easily allow access to the single {@link TaskProcessingBroker} implementation instance that should be available in a Triquetrum runtime.
 * <p>
 * This avoids the complexity where everything that needs to access the broker instance should implement one of the OSGi service retrieval methods (Activators
 * with ServiceTrackers, DS, ...).
 * </p>
 * <p>
 * It also allows an easy access from places where the DS component model can not be applied (e.g. from inside Actors whose creation life cycle can not be under
 * the control of DS).
 * </p>
 */
public class TaskProcessingBrokerTracker {

  // the configured broker instance for active runtime
  private static TaskProcessingBroker broker = null;

  /**
   * This method provides the ease-of-access on the runtime's broker instance.
   *
   * @return the single registered broker instance of this Triquetrum runtime.
   */
  public static TaskProcessingBroker getBroker() {
    return broker;
  }

  /**
   * Set the broker instance for this Triquetrum runtime.
   * <p>
   * This method can be invoked e.g. from an activation method of the broker instance, invoked by DS when the instance is created as a DS component.
   * </p>
   *
   * @param broker
   */
  public static void setBroker(TaskProcessingBroker broker) {
    TaskProcessingBrokerTracker.broker = broker;
  }

  /**
   * Unsets the broker instance for this Triquetrum runtime.
   * <p>
   * This method can be invoked e.g. from a deactivation method of the broker instance, invoked by DS when the instance is destroyed as a DS component.
   * </p>
   * Post-condition of this method is either a null default broker instance (if the given broker matches the current default one), or the unchanged instance, if
   * the given broker was not set as the default one.
   *
   * @param broker
   */
  public static void unsetBroker(TaskProcessingBroker broker) {
    if ((TaskProcessingBrokerTracker.broker != null) && (TaskProcessingBrokerTracker.broker == broker)) {
      TaskProcessingBrokerTracker.broker = null;
    }
  }
}
