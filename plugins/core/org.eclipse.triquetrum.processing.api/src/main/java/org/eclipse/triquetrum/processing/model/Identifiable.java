/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
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
