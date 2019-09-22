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
package org.eclipse.triquetrum.workflow.ui;

import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

import ptolemy.data.IntMatrixToken;
import ptolemy.data.expr.Parameter;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.InternalErrorException;
import ptolemy.kernel.util.NameDuplicationException;
import ptolemy.kernel.util.NamedObj;
import ptolemy.kernel.util.Settable;
import ptolemy.kernel.util.Workspace;

///////////////////////////////////////////////////////////////////
//// SizeAttribute

/**
 * This attribute stores the width and height of a graphical control. The token in this attribute is an IntMatrixToken containing a matrix of dimension 1x2,
 * containing the width and the height, in that order. By default, this attribute has visibility NONE, so the user will not see it in parameter editing dialogs.
 * <p>
 * Note that this attribute is typically used to record the size of a control within a shell (a top-level window). To record the size and position of the shell,
 * use WindowPropertiesAttribute.
 *
 * @author Edward A. Lee, Contributors: Erwin De Ley
 * @see WindowPropertiesAttribute
 */
@SuppressWarnings("restriction")
public class SizeAttribute extends Parameter implements ControlListener {
  /**
   * Construct an attribute with the given name contained by the specified entity. The container argument must not be null, or a NullPointerException will be
   * thrown. This attribute will use the workspace of the container for synchronization and version counts. If the name argument is null, then the name is set
   * to the empty string. Increment the version of the workspace.
   *
   * @param container
   *          The container.
   * @param name
   *          The name of this attribute.
   * @exception IllegalActionException
   *              If the attribute is not of an acceptable class for the container, or if the name contains a period.
   * @exception NameDuplicationException
   *              If the name coincides with an attribute already in the container.
   */
  public SizeAttribute(NamedObj container, String name) throws IllegalActionException, NameDuplicationException {
    super(container, name);
    setVisibility(Settable.NONE);
  }

  ///////////////////////////////////////////////////////////////////
  //// public methods ////

  /**
   * Clone the attribute into the specified workspace. This calls the base class and then sets the attribute public members to refer to the attributes of the
   * new attribute.
   *
   * @param workspace
   *          The workspace for the new attribute
   * @return A new director.
   * @exception CloneNotSupportedException
   *              If a derived class contains an attribute that cannot be cloned.
   */
  @Override
  public Object clone(Workspace workspace) throws CloneNotSupportedException {
    SizeAttribute newObject = (SizeAttribute) super.clone(workspace);
    newObject._listeningTo = null;
    return newObject;
  }

  /**
   * Do nothing. This method is invoked when the control's position changes.
   *
   * @param event
   *          The control event.
   */
  @Override
  public void controlMoved(ControlEvent event) {
  }

  /**
   * Record the new size. This method is invoked when the control's size changes.
   *
   * @param event
   *          The control event.
   */
  @Override
  public void controlResized(ControlEvent event) {
    recordSize(_listeningTo);
  }

  /**
   * Set the value of the attribute to match those of the specified control.
   *
   * @param control
   *          The control whose size is to be recorded.
   */
  public void recordSize(Control control) {
    try {
      Rectangle bounds = control.getBounds();
      setToken("[" + bounds.width + ", " + bounds.height + "]");
      // Not clear why the following is needed, but if it isn't there,
      // then window properties may not be recorded.
      propagateValue();
    } catch (IllegalActionException ex) {
      throw new InternalErrorException("Can't set bounds value!");
    }
  }

  /**
   * Set the size of the specified control to match the current value of the attribute. If the value of the attribute has not been set, then do nothing. If it
   * has not already done so, this method also registers with the control as a listener for control events like resizing.
   *
   * @param control
   *          The control whose size is to be set.
   * @return true if successful.
   */
  public boolean setSize(Control control) {
    if (control == null) {
      if (_listeningTo != null) {
        _listeningTo.removeControlListener(this);
        _listeningTo = null;
      }
      return true;
    }
    if (_listeningTo != control) {
      if (_listeningTo != null) {
        _listeningTo.removeControlListener(this);
      }
      control.addControlListener(this);
      _listeningTo = control;
    }
    try {
      IntMatrixToken token = (IntMatrixToken) getToken();
      if (token != null) {
        int width = token.getElementAt(0, 0);
        int height = token.getElementAt(0, 1);
        control.setSize(width, height);
      }
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  ///////////////////////////////////////////////////////////////////
  //// private variables ////

  /** The control we are listening to. */
  private Control _listeningTo;
  // FIXME: should the above have a weak reference like what we have for WindowPropertiesAttribute?
}
