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
package org.eclipse.triquetrum.processing.model;

import org.eclipse.triquetrum.Event;

/**
 * Interface for <code>Event</code>s associated to processing a <code>Task</code> or <code>Process</code>.
 * <p>
 * These can correspond to state transitions, or to arbitrary actions for which someone thought it was important to notify listeners and/or store execution
 * traces.
 * </p>
 */
public interface ProcessingEvent<T> extends Event, Identifiable, Comparable<ProcessingEvent<T>> {

  /**
   *
   * @return the associated object on which this event occurred
   */
  T getContext();

  /**
   * @return the event message, typically empty except for ERROR events, where error info is then stored in here.
   */
  String getMessage();
}