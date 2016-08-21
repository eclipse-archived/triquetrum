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
package org.eclipse.triquetrum.python.actor.activator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.triquetrum.python.service.PythonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.actor.TypedAtomicActor;
import ptolemy.actor.TypedIOPort;
import ptolemy.data.ObjectToken;
import ptolemy.data.RecordToken;
import ptolemy.data.Token;
import ptolemy.data.expr.ConversionUtilities;
import ptolemy.data.expr.FileParameter;
import ptolemy.data.type.BaseType;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;

/**
 * A basic Python actor, binding input and output variables from a python script to the actor's input&output ports.
 * <p>
 * To use this actor you need to set one or more input ports, e.g. 2 ports "input1,input2" and output ports, e.g. "output1,output2". The script can then refer
 * to the input variables and can create result/output variables with the corresponding port names. The actor will send the individual produced outputs on the
 * respectively named ports. The complete result map wil be sent out on the actor's default output port as well, as a RecordToken. <br/>
 * Below is a simple example that would work for the above ports configuration :<br/>
 *
 * <pre>
 * def run(input1, input2, **kwargs):
 *     print "hello world : %s and %s" % (input1 , input2)
 *     return {"output1": "isn't this nice", "output2": "imagine this"}
 * </pre>
 * </p>
 * <p>
 * The actor uses the {@link PythonService} to run the script on a separate "true" Python interpreter process. I.e. no Jython or so.<br/>
 * A new Python process is launched each time, for each individual script execution.
 * </p>
 */
public class PythonActor extends TypedAtomicActor {
  private final static Logger LOGGER = LoggerFactory.getLogger(PythonActor.class);
  private final static String PYTHONINTERPRETER_DEFAULT = "python";

  public TypedIOPort output;

  public FileParameter scriptPathParameter;
  public FileParameter pythonInterpreterParam;

  public PythonActor(CompositeEntity container, String name) throws IllegalActionException, NameDuplicationException {
    super(container, name);
    output = new TypedIOPort(this, "output", false, true);
    output.setTypeEquals(BaseType.RECORD);

    scriptPathParameter = new FileParameter(this, "Script path");
    pythonInterpreterParam = new FileParameter(this, "Python path");
    pythonInterpreterParam.setExpression(PYTHONINTERPRETER_DEFAULT);
  }

  /**
   * check that all connected ports have a token available
   */
  @Override
  public boolean prefire() throws IllegalActionException {
    boolean result = super.prefire();
    if (result) {
      for (TypedIOPort p : inputPortList()) {
        result &= (p.getWidth() > 0 && p.hasToken(0));
        if (!result) {
          break;
        }
      }
    }
    return result;
  }

  @Override
  public void fire() throws IllegalActionException {
    super.fire();
    Map<String, Token> inputs = new HashMap<String, Token>();
    for (TypedIOPort p : inputPortList()) {
      inputs.put(p.getName(), p.get(0));
    }

    String scriptPath = scriptPathParameter.asFile().getAbsolutePath();
    try {
      final Map<String, Token> outputs = runScript(scriptPath, inputs);
      output.send(0, new RecordToken(outputs));
      sendMappedOutputs(outputs);
    } catch (Exception e) {
      LOGGER.error("Error running script " + scriptPath, e);
    }
  }

  protected final void sendMappedOutputs(Map<String, Token> outputs) {
    for (String key : outputs.keySet()) {
      TypedIOPort p = (TypedIOPort) getPort(key);
      if (p != null) {
        try {
          p.send(0, outputs.get(key));
        } catch (Exception e) {
          LOGGER.error("Error sending output for result item " + key, e);
        }
      }
    }
  }

  private Map<String, Token> runScript(String scriptPath, Map<String, Token> data) throws Exception {
    PythonService service = null;
    try {
      Map<String, Object> inputs = new HashMap<>();
      for (String key : data.keySet()) {
        inputs.put(key, ConversionUtilities.convertTokenToJavaType(data.get(key)));
      }
      String pythonInterpreter = pythonInterpreterParam.stringValue();
      if (pythonInterpreter == null || pythonInterpreter.trim().length() == 0) {
        pythonInterpreter = PYTHONINTERPRETER_DEFAULT;
      }
      service = PythonService.openConnection(pythonInterpreter);
      Map<String, ? extends Object> results = service.runScript(scriptPath, inputs);
      Map<String, Token> outputs = new HashMap<>();
      for (String key : results.keySet()) {
        Object resultItem = results.get(key);
        try {
          outputs.put(key, ConversionUtilities.convertJavaTypeToToken(resultItem));
        } catch (Exception e) {
          LOGGER.error("Error converting result item " + resultItem, e);
        }
      }
      return outputs;
    } finally {
      if (service != null)
        service.stop();
    }
  }
}
