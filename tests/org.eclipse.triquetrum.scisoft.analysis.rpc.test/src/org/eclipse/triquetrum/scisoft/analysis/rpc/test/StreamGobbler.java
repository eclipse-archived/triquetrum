/*******************************************************************************
 * Copyright (c) 2016 Kichwa Coders
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jonah Graham (Kichwa Coders) - initial API and implementation
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
