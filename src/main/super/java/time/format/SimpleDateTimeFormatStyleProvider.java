/*
 * Copyright (c) 2007-present, Stephen Colebourne & Michael Nascimento Santos
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of JSR-310 nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package java.time.format;

import java.text.SimpleDateFormat;
import java.time.chrono.Chronology;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

import org.jresearch.gwt.time.apt.base.Bases;
import org.jresearch.gwt.time.apt.base.Chrono;
import org.jresearch.threetenbp.gwt.client.Support;
import org.jresearch.threetenbp.gwt.client.cldr.PatternCoordinates;
import org.jresearch.threetenbp.gwt.client.cldr.PatternInfo;

/**
 * The Service Provider Implementation to obtain date-time formatters for a style.
 * <p>
 * This implementation is based on extraction of data from a {@link SimpleDateFormat}.
 *
 * <h3>Specification for implementors</h3>
 * This class is immutable and thread-safe.
 */
@SuppressWarnings("nls")
final class SimpleDateTimeFormatStyleProvider extends DateTimeFormatStyleProvider {
    // TODO: Better implementation based on CLDR

    /** Cache of formatters. */
    private static final ConcurrentMap<String, Object> FORMATTER_CACHE =
                        new ConcurrentHashMap<String, Object>(16, 0.75f);

    @Override
	// GWT Specific
    public Locale[] getAvailableLocales() {
		return Support.supportedLocalesOfDateTimeFormat(Locale.getAvailableLocales());
    }

    @Override
	public DateTimeFormatter getFormatter(FormatStyle dateStyle, FormatStyle timeStyle, Chronology chrono, Locale locale) {
        if (dateStyle == null && timeStyle == null) {
            throw new IllegalArgumentException("Date and Time style must not both be null");
        }
        String key = chrono.getId() + '|' + locale.toString() + '|' + dateStyle + timeStyle;
        Object cached = FORMATTER_CACHE.get(key);
        if (cached != null) {
            if (cached.equals("")) {
                throw new IllegalArgumentException("Unable to convert DateFormat to DateTimeFormatter");
            }
            return (DateTimeFormatter) cached;
        }

//GWT Specific Localized patterns
		String pattern = null;
		Chrono c = Bases.ofJavaTine(chrono).orElse(Chrono.ISO);
		if (dateStyle != null) {
			Map<String, PatternCoordinates[]> dateMap = getDateMap(dateStyle);
			String datePattern = getPattern(dateMap, c, locale);
			if (timeStyle != null) {
				Map<String, PatternCoordinates[]> timeMap = getTimeMap(timeStyle);
				String timePattern = getPattern(timeMap, c, locale);

				FormatStyle dateTimeStyle = FormatStyle.values()[Math.min(dateStyle.ordinal(), timeStyle.ordinal())];
				Map<String, PatternCoordinates[]> dateTimeMap = getDateTimeMap(dateTimeStyle);
				String dateTimePattern = getPattern(dateTimeMap, c, locale);

				pattern = substitute(dateTimePattern, datePattern, timePattern);
			} else {
				pattern = datePattern;
			}
		} else {
			Map<String, PatternCoordinates[]> timeMap = getTimeMap(timeStyle);
			pattern = getPattern(timeMap, c, locale);
		}
		if (pattern != null) {
			DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(pattern).toFormatter(locale);
			FORMATTER_CACHE.putIfAbsent(key, formatter);
			return formatter;
		}
        FORMATTER_CACHE.putIfAbsent(key, "");
        throw new IllegalArgumentException("Unable to convert DateFormat to DateTimeFormatter");
    }

	private static String substitute(String dateTimePattern, String datePattern, String timePattern) {
		return dateTimePattern.replace("{1}", datePattern).replace("{0}", timePattern).replace("'", "");
	}

	private static String getPattern(Map<String, PatternCoordinates[]> patterns, Chrono chrono, Locale locale) {
		return patterns.entrySet().stream().filter(e -> isFor(e, chrono, locale)).findAny().map(Entry::getKey).orElseGet(() -> getPattern(patterns, chrono, up(locale)));
	}

	private static boolean isFor(Entry<String, PatternCoordinates[]> e, Chrono chrono, Locale locale) {
		return Stream.of(e.getValue()).anyMatch(pc -> isFor(pc, chrono, locale));
	}

	private static boolean isFor(PatternCoordinates pc, Chrono chrono, Locale locale) {
		return chrono.equals(pc.chrono()) && locale.equals(pc.locale());
	}

	private static Locale up(Locale locale) {
		String variant = locale.getVariant();
		if (variant.isEmpty()) {
			String country = locale.getCountry();
			if (country.isEmpty()) {
				return Locale.ROOT;
			}
			return new Locale(locale.getLanguage());
		}
		return new Locale(locale.getLanguage(), locale.getCountry());
	}

	private static Map<String, PatternCoordinates[]> getTimeMap(FormatStyle style) {
		switch (style) {
		case FULL:
			return PatternInfo.TIME_FULL_PATTERNS;
		case LONG:
			return PatternInfo.TIME_LONG_PATTERNS;
		case MEDIUM:
			return PatternInfo.TIME_MEDIUM_PATTERNS;
		case SHORT:
			return PatternInfo.TIME_SHORT_PATTERNS;
		default:
			throw new IllegalArgumentException("Unsupported FormatStyle: " + style);
		}
	}

	private static Map<String, PatternCoordinates[]> getDateMap(FormatStyle style) {
		switch (style) {
		case FULL:
			return PatternInfo.DATE_FULL_PATTERNS;
		case LONG:
			return PatternInfo.DATE_LONG_PATTERNS;
		case MEDIUM:
			return PatternInfo.DATE_MEDIUM_PATTERNS;
		case SHORT:
			return PatternInfo.DATE_SHORT_PATTERNS;
		default:
			throw new IllegalArgumentException("Unsupported FormatStyle: " + style);
		}
	}

	private static Map<String, PatternCoordinates[]> getDateTimeMap(FormatStyle style) {
		switch (style) {
		case FULL:
			return PatternInfo.DATE_TIME_FULL_PATTERNS;
		case LONG:
			return PatternInfo.DATE_TIME_LONG_PATTERNS;
		case MEDIUM:
			return PatternInfo.DATE_TIME_MEDIUM_PATTERNS;
		case SHORT:
			return PatternInfo.DATE_TIME_SHORT_PATTERNS;
		default:
			throw new IllegalArgumentException("Unsupported FormatStyle: " + style);
		}
	}

    /**
     * Converts the enum style to the old format style.
     * @param style  the enum style, not null
     * @return the int style
     */
    private int convertStyle(FormatStyle style) {
        return style.ordinal();  // indices happen to align
    }

}
