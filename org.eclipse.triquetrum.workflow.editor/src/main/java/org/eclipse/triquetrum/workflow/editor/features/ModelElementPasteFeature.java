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
package org.eclipse.triquetrum.workflow.editor.features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.AbstractPasteFeature;
import org.eclipse.triquetrum.workflow.editor.util.BuildDiagramElementsFromPtolemyElementCommand;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.TriqFactory;
import org.eclipse.triquetrum.workflow.model.Vertex;

import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;

public class ModelElementPasteFeature extends AbstractPasteFeature {

  public ModelElementPasteFeature(IFeatureProvider fp) {
    super(fp);
  }

  public boolean canPaste(IPasteContext context) {
    // can paste, if at least one valid element in the clipboard
    Object[] fromClipboard = getFromClipboard();
    if (fromClipboard == null || fromClipboard.length == 0) {
      return false;
    }
    for (Object object : fromClipboard) {
      if (object instanceof PictogramElement) {
        return true;
      }
    }
    return false;
  }

  public void paste(IPasteContext context) {
    Diagram diagram = getDiagram();
    CompositeActor model = (CompositeActor) getBusinessObjectForPictogramElement(getDiagram());
    ptolemy.kernel.util.NamedObj ptModel = model.getWrappedObject();
    Object[] pes = getFromClipboard();

    double[] topLeftOriginal = EditorUtils.getTopLeftLocation(pes);
    double xOffset = context.getX() - topLeftOriginal[0];
    double yOffset = context.getY() - topLeftOriginal[1];

    List<Connection> originalConnections = new ArrayList<>();
    Map<String, String> oldNewElementNames = new HashMap<>();
    Map<String, Anchor> anchorMap = new HashMap<>();

    for (Object peObj : pes) {
      if (peObj instanceof Connection) {
        originalConnections.add((Connection) peObj);
      } else if (peObj instanceof PictogramElement) {
        PictogramElement pe = (PictogramElement) peObj;
        double[] originalLocation = EditorUtils.getLocation(pe);
        double[] newLocation = new double[] { originalLocation[0] + xOffset, originalLocation[1] + yOffset };
        Object object = getBusinessObjectForPictogramElement(pe);
        if (object instanceof NamedObj) {
          NamedObj child = (NamedObj) object;
          // This fails due to the order in which contained things get initialized,
          // before the parent has received its copied container, or something like that.
          // NamedObj copiedChild = EcoreUtil.copy(child);
          // So we replicate the behaviour used for importing from Ptolemy model elements.
          // I.e. we first clone the Ptolemy objects and put them in their container hierarchy,
          // and then construct the Graphiti/EMF copies from there.
          ptolemy.kernel.util.NamedObj ptObj = child.getWrappedObject();
          ptolemy.kernel.util.NamedObj clonedPtObj = null;
          try {
            clonedPtObj = (ptolemy.kernel.util.NamedObj) ptObj.clone(ptModel.workspace());
            String newName = EditorUtils.buildUniqueName(model, ptObj.getName());
            oldNewElementNames.put(ptObj.getName(), newName);
            clonedPtObj.setName(newName);
            clonedPtObj.setDisplayName(newName);
            EditorUtils.setPtolemyContainer(clonedPtObj, ptModel);
            EditorUtils.setPtolemyLocation(clonedPtObj, newLocation[0], newLocation[1]);
            // TODO move the cloned objects to the new location,
            // based on the currently selected location in the paste context.
            BuildDiagramElementsFromPtolemyElementCommand cmd = new BuildDiagramElementsFromPtolemyElementCommand(diagram, clonedPtObj);
            cmd.execute();
            anchorMap.putAll(cmd.getAnchorMap());
          } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch (IllegalActionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch (NameDuplicationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }
    }
    // and now try to create copied connections between the copied model elements...
    for (Connection connection : originalConnections) {
      NamedObj origStart = getAnchorBO(connection.getStart());
      NamedObj origEnd = getAnchorBO(connection.getEnd());
      // for a port, take the port container; for a vertex take the vertex itself
      NamedObj origStartContainer = (origStart instanceof Port) ? origStart.getContainer() : origStart;
      NamedObj origEndContainer = (origEnd instanceof Port) ? origEnd.getContainer() : origEnd;

      NamedObj copiedStartContainer = model.getChild(oldNewElementNames.get(origStartContainer.getName()));
      NamedObj copiedEndContainer = model.getChild(oldNewElementNames.get(origEndContainer.getName()));
      NamedObj copiedStart = (copiedStartContainer instanceof Vertex) ? copiedStartContainer : copiedStartContainer.getChild(origStart.getName());
      NamedObj copiedEnd = (copiedStartContainer instanceof Vertex) ? copiedEndContainer : copiedEndContainer.getChild(origEnd.getName());
      try {
        Relation copiedRelation = createRelation(copiedStart, copiedEnd);
        System.out.println("copied relation "+copiedRelation);
        // add connection for business object
        AddConnectionContext addContext = new AddConnectionContext(anchorMap.get(copiedStart.getFullName()), anchorMap.get(copiedEnd.getFullName()));
        addContext.setNewObject(copiedRelation);
        getFeatureProvider().addIfPossible(addContext);
      } catch (IllegalActionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * Returns the Port or Vertex belonging to the anchor, or null if not available.
   */
  private NamedObj getAnchorBO(Anchor anchor) {
    if (anchor != null) {
      Object object = getBusinessObjectForPictogramElement(anchor);
      if (object instanceof Port) {
        return (Port) object;
      }
      if (object instanceof Vertex) {
        return (Vertex) object;
      } else {
        throw new IllegalArgumentException("Anchor " + anchor + " linked to invalid object " + object);
      }
    }
    return null;
  }

  private Relation createRelation(NamedObj source, NamedObj target) throws IllegalActionException {
    Relation relation = null;
    if (target instanceof Vertex) {
      // use the vertex's relation
      relation = (Relation) target.getContainer();
      relation.link(source);
    } else if (source instanceof Vertex) {
      // use the vertex's relation
      relation = (Relation) source.getContainer();
      relation.link(target);
    } else {
      // create a new relation directly linking 2 ports
      relation = TriqFactory.eINSTANCE.createRelation();
      NamedObj relationContainer = source.getContainer();
      while (!(relationContainer instanceof CompositeActor)) {
        relationContainer = relationContainer.getContainer();
      }
      relation.setName(EditorUtils.buildUniqueName(relationContainer, "_R"));
      ((CompositeActor) relationContainer).getRelations().add(relation);
      relation.link(source);
      relation.link(target);
    }
    return relation;
  }
}
