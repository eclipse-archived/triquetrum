/*******************************************************************************
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.processing.actor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;

import org.eclipse.triquetrum.TriqException;
import org.eclipse.triquetrum.processing.ErrorCode;
import org.eclipse.triquetrum.processing.actor.util.AttributeFromTokenBuilder;
import org.eclipse.triquetrum.processing.model.ProcessingErrorEvent;
import org.eclipse.triquetrum.processing.model.Task;
import org.eclipse.triquetrum.processing.model.TriqFactory;
import org.eclipse.triquetrum.processing.model.TriqFactoryTracker;
import org.eclipse.triquetrum.processing.service.TaskProcessingBrokerTracker;
import org.eclipse.triquetrum.processing.service.TaskProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.actor.TypedAtomicActor;
import ptolemy.actor.TypedIOPort;
import ptolemy.data.LongToken;
import ptolemy.data.RecordToken;
import ptolemy.data.Token;
import ptolemy.data.expr.ConversionUtilities;
import ptolemy.data.expr.Parameter;
import ptolemy.data.expr.StringParameter;
import ptolemy.data.type.BaseType;
import ptolemy.data.type.RecordType;
import ptolemy.data.type.Type;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;

/**
 * An actor that maps tokens received on input ports to task attributes, and task results to tokens sent on output ports.
 * <p>
 * Each fire iteration creates and submits a new Task instance. Input port names are used as the Task's Attribute names, and the values of the Tokens received
 * are assigned to correspondingly typed attribute values. Conversely, output ports whose names correspond to a Task's result items will send out Tokens with
 * the values of those corresponding items, and matching the type of those result values.
 * </p>
 * <p>
 * Remark that ports must have unique names for an actor, so this approach implies that attributes and result items must all have unique names!
 * </p>
 * TODO check about how to implement mandatory vs optional inputs. For now, all ports are assumed to have a token available for each iteration.
 * <p>
 * In a later phase, we should be able to maintain a registry of known task types, and they should have specifications of inputs & outputs from which we could
 * auto-create the corresponding ports. For now the user needs to manage that manually.
 * </p>
 */
public class TaskBasedActor extends TypedAtomicActor {
  private static final Logger LOGGER = LoggerFactory.getLogger(TaskBasedActor.class);

  private static final String ERROR_RECORD_DESCRIPTION = "description";
  private static final String ERROR_RECORD_ERROR_SEVERITY = "errorSeverity";
  private static final String ERROR_RECORD_ERROR_CODE = "errorCode";
  private static final String ERROR_RECORD_SHORT_DESCRIPTION = "shortDescription";
  private static final String ERROR_RECORD_TASK_ID = "taskId";

  private static final long DEFAULT_TIMEOUT = 5L;

  /**
   * Defines the error record token structure.
   */
  private static final RecordType ERROR_RECORDTYPE = new RecordType(
      new String[] { TaskBasedActor.ERROR_RECORD_TASK_ID, TaskBasedActor.ERROR_RECORD_ERROR_CODE, TaskBasedActor.ERROR_RECORD_ERROR_SEVERITY,
          TaskBasedActor.ERROR_RECORD_SHORT_DESCRIPTION, TaskBasedActor.ERROR_RECORD_DESCRIPTION },
      new Type[] { BaseType.LONG, BaseType.STRING, BaseType.STRING, BaseType.STRING, BaseType.STRING });

  /**
   * Used to specify the task type for tasks submitted by the actor instance. This type is a plain text thing that is typically used as main matching criterium
   * by {@link TaskProcessingService} implementations.
   */
  public StringParameter taskType;
  /**
   * Used to specify the timeout value for the execution of a new {@link Task} by the underlying {@link TaskProcessingService}. Typically, values <= 0 are
   * interpreted as : no timeout defined, although it is not considered good practice to skip timeout handling!
   */
  public Parameter timeOutParameter;
  /**
   * Used to specify the {@link TimeUnit} for the timeout handling.
   */
  public StringParameter timeUnitParameter;
  /**
   * The output port that is used to send out error information when a task submission results in an exception. The error information is sent using a
   * {@link RecordToken} with structure as defined in ERROR_RECORDTYPE.
   */
  public TypedIOPort errorPort;

  /**
   * The standard constructor for an actor implementation.
   *
   * @param container
   *          the (sub)model in which this actor must be created.
   * @param name
   * @throws IllegalActionException
   * @throws NameDuplicationException
   */
  public TaskBasedActor(CompositeEntity container, String name) throws IllegalActionException, NameDuplicationException {
    super(container, name);

    errorPort = new TypedIOPort(this, "error", false, true);
    errorPort.setTypeEquals(ERROR_RECORDTYPE);

    taskType = new StringParameter(this, "Task type");

    timeOutParameter = new Parameter(this, "Timeout", new LongToken(TaskBasedActor.DEFAULT_TIMEOUT));
    timeOutParameter.setTypeEquals(BaseType.LONG);

    timeUnitParameter = new StringParameter(this, "Time unit");
    TimeUnit[] timeUnits = TimeUnit.values();
    timeUnitParameter.setExpression(TimeUnit.SECONDS.name());
    for (TimeUnit timeUnit : timeUnits) {
      timeUnitParameter.addChoice(timeUnit.name());
    }
  }

  /**
   * check that all connected ports have a token available
   */
  @Override
  public boolean prefire() throws IllegalActionException {
    boolean result = super.prefire();
    if (result) {
      for (TypedIOPort p : inputPortList()) {
        result &= (p.getWidth() > 0 && p.hasToken(0));
        if (!result) {
          break;
        }
      }
    }
    return result;
  }

  @Override
  public void fire() throws IllegalActionException {
    super.fire();
    TriqFactory factory = TriqFactoryTracker.getDefaultFactory();
    // TODO think about usage for parent task (once we're capable of passing process context IDs around),
    // and about correlation ID and external reference.
    Task task = factory.createTask(null, getFullName(), taskType.stringValue(), null, null);

    for (TypedIOPort p : inputPortList()) {
      AttributeFromTokenBuilder.createAttribute(task, p.getName(), p.get(0));
    }

    // The actual output sending is done when the task has been processed, which is an asynchronous thing!
    // TODO check what this means for Ptolemy II MoC formalisms!
    TaskProcessingBrokerTracker.getBroker().process(task, getTimeOutValue(), TimeUnit.valueOf(timeUnitParameter.stringValue()))
        .exceptionally(ex -> handleError(task, null, ex)).thenAccept(t -> sendOutput(t));
  }

  /**
   * Gets the first result block, if present, and iterates its result items. For each result item, if an output port exists with a matching name, an output
   * token is sent with the item's value.
   * 
   * @param t
   */
  protected final void sendOutput(Task t) {
    t.getResults().findFirst().ifPresent(r -> r.getAllItems().forEach(ri -> {
      TypedIOPort p = (TypedIOPort) getPort(ri.getName());
      if (p != null) {
        try {
          p.send(0, ConversionUtilities.convertJavaTypeToToken(ri.getValue()));
        } catch (Exception e) {
          handleError(t, "Error sending output for result item " + ri, e);
        }
      }
    }));
  }

  protected final Task handleError(Task t, Object detailedContext, Throwable ex) {
    if (ex instanceof CompletionException) {
      ex = ex.getCause();
    }

    String description = null;
    TriqFactory factory = TriqFactoryTracker.getDefaultFactory();
    ProcessingErrorEvent<Task> errorEvent = null;
    if (ex instanceof TriqException) {
      TriqException triqEx = (TriqException) ex;
      description = (detailedContext != null ? detailedContext.toString() : (triqEx.getSimpleMessage() != null ? triqEx.getSimpleMessage() : ""));
      errorEvent = factory.createErrorEvent(t, triqEx.getErrorCode(), description, ex, null);
    } else {
      description = (detailedContext != null ? detailedContext.toString() : ex.getMessage());
      errorEvent = factory.createErrorEvent(t, ErrorCode.TASK_ERROR, description, ex, null);
    }

    // TODO evaluate if we want to log all failed tasks?
    LOGGER.error(ErrorCode.TASK_ERROR + " - " + description, ex);

    try {
      Map<String, Token> recordMap = new HashMap<>();
      recordMap.put(TaskBasedActor.ERROR_RECORD_TASK_ID, ConversionUtilities.convertJavaTypeToToken(errorEvent.getContext().getId()));
      recordMap.put(TaskBasedActor.ERROR_RECORD_ERROR_CODE, ConversionUtilities.convertJavaTypeToToken(errorEvent.getCode()));
      recordMap.put(TaskBasedActor.ERROR_RECORD_ERROR_SEVERITY, ConversionUtilities.convertJavaTypeToToken(errorEvent.getSeverity().name()));
      recordMap.put(TaskBasedActor.ERROR_RECORD_SHORT_DESCRIPTION, ConversionUtilities.convertJavaTypeToToken(errorEvent.getShortDescription()));
      recordMap.put(TaskBasedActor.ERROR_RECORD_DESCRIPTION, ConversionUtilities.convertJavaTypeToToken(errorEvent.getDescription()));
      errorPort.send(0, new RecordToken(recordMap));
    } catch (Exception e) {
      LOGGER.error(org.eclipse.triquetrum.ErrorCode.ERROR + " - Error sending error output for task " + t.getId(), e);
    }
    return t;
  }

  /**
   *
   * @return the configured time out
   * @throws IllegalActionException
   *           when the timeOutParameter can not be read
   */
  protected final Long getTimeOutValue() throws IllegalActionException {
    Long timeoutValue = TaskBasedActor.DEFAULT_TIMEOUT;
    if (timeOutParameter.getToken() != null) {
      timeoutValue = ((LongToken) timeOutParameter.getToken()).longValue();
    }
    return timeoutValue;
  }
}
