package org.jresearch.threetenbp.gwt.time.client.loader;

import javax.annotation.Nonnull;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface TimeJsBundle extends ClientBundle {

	@Nonnull
	@Source("support.js")
	public TextResource support();

}
