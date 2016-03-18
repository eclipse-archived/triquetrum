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
package org.eclipse.triquetrum.workflow.editor.shapes;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRenderer;
import org.eclipse.graphiti.platform.ga.IRendererContext;
import org.eclipse.graphiti.services.Graphiti;

public abstract class AbstractCustomModelElementShape extends RectangleFigure implements IGraphicsAlgorithmRenderer {

  private String iconURI;
  protected GraphicsAlgorithm ga;
  private IDiagramTypeProvider dtp;

  public AbstractCustomModelElementShape(IRendererContext rendererContext) {
    this.ga = rendererContext.getGraphicsAlgorithm();
    this.dtp = rendererContext.getDiagramTypeProvider();

    for (Property property : ga.getProperties()) {
      switch (property.getKey()) {
      case "iconResource":
        iconURI = property.getValue();
        break;
      default:
        break;
      }
    }
  }

  public String getIconURI() {
    return iconURI;
  }

  public Optional<String> getGaProperty(String key) {
    for (Property property : ga.getProperties()) {
      if (property.getKey().equals(key)) {
        return Optional.of(property.getValue());
      }
    }
    return Optional.empty();
  }

  public void addGaProperty(String key, String value) {
    Property property = MmFactory.eINSTANCE.createProperty();
    property.setKey(key);
    property.setValue(value);
    ga.getProperties().add(property);
  }

  protected void setGaWidth(GraphicsAlgorithm ga, int width, int height) {
    if (ga.getWidth() != width || ga.getHeight() != height) {
      final TransactionalEditingDomain editingDomain = dtp.getDiagramBehavior().getEditingDomain();

      final RecordingCommand command = new RecordingCommand(editingDomain, getIconURI()) {
        private IStatus result = null;

        @Override
        protected void doExecute() {
          try {
            Graphiti.getGaService().setSize(ga, width, height);
            Graphiti.getGaService().setSize(ga.getParentGraphicsAlgorithm(), width+15, height);
            result = Status.OK_STATUS;
          } catch (OperationCanceledException e) {
            result = Status.CANCEL_STATUS;
          }
        }

        @Override
        public Collection<?> getResult() {
          return result == null ? Collections.EMPTY_LIST : Collections.singletonList(result);
        }
      };

      // Execute (synchronously) the defined command in a proper EMF transaction
      editingDomain.getCommandStack().execute(command);
    }
  }
}
