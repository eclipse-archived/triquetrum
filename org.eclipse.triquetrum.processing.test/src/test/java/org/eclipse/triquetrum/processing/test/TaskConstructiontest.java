package org.eclipse.triquetrum.processing.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.eclipse.triquetrum.processing.model.Attribute;
import org.eclipse.triquetrum.processing.model.Task;
import org.eclipse.triquetrum.processing.model.TriqFactory;
import org.eclipse.triquetrum.processing.model.TriqFactoryTracker;
import org.eclipse.triquetrum.processing.model.impl.TriqFactoryImpl;
import org.junit.Before;
import org.junit.Test;

public class TaskConstructiontest {

  @Before
  public void setUp() throws Exception {
    TriqFactoryTracker.setDefaultFactory(new TriqFactoryImpl());
  }

  @Test
  public void testTriqFactoryNotNull() {
    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();

    assertNotNull("Factory not correctly set in TriqFactoryTracker", triqFactory);
  }

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

  @Test
  public void testStringAttributeConstruction() {
    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Attribute<String> attr = triqFactory.createAttribute(t, "testName", "testValue");

    assertNotNull("Attribute", attr);
    assertEquals("Name not correctly set", "testName", attr.getName());
    assertEquals("Value not correctly set", "testValue", attr.getValue());
    assertEquals("Attribute not found in task", attr, t.getAttribute("testName"));
  }

  @Test
  public void testDoubleAttributeConstruction() {
    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Double value = new Double(1.0);
    Attribute<Double> attr = triqFactory.createAttribute(t, "testName", value);

    assertNotNull("Attribute", attr);
    assertEquals("Name not correctly set", "testName", attr.getName());
    assertEquals("Value not correctly set", value, attr.getValue());
    assertEquals("Attribute not found in task", attr, t.getAttribute("testName"));
  }
}
