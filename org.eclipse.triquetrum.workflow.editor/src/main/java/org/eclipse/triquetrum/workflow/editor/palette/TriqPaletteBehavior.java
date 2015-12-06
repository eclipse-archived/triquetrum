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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.ui.editor.DefaultPaletteBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;

/**
 * Interprets Triquetrum PaletteContribution extensions and constructs a palette group/entry hierarchy.
 * 
 * The underlying model reuses PaletteEntry and PaletteGroup classes from GEF.
 * 
 * TODO : react on dynamically registered extra palette contributions, registered in a running workbench.
 */
public class TriqPaletteBehavior extends DefaultPaletteBehavior {

  public static final String PALETTE_CONTRIBUTION_EXTENSION_ID = "org.eclipse.triquetrum.workflow.editor.paletteContribution";

  public TriqPaletteBehavior(DiagramBehavior diagramBehavior) {
    super(diagramBehavior);
  }

  @Override
  protected PaletteRoot createPaletteRoot() {
    TriqPaletteRoot result = new TriqPaletteRoot(getDiagramTypeProvider());
    IExtensionPoint extPoint = Platform.getExtensionRegistry().getExtensionPoint(PALETTE_CONTRIBUTION_EXTENSION_ID);
    for (IExtension ext : extPoint.getExtensions()) {
      for (IConfigurationElement cfgElem : ext.getConfigurationElements()) {
        handlePaletteEntry(result, cfgElem);
      }
    }
    return result;
  }

  public void handlePaletteEntry(PaletteContainer parent, IConfigurationElement cfgElem) {
    switch (cfgElem.getName()) {
    case "entry": {
      String label = cfgElem.getAttribute("displayName");
      String clazz = cfgElem.getAttribute("class");
      String iconResource = cfgElem.getAttribute("icon");
      PaletteEntry pe = new PaletteEntry(label, null, clazz);
      pe.setSmallIcon(TriqEditorPlugin.imageDescriptorFromPlugin(cfgElem.getDeclaringExtension().getContributor().getName(), iconResource));
      parent.add(pe);
      break;
    }
    case "group": {
      String label = cfgElem.getAttribute("displayName");
      String iconResource = cfgElem.getAttribute("icon");
      PaletteGroup pg = new PaletteGroup(label);
      pg.setSmallIcon(TriqEditorPlugin.imageDescriptorFromPlugin(cfgElem.getDeclaringExtension().getContributor().getName(), iconResource));
      parent.add(pg);
      for (IConfigurationElement child : cfgElem.getChildren()) {
        handlePaletteEntry(pg, child);
      }
    }
    }
  }
  
  private IDiagramTypeProvider getDiagramTypeProvider() {
    return this.diagramBehavior.getDiagramTypeProvider();
  }
}
