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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.nebula.visualization.xygraph.dataprovider.CircularBufferDataProvider;
import org.eclipse.nebula.visualization.xygraph.dataprovider.Sample;
import org.eclipse.nebula.visualization.xygraph.figures.Trace;
import org.eclipse.nebula.visualization.xygraph.figures.Trace.PointStyle;
import org.eclipse.nebula.visualization.xygraph.figures.Trace.TraceType;
import org.eclipse.nebula.visualization.xygraph.figures.XYGraph;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.triquetrum.workflow.actor.plot.XYPlotActor;
import org.eclipse.triquetrum.workflow.ui.AbstractPlaceableSWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;
import ptolemy.util.MessageHandler;

/**
 * A utility to prepare a shell to show an XY plot, used by the XYPlotActor.
 * <p>
 * The implementation follows some of the mechanisms of e.g. org.eclipse.triquetrum.workflow.actor.ui.DisplayJavaSWT.
 * </p>
 *
 */
public class XYPlot extends AbstractPlaceableSWT {
  private final static Logger LOGGER = LoggerFactory.getLogger(XYPlot.class);

  private XYGraph graph;
  private List<CircularBufferDataProvider> traceDataProviders = new ArrayList<>();
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

            String title = actor.title.stringValue();
            title = !StringUtils.isBlank(title) ? title : actor.getName();
            String xName = actor.xName.stringValue();
            String yName = actor.yName.stringValue();
            String[] seriesNames = actor.seriesNames.stringValue().split(",");

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

            // For each channel connected to the actor's inputY port,
            // create a trace data provider, which will provide the data to the trace.
            int widthY = actor.inputY.getWidth();
            for (int i = 0; i < widthY; ++i) {
              CircularBufferDataProvider traceDataProvider = new CircularBufferDataProvider(false);
              traceDataProvider.setBufferSize(100);
              traceDataProviders.add(traceDataProvider);
              // create the trace
              String traceName = (seriesNames.length > i) ? seriesNames[i] : seriesNames[seriesNames.length - 1];
              Trace trace = new Trace(traceName, xyGraph.primaryXAxis, xyGraph.primaryYAxis, traceDataProvider);
              // set trace properties
              PointStyle pointStyle = PointStyle.NONE;
              try {
                pointStyle = PointStyle.valueOf(actor.plotPointStyle.stringValue());
                if (!PointStyle.NONE.equals(pointStyle)) {
                  // increase the point size a bit as the point shapes are barely noticeable otherwise
                  trace.setPointSize(6);
                }
              } catch (Exception e) {
                // it's OK, this is not a critical issue just leave it at the default NONE.
                LOGGER.warn("Error determining the desired plot Point Style", e);
              }
              trace.setPointStyle(pointStyle);
              TraceType traceType = TraceType.SOLID_LINE;
              try {
                traceType = TraceType.valueOf(actor.plotLineStyle.stringValue());
              } catch (Exception e) {
                // it's OK, this is not a critical issue just leave it at the default SOLIDE_LINE.
                LOGGER.warn("Error determining the desired plot Line Style", e);
              }
              trace.setTraceType(traceType);
              // add the trace to xyGraph
              xyGraph.addTrace(trace);
            }
            setShell(_shell);
          } catch (Exception ex) {
            MessageHandler.error("Error opening window for XYPlotActor.", ex);
          }
        }
      }
    };
    runDeferred(doIt);
  }

  public void addPoint(int channel, double x, double y) {
    runDeferred(new Runnable() {
      @Override
      public void run() {
        traceDataProviders.get(channel).addSample(new Sample(x, y));
      }
    });
  }
}
