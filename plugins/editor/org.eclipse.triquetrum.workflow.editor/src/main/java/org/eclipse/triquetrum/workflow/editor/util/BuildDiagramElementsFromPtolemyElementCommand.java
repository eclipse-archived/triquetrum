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
package org.eclipse.triquetrum.workflow.editor.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.CreateContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.triquetrum.ErrorCode;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramTypeProvider;
import org.eclipse.triquetrum.workflow.editor.features.FeatureConstants;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementCreateFeature;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.TriqFactory;
import org.eclipse.triquetrum.workflow.model.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.actor.IOPort;
import ptolemy.actor.IORelation;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.Entity;
import ptolemy.kernel.util.NamedObj;

/**
 * This command can visit the model element tree of a Ptolemy II NamedObj to add the corresponding diagram elements in a new Triquetrum workflow Diagram. If the
 * NamedObj is a composite, this command also copies contained elements and their potential relations.
 *
 */
public class BuildDiagramElementsFromPtolemyElementCommand {
  private final static Logger LOGGER = LoggerFactory.getLogger(BuildDiagramElementsFromPtolemyElementCommand.class);

  private Diagram diagram;
  private NamedObj ptolemyElement;

  // A cache maintained during importing, to easily retrieve anchors
  // for creating relations based on the names of the corresponding linked ports in the Ptolemy model.
  private Map<String, Anchor> anchorMap = new HashMap<>();
  private Map<String, Relation> relationMap = new HashMap<>();

  private String iconType;
  private String iconResource;

  /**
   *
   * @param diagram
   *          a Triq diagram that will be extended with new model elements
   * @param ptolemyElement
   *          the model that must be imported into the diagram
   * @param iconResource 
   * @param iconType 
   */
  public BuildDiagramElementsFromPtolemyElementCommand(Diagram diagram, NamedObj ptolemyElement, String iconType, String iconResource) {
    this.diagram = diagram;
    this.ptolemyElement = ptolemyElement;
    this.iconType = iconType;
    this.iconResource = iconResource;
  }

  public void execute() {
    IDiagramTypeProvider dtp = GraphitiUi.getExtensionManager().createDiagramTypeProvider(diagram, TriqDiagramTypeProvider.ID);
    IFeatureProvider featureProvider = dtp.getFeatureProvider();

    // get the root compositeactor of the diagram, where we must add the extra ptolemy element
    CompositeActor model = (CompositeActor) featureProvider.getBusinessObjectForPictogramElement(diagram);

//    if (ptolemyElement instanceof ptolemy.actor.CompositeActor) {
//      ptolemy.actor.CompositeActor compPtElem = (ptolemy.actor.CompositeActor) ptolemyElement;
//      // Get the director as a first trial to add a new diagram element from ptolemy model elements
//      Director director = compPtElem.getDirector();
//      createModelElement(model, featureProvider, director, model);
//
//      
//      for (IORelation rel : (List<IORelation>) compPtElem.relationList()) {
//        relationMap.put(rel.getFullName(), createRelation(model, featureProvider, rel));
//      }
//
//      for (IOPort p : (List<IOPort>) compPtElem.portList()) {
//        createModelElement(model, featureProvider, p, model);
//      }
//
//      for (Entity entity : compPtElem.entityList(Entity.class)) {
//        createModelElement(model, featureProvider, entity, model);
//      }
//
//      // we don't import all attributes as lots of them are ptolemy-internal
//      // TODO find something to resolve errors related to the order of adding parameters
//      // that contain expressions with references to other parameters that may be later in the list.
//
//      // First we take parameters ...
//      for (Parameter p : ptolemyElement.attributeList(Parameter.class)) {
//        createModelElement(model, featureProvider, p, model);
//      }
//      // ... and annotations (i.e. TextAttributes)
//      for (TextAttribute a : ptolemyElement.attributeList(TextAttribute.class)) {
//        createModelElement(model, featureProvider, a, model);
//      }
//
//      linkRelations(compPtElem, model, featureProvider);
//    } else {
      createModelElement(model, featureProvider, ptolemyElement, model);
//    }
  }

  public Map<String, Anchor> getAnchorMap() {
    return anchorMap;
  }

  protected org.eclipse.triquetrum.workflow.model.NamedObj createModelElement(org.eclipse.triquetrum.workflow.model.CompositeActor model,
      IFeatureProvider featureProvider, NamedObj ptObject, org.eclipse.triquetrum.workflow.model.NamedObj triqContainer) {

    org.eclipse.triquetrum.workflow.model.NamedObj result = null;
    double[] location = EditorUtils.getLocation(ptObject);
    int x = (int) (location != null ? location[0] : 10);
    int y = (int) (location != null ? location[1] : 10);
    CreateContext context = new CreateContext();
    context.setTargetContainer(diagram);
    context.setX(x);
    context.setY(y);
    // in specific cases we must be able to pass a predefined/known container, e.g. for creating a vertex in its containing relation
    context.putProperty(FeatureConstants.CONTAINER, triqContainer);
    // A hack to facilitate caching port anchors.
    // As we don't get Graphiti shapes from the create feature, but our business object,
    // it's rather tricky to retrieve anchors from the feature's returned creation result.
    // So we push the cache/map into the creation feature (and so into the corresponding AddFeature)
    // and let them fill in the anchors on the fly, when they're created/added.
    context.putProperty(FeatureConstants.ANCHORMAP_NAME, anchorMap);
    for (ICreateFeature createFeature : featureProvider.getCreateFeatures()) {
      if (createFeature instanceof ModelElementCreateFeature) {
        ModelElementCreateFeature mecf = (ModelElementCreateFeature) createFeature;
        if (ptObject.getClass().getName().equals(mecf.getWrappedClass())) {
          mecf.setWrappedObject(ptObject);
          result = (org.eclipse.triquetrum.workflow.model.NamedObj) mecf.create(context)[0];
          break;
        }
      }
    }
    if (result == null) {
      // seems to be about a model element for which no preconfigured ModelElementCreateFeature was found
      BoCategory meCategory = BoCategory.valueOf(ptObject.getClass());
      if (meCategory != null) {
        ModelElementCreateFeature mecf = new ModelElementCreateFeature(featureProvider, null, meCategory, ptObject.getName(), null, iconResource, iconType, null);
        mecf.setWrappedObject(ptObject);
        result = (org.eclipse.triquetrum.workflow.model.NamedObj) mecf.create(context)[0];
      } else {
        // TODO implement some better error handling reaching into the user's import GUI
        LOGGER.error(ErrorCode.WARN + " - Unsupported Ptolemy model element " + ptObject);
      }
    }
    return result;
  }

  protected org.eclipse.triquetrum.workflow.model.Relation createRelation(org.eclipse.triquetrum.workflow.model.CompositeActor model,
      IFeatureProvider featureProvider, IORelation ptObject) {

    Relation result = TriqFactory.eINSTANCE.createRelation();
    result.setWrappedObject(ptObject);
    model.getRelations().add(result);

    List<ptolemy.moml.Vertex> vertexList = ptObject.attributeList(ptolemy.moml.Vertex.class);
    for (ptolemy.moml.Vertex v : vertexList) {
      createModelElement(model, featureProvider, v, result);
    }

    return result;
  }

  protected void linkRelations(CompositeEntity ptModel, org.eclipse.triquetrum.workflow.model.CompositeActor triqModel, IFeatureProvider featureProvider) {

    linkRelationsForEntity(ptModel, featureProvider);

    for (Entity e : (List<Entity>) ptModel.entityList()) {
      linkRelationsForEntity(e, featureProvider);
    }

    for (Relation triqRelation : relationMap.values()) {
      int portCount = triqRelation.getLinkedPorts().size();
      Vertex vertex = triqRelation.getVertex();
      if (vertex == null) {
        if (portCount == 2) {
          Port p1 = triqRelation.getLinkedPorts().get(0);
          Port p2 = triqRelation.getLinkedPorts().get(1);
          Anchor anchor1 = anchorMap.get(p1.getFullName());
          Anchor anchor2 = anchorMap.get(p2.getFullName());
          AddConnectionContext addContext = new AddConnectionContext(anchor1, anchor2);
          addContext.setNewObject(triqRelation);
          featureProvider.addIfPossible(addContext);
        } else {
          System.err.println("Relation " + triqRelation + " has wrong count of connected ports : " + portCount);
        }
      } else {
        Anchor vertexAnchor = anchorMap.get(vertex.getFullName());
        // a relation with a vertex can have an arbitrary port count
        for (Port p : triqRelation.getLinkedPorts()) {
          Anchor portAnchor = anchorMap.get(p.getFullName());
          AddConnectionContext addContext = new AddConnectionContext(portAnchor, vertexAnchor);
          addContext.setNewObject(triqRelation);
          featureProvider.addIfPossible(addContext);
        }
        for (Relation r : triqRelation.getLinkedRelations()) {
          Vertex linkedVertex = r.getVertex();
          if (linkedVertex != null) {
            Anchor vertexAnchor2 = anchorMap.get(linkedVertex.getFullName());
            AddConnectionContext addContext = new AddConnectionContext(vertexAnchor, vertexAnchor2);
            addContext.setNewObject(r);
            featureProvider.addIfPossible(addContext);
          }
        }
      }
    }
  }

  private void linkRelationsForEntity(Entity<?> ptEntity, IFeatureProvider featureProvider) {
    for (IOPort ptPort : (List<IOPort>) ptEntity.portList()) {
      Anchor triqAnchor1 = anchorMap.get(ptPort.getFullName());
      Port triqPort = (Port) featureProvider.getBusinessObjectForPictogramElement(triqAnchor1);
      if (triqPort != null) {
        for (IORelation ptRelation : (List<IORelation>) ptPort.linkedRelationList()) {
          Relation triqRelation = relationMap.get(ptRelation.getFullName());
          if (triqRelation != null) {
            triqPort.link(triqRelation);
          } else {
            LOGGER.error("Triq relation not found for {} in port {}", ptRelation, ptPort);
          }
        }
      } else {
        LOGGER.error("Triq port not found for {}", ptPort);
      }
    }
    if (ptEntity instanceof CompositeEntity) {
      CompositeEntity compPtEntity = (CompositeEntity) ptEntity;
      for (IORelation r : (List<IORelation>) compPtEntity.relationList()) {
        Relation triqRelation = relationMap.get(r.getFullName());
        for (Object o : r.linkedObjectsList()) {
          if (o instanceof IORelation) {
            Relation triqLinkedRelation = relationMap.get(((IORelation) o).getFullName());
            if (!triqRelation.getLinkedRelations().contains(triqLinkedRelation) && !triqRelation.getLinkingRelations().contains(triqLinkedRelation)) {
              triqLinkedRelation.link(triqRelation);
            }
          }
        }
      }
      for (Entity e : (List<Entity>) compPtEntity.entityList()) {
        linkRelationsForEntity(e, featureProvider);
      }
    }
  }
}
