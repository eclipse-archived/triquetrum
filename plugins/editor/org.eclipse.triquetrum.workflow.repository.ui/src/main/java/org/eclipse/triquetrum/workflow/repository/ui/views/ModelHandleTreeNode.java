/*******************************************************************************
 * Copyright (c) 2017,2019 iSencia Belgium NV.
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
package org.eclipse.triquetrum.workflow.repository.ui.views;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.eclipse.triquetrum.workflow.WorkflowRepositoryService;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

class ModelHandleTreeNode extends AbstractTreeNode implements IAdaptable {
  
  private ModelHandlePropertySource propertySource = null;
  
  public ModelHandleTreeNode(ModelCodeTreeNode parent, ModelHandle modelHandle) {
    super(modelHandle);
    setParent(parent);
  }

  public ModelHandle getValue() {
    return (ModelHandle)super.getValue();
  }
  
  public String getModelCode() {
    return getValue().getCode();
  }
  
  public WorkflowRepositoryService getRepository() {
    return getParent().getParent().getValue();
  }
  
  @Override
  public ModelCodeTreeNode getParent() {
    return (ModelCodeTreeNode) super.getParent();
  }

  @Override
  public <T> T getAdapter(Class<T> adapter) {
    if(adapter == IPropertySource.class) {
      if(propertySource == null) {
        propertySource = new ModelHandlePropertySource();
      }
      return (T) propertySource;
    }
    return null;
  }

  public String toString() {
    return getValue().getVersion().toString();
  }
  
  
  /**
   * This gets us some ModelHandle info in the Properties view. 
   *
   */
  private class ModelHandlePropertySource implements IPropertySource {

    private static final String URI = "uri";
    private static final String VERSION = "version";
    private static final String CODE = "code";

    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {
      return new IPropertyDescriptor[] {
          new PropertyDescriptor(CODE, "Code"),
          new PropertyDescriptor(VERSION, "Version"),
          new PropertyDescriptor(URI, "Uri"),
      };
    }

    @Override
    public Object getPropertyValue(Object id) {
      ModelHandle handle = getValue();
      if(CODE.equals(id)) {
        return handle.getCode();
      } else if(VERSION.equals(id)) {
        return handle.getVersion();
      } else if(URI.equals(id)) {
        return handle.getResourceLocation();
      }
      return null;
    }

    @Override
    public Object getEditableValue() {
      return this;
    }

    @Override
    public boolean isPropertySet(Object id) {
      return false;
    }

    @Override
    public void resetPropertyValue(Object id) {
    }

    @Override
    public void setPropertyValue(Object id, Object value) {
    }
  }
}