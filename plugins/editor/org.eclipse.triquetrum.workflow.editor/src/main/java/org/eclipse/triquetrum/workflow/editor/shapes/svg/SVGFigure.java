/*******************************************************************************
 * Copyright (c) 2016,2019 iSencia Belgium NV.
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
package org.eclipse.triquetrum.workflow.editor.shapes.svg;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.WeakHashMap;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.triquetrum.workflow.editor.TriqEditorPlugin;
import org.eclipse.triquetrum.workflow.editor.shapes.ShapeUtils;
import org.w3c.dom.Document;

public class SVGFigure extends Figure {

  private String uri;
  private int translateX;
  private int translateY;

  private boolean failedToLoadDocument;
  private SimpleImageTranscoder transcoder;

  private static WeakHashMap<String, Document> documentsMap = new WeakHashMap<>();

  public int getTranslateX() {
    return translateX;
  }

  public void setTranslateX(int translateX) {
    this.translateX = translateX;
  }

  public int getTranslateY() {
    return translateY;
  }

  public void setTranslateY(int translateY) {
    this.translateY = translateY;
  }

  public final String getURI() {
    return uri;
  }

  public final void setURI(String uri) {
    setURI(uri, true);
  }

  public void setURI(String uri, boolean loadOnDemand) {
    this.uri = uri;
    transcoder = null;
    failedToLoadDocument = false;
    if (loadOnDemand) {
      loadDocument();
    }
  }

  private void loadDocument() {
    transcoder = null;
    failedToLoadDocument = true;
    if (uri == null) {
      return;
    }
    String parser = XMLResourceDescriptor.getXMLParserClassName();
    parser = parser != null ? parser : "org.apache.xerces.parsers.SAXParser";
    SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
    try {
      Document document;
      if (documentsMap.containsKey(uri))
        document = documentsMap.get(uri);
      else {
        document = factory.createDocument(uri);
        documentsMap.put(uri, document);
      }
      transcoder = new SimpleImageTranscoder(document, translateX, translateY);
      failedToLoadDocument = false;
    } catch (IOException e) {
      TriqEditorPlugin.logError("Error loading SVG file", e);
    }
  }

  protected final Document getDocument() {
    if (failedToLoadDocument) {
      return null;
    }
    if (transcoder == null) {
      loadDocument();
    }
    return transcoder == null ? null : transcoder.getDocument();
  }

  @Override
  protected void paintFigure(Graphics graphics) {
    super.paintFigure(graphics);
    Document document = getDocument();
    if (document == null) {
      return;
    }
    Image image = null;
    try {
      Rectangle r = getClientArea();
      updateRenderingHints(graphics);
      BufferedImage awtImage = transcoder.getBufferedImage();
      if (awtImage != null) {
        image = ShapeUtils.toSWT(Display.getCurrent(), awtImage);
        graphics.drawImage(image, r.x, r.y);
      }
    } finally {
      if (image != null) {
        image.dispose();
      }
    }
  }

  private void updateRenderingHints(Graphics graphics) {
    {
      int aa = SWT.DEFAULT;
      try {
        aa = graphics.getAntialias();
      } catch (Exception e) {
        // not supported
      }
      Object aaHint;
      if (aa == SWT.ON) {
        aaHint = RenderingHints.VALUE_ANTIALIAS_ON;
      } else if (aa == SWT.OFF) {
        aaHint = RenderingHints.VALUE_ANTIALIAS_OFF;
      } else {
        aaHint = RenderingHints.VALUE_ANTIALIAS_DEFAULT;
      }
      if (transcoder.getRenderingHints().get(RenderingHints.KEY_ANTIALIASING) != aaHint) {
        transcoder.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, aaHint);
        transcoder.contentChanged();
      }
    }
    {
      int aa = SWT.DEFAULT;
      try {
        aa = graphics.getTextAntialias();
      } catch (Exception e) {
        // not supported
      }
      Object aaHint;
      if (aa == SWT.ON) {
        aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
      } else if (aa == SWT.OFF) {
        aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
      } else {
        aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT;
      }
      if (transcoder.getRenderingHints().get(RenderingHints.KEY_TEXT_ANTIALIASING) != aaHint) {
        transcoder.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, aaHint);
        transcoder.contentChanged();
      }
    }
  }

  /**
   * Should be called when SVG document has been changed. It will be re-rendered and figure will be repainted.
   */
  public void contentChanged() {
    getDocument();
    if (transcoder != null) {
      transcoder.contentChanged();
    }
    repaint();
  }
}