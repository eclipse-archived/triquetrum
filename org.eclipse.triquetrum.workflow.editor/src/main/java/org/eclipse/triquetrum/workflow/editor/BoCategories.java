package org.eclipse.triquetrum.workflow.editor;

import org.apache.commons.lang.StringUtils;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum BoCategories {
  Actor, Director, Parameter, Attribute, Annotation, Relation, Vertex, Port, Input, Output;

  public final static String BO_CATEGORY_PROPNAME = "__BO_CATEGORY";
  private final static Logger LOGGER = LoggerFactory.getLogger(BoCategories.class);

  public static BoCategories retrieveFrom(PictogramElement pe) {
    String boCategoryStr = Graphiti.getPeService().getPropertyValue(pe, BoCategories.BO_CATEGORY_PROPNAME);
    if (!StringUtils.isBlank(boCategoryStr)) {
      try {
        return BoCategories.valueOf(boCategoryStr);
      } catch (Exception e) {
        LOGGER.error("Invalid BO Category for " + pe, e);
      }
    }
    return null;
  }
}
