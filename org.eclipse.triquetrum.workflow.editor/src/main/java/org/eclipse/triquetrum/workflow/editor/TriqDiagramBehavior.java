/*******************************************************************************
 * Copyright (c) 2015 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.editparts.GridLayer;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.ui.editor.DefaultPaletteBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal;
import org.eclipse.graphiti.ui.internal.contextbuttons.ContextButtonManagerForPad;
import org.eclipse.graphiti.ui.internal.editor.GFMarqueeDragTracker;
import org.eclipse.graphiti.ui.internal.editor.GFMarqueeSelectionTool;
import org.eclipse.graphiti.ui.internal.util.gef.ScalableRootEditPartAnimated;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.triquetrum.workflow.editor.palette.TriqPaletteBehavior;

public class TriqDiagramBehavior extends DiagramBehavior {

  private ResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources());

  public TriqDiagramBehavior(IDiagramContainerUI diagramContainer) {
    super(diagramContainer);
  }

  @Override
  protected DefaultPaletteBehavior createPaletteBehaviour() {
    return new TriqPaletteBehavior(this);
  }

  /**
   * Override this to enforce that connections can also be selected with a mouse drag marquee selection.
   */
  @Override
  protected void configureGraphicalViewer() {

    ScrollingGraphicalViewer viewer = (ScrollingGraphicalViewer) getDiagramContainer().getGraphicalViewer();

    ScalableRootEditPartAnimated rootEditPart = new ScalableRootEditPartAnimated(viewer, getConfigurationProvider()) {
      @Override
      protected GridLayer createGridLayer() {
        return new org.eclipse.graphiti.ui.internal.util.draw2d.GridLayer((IConfigurationProviderInternal) getConfigurationProvider());
      }

      @Override
      public DragTracker getDragTracker(Request req) {
        GFMarqueeDragTracker trckr = new GFMarqueeDragTracker(this);
        trckr.setMarqueeBehavior(GFMarqueeSelectionTool.BEHAVIOR_NODES_AND_CONNECTIONS);
        return trckr;
      }
    };

    // configure ZoomManager
    viewer.setRootEditPart(rootEditPart); // support

    // animation of the zoom
    ZoomManager zoomManager = rootEditPart.getZoomManager();
    List<String> zoomLevels = new ArrayList<>(3);
    zoomLevels.add(ZoomManager.FIT_ALL);
    zoomLevels.add(ZoomManager.FIT_WIDTH);
    zoomLevels.add(ZoomManager.FIT_HEIGHT);
    zoomManager.setZoomLevelContributions(zoomLevels);
    IToolBehaviorProvider toolBehaviorProvider = getConfigurationProvider().getDiagramTypeProvider().getCurrentToolBehaviorProvider();
    zoomManager.setZoomLevels(toolBehaviorProvider.getZoomLevels());

    this.initActionRegistry(zoomManager);

    // set the keyhandler.
    viewer.setKeyHandler((new GraphicalViewerKeyHandler(viewer)).setParent(getCommonKeyHandler()));

    // settings for grid and guides
    Diagram diagram = getConfigurationProvider().getDiagram();

    boolean snapToGrid = diagram.isSnapToGrid();
    int horizontalGridUnit = diagram.getGridUnit();
    int verticalGridUnit = diagram.getVerticalGridUnit();
    if (verticalGridUnit == -1) {
      // No vertical grid unit set (or old diagram before 0.8): use
      // vertical grid unit
      verticalGridUnit = horizontalGridUnit;
    }
    boolean gridVisisble = (horizontalGridUnit > 0) && (verticalGridUnit > 0);

    viewer.setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE, new Boolean(gridVisisble));
    viewer.setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, new Boolean(snapToGrid));
    viewer.setProperty(SnapToGrid.PROPERTY_GRID_SPACING, new Dimension(horizontalGridUnit, verticalGridUnit));
    viewer.setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, toolBehaviorProvider.isShowGuides());

    // context button manager
    IConfigurationProviderInternal configurationProvider = (IConfigurationProviderInternal) this.getConfigurationProvider();
    configurationProvider.setContextButtonManager(new ContextButtonManagerForPad(this, configurationProvider.getResourceRegistry()));
  }

  @Override
  protected void disposeAfterGefDispose() {
    super.disposeAfterGefDispose();
    resourceManager.dispose();
  }

  /**
   *
   * @return the JFace ResourceManager to be used to create images, fonts etc for an open diagram.
   */
  public ResourceManager getResourceManager() {
    return resourceManager;
  }
}
