package org.eclipse.triquetrum.workflow.execution.test;

import java.util.concurrent.TimeUnit;

import org.eclipse.triquetrum.workflow.ProcessHandle;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService.StartMode;
import org.eclipse.triquetrum.workflow.execution.impl.WorkflowExecutionServiceImpl;
import org.ptolemy.testsupport.ModelExecutionAssertion;
import org.ptolemy.testsupport.TestUtilities;

import junit.framework.TestCase;
import ptolemy.actor.TypedCompositeActor;
import ptolemy.actor.lib.Const;
import ptolemy.actor.lib.Recorder;
import ptolemy.domains.sdf.kernel.SDFDirector;

public class WorkflowExecutionTest extends TestCase {

  public static WorkflowExecutionService processingService;

  @Override
  protected void setUp() throws Exception {
    processingService = new WorkflowExecutionServiceImpl(5);
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public final void testStartAndCheckProcessHandle() throws Exception {
    TypedCompositeActor model = new TypedCompositeActor();
    model.setName("testStartAndCheckProcessHandle");
    new SDFDirector(model, "director");
    Const c = new Const(model, "const");
    c.value.setToken("\"hello\"");
    c.firingCountLimit.setToken("1");
    Recorder r = new Recorder(model,"sink");
    model.connect(c.output, r.input);

    // use DEBUG mode to make sure execution statistics are collected, needed for the ModelExecutionAssertion at the end
    ProcessHandle procHandle = processingService.start(StartMode.DEBUG, model, null, null, null);
    assertNotNull("Process handle must be not-null", procHandle);
    assertNotNull("Process handle must have a non-null process context ID", procHandle.getProcessId());
    assertNotNull("Process status must be not-null", procHandle.getExecutionStatus());
    assertNotNull("Process's model must be not-null", procHandle.getModelHandle());
    assertEquals("Process's model code must be as defined", "testStartAndCheckProcessHandle", procHandle.getModelHandle().getCode());

    processingService.waitUntilFinished(procHandle, 10, TimeUnit.MINUTES);
    
    new ModelExecutionAssertion()
    .expectActorIterationCount(c, 1L)
    .expectActorIterationCount(r, 1L)
    .expectMsgReceiptCount(r.input, 1L)
    .expectMsgSentCount(c.output, 1L)
    .assertModel(procHandle.getModelHandle().getModel());
  }
}
