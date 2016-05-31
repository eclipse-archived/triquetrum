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

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.ui.editor.DefaultPaletteBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.internal.editor.GFCreationTool;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramTypeProvider;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.triquetrum.workflow.editor.TriqFeatureProvider;
import org.eclipse.triquetrum.workflow.editor.palette.ui.PaletteTreeViewerProvider;

/**
 * Interprets Triquetrum PaletteContribution extensions and constructs a palette group/entry hierarchy.
 *
 * The underlying model reuses PaletteEntry and PaletteGroup classes from GEF.
 */
public class TriqPaletteBehavior extends DefaultPaletteBehavior {

  public static final String PALETTE_CONTRIBUTION_EXTENSION_ID = "org.eclipse.triquetrum.workflow.editor.paletteContribution";

  public TriqPaletteBehavior(DiagramBehavior diagramBehavior) {
    super(diagramBehavior);
  }

  @Override
  protected PaletteViewerProvider createPaletteViewerProvider() {
//    return super.createPaletteViewerProvider();
    return new PaletteTreeViewerProvider(diagramBehavior);
  }

  @Override
  protected PaletteRoot createPaletteRoot() {
    TriqPaletteRoot result = new TriqPaletteRoot(getDiagramTypeProvider());
    IExtensionPoint extPoint = Platform.getExtensionRegistry().getExtensionPoint(PALETTE_CONTRIBUTION_EXTENSION_ID);
    IExtension[] extensions = extPoint.getExtensions();
    for (IExtension ext : extensions) {
      IConfigurationElement[] cfgElements = ext.getConfigurationElements();
      for (IConfigurationElement cfgElem : cfgElements) {
        handlePaletteEntry(result, null, cfgElem);
      }
    }
    return result;
  }

  public void handlePaletteEntry(PaletteContainer parent, IConfigurationElement parentGroupElem, IConfigurationElement cfgElem) {
    String label = cfgElem.getAttribute("displayName");
    String iconType = cfgElem.getAttribute("iconType");
    iconType = StringUtils.isBlank(iconType) ? TriqFeatureProvider.ICONTYPE_IMG : iconType;
    String iconResource = cfgElem.getAttribute("icon");
    iconResource = !StringUtils.isBlank(iconResource) ? iconResource : null;
    iconResource = TriqFeatureProvider.ICONTYPE_IMG.equalsIgnoreCase(iconType) ? iconResource : null;
    ImageDescriptor imgDescriptor = null;
    if(iconResource!=null) {
      getDiagramTypeProvider().getImageProvider().myAddImageFilePath(cfgElem.getContributor().getName(), iconResource, iconResource);
      imgDescriptor = TriqEditorPlugin.imageDescriptorFromPlugin(cfgElem.getDeclaringExtension().getContributor().getName(), iconResource);
    }
    switch (cfgElem.getName()) {
    case "entry": {
      String clazz = cfgElem.getAttribute("class");
      ICreateFeature createFeature = getFeatureProvider().buildCreateFeature(parentGroupElem, cfgElem);
      TriqPaletteRoot.DefaultCreationFactory cf = new TriqPaletteRoot.DefaultCreationFactory(createFeature, ICreateFeature.class);
      CombinedTemplateCreationEntry pe = new CombinedTemplateCreationEntry(label, clazz, cf, cf, imgDescriptor, imgDescriptor);
      pe.setToolClass(GFCreationTool.class);
      parent.add(pe);
      break;
    }
    case "group": {
      PaletteTreeNode pg = new PaletteTreeNode(label);
      pg.setSmallIcon(imgDescriptor);
      parent.add(pg);
      for (IConfigurationElement child : cfgElem.getChildren()) {
        handlePaletteEntry(pg, cfgElem, child);
      }
    }
    }
  }

  private TriqFeatureProvider getFeatureProvider() {
    return (TriqFeatureProvider) getDiagramTypeProvider().getFeatureProvider();
  }
  private TriqDiagramTypeProvider getDiagramTypeProvider() {
    return (TriqDiagramTypeProvider) this.diagramBehavior.getDiagramTypeProvider();
  }
}
