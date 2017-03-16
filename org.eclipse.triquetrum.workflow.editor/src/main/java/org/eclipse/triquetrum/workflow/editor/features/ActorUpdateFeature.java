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



import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.triquetrum.workflow.editor.BoCategory;
import org.eclipse.triquetrum.workflow.editor.Category;
import org.eclipse.triquetrum.workflow.editor.PortCategory;
import org.eclipse.triquetrum.workflow.editor.shapes.ActorShapes;
import org.eclipse.triquetrum.workflow.editor.shapes.PortShapes;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.editor.util.PortAnchorPair;
import org.eclipse.triquetrum.workflow.model.Direction;
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
    boolean actorNameChanged = false;
    boolean portsChanged = false;
    List<String> changedParameters = new ArrayList<>();

    PictogramElement pictogramElement = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    if (bo instanceof Entity && pictogramElement instanceof ContainerShape) {
      Entity actor = (Entity) bo;
      ContainerShape cs = (ContainerShape) pictogramElement;

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
      // it's no longer sufficient to just look at port categories...
      if(actor.getPorts().size()!=cs.getAnchors().size()) {
        portsChanged = true;
      } else {
        for (Anchor anchor : cs.getAnchors()) {
          PortCategory anchorCategory = PortCategory.retrieveFrom(anchor);
          Port p = (Port) getBusinessObjectForPictogramElement(anchor);
          if (anchorCategory != PortCategory.retrieveFrom(p)) {
            portsChanged = true;
            break;
          }
        }
      }
    }
    // build diff result and store some useful info in the update context
    if (actorNameChanged || (changedParameters.size() > 0) || portsChanged) {
      StringBuilder diffResultBldr = new StringBuilder();
      if (actorNameChanged) {
        diffResultBldr.append("Actor name changed; ");
        context.putProperty("ACTORNAME_CHANGED", "true");
      }
      if (portsChanged) {
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
        updateAnchorsForPorts(cs, actor);
        layoutPictogramElement(cs);
      }
    }
    return result;
  }

  private void updateAnchorsForPorts(ContainerShape containerShape, Entity actor) {
    // So now we need to adapt the graphical model :
    // - remove anchors for deleted ports
    // - rearrange anchor positions for ports that moved up/down in the actor's port list
    // - add anchors for new ports
    // - adjust anchor positions
    List<Anchor> anchors = new ArrayList<>(containerShape.getAnchors());
    List<Port> ports = new ArrayList<>(actor.getPorts());
    
    List<PortAnchorPair> paList = new ArrayList<>();
    
    anchors.forEach(anchor -> {
      Object bo = getBusinessObjectForPictogramElement(anchor);
      boolean removeAnchor = (bo == null || !(ports.remove(bo)));
      if(!removeAnchor) {
        PortCategory pc = PortCategory.retrieveFrom((Port)bo);
        PortCategory ac = PortCategory.retrieveFrom(anchor);
        if (ac!=pc) {
          pc.storeIn(anchor);
        }
      }
      
      if(removeAnchor) {
        // This is explicitly needed it seems, cfr https://www.eclipse.org/forums/index.php/t/297903/ ,
        // otherwise we end up with the dreaded save-time error
        // "The object 'org.eclipse.graphiti.mm.pictograms.impl.PictogramLinkImpl@xxxxxxx' is not contained in a resource."
        EcoreUtil.delete(anchor.getGraphicsAlgorithm());
        // TODO find and delete all linked connections and their linked relations
        IRemoveContext rc = new RemoveContext(anchor);
        IFeatureProvider featureProvider = getFeatureProvider();
        IRemoveFeature removeFeature = featureProvider.getRemoveFeature(rc);
        if (removeFeature != null) {
          removeFeature.remove(rc);
        }
        anchor=null;
      }
      if(bo!=null && bo instanceof Port) {
        paList.add(new PortAnchorPair((Port) bo, anchor));
      }
    });
    
    ports.forEach(port -> paList.add(new PortAnchorPair(port, null)));

    paList.sort(
        (pa1, pa2) -> Integer.compare(ports.indexOf(getBusinessObjectForPictogramElement(pa1.anchor)), ports.indexOf(getBusinessObjectForPictogramElement(pa2.anchor))));
    
    // Group anchors and ports depending on their category/direction and then update anchors for each direction
    // TODO double check that ordering is not lost in this black-magic-oneliner 
    Map<Direction, List<PortAnchorPair>> categorizedPorts = paList.stream().collect(groupingBy(PortAnchorPair::getPortDirection, mapping(Function.identity(), toList())));
    categorizedPorts.forEach((direction, pairs) -> updateForDirection(containerShape, direction, pairs));
  }

  private void updateForDirection(ContainerShape containerShape, Direction direction, List<PortAnchorPair> pairList) {
    // The list should only contain pairs for which there are still ports on the actor.
    // But there may still be new ports for which no anchor is present yet in the graphical model.
    int portCount = pairList.size();
    for (int i = 0; i < portCount; ++i) {
      Port p = pairList.get(i).port;
      Anchor anchor = pairList.get(i).anchor;
      if (anchor == null) {
        anchor = PortShapes.createAnchor(getDiagram(), containerShape, direction, p, i, portCount);
        link(anchor, p, BoCategory.Port, PortCategory.valueOf(direction));
      } else {
        FixPointAnchor fpa = (FixPointAnchor) anchor;
        fpa.setLocation(ActorShapes.determineAnchorLocation(containerShape, direction, i, portCount));
      }
    }
  }

  protected void link(PictogramElement pe, Object businessObject, Category... categories) {
    super.link(pe, businessObject);
    // add property on the graphical model element, identifying the associated triq model element
    // so we can easily distinguish and identify them later on for updates etc
    for (Category category : categories) {
      category.storeIn(pe);
    }
    if (businessObject instanceof NamedObj) {
      Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.BO_NAME, ((NamedObj) businessObject).getName());
    }
    Graphiti.getPeService().setPropertyValue(pe, FeatureConstants.BO_CLASS, businessObject.getClass().getName());
  }
}
