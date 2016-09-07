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

import java.io.Serializable;
import java.net.URI;

import org.eclipse.triquetrum.TriqException;
import org.ptolemy.commons.VersionSpecification;

import ptolemy.actor.CompositeActor;


/**
 * A light-weight handle on a workflow model.
 * <p>
 * It offers direct access to some important metadata-properties for workflow models,
 * and can be used as a means to identify models and to work with them
 * without needing to load/parse/build the actual model instance.
 * </p>
 *
 */
public interface ModelHandle extends Serializable {

  /**
   *
   * @return the URI where the model resource can be retrieved
   */
  URI getResourceLocation();

  /**
   * @return the unique code identifying the model in its repository
   */
  String getCode();

  /**
   * @return the version of this handle's model in the repository
   */
  VersionSpecification getVersion();

  /**
   *
   * @return the model behind this handle.
   * This may be a slow/heavy operation as it may involve reading the definition from a remote repository,
   * parsing it and constructing all its model elements.
   * @throws TriqException when the model can not be loaded/parsed for this handle
   */
  CompositeActor getModel() throws TriqException;

  /**
   * @return the model definition in its raw format, typically a MOML/XML.
   * This may be empty for "compacted" handles, in which case the full contents can
   * be explicitly loaded via RepositoryService.loadModelHandleWithContent.
   * @throws TriqException when the raw model definition (moml/xml) can not be loaded for this handle
   */
  String getRawModelDefinition() throws TriqException;
}
