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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.eclipse.triquetrum.ProcessingStatus;
import org.eclipse.triquetrum.processing.ErrorCode;
import org.eclipse.triquetrum.processing.ProcessingException;
import org.eclipse.triquetrum.processing.model.Task;
import org.eclipse.triquetrum.processing.service.TaskProcessingBroker;
import org.eclipse.triquetrum.processing.service.TaskProcessingBrokerTracker;
import org.eclipse.triquetrum.processing.service.TaskProcessingService;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A basic implementation of a {@link TaskProcessingBroker}, that maintains a set of all known {@link TaskProcessingService} implementations, and asks them
 * one-by-one if they can process a submitted task, until the first that accepts it.
 */
public class DefaultTaskProcessingBroker implements TaskProcessingBroker {
  private final static Logger LOGGER = LoggerFactory.getLogger(DefaultTaskProcessingBroker.class);

  // a collection of all registered services, ordered by version in a set per name
  private Map<String, SortedSet<ServiceEntry>> services = new ConcurrentHashMap<String, SortedSet<ServiceEntry>>();

  // using a scheduled executor service for timeout handling is more robust and offers better concurrency than a plain java.util.Timer.
  private ScheduledExecutorService delayTimer;

  @Override
  public CompletableFuture<Task> process(Task task, Long timeout, TimeUnit unit) {
    // Get timeout handling working before accessing the services
    // to make sure that bad/blocking service implementations don't interfere with it.
    // TODO check if should provide a service base class that does the timeout handling,
    // then we can move it away from this boker.
    CompletableFuture<Task> timeOutHandler = registerTimeOutHandler(task, timeout, unit);

    CompletableFuture<Task> futResult = null;
    for (SortedSet<ServiceEntry> svcSet : services.values()) {
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

    if (futResult == null) {
      futResult = new CompletableFuture<>();
      futResult.completeExceptionally(new ProcessingException(ErrorCode.TASK_UNHANDLED, "No service found for " + task, null));
    }

    // Remark that timeout handling in the broker only works for non-blocking services!
    // When the selected TaskProcessingService above was a blocking one, the process method by definition only returns
    // when the processing is done or ended in error.
    // Timeout-handling for blocking services must be implemented in the service itself.
    // (and as remarked above, it might be best to do that for all service implementations anyway)
    futResult = futResult.applyToEither(timeOutHandler, t -> {t.setStatus(ProcessingStatus.FINISHED); return t;})
      .whenComplete((t, ex) -> {
      if (ex != null) {
        ProcessingException pex = null;
        if (ex.getCause() instanceof ProcessingException) {
          pex = (ProcessingException) ex.getCause();
        } else if (ex.getCause() instanceof TimeoutException) {
          pex = new ProcessingException(ErrorCode.TASK_TIMEOUT, ex.getCause());
        } else {
          pex = new ProcessingException(ErrorCode.TASK_ERROR, ex.getCause());
        }
        task.setErrorStatus(pex);
      }
    });

    return futResult;
  }

  @Override
  public CompletableFuture<Task> processSubTasks(Task task, Long timeout, TimeUnit unit, boolean finishWhenDone) {
    List<CompletableFuture<Task>> futures = task.getSubTasks().map(t -> process(t, timeout, unit)).collect(Collectors.<CompletableFuture<Task>> toList());
    CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

    return allDoneFuture.thenApply(t -> {
      if (finishWhenDone) {
        task.setStatus(ProcessingStatus.FINISHED);
      }
      return task;
    });
  }

  @Override
  public CompletableFuture<List<Task>> process(Long timeout, TimeUnit unit, Task... tasks) {
    List<CompletableFuture<Task>> futures = new ArrayList<>();
    for (Task t : tasks) {
      futures.add(process(t, timeout, unit));
    }
    return sequence(futures);
  }

  /**
   * Typically invoked by the DS component activation.
   */
  public void activate(ComponentContext cContext, Map<String, Object> properties) {
    modified(properties);
    TaskProcessingBrokerTracker.setBroker(this);
  }

  /**
   * Typically invoked by a reconfiguration of the DS component.
   */
  public void modified(Map<String, Object> properties) {
    short timeoutHandlingThreads = (short) properties.getOrDefault("timeoutHandlingThreads", (short) 1);
    if (delayTimer != null) {
      // first shutdown the pending timeout handlers
      try {
        List<Runnable> pendingThings = delayTimer.shutdownNow();
        for (Runnable runnable : pendingThings) {
          LOGGER.warn("Configuration was modified. Shutdown " + runnable);
        }
      } catch (Exception e) {
        // ignore this one
      }
    }
    // (re)create a thread pool with the configured size
    delayTimer = Executors.newScheduledThreadPool(timeoutHandlingThreads, new TimeoutHandlerThreadFactory("task-timeout-handler"));
  }

  /**
   * Typically invoked by the DS component deactivation.
   */
  public void deactivate(ComponentContext cContext, int reason) {
    TaskProcessingBrokerTracker.unsetBroker(this);
    services.clear();
    if (delayTimer != null) {
      try {
        delayTimer.shutdownNow();
      } catch (Exception e) {
        // ignore this one
      }
    }
  }

  /**
   * remark that the services are registered typically via OSGi DS, and the register/remove methods are not expected to be invoked from code.
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
   * remark that the services are registered typically via OSGi DS, and the register/remove methods are not expected to be invoked from code.
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

  // TODO improve error handling in future sequencing
  // For the moment, if one future returns an error the whole chain is interrupted by an unchecked exception.
  private static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
    CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
    return allDoneFuture.thenApply(v -> futures.stream().map(future -> future.join()).collect(Collectors.<T> toList()));
  }

  private CompletableFuture<Task> registerTimeOutHandler(final Task task, Long timeout, TimeUnit unit) {
    final CompletableFuture<Task> timeoutHandler = new CompletableFuture<>();
    if (timeout != null && unit != null && (timeout > 0)) {
      delayTimer.schedule(() -> {
        return timeoutHandler.completeExceptionally(new TimeoutException("Task " + task.getId() + " timeout after " + timeout + " "+ unit));
      }, timeout, unit);
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Task {} timeout set to {} {}", new Object[] { task.getId(), timeout, unit });
      }
    }
    return timeoutHandler;
  }

  private static class TimeoutHandlerThreadFactory implements ThreadFactory {
    final private AtomicInteger threadNumber = new AtomicInteger(1);
    final private String namePrefix;
    final private ClassLoader contextClassLoader;

    TimeoutHandlerThreadFactory(String namePrefix) {
      this.namePrefix = namePrefix;
      this.contextClassLoader = getClass().getClassLoader();
    }

    public Thread newThread(Runnable r) {
      Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
      t.setDaemon(true);
      t.setPriority(Thread.NORM_PRIORITY);
      t.setContextClassLoader(contextClassLoader);
      return t;
    }
  }


  /**
   * A pair of a service and its version
   */
  private static final class ServiceEntry implements Comparable<ServiceEntry> {
    final TaskProcessingService service;
    final Version version;

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
      if (res == 0) {
        res = version.compareTo(o.version);
      }
      return res;
    }

  }
}
