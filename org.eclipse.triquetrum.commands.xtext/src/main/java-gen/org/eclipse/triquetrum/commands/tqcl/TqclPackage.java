/*******************************************************************************
 * Copyright (c)  2017 Totaro Rodolfo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Rodolfo Totaro - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.commands.tqcl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.triquetrum.commands.tqcl.TqclFactory
 * @model kind="package"
 * @generated
 */
public interface TqclPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "tqcl";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/triquetrum/commands/Tqcl";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "tqcl";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  TqclPackage eINSTANCE = org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.TriquetrumScriptImpl <em>Triquetrum Script</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.commands.tqcl.impl.TriquetrumScriptImpl
   * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getTriquetrumScript()
   * @generated
   */
  int TRIQUETRUM_SCRIPT = 0;

  /**
   * The feature id for the '<em><b>Libraries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRIQUETRUM_SCRIPT__LIBRARIES = 0;

  /**
   * The feature id for the '<em><b>Commands</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRIQUETRUM_SCRIPT__COMMANDS = 1;

  /**
   * The number of structural features of the '<em>Triquetrum Script</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRIQUETRUM_SCRIPT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.CommandImpl <em>Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.commands.tqcl.impl.CommandImpl
   * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getCommand()
   * @generated
   */
  int COMMAND = 2;

  /**
   * The number of structural features of the '<em>Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMMAND_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.CompositeCommandImpl <em>Composite Command</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.commands.tqcl.impl.CompositeCommandImpl
   * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getCompositeCommand()
   * @generated
   */
  int COMPOSITE_COMMAND = 1;

  /**
   * The feature id for the '<em><b>Start</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_COMMAND__START = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Commands</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_COMMAND__COMMANDS = COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>End</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_COMMAND__END = COMMAND_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Composite Command</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITE_COMMAND_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.IncludeImpl <em>Include</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.commands.tqcl.impl.IncludeImpl
   * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getInclude()
   * @generated
   */
  int INCLUDE = 3;

  /**
   * The feature id for the '<em><b>Filename</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INCLUDE__FILENAME = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Include</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INCLUDE_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.LibraryImpl <em>Library</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.commands.tqcl.impl.LibraryImpl
   * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getLibrary()
   * @generated
   */
  int LIBRARY = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__NAME = 0;

  /**
   * The number of structural features of the '<em>Library</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.InsertImpl <em>Insert</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.commands.tqcl.impl.InsertImpl
   * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getInsert()
   * @generated
   */
  int INSERT = 5;

  /**
   * The feature id for the '<em><b>Category</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSERT__CATEGORY = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Entity Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSERT__ENTITY_CLASS = COMMAND_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSERT__NAME = COMMAND_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSERT__PARAMETERS = COMMAND_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Insert</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSERT_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.SetImpl <em>Set</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.commands.tqcl.impl.SetImpl
   * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getSet()
   * @generated
   */
  int SET = 6;

  /**
   * The feature id for the '<em><b>Param</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET__PARAM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Set</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.ConnectImpl <em>Connect</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.commands.tqcl.impl.ConnectImpl
   * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getConnect()
   * @generated
   */
  int CONNECT = 7;

  /**
   * The feature id for the '<em><b>From</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECT__FROM = COMMAND_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>To</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECT__TO = COMMAND_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Connect</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECT_FEATURE_COUNT = COMMAND_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.ConnectionPortImpl <em>Connection Port</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.commands.tqcl.impl.ConnectionPortImpl
   * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getConnectionPort()
   * @generated
   */
  int CONNECTION_PORT = 8;

  /**
   * The feature id for the '<em><b>Actor</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECTION_PORT__ACTOR = 0;

  /**
   * The feature id for the '<em><b>Port</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECTION_PORT__PORT = 1;

  /**
   * The number of structural features of the '<em>Connection Port</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECTION_PORT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.GoImpl <em>Go</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.commands.tqcl.impl.GoImpl
   * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getGo()
   * @generated
   */
  int GO = 9;

  /**
   * The feature id for the '<em><b>Direction</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GO__DIRECTION = 0;

  /**
   * The feature id for the '<em><b>Actor</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GO__ACTOR = 1;

  /**
   * The number of structural features of the '<em>Go</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GO_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.ParameterImpl <em>Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.commands.tqcl.impl.ParameterImpl
   * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getParameter()
   * @generated
   */
  int PARAMETER = 10;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__ID = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__VALUE = 1;

  /**
   * The number of structural features of the '<em>Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.triquetrum.commands.tqcl.Category <em>Category</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.triquetrum.commands.tqcl.Category
   * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getCategory()
   * @generated
   */
  int CATEGORY = 11;


  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.commands.tqcl.TriquetrumScript <em>Triquetrum Script</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Triquetrum Script</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.TriquetrumScript
   * @generated
   */
  EClass getTriquetrumScript();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.commands.tqcl.TriquetrumScript#getLibraries <em>Libraries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Libraries</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.TriquetrumScript#getLibraries()
   * @see #getTriquetrumScript()
   * @generated
   */
  EReference getTriquetrumScript_Libraries();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.commands.tqcl.TriquetrumScript#getCommands <em>Commands</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Commands</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.TriquetrumScript#getCommands()
   * @see #getTriquetrumScript()
   * @generated
   */
  EReference getTriquetrumScript_Commands();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.commands.tqcl.CompositeCommand <em>Composite Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Composite Command</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.CompositeCommand
   * @generated
   */
  EClass getCompositeCommand();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.triquetrum.commands.tqcl.CompositeCommand#getStart <em>Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Start</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.CompositeCommand#getStart()
   * @see #getCompositeCommand()
   * @generated
   */
  EReference getCompositeCommand_Start();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.commands.tqcl.CompositeCommand#getCommands <em>Commands</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Commands</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.CompositeCommand#getCommands()
   * @see #getCompositeCommand()
   * @generated
   */
  EReference getCompositeCommand_Commands();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.triquetrum.commands.tqcl.CompositeCommand#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>End</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.CompositeCommand#getEnd()
   * @see #getCompositeCommand()
   * @generated
   */
  EReference getCompositeCommand_End();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.commands.tqcl.Command <em>Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Command</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Command
   * @generated
   */
  EClass getCommand();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.commands.tqcl.Include <em>Include</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Include</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Include
   * @generated
   */
  EClass getInclude();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.commands.tqcl.Include#getFilename <em>Filename</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Filename</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Include#getFilename()
   * @see #getInclude()
   * @generated
   */
  EAttribute getInclude_Filename();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.commands.tqcl.Library <em>Library</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Library</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Library
   * @generated
   */
  EClass getLibrary();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.commands.tqcl.Library#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Library#getName()
   * @see #getLibrary()
   * @generated
   */
  EAttribute getLibrary_Name();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.commands.tqcl.Insert <em>Insert</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Insert</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Insert
   * @generated
   */
  EClass getInsert();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.commands.tqcl.Insert#getCategory <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Category</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Insert#getCategory()
   * @see #getInsert()
   * @generated
   */
  EAttribute getInsert_Category();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.commands.tqcl.Insert#getEntityClass <em>Entity Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Entity Class</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Insert#getEntityClass()
   * @see #getInsert()
   * @generated
   */
  EAttribute getInsert_EntityClass();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.commands.tqcl.Insert#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Insert#getName()
   * @see #getInsert()
   * @generated
   */
  EAttribute getInsert_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.commands.tqcl.Insert#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Insert#getParameters()
   * @see #getInsert()
   * @generated
   */
  EReference getInsert_Parameters();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.commands.tqcl.Set <em>Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Set
   * @generated
   */
  EClass getSet();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.triquetrum.commands.tqcl.Set#getParam <em>Param</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Param</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Set#getParam()
   * @see #getSet()
   * @generated
   */
  EReference getSet_Param();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.commands.tqcl.Connect <em>Connect</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Connect</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Connect
   * @generated
   */
  EClass getConnect();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.commands.tqcl.Connect#getFrom <em>From</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>From</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Connect#getFrom()
   * @see #getConnect()
   * @generated
   */
  EReference getConnect_From();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.triquetrum.commands.tqcl.Connect#getTo <em>To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>To</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Connect#getTo()
   * @see #getConnect()
   * @generated
   */
  EReference getConnect_To();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.commands.tqcl.ConnectionPort <em>Connection Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Connection Port</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.ConnectionPort
   * @generated
   */
  EClass getConnectionPort();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.triquetrum.commands.tqcl.ConnectionPort#getActor <em>Actor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Actor</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.ConnectionPort#getActor()
   * @see #getConnectionPort()
   * @generated
   */
  EReference getConnectionPort_Actor();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.commands.tqcl.ConnectionPort#getPort <em>Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Port</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.ConnectionPort#getPort()
   * @see #getConnectionPort()
   * @generated
   */
  EAttribute getConnectionPort_Port();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.commands.tqcl.Go <em>Go</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Go</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Go
   * @generated
   */
  EClass getGo();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.commands.tqcl.Go#getDirection <em>Direction</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Direction</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Go#getDirection()
   * @see #getGo()
   * @generated
   */
  EAttribute getGo_Direction();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.triquetrum.commands.tqcl.Go#getActor <em>Actor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Actor</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Go#getActor()
   * @see #getGo()
   * @generated
   */
  EReference getGo_Actor();

  /**
   * Returns the meta object for class '{@link org.eclipse.triquetrum.commands.tqcl.Parameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Parameter
   * @generated
   */
  EClass getParameter();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.commands.tqcl.Parameter#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Parameter#getId()
   * @see #getParameter()
   * @generated
   */
  EAttribute getParameter_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.triquetrum.commands.tqcl.Parameter#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Parameter#getValue()
   * @see #getParameter()
   * @generated
   */
  EAttribute getParameter_Value();

  /**
   * Returns the meta object for enum '{@link org.eclipse.triquetrum.commands.tqcl.Category <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Category</em>'.
   * @see org.eclipse.triquetrum.commands.tqcl.Category
   * @generated
   */
  EEnum getCategory();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  TqclFactory getTqclFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.TriquetrumScriptImpl <em>Triquetrum Script</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.commands.tqcl.impl.TriquetrumScriptImpl
     * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getTriquetrumScript()
     * @generated
     */
    EClass TRIQUETRUM_SCRIPT = eINSTANCE.getTriquetrumScript();

    /**
     * The meta object literal for the '<em><b>Libraries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TRIQUETRUM_SCRIPT__LIBRARIES = eINSTANCE.getTriquetrumScript_Libraries();

    /**
     * The meta object literal for the '<em><b>Commands</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TRIQUETRUM_SCRIPT__COMMANDS = eINSTANCE.getTriquetrumScript_Commands();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.CompositeCommandImpl <em>Composite Command</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.commands.tqcl.impl.CompositeCommandImpl
     * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getCompositeCommand()
     * @generated
     */
    EClass COMPOSITE_COMMAND = eINSTANCE.getCompositeCommand();

    /**
     * The meta object literal for the '<em><b>Start</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITE_COMMAND__START = eINSTANCE.getCompositeCommand_Start();

    /**
     * The meta object literal for the '<em><b>Commands</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITE_COMMAND__COMMANDS = eINSTANCE.getCompositeCommand_Commands();

    /**
     * The meta object literal for the '<em><b>End</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITE_COMMAND__END = eINSTANCE.getCompositeCommand_End();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.CommandImpl <em>Command</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.commands.tqcl.impl.CommandImpl
     * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getCommand()
     * @generated
     */
    EClass COMMAND = eINSTANCE.getCommand();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.IncludeImpl <em>Include</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.commands.tqcl.impl.IncludeImpl
     * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getInclude()
     * @generated
     */
    EClass INCLUDE = eINSTANCE.getInclude();

    /**
     * The meta object literal for the '<em><b>Filename</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INCLUDE__FILENAME = eINSTANCE.getInclude_Filename();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.LibraryImpl <em>Library</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.commands.tqcl.impl.LibraryImpl
     * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getLibrary()
     * @generated
     */
    EClass LIBRARY = eINSTANCE.getLibrary();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIBRARY__NAME = eINSTANCE.getLibrary_Name();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.InsertImpl <em>Insert</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.commands.tqcl.impl.InsertImpl
     * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getInsert()
     * @generated
     */
    EClass INSERT = eINSTANCE.getInsert();

    /**
     * The meta object literal for the '<em><b>Category</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INSERT__CATEGORY = eINSTANCE.getInsert_Category();

    /**
     * The meta object literal for the '<em><b>Entity Class</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INSERT__ENTITY_CLASS = eINSTANCE.getInsert_EntityClass();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INSERT__NAME = eINSTANCE.getInsert_Name();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INSERT__PARAMETERS = eINSTANCE.getInsert_Parameters();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.SetImpl <em>Set</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.commands.tqcl.impl.SetImpl
     * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getSet()
     * @generated
     */
    EClass SET = eINSTANCE.getSet();

    /**
     * The meta object literal for the '<em><b>Param</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SET__PARAM = eINSTANCE.getSet_Param();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.ConnectImpl <em>Connect</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.commands.tqcl.impl.ConnectImpl
     * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getConnect()
     * @generated
     */
    EClass CONNECT = eINSTANCE.getConnect();

    /**
     * The meta object literal for the '<em><b>From</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONNECT__FROM = eINSTANCE.getConnect_From();

    /**
     * The meta object literal for the '<em><b>To</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONNECT__TO = eINSTANCE.getConnect_To();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.ConnectionPortImpl <em>Connection Port</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.commands.tqcl.impl.ConnectionPortImpl
     * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getConnectionPort()
     * @generated
     */
    EClass CONNECTION_PORT = eINSTANCE.getConnectionPort();

    /**
     * The meta object literal for the '<em><b>Actor</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONNECTION_PORT__ACTOR = eINSTANCE.getConnectionPort_Actor();

    /**
     * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONNECTION_PORT__PORT = eINSTANCE.getConnectionPort_Port();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.GoImpl <em>Go</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.commands.tqcl.impl.GoImpl
     * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getGo()
     * @generated
     */
    EClass GO = eINSTANCE.getGo();

    /**
     * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GO__DIRECTION = eINSTANCE.getGo_Direction();

    /**
     * The meta object literal for the '<em><b>Actor</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GO__ACTOR = eINSTANCE.getGo_Actor();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.commands.tqcl.impl.ParameterImpl <em>Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.commands.tqcl.impl.ParameterImpl
     * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getParameter()
     * @generated
     */
    EClass PARAMETER = eINSTANCE.getParameter();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAMETER__ID = eINSTANCE.getParameter_Id();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAMETER__VALUE = eINSTANCE.getParameter_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.triquetrum.commands.tqcl.Category <em>Category</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.triquetrum.commands.tqcl.Category
     * @see org.eclipse.triquetrum.commands.tqcl.impl.TqclPackageImpl#getCategory()
     * @generated
     */
    EEnum CATEGORY = eINSTANCE.getCategory();

  }

} //TqclPackage
