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
package org.eclipse.triquetrum.workflow.aoc.repository;

import org.eclipse.triquetrum.ErrorCode;
import org.eclipse.triquetrum.TriqException;
import org.eclipse.triquetrum.workflow.EntryNotFoundException;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.eclipse.triquetrum.workflow.WorkflowRepositoryService;
import org.ptolemy.classloading.ActorOrientedClassProvider;
import org.ptolemy.commons.VersionSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.kernel.CompositeEntity;

/**
 * An Actor Oriented Classes (AOC) provider that loads AOCs from a WorkflowRepositoryService.
 *
 */
public class AocProviderFromRepository implements ActorOrientedClassProvider {
  private final static Logger LOGGER = LoggerFactory.getLogger(AocProviderFromRepository.class);

  private WorkflowRepositoryService repositoryService;

  public void setRepositoryService(WorkflowRepositoryService repositoryService) {
    LOGGER.info("AocProviderFromRepository - using " + repositoryService);
    this.repositoryService = repositoryService;
  }

  @Override
  public CompositeEntity getActorOrientedClass(String className, VersionSpecification versionSpec) throws ClassNotFoundException {
    LOGGER.trace("getActorOrientedClass() - entry : aoc {} version {}", className, versionSpec);
    CompositeEntity result = null;
    try {
      if (repositoryService != null) {
        try {
          ModelHandle aocHandle = versionSpec != null ? repositoryService.getModelVersion(className, versionSpec)
              : repositoryService.getActiveModel(className);
          result = aocHandle.getModel();
          if (result.isClassDefinition()) {
            return result;
          } else {
            // it's not the right one after all as it's not a class!
            // TODO think about this a bit. Could Ptolemy/Triquetrum allow multiple models with a same code/className
            // loaded from different places? And that one could be a class and others not?
            // Or is this a dramatic error in the system configuration?
            throw new ClassNotFoundException();
          }
        } catch (EntryNotFoundException e) {
          throw new ClassNotFoundException();
        } catch (TriqException e) {
          LOGGER.error(ErrorCode.ERROR + " - Error parsing class model for " + className, e);
          throw new ClassNotFoundException();
        }
      } else {
        LOGGER.warn(ErrorCode.WARN + " - WorkflowRepositoryService not initialized");
        throw new ClassNotFoundException();
      }
    } finally {
      LOGGER.trace("getActorOrientedClass() - exit : aoc {} version {} : {}", className, versionSpec, result!=null ? "found " + result : "not found");
    }
  }

}
