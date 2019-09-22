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
package org.eclipse.triquetrum.workflow.model;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Annotation</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.triquetrum.workflow.model.Annotation#getText <em>Text</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.Annotation#getFontFamily <em>Font Family</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.Annotation#getTextSize <em>Text Size</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.Annotation#isBold <em>Bold</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.Annotation#isItalic <em>Italic</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.Annotation#getColor <em>Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getAnnotation()
 * @model
 * @generated
 */
public interface Annotation extends Attribute {
  /**
   * Returns the value of the '<em><b>Text</b></em>' attribute. The default value is <code>"Configure to edit text."</code>. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Text</em>' attribute isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Text</em>' attribute.
   * @see #setText(String)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getAnnotation_Text()
   * @model default="Configure to edit text."
   * @generated
   */
  String getText();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.Annotation#getText <em>Text</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @param value
   *          the new value of the '<em>Text</em>' attribute.
   * @see #getText()
   * @generated
   */
  void setText(String value);

  /**
   * Returns the value of the '<em><b>Font Family</b></em>' attribute. The default value is <code>"Arial"</code>. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Font Family</em>' attribute isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Font Family</em>' attribute.
   * @see #setFontFamily(String)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getAnnotation_FontFamily()
   * @model default="Arial"
   * @generated
   */
  String getFontFamily();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.Annotation#getFontFamily <em>Font Family</em>}' attribute. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Font Family</em>' attribute.
   * @see #getFontFamily()
   * @generated
   */
  void setFontFamily(String value);

  /**
   * Returns the value of the '<em><b>Text Size</b></em>' attribute. The default value is <code>"8"</code>. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Text Size</em>' attribute isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Text Size</em>' attribute.
   * @see #setTextSize(int)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getAnnotation_TextSize()
   * @model default="8"
   * @generated
   */
  int getTextSize();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.Annotation#getTextSize <em>Text Size</em>}' attribute. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Text Size</em>' attribute.
   * @see #getTextSize()
   * @generated
   */
  void setTextSize(int value);

  /**
   * Returns the value of the '<em><b>Bold</b></em>' attribute. The default value is <code>"false"</code>. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bold</em>' attribute isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Bold</em>' attribute.
   * @see #setBold(boolean)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getAnnotation_Bold()
   * @model default="false"
   * @generated
   */
  boolean isBold();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.Annotation#isBold <em>Bold</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @param value
   *          the new value of the '<em>Bold</em>' attribute.
   * @see #isBold()
   * @generated
   */
  void setBold(boolean value);

  /**
   * Returns the value of the '<em><b>Italic</b></em>' attribute. The default value is <code>"false"</code>. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Italic</em>' attribute isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Italic</em>' attribute.
   * @see #setItalic(boolean)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getAnnotation_Italic()
   * @model default="false"
   * @generated
   */
  boolean isItalic();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.Annotation#isItalic <em>Italic</em>}' attribute. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Italic</em>' attribute.
   * @see #isItalic()
   * @generated
   */
  void setItalic(boolean value);

  /**
   * Returns the value of the '<em><b>Color</b></em>' attribute. The default value is <code>"0,0,0,255"</code>. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Color</em>' reference isn't clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Color</em>' attribute.
   * @see #setColor(String)
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#getAnnotation_Color()
   * @model default="0,0,0,255" required="true"
   * @generated
   */
  String getColor();

  /**
   * Sets the value of the '{@link org.eclipse.triquetrum.workflow.model.Annotation#getColor <em>Color</em>}' attribute. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Color</em>' attribute.
   * @see #getColor()
   * @generated
   */
  void setColor(String value);

} // Annotation
