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
package org.eclipse.triquetrum.workflow.model.util;

import org.eclipse.triquetrum.workflow.model.NamedObj;

public class PtObjectBuilderVisitor implements Visitor {

  @Override
  public void visit(NamedObj modelElement) {
    modelElement.buildWrappedObject();
  }

}
