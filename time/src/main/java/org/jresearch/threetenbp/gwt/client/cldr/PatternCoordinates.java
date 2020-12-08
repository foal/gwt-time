package org.jresearch.threetenbp.gwt.client.cldr;

import java.util.Locale;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;
import org.jresearch.gwt.time.apt.base.Chrono;

@Immutable
public interface PatternCoordinates {

	@Parameter
	Chrono chrono();

	@Parameter
	Locale locale();

}
