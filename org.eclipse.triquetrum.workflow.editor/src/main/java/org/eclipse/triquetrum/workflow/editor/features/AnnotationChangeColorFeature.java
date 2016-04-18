/*******************************************************************************
 * Copyright (c) 2015-2016 iSencia Belgium NV.
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
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.triquetrum.workflow.editor.BoCategories;
import org.eclipse.triquetrum.workflow.editor.ImageConstants;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.Annotation;

/**
 * This is not in use for the moment.
 * Color changes for annotations are handled via the EMF Form, with a custom ColorControlRenderer.
 */
public class AnnotationChangeColorFeature extends AbstractCustomFeature {

	public AnnotationChangeColorFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		return "Change text color";
	}

	@Override
	public String getDescription() {
		return "Change the annotation's text color";
	}

  @Override
  public String getImageId() {
    return ImageConstants.IMG_COLOR_CHANGE;
  }

	@Override
	public boolean canExecute(ICustomContext context) {
    boolean ret = false;
    PictogramElement pe = context.getInnerPictogramElement();
    if(pe==null) {
      PictogramElement[] pes = context.getPictogramElements();
      if (pes != null && pes.length == 1) {
        pe = pes[0];
      }
    }
    if (pe != null) {
      BoCategories boCategory = BoCategories.retrieveFrom(pe);
      ret = (BoCategories.Annotation.equals(boCategory));
    }
    return ret;
	}

	public void execute(ICustomContext context) {
    PictogramElement[] pes = context.getPictogramElements();
    if (pes != null && pes.length == 1) {
      Object bo = getBusinessObjectForPictogramElement(pes[0]);
      if (bo instanceof Annotation) {
        Annotation modelElement = (Annotation) bo;
        pes[0].getGraphicsAlgorithm().getForeground();
        String newColor = EditorUtils.editColor(getDiagram(), modelElement.getColor());
        modelElement.setColor(newColor);
      }
    }
	}
}
