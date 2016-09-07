/*******************************************************************************
 * Copyright (c) 2015 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
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