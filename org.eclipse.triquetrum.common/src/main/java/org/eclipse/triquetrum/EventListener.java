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
package org.eclipse.triquetrum;


/**
 * 
 * TODO : check what's better : different methods per large event categories
 * or one hyper-generic method as below...
 * 
 */
public interface EventListener extends java.util.EventListener{
  
  /**
   * 
   * @param event
   */
  void handle(Event event);

}
