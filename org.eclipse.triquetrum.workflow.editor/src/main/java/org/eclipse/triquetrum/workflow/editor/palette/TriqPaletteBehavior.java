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
package org.eclipse.triquetrum.workflow.editor.palette;

import java.util.Collections;
import java.util.Comparator;

import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.*;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.ui.editor.DefaultPaletteBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.internal.editor.GFCreationTool;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramTypeProvider;
import org.eclipse.triquetrum.workflow.editor.TriqFeatureProvider;
import org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteEntryProvider;
import org.eclipse.triquetrum.workflow.editor.palette.ui.PaletteTreeViewerProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

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
    // return super.createPaletteViewerProvider();
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

  @SuppressWarnings("unchecked")
  public synchronized void handlePaletteEntry(PaletteContainer parent, IConfigurationElement parentGroupElem, IConfigurationElement cfgElem) {
    String priorityStr = cfgElem.getAttribute(PRIORITY);
    priorityStr = StringUtils.isBlank(priorityStr) ? "0" : priorityStr;
    Integer priority = 0;
    try {
      priority = Integer.valueOf("-" + priorityStr);
    } catch (NumberFormatException e) {
      // just ignore this and take the default value 0
    }
    String label = cfgElem.getAttribute(DISPLAY_NAME);
    String iconType = cfgElem.getAttribute(ICON_TYPE);
    iconType = StringUtils.isBlank(iconType) ? TriqFeatureProvider.ICONTYPE_IMG : iconType;
    String iconResource = cfgElem.getAttribute(ICON);
    iconResource = !StringUtils.isBlank(iconResource) ? iconResource : null;
    iconResource = TriqFeatureProvider.ICONTYPE_IMG.equalsIgnoreCase(iconType) ? iconResource : null;
    ImageDescriptor imgDescriptor = null;
    if (iconResource != null) {
      getDiagramTypeProvider().getImageProvider().myAddImageFilePath(cfgElem.getContributor().getName(), iconResource, iconResource);
      imgDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(cfgElem.getContributor().getName(), iconResource);
    }
    switch (cfgElem.getName()) {
    case "entry": {
      String clazz = cfgElem.getAttribute(CLASS);
      ICreateFeature createFeature = getFeatureProvider().buildCreateFeature(parentGroupElem, cfgElem);
      TriqPaletteRoot.DefaultCreationFactory cf = new TriqPaletteRoot.DefaultCreationFactory(createFeature, ICreateFeature.class);
      CombinedTemplateCreationEntry pe = new CombinedTemplateCreationEntry(label, clazz, cf, cf, imgDescriptor, imgDescriptor);
      pe.setToolClass(GFCreationTool.class);
      parent.add(pe);
      break;
    }
    case "group": {
      // We assume the nr of entries on each level in the palette will be limited
      // so a straightforward iteration to look for existing groups should not be an issue.
      PaletteTreeNode pg = null;
      for (Object pe : parent.getChildren()) {
        if (pe instanceof PaletteTreeNode && ((PaletteTreeNode)pe).getLabel().equals(label)) {
          pg = (PaletteTreeNode) pe;
          // We're a bit limited to determine the real desired priority
          // when a group is used in different palette contributions
          // as we have no control on the order in which the contributions
          // are handled.
          // So we always go for the highest priority across all contributions.
          // (but remember that we store them as negative values)
          if (pg.getPriority() > priority) {
            pg.setPriority(priority);
          }
          break;
        }
      }
      if (pg == null) {
        pg = new PaletteTreeNode(label);
        pg.setSmallIcon(imgDescriptor);
        pg.setPriority(priority);
        parent.add(pg);
      }
      for (IConfigurationElement child : cfgElem.getChildren()) {
        handlePaletteEntry(pg, cfgElem, child);
      }

      String providerClazz = cfgElem.getAttribute(PROVIDER);
      if(providerClazz!=null) {
        try {
          PaletteEntryProvider pep = (PaletteEntryProvider) cfgElem.createExecutableExtension(PROVIDER);
          for (IConfigurationElement child : pep.getPaletteEntries()) {
            handlePaletteEntry(pg, cfgElem, child);
          }
        } catch (CoreException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }

    }
    }
    Collections.sort(parent.getChildren(), new PaletteEntryComparator());
  }

  private TriqFeatureProvider getFeatureProvider() {
    return (TriqFeatureProvider) getDiagramTypeProvider().getFeatureProvider();
  }

  private TriqDiagramTypeProvider getDiagramTypeProvider() {
    return (TriqDiagramTypeProvider) this.diagramBehavior.getDiagramTypeProvider();
  }

  private static class PaletteEntryComparator implements Comparator<PaletteEntry> {
    @Override
    public int compare(PaletteEntry o1, PaletteEntry o2) {
      if(o1 instanceof PaletteTreeNode) {
        if(o2 instanceof PaletteTreeNode) {
          int result = ((PaletteTreeNode)o1).getPriority().compareTo(((PaletteTreeNode)o2).getPriority());
          if(result != 0) {
            return result;
          }
        } else {
          return 1;
        }
      } else if (o2 instanceof PaletteTreeNode) {
        return -1;
      }
      return o1.getLabel().compareTo(o2.getLabel());
    }
  }
}
