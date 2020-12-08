package org.jresearch.threetenbp.gwt.client;

import static jsinterop.annotations.JsPackage.GLOBAL;

import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = GLOBAL, name = "Object")
public class DecimalProperty {
	public String zeroDigit;
	public String positiveSign;
	public String negativeSign;
	public String decimalSeparator;
}