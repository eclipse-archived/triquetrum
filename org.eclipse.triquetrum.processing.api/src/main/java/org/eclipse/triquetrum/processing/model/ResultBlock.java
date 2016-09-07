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
import java.util.stream.Stream;

/**
 * A simple container for <code>ResultItem</code>s, that can be given a "type" identifier.
 * <p>
 * ResultItems should have unique names within their containing ResultBlock.
 * </p>
 */
public interface ResultBlock extends Serializable, Identifiable, AttributeHolder {

  /**
   *
   * @return The resultblock type
   */
  String getType();

  /**
   * Add a result item, potentially replacing an item with a same name in this block.
   *
   * @param item
   * @return the previous item in this block with the same name, if any, or null if no such item was present.
   */
  ResultItem<? extends Serializable> putItem(ResultItem<? extends Serializable> item);

  /**
   * @return all the ResultItems as a Stream.
   */
  Stream<ResultItem<? extends Serializable>> getAllItems();

  /**
   * @param name
   * @return the ResultItem with the given name
   */
  ResultItem<? extends Serializable> getItemForName(String name);

  /**
   * @return parent Task
   */
  Task getTask();
}
