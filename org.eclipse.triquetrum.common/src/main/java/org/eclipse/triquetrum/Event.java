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

package org.eclipse.triquetrum;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

/**
 * Events for Triquetrum are light-weight objects that can e.g. be used for high-throughput CEP scenarios.
 * <p>
 * This implies :
 * <ul>
 * <li>They have a (start) timestamp</li>
 * <li>They have an optional duration (i.e. could be 0)</li>
 * <li>They have a topic, just because that's easy to differentiate them, refer to them etc</li>
 * <li>They are not persisted entities or anything heavy like that...</li>
 * <li>It should be straightforward to transform them to/from OSGi's <code>org.osgi.service.event.Event</code>,  JMX's <code>javax.management.Notification</code> etc.</li>
 * </ul>
 * </p>
 * Implementations can add "content" as necessary, besides the topic and time info. <br/>
 * 
 */
public interface Event extends Serializable {
  
  public final static String TOPIC_PREFIX = "org/eclipse/triquetrum/";
  public final static String SUBJECT = "org.eclipse.triquetrum.SUBJECT";

  /**
   * @return the event's fully-specified topic
   */
  String getTopic();
  
  /**
   * @return the creation timestamp of the event
   */
  Date getCreationTS();

  /**
   * When this event represents a temporary "situation", the duration identifies the time (in ms), from the creationTS, that the situation will remain the same.
   * For the vast majority of items, this will be irrelevant, in which case it will just return 0.
   * 
   * @return the duration in ms
   */
  Long getDuration();
  
  String getProperty(String propName);
  
  Iterator<String> getPropertyNames();
}
