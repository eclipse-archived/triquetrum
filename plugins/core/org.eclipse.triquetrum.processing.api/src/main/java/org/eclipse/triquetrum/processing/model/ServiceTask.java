/*******************************************************************************
 * Copyright (c) 2016,2019 iSencia Belgium NV.
 *  
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.processing.model;

/**
 * This is the interface for tasks that use external services.
 * <p>
 * Interactions with external services typically involve some kind of request/response mechanism across one or more dedicated protocols. <br/>
 * On the task-level we want to be able to (optionally) trace the literal request and response contents. The technical implementation of the actual interaction
 * is handled by a set of service&adapter-implementations.
 * </p>
 */
public interface ServiceTask extends Task {

  /**
   * The topic of the task's context event of sending a request to an external service. The raw service request will be (optionally) stored with this event.
   */
  final static String REQUEST_SENT_EVENT_TOPIC = "REQUEST SENT";

  /**
   * The topic of the task's context event of receiving a response from an external service. The raw service response will be (optionally) stored with this
   * event.
   */
  final static String RESPONSE_RECEIVED_EVENT_TOPIC = "RESPONSE RECEIVED";

  /**
   *
   * @return if this task instance requires tracing the service request & response in a raw string format.
   * @see #getServiceRequest()
   * @see #getServiceResponse()
   */
  boolean isTraceRequestResponse();

  /**
   *
   * @param traceIt
   *          true to activate the tracing of service requests&responses
   */
  void setTraceRequestResponse(boolean traceIt);

  /**
   *
   * @return some human-readable raw representation of the request, e.g. XML, as sent to the backing service
   */
  String getServiceRequest();

  /**
   *
   * @param request
   *          some human-readable raw representation of the request, e.g. XML, as sent to the backing service
   */
  void setServiceRequest(String request);

  /**
   *
   * @return some human-readable raw representation of the response, e.g. XML, as obtained from the backing service
   */
  String getServiceResponse();

  /**
   *
   * @param response
   *          some human-readable raw representation of the response, e.g. XML, as obtained from the backing service
   */
  void setServiceResponse(String response);
}
