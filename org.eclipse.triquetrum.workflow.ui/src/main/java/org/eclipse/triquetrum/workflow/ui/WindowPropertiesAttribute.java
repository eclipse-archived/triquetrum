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

import java.lang.ref.WeakReference;

import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import ptolemy.data.ArrayToken;
import ptolemy.data.BooleanToken;
import ptolemy.data.IntToken;
import ptolemy.data.RecordToken;
import ptolemy.data.expr.Parameter;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.InternalErrorException;
import ptolemy.kernel.util.NameDuplicationException;
import ptolemy.kernel.util.NamedObj;
import ptolemy.kernel.util.Settable;
import ptolemy.kernel.util.Workspace;

///////////////////////////////////////////////////////////////////
//// WindowPropertiesAttribute

/**
 * This attribute stores properties of a window, including the width, height, and location. The token in this attribute is a RecordToken containing a field
 * "bounds" with a 4-element integer array. There is also a field that indicates whether the window is maximized. By default, this attribute has visibility
 * NONE, so the user will not see it in parameter editing dialogs.
 *
 * @author Edward A. Lee, Contributors: Jason E. Smith, Christopher Brooks, Erwin De Ley
 *
 * @see ptolemy.actor.gui.WindowPropertiesAttribute
 */
@SuppressWarnings("restriction")
public class WindowPropertiesAttribute extends Parameter implements ControlListener {
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
  public WindowPropertiesAttribute(NamedObj container, String name) throws IllegalActionException, NameDuplicationException {
    super(container, name);
    setVisibility(Settable.NONE);

    // The following line, if uncommented, results in
    // icons that are defined in an external file always being
    // exported along with the model that defines them. This bloats
    // the MoML files with information that is not needed. I suspect
    // the line was put there because it wasn't clear that you need
    // to invoke File->Save in a submodel if you want the location
    // and position of the submodel window to be saved. It is not
    // sufficient to invoke save just at the top level.
    // setPersistent(true);
  }

  ///////////////////////////////////////////////////////////////////
  //// public methods ////

  /**
   * Clone the attribute into the specified workspace. This calls the base class and then sets the attribute public members to refer to the attributes of the
   * new attribute
   *
   * @param workspace
   *          The workspace for the new attribute
   * @return A new director.
   * @exception CloneNotSupportedException
   *              If a derived class contains an attribute that cannot be cloned.
   */
  @Override
  public Object clone(Workspace workspace) throws CloneNotSupportedException {
    WindowPropertiesAttribute newObject = (WindowPropertiesAttribute) super.clone(workspace);
    newObject._listeningTo = new WeakReference<Shell>(null);
    return newObject;
  }

  /**
   * Record the new position. This method is invoked when the control's position changes.
   *
   * @param event
   *          The control event.
   */
  @Override
  public void controlMoved(ControlEvent event) {
    recordProperties(_listeningTo.get());
  }

  /**
   * Record the new size. This method is invoked when the control's size changes.
   *
   * @param event
   *          The control event.
   */
  @Override
  public void controlResized(ControlEvent event) {
    recordProperties(_listeningTo.get());
  }

  /**
   * Set the value of the attribute to match those of the specified frame.
   *
   * @param shell
   *          The frame whose properties are to be recorded.
   */
  public void recordProperties(Shell shell) {
    try {
      Rectangle bounds = shell.getBounds();

      // Determine whether the window is maximized.
      boolean maximized = shell.getMaximized();

      // Get the current values.
      RecordToken value = null;
      try {
        value = (RecordToken) getToken();
      } catch (IllegalActionException ex) {
        throw new IllegalActionException(this, ex,
            "Attempting to get the value of the WindowPropertiesAttribute " + "failed.  This can happen when a model with graphical actors "
                + "is run using MoMLSimpleApplication " + "because MoMLSimpleApplication does not run the model in "
                + "the SWT event thread.  One possibility is that the model " + "needs to be run in Triquetrum and saved.");
      }
      boolean updateValue = false;

      if (value == null) {
        updateValue = true;
      } else {
        ArrayToken boundsToken = (ArrayToken) value.get("bounds");
        BooleanToken maximizedToken = (BooleanToken) value.get("maximized");
        int x = ((IntToken) boundsToken.getElement(0)).intValue();
        int y = ((IntToken) boundsToken.getElement(1)).intValue();
        int width = ((IntToken) boundsToken.getElement(2)).intValue();
        int height = ((IntToken) boundsToken.getElement(3)).intValue();

        if (maximizedToken == null) {
          maximizedToken = BooleanToken.FALSE;
        }

        // If the new values are different, then do a MoMLChangeRequest.
        if (maximizedToken.booleanValue() != maximized || x != bounds.x || y != bounds.y || width != bounds.width || height != bounds.height) {
          updateValue = true;
        }
      }

      // Check that the toplevel is non-empty so that if a user
      // opens Kepler and closes, they are not prompted to save
      // a blank model.
      if (updateValue) {

        // Construct values for the record token.
        String values = "{bounds={" + bounds.x + ", " + bounds.y + ", " + bounds.width + ", " + bounds.height + "}, maximized=" + maximized + "}";

        // Don't call setToken(), instead use a MoMLChangeRequest so that
        // the model is marked modified so that any changes are preserved.
        // See "closing workflow does not save the location change of popup display windows."
        // http://bugzilla.ecoinformatics.org/show_bug.cgi?id=5188
        //
        // The problem is that if the user opens a model and
        // moves the Display actor and then closes it, they
        // are prompted for saving.
        //
        // If the user wants to save the location of the
        // Display actors, then they should explicitly save the model.
        setToken(values);

        // String moml = "<property name=\"" + getName()
        // + "\" value=\"" + values + "\"/>";

        // MoMLChangeRequest request = new MoMLChangeRequest(this,
        // getContainer(), moml, false);
        // getContainer().requestChange(request);
        // Not clear why the following is needed, but if it isn't there,
        // then window properties may not be recorded.
        propagateValue();
      }
    } catch (IllegalActionException ex) {
      throw new InternalErrorException(this, ex, "Can't set propertes value!");
    }
  }

  /**
   * Set the properties of the specified frame to match the current value of the attribute.
   * If the value of the attribute has not been set, then do nothing and return true.
   * If the value of this attribute is malformed in any way, then just return false.
   *
   * <p>
   * If the x or y position is less than 0 pixels or greater than (width - 10 pixels) or (height - 10) pixels of the screen,
   * then offset the position by 30 pixels so the user can drag the window.
   *
   * @param shell
   *          The shell whose properties are to be set.
   * @return True if successful.
   */
  public boolean setProperties(Shell shell) {
    Shell listeningTo = _listeningTo.get();
    if (listeningTo != shell) {
      if (listeningTo != null) {
        listeningTo.removeControlListener(this);
      }
      shell.addControlListener(this);
      _listeningTo.clear();
      _listeningTo = new WeakReference<>(shell);
    }
    try {
      RecordToken value = (RecordToken) getToken();
      if (value == null) {
        return true;
      }
      ArrayToken boundsToken = (ArrayToken) value.get("bounds");
      BooleanToken maximizedToken = (BooleanToken) value.get("maximized");
      int x = ((IntToken) boundsToken.getElement(0)).intValue();
      int y = ((IntToken) boundsToken.getElement(1)).intValue();
      int width = ((IntToken) boundsToken.getElement(2)).intValue();
      int height = ((IntToken) boundsToken.getElement(3)).intValue();

      // FIXME: If we change the size, should we mark the model
      // as dirty so it gets saved?

      // See ptolemy/actor/gui/test/MyFourCorners.xml for a test file
      // that produces four plots in the four corners of a multi-screen window.
      Monitor[] displayMonitors = shell.getDisplay().getMonitors();

      int widths[] = new int[displayMonitors.length];
      int heights[] = new int[displayMonitors.length];
      boolean widthsEqual = true;
      boolean heightsEqual = true;
      int maxWidth = 0;
      int maxHeight = 0;

      for (int j = 0; j < displayMonitors.length; j++) {
        Monitor monitor = displayMonitors[j];
        Rectangle visualArea = monitor.getClientArea();
        widths[j] = visualArea.width;
        heights[j] = visualArea.height;
      }
      for (int j = 0; j < displayMonitors.length - 1; j++) {
        if (widths[j] != widths[j + 1]) {
          widthsEqual = false;
        }
        if (heights[j] != heights[j + 1]) {
          heightsEqual = false;
        }
      }
      if (widthsEqual && heightsEqual) {
        // Nominal setup
        if (heights[0] > widths[0]) {// Width is cumulative.
          for (int j = 0; j < displayMonitors.length; j++) {
            maxWidth += widths[j];
          }
          maxHeight = heights[0];
        } else {// Height is cumulative.
          for (int j = 0; j < displayMonitors.length; j++) {
            maxHeight += heights[j];
          }
          maxWidth = widths[0];
        }
      } else {
        // Strange setup.
        maxWidth = widths[0];
        maxHeight = heights[0];
        for (int j = 0; j < displayMonitors.length; j++) {
          maxWidth = maxWidth > widths[j] ? widths[j] : maxWidth;
          maxHeight = maxHeight > heights[j] ? heights[j] : maxHeight;
        }
      }

      x = x < 0 ? 0 : x;
      y = y < 0 ? 0 : y + 0;

      width = width > maxWidth ? maxWidth : width;
      height = height > maxHeight ? maxHeight : height;

      y = y + height > maxHeight ? maxHeight - height : y;
      x = x + width > maxWidth ? maxWidth - width : x;

      shell.setBounds(x, y, width, height);

      if (maximizedToken != null) {
        boolean maximized = maximizedToken.booleanValue();
        if (maximized) {
          shell.setMaximized(true);
        }
      }
      return true;
    } catch (Throwable throwable) {
      return false;
    }
  }

  ///////////////////////////////////////////////////////////////////
  //// private variables ////

  /** The shell we are listening to. */
  private WeakReference<Shell> _listeningTo = new WeakReference<>(null);
}
