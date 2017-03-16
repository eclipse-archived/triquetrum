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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.triquetrum.workflow.model.CompositeEntity;
import org.eclipse.triquetrum.workflow.model.TriqFactory;
import org.eclipse.triquetrum.workflow.model.TriqPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.triquetrum.workflow.model.CompositeEntity} object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class CompositeEntityItemProvider extends EntityItemProvider {
  /**
   * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public CompositeEntityItemProvider(AdapterFactory adapterFactory) {
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

    }
    return itemPropertyDescriptors;
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an {@link org.eclipse.emf.edit.command.AddCommand},
   * {@link org.eclipse.emf.edit.command.RemoveCommand} or {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
    if (childrenFeatures == null) {
      super.getChildrenFeatures(object);
      childrenFeatures.add(TriqPackage.Literals.COMPOSITE_ENTITY__ENTITIES);
      childrenFeatures.add(TriqPackage.Literals.COMPOSITE_ENTITY__RELATIONS);
    }
    return childrenFeatures;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EStructuralFeature getChildFeature(Object object, Object child) {
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
  }

  /**
   * This returns CompositeEntity.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/CompositeEntity"));
  }

  /**
   * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String getText(Object object) {
    String label = ((CompositeEntity) object).getName();
    return label == null || label.length() == 0 ? getString("_UI_CompositeEntity_type") : getString("_UI_CompositeEntity_type") + " " + label;
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

    switch (notification.getFeatureID(CompositeEntity.class)) {
    case TriqPackage.COMPOSITE_ENTITY__ENTITIES:
    case TriqPackage.COMPOSITE_ENTITY__RELATIONS:
      fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

    newChildDescriptors.add(createChildParameter(TriqPackage.Literals.COMPOSITE_ENTITY__ENTITIES, TriqFactory.eINSTANCE.createEntity()));

    newChildDescriptors.add(createChildParameter(TriqPackage.Literals.COMPOSITE_ENTITY__ENTITIES, TriqFactory.eINSTANCE.createCompositeEntity()));

    newChildDescriptors.add(createChildParameter(TriqPackage.Literals.COMPOSITE_ENTITY__ENTITIES, TriqFactory.eINSTANCE.createActor()));

    newChildDescriptors.add(createChildParameter(TriqPackage.Literals.COMPOSITE_ENTITY__ENTITIES, TriqFactory.eINSTANCE.createCompositeActor()));

    newChildDescriptors.add(createChildParameter(TriqPackage.Literals.COMPOSITE_ENTITY__RELATIONS, TriqFactory.eINSTANCE.createRelation()));
  }

}
