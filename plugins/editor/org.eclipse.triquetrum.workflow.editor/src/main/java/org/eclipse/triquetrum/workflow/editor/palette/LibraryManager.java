/*******************************************************************************
 * Copyright (c) 2016,2019 iSencia Belgium NV.
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
package org.eclipse.triquetrum.workflow.editor.palette;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.triquetrum.ErrorCode;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.triquetrum.workflow.model.util.PtolemyUtil;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.actor.gui.Configuration;
import ptolemy.data.expr.FileParameter;
import ptolemy.kernel.ComponentEntity;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.Entity;
import ptolemy.kernel.util.ChangeListener;
import ptolemy.kernel.util.ChangeRequest;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;
import ptolemy.kernel.util.NamedObj;
import ptolemy.kernel.util.Workspace;
import ptolemy.moml.CollectingMomlParsingErrorHandler;
import ptolemy.moml.CollectingMomlParsingErrorHandler.ErrorItem;
import ptolemy.moml.EntityLibrary;
import ptolemy.moml.ErrorHandler;
import ptolemy.moml.MoMLChangeRequest;
import ptolemy.moml.MoMLParser;

/**
 * A class that groups all services related to maintaining/modifying/... the user actor library that is available in the
 * IDE.
 * <p>
 * Remark that this utility class is assumed to be used in a UI context. Errors are typically reported in error dialogs.
 * </p>
 */
public class LibraryManager implements EventHandler {

  private static final Logger logger = LoggerFactory.getLogger(LibraryManager.class);
  private static final String EVENT_TOPIC_PATTERN = "org/eclipse/triquetrum/workflow/userlibrary/*";
  public static final String ADD_EVENT_TOPIC = "org/eclipse/triquetrum/workflow/userlibrary/add";
  public static final String DELETE_EVENT_TOPIC = "org/eclipse/triquetrum/workflow/userlibrary/delete";
  public static final String ACTOR_LIBRARY_NAME = "actor library";
  private static final int ACTORS_LIBRARY_PREFIX_LENGTH = (".configuration." + ACTOR_LIBRARY_NAME + ".").length();
  private static final String SOURCE_PATH_LIB_ATTR_NAME = "_sourcePath";
  public static final String USER_LIBRARY_NAME = "User Library";

  private static LibraryManager instance;

  private SortedMap<String, EntityLibrary> userLibraryMap = new TreeMap<String, EntityLibrary>();
  private Configuration configuration;

  // Implementation remark : we don't want to provide LibraryManager as a service or DS component (yet).
  // There are some issues with startup order management : activating the library manager must only be done
  // when a repository service and aoc providers etc are already loaded, and this is tricky to express
  // as the library manager can not have direct dependencies to concrete instances of those neither.
  // So we go for a singleton-like pattern here, controlled from the UserLibraryPaletteEntryProvider.
  private LibraryManager() {
  }

  public static LibraryManager getActiveInstance() {
    if (instance == null) {
      new LibraryManager().activate();
    }
    return instance;
  }

  /**
   * 
   * @return the configured user library
   */
  public EntityLibrary getUserLibrary() {
    return userLibraryMap.get(USER_LIBRARY_NAME);
  }

  /**
   * 
   * @return the configured user library with the given name
   */
  public EntityLibrary getUserLibrary(String libraryName) {
    return userLibraryMap.get(libraryName);
  }

  /**
   * Updates the cached info for all libraries managed here.
   * 
   * @param configuration
   */
  public void refreshManagerCache(Configuration configuration) {
    if (configuration != this.configuration) {
      if (configuration != null) {
        refreshUserLibraryMap(configuration);
      } else {
        if (userLibraryMap != null) {
          userLibraryMap.clear();
          userLibraryMap = null;
        }
      }
      this.configuration = configuration;
    }
  }

  /**
   * @return the names of the user library and its sublibraries
   */
  public String[] getUserLibraryNames() {
    if (userLibraryMap == null || userLibraryMap.isEmpty())
      refreshUserLibraryMap(configuration);
    if (userLibraryMap == null) {
      // means the refresh failed
      return new String[0];
    } else {
      Set<String> libraryNames = userLibraryMap.keySet();
      return libraryNames.toArray(new String[0]);
    }
  }

  /**
   * Add a new sub library with the given folderName, in the given parent library.
   * 
   * @param library
   * @param folderName
   * @throws NameDuplicationException
   * @throws IllegalActionException
   */
  public void addSubLibrary(final EntityLibrary library, String folderName) throws NameDuplicationException, IllegalActionException {
    EntityLibrary subLibrary = new EntityLibrary(library, folderName);
    StringWriter buffer = new StringWriter();
    try {
      subLibrary.exportMoML(buffer, 1);
    } catch (IOException e) {
      // ignore, will never happen for a StringWriter
    }
    subLibrary.setName(folderName);
    ChangeRequest request = new MoMLChangeRequest(this, library, buffer.toString()) {
      @Override
      public NamedObj getLocality() {
        return library;
      }
    };
    request.addChangeListener(new EntityLibraryChangedListener(this));
    library.requestChange(request);
    refreshUserLibraryMap(configuration);
  }

  /**
   * Modify the name of the given library.
   * 
   * @param library
   * @param newName
   */
  public void renameLibrary(final EntityLibrary library, String newName) {
    String oldName = library.getName();
    StringBuffer moml = new StringBuffer("<");
    String elementName = library.getElementName();
    moml.append(elementName);
    moml.append(" name=\"");
    moml.append(oldName);
    moml.append("\">");
    if (!oldName.equals(newName)) {
      moml.append("<rename name=\"");
      moml.append(newName);
      moml.append("\"/>");
    }

    moml.append("</");
    moml.append(elementName);
    moml.append(">");

    NamedObj parent = library.getContainer();
    MoMLChangeRequest request = new MoMLChangeRequest(this, // originator
        parent, // context
        moml.toString(), // MoML code
        null) /* base */ {
      @Override
      public NamedObj getLocality() {
        return library;
      }
    };

    request.addChangeListener(new EntityLibraryChangedListener(this));
    request.setUndoable(true);
    parent.requestChange(request);
  }

  /**
   * Saves the given library in the file location from where it was originally loaded.
   * 
   * @param library
   */
  public void saveChangedEntityLibrary(EntityLibrary library) {
    FileWriter w = null;
    FileParameter libSourceFileParameter = (FileParameter) library.getAttribute(SOURCE_PATH_LIB_ATTR_NAME);
    if (libSourceFileParameter != null) {
      try {
        URL libURL = libSourceFileParameter.asURL();
        w = new FileWriter(libURL.getFile());
        library.exportMoML(w, 0);
        if (logger.isDebugEnabled())
          logger.debug("Saved modified library ");
      } catch (Exception e) {
        logger.error("Failed to save update in " + libSourceFileParameter, e);
        StatusManager.getManager().handle(new Status(IStatus.ERROR, TriqEditorPlugin.getID(), "Failed to save update in " + libSourceFileParameter, e),
            StatusManager.BLOCK);
      } finally {
        if (w != null)
          try {
            w.close();
          } catch (IOException e1) {
          }
      }
    } else if (library.getContainer() instanceof EntityLibrary) {
      // try to save the container (i.e. hope this library can be saved in
      // a moml of the container)
      saveChangedEntityLibrary((EntityLibrary) library.getContainer());
    } else {
      StatusManager.getManager()
          .handle(new Status(IStatus.ERROR, TriqEditorPlugin.getID(),
              "Library update not supported for library " + library.getName() + "\n" + SOURCE_PATH_LIB_ATTR_NAME + " attribute missing in library cfg file."),
              StatusManager.BLOCK);
    }
  }

  /**
   * Saves the given entity in the given library.
   * 
   * @param library
   * @param entity
   * @throws Exception
   */
  public void saveEntityInLibrary(EntityLibrary library, Entity<?> entity) throws Exception {
    // Check whether there is already something existing in the
    // library with this name.
    if (library.getEntity(entity.getName()) != null) {
      throw new Exception("An object with name " + entity.getName() + " already exists in the library " + library.getName());
    }

    Entity<?> entityAsClass = (Entity<?>) entity.clone(entity.workspace());
    entityAsClass.setClassDefinition(true);

    Entity<?> instance = (Entity<?>) entityAsClass.instantiate(library, entity.getName());
    instance.setClassName(entity.getClassName());

    StringWriter buffer = new StringWriter();
    try {
      instance.exportMoML(buffer, 1);
    } catch (IOException e) {
      // ignore, will never happen for a StringWriter
    }

    ChangeRequest request = new MoMLChangeRequest(instance, library, buffer.toString());
    request.addChangeListener(new EntityLibraryChangedListener(this));
    library.requestChange(request);
  }

  /**
   * Deletes the given entity from the given library.
   * 
   * @param library
   * @param entity
   */
  public void deleteEntityFromLibrary(final EntityLibrary library, final Entity<?> entity) {
    // Check whether there is already something existing in the
    // library with this name.
    if (library.getEntity(entity.getName()) == null) {
      StatusManager.getManager().handle(
          new Status(IStatus.WARNING, TriqEditorPlugin.getID(),
              "Delete from Library failed: An object with name " + entity.getName() + " does not exist in the library " + library.getName()),
          StatusManager.SHOW);
      return;
    }

    ChangeRequest request = new MoMLChangeRequest(this, library, "<deleteEntity name=\"" + entity.getName() + "\"/>\n") {
      @Override
      public NamedObj getLocality() {
        return userLibraryMap.get(USER_LIBRARY_NAME);
      }
    };
    request.addChangeListener(new EntityLibraryChangedListener(this));
    library.requestChange(request);
  }

  /**
   * @param libraries
   *          the parent libraries in which we should look for sublibraries
   * @param attrNames
   *          filter, i.e. the method will only return the libraries that have attributes defined with the given names
   * @return
   */
  private List<EntityLibrary> getDeepLibrariesWithAttributes(List<EntityLibrary> libraries, CollectingMomlParsingErrorHandler errorHandler,
      String... attrNames) {
    List<EntityLibrary> deepLibraries = new ArrayList<EntityLibrary>();
    for (EntityLibrary library : libraries) {
      // libraries are lazy loaders, so we need to call populate()
      // explicitly here!
      ErrorHandler prevErrHandler = MoMLParser.getErrorHandler();
      try {
        // ensure no error pop-ups with annoying stack traces
        // are happening while populating the libraries
        // remark that as a result, a library containing entity
        // definitions
        // that fail to load, will after this saving operation no longer
        // contain these failed ones!
        MoMLParser.setErrorHandler(errorHandler);
        library.populate();
      } catch (Exception e) {
        // catch any problems during populating
        logger.error("Error populating library " + library.getFullName() + " : " + e.getMessage());
      } finally {
        MoMLParser.setErrorHandler(prevErrHandler);
      }
      List<EntityLibrary> sublibs = library.entityList(EntityLibrary.class);
      if (sublibs != null && sublibs.size() > 0) {
        deepLibraries.addAll(getDeepLibrariesWithAttributes(sublibs, errorHandler, attrNames));
      }
      // also show intermediate levels if they have the right attributes
      boolean accepted = true;
      for (String attrName : attrNames) {
        if (library.getAttribute(attrName) == null) {
          accepted = false;
          break;
        }
      }
      if (accepted) {
        deepLibraries.add(library);
      }
    }

    return deepLibraries;
  }

  /**
   * Reacts on an ADD_EVENT_TOPIC by reading from the event the specification of an entity to be added, and adds that one
   * to the default user library.
   */
  @Override
  public void handleEvent(Event event) {
    String clazz = (String) event.getProperty("class");
    String name = (String) event.getProperty("displayName");
    String libraryName = (String) event.getProperty("libraryName");
    boolean isLibraryGiven = !StringUtils.isBlank(libraryName);
    EntityLibrary library = isLibraryGiven ? getUserLibrary(libraryName) : getUserLibrary();
    if (library == null && isLibraryGiven && libraryName.indexOf(USER_LIBRARY_NAME) == 1) {
      if (libraryName.length() > (USER_LIBRARY_NAME.length() + 2)) {
        // chop the possible leading User Library name
        libraryName = libraryName.substring(USER_LIBRARY_NAME.length() + 2);
        library = getUserLibrary(libraryName);
      } else {
        // libraryName is the User Library itself
        library = getUserLibrary();
      }
    }
    if (library == null) {
      logger.warn("No User Library found for event " + event);
      StatusManager.getManager().handle(
          new Status(IStatus.ERROR, TriqEditorPlugin.getID(),
              "No User Library found for " + event.getTopic() + " on " + name + (isLibraryGiven ? " with library " + libraryName : "")),
          StatusManager.BLOCK | StatusManager.LOG);
    } else {
      ComponentEntity existingEntry = library.getEntity(name);
      if (ADD_EVENT_TOPIC.equals(event.getTopic())) {
        try {
          // TODO check if we should not block adding a same class twice, i.o. checking mainly on the name.
          if (existingEntry != null) {
            StatusManager.getManager().handle(new Status(IStatus.INFO, TriqEditorPlugin.getID(), name + " already in the User Library"), StatusManager.BLOCK);
          } else if (!StringUtils.isBlank(clazz)) {
            Entity<?> addedActor = PtolemyUtil._createEntity(null, clazz, null, name);
            saveEntityInLibrary(library, addedActor);
          } else {
            // it's about a new subfolder in the UserLibrary structure
            addSubLibrary(library, name);
          }
        } catch (Exception e) {
          StatusManager.getManager().handle(new Status(IStatus.ERROR, TriqEditorPlugin.getID(), "Failed to add " + name + " in the User Library", e),
              StatusManager.BLOCK | StatusManager.LOG);
        }
      }
      if (DELETE_EVENT_TOPIC.equals(event.getTopic())) {
        try {
          if (existingEntry == null) {
            StatusManager.getManager().handle(new Status(IStatus.INFO, TriqEditorPlugin.getID(), name + " not in the User Library"), StatusManager.BLOCK);
          } else {
            deleteEntityFromLibrary(library, existingEntry);
          }
        } catch (Exception e) {
          StatusManager.getManager().handle(new Status(IStatus.ERROR, TriqEditorPlugin.getID(), "Failed to delete " + name + " from the User Library", e),
              StatusManager.BLOCK | StatusManager.LOG);
        }
      }
    }
  }

  public static class EntityLibraryChangedListener implements ChangeListener {
    private LibraryManager libraryManager;

    public EntityLibraryChangedListener(LibraryManager libraryManager) {
      this.libraryManager = libraryManager;
    }

    public void changeExecuted(ChangeRequest change) {
      EntityLibrary library = (EntityLibrary) ((MoMLChangeRequest) change).getContext();

      libraryManager.saveChangedEntityLibrary(library);
    }

    public void changeFailed(ChangeRequest change, Exception exception) {
      // TODO Auto-generated method stub

    }
  }

  private synchronized void activate() {
    try {
      File userHome = new File(System.getProperty("user.home"));
      File triqUserHome = new File(userHome, ".triquetrum");

      Configuration ptCfg = new Configuration(new Workspace());
      EntityLibrary actorLibrary = new EntityLibrary(ptCfg, "actor library");
      File userLibraryFile = new File(triqUserHome, "UserLibrary.xml");
      String userLibraryFilePath = userLibraryFile.toURI().toString();
      if (userLibraryFile.isFile()) {
        actorLibrary.configure(null, userLibraryFilePath, null);
      } else {
        new EntityLibrary(actorLibrary, USER_LIBRARY_NAME);
      }
      actorLibrary.setClassDefinition(true);
      refreshManagerCache(ptCfg);
      try {
        FileParameter fp = new FileParameter(getUserLibrary(), "_sourcePath");
        fp.setExpression(userLibraryFilePath);
        fp.setPersistent(false);
      } catch (NameDuplicationException e) {
        // ignore as this would imply that the sourcePath is already present, which is fine.
      }
      TriqEditorPlugin.getDefault().registerEventHandler(this, EVENT_TOPIC_PATTERN);
      instance = this;
    } catch (IllegalActionException | NameDuplicationException e) {
      logger.warn(ErrorCode.WARN + " - Error activating the LibraryManager", e);
    }
  }

  /**
   * 
   * @param configuration
   */
  private void refreshUserLibraryMap(Configuration configuration) {
    if (userLibraryMap == null) {
      userLibraryMap = new TreeMap<String, EntityLibrary>();
    } else {
      userLibraryMap.clear();
    }
    CompositeEntity topLibrary = (CompositeEntity) configuration.getEntity(ACTOR_LIBRARY_NAME);
    if (topLibrary != null) {
      EntityLibrary userLibrary = (EntityLibrary) topLibrary.getEntity(USER_LIBRARY_NAME);
      if (userLibrary != null) {
        // during start-up the userlibrary may not yet be in the configuration...
        userLibraryMap.put(USER_LIBRARY_NAME, userLibrary);
        List<EntityLibrary> libraries = userLibrary.entityList(EntityLibrary.class);
        CollectingMomlParsingErrorHandler errorHandler = new CollectingMomlParsingErrorHandler();

        // no filter needed on sourcepath attr or whatever
        // userlibrary sublibraries are maintained in the 1 single UserLibrary.xml file
        List<EntityLibrary> deepLibraries = getDeepLibrariesWithAttributes(libraries, errorHandler);
        for (EntityLibrary lib : deepLibraries) {
          String name = lib.getFullName().substring(ACTORS_LIBRARY_PREFIX_LENGTH);
          userLibraryMap.put(name, lib);
        }

        if (errorHandler.hasErrors()) {
          MultiStatus status = new MultiStatus(TriqEditorPlugin.getID(), IStatus.ERROR, "Some Library entries could not be constructed.", null);
          for (ErrorItem errorItem : errorHandler) {
            status.add(new Status(IStatus.ERROR, TriqEditorPlugin.getID(), "Error populating library " + userLibrary.getFullName(), errorItem.exception));
          }
          StatusManager.getManager().handle(status, StatusManager.BLOCK);
        }
      }
    }
  }
}
