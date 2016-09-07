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

import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.Tool;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.tools.ConnectionCreationTool;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;

public class PaletteTreeViewerProvider extends PaletteViewerProvider {

  private KeyHandler paletteKeyHandler = null;
  private DiagramBehavior diagramBehavior;

  public PaletteTreeViewerProvider(DiagramBehavior diagramBehavior) {
    super(diagramBehavior.getEditDomain());
    this.diagramBehavior = diagramBehavior;
  }

  @Override
  public PaletteViewer createPaletteViewer(Composite parent) {
    PaletteTreeViewer pViewer = new PaletteTreeViewer();
    pViewer.createTreeControl(parent);
    configurePaletteViewer(pViewer);
    hookPaletteViewer(pViewer);
    return pViewer;
  }

  @Override
  protected void configurePaletteViewer(PaletteViewer viewer) {
    super.configurePaletteViewer(viewer);
    viewer.getKeyHandler().setParent(getPaletteKeyHandler());
    viewer.addDragSourceListener(new TemplateTransferDragSourceListener(viewer));
  }

  /**
   * @return Palette Key Handler for the palette
   */
  private KeyHandler getPaletteKeyHandler() {
    if (paletteKeyHandler == null) {
      paletteKeyHandler = new KeyHandler() {
        /**
         * Processes a <i>key released </i> event. This method is called by the Tool whenever a key is released, and the Tool is in the proper state. Overridden
         * to support pressing the enter key to create a shape or connection (between two selected shapes)
         *
         * @param event
         *          the KeyEvent
         * @return <code>true</code> if KeyEvent was handled in some way
         */
        @Override
        public boolean keyReleased(KeyEvent event) {
          if (event.keyCode == SWT.Selection) {
            Tool tool = getEditDomain().getPaletteViewer().getActiveTool().createTool();
            if (tool instanceof CreationTool || tool instanceof ConnectionCreationTool) {
              tool.keyUp(event, diagramBehavior.getDiagramContainer().getGraphicalViewer());
              // Deactivate current selection
              getEditDomain().getPaletteViewer().setActiveTool(null);
              return true;
            }
          }
          return super.keyReleased(event);
        }
      };
    }
    return paletteKeyHandler;
  }

}
