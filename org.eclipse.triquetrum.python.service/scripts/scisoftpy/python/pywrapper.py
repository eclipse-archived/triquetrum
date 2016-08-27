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

class typednone(object):
    '''
    A wrapper class for use when calling java overloaded methods and you
    need to distinguish which one of the methods to call when the argument
    is None. Has the effect of in Java source of (Type)null.
    '''

    def __init__(self, typedNoneType="java.lang.Object"):
        '''Create an instance of a typed none. The argument should be
        the name of a Java type suitable to pass to Class.forName()'''
        self.typedNoneType = typedNoneType

    __hash__ = None

    def __eq__(self, other):
        return (isinstance(other, self.__class__)
            and self.typedNoneType == other.typedNoneType)

    def __ne__(self, other):
        return not self.__eq__(other)

    def __repr__(self):
        return '%s(%s)' % (self.__class__.__name__, self.__dict__.__repr__())

#Use this class to wrap a Binary object, typically a str of bytes
try:
    import xmlrpclib
    binarywrapper = xmlrpclib.Binary
except:
    import xmlrpc.client
    binarywrapper = xmlrpc.client.Binary
