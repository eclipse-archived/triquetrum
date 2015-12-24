---
layout: post
title: Introducing Triquetrum
---

[Triquetrum](https://projects.eclipse.org/projects/technology.triquetrum), a new project within the [Eclipse Science IWG](https://science.eclipse.org/), has delivered its initial contribution for [Sinterklaas](https://en.wikipedia.org/wiki/Sinterklaas) 2015.

Triquetrum delivers an open platform for managing and executing scientific workflows. The goal of Triquetrum is to support a wide range of use cases, ranging from automated processes based on predefined models, to replaying ad-hoc research workflows recorded from a user's actions in a scientific workbench UI. It will allow the user to define and execute models from personal pipelines with a few steps to massive models with thousands of elements.

Besides delivering a generic workflow environment, Triquetrum also delivers extensions with a focus on scientific software. There is no a-priori limitation on target scientific domains, but the current interested organizations are big research institutions in materials research (synchrotrons), physics and engineering.

The integration of a workflow system in a platform for scientific software can bring many benefits:

1. the steps in scientific processes are made explicitly visible in the workflow models (i.e. being hidden inside program code).
2. such models can serve as a means to present, discuss and share scientific processes in communities with different skills-sets
3. allow differentiating for different roles within a common tools set: software engineers, internal scientists, visiting scientists etc.
4. promotes reuse of software assets and modular solution design
5. technical services for automating complex processes in a scalable and maintainable way
6. crucial tool for advanced analytics on gigantic datasets
7. integrates execution tracing, provenance data, etc.

The project is structured on three lines of work:

1. A graphical model editor delivered as plugins for an Eclipse RCP application, and an execution runtime that must be easy to integrate in different environments, ranging from a personal RCP workbench to large-scale distributed systems.
2. APIs and OSGi service impls for Task-based processing.
3. Supporting APIs and tools, e.g. integration adapters to all kinds of things like external software packages, resource managers, data sources etc.

Below we will discuss the first two lines of work in a bit more detail, followed by the current state of Triquetrum as delivered in its initial contribution.

### Triquetrum workflow management

The core of Triquetrum is an integration of [Ptolemy II](http://ptolemy.eecs.berkeley.edu/ptolemyII/) in an Eclipse and OSGi technology stack. Ptolemy II is a simulation and modeling tool from UC Berkeley. Originally intended for research and experimenting with system design techniques across engineering domains, it provides formalisms and tools for defining and executing hierarchical models of heterogeneous systems. Ptolemy II combines its actor-oriented architecture with a strong focus on concurrency, encapsulation and modularity. It comes with its own model design GUI called Vergil, based on Swing and a 2D graphical framework called Diva.

During its history of almost two decades it has been used internationally in varying domains, including in spin-offs for scientific workflow management like the [Kepler Project](https://kepler-project.org/) and [Passerelle](https://github.com/eclipselabs/passerelle). Triquetrum continues on the track started by Passerelle but now as an official Eclipse project linked to the Science IWG, and with a much closer integration and collaboration with the Ptolemy II team at UC Berkeley.

The combination of Eclipse/OSGi with Ptolemy II delivers a solid platform for a wide range of workflow applications, and in particular for scientific workflows. The modularity and dynamism offered by OSGi, the rich set of frameworks and technologies offered through the Eclipse foundation, and the community of the Science IWG together result in a very powerful ecosystem for projects like Triquetrum.

Triquetrum delivers both a model design GUI based on Eclipse technology, and supporting APIs for local & remote executions, including support for debugging/breakpoints etc.
The platform and RCP editor are extensible with domain-specific components and modules, building on the modular features and related APIs that both Ptolemy II and Eclipse/OSGi offer.

### Task-based processing

A Triquetrum Task represents WHAT a certain step in a process must achieve, not HOW it must be processed. The actual processing is typically done by corresponding TaskProcessingService implementations.
Neither a Task nor the thing creating a Task are aware of the concrete service that will process it. A TaskProcessingBroker mediates between submitted tasks and the right TaskProcessingService. Service implementations are typically asynchronous (a.k.a. non-blocking), they may perform local work or may invoke remote services and they encapsulate whatever technical features that may be needed.

The definition of a Task mainly consists of a combination of a type and an optional set of Attributes that can be used to parameterize the concrete processing. A Task is considered to be an entity that represents a unit of work within a process, with a life cycle going through different states and finally optionally obtaining results if all went well.

Tasks are also used to structure execution traces with the received inputs, detailed event logs for all state changes and error messages, and to store (references to) results. As such, they provide the basis for what is often called "provenance tracking" in scientific software.

A graphically designed Triquetrum workflow could be considered as an easy way to define and orchestrate sequences of Tasks that get processed by a service layer with support for concurrency, asynchronous processing, persistence, remote invocations etc., instead of writing a program to do the same. But it's important to note that the Task-based API can be used independently of Triquetrum's workflow implementation, e.g. by other workflow/orchestration/sequencing software or even ad-hoc systems, interactive UIs etc. As other Science IWG software projects may have similar requirements or features to structure work performed by users or automated tools, we hope to integrate the different ideas and implementations and to be able to define a uniform and reusable Task-oriented API.

### The scope of the initial contribution

Besides learning about the Eclipse project set-up procedures, IP checking etc., the focus of this first phase has been on two topics:

1. select the Eclipse technologies that will be at the core of the graphical editor and build a trivial RCP application to demonstrate their integration
2. migrate an initial set of APIs and implementations from Passerelle

The main Eclipse frameworks that are used for the workflow editor are:

1. [Equinox](http://www.eclipse.org/equinox/), RCP, ...: the traditional stuff for RCP applications
2. [EMF](https://eclipse.org/modeling/emf/): to define a meta-model for Ptolemy II's model elements like Actors, CompositeActors, Parameters, Directors etc.
3. [Graphiti](https://eclipse.org/graphiti/): for the graphical editor
4. [EMF Forms](http://www.eclipse.org/ecp/emfforms/): to define Actor configuration forms

Following APIs have been provided alongside the editor:

1. org.eclipse.triquetrum.processing.api
  * org.eclipse.triquetrum.processing.model: this contains interfaces representing tasks, their attributes, lifecycle events, results etc.
  * org.eclipse.triquetrum.processing.service: service interfaces to validate tasks and to get them processed
2. org.eclipse.triquetrum.workflow.api
  * org.eclipse.triquetrum.workflow: defines interfaces to maintain workflow models as versioned assets, and to execute workflows

Only org.eclipse.triquetrum.workflow.WorkflowExecutionService has been implemented at this stage (with any required supporting interfaces), by org.eclipse.triquetrum.workflow.execution.impl.
This one is used in the RCP application to run its models. The processing APIs are not yet implemented, but will serve as a basis to investigate the integration of related implementations from other scientific software projects.

### The roadmap for the near future
For the moment, we're working on an initial set of documents to describe the different parts of the project in more detail, and to prepare a [Getting Started](https://github.com/eclipse/triquetrum/wiki/Getting-Started) guide for new collaborators.

Now that we've have the main elements working together for the workflow editor, we will integrate more Ptolemy II actors and tools in the editor. Support for hierarchical models will be added with priority.

A second line of work will be to implement the Task-based APIs, together with different storage strategies for execution traces and provenance info. This will be done in collaboration with other Science projects like [DAWNSci](https://projects.eclipse.org/projects/technology.dawnsci) and [ICE](https://projects.eclipse.org/projects/technology.ice). Other interested parties are of course welcome!

Thirdly, we will provide an initial set of integration adapters for remote services and computing resources. In this domain, a collaboration with the Eclipse [PTP](https://projects.eclipse.org/projects/tools.ptp) and [ECF](https://eclipse.org/ecf/) projects would be logical.

Finally, we will start developing a library of actors and services to integrate Task-based workflow concepts with support for scientific data sets, via the upcoming Eclipse Data Structures project.

Feel free to discover and follow Triquetrum via the different channels:

* The project site at: <https://projects.eclipse.org/projects/technology.triquetrum>
* Source repository at: <https://github.com/eclipse/triquetrum>
* Mailing list: <https://dev.eclipse.org/mailman/listinfo/triquetrum-dev>
* Forum: <https://www.eclipse.org/forums/index.php/f/325/>

