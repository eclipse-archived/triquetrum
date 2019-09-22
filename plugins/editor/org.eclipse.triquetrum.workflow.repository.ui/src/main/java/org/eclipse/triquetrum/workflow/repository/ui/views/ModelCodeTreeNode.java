/*******************************************************************************
 * Copyright (c) 2017,2019 iSencia Belgium NV.
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
package org.eclipse.triquetrum.workflow.repository.ui.views;

import java.util.Arrays;

import org.eclipse.triquetrum.workflow.EntryNotFoundException;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.eclipse.triquetrum.workflow.WorkflowRepositoryService;

class ModelCodeTreeNode extends AbstractTreeNode {

  public ModelCodeTreeNode(WorkflowRepositoryTreeNode parent, String modelCode) {
    super(modelCode);
    setParent(parent);
  }

  public String getValue() {
    return (String) super.getValue();
  }
  
  public String getModelCode() {
    return getValue();
  }
  
  public ModelHandle getActiveModelHandle() throws EntryNotFoundException {
    return getRepository().getActiveModel(getModelCode());
  }
  
  public WorkflowRepositoryService getRepository() {
    return getParent().getValue();
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