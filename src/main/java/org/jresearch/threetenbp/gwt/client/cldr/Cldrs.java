package org.jresearch.threetenbp.gwt.client.cldr;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class Cldrs {

	private static final Map<String, Region> TERRITORY_INDEX = new HashMap<>(Region.values().length);

	static {
		Stream.of(Region.values())
				.forEach(r -> TERRITORY_INDEX.put(r.name().toUpperCase(), r));
	}

	public static Region regionOf(Locale locale) {
		return Optional.of(locale)
				.map(Locale::getCountry)
				.map(String::toUpperCase)
				.map(TERRITORY_INDEX::get)
				.orElse(Region._001);
	}

}
