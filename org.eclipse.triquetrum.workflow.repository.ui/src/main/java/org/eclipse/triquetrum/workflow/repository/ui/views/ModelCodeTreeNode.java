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

import java.util.Arrays;

import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.triquetrum.workflow.EntryNotFoundException;
import org.eclipse.triquetrum.workflow.ModelHandle;

class ModelCodeTreeNode extends TreeNode {

  public ModelCodeTreeNode(WorkflowRepositoryTreeNode parent, String modelCode) {
    super(modelCode);
    setParent(parent);
  }

  public String getValue() {
    return (String) super.getValue();
  }
  
  @Override
  public WorkflowRepositoryTreeNode getParent() {
    return (WorkflowRepositoryTreeNode) super.getParent();
  }

  public ModelHandleTreeNode[] getChildren() {
    try {
      ModelHandle[] allModelRevisions = getParent().getValue().getAllModelRevisions(getValue());
      return Arrays.stream(allModelRevisions).map(mh -> new ModelHandleTreeNode(this, mh)).toArray(ModelHandleTreeNode[]::new);
    } catch (EntryNotFoundException e) {
      return new ModelHandleTreeNode[0];
    }
  }
  
  @Override
  public boolean hasChildren() {
    try {
      return getParent().getValue().getAllModelRevisions(getValue()).length > 0;
    } catch (EntryNotFoundException e) {
      return false;
    }
  }

  public String toString() {
    return getValue();
  }
}