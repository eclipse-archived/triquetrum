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
package org.eclipse.triquetrum.workflow.actor.ui.activator;

import java.util.ResourceBundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import ptolemy.actor.injection.PtolemyInjector;
import ptolemy.actor.injection.PtolemyModule;

public class Activator implements BundleActivator {

  @Override
  public void start(BundleContext context) throws Exception {
    PtolemyInjector.createInjector(new PtolemyModule(getClass().getClassLoader(), ResourceBundle.getBundle("org.eclipse.triquetrum.workflow.actor.ui.ActorModule")));
  }

  @Override
  public void stop(BundleContext context) throws Exception {
  }

}
