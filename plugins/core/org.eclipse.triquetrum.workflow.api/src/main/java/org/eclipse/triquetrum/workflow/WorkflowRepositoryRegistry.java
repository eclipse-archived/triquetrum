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
package org.eclipse.triquetrum.workflow;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Provides an easy access to all registered repositories, for use by things that can not use injection.
 * E.g. things that are not DS components/services themselves, such as Views etc.
 *
 */
public class WorkflowRepositoryRegistry {
  
  private static Set<WorkflowRepositoryService> repositories = Collections.synchronizedSet(new HashSet<>());
  
  /**
   * Add the given repository to the registry, if it is not null.
   * 
   * @param repository
   */
  public void addWorkflowRepository(WorkflowRepositoryService repository) {
    if(repository!=null) {
      repositories.add(repository);
    }
  }
  
  /**
   * Remove the given repository from the registry.
   * 
   * @param repository
   */
  public void removeWorkflowRepository(WorkflowRepositoryService repository) {
    repositories.remove(repository);
  }
  
  /**
   * 
   * @return an unmodifiable view on the registered repositories
   */
  public static WorkflowRepositoryService[] getRepositories() {
    return repositories.toArray(new WorkflowRepositoryService[0]);
  }
}
