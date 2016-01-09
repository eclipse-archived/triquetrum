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

import org.eclipse.triquetrum.processing.model.Attribute;
import org.eclipse.triquetrum.processing.model.AttributeHolder;
import org.eclipse.triquetrum.processing.model.ProcessingEvent;
import org.eclipse.triquetrum.processing.model.ResultBlock;
import org.eclipse.triquetrum.processing.model.ResultItem;
import org.eclipse.triquetrum.processing.model.Task;
import org.eclipse.triquetrum.processing.model.TriqFactory;

public class TriqFactoryImpl implements TriqFactory {

  public TriqFactoryImpl() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public <V extends Serializable> Attribute<V> createAttribute(AttributeHolder holder, String name, V value) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Task createTask(Task parentTask, String initiator, String type) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <T> ProcessingEvent<T> createContextEvent(T context, String topic, String message) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResultBlock createResultBlock(Task task, String type) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <V extends Serializable> ResultItem<V> createResultItem(ResultBlock resultBlock, String name, V value) {
    // TODO Auto-generated method stub
    return null;
  }

}
