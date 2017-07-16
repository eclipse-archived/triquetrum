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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
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
  private PropertyChangeListener modelListener = new PropertyChangeListener() {
    public void propertyChange(PropertyChangeEvent evt) {
      handlePropertyChanged(evt);
    }
  };

  public PaletteTreeProvider(TreeViewer treeviewer) {
    this.viewer = treeviewer;
  }

  @Override
  public void dispose() {
    if (root != null) {
      traverseModel((PaletteEntry) root.getModel(), false);
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
      traverseModel((PaletteEntry) root.getModel(), false);
    if (newInput != null) {
      root = (PaletteTreeNodeEditPart) newInput;
      traverseModel((PaletteEntry) root.getModel(), true);
    }
  }

  /**
   * This method is invoked whenever there is any change in the model. It updates the viewer with the changes that were
   * made to the model. Sub-classes may override this method to change or extend its functionality.
   * 
   * @param evt
   *          The {@link PropertyChangeEvent} that was fired from the model
   */
  protected void handlePropertyChanged(PropertyChangeEvent evt) {
    PaletteEntry entry = ((PaletteEntry) evt.getSource());
    String property = evt.getPropertyName();
    if (property.equals(PaletteEntry.PROPERTY_LABEL) || property.equals(PaletteEntry.PROPERTY_SMALL_ICON)) {
      viewer.update(entry, null);
    } else if (property.equals(PaletteEntry.PROPERTY_VISIBLE)) {
      viewer.refresh(entry);
    } else if (property.equals(PaletteContainer.PROPERTY_CHILDREN)) {
      viewer.refresh();
      List<PaletteEntry> oldChildren = (List<PaletteEntry>) evt.getOldValue();
      for (PaletteEntry child : oldChildren) {
        traverseModel(child, false);
      }
      traverseModel(entry, true);
    }
  }

  private void traverseModel(PaletteEntry entry, boolean isHook) {
    if (isHook) {
      entry.addPropertyChangeListener(modelListener);
    } else {
      entry.removePropertyChangeListener(modelListener);
    }
    Object[] children = getChildren(entry);
    if (children != null) {
      for (int i = 0; i < children.length; i++) {
        traverseModel((PaletteEntry) children[i], isHook);
      }
    }
  }
}
