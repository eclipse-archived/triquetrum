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
package org.eclipse.triquetrum.workflow.editor.palette;

import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;

/**
 * A variation on GFPaletteRoot, replacing the palette construction based on creation features by a palette based on Triquetrum PaletteContribution extension
 * implementations (implemented in TriqFeatureProvider).
 */
public class TriqPaletteRoot extends PaletteRoot {

  private IDiagramTypeProvider diagramTypeProvider;

  /**
   *
   * @param diagramTypeProvider
   *          the diagram type provider
   */
  public TriqPaletteRoot(IDiagramTypeProvider diagramTypeProvider) {
    super();
    if (diagramTypeProvider == null) {
      throw new IllegalArgumentException("diagramTypeProvider must be specified");
    }
    this.diagramTypeProvider = diagramTypeProvider;
  }

  public IDiagramTypeProvider getDiagramTypeProvider() {
    return diagramTypeProvider;
  }

  public static class DefaultCreationFactory implements CreationFactory {
    private Object obj;
    private Object objType;

    public DefaultCreationFactory(Object obj, Object objType) {
      this.obj = obj;
      this.objType = objType;
    }

    @Override
    public Object getNewObject() {
      return obj;
    }

    @Override
    public Object getObjectType() {
      return objType;
    }
  }
}
