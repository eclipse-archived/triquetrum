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
package org.eclipse.triquetrum.workflow.editor.features;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.Attribute;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.Director;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.TriqFactory;
import org.eclipse.triquetrum.workflow.model.TriqPackage;


/**
 * Creates a new model element based on a drag-n-drop from the palette, after prompting the user for the name.
 *
 */
public class ModelElementCreateFeature extends AbstractCreateFeature {

  private String group;

  private String category;
  private String elementName;
  private String wrappedClass;
  private String imageId;
  private Map<String, String> properties = new HashMap<>();

  public ModelElementCreateFeature(IFeatureProvider fp, String group, String category, String elementName, String wrappedClass, String imageId, Map<String, String> properties) {
    super(fp, elementName, "Create a " + elementName);
    this.group = group;
    this.category = category;
    this.elementName = elementName;
    this.wrappedClass = wrappedClass;
    this.imageId = imageId;
    if(properties!=null) {
      this.properties.putAll(properties);
    }
  }

  public String getCategory() {
    return category;
  }

  public String getWrappedClass() {
    return wrappedClass;
  }

  public String getElementName() {
    return elementName;
  }

  /**
   * This can be used e.g. to determine the compartment in which to show this feature's creation tool in the palette.
   *
   * @return the group to which this feature belongs.
   */
  public String getGroup() {
    return group;
  }

  @Override
  public String getCreateImageId() {
    return imageId;
  }

  public boolean canCreate(ICreateContext context) {
    if(!(context.getTargetContainer() instanceof Diagram)) {
      return false;
    } else {
      // make sure we can only set 1 Director per model level
      EClassifier eClassifier = TriqPackage.eINSTANCE.getEClassifier(category);
      if(TriqPackage.DIRECTOR == eClassifier.getClassifierID()) {
        CompositeActor model = EditorUtils.getSelectedModel();
        return model==null || model.getDirector() == null;
      }
      return true;
    }
  }

  public Object[] create(ICreateContext context) {
    try {
//      CompositeActor model = EditorUtils.getSelectedModel();

      CompositeActor model = (CompositeActor) getBusinessObjectForPictogramElement(getDiagram());
      if (model == null) {
        model = TriqFactory.eINSTANCE.createCompositeActor();
        model.setName(getDiagram().getName());
        // we need to force the construction of the root Ptolemy II toplevel here,
        // otherwise the addition of the new model element can not use ptolemy features
        // like determining unique names etc.
        model.buildWrappedObject();
        getDiagram().eResource().getContents().add(model);
        link(getDiagram(), model);
      }

      // create new model element
      EClassifier eClassifier = TriqPackage.eINSTANCE.getEClassifier(category);
      NamedObj result = (NamedObj) TriqFactory.eINSTANCE.create((EClass) eClassifier);
      result.setName(EditorUtils.buildUniqueName(model, elementName));
      result.setWrappedType(wrappedClass);
      // TODO review if we can live with a palette with properties without a class specification
      for(Map.Entry<String, String> attrEntry : properties.entrySet()) {
        String name = attrEntry.getKey();
        String value = attrEntry.getValue();
        result.setProperty(name, value, null);
      }

      if (result instanceof Director) {
        model.setDirector((Director) result);
      } else if (result instanceof Entity) {
        model.getEntities().add((Entity) result);
      } else if (result instanceof Attribute) {
        model.getAttributes().add((Attribute) result);
      } else if (result instanceof Port) {
        Port p = (Port) result;
        if(p.isInput()) {
          model.getInputPorts().add(p);
        }
        if(p.isOutput()) {
          model.getOutputPorts().add(p);
        }
      }

      // set the icon in the create context, so the add feature can add it in the element's shape
      context.putProperty("icon", getCreateImageId());
      // do the add
      addGraphicalRepresentation(context, result);
      // activate direct editing after object creation
      getFeatureProvider().getDirectEditingInfo().setActive(true);
      // return newly created business object(s)
      return new Object[] { result };
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
