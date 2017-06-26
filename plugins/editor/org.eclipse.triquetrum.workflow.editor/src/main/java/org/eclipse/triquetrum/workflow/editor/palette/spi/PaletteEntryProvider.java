/*******************************************************************************
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.palette.spi;

import org.eclipse.core.runtime.IConfigurationElement;

public interface PaletteEntryProvider {

  /**
   *
   * @return the palette entries from this provider, as configuration elements
   */
  IConfigurationElement[] getPaletteEntries();

}
