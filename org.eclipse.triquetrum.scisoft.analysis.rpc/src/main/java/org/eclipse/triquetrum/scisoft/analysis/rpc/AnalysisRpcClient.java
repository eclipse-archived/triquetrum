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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.IRootFlattener;
import org.eclipse.triquetrum.scisoft.analysis.rpc.internal.AnalysisRpcTypeFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client class for AnalysisRpc.
 * <p>
 * Generally it is expected that a provider of a service will write a wrapper class that delegates to {@link AnalysisRpcClient} and provides a "nice" interface
 * which is strongly typed.
 * <p>
 * 
 * @see AnalysisRpcBasicTest See the Ananlysis Rpc Basic Test for an example of use
 */
public class AnalysisRpcClient {
  private static final Logger logger = LoggerFactory.getLogger(AnalysisRpcClient.class);

  private XmlRpcClient client;
  private IRootFlattener flattener = FlatteningService.getFlattener();

  private final int port;

  /**
   * Create a new AnalysisRpc client that connects to a server on the given port
   * 
   * @param port
   *          to connect to
   */
  public AnalysisRpcClient(int port) {
    this.port = port;
    try {
      XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
      config.setServerURL(new URL("http://127.0.0.1:" + port + "/RPC2"));
      client = new XmlRpcClient();
      client.setConfig(config);
      client.setTypeFactory(new AnalysisRpcTypeFactoryImpl(client));
    } catch (MalformedURLException e) {
      // This is a programming error
      logger.error("Failed to create AnalysisRPCClient due to MalformedURLException", e);
    }
  }

  private Object request_common(String destination, Object[] args, boolean debug, boolean suspend) throws AnalysisRpcException {
    try {
      if (args == null) {
        // No arguments, convert null to empty array
        args = new Object[0];
      }
      Object[] flatargs = (Object[]) flattener.flatten(args);
      final Object flatret;
      if (debug) {
        flatret = client.execute("Analysis.handler_debug", new Object[] { destination, flatargs, suspend });
      } else {
        flatret = client.execute("Analysis.handler", new Object[] { destination, flatargs });
      }
      Object unflatret = flattener.unflatten(flatret);
      if (unflatret instanceof Exception) {
        throw new AnalysisRpcException((Exception) unflatret);
      }
      return unflatret;
    } catch (XmlRpcException e) {
      throw new AnalysisRpcException(e);
    } catch (UnsupportedOperationException e) {
      throw new AnalysisRpcException(e);
    }
  }

  /**
   * Issue a RPC call by calling request. The call is sent to the server on the registered port to the handler registered with the name passed to destination.
   * <p>
   * All arguments passed the server are "flattened" for transport, and automatically unflattened by the server before delivery to the destination handler. The
   * return value is similarly flattened and unflattened.
   * <p>
   * If the delegated to method throws an exception, it is re-thrown here wrapped as an {@link AnalysisRpcException}. If the delegated to method returns an
   * exception, it will be thrown rather than returned.
   * <p>
   * 
   * @param destination
   *          target handler in server
   * @param args
   *          arguments in the server
   * @return value that the delegated to method returns
   * @throws AnalysisRpcException
   *           under a few conditions, always as a wrapper around the original cause. The original causes can be one of:
   *           <ul>
   *           <li>{@link UnsupportedOperationException} if the arguments or return type failed to be flattened or unflattened.</li>
   *           <li>{@link AnalysisRpcRemoteException} if an exception occurred on the remote side of the call.</li>
   *           <li>{@link XmlRpcException} if the underlying transport had a failure and XML-RPC was used as the transport</li>
   *           </ul>
   */
  public Object request(String destination, Object[] args) throws AnalysisRpcException {
    return request_common(destination, args, false, false);
  }

  /**
   * Issue a RPC call by calling request, entering debug mode if server is available.
   * 
   * @param suspend
   *          suspend on entry to handler
   * @see #request(String, Object[])
   */
  public Object request_debug(String destination, Object[] args, boolean suspend) throws AnalysisRpcException {
    return request_common(destination, args, true, suspend);
  }

  /**
   * Test if the server is up and running.
   * 
   * @warning The AnalysisRpc server may become unreachable between is_alive being called and a request being made.
   * 
   * @return True if communication is working
   */
  public boolean isAlive() {
    try {
      client.execute("Analysis.is_alive", new Object[0]);
      return true;
    } catch (XmlRpcException e) {
      return false;
    }
  }

  /**
   * Set PyDev Debugger settrace options. See pydevd.py for further documentation. Calling this method overrides previous settings and this only has an effect
   * if it is called before any calls to {@link #request_debug(String, Object[], boolean)}
   * 
   * @param options
   *          Key/Value pairs of parameters to settrace to their desired values.
   * @throws AnalysisRpcException
   */
  public void setPyDevSetTraceParams(Map<String, Object> options) throws AnalysisRpcException {
    try {
      client.execute("Analysis.set_pydev_settrace_params", new Object[] { options });
    } catch (XmlRpcException e) {
      throw new AnalysisRpcException("Failed to set_pydev_settrace_params", e);
    }
  }

  /**
   * Convenience method around {@link #setPyDevSetTraceParams(Map)} for the most common situation where only the port needs to be overridden.
   * 
   * @param port
   * @throws AnalysisRpcException
   */
  public void setPyDevSetTracePort(int port) throws AnalysisRpcException {
    Map<String, Object> options = new HashMap<String, Object>();
    options.put("port", port);
    setPyDevSetTraceParams(options);
  }

  /**
   * Poll isAlive until True, raise an exception if isAlive is not true before the timeout.
   * 
   * @throws AnalysisRpcException
   */
  public void waitUntilAlive() throws AnalysisRpcException {
    String timeoutSystemProp = System.getProperty("org.eclipse.triquetrum.scisoft.analysis.xmlrpc.client.timeout", "5000");
    final long ms = Integer.parseInt(timeoutSystemProp);
    waitUntilAlive(ms);
  }

  /**
   * Poll isAlive until True, raise an exception if isAlive is not true before the timeout.
   * 
   * @param milliseconds
   *          timeout time
   * @throws AnalysisRpcException
   */
  public void waitUntilAlive(long milliseconds) throws AnalysisRpcException {
    long stop = System.currentTimeMillis() + milliseconds;
    while (System.currentTimeMillis() < stop) {
      if (isAlive())
        return;
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
      }
    }

    throw new AnalysisRpcException("Timeout waiting for other end to be alive");
  }

  /**
   * Return port number in use
   * 
   * @return port
   */
  public int getPort() {
    return port;
  }

  /**
   * Create a proxy that implements the given interfaces. All methods that are called on the proxy must be declared to throw {@link AnalysisRpcException}.
   * 
   * @param loader
   *          class loader to use. See {@link Proxy#newProxyInstance(ClassLoader, Class[], InvocationHandler)} for details on the class loader.
   * @param interfaces
   *          Interfaces to implement for the proxy
   * @param debug
   *          have calls use invoke_debug when true
   * @return a new instance of an object that implements the interfaces supplied.
   */
  public Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, boolean debug) {
    return Proxy.newProxyInstance(loader, interfaces, new ClientProxy(debug));
  }

  /**
   * Convenience wrapper around {@link #newProxyInstance(ClassLoader, Class[])}
   * 
   * @param interfaces
   *          to implement in proxy. The class loader used is the class loader of the first interface in the list.
   * @param debug
   *          have calls use invoke_debug when true
   */
  public Object newProxyInstance(Class<?>[] interfaces, boolean debug) {
    return newProxyInstance(interfaces[0].getClassLoader(), interfaces, debug);
  }

  /**
   * Convenience wrapper around {@link #newProxyInstance(Class[])}
   * 
   * @param single_interface
   *          Sinlge Interface to make proxy for
   * @param debug
   *          have calls use invoke_debug when true
   */
  public <T> T newProxyInstance(Class<T> single_interface, boolean debug) {
    @SuppressWarnings("unchecked")
    T result = (T) newProxyInstance(new Class<?>[] { single_interface }, debug);
    return result;
  }

  /**
   * Convenience wrapper around {@link #newProxyInstance(Class[])} with no debug
   * 
   * @param single_interface
   *          Sinlge Interface to make proxy for
   */
  public <T> T newProxyInstance(Class<T> single_interface) {
    @SuppressWarnings("unchecked")
    T result = (T) newProxyInstance(new Class<?>[] { single_interface }, false);
    return result;
  }

  private class ClientProxy implements InvocationHandler {

    private boolean debug;

    public ClientProxy(boolean debug) {
      this.debug = debug;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if (!hasThrowsAnalysisRpcException(method.getExceptionTypes())) {
        throw new RuntimeException("Invoked methods on AnalysisRpcClient must be declared to throw AnalysisRpcException");
      }

      final String methodName = method.getName();
      if (debug) {
        return request_debug(methodName, args, false);
      } else {
        return request(methodName, args);
      }
    }

    private boolean hasThrowsAnalysisRpcException(Class<?>[] exceptionTypes) {
      for (Class<?> exceptionType : exceptionTypes) {
        if (exceptionType.isAssignableFrom(AnalysisRpcException.class)) {
          return true;
        }
      }
      return false;
    }

  }
}
