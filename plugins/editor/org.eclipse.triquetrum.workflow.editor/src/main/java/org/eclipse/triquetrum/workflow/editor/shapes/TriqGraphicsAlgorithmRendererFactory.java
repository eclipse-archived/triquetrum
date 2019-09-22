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
package org.eclipse.triquetrum.workflow.editor.shapes;

import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRenderer;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRendererFactory;
import org.eclipse.graphiti.platform.ga.IRendererContext;
import org.eclipse.triquetrum.workflow.editor.TriqFeatureProvider;
import org.eclipse.triquetrum.workflow.editor.shapes.ptolemy.PtolemyModelElementShape;
import org.eclipse.triquetrum.workflow.editor.shapes.svg.SvgModelElementShape;

public class TriqGraphicsAlgorithmRendererFactory implements IGraphicsAlgorithmRendererFactory {

  public TriqGraphicsAlgorithmRendererFactory() {
    TriqGarFactorytracker.setGarFactory(this);
  }

  @Override
  public IGraphicsAlgorithmRenderer createGraphicsAlgorithmRenderer(IRendererContext rendererContext) {
    String iconType = null;
    for (Property property : rendererContext.getPlatformGraphicsAlgorithm().getProperties()) {
      if ("iconType".equalsIgnoreCase(property.getKey())) {
        iconType = property.getValue();
        break;
      }
    }
    switch (iconType) {
    case TriqFeatureProvider.ICONTYPE_PTOLEMY:
      return new PtolemyModelElementShape(rendererContext);
    case TriqFeatureProvider.ICONTYPE_SVG:
      return new SvgModelElementShape(rendererContext);
    default:
      return null;
    }
  }

}
