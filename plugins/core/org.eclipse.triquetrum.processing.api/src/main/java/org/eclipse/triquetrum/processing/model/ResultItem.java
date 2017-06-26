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

import java.io.Serializable;

/**
 * A <code>ResultItem</code> represents a significant data item that is worth maintaining/storing/analysing/showing, e.g. an individual measurement result or
 * data element obtained from a user-filled form etc.
 * <p>
 * It can be enriched in later processing with <code>Attribute</code>s, can be <code>Coloured</code> etc.
 * </p>
 *
 */
public interface ResultItem<V extends Serializable> extends NamedValue<V>, Identifiable, AttributeHolder {

  /**
   * TODO investigate Measurement APIs to improve this basic unit handling
   *
   * @return an identifier of the physical unit (if any) for the contained value. By preference, this should correspond to some standard units system like SI
   *         etc.
   */
  String getUnit();

  /**
   *
   * @return the parent result block to which this item belongs.
   */
  ResultBlock getResultBlock();
}
