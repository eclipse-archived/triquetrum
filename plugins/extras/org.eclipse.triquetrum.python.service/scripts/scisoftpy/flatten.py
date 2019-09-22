###
# Copyright (c) 2011, 2019  Diamond Light Source Ltd.,
#                          Kichwa Coders & iSencia Belgium NV.
# This program and the accompanying materials are made available under the
# terms of the Eclipse Public License 2.0 which is available at
# http://www.eclipse.org/legal/epl-2.0.
#
# SPDX-License-Identifier: EPL-2.0
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
