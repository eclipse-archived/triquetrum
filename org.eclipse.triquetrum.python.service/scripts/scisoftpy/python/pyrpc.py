###
# Copyright (c) 2011, 2016  Diamond Light Source Ltd.,
#                          Kichwa Coders & iSencia Belgium NV.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#    DLS, Kichwa Coders - initial API and implementation and/or initial documentation
#    Erwin De Ley - extraction from DAWN to ease reuse in other contexts
###


'''
AnalysisRpc implementation in Python
'''
try:
    # Python 2
    from SocketServer import ThreadingMixIn
    from SimpleXMLRPCServer import SimpleXMLRPCRequestHandler, SimpleXMLRPCServer
    from xmlrpclib import ServerProxy
except:
    # Python 3
    from socketserver import ThreadingMixIn
    from xmlrpc.server import SimpleXMLRPCRequestHandler, SimpleXMLRPCServer
    from xmlrpc.client import ServerProxy
import scisoftpy.python.pyflatten as _flatten

class _method:
    def __init__(self, send, destination):
        self._send = send
        self._destination = destination
    def __call__(self, *args):
        return self._send(self._destination, args)

class RequestHandler(SimpleXMLRPCRequestHandler):
    rpc_paths = ('/RPC2',)

class ThreadedSimpleXMLRPCServer(ThreadingMixIn, SimpleXMLRPCServer):
    pass

class rpcserver(object):
    '''
    An AnalysisRpc Server to serve up Python functions to other processes via RPC
    '''
    def __init__(self, port):
        '''
        Create a new AnalysisRpc Server listening on the specified port
        '''
        self._server = ThreadedSimpleXMLRPCServer(("127.0.0.1", port), requestHandler=RequestHandler, logRequests=False)
        self._server.register_introspection_functions()

        self._server.register_function(self._xmlrpchandler, 'Analysis.handler');
        self._server.register_function(self._xmlrpchandler_debug, 'Analysis.handler_debug');
        self._server.register_function(self._xmlrpc_is_alive, 'Analysis.is_alive');
        self._server.register_function(self._xmlrpc_set_pydev_settrace_params, 'Analysis.set_pydev_settrace_params');
        self._handlers = dict()

        self.pydev_settrace_params = dict()

    def _xmlrpchandler_common(self, destination, args, debug=False, suspend=False):
        try:
            handler = self._handlers.get(destination)
            if handler is None:
                ret = Exception("No handler registered for " + destination)
            else:
                unflattened = map(_flatten.unflatten, args)
                if debug:
                    # do the import here, pydevd must already be in the sys.
                    # it is important that the import is within the outer try/except
                    # so that import error gets propagated back up as
                    # an AnalysisRpcException
                    import pydevd  # @UnresolvedImport
                    # Initialise debug, without the suspend, next settrace sets the
                    # suspend if needed. This split makes it easier to detect cause
                    # of failure if an exception is thrown. This line covers the
                    # connection to the debug server, the next settrace then only
                    # does the suspend.
                    pydevd.settrace(suspend=False, **self.pydev_settrace_params) # Needs PyDev Debug Server Running


                    # These two statements must be on same line so that suspend happens
                    # on next executable line, being the first line of the handler

                    # Run the registered Analysis RPC Handler. (If you are browsing
                    # a stack trace, this is probably as high up the stack as you
                    # want to go).
                    pydevd.settrace(suspend=suspend); ret = handler(*unflattened)
                else:
                    ret = handler(*unflattened)
            flatret = _flatten.flatten(ret)
        except Exception as e:
            flatret = _flatten.flatten(e)
        return flatret
    def _xmlrpchandler(self, destination, args):
        return self._xmlrpchandler_common(destination, args)
    def _xmlrpchandler_debug(self, destination, args, suspend):
        return self._xmlrpchandler_common(destination, args, True, suspend)

    def _xmlrpc_is_alive(self):
        return True

    def _xmlrpc_set_pydev_settrace_params(self, params):
        self.pydev_settrace_params = dict(params)
        # Cannot return None (aka null in java), return value is unused
        # This call isn't handled via flatteners which normally
        # handles None
        return 0

    def add_handler(self, name, function):
        '''
        Register a new function with the Server. The function
        will be called when a request to the given name is made
        '''
        self._handlers[name] = function

    def serve_forever(self):
        '''
        Serve the RPC forever. The function does not return unless
        shutdown() is called from another thread.
        '''
        self._server.serve_forever()

    def shutdown(self):
        '''
        Shutdown the RPC Server. Must be called after serve_forever
        or it will deadlock.
        Only available in Python >= 2.6
        '''
        self._server.shutdown()

    def close(self):
        '''
        Close the port related to the server
        '''
        self._server.server_close()


class rpcclient(object):
    '''
    An AnalysisRpc Client, used to connect to an AnalysisRpc server
    in another process.
    Calls to the server can be made either with the request method
    or as an attribute of the rpcclient instance.
    '''
    def __init__(self, port):
        '''
        Create a new AnalysisRpc Client which will connect on the specified port
        '''
        self._serverProxy = ServerProxy("http://127.0.0.1:%d" % port)
        self._port = port

    def _request_common(self, destination, params, debug=False, suspend=False):
        flatargs = _flatten.flatten(params)
        if debug:
            flatret = self._serverProxy.Analysis.handler_debug(destination, flatargs, suspend)
        else:
            flatret = self._serverProxy.Analysis.handler(destination, flatargs)
        unflatret = _flatten.unflatten(flatret)
        if (isinstance(unflatret, Exception)):
            raise unflatret
        return unflatret

    def request(self, destination, params):
        '''
        Perform a request to the Server, directing it at the destination
        which was registered as the handler on the server, passing
        it the params specified.
        params must be a tuple or a list of the arguments
        '''
        return self._request_common(destination, params)
    def request_debug(self, destination, params, suspend):
        '''
        Perform a request to the Server starting debug if server supports
        it, directing it at the destination
        which was registered as the handler on the server, passing
        it the params specified.
        params must be a tuple or a list of the arguments
        suspend if true, suspends on entry
        '''
        return self._request_common(destination, params, True, suspend)

    def is_alive(self):
        try:
            self._serverProxy.Analysis.is_alive()
            return True
        except:
            return False

    def __getattr__(self, destination):
        return _method(self.request, destination)


