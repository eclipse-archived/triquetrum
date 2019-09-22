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
package org.eclipse.triquetrum.workflow.editor;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IShapeSelectionInfo;
import org.eclipse.graphiti.tb.ShapeSelectionInfoImpl;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementConfigureFeature;
import org.eclipse.triquetrum.workflow.editor.features.PauseFeature;
import org.eclipse.triquetrum.workflow.editor.features.ResumeFeature;
import org.eclipse.triquetrum.workflow.editor.features.RunFeature;
import org.eclipse.triquetrum.workflow.editor.features.StopFeature;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Vertex;

public class TriqToolBehaviorProvider extends DefaultToolBehaviorProvider {

  /**
   *
   * @param diagramTypeProvider
   */
  public TriqToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
    super(diagramTypeProvider);
  }

  @Override
  public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {
    IContextButtonPadData data = super.getContextButtonPad(context);
    PictogramElement pe = context.getPictogramElement();

    // 1. set the generic context buttons
    setGenericContextButtons(data, pe, CONTEXT_BUTTON_DELETE | CONTEXT_BUTTON_UPDATE);

    // 2. ico a vertex, add one context-button, which offers all
    // available connection-features as drag&drop features...
    Object bo = getFeatureProvider().getBusinessObjectForPictogramElement(pe);
    if (bo instanceof Vertex) {
      Anchor anchor = null;
      if (pe instanceof AnchorContainer) {
        // a Vertex has one chopbox anchor
        anchor = Graphiti.getPeService().getChopboxAnchor((AnchorContainer) pe);
      }

      if (anchor != null) {
        CreateConnectionContext ccc = new CreateConnectionContext();
        ccc.setSourcePictogramElement(pe);
        ccc.setSourceAnchor(anchor);

        ContextButtonEntry button = new ContextButtonEntry(null, context);
        button.setText("Create connection"); //$NON-NLS-1$
        button.setIconId(ImageConstants.IMG_CONNECTION);
        ICreateConnectionFeature[] features = getFeatureProvider().getCreateConnectionFeatures();
        for (ICreateConnectionFeature feature : features) {
          if (feature.isAvailable(ccc) && feature.canStartConnection(ccc))
            button.addDragAndDropFeature(feature);
        }

        if (button.getDragAndDropFeatures().size() > 0) {
          data.getDomainSpecificContextButtons().add(button);
        }
      }
    } else {
      // vertex has no configuration needs
      CustomContext cc = new CustomContext(new PictogramElement[] { pe });
      ICustomFeature[] cf = getFeatureProvider().getCustomFeatures(cc);
      for (int i = 0; i < cf.length; i++) {
        ICustomFeature iCustomFeature = cf[i];
        ContextButtonEntry button = new ContextButtonEntry(iCustomFeature, cc);
        data.getDomainSpecificContextButtons().add(button);
      }
    }

    return data;
  }

  @Override
  public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
    ICustomFeature customFeature = new ModelElementConfigureFeature((TriqFeatureProvider) getFeatureProvider());
    // canExecute() tests especially if the context contains an actor or director or so
    if (customFeature.canExecute(context)) {
      return customFeature;
    } else {
      return super.getDoubleClickFeature(context);
    }
  }

  @Override
  public ICustomFeature getCommandFeature(CustomContext context, String hint) {
    switch (hint) {
    case RunFeature.HINT:
      return new RunFeature(getFeatureProvider());
    case PauseFeature.HINT:
      return new PauseFeature(getFeatureProvider());
    case ResumeFeature.HINT:
      return new ResumeFeature(getFeatureProvider());
    case StopFeature.HINT:
      return new StopFeature(getFeatureProvider());
    default:
      return super.getCommandFeature(context, hint);
    }
  }

  @Override
  public Object getToolTip(GraphicsAlgorithm ga) {
    PictogramElement pe = ga.getPictogramElement();
    Object bo = getFeatureProvider().getBusinessObjectForPictogramElement(pe);
    if (bo instanceof Vertex) {
      // For a vertex, we want to show the name of the containing relation
      bo = ((Vertex) bo).getContainer();
    }
    if (bo instanceof NamedObj) {
      String name = ((NamedObj) bo).getName();
      if (name != null && !(name.length() == 0)) {
        return name;
      }
    }
    return super.getToolTip(ga);
  }

  @Override
  public GraphicsAlgorithm[] getClickArea(PictogramElement pe) {
    IFeatureProvider featureProvider = getFeatureProvider();
    Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
    if (bo instanceof Entity) {
      GraphicsAlgorithm invisible = pe.getGraphicsAlgorithm();
      if(invisible.getGraphicsAlgorithmChildren().size()>0) {
        return new GraphicsAlgorithm[] {invisible.getGraphicsAlgorithmChildren().get(0)};
      } else {
        return new GraphicsAlgorithm[] {invisible};
      }
    }
    return super.getClickArea(pe);
  }

  @Override
  public GraphicsAlgorithm getSelectionBorder(PictogramElement pe) {
    if (pe instanceof ContainerShape) {
      GraphicsAlgorithm invisible = pe.getGraphicsAlgorithm();
      if (!invisible.getLineVisible()) {
        EList<GraphicsAlgorithm> graphicsAlgorithmChildren = invisible.getGraphicsAlgorithmChildren();
        if (!graphicsAlgorithmChildren.isEmpty()) {
          return graphicsAlgorithmChildren.get(0);
        }
      }
    }
    return super.getSelectionBorder(pe);
  }
  
  public IShapeSelectionInfo getSelectionInfoForShape(Shape shape) {
    IShapeSelectionInfo si = new ShapeSelectionInfoImpl();
    si.setColor(IColorConstant.BLUE);
    si.setLineStyle(LineStyle.DASH);
    return si;
  }


  @Override
  public boolean isShowFlyoutPalette() {
    return true;
  }
}
