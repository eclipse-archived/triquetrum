/*******************************************************************************
 * Copyright (c) 2015,2019 iSencia Belgium NV.
 *  
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.model.util;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ptolemy.classloading.ClassLoadingStrategy;
import org.ptolemy.commons.VersionSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.data.expr.UndefinedConstantOrIdentifierException;
import ptolemy.kernel.ComponentEntity;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.Entity;
import ptolemy.kernel.Port;
import ptolemy.kernel.Relation;
import ptolemy.kernel.util.Attribute;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.Nameable;
import ptolemy.kernel.util.NamedObj;
import ptolemy.kernel.util.Singleton;
import ptolemy.kernel.util.Workspace;
import ptolemy.moml.EntityLibrary;
import ptolemy.moml.MoMLFilter;
import ptolemy.moml.MoMLParser;

/**
 * This is a minimized copy of MomlParser and MomlVariableChecker code to support Ptolemy model element creation and copy/pasting, plus some miscellaneous
 * utilities.
 * <p>
 * TODO refactor MOMLParser, so Ptolemy objects can be created outside of "parsing". I.e. split out all "creation"-related code in a separate utilities class
 * that is then used by the MomlParser and other code (like Triq model editing via EMF).
 * </p>
 */
public class PtolemyUtil {
  private final static Logger LOGGER = LoggerFactory.getLogger(PtolemyUtil.class);

  private final static ArrayList<MoMLFilter> importFilters = new ArrayList<>();

  /**
   * If the modelElement is not the model/toplevel itself, this returns the full name but without the prefix containing the model name surrounded by 2 '.'s. For
   * example, and actor named <code>"Const"</code> in a submodel <code>"sub"</code> in a model <code>"HelloWorld"</code> has as full name
   * <code>".HelloWorld.sub.Const"</code>. This method then returns <code>"sub.Const"</code>.
   * <p>
   * Remark that if the modelElement is the toplevel/model itself, the method returns the empty string "".
   * </p>
   * 
   * @param modelElement
   * @return the modelElement's full name without the prefix part with the toplevel/model name
   */
  public static String getFullNameWithoutToplevel(Nameable modelElement) {
    Nameable toplevel = modelElement;
    while (toplevel.getContainer() != null) {
      toplevel = toplevel.getContainer();
  }
    if (toplevel != modelElement) {
      return modelElement.getFullName().substring(toplevel.getName().length() + 2);
    } else {
      return "";
    }
  }

  /**
   * Find out if the exception is or contains an UndefinedConstantOrIdentifierException and if so return the name of the undefined element.
   * <p>
   * This is used during copy/paste to identify references to elements that are not in the copy stack and that should be automatically added to ensure that the
   * paste operation includes everything that is needed to obtain a functional target model. See ModelElementPasteFeature in the editor implementation bundle.
   * 
   * @param exception
   *          the (wrapped) exception that may contain info on an undefined model element.
   * @return the name of the undefined element or null if no such root cause is present.
   */
  public static String _findUndefinedConstantOrIdentifier(IllegalActionException exception) {
    UndefinedConstantOrIdentifierException idException = null;
    if (exception instanceof UndefinedConstantOrIdentifierException) {
      idException = (UndefinedConstantOrIdentifierException) exception;
    } else {
      Throwable cause = exception.getCause();
      while (cause instanceof IllegalActionException) {
        if (cause instanceof UndefinedConstantOrIdentifierException) {
          idException = (UndefinedConstantOrIdentifierException) cause;
          break;
        }
        cause = ((IllegalActionException) cause).getCause();
      }
    }

    if (idException != null) {
      // We have an exception that has the name of the missing element.
      return idException.nodeName();
    } else {
      return null;
    }
  }

  /**
   * Creates a new attribute instance in the given container, of a type identified by the given class name, and with the given name.
   * 
   * @param container
   * @param className
   * @param attrName
   * @return
   * @throws Exception
   */
  public static Attribute _createAttribute(NamedObj container, String className, String attrName) throws Exception {
    Attribute previous = container != null ? container.getAttribute(attrName) : null;
    Class<?> newClass = null;

    if (className != null) {
      // If we throw an error or exception be sure to save the original error message before we go off and try to fix the error.
      // Sometimes, the original error message is the true cause of the problem, and we should always provide the user
      // with the cause of the original error in the unlikely event that our error correction fails
      try {
        newClass = loadClass(className, null);
      } catch (Exception ex) {
        throw new IllegalActionException(null, ex, "Cannot find class: " + className);
      }
    }

    if (previous != null) {
      if (newClass != null) {
        _checkClass(previous, newClass, "attribute named \"" + attrName + "\" exists and is not an instance of " + className);
      }
      return previous;
    } else {

      // First check that there will be no name collision when this is propagated.
      // Note that we need to include all derived objects, irrespective of whether they are locally changed.
      List<?> derivedList = container.getDerivedList();
      Iterator<?> derivedObjects = derivedList.iterator();

      while (derivedObjects.hasNext()) {
        NamedObj derived = (NamedObj) derivedObjects.next();
        Attribute other = derived.getAttribute(attrName);
        if (other != null && !(other instanceof Singleton)) {
          throw new IllegalActionException(container,
              "Cannot create attribute because a subclass or instance " + "contains an attribute with the same name: " + other.getFullName());
        }
      }

      Attribute newAttribute = null;
      newClass = (newClass != null) ? newClass : Attribute.class;
      Object[] arguments = new Object[2];
      arguments[0] = container;
      arguments[1] = attrName;
      newAttribute = (Attribute) _createInstance(newClass, arguments);

      // Propagate.
      newAttribute.propagateExistence();
      return newAttribute;
    }
  }

  /**
   * Creates a new relation instance in the given container, of a type identified by the given class name, and with the given name.
   * 
   * @param container
   * @param className
   * @param relationName
   * @return
   * @throws Exception
   */
  public static Relation _createRelation(CompositeEntity container, String className, String relationName) throws Exception {
    Relation previous = container != null ? container.getRelation(relationName) : null;
    Class<?> newClass = null;

    if (className != null) {
      // If we throw an error or exception be sure to save the original error message before we go off and try to fix the error.
      // Sometimes, the original error message is the true cause of the problem, and we should always provide the user
      // with the cause of the original error in the unlikely event that our error correction fails
      try {
        newClass = loadClass(className, null);
      } catch (Exception ex) {
        throw new IllegalActionException(null, ex, "Cannot find class: " + className);
      }
    }

    if (previous != null) {
      if (newClass != null) {
        _checkClass(previous, newClass, "relation named \"" + relationName + "\" exists and is not an instance of " + className);
      }
      return previous;
    } else {

      // First check that there will be no name collision when this is propagated.
      // Note that we need to include all derived objects, irrespective of whether they are locally changed.
      List<?> derivedList = container.getDerivedList();
      Iterator<?> derivedObjects = derivedList.iterator();

      while (derivedObjects.hasNext()) {
        CompositeEntity derived = (CompositeEntity) derivedObjects.next();
        if (derived.getRelation(relationName) != null) {
          throw new IllegalActionException(container, "Cannot create relation because a subclass or instance " + "contains a relation with the same name: "
              + derived.getRelation(relationName).getFullName());
        }
      }

      Relation newRelation = null;
      if (newClass == null) {
        // No classname. Use the newRelation() method.
        newRelation = container.newRelation(relationName);

        // Mark the contents of the new entity as being derived objects.
        // If we wouldn't do this the default attributes would be saved.
        _markContentsDerived(newRelation, 0);

      } else {
        Object[] arguments = new Object[2];
        arguments[0] = container;
        arguments[1] = relationName;
        newRelation = (Relation) _createInstance(newClass, arguments);
      }

      // Propagate.
      // NOTE: Propagated relations will not use newRelation(),
      // but rather will use clone. Classes that rely
      // on newRelation(), will no longer work, possibly!
      newRelation.propagateExistence();

      return newRelation;
    }
  }

  /**
   * /** Creates a new port instance in the given container, of a type identified by the given class name, and with the given name.
   * 
   * @param container
   * @param className
   * @param portName
   * @return
   * @throws Exception
   */
  public static Port _createPort(Entity<?> container, String className, String portName) throws Exception {
    Port previous = container != null ? container.getPort(portName) : null;
    Class<?> newClass = null;

    if (className != null) {
      // If we throw an error or exception be sure to save the original error message before we go off and try to fix the error.
      // Sometimes, the original error message is the true cause of the problem, and we should always provide the user
      // with the cause of the original error in the unlikely event that our error correction fails
      try {
        newClass = loadClass(className, null);
      } catch (Exception ex) {
        throw new IllegalActionException(null, ex, "Cannot find class: " + className);
      }
    }

    if (previous != null) {
      if (newClass != null) {
        _checkClass(previous, newClass, "port named \"" + portName + "\" exists and is not an instance of " + className);
      }
      return previous;
    } else {

      // First check that there will be no name collision when this is propagated.
      // Note that we need to include all derived objects, irrespective of whether they are locally changed.
      List<?> derivedList = container.getDerivedList();
      Iterator<?> derivedObjects = derivedList.iterator();

      while (derivedObjects.hasNext()) {
        Entity<?> derived = (Entity<?>) derivedObjects.next();
        if (derived.getPort(portName) != null) {
          throw new IllegalActionException(container,
              "Cannot create port because a subclass or instance " + "contains a port with the same name: " + derived.getPort(portName).getFullName());
        }
      }

      Port port = null;
      if (newClass == null) {
        // Classname is not given. Invoke newPort() on the container.
        port = container.newPort(portName);
      } else {
        // Classname is given.
        Object[] arguments = new Object[2];
        arguments[0] = container;
        arguments[1] = portName;
        port = (Port) _createInstance(newClass, arguments);
      }

      // Propagate.
      // NOTE: Propagated ports will not use newPort(), but rather will use clone.
      // Classes that override newPort() to perform special actions will no longer work, possibly!
      port.propagateExistence();

      return port;
    }
  }

  /**
   * Create a new entity from the specified class name, give it the specified entity name, and specify that its container is the current container object. If
   * the current container already contains an entity with the specified name and class, then return that entity. If the class name matches a class that has
   * been previously defined in the scope (or with an absolute name), then that class is instantiated. Otherwise, the class name is interpreted as a Java class
   * name and we attempt to construct the entity. If instantiating a Java class doesn't work, then we look for a MoML file on the classpath that defines a class
   * by this name. The file is assumed to be named "foo.xml", where "foo" is the name of the class. Moreover, the classname is assumed to have no periods (since
   * a MoML name does not allow periods, this is reasonable). If _current is not an instance of CompositeEntity, then an XML exception is thrown. If an object
   * is created and we are propagating, then that object is marked as a derived object. The third argument, if non-null, gives a URL to import to create a
   * reference class from which to instantiate this entity.
   *
   * @param className
   * @param versionSpec
   * @param entityName
   * @return The created NamedObj
   * @exception Exception
   *              If anything goes wrong.
   */
  public static Entity<?> _createEntity(CompositeEntity container, String className, VersionSpecification versionSpec, String entityName) throws Exception {

    Workspace workSpace = container != null ? container.workspace() : new Workspace();

    ComponentEntity<?> previous = container != null ? container.getEntity(entityName) : null;
    Class<?> newClass = null;
    Entity<?> newEntity = null;

    if (className != null) {
      // If we throw an error or exception be sure to save the original error message before we go off and try to fix the error.
      // Sometimes, the original error message is the true cause of the problem, and we should always provide the user
      // with the cause of the original error in the unlikely event that our error correction fails
      try {
        newClass = loadClass(className, versionSpec);
      } catch (Exception ex) {
        try {
          newEntity = loadActorOrientedClass(className, versionSpec);
        } catch (Exception e) {
          throw new IllegalActionException(null, e, "Cannot find class: " + className);
        }
      }
    }

    if (previous != null) {
      if (newClass != null) {
        _checkClass(previous, newClass, "entity named \"" + entityName + "\" exists and is not an instance of " + className);
      }
      // TODO should we add a duplicate check for AOCs as well?
      return previous;
    } else {

      // No previous entity. Class name is required.
      _checkForNull(className, "Cannot create entity without a class name.");

      // Not a named entity. Instantiate a Java class.
      if (container != null) {
        // Not a top-level entity.
        // First check that there will be no name collision when this is propagated.
        // Note that we need to include all derived objects, irrespective of whether they are locally changed.
        List<?> derivedList = container.getDerivedList();
        Iterator<?> derivedObjects = derivedList.iterator();

        while (derivedObjects.hasNext()) {
          CompositeEntity derived = (CompositeEntity) derivedObjects.next();

          // The following call, if derived is lazy, will trigger its expansion. However, derived may contain an instance of the same class we are now trying to
          // define,
          // so the populate will delegate to this class definition, which will result in the class getting defined, and the collidingEntity being non-null.
          // Entity collidingEntity = derived.getEntity(entityName);
          // Hence, we have to scroll through the list of entities lazily, avoiding populating.
          if (derived.getEntity(entityName) != null) {
            // If the derived is within an EntityLibrary, then don't throw an exception.
            // To replicate this, create an EntityLibrary within the UserLibrary, save and then try to edit the UserLibrary.
            boolean derivedIsNotWithinEntityLibrary = true;
            CompositeEntity derivedContainer = derived;
            while (derivedContainer != null && (derivedIsNotWithinEntityLibrary = !(derivedContainer instanceof EntityLibrary))) {
              derivedContainer = (CompositeEntity) derivedContainer.getContainer();
            }
            if (derivedIsNotWithinEntityLibrary) {
              throw new IllegalActionException(container,
                  "Cannot create entity named \"" + entityName + "\" because a subclass or instance in \"" + container.getFullName()
                      + "\" contains an entity with the same name \"" + derived.getEntity(entityName).getFullName()
                      + "\".  Note that this can happen when actor oriented class " + "definitions are LazyTypedCompositeActors.");
            }
          }
        }
        if(newEntity == null) {
          newEntity = (Entity<?>) _createInstance(newClass, container, entityName);
        } else if(newEntity.isClassDefinition()) {
          newEntity = (Entity<?>) newEntity.instantiate(container, entityName);
          newEntity.setClassName(className);
        } else {
          newEntity.setName(entityName);
        }
        newEntity.propagateExistence();
        return newEntity;
      } else {
        // Top-level entity. Instantiate in the workspace.
        // Note that there cannot possibly be any propagation here.
        if(newEntity == null) {
          newEntity = (Entity<?>) _createInstance(newClass, workSpace);
        } else if(newEntity.isClassDefinition()) {
          newEntity = (Entity<?>) newEntity.instantiate(container, entityName);
          newEntity.setClassName(className);
        } else {
          newEntity.setName(entityName);
        }
        return newEntity;
      }
    }
  }

  /**
   * Create an instance of the specified class name by finding a constructor that matches the specified arguments. The specified class must be NamedObj or
   * derived, or a ClassCastException will be thrown. NOTE: This mechanism does not support instantiation of inner classes, since those take an additional
   * argument (the first argument), which is the enclosing class. Static inner classes, however, work fine. This method marks the contents of what it creates as
   * derived objects, since they are defined in the Java code of the constructor. If we are currently propagating, then it also marks the new instance itself as
   * a derived object.
   *
   * @param newClass
   *          The class.
   * @param arguments
   *          The constructor arguments.
   * @exception Exception
   *              If no matching constructor is found, or if invoking the constructor triggers an exception.
   */
  public static NamedObj _createInstance(Class<?> newClass, Object... arguments) throws Exception {
    Constructor<?>[] constructors = newClass.getConstructors();

    for (Constructor<?> constructor : constructors) {
      Class<?>[] parameterTypes = constructor.getParameterTypes();

      if (parameterTypes.length != arguments.length) {
        continue;
      }

      boolean match = true;

      for (int j = 0; j < parameterTypes.length; j++) {
        if (!parameterTypes[j].isInstance(arguments[j])) {
          match = false;
          break;
        }
      }

      if (match) {
        NamedObj newEntity = (NamedObj) constructor.newInstance(arguments);

        // Mark the contents of the new entity as being derived objects.
        _markContentsDerived(newEntity, 0);

        return newEntity;
      }
    }

    // If we get here, then there is no matching constructor.
    // Generate a StringBuffer containing what we were looking for.
    StringBuffer argumentBuffer = new StringBuffer();

    for (int i = 0; i < arguments.length; i++) {
      argumentBuffer.append(arguments[i].getClass() + " = \"" + arguments[i].toString() + "\"");

      if (i < arguments.length - 1) {
        argumentBuffer.append(", ");
      }
    }

    throw new Exception("Cannot find a suitable constructor (" + arguments.length + " args) (" + argumentBuffer + ") for '" + newClass.getName() + "'");
  }

  /**
   * Load a class for model elements using the default ClassLoadingStrategy set in MoMLParser.
   * 
   * @param className
   * @param versionSpec
   * @return
   * @throws ClassNotFoundException
   */
  public static Class<?> loadClass(String className, VersionSpecification versionSpec) throws ClassNotFoundException {
    ClassLoadingStrategy defaultClassLoadingStrategy = MoMLParser.getDefaultClassLoadingStrategy();
    return defaultClassLoadingStrategy.loadJavaClass(className, versionSpec);
  }
  
  /**
   * Load an actor oriented class for model elements using the default ClassLoadingStrategy set in MoMLParser.
   * 
   * @param className
   * @param versionSpec
   * @return
   * @throws ClassNotFoundException
   */
  public static ComponentEntity<?> loadActorOrientedClass(String className, VersionSpecification versionSpec) throws ClassNotFoundException {
    ClassLoadingStrategy defaultClassLoadingStrategy = MoMLParser.getDefaultClassLoadingStrategy();
    return defaultClassLoadingStrategy.loadActorOrientedClass(className, versionSpec);
  }

  /**
   * 
   * @return the filters needed to import a native Ptolemy II model in Triquetrum.
   */
  public static List<MoMLFilter> getImportFilters() {
    if (importFilters.isEmpty()) {
      importFilters.add(new SubstituteClassesForTriquetrum());
      importFilters.add(new RemoveGraphicalClassesForTriquetrum());
    }
    return (List<MoMLFilter>) importFilters.clone();
  }

  /**
   * Mark the contents as being derived objects at a depth one greater than the depth argument, and then recursively mark their contents derived. This makes
   * them not export MoML, and prohibits name and container changes. Normally, the argument is an Entity, but this method will accept any NamedObj. This method
   * also adds all (deeply) contained instances of Settable to the _paramsToParse list, which ensures that they will be validated.
   *
   * @param object
   *          The instance that is defined by a class.
   * @param depth
   *          The depth (normally 0).
   */
  private static void _markContentsDerived(NamedObj object, int depth) {
    Iterator<?> objects = object.lazyContainedObjectsIterator();
    while (objects.hasNext()) {
      NamedObj containedObject = (NamedObj) objects.next();
      containedObject.setDerivedLevel(depth + 1);
      _markContentsDerived(containedObject, depth + 1);
    }
  }

  // If the first argument is not an instance of the second,
  // throw an exception with the given message.
  private static void _checkClass(Object object, Class<?> correctClass, String msg) throws Exception {
    if (!correctClass.isInstance(object)) {
      throw new Exception(msg);
    }
  }

  // If the argument is null, throw an exception with the given message.
  private static void _checkForNull(Object object, String message) throws Exception {
    if (object == null) {
      throw new Exception(message);
    }
  }
}
