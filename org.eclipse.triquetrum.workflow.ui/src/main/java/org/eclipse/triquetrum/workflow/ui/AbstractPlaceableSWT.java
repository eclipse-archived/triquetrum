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

import java.io.IOException;
import java.io.Writer;

import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Shell;

import ptolemy.actor.TypedAtomicActor;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;

///////////////////////////////////////////////////////////////////
////AbstractPlaceableSWT

/**
 * Base class for SWT implementation of actors that implement PortablePlaceable.
 *
 * @author Erwin De Ley
 */

@SuppressWarnings("restriction")
public abstract class AbstractPlaceableSWT {

  /**
   * Initialize the specified actor with window properties and pane size attributes.
   *
   * @param actor
   *          The actor contained in the Window.
   * @exception IllegalActionException
   *              If the entity cannot be contained by the proposed container.
   * @exception NameDuplicationException
   *              If the container already has an actor with this name.
   */
  public void init(TypedAtomicActor actor) throws IllegalActionException, NameDuplicationException {
    // An actor may already have _windowProperties set.
    _windowProperties = (WindowPropertiesAttribute) actor.getAttribute("_windowProperties", WindowPropertiesAttribute.class);
    if (_windowProperties == null) {
      _windowProperties = new WindowPropertiesAttribute(actor, "_windowProperties");
    }

    // Note that we have to force this to be persistent because
    // there is no real mechanism for the value of the properties
    // to be updated when the window is moved or resized. By
    // making it persistent, when the model is saved, the
    // attribute will determine the current size and position
    // of the window and save it.
    _windowProperties.setPersistent(true);
  }

  /**
   * Specify the associated shell and set its properties (size, etc.) to match those stored in the _windowProperties attribute.
   *
   * @param shell
   *          The associated shell.
   */
  public void setShell(Shell shell) {
    if (_shell != null && _windowClosingAdapter != null) {
      _shell.removeShellListener(_windowClosingAdapter);
    }
    if (shell == null) {
      _shell = null;
      return;
    }
    _shell = shell;
    _windowClosingAdapter = new WindowClosingAdapter();
    shell.addShellListener(_windowClosingAdapter);
    _windowProperties.setProperties(_shell);
  }

  ///////////////////////////////////////////////////////////////////
  //// protected methods ////

  /** Free up memory when closing. */
  protected void cleanUp() {
    setShell(null);
  }

  /**
   * Write a MoML description of the contents of this object.
   * This overrides the base class to make sure that the current shell properties, if there is a shell, are recorded.
   *
   * @param output
   *          The output stream to write to.
   * @param depth
   *          The depth in the hierarchy, to determine indenting.
   * @exception IOException
   *              If an I/O error occurs.
   */
  protected void _exportMoMLContents(Writer output, int depth) throws IOException {
    // Make sure that the current position of the shell, if any, is up to date.
    if (_shell != null) {
      _windowProperties.recordProperties(_shell);
    }
  }

  ///////////////////////////////////////////////////////////////////
  //// protected variables ////

  /** The associated Shell */
  protected Shell _shell;

  /** A specification for the window properties of the shell. */
  protected WindowPropertiesAttribute _windowProperties;

  /** A reference to the listener for removal purposes. */
  protected WindowClosingAdapter _windowClosingAdapter;

  ///////////////////////////////////////////////////////////////////
  //// inner classes ////

  /** Listener for shell being closed. */
  public class WindowClosingAdapter extends ShellAdapter {
    @Override
    public void shellClosed(ShellEvent e) {
      cleanUp();
    }
  }

}
