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

package org.eclipse.triquetrum.workflow.actor.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.triquetrum.workflow.ui.AbstractPlaceableSWT;

import ptolemy.actor.injection.PortableContainer;
import ptolemy.actor.lib.gui.Display;
import ptolemy.actor.lib.gui.DisplayInterface;
import ptolemy.data.IntToken;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;
import ptolemy.util.MessageHandler;

///////////////////////////////////////////////////////////////////
//// DisplayJavaSWT

/**
 * <p>
 * DisplayJavaSWT is the implementation of the DisplayInterface that uses AWT and Swing classes. Values of the tokens arriving on the input channels in a text
 * area on the screen. Each input token is written on a separate line. The input type can be of any type. Thus, string-valued tokens can be used to generate
 * arbitrary textual output, at one token per line.
 * </p>
 * <p>
 * Note that because of complexities in Swing, if you resize the display window, then, unlike the plotters, the new size will not be persistent. That is, if you
 * save the model and then re-open it, the new size is forgotten. To control the size, you should set the <i>rowsDisplayed</i> and <i>columnsDisplayed</i>
 * parameters.
 * </p>
 * <p>
 * Note that this actor internally uses JTextArea, a Java Swing object that is known to consume large amounts of memory. It is not advisable to use this actor
 * to log large output streams.
 * </p>
 *
 * @author Erwin De Ley
 */

public class DisplayJavaSWT extends AbstractPlaceableSWT implements DisplayInterface {

  ///////////////////////////////////////////////////////////////////
  //// public methods ////

  /**
   * Free up memory when closing. This is executed in the GUI event thread.
   */
  @Override
  public void cleanUp() {
    Runnable doIt = new Runnable() {
      @Override
      public void run() {
        DisplayJavaSWT.super.cleanUp();
      }
    };
    runDeferred(doIt);
  }

  /**
   * Append the string value of the token to the text area on the screen. Each value is terminated with a newline character. This is executed in the Swing event
   * thread.
   *
   * @param value
   *          The string to be displayed
   */
  @Override
  public void display(final String value) {
    Runnable doIt = new Runnable() {
      @Override
      public void run() {
        if (textArea == null) {
          return;
        }

        textArea.append(value);

        // Append a newline character.
        // TODO add support for Display.suppressBlankLines
        if (value.length() > 0) {
          textArea.append("\n");
        }
        textArea.update();
      }
    };
    runDeferred(doIt);
  }

  /**
   * Return the object of the containing text area.
   *
   * @return the text area.
   */
  @Override
  public Object getTextArea() {
    return textArea;
  }

  /**
   * Set the number of rows for the text area.
   *
   * @param displayActor
   *          The display actor to be initialized.
   * @exception IllegalActionException
   *              If the entity cannot be contained by the proposed container.
   * @exception NameDuplicationException
   *              If the container already has an actor with this name.
   */
  @Override
  public void init(Display displayActor) throws IllegalActionException, NameDuplicationException {
    _display = displayActor;
    super.init(displayActor);
  }

  /**
   * Open the display window if it has not been opened.
   *
   * @exception IllegalActionException
   *              If there is a problem creating the effigy and tableau. This is executed in the Swing event thread.
   */
  @Override
  public void openWindow() throws IllegalActionException {
    Runnable doIt = new Runnable() {
      @Override
      public void run() {
        if (textArea == null) {
          // No container has been specified for display.
          // Place the text area in its own frame.
          try {
            _shell = new Shell();
            _shell.setLayout(new GridLayout());

            int numRows = ((IntToken) _display.rowsDisplayed.getToken()).intValue();
            int numColumns = ((IntToken) _display.columnsDisplayed.getToken()).intValue();

            textArea = new Text(_shell, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
            GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
            gridData.heightHint = numRows * textArea.getLineHeight();
            GC gc = new GC(textArea);
            int charWidth = gc.getFontMetrics().getAverageCharWidth();
            gc.dispose();
            gridData.widthHint = numColumns * charWidth;
            textArea.setLayoutData(gridData);
            setShell(_shell);
            _shell.pack();
          } catch (Exception ex) {
            MessageHandler.error("Error opening window for Display actor.", ex);
          }
        } else {
          // Erase previous text.
          textArea.setText("");
        }

        if (_shell != null) {
          _shell.setActive();
          _shell.open();
        }
      }
    };
    runDeferred(doIt);
  }

  /**
   * Specify the container in which the data should be displayed.
   * An instance of JTextArea will be added to that container.
   * This method needs to be called before the first call to initialize().
   * Otherwise, an instance of JTextArea will be placed in its own frame.
   * The text area is also placed in its own frame if this method is called with a null argument.
   * The background of the text area is set equal to that of the container (unless it is null).
   * This is executed in the Swing event thread.
   *
   * @param portableContainer
   *          The container into which to place the text area, or null to specify that there is no current container.
   */
  @Override
  public void place(final PortableContainer portableContainer) {
    Runnable doIt = new Runnable() {
      @Override
      public void run() {
        Composite container = (Composite) (portableContainer != null ? portableContainer.getPlatformContainer() : null);
        if (container == null) {
          // Reset everything.
          if (_shell != null) {
              _shell.dispose();
          }
          _shell = null;
          textArea = null;
          return;
        }

        _shell = new Shell();
        textArea = new Text(_shell, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.WRAP);

        String titleSpec;
        try {
          titleSpec = _display.title.stringValue();
        } catch (IllegalActionException e) {
          titleSpec = "Error in title: " + e.getMessage();
        }

        if (!titleSpec.trim().equals("")) {
          _shell.setText(titleSpec);
        }

//        try {
       // TODO find a way to set the size of the Text area
//          int numRows = ((IntToken) _display.rowsDisplayed.getToken()).intValue();
//          textArea.setRows(numRows);
//
//          int numColumns = ((IntToken) _display.columnsDisplayed.getToken()).intValue();
//          textArea.setColumns(numColumns);

//        } catch (IllegalActionException ex) {
//          // Ignore, and use default number of rows.
//        }

        // Make sure the text is not editable.
        textArea.setEditable(false);
        _swtContainer = container;
      }
    };
    runDeferred(doIt);
  }

  /**
   * Remove the display from the current container, if there is one. This is executed in the Swing thread later.
   */
  @Override
  public void remove() {
    Runnable doIt = new Runnable() {
      @Override
      public void run() {
        // TODO implement remove
      }
    };
    runDeferred(doIt);
  }

  /**
   * Set the desired number of columns of the textArea, if there is one. This is executed in the Swing event thread.
   *
   * @param numberOfColumns
   *          The new value of the attribute.
   * @exception IllegalActionException
   *              If the specified attribute is <i>rowsDisplayed</i> and its value is not positive.
   */
  @Override
  public void setColumns(final int numberOfColumns) throws IllegalActionException {
    Runnable doIt = new Runnable() {
      @Override
      public void run() {
        // TODO implement setColumns
      }
    };
    runDeferred(doIt);
  }

  /**
   * Set the desired number of rows of the textArea, if there is one. This is executed in the Swing event thread.
   *
   * @param numberOfRows
   *          The new value of the attribute.
   * @exception IllegalActionException
   *              If the specified attribute is <i>rowsDisplayed</i> and its value is not positive.
   */
  @Override
  public void setRows(final int numberOfRows) throws IllegalActionException {
    Runnable doIt = new Runnable() {
      @Override
      public void run() {
        // TODO implement setRows
      }
    };
    runDeferred(doIt);
  }

  /**
   * Set the title of the window.
   * <p>
   * If the <i>title</i> parameter is set to the empty string, and the Display window has been rendered, then the title of the Display window will be updated to
   * the value of the name parameter.
   * </p>
   * This is executed in the Swing event thread.
   *
   * @param stringValue
   *          The title to be set.
   * @exception IllegalActionException
   *              If the title cannot be set.
   */
  @Override
  public void setTitle(final String stringValue) throws IllegalActionException {
    Runnable doIt = new Runnable() {
      @Override
      public void run() {
       // TODO implement setTitle
      }
    };
    runDeferred(doIt);
  }

  ///////////////////////////////////////////////////////////////////
  //// private methods ////
  private void runDeferred(Runnable doIt) {
    org.eclipse.swt.widgets.Display display = _shell!=null ? _shell.getDisplay() : org.eclipse.swt.widgets.Display.getDefault();
    display.syncExec(doIt);
  }

  ///////////////////////////////////////////////////////////////////
  //// public members ////

  /** The text area in which the data will be displayed. */
  public transient Text textArea;

  ///////////////////////////////////////////////////////////////////
  //// private members ////

  /** The SWT Container */
  private Composite _swtContainer;

  /** Reference to the Display actor */
  private Display _display;
}
