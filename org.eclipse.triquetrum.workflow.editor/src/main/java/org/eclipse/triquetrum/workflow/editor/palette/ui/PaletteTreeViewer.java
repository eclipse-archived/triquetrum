/*******************************************************************************
 *  Copyright (c) 2016 iSencia Belgium NV.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *     Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.palette.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.gef.editparts.RootTreeEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * Try to merge features of {@link TreeViewer} into the {@link PaletteViewer}
 * and use a {@link FilteredTree} with that as well.
 *
 */
public class PaletteTreeViewer extends PaletteViewer {
  private boolean ignore = false;
  private FigureCanvas figCanvas;

  public PaletteTreeViewer() {
    super();
    setKeyHandler(new GraphicalViewerKeyHandler(this));
    setEditPartFactory(new PaletteTreeEditPartFactory());
    addDragSourceListener(new TreeViewerTransferDragListener(this));
  }

  // hack to use a dummy canvas to please the graphical base classes
  @Override
  protected FigureCanvas getFigureCanvas() {
    if (figCanvas == null) {
      figCanvas = new FigureCanvas(getControl().getParent());
    }
    return figCanvas;
  }

  @Override
  protected void createDefaultRoot() {
    try {
      setRootEditPart(new RootTreeEditPart());
    } catch (ClassCastException e) {
      // to catch the wrong cast to a GraphicalEditPart in the GraphicalViewerImpl.setRootEditPart implementation
    }
  }

  @Override
  public void setPaletteRoot(PaletteRoot root) {
    super.setPaletteRoot(root);
    ((FilteredTree) getControl()).getViewer().setInput(getRootEditPart().getContents().getChildren());
  }

  /**
   * Creates the default tree and sets it as the control. The default styles will show scrollbars as needed, and allows for multiple selection.
   * <p>
   * Doesn't use the default createControl method name, as that one is made final in the ScrollingGraphicalViewer base class...
   * </p>
   *
   * @param parent
   *          The parent for the Tree
   * @return the control
   */
  public Control createTreeControl(Composite parent) {
    PatternFilter filter = new PatternFilter();
    FilteredTree tree = new FilteredTree(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, filter, true);
    tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    tree.getViewer().setContentProvider(new PaletteTreeProvider());
    tree.getViewer().setLabelProvider(new PaletteLabelProvider(this));
    setControl(tree);
    return tree;
  }

  protected Tree getTreeControl() {
    final FilteredTree filteredTree = (FilteredTree) getControl();
    return filteredTree.getViewer().getTree();
  }

  /**
   * @see org.eclipse.gef.EditPartViewer#findObjectAtExcluding(Point, Collection, EditPartViewer.Conditional)
   */
  public EditPart findObjectAtExcluding(Point pt, Collection exclude, Conditional condition) {
    if (getControl() == null)
      return null;

    final Tree tree = getTreeControl();
    Rectangle area = tree.getClientArea();
    if (pt.x < area.x || pt.y < area.y || pt.x >= area.x + area.width || pt.y >= area.y + area.height)
      return null;

    EditPart result = null;
    TreeItem tie = tree.getItem(new org.eclipse.swt.graphics.Point(pt.x, pt.y));

    if (tie != null) {
      result = (EditPart) tie.getData();
    } else if(tree.getData() instanceof EditPart){
      result = (EditPart) tree.getData();
    }
    while (result != null) {
      if ((condition == null || condition.evaluate(result)) && !exclude.contains(result))
        return result;
      result = result.getParent();
    }
    return null;
  }

  /**
   * @see org.eclipse.gef.ui.parts.AbstractEditPartViewer#fireSelectionChanged()
   */
  protected void fireSelectionChanged() {
    super.fireSelectionChanged();
    showSelectionInTree();
  }

  /**
   * "Hooks up" a Control, i.e. sets it as the control for the RootTreeEditPart, adds necessary listener for proper operation, etc.
   */
  protected void hookControl() {
    if (getControl() == null)
      return;

    final Tree tree = getTreeControl();
    tree.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        TreeItem[] ties = tree.getSelection();
        Object newSelection[] = new Object[ties.length];
        for (int i = 0; i < ties.length; i++)
          newSelection[i] = ties[i].getData();
        ignore = true;
        setSelection(new StructuredSelection(newSelection));
        ignore = false;
      }

      public void widgetDefaultSelected(SelectionEvent e) {
        widgetSelected(e);
      }
    });
    TreeEditPart tep = (TreeEditPart) getRootEditPart();
    tep.setWidget(tree);
    try {
      super.hookControl();
    } catch (ClassCastException e) {
      // to catch the wrong cast to graphical widget etc in the super-classes
    }
  }

  /**
   * @see org.eclipse.gef.ui.parts.AbstractEditPartViewer#reveal(org.eclipse.gef.EditPart)
   */
  public void reveal(EditPart part) {
    if (!(part instanceof TreeEditPart))
      return;
    TreeEditPart treePart = (TreeEditPart) part;
    final Tree tree = getTreeControl();
    Widget widget = treePart.getWidget();
    if (widget instanceof TreeItem)
      tree.showItem((TreeItem) widget);
  }

  /**
   * Creates or disposes a DragSource as needed, and sets the supported transfer types. Clients should not need to call or override this method.
   */
  protected void refreshDragSourceAdapter() {
    if (getControl() == null)
      return;
    if (getDelegatingDragAdapter().isEmpty())
      setDragSource(null);
    else {
      if (getDragSource() == null)
        setDragSource(new DragSource(getTreeControl(), DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK));
      getDragSource().setTransfer(getDelegatingDragAdapter().getTransfers());
    }
  }

  /**
   * Creates or disposes a DropTarget as needed, and sets the supported transfer types. Clients should not need to call or override this method.
   */
  protected void refreshDropTargetAdapter() {
    if (getControl() == null)
      return;
    if (getDelegatingDropAdapter().isEmpty())
      setDropTarget(null);
    else {
      if (getDropTarget() == null)
        setDropTarget(new DropTarget(getTreeControl(), DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK));
      getDropTarget().setTransfer(getDelegatingDropAdapter().getTransfers());
    }
  }

  private void showSelectionInTree() {
    if (ignore || getControl() == null || getControl().isDisposed())
      return;
    List selection = getSelectedEditParts();
    final Tree tree = getTreeControl();
    List<TreeEditPart> treeParts = new ArrayList<>();
    for (int i = 0; i < selection.size(); i++) {
      TreeEditPart part = (TreeEditPart) selection.get(i);
      if (part.getWidget() instanceof TreeItem)
        treeParts.add(part);
    }
    TreeItem[] treeItems = new TreeItem[treeParts.size()];
    for (int i = 0; i < treeParts.size(); i++) {
      TreeEditPart part = (TreeEditPart) treeParts.get(i);
      treeItems[i] = (TreeItem) part.getWidget();
    }
    tree.setSelection(treeItems);
  }

  /**
   * Unhooks a control so that it can be reset. This method deactivates the contents, removes the Control as being the Control of the RootTreeEditPart, etc. It
   * does not remove the listeners because it is causing errors, although that would be a desirable outcome.
   */
  protected void unhookControl() {
    if (getControl() == null)
      return;
    super.unhookControl();
    // Ideally, you would want to remove the listeners here
    TreeEditPart tep = (TreeEditPart) getRootEditPart();
    tep.setWidget(null);
  }
}
