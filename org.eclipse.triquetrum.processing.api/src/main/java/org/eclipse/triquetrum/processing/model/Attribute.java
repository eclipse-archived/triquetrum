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

package org.eclipse.triquetrum.processing.model;

import java.io.Serializable;

/**
 * Attributes are identifiable name/value pairs.
 * They are typically associated with parent entities like requests or tasks etc.
 * 
 * @author erwin
 *
 */
public interface Attribute<V extends Serializable> extends NamedValue<V>, Identifiable {

}
