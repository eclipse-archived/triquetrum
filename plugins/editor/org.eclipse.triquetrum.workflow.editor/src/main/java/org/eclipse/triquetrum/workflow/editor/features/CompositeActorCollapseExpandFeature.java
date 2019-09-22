/*******************************************************************************
 * Copyright (c) 2016,2019 iSencia Belgium NV.
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
package org.eclipse.triquetrum.workflow.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.impl.ResizeShapeContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.shapes.ActorShapes;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;

public class CompositeActorCollapseExpandFeature extends AbstractCustomFeature {

  private boolean collapse = true;

  public CompositeActorCollapseExpandFeature(IFeatureProvider fp, boolean isCollapsed) {
    super(fp);
    this.collapse = !isCollapsed;
  }

  @Override
  public boolean canExecute(ICustomContext context) {
    boolean ret = false;
    PictogramElement[] pes = context.getPictogramElements();
    if (pes != null && pes.length == 1) {
      BoCategory boCategory = BoCategory.retrieveFrom(pes[0]);
      ret = BoCategory.CompositeActor.equals(boCategory);
    }
    return ret;
  }

  @Override
  public void execute(ICustomContext context) {
    PictogramElement[] pes = context.getPictogramElements();
    if (pes != null && pes.length == 1) {
      BoCategory boCategory = BoCategory.retrieveFrom(pes[0]);
      if (BoCategory.CompositeActor.equals(boCategory)) {
        collapseShape(pes[0]);
      }
    }
  }

  private void collapseShape(PictogramElement pe) {
    ContainerShape cs = (ContainerShape) pe;
    boolean isCollapsed = EditorUtils.isCollapsed(pe);

    if((collapse && isCollapsed) || (!collapse && !isCollapsed)) {
      return;
    }

    int width = pe.getGraphicsAlgorithm().getWidth();
    int height = pe.getGraphicsAlgorithm().getHeight();

    int changeWidth = ActorShapes.ACTOR_VISIBLE_WIDTH;
    int changeHeight = ActorShapes.ACTOR_VISIBLE_HEIGHT;

    // show/hide all the children
    makeChildrenVisible(cs, cs, isCollapsed);

    if (!isCollapsed) {
      // store properties to be able to expand the pe again to its original size, later on
      EditorUtils.setCollapsed(pe, width, height);
    } else {
      int[] originalSize = EditorUtils.setExpanded(pe);
      changeWidth = originalSize[0];
      changeHeight = originalSize[1];
    }

    ResizeShapeContext context1 = new ResizeShapeContext(cs);
    context1.setSize(changeWidth, changeHeight);
    context1.setLocation(cs.getGraphicsAlgorithm().getX(), cs.getGraphicsAlgorithm().getY());
    IResizeShapeFeature rsf = getFeatureProvider().getResizeShapeFeature(context1);
    if (rsf.canExecute(context1)) {
      rsf.execute(context1);
    }
  }

  @Override
  public String getName() {
    return collapse?"Collapse":"Expand";
  }

  @Override
  public String getDescription() {
    return collapse?"Collapse Submodel":"Expand Submodel";
  }

  @Override
  public String getImageId() {
    return collapse?IPlatformImageConstants.IMG_EDIT_COLLAPSE:IPlatformImageConstants.IMG_EDIT_EXPAND;
  }

  @Override
  public boolean isAvailable(IContext context) {
    return true;
  }

  /**
   * Sets visibility for the children of a model element's shape, to support collapse/expand behaviour in a diagram.
   * The idea is to not remove elements from a diagram when collapsing e.g. a submodel, as that is a complex action
   * and adding the removed elements again afterwards is complex as well. Instead we just make the elements invisible
   * and show the container in its collapsed shape.
   *
   * @param toplevel
   * @param cs
   * @param visible
   */
  public void makeChildrenVisible(ContainerShape toplevel, ContainerShape cs, boolean visible) {
    for (Shape shape : cs.getChildren()) {
      BoCategory boCategory = BoCategory.retrieveFrom(shape);
      if (shape instanceof ContainerShape) {
        makeChildrenVisible(toplevel, (ContainerShape) shape, visible);
        shape.setVisible(visible);
      } else if (BoCategory.Parameter.equals(boCategory)) {
        shape.setVisible(visible);
      } else if (BoCategory.Port.equals(boCategory)) {

      }
    }
    // To be able to show/hide connections, we need to determine
    // which connections are completely contained in the ContainerShape.
    // As Graphiti does not place the connections at the relevant level
    // in its model hierarchy (they're all in a same collection on the Diagram instance),
    // a connection's containment must be determined through its start and end anchors.

    for(Anchor anchor : cs.getAnchors()) {
      for(Connection c  : anchor.getIncomingConnections()) {
        if(deepContains(toplevel, c.getStart())) {
          c.setVisible(visible);
        }
      }
      for(Connection c  : anchor.getOutgoingConnections()) {
        if(deepContains(toplevel, c.getEnd())) {
          c.setVisible(visible);
        }
      }
    }
  }

  private static boolean deepContains(ContainerShape cs, Anchor a) {
    boolean result = cs.getAnchors().contains(a);
    if(!result) {
      AnchorContainer aContainer = a.getParent();
      while (!result) {
        if(aContainer==null) {
          break;
        }
        result = cs.getChildren().contains(aContainer);
        if(!result && (aContainer instanceof Shape)) {
          aContainer = ((Shape)aContainer).getContainer();
        }
      }
    }
    return result;
  }
}
