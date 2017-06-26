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