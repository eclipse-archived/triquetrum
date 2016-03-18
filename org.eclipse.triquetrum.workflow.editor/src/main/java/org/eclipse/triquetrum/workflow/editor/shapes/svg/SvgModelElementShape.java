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
package org.eclipse.triquetrum.workflow.editor.shapes.svg;

import java.io.IOException;

import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.platform.ga.IRendererContext;
import org.eclipse.triquetrum.workflow.editor.shapes.AbstractCustomModelElementShape;
import org.w3c.dom.Document;

public class SvgModelElementShape extends AbstractCustomModelElementShape {

  /**
   * @param properties
   */
  public  SvgModelElementShape(IRendererContext rendererContext) {
    super(rendererContext);
  }

  @Override
  protected void fillShape(Graphics graphics) {
    try {
      double[] locationInfo = getLocationInfo(getIconURI());
      int minX = (int) locationInfo[0];
      int minY = (int) locationInfo[1];

      int width = (int) locationInfo[2];
      int height = (int) locationInfo[3];
      setGaWidth(ga, width, height);
//      setGaWidth(ga.getParentGraphicsAlgorithm(), width, height);

      SVGFigure figure = new SVGFigure();
      // move SVG figure from its defined top-left to origin (0,0) top-left
      figure.setTranslateX(-minX);
      figure.setTranslateY(-minY);
      figure.setURI(getIconURI());
      figure.setBounds(this.getBounds());
      figure.paint(graphics);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  @Override
  protected void outlineShape(Graphics graphics) {
    float lineInset = Math.max(1.0f, getLineWidthFloat()) / 2.0f;
    int inset1 = (int) Math.floor(lineInset);
    int inset2 = (int) Math.ceil(lineInset);

    Rectangle r = Rectangle.SINGLETON.setBounds(getBounds());
    r.x += inset1;
    r.y += inset1;
    r.width -= inset1 + inset2;
    r.height -= inset1 + inset2;

    graphics.drawRectangle(r);
  }

  /**
  *
  * @param uri
  * @return [minX, minY, width,height] of the contained SVG
  * @throws IOException
  */
 private double[] getLocationInfo(String uri) throws IOException {
   String parser = XMLResourceDescriptor.getXMLParserClassName();
   parser = parser !=null ? parser : "org.apache.xerces.parsers.SAXParser";
   SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
   Document doc = factory.createDocument(uri);
   UserAgent agent = new UserAgentAdapter();
   DocumentLoader loader = new DocumentLoader(agent);
   BridgeContext context = new BridgeContext(agent, loader);
   context.setDynamic(true);
   GVTBuilder builder = new GVTBuilder();
   GraphicsNode root = builder.build(context, doc);
   double height = root.getGeometryBounds().getHeight();
   double width = root.getGeometryBounds().getWidth();
   double minX = root.getGeometryBounds().getMinX();
   double minY = root.getGeometryBounds().getMinY();
   return new double[] {minX, minY, width, height};
 }
}
