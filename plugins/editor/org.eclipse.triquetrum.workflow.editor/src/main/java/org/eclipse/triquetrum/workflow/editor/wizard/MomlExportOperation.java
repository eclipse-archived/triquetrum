/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.wizard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.operation.ModalContext;
import org.eclipse.osgi.util.NLS;
import org.eclipse.triquetrum.workflow.editor.util.EditorUtils;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.CompositeEntity;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.IOverwriteQuery;

import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;

@SuppressWarnings("restriction")
public class MomlExportOperation implements IRunnableWithProgress {
  private IPath path;

  private IProgressMonitor monitor;

  private List<?> resourcesToExport;

  private IOverwriteQuery overwriteCallback;

  private IResource resource;

  private List<IStatus> errorTable = new ArrayList<>(1);

  // The constants for the overwrite 3 state
  private static final int OVERWRITE_NOT_SET = 0;

  private static final int OVERWRITE_NONE = 1;

  private static final int OVERWRITE_ALL = 2;

  private int overwriteState = OVERWRITE_NOT_SET;

  private boolean createLeadupStructure = true;

  private boolean createContainerDirectories = true;

  /**
   * Create an instance of this class. Use this constructor if you wish to recursively export a single resource
   */
  public MomlExportOperation(IResource res, String destinationPath, IOverwriteQuery overwriteImplementor) {
    super();
    resource = res;
    path = new Path(destinationPath);
    overwriteCallback = overwriteImplementor;
  }

  /**
   * Create an instance of this class. Use this constructor if you wish to export specific resources with a common parent resource (affects container directory
   * creation)
   */
  public MomlExportOperation(IResource res, List<?> resources, String destinationPath, IOverwriteQuery overwriteImplementor) {
    this(res, destinationPath, overwriteImplementor);
    resourcesToExport = resources;
  }

  /**
   * Answer the total number of file resources that exist at or below self in the resources hierarchy.
   *
   * @return int
   * @param parentResource
   *          org.eclipse.core.resources.IResource
   */
  protected int countChildrenOf(IResource parentResource) throws CoreException {
    if (parentResource.getType() == IResource.FILE) {
      return 1;
    }

    int count = 0;
    if (parentResource.isAccessible()) {
      IResource[] children = ((IContainer) parentResource).members();
      for (int i = 0; i < children.length; i++) {
        count += countChildrenOf(children[i]);
      }
    }

    return count;
  }

  /**
   * Answer a boolean indicating the number of file resources that were specified for export
   *
   * @return int
   */
  protected int countSelectedResources() throws CoreException {
    int result = 0;
    Iterator<?> resources = resourcesToExport.iterator();

    while (resources.hasNext()) {
      result += countChildrenOf((IResource) resources.next());
    }

    return result;
  }

  /**
   * Create the directories required for exporting the passed resource, based upon its container hierarchy
   *
   * @param childResource
   *          org.eclipse.core.resources.IResource
   */
  protected void createLeadupDirectoriesFor(IResource childResource) {
    IPath resourcePath = childResource.getFullPath().removeLastSegments(1);

    for (int i = 0; i < resourcePath.segmentCount(); i++) {
      path = path.append(resourcePath.segment(i));
      new File(path.toOSString()).mkdir();
    }
  }

  /**
   * Recursively export the previously-specified resource
   */
  protected void exportAllResources() throws InterruptedException {
    if (resource.getType() == IResource.FILE) {
      exportFile((IFile) resource, path);
    } else {
      try {
        exportChildren(((IContainer) resource).members(), path);
      } catch (CoreException e) {
        // not safe to show a dialog
        // should never happen because the file system export wizard ensures that the
        // single resource chosen for export is both existent and accessible
        errorTable.add(e.getStatus());
      }
    }
  }

  /**
   * Export all of the resources contained in the passed collection
   *
   * @param children
   *          java.util.Enumeration
   * @param currentPath
   *          IPath
   */
  protected void exportChildren(IResource[] children, IPath currentPath) throws InterruptedException {
    for (int i = 0; i < children.length; i++) {
      IResource child = children[i];
      if (!child.isAccessible()) {
        continue;
      }

      if (child.getType() == IResource.FILE) {
        exportFile((IFile) child, currentPath);
      } else {
        IPath destination = currentPath.append(child.getName());
        new File(destination.toOSString()).mkdir();
        try {
          exportChildren(((IContainer) child).members(), destination);
        } catch (CoreException e) {
          // not safe to show a dialog
          // should never happen because:
          // i. this method is called recursively iterating over the result of #members,
          // which only answers existing children
          // ii. there is an #isAccessible check done before #members is invoked
          errorTable.add(e.getStatus());
        }
      }
    }
  }

  /**
   * Export the passed file to the specified location
   *
   * @param file
   *          org.eclipse.core.resources.IFile
   * @param location
   *          org.eclipse.core.runtime.IPath
   */
  protected void exportFile(IFile file, IPath location) throws InterruptedException {
    IPath fullPath = location.append(file.getName()).removeFileExtension().addFileExtension("moml");
    monitor.subTask(file.getFullPath().toString());
    String properPathString = fullPath.toOSString();
    File targetFile = new File(properPathString);

    if (targetFile.exists()) {
      if (!targetFile.canWrite()) {
        errorTable.add(new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID, 0, NLS.bind("Cannot overwrite file: {0}", targetFile.getAbsolutePath()), null));
        monitor.worked(1);
        return;
      }

      if (overwriteState == OVERWRITE_NONE) {
        return;
      }

      if (overwriteState != OVERWRITE_ALL) {
        String overwriteAnswer = overwriteCallback.queryOverwrite(properPathString);

        if (overwriteAnswer.equals(IOverwriteQuery.CANCEL)) {
          throw new InterruptedException();
        }

        if (overwriteAnswer.equals(IOverwriteQuery.NO)) {
          monitor.worked(1);
          return;
        }

        if (overwriteAnswer.equals(IOverwriteQuery.NO_ALL)) {
          monitor.worked(1);
          overwriteState = OVERWRITE_NONE;
          return;
        }

        if (overwriteAnswer.equals(IOverwriteQuery.ALL)) {
          overwriteState = OVERWRITE_ALL;
        }
      }
    }

    try {
      // 1. obtain Diagram for the file
      Diagram diagram = GraphitiUiInternal.getEmfService().getDiagramFromFile(file, new ResourceSetImpl());
      // 2. find root CompositeActor
      CompositeActor toplevel = (CompositeActor) Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(diagram);
      // 3. push location info of diagram model elements to the Ptolemy II model elements
      pushLocations(diagram, toplevel);
      // 4. exportMOML to the destination file
      FileUtils.writeStringToFile(new File(fullPath.toOSString()), toplevel.getWrappedObject().exportMoML());
    } catch (IOException e) {
      errorTable.add(new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID, 0, NLS.bind("Error exporting {0}: {1}", fullPath, e.getMessage()), e));
    }

    monitor.worked(1);
    ModalContext.checkCanceled(monitor);
  }

  /**
   * Iterate over the shape's (deep) children and push the Graphiti diagram's location info to the corresponding model elements.
   *
   * @param modelContainer
   * @param modelElement
   */
  private void pushLocations(ContainerShape modelContainer, CompositeEntity modelElement) {
    for (Shape modelShape : modelContainer.getChildren()) {
      double x = modelShape.getGraphicsAlgorithm().getX();
      double y = modelShape.getGraphicsAlgorithm().getY();
      NamedObj bo = (NamedObj) Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(modelShape);
      // TODO think about validating the obtained bo against its container modelElement?
      try {
        EditorUtils.setLocation(bo, x, y);
      } catch (IllegalActionException | NameDuplicationException e) {
        errorTable.add(new Status(IStatus.WARNING, PlatformUI.PLUGIN_ID, 0, NLS.bind("Error setting location for {0}: {1}", bo.getName(), e.getMessage()), e));
      }
      // a composite entity should always have a container shape but one never knows, so let's double-check
      if ((modelShape instanceof ContainerShape) && (bo instanceof CompositeEntity)) {
        pushLocations((ContainerShape) modelShape, (CompositeEntity) bo);
      }
    }
  }

  /**
   * Export the resources contained in the previously-defined resourcesToExport collection
   */
  protected void exportSpecifiedResources() throws InterruptedException {
    Iterator<?> resources = resourcesToExport.iterator();
    IPath initPath = (IPath) path.clone();

    while (resources.hasNext()) {
      IResource currentResource = (IResource) resources.next();
      if (!currentResource.isAccessible()) {
        continue;
      }

      path = initPath;

      if (resource == null) {
        // No root resource specified and creation of containment directories
        // is required. Create containers from depth 2 onwards (ie.- project's
        // child inclusive) for each resource being exported.
        if (createLeadupStructure) {
          createLeadupDirectoriesFor(currentResource);
        }

      } else {
        // Root resource specified. Must create containment directories
        // from this point onwards for each resource being exported
        IPath containersToCreate = currentResource.getFullPath().removeFirstSegments(resource.getFullPath().segmentCount()).removeLastSegments(1);

        for (int i = 0; i < containersToCreate.segmentCount(); i++) {
          path = path.append(containersToCreate.segment(i));
          new File(path.toOSString()).mkdir();
        }
      }

      if (currentResource.getType() == IResource.FILE) {
        exportFile((IFile) currentResource, path);
      } else {
        if (createContainerDirectories) {
          path = path.append(currentResource.getName());
          new File(path.toOSString()).mkdir();
        }

        try {
          exportChildren(((IContainer) currentResource).members(), path);
        } catch (CoreException e) {
          // should never happen because #isAccessible is called before #members is invoked,
          // which implicitly does an existence check
          errorTable.add(e.getStatus());
        }
      }
    }
  }

  /**
   * Returns the status of the export operation. If there were any errors, the result is a status object containing individual status objects for each error. If
   * there were no errors, the result is a status object with error code <code>OK</code>.
   *
   * @return the status
   */
  public IStatus getStatus() {
    IStatus[] errors = new IStatus[errorTable.size()];
    errorTable.toArray(errors);
    return new MultiStatus(PlatformUI.PLUGIN_ID, IStatus.OK, errors, "Problems were encountered during export:", null);
  }

  /**
   * Answer a boolean indicating whether the passed child is a descendent of one or more members of the passed resources collection
   *
   * @return boolean
   * @param resources
   *          java.util.List
   * @param child
   *          org.eclipse.core.resources.IResource
   */
  protected boolean isDescendent(List<?> resources, IResource child) {
    if (child.getType() == IResource.PROJECT) {
      return false;
    }

    IResource parent = child.getParent();
    if (resources.contains(parent)) {
      return true;
    }

    return isDescendent(resources, parent);
  }

  /**
   * Export the resources that were previously specified for export (or if a single resource was specified then export it recursively)
   */
  @Override
  public void run(IProgressMonitor progressMonitor) throws InterruptedException {
    this.monitor = progressMonitor;

    if (resource != null) {
      if (createLeadupStructure) {
        createLeadupDirectoriesFor(resource);
      }

      if (createContainerDirectories && resource.getType() != IResource.FILE) {
        // ensure it's a container
        path = path.append(resource.getName());
        new File(path.toOSString()).mkdir();
      }
    }

    try {
      int totalWork = IProgressMonitor.UNKNOWN;
      try {
        if (resourcesToExport == null) {
          totalWork = countChildrenOf(resource);
        } else {
          totalWork = countSelectedResources();
        }
      } catch (CoreException e) {
        // Should not happen
        errorTable.add(e.getStatus());
      }
      monitor.beginTask("Exporting:", totalWork);
      if (resourcesToExport == null) {
        exportAllResources();
      } else {
        exportSpecifiedResources();
      }
    } finally {
      monitor.done();
    }
  }

  /**
   * Set this boolean indicating whether a directory should be created for Folder resources that are explicitly passed for export
   *
   * @param value
   *          boolean
   */
  public void setCreateContainerDirectories(boolean value) {
    createContainerDirectories = value;
  }

  /**
   * Set this boolean indicating whether each exported resource's complete path should include containment hierarchies as dictated by its parents
   *
   * @param value
   *          boolean
   */
  public void setCreateLeadupStructure(boolean value) {
    createLeadupStructure = value;
  }

  /**
   * Set this boolean indicating whether exported resources should automatically overwrite existing files when a conflict occurs. If not query the user.
   *
   * @param value
   *          boolean
   */
  public void setOverwriteFiles(boolean value) {
    if (value) {
      overwriteState = OVERWRITE_ALL;
    }
  }
}
