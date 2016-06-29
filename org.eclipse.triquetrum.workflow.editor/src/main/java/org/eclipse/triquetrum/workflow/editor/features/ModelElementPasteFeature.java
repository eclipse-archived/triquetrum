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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.AbstractPasteFeature;
import org.eclipse.triquetrum.workflow.editor.util.BuildDiagramElementsFromPtolemyElementCommand;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.NamedObj;

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
    Object[] pes = getFromClipboard();

    double[] topLeftOriginal = EditorUtils.getTopLeftLocation(pes);
    double xOffset = context.getX() - topLeftOriginal[0];
    double yOffset = context.getY() - topLeftOriginal[1];

    for (Object peObj : pes) {
      if (peObj instanceof PictogramElement) {
        PictogramElement pe = (PictogramElement) peObj;
        double[] originalLocation = EditorUtils.getLocation(pe);
        double[] newLocation = new double[] {originalLocation[0] + xOffset, originalLocation[1] + yOffset};
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
            clonedPtObj = (ptolemy.kernel.util.NamedObj) ptObj.clone(model.getWrappedObject().workspace());
            String newName = EditorUtils.buildUniqueName(model, ptObj.getName());
            clonedPtObj.setName(newName);
            clonedPtObj.setDisplayName(newName);
            EditorUtils.setPtolemyContainer(clonedPtObj, model.getWrappedObject());
            EditorUtils.setPtolemyLocation(clonedPtObj, newLocation[0], newLocation[1]);
            // TODO move the cloned objects to the new location,
            // based on the currently selected location in the paste context.
            new BuildDiagramElementsFromPtolemyElementCommand(diagram, clonedPtObj).execute();
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
  }
}
