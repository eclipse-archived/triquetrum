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
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

/**
 * Based on org.eclipse.gef.ui.palette.customize.PaletteTreeProvider. (which is not visible for reuse.)
 *
 */
public class PaletteTreeProvider implements ITreeContentProvider {

  private TreeViewer viewer;
  private PaletteTreeNodeEditPart root;
  private EditPartListener modelListener = new EditPartListener.Stub() {
    @Override
    public void childAdded(EditPart child, int index) {
      traverseModel(child, true);
      viewer.refresh(child.getParent());
    }
    @Override
    public void removingChild(EditPart child, int index) {
      traverseModel(child, false);
      // this doesn't work here, as the method is called BEFORE the child has been removed,
      // so the refresh doesn't see that yet...
//      viewer.refresh(child.getParent());
    }
  };

  public PaletteTreeProvider(TreeViewer treeviewer) {
    this.viewer = treeviewer;
  }

  @Override
  public void dispose() {
    if (root != null) {
      traverseModel(root, false);
    }
  }

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
    if (parentElement instanceof PaletteContainer) {
      List children = ((PaletteContainer) parentElement).getChildren();
      if (!children.isEmpty()) {
        return children.toArray();
      }
    }
    return null;
  }

  @Override
  public boolean hasChildren(Object element) {
    return getChildren(element) != null;
  }

  @Override
  public Object[] getElements(Object inputElement) {
    Object[] elements = getChildren(inputElement);
    if (elements == null) {
      elements = new Object[0];
    }
    return elements;
  }

  @Override
  public Object getParent(Object element) {
    return ((EditPart) element).getParent();
  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    if (root != null)
      traverseModel(root, false);
    if (newInput != null) {
      root = (PaletteTreeNodeEditPart) newInput;
      traverseModel(root, true);
    }
  }

  private void traverseModel(EditPart entry, boolean isHook) {
    if (isHook) {
      entry.addEditPartListener(modelListener);
    } else {
      entry.removeEditPartListener(modelListener);
    }
    Object[] children = getChildren(entry);
    if (children != null) {
      for (int i = 0; i < children.length; i++) {
        traverseModel((EditPart) children[i], isHook);
      }
    }
  }
}
