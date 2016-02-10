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
package org.eclipse.triquetrum.workflow.actor.plot.ui;

import org.apache.commons.lang.StringUtils;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.nebula.visualization.xygraph.dataprovider.CircularBufferDataProvider;
import org.eclipse.nebula.visualization.xygraph.dataprovider.Sample;
import org.eclipse.nebula.visualization.xygraph.figures.Trace;
import org.eclipse.nebula.visualization.xygraph.figures.Trace.PointStyle;
import org.eclipse.nebula.visualization.xygraph.figures.XYGraph;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.triquetrum.workflow.actor.plot.XYPlotActor;
import org.eclipse.triquetrum.workflow.ui.AbstractPlaceableSWT;

import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;
import ptolemy.util.MessageHandler;

/**
 * A utility to prepare a shell to show an XY plot, uased by the XYPlotActor.
 * <p>
 * The implementation follows some of the mechanisms of e.g. org.eclipse.triquetrum.workflow.actor.ui.DisplayJavaSWT.
 * </p>
 *
 */
public class XYPlot extends AbstractPlaceableSWT {

  private XYGraph graph;
  private CircularBufferDataProvider traceDataProvider;
  private XYPlotActor actor;

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
  public void init(XYPlotActor actor) throws IllegalActionException, NameDuplicationException {
    this.actor = actor;
    super.init(actor);
  }

  public void openWindow() {
    Runnable doIt = new Runnable() {
      @Override
      public void run() {
        if (graph == null) {
          // No container has been specified for display.
          // Place the text area in its own frame.
          try {
            _shell = new Shell();
            _shell.setText(actor.getName());
            _shell.setSize(300, 250);
            _shell.setActive();
            _shell.open();

            String title = actor.title.getExpression();
            title = !StringUtils.isBlank(title) ? title : actor.getName();
            String xName = actor.xName.getExpression();
            String yName = actor.yName.getExpression();

            // use LightweightSystem to create the bridge between SWT and draw2D
            final LightweightSystem lws = new LightweightSystem(_shell);

            // create a new XY Graph.
            final XYGraph xyGraph = new XYGraph();
            xyGraph.setTitle(title);
            xyGraph.primaryYAxis.setAutoScale(true);
            xyGraph.primaryXAxis.setAutoScale(true);
            xyGraph.primaryXAxis.setShowMajorGrid(true);
            xyGraph.primaryYAxis.setShowMajorGrid(true);
            xyGraph.primaryXAxis.setAutoScaleThreshold(0);
            xyGraph.primaryXAxis.setTitle(xName);
            xyGraph.primaryYAxis.setTitle(yName);
            // set it as the content of LightwightSystem
            lws.setContents(xyGraph);

            // create a trace data provider, which will provide the data to the trace.
            traceDataProvider = new CircularBufferDataProvider(false);
            traceDataProvider.setBufferSize(100);

            // create the trace
            Trace trace = new Trace(yName, xyGraph.primaryXAxis, xyGraph.primaryYAxis, traceDataProvider);

            // set trace property
            trace.setPointStyle(PointStyle.XCROSS);

            // add the trace to xyGraph
            xyGraph.addTrace(trace);

            setShell(_shell);
          } catch (Exception ex) {
            MessageHandler.error("Error opening window for XYPlotActor.", ex);
          }
        }
      }
    };
    runDeferred(doIt);
  }

  public void addPoint(double x, double y) {
    runDeferred(new Runnable() {
      @Override
      public void run() {
        traceDataProvider.addSample(new Sample(x, y));
      }
    });
  }
}
