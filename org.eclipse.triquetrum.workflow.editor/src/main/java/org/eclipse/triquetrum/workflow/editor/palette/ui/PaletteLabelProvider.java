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

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

public class PaletteLabelProvider implements ILabelProvider, IColorProvider, IFontProvider {
  private static Font BOLD_FONT;
  private PaletteTreeViewer viewer;

  /**
   * Constructor
   *
   * @param viewer
   *          The TreeViewer for which this instance is a LabelProvider
   */
  public PaletteLabelProvider(PaletteTreeViewer viewer) {
    this.viewer = viewer;
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getBackground(Object)
   */
  public Color getBackground(Object element) {
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getForeground(Object)
   */
  public Color getForeground(Object element) {
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.ILabelProvider#getImage(Object)
   */
  public Image getImage(Object element) {
    PaletteEntryEditPart entry = (PaletteEntryEditPart) element;
    return entry.getImage();
  }

  /**
   * @see org.eclipse.jface.viewers.ILabelProvider#getText(Object)
   */
  public String getText(Object element) {
    return ((PaletteEntryEditPart) element).getText();
  }

  /**
   * Not implemented
   *
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(ILabelProviderListener)
   */
  public void addListener(ILabelProviderListener listener) {
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
   */
  public void dispose() {
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(Object, String)
   */
  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  /**
   * Not implemented
   *
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(ILabelProviderListener)
   */
  public void removeListener(ILabelProviderListener listener) {
  }

  @Override
  public Font getFont(Object element) {
    if (element instanceof PaletteTreeNodeEditPart) {
      if (BOLD_FONT == null) {
        Widget widget = ((PaletteEntryEditPart) element).getWidget();
        if (widget instanceof TreeItem) {
          TreeItem treeItem = (TreeItem) widget;
          Font f = null;
          // there's something about the order of tree construction logic that causes treeitems to not be
          // fully ready yet here
          if (!treeItem.isDisposed()) {
            f = treeItem.getFont();
          } else {
            f = new Font(Display.getCurrent(), viewer.getPaletteViewerPreferences().getFontData());
          }
          FontDescriptor boldDescriptor = FontDescriptor.createFrom(f).setStyle(SWT.BOLD);
          BOLD_FONT = boldDescriptor.createFont(Display.getCurrent());
        }
      }
      return BOLD_FONT;
    }
    return null;
  }

}