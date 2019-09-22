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
package org.eclipse.triquetrum;

/**
 *
 * TODO : check what's better : different methods per large event categories or one hyper-generic method as below...
 *
 */
public interface EventListener<T extends Event> extends java.util.EventListener {

  /**
   *
   * @param event
   */
  void handle(T event);

}
