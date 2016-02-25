/*******************************************************************************
 * Copyright (c) 2015-2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.repository.impl.filesystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
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
 *
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
    this(new File(rootFolderPath));
  }

  public WorkflowRepositoryServiceImpl(File rootFolder) {
    LOGGER.info("Creating FlowRepositoryService on folder {}", rootFolder);
    this.rootFolder = rootFolder;
    if (!rootFolder.exists()) {
      rootFolder.mkdirs();
    } else if (!rootFolder.isDirectory()) {
      throw new IllegalArgumentException(rootFolder.getPath() + " is not a folder");
    }
  }

  public void clearRepository() {
    try {
      FileUtils.deleteDirectory(rootFolder);
      rootFolder.mkdirs();
    } catch (IOException e) {
      LOGGER.error(ErrorCode.ERROR + " - Failed to clear repository directory", e);
    }
  }

  @Override
  public ModelHandle commit(CompositeActor flow) throws DuplicateEntryException {
    return commit(flow.getName(), flow);
  }

  @Override
  public ModelHandle commit(String flowCode, CompositeActor flow) throws DuplicateEntryException {
    File newFlowFolder = new File(rootFolder, flowCode);
    if (newFlowFolder.exists()) {
      throw new DuplicateEntryException(flowCode);
    } else {
      ModelHandle flowHandle = null;
      VersionSpecification vSpec = new ThreeDigitVersionSpecification(1, 0, 0);
      File versionFolder = new File(newFlowFolder, vSpec.toString());
      versionFolder.mkdirs();
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
  }

  @Override
  public ModelHandle[] delete(String flowCode) throws EntryNotFoundException {
    ModelHandle[] results = getAllModelRevisions(flowCode);
    try {
      FileUtils.deleteDirectory(new File(rootFolder, flowCode));
    } catch (IOException e) {
      LOGGER.error(ErrorCode.ERROR + " - Failed to delete " + flowCode, e);
    }
    return results;
  }

  @Override
  public ModelHandle update(ModelHandle handle, CompositeActor updatedFlow, boolean activate) throws EntryNotFoundException {
    String flowCode = handle.getCode();
    File flowRootFolder = new File(rootFolder, flowCode);
    if (!flowRootFolder.isDirectory()) {
      throw new EntryNotFoundException(ErrorCode.MODEL_SAVING_ERROR_FUNC, "Flow code unknown " + flowCode);
    } else {
      ModelHandle flowHandle = null;
      ThreeDigitVersionSpecification vSpec = ((ThreeDigitVersionSpecification)handle.getVersion()).increaseMinor();
      File versionFolder = new File(flowRootFolder, vSpec.toString());
      while(versionFolder.exists()) {
        vSpec = vSpec.increaseMinor();
        versionFolder = new File(flowRootFolder, vSpec.toString());
      }
      versionFolder.mkdirs();
      File destinationFile = new File(versionFolder, updatedFlow.getName() + ".moml");
      if ((!destinationFile.exists() || destinationFile.canWrite())) {
        BufferedWriter outputWriter = null;
        try {
          outputWriter = new BufferedWriter(new FileWriter(destinationFile));
          updatedFlow.exportMoML(outputWriter);
          flowHandle = new ModelHandleImpl(flowCode, vSpec, destinationFile.toURI(), updatedFlow);
          writeMetaData(flowCode, VERSION_MOSTRECENT, vSpec.toString());
          if(activate) {
            activateModelRevision(flowHandle);
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
        return flowHandle;
      } else {
        throw new RuntimeException(new IOException("File not writable " + destinationFile));
      }
    }
  }

  @Override
  public ModelHandle getActiveModel(String flowCode) throws EntryNotFoundException {
    File flowRootFolder = new File(rootFolder, flowCode);
    if (!flowRootFolder.isDirectory()) {
      throw new EntryNotFoundException("Invalid flow code " + flowCode);
    } else {
      ModelHandle flow = null;
      Properties metaData = readMetaData(flowCode);
      String activeVersion = metaData.getProperty(VERSION_ACTIVE);
      flow = readAndBuildModelHandle(flowRootFolder.getName(), activeVersion);
      return flow;
    }
  }

  @Override
  public ModelHandle getMostRecentModel(String flowCode) throws EntryNotFoundException {
    File flowRootFolder = new File(rootFolder, flowCode);
    if (!flowRootFolder.isDirectory()) {
      throw new EntryNotFoundException("Invalid flow code " + flowCode);
    } else {
      ModelHandle flow = null;
      Properties metaData = readMetaData(flowCode);
      String mostRecentVersion = metaData.getProperty(VERSION_MOSTRECENT);
      flow = readAndBuildModelHandle(flowRootFolder.getName(), new File(flowRootFolder, mostRecentVersion));
      return flow;
    }
  }

  @Override
  public ModelHandle getModelVersion(String flowCode, VersionSpecification version) throws EntryNotFoundException {
    File flowRootFolder = new File(rootFolder, flowCode);
    if (!flowRootFolder.isDirectory()) {
      throw new EntryNotFoundException("Invalid flow code " + flowCode);
    } else {
      ModelHandle flow = null;
      String requestedVersion = version.toString();
      flow = readAndBuildModelHandle(flowRootFolder.getName(), new File(flowRootFolder, requestedVersion));
      if(flow==null) {
        throw new EntryNotFoundException("Version " + requestedVersion + " not found for flow code " + flowCode);
      } else {
        return flow;
      }
    }
  }

  @Override
  public ModelHandle loadModelHandleWithContent(ModelHandle handle) throws EntryNotFoundException {
    return getModelVersion(handle.getCode(), handle.getVersion());
  }

  @Override
  public String[] getAllModelCodes() {
    File[] subFolders = rootFolder.listFiles(new DirectoryFilter());
    String[] flowCodes = new String[subFolders.length];
    for (int i = 0; i < subFolders.length; ++i) {
      flowCodes[i] = subFolders[i].getName();
    }
    return flowCodes;
  }

  @Override
  public ModelHandle[] getAllModelRevisions(String flowCode) throws EntryNotFoundException {
    File codeFolder = new File(rootFolder, flowCode);
    if (!codeFolder.isDirectory()) {
      throw new EntryNotFoundException("Invalid flow code " + flowCode);
    } else {
      ArrayList<ModelHandle> results = new ArrayList<ModelHandle>();
      File[] versionFolders = codeFolder.listFiles(new DirectoryFilter());
      for (File versionFolder : versionFolders) {
        ModelHandle fh = readAndBuildModelHandle(flowCode, versionFolder);
        if (fh != null) {
          results.add(fh);
        }
      }
      return results.toArray(new ModelHandle[results.size()]);
    }
  }

  @Override
  public ModelHandle activateModelRevision(ModelHandle handle) throws EntryNotFoundException {
    try {
      return writeMetaData(handle.getCode(), VERSION_ACTIVE, handle.getVersion().toString());
    } catch (IOException e) {
      throw new RuntimeException("Error writing activation data", e);
    }
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
        File modelFile = modelFiles[0];
        return new ModelHandleImpl(code, vSpec, modelFile.toURI(), null);
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
    File metaDataFile2 = new File(flowRootFolder, ".metadata");
    Writer metaDataWriter = new FileWriter(metaDataFile2);
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
          metaDataReader = new FileReader(metaDataFile);
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
