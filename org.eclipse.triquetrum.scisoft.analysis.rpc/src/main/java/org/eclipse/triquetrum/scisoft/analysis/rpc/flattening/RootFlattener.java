/*******************************************************************************
 * Copyright (c) 2012-2016 Diamond Light Source Ltd., 
 *                         Kichwa Coders & iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    DLS, Kichwa Coders - initial API and implementation and/or initial documentation
 *    Erwin De Ley - extraction from DAWN to ease reuse in other contexts
 *******************************************************************************/

package org.eclipse.triquetrum.scisoft.analysis.rpc.flattening;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.triquetrum.scisoft.analysis.rpc.FlatteningService;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.helpers.ExceptionHelper;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.helpers.ListHelper;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.helpers.MapHelper;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.helpers.NoneFlatteningHelper;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.helpers.ObjectArrayHelper;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.helpers.PassThroughFlatteningHelper;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.helpers.PrimitiveBoolArrayHelper;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.helpers.PrimitiveDoubleArrayHelper;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.helpers.PrimitiveIntArrayHelper;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.helpers.SelfFlattensHelper;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.helpers.StackTraceElementHelper;
import org.eclipse.triquetrum.scisoft.analysis.rpc.flattening.helpers.UUIDHelper;

/**
 * {@link IRootFlattener} implementation to flatten/unflatten objects for transportation over XMLRPC.
 */
public class RootFlattener implements IRootFlattener {

  private List<IFlattener<?>> flatteningHelpers;
  private File tempLocation;

  /**
   * Create a new {@link RootFlattener}.
   * <p>
   * NOTE: If you are using AnalysisRpc client/server you probably want to use the singleton version of this class provided by
   * {@link FlatteningService#getFlattener()}.
   */
  public RootFlattener() {
    flatteningHelpers = new LinkedList<IFlattener<?>>();

    // These are used in order by flatten() so how they are added implies
    // precedence
    flatteningHelpers.add(new SelfFlattensHelper());
    flatteningHelpers.add(new NoneFlatteningHelper());
    flatteningHelpers.add(new UUIDHelper());
    flatteningHelpers.add(new PassThroughFlatteningHelper());
    flatteningHelpers.add(new ListHelper());
    flatteningHelpers.add(new ExceptionHelper());
    flatteningHelpers.add(new StackTraceElementHelper());
    flatteningHelpers.add(new MapHelper());
    flatteningHelpers.add(new PrimitiveIntArrayHelper());
    flatteningHelpers.add(new PrimitiveDoubleArrayHelper());
    flatteningHelpers.add(new PrimitiveBoolArrayHelper());
    flatteningHelpers.add(new ObjectArrayHelper());
  }

  @Override
  public Object flatten(Object obj) throws UnsupportedOperationException {
    for (Iterator<IFlattener<?>> iterator = flatteningHelpers.iterator(); iterator.hasNext();) {
      IFlattener<?> flatteningHelper = iterator.next();
      if (flatteningHelper.canFlatten(obj))
        return flatteningHelper.flatten(obj, this);
    }

    throw new UnsupportedOperationException("Value " + obj.toString() + " is of unknown type");
  }

  /**
   * Take an object that has been flattened for transmission over XML RPC and try to reconstruct the original object. Containers are always returned as arrays
   * of unflattened objects. Known types e.g. RectangularROI are reconstructed to their respective types.
   * 
   * @param obj
   *          a flattened object. This object is not modified.
   * @return the unflattened version of obj
   */
  @Override
  public Object unflatten(Object obj) {
    for (Iterator<IFlattener<?>> iterator = flatteningHelpers.iterator(); iterator.hasNext();) {
      IFlattener<?> flatteningHelper = iterator.next();
      if (flatteningHelper.canUnFlatten(obj))
        return flatteningHelper.unflatten(obj, this);
    }
    throw new UnsupportedOperationException("Value " + obj.toString() + " is of unknown type");
  }

  @Override
  public boolean canFlatten(Object obj) {
    for (Iterator<IFlattener<?>> iterator = flatteningHelpers.iterator(); iterator.hasNext();) {
      IFlattener<?> flatteningHelper = iterator.next();
      if (flatteningHelper.canFlatten(obj))
        return true;
    }
    return false;
  }

  @Override
  public boolean canUnFlatten(Object obj) {
    if (obj == null) {
      return false;
    }

    for (Iterator<IFlattener<?>> iterator = flatteningHelpers.iterator(); iterator.hasNext();) {
      IFlattener<?> flatteningHelper = iterator.next();
      if (flatteningHelper.canUnFlatten(obj))
        return true;
    }
    return false;
  }

  @Override
  public void setTempLocation(String tempLocation) {
    if (tempLocation == null) {
      this.tempLocation = null;
    } else {
      this.tempLocation = new File(tempLocation);
    }
  }

  @Override
  public File getTempLocation() {
    return tempLocation;
  }

  @Override
  public void addHelper(IFlattener<?> helper) {
    flatteningHelpers.add(0, helper);
  }

}
