package org.eclipse.triquetrum.processing.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
  public void testWithoutTaskProcessingService() {
    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");

    try {
      CompletableFuture<Task> future = TaskProcessingBrokerTracker.getBroker().process(t, 3L, TimeUnit.SECONDS);
      future.get();
      fail("Should get a TASK_UNHANDLED ProcessingException");
    } catch (ProcessingException e) {
      assertEquals("Should get a TASK_UNHANDLED ProcessingException", ErrorCode.TASK_UNHANDLED, e.getErrorCode());
    } catch (Exception e) {
      fail("Exception in task processing "+e);
    }
  }

  @Test
  public void testWithMatchingTaskProcessingService() {
    TaskProcessingBrokerTracker.getBroker().registerService(new MockTaskProcessingService("testType", null));

    TriqFactory triqFactory = TriqFactoryTracker.getDefaultFactory();
    Task t = triqFactory.createTask(null, "testInitiator", "testType", "testCorrelationId", "testExternalRef");

    try {
      CompletableFuture<Task> future = TaskProcessingBrokerTracker.getBroker().process(t, 3L, TimeUnit.SECONDS);
      Task tDone = future.get();
      assertEquals("Should get same task back after execution", t,  tDone);
      assertEquals("Task status should be FINISHED",  ProcessingStatus.FINISHED, tDone.getStatus());
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
}
