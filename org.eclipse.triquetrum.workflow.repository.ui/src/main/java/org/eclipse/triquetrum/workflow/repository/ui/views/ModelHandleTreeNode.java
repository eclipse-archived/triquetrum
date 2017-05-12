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
package org.eclipse.triquetrum.workflow.repository.ui.views;

import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.triquetrum.workflow.ModelHandle;

class ModelHandleTreeNode extends TreeNode {
  public ModelHandleTreeNode(ModelCodeTreeNode parent, ModelHandle modelHandle) {
    super(modelHandle);
    setParent(parent);
  }

  public ModelHandle getValue() {
    return (ModelHandle)super.getValue();
  }
  
  @Override
  public ModelCodeTreeNode getParent() {
    return (ModelCodeTreeNode) super.getParent();
  }

  public String toString() {
    return getValue().getVersion().toString();
  }
}