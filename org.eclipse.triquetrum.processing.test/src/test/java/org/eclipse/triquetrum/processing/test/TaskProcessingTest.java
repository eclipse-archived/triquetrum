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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import org.eclipse.triquetrum.ProcessingStatus;
import org.eclipse.triquetrum.processing.ErrorCode;
import org.eclipse.triquetrum.processing.ProcessingException;
import org.eclipse.triquetrum.processing.model.DataType;
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

/**
 * Unit tests for processing one or more tasks via the TaskProcessingBroker.
 * <p>
 * The tests also show some basic patterns for task creation and submission.
 * </p>
 */
public class TaskProcessingTest {

  @Before
  public void setUp() throws Exception {
    TriqFactoryTracker.setDefaultFactory(new TriqFactoryImpl());
    DefaultTaskProcessingBroker broker = new DefaultTaskProcessingBroker();
    broker.activate(null, new HashMap<>());
    TaskProcessingBrokerTracker.setBroker(broker);
  }

  /**
   * This is the simplest illustration of a working case for processing a task :
   * - there's a (mock) implementation of a TaskProcessingService that accepts to process the task
   * - we don't care about results yet, we just want to make sure the Task status is FINISHED afterwards.
   */
  @Test
  public void testWithMatchingTaskProcessingService() {
    TaskProcessingBrokerTracker.getBroker().registerService(new MockTaskProcessingService("testType", null));

    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");

    try {
      // The process() api assumes the case of asynchronous task processing.
      // Using the future, the caller can decide if/when the actual processing result is of interest
      // and can then access it at the best moment.
      CompletableFuture<Task> future = TaskProcessingBrokerTracker.getBroker().process(t, 3L, TimeUnit.SECONDS);
      // Normally this double timeout spec (the process() invocation and this get() invocation) should not be necessary.
      // The service implementation picked by the broker should have correct timeout-handling itself.
      // Furthermore the MockTaskProcessingService processes the task synchronously, so the task is already finished when the process method returns.
      // But you never know... and we don't want our unit tests to block forever.
      Task tDone = future.get(3L, TimeUnit.SECONDS);
      // The processing API should not create other instances of the submitted task,
      // but should just return the submitted task with an updated status.
      assertEquals("Should get same task back after execution", t,  tDone);
      assertEquals("Task status should be FINISHED",  ProcessingStatus.FINISHED, tDone.getStatus());
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception in task processing "+e);
    }
  }

  /**
   * This tests an error situation, where the TaskProcessingService present is not able/willing to process the submitted task.
   */
  @Test
  public void testNoMatchingTaskProcessingService() {
    TaskProcessingBrokerTracker.getBroker().registerService(new MockTaskProcessingService("someOtherType", null));

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

  /**
   * This tests an error situation, where there is no TaskProcessingService present at all.
   */
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

  /**
   * This test goes a step further with its task processing, the service implementation also adds some basic results.
   */
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
      // first check that there's a result block as expected
      Stream<ResultBlock> results = tDone.getResults();
      assertNotNull("Task results stream must be not null", results);
      ResultBlock resultBlock = results.findFirst().orElse(null);
      assertNotNull("Task result must be not null", resultBlock);
      assertEquals("ResultBlock type must be testType","testType", resultBlock.getType());

      // also check the individual items, and that they have the right value and type
      ResultItem<? extends Serializable> item1 = resultBlock.getItemForName("item1");
      assertNotNull("Result item1 should be not null", item1);
      assertEquals("Result item1 should have value == value1", "value1", item1.getValue());
      assertEquals("Result item1 should have a DataType STRING", DataType.STRING, item1.getDataType());

      ResultItem<? extends Serializable> item2 = resultBlock.getItemForName("item2");
      assertNotNull("Result item2 should be not null", item2);
      assertEquals("Result item2 should have value == 10L", 10L, item2.getValue());
      assertEquals("Result item2 should have a DataType LONG", DataType.LONG, item2.getDataType());
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception in task processing "+e);
    }
  }

  /**
   * This test checks what should happen when the task processing fails with some exception thrown inside the service implementation.
   */
  @Test
  public void testTaskProcessingWithError() {
    Map<String, Serializable> resultItems = new HashMap<>();
    resultItems.put("item1", "value1");
    resultItems.put("item2", 10L);
    TaskProcessingBrokerTracker.getBroker().registerService(new MockTaskProcessingService("testType", resultItems));

    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    // This is a simple trick to tell the MockTaskProcessingService that it should throw an exception of the specified type.
    // We use the task to pass the requested exception type into the service impl, where the code checks for the presence
    // of such an attribute and then simply throws such an exception.
    triqFactory.createAttribute(t, MockTaskProcessingService.EXCEPTION_CLASS_ATTRIBUTE, "java.lang.IllegalStateException");

    try {
      CompletableFuture<Task> future = TaskProcessingBrokerTracker.getBroker().process(t, 3L, TimeUnit.SECONDS);
      future.get();
      fail("Task processing should have caused an IllegalStateException");
    } catch (ExecutionException e) {
      // the future reports a processing exception wrapped in an ExecutionException
      assertEquals("Should be an IllegalStateException", IllegalStateException.class, e.getCause().getClass());
      assertEquals("Task should be in error",  ProcessingStatus.ERROR, t.getStatus());
      // we still want to make sure that the result stream is not-null
      Stream<ResultBlock> results = t.getResults();
      assertNotNull("Task results stream must be not null", results);
      // but it should be "empty", i.e. there can be no first element
      ResultBlock resultBlock = results.findFirst().orElse(null);
      assertNull("Task result must be empty/null", resultBlock);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception in task processing "+e);
    }
  }

  /**
   * This test checks what should happen when the task processing fails with some exception thrown inside the service implementation.
   */
  @Test
  public void testTaskProcessingWithTimeout() {
    Map<String, Serializable> resultItems = new HashMap<>();
    resultItems.put("item1", "value1");
    resultItems.put("item2", 10L);
    TaskProcessingBrokerTracker.getBroker().registerService(new MockTaskProcessingService("testType", resultItems));

    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    // This is a simple trick to tell the MockTaskProcessingService that it should throw an exception of the specified type.
    // We use the task to pass the requested exception type into the service impl, where the code checks for the presence
    // of such an attribute and then simply throws such an exception.
    triqFactory.createAttribute(t, MockTaskProcessingService.DELAY_ATTRIBUTE, 1);

    try {
      CompletableFuture<Task> future = TaskProcessingBrokerTracker.getBroker().process(t, 100L, TimeUnit.MILLISECONDS);
      future.get();
      fail("Task processing should have caused an error through timeout");
    } catch (ExecutionException e) {
      // the future reports a processing exception wrapped in an ExecutionException
      Throwable cause = e.getCause();
      assertEquals("Should be a TimeOutException", TimeoutException.class, cause.getClass());
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception in task processing "+e);
    }
  }

  /**
   * This test exploits the features of CompletableFuture to process 2 tasks in an asynchronous chain/sequence.
   */
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
    // So even though we don't really need a main task for this unit test, we show the construction here as a typical code example.
    Task mainTask = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Task t1 = triqFactory.createTask(mainTask, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Task t2 = triqFactory.createTask(mainTask, "testInitiator2", "testType", "testCorrelationId2", "testExternalRef2");

    try {
      // This gives an example of chaining task executions asynchronously.
      CompletableFuture<Task> future1 = TaskProcessingBrokerTracker.getBroker().process(t1, 3L, TimeUnit.SECONDS);
      CompletableFuture<Task> future2 = future1.
             // This thenAccept is just to check the functioning of chained future APIs ;-)
             // thenAccept(t -> assertEquals("Task t1 status should be FINISHED",  ProcessingStatus.FINISHED, t1.getStatus())).

             // This is the real task chaining that we want to test.
             // Remark that the process method takes the "new" task as input, not the task resulting from the previous invocation.
             // So the "t" parameter is only present for syntactic reasons.
             thenCompose(t -> TaskProcessingBrokerTracker.getBroker().process(t2, 3L, TimeUnit.SECONDS)
        );
      Task tDone1 = future1.get(1L, TimeUnit.SECONDS);
      Task tDone2 = future2.get(1L, TimeUnit.SECONDS);
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
  /**
   * This test exploits the features of CompletableFuture to process 2 concurrent subtasks asynchronously,
   * and set the MainTask to finished when both subtasks are done.
   */
  @Test
  public void testMainAndSubTaskSequenceProcessing1() {
    Map<String, Serializable> resultItems = new HashMap<>();
    resultItems.put("item1", "value1");
    resultItems.put("item2", 10L);
    TaskProcessingBrokerTracker.getBroker().registerService(new MockTaskProcessingService("testType", resultItems));

    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();

    // Chained task processing typically represents the handling of a complex "main" task, that can be split up in a sequence of smaller "sub" tasks.
    // TaskProcessingService implementations may need to refer to results of one-or-more previous tasks in the chain/sequence.
    // This can be achieved by passing via a new task's parent task, to find the (other) sub tasks that must be consulted (e.g. to read their results).
    // So even though we don't really need a main task for this unit test, we show the construction here as a typical code example.
    Task mainTask = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Task t1 = triqFactory.createTask(mainTask, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Task t2 = triqFactory.createTask(mainTask, "testInitiator2", "testType", "testCorrelationId2", "testExternalRef2");

    try {
      // This gives an example of chaining task executions asynchronously.
      CompletableFuture<Task> future1 = TaskProcessingBrokerTracker.getBroker().process(t1, 3L, TimeUnit.SECONDS);
      CompletableFuture<Task> future2 = TaskProcessingBrokerTracker.getBroker().process(t2, 3L, TimeUnit.SECONDS);
      CompletableFuture<Void> mainTaskFuture = future2.thenAcceptBoth(future1, (_t1, _t2) -> mainTask.setStatus(ProcessingStatus.FINISHED));
      mainTaskFuture.get(1L, TimeUnit.SECONDS);
      Task tDone1 = future1.get(1L, TimeUnit.MILLISECONDS);
      Task tDone2 = future2.get(1L, TimeUnit.MILLISECONDS);
      assertEquals("Should get same task t1 back after execution", t1,  tDone1);
      assertEquals("Should get same task t2 back after execution", t2,  tDone2);
      assertEquals("Task mainTask status should be FINISHED",  ProcessingStatus.FINISHED, mainTask.getStatus());
      assertEquals("Task t1 status should be FINISHED",  ProcessingStatus.FINISHED, t1.getStatus());
      assertEquals("Task t2 status should be FINISHED",  ProcessingStatus.FINISHED, t2.getStatus());
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception in task processing "+e);
    }
  }
  /**
   * This test exploits the features of CompletableFuture to process 2 concurrent subtasks asynchronously,
   * and set the MainTask to finished when both subtasks are done.
   */
  @Test
  public void testMainAndSubTaskSequenceProcessing2() {
    Map<String, Serializable> resultItems = new HashMap<>();
    resultItems.put("item1", "value1");
    resultItems.put("item2", 10L);
    TaskProcessingBrokerTracker.getBroker().registerService(new MockTaskProcessingService("testType", resultItems));

    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();

    // Chained task processing typically represents the handling of a complex "main" task, that can be split up in a sequence of smaller "sub" tasks.
    // TaskProcessingService implementations may need to refer to results of one-or-more previous tasks in the chain/sequence.
    // This can be achieved by passing via a new task's parent task, to find the (other) sub tasks that must be consulted (e.g. to read their results).
    // So even though we don't really need a main task for this unit test, we show the construction here as a typical code example.
    Task mainTask = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Task t1 = triqFactory.createTask(mainTask, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Task t2 = triqFactory.createTask(mainTask, "testInitiator2", "testType", "testCorrelationId2", "testExternalRef2");

    try {
      // This gives an example of chaining task executions asynchronously.
      CompletableFuture<List<Task>> future = TaskProcessingBrokerTracker.getBroker().process(3L, TimeUnit.SECONDS, t1, t2);
      CompletableFuture<Void> mainTaskFuture = future.thenAccept(taskList -> mainTask.setStatus(ProcessingStatus.FINISHED));
      mainTaskFuture.get(1L, TimeUnit.SECONDS);
      Task tDone1 = future.get(1L, TimeUnit.MILLISECONDS).get(0);
      Task tDone2 = future.get(1L, TimeUnit.MILLISECONDS).get(1);
      assertEquals("Should get same task t1 back after execution", t1,  tDone1);
      assertEquals("Should get same task t2 back after execution", t2,  tDone2);
      assertEquals("Task mainTask status should be FINISHED",  ProcessingStatus.FINISHED, mainTask.getStatus());
      assertEquals("Task t1 status should be FINISHED",  ProcessingStatus.FINISHED, t1.getStatus());
      assertEquals("Task t2 status should be FINISHED",  ProcessingStatus.FINISHED, t2.getStatus());
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception in task processing "+e);
    }
  }
  @Test
  public void testMainAndSubTaskSequenceProcessing3() {
    Map<String, Serializable> resultItems = new HashMap<>();
    resultItems.put("item1", "value1");
    resultItems.put("item2", 10L);
    TaskProcessingBrokerTracker.getBroker().registerService(new MockTaskProcessingService("testType", resultItems));

    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();

    // Chained task processing typically represents the handling of a complex "main" task, that can be split up in a sequence of smaller "sub" tasks.
    // TaskProcessingService implementations may need to refer to results of one-or-more previous tasks in the chain/sequence.
    // This can be achieved by passing via a new task's parent task, to find the (other) sub tasks that must be consulted (e.g. to read their results).
    // So even though we don't really need a main task for this unit test, we show the construction here as a typical code example.
    Task mainTask = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Task t1 = triqFactory.createTask(mainTask, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Task t2 = triqFactory.createTask(mainTask, "testInitiator2", "testType", "testCorrelationId2", "testExternalRef2");

    try {
      // This gives an example of chaining task executions asynchronously.
      CompletableFuture<Task> future = TaskProcessingBrokerTracker.getBroker().processSubTasks(mainTask, 3L, TimeUnit.SECONDS, true);
      Task mainDone = future.get(3L, TimeUnit.SECONDS);
      assertEquals("Should get same mainTask back after execution", mainTask,  mainDone);
      assertEquals("Task mainTask status should be FINISHED",  ProcessingStatus.FINISHED, mainTask.getStatus());
      mainDone.getSubTasks().forEach(t->assertEquals("Task status should be FINISHED",  ProcessingStatus.FINISHED, t.getStatus()));
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception in task processing "+e);
    }
  }
  @Test
  public void testMainAndSubTaskSequenceProcessing4() {
    Map<String, Serializable> resultItems = new HashMap<>();
    resultItems.put("item1", "value1");
    resultItems.put("item2", 10L);
    TaskProcessingBrokerTracker.getBroker().registerService(new MockTaskProcessingService("testType", resultItems));

    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();

    // Chained task processing typically represents the handling of a complex "main" task, that can be split up in a sequence of smaller "sub" tasks.
    // TaskProcessingService implementations may need to refer to results of one-or-more previous tasks in the chain/sequence.
    // This can be achieved by passing via a new task's parent task, to find the (other) sub tasks that must be consulted (e.g. to read their results).
    // So even though we don't really need a main task for this unit test, we show the construction here as a typical code example.
    Task mainTask = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Task t1 = triqFactory.createTask(mainTask, "testInitiator", "testType", "testCorrelationId", "testExternalRef");
    Task t2 = triqFactory.createTask(mainTask, "testInitiator2", "testType", "testCorrelationId2", "testExternalRef2");

    try {
      // This gives an example of chaining task executions asynchronously.
      CompletableFuture<Task> future = TaskProcessingBrokerTracker.getBroker().processSubTasks(mainTask, 3L, TimeUnit.SECONDS, false);
      Task mainDone = future.get(3L, TimeUnit.SECONDS);
      assertEquals("Should get same mainTask back after execution", mainTask,  mainDone);
      assertNotEquals("Task mainTask status should not be FINISHED",  ProcessingStatus.FINISHED, mainTask.getStatus());
      mainDone.getSubTasks().forEach(t->assertEquals("Task status should be FINISHED",  ProcessingStatus.FINISHED, t.getStatus()));
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception in task processing "+e);
    }
  }
}
