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

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.CreateContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramTypeProvider;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementCreateFeature;
import org.eclipse.triquetrum.workflow.model.TriqFactory;

import ptolemy.actor.CompositeActor;
import ptolemy.actor.Director;
import ptolemy.data.expr.Parameter;
import ptolemy.kernel.Entity;
import ptolemy.kernel.Port;
import ptolemy.kernel.Relation;
import ptolemy.kernel.util.Location;
import ptolemy.kernel.util.NamedObj;
import ptolemy.vergil.kernel.attributes.TextAttribute;

/**
 * This command can visit the model element tree of a Ptolemy II CompositeActor
 * to add the corresponding diagram elements in a new Triquetrum workflow Diagram.
 *
 */
public class FillDiagramFromPtolemyModelCommand extends RecordingCommand {

  private TransactionalEditingDomain editingDomain;
  private Diagram diagram;
  private CompositeActor ptolemyModel;
  private IFolder saveLocation;
  private Resource createdResource;

  /**
   *
   * @param saveLocation the folder where the new diagram file should be stored
   * @param editingDomain
   * @param diagram an (empty normally) Triq diagram that will be filled with new model elements
   * @param ptolemyModel the model that must be imported into the diagram
   */
  public FillDiagramFromPtolemyModelCommand(IFolder saveLocation, TransactionalEditingDomain editingDomain, Diagram diagram, CompositeActor ptolemyModel) {
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
    org.eclipse.triquetrum.workflow.model.CompositeActor model = TriqFactory.eINSTANCE.createCompositeActor();
    model.setWrappedObject(ptolemyModel);
    diagram.eResource().getContents().add(model);
    featureProvider.link(diagram, model);

    // Get the director as a first trial to add a new diagram element from ptolemy model elements
    Director director = ptolemyModel.getDirector();
    createModelElement(featureProvider, director);

    // we don't import all attributes as lots of them are ptolemy-internal
    // so we take parameters ...
    for(Parameter p : (List<Parameter>)ptolemyModel.attributeList(Parameter.class)) {
      createModelElement(featureProvider, p);
    }
    // ... and annotations (i.e. TextAttributes)
    for(TextAttribute a : (List<TextAttribute>)ptolemyModel.attributeList(TextAttribute.class)) {
      createModelElement(featureProvider, a);
    }

    for(Port p : (List<Port>)ptolemyModel.portList()) {
      createModelElement(featureProvider, p);
    }

    for(Entity entity : (List<Entity>)ptolemyModel.entityList(Entity.class)) {
      createModelElement(featureProvider, entity);
    }

    for(Relation rel : (List<Relation>)ptolemyModel.relationList()) {

    }



    // TODO the code above only allows to import ptolemy model elements that are configured in the editor palette!
    // so we may need to handle a generic creation feature here that works on the info as found in the ptolemy model,
    // and would only fail if the underlying ptolemy implementation class is not found in the Triq runtime??
  }

  protected org.eclipse.triquetrum.workflow.model.NamedObj createModelElement(IFeatureProvider featureProvider, NamedObj ptObject) {
    org.eclipse.triquetrum.workflow.model.NamedObj result = null;
    Location location = _getLocation(ptObject);
    int x = (int) (location!=null ? location.getLocation()[0] : 10);
    int y = (int) (location!=null ? location.getLocation()[1] : 10);
    CreateContext context = new CreateContext();
    context.setTargetContainer(diagram);
    context.setX(x);
    context.setY(y);
    for (ICreateFeature createFeature : featureProvider.getCreateFeatures()) {
      if (createFeature instanceof ModelElementCreateFeature) {
        ModelElementCreateFeature mecf = (ModelElementCreateFeature) createFeature;
        if (ptObject.getClass().getName().equals(mecf.getWrappedClass())) {
          mecf.setWrappedObject(ptObject);
          result = (org.eclipse.triquetrum.workflow.model.NamedObj) mecf.create(context)[0];
//          result.setWrappedObject(ptObject);
          break;
        }
      }
    }
    return result;
  }

  /**
   * @return the createdResource
   */
  public Resource getCreatedResource() {
    return createdResource;
  }


  private static Location _getLocation(NamedObj object) {
    List<Location> locations = object.attributeList(Location.class);
    if (locations.size() > 0) {
        return locations.get(0);
    }
    return null;
  }

}
