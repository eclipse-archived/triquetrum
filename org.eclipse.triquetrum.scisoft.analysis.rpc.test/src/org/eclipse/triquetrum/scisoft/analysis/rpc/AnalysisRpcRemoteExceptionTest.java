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

import org.eclipse.triquetrum.scisoft.analysis.rpc.AnalysisRpcRemoteException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class AnalysisRpcRemoteExceptionTest {
  @Rule
  public Timeout globalTimeout = Timeout.seconds(2);

  @Test
  public void testPreamble() {
    AnalysisRpcRemoteException e = new AnalysisRpcRemoteException("Exception: MyMessage");
    String formatted = e.getPythonFormattedStackTrace(null);
    Assert.assertTrue(formatted.startsWith("Traceback (most recent call last):\n"));
  }

  @Test
  public void testMessageLine() {
    AnalysisRpcRemoteException e = new AnalysisRpcRemoteException("Exception: MyMessage");
    String formatted = e.getPythonFormattedStackTrace(null);
    Assert.assertTrue(formatted.endsWith("\nException: MyMessage\n"));
  }

  @Test
  public void testStackTraceNoTexts() {
    AnalysisRpcRemoteException e = new AnalysisRpcRemoteException("Exception: MyMessage");
    String formatted = e.getPythonFormattedStackTrace(null);
    String[] lines = formatted.split("\n");
    Assert.assertTrue(lines[lines.length - 2].contains(AnalysisRpcRemoteExceptionTest.class.getSimpleName()));
  }

  private String[] formatException(String filter) {
    // Create an exception as if it arrived from Python unflattening
    AnalysisRpcRemoteException e = new AnalysisRpcRemoteException("Exception: MyMessage");
    StackTraceElement[] elems = new StackTraceElement[] { new StackTraceElement("class1", "meth1", "file1", 1),
        new StackTraceElement("class2", "meth2", "file2", 2), new StackTraceElement("class3", "meth3", "file3", 3) };
    String[] texts = new String[] { "text1", "text2", "text3" };
    e.setStackTrace(elems);
    e.setStackTraceTexts(texts);

    String formatted = e.getPythonFormattedStackTrace(filter);
    String[] lines = formatted.split("\n");
    return lines;
  }

  private void verifyException(String[] lines) {
    Assert.assertEquals("Traceback (most recent call last):", lines[0]);
    Assert.assertEquals("  File \"file3\", line 3, in meth3", lines[1]);
    Assert.assertEquals("    text3", lines[2]);
    Assert.assertEquals("  File \"file2\", line 2, in meth2", lines[3]);
    Assert.assertEquals("    text2", lines[4]);
    Assert.assertEquals("  File \"file1\", line 1, in meth1", lines[5]);
    Assert.assertEquals("    text1", lines[6]);
    Assert.assertEquals("Exception: MyMessage", lines[7]);
    Assert.assertEquals(8, lines.length);
  }

  @Test
  public void testNormal() {
    verifyException(formatException(null));
  }

  @Test
  public void testFiltered() {
    String[] lines = formatException("file2");

    Assert.assertEquals("Traceback (most recent call last):", lines[0]);
    Assert.assertEquals("  File \"file1\", line 1, in meth1", lines[1]);
    Assert.assertEquals("    text1", lines[2]);
    Assert.assertEquals("Exception: MyMessage", lines[3]);
    Assert.assertEquals(4, lines.length);
  }

  @Test
  public void testFilteredPartial() {
    String[] lines = formatException("le2");

    Assert.assertEquals("Traceback (most recent call last):", lines[0]);
    Assert.assertEquals("  File \"file1\", line 1, in meth1", lines[1]);
    Assert.assertEquals("    text1", lines[2]);
    Assert.assertEquals("Exception: MyMessage", lines[3]);
    Assert.assertEquals(4, lines.length);
  }

  @Test
  public void testFilteredNoMatch() {
    // If nothing matches, no filtering should be applied
    String[] lines = formatException("otherfile");
    verifyException(lines);
  }

  @Test
  public void testFilteredMatchesLast() {
    // If the last one matches, no filtering should be applied
    String[] lines = formatException("file1");
    verifyException(lines);
  }

  @Test
  public void testNoTexts() {
    AnalysisRpcRemoteException e = new AnalysisRpcRemoteException("Exception: MyMessage");
    StackTraceElement[] elems = new StackTraceElement[] { new StackTraceElement("class1", "meth1", "file1", 1),
        new StackTraceElement("class2", "meth2", "file2", 2), new StackTraceElement("class3", "meth3", "file3", 3) };
    e.setStackTrace(elems);

    String formatted = e.getPythonFormattedStackTrace(null);
    String[] lines = formatted.split("\n");

    Assert.assertEquals("Traceback (most recent call last):", lines[0]);
    Assert.assertEquals("  File \"file3\", line 3, in meth3", lines[1]);
    Assert.assertEquals("  File \"file2\", line 2, in meth2", lines[2]);
    Assert.assertEquals("  File \"file1\", line 1, in meth1", lines[3]);
    Assert.assertEquals("Exception: MyMessage", lines[4]);
    Assert.assertEquals(5, lines.length);
  }

  @Test
  public void testInvalidTextsTooShort() {
    // Make sure texts array that does not match is ignored
    AnalysisRpcRemoteException e = new AnalysisRpcRemoteException("Exception: MyMessage");
    StackTraceElement[] elems = new StackTraceElement[] { new StackTraceElement("class1", "meth1", "file1", 1),
        new StackTraceElement("class2", "meth2", "file2", 2), new StackTraceElement("class3", "meth3", "file3", 3) };
    // texts length != elems length
    String[] texts = new String[] { "text1" };
    e.setStackTrace(elems);
    e.setStackTraceTexts(texts);

    String formatted = e.getPythonFormattedStackTrace(null);
    String[] lines = formatted.split("\n");

    Assert.assertEquals("Traceback (most recent call last):", lines[0]);
    Assert.assertEquals("  File \"file3\", line 3, in meth3", lines[1]);
    Assert.assertEquals("  File \"file2\", line 2, in meth2", lines[2]);
    Assert.assertEquals("  File \"file1\", line 1, in meth1", lines[3]);
    Assert.assertEquals("Exception: MyMessage", lines[4]);
    Assert.assertEquals(5, lines.length);
  }

  @Test
  public void testInvalidTextsTooLong() {
    // Make sure texts array that does not match is ignored
    AnalysisRpcRemoteException e = new AnalysisRpcRemoteException("Exception: MyMessage");
    StackTraceElement[] elems = new StackTraceElement[] { new StackTraceElement("class1", "meth1", "file1", 1),
        new StackTraceElement("class2", "meth2", "file2", 2), new StackTraceElement("class3", "meth3", "file3", 3) };
    // texts length != elems length
    String[] texts = new String[] { "text1", "text2", "text3", "text4" };
    e.setStackTrace(elems);
    e.setStackTraceTexts(texts);

    String formatted = e.getPythonFormattedStackTrace(null);
    String[] lines = formatted.split("\n");

    Assert.assertEquals("Traceback (most recent call last):", lines[0]);
    Assert.assertEquals("  File \"file3\", line 3, in meth3", lines[1]);
    Assert.assertEquals("  File \"file2\", line 2, in meth2", lines[2]);
    Assert.assertEquals("  File \"file1\", line 1, in meth1", lines[3]);
    Assert.assertEquals("Exception: MyMessage", lines[4]);
    Assert.assertEquals(5, lines.length);
  }

}
