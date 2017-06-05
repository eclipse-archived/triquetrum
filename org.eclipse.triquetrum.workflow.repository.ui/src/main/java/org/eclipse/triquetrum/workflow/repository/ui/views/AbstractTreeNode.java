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
import org.eclipse.triquetrum.workflow.WorkflowRepositoryService;

abstract class AbstractTreeNode extends TreeNode {

  public AbstractTreeNode(Object value) {
    super(value);
  }

  abstract WorkflowRepositoryService getRepository();
}
