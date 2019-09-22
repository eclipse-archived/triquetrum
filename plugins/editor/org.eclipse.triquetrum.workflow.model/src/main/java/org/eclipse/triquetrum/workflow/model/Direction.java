/*******************************************************************************
 * Copyright (c) 2015,2019 iSencia Belgium NV.
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
package org.eclipse.triquetrum.workflow.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Direction</b></em>', and utility methods for working with them. <!--
 * end-user-doc -->
 * 
 * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getDirection()
 * @model
 * @generated
 */
public enum Direction implements Enumerator {
  /**
   * The '<em><b>NORTH</b></em>' literal object. <!-- begin-user-doc -->Places the port on the top side of an actor.<!-- end-user-doc -->
   * 
   * @see #NORTH_VALUE
   * @generated
   * @ordered
   */
  NORTH(1, "NORTH", "NORTH"),

  /**
   * The '<em><b>SOUTH</b></em>' literal object. <!-- begin-user-doc -->Places the port on the bottom side of an actor.<!-- end-user-doc -->
   * 
   * @see #SOUTH_VALUE
   * @generated
   * @ordered
   */
  SOUTH(5, "SOUTH", "SOUTH"),

  /**
   * The '<em><b>EAST</b></em>' literal object. <!-- begin-user-doc -->Places the port on the right side of an actor.<!-- end-user-doc -->
   * 
   * @see #EAST_VALUE
   * @generated
   * @ordered
   */
  EAST(3, "EAST", "EAST"),

  /**
   * The '<em><b>WEST</b></em>' literal object. <!-- begin-user-doc -->Places the port on the left side of an actor.<!-- end-user-doc -->
   * 
   * @see #WEST_VALUE
   * @generated
   * @ordered
   */
  WEST(7, "WEST", "WEST"),
  /**
   * The '<em><b>DEFAULT</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #DEFAULT_VALUE
   * @generated
   * @ordered
   */
  DEFAULT(0, "DEFAULT", "DEFAULT");

  /**
   * The '<em><b>NORTH</b></em>' literal value. <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>NORTH</b></em>' literal object isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @see #NORTH
   * @model
   * @generated
   * @ordered
   */
  public static final int NORTH_VALUE = 1;

  /**
   * The '<em><b>SOUTH</b></em>' literal value. <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SOUTH</b></em>' literal object isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @see #SOUTH
   * @model
   * @generated
   * @ordered
   */
  public static final int SOUTH_VALUE = 5;

  /**
   * The '<em><b>EAST</b></em>' literal value. <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>EAST</b></em>' literal object isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @see #EAST
   * @model
   * @generated
   * @ordered
   */
  public static final int EAST_VALUE = 3;

  /**
   * The '<em><b>WEST</b></em>' literal value. <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>WEST</b></em>' literal object isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @see #WEST
   * @model
   * @generated
   * @ordered
   */
  public static final int WEST_VALUE = 7;

  /**
   * The '<em><b>DEFAULT</b></em>' literal value. <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>DEFAULT</b></em>' literal object isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @see #DEFAULT
   * @model
   * @generated
   * @ordered
   */
  public static final int DEFAULT_VALUE = 0;

  /**
   * An array of all the '<em><b>Direction</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private static final Direction[] VALUES_ARRAY = new Direction[] { NORTH, SOUTH, EAST, WEST, DEFAULT, };

  /**
   * A public read-only list of all the '<em><b>Direction</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Direction</b></em>' literal with the specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param literal
   *          the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static Direction get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      Direction result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Direction</b></em>' literal with the specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param name
   *          the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static Direction getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      Direction result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Direction</b></em>' literal with the specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value
   *          the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static Direction get(int value) {
    switch (value) {
    case NORTH_VALUE:
      return NORTH;
    case SOUTH_VALUE:
      return SOUTH;
    case EAST_VALUE:
      return EAST;
    case WEST_VALUE:
      return WEST;
    case DEFAULT_VALUE:
      return DEFAULT;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private Direction(int value, String name, String literal) {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public int getValue() {
    return value;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String getName() {
    return name;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String getLiteral() {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String toString() {
    return literal;
  }

} // Direction
