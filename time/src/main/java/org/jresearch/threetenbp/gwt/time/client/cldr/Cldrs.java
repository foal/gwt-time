package org.jresearch.threetenbp.gwt.time.client.cldr;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.jresearch.gwt.time.apt.base.Bases;
import org.jresearch.gwt.time.apt.data.client.Region;

public class Cldrs extends Bases {

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
