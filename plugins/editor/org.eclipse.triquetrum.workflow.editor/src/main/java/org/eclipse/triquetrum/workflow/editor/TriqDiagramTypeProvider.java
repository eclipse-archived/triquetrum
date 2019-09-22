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
package org.eclipse.triquetrum.workflow.editor;

import java.util.Collection;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRendererFactory;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.internal.platform.ExtensionManager;
import org.eclipse.graphiti.ui.platform.IImageProvider;
import org.eclipse.triquetrum.workflow.editor.shapes.TriqGraphicsAlgorithmRendererFactory;

public class TriqDiagramTypeProvider extends AbstractDiagramTypeProvider {

  public static final String ID = "org.eclipse.triquetrum.workflow.editor.TriqDiagramTypeProvider";
  public static final String DIAGRAMTYPE = "Triquetrum workflow";

  private IToolBehaviorProvider[] toolBehaviorProviders;
  private ImageProvider imageProvider;
  private IGraphicsAlgorithmRendererFactory factory;

  public TriqDiagramTypeProvider() {
    setFeatureProvider(new TriqFeatureProvider(this));
  }

  public ImageProvider getImageProvider() {
    if (imageProvider != null) {
      return imageProvider;
    } else {
      Collection<IImageProvider> imgProviders = ExtensionManager.getSingleton().getImageProvidersForDiagramTypeProviderId(getProviderId());
      if (imgProviders != null && !imgProviders.isEmpty()) {
        for (IImageProvider iImageProvider : imgProviders) {
          if (iImageProvider instanceof ImageProvider) {
            imageProvider = (ImageProvider) iImageProvider;
          }
        }
      }
      return imageProvider;
    }
  }

  @Override
  public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
    if (toolBehaviorProviders == null) {
      toolBehaviorProviders = new IToolBehaviorProvider[] { new TriqToolBehaviorProvider(this) };
    }
    return toolBehaviorProviders;
  }

  @Override
  public IGraphicsAlgorithmRendererFactory getGraphicsAlgorithmRendererFactory() {
    if (factory == null) {
      factory = new TriqGraphicsAlgorithmRendererFactory();
    }
    return factory;
  }

  @Override
  public boolean isAutoUpdateAtStartup() {
    return true;
  }

  @Override
  public boolean isAutoUpdateAtReset() {
    return true;
  }

  @Override
  public boolean isAutoUpdateAtRuntime() {
    return true;
  }

  @Override
  public boolean isAutoUpdateAtRuntimeWhenEditorIsSaved() {
    return true;
  }
}
