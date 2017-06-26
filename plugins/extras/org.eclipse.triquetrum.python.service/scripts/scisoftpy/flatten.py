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
Flatten Package, normally for internal use by RPC only.
For documentation, refer to Java IFlattener and IRootFlattener
'''

import os
if os.name == 'java':
    raise 'Jython is unsupported'
from scisoftpy.python import pyflatten as _flatten #@Reimport

flatten=_flatten.flatten
unflatten=_flatten.unflatten
canflatten=_flatten.canflatten
canunflatten=_flatten.canunflatten
settemplocation=_flatten.settemplocation
addhelper=_flatten.addhelper
