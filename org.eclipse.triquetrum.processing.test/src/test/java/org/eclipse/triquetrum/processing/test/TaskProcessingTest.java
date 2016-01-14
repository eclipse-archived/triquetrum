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
package org.eclipse.triquetrum.processing.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.eclipse.triquetrum.processing.ErrorCode;
import org.eclipse.triquetrum.processing.ProcessingException;
import org.eclipse.triquetrum.processing.model.ProcessingStatus;
import org.eclipse.triquetrum.processing.model.ResultBlock;
import org.eclipse.triquetrum.processing.model.ResultItem;
import org.eclipse.triquetrum.processing.model.Task;
import org.eclipse.triquetrum.processing.model.TriqFactory;
import org.eclipse.triquetrum.processing.model.TriqFactoryTracker;
import org.eclipse.triquetrum.processing.model.impl.TriqFactoryImpl;
import org.eclipse.triquetrum.processing.service.TaskProcessingBrokerTracker;
import org.eclipse.triquetrum.processing.service.impl.DefaultTaskProcessingBroker;
import org.junit.Before;
import org.junit.Test;

public class TaskProcessingTest {

  @Before
  public void setUp() throws Exception {
    TriqFactoryTracker.setDefaultFactory(new TriqFactoryImpl());
    DefaultTaskProcessingBroker broker = new DefaultTaskProcessingBroker();
    broker.activate(null, new HashMap<>());
    TaskProcessingBrokerTracker.setBroker(broker);
  }

  @Test
  public void testWithMatchingTaskProcessingService() {
    TaskProcessingBrokerTracker.getBroker().registerService(new MockTaskProcessingService("testType", null));

    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");

    try {
      CompletableFuture<Task> future = TaskProcessingBrokerTracker.getBroker().process(t, 3L, TimeUnit.SECONDS);
      // Normally this double timeout spec (the process() invocation and this get() invocation) should not be necessary.
      // The service implementation picked by the broker should have correct timeout-handling itself.
      // But you never know... and we don't want our unit tests to block forever.
      Task tDone = future.get(3L, TimeUnit.SECONDS);
      assertEquals("Should get same task back after execution", t,  tDone);
      assertEquals("Task status should be FINISHED",  ProcessingStatus.FINISHED, tDone.getStatus());
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception in task processing "+e);
    }
  }

  @Test
  public void testWithoutTaskProcessingService() {
    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");

    try {
      CompletableFuture<Task> future = TaskProcessingBrokerTracker.getBroker().process(t, 3L, TimeUnit.SECONDS);
      future.get(3L, TimeUnit.SECONDS);
      fail("Should get a TASK_UNHANDLED ProcessingException");
    } catch (ExecutionException e) {
      Throwable cause = e.getCause();
      assertEquals("Should get a TASK_UNHANDLED ProcessingException", ProcessingException.class, cause.getClass());
      assertEquals("Should get a TASK_UNHANDLED ProcessingException", ErrorCode.TASK_UNHANDLED, ((ProcessingException)e.getCause()).getErrorCode());
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception in task processing "+e);
    }
  }

  @Test
  public void testTaskProcessingWithResults() {
    Map<String, Serializable> resultItems = new HashMap<>();
    resultItems.put("item1", "value1");
    resultItems.put("item2", 10L);
    TaskProcessingBrokerTracker.getBroker().registerService(new MockTaskProcessingService("testType", resultItems));

    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");

    try {
      CompletableFuture<Task> future = TaskProcessingBrokerTracker.getBroker().process(t, 3L, TimeUnit.SECONDS);
      Task tDone = future.get();
      Stream<ResultBlock> results = tDone.getResults();
      assertNotNull("Task results stream must be not null", results);
      ResultBlock resultBlock = results.findFirst().orElse(null);
      assertNotNull("Task result must be not null", resultBlock);
      assertEquals("ResultBlock type must be testType","testType", resultBlock.getType());

      ResultItem<? extends Serializable> item1 = resultBlock.getItemForName("item1");
      assertNotNull("Result item1 should be not null", item1);
      assertEquals("Result item1 should have value == value1", "value1", item1.getValue());

      ResultItem<? extends Serializable> item2 = resultBlock.getItemForName("item2");
      assertNotNull("Result item2 should be not null", item2);
      assertEquals("Result item2 should have value == 10L", 10L, item2.getValue());
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception in task processing "+e);
    }
  }

  @Test
  public void testTaskProcessingWithError() {
    Map<String, Serializable> resultItems = new HashMap<>();
    resultItems.put("item1", "value1");
    resultItems.put("item2", 10L);
    TaskProcessingBrokerTracker.getBroker().registerService(new MockTaskProcessingService("testType", resultItems));

    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    triqFactory.createAttribute(t, MockTaskProcessingService.EXCEPTION_CLASS_ATTRIBUTE, "java.lang.IllegalStateException");

    try {
      CompletableFuture<Task> future = TaskProcessingBrokerTracker.getBroker().process(t, 3L, TimeUnit.SECONDS);
      future.get();
      fail("Task processing should have caused an IllegalStateException");
    } catch (ExecutionException e) {
      assertEquals("Should be an IllegalStateException", IllegalStateException.class, e.getCause().getClass());
      Stream<ResultBlock> results = t.getResults();
      assertNotNull("Task results stream must be not null", results);
      ResultBlock resultBlock = results.findFirst().orElse(null);
      assertNull("Task result must be empty/null", resultBlock);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception in task processing "+e);
    }
  }

  @Test
  public void testTaskSequenceProcessing() {
    Map<String, Serializable> resultItems = new HashMap<>();
    resultItems.put("item1", "value1");
    resultItems.put("item2", 10L);
    TaskProcessingBrokerTracker.getBroker().registerService(new MockTaskProcessingService("testType", resultItems));

    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();

    // Chained task processing typically represents the handling of a complex "main" task, that can be split up in a sequence of smaller "sub" tasks.
    // TaskProcessingService implementations may need to refer to results of one-or-more previous tasks in the chain/sequence.
    // This can be achieved by passing via a new task's parent task, to find the (other) sub tasks that must be consulted (e.g. to read their results).
    // So even though for this unit test, we don't really need a main task, we show the construction here as a typical code example.
    Task mainTask = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Task t1 = triqFactory.createTask(mainTask, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Task t2 = triqFactory.createTask(mainTask, "testInitiator2", "testType", "testCorrelationId2", "testExternalRef2");

    try {
      // This gives an example of chaining task executions asynchronously.
      CompletableFuture<Task> future1 = TaskProcessingBrokerTracker.getBroker().process(t1, 3L, TimeUnit.SECONDS);
      CompletableFuture<Task> future2 = future1.
             // This is just to check the functioning of chained future APIs ;-)
             // thenAccept(t -> assertEquals("Task t1 status should be FINISHED",  ProcessingStatus.FINISHED, t1.getStatus())).

             // This is the real task chaining that we want to test.
             // Remark that the process method takes the "new" task as input, not the task resulting from the previous invocation.
             // So the "t" parameter just is there for syntactic reasons.
             thenCompose(t -> TaskProcessingBrokerTracker.getBroker().process(t2, 3L, TimeUnit.SECONDS)
        );
      Task tDone1 = future1.get(3L, TimeUnit.SECONDS);
      Task tDone2 = future2.get(3L, TimeUnit.SECONDS);
      assertEquals("Should get same task t1 back after execution", t1,  tDone1);
      assertEquals("Should get same task t2 back after execution", t2,  tDone2);
      assertEquals("Task t1 status should be FINISHED",  ProcessingStatus.FINISHED, t1.getStatus());
      assertEquals("Task t2 status should be FINISHED",  ProcessingStatus.FINISHED, t2.getStatus());
      // TODO evaluate if we need to implement and test automated FINISHED status setting for the main task, once all it's subtasks are done.
      // I think this would be the correct behaviour, and it could be implemented by having a parent task listening on status changes of it's sub tasks.
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception in task processing "+e);
    }
  }
}
