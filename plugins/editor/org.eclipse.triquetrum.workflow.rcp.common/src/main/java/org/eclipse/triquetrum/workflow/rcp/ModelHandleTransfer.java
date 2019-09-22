/*******************************************************************************
 * Copyright (c) 2015,2019 iSencia Belgium NV.
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
package org.eclipse.triquetrum.workflow.rcp;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.triquetrum.workflow.ModelHandle;

public class ModelHandleTransfer extends ByteArrayTransfer {

  private static final ModelHandleTransfer INSTANCE = new ModelHandleTransfer();
  private static final String TYPE_NAME = "ModelHandle Transfer"//$NON-NLS-1$
      + System.currentTimeMillis() + ":" + INSTANCE.hashCode();//$NON-NLS-1$
  private static final int TYPEID = registerType(TYPE_NAME);

  private ModelHandle modelHandle;
  private long startTime;

  
  /**
   * Returns the singleton instance.
   *
   * @return The singleton instance
   */
  public static ModelHandleTransfer getInstance() {
    return INSTANCE;
  }
  
  /**
   * Returns the ModelHandle.
   * 
   * @return The ModelHandle
   */
  public ModelHandle getModelHandle() {
    return modelHandle;
  }

  /**
   * The data object is not converted to bytes. It is held onto in a field.
   * Instead, a checksum is written out to prevent unwanted drags across
   * mulitple running copies of Eclipse.
   * 
   * @see org.eclipse.swt.dnd.Transfer#javaToNative(Object, TransferData)
   */
  public void javaToNative(Object object, TransferData transferData) {
    setModelHandle((ModelHandle)object);
    startTime = System.currentTimeMillis();
    if (transferData != null)
      super.javaToNative(String.valueOf(startTime).getBytes(),
          transferData);
  }

  /**
   * The data object is not converted to bytes. It is held onto in a field.
   * Instead, a checksum is written out to prevent unwanted drags across
   * mulitple running. copies of Eclipse.
   * 
   * @see org.eclipse.swt.dnd.Transfer#nativeToJava(TransferData)
   */
  public Object nativeToJava(TransferData transferData) {
    byte bytes[] = (byte[]) super.nativeToJava(transferData);
    if (bytes == null) {
      return null;
    }
    long startTime = Long.parseLong(new String(bytes));
    return (this.startTime == startTime) ? getModelHandle() : null;
  }

  /**
   * Sets the ModelHandle.
   * 
   * @param modelHandle
   *            The ModelHandle
   */
  public void setModelHandle(ModelHandle modelHandle) {
    this.modelHandle = modelHandle;
  }

  /**
   * @see org.eclipse.swt.dnd.Transfer#getTypeIds()
   */
  @Override
  protected int[] getTypeIds() {
    return new int[] { TYPEID };
  }

  /**
   * @see org.eclipse.swt.dnd.Transfer#getTypeNames()
   */
  @Override
  protected String[] getTypeNames() {
    return new String[] { TYPE_NAME };
  }
}
