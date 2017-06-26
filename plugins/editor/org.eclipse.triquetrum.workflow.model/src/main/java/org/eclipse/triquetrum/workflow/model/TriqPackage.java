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
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.triquetrum.workflow.model.TriqFactory
 * @model kind="package"
 * @generated
 */
public interface TriqPackage extends EPackage {
  /**
   * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNAME = "model";

  /**
   * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/triquetrum";

  /**
   * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNS_PREFIX = "org.eclipse.triquetrum.workflow";

  /**
   * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  TriqPackage eINSTANCE = org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl <em>Named Obj</em>}' class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getNamedObj()
   * @generated
   */
  int NAMED_OBJ = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ__NAME = 0;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ__ATTRIBUTES = 1;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ__WRAPPED_TYPE = 2;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ__WRAPPED_OBJECT = 3;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ__DEEP_COMPLETE = 4;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ__ICON_ID = 5;

  /**
   * The number of structural features of the '<em>Named Obj</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ_FEATURE_COUNT = 6;

  /**
   * The operation id for the '<em>Get Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ___GET_CONTAINER = 0;

  /**
   * The operation id for the '<em>Get Lowest Common Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = 1;

  /**
   * The operation id for the '<em>Top Level</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ___TOP_LEVEL = 2;

  /**
   * The operation id for the '<em>Set Property</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ___SET_PROPERTY__STRING_STRING_STRING = 3;

  /**
   * The operation id for the '<em>Apply Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ___APPLY_WRAPPED_OBJECT = 4;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ___BUILD_WRAPPED_OBJECT = 5;

  /**
   * The operation id for the '<em>Get Full Name</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ___GET_FULL_NAME = 6;

  /**
   * The operation id for the '<em>Get Child</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ___GET_CHILD__STRING = 7;

  /**
   * The operation id for the '<em>Welcome</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ___WELCOME__VISITOR_BOOLEAN = 8;

  /**
   * The number of operations of the '<em>Named Obj</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NAMED_OBJ_OPERATION_COUNT = 9;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.AttributeImpl <em>Attribute</em>}' class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see org.eclipse.triquetrum.workflow.model.impl.AttributeImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getAttribute()
   * @generated
   */
  int ATTRIBUTE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE__NAME = NAMED_OBJ__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE__ATTRIBUTES = NAMED_OBJ__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE__WRAPPED_TYPE = NAMED_OBJ__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE__WRAPPED_OBJECT = NAMED_OBJ__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE__DEEP_COMPLETE = NAMED_OBJ__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE__ICON_ID = NAMED_OBJ__ICON_ID;

  /**
   * The number of structural features of the '<em>Attribute</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE_FEATURE_COUNT = NAMED_OBJ_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE___GET_CONTAINER = NAMED_OBJ___GET_CONTAINER;

  /**
   * The operation id for the '<em>Get Lowest Common Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = NAMED_OBJ___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ;

  /**
   * The operation id for the '<em>Top Level</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE___TOP_LEVEL = NAMED_OBJ___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE___SET_PROPERTY__STRING_STRING_STRING = NAMED_OBJ___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Apply Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE___APPLY_WRAPPED_OBJECT = NAMED_OBJ___APPLY_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE___BUILD_WRAPPED_OBJECT = NAMED_OBJ___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Get Full Name</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE___GET_FULL_NAME = NAMED_OBJ___GET_FULL_NAME;

  /**
   * The operation id for the '<em>Get Child</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE___GET_CHILD__STRING = NAMED_OBJ___GET_CHILD__STRING;

  /**
   * The operation id for the '<em>Welcome</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE___WELCOME__VISITOR_BOOLEAN = NAMED_OBJ___WELCOME__VISITOR_BOOLEAN;

  /**
   * The number of operations of the '<em>Attribute</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ATTRIBUTE_OPERATION_COUNT = NAMED_OBJ_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.AnnotationImpl <em>Annotation</em>}' class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see org.eclipse.triquetrum.workflow.model.impl.AnnotationImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getAnnotation()
   * @generated
   */
  int ANNOTATION = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION__NAME = ATTRIBUTE__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION__ATTRIBUTES = ATTRIBUTE__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION__WRAPPED_TYPE = ATTRIBUTE__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION__WRAPPED_OBJECT = ATTRIBUTE__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION__DEEP_COMPLETE = ATTRIBUTE__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION__ICON_ID = ATTRIBUTE__ICON_ID;

  /**
   * The feature id for the '<em><b>Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION__TEXT = ATTRIBUTE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Font Family</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION__FONT_FAMILY = ATTRIBUTE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Text Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION__TEXT_SIZE = ATTRIBUTE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Bold</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION__BOLD = ATTRIBUTE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Italic</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION__ITALIC = ATTRIBUTE_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION__COLOR = ATTRIBUTE_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>Annotation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 6;

  /**
   * The operation id for the '<em>Get Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION___GET_CONTAINER = ATTRIBUTE___GET_CONTAINER;

  /**
   * The operation id for the '<em>Get Lowest Common Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = ATTRIBUTE___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ;

  /**
   * The operation id for the '<em>Top Level</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION___TOP_LEVEL = ATTRIBUTE___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION___SET_PROPERTY__STRING_STRING_STRING = ATTRIBUTE___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Apply Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION___APPLY_WRAPPED_OBJECT = ATTRIBUTE___APPLY_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION___BUILD_WRAPPED_OBJECT = ATTRIBUTE___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Get Full Name</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION___GET_FULL_NAME = ATTRIBUTE___GET_FULL_NAME;

  /**
   * The operation id for the '<em>Get Child</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION___GET_CHILD__STRING = ATTRIBUTE___GET_CHILD__STRING;

  /**
   * The operation id for the '<em>Welcome</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION___WELCOME__VISITOR_BOOLEAN = ATTRIBUTE___WELCOME__VISITOR_BOOLEAN;

  /**
   * The number of operations of the '<em>Annotation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ANNOTATION_OPERATION_COUNT = ATTRIBUTE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.ParameterImpl <em>Parameter</em>}' class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see org.eclipse.triquetrum.workflow.model.impl.ParameterImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getParameter()
   * @generated
   */
  int PARAMETER = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER__NAME = ATTRIBUTE__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER__ATTRIBUTES = ATTRIBUTE__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER__WRAPPED_TYPE = ATTRIBUTE__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER__WRAPPED_OBJECT = ATTRIBUTE__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER__DEEP_COMPLETE = ATTRIBUTE__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER__ICON_ID = ATTRIBUTE__ICON_ID;

  /**
   * The feature id for the '<em><b>Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER__EXPRESSION = ATTRIBUTE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Parameter</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 1;

  /**
   * The operation id for the '<em>Get Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER___GET_CONTAINER = ATTRIBUTE___GET_CONTAINER;

  /**
   * The operation id for the '<em>Get Lowest Common Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = ATTRIBUTE___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ;

  /**
   * The operation id for the '<em>Top Level</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER___TOP_LEVEL = ATTRIBUTE___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER___SET_PROPERTY__STRING_STRING_STRING = ATTRIBUTE___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Apply Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER___APPLY_WRAPPED_OBJECT = ATTRIBUTE___APPLY_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER___BUILD_WRAPPED_OBJECT = ATTRIBUTE___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Get Full Name</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER___GET_FULL_NAME = ATTRIBUTE___GET_FULL_NAME;

  /**
   * The operation id for the '<em>Get Child</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER___GET_CHILD__STRING = ATTRIBUTE___GET_CHILD__STRING;

  /**
   * The operation id for the '<em>Welcome</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER___WELCOME__VISITOR_BOOLEAN = ATTRIBUTE___WELCOME__VISITOR_BOOLEAN;

  /**
   * The number of operations of the '<em>Parameter</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PARAMETER_OPERATION_COUNT = ATTRIBUTE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.DirectorImpl <em>Director</em>}' class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see org.eclipse.triquetrum.workflow.model.impl.DirectorImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getDirector()
   * @generated
   */
  int DIRECTOR = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR__NAME = ATTRIBUTE__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR__ATTRIBUTES = ATTRIBUTE__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR__WRAPPED_TYPE = ATTRIBUTE__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR__WRAPPED_OBJECT = ATTRIBUTE__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR__DEEP_COMPLETE = ATTRIBUTE__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR__ICON_ID = ATTRIBUTE__ICON_ID;

  /**
   * The number of structural features of the '<em>Director</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR___GET_CONTAINER = ATTRIBUTE___GET_CONTAINER;

  /**
   * The operation id for the '<em>Get Lowest Common Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = ATTRIBUTE___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ;

  /**
   * The operation id for the '<em>Top Level</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR___TOP_LEVEL = ATTRIBUTE___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR___SET_PROPERTY__STRING_STRING_STRING = ATTRIBUTE___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Apply Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR___APPLY_WRAPPED_OBJECT = ATTRIBUTE___APPLY_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR___BUILD_WRAPPED_OBJECT = ATTRIBUTE___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Get Full Name</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR___GET_FULL_NAME = ATTRIBUTE___GET_FULL_NAME;

  /**
   * The operation id for the '<em>Get Child</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR___GET_CHILD__STRING = ATTRIBUTE___GET_CHILD__STRING;

  /**
   * The operation id for the '<em>Welcome</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR___WELCOME__VISITOR_BOOLEAN = ATTRIBUTE___WELCOME__VISITOR_BOOLEAN;

  /**
   * The operation id for the '<em>Get Parameters</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR___GET_PARAMETERS = ATTRIBUTE_OPERATION_COUNT + 0;

  /**
   * The number of operations of the '<em>Director</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DIRECTOR_OPERATION_COUNT = ATTRIBUTE_OPERATION_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.EntityImpl <em>Entity</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   *
   * @see org.eclipse.triquetrum.workflow.model.impl.EntityImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getEntity()
   * @generated
   */
  int ENTITY = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY__NAME = NAMED_OBJ__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY__ATTRIBUTES = NAMED_OBJ__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY__WRAPPED_TYPE = NAMED_OBJ__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY__WRAPPED_OBJECT = NAMED_OBJ__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY__DEEP_COMPLETE = NAMED_OBJ__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY__ICON_ID = NAMED_OBJ__ICON_ID;

  /**
   * The feature id for the '<em><b>Ports</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY__PORTS = NAMED_OBJ_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Entity</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY_FEATURE_COUNT = NAMED_OBJ_FEATURE_COUNT + 1;

  /**
   * The operation id for the '<em>Get Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY___GET_CONTAINER = NAMED_OBJ___GET_CONTAINER;

  /**
   * The operation id for the '<em>Get Lowest Common Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = NAMED_OBJ___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ;

  /**
   * The operation id for the '<em>Top Level</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY___TOP_LEVEL = NAMED_OBJ___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY___SET_PROPERTY__STRING_STRING_STRING = NAMED_OBJ___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Apply Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY___APPLY_WRAPPED_OBJECT = NAMED_OBJ___APPLY_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY___BUILD_WRAPPED_OBJECT = NAMED_OBJ___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Get Full Name</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY___GET_FULL_NAME = NAMED_OBJ___GET_FULL_NAME;

  /**
   * The operation id for the '<em>Get Child</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY___GET_CHILD__STRING = NAMED_OBJ___GET_CHILD__STRING;

  /**
   * The operation id for the '<em>Welcome</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY___WELCOME__VISITOR_BOOLEAN = NAMED_OBJ___WELCOME__VISITOR_BOOLEAN;

  /**
   * The operation id for the '<em>Get Parameters</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY___GET_PARAMETERS = NAMED_OBJ_OPERATION_COUNT + 0;

  /**
   * The operation id for the '<em>Get Input Ports</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY___GET_INPUT_PORTS = NAMED_OBJ_OPERATION_COUNT + 1;

  /**
   * The operation id for the '<em>Get Output Ports</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY___GET_OUTPUT_PORTS = NAMED_OBJ_OPERATION_COUNT + 2;

  /**
   * The number of operations of the '<em>Entity</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ENTITY_OPERATION_COUNT = NAMED_OBJ_OPERATION_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.CompositeEntityImpl <em>Composite Entity</em>}' class. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @see org.eclipse.triquetrum.workflow.model.impl.CompositeEntityImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getCompositeEntity()
   * @generated
   */
  int COMPOSITE_ENTITY = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__NAME = ENTITY__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__ATTRIBUTES = ENTITY__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__WRAPPED_TYPE = ENTITY__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__WRAPPED_OBJECT = ENTITY__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__DEEP_COMPLETE = ENTITY__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__ICON_ID = ENTITY__ICON_ID;

  /**
   * The feature id for the '<em><b>Ports</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__PORTS = ENTITY__PORTS;

  /**
   * The feature id for the '<em><b>Entities</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__ENTITIES = ENTITY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Relations</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY__RELATIONS = ENTITY_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Composite Entity</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 2;

  /**
   * The operation id for the '<em>Get Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___GET_CONTAINER = ENTITY___GET_CONTAINER;

  /**
   * The operation id for the '<em>Get Lowest Common Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = ENTITY___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ;

  /**
   * The operation id for the '<em>Top Level</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___TOP_LEVEL = ENTITY___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___SET_PROPERTY__STRING_STRING_STRING = ENTITY___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Apply Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___APPLY_WRAPPED_OBJECT = ENTITY___APPLY_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___BUILD_WRAPPED_OBJECT = ENTITY___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Get Full Name</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___GET_FULL_NAME = ENTITY___GET_FULL_NAME;

  /**
   * The operation id for the '<em>Get Child</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___GET_CHILD__STRING = ENTITY___GET_CHILD__STRING;

  /**
   * The operation id for the '<em>Welcome</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___WELCOME__VISITOR_BOOLEAN = ENTITY___WELCOME__VISITOR_BOOLEAN;

  /**
   * The operation id for the '<em>Get Parameters</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___GET_PARAMETERS = ENTITY___GET_PARAMETERS;

  /**
   * The operation id for the '<em>Get Input Ports</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___GET_INPUT_PORTS = ENTITY___GET_INPUT_PORTS;

  /**
   * The operation id for the '<em>Get Output Ports</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY___GET_OUTPUT_PORTS = ENTITY___GET_OUTPUT_PORTS;

  /**
   * The number of operations of the '<em>Composite Entity</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ENTITY_OPERATION_COUNT = ENTITY_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.ActorImpl <em>Actor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   *
   * @see org.eclipse.triquetrum.workflow.model.impl.ActorImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getActor()
   * @generated
   */
  int ACTOR = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR__NAME = ENTITY__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR__ATTRIBUTES = ENTITY__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR__WRAPPED_TYPE = ENTITY__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR__WRAPPED_OBJECT = ENTITY__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR__DEEP_COMPLETE = ENTITY__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR__ICON_ID = ENTITY__ICON_ID;

  /**
   * The feature id for the '<em><b>Ports</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR__PORTS = ENTITY__PORTS;

  /**
   * The number of structural features of the '<em>Actor</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR___GET_CONTAINER = ENTITY___GET_CONTAINER;

  /**
   * The operation id for the '<em>Get Lowest Common Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = ENTITY___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ;

  /**
   * The operation id for the '<em>Top Level</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR___TOP_LEVEL = ENTITY___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR___SET_PROPERTY__STRING_STRING_STRING = ENTITY___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Apply Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR___APPLY_WRAPPED_OBJECT = ENTITY___APPLY_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR___BUILD_WRAPPED_OBJECT = ENTITY___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Get Full Name</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR___GET_FULL_NAME = ENTITY___GET_FULL_NAME;

  /**
   * The operation id for the '<em>Get Child</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR___GET_CHILD__STRING = ENTITY___GET_CHILD__STRING;

  /**
   * The operation id for the '<em>Welcome</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR___WELCOME__VISITOR_BOOLEAN = ENTITY___WELCOME__VISITOR_BOOLEAN;

  /**
   * The operation id for the '<em>Get Parameters</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR___GET_PARAMETERS = ENTITY___GET_PARAMETERS;

  /**
   * The operation id for the '<em>Get Input Ports</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR___GET_INPUT_PORTS = ENTITY___GET_INPUT_PORTS;

  /**
   * The operation id for the '<em>Get Output Ports</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR___GET_OUTPUT_PORTS = ENTITY___GET_OUTPUT_PORTS;

  /**
   * The number of operations of the '<em>Actor</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ACTOR_OPERATION_COUNT = ENTITY_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.CompositeActorImpl <em>Composite Actor</em>}' class. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @see org.eclipse.triquetrum.workflow.model.impl.CompositeActorImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getCompositeActor()
   * @generated
   */
  int COMPOSITE_ACTOR = 8;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__NAME = COMPOSITE_ENTITY__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__ATTRIBUTES = COMPOSITE_ENTITY__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__WRAPPED_TYPE = COMPOSITE_ENTITY__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__WRAPPED_OBJECT = COMPOSITE_ENTITY__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__DEEP_COMPLETE = COMPOSITE_ENTITY__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__ICON_ID = COMPOSITE_ENTITY__ICON_ID;

  /**
   * The feature id for the '<em><b>Ports</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__PORTS = COMPOSITE_ENTITY__PORTS;

  /**
   * The feature id for the '<em><b>Entities</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__ENTITIES = COMPOSITE_ENTITY__ENTITIES;

  /**
   * The feature id for the '<em><b>Relations</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__RELATIONS = COMPOSITE_ENTITY__RELATIONS;

  /**
   * The feature id for the '<em><b>Director</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR__DIRECTOR = COMPOSITE_ENTITY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Composite Actor</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR_FEATURE_COUNT = COMPOSITE_ENTITY_FEATURE_COUNT + 1;

  /**
   * The operation id for the '<em>Get Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___GET_CONTAINER = COMPOSITE_ENTITY___GET_CONTAINER;

  /**
   * The operation id for the '<em>Get Lowest Common Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = COMPOSITE_ENTITY___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ;

  /**
   * The operation id for the '<em>Top Level</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___TOP_LEVEL = COMPOSITE_ENTITY___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___SET_PROPERTY__STRING_STRING_STRING = COMPOSITE_ENTITY___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Apply Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___APPLY_WRAPPED_OBJECT = COMPOSITE_ENTITY___APPLY_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___BUILD_WRAPPED_OBJECT = COMPOSITE_ENTITY___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Get Full Name</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___GET_FULL_NAME = COMPOSITE_ENTITY___GET_FULL_NAME;

  /**
   * The operation id for the '<em>Get Child</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___GET_CHILD__STRING = COMPOSITE_ENTITY___GET_CHILD__STRING;

  /**
   * The operation id for the '<em>Welcome</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___WELCOME__VISITOR_BOOLEAN = COMPOSITE_ENTITY___WELCOME__VISITOR_BOOLEAN;

  /**
   * The operation id for the '<em>Get Parameters</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___GET_PARAMETERS = COMPOSITE_ENTITY___GET_PARAMETERS;

  /**
   * The operation id for the '<em>Get Input Ports</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___GET_INPUT_PORTS = COMPOSITE_ENTITY___GET_INPUT_PORTS;

  /**
   * The operation id for the '<em>Get Output Ports</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR___GET_OUTPUT_PORTS = COMPOSITE_ENTITY___GET_OUTPUT_PORTS;

  /**
   * The number of operations of the '<em>Composite Actor</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPOSITE_ACTOR_OPERATION_COUNT = COMPOSITE_ENTITY_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl <em>Port</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.eclipse.triquetrum.workflow.model.impl.PortImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getPort()
   * @generated
   */
  int PORT = 9;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.RelationImpl <em>Relation</em>}' class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see org.eclipse.triquetrum.workflow.model.impl.RelationImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getRelation()
   * @generated
   */
  int RELATION = 10;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.LocationImpl <em>Location</em>}' class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see org.eclipse.triquetrum.workflow.model.impl.LocationImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getLocation()
   * @generated
   */
  int LOCATION = 11;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.impl.VertexImpl <em>Vertex</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   *
   * @see org.eclipse.triquetrum.workflow.model.impl.VertexImpl
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getVertex()
   * @generated
   */
  int VERTEX = 12;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.Linkable <em>Linkable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.eclipse.triquetrum.workflow.model.Linkable
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getLinkable()
   * @generated
   */
  int LINKABLE = 13;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE__NAME = NAMED_OBJ__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE__ATTRIBUTES = NAMED_OBJ__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE__WRAPPED_TYPE = NAMED_OBJ__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE__WRAPPED_OBJECT = NAMED_OBJ__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE__DEEP_COMPLETE = NAMED_OBJ__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE__ICON_ID = NAMED_OBJ__ICON_ID;

  /**
   * The number of structural features of the '<em>Linkable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE_FEATURE_COUNT = NAMED_OBJ_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE___GET_CONTAINER = NAMED_OBJ___GET_CONTAINER;

  /**
   * The operation id for the '<em>Get Lowest Common Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = NAMED_OBJ___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ;

  /**
   * The operation id for the '<em>Top Level</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE___TOP_LEVEL = NAMED_OBJ___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE___SET_PROPERTY__STRING_STRING_STRING = NAMED_OBJ___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Apply Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE___APPLY_WRAPPED_OBJECT = NAMED_OBJ___APPLY_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE___BUILD_WRAPPED_OBJECT = NAMED_OBJ___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Get Full Name</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE___GET_FULL_NAME = NAMED_OBJ___GET_FULL_NAME;

  /**
   * The operation id for the '<em>Get Child</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE___GET_CHILD__STRING = NAMED_OBJ___GET_CHILD__STRING;

  /**
   * The operation id for the '<em>Welcome</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE___WELCOME__VISITOR_BOOLEAN = NAMED_OBJ___WELCOME__VISITOR_BOOLEAN;

  /**
   * The operation id for the '<em>Link</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE___LINK__RELATION = NAMED_OBJ_OPERATION_COUNT + 0;

  /**
   * The operation id for the '<em>Unlink</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE___UNLINK__RELATION = NAMED_OBJ_OPERATION_COUNT + 1;

  /**
   * The operation id for the '<em>Is Potential Start</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE___IS_POTENTIAL_START = NAMED_OBJ_OPERATION_COUNT + 2;

  /**
   * The operation id for the '<em>Is Potential End</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE___IS_POTENTIAL_END__LINKABLE = NAMED_OBJ_OPERATION_COUNT + 3;

  /**
   * The operation id for the '<em>Build Wrapped Links</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE___BUILD_WRAPPED_LINKS = NAMED_OBJ_OPERATION_COUNT + 4;

  /**
   * The number of operations of the '<em>Linkable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LINKABLE_OPERATION_COUNT = NAMED_OBJ_OPERATION_COUNT + 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT__NAME = LINKABLE__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT__ATTRIBUTES = LINKABLE__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT__WRAPPED_TYPE = LINKABLE__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT__WRAPPED_OBJECT = LINKABLE__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT__DEEP_COMPLETE = LINKABLE__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT__ICON_ID = LINKABLE__ICON_ID;

  /**
   * The feature id for the '<em><b>Input</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT__INPUT = LINKABLE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Output</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT__OUTPUT = LINKABLE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Multi Port</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT__MULTI_PORT = LINKABLE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Direction</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT__DIRECTION = LINKABLE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Linked Relations</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT__LINKED_RELATIONS = LINKABLE_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Inside Linked Relations</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT__INSIDE_LINKED_RELATIONS = LINKABLE_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Outside Linked Relations</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT__OUTSIDE_LINKED_RELATIONS = LINKABLE_FEATURE_COUNT + 6;

  /**
   * The number of structural features of the '<em>Port</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT_FEATURE_COUNT = LINKABLE_FEATURE_COUNT + 7;

  /**
   * The operation id for the '<em>Get Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___GET_CONTAINER = LINKABLE___GET_CONTAINER;

  /**
   * The operation id for the '<em>Get Lowest Common Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = LINKABLE___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ;

  /**
   * The operation id for the '<em>Top Level</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___TOP_LEVEL = LINKABLE___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___SET_PROPERTY__STRING_STRING_STRING = LINKABLE___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Apply Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___APPLY_WRAPPED_OBJECT = LINKABLE___APPLY_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___BUILD_WRAPPED_OBJECT = LINKABLE___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Get Full Name</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___GET_FULL_NAME = LINKABLE___GET_FULL_NAME;

  /**
   * The operation id for the '<em>Get Child</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___GET_CHILD__STRING = LINKABLE___GET_CHILD__STRING;

  /**
   * The operation id for the '<em>Welcome</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___WELCOME__VISITOR_BOOLEAN = LINKABLE___WELCOME__VISITOR_BOOLEAN;

  /**
   * The operation id for the '<em>Link</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___LINK__RELATION = LINKABLE___LINK__RELATION;

  /**
   * The operation id for the '<em>Unlink</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___UNLINK__RELATION = LINKABLE___UNLINK__RELATION;

  /**
   * The operation id for the '<em>Is Potential Start</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___IS_POTENTIAL_START = LINKABLE___IS_POTENTIAL_START;

  /**
   * The operation id for the '<em>Is Potential End</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___IS_POTENTIAL_END__LINKABLE = LINKABLE___IS_POTENTIAL_END__LINKABLE;

  /**
   * The operation id for the '<em>Build Wrapped Links</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___BUILD_WRAPPED_LINKS = LINKABLE___BUILD_WRAPPED_LINKS;

  /**
   * The operation id for the '<em>Can Accept New Outside Relation</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___CAN_ACCEPT_NEW_OUTSIDE_RELATION = LINKABLE_OPERATION_COUNT + 0;

  /**
   * The operation id for the '<em>Can Accept New Inside Relation</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT___CAN_ACCEPT_NEW_INSIDE_RELATION = LINKABLE_OPERATION_COUNT + 1;

  /**
   * The number of operations of the '<em>Port</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PORT_OPERATION_COUNT = LINKABLE_OPERATION_COUNT + 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION__NAME = LINKABLE__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION__ATTRIBUTES = LINKABLE__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION__WRAPPED_TYPE = LINKABLE__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION__WRAPPED_OBJECT = LINKABLE__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION__DEEP_COMPLETE = LINKABLE__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION__ICON_ID = LINKABLE__ICON_ID;

  /**
   * The feature id for the '<em><b>Linked Relations</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION__LINKED_RELATIONS = LINKABLE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Linking Relations</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION__LINKING_RELATIONS = LINKABLE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Linked Ports</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION__LINKED_PORTS = LINKABLE_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Relation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION_FEATURE_COUNT = LINKABLE_FEATURE_COUNT + 3;

  /**
   * The operation id for the '<em>Get Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___GET_CONTAINER = LINKABLE___GET_CONTAINER;

  /**
   * The operation id for the '<em>Get Lowest Common Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = LINKABLE___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ;

  /**
   * The operation id for the '<em>Top Level</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___TOP_LEVEL = LINKABLE___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___SET_PROPERTY__STRING_STRING_STRING = LINKABLE___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Apply Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___APPLY_WRAPPED_OBJECT = LINKABLE___APPLY_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___BUILD_WRAPPED_OBJECT = LINKABLE___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Get Full Name</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___GET_FULL_NAME = LINKABLE___GET_FULL_NAME;

  /**
   * The operation id for the '<em>Get Child</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___GET_CHILD__STRING = LINKABLE___GET_CHILD__STRING;

  /**
   * The operation id for the '<em>Welcome</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___WELCOME__VISITOR_BOOLEAN = LINKABLE___WELCOME__VISITOR_BOOLEAN;

  /**
   * The operation id for the '<em>Link</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___LINK__RELATION = LINKABLE___LINK__RELATION;

  /**
   * The operation id for the '<em>Unlink</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___UNLINK__RELATION = LINKABLE___UNLINK__RELATION;

  /**
   * The operation id for the '<em>Is Potential Start</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___IS_POTENTIAL_START = LINKABLE___IS_POTENTIAL_START;

  /**
   * The operation id for the '<em>Is Potential End</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___IS_POTENTIAL_END__LINKABLE = LINKABLE___IS_POTENTIAL_END__LINKABLE;

  /**
   * The operation id for the '<em>Build Wrapped Links</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___BUILD_WRAPPED_LINKS = LINKABLE___BUILD_WRAPPED_LINKS;

  /**
   * The operation id for the '<em>Is Connected</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___IS_CONNECTED = LINKABLE_OPERATION_COUNT + 0;

  /**
   * The operation id for the '<em>Get Vertex</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION___GET_VERTEX = LINKABLE_OPERATION_COUNT + 1;

  /**
   * The number of operations of the '<em>Relation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RELATION_OPERATION_COUNT = LINKABLE_OPERATION_COUNT + 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION__NAME = ATTRIBUTE__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION__ATTRIBUTES = ATTRIBUTE__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION__WRAPPED_TYPE = ATTRIBUTE__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION__WRAPPED_OBJECT = ATTRIBUTE__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION__DEEP_COMPLETE = ATTRIBUTE__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION__ICON_ID = ATTRIBUTE__ICON_ID;

  /**
   * The feature id for the '<em><b>Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION__EXPRESSION = ATTRIBUTE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Location</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 1;

  /**
   * The operation id for the '<em>Get Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION___GET_CONTAINER = ATTRIBUTE___GET_CONTAINER;

  /**
   * The operation id for the '<em>Get Lowest Common Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = ATTRIBUTE___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ;

  /**
   * The operation id for the '<em>Top Level</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION___TOP_LEVEL = ATTRIBUTE___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION___SET_PROPERTY__STRING_STRING_STRING = ATTRIBUTE___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Apply Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION___APPLY_WRAPPED_OBJECT = ATTRIBUTE___APPLY_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION___BUILD_WRAPPED_OBJECT = ATTRIBUTE___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Get Full Name</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION___GET_FULL_NAME = ATTRIBUTE___GET_FULL_NAME;

  /**
   * The operation id for the '<em>Get Child</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION___GET_CHILD__STRING = ATTRIBUTE___GET_CHILD__STRING;

  /**
   * The operation id for the '<em>Welcome</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION___WELCOME__VISITOR_BOOLEAN = ATTRIBUTE___WELCOME__VISITOR_BOOLEAN;

  /**
   * The operation id for the '<em>Get Location</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION___GET_LOCATION = ATTRIBUTE_OPERATION_COUNT + 0;

  /**
   * The number of operations of the '<em>Location</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOCATION_OPERATION_COUNT = ATTRIBUTE_OPERATION_COUNT + 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX__NAME = LOCATION__NAME;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX__ATTRIBUTES = LOCATION__ATTRIBUTES;

  /**
   * The feature id for the '<em><b>Wrapped Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX__WRAPPED_TYPE = LOCATION__WRAPPED_TYPE;

  /**
   * The feature id for the '<em><b>Wrapped Object</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX__WRAPPED_OBJECT = LOCATION__WRAPPED_OBJECT;

  /**
   * The feature id for the '<em><b>Deep Complete</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX__DEEP_COMPLETE = LOCATION__DEEP_COMPLETE;

  /**
   * The feature id for the '<em><b>Icon Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX__ICON_ID = LOCATION__ICON_ID;

  /**
   * The feature id for the '<em><b>Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX__EXPRESSION = LOCATION__EXPRESSION;

  /**
   * The number of structural features of the '<em>Vertex</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX_FEATURE_COUNT = LOCATION_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___GET_CONTAINER = LOCATION___GET_CONTAINER;

  /**
   * The operation id for the '<em>Get Lowest Common Container</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = LOCATION___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ;

  /**
   * The operation id for the '<em>Top Level</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___TOP_LEVEL = LOCATION___TOP_LEVEL;

  /**
   * The operation id for the '<em>Set Property</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___SET_PROPERTY__STRING_STRING_STRING = LOCATION___SET_PROPERTY__STRING_STRING_STRING;

  /**
   * The operation id for the '<em>Apply Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___APPLY_WRAPPED_OBJECT = LOCATION___APPLY_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Build Wrapped Object</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___BUILD_WRAPPED_OBJECT = LOCATION___BUILD_WRAPPED_OBJECT;

  /**
   * The operation id for the '<em>Get Full Name</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___GET_FULL_NAME = LOCATION___GET_FULL_NAME;

  /**
   * The operation id for the '<em>Get Child</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___GET_CHILD__STRING = LOCATION___GET_CHILD__STRING;

  /**
   * The operation id for the '<em>Welcome</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___WELCOME__VISITOR_BOOLEAN = LOCATION___WELCOME__VISITOR_BOOLEAN;

  /**
   * The operation id for the '<em>Get Location</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___GET_LOCATION = LOCATION___GET_LOCATION;

  /**
   * The operation id for the '<em>Link</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___LINK__RELATION = LOCATION_OPERATION_COUNT + 0;

  /**
   * The operation id for the '<em>Unlink</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___UNLINK__RELATION = LOCATION_OPERATION_COUNT + 1;

  /**
   * The operation id for the '<em>Is Potential Start</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___IS_POTENTIAL_START = LOCATION_OPERATION_COUNT + 2;

  /**
   * The operation id for the '<em>Is Potential End</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___IS_POTENTIAL_END__LINKABLE = LOCATION_OPERATION_COUNT + 3;

  /**
   * The operation id for the '<em>Build Wrapped Links</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX___BUILD_WRAPPED_LINKS = LOCATION_OPERATION_COUNT + 4;

  /**
   * The number of operations of the '<em>Vertex</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VERTEX_OPERATION_COUNT = LOCATION_OPERATION_COUNT + 5;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.workflow.model.Direction <em>Direction</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.eclipse.triquetrum.workflow.model.Direction
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getDirection()
   * @generated
   */
  int DIRECTION = 14;

  /**
   * The meta object id for the '<em>Visitor</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.eclipse.triquetrum.workflow.model.util.Visitor
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getVisitor()
   * @generated
   */
  int VISITOR = 16;

  /**
   * The meta object id for the '<em>Ptolemy Named Obj</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see ptolemy.kernel.util.NamedObj
   * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getPtolemyNamedObj()
   * @generated
   */
  int PTOLEMY_NAMED_OBJ = 15;

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.NamedObj <em>Named Obj</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   *
   * @return the meta object for class '<em>Named Obj</em>'.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj
   * @generated
   */
  EClass getNamedObj();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getName <em>Name</em>}'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getName()
   * @see #getNamedObj()
   * @generated
   */
  EAttribute getNamedObj_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getAttributes <em>Attributes</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference list '<em>Attributes</em>'.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getAttributes()
   * @see #getNamedObj()
   * @generated
   */
  EReference getNamedObj_Attributes();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getWrappedType <em>Wrapped Type</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Wrapped Type</em>'.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getWrappedType()
   * @see #getNamedObj()
   * @generated
   */
  EAttribute getNamedObj_WrappedType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getWrappedObject <em>Wrapped Object</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Wrapped Object</em>'.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getWrappedObject()
   * @see #getNamedObj()
   * @generated
   */
  EAttribute getNamedObj_WrappedObject();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.NamedObj#isDeepComplete <em>Deep Complete</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Deep Complete</em>'.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#isDeepComplete()
   * @see #getNamedObj()
   * @generated
   */
  EAttribute getNamedObj_DeepComplete();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getIconId <em>Icon Id</em>}'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Icon Id</em>'.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getIconId()
   * @see #getNamedObj()
   * @generated
   */
  EAttribute getNamedObj_IconId();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getContainer() <em>Get Container</em>}' operation. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the '<em>Get Container</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getContainer()
   * @generated
   */
  EOperation getNamedObj__GetContainer();

  /**
   * Returns the meta object for the '
   * {@link org.eclipse.triquetrum.workflow.model.NamedObj#getLowestCommonContainer(org.eclipse.triquetrum.workflow.model.NamedObj)
   * <em>Get Lowest Common Container</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Get Lowest Common Container</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getLowestCommonContainer(org.eclipse.triquetrum.workflow.model.NamedObj)
   * @generated
   */
  EOperation getNamedObj__GetLowestCommonContainer__NamedObj();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#topLevel() <em>Top Level</em>}' operation. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Top Level</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#topLevel()
   * @generated
   */
  EOperation getNamedObj__TopLevel();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#setProperty(java.lang.String, java.lang.String, java.lang.String)
   * <em>Set Property</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Set Property</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#setProperty(java.lang.String, java.lang.String, java.lang.String)
   * @generated
   */
  EOperation getNamedObj__SetProperty__String_String_String();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#applyWrappedObject() <em>Apply Wrapped Object</em>}' operation. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the '<em>Apply Wrapped Object</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#applyWrappedObject()
   * @generated
   */
  EOperation getNamedObj__ApplyWrappedObject();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#buildWrappedObject() <em>Build Wrapped Object</em>}' operation. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the '<em>Build Wrapped Object</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#buildWrappedObject()
   * @generated
   */
  EOperation getNamedObj__BuildWrappedObject();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getFullName() <em>Get Full Name</em>}' operation. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the '<em>Get Full Name</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getFullName()
   * @generated
   */
  EOperation getNamedObj__GetFullName();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#getChild(java.lang.String) <em>Get Child</em>}' operation. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the '<em>Get Child</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#getChild(java.lang.String)
   * @generated
   */
  EOperation getNamedObj__GetChild__String();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.NamedObj#welcome(org.eclipse.triquetrum.workflow.model.util.Visitor, boolean)
   * <em>Welcome</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Welcome</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.NamedObj#welcome(org.eclipse.triquetrum.workflow.model.util.Visitor, boolean)
   * @generated
   */
  EOperation getNamedObj__Welcome__Visitor_boolean();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Attribute <em>Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   *
   * @return the meta object for class '<em>Attribute</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Attribute
   * @generated
   */
  EClass getAttribute();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Annotation <em>Annotation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   *
   * @return the meta object for class '<em>Annotation</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Annotation
   * @generated
   */
  EClass getAnnotation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Annotation#getText <em>Text</em>}'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Text</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Annotation#getText()
   * @see #getAnnotation()
   * @generated
   */
  EAttribute getAnnotation_Text();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Annotation#getFontFamily <em>Font Family</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Font Family</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Annotation#getFontFamily()
   * @see #getAnnotation()
   * @generated
   */
  EAttribute getAnnotation_FontFamily();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Annotation#getTextSize <em>Text Size</em>}'. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Text Size</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Annotation#getTextSize()
   * @see #getAnnotation()
   * @generated
   */
  EAttribute getAnnotation_TextSize();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Annotation#isBold <em>Bold</em>}'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Bold</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Annotation#isBold()
   * @see #getAnnotation()
   * @generated
   */
  EAttribute getAnnotation_Bold();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Annotation#isItalic <em>Italic</em>}'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Italic</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Annotation#isItalic()
   * @see #getAnnotation()
   * @generated
   */
  EAttribute getAnnotation_Italic();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Annotation#getColor <em>Color</em>}'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Color</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Annotation#getColor()
   * @see #getAnnotation()
   * @generated
   */
  EAttribute getAnnotation_Color();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Parameter <em>Parameter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   *
   * @return the meta object for class '<em>Parameter</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Parameter
   * @generated
   */
  EClass getParameter();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Parameter#getExpression <em>Expression</em>}'. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Expression</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Parameter#getExpression()
   * @see #getParameter()
   * @generated
   */
  EAttribute getParameter_Expression();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Director <em>Director</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Director</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Director
   * @generated
   */
  EClass getDirector();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Director#getParameters() <em>Get Parameters</em>}' operation. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the '<em>Get Parameters</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Director#getParameters()
   * @generated
   */
  EOperation getDirector__GetParameters();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Entity <em>Entity</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Entity</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Entity
   * @generated
   */
  EClass getEntity();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.workflow.model.Entity#getPorts <em>Ports</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference list '<em>Ports</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Entity#getPorts()
   * @see #getEntity()
   * @generated
   */
  EReference getEntity_Ports();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Entity#getParameters() <em>Get Parameters</em>}' operation. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the '<em>Get Parameters</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Entity#getParameters()
   * @generated
   */
  EOperation getEntity__GetParameters();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Entity#getInputPorts() <em>Get Input Ports</em>}' operation. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Get Input Ports</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Entity#getInputPorts()
   * @generated
   */
  EOperation getEntity__GetInputPorts();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Entity#getOutputPorts() <em>Get Output Ports</em>}' operation. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Get Output Ports</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Entity#getOutputPorts()
   * @generated
   */
  EOperation getEntity__GetOutputPorts();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.CompositeEntity <em>Composite Entity</em>}'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the meta object for class '<em>Composite Entity</em>'.
   * @see org.eclipse.triquetrum.workflow.model.CompositeEntity
   * @generated
   */
  EClass getCompositeEntity();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.workflow.model.CompositeEntity#getEntities <em>Entities</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference list '<em>Entities</em>'.
   * @see org.eclipse.triquetrum.workflow.model.CompositeEntity#getEntities()
   * @see #getCompositeEntity()
   * @generated
   */
  EReference getCompositeEntity_Entities();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.workflow.model.CompositeEntity#getRelations <em>Relations</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference list '<em>Relations</em>'.
   * @see org.eclipse.triquetrum.workflow.model.CompositeEntity#getRelations()
   * @see #getCompositeEntity()
   * @generated
   */
  EReference getCompositeEntity_Relations();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Actor <em>Actor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Actor</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Actor
   * @generated
   */
  EClass getActor();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.CompositeActor <em>Composite Actor</em>}'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the meta object for class '<em>Composite Actor</em>'.
   * @see org.eclipse.triquetrum.workflow.model.CompositeActor
   * @generated
   */
  EClass getCompositeActor();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.triquetrum.workflow.model.CompositeActor#getDirector <em>Director</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference '<em>Director</em>'.
   * @see org.eclipse.triquetrum.workflow.model.CompositeActor#getDirector()
   * @see #getCompositeActor()
   * @generated
   */
  EReference getCompositeActor_Director();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Port <em>Port</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Port</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Port
   * @generated
   */
  EClass getPort();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Port#isInput <em>Input</em>}'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Input</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Port#isInput()
   * @see #getPort()
   * @generated
   */
  EAttribute getPort_Input();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Port#isOutput <em>Output</em>}'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Output</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Port#isOutput()
   * @see #getPort()
   * @generated
   */
  EAttribute getPort_Output();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Port#canAcceptNewInsideRelation() <em>Can Accept New Inside Relation</em>}'
   * operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Can Accept New Inside Relation</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Port#canAcceptNewInsideRelation()
   * @generated
   */
  EOperation getPort__CanAcceptNewInsideRelation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Port#isMultiPort <em>Multi Port</em>}'. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Multi Port</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Port#isMultiPort()
   * @see #getPort()
   * @generated
   */
  EAttribute getPort_MultiPort();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Port#getDirection <em>Direction</em>}'. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Direction</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Port#getDirection()
   * @see #getPort()
   * @generated
   */
  EAttribute getPort_Direction();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.triquetrum.workflow.model.Port#getLinkedRelations <em>Linked Relations</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the reference list '<em>Linked Relations</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Port#getLinkedRelations()
   * @see #getPort()
   * @generated
   */
  EReference getPort_LinkedRelations();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.triquetrum.workflow.model.Port#getInsideLinkedRelations <em>Inside Linked Relations</em>
   * }'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Inside Linked Relations</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Port#getInsideLinkedRelations()
   * @see #getPort()
   * @generated
   */
  EReference getPort_InsideLinkedRelations();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.triquetrum.workflow.model.Port#getOutsideLinkedRelations
   * <em>Outside Linked Relations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Outside Linked Relations</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Port#getOutsideLinkedRelations()
   * @see #getPort()
   * @generated
   */
  EReference getPort_OutsideLinkedRelations();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Port#canAcceptNewOutsideRelation() <em>Can Accept New Outside Relation</em>}'
   * operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Can Accept New Outside Relation</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Port#canAcceptNewOutsideRelation()
   * @generated
   */
  EOperation getPort__CanAcceptNewOutsideRelation();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Relation <em>Relation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Relation</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Relation
   * @generated
   */
  EClass getRelation();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.triquetrum.workflow.model.Relation#getLinkedRelations <em>Linked Relations</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the reference list '<em>Linked Relations</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Relation#getLinkedRelations()
   * @see #getRelation()
   * @generated
   */
  EReference getRelation_LinkedRelations();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.triquetrum.workflow.model.Relation#getLinkingRelations <em>Linking Relations</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Linking Relations</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Relation#getLinkingRelations()
   * @see #getRelation()
   * @generated
   */
  EReference getRelation_LinkingRelations();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.triquetrum.workflow.model.Relation#getLinkedPorts <em>Linked Ports</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the reference list '<em>Linked Ports</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Relation#getLinkedPorts()
   * @see #getRelation()
   * @generated
   */
  EReference getRelation_LinkedPorts();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Relation#isConnected() <em>Is Connected</em>}' operation. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Is Connected</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Relation#isConnected()
   * @generated
   */
  EOperation getRelation__IsConnected();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Relation#getVertex() <em>Get Vertex</em>}' operation. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Get Vertex</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Relation#getVertex()
   * @generated
   */
  EOperation getRelation__GetVertex();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Location <em>Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Location</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Location
   * @generated
   */
  EClass getLocation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.workflow.model.Location#getExpression <em>Expression</em>}'. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Expression</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Location#getExpression()
   * @see #getLocation()
   * @generated
   */
  EAttribute getLocation_Expression();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Location#getLocation() <em>Get Location</em>}' operation. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Get Location</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Location#getLocation()
   * @generated
   */
  EOperation getLocation__GetLocation();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Vertex <em>Vertex</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Vertex</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Vertex
   * @generated
   */
  EClass getVertex();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.workflow.model.Linkable <em>Linkable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Linkable</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Linkable
   * @generated
   */
  EClass getLinkable();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Linkable#link(org.eclipse.triquetrum.workflow.model.Relation) <em>Link</em>}'
   * operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Link</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Linkable#link(org.eclipse.triquetrum.workflow.model.Relation)
   * @generated
   */
  EOperation getLinkable__Link__Relation();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Linkable#unlink(org.eclipse.triquetrum.workflow.model.Relation)
   * <em>Unlink</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Unlink</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Linkable#unlink(org.eclipse.triquetrum.workflow.model.Relation)
   * @generated
   */
  EOperation getLinkable__Unlink__Relation();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Linkable#isPotentialStart() <em>Is Potential Start</em>}' operation. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the '<em>Is Potential Start</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Linkable#isPotentialStart()
   * @generated
   */
  EOperation getLinkable__IsPotentialStart();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Linkable#isPotentialEnd(org.eclipse.triquetrum.workflow.model.Linkable)
   * <em>Is Potential End</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Is Potential End</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Linkable#isPotentialEnd(org.eclipse.triquetrum.workflow.model.Linkable)
   * @generated
   */
  EOperation getLinkable__IsPotentialEnd__Linkable();

  /**
   * Returns the meta object for the '{@link org.eclipse.triquetrum.workflow.model.Linkable#buildWrappedLinks() <em>Build Wrapped Links</em>}' operation. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the '<em>Build Wrapped Links</em>' operation.
   * @see org.eclipse.triquetrum.workflow.model.Linkable#buildWrappedLinks()
   * @generated
   */
  EOperation getLinkable__BuildWrappedLinks();

  /**
   * Returns the meta object for enum '{@link org.eclipse.triquetrum.workflow.model.Direction <em>Direction</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @return the meta object for enum '<em>Direction</em>'.
   * @see org.eclipse.triquetrum.workflow.model.Direction
   * @generated
   */
  EEnum getDirection();

  /**
   * Returns the meta object for data type '{@link org.eclipse.triquetrum.workflow.model.util.Visitor <em>Visitor</em>}'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the meta object for data type '<em>Visitor</em>'.
   * @see org.eclipse.triquetrum.workflow.model.util.Visitor
   * @model instanceClass="org.eclipse.triquetrum.workflow.model.util.Visitor"
   * @generated
   */
  EDataType getVisitor();

  /**
   * Returns the meta object for data type '{@link ptolemy.kernel.util.NamedObj <em>Ptolemy Named Obj</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for data type '<em>Ptolemy Named Obj</em>'.
   * @see ptolemy.kernel.util.NamedObj
   * @model instanceClass="ptolemy.kernel.util.NamedObj"
   * @generated
   */
  EDataType getPtolemyNamedObj();

  /**
   * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the factory that creates the instances of the model.
   * @generated
   */
  TriqFactory getTriqFactory();

  /**
   * <!-- begin-user-doc --> Defines literals for the meta objects that represent
   * <ul>
   * <li>each class,</li>
   * <li>each feature of each class,</li>
   * <li>each operation of each class,</li>
   * <li>each enum,</li>
   * <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * 
   * @generated
   */
  interface Literals {
    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl <em>Named Obj</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.triquetrum.workflow.model.impl.NamedObjImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getNamedObj()
     * @generated
     */
    EClass NAMED_OBJ = eINSTANCE.getNamedObj();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute NAMED_OBJ__NAME = eINSTANCE.getNamedObj_Name();

    /**
     * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference NAMED_OBJ__ATTRIBUTES = eINSTANCE.getNamedObj_Attributes();

    /**
     * The meta object literal for the '<em><b>Wrapped Type</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute NAMED_OBJ__WRAPPED_TYPE = eINSTANCE.getNamedObj_WrappedType();

    /**
     * The meta object literal for the '<em><b>Wrapped Object</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute NAMED_OBJ__WRAPPED_OBJECT = eINSTANCE.getNamedObj_WrappedObject();

    /**
     * The meta object literal for the '<em><b>Deep Complete</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute NAMED_OBJ__DEEP_COMPLETE = eINSTANCE.getNamedObj_DeepComplete();

    /**
     * The meta object literal for the '<em><b>Icon Id</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute NAMED_OBJ__ICON_ID = eINSTANCE.getNamedObj_IconId();

    /**
     * The meta object literal for the '<em><b>Get Container</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation NAMED_OBJ___GET_CONTAINER = eINSTANCE.getNamedObj__GetContainer();

    /**
     * The meta object literal for the '<em><b>Get Lowest Common Container</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation NAMED_OBJ___GET_LOWEST_COMMON_CONTAINER__NAMEDOBJ = eINSTANCE.getNamedObj__GetLowestCommonContainer__NamedObj();

    /**
     * The meta object literal for the '<em><b>Top Level</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation NAMED_OBJ___TOP_LEVEL = eINSTANCE.getNamedObj__TopLevel();

    /**
     * The meta object literal for the '<em><b>Set Property</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation NAMED_OBJ___SET_PROPERTY__STRING_STRING_STRING = eINSTANCE.getNamedObj__SetProperty__String_String_String();

    /**
     * The meta object literal for the '<em><b>Apply Wrapped Object</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation NAMED_OBJ___APPLY_WRAPPED_OBJECT = eINSTANCE.getNamedObj__ApplyWrappedObject();

    /**
     * The meta object literal for the '<em><b>Build Wrapped Object</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation NAMED_OBJ___BUILD_WRAPPED_OBJECT = eINSTANCE.getNamedObj__BuildWrappedObject();

    /**
     * The meta object literal for the '<em><b>Get Full Name</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation NAMED_OBJ___GET_FULL_NAME = eINSTANCE.getNamedObj__GetFullName();

    /**
     * The meta object literal for the '<em><b>Get Child</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation NAMED_OBJ___GET_CHILD__STRING = eINSTANCE.getNamedObj__GetChild__String();

    /**
     * The meta object literal for the '<em><b>Welcome</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation NAMED_OBJ___WELCOME__VISITOR_BOOLEAN = eINSTANCE.getNamedObj__Welcome__Visitor_boolean();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.AttributeImpl <em>Attribute</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.triquetrum.workflow.model.impl.AttributeImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getAttribute()
     * @generated
     */
    EClass ATTRIBUTE = eINSTANCE.getAttribute();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.AnnotationImpl <em>Annotation</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.triquetrum.workflow.model.impl.AnnotationImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getAnnotation()
     * @generated
     */
    EClass ANNOTATION = eINSTANCE.getAnnotation();

    /**
     * The meta object literal for the '<em><b>Text</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute ANNOTATION__TEXT = eINSTANCE.getAnnotation_Text();

    /**
     * The meta object literal for the '<em><b>Font Family</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute ANNOTATION__FONT_FAMILY = eINSTANCE.getAnnotation_FontFamily();

    /**
     * The meta object literal for the '<em><b>Text Size</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute ANNOTATION__TEXT_SIZE = eINSTANCE.getAnnotation_TextSize();

    /**
     * The meta object literal for the '<em><b>Bold</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute ANNOTATION__BOLD = eINSTANCE.getAnnotation_Bold();

    /**
     * The meta object literal for the '<em><b>Italic</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute ANNOTATION__ITALIC = eINSTANCE.getAnnotation_Italic();

    /**
     * The meta object literal for the '<em><b>Color</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute ANNOTATION__COLOR = eINSTANCE.getAnnotation_Color();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.ParameterImpl <em>Parameter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.triquetrum.workflow.model.impl.ParameterImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getParameter()
     * @generated
     */
    EClass PARAMETER = eINSTANCE.getParameter();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute PARAMETER__EXPRESSION = eINSTANCE.getParameter_Expression();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.DirectorImpl <em>Director</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.triquetrum.workflow.model.impl.DirectorImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getDirector()
     * @generated
     */
    EClass DIRECTOR = eINSTANCE.getDirector();

    /**
     * The meta object literal for the '<em><b>Get Parameters</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation DIRECTOR___GET_PARAMETERS = eINSTANCE.getDirector__GetParameters();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.EntityImpl <em>Entity</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.triquetrum.workflow.model.impl.EntityImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getEntity()
     * @generated
     */
    EClass ENTITY = eINSTANCE.getEntity();

    /**
     * The meta object literal for the '<em><b>Ports</b></em>' containment reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference ENTITY__PORTS = eINSTANCE.getEntity_Ports();

    /**
     * The meta object literal for the '<em><b>Get Parameters</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation ENTITY___GET_PARAMETERS = eINSTANCE.getEntity__GetParameters();

    /**
     * The meta object literal for the '<em><b>Get Input Ports</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation ENTITY___GET_INPUT_PORTS = eINSTANCE.getEntity__GetInputPorts();

    /**
     * The meta object literal for the '<em><b>Get Output Ports</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation ENTITY___GET_OUTPUT_PORTS = eINSTANCE.getEntity__GetOutputPorts();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.CompositeEntityImpl <em>Composite Entity</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.triquetrum.workflow.model.impl.CompositeEntityImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getCompositeEntity()
     * @generated
     */
    EClass COMPOSITE_ENTITY = eINSTANCE.getCompositeEntity();

    /**
     * The meta object literal for the '<em><b>Entities</b></em>' containment reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference COMPOSITE_ENTITY__ENTITIES = eINSTANCE.getCompositeEntity_Entities();

    /**
     * The meta object literal for the '<em><b>Relations</b></em>' containment reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference COMPOSITE_ENTITY__RELATIONS = eINSTANCE.getCompositeEntity_Relations();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.ActorImpl <em>Actor</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.triquetrum.workflow.model.impl.ActorImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getActor()
     * @generated
     */
    EClass ACTOR = eINSTANCE.getActor();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.CompositeActorImpl <em>Composite Actor</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.triquetrum.workflow.model.impl.CompositeActorImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getCompositeActor()
     * @generated
     */
    EClass COMPOSITE_ACTOR = eINSTANCE.getCompositeActor();

    /**
     * The meta object literal for the '<em><b>Director</b></em>' containment reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference COMPOSITE_ACTOR__DIRECTOR = eINSTANCE.getCompositeActor_Director();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.PortImpl <em>Port</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.triquetrum.workflow.model.impl.PortImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getPort()
     * @generated
     */
    EClass PORT = eINSTANCE.getPort();

    /**
     * The meta object literal for the '<em><b>Input</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute PORT__INPUT = eINSTANCE.getPort_Input();

    /**
     * The meta object literal for the '<em><b>Output</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute PORT__OUTPUT = eINSTANCE.getPort_Output();

    /**
     * The meta object literal for the '<em><b>Can Accept New Inside Relation</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation PORT___CAN_ACCEPT_NEW_INSIDE_RELATION = eINSTANCE.getPort__CanAcceptNewInsideRelation();

    /**
     * The meta object literal for the '<em><b>Multi Port</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute PORT__MULTI_PORT = eINSTANCE.getPort_MultiPort();

    /**
     * The meta object literal for the '<em><b>Direction</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute PORT__DIRECTION = eINSTANCE.getPort_Direction();

    /**
     * The meta object literal for the '<em><b>Linked Relations</b></em>' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference PORT__LINKED_RELATIONS = eINSTANCE.getPort_LinkedRelations();

    /**
     * The meta object literal for the '<em><b>Inside Linked Relations</b></em>' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference PORT__INSIDE_LINKED_RELATIONS = eINSTANCE.getPort_InsideLinkedRelations();

    /**
     * The meta object literal for the '<em><b>Outside Linked Relations</b></em>' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference PORT__OUTSIDE_LINKED_RELATIONS = eINSTANCE.getPort_OutsideLinkedRelations();

    /**
     * The meta object literal for the '<em><b>Can Accept New Outside Relation</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation PORT___CAN_ACCEPT_NEW_OUTSIDE_RELATION = eINSTANCE.getPort__CanAcceptNewOutsideRelation();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.RelationImpl <em>Relation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.triquetrum.workflow.model.impl.RelationImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getRelation()
     * @generated
     */
    EClass RELATION = eINSTANCE.getRelation();

    /**
     * The meta object literal for the '<em><b>Linked Relations</b></em>' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference RELATION__LINKED_RELATIONS = eINSTANCE.getRelation_LinkedRelations();

    /**
     * The meta object literal for the '<em><b>Linking Relations</b></em>' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference RELATION__LINKING_RELATIONS = eINSTANCE.getRelation_LinkingRelations();

    /**
     * The meta object literal for the '<em><b>Linked Ports</b></em>' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference RELATION__LINKED_PORTS = eINSTANCE.getRelation_LinkedPorts();

    /**
     * The meta object literal for the '<em><b>Is Connected</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation RELATION___IS_CONNECTED = eINSTANCE.getRelation__IsConnected();

    /**
     * The meta object literal for the '<em><b>Get Vertex</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation RELATION___GET_VERTEX = eINSTANCE.getRelation__GetVertex();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.LocationImpl <em>Location</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.triquetrum.workflow.model.impl.LocationImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getLocation()
     * @generated
     */
    EClass LOCATION = eINSTANCE.getLocation();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute LOCATION__EXPRESSION = eINSTANCE.getLocation_Expression();

    /**
     * The meta object literal for the '<em><b>Get Location</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation LOCATION___GET_LOCATION = eINSTANCE.getLocation__GetLocation();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.impl.VertexImpl <em>Vertex</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.triquetrum.workflow.model.impl.VertexImpl
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getVertex()
     * @generated
     */
    EClass VERTEX = eINSTANCE.getVertex();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.Linkable <em>Linkable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.triquetrum.workflow.model.Linkable
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getLinkable()
     * @generated
     */
    EClass LINKABLE = eINSTANCE.getLinkable();

    /**
     * The meta object literal for the '<em><b>Link</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation LINKABLE___LINK__RELATION = eINSTANCE.getLinkable__Link__Relation();

    /**
     * The meta object literal for the '<em><b>Unlink</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation LINKABLE___UNLINK__RELATION = eINSTANCE.getLinkable__Unlink__Relation();

    /**
     * The meta object literal for the '<em><b>Is Potential Start</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation LINKABLE___IS_POTENTIAL_START = eINSTANCE.getLinkable__IsPotentialStart();

    /**
     * The meta object literal for the '<em><b>Is Potential End</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation LINKABLE___IS_POTENTIAL_END__LINKABLE = eINSTANCE.getLinkable__IsPotentialEnd__Linkable();

    /**
     * The meta object literal for the '<em><b>Build Wrapped Links</b></em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation LINKABLE___BUILD_WRAPPED_LINKS = eINSTANCE.getLinkable__BuildWrappedLinks();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.workflow.model.Direction <em>Direction</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.triquetrum.workflow.model.Direction
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getDirection()
     * @generated
     */
    EEnum DIRECTION = eINSTANCE.getDirection();

    /**
     * The meta object literal for the '<em>Visitor</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.triquetrum.workflow.model.util.Visitor
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getVisitor()
     * @generated
     */
    EDataType VISITOR = eINSTANCE.getVisitor();

    /**
     * The meta object literal for the '<em>Ptolemy Named Obj</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see ptolemy.kernel.util.NamedObj
     * @see org.eclipse.triquetrum.workflow.model.impl.TriqPackageImpl#getPtolemyNamedObj()
     * @generated
     */
    EDataType PTOLEMY_NAMED_OBJ = eINSTANCE.getPtolemyNamedObj();

  }

} // TriqPackage
