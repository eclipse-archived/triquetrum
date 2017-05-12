/*******************************************************************************
 * Copyright (c) 2017 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow;

import java.util.Arrays;
import java.util.Collection;
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
