/*******************************************************************************
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.palette.ui;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class PaletteTreeProvider implements ITreeContentProvider {

  /**
   * @see org.eclipse.jface.viewers.IContentProvider#dispose()
   */
  @Override
  public void dispose() {
  }

  /**
   * If the given element does not have any children, this method should return <code>null</code>. This fixes the problem where a "+" sign is incorrectly placed
   * next to an empty container in the tree.
   *
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(Object)
   */
  @Override
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof List) {
      return ((List) parentElement).toArray();
    }
    if (parentElement instanceof EditPart) {
      List children = ((EditPart) parentElement).getChildren();
      if (!children.isEmpty()) {
        return children.toArray();
      }
    }
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(Object)
   */
  @Override
  public boolean hasChildren(Object element) {
    return getChildren(element) != null;
  }

  /**
   * This method should not return <code>null</code>.
   *
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(Object)
   */
  @Override
  public Object[] getElements(Object inputElement) {
    Object[] elements = getChildren(inputElement);
    if (elements == null) {
      elements = new Object[0];
    }
    return elements;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(Object)
   */
  @Override
  public Object getParent(Object element) {
    return ((EditPart) element).getParent();
  }

  /**
   * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(Viewer, Object, Object)
   */
  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }
}
