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
package org.eclipse.triquetrum.processing.service.impl;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.triquetrum.processing.ErrorCode;
import org.eclipse.triquetrum.processing.ProcessingException;
import org.eclipse.triquetrum.processing.model.Task;
import org.eclipse.triquetrum.processing.service.TaskProcessingBroker;
import org.eclipse.triquetrum.processing.service.TaskProcessingService;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A basic implementation of a {@link TaskProcessingBroker}, that maintains a set of all known {@link TaskProcessingService} implementations,
 * and asks them one-by-one if they can process a submitted task, until the first that accepts it.
 */
public class DefaultTaskProcessingBroker implements TaskProcessingBroker {
  private final static Logger LOGGER = LoggerFactory.getLogger(DefaultTaskProcessingBroker.class);

  // a collection of all registered services, ordered by version in a set per name
  private Map<String, SortedSet<ServiceEntry>> services = new ConcurrentHashMap<String, SortedSet<ServiceEntry>>();

  // using a scheduled executor service for timeout handling is more robust and offers better concurrency than a plain java.util.Timer.
  // TODO make the nr of threads configurable
  private static ScheduledExecutorService delayTimer = Executors.newScheduledThreadPool(5);

  @Override
  public CompletableFuture<Task> process(Task task, Long timeout, TimeUnit unit) throws ProcessingException {
    // Get timeout handling working before accessing the services
    // to make sure that bad/blocking service implementations don't interfere with it.
    registerTimeOutHandler(task, timeout, unit);

    CompletableFuture<Task> futResult = null;
    for(SortedSet<ServiceEntry> svcSet : services.values()) {
      final ServiceEntry svcEntry = svcSet.last();
      TaskProcessingService service = svcEntry.service;
      if (service.canProcess(task)) {
        futResult = service.process(task, timeout, unit);
        if (futResult != null) {
          LOGGER.debug("Task {} will be processed by service {}", task.getId(), service.getName());
          break;
        }
      }
    }
    if (futResult != null) {
      return futResult;
    } else {
      throw new ProcessingException(ErrorCode.TASK_UNHANDLED, "No service found for " + task, null);
    }
  }

  private void registerTimeOutHandler(final Task task, Long timeout, TimeUnit unit) {
    if (timeout == null || unit == null || (timeout <= 0)) {
      return;
    }
    delayTimer.schedule(new TimeoutHandler(task.getProcessId(), task.getId()), timeout, unit);
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Task {} timeout set to {} {}", new Object[] { task.getId(), timeout, unit });
    }
  }

  public void init() {
    // TODO complete this
  }

  public void destroy() {
    services.clear();
    try {
      delayTimer.shutdownNow();
    } catch (Exception e) {
      // ignore this one
    }
  }

  /**
   * remark that the services are registered typically via OSGi DS,
   * and the register/remove methods are not expected to be invoked from code.
   */
  @Override
  public boolean registerService(TaskProcessingService service) {
    return internalRegisterService(service, Version.emptyVersion);
  }

  public void registerServiceRef(ServiceReference<TaskProcessingService> svcRef) {
    TaskProcessingService service = svcRef.getBundle().getBundleContext().getService(svcRef);
    Version svcVersion = svcRef.getBundle().getVersion();
    internalRegisterService(service, svcVersion);
  }

  private boolean internalRegisterService(TaskProcessingService service, Version svcVersion) {
    boolean result = false;
    SortedSet<ServiceEntry> svcSet = services.get(service.getName());
    if (svcSet == null) {
      svcSet = new ConcurrentSkipListSet<DefaultTaskProcessingBroker.ServiceEntry>();
      services.put(service.getName(), svcSet);
    }
    if (svcSet.add(new ServiceEntry(service, svcVersion))) {
      result = true;
      LOGGER.debug("Registered service {} with version {}", service.getName(), svcVersion);
    } else {
      LOGGER.debug("Ignored duplicate service {} with version {}", service.getName(), svcVersion);
    }
    return result;
  }

  /**
   * remark that the services are registered typically via OSGi DS,
   * and the register/remove methods are not expected to be invoked from code.
   */
  @Override
  public boolean removeService(TaskProcessingService service) {
    return internalRemoveService(service, Version.emptyVersion);
  }

  public void removeServiceRef(ServiceReference<TaskProcessingService> svcRef) {
    TaskProcessingService service = svcRef.getBundle().getBundleContext().getService(svcRef);
    Version svcVersion = svcRef.getBundle().getVersion();
    internalRemoveService(service, svcVersion);
  }

  private boolean internalRemoveService(TaskProcessingService service, Version svcVersion) {
    boolean result = false;
    Set<ServiceEntry> svcSet = services.get(service.getName());
    if (svcSet != null) {
      if (svcSet.remove(new ServiceEntry(service, svcVersion))) {
        result = true;
        LOGGER.debug("Removed service {} with version {}", service.getName(), svcVersion);
      } else {
        LOGGER.debug("Did not remove unknown service {} with version {}", service.getName(), svcVersion);
      }
    }
    return result;
  }

  public static final class TimeoutHandler implements Callable<Void> {
    private final Long processID;
    private final Long taskID;

    public TimeoutHandler(Long processID, Long taskID) {
      this.processID = processID;
      this.taskID = taskID;
    }

    public Void call() {
      // TODO : port this from Passerelle
//      ProcessManager procMgr = ProcessManagerServiceTracker.getService().getProcessManager(processID);
//      if (procMgr != null) {
//        Task task = procMgr.getTask(taskID);
//        if (task != null && !task.getProcessingContext().isFinished()) {
//          procMgr.notifyTimeOut(task);
//        }
//      }
      return null;
    }
  }

  /**
   * A pair of a service and its version
   */
  public static final class ServiceEntry implements Comparable<ServiceEntry> {
    TaskProcessingService service;
    Version version;

    public ServiceEntry(TaskProcessingService service, Version version) {
      this.service = service;
      this.version = version;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((service == null) ? 0 : service.hashCode());
      result = prime * result + ((version == null) ? 0 : version.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      ServiceEntry other = (ServiceEntry) obj;
      if (service == null) {
        if (other.service != null)
          return false;
      } else if (!service.getName().equals(other.service.getName()))
        return false;
      if (version == null) {
        if (other.version != null)
          return false;
      } else if (!version.equals(other.version))
        return false;
      return true;
    }

    @Override
    public int compareTo(ServiceEntry o) {
      int res = service.getName().compareTo(o.service.getName());
      if(res==0) {
        res = version.compareTo(o.version);
      }
      return res;
    }

  }
}
