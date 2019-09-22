/*******************************************************************************
 * Copyright (c) 2017,2019 iSencia Belgium NV.
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
package org.eclipse.triquetrum.workflow.repository.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventAdmin;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The activator class controls the plug-in life cycle
 */
public class RepositoryPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.triquetrum.workflow.repository.ui"; //$NON-NLS-1$

	// The shared instance
	private static RepositoryPlugin plugin;

  private ServiceTracker<?, EventAdmin> eventAdminServiceTracker;

	/**
	 * The constructor
	 */
	public RepositoryPlugin() {
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		eventAdminServiceTracker = new ServiceTracker<>(context, EventAdmin.class.getName(), null);
		eventAdminServiceTracker.open();

	}

	public void stop(BundleContext context) throws Exception {
	  eventAdminServiceTracker.close();
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RepositoryPlugin getDefault() {
		return plugin;
	}
	
	public EventAdmin getEventAdminService() {
	  return eventAdminServiceTracker.getService();
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
