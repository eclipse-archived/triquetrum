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

import java.util.ArrayList;
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
    List<IConfigurationElement> resultsList = new ArrayList<>();
    LibraryManager libraryManager = LibraryManager.getActiveInstance();
    EntityLibrary userLibrary = libraryManager != null ? libraryManager.getUserLibrary() : null;
    if (userLibrary != null) {
      for (Entity<?> entity : (List<Entity<?>>) userLibrary.entityList()) {
        Map<String, String> attributes = new HashMap<>();
        String pceType = "entry";
        if (entity instanceof EntityLibrary) {
          attributes.put(DISPLAY_NAME, entity.getDisplayName());
          pceType = "group";
        } else {
          attributes.put(CLASS, entity.getClassName());
          attributes.put(DISPLAY_NAME, entity.getDisplayName());
          attributes.put(TYPE, (entity instanceof CompositeEntity) ? BoCategory.CompositeActor.name() : BoCategory.Actor.name());
        }
        PaletteConfigurationElement pce = new PaletteConfigurationElement(pceType, "org.eclipse.triquetrum.workflow.editor", attributes);
        resultsList.add(pce);
      }
    }
    return resultsList.toArray(new IConfigurationElement[0]);
  }
}
