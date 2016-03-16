package org.eclipse.triquetrum.workflow.editor.shapes.ptolemy;

import org.eclipse.draw2d.Graphics;

import ptolemy.vergil.kernel.attributes.VisibleAttribute;

public interface DrawingStrategy<A extends VisibleAttribute> {

  void draw(A visibleAttribute, Graphics graphics);

}
