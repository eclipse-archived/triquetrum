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
package org.eclipse.triquetrum.workflow.editor;

import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.CLASS;
import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.DISPLAY_NAME;
import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.ICON;
import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.ICON_TYPE;
import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.PROPERTY;
import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.TYPE;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.util.URI;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICopyFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IPasteFeature;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;
import org.eclipse.triquetrum.workflow.editor.features.ActorAddFeature;
import org.eclipse.triquetrum.workflow.editor.features.ActorDeleteFeature;
import org.eclipse.triquetrum.workflow.editor.features.ActorLayoutFeature;
import org.eclipse.triquetrum.workflow.editor.features.ActorUpdateFeature;
import org.eclipse.triquetrum.workflow.editor.features.AnnotationAddFeature;
import org.eclipse.triquetrum.workflow.editor.features.AnnotationResizeFeature;
import org.eclipse.triquetrum.workflow.editor.features.AnnotationUpdateFeature;
import org.eclipse.triquetrum.workflow.editor.features.AttributeAddFeature;
import org.eclipse.triquetrum.workflow.editor.features.AttributeLayoutFeature;
import org.eclipse.triquetrum.workflow.editor.features.CompositeActorAddFeature;
import org.eclipse.triquetrum.workflow.editor.features.CompositeActorCollapseExpandFeature;
import org.eclipse.triquetrum.workflow.editor.features.ConnectionAddFeature;
import org.eclipse.triquetrum.workflow.editor.features.ConnectionCreateFeature;
import org.eclipse.triquetrum.workflow.editor.features.ConnectionDeleteFeature;
import org.eclipse.triquetrum.workflow.editor.features.ConnectionReconnectFeature;
import org.eclipse.triquetrum.workflow.editor.features.ConnectionRemoveFeature;
import org.eclipse.triquetrum.workflow.editor.features.DirectorAddFeature;
import org.eclipse.triquetrum.workflow.editor.features.DirectorUpdateFeature;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementConfigureFeature;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementCopyFeature;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementCreateFeature;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementNameDirectEditFeature;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementPasteFeature;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementResizeFeature;
import org.eclipse.triquetrum.workflow.editor.features.ParameterUpdateFeature;
import org.eclipse.triquetrum.workflow.editor.features.PortUpdateFeature;
import org.eclipse.triquetrum.workflow.editor.features.TriqDefaultDeleteFeature;
import org.eclipse.triquetrum.workflow.editor.features.VertexAddFeature;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.Actor;
import org.eclipse.triquetrum.workflow.model.Annotation;
import org.eclipse.triquetrum.workflow.model.Attribute;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.Director;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Parameter;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates all Triq editor features, including ICreateFeatures for actors as defined in palette extension contributions.
 *
 */
public class TriqFeatureProvider extends DefaultFeatureProvider {

  private final static Logger LOGGER = LoggerFactory.getLogger(TriqFeatureProvider.class);

  public static final String ICONTYPE_IMG = "img";
  public static final String ICONTYPE_SVG = "svg";
  public static final String ICONTYPE_PTOLEMY = "ptolemy";
  public static final String DEFAULT_ACTOR_IMG = "icons/actor.gif";

  public static final String PALETTE_CONTRIBUTION_EXTENSION_ID = "org.eclipse.triquetrum.workflow.editor.paletteContribution";

  /**
   * This map maintains the registered palette contributions for groups. For the moment we only support 1 level, i.e. no
   * tree/hierarchy yet. This map is consulted during the execution of
   * org.eclipse.triquetrum.workflow.editor.TriqToolBehaviorProvider.getPalette()
   */
  private Map<String, IConfigurationElement> rootgroupsByName = new HashMap<>();

  /**
   * @param dtp
   */
  public TriqFeatureProvider(IDiagramTypeProvider dtp) {
    super(dtp);
  }

  public Map<String, IConfigurationElement> getRootgroupsByName() {
    return rootgroupsByName;
  }

  @Override
  public ILayoutFeature getLayoutFeature(ILayoutContext context) {
    BoCategory boCategory = BoCategory.retrieveFrom(context.getPictogramElement());
    if (BoCategory.CompositeActor.equals(boCategory) || BoCategory.Actor.equals(boCategory)) {
      return new ActorLayoutFeature(this);
    } else if (BoCategory.Attribute.equals(boCategory) || BoCategory.Attribute.equals(boCategory)) {
      return new AttributeLayoutFeature(this);
    }
    return super.getLayoutFeature(context);
  }

  @Override
  public ICustomFeature[] getCustomFeatures(ICustomContext context) {
    PictogramElement[] pes = context.getPictogramElements();
    boolean isCollapsed = false;
    if (pes != null && pes.length == 1) {
      isCollapsed = EditorUtils.isCollapsed(pes[0]);
    }
    return new ICustomFeature[] { new ModelElementConfigureFeature(this), new CompositeActorCollapseExpandFeature(this, isCollapsed) };
  }

  @Override
  public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
    // simply return all create connection features
    return getCreateConnectionFeatures();
  }

  @Override
  public ICreateConnectionFeature[] getCreateConnectionFeatures() {
    return new ICreateConnectionFeature[] { new ConnectionCreateFeature(this) };
  }

  @Override
  public ICreateFeature[] getCreateFeatures() {
    // these are all managed via the palette construction
    return new ICreateFeature[0];
  }

  @Override
  public IAddFeature getAddFeature(IAddContext context) {
    if (context.getNewObject() instanceof Director) {
      return new DirectorAddFeature(this);
    } else if (context.getNewObject() instanceof Actor) {
      return new ActorAddFeature(this);
    } else if (context.getNewObject() instanceof CompositeActor) {
      return new CompositeActorAddFeature(this);
    } else if (context.getNewObject() instanceof Relation) {
      return new ConnectionAddFeature(this);
    } else if (context.getNewObject() instanceof Vertex) {
      return new VertexAddFeature(this);
    } else if (context.getNewObject() instanceof Annotation) {
      return new AnnotationAddFeature(this);
    } else if (context.getNewObject() instanceof Attribute) {
      return new AttributeAddFeature(this);
    } 
    return super.getAddFeature(context);
  }

  @Override
  public IUpdateFeature getUpdateFeature(IUpdateContext context) {
    PictogramElement pictogramElement = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    if (bo instanceof Parameter) {
      return new ParameterUpdateFeature(this);
    } else if (bo instanceof Port) {
      return new PortUpdateFeature(this);
    } else if (bo instanceof Annotation) {
      return new AnnotationUpdateFeature(this);
    } else if (bo instanceof Director) {
      return new DirectorUpdateFeature(this);
    } else if ((bo instanceof Actor) || (bo instanceof CompositeActor && !(pictogramElement instanceof Diagram))) {
      return new ActorUpdateFeature(this);
    }
    return super.getUpdateFeature(context);
  }

  @Override
  public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
    PictogramElement pictogramElement = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    if (bo instanceof Annotation) {
      return new AnnotationResizeFeature(this);
    } else if (bo instanceof NamedObj) {
      return new ModelElementResizeFeature(this);
    }
    return super.getResizeShapeFeature(context);
  }

  @Override
  public ICopyFeature getCopyFeature(ICopyContext context) {
    return new ModelElementCopyFeature(this);
  }

  @Override
  public IPasteFeature getPasteFeature(IPasteContext context) {
    return new ModelElementPasteFeature(this);
  }

  @Override
  public IDeleteFeature getDeleteFeature(IDeleteContext context) {
    PictogramElement pe = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pe);
    if (bo instanceof Actor) {
      return new ActorDeleteFeature(this);
    } else if (pe instanceof Connection) {
      return new ConnectionDeleteFeature(this);
    }
    return new TriqDefaultDeleteFeature(this);
  }

  @Override
  public IRemoveFeature getRemoveFeature(IRemoveContext context) {
    PictogramElement pictogramElement = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    if (bo instanceof Relation) {
      return new ConnectionRemoveFeature(this);
    }
    return super.getRemoveFeature(context);
  }

  @Override
  public IReconnectionFeature getReconnectionFeature(IReconnectionContext context) {
    return new ConnectionReconnectFeature(this);
  }

  @Override
  public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
    PictogramElement pe = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pe);
    if (bo instanceof NamedObj) {
      return new ModelElementNameDirectEditFeature(this);
    }
    return super.getDirectEditingFeature(context);
  }

  public ModelElementCreateFeature buildCreateFeature(IConfigurationElement parentGroupElem, IConfigurationElement cfgElem) {
    ModelElementCreateFeature mecf = null;
    String group = parentGroupElem != null ? parentGroupElem.getAttribute(DISPLAY_NAME) : null;
    String label = cfgElem.getAttribute(DISPLAY_NAME);
    String clazz = cfgElem.getAttribute(CLASS);
    String iconResource = cfgElem.getAttribute(ICON);
    String iconType = cfgElem.getAttribute(ICON_TYPE);

    String categoryTypeStr = cfgElem.getAttribute(TYPE);
    try {
      BoCategory category = BoCategory.valueOf(categoryTypeStr);

      // look for (optional) attributes
      Map<String, String> properties = new HashMap<>();
      for (IConfigurationElement child : cfgElem.getChildren()) {
        if (PROPERTY.equals(child.getName())) {
          String name = child.getAttribute("name");
          String value = child.getAttribute("value");
          properties.put(name, value);
        }
      }
      mecf = buildCreateFeature(cfgElem.getContributor().getName(), group, label, clazz, iconResource, iconType, category, properties);
    } catch (Exception e1) {
      LOGGER.error("Error adding feature from palette for " + label, e1);
    }
    return mecf;
  }

  public ModelElementCreateFeature buildCreateFeature(String contributorName, String group, String label, String clazz, String iconResource, String iconType,
      BoCategory category, Map<String, String> properties) {
    
    ModelElementCreateFeature mecf = null;
    iconResource = !StringUtils.isBlank(iconResource) ? iconResource : DEFAULT_ACTOR_IMG;
    iconType = StringUtils.isBlank(iconType) ? ICONTYPE_IMG : iconType;

    switch (iconType) {
    case ICONTYPE_SVG:
    case ICONTYPE_PTOLEMY:
      try {
        iconResource = URI.createPlatformPluginURI(contributorName + "/" + iconResource, true).toString();
        mecf = new ModelElementCreateFeature(this, group, category, label, clazz, iconResource, iconType, properties);
      } catch (Exception e) {
        LOGGER.error("Error adding feature from palette for " + label, e);
      }
      break;
    case ICONTYPE_IMG:
    default:
      // Images are managed by an ImageProvider in Graphiti
      // By default it's not possible to add extra images from outside the ImageProvider implementation itself,
      // e.g. as needed to allow extra image uploads by additional bundles, via extension points etc.

      // option 1 to register extra images from palette extensions
      // not an ideal hack, as we need to replicate Graphiti's ad-hoc internal image key construction
      // (cfr. org.eclipse.graphiti.ui.internal.services.impl.ImageService.createImageDescriptorForId(String, String))
      // ImageDescriptor imageDescriptor = TriqEditorPlugin.imageDescriptorFromPlugin(cfgElem.getContributor().getName(),
      // iconResource);
      // GraphitiUIPlugin.getDefault().getImageRegistry().put(makeKey(TriqDiagramTypeProvider.ID,iconResource),
      // imageDescriptor);

      // option 2 : cfr suggestion in https://bugs.eclipse.org/bugs/show_bug.cgi?id=366452#c8
      mecf = new ModelElementCreateFeature(this, group, category, label, clazz, iconResource, iconType, properties);
      ((TriqDiagramTypeProvider) getDiagramTypeProvider()).getImageProvider().myAddImageFilePath(contributorName, iconResource, iconResource);
    }
    return mecf;
  }

  // this is needed for option 1 to register extra images from palette extensions
  // private String makeKey(String dtp, String imageId) {
  // return dtp + "||" + imageId;
  // }

}
