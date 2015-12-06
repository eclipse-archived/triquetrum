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
package org.eclipse.triquetrum.workflow.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;
import org.eclipse.triquetrum.workflow.editor.features.ActorAddFeature;
import org.eclipse.triquetrum.workflow.editor.features.ActorUpdateFeature;
import org.eclipse.triquetrum.workflow.editor.features.ConnectionAddFeature;
import org.eclipse.triquetrum.workflow.editor.features.ConnectionCreateFeature;
import org.eclipse.triquetrum.workflow.editor.features.DirectorAddFeature;
import org.eclipse.triquetrum.workflow.editor.features.DirectorUpdateFeature;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementCreateFeature;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementNameDirectEditFeature;
import org.eclipse.triquetrum.workflow.editor.features.ParameterUpdateFeature;
import org.eclipse.triquetrum.workflow.model.Actor;
import org.eclipse.triquetrum.workflow.model.Director;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Parameter;
import org.eclipse.triquetrum.workflow.model.Relation;

/**
 * @author delerw
 *
 */
public class TriqFeatureProvider extends DefaultFeatureProvider {
  public static final String PALETTE_CONTRIBUTION_EXTENSION_ID = "org.eclipse.triquetrum.workflow.editor.paletteContribution";

  /**
   * @param dtp
   */
  public TriqFeatureProvider(IDiagramTypeProvider dtp) {
    super(dtp);
  }

  @Override
  public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
    // simply return all create connection features
    return getCreateConnectionFeatures();
  }

  @Override
  public ICreateConnectionFeature[] getCreateConnectionFeatures() {
    return new ICreateConnectionFeature[] { new ConnectionCreateFeature(this) };
  }

  @Override
  public ICreateFeature[] getCreateFeatures() {
    List<ICreateFeature> results = new ArrayList<>();
    IExtensionPoint extPoint = Platform.getExtensionRegistry().getExtensionPoint(PALETTE_CONTRIBUTION_EXTENSION_ID);
    for (IExtension ext : extPoint.getExtensions()) {
      for (IConfigurationElement cfgElem : ext.getConfigurationElements()) {
        handlePaletteEntry(results, cfgElem);
      }
    }
    return results.toArray(new ICreateFeature[0]);
  }

  @Override
  public IAddFeature getAddFeature(IAddContext context) {
    if (context.getNewObject() instanceof Director) {
      return new DirectorAddFeature(this);
    } else if (context.getNewObject() instanceof Actor) {
      return new ActorAddFeature(this);
    } else if (context.getNewObject() instanceof Relation) {
      return new ConnectionAddFeature(this);
    }
    return super.getAddFeature(context);
  }

  @Override
  public IUpdateFeature getUpdateFeature(IUpdateContext context) {
    PictogramElement pictogramElement = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    if (bo instanceof Parameter) {
      return new ParameterUpdateFeature(this);
    } else if (bo instanceof Director) {
      return new DirectorUpdateFeature(this);
    } else if (bo instanceof Actor) {
      return new ActorUpdateFeature(this);
    }
    return super.getUpdateFeature(context);
  }

  @Override
  public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
    PictogramElement pe = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pe);
    if (bo instanceof NamedObj) {
      return new ModelElementNameDirectEditFeature(this);
    }
    return super.getDirectEditingFeature(context);
  }

  public void handlePaletteEntry(List<ICreateFeature> results, IConfigurationElement cfgElem) {
    switch (cfgElem.getName()) {
    case "entry": {
      String label = cfgElem.getAttribute("displayName");
      String clazz = cfgElem.getAttribute("class");
//      String iconResource = cfgElem.getAttribute("icon");
      String eClassName = cfgElem.getAttribute("type");
      ModelElementCreateFeature mecf = new ModelElementCreateFeature(this, eClassName, label, clazz);
      results.add(mecf);
      break;
    }
    case "group": {
//      String label = cfgElem.getAttribute("displayName");
//      String iconResource = cfgElem.getAttribute("icon");
      // TODO store group hierarchy somehow in the features or so?
      for (IConfigurationElement child : cfgElem.getChildren()) {
        handlePaletteEntry(results, child);
      }
    }
    }
  }
}
