/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia S.L.
 * This code was donated by Jan Vermeulen and the Vilassar office of iSencia Spain.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jan Vermeulen
 *******************************************************************************/
package org.eclipse.triquetrum;

import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * <code>Enumerated</code> is the extendable implementation of the java enum. It has the same interface of the java <code>enum</code> type, but it allows
 * inheritance and dynamic addition of enum values.
 *
 * @param <T>
 *          the enumType of an enum. This should always be the class that directly extends <code>Enumerated</code>.
 */
@SuppressWarnings("unchecked")
public abstract class Enumerated<T extends Enumerated<?>> implements Comparable<T>, Serializable, Cloneable {
  private static final long serialVersionUID = 1L;

  private static final Map<Class<? extends Enumerated<?>>, SortedSet<? extends Enumerated<?>>> enumerateds = new HashMap<>();
  private static final Set<Class<? extends Enumerated<?>>> initialized = new HashSet<>();
  private static int AUTOINCREMENT = Integer.MAX_VALUE;

  public static final Class<? extends Enumerated<?>> getEnumType(Class<? extends Enumerated<?>> clazz) {
    while (clazz.getSuperclass() != Enumerated.class) {
      clazz = (Class<? extends Enumerated<?>>) clazz.getSuperclass();
    }
    return (clazz);
  }

  protected static final void initialize(Class<? extends Enumerated<?>> clazz) {
    if (initialized.contains(clazz))
      return;

    try {
      Class.forName(clazz.getName(), true, clazz.getClassLoader());
    } catch (ClassNotFoundException e) {
      // should not occur
    }

    initialized.add(clazz);
  }

  protected static final void clear(Class<? extends Enumerated<?>> enumType) {
    if (enumType == null)
      throw new NullPointerException("EnumType is null");

    enumType = getEnumType(enumType);
    SortedSet<? extends Enumerated<?>> values = enumerateds.get(enumType);
    if (values != null)
      values.clear();
  }

  public static final void setInitialized(Class<? extends Enumerated<?>> clazz) {
    initialized.add(clazz);
  }

  /**
   * Returns the enum constant of the specified enum type with the specified name. The name must match exactly an identifier used to declare an enum constant in
   * this type. (Extraneous whitespace characters are not permitted.)
   *
   * @param enumType
   *          the <tt>Class</tt> object of the enum type from which to return a constant
   * @param name
   *          the name of the constant to return
   * @return the enum constant of the specified enum type with the specified name
   * @throws IllegalArgumentException
   *           if the specified enum type has no value with the specified name, or the specified enum type has no values
   */
  public static <T extends Enumerated<?>> T valueOf(Class<? extends Enumerated<?>> enumType, String name) {
    if (enumType == null)
      throw new NullPointerException("EnumType is null");
    if (name == null)
      throw new NullPointerException("Name is null");

    Set<T> values = (Set<T>) values(enumType);

    for (T value : values) {
      if (!enumType.isInstance(value))
        continue;
      if (value.name().equals(name))
        return (value);
    }

    throw new IllegalArgumentException("No value with name " + name + " for " + enumType.getName());
  }

  public static <T extends Enumerated<?>> T valueOf(Class<T> enumType, int ordinal) {
    if (enumType == null)
      throw new NullPointerException("EnumType is null");

    Set<T> values = values(enumType);

    for (T value : values) {
      if (!enumType.isInstance(value))
        continue;
      int order = value.ordinal() - ordinal;
      if (order == 0)
        return (value);
      if (order > 0)
        break;
    }

    throw new IllegalArgumentException("No value with ordinal " + ordinal + " for " + enumType.getName());
  }

  /**
   * Returns all values defined for the given enum type.
   *
   * @param enumType
   * @return
   */
  public static <T extends Enumerated<?>> SortedSet<T> values(Class<T> enumType) {
    if (enumType == null)
      throw new NullPointerException("EnumType is null");

    Class<? extends Enumerated<?>> rootType = getEnumType(enumType);

    // assure enumTypes have been initialized
    initialize(rootType);
    initialize(enumType);

    SortedSet<Enumerated<?>> values = (SortedSet<Enumerated<?>>) enumerateds.get(rootType);

    if (values == null)
      return (new TreeSet<>());

    if (enumType == rootType)
      return ((SortedSet<T>) values);

    TreeSet<T> selectedValues = new TreeSet<>();
    for (Enumerated<?> enumerated : values)
      if (enumType.isInstance(enumerated))
        selectedValues.add((T) enumerated);
    return (selectedValues);
  }

  /**
   * The name of this enum constant, as declared in the enum declaration. Most programmers should use the {@link #toString} method rather than accessing this
   * field.
   */
  private final String name;

  /**
   * The ordinal of this enumeration constant (its position in the enum declaration, where the initial constant is assigned an ordinal of zero). Most
   * programmers will have no use for this field. It is designed for use by sophisticated enum-based data structures, such as {@link java.util.EnumSet} and
   * {@link java.util.EnumMap}.
   */
  private final int ordinal;

  protected Enumerated(String name) {
    this(name, AUTOINCREMENT);
  }

  /**
   * @param name
   *          - The name of this enum constant, which is the identifier used to declare it. Must be non-null and non-blank.
   * @param ordinal
   *          - The ordinal of this enumeration constant (its position in the enum declaration, where the initial constant is assigned an ordinal of zero).
   */
  protected Enumerated(String name, int ordinal) {
    if (name == null || name.trim().length() == 0)
      throw new IllegalArgumentException("name can't be empty");

    this.name = name;

    // check the existing values
    Class<? extends Enumerated<?>> enumType = getEnumType();
    SortedSet<T> values = (SortedSet<T>) enumerateds.get(enumType);
    if (values == null) {
      enumerateds.put(enumType, values = new TreeSet<>());
      this.ordinal = (ordinal == AUTOINCREMENT) ? (0) : (ordinal);
    } else {
      T value = null;
      // prevent duplicate names
      for (Iterator<T> i = values.iterator(); i.hasNext();) {
        value = i.next();
        if (name.equals(value.name()))
          throw new IllegalArgumentException("Duplicate name " + name + " for " + enumType.getName());
      }
      // determine the ordinal
      this.ordinal = (ordinal == AUTOINCREMENT) ? ((value != null) ? (value.ordinal() + 1) : (0)) : (ordinal);
    }

    // add the value to the existing values
    if (!values.add((T) this))
      throw new IllegalArgumentException("Duplicate ordinal " + ordinal + " for " + enumType.getName());
  }

  /**
   * returns the same instance. This guarantees that enumerateds are never cloned, which is necessary to preserve their "singleton" status.
   *
   * @return T the same instance
   */
  @Override
  protected final T clone() {
    return ((T) this);
  }

  /**
   * Compares this enum with the specified object for order. Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or
   * greater than the specified object. Enumerated constants are only comparable to other enum constants of the same enum type. The natural order implemented by
   * this method is the order in which the constants are declared.
   */
  @Override
  public final int compareTo(T object) {
    if (!getEnumType().isInstance(object))
      throw new ClassCastException();

    return (ordinal - ((Enumerated<?>) object).ordinal);
  }

  /**
   * Returns true if the specified object is equal to this enum constant.
   *
   * @param other
   *          the object to be compared for equality with this object.
   * @return true if the specified object is equal to this enum constant.
   */
  @Override
  public final boolean equals(Object object) {
    return (this == object);
  }

  public final Class<? extends Enumerated<?>> getEnumType() {
    return (getEnumType((Class<? extends Enumerated<?>>) getClass()));
  }

  /**
   * Returns a hash code for this enum constant.
   *
   * @return a hash code for this enum constant.
   */
  @Override
  public final int hashCode() {
    return (System.identityHashCode(this));
  }

  /**
   * Returns the name of this enum constant, exactly as declared in its enum declaration. <b>Most programmers should use the {@link #toString} method in
   * preference to this one, as the toString method may return a more user-friendly name.</b> This method is designed primarily for use in specialized
   * situations where correctness depends on getting the exact name, which will not vary from release to release.
   *
   * @return the name of this enum constant
   */
  public final String name() {
    return (name);
  }

  /**
   * Returns the ordinal of this enumeration constant (its position in its enum declaration, where the initial constant is assigned an ordinal of zero). Most
   * programmers will have no use for this method. It is designed for use by sophisticated enum-based data structures, such as {@link java.util.EnumSet} and
   * {@link java.util.EnumMap}.
   *
   * @return the ordinal of this enumeration constant
   */
  public final int ordinal() {
    return (ordinal);
  }

  protected Object readResolve() throws ObjectStreamException {
    Class<? extends Enumerated<?>> enumType = getEnumType();
    SortedSet<T> values = (SortedSet<T>) enumerateds.get(enumType);
    if (values == null)
      throw new InvalidObjectException("No values available for " + enumType.getName());

    for (T value : values) {
      int order = value.ordinal() - ordinal;
      if (order == 0)
        return (value);
      if (order > 0)
        break;
    }

    throw new InvalidObjectException("No value with name " + name + " for " + enumType.getName());
  }

  /**
   * Returns the name of this enum constant, as contained in the declaration. This method may be overridden, though it typically isn't necessary or desirable.
   * An enum type should override this method when a more "programmer-friendly" string form exists.
   *
   * @return the name of this enum constant
   */
  @Override
  public String toString() {
    return (name);
  }
}
