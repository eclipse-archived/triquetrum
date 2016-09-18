/*******************************************************************************
 * Copyright (c) 2015, 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.model.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptolemy.kernel.util.NamedObj;
import ptolemy.moml.MoMLParser;
import ptolemy.moml.filter.MoMLFilterSimple;

///////////////////////////////////////////////////////////////////
//// ClassChanges

/**
 * A filter to replace some Ptolemy classes by their Triquetrum equivalents.
 *
 * Based on ptolemy.moml.filter.ClassChanges, but with a different and much set of class substitutions.
 *
 * @author Christopher Brooks, Erwin De Ley
 */
public class SubstituteClassesForTriquetrum extends MoMLFilterSimple {

  private final static Logger LOGGER = LoggerFactory.getLogger(SubstituteClassesForTriquetrum.class);

  /**
   * Clear the map of class renames and the set of class removals.
   */
  public static void clear() {
    _classChanges = new HashMap<>();
  }

  /**
   * If the attributeName is "class" and attributeValue names a class that needs to be renamed, then substitute in the new class name. If the attributeValue
   * names a class that needs to be removed, then return null, which will cause the MoMLParser to skip the rest of the element;
   *
   * @param container
   *          The container for this attribute.
   * @param element
   *          The XML element name.
   * @param attributeName
   *          The name of the attribute.
   * @param attributeValue
   *          The value of the attribute.
   * @param xmlFile
   *          The file currently being parsed.
   * @return the value of the attributeValue argument.
   */
  @Override
  public String filterAttributeValue(NamedObj container, String element, String attributeName, String attributeValue, String xmlFile) {
    // This method gets called many times by the MoMLParser,
    // so we try to be smart about the number of comparisons
    // and we try to group comparisons together so that we
    // are not making the same comparison more than once.
    if (attributeValue == null) {
      // attributeValue == null is fairly common, so we check for that first
      return null;
    }
    // If you change this class, you should run before and after
    // timing tests on large moml files, a good command to run is:
    // $PTII/bin/ptolemy -test $PTII/ptolemy/domains/ct/demo/CarTracking/CarTracking.xml
    // which will open up a large xml file and then close after 2 seconds.
    if (attributeName.equals("class")) {
      if (_classChanges.containsKey(attributeValue)) {
        String changedClassName = _classChanges.get(attributeValue);

        LOGGER.debug("ClassChanges: {} -> {}", attributeValue, changedClassName);
        // We found a class with a class change.
        MoMLParser.setModified(true);

        return changedClassName;
      }
    }
    return attributeValue;
  }

  /**
   * In this class, do nothing.
   *
   * @param container
   *          The object created by this element.
   * @param elementName
   *          The element name.
   * @param currentCharData
   *          The character data, which appears only in the doc and configure elements
   * @param xmlFile
   *          The file currently being parsed.
   * @exception Exception
   *              Not thrown in this base class.
   */
  @Override
  public void filterEndElement(NamedObj container, String elementName, StringBuffer currentCharData, String xmlFile) throws Exception {
  }

  /**
   * Add a class to be filtered. Note that if you add a class with this method, then you must remove it with {@link #remove(String)}, calling "new
   * ClassChanges()" will not remove a class that was added with this method.
   *
   * @param oldName
   *          The old name of the class to be filtered.
   * @param newName
   *          The new name of the class to be filtered. If the value is null, then the class in oldName will be removed.
   * @see #remove(String)
   */
  public void put(String oldName, String newName) {
    _classChanges.put(oldName, newName);
  }

  /**
   * Remove a class to be filtered.
   *
   * @param className
   *          The name of the class to be filtered out, for example "ptolemy.copernicus.kernel.GeneratorAttribute".
   * @see #put(String, String)
   */
  public void remove(String className) {
    _classChanges.remove(className);
  }

  /**
   * Return a string that describes what the filter does.
   *
   * @return the description of the filter that ends with a newline.
   */
  @Override
  public String toString() {
    StringBuffer results = new StringBuffer(getClass().getName() + ": change any class names that have been " + "renamed and remove obsolete classes.\n"
        + "Below are original class names followed by the new class names:\n");

    for (Map.Entry<String, String> classChange : _classChanges.entrySet()) {
      String className = classChange.getKey();
      results.append("\t" + className + "\t -> " + classChange.getValue() + "\n");
    }

    return results.toString();
  }

  ///////////////////////////////////////////////////////////////////
  //// private variables ////
  // Map of actor names a HashMap of property names to new classes.
  private static HashMap<String, String> _classChanges;

  static {
    ///////////////////////////////////////////////////////////
    // Actors and attributes that have changed names.
    _classChanges = new HashMap<>();
    _classChanges.put("ptolemy.actor.gui.WindowPropertiesAttribute", "org.eclipse.triquetrum.workflow.ui.WindowPropertiesAttribute");
    _classChanges.put("ptolemy.actor.lib.gui.XYPlotter", "org.eclipse.triquetrum.workflow.actor.plot.XYPlotActor");
  }
}
