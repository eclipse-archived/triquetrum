/*******************************************************************************
 * Copyright (c) 2015 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.processing.service;

import org.eclipse.triquetrum.processing.model.Task;
import org.eclipse.triquetrum.validation.service.ValidationService;

/**
 * TODO To be evaluated if this kind of "marker interface" adds any value.
 * It may be handy as the interface for the OSGi service registration and retrieval?
 * 
 */
public interface TaskValidationService extends ValidationService<Task>{
}
