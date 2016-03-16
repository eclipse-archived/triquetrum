/*******************************************************************************
 * Copyright (c) 2015-2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.shapes;

import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRenderer;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRendererFactory;
import org.eclipse.graphiti.platform.ga.IRendererContext;
import org.eclipse.triquetrum.workflow.editor.TriqFeatureProvider;

public class TriqGraphicsAlgorithmRendererFactory implements IGraphicsAlgorithmRendererFactory {

  @Override
  public IGraphicsAlgorithmRenderer createGraphicsAlgorithmRenderer(IRendererContext rendererContext) {
    String iconResource = null;
    String iconType = TriqFeatureProvider.ICONTYPE_SVG;
    int translateX = 0;
    int translateY = 0;
    for (Property property: rendererContext.getPlatformGraphicsAlgorithm().getProperties()) {
      switch(property.getKey()) {
      case "iconResource" :
        iconResource = property.getValue();
        break;
      case "iconType" :
        iconType = property.getValue();
        break;
      case "translateX" :
        translateX = Integer.parseInt(property.getValue());
        break;
      case "translateY" :
        translateY = Integer.parseInt(property.getValue());
        break;
      default :
        break;
      }

    }
    switch(iconType) {
    case TriqFeatureProvider.ICONTYPE_PTOLEMY :
      return new PtolemyModelElementShape(iconResource, translateX, translateY);
    case TriqFeatureProvider.ICONTYPE_SVG :
    default :
        return new SvgModelElementShape(iconResource, translateX, translateY);
    }
  }

}
