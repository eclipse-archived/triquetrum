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

import java.util.concurrent.TimeUnit;

import org.eclipse.triquetrum.processing.ErrorCode;
import org.eclipse.triquetrum.processing.actor.util.AttributeFromTokenBuilder;
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
import ptolemy.data.expr.ConversionUtilities;
import ptolemy.data.expr.Parameter;
import ptolemy.data.expr.StringParameter;
import ptolemy.data.type.BaseType;
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
 * TODO check about how to implement mandatory vs optional inputs For now, all ports are assumed to have a token available for each iteration.
 * <p>
 * In a later phase, we should be able to maintain a registry of known task types, and they should specifications of inputs & outputs
 * from which we could auto-create the corresponding ports. For now the user needs to manage that manually.
 * </p>
 */
public class TaskBasedActor extends TypedAtomicActor {
  private final static Logger LOGGER = LoggerFactory.getLogger(TaskBasedActor.class);

  private static final long DEFAULT_TIMEOUT = 5L;

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

  public TaskBasedActor(CompositeEntity container, String name) throws IllegalActionException, NameDuplicationException {
    super(container, name);
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

  @Override
  public void initialize() throws IllegalActionException {
    super.initialize();
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
    TaskProcessingBrokerTracker.getBroker()
      .process(task, getTimeOutValue(), TimeUnit.valueOf(timeUnitParameter.stringValue()))
      .thenAccept(t -> sendOutput(t));
  }

  /**
   * Gets the first result block, if present, and iterates its result items.
   * For each result item, if an output port exists with a matching name, an output token is sent with the item's value.
   * @param t
   */
  protected final void sendOutput(Task t) {
    t.getResults().findFirst().ifPresent(r -> r.getAllItems().forEach(ri -> {
      TypedIOPort p = (TypedIOPort) getPort(ri.getName());
      if (p != null) {
        try {
          p.send(0, ConversionUtilities.convertJavaTypeToToken(ri.getValue()));
        } catch (Exception e) {
          // TODO store this as an error event
          LOGGER.error(ErrorCode.TASK_ERROR +" - Error sending output token for task " + t.getId() + " result item " + ri.getName(), e);
        }
      }
    }));
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
