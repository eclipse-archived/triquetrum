/*******************************************************************************
 * Copyright (c) 2015 iSencia Belgium NV.
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
import java.util.regex.Pattern;

/**
 * ErrorCodes should be used for all Triquetrum-related exceptions. They group info on :
 * <ul>
 * <li>a unique code to identify the type of error, which can be referred to in operational docs etc</li>
 * <li>a severity indicator</li>
 * <li>a topic to which the error is related, which can be used e.g. to publish error info on an event bus</li>
 * </ul>
 */
public class ErrorCode extends Enumerated<ErrorCode> {
  private static final long serialVersionUID = 1L;

  private static final char FORMATTEDCODE_SEPARATOR_CHAR = '-';
  private static final String TOSTRING_SEPARATOR = " - ";
  private static final char TOPIC_SEPARATOR = '/';
  private static final Pattern CODE_FORMAT_PATTERN = Pattern.compile("\\d{4}");

  public static final ErrorCode WARN = new ErrorCode("WARN", "9996", ErrorCategory.FUNCTIONAL, ErrorCode.Severity.WARNING, "");
  public static final ErrorCode INFO = new ErrorCode("INFO", "9997", ErrorCategory.FUNCTIONAL, ErrorCode.Severity.INFO, "");
  public static final ErrorCode ERROR = new ErrorCode("ERROR", "9998", ErrorCategory.TECHNICAL, ErrorCode.Severity.ERROR, "");
  public static final ErrorCode FATAL = new ErrorCode("FATAL", "9999", ErrorCategory.TECHNICAL, ErrorCode.Severity.FATAL, "");

  public static enum Severity {
    INFO, WARNING, ERROR, FATAL;
  }

  private String code;
  private Integer codeAsInteger;
  private ErrorCategory category;
  private Severity severity;
  private String topic;
  private String description;

  private String formattedCode;
  private String formattedString;

  /**
   * ErrorCode constructor with automatically generated topic, based on the category prefix and the severity : PREFIX/SEVERITY.
   *
   * @param name
   * @param code
   * @param category
   * @param severity
   * @param description
   */
  protected ErrorCode(String name, String code, ErrorCategory category, Severity severity, String description) {
    super(name);
    assert CODE_FORMAT_PATTERN.matcher(code).matches();
    this.code = code;
    this.codeAsInteger = Integer.parseInt(code);
    this.category = category;
    this.severity = severity;
    this.description = description;
    this.topic = category.getPrefix() + TOPIC_SEPARATOR + severity + TOPIC_SEPARATOR + name;
  }

  /**
   * @param name
   * @param code
   * @param topic
   * @param category
   * @param severity
   * @param description
   */
  protected ErrorCode(String name, String code, String topic, ErrorCategory category, Severity severity, String description) {
    this(name, code, category, severity, description);
    this.topic = topic;
  }

  public String getCode() {
    return code;
  }

  public int getCodeAsInteger() {
    return codeAsInteger;
  }

  public ErrorCategory getCategory() {
    return category;
  }

  public Severity getSeverity() {
    return severity;
  }

  public String getTopic() {
    return topic;
  }

  public String getDescription() {
    return description;
  }

  public String getFormattedCode() {
    if (formattedCode == null) {
      formattedCode = "[" + getCategory().getPrefixChain() + FORMATTEDCODE_SEPARATOR_CHAR + getCode() + "]";
    }
    return formattedCode;
  }

  public static ErrorCode valueOf(String name) {
    return (Enumerated.valueOf(ErrorCode.class, name));
  }

  public static ErrorCode valueOf(int ordinal) {
    return (Enumerated.valueOf(ErrorCode.class, ordinal));
  }

  public static SortedSet<ErrorCode> values() {
    return (Enumerated.values(ErrorCode.class));
  }

  @Override
  public String toString() {
    if (formattedString == null) {
      formattedString = name() + TOSTRING_SEPARATOR + getSeverity() + TOSTRING_SEPARATOR + getFormattedCode() + TOSTRING_SEPARATOR + getDescription();
    }
    return formattedString;
  }
}
