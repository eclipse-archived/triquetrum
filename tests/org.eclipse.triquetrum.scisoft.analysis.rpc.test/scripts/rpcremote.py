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

# Set up environment
import os, sys
sys.path.append(sys.argv[1])
import scisoftpy as dnp #@UnresolvedImport

# define fancy function which is easier to write in Python
def python_cat(input1, input2):
    '''Performs a cat() on two inputs'''
    return input1 + input2


# Make the fancy function available
rpcserver = dnp.rpc.rpcserver(int(sys.argv[2]))
rpcserver.add_handler("cat", python_cat)
rpcserver.serve_forever()
