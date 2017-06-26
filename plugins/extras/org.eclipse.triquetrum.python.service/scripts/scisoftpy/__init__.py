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

'''
import sys
if sys.hexversion < 0x02040000:
    raise 'Must use python of at least version 2.4'

import os
if os.name == 'java':
    raise 'Jython is unsupported'

from scisoftpy import flatten
from scisoftpy import rpc
