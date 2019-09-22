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
package org.eclipse.triquetrum.workflow.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.triquetrum.workflow.model.Annotation;
import org.eclipse.triquetrum.workflow.model.TriqPackage;

import ptolemy.kernel.util.IllegalActionException;
import ptolemy.vergil.kernel.attributes.TextAttribute;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Annotation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.AnnotationImpl#getText <em>Text</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.AnnotationImpl#getFontFamily <em>Font Family</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.AnnotationImpl#getTextSize <em>Text Size</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.AnnotationImpl#isBold <em>Bold</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.AnnotationImpl#isItalic <em>Italic</em>}</li>
 * <li>{@link org.eclipse.triquetrum.workflow.model.impl.AnnotationImpl#getColor <em>Color</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AnnotationImpl extends AttributeImpl implements Annotation {
  /**
   * The default value of the '{@link #getText() <em>Text</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getText()
   * @generated
   * @ordered
   */
  protected static final String TEXT_EDEFAULT = "Configure to edit text.";

  /**
   * The cached value of the '{@link #getText() <em>Text</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getText()
   * @generated
   * @ordered
   */
  protected String text = TEXT_EDEFAULT;

  /**
   * The default value of the '{@link #getFontFamily() <em>Font Family</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getFontFamily()
   * @generated
   * @ordered
   */
  protected static final String FONT_FAMILY_EDEFAULT = "Arial";

  /**
   * The cached value of the '{@link #getFontFamily() <em>Font Family</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getFontFamily()
   * @generated
   * @ordered
   */
  protected String fontFamily = FONT_FAMILY_EDEFAULT;

  /**
   * The default value of the '{@link #getTextSize() <em>Text Size</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getTextSize()
   * @generated
   * @ordered
   */
  protected static final int TEXT_SIZE_EDEFAULT = 8;

  /**
   * The cached value of the '{@link #getTextSize() <em>Text Size</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getTextSize()
   * @generated
   * @ordered
   */
  protected int textSize = TEXT_SIZE_EDEFAULT;

  /**
   * The default value of the '{@link #isBold() <em>Bold</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #isBold()
   * @generated
   * @ordered
   */
  protected static final boolean BOLD_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isBold() <em>Bold</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #isBold()
   * @generated
   * @ordered
   */
  protected boolean bold = BOLD_EDEFAULT;

  /**
   * The default value of the '{@link #isItalic() <em>Italic</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #isItalic()
   * @generated
   * @ordered
   */
  protected static final boolean ITALIC_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isItalic() <em>Italic</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #isItalic()
   * @generated
   * @ordered
   */
  protected boolean italic = ITALIC_EDEFAULT;

  /**
   * The default value of the '{@link #getColor() <em>Color</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getColor()
   * @generated
   * @ordered
   */
  protected static final String COLOR_EDEFAULT = "0,0,0,255";

  /**
   * The cached value of the '{@link #getColor() <em>Color</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getColor()
   * @generated
   * @ordered
   */
  protected String color = COLOR_EDEFAULT;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected AnnotationImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.ANNOTATION;
  }

  @Override
  public void buildWrappedObject() {
    super.buildWrappedObject();
    try {
      if (getText() != null) {
        if (!(wrappedObject instanceof TextAttribute)) {
          throw new Exception("Property cannot be assigned a value: " + getName() + " (instance of " + wrappedType + ")");
        } else {
          TextAttribute ptObject = (TextAttribute) wrappedObject;
          ptObject.text.setExpression(getText());
          ptObject.textSize.setExpression(Integer.toString(getTextSize()));
          ptObject.fontFamily.setExpression(getFontFamily());
          ptObject.bold.setExpression(Boolean.toString(isBold()));
          ptObject.italic.setExpression(Boolean.toString(isItalic()));
          // Propagate. This has the side effect of marking the object overridden.
          ptObject.text.propagateValue();
          ptObject.textSize.propagateValue();
          ptObject.fontFamily.propagateValue();
          ptObject.bold.propagateValue();
          ptObject.italic.propagateValue();
        }
      }
    } catch (IllegalActionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void applyWrappedObject() {
    if (!isDeepComplete()) {
      super.applyWrappedObject();
      TextAttribute textAttr = (TextAttribute) wrappedObject;
      setText(textAttr.text.getExpression());
      setFontFamily(textAttr.fontFamily.getExpression());
      setTextSize(Integer.parseInt(textAttr.textSize.getExpression()));
      setBold(Boolean.getBoolean(textAttr.bold.getExpression()));
      setItalic(Boolean.getBoolean(textAttr.italic.getExpression()));
    }
  }

  @Override
  public TextAttribute getWrappedObject() {
    return (TextAttribute) super.getWrappedObject();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String getText() {
    return text;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void setText(String newText) {
    String oldText = text;
    text = newText;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.ANNOTATION__TEXT, oldText, text));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String getFontFamily() {
    return fontFamily;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void setFontFamily(String newFontFamily) {
    String oldFontFamily = fontFamily;
    fontFamily = newFontFamily;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.ANNOTATION__FONT_FAMILY, oldFontFamily, fontFamily));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public int getTextSize() {
    return textSize;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void setTextSize(int newTextSize) {
    int oldTextSize = textSize;
    textSize = newTextSize;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.ANNOTATION__TEXT_SIZE, oldTextSize, textSize));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public boolean isBold() {
    return bold;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void setBold(boolean newBold) {
    boolean oldBold = bold;
    bold = newBold;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.ANNOTATION__BOLD, oldBold, bold));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public boolean isItalic() {
    return italic;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void setItalic(boolean newItalic) {
    boolean oldItalic = italic;
    italic = newItalic;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.ANNOTATION__ITALIC, oldItalic, italic));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String getColor() {
    return color;
  }

  /**
   * <!-- begin-user-doc --> TODO check compatibility between the color string presentations in Triq and Ptolemy II <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void setColor(String newColor) {
    String oldColor = color;
    color = newColor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TriqPackage.ANNOTATION__COLOR, oldColor, color));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case TriqPackage.ANNOTATION__TEXT:
      return getText();
    case TriqPackage.ANNOTATION__FONT_FAMILY:
      return getFontFamily();
    case TriqPackage.ANNOTATION__TEXT_SIZE:
      return getTextSize();
    case TriqPackage.ANNOTATION__BOLD:
      return isBold();
    case TriqPackage.ANNOTATION__ITALIC:
      return isItalic();
    case TriqPackage.ANNOTATION__COLOR:
      return getColor();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case TriqPackage.ANNOTATION__TEXT:
      setText((String) newValue);
      return;
    case TriqPackage.ANNOTATION__FONT_FAMILY:
      setFontFamily((String) newValue);
      return;
    case TriqPackage.ANNOTATION__TEXT_SIZE:
      setTextSize((Integer) newValue);
      return;
    case TriqPackage.ANNOTATION__BOLD:
      setBold((Boolean) newValue);
      return;
    case TriqPackage.ANNOTATION__ITALIC:
      setItalic((Boolean) newValue);
      return;
    case TriqPackage.ANNOTATION__COLOR:
      setColor((String) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
    case TriqPackage.ANNOTATION__TEXT:
      setText(TEXT_EDEFAULT);
      return;
    case TriqPackage.ANNOTATION__FONT_FAMILY:
      setFontFamily(FONT_FAMILY_EDEFAULT);
      return;
    case TriqPackage.ANNOTATION__TEXT_SIZE:
      setTextSize(TEXT_SIZE_EDEFAULT);
      return;
    case TriqPackage.ANNOTATION__BOLD:
      setBold(BOLD_EDEFAULT);
      return;
    case TriqPackage.ANNOTATION__ITALIC:
      setItalic(ITALIC_EDEFAULT);
      return;
    case TriqPackage.ANNOTATION__COLOR:
      setColor(COLOR_EDEFAULT);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case TriqPackage.ANNOTATION__TEXT:
      return TEXT_EDEFAULT == null ? text != null : !TEXT_EDEFAULT.equals(text);
    case TriqPackage.ANNOTATION__FONT_FAMILY:
      return FONT_FAMILY_EDEFAULT == null ? fontFamily != null : !FONT_FAMILY_EDEFAULT.equals(fontFamily);
    case TriqPackage.ANNOTATION__TEXT_SIZE:
      return textSize != TEXT_SIZE_EDEFAULT;
    case TriqPackage.ANNOTATION__BOLD:
      return bold != BOLD_EDEFAULT;
    case TriqPackage.ANNOTATION__ITALIC:
      return italic != ITALIC_EDEFAULT;
    case TriqPackage.ANNOTATION__COLOR:
      return COLOR_EDEFAULT == null ? color != null : !COLOR_EDEFAULT.equals(color);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy())
      return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (text: ");
    result.append(text);
    result.append(", fontFamily: ");
    result.append(fontFamily);
    result.append(", textSize: ");
    result.append(textSize);
    result.append(", bold: ");
    result.append(bold);
    result.append(", italic: ");
    result.append(italic);
    result.append(", color: ");
    result.append(color);
    result.append(')');
    return result.toString();
  }

} // AnnotationImpl
