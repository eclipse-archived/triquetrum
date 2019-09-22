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

def run(input1, input2, **kwargs):

    print "hello URL : %s and %s" % (input1 , input2)

    return {"output1": 1, "outputURL": "http://www.epn-campus.eu/"}