/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.triquetrum.TriqException;
import org.eclipse.triquetrum.workflow.ErrorCode;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.eclipse.triquetrum.workflow.model.util.PtolemyUtil;
import org.ptolemy.commons.VersionSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.actor.CompositeActor;
import ptolemy.data.expr.Parameter;
import ptolemy.kernel.Entity;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NamedObj;
import ptolemy.kernel.util.Workspace;
import ptolemy.moml.MoMLFilter;
import ptolemy.moml.MoMLParser;

/**
 * A collection of helper methods to manipulate workflow models and files.
 *
 */
public class WorkflowUtils {
  private final static Logger LOGGER = LoggerFactory.getLogger(WorkflowUtils.class);

  /**
   * Parses the model found at modelResourceLocator where each relative reference to external elements is retrieved relative to the current working directory.
   *
   * @param modelResourceLocator
   * @return
   * @throws MalformedURLException
   * @throws Exception
   */
  public static NamedObj readFrom(URI modelResourceLocator) throws MalformedURLException, Exception {
    return readFrom(null, modelResourceLocator, null);
  }

  /**
   * Parses the model found at modelResourceLocator where each relative reference to external elements is retrieved relative to the current working directory,
   * and retrievals of submodels and other (optionally) versioned contained elements look for the given version as default, if no explicit version is specified
   * in the model.
   *
   * @param modelResourceLocator
   * @param version
   * @return
   * @throws MalformedURLException
   * @throws Exception
   */
  public static NamedObj readFrom(URI modelResourceLocator, VersionSpecification version) throws MalformedURLException, Exception {
    return readFrom(null, modelResourceLocator, version);
  }

  /**
   * Parses the model found at modelResourceLocator where each relative reference to external elements is retrieved relative to the modelsRootLocator, and
   * retrievals of submodels and other (optionally) versioned contained elements look for the given version as default, if no explicit version is specified in
   * the model.
   *
   * @param modelsRootLocator
   * @param modelResourceLocator
   * @param version
   * @param filters
   *          optional filters to manipulate the moml parsing
   * @return
   * @throws MalformedURLException
   * @throws Exception
   */
  public static NamedObj readFrom(URI modelsRootLocator, URI modelResourceLocator, VersionSpecification version, MoMLFilter... filters)
      throws MalformedURLException, Exception {
    Workspace workspace = new Workspace("TriqImport");
    MoMLParser parser = new MoMLParser(workspace, version, null);
    MoMLParser.addMoMLFilters(PtolemyUtil.getImportFilters(), workspace);
    URL modelURL = modelResourceLocator.toURL();
    MoMLParser.purgeModelRecord(modelURL);
    URL baseURL = modelsRootLocator != null ? modelsRootLocator.toURL() : null;
    NamedObj result = parser.parse(baseURL, modelURL);
    MoMLParser.setMoMLFilters(null, workspace);
    return result;
  }

  /**
   * Apply the given parameter names and values on the model identified by the modelHandle.
   * <p>
   * The parameterOverrides Map's keys should contain the full parameter names without the model's name, e.g. :
   * <ul>
   * <li>"frequency" to identify a parameter in the toplevel of a model.</li>
   * <li>"Const.value" to identify a parameter in the Const actor in the toplevel of a model.</li>
   * <li>"A.Const.value" to identify a parameter in the Const actor in a submodel called "A", in the model.</li>
   * </ul>
   * Values are applied using Parameter.setExpression(), are set as persistent and the overridden parameters are then validated.
   * </p>
   *
   * @param modelHandle
   *          a handle to the model with its default (deep) parameter values
   * @param processId
   *          (can be null) when this must be done in the context of a workflow execution, the process ID can be passed along to enrich logging
   * @param parameterOverrides
   *          the map of parameter names&values to be applied on the given model
   * @return a clone of the ModelHandle's model, with applied parameter overrides
   * @throws TriqException
   */
  public static CompositeActor applyParameterSettings(ModelHandle modelHandle, String processId, Map<String, String> parameterOverrides) throws TriqException {
    CompositeActor model = modelHandle.getModel();
    try {
      model = (CompositeActor) model.clone(new Workspace());
    } catch (CloneNotSupportedException e) {
      throw new TriqException(ErrorCode.MODEL_CONFIGURATION_ERROR, "Error cloning model " + modelHandle, model, e);
    } catch (NullPointerException e) {
      throw new TriqException(ErrorCode.MODEL_LOADING_ERROR, "Error obtaining model from its handle " + modelHandle, e);
    }

    if (parameterOverrides != null) {
      Iterator<Entry<String, String>> propsItr = parameterOverrides.entrySet().iterator();
      while (propsItr.hasNext()) {
        Entry<String, String> element = propsItr.next();
        String propName = element.getKey();
        String propValue = element.getValue();
        String[] nameParts = propName.split("[\\.]");

        // set model parameters
        if (nameParts.length == 1 && !model.attributeList().isEmpty()) {
          try {
            final Parameter p = (Parameter) model.getAttribute(nameParts[0], Parameter.class);
            setParameter(modelHandle, processId, p, propName, propValue);
          } catch (final IllegalActionException e) {
            throw new TriqException(ErrorCode.MODEL_CONFIGURATION_ERROR, "Inconsistent parameter definition " + propName, model, e);
          }
        }
        // parts[parts.length-1] is the parameter name
        // all the parts[] before that are part of the nested Parameter name
        Entity<?> entity = model;
        for (int j = 0; j < nameParts.length - 1; j++) {
          if (entity instanceof CompositeActor) {
            Entity<?> test = ((CompositeActor) entity).getEntity(nameParts[j]);
            if (test == null) {
              try {
                // maybe it is a director
                ptolemy.actor.Director d = ((CompositeActor) entity).getDirector();
                if (d != null) {
                  Parameter p = (Parameter) d.getAttribute(nameParts[nameParts.length - 1], Parameter.class);
                  setParameter(modelHandle, processId, p, propName, propValue);
                }
              } catch (IllegalActionException e) {
                throw new TriqException(ErrorCode.MODEL_CONFIGURATION_ERROR, "Inconsistent parameter definition " + propName, model, e);
              }
            } else {
              entity = ((CompositeActor) entity).getEntity(nameParts[j]);
              if (entity != null) {
                try {
                  Parameter p = (Parameter) entity.getAttribute(nameParts[nameParts.length - 1], Parameter.class);
                  setParameter(modelHandle, processId, p, propName, propValue);
                } catch (IllegalActionException e) {
                  throw new TriqException(ErrorCode.MODEL_CONFIGURATION_ERROR, "Inconsistent parameter definition " + propName, model, e);
                }
              }
            }
          } else {
            break;
          }
        }
      }
    }
    return model;
  }

  private static void setParameter(ModelHandle modelHandle, String processId, final Parameter p, String propName, String propValue)
      throws IllegalActionException {
    if (p != null) {
      p.setExpression(propValue);
      p.setPersistent(true);
      p.validate();
      LOGGER.info("Context {} - Flow {} - Override parameter {} : {}", new Object[] { processId, modelHandle.getCode(), propName, propValue });
    } else if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Context {} - Flow {} - Unknown parameter, no override : {} ", new Object[] { processId, modelHandle.getCode(), propName });
    }
  }
}
