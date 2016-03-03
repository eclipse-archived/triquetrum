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

import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
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
import org.eclipse.triquetrum.workflow.editor.TriqDiagramEditor;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.CompositeEntity;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;

public class EditorUtils {


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
   * This method delegates to ptolemy.kernel.CompositeEntity.uniqueName(String)
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
    while ((container != null) && !(container instanceof CompositeEntity)) {
      container = container.getContainer();
    }
    if (container == null) {
      return prefix;
    } else {
      return ((ptolemy.kernel.CompositeEntity) container.getWrappedObject()).uniqueName(prefix);
    }
  }

  /**
   * Find the diagram for the selected editor part.
   *
   * @return the diagram from the selected diagram editor
   */
  @SuppressWarnings("restriction")
  public static Diagram getSelectedDiagram() {
    Diagram result = null;
    IWorkbenchPage page = EclipseUtils.getPage();
    if (page != null) {
      for (IEditorReference editorRef : page.getEditorReferences()) {
        if (editorRef.getId().contains("triquetrum")) {
          result = ((TriqDiagramEditor) editorRef.getEditor(true)).getDiagramTypeProvider().getDiagram();
          break;
        }
      }
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
    if (editPart instanceof GraphitiShapeEditPart) {
      GraphitiShapeEditPart shapeEditPart = (GraphitiShapeEditPart) editPart;
      IFeatureProvider fp = shapeEditPart.getFeatureProvider();
      Object bo = fp.getBusinessObjectForPictogramElement(shapeEditPart.getPictogramElement());
      if (bo instanceof NamedObj) {
        result = (NamedObj) bo;
      }
    }
    return result;
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
}
