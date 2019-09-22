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

import java.util.Date;

/**
 * Base interface for all things that are identifiable with a unique primary key, with a non-business-key, i.e. a simple generated number or so.
 *
 */
public interface Identifiable {

  /**
   * @return the PK
   */
  Long getId();

  Date getCreationTS();
}
