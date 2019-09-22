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
package org.eclipse.triquetrum.workflow.rcp;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

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
}
