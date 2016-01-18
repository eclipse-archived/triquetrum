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

package org.eclipse.triquetrum.workflow.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import ptolemy.actor.injection.PortableContainer;

///////////////////////////////////////////////////////////////////
//// SWTContainer

/**
 * The portable container that wraps {@link Composite}.
 *
 * @author Erwin De Ley
 */
public class SWTContainer implements PortableContainer {

  /**
   * Create a new instance of the object by wrapping the provided container.
   *
   * @param container
   *          The container to wrap.
   */
  public SWTContainer(Composite container) {
    _container = container;
  }

  ///////////////////////////////////////////////////////////////////
  //// public methods ////

  /**
   * Add the control to the container. Remark that in SWT a control receives its parent container in its constructor, and assigning/adding it to another
   * container afterwards is not guaranteed to work!
   *
   * @param control
   *          the control to be added to the container.
   * @see ptolemy.actor.injection.PortableContainer#add(java.lang.Object)
   */
  @Override
  public void add(Object control) {
    Control ctrl = (Control) control;
    if (ctrl.isReparentable()) {
      ctrl.setParent(_container);
    }
  }

  /**
   * Return the SWT container that this instance wraps.
   *
   * @see ptolemy.actor.injection.PortableContainer#getPlatformContainer()
   * @return the SWT container that this instance wraps.
   */
  @Override
  public Composite getPlatformContainer() {
    return _container;
  }

  ///////////////////////////////////////////////////////////////////
  //// private variables ////

  /**
   * The SWT Composite that this instance wraps.
   */
  private final Composite _container;

}
