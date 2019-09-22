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

'''
import sys
if sys.hexversion < 0x02040000:
    raise 'Must use python of at least version 2.4'

import os
if os.name == 'java':
    raise 'Jython is unsupported'

from scisoftpy import flatten
from scisoftpy import rpc
