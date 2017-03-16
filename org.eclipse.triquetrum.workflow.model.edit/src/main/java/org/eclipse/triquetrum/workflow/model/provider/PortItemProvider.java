/**
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 */
package org.eclipse.triquetrum.workflow.model.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.TriqPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.triquetrum.workflow.model.Port} object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class PortItemProvider extends NamedObjItemProvider {
  /**
   * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public PortItemProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
    if (itemPropertyDescriptors == null) {
      super.getPropertyDescriptors(object);

      addInputPropertyDescriptor(object);
      addOutputPropertyDescriptor(object);
      addMultiPortPropertyDescriptor(object);
      addDirectionPropertyDescriptor(object);
      addLinkedRelationsPropertyDescriptor(object);
      addInsideLinkedRelationsPropertyDescriptor(object);
      addOutsideLinkedRelationsPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Input feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addInputPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_Port_input_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Port_input_feature", "_UI_Port_type"),
        TriqPackage.Literals.PORT__INPUT, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
  }

  /**
   * This adds a property descriptor for the Output feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addOutputPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_Port_output_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Port_output_feature", "_UI_Port_type"),
        TriqPackage.Literals.PORT__OUTPUT, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
  }

  /**
   * This adds a property descriptor for the Multi Port feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addMultiPortPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_Port_multiPort_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Port_multiPort_feature", "_UI_Port_type"),
        TriqPackage.Literals.PORT__MULTI_PORT, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
  }

  /**
   * This adds a property descriptor for the Direction feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addDirectionPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_Port_direction_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Port_direction_feature", "_UI_Port_type"),
        TriqPackage.Literals.PORT__DIRECTION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
  }

  /**
   * This adds a property descriptor for the Linked Relations feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addLinkedRelationsPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_Port_linkedRelations_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Port_linkedRelations_feature", "_UI_Port_type"),
        TriqPackage.Literals.PORT__LINKED_RELATIONS, true, false, true, null, null, null));
  }

  /**
   * This adds a property descriptor for the Inside Linked Relations feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addInsideLinkedRelationsPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_Port_insideLinkedRelations_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Port_insideLinkedRelations_feature", "_UI_Port_type"),
        TriqPackage.Literals.PORT__INSIDE_LINKED_RELATIONS, true, false, true, null, null, null));
  }

  /**
   * This adds a property descriptor for the Outside Linked Relations feature. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void addOutsideLinkedRelationsPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_Port_outsideLinkedRelations_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Port_outsideLinkedRelations_feature", "_UI_Port_type"),
        TriqPackage.Literals.PORT__OUTSIDE_LINKED_RELATIONS, false, false, false, null, null, null));
  }

  /**
   * This returns Port.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/Port"));
  }

  /**
   * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String getText(Object object) {
    String label = ((Port) object).getName();
    return label == null || label.length() == 0 ? getString("_UI_Port_type") : getString("_UI_Port_type") + " " + label;
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating a viewer notification, which it passes to
   * {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void notifyChanged(Notification notification) {
    updateChildren(notification);

    switch (notification.getFeatureID(Port.class)) {
    case TriqPackage.PORT__INPUT:
    case TriqPackage.PORT__OUTPUT:
    case TriqPackage.PORT__MULTI_PORT:
    case TriqPackage.PORT__DIRECTION:
      fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
      return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created under this object. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
    super.collectNewChildDescriptors(newChildDescriptors, object);
  }

}
