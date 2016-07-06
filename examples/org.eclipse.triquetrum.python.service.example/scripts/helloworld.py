
def run(input1, input2, **kwargs):

    print "hello world : %s and %s" % (input1 , input2)

    return {"output0": "1", "output1": 1, "output2": (1 , 5.2), "output3": {"key1": input1, "key2": input2},
    "output4": "hey", "output5": {"key1": "hey there", "key2": 3, "key3": [{"key1": "hey there", "key2": 3}, [1,2,3]]}
    }