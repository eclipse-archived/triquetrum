/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor;

import java.util.Collection;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.internal.platform.ExtensionManager;
import org.eclipse.graphiti.ui.platform.IImageProvider;

public class TriqDiagramTypeProvider extends AbstractDiagramTypeProvider {

  public static final String ID = "org.eclipse.triquetrum.workflow.editor.TriqDiagramTypeProvider";
  public static final String DIAGRAMTYPE = "Triquetrum workflow";

  private IToolBehaviorProvider[] toolBehaviorProviders;
  private ImageProvider imageProvider;

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
