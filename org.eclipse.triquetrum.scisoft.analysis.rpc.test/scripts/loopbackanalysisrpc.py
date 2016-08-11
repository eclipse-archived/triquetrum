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
    import thread
except:
    # Python 3
    import _thread as thread

internal_rpcclient = dnp.rpc.rpcclient(8715)
rpcserver = dnp.rpc.rpcserver(8714)
rpcserver.add_handler("loopback", lambda arg: arg)
rpcserver.add_handler("loopback_after_local", lambda arg: internal_rpcclient.loopback(arg))
thread.start_new_thread(rpcserver.serve_forever, ())

# This is the server that is going to loopback locally to python
rpcserver = dnp.rpc.rpcserver(8715)
rpcserver.add_handler("loopback", lambda arg: arg)
rpcserver.serve_forever()
