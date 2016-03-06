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
package org.eclipse.triquetrum.workflow.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.triquetrum.workflow.model.TriqFactory
 * @model kind="package"
 * @generated
 */
public interface TriqPackage extends EPackage {
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "model";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/triquetrum";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "org.eclipse.triquetrum.workflow";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  TriqPackage eINSTANCE = org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl <em>Named Obj</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getNamedObj()
   * @generated
   */
  int NAMED_OBJ = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_OBJ__NAME = 0;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_OBJ__ATTRIBUTES = 1;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_OBJ__WRAPPED_TYPE = 2;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_OBJ__WRAPPED_OBJECT = 3;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_OBJ__DEEP_COMPLETE = 4;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_OBJ__ICON_ID = 5;

  /**
   * The number of structural features of the '<em>Named Obj</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_OBJ_FEATURE_COUNT = 6;

  /**
   * The operation id for the '<em>Get Container</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_OBJ___GET_CONTAINER = 0;

  /**
   * The operation id for the '<em>Top Level</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_OBJ___TOP_LEVEL = 1;

  /**
   * The operation id for the '<em>Set Property</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_OBJ___SET_PROPERTY__STRING_STRING_STRING = 2;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_OBJ___BUILD_WRAPPED_OBJECT = 3;

  /**
   * The operation id for the '<em>Initialize From</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_OBJ___INITIALIZE_FROM__NAMEDOBJ = 4;

  /**
   * The number of operations of the '<em>Named Obj</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_OBJ_OPERATION_COUNT = 5;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.AttributeImpl <em>Attribute</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.workflow.model.impl.AttributeImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getAttribute()
   * @generated
   */
  int ATTRIBUTE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__NAME = NAMED_OBJ__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__ATTRIBUTES = NAMED_OBJ__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__WRAPPED_TYPE = NAMED_OBJ__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__WRAPPED_OBJECT = NAMED_OBJ__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__DEEP_COMPLETE = NAMED_OBJ__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__ICON_ID = NAMED_OBJ__ICON_ID;

  /**
   * The number of structural features of the '<em>Attribute</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_FEATURE_COUNT = NAMED_OBJ_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Container</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE___GET_CONTAINER = NAMED_OBJ___GET_CONTAINER;

  /**
   * The operation id for the '<em>Top Level</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE___TOP_LEVEL = NAMED_OBJ___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE___SET_PROPERTY__STRING_STRING_STRING = NAMED_OBJ___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE___BUILD_WRAPPED_OBJECT = NAMED_OBJ___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Initialize From</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE___INITIALIZE_FROM__NAMEDOBJ = NAMED_OBJ___INITIALIZE_FROM__NAMEDOBJ;

  /**
   * The number of operations of the '<em>Attribute</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_OPERATION_COUNT = NAMED_OBJ_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.AnnotationImpl <em>Annotation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.workflow.model.impl.AnnotationImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getAnnotation()
   * @generated
   */
  int ANNOTATION = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__NAME = ATTRIBUTE__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__ATTRIBUTES = ATTRIBUTE__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__WRAPPED_TYPE = ATTRIBUTE__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__WRAPPED_OBJECT = ATTRIBUTE__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__DEEP_COMPLETE = ATTRIBUTE__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__ICON_ID = ATTRIBUTE__ICON_ID;

  /**
   * The feature id for the '<em><b>Text</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__TEXT = ATTRIBUTE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Font Family</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__FONT_FAMILY = ATTRIBUTE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Text Size</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__TEXT_SIZE = ATTRIBUTE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Bold</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__BOLD = ATTRIBUTE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Italic</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__ITALIC = ATTRIBUTE_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Color</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__COLOR = ATTRIBUTE_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>Annotation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 6;

  /**
   * The operation id for the '<em>Get Container</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION___GET_CONTAINER = ATTRIBUTE___GET_CONTAINER;

  /**
   * The operation id for the '<em>Top Level</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION___TOP_LEVEL = ATTRIBUTE___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION___SET_PROPERTY__STRING_STRING_STRING = ATTRIBUTE___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION___BUILD_WRAPPED_OBJECT = ATTRIBUTE___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Initialize From</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION___INITIALIZE_FROM__NAMEDOBJ = ATTRIBUTE___INITIALIZE_FROM__NAMEDOBJ;

  /**
   * The number of operations of the '<em>Annotation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_OPERATION_COUNT = ATTRIBUTE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.ParameterImpl <em>Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.workflow.model.impl.ParameterImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getParameter()
   * @generated
   */
  int PARAMETER = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__NAME = ATTRIBUTE__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__ATTRIBUTES = ATTRIBUTE__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__WRAPPED_TYPE = ATTRIBUTE__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__WRAPPED_OBJECT = ATTRIBUTE__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__DEEP_COMPLETE = ATTRIBUTE__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__ICON_ID = ATTRIBUTE__ICON_ID;

  /**
   * The feature id for the '<em><b>Expression</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__EXPRESSION = ATTRIBUTE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 1;

  /**
   * The operation id for the '<em>Get Container</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER___GET_CONTAINER = ATTRIBUTE___GET_CONTAINER;

  /**
   * The operation id for the '<em>Top Level</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER___TOP_LEVEL = ATTRIBUTE___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER___SET_PROPERTY__STRING_STRING_STRING = ATTRIBUTE___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER___BUILD_WRAPPED_OBJECT = ATTRIBUTE___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Initialize From</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER___INITIALIZE_FROM__NAMEDOBJ = ATTRIBUTE___INITIALIZE_FROM__NAMEDOBJ;

  /**
   * The number of operations of the '<em>Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_OPERATION_COUNT = ATTRIBUTE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.DirectorImpl <em>Director</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.workflow.model.impl.DirectorImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getDirector()
   * @generated
   */
  int DIRECTOR = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTOR__NAME = ATTRIBUTE__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTOR__ATTRIBUTES = ATTRIBUTE__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTOR__WRAPPED_TYPE = ATTRIBUTE__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTOR__WRAPPED_OBJECT = ATTRIBUTE__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTOR__DEEP_COMPLETE = ATTRIBUTE__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTOR__ICON_ID = ATTRIBUTE__ICON_ID;

  /**
   * The number of structural features of the '<em>Director</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTOR_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Container</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTOR___GET_CONTAINER = ATTRIBUTE___GET_CONTAINER;

  /**
   * The operation id for the '<em>Top Level</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTOR___TOP_LEVEL = ATTRIBUTE___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTOR___SET_PROPERTY__STRING_STRING_STRING = ATTRIBUTE___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTOR___BUILD_WRAPPED_OBJECT = ATTRIBUTE___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Initialize From</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTOR___INITIALIZE_FROM__NAMEDOBJ = ATTRIBUTE___INITIALIZE_FROM__NAMEDOBJ;

  /**
   * The operation id for the '<em>Get Parameters</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTOR___GET_PARAMETERS = ATTRIBUTE_OPERATION_COUNT + 0;

  /**
   * The number of operations of the '<em>Director</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTOR_OPERATION_COUNT = ATTRIBUTE_OPERATION_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.EntityImpl <em>Entity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.workflow.model.impl.EntityImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getEntity()
   * @generated
   */
  int ENTITY = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__NAME = NAMED_OBJ__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__ATTRIBUTES = NAMED_OBJ__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__WRAPPED_TYPE = NAMED_OBJ__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__WRAPPED_OBJECT = NAMED_OBJ__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__DEEP_COMPLETE = NAMED_OBJ__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__ICON_ID = NAMED_OBJ__ICON_ID;

  /**
   * The feature id for the '<em><b>Input Ports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__INPUT_PORTS = NAMED_OBJ_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Output Ports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__OUTPUT_PORTS = NAMED_OBJ_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_FEATURE_COUNT = NAMED_OBJ_FEATURE_COUNT + 2;

  /**
   * The operation id for the '<em>Get Container</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY___GET_CONTAINER = NAMED_OBJ___GET_CONTAINER;

  /**
   * The operation id for the '<em>Top Level</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY___TOP_LEVEL = NAMED_OBJ___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY___SET_PROPERTY__STRING_STRING_STRING = NAMED_OBJ___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY___BUILD_WRAPPED_OBJECT = NAMED_OBJ___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Initialize From</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY___INITIALIZE_FROM__NAMEDOBJ = NAMED_OBJ___INITIALIZE_FROM__NAMEDOBJ;

  /**
   * The operation id for the '<em>Get Parameters</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY___GET_PARAMETERS = NAMED_OBJ_OPERATION_COUNT + 0;

  /**
   * The number of operations of the '<em>Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_OPERATION_COUNT = NAMED_OBJ_OPERATION_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.CompositeEntityImpl <em>Composite Entity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.workflow.model.impl.CompositeEntityImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getCompositeEntity()
   * @generated
   */
  int COMPOSITE_ENTITY = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__NAME = ENTITY__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__ATTRIBUTES = ENTITY__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__WRAPPED_TYPE = ENTITY__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__WRAPPED_OBJECT = ENTITY__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__DEEP_COMPLETE = ENTITY__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__ICON_ID = ENTITY__ICON_ID;

  /**
   * The feature id for the '<em><b>Input Ports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__INPUT_PORTS = ENTITY__INPUT_PORTS;

  /**
   * The feature id for the '<em><b>Output Ports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__OUTPUT_PORTS = ENTITY__OUTPUT_PORTS;

  /**
   * The feature id for the '<em><b>Entities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__ENTITIES = ENTITY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Relations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__RELATIONS = ENTITY_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Composite Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 2;

  /**
   * The operation id for the '<em>Get Container</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___GET_CONTAINER = ENTITY___GET_CONTAINER;

  /**
   * The operation id for the '<em>Top Level</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___TOP_LEVEL = ENTITY___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___SET_PROPERTY__STRING_STRING_STRING = ENTITY___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___BUILD_WRAPPED_OBJECT = ENTITY___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Initialize From</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___INITIALIZE_FROM__NAMEDOBJ = ENTITY___INITIALIZE_FROM__NAMEDOBJ;

  /**
   * The operation id for the '<em>Get Parameters</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___GET_PARAMETERS = ENTITY___GET_PARAMETERS;

  /**
   * The number of operations of the '<em>Composite Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY_OPERATION_COUNT = ENTITY_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.ActorImpl <em>Actor</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.workflow.model.impl.ActorImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getActor()
   * @generated
   */
  int ACTOR = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR__NAME = ENTITY__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR__ATTRIBUTES = ENTITY__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR__WRAPPED_TYPE = ENTITY__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR__WRAPPED_OBJECT = ENTITY__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR__DEEP_COMPLETE = ENTITY__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR__ICON_ID = ENTITY__ICON_ID;

  /**
   * The feature id for the '<em><b>Input Ports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR__INPUT_PORTS = ENTITY__INPUT_PORTS;

  /**
   * The feature id for the '<em><b>Output Ports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR__OUTPUT_PORTS = ENTITY__OUTPUT_PORTS;

  /**
   * The number of structural features of the '<em>Actor</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Container</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR___GET_CONTAINER = ENTITY___GET_CONTAINER;

  /**
   * The operation id for the '<em>Top Level</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR___TOP_LEVEL = ENTITY___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR___SET_PROPERTY__STRING_STRING_STRING = ENTITY___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR___BUILD_WRAPPED_OBJECT = ENTITY___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Initialize From</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR___INITIALIZE_FROM__NAMEDOBJ = ENTITY___INITIALIZE_FROM__NAMEDOBJ;

  /**
   * The operation id for the '<em>Get Parameters</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR___GET_PARAMETERS = ENTITY___GET_PARAMETERS;

  /**
   * The number of operations of the '<em>Actor</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTOR_OPERATION_COUNT = ENTITY_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.CompositeActorImpl <em>Composite Actor</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.workflow.model.impl.CompositeActorImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getCompositeActor()
   * @generated
   */
  int COMPOSITE_ACTOR = 8;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__NAME = COMPOSITE_ENTITY__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__ATTRIBUTES = COMPOSITE_ENTITY__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__WRAPPED_TYPE = COMPOSITE_ENTITY__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__WRAPPED_OBJECT = COMPOSITE_ENTITY__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__DEEP_COMPLETE = COMPOSITE_ENTITY__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__ICON_ID = COMPOSITE_ENTITY__ICON_ID;

  /**
   * The feature id for the '<em><b>Input Ports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__INPUT_PORTS = COMPOSITE_ENTITY__INPUT_PORTS;

  /**
   * The feature id for the '<em><b>Output Ports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__OUTPUT_PORTS = COMPOSITE_ENTITY__OUTPUT_PORTS;

  /**
   * The feature id for the '<em><b>Entities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__ENTITIES = COMPOSITE_ENTITY__ENTITIES;

  /**
   * The feature id for the '<em><b>Relations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__RELATIONS = COMPOSITE_ENTITY__RELATIONS;

  /**
   * The feature id for the '<em><b>Director</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__DIRECTOR = COMPOSITE_ENTITY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Composite Actor</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR_FEATURE_COUNT = COMPOSITE_ENTITY_FEATURE_COUNT + 1;

  /**
   * The operation id for the '<em>Get Container</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___GET_CONTAINER = COMPOSITE_ENTITY___GET_CONTAINER;

  /**
   * The operation id for the '<em>Top Level</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___TOP_LEVEL = COMPOSITE_ENTITY___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___SET_PROPERTY__STRING_STRING_STRING = COMPOSITE_ENTITY___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___BUILD_WRAPPED_OBJECT = COMPOSITE_ENTITY___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Initialize From</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___INITIALIZE_FROM__NAMEDOBJ = COMPOSITE_ENTITY___INITIALIZE_FROM__NAMEDOBJ;

  /**
   * The operation id for the '<em>Get Parameters</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___GET_PARAMETERS = COMPOSITE_ENTITY___GET_PARAMETERS;

  /**
   * The number of operations of the '<em>Composite Actor</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR_OPERATION_COUNT = COMPOSITE_ENTITY_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl <em>Port</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.workflow.model.impl.PortImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getPort()
   * @generated
   */
  int PORT = 9;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT__NAME = NAMED_OBJ__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT__ATTRIBUTES = NAMED_OBJ__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT__WRAPPED_TYPE = NAMED_OBJ__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT__WRAPPED_OBJECT = NAMED_OBJ__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT__DEEP_COMPLETE = NAMED_OBJ__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT__ICON_ID = NAMED_OBJ__ICON_ID;

  /**
   * The feature id for the '<em><b>Input</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT__INPUT = NAMED_OBJ_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Output</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT__OUTPUT = NAMED_OBJ_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Linked Relations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT__LINKED_RELATIONS = NAMED_OBJ_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Multi Port</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT__MULTI_PORT = NAMED_OBJ_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Port</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_FEATURE_COUNT = NAMED_OBJ_FEATURE_COUNT + 4;

  /**
   * The operation id for the '<em>Get Container</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT___GET_CONTAINER = NAMED_OBJ___GET_CONTAINER;

  /**
   * The operation id for the '<em>Top Level</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT___TOP_LEVEL = NAMED_OBJ___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT___SET_PROPERTY__STRING_STRING_STRING = NAMED_OBJ___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT___BUILD_WRAPPED_OBJECT = NAMED_OBJ___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Initialize From</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT___INITIALIZE_FROM__NAMEDOBJ = NAMED_OBJ___INITIALIZE_FROM__NAMEDOBJ;

  /**
   * The operation id for the '<em>Can Accept New Connection</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT___CAN_ACCEPT_NEW_CONNECTION = NAMED_OBJ_OPERATION_COUNT + 0;

  /**
   * The number of operations of the '<em>Port</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_OPERATION_COUNT = NAMED_OBJ_OPERATION_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.RelationImpl <em>Relation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.workflow.model.impl.RelationImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getRelation()
   * @generated
   */
  int RELATION = 10;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION__NAME = NAMED_OBJ__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION__ATTRIBUTES = NAMED_OBJ__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION__WRAPPED_TYPE = NAMED_OBJ__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION__WRAPPED_OBJECT = NAMED_OBJ__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION__DEEP_COMPLETE = NAMED_OBJ__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION__ICON_ID = NAMED_OBJ__ICON_ID;

  /**
   * The feature id for the '<em><b>Linked Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION__LINKED_PORTS = NAMED_OBJ_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Relation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION_FEATURE_COUNT = NAMED_OBJ_FEATURE_COUNT + 1;

  /**
   * The operation id for the '<em>Get Container</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION___GET_CONTAINER = NAMED_OBJ___GET_CONTAINER;

  /**
   * The operation id for the '<em>Top Level</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION___TOP_LEVEL = NAMED_OBJ___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION___SET_PROPERTY__STRING_STRING_STRING = NAMED_OBJ___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION___BUILD_WRAPPED_OBJECT = NAMED_OBJ___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Initialize From</em>' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION___INITIALIZE_FROM__NAMEDOBJ = NAMED_OBJ___INITIALIZE_FROM__NAMEDOBJ;

  /**
   * The number of operations of the '<em>Relation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION_OPERATION_COUNT = NAMED_OBJ_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '<em>Ptolemy Named Obj</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see ptolemy.kernel.util.NamedObj
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getPtolemyNamedObj()
   * @generated
   */
  int PTOLEMY_NAMED_OBJ = 11;


  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.NamedObj <em>Named Obj</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named Obj</em>'.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj
   * @generated
   */
  EClass getNamedObj();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getName()
   * @see #getNamedObj()
   * @generated
   */
  EAttribute getNamedObj_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getAttributes <em>Attributes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Attributes</em>'.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getAttributes()
   * @see #getNamedObj()
   * @generated
   */
  EReference getNamedObj_Attributes();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getWrappedType <em>Wrapped Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Wrapped Type</em>'.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getWrappedType()
   * @see #getNamedObj()
   * @generated
   */
  EAttribute getNamedObj_WrappedType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getWrappedObject <em>Wrapped Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Wrapped Object</em>'.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getWrappedObject()
   * @see #getNamedObj()
   * @generated
   */
  EAttribute getNamedObj_WrappedObject();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.NamedObj#isDeepComplete <em>Deep Complete</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Deep Complete</em>'.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#isDeepComplete()
   * @see #getNamedObj()
   * @generated
   */
  EAttribute getNamedObj_DeepComplete();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getIconId <em>Icon Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Icon Id</em>'.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getIconId()
   * @see #getNamedObj()
   * @generated
   */
  EAttribute getNamedObj_IconId();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getContainer() <em>Get Container</em>}' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the '<em>Get Container</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getContainer()
   * @generated
   */
  EOperation getNamedObj__GetContainer();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#topLevel() <em>Top Level</em>}' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the '<em>Top Level</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#topLevel()
   * @generated
   */
  EOperation getNamedObj__TopLevel();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#setProperty(java.lang.String, java.lang.String, java.lang.String) <em>Set Property</em>}' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the '<em>Set Property</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#setProperty(java.lang.String, java.lang.String, java.lang.String)
   * @generated
   */
  EOperation getNamedObj__SetProperty__String_String_String();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#buildWrappedObject() <em>Build Wrapped Object</em>}' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the '<em>Build Wrapped Object</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#buildWrappedObject()
   * @generated
   */
  EOperation getNamedObj__BuildWrappedObject();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#initializeFrom(ptolemy.kernel.util.NamedObj) <em>Initialize From</em>}' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the '<em>Initialize From</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#initializeFrom(ptolemy.kernel.util.NamedObj)
   * @generated
   */
  EOperation getNamedObj__InitializeFrom__NamedObj();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Attribute <em>Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Attribute
   * @generated
   */
  EClass getAttribute();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Annotation <em>Annotation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Annotation</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Annotation
   * @generated
   */
  EClass getAnnotation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Annotation#getText <em>Text</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Text</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Annotation#getText()
   * @see #getAnnotation()
   * @generated
   */
  EAttribute getAnnotation_Text();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Annotation#getFontFamily <em>Font Family</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Font Family</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Annotation#getFontFamily()
   * @see #getAnnotation()
   * @generated
   */
  EAttribute getAnnotation_FontFamily();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Annotation#getTextSize <em>Text Size</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Text Size</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Annotation#getTextSize()
   * @see #getAnnotation()
   * @generated
   */
  EAttribute getAnnotation_TextSize();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Annotation#isBold <em>Bold</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Bold</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Annotation#isBold()
   * @see #getAnnotation()
   * @generated
   */
  EAttribute getAnnotation_Bold();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Annotation#isItalic <em>Italic</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Italic</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Annotation#isItalic()
   * @see #getAnnotation()
   * @generated
   */
  EAttribute getAnnotation_Italic();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Annotation#getColor <em>Color</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Color</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Annotation#getColor()
   * @see #getAnnotation()
   * @generated
   */
  EAttribute getAnnotation_Color();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Parameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Parameter
   * @generated
   */
  EClass getParameter();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Parameter#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Expression</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Parameter#getExpression()
   * @see #getParameter()
   * @generated
   */
  EAttribute getParameter_Expression();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Director <em>Director</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Director</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Director
   * @generated
   */
  EClass getDirector();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Director#getParameters() <em>Get Parameters</em>}' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the '<em>Get Parameters</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Director#getParameters()
   * @generated
   */
  EOperation getDirector__GetParameters();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Entity <em>Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Entity
   * @generated
   */
  EClass getEntity();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.workflow.model.Entity#getInputPorts <em>Input Ports</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Input Ports</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Entity#getInputPorts()
   * @see #getEntity()
   * @generated
   */
  EReference getEntity_InputPorts();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.workflow.model.Entity#getOutputPorts <em>Output Ports</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Output Ports</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Entity#getOutputPorts()
   * @see #getEntity()
   * @generated
   */
  EReference getEntity_OutputPorts();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Entity#getParameters() <em>Get Parameters</em>}' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the '<em>Get Parameters</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Entity#getParameters()
   * @generated
   */
  EOperation getEntity__GetParameters();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.CompositeEntity <em>Composite Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Composite Entity</em>'.
   * @see org.eclipse.triquetrum.workflow.model.CompositeEntity
   * @generated
   */
  EClass getCompositeEntity();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.workflow.model.CompositeEntity#getEntities <em>Entities</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entities</em>'.
   * @see org.eclipse.triquetrum.workflow.model.CompositeEntity#getEntities()
   * @see #getCompositeEntity()
   * @generated
   */
  EReference getCompositeEntity_Entities();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.workflow.model.CompositeEntity#getRelations <em>Relations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Relations</em>'.
   * @see org.eclipse.triquetrum.workflow.model.CompositeEntity#getRelations()
   * @see #getCompositeEntity()
   * @generated
   */
  EReference getCompositeEntity_Relations();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Actor <em>Actor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Actor</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Actor
   * @generated
   */
  EClass getActor();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.CompositeActor <em>Composite Actor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Composite Actor</em>'.
   * @see org.eclipse.triquetrum.workflow.model.CompositeActor
   * @generated
   */
  EClass getCompositeActor();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.triquetrum.workflow.model.CompositeActor#getDirector <em>Director</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Director</em>'.
   * @see org.eclipse.triquetrum.workflow.model.CompositeActor#getDirector()
   * @see #getCompositeActor()
   * @generated
   */
  EReference getCompositeActor_Director();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Port <em>Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Port</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Port
   * @generated
   */
  EClass getPort();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Port#isInput <em>Input</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Input</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Port#isInput()
   * @see #getPort()
   * @generated
   */
  EAttribute getPort_Input();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Port#isOutput <em>Output</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Output</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Port#isOutput()
   * @see #getPort()
   * @generated
   */
  EAttribute getPort_Output();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.triquetrum.workflow.model.Port#getLinkedRelations <em>Linked Relations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Linked Relations</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Port#getLinkedRelations()
   * @see #getPort()
   * @generated
   */
  EReference getPort_LinkedRelations();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Port#isMultiPort <em>Multi Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Multi Port</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Port#isMultiPort()
   * @see #getPort()
   * @generated
   */
  EAttribute getPort_MultiPort();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Port#canAcceptNewConnection() <em>Can Accept New Connection</em>}' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the '<em>Can Accept New Connection</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Port#canAcceptNewConnection()
   * @generated
   */
  EOperation getPort__CanAcceptNewConnection();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Relation <em>Relation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Relation</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Relation
   * @generated
   */
  EClass getRelation();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.triquetrum.workflow.model.Relation#getLinkedPorts <em>Linked Ports</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Linked Ports</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Relation#getLinkedPorts()
   * @see #getRelation()
   * @generated
   */
  EReference getRelation_LinkedPorts();

  /**
   * Returns the meta object for data type '{@link ptolemy.kernel.util.NamedObj <em>Ptolemy Named Obj</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Ptolemy Named Obj</em>'.
   * @see ptolemy.kernel.util.NamedObj
   * @model instanceClass="ptolemy.kernel.util.NamedObj"
   * @generated
   */
  EDataType getPtolemyNamedObj();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  TriqFactory getTriqFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each operation of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals {
    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl <em>Named Obj</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getNamedObj()
     * @generated
     */
    EClass NAMED_OBJ = eINSTANCE.getNamedObj();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMED_OBJ__NAME = eINSTANCE.getNamedObj_Name();

    /**
     * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_OBJ__ATTRIBUTES = eINSTANCE.getNamedObj_Attributes();

    /**
     * The meta object literal for the '<em><b>Wrapped Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMED_OBJ__WRAPPED_TYPE = eINSTANCE.getNamedObj_WrappedType();

    /**
     * The meta object literal for the '<em><b>Wrapped Object</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMED_OBJ__WRAPPED_OBJECT = eINSTANCE.getNamedObj_WrappedObject();

    /**
     * The meta object literal for the '<em><b>Deep Complete</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMED_OBJ__DEEP_COMPLETE = eINSTANCE.getNamedObj_DeepComplete();

    /**
     * The meta object literal for the '<em><b>Icon Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMED_OBJ__ICON_ID = eINSTANCE.getNamedObj_IconId();

    /**
     * The meta object literal for the '<em><b>Get Container</b></em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EOperation NAMED_OBJ___GET_CONTAINER = eINSTANCE.getNamedObj__GetContainer();

    /**
     * The meta object literal for the '<em><b>Top Level</b></em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EOperation NAMED_OBJ___TOP_LEVEL = eINSTANCE.getNamedObj__TopLevel();

    /**
     * The meta object literal for the '<em><b>Set Property</b></em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EOperation NAMED_OBJ___SET_PROPERTY__STRING_STRING_STRING = eINSTANCE.getNamedObj__SetProperty__String_String_String();

    /**
     * The meta object literal for the '<em><b>Build Wrapped Object</b></em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EOperation NAMED_OBJ___BUILD_WRAPPED_OBJECT = eINSTANCE.getNamedObj__BuildWrappedObject();

    /**
     * The meta object literal for the '<em><b>Initialize From</b></em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EOperation NAMED_OBJ___INITIALIZE_FROM__NAMEDOBJ = eINSTANCE.getNamedObj__InitializeFrom__NamedObj();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.AttributeImpl <em>Attribute</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.workflow.model.impl.AttributeImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getAttribute()
     * @generated
     */
    EClass ATTRIBUTE = eINSTANCE.getAttribute();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.AnnotationImpl <em>Annotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.workflow.model.impl.AnnotationImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getAnnotation()
     * @generated
     */
    EClass ANNOTATION = eINSTANCE.getAnnotation();

    /**
     * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANNOTATION__TEXT = eINSTANCE.getAnnotation_Text();

    /**
     * The meta object literal for the '<em><b>Font Family</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANNOTATION__FONT_FAMILY = eINSTANCE.getAnnotation_FontFamily();

    /**
     * The meta object literal for the '<em><b>Text Size</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANNOTATION__TEXT_SIZE = eINSTANCE.getAnnotation_TextSize();

    /**
     * The meta object literal for the '<em><b>Bold</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANNOTATION__BOLD = eINSTANCE.getAnnotation_Bold();

    /**
     * The meta object literal for the '<em><b>Italic</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANNOTATION__ITALIC = eINSTANCE.getAnnotation_Italic();

    /**
     * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANNOTATION__COLOR = eINSTANCE.getAnnotation_Color();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.ParameterImpl <em>Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.workflow.model.impl.ParameterImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getParameter()
     * @generated
     */
    EClass PARAMETER = eINSTANCE.getParameter();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAMETER__EXPRESSION = eINSTANCE.getParameter_Expression();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.DirectorImpl <em>Director</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.workflow.model.impl.DirectorImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getDirector()
     * @generated
     */
    EClass DIRECTOR = eINSTANCE.getDirector();

    /**
     * The meta object literal for the '<em><b>Get Parameters</b></em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EOperation DIRECTOR___GET_PARAMETERS = eINSTANCE.getDirector__GetParameters();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.EntityImpl <em>Entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.workflow.model.impl.EntityImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getEntity()
     * @generated
     */
    EClass ENTITY = eINSTANCE.getEntity();

    /**
     * The meta object literal for the '<em><b>Input Ports</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY__INPUT_PORTS = eINSTANCE.getEntity_InputPorts();

    /**
     * The meta object literal for the '<em><b>Output Ports</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY__OUTPUT_PORTS = eINSTANCE.getEntity_OutputPorts();

    /**
     * The meta object literal for the '<em><b>Get Parameters</b></em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EOperation ENTITY___GET_PARAMETERS = eINSTANCE.getEntity__GetParameters();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.CompositeEntityImpl <em>Composite Entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.workflow.model.impl.CompositeEntityImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getCompositeEntity()
     * @generated
     */
    EClass COMPOSITE_ENTITY = eINSTANCE.getCompositeEntity();

    /**
     * The meta object literal for the '<em><b>Entities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITE_ENTITY__ENTITIES = eINSTANCE.getCompositeEntity_Entities();

    /**
     * The meta object literal for the '<em><b>Relations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITE_ENTITY__RELATIONS = eINSTANCE.getCompositeEntity_Relations();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.ActorImpl <em>Actor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.workflow.model.impl.ActorImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getActor()
     * @generated
     */
    EClass ACTOR = eINSTANCE.getActor();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.CompositeActorImpl <em>Composite Actor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.workflow.model.impl.CompositeActorImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getCompositeActor()
     * @generated
     */
    EClass COMPOSITE_ACTOR = eINSTANCE.getCompositeActor();

    /**
     * The meta object literal for the '<em><b>Director</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITE_ACTOR__DIRECTOR = eINSTANCE.getCompositeActor_Director();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl <em>Port</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.workflow.model.impl.PortImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getPort()
     * @generated
     */
    EClass PORT = eINSTANCE.getPort();

    /**
     * The meta object literal for the '<em><b>Input</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PORT__INPUT = eINSTANCE.getPort_Input();

    /**
     * The meta object literal for the '<em><b>Output</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PORT__OUTPUT = eINSTANCE.getPort_Output();

    /**
     * The meta object literal for the '<em><b>Linked Relations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PORT__LINKED_RELATIONS = eINSTANCE.getPort_LinkedRelations();

    /**
     * The meta object literal for the '<em><b>Multi Port</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PORT__MULTI_PORT = eINSTANCE.getPort_MultiPort();

    /**
     * The meta object literal for the '<em><b>Can Accept New Connection</b></em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EOperation PORT___CAN_ACCEPT_NEW_CONNECTION = eINSTANCE.getPort__CanAcceptNewConnection();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.RelationImpl <em>Relation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.workflow.model.impl.RelationImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getRelation()
     * @generated
     */
    EClass RELATION = eINSTANCE.getRelation();

    /**
     * The meta object literal for the '<em><b>Linked Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RELATION__LINKED_PORTS = eINSTANCE.getRelation_LinkedPorts();

    /**
     * The meta object literal for the '<em>Ptolemy Named Obj</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ptolemy.kernel.util.NamedObj
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getPtolemyNamedObj()
     * @generated
     */
    EDataType PTOLEMY_NAMED_OBJ = eINSTANCE.getPtolemyNamedObj();

  }

} //TriqPackage
