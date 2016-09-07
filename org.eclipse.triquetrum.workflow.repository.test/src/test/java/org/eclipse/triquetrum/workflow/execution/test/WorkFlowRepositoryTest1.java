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
package org.eclipse.triquetrum.workflow.execution.test;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.triquetrum.workflow.DuplicateEntryException;
import org.eclipse.triquetrum.workflow.EntryNotFoundException;
import org.eclipse.triquetrum.workflow.ModelHandle;
import org.eclipse.triquetrum.workflow.WorkflowRepositoryService;
import org.eclipse.triquetrum.workflow.repository.impl.filesystem.WorkflowRepositoryServiceImpl;
import org.eclipse.triquetrum.workflow.util.WorkflowUtils;
import org.ptolemy.commons.VersionSpecification;

import junit.framework.TestCase;
import ptolemy.actor.CompositeActor;
import ptolemy.actor.TypedCompositeActor;
import ptolemy.actor.lib.Const;
import ptolemy.actor.lib.Discard;
import ptolemy.domains.sdf.kernel.SDFDirector;

public class WorkFlowRepositoryTest1 extends TestCase {

  private static final String HELLO_WORLD_FLOWNAME = "HelloWorld";
  private static final String HELLO_CODE = "HELLO";
  private static final String HELLO_CODE2 = "HELLO2";
  private static final String HELLO_CODE3 = "HELLO3";

  private static final File userHome = new File(System.getProperty("user.home"));
  private static final File defaultRootFolderPath = new File(userHome, ".triquetrum/workflow-repository");
  private static final String REPOS_ROOTFOLDER = System.getProperty("org.eclipse.triquetrum.workflow.repository.root", defaultRootFolderPath.getAbsolutePath());

  public static WorkflowRepositoryService repositoryService;

  @Override
  protected void setUp() throws Exception {
    if (repositoryService == null) {
      File repositoryRootFolder = new File(REPOS_ROOTFOLDER);
      FileUtils.deleteDirectory(repositoryRootFolder);
      repositoryService = new WorkflowRepositoryServiceImpl(repositoryRootFolder);
    } else {
      // this is a bit of a hack, assuming that when we run this test on a REST client facade,
      // the system property for the backing repos root folder has been set to the same location
      // for the server-side and this test-client-side.
      // we need to ensure the repos is cleared before each test, to avoid DuplicateEntryExceptions....
      File repositoryRootFolder = new File(REPOS_ROOTFOLDER);
      FileUtils.deleteDirectory(repositoryRootFolder);
      repositoryRootFolder.mkdirs();
    }
  }

  public void testCommitFlowForCode() throws Exception {
    try {
      ModelHandle handle = repositoryService.commit(HELLO_CODE, buildTrivialFlow(HELLO_WORLD_FLOWNAME));
      assertNotNull("A non-null handle should be returned", handle);
      assertEquals("Wrong flow code", HELLO_CODE, handle.getCode());
      assertEquals("Wrong version spec", VersionSpecification.parse("1.0.0"), handle.getVersion());
      assertNotNull("Flow's commit resource location should be not-null", handle.getResourceLocation());
      assertEquals("Wrong flow name", HELLO_WORLD_FLOWNAME, handle.getModel().getName());
      assertNotNull("Committed flow shas lost its director", handle.getModel().getDirector());
      assertNotNull("MOML should be not null", handle.getRawModelDefinition());
      assertFalse("MOML should be not empty", handle.getRawModelDefinition().isEmpty());
    } catch (DuplicateEntryException e) {
      fail("First flow commit should not fail with DuplicatEntryException");
    }
  }

  public void testDelete() throws Exception {
    ModelHandle handle = repositoryService.commit(HELLO_CODE, buildTrivialFlow(HELLO_WORLD_FLOWNAME));
    ModelHandle[] handles = repositoryService.delete(HELLO_CODE);
    assertNotNull("Handles from delete() should be not-null", handles);
    assertEquals("Handles from delete() should contain one entry", 1, handles.length);
    assertEquals("Handles from delete() should contain committed flow", handle, handles[0]);
    try {
      repositoryService.getAllModelRevisions(HELLO_CODE);
      fail("After deletion, no revisions should be found anymore");
    } catch (EntryNotFoundException e) {
      // this is what is expected
    }
  }

  public void testGetActiveFlow() throws Exception {
    ModelHandle commitHandle = repositoryService.commit(HELLO_CODE, buildTrivialFlow(HELLO_WORLD_FLOWNAME));
    ModelHandle activeHandle = repositoryService.getActiveModel(HELLO_CODE);

    assertEquals("Committed flow not returned as active flow", commitHandle, activeHandle);
  }

  public void testMostRecentFlowAfterCommit() throws Exception {
    ModelHandle commitHandle = repositoryService.commit(HELLO_CODE, buildTrivialFlow(HELLO_WORLD_FLOWNAME));
    ModelHandle mostRecentHandle = repositoryService.getMostRecentModel(HELLO_CODE);

    assertEquals("Committed flow not returned as most recent flow", commitHandle, mostRecentHandle);
  }

  public void testMostRecentAfterUpdateWithActivate() throws Exception {
    ModelHandle commitHandle = repositoryService.commit(HELLO_CODE, buildTrivialFlow(HELLO_WORLD_FLOWNAME));

    Map<String, String> paramOverrides = new HashMap<>();
    paramOverrides.put("const.value", "12345");
    CompositeActor f = WorkflowUtils.applyParameterSettings(commitHandle, null, paramOverrides);

    ModelHandle updatedHandle = repositoryService.update(commitHandle, f, true);
    ModelHandle mostRecentHandle = repositoryService.getMostRecentModel(HELLO_CODE);

    assertFalse("Most recent handle should not be the originally committed one", mostRecentHandle.equals(commitHandle));
    assertEquals("Most recent handle should be the updated one", updatedHandle, mostRecentHandle);
  }

  public void testMostRecentAfterUpdateWithoutActivate() throws Exception {
    ModelHandle commitHandle = repositoryService.commit(HELLO_CODE, buildTrivialFlow(HELLO_WORLD_FLOWNAME));

    Map<String, String> paramOverrides = new HashMap<>();
    paramOverrides.put("const.value", "12345");
    CompositeActor f = WorkflowUtils.applyParameterSettings(commitHandle, null, paramOverrides);

    ModelHandle updatedHandle = repositoryService.update(commitHandle, f, false);
    ModelHandle mostRecentHandle = repositoryService.getMostRecentModel(HELLO_CODE);

    assertFalse("Most recent handle should not be the originally committed one", mostRecentHandle.equals(commitHandle));
    assertEquals("Most recent handle should be the updated one", updatedHandle, mostRecentHandle);
  }

  public void testGetAllFlowCodes() throws Exception {
    repositoryService.commit(HELLO_CODE, buildTrivialFlow(HELLO_WORLD_FLOWNAME));
    repositoryService.commit(HELLO_CODE2, buildTrivialFlow(HELLO_WORLD_FLOWNAME));
    repositoryService.commit(HELLO_CODE3, buildTrivialFlow(HELLO_WORLD_FLOWNAME));
    repositoryService.commit(buildTrivialFlow(HELLO_WORLD_FLOWNAME));

    String[] allFlowCodes = repositoryService.getAllModelCodes();
    assertEquals("Repository should know 4 codes", 4, allFlowCodes.length);
    List<String> codesAsList = Arrays.asList(allFlowCodes);
    assertTrue("Repository should know " + HELLO_CODE, codesAsList.contains(HELLO_CODE));
    assertTrue("Repository should know " + HELLO_CODE2, codesAsList.contains(HELLO_CODE2));
    assertTrue("Repository should know " + HELLO_CODE3, codesAsList.contains(HELLO_CODE3));
    assertTrue("Repository should know " + HELLO_WORLD_FLOWNAME, codesAsList.contains(HELLO_WORLD_FLOWNAME));
  }

  public void testUpdateAndActivation() throws Exception {
    repositoryService.commit(HELLO_CODE, buildTrivialFlow(HELLO_WORLD_FLOWNAME));
    ModelHandle activeHandle = repositoryService.getActiveModel(HELLO_CODE);

    Map<String, String> paramOverrides = new HashMap<>();
    paramOverrides.put("const.value", "12345");
    CompositeActor f = WorkflowUtils.applyParameterSettings(activeHandle, null, paramOverrides);

    ModelHandle updatedHandle = repositoryService.update(activeHandle, f, true);
    ModelHandle activeHandle2 = repositoryService.getActiveModel(HELLO_CODE);

    assertFalse("Updated handle should not be the previously active one", activeHandle.equals(updatedHandle));
    assertEquals("Updated handle should be the new active one", activeHandle2, updatedHandle);
    assertEquals("Code should remain the same for an update", activeHandle.getCode(), updatedHandle.getCode());
    assertTrue("Version must have increased after update", updatedHandle.getVersion().compareTo(activeHandle.getVersion()) > 0);
  }

  public void testUpdateAndLaterActivation() throws Exception {
    repositoryService.commit(HELLO_CODE, buildTrivialFlow(HELLO_WORLD_FLOWNAME));
    ModelHandle activeHandle = repositoryService.getActiveModel(HELLO_CODE);

    Map<String, String> paramOverrides = new HashMap<>();
    paramOverrides.put("const.value", "12345");
    CompositeActor f = WorkflowUtils.applyParameterSettings(activeHandle, null, paramOverrides);

    ModelHandle updatedHandle = repositoryService.update(activeHandle, f, false);

    ModelHandle previouslyActiveHandle = repositoryService.activateModelRevision(updatedHandle);
    ModelHandle activeHandle2 = repositoryService.getActiveModel(HELLO_CODE);

    assertFalse("Updated handle should not be the previously active one", activeHandle.equals(updatedHandle));
    assertEquals("Previously active handle should be the originally committed one", activeHandle, previouslyActiveHandle);
    assertEquals("Updated handle should be the new active one", activeHandle2, updatedHandle);
    assertEquals("Code should remain the same for an update followed by an activation", activeHandle.getCode(), updatedHandle.getCode());
    assertTrue("Version must have increased after update", updatedHandle.getVersion().compareTo(activeHandle.getVersion()) > 0);
  }

  public void testUpdateWithoutActivation() throws Exception {
    repositoryService.commit(HELLO_CODE, buildTrivialFlow(HELLO_WORLD_FLOWNAME));
    ModelHandle activeHandle = repositoryService.getActiveModel(HELLO_CODE);

    Map<String, String> paramOverrides = new HashMap<>();
    paramOverrides.put("const.value", "12345");
    CompositeActor f = WorkflowUtils.applyParameterSettings(activeHandle, null, paramOverrides);

    ModelHandle updatedHandle = repositoryService.update(activeHandle, f, false);
    ModelHandle activeHandle2 = repositoryService.getActiveModel(HELLO_CODE);

    assertFalse("Updated handle should not be the previously active one", activeHandle.equals(updatedHandle));
    assertEquals("Code should remain the same for an update", activeHandle.getCode(), updatedHandle.getCode());
    assertTrue("Version must have increased after update", updatedHandle.getVersion().compareTo(activeHandle.getVersion()) > 0);
    assertEquals("Active flow should not have changed", activeHandle, activeHandle2);
  }

  public TypedCompositeActor buildTrivialFlow(String wfName) throws Exception {
    TypedCompositeActor compositeActor = new TypedCompositeActor();
    compositeActor.setName(wfName);
    compositeActor.setDirector(new SDFDirector(compositeActor, "director"));
    Const source = new Const(compositeActor, "const");
    Discard sink = new Discard(compositeActor, "sink");
    compositeActor.connect(source.output, sink.input);
    return compositeActor;
  }

}
