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
package org.eclipse.triquetrum.workflow.repository.impl.filesystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.eclipse.triquetrum.workflow.DuplicateEntryException;
import org.eclipse.triquetrum.workflow.EntryNotFoundException;
import org.eclipse.triquetrum.workflow.ErrorCode;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.eclipse.triquetrum.workflow.WorkflowRepositoryService;
import org.ptolemy.commons.ThreeDigitVersionSpecification;
import org.ptolemy.commons.VersionSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.actor.CompositeActor;

/**
 * Stores flows on local disk in a configurable root folder.
 * <p>
 * Each flow is stored in a subdirectory with the flow's name. Within each flow's directory, separate subdirectories are maintained per version.
 * </p>
 * TODO analyse if we want to support hierarchy, i.e. deep folder structures.
 * E.g. to support AOCs with fully-qualified class names like "ptolemy.actor.lib.Sinewave" etc.
 * For the moment we assume these are mapped to single-level subfolders with a composite folder name.
 */
public class WorkflowRepositoryServiceImpl implements WorkflowRepositoryService {

  private static final String VERSION_MOSTRECENT = "version.mostrecent";
  private static final String VERSION_ACTIVE = "version.active";
  private final static Logger LOGGER = LoggerFactory.getLogger(WorkflowRepositoryServiceImpl.class);

  private static final class DirectoryFilter implements FileFilter {
    @Override
    public boolean accept(File fileOrFolder) {
      return fileOrFolder.isDirectory();
    }
  }

  private File rootFolder;

  public WorkflowRepositoryServiceImpl(String rootFolderPath) {
    setRootFolder(new File(rootFolderPath));
  }

  public WorkflowRepositoryServiceImpl(File rootFolder) {
    setRootFolder(rootFolder);
  }

  public void setRootFolder(File rootFolder) {
    LOGGER.info("Pointing FlowRepositoryService to folder {}", rootFolder);
    this.rootFolder = rootFolder;
    if (!rootFolder.exists() && !rootFolder.mkdirs()) {
      throw new RuntimeException(new IOException(rootFolder.getPath() + " could not be created"));
    } else if (!rootFolder.isDirectory()) {
      throw new IllegalArgumentException(rootFolder.getPath() + " is not a folder");
    }
  }

  public void clearRepository() {
    LOGGER.trace("clearRepository() - entry");
    try {
      LOGGER.info("Clearing repository {}", rootFolder);
      FileUtils.deleteDirectory(rootFolder);
      if (!rootFolder.mkdirs()) {
        LOGGER.error(org.eclipse.triquetrum.ErrorCode.ERROR + " - Failed to recreate clean repository directory");
      }
    } catch (IOException e) {
      LOGGER.error(org.eclipse.triquetrum.ErrorCode.ERROR + " - Failed to clear repository directory", e);
    } finally {
      LOGGER.trace("clearRepository() - exit");
    }
  }

  @Override
  public ModelHandle commit(CompositeActor flow) throws DuplicateEntryException {
    return commit(flow.getName(), flow);
  }

  @Override
  public ModelHandle commit(String flowCode, CompositeActor flow) throws DuplicateEntryException {
    LOGGER.trace("commit() - entry : {} {}", flowCode, flow);
    ModelHandle flowHandle = null;
    try {
      File newFlowFolder = new File(rootFolder, flowCode);
      if (newFlowFolder.exists()) {
        throw new DuplicateEntryException(flowCode);
      } else {
        VersionSpecification vSpec = new ThreeDigitVersionSpecification(1, 0, 0);
        File versionFolder = new File(newFlowFolder, vSpec.toString());
        if (!versionFolder.mkdirs()) {
          throw new RuntimeException(new IOException("Error creating folder " + versionFolder));
        }
        File destinationFile = new File(versionFolder, flow.getName() + ".moml");
        if ((!destinationFile.exists() || destinationFile.canWrite())) {
          BufferedWriter outputWriter = null;
          try {
            outputWriter = new BufferedWriter(new FileWriterWithEncoding(destinationFile, StandardCharsets.UTF_8));
            flow.exportMoML(outputWriter);
            flowHandle = new ModelHandleImpl(flowCode, vSpec, destinationFile.toURI(), flow);
            writeMetaData(flowCode, VERSION_ACTIVE, vSpec.toString());
            writeMetaData(flowCode, VERSION_MOSTRECENT, vSpec.toString());
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (EntryNotFoundException e) {
            // should not happen
          } finally {
            if (outputWriter != null) {
              try {
                outputWriter.flush();
                outputWriter.close();
              } catch (Exception e) {
                // ignore
              }
            }
          }
          return flowHandle;
        } else {
          throw new RuntimeException(new IOException("File not writable " + destinationFile));
        }
      }
    } finally {
      LOGGER.trace("commit() - exit : {}", flowHandle);
    }
  }

  @Override
  public ModelHandle[] delete(String flowCode) throws EntryNotFoundException {
    LOGGER.trace("delete() - entry : {}", flowCode);
    try {
      ModelHandle[] results = getAllModelRevisions(flowCode);
      try {
        FileUtils.deleteDirectory(new File(rootFolder, flowCode));
      } catch (IOException e) {
        LOGGER.error(org.eclipse.triquetrum.ErrorCode.ERROR + " - Failed to delete " + flowCode, e);
      }
      return results;
    } finally {
      LOGGER.trace("delete() - exit : {}", flowCode);
    }
  }

  @Override
  public ModelHandle update(ModelHandle handle, CompositeActor updatedFlow, boolean activate) throws EntryNotFoundException {
    LOGGER.trace("update() - entry : {} updated flow : {} activate : {}", new Object[] { handle, updatedFlow, activate });
    ModelHandle updatedHandle = null;
    try {
      String flowCode = handle.getCode();
      File flowRootFolder = new File(rootFolder, flowCode);
      if (!flowRootFolder.isDirectory()) {
        throw new EntryNotFoundException(ErrorCode.MODEL_SAVING_ERROR_FUNC, "Flow code unknown " + flowCode);
      } else {
        ThreeDigitVersionSpecification vSpec = ((ThreeDigitVersionSpecification) handle.getVersion()).increaseMinor();
        File versionFolder = new File(flowRootFolder, vSpec.toString());
        while (versionFolder.exists()) {
          vSpec = vSpec.increaseMinor();
          versionFolder = new File(flowRootFolder, vSpec.toString());
        }
        if (!versionFolder.mkdirs()) {
          throw new RuntimeException(new IOException("Error creating folder " + versionFolder));
        }
        File destinationFile = new File(versionFolder, updatedFlow.getName() + ".moml");
        if ((!destinationFile.exists() || destinationFile.canWrite())) {
          BufferedWriter outputWriter = null;
          try {
            outputWriter = new BufferedWriter(new FileWriterWithEncoding(destinationFile, StandardCharsets.UTF_8));
            updatedFlow.exportMoML(outputWriter);
            updatedHandle = new ModelHandleImpl(flowCode, vSpec, destinationFile.toURI(), updatedFlow);
            writeMetaData(flowCode, VERSION_MOSTRECENT, vSpec.toString());
            if (activate) {
              activateModelRevision(updatedHandle);
            }
          } catch (IOException e) {
            throw new RuntimeException(e);
          } finally {
            if (outputWriter != null) {
              try {
                outputWriter.flush();
                outputWriter.close();
              } catch (Exception e) {
                // ignore
              }
            }
          }
          return updatedHandle;
        } else {
          throw new RuntimeException(new IOException("File not writable " + destinationFile));
        }
      }
    } finally {
      LOGGER.trace("update() - exit : {}", updatedHandle);
    }
  }

  @Override
  public ModelHandle activateModelRevision(ModelHandle handle) throws EntryNotFoundException {
    LOGGER.trace("activateModelRevision() - entry : {}", handle);
    try {
      return writeMetaData(handle.getCode(), VERSION_ACTIVE, handle.getVersion().toString());
    } catch (IOException e) {
      throw new RuntimeException("Error writing activation data", e);
    } finally {
      LOGGER.trace("activateModelRevision() - exit : {}", handle);
    }
  }

  @Override
  public boolean isActiveModelRevision(ModelHandle handle) {
    LOGGER.trace("isActiveModelRevision() - entry : {}", handle);
    boolean result = false;
    try {
      if (handle != null) {
        result = getActiveModel(handle.getCode()).equals(handle);
      }
      return result;
    } catch (EntryNotFoundException e) {
      return result;
    } finally {
      LOGGER.trace("isActiveModelRevision() - exit : {} : {}", handle, result);
    }
  }

  @Override
  public ModelHandle getActiveModel(String flowCode) throws EntryNotFoundException {
    LOGGER.trace("getActiveModel() - entry : {}", flowCode);
    ModelHandle flow = null;
    try {
      File flowRootFolder = new File(rootFolder, flowCode);
      if (!flowRootFolder.isDirectory()) {
        throw new EntryNotFoundException("Invalid flow code " + flowCode);
      } else {
        Properties metaData = readMetaData(flowCode);
        String activeVersion = metaData.getProperty(VERSION_ACTIVE);
        flow = readAndBuildModelHandle(flowRootFolder.getName(), activeVersion);
        return flow;
      }
    } finally {
      LOGGER.trace("getActiveModel() - exit : {}", flow);
    }
  }

  @Override
  public ModelHandle getMostRecentModel(String flowCode) throws EntryNotFoundException {
    LOGGER.trace("getMostRecentModel() - entry : {}", flowCode);
    ModelHandle flow = null;
    try {
      File flowRootFolder = new File(rootFolder, flowCode);
      if (!flowRootFolder.isDirectory()) {
        throw new EntryNotFoundException("Invalid flow code " + flowCode);
      } else {
        Properties metaData = readMetaData(flowCode);
        String mostRecentVersion = metaData.getProperty(VERSION_MOSTRECENT);
        flow = readAndBuildModelHandle(flowRootFolder.getName(), new File(flowRootFolder, mostRecentVersion));
        return flow;
      }
    } finally {
      LOGGER.trace("getMostRecentModel() - exit : {}", flow);
    }
  }

  @Override
  public ModelHandle getModelVersion(String flowCode, VersionSpecification version) throws EntryNotFoundException {
    LOGGER.trace("getModelVersion() - entry : {} - {}", flowCode, version);
    ModelHandle flow = null;
    try {
      File flowRootFolder = new File(rootFolder, flowCode);
      if (!flowRootFolder.isDirectory()) {
        throw new EntryNotFoundException("Invalid flow code " + flowCode);
      } else {
        String requestedVersion = version.toString();
        flow = readAndBuildModelHandle(flowRootFolder.getName(), new File(flowRootFolder, requestedVersion));
        if (flow == null) {
          throw new EntryNotFoundException("Version " + requestedVersion + " not found for flow code " + flowCode);
        } else {
          return flow;
        }
      }
    } finally {
      LOGGER.trace("getModelVersion() - exit : {}", flow);
    }
  }

  @Override
  public ModelHandle loadModelHandleWithContent(ModelHandle handle) throws EntryNotFoundException {
    return getModelVersion(handle.getCode(), handle.getVersion());
  }

  @Override
  public String[] getAllModelCodes() {
    LOGGER.trace("getAllModelCodes() - entry");
    String[] results = null;
    try {
      File[] subFolders = rootFolder.listFiles(new DirectoryFilter());
      if (subFolders != null) {
        String[] flowCodes = new String[subFolders.length];
        for (int i = 0; i < subFolders.length; ++i) {
          flowCodes[i] = subFolders[i].getName();
        }
        results = flowCodes;
      } else {
        results = new String[0];
      }
      return results;
    } finally {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("getAllModelCodes() - exit : " + results);
      }
    }
  }

  @Override
  public ModelHandle[] getAllModelRevisions(String flowCode) throws EntryNotFoundException {
    LOGGER.trace("getAllModelRevisions() - entry : {}", flowCode);
    ArrayList<ModelHandle> results = null;
    try {
      File codeFolder = new File(rootFolder, flowCode);
      if (!codeFolder.isDirectory()) {
        throw new EntryNotFoundException("Invalid flow code " + flowCode);
      } else {
        results = new ArrayList<>();
        File[] versionFolders = codeFolder.listFiles(new DirectoryFilter());
        // prophylactic null check to keep FindBugs happy
        if (versionFolders != null) {
          for (File versionFolder : versionFolders) {
            ModelHandle fh = readAndBuildModelHandle(flowCode, versionFolder);
            if (fh != null) {
              results.add(fh);
            }
          }
        }
        return results.toArray(new ModelHandle[results.size()]);
      }
    } finally {
      LOGGER.trace("getAllModelRevisions() - exit : {} {}", flowCode, results);
    }
  }

  @Override
  public String toString() {
    return rootFolder.toURI().toString();
  }

  private ModelHandle readAndBuildModelHandle(String code, String version) {
    return readAndBuildModelHandle(code, new File(new File(rootFolder, code), version));
  }

  private ModelHandle readAndBuildModelHandle(String code, File versionFolder) {
    if (!versionFolder.isDirectory()) {
      return null;
    } else {
      try {
        VersionSpecification vSpec = VersionSpecification.parse(versionFolder.getName());
        File[] modelFiles = versionFolder.listFiles(new FilenameFilter() {
          @Override
          public boolean accept(File dir, String name) {
            return name.endsWith("moml") || name.endsWith("xml");
          }
        });
        if (modelFiles != null && modelFiles.length > 0) {
          File modelFile = modelFiles[0];
          return new ModelHandleImpl(code, vSpec, modelFile.toURI(), null);
        } else {
          LOGGER.warn(ErrorCode.MODEL_LOADING_ERROR + " - No model file found for " + code + " in " + versionFolder);
          return null;
        }
      } catch (Exception e) {
        LOGGER.warn(ErrorCode.MODEL_LOADING_ERROR + " - Error building model handle for " + code + " in " + versionFolder, e);
        return null;
      }
    }
  }

  private ModelHandle writeMetaData(String flowCode, String dataItemName, String dataItemValue) throws IOException, EntryNotFoundException {
    ModelHandle previouslyActive = null;
    Properties flowMetaDataProps = readMetaData(flowCode);
    String activeVersion = flowMetaDataProps.getProperty(dataItemName);
    if (activeVersion != null) {
      previouslyActive = readAndBuildModelHandle(flowCode, activeVersion);
    }
    flowMetaDataProps.setProperty(dataItemName, dataItemValue);
    writeMetaData(flowCode, flowMetaDataProps);
    return previouslyActive;
  }

  /**
   * @param flowCode
   * @param flowMetaDataProps
   * @throws IOException
   */
  private void writeMetaData(String flowCode, Properties flowMetaDataProps) throws IOException {
    File flowRootFolder = new File(rootFolder, flowCode);
    File metaDataFile = new File(flowRootFolder, ".metadata");
    Writer metaDataWriter = new FileWriterWithEncoding(metaDataFile, StandardCharsets.UTF_8);
    try {
      flowMetaDataProps.store(metaDataWriter, flowCode);
    } finally {
      metaDataWriter.close();
    }
  }

  /**
   * @param flowCode
   * @return metadata props for the given flow; returns empty properties when no metadata is found
   * @throws EntryNotFoundException
   */
  private Properties readMetaData(String flowCode) throws EntryNotFoundException {
    Properties flowMetaDataProps = new Properties();
    File flowRootFolder = new File(rootFolder, flowCode);
    File metaDataFile = new File(flowRootFolder, ".metadata");
    if (!flowRootFolder.isDirectory()) {
      throw new EntryNotFoundException("Flow not managed by this repository " + flowCode);
    } else {
      if (metaDataFile.exists()) {
        Reader metaDataReader = null;
        try {
          // use this complicated construction i.o. plain FileReader, to keep FindBugs "as happy as Pharell Williams"
          // cfr https://myshittycode.com/2014/03/26/findbug-solving-dm_default_encoding-warning-when-using-filewriter/
          // it seems there's no FileReaderWithEncoding in commons IO...
          metaDataReader = new InputStreamReader(new FileInputStream(metaDataFile), Charset.forName("UTF-8").newDecoder());
          flowMetaDataProps.load(metaDataReader);
        } catch (Exception e) {

        } finally {
          if (metaDataReader != null) {
            try {
              metaDataReader.close();
            } catch (Exception e) {
            }
          }
        }
      }
      return flowMetaDataProps;
    }
  }
}
