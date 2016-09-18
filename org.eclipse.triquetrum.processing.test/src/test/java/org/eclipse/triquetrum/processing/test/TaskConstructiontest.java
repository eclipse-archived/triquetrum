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
package org.eclipse.triquetrum.processing.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.eclipse.triquetrum.processing.model.Attribute;
import org.eclipse.triquetrum.processing.model.ResultBlock;
import org.eclipse.triquetrum.processing.model.ResultItem;
import org.eclipse.triquetrum.processing.model.Task;
import org.eclipse.triquetrum.processing.model.TriqFactory;
import org.eclipse.triquetrum.processing.model.TriqFactoryTracker;
import org.eclipse.triquetrum.processing.model.impl.TriqFactoryImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Some trivial unit tests for the construction of tasks and related entities.
 * <p>
 * This also shows some simple code examples for such entity construction.
 * </p>
 */
public class TaskConstructiontest {

  @Before
  public void setUp() throws Exception {
    TriqFactoryTracker.setDefaultFactory(new TriqFactoryImpl());
  }

  /**
   * Test the basic functioning of the TriqFactoryTracker
   */
  @Test
  public void testTriqFactoryNotNull() {
    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();

    assertNotNull("Factory not correctly set in TriqFactoryTracker", triqFactory);
  }

  /**
   * Test the construction of a main Task with its principal properties
   */
  @Test
  public void testMainTaskConstruction() {
    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");

    assertNotNull("Task not correctly created", t);
    assertEquals("Initiator not correctly set", "testInitiator", t.getInitiator());
    assertEquals("Type not correctly set", "testType", t.getType());
    assertEquals("CorrelationId not correctly set", "testCorrelationId", t.getCorrelationId());
    assertEquals("ExternalRef not correctly set", "testExternalRef", t.getExternalReference());
  }

  /**
   * Test the construction of a sub task, linked to a main task.
   */
  @Test
  public void testSubTaskConstruction() {
    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task mainTask = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Task t = triqFactory.createTask(mainTask, "testInitiator2", "testType2", "testCorrelationId2", "testExternalRef2");

    assertNotNull("SubTask not correctly created", t);
    assertEquals("Parent task not correctly set", mainTask, t.getParentTask());
    assertEquals("Parent task not correctly set", t, mainTask.getSubTasks().findFirst().orElse(null));
    assertEquals("Initiator not correctly set", "testInitiator2", t.getInitiator());
    assertEquals("Type not correctly set", "testType2", t.getType());
    assertEquals("CorrelationId not correctly set", "testCorrelationId2", t.getCorrelationId());
    assertEquals("ExternalRef not correctly set", "testExternalRef2", t.getExternalReference());
  }

  /**
   * Test the construction of a simple string attribute on a task
   */
  @Test
  public void testStringAttributeConstruction() {
    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Attribute<String> attr = triqFactory.createAttribute(t, "testName", "testValue");

    assertNotNull("Attribute not correctly created", attr);
    assertEquals("Name not correctly set", "testName", attr.getName());
    assertEquals("Value not correctly set", "testValue", attr.getValue());
    assertEquals("Attribute not found in task", attr, t.getAttribute("testName"));
  }

  /**
   * Test the construction of a simple double attribute on a task
   */
  @Test
  public void testDoubleAttributeConstruction() {
    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Double value = new Double(1.0);
    Attribute<Double> attr = triqFactory.createAttribute(t, "testName", value);

    assertNotNull("Attribute not correctly created", attr);
    assertEquals("Name not correctly set", "testName", attr.getName());
    assertEquals("Value not correctly set", value, attr.getValue());
    assertEquals("Attribute not found in task", attr, t.getAttribute("testName"));
  }

  // from here on, there are tests related to constructing results of tasks

  /**
   * Test the construction of a result block for a given task.
   */
  @Test
  public void testResultBlockConstruction() {
    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    ResultBlock resultBlock = triqFactory.createResultBlock(t, "testType");

    assertNotNull("ResultBlock not correctly created", resultBlock);
    assertEquals("Type not correctly set", "testType", resultBlock.getType());
    assertEquals("ResultBlock not found in task", resultBlock, t.getResults().findFirst().orElse(null));
  }

  /**
   * Test the construction of simple string-based result item in a result block.
   */
  @Test
  public void testStringResultItemConstruction() {
    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    ResultBlock resultBlock = triqFactory.createResultBlock(null, "testType");
    ResultItem<String> resultItem = triqFactory.createResultItem(resultBlock, "testName", "testValue");

    assertNotNull("ResultItem not correctly created", resultItem);
    assertEquals("Name not correctly set", "testName", resultItem.getName());
    assertEquals("Value not correctly set", "testValue", resultItem.getValue());
    assertEquals("ResultItem not found in result block", resultItem, resultBlock.getItemForName("testName"));
  }

  /**
   * Test the construction of simple double-valued result item in a result block.
   */
  @Test
  public void testDoubleResultItemConstruction() {
    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    ResultBlock resultBlock = triqFactory.createResultBlock(null, "testType");
    Double value = new Double(1.0);
    ResultItem<Double> resultItem = triqFactory.createResultItem(resultBlock, "testName", value);

    assertNotNull("ResultItem not correctly created", resultItem);
    assertEquals("Name not correctly set", "testName", resultItem.getName());
    assertEquals("Value not correctly set", value, resultItem.getValue());
    assertEquals("ResultItem not found in result block", resultItem, resultBlock.getItemForName("testName"));
  }

}
