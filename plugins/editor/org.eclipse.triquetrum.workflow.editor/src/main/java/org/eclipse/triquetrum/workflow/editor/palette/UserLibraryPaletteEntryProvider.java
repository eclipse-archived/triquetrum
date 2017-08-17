/*******************************************************************************
 * Copyright (c) 2017 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.palette;

import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.CLASS;
import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.DISPLAY_NAME;
import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.TYPE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement;
import org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteEntryProvider;

import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.Entity;
import ptolemy.moml.EntityLibrary;

public class UserLibraryPaletteEntryProvider implements PaletteEntryProvider {

  @Override
  public IConfigurationElement[] getPaletteEntries() {
    LibraryManager libraryManager = LibraryManager.getActiveInstance();
    EntityLibrary userLibrary = libraryManager != null ? libraryManager.getUserLibrary() : null;
    if (userLibrary != null) {
      Map<String, String> attributes = new HashMap<>();
      attributes.put(DISPLAY_NAME, LibraryManager.USER_LIBRARY_NAME);
      PaletteConfigurationElement pce = new PaletteConfigurationElement("group", "org.eclipse.triquetrum.workflow.editor", attributes);
      buildEntriesForLibrary(pce, (List<Entity<?>>) userLibrary.entityList());
      return new IConfigurationElement[] {pce};
    } else {
      return new IConfigurationElement[0];
    }
  }

  private void buildEntriesForLibrary(PaletteConfigurationElement parentPCE, List<Entity<?>> libraryEntries) {
    for (Entity<?> entity : libraryEntries) {
      Map<String, String> attributes = new HashMap<>();
      String pceType = "entry";
      List<Entity<?>> children = null;
      attributes.put(DISPLAY_NAME, entity.getDisplayName());
      if (entity instanceof EntityLibrary) {
        pceType = "group";
        children = (List<Entity<?>>) ((EntityLibrary)entity).entityList();
      } else {
        attributes.put(CLASS, entity.getClassName());
        attributes.put(TYPE, (entity instanceof CompositeEntity) ? BoCategory.CompositeActor.name() : BoCategory.Actor.name());
      }
      PaletteConfigurationElement pce = new PaletteConfigurationElement(pceType, "org.eclipse.triquetrum.workflow.editor", attributes);
      if(children!=null) {
        buildEntriesForLibrary(pce, children);
      }
      if(parentPCE!=null) {
        parentPCE.addChild(entity.getDisplayName(), pce);
      }
    }
  }
}
