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
import org.eclipse.triquetrum.workflow.model.util.PtolemyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.data.expr.ModelScope;
import ptolemy.data.expr.ParserScope;
import ptolemy.data.expr.Variable;
import ptolemy.kernel.util.Attribute;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;

public class ModelElementPasteFeature extends AbstractPasteFeature {

  private final static Logger LOGGER = LoggerFactory.getLogger(ModelElementPasteFeature.class);
  private Variable _previousNode;

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
    CompositeActor targetModel = (CompositeActor) getBusinessObjectForPictogramElement(getDiagram());
    ptolemy.kernel.util.NamedObj targetPtModel = targetModel.getWrappedObject();
    Object[] sourcePEs = getFromClipboard();

    double[] topLeftOriginal = EditorUtils.getTopLeftLocation(sourcePEs);
    int xOffset = context.getX() - (int) topLeftOriginal[0];
    int yOffset = context.getY() - (int) topLeftOriginal[1];

    List<Connection> originalConnections = new ArrayList<>();
    Map<String, String> oldNewElementNames = new HashMap<>();
    Map<String, Vertex> clonedVertexMap = new HashMap<>();
    Map<String, Anchor> anchorMap = new HashMap<>();

    for (Object sourceObj : sourcePEs) {
      if (sourceObj instanceof Connection) {
        originalConnections.add((Connection) sourceObj);
      } else if (sourceObj instanceof PictogramElement) {
        PictogramElement sourcePE = (PictogramElement) sourceObj;
        double[] originalLocation = EditorUtils.getLocation(sourcePE);
        double[] newLocation = new double[] { originalLocation[0] + xOffset, originalLocation[1] + yOffset };
        Object sourceBO = getBusinessObjectForPictogramElement(sourcePE);
        if (sourceBO instanceof Vertex) {
          // The child/container associations can not easily be cloned here, as a Vertex's container is a Relation!?
          // And the original relations also have connections to ports etc.
          // Cloning the combination of Relation/Vertex/linked-ports seems to always lead to problems with the order of doing cloning things...
          // So we follow another approach here.
          Vertex sourceVertex = (Vertex) sourceBO;

          Relation relation = TriqFactory.eINSTANCE.createRelation();
          relation.setName(EditorUtils.buildUniqueName(targetModel, "_R"));
          targetModel.getRelations().add(relation);

          Vertex clonedChild = TriqFactory.eINSTANCE.createVertex();
          clonedChild.setName(EditorUtils.buildUniqueName(relation, sourceVertex.getName()));
          clonedChild.setWrappedType(sourceVertex.getWrappedType());
          // store the cloned vertex linked to the name of the relation containing the original vertex
          // as we need to be able to look it up when building the copied relations further below.
          clonedVertexMap.put(sourceVertex.getContainer().getName(), clonedChild);
          relation.getAttributes().add(clonedChild);

          AddContext addCtxt = new AddContext();
          addCtxt.setLocation((int) newLocation[0], (int) newLocation[1]);
          addCtxt.putProperty(FeatureConstants.ANCHORMAP_NAME, anchorMap);
          addCtxt.setTargetContainer(getDiagram());
          getFeatureProvider().addIfPossible(new AddContext(addCtxt, clonedChild));
        } else if (sourceBO instanceof NamedObj) {
          // Handling the icon spec in this way is OK for making sure custom icons are maintained
          // for copy/pasted model elements, on their top level only.
          // I.e. once we must be able to look inside composite hierarchies, this is no longer sufficient.
          // The basis of this limitation is the current approach to clone from the Ptolemy objects,
          // meaning that we don't pass along all graphical info contained in the Graphiti model objects.
          String iconType = Graphiti.getPeService().getPropertyValue(sourcePE, FeatureConstants.ICON_TYPE);
          String iconResource = Graphiti.getPeService().getPropertyValue(sourcePE, FeatureConstants.ICON);
          NamedObj sourceNO = (NamedObj) sourceBO;
          // This fails due to the order in which contained things get initialized,
          // before the parent has received its copied container, or something like that.
          // NamedObj copiedChild = EcoreUtil.copy(child);
          // So we replicate the behaviour used for importing from Ptolemy model elements.
          // I.e. we first clone the Ptolemy objects and put them in their container hierarchy,
          // and then construct the Graphiti/EMF copies from there.
          ptolemy.kernel.util.NamedObj sourcePtNO = sourceNO.getWrappedObject();
          try {
            cloneModelElement(targetModel, targetPtModel, sourcePtNO, iconType, iconResource, newLocation, oldNewElementNames, anchorMap);
          } catch (Exception e) {
            // TODO how to fix/report paste errors that are not about missing elements?
            LOGGER.error("Error pasting model elements", e);
          }
        }
      }
    }
    // and now try to create copied connections between the copied model elements...
    for (Connection connection : originalConnections) {
      NamedObj origStart = getAnchorBO(connection.getStart());
      NamedObj origEnd = getAnchorBO(connection.getEnd());
      NamedObj copiedStart = getCopiedRelationSide(targetModel, origStart, oldNewElementNames, clonedVertexMap);
      NamedObj copiedEnd = getCopiedRelationSide(targetModel, origEnd, oldNewElementNames, clonedVertexMap);
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
        // TODO how to fix/report paste errors that are not about missing elements?
        LOGGER.error("Error pasting model elements", e);
      }
    }
  }

  private void cloneModelElement(CompositeActor targetModel, ptolemy.kernel.util.NamedObj targetPtModel, ptolemy.kernel.util.NamedObj sourcePtNO,
      String iconType, String iconResource, double[] newLocation, Map<String, String> oldNewElementNames, Map<String, Anchor> anchorMap)
      throws CloneNotSupportedException, IllegalActionException, NameDuplicationException {
    ptolemy.kernel.util.NamedObj clonedPtNO = (ptolemy.kernel.util.NamedObj) sourcePtNO.clone(targetPtModel.workspace());
    try {
      EditorUtils.setPtolemyContainer(clonedPtNO, targetPtModel);
    } catch (NameDuplicationException e) {
      String newName = EditorUtils.buildUniqueName(targetModel, clonedPtNO.getName());
      oldNewElementNames.put(clonedPtNO.getName(), newName);
      clonedPtNO.setName(newName);
      clonedPtNO.setDisplayName(newName);
      EditorUtils.setPtolemyContainer(clonedPtNO, targetPtModel);
    }
    if(newLocation!=null) {
      EditorUtils.setPtolemyLocation(clonedPtNO, newLocation[0], newLocation[1]);
    }
    boolean continueValidation = true;
    while (continueValidation) {
      continueValidation = false;
      try {
        clonedPtNO.validateSettables();
      } catch (IllegalActionException e) {
        String missingElementName = PtolemyUtil._findUndefinedConstantOrIdentifier(e);
        if (missingElementName != null) {
          String errorVariableName = PtolemyUtil.getFullNameWithoutToplevel(e.getNameable1());
          continueValidation = handleMissingElement(targetModel, targetPtModel, sourcePtNO, errorVariableName, missingElementName, oldNewElementNames, anchorMap);
        } else {
          // TODO how to fix/report paste errors that are not about missing elements?
          LOGGER.error("Error pasting model elements", e);
        }
      }
    }
    BuildDiagramElementsFromPtolemyElementCommand cmd = new BuildDiagramElementsFromPtolemyElementCommand(getDiagram(), clonedPtNO, iconType, iconResource);
    cmd.execute();
    anchorMap.putAll(cmd.getAnchorMap());
  }

  private boolean handleMissingElement(CompositeActor model, ptolemy.kernel.util.NamedObj ptModel, ptolemy.kernel.util.NamedObj ptObj, String containingVariableName, String missingElementName, Map<String, String> oldNewElementNames, Map<String, Anchor> anchorMap)
      throws IllegalActionException {
    boolean continueValidation = false;
    ptolemy.kernel.util.NamedObj container = ptObj.getContainer();
    Attribute masterAttribute = container.getAttribute(containingVariableName);
    if (masterAttribute == null) {
      // Needed to find Parameters that are up scope
      ptolemy.kernel.util.NamedObj searchContainer = container;
      while (searchContainer != null && masterAttribute == null) {
        masterAttribute = searchContainer.getAttribute(containingVariableName);
        searchContainer = searchContainer.getContainer();
      }
    }
    if (masterAttribute instanceof Variable) {
      Variable masterVariable = (Variable) masterAttribute;
      ParserScope parserScope = masterVariable.getParserScope();
      if (parserScope instanceof ModelScope) {
        Variable node = masterVariable.getVariable(missingElementName);

        if (node == null) {
          // Needed when we are copying a composite that contains an Expression that refers to an upscope Parameter.
          node = masterVariable;
        }

        if (node == _previousNode) {
          // We've already seen this node, so stop looping through the validation loop.
          return false;
        }
        _previousNode = node;

        try {
          // In Ptolemy II this is done via MoML change requests, but we're not using that (yet) in Triquetrum
          cloneModelElement(model, ptModel, node, null, null, null, oldNewElementNames, anchorMap);

          // Rerun the validation in case there are other problem variables.
          continueValidation = true;
        } catch (Throwable ex2) {
          // Ignore and hope the user pastes into a location where the variable is defined
        }
      }
    }
    return continueValidation;
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
