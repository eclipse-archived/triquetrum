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
import org.eclipse.triquetrum.workflow.WorkflowRepositoryService;

class WorkflowRepositoryTreeNode extends TreeNode {
  
  public WorkflowRepositoryTreeNode(WorkflowRepositoryService repoSvc) {
    super(repoSvc);
  }

  public WorkflowRepositoryService getValue() {
    return (WorkflowRepositoryService) super.getValue();
  }
  
  public WorkflowRepositoryService getRepository() {
    return getValue();
  }

  public ModelCodeTreeNode[] getChildren() {
    try {
      String[] allCodes = getValue().getAllModelCodes();
      return Arrays.stream(allCodes).map(cd -> new ModelCodeTreeNode(this, cd)).toArray(ModelCodeTreeNode[]::new);
    } catch (Exception e) {
      return new ModelCodeTreeNode[0];
    }
  }
  
  @Override
  public boolean hasChildren() {
    return getValue().getAllModelCodes().length > 0;
  }
  
  public String toString() {
    return getValue().toString();
  }
}