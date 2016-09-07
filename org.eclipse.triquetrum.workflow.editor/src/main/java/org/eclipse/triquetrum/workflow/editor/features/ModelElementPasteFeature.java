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
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
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

  @Override
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

  @Override
  public void paste(IPasteContext context) {
    Diagram diagram = getDiagram();
    CompositeActor model = (CompositeActor) getBusinessObjectForPictogramElement(getDiagram());
    ptolemy.kernel.util.NamedObj ptModel = model.getWrappedObject();
    Object[] pes = getFromClipboard();

    double[] topLeftOriginal = EditorUtils.getTopLeftLocation(pes);
    int xOffset = context.getX() - (int) topLeftOriginal[0];
    int yOffset = context.getY() - (int) topLeftOriginal[1];

    List<Connection> originalConnections = new ArrayList<>();
    Map<String, String> oldNewElementNames = new HashMap<>();
    Map<String, Vertex> clonedVertexMap = new HashMap<>();
    Map<String, Anchor> anchorMap = new HashMap<>();

    for (Object peObj : pes) {
      if (peObj instanceof Connection) {
        originalConnections.add((Connection) peObj);
      } else if (peObj instanceof PictogramElement) {
        PictogramElement pe = (PictogramElement) peObj;
        double[] originalLocation = EditorUtils.getLocation(pe);
        double[] newLocation = new double[] { originalLocation[0] + xOffset, originalLocation[1] + yOffset };
        Object object = getBusinessObjectForPictogramElement(pe);
        if (object instanceof Vertex) {
          // The child/container associations can not easily be cloned here, as a Vertex's container is a Relation!?
          // And the original relations also have connections to ports etc.
          // Cloning the combination of Relation/Vertex/linked-ports seems to always lead to problems with the order of doing cloning things...
          // So we follow another approach here.
          Vertex child = (Vertex) object;

          Relation relation = TriqFactory.eINSTANCE.createRelation();
          relation.setName(EditorUtils.buildUniqueName(model, "_R"));
          model.getRelations().add(relation);

          Vertex clonedChild = TriqFactory.eINSTANCE.createVertex();
          clonedChild.setName(EditorUtils.buildUniqueName(relation, child.getName()));
          clonedChild.setWrappedType(child.getWrappedType());
          // store the cloned vertex linked to the name of the relation containing the original vertex
          // as we need to be able to look it up when building the copied relations further below.
          clonedVertexMap.put(child.getContainer().getName(), clonedChild);
          relation.getAttributes().add(clonedChild);

          AddContext addCtxt = new AddContext();
          addCtxt.setLocation((int) newLocation[0], (int) newLocation[1]);
          addCtxt.putProperty(FeatureConstants.ANCHORMAP_NAME, anchorMap);
          addCtxt.setTargetContainer(diagram);
          getFeatureProvider().addIfPossible(new AddContext(addCtxt, clonedChild));
        } else if (object instanceof NamedObj) {
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
      NamedObj copiedStart = getCopiedRelationSide(model, origStart, oldNewElementNames, clonedVertexMap);
      NamedObj copiedEnd = getCopiedRelationSide(model, origEnd, oldNewElementNames, clonedVertexMap);
      try {
        Relation copiedRelation = EditorUtils.createRelation(copiedStart, copiedEnd, null);
        // add the graphical representation for the copied relation
        AddConnectionContext addContext = new AddConnectionContext(anchorMap.get(copiedStart.getFullName()), anchorMap.get(copiedEnd.getFullName()));
        addContext.setNewObject(copiedRelation);
        PictogramElement copiedConnection = getFeatureProvider().addIfPossible(addContext);
        // TODO find a way to copy other connection properties to the copied connection, if this would be needed?
        if (connection instanceof FreeFormConnection && copiedConnection instanceof FreeFormConnection) {
          FreeFormConnection copiedFFC = (FreeFormConnection) copiedConnection;
          IGaService gaService = Graphiti.getGaService();
          for (Point point : ((FreeFormConnection) connection).getBendpoints()) {
            copiedFFC.getBendpoints().add(gaService.createPoint(point.getX() + xOffset, point.getY() + yOffset));
          }
        }
      } catch (IllegalActionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  private NamedObj getCopiedRelationSide(CompositeActor model, NamedObj originalRelationSide, Map<String, String> oldNewElementNames,
      Map<String, Vertex> clonedVertexMap) {
    NamedObj copiedRelationSide = null;
    String origContainerName = originalRelationSide.getContainer().getName();
    if (originalRelationSide instanceof Port) {
      NamedObj copiedPortContainer = model.getChild(oldNewElementNames.get(origContainerName));
      copiedRelationSide = copiedPortContainer.getChild(originalRelationSide.getName());
    } else if (originalRelationSide instanceof Vertex) {
      copiedRelationSide = clonedVertexMap.get(origContainerName);
    }
    return copiedRelationSide;
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
}
