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
Python version of flattening. See Java version for documentation on flattening.
This module is generally for internal use by AnalysisRPC and is only made
public for the purpose of testing, see pyflatten_test.py
'''

import scisoftpy.python.pywrapper as _wrapper
import sys
import uuid
import traceback

TYPE = "__type__"
CONTENT = "content"

_TEMP_LOCATION_SET = False
_TEMP_LOCATION = None

def settemplocation(loc=None):
    '''
     Set a custom temporary file location. This is used by some flatteners to store large data sets which are faster
     to write to disk than send over XML-RPC.

     There is no requirement for the unflattener at the other end to have the same temp location as a full path to the
     temp file should be stored in the flattened form.

     loc new temp file location to use, or None to use default of system temp
    '''
    global _TEMP_LOCATION, _TEMP_LOCATION_SET
    _TEMP_LOCATION = loc
    _TEMP_LOCATION_SET = True

class flatteningHelper(object):
    def __init__(self, typeObj, typeName):
        self.typeObj = typeObj
        self.typeName = typeName

    def canunflatten(self, obj):
        return isinstance(obj, dict) and obj.get(TYPE) == self.typeName

    def canflatten(self, obj):
        return isinstance(obj, self.typeObj)

class dictHelper(flatteningHelper):
    TYPE_NAME = "java.util.Map"
    KEYS = "keys"
    VALUES = "values"

    def __init__(self):
        super(dictHelper, self).__init__(dict, self.TYPE_NAME)

    def flatten(self, thisDict):
        rval = dict()
        rval[TYPE] = self.TYPE_NAME
        rval[self.KEYS] = []
        rval[self.VALUES] = []
        for k, v in thisDict.iteritems():
            rval[self.KEYS].append(flatten(k))
            rval[self.VALUES].append(flatten(v))
        return rval

    def unflatten(self, thisDict):
        rval = dict()
        for k, v in zip(thisDict[self.KEYS], thisDict[self.VALUES]):
            rval[unflatten(k)] = unflatten(v)
        return rval

passThroughTypes = (str, int, float, _wrapper.binarywrapper)
if sys.hexversion < 0x03000000:
    passThroughTypes = passThroughTypes + (unicode, long)
class passThroughHelper(object):
    def flatten(self, obj):
        return obj

    def unflatten(self, obj):
        return obj

    def canflatten(self, obj):
        return isinstance(obj, passThroughTypes)

    def canunflatten(self, obj):
        return self.canflatten(obj)

class listAndTupleHelper(object):
    def flatten(self, obj):
        outList = []
        for thisItem in obj:
            outList.append(flatten(thisItem))
        return outList

    def unflatten(self, obj):
        outList = []
        for thisItem in obj:
            outList.append(unflatten(thisItem))
        return outList


    def canflatten(self, obj):
        return isinstance(obj, (list, tuple))

    def canunflatten(self, obj):
        return isinstance(obj, (list, tuple))

class noneHelper(flatteningHelper):
    TYPE_NAME = "__None__"
    TYPED_NONE_TYPE = "typedNoneType"
    NULL = "null"

    def __init__(self):
        super(noneHelper, self).__init__(None, self.TYPE_NAME)

    def flatten(self, thisNone):
        rval = dict()
        rval[TYPE] = self.TYPE_NAME
        if not (thisNone is None):
            rval[self.TYPED_NONE_TYPE] = thisNone.typedNoneType
        else:
            rval[self.TYPED_NONE_TYPE] = self.NULL
        return rval

    def unflatten(self, obj):
        if obj is None or obj[self.TYPED_NONE_TYPE] == self.NULL:
            return None

        rval = _wrapper.typednone()
        rval.typedNoneType = obj[self.TYPED_NONE_TYPE]
        return rval

    def canflatten(self, obj):
        return obj is None or isinstance(obj, _wrapper.typednone)

    def canunflatten(self, obj):
        return obj is None or super(noneHelper, self).canunflatten(obj)

class uuidHelper(flatteningHelper):
    TYPE_NAME = "java.util.UUID"

    def __init__(self):
        super(uuidHelper, self).__init__(uuid.UUID, self.TYPE_NAME)

    def flatten(self, thisUUID):
        rval = dict()
        rval[TYPE] = self.TYPE_NAME
        rval[CONTENT] = str(thisUUID)
        return rval

    def unflatten(self, obj):
        return uuid.UUID(obj[CONTENT])

class stackTraceElementHelper(object):
    TYPE_NAME = "java.lang.StackTraceElement"
    DECLARINGCLASS = "declaringClass"
    METHODNAME = "methodName"
    FILENAME = "fileName"
    LINENUMBER = "lineNumber"

    def canunflatten(self, obj):
        return isinstance(obj, dict) and obj.get(TYPE) == self.TYPE_NAME

    def canflatten(self, obj):
        # There is no actual type in Python that is a StackTraceElement
        # so we can't flatten it. We flatten it as a side effect of
        # creating a flat exception (see exceptionHelper)
        return False

    def flatten(self, obj):
        # This method can take a tuple as returned by extract_tb and
        # convert it to a flattened form
        (filename, line_number, function_name, unused_text, clazz) = obj

        rval = dict()
        rval[TYPE] = self.TYPE_NAME

        rval[self.DECLARINGCLASS] = flatten(clazz)
        rval[self.METHODNAME] = flatten(function_name)
        rval[self.FILENAME] = flatten(filename)
        rval[self.LINENUMBER] = flatten(line_number)

        return rval

    def unflatten(self, obj):
        clazz = unflatten(obj[self.DECLARINGCLASS])
        function_name = unflatten(obj[self.METHODNAME])
        filename = unflatten(obj[self.FILENAME])
        line_number = unflatten(obj[self.LINENUMBER])
        return (filename, line_number, function_name, clazz)


class exceptionHelper(flatteningHelper):
    TYPE_NAME = "java.lang.Exception"
    EXECTYPESTR = "exctypestr"
    EXECVALUESTR = "excvaluestr"
    TRACEBACK = "traceback"
    PYTHONTEXTS = "pythontexts"

    def __init__(self):
        super(exceptionHelper, self).__init__(Exception, self.TYPE_NAME)

    def flatten(self, thisException):
        rval = dict()
        rval[TYPE] = self.TYPE_NAME
        (_etype, value, tb) = sys.exc_info()
        try:
            rval[self.EXECTYPESTR] = flatten(str(thisException.__class__.__name__))
            traceback.format_exception_only(thisException.__class__, thisException)
            try:
                rval[self.EXECVALUESTR] = flatten(str(thisException))
            except:
                rval[self.EXECVALUESTR] = flatten(None)

            # We only add a traceback if one is available and the exception
            # being flattened is the exception
            def flatten_tb(tb):
                # Set the stack order to newest on top
                # (Java and Python have opposite order for storing
                # stack traces, this normalizes them)
                tb.reverse()

                # Ideally we would do "rval[self.TRACEBACK] = flatten(tb)" but
                # there is no type info to do that, so instead we have to
                # explicitly flatten
                steHelper = stackTraceElementHelper()
                stes = [steHelper.flatten(f) for f in tb]

                texts = flatten([f[3] for f in tb])

                return stes, texts
            if tb is not None and id(value) == id(thisException):
                extract_tb = traceback.extract_tb(tb)
                extract_tb = zip(extract_tb, ("",) * len(extract_tb))
                extract_tb = [s[0] + (s[1],) for s in extract_tb]
                rval[self.TRACEBACK], rval[self.PYTHONTEXTS] = flatten_tb(extract_tb)
            elif hasattr(thisException, "flatten_traceback"):
                rval[self.TRACEBACK], rval[self.PYTHONTEXTS] = flatten_tb(thisException.flatten_traceback)
        finally:
            _etype = value = tb = None
        return rval

    def unflatten(self, obj):
        # For Python we squish the entire traceback into the message
        # because we can't create a "fake" traceback like we do with
        # Java using Exception.setStackTrace()
        # But we also store the raw stack trace in flatten_traceback,
        # however, this is not really API and just enables us to
        # preserve the flattened form (i.e. flatten -> unflatten -> flatten ->
        # unflatten -> etc) does not lose information. In practice this
        # continued transforms is only done on tests and the actual message
        # of the exception is not well preserved
        exctype = unflatten(obj[self.EXECTYPESTR])
        excstr = unflatten(obj[self.EXECVALUESTR])
        if excstr is None:
            excstr = ""
        else:
            excstr = ": " + excstr
        excheader = exctype + excstr
        if self.TRACEBACK in obj:
            stackTrace = unflatten(obj[self.TRACEBACK])
            texts = None
            if self.PYTHONTEXTS in obj:
                texts = unflatten(obj[self.PYTHONTEXTS])
            if texts is None or len(texts) != len(stackTrace):
                # texts are mismatched, discard them
                texts = [""] * len(stackTrace)
            stackTrace = [s[0][:3] + (s[1],s[0][3]) for s in zip(stackTrace, texts)]

            # Set the stack order to oldest on top
            # (Java and Python have opposite order for storing
            # stack traces, this returns to Python order)
            stackTrace.reverse()

            excmsg = excheader
            analheader = "\n\nTraceback (from AnalysisRPC Remote Side, most recent call last):\n"
            if analheader not in excmsg:
                excmsg += analheader
                # the map removes the sometime present 5th element of "class"
                out = traceback.format_list(map(lambda x: x[0:4], stackTrace))
                excmsg += "".join(out)
            e = Exception(excmsg)
            e.flatten_traceback = stackTrace
            return e
        else:
            return Exception(excheader)

helpers = [noneHelper(),
           dictHelper(), passThroughHelper(), listAndTupleHelper(),
           uuidHelper(), exceptionHelper(), stackTraceElementHelper()]

def addhelper(helper):
    helpers.insert(0, helper)

def flatten(obj):
    for thisHelper in helpers:
        if thisHelper.canflatten(obj):
            return thisHelper.flatten(obj)
    raise TypeError("Object " + repr(obj) + " cannot be flattened")

def unflatten(obj):
    for thisHelper in helpers:
        if thisHelper.canunflatten(obj):
            return thisHelper.unflatten(obj)
    raise TypeError("Object " + repr(obj) + " cannot be unflattened")

def canflatten(obj):
    for thisHelper in helpers:
        if thisHelper.canflatten(obj):
            return True
    return False

def canunflatten(obj):
    for thisHelper in helpers:
        if thisHelper.canunflatten(obj):
            return True
    return False





