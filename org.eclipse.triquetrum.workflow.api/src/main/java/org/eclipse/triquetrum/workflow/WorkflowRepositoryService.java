/*******************************************************************************
 * Copyright (c) 2015 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow;

import org.ptolemy.commons.VersionSpecification;

import ptolemy.actor.CompositeActor;

/**
 * This interface offers operations to manage Models in a repository.
 * <p>
 * As Models can be large and complex structures, lightweight ModelHandles are used as intermediate entities for most
 * actions that must be done on the repository. <br/>
 * A ModelHandle allows to obtain its associated Model, and/or metadata like the identifying "code", version info etc.
 * <br/>
 * For environments with high throughputs and/or large models, it may be beneficial to use "compacted" ModelHandles as
 * much as possible. These have only the necessary metadata but not the actual Model nor raw flow definition XML/MOML.
 * Only when really needed should the flow definition be retrieved via <code>loadModelHandleWithContent</code>.
 * </p>
 * <p>
 * The repository may be able to maintain different versions for the Models, in which case it is possible to indicate
 * the current active version/revision. The active version is the one that will be used when actions (e.g. executing)
 * are performed on a Model by name or identifying code.
 * </p>
 * <p>
 * This interface offers a basic "linear" view on Models in a repository. More advanced repository interfaces (e.g.
 * including Projects, rules modules, images and other asset types) will/must be defined separately.
 * </p>
 *
 * @author erwin
 *
 */
public interface WorkflowRepositoryService {

  /**
   * Store the given Model in the repository, if it does not exist yet, using the Model's name as identifier.
   * <p>
   * If an entry already exists for the given name, this operation will fail.
   * </p>
   *
   * @param model
   * @return the handle to the created repository entry
   * @throws DuplicateEntryException
   *           if an entry with the same identifier already exists
   */
  ModelHandle commit(CompositeActor model) throws DuplicateEntryException;

  /**
   * Store the given Model in the repository, if it does not exist yet, using the given modelCode as identifier.
   * <p>
   * If an entry already exists for the given modelCode, this operation will fail.
   * </p>
   *
   * @param modelCode
   *          the identifier to be used for the model and its future revisions
   * @param model
   * @return the handle to the created repository entry
   * @throws DuplicateEntryException
   *           if an entry with the same identifier already exists
   */
  ModelHandle commit(String modelCode, CompositeActor model) throws DuplicateEntryException;

  /**
   *
   * @param modelCode
   * @return all versions of the Model, that were stored in the repository for the given modelCode.
   * @throws EntryNotFoundException
   */
  ModelHandle[] delete(String modelCode) throws EntryNotFoundException;

  /**
   * Updates the Model definition in the repository, using the given ModelHandle to identify the repository entry that
   * must be updated.
   * <p>
   * In a versioned repository, this should increase the major version nr for the model.
   * </p>
   *
   * @param handle
   * @param updatedModel
   * @param activate
   *          true if the given model should be directly set as the active revision; false if the current active
   *          revision for the same modelCode should remain active.
   * @return the handle to the updated repository entry
   * @throws EntryNotFoundException
   *           when the handle does not correspond to a Model entry in this repository
   */
  ModelHandle update(ModelHandle handle, CompositeActor updatedModel, boolean activate) throws EntryNotFoundException;

  /**
   * Returns the Model stored in the repository for the given code, in the version that is currently activated.
   *
   * @param modelCode
   * @return the ModelHandle active for the given code
   * @throws EntryNotFoundException
   *           when the code does not correspond to a Model entry in this repository
   */
  ModelHandle getActiveModel(String modelCode) throws EntryNotFoundException;

  /**
   * Returns the Model stored in the repository for the given code, in the most recent version.
   *
   * @param modelCode
   * @return the most recent ModelHandle for the given code
   * @throws EntryNotFoundException
   *           when the code does not correspond to a Model entry in this repository
   */
  ModelHandle getMostRecentModel(String modelCode) throws EntryNotFoundException;

  /**
   * Returns the Model stored in the repository for the given code and version.
   *
   * @param modelCode
   * @param version
   * @return the ModelHandle for the given code and version
   * @throws EntryNotFoundException
   *           when the combination of code and version does not correspond to a Model entry in this repository
   */
  ModelHandle getModelVersion(String modelCode, VersionSpecification version) throws EntryNotFoundException;

  /**
   *
   * @param handle
   *          a handle that may be a "compacted" one, i.e. without the actual model definition but just with
   *          code/version/location metadata.
   * @return the handle with the raw model definition filled in
   * @throws EntryNotFoundException
   */
  ModelHandle loadModelHandleWithContent(ModelHandle handle) throws EntryNotFoundException;

  /**
   * @return list of all Model codes, for which Models are stored in the repository.
   */
  String[] getAllModelCodes();

  /**
   *
   * @param modelCode
   * @return handles for all model versions in the repository for the given code
   * @throws EntryNotFoundException
   *           when the code does not correspond to a Model entry in this repository
   */
  ModelHandle[] getAllModelRevisions(String modelCode) throws EntryNotFoundException;

  /**
   *
   * @param handle
   * @return the handle to the model revision that was active before
   * @throws EntryNotFoundException
   */
  ModelHandle activateModelRevision(ModelHandle handle) throws EntryNotFoundException;
}
