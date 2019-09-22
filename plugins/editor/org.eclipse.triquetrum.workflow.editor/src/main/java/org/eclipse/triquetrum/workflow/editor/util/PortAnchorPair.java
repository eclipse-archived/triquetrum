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
package org.eclipse.triquetrum.workflow.editor.util;

import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.triquetrum.workflow.editor.PortCategory;
import org.eclipse.triquetrum.workflow.model.Direction;
import org.eclipse.triquetrum.workflow.model.Port;

public final class PortAnchorPair {
  public Port port;
  public Anchor anchor;
  /**
   * @param port
   * @param anchor
   */
  public PortAnchorPair(Port port, Anchor anchor) {
    this.port = port;
    this.anchor = anchor;
  }
  
  /**
   * Calculates the concrete direction for the port, i.e. never DEFAULT.
   * If no specific direction is set as a port property, a calculated direction is returned,
   * matching the input/output nature of the port.
   * 
   * @return the concrete direction for the port
   */
  public Direction getPortDirection() {
    return PortCategory.retrieveFrom(port).getDirection();
  }
}