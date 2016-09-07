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
package org.eclipse.triquetrum.workflow.model.util;

import ptolemy.moml.filter.RemoveGraphicalClasses;

///////////////////////////////////////////////////////////////////
//// RemoveGraphicalClasses

/**
 * When this class is registered with the MoMLParser.setMoMLFilter() method, it will cause MoMLParser to filter out most graphical classes.
 *
 * <p>
 * This is needed in Triquetrum which is built on SWT i.o. Swing/AWT.
 * Only a few graphical Ptolemy elements are supported as-is, like the Display actor and some Plot actors.
 * </p>
 */
public class RemoveGraphicalClassesForTriquetrum extends RemoveGraphicalClasses {

  /**
   * Construct a filter that removes all graphical classes that are not supported in Triquetrum.
   */
    public RemoveGraphicalClassesForTriquetrum() {
      remove("ptolemy.actor.lib.gui.Display");
      remove("ptolemy.actor.lib.gui.XYPlotter");

      remove("ptolemy.vergil.icon.EditorIcon");
      remove("ptolemy.vergil.kernel.attributes.ArcAttribute");
      remove("ptolemy.vergil.kernel.attributes.ArrowAttribute");
      remove("ptolemy.vergil.kernel.attributes.EllipseAttribute");
      remove("ptolemy.vergil.kernel.attributes.ImageAttribute");
      remove("ptolemy.vergil.kernel.attributes.LineAttribute");
      remove("ptolemy.vergil.kernel.attributes.RectangleAttribute");
      remove("ptolemy.vergil.kernel.attributes.ResizablePolygonAttribute");
      remove("ptolemy.vergil.kernel.attributes.TextAttribute");
      remove("ptolemy.vergil.kernel.attributes.VisibleAttribute");
      // TODO add more when they become supported in Triquetrum
    }
}
