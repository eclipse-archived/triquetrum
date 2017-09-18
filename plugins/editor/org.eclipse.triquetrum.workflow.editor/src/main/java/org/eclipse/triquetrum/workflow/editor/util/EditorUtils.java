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
package org.eclipse.triquetrum.workflow.editor.util;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.PlatformGraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.internal.parts.DiagramEditPart;
import org.eclipse.graphiti.ui.platform.GraphitiShapeEditPart;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.RGBA;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.triquetrum.workflow.editor.PortCategory;
import org.eclipse.triquetrum.workflow.editor.TriqDiagramEditor;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.Linkable;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.TriqFactory;
import org.eclipse.triquetrum.workflow.model.util.PtObjectBuilderAndApplierVisitor;
import org.eclipse.triquetrum.workflow.rcp.EclipseUtils;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;

import ptolemy.actor.Director;
import ptolemy.actor.IORelation;
import ptolemy.actor.TypedIOPort;
import ptolemy.actor.TypedIORelation;
import ptolemy.data.expr.Variable;
import ptolemy.kernel.ComponentEntity;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.Entity;
import ptolemy.kernel.util.Attribute;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.Locatable;
import ptolemy.kernel.util.Location;
import ptolemy.kernel.util.NameDuplicationException;
import ptolemy.moml.Vertex;
import ptolemy.vergil.kernel.attributes.TextAttribute;

public class EditorUtils {

  /**
   *
   * @param pe
   * @return true if this pe is in collapsed state, based on triq-specific properties being maintained on it.
   */
  public static boolean isCollapsed(PictogramElement pe) {
    return Graphiti.getPeService().getPropertyValue(pe, "isCollapsed") != null && Graphiti.getPeService().getPropertyValue(pe, "isCollapsed").equals("true");
  }

  /**
   * Set the pe as being collapsed and store its original (expanded) size. If the pe is already collapsed, nothing is done.
   *
   * @param pe
   * @param originalWidth
   * @param originalHeight
   */
  public static void setCollapsed(PictogramElement pe, int originalWidth, int originalHeight) {
    if (!isCollapsed(pe)) {
      Graphiti.getPeService().setPropertyValue(pe, "original_width", String.valueOf(originalWidth));
      Graphiti.getPeService().setPropertyValue(pe, "original_height", String.valueOf(originalHeight));
      Graphiti.getPeService().setPropertyValue(pe, "isCollapsed", "true");
    }
  }

  /**
   * Set the pe as being expanded, i.e. showing all its contents as originally designed. If the pe is not collapsed, nothing is done and the pe's current size
   * is returned.
   *
   * @param pe
   * @return the original size of the pe, in its expanded state.
   */
  public static int[] setExpanded(PictogramElement pe) {
    if (isCollapsed(pe)) {
      int originalWidth = Integer.parseInt(Graphiti.getPeService().getPropertyValue(pe, "original_width"));
      int originalHeight = Integer.parseInt(Graphiti.getPeService().getPropertyValue(pe, "original_height"));
      Graphiti.getPeService().setPropertyValue(pe, "isCollapsed", "false");
      return new int[] { originalWidth, originalHeight };
    } else {
      return new int[] { pe.getGraphicsAlgorithm().getWidth(), pe.getGraphicsAlgorithm().getHeight() };
    }
  }

  /**
   * Opens a dialog to change the color.
   *
   * @param color
   *          the color to change
   * @return the changed color
   */
  public static String editColor(Diagram diagram, String colorStr) {
    Color color = null;
    if (colorStr == null) {
      color = Graphiti.getGaService().manageColor(diagram, IColorConstant.BLACK);
    } else {
      color = EditorUtils.buildColorFromString(diagram, colorStr);
    }
    ColorDialog colorDialog = new ColorDialog(Display.getDefault().getActiveShell());
    colorDialog.setText("Choose color");
    colorDialog.setRGB(new RGB(color.getRed(), color.getGreen(), color.getBlue()));

    RGB retRgb = colorDialog.open();
    if (retRgb == null) {
      return colorStr;
    } else {
      return EditorUtils.toString(retRgb);
    }
  }

  /**
   * Current color-treatment is based on storing a comma-separated string of rgba values. RGBA values must be integers in the range [0-255].
   * <p>
   * Each time the actual color is needed, this must be parsed and converted to a Color instance.
   *
   * TODO : add Color as an EMF model entity, and figure out how EMF Forms can integrate a color picker control that supports data binding etc.
   * </p>
   * Remark that Graphiti's Color does not support setting opacity/alpha. So its value is currently silently ignored in Triq.
   *
   * @param colorStr
   *          a comma-separated rgba value list. It can be partially specified, e.g. just rg or so. The non-specified elements will be set with defaults of 0
   *          for the color components and 1 for the alpha.
   * @return
   *
   * @see RGBA
   * @see Color
   */
  public static Color buildColorFromString(Diagram diagram, String colorStr) {
    String[] colorParts = colorStr != null ? colorStr.split(",") : new String[] {};
    int red = 0;
    int green = 0;
    int blue = 0;
    // alpha is not supported by graphiti colors it seems...
    // int alpha = 1;

    if (colorParts.length > 0) {
      red = Integer.parseInt(colorParts[0]);
    }
    if (colorParts.length > 1) {
      green = Integer.parseInt(colorParts[1]);
    }
    if (colorParts.length > 2) {
      blue = Integer.parseInt(colorParts[2]);
    }

    return Graphiti.getGaService().manageColor(diagram, red, green, blue);
  }

  /**
   * @param color
   * @return a ","-separated list of the color's rgb values followed by a fixed 255 alpha value
   */
  public static String toString(Color color) {
    return color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ",255";
  }

  /**
   * @param color
   * @return a ","-separated list of the color's rgb values followed by a fixed 255 alpha value
   */
  public static String toString(RGB color) {
    return color.red + "," + color.green + "," + color.blue + ",255";
  }

  /**
   * Creates a unique name for a new model element, in its container. The relatedObj should be either the container or a sibling of the to-be-created element,
   * i.e. it should be in the same container as the new element.
   * <p>
   * This method delegates to ptolemy.kernel.Entity.uniqueName(String)
   * </p>
   *
   * @param relatedObj
   *          a model element in the same container as the new element, or the container itself.
   * @param prefix
   *          a prefix for the generated unique name.
   * @return a unique name constructed from the prefix.
   */
  public static String buildUniqueName(NamedObj relatedObj, String prefix) {
    NamedObj container = relatedObj;
    while ((container != null) && !(container instanceof org.eclipse.triquetrum.workflow.model.Entity)) {
      container = container.getContainer();
    }
    if (container == null || container.getWrappedObject() == null) {
      return prefix;
    } else {
      return ((Entity<?>) container.getWrappedObject()).uniqueName(prefix);
    }
  }

  /**
   * Find the selected diagram editor.
   *
   * @return the selected diagram editor
   */
  public static TriqDiagramEditor getSelectedDiagramEditor() {
    TriqDiagramEditor result = null;
    IWorkbenchPage page = EclipseUtils.getPage();
    if (page != null) {
      IEditorPart activeEditor = page.getActiveEditor();
      if (activeEditor instanceof TriqDiagramEditor) {
        result = (TriqDiagramEditor) activeEditor;
      } else {
        for (IEditorReference editorRef : page.getEditorReferences()) {
          if (editorRef.getId().contains("triquetrum")) {
            result = ((TriqDiagramEditor) editorRef.getEditor(true));
            break;
          }
        }
      }
    }
    return result;
  }

  /**
   * Find the diagram for the selected editor part.
   *
   * @return the diagram from the selected diagram editor
   */
  public static Diagram getSelectedDiagram() {
    Diagram result = null;
    TriqDiagramEditor editor = getSelectedDiagramEditor();
    if (editor != null) {
      result = editor.getDiagramTypeProvider().getDiagram();
    }
    return result;
  }

  /**
   * Find the model from the selected editor part and its contained diagram.
   *
   * @return the model from the selected diagram editor
   */
  @SuppressWarnings("restriction")
  public static CompositeActor getSelectedModel() {
    NamedObj result = null;
    IWorkbenchPage page = EclipseUtils.getPage();
    final ISelection sel = page != null ? page.getSelection() : null;
    if (sel != null && sel instanceof IStructuredSelection) {
      final IStructuredSelection str = (IStructuredSelection) sel;
      Object res = str.getFirstElement();
      result = getModelObjectForSelection(res);
      if (result == null && res instanceof DiagramEditPart) {
        // FIXME a temp hack here to get the ptolemy model from the Diagram
        // don't know how to get the linked emf business model root from a Diagram, as the linkmodel doesn't seem to contain that one?
        // so we pass via a contained entity (i.e. via it's editpart)
        List<?> diagramChildren = ((DiagramEditPart) res).getChildren();
        if (!diagramChildren.isEmpty()) {
          result = getModelObjectForSelection(diagramChildren.get(0));
        }
      }
    }
    result = (result != null) ? result.topLevel() : null;
    return (CompositeActor) result;
  }

  /**
   * Find the model element for the selected edit part
   *
   * @param editPart
   * @return
   */
  public static NamedObj getModelObjectForSelection(Object editPart) {
    NamedObj result = null;
    PictogramElement pe = (editPart instanceof PictogramElement) ? (PictogramElement) editPart : null;
    if (editPart instanceof GraphitiShapeEditPart) {
      GraphitiShapeEditPart shapeEditPart = (GraphitiShapeEditPart) editPart;
      pe = shapeEditPart.getPictogramElement();
    }

    if (pe != null) {
      Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
      if (bo instanceof NamedObj) {
        result = (NamedObj) bo;
      }
    }

    return result;
  }

  /**
   * Adds the child in a container. Works on Ptolemy objects!
   *
   * @param child
   * @param container
   * @throws IllegalActionException
   * @throws NameDuplicationException
   */
  public static void setPtolemyContainer(ptolemy.kernel.util.NamedObj child, ptolemy.kernel.util.NamedObj container)
      throws IllegalActionException, NameDuplicationException {
    if (child instanceof ComponentEntity) {
      ((ComponentEntity) child).setContainer((CompositeEntity) container);
    } else if (child instanceof Director) {
      ((Director) child).setContainer(container);
    } else if (child instanceof Vertex) {
      ((TypedIORelation) ((Vertex) child).getContainer()).setContainer((CompositeEntity) container);
    } else if (child instanceof TextAttribute) {
      ((TextAttribute) child).setContainer(container);
    } else if (child instanceof Variable) {
      ((Variable) child).setContainer(container);
    } else if (child instanceof TypedIOPort) {
      ((TypedIOPort) child).setContainer((CompositeEntity) container);
    }
  }

  // TODO FP this with Java 8 features!
  public static double[] getTopLeftLocation(Object[] selectedObjects) {
    boolean foundOneValidThing = false;
    double minX = Double.MAX_VALUE;
    double minY = Double.MAX_VALUE;

    if (selectedObjects != null && selectedObjects.length > 0) {
      for (Object object : selectedObjects) {
        double[] xy = null;
        if (object instanceof GraphicsAlgorithm) {
          xy = getLocation((GraphicsAlgorithm) object);
        } else if (object instanceof PictogramElement) {
          xy = getLocation((PictogramElement) object);
        } else if (object instanceof NamedObj) {
          xy = getLocation((NamedObj) object);
        } else if (object instanceof ptolemy.kernel.util.NamedObj) {
          xy = getLocation((ptolemy.kernel.util.NamedObj) object);
        }
        if (xy != null) {
          foundOneValidThing = true;
          minX = Math.min(xy[0], minX);
          minY = Math.min(xy[1], minY);
        }
      }
    }

    if (foundOneValidThing) {
      return new double[] { minX, minY };
    } else {
      return new double[] { 0, 0 };
    }
  }

  public static double[] getLocation(PictogramElement pe) {
    return getLocation(pe.getGraphicsAlgorithm());
  }

  public static double[] getLocation(GraphicsAlgorithm ga) {
    return new double[] { ga.getX(), ga.getY() };
  }

  public static double[] getLocation(NamedObj triqObject) {
    return getLocation(triqObject.getWrappedObject());
  }

  public static double[] getLocation(ptolemy.kernel.util.NamedObj ptObject) {
    if (ptObject instanceof Location) {
      return ((Location) ptObject).getLocation();
    } else {
      List<Location> locations = ptObject.attributeList(Location.class);
      if (locations.size() > 0) {
        return locations.get(0).getLocation();
      }
    }
    return null;
  }

  /**
   * Set the location info on the model element, more precisely on the wrapped Ptolemy II model object.
   *
   * Graphiti maintains location info in the graphical algorithm linked to the shape&business object. But to guarantee that an export to a Ptolemy MOML file
   * also has the location info, we need to push locations to the Ptolemy layer as well.
   *
   * @param modelElement
   *          the Triquetrum model element wrapping a Ptolemy model element
   * @param x
   * @param y
   * @throws IllegalActionException
   * @throws NameDuplicationException
   *           when adding the location attribute failed because there was already another linked child object with a same name.
   */
  public static void setLocation(NamedObj modelElement, double x, double y) throws IllegalActionException, NameDuplicationException {
    setPtolemyLocation(modelElement.getWrappedObject(), x, y);
  }

  public static void setPtolemyLocation(ptolemy.kernel.util.NamedObj ptObject, double x, double y) throws IllegalActionException, NameDuplicationException {
    double[] location = new double[] { x, y };
    if (ptObject instanceof Locatable) {
      ((Locatable) ptObject).setLocation(location);
      ptolemy.kernel.util.NamedObj cont = ptObject.getContainer();
      cont.attributeChanged((Attribute) ptObject);
    }
    List<Locatable> attributes = ptObject.attributeList(Locatable.class);
    if (attributes == null)
      return;
    if (attributes.size() > 0) {
      Locatable locationAttribute = attributes.get(0);
      locationAttribute.setLocation(location);
      ptObject.attributeChanged((Attribute) attributes.get(0));
    } else {
      new Location(ptObject, "_location").setLocation(location);
    }
  }

  /**
   *
   * @param shape
   * @param gaClass
   * @return the graphicsAlgorithm of the given class, contained in the shape or in child shapes
   */
  @SuppressWarnings("unchecked")
  public static <T extends GraphicsAlgorithm> T getGraphicsAlgorithmOfShape(Shape shape, Class<T> gaClass) {
    T ga = null;
    if (gaClass.isInstance(shape.getGraphicsAlgorithm())) {
      ga = (T) shape.getGraphicsAlgorithm();
    } else if (shape instanceof ContainerShape) {
      for (Shape childShape : ((ContainerShape) shape).getChildren()) {
        if (gaClass.isInstance(childShape.getGraphicsAlgorithm())) {
          ga = (T) childShape.getGraphicsAlgorithm();
          break;
        }
      }
    }
    return ga;
  }

  /**
   *
   * @param pe
   * @return true if the containerShape contains an externally defined figure (based on svg or ptolemy)
   */
  public static boolean containsExternallyDefinedFigure(PictogramElement pe) {
    boolean extFigure = (pe.getGraphicsAlgorithm() instanceof PlatformGraphicsAlgorithm);
    if (!extFigure && (pe instanceof ContainerShape)) {
      // check if the platform shape is somewhere in there
      for (Shape childShape : ((ContainerShape) pe).getChildren()) {
        if (childShape.getGraphicsAlgorithm() instanceof PlatformGraphicsAlgorithm) {
          extFigure = true;
          break;
        }
      }
    }
    if (!extFigure) {
      // check if the platform shape is somewhere in there
      for (GraphicsAlgorithm childShape : pe.getGraphicsAlgorithm().getGraphicsAlgorithmChildren()) {
        if (childShape instanceof PlatformGraphicsAlgorithm) {
          extFigure = true;
          break;
        }
      }
    }
    return extFigure;
  }

  /**
   * Returns a freshly created list of the port anchors for the given actor shape (or composite actor shape) that are in the given category. The list can be
   * manipulated/changed without risk of impacting the original actorShape definition (at least when the contained anchors properties are not touched!).
   *
   * @param actorShape
   * @param portIoType
   *          PortCategory.Input or Output
   * @return
   */
  public static List<Anchor> getContainedPorts(ContainerShape actorShape, PortCategory portIoType) {
    List<Anchor> portShapes = new LinkedList<>();
    for (Anchor anchor : actorShape.getAnchors()) {
      FixPointAnchor fpa = (FixPointAnchor) anchor;
      PortCategory portCategory = PortCategory.retrieveFrom(anchor);
      if (portIoType.equals(portCategory)) {
        portShapes.add(fpa);
      }
    }
    return portShapes;
  }

  /**
   *
   * @param source
   * @param target
   * @param ptRelation
   * @return
   * @throws IllegalActionException
   */
  public static Relation createRelation(NamedObj source, NamedObj target, IORelation ptRelation) throws IllegalActionException {
    Relation relation = null;
    if (target instanceof Vertex) {
      // use the vertex's relation
      relation = (Relation) target.getContainer();
      ((Linkable) source).link(relation);
    } else if (source instanceof Vertex) {
      // use the vertex's relation
      relation = (Relation) source.getContainer();
      ((Linkable) target).link(relation);
    } else {
      // Create a new relation directly linking 2 ports.
      // We need to determine what should be the relation container : the lowest common container that is a CompositeActor.
      relation = TriqFactory.eINSTANCE.createRelation();
      CompositeActor relationContainer = null;
      NamedObj potentialRelationContainer = source.getLowestCommonContainer(target);
      if (potentialRelationContainer instanceof CompositeActor) {
        relationContainer = (CompositeActor) potentialRelationContainer;
      } else {
        // this should only happen when connecting an output port and an input port of the same atomic actor
        if (potentialRelationContainer.getContainer() instanceof CompositeActor) {
          relationContainer = (CompositeActor) potentialRelationContainer.getContainer();
        } else {
          throw new IllegalActionException(source.getWrappedObject(), target.getWrappedObject(),
              "Unsupported source and target hierarchy for creating a relation");
        }
      }

      if (ptRelation != null) {
        relation.setWrappedObject(ptRelation);
      } else {
        relation.setName(EditorUtils.buildUniqueName(relationContainer, "_R"));
      }
      relationContainer.getRelations().add(relation);
      relation.welcome(new PtObjectBuilderAndApplierVisitor(), true);
      // need to make sure that source and target ports have their Pt wrapped object in order as well,
      // before doing all the linking.
      source.welcome(new PtObjectBuilderAndApplierVisitor(), true);
      target.welcome(new PtObjectBuilderAndApplierVisitor(), true);
      ((Linkable) source).link(relation);
      ((Linkable) target).link(relation);
    }
    return relation;
  }
}
