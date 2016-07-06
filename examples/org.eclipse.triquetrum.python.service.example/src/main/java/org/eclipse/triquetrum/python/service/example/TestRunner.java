/*******************************************************************************
 * Copyright (c) 2014-2016 Diamond Light Source Ltd.,
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
package org.eclipse.triquetrum.python.service.example;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.eclipse.triquetrum.python.service.PythonService;

/**
 *
 * Example to demonstrate the invocation of a python script, with optional parameter specifications.
 * <p>
 * To try this, run all org.eclipse.triquetrum... bundles in an Equinox + console and use the contributed "runScript" command from the osgi console.
 * </p>
 * <p>
 * Sample launcher is included to show the system property that must be used to point to the location of the rpc "system" scripts, e.g. :<br/>
 * <em>-Dorg.passerelle.python.scripts.system=${workspace_loc}/org.eclipse.triquetrum.python.service/scripts</em>
 * </p>
 *
 */
public class TestRunner implements CommandProvider {

  private static final String PLUGIN_ID = "org.eclipse.triquetrum.python.service.example";
  private static final String OVERRIDE_LOCATION_PROPERTY = PLUGIN_ID + ".scripts.user";

  public String getHelp() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("\n---Python RPC test---\n");
    buffer.append("\trunScript <script filename> [param1=val1] [param2=val2] ...\n");
    buffer.append("\t E.g. : ");
    buffer.append("\t osgi> runScript helloworld input1=hi input2=ho\n");
    buffer.append("\t the script filename should point to a file in the user scripts folder,\n");
    buffer.append("\t whose location is in the org.eclipse.triquetrum.python.service.example\n");
    buffer.append("\t plug-in in the scripts directory. The location can be overridden by setting\n");
    buffer.append("\t system property " + OVERRIDE_LOCATION_PROPERTY + "\n");
    return buffer.toString();
  }

  public void _runScript(CommandInterpreter ci) {
    try {
      String script = ci.nextArgument();
      script = getExampleScriptsHome() + File.separator + script;
      if (!script.endsWith(".py")) {
        script += ".py";
      }
      PythonService service = PythonService.openConnection("python");
      Map<String, Serializable> data = new HashMap<String, Serializable>();
      String param = null;
      while ((param = ci.nextArgument()) != null) {
        String[] parts = param.split("=");
        if (parts.length == 2) {
          data.put(parts[0].trim(), parts[1].trim());
        } else {
          System.err.println("Invalid param=value format for " + param);
          System.err.println(getHelp());
        }
      }
      final Map<String, ? extends Object> result = service.runScript(script, data);
      System.out.println(result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String getExampleScriptsHome() throws IOException, URISyntaxException {
    String location = System.getProperty(OVERRIDE_LOCATION_PROPERTY);
    if (location != null) {
      return location;
    }

    URL url = new URL("platform:/plugin/" + PLUGIN_ID + "/scripts");
    URL fileURL = FileLocator.toFileURL(url);
    File exampleScriptsHome = new File(URIUtil.toURI(fileURL));
    if (!exampleScriptsHome.exists()) {
      throw new IOException("Failed to find " + PLUGIN_ID + "/scripts, expected it here: " + exampleScriptsHome);
    }
    return exampleScriptsHome.toString();
  }

}
