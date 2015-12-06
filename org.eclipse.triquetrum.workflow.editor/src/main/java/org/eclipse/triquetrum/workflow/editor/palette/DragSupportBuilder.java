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
package org.eclipse.triquetrum.workflow.editor.palette;

import org.eclipse.jface.viewers.TreeViewer;

/**
 * Interface to allow varying drag support, especially the CreationFactories used, 
 * matching the active model editor and its drop requirements.
 *  
 */
public interface DragSupportBuilder {

  /**
   * Adds the correctly configured drag support, consisting of the supported operations, a drag target listener,
   * and the associated CreationFactory.
   * @param treeViewer
   */
  void addDragSupport(TreeViewer treeViewer);
  
}
