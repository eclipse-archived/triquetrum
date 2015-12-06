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

import java.io.Serializable;

/**
 * A generic container for named values, where values can be of any Serializable type.
 * 
 */
public interface NamedValue<V extends Serializable> extends Serializable {

  /**
  * 
  * @return the name
  */
  String getName();

  /**
   * 
   * @return the value in its "raw" type/format
   */
  V getValue();
  
  /**
   * 
   * @return a textual name of the contained data type, that should be
   *         consistent with the actual type of the specified generic V.
   */
  DataType getDataType();

  /**
   * 
   * @return the value in a String-representation
   */
  String getValueAsString();
}
