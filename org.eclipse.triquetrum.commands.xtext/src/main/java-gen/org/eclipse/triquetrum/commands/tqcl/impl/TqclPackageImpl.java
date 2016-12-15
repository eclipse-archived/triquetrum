/**
 * ******************************************************************************
 * Copyright (c)  2016 Totaro Rodolfo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * generated by Xtext 2.10.0	
 *  *
 * Contributors:
 *    Rodolfo Totaro - initial API and implementation and/or initial documentation
 *  ******************************************************************************
 */
package org.eclipse.triquetrum.commands.tqcl.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.triquetrum.commands.tqcl.Category;
import org.eclipse.triquetrum.commands.tqcl.Command;
import org.eclipse.triquetrum.commands.tqcl.CompositeCommand;
import org.eclipse.triquetrum.commands.tqcl.Connect;
import org.eclipse.triquetrum.commands.tqcl.ConnectionPort;
import org.eclipse.triquetrum.commands.tqcl.Go;
import org.eclipse.triquetrum.commands.tqcl.Include;
import org.eclipse.triquetrum.commands.tqcl.Insert;
import org.eclipse.triquetrum.commands.tqcl.Library;
import org.eclipse.triquetrum.commands.tqcl.Parameter;
import org.eclipse.triquetrum.commands.tqcl.Set;
import org.eclipse.triquetrum.commands.tqcl.TqclFactory;
import org.eclipse.triquetrum.commands.tqcl.TqclPackage;
import org.eclipse.triquetrum.commands.tqcl.TriquetrumScript;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TqclPackageImpl extends EPackageImpl implements TqclPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass triquetrumScriptEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass compositeCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass commandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass includeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass libraryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass insertEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass setEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass connectEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass connectionPortEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass goEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum categoryEEnum = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.triquetrum.commands.tqcl.TqclPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private TqclPackageImpl()
  {
    super(eNS_URI, TqclFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link TqclPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static TqclPackage init()
  {
    if (isInited) return (TqclPackage)EPackage.Registry.INSTANCE.getEPackage(TqclPackage.eNS_URI);

    // Obtain or create and register package
    TqclPackageImpl theTqclPackage = (TqclPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TqclPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TqclPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theTqclPackage.createPackageContents();

    // Initialize created meta-data
    theTqclPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theTqclPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(TqclPackage.eNS_URI, theTqclPackage);
    return theTqclPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTriquetrumScript()
  {
    return triquetrumScriptEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTriquetrumScript_Libraries()
  {
    return (EReference)triquetrumScriptEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTriquetrumScript_Commands()
  {
    return (EReference)triquetrumScriptEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCompositeCommand()
  {
    return compositeCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCompositeCommand_Start()
  {
    return (EReference)compositeCommandEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCompositeCommand_Commands()
  {
    return (EReference)compositeCommandEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCompositeCommand_End()
  {
    return (EReference)compositeCommandEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCommand()
  {
    return commandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getInclude()
  {
    return includeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getInclude_Filename()
  {
    return (EAttribute)includeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLibrary()
  {
    return libraryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLibrary_Name()
  {
    return (EAttribute)libraryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getInsert()
  {
    return insertEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getInsert_Category()
  {
    return (EAttribute)insertEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getInsert_EntityClass()
  {
    return (EAttribute)insertEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getInsert_Name()
  {
    return (EAttribute)insertEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getInsert_Parameters()
  {
    return (EReference)insertEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSet()
  {
    return setEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSet_Param()
  {
    return (EReference)setEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConnect()
  {
    return connectEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConnect_From()
  {
    return (EReference)connectEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConnect_To()
  {
    return (EReference)connectEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConnectionPort()
  {
    return connectionPortEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConnectionPort_Actor()
  {
    return (EReference)connectionPortEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getConnectionPort_Port()
  {
    return (EAttribute)connectionPortEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGo()
  {
    return goEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGo_Direction()
  {
    return (EAttribute)goEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGo_Actor()
  {
    return (EReference)goEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParameter()
  {
    return parameterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getParameter_Id()
  {
    return (EAttribute)parameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getParameter_Value()
  {
    return (EAttribute)parameterEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getCategory()
  {
    return categoryEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TqclFactory getTqclFactory()
  {
    return (TqclFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    triquetrumScriptEClass = createEClass(TRIQUETRUM_SCRIPT);
    createEReference(triquetrumScriptEClass, TRIQUETRUM_SCRIPT__LIBRARIES);
    createEReference(triquetrumScriptEClass, TRIQUETRUM_SCRIPT__COMMANDS);

    compositeCommandEClass = createEClass(COMPOSITE_COMMAND);
    createEReference(compositeCommandEClass, COMPOSITE_COMMAND__START);
    createEReference(compositeCommandEClass, COMPOSITE_COMMAND__COMMANDS);
    createEReference(compositeCommandEClass, COMPOSITE_COMMAND__END);

    commandEClass = createEClass(COMMAND);

    includeEClass = createEClass(INCLUDE);
    createEAttribute(includeEClass, INCLUDE__FILENAME);

    libraryEClass = createEClass(LIBRARY);
    createEAttribute(libraryEClass, LIBRARY__NAME);

    insertEClass = createEClass(INSERT);
    createEAttribute(insertEClass, INSERT__CATEGORY);
    createEAttribute(insertEClass, INSERT__ENTITY_CLASS);
    createEAttribute(insertEClass, INSERT__NAME);
    createEReference(insertEClass, INSERT__PARAMETERS);

    setEClass = createEClass(SET);
    createEReference(setEClass, SET__PARAM);

    connectEClass = createEClass(CONNECT);
    createEReference(connectEClass, CONNECT__FROM);
    createEReference(connectEClass, CONNECT__TO);

    connectionPortEClass = createEClass(CONNECTION_PORT);
    createEReference(connectionPortEClass, CONNECTION_PORT__ACTOR);
    createEAttribute(connectionPortEClass, CONNECTION_PORT__PORT);

    goEClass = createEClass(GO);
    createEAttribute(goEClass, GO__DIRECTION);
    createEReference(goEClass, GO__ACTOR);

    parameterEClass = createEClass(PARAMETER);
    createEAttribute(parameterEClass, PARAMETER__ID);
    createEAttribute(parameterEClass, PARAMETER__VALUE);

    // Create enums
    categoryEEnum = createEEnum(CATEGORY);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    compositeCommandEClass.getESuperTypes().add(this.getCommand());
    includeEClass.getESuperTypes().add(this.getCommand());
    insertEClass.getESuperTypes().add(this.getCommand());
    setEClass.getESuperTypes().add(this.getCommand());
    connectEClass.getESuperTypes().add(this.getCommand());

    // Initialize classes and features; add operations and parameters
    initEClass(triquetrumScriptEClass, TriquetrumScript.class, "TriquetrumScript", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTriquetrumScript_Libraries(), this.getLibrary(), null, "libraries", null, 0, -1, TriquetrumScript.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTriquetrumScript_Commands(), this.getCommand(), null, "commands", null, 0, -1, TriquetrumScript.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(compositeCommandEClass, CompositeCommand.class, "CompositeCommand", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCompositeCommand_Start(), this.getGo(), null, "start", null, 0, 1, CompositeCommand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCompositeCommand_Commands(), this.getCommand(), null, "commands", null, 0, -1, CompositeCommand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCompositeCommand_End(), this.getGo(), null, "end", null, 0, 1, CompositeCommand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(commandEClass, Command.class, "Command", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(includeEClass, Include.class, "Include", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getInclude_Filename(), ecorePackage.getEString(), "filename", null, 0, 1, Include.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(libraryEClass, Library.class, "Library", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLibrary_Name(), ecorePackage.getEString(), "name", null, 0, 1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(insertEClass, Insert.class, "Insert", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getInsert_Category(), this.getCategory(), "category", null, 0, 1, Insert.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getInsert_EntityClass(), ecorePackage.getEString(), "entityClass", null, 0, 1, Insert.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getInsert_Name(), ecorePackage.getEString(), "name", null, 0, 1, Insert.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getInsert_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, Insert.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(setEClass, Set.class, "Set", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSet_Param(), this.getParameter(), null, "param", null, 0, 1, Set.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(connectEClass, Connect.class, "Connect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getConnect_From(), this.getConnectionPort(), null, "from", null, 0, -1, Connect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConnect_To(), this.getConnectionPort(), null, "to", null, 0, -1, Connect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(connectionPortEClass, ConnectionPort.class, "ConnectionPort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getConnectionPort_Actor(), this.getInsert(), null, "actor", null, 0, 1, ConnectionPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getConnectionPort_Port(), ecorePackage.getEString(), "port", null, 0, 1, ConnectionPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(goEClass, Go.class, "Go", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGo_Direction(), ecorePackage.getEString(), "direction", null, 0, 1, Go.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGo_Actor(), this.getInsert(), null, "actor", null, 0, 1, Go.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getParameter_Id(), ecorePackage.getEString(), "id", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getParameter_Value(), ecorePackage.getEString(), "value", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(categoryEEnum, Category.class, "Category");
    addEEnumLiteral(categoryEEnum, Category.ACTOR);
    addEEnumLiteral(categoryEEnum, Category.PARAMETER);
    addEEnumLiteral(categoryEEnum, Category.PORT);
    addEEnumLiteral(categoryEEnum, Category.DIRECTOR);

    // Create resource
    createResource(eNS_URI);
  }

} //TqclPackageImpl