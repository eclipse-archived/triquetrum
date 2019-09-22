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
package org.eclipse.triquetrum.python.actor.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.Version;
import org.ptolemy.classloading.ModelElementClassProvider;
import org.ptolemy.classloading.osgi.DefaultModelElementClassProvider;
import org.ptolemy.commons.ThreeDigitVersionSpecification;
import org.ptolemy.commons.VersionSpecification;

public class Activator implements BundleActivator {

  @Override
  public void start(BundleContext context) throws Exception {

    // FIXME figure out a more compact way to create a version-aware provider,
    // that uses the bundle version but is not too dependent on OSGi APIs itself.
    Version bundleVersion = context.getBundle().getVersion();
    VersionSpecification providerVersion = new ThreeDigitVersionSpecification(bundleVersion.getMajor(), bundleVersion.getMinor(), bundleVersion.getMicro(),
        bundleVersion.getQualifier());

    _apSvcReg = context.registerService(ModelElementClassProvider.class.getName(), new DefaultModelElementClassProvider(providerVersion, PythonActor.class),
        null);
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    _apSvcReg.unregister();
  }

  // private stuff
  /** The svc registration for the actor provider */
  private ServiceRegistration<?> _apSvcReg;
}
