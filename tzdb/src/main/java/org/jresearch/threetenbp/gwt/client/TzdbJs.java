package org.jresearch.threetenbp.gwt.client;

import static jsinterop.annotations.JsPackage.GLOBAL;

import javax.annotation.Nonnull;

import org.gwtproject.typedarrays.shared.Uint8Array;

import elemental2.core.ArrayBuffer;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = GLOBAL, name = "support")
public class TzdbJs {

	@Nonnull
	@JsMethod(namespace = "Base64Binary")
	public static native ArrayBuffer decodeArrayBuffer(String base64);

	@Nonnull
	@JsMethod(namespace = "Base64Binary")
	public static native Uint8Array decode(String base64);

}
