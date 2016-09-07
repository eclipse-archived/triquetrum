/*******************************************************************************
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.processing.actor.util;

import org.eclipse.triquetrum.processing.model.Attribute;
import org.eclipse.triquetrum.processing.model.Task;
import org.eclipse.triquetrum.processing.model.TriqFactory;
import org.eclipse.triquetrum.processing.model.TriqFactoryTracker;

import ptolemy.data.BooleanToken;
import ptolemy.data.DoubleToken;
import ptolemy.data.FloatToken;
import ptolemy.data.IntToken;
import ptolemy.data.LongToken;
import ptolemy.data.ShortToken;
import ptolemy.data.StringToken;
import ptolemy.data.Token;
import ptolemy.data.UnsignedByteToken;
import ptolemy.data.expr.ConversionUtilities;

public class AttributeFromTokenBuilder {

  /**
   * Supports only a subset of all Ptolemy II Token value types!
   *
   * <pre>
   * Supported Token type
   * --------------------
   * DoubleToken
   * IntToken
   * UnsignedByteToken
   * LongToken
   * StringToken
   * BooleanToken
   * FloatToken
   * ShortToken
   * --------------------
   * </pre>
   * @param t
   * @param name
   * @param token
   * @return
   * @see ConversionUtilities to see the full range of possible value types.
   */
  public static Attribute<?> createAttribute(Task t, String name, Token token) {
    Attribute<?> returnValue = null;
    TriqFactory factory = TriqFactoryTracker.getDefaultFactory();

    if (token instanceof DoubleToken) {
      returnValue = factory.createAttribute(t, name, Double.valueOf(((DoubleToken) token).doubleValue()));
    } else if (token instanceof IntToken) {
      returnValue = factory.createAttribute(t, name, Integer.valueOf(((IntToken) token).intValue()));
    } else if (token instanceof UnsignedByteToken) {
      returnValue = factory.createAttribute(t, name, Byte.valueOf(((UnsignedByteToken) token).byteValue()));
    } else if (token instanceof LongToken) {
      returnValue = factory.createAttribute(t, name, Long.valueOf(((LongToken) token).longValue()));
    } else if (token instanceof StringToken) {
      returnValue = factory.createAttribute(t, name, ((StringToken) token).stringValue());
    } else if (token instanceof BooleanToken) {
      returnValue = factory.createAttribute(t, name, Boolean.valueOf(((BooleanToken) token).booleanValue()));
      // unfortunately these value types are not Serializable...
//    } else if (token instanceof ComplexToken) {
//      returnValue = factory.createAttribute(t, name, ((ComplexToken) token).complexValue());
//    } else if (token instanceof FixToken) {
//      returnValue = factory.createAttribute(t, name, ((FixToken) token).fixValue());
    } else if (token instanceof FloatToken) {
      returnValue = factory.createAttribute(t, name, ((FloatToken) token).floatValue());
    } else if (token instanceof ShortToken) {
      returnValue = factory.createAttribute(t, name, ((ShortToken) token).shortValue());
    }
    // TODO once attributes can store serialized collections/arrays, add that here as well
    return returnValue;
  }
}
