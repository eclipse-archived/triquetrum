/*******************************************************************************
 *  Copyright (c) 2012-2016 Diamond Light Source Ltd.,
 *                           Kichwa Coders & iSencia Belgium NV.
 *                           
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0.
 *  
 *  SPDX-License-Identifier: EPL-2.0
 *  
 *  Contributors:
 *      DLS, Kichwa Coders - initial API and implementation and/or initial documentation
 *      Erwin De Ley - extraction from DAWN to ease reuse in other contexts
 *******************************************************************************/
package org.eclipse.triquetrum.scisoft.analysis.rpc.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

public class StreamGobbler extends Thread {

  private static AtomicInteger count = new AtomicInteger();
  private final InputStream inputStream;
  private final StringBuilder contents = new StringBuilder();

  public StreamGobbler(InputStream inputSteam) {
    super("ThreadStreamReader: " + count.incrementAndGet());
    this.inputStream = inputSteam;
  }

  @Override
  public void run() {
    InputStreamReader in = new InputStreamReader(inputStream);
    char[] buf = new char[512];
    int count;
    try {
      while ((count = in.read(buf)) != -1) {
        contents.append(buf, 0, count);
      }
    } catch (IOException e) {
    }
  }

  public String getContents() {
    return contents.toString();
  }
}
