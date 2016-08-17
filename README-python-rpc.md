# python-rpc

An RPC-based bridge from Java to Python, extracted from [DAWN](http://www.dawnsci.org/) to prepare for reuse in other contexts.
Dependencies to DAWN-related packages were removed and some refactoring was done to make it work in a minimal runtime.

At the Science WG unconference workshop at EclipseCon France 2016, it was proposed to integrate this in Triquetrum.

## Getting started

1. Make sure you have a Python installed with all modules as needed for DAWN-like scripting. (I used a Python 2.7)
2. Clone this repo and import all projects in a fresh eclipse workspace.
2. Open and set the target definition in org.eclipse.triquetrum.python.rpc.platform.
3. You should not have any compilation errors.
4. Go to Run Configurations... and select the "OSGi Framework" configuration
5. Run the thing
6. Try runscript helloworld input1=hi input2=bye

You should get output similar to :
```
2016-06-02 13:18:07,477 INFO [StdErr]  cmdline.ManagedCommandline (read:228) - StdErr> Could not import python image library
2016-06-02 13:18:07,725 INFO [StdOut]  cmdline.ManagedCommandline (read:228) - StdOut> hello world : hi and bye
{output3={key1=hi, key2=bye}, output4=hey, output1=1, output2=[Ljava.lang.Object;@7d2f256b, output5={key1=hey there, key2=3, key3=[Ljava.lang.Object;@208ad55e}, output0=1}
```

## TODO

 * Original version in DAWN redirected Python's output using StreamGobbler, this version inherits IO from parent process. 

