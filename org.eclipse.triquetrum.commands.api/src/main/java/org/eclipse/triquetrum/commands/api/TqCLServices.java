/*******************************************************************************
 * Copyright (c)  2016 Totaro Rodolfo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Rodolfo Totaro - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.commands.api;

import java.util.Collection;

import org.eclipse.triquetrum.commands.api.services.ModelBuilderService;
import org.eclipse.triquetrum.commands.api.services.TqCLLibraryProvider;
import org.eclipse.triquetrum.commands.api.services.impl.TcQLLibraryProviderProxy;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceObjects;
import org.osgi.framework.ServiceReference;

public class TqCLServices implements BundleActivator {

	private static BundleContext context;
	
	private static TqCLServices instance;
	
	private TcQLLibraryProviderProxy tcQLLibraryProviderProxy;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		TqCLServices.context = bundleContext;
		instance=this;
		
		ServiceReference<TcQLLibraryProviderProxy> serviceReference = getContext().getServiceReference(TcQLLibraryProviderProxy.class);
		tcQLLibraryProviderProxy = getContext().getService(serviceReference);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		TqCLServices.context = null;
		instance=null;
	}
	
	public static TqCLServices getInstance() {
		return instance;
	}

	@SuppressWarnings("rawtypes")
	public ModelBuilderService getModelBuilderService(Class<?> modelClass) {
		try {
			Collection<ServiceReference<ModelBuilderService>> serviceReferences = getContext()
					.getServiceReferences(ModelBuilderService.class, null);
			for (ServiceReference<ModelBuilderService> serviceReference : serviceReferences) {
				//TODO:Release service when done
				ServiceObjects<ModelBuilderService> serviceObjects = getContext().getServiceObjects(serviceReference);
				ModelBuilderService service = serviceObjects.getService();
				if (service.getSupportedModelClass() == modelClass) {
					return service;
				}
			}
		} catch (InvalidSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public TqCLLibraryProvider getTqclLibraryProvider()
	{
		return tcQLLibraryProviderProxy;
	}
	

}
