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
package org.eclipse.triquetrum.workflow.editor.palette;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.palette.ICreationToolEntry;
import org.eclipse.graphiti.palette.IObjectCreationToolEntry;
import org.eclipse.graphiti.ui.editor.IEclipseImageDescriptor;
import org.eclipse.graphiti.ui.internal.editor.GFCreationTool;
import org.eclipse.graphiti.ui.internal.editor.GFPaletteRoot;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * A variation on {@link GFPaletteRoot}, replacing the palette construction based on creation features 
 * by a palette based on Triquetrum PaletteContribution extension implementations.
 */
public class TriqPaletteRoot extends PaletteRoot {

  private IDiagramTypeProvider diagramTypeProvider;

  /**
   * 
   * @param diagramTypeProvider
   *          the diagram type provider
   */
  public TriqPaletteRoot(IDiagramTypeProvider diagramTypeProvider) {
    super();
    if (diagramTypeProvider == null) {
      throw new IllegalArgumentException("diagramTypeProvider must be specified");
    }
    this.diagramTypeProvider = diagramTypeProvider;
  }

  public IDiagramTypeProvider getDiagramTypeProvider() {
    return diagramTypeProvider;
  }

  private PaletteEntry createTool(ICreationToolEntry creationToolEntry) {
    String label = creationToolEntry.getLabel();
    String description = creationToolEntry.getDescription();
    if (creationToolEntry instanceof IObjectCreationToolEntry) {
      IObjectCreationToolEntry objectCreationToolEntry = (IObjectCreationToolEntry) creationToolEntry;
      DefaultCreationFactory cf = new DefaultCreationFactory(objectCreationToolEntry.getCreateFeature(), ICreateFeature.class);
      CombinedTemplateCreationEntry pe = new CombinedTemplateCreationEntry(label, description, cf, cf, getImageDescriptor(creationToolEntry, true),
          getImageDescriptor(creationToolEntry, false));
      pe.setToolClass(GFCreationTool.class);
      return pe;
    }
    return null;
  }

  private class DefaultCreationFactory implements CreationFactory {
    private Object obj;
    private Object objType;

    public DefaultCreationFactory(Object obj, Object objType) {
      this.obj = obj;
      this.objType = objType;
    }

    public Object getNewObject() {
      return obj;
    }

    public Object getObjectType() {
      return objType;
    }
  }

  private ImageDescriptor getImageDescriptor(ICreationToolEntry creationToolEntry, boolean smallImage) {
    ImageDescriptor imageDescriptor = null;
    if (creationToolEntry instanceof IEclipseImageDescriptor) {
      imageDescriptor = ((IEclipseImageDescriptor) creationToolEntry).getImageDescriptor();
    } else {
      String iconId = (smallImage) ? creationToolEntry.getIconId() : creationToolEntry.getLargeIconId();
      imageDescriptor = GraphitiUi.getImageService().getImageDescriptorForId(diagramTypeProvider.getProviderId(), iconId);
    }
    return imageDescriptor;
  }
}
