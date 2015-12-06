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
import java.util.Iterator;
import java.util.Set;


/**
 * Interface for all things that can have a collection of <code>Attribute</code>s.
 * <p>
 * An <code>AttributeHolder</code> by assumption stores the attributes by their name,
 * that should be unique within the holder. I.e. it can only contain one attribute for a given name.
 * </p>
 *
 */
public interface AttributeHolder {
  
	/**
	 * 
	 * @param name should be non-null
	 * @return the attribute with the given name, or null if not found
	 */
  Attribute<? extends Serializable> getAttribute(String name);
  
  /**
   * Associate the attribute with this holder.
   * If the holder already has an attribute with the same name,
   * it will be replaced by this new one,
   * and the previous attribute will be returned.
   * @param attribute should be non-null
   * @return the attribute previously associated with this holder, with a same name as the new attribute.
   * Or null if there was no attribute with the same name. 
   */
  Attribute<? extends Serializable> putAttribute(Attribute<? extends Serializable> attribute);
  
  /**
   * 
   * @return an iterator over the names of all associated attributes of this holder
   */
  Iterator<String> getAttributeNames();
  
  /**
   * 
   * @return a read-only set of all associated attributes
   */
  Set<Attribute<? extends Serializable>> getAttributes();
}
