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

package org.eclipse.triquetrum.scisoft.analysis.rpc;

/**
 * Exceptions generated from Remote side are stored in this class. It allows some extra convenience on formatting exception messages. In particular if the
 * remote side supports text snippets for each line of stack trace they can be used.
 */
public class AnalysisRpcRemoteException extends AnalysisRpcException {
  private static final long serialVersionUID = -7729269172566951025L;
  private String[] texts;

  public AnalysisRpcRemoteException() {
    super();
  }

  public AnalysisRpcRemoteException(String s) {
    super(s);
  }

  public AnalysisRpcRemoteException(String message, Throwable cause) {
    super(message, cause);
  }

  public AnalysisRpcRemoteException(Throwable cause) {
    super(cause);
  }

  /**
   * Set a list of text fragments corresponding to the stack trace of the exception. The stack trace and the texts should have the same number of elements.
   *
   * @param texts
   *          The texts. The array is copied.
   */
  public void setStackTraceTexts(String[] texts) {
    if (texts == null) {
      this.texts = null;
    } else {
      this.texts = texts.clone();
    }
  }

  /**
   * Retrieve a copy of the array of texts corresponding to the stack trace.
   *
   * @return array of texts or <code>null</code> if there are no texts associated or the associated texts are mismatched to the stack trace.
   */
  public String[] getStackTraceTexts() {
    if (texts == null)
      return null;
    if (texts.length != getStackTrace().length)
      return null;
    return texts.clone();
  }

  /**
   * Return the stack trace formatted in the standard Python way. The aim is to replicate what traceback.format_exception() does.
   * <p>
   * Use this method to generate prettier error messages when the remote is Python. If {@link #getStackTraceTexts()} returns the same number of elements as
   * {@link #getStackTrace()} then the text snippets will be included in the trace.
   * <p>
   * The stack trace is formatted in Python convention which is newest frames last. In addition, this formatting matches the console line tracker that PyDev
   * uses to make links back to Python files.
   *
   * @param limitStackTraceFileName
   *          If non-<code>null</code> will be used to limit the stack trace to frames called after code in this function. The parameter is checked against the
   *          end of the filename in the stack trace element {@link StackTraceElement#getFileName()}. If no frames or the last frame match then all the frames
   *          are included.
   * @return representation of error
   */
  public String getPythonFormattedStackTrace(String limitStackTraceFileName) {
    StringBuilder msg = new StringBuilder("Traceback (most recent call last):\n");

    // get and normalise stack traces
    StackTraceElement[] trace = getStackTrace();
    String[] texts = getStackTraceTexts();
    if (texts == null || texts.length != trace.length) {
      texts = null;
    }

    // search for starting point
    int start = trace.length - 1;
    if (limitStackTraceFileName != null) {
      for (int i = 0; i < trace.length; i++) {
        StackTraceElement elem = trace[i];
        if (elem.getFileName().endsWith(limitStackTraceFileName)) {
          start = i - 1;
        }
      }
      if (start < 0) {
        start = trace.length - 1;
      }
    }

    // format individual lines
    for (int i = start; i >= 0; i--) {
      StackTraceElement elem = trace[i];
      msg.append(String.format("  File \"%s\", line %d, in %s\n", elem.getFileName(), elem.getLineNumber(), elem.getMethodName()));
      if (texts != null) {
        String text = texts[i].trim();
        if (text.length() > 0) {
          msg.append("    ");
          msg.append(text);
          msg.append("\n");
        }
      }
    }

    // add in the exception type and message
    msg.append(getMessage());
    msg.append("\n");
    return msg.toString();
  }
}
