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
package org.eclipse.triquetrum.workflow.editor.wizard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
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

import ptolemy.actor.Director;
import ptolemy.actor.IOPort;
import ptolemy.actor.IORelation;
import ptolemy.data.expr.Parameter;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.Entity;
import ptolemy.kernel.util.Location;
import ptolemy.kernel.util.NamedObj;
import ptolemy.vergil.kernel.attributes.TextAttribute;

/**
 * This command can visit the model element tree of a Ptolemy II CompositeActor to add the corresponding diagram elements in a new Triquetrum workflow Diagram.
 *
 */
public class FillDiagramFromPtolemyModelCommand extends RecordingCommand {
  private final static Logger LOGGER = LoggerFactory.getLogger(FillDiagramFromPtolemyModelCommand.class);

  private TransactionalEditingDomain editingDomain;
  private Diagram diagram;
  private ptolemy.actor.CompositeActor ptolemyModel;
  private IFolder saveLocation;
  private Resource createdResource;

  // A cache maintained during importing, to easily retrieve anchors
  // for creating relations based on the names of the corresponding linked ports in the Ptolemy model.
  private Map<String, Anchor> anchorMap = new HashMap<>();
  private Map<String, Relation> relationMap = new HashMap<>();

  /**
   *
   * @param saveLocation
   *          the folder where the new diagram file should be stored
   * @param editingDomain
   * @param diagram
   *          an (empty normally) Triq diagram that will be filled with new model elements
   * @param ptolemyModel
   *          the model that must be imported into the diagram
   */
  public FillDiagramFromPtolemyModelCommand(IFolder saveLocation, TransactionalEditingDomain editingDomain, Diagram diagram,
      ptolemy.actor.CompositeActor ptolemyModel) {
    super(editingDomain);
    this.saveLocation = saveLocation;
    this.editingDomain = editingDomain;
    this.diagram = diagram;
    this.ptolemyModel = ptolemyModel;
  }

  @Override
  protected void doExecute() {
    IFile diagramFile = saveLocation.getFile(diagram.getName() + ".tdml"); //$NON-NLS-1$
    URI uri = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
    createdResource = editingDomain.getResourceSet().createResource(uri);
    createdResource.getContents().add(diagram);
    IDiagramTypeProvider dtp = GraphitiUi.getExtensionManager().createDiagramTypeProvider(diagram, TriqDiagramTypeProvider.ID);
    IFeatureProvider featureProvider = dtp.getFeatureProvider();

    // construct the root compositeactor, wrapping the imported ptolemy model
    CompositeActor model = TriqFactory.eINSTANCE.createCompositeActor();
    model.setWrappedObject(ptolemyModel);
    diagram.eResource().getContents().add(model);
    featureProvider.link(diagram, model);

    // Get the director as a first trial to add a new diagram element from ptolemy model elements
    Director director = ptolemyModel.getDirector();
    createModelElement(model, featureProvider, director, model);

    for (IORelation rel : (List<IORelation>) ptolemyModel.relationList()) {
      relationMap.put(rel.getFullName(), createRelation(model, featureProvider, rel));
    }

    // we don't import all attributes as lots of them are ptolemy-internal
    // TODO find something to resolve errors related to the order of adding parameters
    // that contain expressions with references to other parameters that may be later in the list.

    // First we take parameters ...
    for (Parameter p : (List<Parameter>) ptolemyModel.attributeList(Parameter.class)) {
      createModelElement(model, featureProvider, p, model);
    }
    // ... and annotations (i.e. TextAttributes)
    for (TextAttribute a : (List<TextAttribute>) ptolemyModel.attributeList(TextAttribute.class)) {
      createModelElement(model, featureProvider, a, model);
    }

    for (IOPort p : (List<IOPort>) ptolemyModel.portList()) {
      // TODO ensure the port anchors are added to the anchorMap
      createModelElement(model, featureProvider, p, model);
    }

    for (Entity entity : (List<Entity>) ptolemyModel.entityList(Entity.class)) {
      // TODO ensure the actors' port anchors are added to the anchorMap
      createModelElement(model, featureProvider, entity, model);
    }

    linkRelations(ptolemyModel, model, featureProvider);
  }

  protected org.eclipse.triquetrum.workflow.model.NamedObj createModelElement(org.eclipse.triquetrum.workflow.model.CompositeActor model,
      IFeatureProvider featureProvider, NamedObj ptObject, org.eclipse.triquetrum.workflow.model.NamedObj triqContainer) {

    org.eclipse.triquetrum.workflow.model.NamedObj result = null;
    Location location = _getLocation(ptObject);
    int x = (int) (location != null ? location.getLocation()[0] : 10);
    int y = (int) (location != null ? location.getLocation()[1] : 10);
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
        ModelElementCreateFeature mecf = new ModelElementCreateFeature(featureProvider, null, meCategory, ptObject.getName());
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
            triqRelation.link(triqPort);
          } else {
            System.err.println("Triq relation not found for " + ptRelation + "in port " + ptPort);
          }
        }
      } else {
        System.err.println("Triq port not found for " + ptPort);
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

  /**
   * @return the createdResource
   */
  public Resource getCreatedResource() {
    return createdResource;
  }

  private static Location _getLocation(NamedObj object) {
    if (object instanceof Location) {
      return (Location) object;
    } else {
      List<Location> locations = object.attributeList(Location.class);
      if (locations.size() > 0) {
        return locations.get(0);
      }
    }
    return null;
  }
}
