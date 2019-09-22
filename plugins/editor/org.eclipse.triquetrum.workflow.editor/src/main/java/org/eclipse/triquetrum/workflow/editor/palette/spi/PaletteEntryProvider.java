/*******************************************************************************
 * Copyright (c) 2016,2019 iSencia Belgium NV.
 *  
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Erwin De Ley - initial API and implementation and/or initial documentation
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
