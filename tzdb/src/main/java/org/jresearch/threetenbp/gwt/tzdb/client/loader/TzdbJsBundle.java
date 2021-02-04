package org.jresearch.threetenbp.gwt.tzdb.client.loader;

import javax.annotation.Nonnull;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface TzdbJsBundle extends ClientBundle {

	@Nonnull
	@Source("base64-binary.js")
	public TextResource base64binary();

	@Nonnull
	@Source("TZDB.txt")
	public TextResource tzdbEncoded();

}
