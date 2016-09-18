/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Port;

/**
 * TODO support changing port counts for dynamic port actors
 *
 */
public class ActorUpdateFeature extends AbstractUpdateFeature {
  public ActorUpdateFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canUpdate(IUpdateContext context) {
    BoCategory boCategory = BoCategory.retrieveFrom(context.getPictogramElement());
    return (BoCategory.Actor.equals(boCategory)) || (BoCategory.CompositeActor.equals(boCategory) && !getDiagram().equals(context.getPictogramElement()));
  }

  /**
   * The indirect update handling of graphiti means that complex diff-checking is needed here between the stuff that is currently maintained in the graphical
   * model, and the actual configuration&status of an actor.
   * <p>
   * This is different from ptolemy's Vergil where updates are done through editor components and/or parameter configuration dialogs and are then applied
   * immediately to the graphical model through MOML change requests, GEF commands etc.
   * </p>
   * <p>
   * Currently following items are checked :
   * <ul>
   * <li>Actor's name</li>
   * <li>Count of input and output ports</li>
   * </ul>
   * Changed port names are assumed to be handled by a separate PortUpdateFeature (TBD).
   * </p>
   */
  @Override
  public IReason updateNeeded(IUpdateContext context) {
    // retrieve name from business model
    String actorName = null;
    int inputPortCount = 0;
    int outputPortCount = 0;
    boolean actorNameChanged = false;
    List<String> changedParameters = new ArrayList<>();

    PictogramElement pictogramElement = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    if (bo instanceof Entity && pictogramElement instanceof ContainerShape) {
      Entity actor = (Entity) bo;
      ContainerShape cs = (ContainerShape) pictogramElement;

      inputPortCount = actor.getInputPorts().size();
      outputPortCount = actor.getOutputPorts().size();

      if (!EditorUtils.containsExternallyDefinedFigure(pictogramElement)) {
        actorName = actor.getName();

        for (Shape shape : cs.getChildren()) {
          Graphiti.getPeService().getPropertyValue(shape, FeatureConstants.BO_NAME);
          BoCategory boCategory = BoCategory.retrieveFrom(shape);
          if (shape.getGraphicsAlgorithm() instanceof Text) {
            Text text = (Text) shape.getGraphicsAlgorithm();
            if (BoCategory.Actor.equals(boCategory) || BoCategory.CompositeActor.equals(boCategory)) {
              // it's the text field with the name of the actor
              String actorNameInGraph = text.getValue();
              actorNameChanged = !actorName.equals(actorNameInGraph);
            }
          }
        }
      }
      // TODO we need to also check in more detail for port changes
      // e.g. when we support changing the order of the ports, or renaming ports,
      // it's no longer sufficient to just count ports...
      for (Anchor anchor : cs.getAnchors()) {
        BoCategory boCategory = BoCategory.retrieveFrom(anchor);
        if (BoCategory.Input.equals(boCategory)) {
          inputPortCount--;
        }
        if (BoCategory.Output.equals(boCategory)) {
          outputPortCount--;
        }
      }
    }
    // build diff result and store some useful info in the update context
    if (actorNameChanged || (changedParameters.size() > 0) || (inputPortCount != 0) || (outputPortCount != 0)) {
      StringBuilder diffResultBldr = new StringBuilder();
      if (actorNameChanged) {
        diffResultBldr.append("Actor name changed; ");
        context.putProperty("ACTORNAME_CHANGED", "true");
      }
      if (inputPortCount != 0 || outputPortCount != 0) {
        diffResultBldr.append("Port change; ");
        context.putProperty("PORTS_CHANGED", "true");
      }
      return Reason.createTrueReason(diffResultBldr.toString());
    } else {
      return Reason.createFalseReason();
    }
  }

  @Override
  public boolean update(IUpdateContext context) {
    boolean result = false;
    boolean portsChange = "true".equals(context.getProperty("PORTS_CHANGED"));

    PictogramElement pe = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pe);
    if ((pe instanceof ContainerShape) && (bo instanceof Entity)) {
      ContainerShape cs = (ContainerShape) pe;
      Entity actor = (Entity) bo;

      for (Shape shape : cs.getChildren()) {
        BoCategory boCategory = BoCategory.retrieveFrom(shape);
        boolean isEntityCategory = BoCategory.Actor.equals(boCategory) || BoCategory.CompositeActor.equals(boCategory);
        if (isEntityCategory && shape.getGraphicsAlgorithm() instanceof Text) {
          Text text = (Text) shape.getGraphicsAlgorithm();
          text.setValue(actor.getName());
          result = true;
          Graphiti.getPeService().setPropertyValue(shape, FeatureConstants.BO_NAME, actor.getName());
        }
      }

      if (portsChange) {
        // current graphical port representations
        List<Anchor> inputPortAnchors = EditorUtils.getContainedPorts(cs, BoCategory.Input);
        List<Anchor> outputPortAnchors = EditorUtils.getContainedPorts(cs, BoCategory.Output);

        // the up-to-date ports in the triq actor
        EList<Port> inputPorts = actor.getInputPorts();
        EList<Port> outputPorts = actor.getOutputPorts();

        updateAnchorsForPorts(cs, inputPortAnchors, inputPorts);
        updateAnchorsForPorts(cs, outputPortAnchors, outputPorts);

        layoutPictogramElement(cs);

      }
    }
    return result;
  }

  private void updateAnchorsForPorts(ContainerShape containerShape, List<Anchor> anchors, EList<Port> ports) {
    // So now we need to adapt the graphical model :
    // - remove anchors for deleted ports
    // - rearrange anchor positions for ports that moved up/down in the actor's port list
    // - add anchors for new ports
    // - adjust anchor positions
    Set<Anchor> anchorsToBeDeleted = new HashSet<>();
    anchors.forEach(pa -> {
      Object bo = getBusinessObjectForPictogramElement(pa);
      if (bo == null || !(ports.contains(bo))) {
        anchorsToBeDeleted.add(pa);
        // This is explicitly needed it seems, cfr https://www.eclipse.org/forums/index.php/t/297903/ ,
        // otherwise we end up with the dreaded save-time error
        // "The object 'org.eclipse.graphiti.mm.pictograms.impl.PictogramLinkImpl@xxxxxxx' is not contained in a resource."
        EcoreUtil.delete(pa.getGraphicsAlgorithm());
        // TODO find and delete all linked connections and their linked relations
        IRemoveContext rc = new RemoveContext(pa);
        IFeatureProvider featureProvider = getFeatureProvider();
        IRemoveFeature removeFeature = featureProvider.getRemoveFeature(rc);
        if (removeFeature != null) {
          removeFeature.remove(rc);
        }
      }
    });
    anchors.removeAll(anchorsToBeDeleted);

    anchors.sort(
        (pa1, pa2) -> Integer.compare(ports.indexOf(getBusinessObjectForPictogramElement(pa1)), ports.indexOf(getBusinessObjectForPictogramElement(pa2))));

    // Now the anchor list should be in the same order as in the port list,
    // and should only contain anchors for which there are still ports on the actor.
    // But there may still be new ports for which no anchor is present yet in the graphical model.
    GraphicsAlgorithm containerGA = containerShape.getGraphicsAlgorithm();
    int height = containerGA.getHeight();
    int width = containerGA.getWidth();

    for (int i = 0; i < ports.size(); ++i) {
      Port p = ports.get(i);
      Anchor a = null;
      if (i < anchors.size()) {
        // check if the anchor at this position is for the right port
        a = anchors.get(i);
        if (!p.equals(getBusinessObjectForPictogramElement(a))) {
          // this should imply that we need to insert an extra anchor here
          a = null;
        }
      }
      if (a == null) {
        a = createAnchor(containerShape, containerGA, anchors, i, p, width, height);
      }
    }
  }

  private Anchor createAnchor(ContainerShape containerShape, GraphicsAlgorithm containerGA, List<Anchor> anchors, int i, Port p, int width, int height) {
    int halfPortSize = ActorAddFeature.PORT_SIZE / 2;
    int anchorsCount = anchors.size();
    anchorsCount = anchorsCount > 0 ? anchorsCount + 1 : 1;
    int yOffsetForPorts = height / (2 * anchorsCount);
    yOffsetForPorts = yOffsetForPorts > halfPortSize ? yOffsetForPorts : halfPortSize;

    ICreateService createService = Graphiti.getCreateService();
    FixPointAnchor anchor = createService.createFixPointAnchor(containerShape);
    int anchorX = p.isOutput() ? width : 0;
    anchor.setLocation(createService.createPoint(anchorX, yOffsetForPorts + i * ActorAddFeature.PORT_SIZE));
    anchor.setReferencedGraphicsAlgorithm(containerGA);
    link(anchor, p, p.isOutput() ? BoCategory.Output : BoCategory.Input);

    final Polygon portShape = Graphiti.getGaService().createPlainPolygon(anchor,
        new int[] { 0, 0, ActorAddFeature.PORT_SIZE, halfPortSize, 0, ActorAddFeature.PORT_SIZE });
    portShape.setForeground(manageColor(ActorAddFeature.PORT_FOREGROUND));
    IColorConstant portColour = p.isMultiPort() ? ActorAddFeature.PORT_BACKGROUND_MULTIPORT : ActorAddFeature.PORT_BACKGROUND_SINGLEPORT;
    portShape.setBackground(manageColor(portColour));
    portShape.setLineWidth(1);
    int shapeX = p.isOutput() ? -ActorAddFeature.PORT_SIZE : 0;
    Graphiti.getGaService().setLocationAndSize(portShape, shapeX, -halfPortSize, ActorAddFeature.PORT_SIZE, ActorAddFeature.PORT_SIZE);

    anchors.add(i, anchor);
    return anchor;
  }

  protected void link(PictogramElement pe, Object businessObject, BoCategory category) {
    super.link(pe, businessObject);
    // add property on the graphical model element, identifying the associated triq model element
    // so we can easily distinguish and identify them later on for updates etc
    category.storeIn(pe);
    if (businessObject instanceof NamedObj) {
      Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.BO_NAME, ((NamedObj) businessObject).getName());
    }
    Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.BO_CLASS, businessObject.getClass().getName());
  }
}
