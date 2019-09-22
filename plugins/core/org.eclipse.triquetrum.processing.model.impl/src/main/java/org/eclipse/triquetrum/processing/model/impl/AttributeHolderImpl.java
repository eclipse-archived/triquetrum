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
package org.eclipse.triquetrum.processing.model.impl;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.eclipse.triquetrum.processing.model.Attribute;
import org.eclipse.triquetrum.processing.model.AttributeHolder;

class AttributeHolderImpl implements AttributeHolder {

  private Map<String, Attribute<? extends Serializable>> attributes = new ConcurrentHashMap<>();

  @Override
  public Attribute<? extends Serializable> getAttribute(String name) {
    return attributes.get(name);
  }

  @Override
  public Attribute<? extends Serializable> putAttribute(Attribute<? extends Serializable> attribute) {
    return attributes.put(attribute.getName(), attribute);
  }

  @Override
  public Stream<String> getAttributeNames() {
    return attributes.keySet().stream();
  }

  @Override
  public Stream<Attribute<? extends Serializable>> getAttributes() {
    return attributes.values().stream();
  }
}
