/*******************************************************************************
 * Copyright (c) 2012-2016 Diamond Light Source Ltd., 
 *                         Kichwa Coders & iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    DLS, Kichwa Coders - initial API and implementation and/or initial documentation
 *    Erwin De Ley - extraction from DAWN to ease reuse in other contexts
 *******************************************************************************/

package org.eclipse.triquetrum.scisoft.analysis.rpc.flattening;

import java.util.List;
import java.util.Map;

/**
 * Implementations can "flatten" or "unflatten" instances of objects. Flattening, allowing the object to be transmitted using a small set of more basic types.
 * Unflattening reconstructs the original object. A particular flattener may be capable of flattening/unflattening many types of object.
 * <p>
 * The flattened form must be suitable for passing over XML-RPC with no extensions on. This means the flattened form must follow the rules as specified in
 * {@link FlattenedFormChecker#legal(Object)}
 * <p>
 * When writing a new implementation of IFlattener, it may be worth referring to some examples provided as Unit Tests:
 * <ul>
 * <li>{@link AddHelperSimpleFlatteningTest} - Flattener for a simple class.
 * <li>{@link AddHelperSpecializedMapFlatteningTest} - Flattener for a class that is a specialisation of a class that flattening is already supported for, in
 * this example a specialised {@link Map}.
 * <li>{@link AddHelperSimpleWithInterfaceFlatteningTest} - Flattener for a simple class. Demonstrates use of {@link IFlattens}.
 * </ul>
 * 
 * @param <T>
 */
public interface IFlattener<T> {

  /**
   * Common name for all objects that serialize as a Map for the key name specifying the type of the object. Normally the value associated is the canonical name
   * from Java, but it can be any unique string.
   * 
   * @see Class#getCanonicalName()
   */
  public static final String TYPE_KEY = "__type__";

  /**
   * Flattens the given object. Will only be called if canFlatten(obj) is true.
   * <p>
   * The normal thing to do would be to represent the object as a dictionary ({@link Map}) with the special key __type__ ({@link IFlattener#TYPE_KEY}) used to
   * identify the type of the object so that the unflattener can identify and unflatten it. By convention, the __type__ key is the qualified Java type name.
   * 
   * @param obj
   *          object to flatten
   * @param rootFlattener
   *          instance of the IRootFlattener to use to flatten any contained objects
   * @return the flattened form of obj. Returned value must be {@link FlattenedFormChecker#legal(Object)}
   */
  public Object flatten(Object obj, IRootFlattener rootFlattener);

  /**
   * Takes a flattened object and reconstructs the original object. Will only be called if canUnFlatten(obj) is true
   * 
   * @param obj
   *          which is of flattened form and which is {@link FlattenedFormChecker#legal(Object)}
   * @param rootFlattener
   *          instance of the IRootFlattener to use to unflatten any contained objects
   * @return the unflattened version of obj
   */
  public Object unflatten(Object obj, IRootFlattener rootFlattener);

  /**
   * Tests whether an object can be flattened by this IFlattener
   * 
   * @param obj
   *          object to test
   * @return true if obj can be flattened
   */
  public boolean canFlatten(Object obj);

  /**
   * Tests whether an object can be unflattened by this IFlattener
   * 
   * @param obj
   *          which is of flattened form and which is {@link FlattenedFormChecker#legal(Object)}
   * @return true if obj can be unflattened
   */
  public boolean canUnFlatten(Object obj);

  /**
   * Specification of legal form for flattened objects. flatten must return an object which will return true when passed to {@link #legal(Object)}.
   * <p>
   * When a flattened form object is passed to {@link IFlattener#canUnFlatten(Object)} or {@link IFlattener#canUnFlatten(Object)} the guarantee is that the Key
   * is of type {@link String}. Therefore it is safe to add @SuppressWarnings("unchecked") in a case like this:
   * 
   * <pre>
   * public boolean canUnFlatten(Object obj) {
   *   if (obj instanceof Map) {
   *      &#64;SuppressWarnings("unchecked")
   *      Map&lt;String, Object&gt; map = (Map&lt;String, Object&gt;) obj;
   *      // ...
   * 
   * </pre>
   */
  public static class FlattenedFormChecker {
    /**
     * Make sure the flattened object contains only legal types for XML RPC transmission.
     * <p>
     * 
     * @param flat
     *          the flattened object
     * @return true if the flat object is legal
     */
    public boolean legal(Object flat) {
      if (flat instanceof String)
        return true;
      if (flat instanceof Integer)
        return true;
      if (flat instanceof Double)
        return true;
      if (flat instanceof Boolean)
        return true;
      if (flat instanceof byte[])
        return true;
      if (flat instanceof Object[]) {
        Object[] flatArray = (Object[]) flat;
        for (Object elem : flatArray) {
          if (!legal(elem))
            return false;
        }
        return true;
      }
      if (flat instanceof List) {
        List<?> flatList = (List<?>) flat;
        for (Object elem : flatList) {
          if (!legal(elem))
            return false;
        }
        return true;
      }
      if (flat instanceof Map) {
        Map<?, ?> flatMap = (Map<?, ?>) flat;
        for (Object elem : flatMap.keySet()) {
          if (!(elem instanceof String))
            return false;
        }
        for (Object elem : flatMap.values()) {
          if (!legal(elem))
            return false;
        }
        return true;
      }

      return false;
    }

  }

}
