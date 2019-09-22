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

import java.io.Serializable;

/**
 * A generic container for named values, where values can be of any Serializable type.
 *
 */
public interface NamedValue<V extends Serializable> extends Serializable, Comparable<NamedValue<V>> {

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
   * @return a textual name of the contained data type, that should be consistent with the actual type of the specified generic V.
   */
  DataType getDataType();

  /**
   *
   * @return the value in a String-representation
   */
  String getValueAsString();
}
