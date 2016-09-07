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
package org.eclipse.triquetrum.workflow.editor.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public class EclipseUtils {
  /**
   * Gets the page, even during startup.
   *
   * @return the page
   */
  public static IWorkbenchPage getPage() {
    IWorkbenchPage activePage = EclipseUtils.getActivePage();
    if (activePage != null)
      return activePage;
    return EclipseUtils.getDefaultPage();
  }

  /**
   * @return IWorkbenchPage
   */
  public static IWorkbenchPage getActivePage() {
    final IWorkbench bench = PlatformUI.getWorkbench();
    if (bench == null)
      return null;
    final IWorkbenchWindow window = bench.getActiveWorkbenchWindow();
    if (window == null)
      return null;
    return window.getActivePage();
  }

  /**
   * @return IWorkbenchPage
   */
  public static IWorkbenchPage getDefaultPage() {
    final IWorkbench bench = PlatformUI.getWorkbench();
    if (bench == null)
      return null;
    final IWorkbenchWindow[] windows = bench.getWorkbenchWindows();
    if (windows == null || windows.length == 0)
      return null;

    return windows[0].getActivePage();
  }

  /**
   * Try to determine the IFile from the edit input
   *
   * @param input
   * @return file
   */
  public static IFile getIFile(IEditorInput input) {
    if (input instanceof FileEditorInput) {
      return ((FileEditorInput) input).getFile();
    }
    return (IFile) input.getAdapter(IFile.class);
  }

  /**
   * Opens an external editor on a file path
   *
   * @param file
   * @throws PartInitException
   */
  public static IEditorPart openEditor(IFile file) throws PartInitException {

    IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName());
    if (desc == null)
      desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName() + ".txt");
    final IWorkbenchPage page = EclipseUtils.getActivePage();
    return page.openEditor(new FileEditorInput(file), desc.getId());
  }

}
