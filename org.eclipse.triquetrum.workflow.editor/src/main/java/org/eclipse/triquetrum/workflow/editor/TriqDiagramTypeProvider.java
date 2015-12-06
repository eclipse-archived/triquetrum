/*******************************************************************************
 * Copyright (c) 2015 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

public class TriqDiagramTypeProvider extends AbstractDiagramTypeProvider {
  
  public static final String ID = "org.eclipse.triquetrum.workflow.editor.TriqDiagramTypeProvider";

  private IToolBehaviorProvider[] toolBehaviorProviders;

  public TriqDiagramTypeProvider() {
    setFeatureProvider(new TriqFeatureProvider(this));
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
