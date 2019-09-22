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
package org.eclipse.triquetrum.workflow.actor.ui.activator;

import java.util.ResourceBundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import ptolemy.actor.injection.PtolemyInjector;
import ptolemy.actor.injection.PtolemyModule;

public class Activator implements BundleActivator {

  @Override
  public void start(BundleContext context) throws Exception {
    PtolemyInjector
        .createInjector(new PtolemyModule(getClass().getClassLoader(), ResourceBundle.getBundle("org.eclipse.triquetrum.workflow.actor.ui.ActorModule")));
  }

  @Override
  public void stop(BundleContext context) throws Exception {
  }

}
