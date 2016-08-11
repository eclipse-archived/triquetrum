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

import os, sys
sys.path.append(sys.argv[1])
import scisoftpy as dnp #@UnresolvedImport
try:
    # Python 2
    from SimpleXMLRPCServer import SimpleXMLRPCServer, SimpleXMLRPCRequestHandler
except:
    # Python 3
    from xmlrpc.server import SimpleXMLRPCRequestHandler, SimpleXMLRPCServer


# Create server
class RequestHandler(SimpleXMLRPCRequestHandler):
    rpc_paths = ('/xmlrpc',)
server = SimpleXMLRPCServer(("127.0.0.1", 8713), requestHandler=RequestHandler, logRequests=False)
server.register_introspection_functions()

def runflat(x):
    obj = x[0]
    if not dnp.flatten.canunflatten(obj):
        raise Exception("Can't unflatten")
    unflat = dnp.flatten.unflatten(obj)
    reflat = dnp.flatten.flatten(unflat)
    return reflat
server.register_function(runflat)

def loopback(x):
    return x[0]
server.register_function(loopback)


# Run the server's main loop
server.serve_forever()
