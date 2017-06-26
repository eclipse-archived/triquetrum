/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum;

import java.util.SortedSet;

/**
 * An extensible enumerator of hierarchical error categories.
 *
 */
public class ErrorCategory extends Enumerated<ErrorCode> {

  private static final long serialVersionUID = 1L;

  private static final char PREFIX_SEPARATOR = '_';

  public static final ErrorCategory ROOTCATEGORY = new ErrorCategory("TRIQ_ROOTCATEGORY", null, "TRIQ");
  public static final ErrorCategory FUNCTIONAL = new ErrorCategory("FUNCTIONAL", ROOTCATEGORY, "FUNC");
  public static final ErrorCategory TECHNICAL = new ErrorCategory("TECHNICAL", ROOTCATEGORY, "TECH");

  /**
   * the (optional) parent category
   */
  private ErrorCategory parent;
  /**
   * a short category identifier that will be used to generate prefixes for error codes
   */
  private String prefix;
  /**
   * the complete parent-first chain of prefixes based on the category hierarchy
   */
  private String prefixChain;

  /**
   *
   * @param name
   *          should be the same as the constant identifier
   * @param parent
   *          the (optional) parent category
   * @param prefix
   *          a short category identifier that will be used to generate prefixes for error codes
   */
  public ErrorCategory(String name, ErrorCategory parent, String prefix) {
    super(name);
    this.parent = parent;
    this.prefix = prefix;
    this.prefixChain = _getPrefixChain();
  }

  private String _getPrefixChain() {
    StringBuilder pB = new StringBuilder(prefix);
    ErrorCategory parent = this.parent;
    while (parent != null) {
      pB.insert(0, parent.prefix + PREFIX_SEPARATOR);
      parent = parent.getParent();
    }
    return pB.toString();
  }

  /**
   *
   * @return the (optional) parent category; null if no parent
   */
  public ErrorCategory getParent() {
    return parent;
  }

  /**
   *
   * @return the short prefix of this category
   */
  public String getPrefix() {
    return prefix;
  }

  /**
   *
   * @return the complete parent-first chain of prefixes based on the category hierarchy
   */
  public String getPrefixChain() {
    return prefixChain;
  }

  public static ErrorCategory valueOf(String name) {
    return (Enumerated.valueOf(ErrorCategory.class, name));
  }

  public static ErrorCategory valueOf(int ordinal) {
    return (Enumerated.valueOf(ErrorCategory.class, ordinal));
  }

  public static SortedSet<ErrorCategory> values() {
    return (Enumerated.values(ErrorCategory.class));
  }
}
