/*******************************************************************************
 * Copyright (c) 2015-2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
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
