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

import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.triquetrum.workflow.WorkflowRepositoryService;

abstract class AbstractTreeNode extends TreeNode {

  public AbstractTreeNode(Object value) {
    super(value);
  }

  abstract WorkflowRepositoryService getRepository();
}
