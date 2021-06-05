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
package org.jresearch.threetenbp.gwt.emu.java.time.format;

import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.AMPM_OF_DAY;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.DAY_OF_WEEK;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.ERA;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.MONTH_OF_YEAR;

import org.jresearch.threetenbp.gwt.emu.java.time.Month;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.IsoEra;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.IsoFields;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalField;
import org.jresearch.threetenbp.gwt.emu.org.jresearch.threetenbp.gwt.time.client.Support;

//import java.text.DateFormatSymbols;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
//import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Service Provider Implementation to obtain date-time text for a field.
 * <p>
 * This implementation is based on extraction of data from a {@link DateFormatSymbols}.
 *
 * <h3>Specification for implementors</h3>
 * This class is immutable and thread-safe.
 */
final class SimpleDateTimeTextProvider extends DateTimeTextProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDateTimeTextProvider.class);

     // TODO: Better implementation based on CLDR

    /** Comparator. */
    private static final Comparator<Entry<String, Long>> COMPARATOR = new Comparator<Entry<String, Long>>() {
        @Override
        public int compare(Entry<String, Long> obj1, Entry<String, Long> obj2) {
            return obj2.getKey().length() - obj1.getKey().length();  // longest to shortest
        }
    };

	// GWT specific
	private static final int AM = 0;
	private static final int PM = 1;

    /** Cache. */
    private final ConcurrentMap<Entry<TemporalField, Locale>, Object> cache =
            new ConcurrentHashMap<Entry<TemporalField, Locale>, Object>(16, 0.75f);

    //-----------------------------------------------------------------------
    @Override
    public String getText(TemporalField field, long value, TextStyle style, Locale locale) {
		LOGGER.debug("Return text for field {}, value {}, style {}, locale {}", field, value, style, locale);
        Object store = findStore(field, locale);
        if (store instanceof LocaleStore) {
            return ((LocaleStore) store).getText(value, style);
        }
        return null;
    }

    @Override
    public Iterator<Entry<String, Long>> getTextIterator(TemporalField field, TextStyle style, Locale locale) {
        Object store = findStore(field, locale);
        if (store instanceof LocaleStore) {
            return ((LocaleStore) store).getTextIterator(style);
        }
        return null;
    }

    //-----------------------------------------------------------------------
    private Object findStore(TemporalField field, Locale locale) {
        Entry<TemporalField, Locale> key = createEntry(field, locale);
        Object store = cache.get(key);
        if (store == null) {
            store = createStore(field, locale);
            cache.putIfAbsent(key, store);
            store = cache.get(key);
        }
        return store;
    }

	// GWT specific
    private Object createStore(TemporalField field, Locale locale) {
        if (field == MONTH_OF_YEAR) {
            Map<TextStyle, Map<Long, String>> styleMap = new HashMap<TextStyle, Map<Long,String>>();
            Long f1 = 1L;
            Long f2 = 2L;
            Long f3 = 3L;
            Long f4 = 4L;
            Long f5 = 5L;
            Long f6 = 6L;
            Long f7 = 7L;
            Long f8 = 8L;
            Long f9 = 9L;
            Long f10 = 10L;
            Long f11 = 11L;
            Long f12 = 12L;
			String[] array = Support.displayMonths("long", false, locale.toLanguageTag());
            Map<Long, String> map = new HashMap<Long, String>();
			map.put(f1, array[Month.JANUARY.ordinal()]);
			map.put(f2, array[Month.FEBRUARY.ordinal()]);
			map.put(f3, array[Month.MARCH.ordinal()]);
			map.put(f4, array[Month.APRIL.ordinal()]);
			map.put(f5, array[Month.MAY.ordinal()]);
			map.put(f6, array[Month.JUNE.ordinal()]);
			map.put(f7, array[Month.JULY.ordinal()]);
			map.put(f8, array[Month.AUGUST.ordinal()]);
			map.put(f9, array[Month.SEPTEMBER.ordinal()]);
			map.put(f10, array[Month.OCTOBER.ordinal()]);
			map.put(f11, array[Month.NOVEMBER.ordinal()]);
			map.put(f12, array[Month.DECEMBER.ordinal()]);
            styleMap.put(TextStyle.FULL, map);

			array = Support.displayMonths("long", true, locale.toLanguageTag());
			map = new HashMap<Long, String>();
			map.put(f1, array[Month.JANUARY.ordinal()]);
			map.put(f2, array[Month.FEBRUARY.ordinal()]);
			map.put(f3, array[Month.MARCH.ordinal()]);
			map.put(f4, array[Month.APRIL.ordinal()]);
			map.put(f5, array[Month.MAY.ordinal()]);
			map.put(f6, array[Month.JUNE.ordinal()]);
			map.put(f7, array[Month.JULY.ordinal()]);
			map.put(f8, array[Month.AUGUST.ordinal()]);
			map.put(f9, array[Month.SEPTEMBER.ordinal()]);
			map.put(f10, array[Month.OCTOBER.ordinal()]);
			map.put(f11, array[Month.NOVEMBER.ordinal()]);
			map.put(f12, array[Month.DECEMBER.ordinal()]);
			styleMap.put(TextStyle.FULL_STANDALONE, map);

			array = Support.displayMonths("short", false, locale.toLanguageTag());
			map = new HashMap<Long, String>();
			map.put(f1, array[Month.JANUARY.ordinal()]);
			map.put(f2, array[Month.FEBRUARY.ordinal()]);
			map.put(f3, array[Month.MARCH.ordinal()]);
			map.put(f4, array[Month.APRIL.ordinal()]);
			map.put(f5, array[Month.MAY.ordinal()]);
			map.put(f6, array[Month.JUNE.ordinal()]);
			map.put(f7, array[Month.JULY.ordinal()]);
			map.put(f8, array[Month.AUGUST.ordinal()]);
			map.put(f9, array[Month.SEPTEMBER.ordinal()]);
			map.put(f10, array[Month.OCTOBER.ordinal()]);
			map.put(f11, array[Month.NOVEMBER.ordinal()]);
			map.put(f12, array[Month.DECEMBER.ordinal()]);
			styleMap.put(TextStyle.SHORT, map);

			array = Support.displayMonths("short", true, locale.toLanguageTag());
            map = new HashMap<Long, String>();
			map.put(f1, array[Month.JANUARY.ordinal()]);
			map.put(f2, array[Month.FEBRUARY.ordinal()]);
			map.put(f3, array[Month.MARCH.ordinal()]);
			map.put(f4, array[Month.APRIL.ordinal()]);
			map.put(f5, array[Month.MAY.ordinal()]);
			map.put(f6, array[Month.JUNE.ordinal()]);
			map.put(f7, array[Month.JULY.ordinal()]);
			map.put(f8, array[Month.AUGUST.ordinal()]);
			map.put(f9, array[Month.SEPTEMBER.ordinal()]);
			map.put(f10, array[Month.OCTOBER.ordinal()]);
			map.put(f11, array[Month.NOVEMBER.ordinal()]);
			map.put(f12, array[Month.DECEMBER.ordinal()]);
			styleMap.put(TextStyle.SHORT_STANDALONE, map);

			array = Support.displayMonths("narrow", false, locale.toLanguageTag());
			map = new HashMap<Long, String>();
			map.put(f1, array[Month.JANUARY.ordinal()]);
			map.put(f2, array[Month.FEBRUARY.ordinal()]);
			map.put(f3, array[Month.MARCH.ordinal()]);
			map.put(f4, array[Month.APRIL.ordinal()]);
			map.put(f5, array[Month.MAY.ordinal()]);
			map.put(f6, array[Month.JUNE.ordinal()]);
			map.put(f7, array[Month.JULY.ordinal()]);
			map.put(f8, array[Month.AUGUST.ordinal()]);
			map.put(f9, array[Month.SEPTEMBER.ordinal()]);
			map.put(f10, array[Month.OCTOBER.ordinal()]);
			map.put(f11, array[Month.NOVEMBER.ordinal()]);
			map.put(f12, array[Month.DECEMBER.ordinal()]);
            styleMap.put(TextStyle.NARROW, map);

			array = Support.displayMonths("narrow", true, locale.toLanguageTag());
            map = new HashMap<Long, String>();
			map.put(f1, array[Month.JANUARY.ordinal()]);
			map.put(f2, array[Month.FEBRUARY.ordinal()]);
			map.put(f3, array[Month.MARCH.ordinal()]);
			map.put(f4, array[Month.APRIL.ordinal()]);
			map.put(f5, array[Month.MAY.ordinal()]);
			map.put(f6, array[Month.JUNE.ordinal()]);
			map.put(f7, array[Month.JULY.ordinal()]);
			map.put(f8, array[Month.AUGUST.ordinal()]);
			map.put(f9, array[Month.SEPTEMBER.ordinal()]);
			map.put(f10, array[Month.OCTOBER.ordinal()]);
			map.put(f11, array[Month.NOVEMBER.ordinal()]);
			map.put(f12, array[Month.DECEMBER.ordinal()]);
			styleMap.put(TextStyle.NARROW_STANDALONE, map);
            return createLocaleStore(styleMap);
        }
        if (field == DAY_OF_WEEK) {
            Map<TextStyle, Map<Long, String>> styleMap = new HashMap<TextStyle, Map<Long,String>>();
            Long f1 = 1L;
            Long f2 = 2L;
            Long f3 = 3L;
            Long f4 = 4L;
            Long f5 = 5L;
            Long f6 = 6L;
            Long f7 = 7L;

			String[] array = Support.displayWeekdays("long", false, locale.toLanguageTag());
			LOGGER.debug("weekdays, long, locale {}, formated {}", locale, array);
            Map<Long, String> map = new HashMap<Long, String>();
			map.put(f1, array[0]);
			map.put(f2, array[1]);
			map.put(f3, array[2]);
			map.put(f4, array[3]);
			map.put(f5, array[4]);
			map.put(f6, array[5]);
			map.put(f7, array[6]);
            styleMap.put(TextStyle.FULL, map);

			array = Support.displayWeekdays("long", true, locale.toLanguageTag());
			LOGGER.debug("weekdays, long, locale {}, standalone {}", locale, array);
			map = new HashMap<Long, String>();
			map.put(f1, array[0]);
			map.put(f2, array[1]);
			map.put(f3, array[2]);
			map.put(f4, array[3]);
			map.put(f5, array[4]);
			map.put(f6, array[5]);
			map.put(f7, array[6]);
			styleMap.put(TextStyle.FULL_STANDALONE, map);

			array = Support.displayWeekdays("short", false, locale.toLanguageTag());
            map = new HashMap<Long, String>();
			map.put(f1, array[0]);
			map.put(f2, array[1]);
			map.put(f3, array[2]);
			map.put(f4, array[3]);
			map.put(f5, array[4]);
			map.put(f6, array[5]);
			map.put(f7, array[6]);
			styleMap.put(TextStyle.SHORT, map);

			array = Support.displayWeekdays("short", true, locale.toLanguageTag());
			map = new HashMap<Long, String>();
			map.put(f1, array[0]);
			map.put(f2, array[1]);
			map.put(f3, array[2]);
			map.put(f4, array[3]);
			map.put(f5, array[4]);
			map.put(f6, array[5]);
			map.put(f7, array[6]);
			styleMap.put(TextStyle.SHORT_STANDALONE, map);

			array = Support.displayWeekdays("narrow", false, locale.toLanguageTag());
            map = new HashMap<Long, String>();
			map.put(f1, array[0]);
			map.put(f2, array[1]);
			map.put(f3, array[2]);
			map.put(f4, array[3]);
			map.put(f5, array[4]);
			map.put(f6, array[5]);
			map.put(f7, array[6]);
			styleMap.put(TextStyle.NARROW, map);

			array = Support.displayWeekdays("narrow", true, locale.toLanguageTag());
			map = new HashMap<Long, String>();
			map.put(f1, array[0]);
			map.put(f2, array[1]);
			map.put(f3, array[2]);
			map.put(f4, array[3]);
			map.put(f5, array[4]);
			map.put(f6, array[5]);
			map.put(f7, array[6]);
			styleMap.put(TextStyle.NARROW_STANDALONE, map);
            return createLocaleStore(styleMap);
        }
        if (field == AMPM_OF_DAY) {
            Map<TextStyle, Map<Long, String>> styleMap = new HashMap<TextStyle, Map<Long,String>>();

			String[] array = Support.displayAmpm("long", locale.toLanguageTag());
            Map<Long, String> map = new HashMap<Long, String>();
			map.put(0L, array[AM]);
			map.put(1L, array[PM]);
            styleMap.put(TextStyle.FULL, map);

			array = Support.displayAmpm("short", locale.toLanguageTag());
			map = new HashMap<Long, String>();
			map.put(0L, array[AM]);
			map.put(1L, array[PM]);
			styleMap.put(TextStyle.SHORT, map);

			array = Support.displayAmpm("narrow", locale.toLanguageTag());
			map = new HashMap<Long, String>();
			map.put(0L, array[AM]);
			map.put(1L, array[PM]);
			styleMap.put(TextStyle.NARROW, map);

            return createLocaleStore(styleMap);
        }
        if (field == ERA) {
            Map<TextStyle, Map<Long, String>> styleMap = new HashMap<TextStyle, Map<Long,String>>();

			String[] array = Support.displayEras("long", locale.toLanguageTag());
            Map<Long, String> map = new HashMap<Long, String>();
			map.put(0L, array[IsoEra.BCE.getValue()]);
			map.put(1L, array[IsoEra.CE.getValue()]);
			styleMap.put(TextStyle.FULL, map);

			array = Support.displayEras("short", locale.toLanguageTag());
			map = new HashMap<Long, String>();
			map.put(0L, array[IsoEra.BCE.getValue()]);
			map.put(1L, array[IsoEra.CE.getValue()]);
            styleMap.put(TextStyle.SHORT, map);

			array = Support.displayEras("narrow", locale.toLanguageTag());
            map = new HashMap<Long, String>();
			map.put(0L, array[IsoEra.BCE.getValue()]);
			map.put(1L, array[IsoEra.CE.getValue()]);
            styleMap.put(TextStyle.NARROW, map);
            return createLocaleStore(styleMap);
        }
		// GWT hard code English quarter text TODO
        if (field == IsoFields.QUARTER_OF_YEAR) {
            Map<TextStyle, Map<Long, String>> styleMap = new HashMap<TextStyle, Map<Long,String>>();
            Map<Long, String> map = new HashMap<Long, String>();
            map.put(1L, "Q1");
            map.put(2L, "Q2");
            map.put(3L, "Q3");
            map.put(4L, "Q4");
            styleMap.put(TextStyle.SHORT, map);
            map = new HashMap<Long, String>();
            map.put(1L, "1st quarter");
            map.put(2L, "2nd quarter");
            map.put(3L, "3rd quarter");
            map.put(4L, "4th quarter");
            styleMap.put(TextStyle.FULL, map);
            return createLocaleStore(styleMap);
        }
        return "";  // null marker for map
    }

    //-----------------------------------------------------------------------
    /**
     * Helper method to create an immutable entry.
     *
     * @param text  the text, not null
     * @param field  the field, not null
     * @return the entry, not null
     */
    private static <A, B> Entry<A, B> createEntry(A text, B field) {
        return new SimpleImmutableEntry<A, B>(text, field);
    }

    //-----------------------------------------------------------------------
    private static LocaleStore createLocaleStore(Map<TextStyle, Map<Long, String>> valueTextMap) {
		if (valueTextMap.containsKey(TextStyle.FULL) && valueTextMap.containsKey(TextStyle.FULL_STANDALONE) == false) {
			valueTextMap.put(TextStyle.FULL_STANDALONE, valueTextMap.get(TextStyle.FULL));
		}
		if (valueTextMap.containsKey(TextStyle.SHORT)
				&& valueTextMap.containsKey(TextStyle.SHORT_STANDALONE) == false) {
			valueTextMap.put(TextStyle.SHORT_STANDALONE, valueTextMap.get(TextStyle.SHORT));
		}
        if (valueTextMap.containsKey(TextStyle.NARROW) && valueTextMap.containsKey(TextStyle.NARROW_STANDALONE) == false) {
            valueTextMap.put(TextStyle.NARROW_STANDALONE, valueTextMap.get(TextStyle.NARROW));
        }
        return new LocaleStore(valueTextMap);
    }

    /**
     * Stores the text for a single locale.
     * <p>
     * Some fields have a textual representation, such as day-of-week or month-of-year.
     * These textual representations can be captured in this class for printing
     * and parsing.
     * <p>
     * This class is immutable and thread-safe.
     */
    static final class LocaleStore {
        /**
         * Map of value to text.
         */
        private final Map<TextStyle, Map<Long, String>> valueTextMap;
        /**
         * Parsable data.
         */
        private final Map<TextStyle, List<Entry<String, Long>>> parsable;

        //-----------------------------------------------------------------------
        /**
         * Constructor.
         *
         * @param valueTextMap  the map of values to text to store, assigned and not altered, not null
         */
        LocaleStore(Map<TextStyle, Map<Long, String>> valueTextMap) {
            this.valueTextMap = valueTextMap;
            Map<TextStyle, List<Entry<String, Long>>> map = new HashMap<TextStyle, List<Entry<String,Long>>>();
            List<Entry<String, Long>> allList = new ArrayList<Map.Entry<String,Long>>();
            for (TextStyle style : valueTextMap.keySet()) {
                Map<String, Entry<String, Long>> reverse = new HashMap<String, Map.Entry<String,Long>>();
                for (Map.Entry<Long, String> entry : valueTextMap.get(style).entrySet()) {
                    if (reverse.put(entry.getValue(), createEntry(entry.getValue(), entry.getKey())) != null) {
                        continue;  // not parsable, try next style
                    }
                }
                List<Entry<String, Long>> list = new ArrayList<Map.Entry<String,Long>>(reverse.values());
                Collections.sort(list, COMPARATOR);
                map.put(style, list);
                allList.addAll(list);
                map.put(null, allList);
            }
            Collections.sort(allList, COMPARATOR);
            this.parsable = map;
        }

        //-----------------------------------------------------------------------
        /**
         * Gets the text for the specified field value, locale and style
         * for the purpose of printing.
         *
         * @param value  the value to get text for, not null
         * @param style  the style to get text for, not null
         * @return the text for the field value, null if no text found
         */
        String getText(long value, TextStyle style) {
            Map<Long, String> map = valueTextMap.get(style);
            return map != null ? map.get(value) : null;
        }

        /**
         * Gets an iterator of text to field for the specified style for the purpose of parsing.
         * <p>
         * The iterator must be returned in order from the longest text to the shortest.
         *
         * @param style  the style to get text for, null for all parsable text
         * @return the iterator of text to field pairs, in order from longest text to shortest text,
         *  null if the style is not parsable
         */
        Iterator<Entry<String, Long>> getTextIterator(TextStyle style) {
            List<Entry<String, Long>> list = parsable.get(style);
            return list != null ? list.iterator() : null;
        }
    }

}
