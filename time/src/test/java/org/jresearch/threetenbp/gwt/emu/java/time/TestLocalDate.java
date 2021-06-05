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
package org.jresearch.threetenbp.gwt.emu.java.time;

import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.ALIGNED_WEEK_OF_MONTH;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.ALIGNED_WEEK_OF_YEAR;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.DAY_OF_MONTH;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.DAY_OF_WEEK;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.DAY_OF_YEAR;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.EPOCH_DAY;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.ERA;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.PROLEPTIC_MONTH;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.YEAR;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.YEAR_OF_ERA;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit.CENTURIES;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit.DAYS;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit.DECADES;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit.MILLENNIA;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit.MONTHS;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit.WEEKS;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit.YEARS;

import org.jresearch.threetenbp.gwt.emu.java.time.Clock;
import org.jresearch.threetenbp.gwt.emu.java.time.DateTimeException;
import org.jresearch.threetenbp.gwt.emu.java.time.DayOfWeek;
import org.jresearch.threetenbp.gwt.emu.java.time.Instant;
import org.jresearch.threetenbp.gwt.emu.java.time.LocalDate;
import org.jresearch.threetenbp.gwt.emu.java.time.LocalDateTime;
import org.jresearch.threetenbp.gwt.emu.java.time.LocalTime;
import org.jresearch.threetenbp.gwt.emu.java.time.Month;
import org.jresearch.threetenbp.gwt.emu.java.time.Year;
import org.jresearch.threetenbp.gwt.emu.java.time.ZoneId;
import org.jresearch.threetenbp.gwt.emu.java.time.ZoneOffset;
import org.jresearch.threetenbp.gwt.emu.java.time.ZonedDateTime;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.IsoChronology;
import org.jresearch.threetenbp.gwt.emu.java.time.format.DateTimeFormatter;
import org.jresearch.threetenbp.gwt.emu.java.time.format.DateTimeParseException;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.JulianFields;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.MockFieldNoValue;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.Temporal;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalAccessor;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalAdjuster;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalField;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalQueries;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Test LocalDate.
 */
public class TestLocalDate extends AbstractDateTimeTest {

	private static ZoneOffset OFFSET_ZONE;
	private static ZoneId ZONE_PARIS;
	private static ZoneId ZONE_GAZA;

	private static LocalDate TEST_2007_07_15;
	private static long MAX_VALID_EPOCHDAYS;
	private static long MIN_VALID_EPOCHDAYS;
	private static LocalDate MAX_DATE;
	private static LocalDate MIN_DATE;
	private static Instant MAX_INSTANT;
	private static Instant MIN_INSTANT;

	@Override
	public void gwtSetUp() throws Exception {
		super.gwtSetUp();
		TEST_2007_07_15 = LocalDate.of(2007, 7, 15);

		LocalDate max = LocalDate.MAX;
		LocalDate min = LocalDate.MIN;
		MAX_VALID_EPOCHDAYS = max.toEpochDay();
		MIN_VALID_EPOCHDAYS = min.toEpochDay();
		MAX_DATE = max;
		MIN_DATE = min;
		MAX_INSTANT = max.atStartOfDay(ZoneOffset.UTC).toInstant();
		MIN_INSTANT = min.atStartOfDay(ZoneOffset.UTC).toInstant();

		OFFSET_ZONE = ZoneOffset.ofHours(1);
		ZONE_PARIS = ZoneId.of("Europe/Paris");
		ZONE_GAZA = ZoneId.of("Asia/Gaza");
	}

	// -----------------------------------------------------------------------
	@Override
	protected List<TemporalAccessor> samples() {
		TemporalAccessor[] array = { TEST_2007_07_15, LocalDate.MAX, LocalDate.MIN, };
		return Arrays.asList(array);
	}

	@Override
	protected List<TemporalField> validFields() {
		TemporalField[] array = { DAY_OF_WEEK, ALIGNED_DAY_OF_WEEK_IN_MONTH, ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR,
				DAY_OF_MONTH, DAY_OF_YEAR, EPOCH_DAY, ALIGNED_WEEK_OF_MONTH, ALIGNED_WEEK_OF_YEAR, MONTH_OF_YEAR,
				PROLEPTIC_MONTH, YEAR_OF_ERA, YEAR, ERA, JulianFields.JULIAN_DAY, JulianFields.MODIFIED_JULIAN_DAY,
				JulianFields.RATA_DIE, };
		return Arrays.asList(array);
	}

	@Override
	protected List<TemporalField> invalidFields() {
		List<TemporalField> list = new ArrayList<TemporalField>(Arrays.<TemporalField>asList(ChronoField.values()));
		list.removeAll(validFields());
		return list;
	}

//    public void test_serialization() throws IOException, ClassNotFoundException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(TEST_2007_07_15);
//        oos.close();
//
//        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
//                baos.toByteArray()));
//        assertEquals(ois.readObject(), TEST_2007_07_15);
//    }

//    public void test_immutable() {
//        Class<LocalDate> cls = LocalDate.class;
//        assertTrue(Modifier.isPublic(cls.getModifiers()));
//        assertTrue(Modifier.isFinal(cls.getModifiers()));
//        Field[] fields = cls.getDeclaredFields();
//        for (Field field : fields) {
//            if (field.getName().contains("$") == false) {
//                if (Modifier.isStatic(field.getModifiers())) {
//                    assertTrue("Field:" + field.getName(), Modifier.isFinal(field.getModifiers()));
//                } else {
//                    assertTrue("Field:" + field.getName(), Modifier.isPrivate(field.getModifiers()));
//                    assertTrue("Field:" + field.getName(), Modifier.isFinal(field.getModifiers()));
//                }
//            }
//        }
//    }

	// -----------------------------------------------------------------------
	private void check(LocalDate test_2008_02_29, int y, int m, int d) {
		assertEquals(test_2008_02_29.getYear(), y);
		assertEquals(test_2008_02_29.getMonth().getValue(), m);
		assertEquals(test_2008_02_29.getDayOfMonth(), d);
	}

	// -----------------------------------------------------------------------
	// now()
	// -----------------------------------------------------------------------
	@Test
	public void test_now() {
		LocalDate expected = LocalDate.now(Clock.systemDefaultZone());
		LocalDate test = LocalDate.now();
		for (int i = 0; i < 100; i++) {
			if (expected.equals(test)) {
				return;
			}
			expected = LocalDate.now(Clock.systemDefaultZone());
			test = LocalDate.now();
		}
		assertEquals(test, expected);
	}

	// -----------------------------------------------------------------------
	// now(ZoneId)
	// -----------------------------------------------------------------------
	@Test(expected = NullPointerException.class)
	public void test_now_ZoneId_nullZoneId() {
		try {
			LocalDate.now((ZoneId) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	public void test_now_ZoneId() {
		ZoneId zone = ZoneId.of("UTC+01:02:03");
		LocalDate expected = LocalDate.now(Clock.system(zone));
		LocalDate test = LocalDate.now(zone);
		for (int i = 0; i < 100; i++) {
			if (expected.equals(test)) {
				return;
			}
			expected = LocalDate.now(Clock.system(zone));
			test = LocalDate.now(zone);
		}
		assertEquals(test, expected);
	}

	// -----------------------------------------------------------------------
	// now(Clock)
	// -----------------------------------------------------------------------
	@Test(expected = NullPointerException.class)
	public void test_now_Clock_nullClock() {
		try {
			LocalDate.now((Clock) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test
	public void long_test_now_Clock_allSecsInDay_utc() {
		for (int i = 0; i < (2 * 24 * 60 * 60); i++) {
			Instant instant = Instant.ofEpochSecond(i);
			Clock clock = Clock.fixed(instant, ZoneOffset.UTC);
			LocalDate test = LocalDate.now(clock);
			assertEquals(test.getYear(), 1970);
			assertEquals(test.getMonth(), Month.JANUARY);
			assertEquals(test.getDayOfMonth(), (i < 24 * 60 * 60 ? 1 : 2));
		}
	}

	@Test
	public void long_test_now_Clock_allSecsInDay_offset() {
		for (int i = 0; i < (2 * 24 * 60 * 60); i++) {
			Instant instant = Instant.ofEpochSecond(i);
			Clock clock = Clock.fixed(instant.minusSeconds(OFFSET_ZONE.getTotalSeconds()), OFFSET_ZONE);
			LocalDate test = LocalDate.now(clock);
			assertEquals(test.getYear(), 1970);
			assertEquals(test.getMonth(), Month.JANUARY);
			assertEquals(test.getDayOfMonth(), (i < 24 * 60 * 60) ? 1 : 2);
		}
	}

	@Test
	public void long_test_now_Clock_allSecsInDay_beforeEpoch() {
		for (int i = -1; i >= -(2 * 24 * 60 * 60); i--) {
			Instant instant = Instant.ofEpochSecond(i);
			Clock clock = Clock.fixed(instant, ZoneOffset.UTC);
			LocalDate test = LocalDate.now(clock);
			assertEquals(test.getYear(), 1969);
			assertEquals(test.getMonth(), Month.DECEMBER);
			assertEquals(test.getDayOfMonth(), (i >= -24 * 60 * 60 ? 31 : 30));
		}
	}

	// -----------------------------------------------------------------------
	@Test
	public void test_now_Clock_maxYear() {
		Clock clock = Clock.fixed(MAX_INSTANT, ZoneOffset.UTC);
		LocalDate test = LocalDate.now(clock);
		assertEquals(test, MAX_DATE);
	}

	@Test(expected = DateTimeException.class)
	public void test_now_Clock_tooBig() {
		try {
			Clock clock = Clock.fixed(MAX_INSTANT.plusSeconds(24 * 60 * 60), ZoneOffset.UTC);
			LocalDate.now(clock);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test
	public void test_now_Clock_minYear() {
		Clock clock = Clock.fixed(MIN_INSTANT, ZoneOffset.UTC);
		LocalDate test = LocalDate.now(clock);
		assertEquals(test, MIN_DATE);
	}

	@Test(expected = DateTimeException.class)
	public void test_now_Clock_tooLow() {
		try {
			Clock clock = Clock.fixed(MIN_INSTANT.minusNanos(1), ZoneOffset.UTC);
			LocalDate.now(clock);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// of() factories
	// -----------------------------------------------------------------------
	@Test
	public void test_factory_of_intsMonth() {
		assertEquals(TEST_2007_07_15, LocalDate.of(2007, Month.JULY, 15));
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_of_intsMonth_29febNonLeap() {
		try {
			LocalDate.of(2007, Month.FEBRUARY, 29);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_of_intsMonth_31apr() {
		try {
			LocalDate.of(2007, Month.APRIL, 31);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_of_intsMonth_dayTooLow() {
		try {
			LocalDate.of(2007, Month.JANUARY, 0);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_of_intsMonth_dayTooHigh() {
		try {
			LocalDate.of(2007, Month.JANUARY, 32);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_factory_of_intsMonth_nullMonth() {
		try {
			LocalDate.of(2007, null, 30);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_of_intsMonth_yearTooLow() {
		try {
			LocalDate.of(Integer.MIN_VALUE, Month.JANUARY, 1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	@Test
	public void test_factory_of_ints() {
		check(TEST_2007_07_15, 2007, 7, 15);
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_of_ints_29febNonLeap() {
		try {
			LocalDate.of(2007, 2, 29);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_of_ints_31apr() {
		try {
			LocalDate.of(2007, 4, 31);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_of_ints_dayTooLow() {
		try {
			LocalDate.of(2007, 1, 0);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_of_ints_dayTooHigh() {
		try {
			LocalDate.of(2007, 1, 32);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_of_ints_monthTooLow() {
		try {
			LocalDate.of(2007, 0, 1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_of_ints_monthTooHigh() {
		try {
			LocalDate.of(2007, 13, 1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_of_ints_yearTooLow() {
		try {
			LocalDate.of(Integer.MIN_VALUE, 1, 1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	@Test
	public void test_factory_ofYearDay_ints_nonLeap() {
		LocalDate date = LocalDate.of(2007, 1, 1);
		for (int i = 1; i <= 365; i++) {
			assertEquals(LocalDate.ofYearDay(2007, i), date);
			date = next(date);
		}
	}

	@Test
	public void test_factory_ofYearDay_ints_leap() {
		LocalDate date = LocalDate.of(2008, 1, 1);
		for (int i = 1; i <= 366; i++) {
			assertEquals(LocalDate.ofYearDay(2008, i), date);
			date = next(date);
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_ofYearDay_ints_366nonLeap() {
		try {
			LocalDate.ofYearDay(2007, 366);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_ofYearDay_ints_dayTooLow() {
		try {
			LocalDate.ofYearDay(2007, 0);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_ofYearDay_ints_dayTooHigh() {
		try {
			LocalDate.ofYearDay(2007, 367);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_ofYearDay_ints_yearTooLow() {
		try {
			LocalDate.ofYearDay(Integer.MIN_VALUE, 1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// Since plusDays/minusDays actually depends on MJDays, it cannot be used for
	// testing
	private LocalDate next(LocalDate date) {
		int newDayOfMonth = date.getDayOfMonth() + 1;
		if (newDayOfMonth <= date.getMonth().length(isIsoLeap(date.getYear()))) {
			return date.withDayOfMonth(newDayOfMonth);
		}
		date = date.withDayOfMonth(1);
		if (date.getMonth() == Month.DECEMBER) {
			date = date.withYear(date.getYear() + 1);
		}
		return date.with(date.getMonth().plus(1));
	}

	private LocalDate previous(LocalDate date) {
		int newDayOfMonth = date.getDayOfMonth() - 1;
		if (newDayOfMonth > 0) {
			return date.withDayOfMonth(newDayOfMonth);
		}
		date = date.with(date.getMonth().minus(1));
		if (date.getMonth() == Month.DECEMBER) {
			date = date.withYear(date.getYear() - 1);
		}
		return date.withDayOfMonth(date.getMonth().length(isIsoLeap(date.getYear())));
	}

	// -----------------------------------------------------------------------
	// ofEpochDay()
	// -----------------------------------------------------------------------
	// GWT - too long
//	@Test
//	public void test_factory_ofEpochDay() {
//		long date_0000_01_01 = -678941 - 40587;
//		assertEquals(LocalDate.ofEpochDay(0), LocalDate.of(1970, 1, 1));
//		assertEquals(LocalDate.ofEpochDay(date_0000_01_01), LocalDate.of(0, 1, 1));
//		assertEquals(LocalDate.ofEpochDay(date_0000_01_01 - 1), LocalDate.of(-1, 12, 31));
//		assertEquals(LocalDate.ofEpochDay(MAX_VALID_EPOCHDAYS), LocalDate.of(Year.MAX_VALUE, 12, 31));
//		assertEquals(LocalDate.ofEpochDay(MIN_VALID_EPOCHDAYS), LocalDate.of(Year.MIN_VALUE, 1, 1));
//
//		LocalDate test = LocalDate.of(0, 1, 1);
//		for (long i = date_0000_01_01; i < 700000; i++) {
//			assertEquals(LocalDate.ofEpochDay(i), test);
//			test = next(test);
//		}
//		test = LocalDate.of(0, 1, 1);
//		for (long i = date_0000_01_01; i > -2000000; i--) {
//			assertEquals(LocalDate.ofEpochDay(i), test);
//			test = previous(test);
//		}
//	}

	@Test(expected = DateTimeException.class)
	public void test_factory_ofEpochDay_aboveMax() {
		try {
			LocalDate.ofEpochDay(MAX_VALID_EPOCHDAYS + 1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_ofEpochDay_belowMin() {
		try {
			LocalDate.ofEpochDay(MIN_VALID_EPOCHDAYS - 1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// from()
	// -----------------------------------------------------------------------
	@Test
	public void test_factory_CalendricalObject() {
		assertEquals(LocalDate.from(LocalDate.of(2007, 7, 15)), LocalDate.of(2007, 7, 15));
		assertEquals(LocalDate.from(LocalDateTime.of(2007, 7, 15, 12, 30)), LocalDate.of(2007, 7, 15));
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_CalendricalObject_invalid_noDerive() {
		try {
			LocalDate.from(LocalTime.of(12, 30));
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_factory_CalendricalObject_null() {
		try {
			LocalDate.from((TemporalAccessor) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// parse()
	// -----------------------------------------------------------------------
	@Test(/* dataProvider="sampleToString" */)
	public void test_factory_parse_validText() {
		Object[][] data = provider_sampleToString();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_factory_parse_validText((Integer) objects[0], (Integer) objects[1], (Integer) objects[2],
					(String) objects[3]);
		}
	}

	public void test_factory_parse_validText(int y, int m, int d, String parsable) {
		LocalDate t = LocalDate.parse(parsable);
		assertNotNull(parsable, t);
		assertEquals(parsable, t.getYear(), y);
		assertEquals(parsable, t.getMonth().getValue(), m);
		assertEquals(parsable, t.getDayOfMonth(), d);
	}

//	@DataProvider(name = "sampleBadParse")
	Object[][] provider_sampleBadParse() {
		return new Object[][] { { "2008/07/05" }, { "10000-01-01" }, { "2008-1-1" }, { "2008--01" }, { "ABCD-02-01" },
				{ "2008-AB-01" }, { "2008-02-AB" }, { "-0000-02-01" }, { "2008-02-01Z" }, { "2008-02-01+01:00" },
				{ "2008-02-01+01:00[Europe/Paris]" }, };
	}

	@Test(/* dataProvider="sampleBadParse", */ expected = DateTimeParseException.class)
	public void test_factory_parse_invalidText() {
		Object[][] data = provider_sampleBadParse();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			for (int j = 0; j < objects.length; j++) {
				Object object = objects[j];
				test_factory_parse_invalidText(object.toString());
			}
		}
	}

	public void test_factory_parse_invalidText(String unparsable) {
		try {
			LocalDate.parse(unparsable);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeParseException.class)
	public void test_factory_parse_illegalValue() {
		try {
			LocalDate.parse("2008-06-32");
			fail("Missing exception");
		} catch (DateTimeParseException e) {
			// expected
		}
	}

	@Test(expected = DateTimeParseException.class)
	public void test_factory_parse_invalidValue() {
		try {
			LocalDate.parse("2008-06-31");
			fail("Missing exception");
		} catch (DateTimeParseException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_factory_parse_nullText() {
		try {
			LocalDate.parse((String) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// parse(DateTimeFormatter)
	// -----------------------------------------------------------------------
	@Test
	public void test_factory_parse_formatter() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("u M d");
		LocalDate test = LocalDate.parse("2010 12 3", f);
		assertEquals(test, LocalDate.of(2010, 12, 3));
	}

	@Test(expected = NullPointerException.class)
	public void test_factory_parse_formatter_nullText() {
		try {
			DateTimeFormatter f = DateTimeFormatter.ofPattern("u M d");
			LocalDate.parse((String) null, f);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_factory_parse_formatter_nullFormatter() {
		try {
			LocalDate.parse("ANY", null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// get(TemporalField)
	// -----------------------------------------------------------------------
	@Test
	public void test_get_TemporalField() {
		LocalDate test = LocalDate.of(2008, 6, 30);
		assertEquals(test.get(YEAR), 2008);
		assertEquals(test.get(MONTH_OF_YEAR), 6);
		assertEquals(test.get(DAY_OF_MONTH), 30);
		assertEquals(test.get(DAY_OF_WEEK), 1);
		assertEquals(test.get(DAY_OF_YEAR), 182);
		assertEquals(test.get(YEAR_OF_ERA), 2008);
		assertEquals(test.get(ERA), 1);
	}

	@Test(expected = DateTimeException.class)
	public void test_get_TemporalField_tooBig() {
		try {
			TEST_2007_07_15.get(EPOCH_DAY);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_get_TemporalField_null() {
		try {
			TEST_2007_07_15.get((TemporalField) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_get_TemporalField_invalidField() {
		try {
			TEST_2007_07_15.get(MockFieldNoValue.INSTANCE);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_get_TemporalField_timeField() {
		try {
			TEST_2007_07_15.get(ChronoField.AMPM_OF_DAY);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// getLong(TemporalField)
	// -----------------------------------------------------------------------
	@Test
	public void test_getLong_TemporalField() {
		LocalDate test = LocalDate.of(2008, 6, 30);
		assertEquals(test.getLong(YEAR), 2008);
		assertEquals(test.getLong(MONTH_OF_YEAR), 6);
		assertEquals(test.getLong(DAY_OF_MONTH), 30);
		assertEquals(test.getLong(DAY_OF_WEEK), 1);
		assertEquals(test.getLong(DAY_OF_YEAR), 182);
		assertEquals(test.getLong(YEAR_OF_ERA), 2008);
		assertEquals(test.getLong(ERA), 1);
		assertEquals(test.getLong(PROLEPTIC_MONTH), 2008 * 12 + 6 - 1);
	}

	@Test(expected = NullPointerException.class)
	public void test_getLong_TemporalField_null() {
		try {
			TEST_2007_07_15.getLong((TemporalField) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_getLong_TemporalField_invalidField() {
		try {
			TEST_2007_07_15.getLong(MockFieldNoValue.INSTANCE);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_getLong_TemporalField_timeField() {
		try {
			TEST_2007_07_15.getLong(ChronoField.AMPM_OF_DAY);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// query(TemporalQuery)
	// -----------------------------------------------------------------------
	@Test
	public void test_query() {
		assertEquals(TEST_2007_07_15.query(TemporalQueries.chronology()), IsoChronology.INSTANCE);
		assertEquals(TEST_2007_07_15.query(TemporalQueries.localDate()), TEST_2007_07_15);
		assertEquals(TEST_2007_07_15.query(TemporalQueries.localTime()), null);
		assertEquals(TEST_2007_07_15.query(TemporalQueries.offset()), null);
		assertEquals(TEST_2007_07_15.query(TemporalQueries.precision()), ChronoUnit.DAYS);
		assertEquals(TEST_2007_07_15.query(TemporalQueries.zone()), null);
		assertEquals(TEST_2007_07_15.query(TemporalQueries.zoneId()), null);
	}

	@Test(expected = NullPointerException.class)
	public void test_query_null() {
		try {
			TEST_2007_07_15.query(null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// get*()
	// -----------------------------------------------------------------------
//	@DataProvider(name = "sampleDates")
	Object[][] provider_sampleDates() {
		return new Object[][] { { 2008, 7, 5 }, { 2007, 7, 5 }, { 2006, 7, 5 }, { 2005, 7, 5 }, { 2004, 1, 1 },
				{ -1, 1, 2 }, };
	}

	// -----------------------------------------------------------------------

	@Test(/* dataProvider="sampleDates" */)
	public void test_get() {
		Object[][] data = provider_sampleDates();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_get((Integer) objects[0], (Integer) objects[1], (Integer) objects[2]);
		}
	}

	public void test_get(int y, int m, int d) {
		LocalDate a = LocalDate.of(y, m, d);
		assertEquals(a.getYear(), y);
		assertEquals(a.getMonth(), Month.of(m));
		assertEquals(a.getDayOfMonth(), d);
	}

	@Test(/* dataProvider="sampleDates" */)
	public void test_getDOY() {
		Object[][] data = provider_sampleDates();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_getDOY((Integer) objects[0], (Integer) objects[1], (Integer) objects[2]);
		}
	}

	public void test_getDOY(int y, int m, int d) {
		LocalDate a = LocalDate.of(y, m, d);
		int total = 0;
		for (int i = 1; i < m; i++) {
			total += Month.of(i).length(isIsoLeap((long) y));
		}
		int doy = total + d;
		assertEquals(a.getDayOfYear(), doy);
	}

	@Test
	public void test_getDayOfWeek() {
		DayOfWeek dow = DayOfWeek.MONDAY;
		for (Month month : Month.values()) {
			int length = month.length(false);
			for (int i = 1; i <= length; i++) {
				LocalDate d = LocalDate.of(2007, month, i);
				assertSame(d.getDayOfWeek(), dow);
				dow = dow.plus(1);
			}
		}
	}

	// -----------------------------------------------------------------------
	// isLeapYear()
	// -----------------------------------------------------------------------
	@Test
	public void test_isLeapYear() {
		assertEquals(LocalDate.of(1999, 1, 1).isLeapYear(), false);
		assertEquals(LocalDate.of(2000, 1, 1).isLeapYear(), true);
		assertEquals(LocalDate.of(2001, 1, 1).isLeapYear(), false);
		assertEquals(LocalDate.of(2002, 1, 1).isLeapYear(), false);
		assertEquals(LocalDate.of(2003, 1, 1).isLeapYear(), false);
		assertEquals(LocalDate.of(2004, 1, 1).isLeapYear(), true);
		assertEquals(LocalDate.of(2005, 1, 1).isLeapYear(), false);

		assertEquals(LocalDate.of(1500, 1, 1).isLeapYear(), false);
		assertEquals(LocalDate.of(1600, 1, 1).isLeapYear(), true);
		assertEquals(LocalDate.of(1700, 1, 1).isLeapYear(), false);
		assertEquals(LocalDate.of(1800, 1, 1).isLeapYear(), false);
		assertEquals(LocalDate.of(1900, 1, 1).isLeapYear(), false);
	}

	// -----------------------------------------------------------------------
	// lengthOfMonth()
	// -----------------------------------------------------------------------
	@Test
	public void test_lengthOfMonth_notLeapYear() {
		assertEquals(LocalDate.of(2007, 1, 1).lengthOfMonth(), 31);
		assertEquals(LocalDate.of(2007, 2, 1).lengthOfMonth(), 28);
		assertEquals(LocalDate.of(2007, 3, 1).lengthOfMonth(), 31);
		assertEquals(LocalDate.of(2007, 4, 1).lengthOfMonth(), 30);
		assertEquals(LocalDate.of(2007, 5, 1).lengthOfMonth(), 31);
		assertEquals(LocalDate.of(2007, 6, 1).lengthOfMonth(), 30);
		assertEquals(LocalDate.of(2007, 7, 1).lengthOfMonth(), 31);
		assertEquals(LocalDate.of(2007, 8, 1).lengthOfMonth(), 31);
		assertEquals(LocalDate.of(2007, 9, 1).lengthOfMonth(), 30);
		assertEquals(LocalDate.of(2007, 10, 1).lengthOfMonth(), 31);
		assertEquals(LocalDate.of(2007, 11, 1).lengthOfMonth(), 30);
		assertEquals(LocalDate.of(2007, 12, 1).lengthOfMonth(), 31);
	}

	@Test
	public void test_lengthOfMonth_leapYear() {
		assertEquals(LocalDate.of(2008, 1, 1).lengthOfMonth(), 31);
		assertEquals(LocalDate.of(2008, 2, 1).lengthOfMonth(), 29);
		assertEquals(LocalDate.of(2008, 3, 1).lengthOfMonth(), 31);
		assertEquals(LocalDate.of(2008, 4, 1).lengthOfMonth(), 30);
		assertEquals(LocalDate.of(2008, 5, 1).lengthOfMonth(), 31);
		assertEquals(LocalDate.of(2008, 6, 1).lengthOfMonth(), 30);
		assertEquals(LocalDate.of(2008, 7, 1).lengthOfMonth(), 31);
		assertEquals(LocalDate.of(2008, 8, 1).lengthOfMonth(), 31);
		assertEquals(LocalDate.of(2008, 9, 1).lengthOfMonth(), 30);
		assertEquals(LocalDate.of(2008, 10, 1).lengthOfMonth(), 31);
		assertEquals(LocalDate.of(2008, 11, 1).lengthOfMonth(), 30);
		assertEquals(LocalDate.of(2008, 12, 1).lengthOfMonth(), 31);
	}

	// -----------------------------------------------------------------------
	// lengthOfYear()
	// -----------------------------------------------------------------------
	@Test
	public void test_lengthOfYear() {
		assertEquals(LocalDate.of(2007, 1, 1).lengthOfYear(), 365);
		assertEquals(LocalDate.of(2008, 1, 1).lengthOfYear(), 366);
	}

	// -----------------------------------------------------------------------
	// with()
	// -----------------------------------------------------------------------
	@Test
	public void test_with_adjustment() {
		final LocalDate sample = LocalDate.of(2012, 3, 4);
		TemporalAdjuster adjuster = new TemporalAdjuster() {
			@Override
			public Temporal adjustInto(Temporal dateTime) {
				return sample;
			}
		};
		assertEquals(TEST_2007_07_15.with(adjuster), sample);
	}

	@Test(expected = NullPointerException.class)
	public void test_with_adjustment_null() {
		try {
			TEST_2007_07_15.with((TemporalAdjuster) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// with(DateTimeField,long)
	// -----------------------------------------------------------------------
	@Test
	public void test_with_DateTimeField_long_normal() {
		LocalDate t = TEST_2007_07_15.with(YEAR, 2008);
		assertEquals(t, LocalDate.of(2008, 7, 15));
	}

	@Test(expected = NullPointerException.class)
	public void test_with_DateTimeField_long_null() {
		try {
			TEST_2007_07_15.with((TemporalField) null, 1);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_with_DateTimeField_long_invalidField() {
		try {
			TEST_2007_07_15.with(MockFieldNoValue.INSTANCE, 1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_with_DateTimeField_long_timeField() {
		try {
			TEST_2007_07_15.with(ChronoField.AMPM_OF_DAY, 1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_with_DateTimeField_long_invalidValue() {
		try {
			TEST_2007_07_15.with(ChronoField.DAY_OF_WEEK, -1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// withYear()
	// -----------------------------------------------------------------------
	@Test
	public void test_withYear_int_normal() {
		LocalDate t = TEST_2007_07_15.withYear(2008);
		assertEquals(t, LocalDate.of(2008, 7, 15));
	}

	@Test(expected = DateTimeException.class)
	public void test_withYear_int_invalid() {
		try {
			TEST_2007_07_15.withYear(Year.MIN_VALUE - 1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test
	public void test_withYear_int_adjustDay() {
		LocalDate t = LocalDate.of(2008, 2, 29).withYear(2007);
		LocalDate expected = LocalDate.of(2007, 2, 28);
		assertEquals(t, expected);
	}

	// -----------------------------------------------------------------------
	// withMonth()
	// -----------------------------------------------------------------------
	@Test
	public void test_withMonth_int_normal() {
		LocalDate t = TEST_2007_07_15.withMonth(1);
		assertEquals(t, LocalDate.of(2007, 1, 15));
	}

	@Test(expected = DateTimeException.class)
	public void test_withMonth_int_invalid() {
		try {
			TEST_2007_07_15.withMonth(13);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test
	public void test_withMonth_int_adjustDay() {
		LocalDate t = LocalDate.of(2007, 12, 31).withMonth(11);
		LocalDate expected = LocalDate.of(2007, 11, 30);
		assertEquals(t, expected);
	}

	// -----------------------------------------------------------------------
	// withDayOfMonth()
	// -----------------------------------------------------------------------
	@Test
	public void test_withDayOfMonth_normal() {
		LocalDate t = TEST_2007_07_15.withDayOfMonth(1);
		assertEquals(t, LocalDate.of(2007, 7, 1));
	}

	@Test(expected = DateTimeException.class)
	public void test_withDayOfMonth_illegal() {
		try {
			TEST_2007_07_15.withDayOfMonth(32);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_withDayOfMonth_invalid() {
		try {
			LocalDate.of(2007, 11, 30).withDayOfMonth(31);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// withDayOfYear(int)
	// -----------------------------------------------------------------------
	@Test
	public void test_withDayOfYear_normal() {
		LocalDate t = TEST_2007_07_15.withDayOfYear(33);
		assertEquals(t, LocalDate.of(2007, 2, 2));
	}

	@Test(expected = DateTimeException.class)
	public void test_withDayOfYear_illegal() {
		try {
			TEST_2007_07_15.withDayOfYear(367);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_withDayOfYear_invalid() {
		try {
			TEST_2007_07_15.withDayOfYear(366);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// plus(Period)
	// -----------------------------------------------------------------------
	@Test
	public void test_plus_Period_positiveMonths() {
		MockSimplePeriod period = MockSimplePeriod.of(7, ChronoUnit.MONTHS);
		LocalDate t = TEST_2007_07_15.plus(period);
		assertEquals(t, LocalDate.of(2008, 2, 15));
	}

	@Test
	public void test_plus_Period_negativeDays() {
		MockSimplePeriod period = MockSimplePeriod.of(-25, ChronoUnit.DAYS);
		LocalDate t = TEST_2007_07_15.plus(period);
		assertEquals(t, LocalDate.of(2007, 6, 20));
	}

	@Test(expected = DateTimeException.class)
	public void test_plus_Period_timeNotAllowed() {
		try {
			MockSimplePeriod period = MockSimplePeriod.of(7, ChronoUnit.HOURS);
			TEST_2007_07_15.plus(period);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_plus_Period_null() {
		try {
			TEST_2007_07_15.plus((MockSimplePeriod) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_plus_Period_invalidTooLarge() {
		try {
			MockSimplePeriod period = MockSimplePeriod.of(1, ChronoUnit.YEARS);
			LocalDate.of(Year.MAX_VALUE, 1, 1).plus(period);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_plus_Period_invalidTooSmall() {
		try {
			MockSimplePeriod period = MockSimplePeriod.of(-1, ChronoUnit.YEARS);
			LocalDate.of(Year.MIN_VALUE, 1, 1).plus(period);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// plus(long,PeriodUnit)
	// -----------------------------------------------------------------------
	@Test
	public void test_plus_longPeriodUnit_positiveMonths() {
		LocalDate t = TEST_2007_07_15.plus(7, ChronoUnit.MONTHS);
		assertEquals(t, LocalDate.of(2008, 2, 15));
	}

	@Test
	public void test_plus_longPeriodUnit_negativeDays() {
		LocalDate t = TEST_2007_07_15.plus(-25, ChronoUnit.DAYS);
		assertEquals(t, LocalDate.of(2007, 6, 20));
	}

	@Test(expected = DateTimeException.class)
	public void test_plus_longPeriodUnit_timeNotAllowed() {
		try {
			TEST_2007_07_15.plus(7, ChronoUnit.HOURS);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_plus_longPeriodUnit_null() {
		try {
			TEST_2007_07_15.plus(1, (TemporalUnit) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_plus_longPeriodUnit_invalidTooLarge() {
		try {
			LocalDate.of(Year.MAX_VALUE, 1, 1).plus(1, ChronoUnit.YEARS);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_plus_longPeriodUnit_invalidTooSmall() {
		try {
			LocalDate.of(Year.MIN_VALUE, 1, 1).plus(-1, ChronoUnit.YEARS);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// plusYears()
	// -----------------------------------------------------------------------
	@Test
	public void test_plusYears_long_normal() {
		LocalDate t = TEST_2007_07_15.plusYears(1);
		assertEquals(t, LocalDate.of(2008, 7, 15));
	}

	@Test
	public void test_plusYears_long_negative() {
		LocalDate t = TEST_2007_07_15.plusYears(-1);
		assertEquals(t, LocalDate.of(2006, 7, 15));
	}

	@Test
	public void test_plusYears_long_adjustDay() {
		LocalDate t = LocalDate.of(2008, 2, 29).plusYears(1);
		LocalDate expected = LocalDate.of(2009, 2, 28);
		assertEquals(t, expected);
	}

	@Test
	public void test_plusYears_long_big() {
		long years = 20L + Year.MAX_VALUE;
		LocalDate test = LocalDate.of(-40, 6, 1).plusYears(years);
		assertEquals(test, LocalDate.of((int) (-40L + years), 6, 1));
	}

	@Test(expected = DateTimeException.class)
	public void test_plusYears_long_invalidTooLarge() {
		try {
			LocalDate test = LocalDate.of(Year.MAX_VALUE, 6, 1);
			test.plusYears(1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_plusYears_long_invalidTooLargeMaxAddMax() {
		try {
			LocalDate test = LocalDate.of(Year.MAX_VALUE, 12, 1);
			test.plusYears(Long.MAX_VALUE);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_plusYears_long_invalidTooLargeMaxAddMin() {
		try {
			LocalDate test = LocalDate.of(Year.MAX_VALUE, 12, 1);
			test.plusYears(Long.MIN_VALUE);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_plusYears_long_invalidTooSmall_validInt() {
		try {
			LocalDate.of(Year.MIN_VALUE, 1, 1).plusYears(-1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_plusYears_long_invalidTooSmall_invalidInt() {
		try {
			LocalDate.of(Year.MIN_VALUE, 1, 1).plusYears(-10);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// plusMonths()
	// -----------------------------------------------------------------------
	@Test
	public void test_plusMonths_long_normal() {
		LocalDate t = TEST_2007_07_15.plusMonths(1);
		assertEquals(t, LocalDate.of(2007, 8, 15));
	}

	@Test
	public void test_plusMonths_long_overYears() {
		LocalDate t = TEST_2007_07_15.plusMonths(25);
		assertEquals(t, LocalDate.of(2009, 8, 15));
	}

	@Test
	public void test_plusMonths_long_negative() {
		LocalDate t = TEST_2007_07_15.plusMonths(-1);
		assertEquals(t, LocalDate.of(2007, 6, 15));
	}

	@Test
	public void test_plusMonths_long_negativeAcrossYear() {
		LocalDate t = TEST_2007_07_15.plusMonths(-7);
		assertEquals(t, LocalDate.of(2006, 12, 15));
	}

	@Test
	public void test_plusMonths_long_negativeOverYears() {
		LocalDate t = TEST_2007_07_15.plusMonths(-31);
		assertEquals(t, LocalDate.of(2004, 12, 15));
	}

	@Test
	public void test_plusMonths_long_adjustDayFromLeapYear() {
		LocalDate t = LocalDate.of(2008, 2, 29).plusMonths(12);
		LocalDate expected = LocalDate.of(2009, 2, 28);
		assertEquals(t, expected);
	}

	@Test
	public void test_plusMonths_long_adjustDayFromMonthLength() {
		LocalDate t = LocalDate.of(2007, 3, 31).plusMonths(1);
		LocalDate expected = LocalDate.of(2007, 4, 30);
		assertEquals(t, expected);
	}

	@Test
	public void test_plusMonths_long_big() {
		long months = 20L + Integer.MAX_VALUE;
		LocalDate test = LocalDate.of(-40, 6, 1).plusMonths(months);
		assertEquals(test, LocalDate.of((int) (-40L + months / 12), 6 + (int) (months % 12), 1));
	}

	@Test(expected = DateTimeException.class)
	public void test_plusMonths_long_invalidTooLarge() {
		try {
			LocalDate.of(Year.MAX_VALUE, 12, 1).plusMonths(1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_plusMonths_long_invalidTooLargeMaxAddMax() {
		try {
			LocalDate test = LocalDate.of(Year.MAX_VALUE, 12, 1);
			test.plusMonths(Long.MAX_VALUE);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_plusMonths_long_invalidTooLargeMaxAddMin() {
		try {
			LocalDate test = LocalDate.of(Year.MAX_VALUE, 12, 1);
			test.plusMonths(Long.MIN_VALUE);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_plusMonths_long_invalidTooSmall() {
		try {
			LocalDate.of(Year.MIN_VALUE, 1, 1).plusMonths(-1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test
	public void test_plusWeeks_normal() {
		LocalDate t = TEST_2007_07_15.plusWeeks(1);
		assertEquals(t, LocalDate.of(2007, 7, 22));
	}

	@Test
	public void test_plusWeeks_overMonths() {
		LocalDate t = TEST_2007_07_15.plusWeeks(9);
		assertEquals(t, LocalDate.of(2007, 9, 16));
	}

	@Test
	public void test_plusWeeks_overYears() {
		LocalDate t = LocalDate.of(2006, 7, 16).plusWeeks(52);
		assertEquals(t, TEST_2007_07_15);
	}

	@Test
	public void test_plusWeeks_overLeapYears() {
		LocalDate t = TEST_2007_07_15.plusYears(-1).plusWeeks(104);
		assertEquals(t, LocalDate.of(2008, 7, 12));
	}

	@Test
	public void test_plusWeeks_negative() {
		LocalDate t = TEST_2007_07_15.plusWeeks(-1);
		assertEquals(t, LocalDate.of(2007, 7, 8));
	}

	@Test
	public void test_plusWeeks_negativeAcrossYear() {
		LocalDate t = TEST_2007_07_15.plusWeeks(-28);
		assertEquals(t, LocalDate.of(2006, 12, 31));
	}

	@Test
	public void test_plusWeeks_negativeOverYears() {
		LocalDate t = TEST_2007_07_15.plusWeeks(-104);
		assertEquals(t, LocalDate.of(2005, 7, 17));
	}

	@Test
	public void test_plusWeeks_maximum() {
		LocalDate t = LocalDate.of(Year.MAX_VALUE, 12, 24).plusWeeks(1);
		LocalDate expected = LocalDate.of(Year.MAX_VALUE, 12, 31);
		assertEquals(t, expected);
	}

	@Test
	public void test_plusWeeks_minimum() {
		LocalDate t = LocalDate.of(Year.MIN_VALUE, 1, 8).plusWeeks(-1);
		LocalDate expected = LocalDate.of(Year.MIN_VALUE, 1, 1);
		assertEquals(t, expected);
	}

	@Test(expected = DateTimeException.class)
	public void test_plusWeeks_invalidTooLarge() {
		try {
			LocalDate.of(Year.MAX_VALUE, 12, 25).plusWeeks(1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_plusWeeks_invalidTooSmall() {
		try {
			LocalDate.of(Year.MIN_VALUE, 1, 7).plusWeeks(-1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = ArithmeticException.class)
	public void test_plusWeeks_invalidMaxMinusMax() {
		try {
			LocalDate.of(Year.MAX_VALUE, 12, 25).plusWeeks(Long.MAX_VALUE);
			fail("Missing exception");
		} catch (ArithmeticException e) {
			// expected
		}
	}

	@Test(expected = ArithmeticException.class)
	public void test_plusWeeks_invalidMaxMinusMin() {
		try {
			LocalDate.of(Year.MAX_VALUE, 12, 25).plusWeeks(Long.MIN_VALUE);
			fail("Missing exception");
		} catch (ArithmeticException e) {
			// expected
		}
	}

	@Test
	public void test_plusDays_normal() {
		LocalDate t = TEST_2007_07_15.plusDays(1);
		assertEquals(t, LocalDate.of(2007, 7, 16));
	}

	@Test
	public void test_plusDays_overMonths() {
		LocalDate t = TEST_2007_07_15.plusDays(62);
		assertEquals(t, LocalDate.of(2007, 9, 15));
	}

	@Test
	public void test_plusDays_overYears() {
		LocalDate t = LocalDate.of(2006, 7, 14).plusDays(366);
		assertEquals(t, TEST_2007_07_15);
	}

	@Test
	public void test_plusDays_overLeapYears() {
		LocalDate t = TEST_2007_07_15.plusYears(-1).plusDays(365 + 366);
		assertEquals(t, LocalDate.of(2008, 7, 15));
	}

	@Test
	public void test_plusDays_negative() {
		LocalDate t = TEST_2007_07_15.plusDays(-1);
		assertEquals(t, LocalDate.of(2007, 7, 14));
	}

	@Test
	public void test_plusDays_negativeAcrossYear() {
		LocalDate t = TEST_2007_07_15.plusDays(-196);
		assertEquals(t, LocalDate.of(2006, 12, 31));
	}

	@Test
	public void test_plusDays_negativeOverYears() {
		LocalDate t = TEST_2007_07_15.plusDays(-730);
		assertEquals(t, LocalDate.of(2005, 7, 15));
	}

	@Test
	public void test_plusDays_maximum() {
		LocalDate t = LocalDate.of(Year.MAX_VALUE, 12, 30).plusDays(1);
		LocalDate expected = LocalDate.of(Year.MAX_VALUE, 12, 31);
		assertEquals(t, expected);
	}

	@Test
	public void test_plusDays_minimum() {
		LocalDate t = LocalDate.of(Year.MIN_VALUE, 1, 2).plusDays(-1);
		LocalDate expected = LocalDate.of(Year.MIN_VALUE, 1, 1);
		assertEquals(t, expected);
	}

	@Test(expected = DateTimeException.class)
	public void test_plusDays_invalidTooLarge() {
		try {
			LocalDate.of(Year.MAX_VALUE, 12, 31).plusDays(1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_plusDays_invalidTooSmall() {
		try {
			LocalDate.of(Year.MIN_VALUE, 1, 1).plusDays(-1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = ArithmeticException.class)
	public void test_plusDays_overflowTooLarge() {
		try {
			LocalDate.of(Year.MAX_VALUE, 12, 31).plusDays(Long.MAX_VALUE);
			fail("Missing exception");
		} catch (ArithmeticException e) {
			// expected
		}
	}

	@Test(expected = ArithmeticException.class)
	public void test_plusDays_overflowTooSmall() {
		try {
			LocalDate.of(Year.MIN_VALUE, 1, 1).plusDays(Long.MIN_VALUE);
			fail("Missing exception");
		} catch (ArithmeticException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// minus(Period)
	// -----------------------------------------------------------------------
	@Test
	public void test_minus_Period_positiveMonths() {
		MockSimplePeriod period = MockSimplePeriod.of(7, ChronoUnit.MONTHS);
		LocalDate t = TEST_2007_07_15.minus(period);
		assertEquals(t, LocalDate.of(2006, 12, 15));
	}

	@Test
	public void test_minus_Period_negativeDays() {
		MockSimplePeriod period = MockSimplePeriod.of(-25, ChronoUnit.DAYS);
		LocalDate t = TEST_2007_07_15.minus(period);
		assertEquals(t, LocalDate.of(2007, 8, 9));
	}

	@Test(expected = DateTimeException.class)
	public void test_minus_Period_timeNotAllowed() {
		try {
			MockSimplePeriod period = MockSimplePeriod.of(7, ChronoUnit.HOURS);
			TEST_2007_07_15.minus(period);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_minus_Period_null() {
		try {
			TEST_2007_07_15.minus((MockSimplePeriod) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_minus_Period_invalidTooLarge() {
		try {
			MockSimplePeriod period = MockSimplePeriod.of(-1, ChronoUnit.YEARS);
			LocalDate.of(Year.MAX_VALUE, 1, 1).minus(period);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_minus_Period_invalidTooSmall() {
		try {
			MockSimplePeriod period = MockSimplePeriod.of(1, ChronoUnit.YEARS);
			LocalDate.of(Year.MIN_VALUE, 1, 1).minus(period);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// minus(long,PeriodUnit)
	// -----------------------------------------------------------------------
	@Test
	public void test_minus_longPeriodUnit_positiveMonths() {
		LocalDate t = TEST_2007_07_15.minus(7, ChronoUnit.MONTHS);
		assertEquals(t, LocalDate.of(2006, 12, 15));
	}

	@Test
	public void test_minus_longPeriodUnit_negativeDays() {
		LocalDate t = TEST_2007_07_15.minus(-25, ChronoUnit.DAYS);
		assertEquals(t, LocalDate.of(2007, 8, 9));
	}

	@Test(expected = DateTimeException.class)
	public void test_minus_longPeriodUnit_timeNotAllowed() {
		try {
			TEST_2007_07_15.minus(7, ChronoUnit.HOURS);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_minus_longPeriodUnit_null() {
		try {
			TEST_2007_07_15.minus(1, (TemporalUnit) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_minus_longPeriodUnit_invalidTooLarge() {
		try {
			LocalDate.of(Year.MAX_VALUE, 1, 1).minus(-1, ChronoUnit.YEARS);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_minus_longPeriodUnit_invalidTooSmall() {
		try {
			LocalDate.of(Year.MIN_VALUE, 1, 1).minus(1, ChronoUnit.YEARS);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// minusYears()
	// -----------------------------------------------------------------------
	@Test
	public void test_minusYears_long_normal() {
		LocalDate t = TEST_2007_07_15.minusYears(1);
		assertEquals(t, LocalDate.of(2006, 7, 15));
	}

	@Test
	public void test_minusYears_long_negative() {
		LocalDate t = TEST_2007_07_15.minusYears(-1);
		assertEquals(t, LocalDate.of(2008, 7, 15));
	}

	@Test
	public void test_minusYears_long_adjustDay() {
		LocalDate t = LocalDate.of(2008, 2, 29).minusYears(1);
		LocalDate expected = LocalDate.of(2007, 2, 28);
		assertEquals(t, expected);
	}

	@Test
	public void test_minusYears_long_big() {
		long years = 20L + Year.MAX_VALUE;
		LocalDate test = LocalDate.of(40, 6, 1).minusYears(years);
		assertEquals(test, LocalDate.of((int) (40L - years), 6, 1));
	}

	@Test(expected = DateTimeException.class)
	public void test_minusYears_long_invalidTooLarge() {
		try {
			LocalDate test = LocalDate.of(Year.MAX_VALUE, 6, 1);
			test.minusYears(-1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_minusYears_long_invalidTooLargeMaxAddMax() {
		try {
			LocalDate test = LocalDate.of(Year.MAX_VALUE, 12, 1);
			test.minusYears(Long.MAX_VALUE);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_minusYears_long_invalidTooLargeMaxAddMin() {
		try {
			LocalDate test = LocalDate.of(Year.MAX_VALUE, 12, 1);
			test.minusYears(Long.MIN_VALUE);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_minusYears_long_invalidTooSmall() {
		try {
			LocalDate.of(Year.MIN_VALUE, 1, 1).minusYears(1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// minusMonths()
	// -----------------------------------------------------------------------
	@Test
	public void test_minusMonths_long_normal() {
		LocalDate t = TEST_2007_07_15.minusMonths(1);
		assertEquals(t, LocalDate.of(2007, 6, 15));
	}

	@Test
	public void test_minusMonths_long_overYears() {
		LocalDate t = TEST_2007_07_15.minusMonths(25);
		assertEquals(t, LocalDate.of(2005, 6, 15));
	}

	@Test
	public void test_minusMonths_long_negative() {
		LocalDate t = TEST_2007_07_15.minusMonths(-1);
		assertEquals(t, LocalDate.of(2007, 8, 15));
	}

	@Test
	public void test_minusMonths_long_negativeAcrossYear() {
		LocalDate t = TEST_2007_07_15.minusMonths(-7);
		assertEquals(t, LocalDate.of(2008, 2, 15));
	}

	@Test
	public void test_minusMonths_long_negativeOverYears() {
		LocalDate t = TEST_2007_07_15.minusMonths(-31);
		assertEquals(t, LocalDate.of(2010, 2, 15));
	}

	@Test
	public void test_minusMonths_long_adjustDayFromLeapYear() {
		LocalDate t = LocalDate.of(2008, 2, 29).minusMonths(12);
		LocalDate expected = LocalDate.of(2007, 2, 28);
		assertEquals(t, expected);
	}

	@Test
	public void test_minusMonths_long_adjustDayFromMonthLength() {
		LocalDate t = LocalDate.of(2007, 3, 31).minusMonths(1);
		LocalDate expected = LocalDate.of(2007, 2, 28);
		assertEquals(t, expected);
	}

	@Test
	public void test_minusMonths_long_big() {
		long months = 20L + Integer.MAX_VALUE;
		LocalDate test = LocalDate.of(40, 6, 1).minusMonths(months);
		assertEquals(test, LocalDate.of((int) (40L - months / 12), 6 - (int) (months % 12), 1));
	}

	@Test(expected = DateTimeException.class)
	public void test_minusMonths_long_invalidTooLarge() {
		try {
			LocalDate.of(Year.MAX_VALUE, 12, 1).minusMonths(-1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_minusMonths_long_invalidTooLargeMaxAddMax() {
		try {
			LocalDate test = LocalDate.of(Year.MAX_VALUE, 12, 1);
			test.minusMonths(Long.MAX_VALUE);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_minusMonths_long_invalidTooLargeMaxAddMin() {
		try {
			LocalDate test = LocalDate.of(Year.MAX_VALUE, 12, 1);
			test.minusMonths(Long.MIN_VALUE);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_minusMonths_long_invalidTooSmall() {
		try {
			LocalDate.of(Year.MIN_VALUE, 1, 1).minusMonths(1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test
	public void test_minusWeeks_normal() {
		LocalDate t = TEST_2007_07_15.minusWeeks(1);
		assertEquals(t, LocalDate.of(2007, 7, 8));
	}

	@Test
	public void test_minusWeeks_overMonths() {
		LocalDate t = TEST_2007_07_15.minusWeeks(9);
		assertEquals(t, LocalDate.of(2007, 5, 13));
	}

	@Test
	public void test_minusWeeks_overYears() {
		LocalDate t = LocalDate.of(2008, 7, 13).minusWeeks(52);
		assertEquals(t, TEST_2007_07_15);
	}

	@Test
	public void test_minusWeeks_overLeapYears() {
		LocalDate t = TEST_2007_07_15.minusYears(-1).minusWeeks(104);
		assertEquals(t, LocalDate.of(2006, 7, 18));
	}

	@Test
	public void test_minusWeeks_negative() {
		LocalDate t = TEST_2007_07_15.minusWeeks(-1);
		assertEquals(t, LocalDate.of(2007, 7, 22));
	}

	@Test
	public void test_minusWeeks_negativeAcrossYear() {
		LocalDate t = TEST_2007_07_15.minusWeeks(-28);
		assertEquals(t, LocalDate.of(2008, 1, 27));
	}

	@Test
	public void test_minusWeeks_negativeOverYears() {
		LocalDate t = TEST_2007_07_15.minusWeeks(-104);
		assertEquals(t, LocalDate.of(2009, 7, 12));
	}

	@Test
	public void test_minusWeeks_maximum() {
		LocalDate t = LocalDate.of(Year.MAX_VALUE, 12, 24).minusWeeks(-1);
		LocalDate expected = LocalDate.of(Year.MAX_VALUE, 12, 31);
		assertEquals(t, expected);
	}

	@Test
	public void test_minusWeeks_minimum() {
		LocalDate t = LocalDate.of(Year.MIN_VALUE, 1, 8).minusWeeks(1);
		LocalDate expected = LocalDate.of(Year.MIN_VALUE, 1, 1);
		assertEquals(t, expected);
	}

	@Test(expected = DateTimeException.class)
	public void test_minusWeeks_invalidTooLarge() {
		try {
			LocalDate.of(Year.MAX_VALUE, 12, 25).minusWeeks(-1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_minusWeeks_invalidTooSmall() {
		try {
			LocalDate.of(Year.MIN_VALUE, 1, 7).minusWeeks(1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = ArithmeticException.class)
	public void test_minusWeeks_invalidMaxMinusMax() {
		try {
			LocalDate.of(Year.MAX_VALUE, 12, 25).minusWeeks(Long.MAX_VALUE);
			fail("Missing exception");
		} catch (ArithmeticException e) {
			// expected
		}
	}

	@Test(expected = ArithmeticException.class)
	public void test_minusWeeks_invalidMaxMinusMin() {
		try {
			LocalDate.of(Year.MAX_VALUE, 12, 25).minusWeeks(Long.MIN_VALUE);
			fail("Missing exception");
		} catch (ArithmeticException e) {
			// expected
		}
	}

	@Test
	public void test_minusDays_normal() {
		LocalDate t = TEST_2007_07_15.minusDays(1);
		assertEquals(t, LocalDate.of(2007, 7, 14));
	}

	@Test
	public void test_minusDays_overMonths() {
		LocalDate t = TEST_2007_07_15.minusDays(62);
		assertEquals(t, LocalDate.of(2007, 5, 14));
	}

	@Test
	public void test_minusDays_overYears() {
		LocalDate t = LocalDate.of(2008, 7, 16).minusDays(367);
		assertEquals(t, TEST_2007_07_15);
	}

	@Test
	public void test_minusDays_overLeapYears() {
		LocalDate t = TEST_2007_07_15.plusYears(2).minusDays(365 + 366);
		assertEquals(t, TEST_2007_07_15);
	}

	@Test
	public void test_minusDays_negative() {
		LocalDate t = TEST_2007_07_15.minusDays(-1);
		assertEquals(t, LocalDate.of(2007, 7, 16));
	}

	@Test
	public void test_minusDays_negativeAcrossYear() {
		LocalDate t = TEST_2007_07_15.minusDays(-169);
		assertEquals(t, LocalDate.of(2007, 12, 31));
	}

	@Test
	public void test_minusDays_negativeOverYears() {
		LocalDate t = TEST_2007_07_15.minusDays(-731);
		assertEquals(t, LocalDate.of(2009, 7, 15));
	}

	@Test
	public void test_minusDays_maximum() {
		LocalDate t = LocalDate.of(Year.MAX_VALUE, 12, 30).minusDays(-1);
		LocalDate expected = LocalDate.of(Year.MAX_VALUE, 12, 31);
		assertEquals(t, expected);
	}

	@Test
	public void test_minusDays_minimum() {
		LocalDate t = LocalDate.of(Year.MIN_VALUE, 1, 2).minusDays(1);
		LocalDate expected = LocalDate.of(Year.MIN_VALUE, 1, 1);
		assertEquals(t, expected);
	}

	@Test(expected = DateTimeException.class)
	public void test_minusDays_invalidTooLarge() {
		try {
			LocalDate.of(Year.MAX_VALUE, 12, 31).minusDays(-1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_minusDays_invalidTooSmall() {
		try {
			LocalDate.of(Year.MIN_VALUE, 1, 1).minusDays(1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = ArithmeticException.class)
	public void test_minusDays_overflowTooLarge() {
		try {
			LocalDate.of(Year.MAX_VALUE, 12, 31).minusDays(Long.MIN_VALUE);
			fail("Missing exception");
		} catch (ArithmeticException e) {
			// expected
		}
	}

	@Test(expected = ArithmeticException.class)
	public void test_minusDays_overflowTooSmall() {
		try {
			LocalDate.of(Year.MIN_VALUE, 1, 1).minusDays(Long.MAX_VALUE);
			fail("Missing exception");
		} catch (ArithmeticException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// until()
	// -----------------------------------------------------------------------
//	@DataProvider(name = "until")
	Object[][] provider_until() {
		return new Object[][] { { "2012-06-30", "2012-06-30", DAYS, 0 }, { "2012-06-30", "2012-06-30", WEEKS, 0 },
				{ "2012-06-30", "2012-06-30", MONTHS, 0 }, { "2012-06-30", "2012-06-30", YEARS, 0 },
				{ "2012-06-30", "2012-06-30", DECADES, 0 }, { "2012-06-30", "2012-06-30", CENTURIES, 0 },
				{ "2012-06-30", "2012-06-30", MILLENNIA, 0 },

				{ "2012-06-30", "2012-07-01", DAYS, 1 }, { "2012-06-30", "2012-07-01", WEEKS, 0 },
				{ "2012-06-30", "2012-07-01", MONTHS, 0 }, { "2012-06-30", "2012-07-01", YEARS, 0 },
				{ "2012-06-30", "2012-07-01", DECADES, 0 }, { "2012-06-30", "2012-07-01", CENTURIES, 0 },
				{ "2012-06-30", "2012-07-01", MILLENNIA, 0 },

				{ "2012-06-30", "2012-07-07", DAYS, 7 }, { "2012-06-30", "2012-07-07", WEEKS, 1 },
				{ "2012-06-30", "2012-07-07", MONTHS, 0 }, { "2012-06-30", "2012-07-07", YEARS, 0 },
				{ "2012-06-30", "2012-07-07", DECADES, 0 }, { "2012-06-30", "2012-07-07", CENTURIES, 0 },
				{ "2012-06-30", "2012-07-07", MILLENNIA, 0 },

				{ "2012-06-30", "2012-07-29", MONTHS, 0 }, { "2012-06-30", "2012-07-30", MONTHS, 1 },
				{ "2012-06-30", "2012-07-31", MONTHS, 1 }, };
	}

	@Test(/* dataProvider = "until" */)
	public void test_until() {
		Object[][] data = provider_until();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_until((String) objects[0], (String) objects[1], (TemporalUnit) objects[2], (Integer) objects[3]);
		}
	}

	public void test_until(String startStr, String endStr, TemporalUnit unit, long expected) {
		LocalDate start = LocalDate.parse(startStr);
		LocalDate end = LocalDate.parse(endStr);
		assertEquals(start.until(end, unit), expected);
		assertEquals(end.until(start, unit), -expected);
	}

	// -----------------------------------------------------------------------
	// atTime()
	// -----------------------------------------------------------------------
	@Test
	public void test_atTime_LocalTime() {
		LocalDate t = LocalDate.of(2008, 6, 30);
		assertEquals(t.atTime(LocalTime.of(11, 30)), LocalDateTime.of(2008, 6, 30, 11, 30));
	}

	@Test(expected = NullPointerException.class)
	public void test_atTime_LocalTime_null() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime((LocalTime) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -------------------------------------------------------------------------
	@Test
	public void test_atTime_int_int() {
		LocalDate t = LocalDate.of(2008, 6, 30);
		assertEquals(t.atTime(11, 30), LocalDateTime.of(2008, 6, 30, 11, 30));
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_hourTooSmall() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(-1, 30);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_hourTooBig() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(24, 30);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_minuteTooSmall() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(11, -1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_minuteTooBig() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(11, 60);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test
	public void test_atTime_int_int_int() {
		LocalDate t = LocalDate.of(2008, 6, 30);
		assertEquals(t.atTime(11, 30, 40), LocalDateTime.of(2008, 6, 30, 11, 30, 40));
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_int_hourTooSmall() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(-1, 30, 40);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_int_hourTooBig() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(24, 30, 40);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_int_minuteTooSmall() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(11, -1, 40);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_int_minuteTooBig() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(11, 60, 40);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_int_secondTooSmall() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(11, 30, -1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_int_secondTooBig() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(11, 30, 60);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test
	public void test_atTime_int_int_int_int() {
		LocalDate t = LocalDate.of(2008, 6, 30);
		assertEquals(t.atTime(11, 30, 40, 50), LocalDateTime.of(2008, 6, 30, 11, 30, 40, 50));
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_int_int_hourTooSmall() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(-1, 30, 40, 50);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_int_int_hourTooBig() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(24, 30, 40, 50);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_int_int_minuteTooSmall() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(11, -1, 40, 50);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_int_int_minuteTooBig() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(11, 60, 40, 50);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_int_int_secondTooSmall() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(11, 30, -1, 50);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_int_int_secondTooBig() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(11, 30, 60, 50);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_int_int_nanoTooSmall() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(11, 30, 40, -1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_atTime_int_int_int_int_nanoTooBig() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atTime(11, 30, 40, 1000000000);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// atStartOfDay()
	// -----------------------------------------------------------------------
	@Test
	public void test_atStartOfDay() {
		LocalDate t = LocalDate.of(2008, 6, 30);
		assertEquals(t.atStartOfDay(ZONE_PARIS), ZonedDateTime.of(LocalDateTime.of(2008, 6, 30, 0, 0), ZONE_PARIS));
	}

	@Test
	public void test_atStartOfDay_dstGap() {
		LocalDate t = LocalDate.of(2007, 4, 1);
		assertEquals(t.atStartOfDay(ZONE_GAZA), ZonedDateTime.of(LocalDateTime.of(2007, 4, 1, 1, 0), ZONE_GAZA));
	}

	@Test(expected = NullPointerException.class)
	public void test_atStartOfDay_nullTimeZone() {
		try {
			LocalDate t = LocalDate.of(2008, 6, 30);
			t.atStartOfDay((ZoneId) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// toEpochDay()
	// -----------------------------------------------------------------------
	@Test
	public void long_test_toEpochDay() {
		long date_0000_01_01 = -678941 - 40587;

		LocalDate test = LocalDate.of(0, 1, 1);
		for (long i = date_0000_01_01; i < 700000; i++) {
			assertEquals(test.toEpochDay(), i);
			test = next(test);
		}
		test = LocalDate.of(0, 1, 1);
		for (long i = date_0000_01_01; i > -2000000; i--) {
			assertEquals(test.toEpochDay(), i);
			test = previous(test);
		}

		assertEquals(LocalDate.of(1858, 11, 17).toEpochDay(), -40587);
		assertEquals(LocalDate.of(1, 1, 1).toEpochDay(), -678575 - 40587);
		assertEquals(LocalDate.of(1995, 9, 27).toEpochDay(), 49987 - 40587);
		assertEquals(LocalDate.of(1970, 1, 1).toEpochDay(), 0);
		assertEquals(LocalDate.of(-1, 12, 31).toEpochDay(), -678942 - 40587);
	}

	// -----------------------------------------------------------------------
	// compareTo()
	// -----------------------------------------------------------------------
	@Test
	public void test_comparisons() {
		doTest_comparisons_LocalDate(LocalDate.of(Year.MIN_VALUE, 1, 1), LocalDate.of(Year.MIN_VALUE, 12, 31),
				LocalDate.of(-1, 1, 1), LocalDate.of(-1, 12, 31), LocalDate.of(0, 1, 1), LocalDate.of(0, 12, 31),
				LocalDate.of(1, 1, 1), LocalDate.of(1, 12, 31), LocalDate.of(2006, 1, 1), LocalDate.of(2006, 12, 31),
				LocalDate.of(2007, 1, 1), LocalDate.of(2007, 12, 31), LocalDate.of(2008, 1, 1),
				LocalDate.of(2008, 2, 29), LocalDate.of(2008, 12, 31), LocalDate.of(Year.MAX_VALUE, 1, 1),
				LocalDate.of(Year.MAX_VALUE, 12, 31));
	}

	void doTest_comparisons_LocalDate(LocalDate... localDates) {
		for (int i = 0; i < localDates.length; i++) {
			LocalDate a = localDates[i];
			for (int j = 0; j < localDates.length; j++) {
				LocalDate b = localDates[j];
				if (i < j) {
					assertTrue(a + " <=> " + b, a.compareTo(b) < 0);
					assertEquals(a + " <=> " + b, a.isBefore(b), true);
					assertEquals(a + " <=> " + b, a.isAfter(b), false);
					assertEquals(a + " <=> " + b, a.equals(b), false);
				} else if (i > j) {
					assertTrue(a + " <=> " + b, a.compareTo(b) > 0);
					assertEquals(a + " <=> " + b, a.isBefore(b), false);
					assertEquals(a + " <=> " + b, a.isAfter(b), true);
					assertEquals(a + " <=> " + b, a.equals(b), false);
				} else {
					assertEquals(a + " <=> " + b, a.compareTo(b), 0);
					assertEquals(a + " <=> " + b, a.isBefore(b), false);
					assertEquals(a + " <=> " + b, a.isAfter(b), false);
					assertEquals(a + " <=> " + b, a.equals(b), true);
				}
			}
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_compareTo_ObjectNull() {
		try {
			TEST_2007_07_15.compareTo(null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test
	public void test_isBefore() {
		assertTrue(TEST_2007_07_15.isBefore(LocalDate.of(2007, 07, 16)));
		assertFalse(TEST_2007_07_15.isBefore(LocalDate.of(2007, 07, 14)));
		assertFalse(TEST_2007_07_15.isBefore(TEST_2007_07_15));
	}

	@Test(expected = NullPointerException.class)
	public void test_isBefore_ObjectNull() {
		try {
			TEST_2007_07_15.isBefore(null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_isAfter_ObjectNull() {
		try {
			TEST_2007_07_15.isAfter(null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test
	public void test_isAfter() {
		assertTrue(TEST_2007_07_15.isAfter(LocalDate.of(2007, 07, 14)));
		assertFalse(TEST_2007_07_15.isAfter(LocalDate.of(2007, 07, 16)));
		assertFalse(TEST_2007_07_15.isAfter(TEST_2007_07_15));
	}

	@Test(expected = ClassCastException.class)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void test_compareToNonLocalDate() {
		try {
			Comparable c = TEST_2007_07_15;
			c.compareTo(new Object());
			fail("Missing exception");
		} catch (ClassCastException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// equals()
	// -----------------------------------------------------------------------
	@Test(/* dataProvider="sampleDates" */)
	public void test_equals_true() {
		Object[][] data = provider_sampleDates();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_equals_true((Integer) objects[0], (Integer) objects[1], (Integer) objects[2]);
		}
	}

	public void test_equals_true(int y, int m, int d) {
		LocalDate a = LocalDate.of(y, m, d);
		LocalDate b = LocalDate.of(y, m, d);
		assertEquals(a.equals(b), true);
	}

	@Test(/* dataProvider="sampleDates" */)
	public void test_equals_false_year_differs() {
		Object[][] data = provider_sampleDates();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_equals_false_year_differs((Integer) objects[0], (Integer) objects[1], (Integer) objects[2]);
		}
	}

	public void test_equals_false_year_differs(int y, int m, int d) {
		LocalDate a = LocalDate.of(y, m, d);
		LocalDate b = LocalDate.of(y + 1, m, d);
		assertEquals(a.equals(b), false);
	}

	@Test(/* dataProvider="sampleDates" */)
	public void test_equals_false_month_differs() {
		Object[][] data = provider_sampleDates();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_equals_false_month_differs((Integer) objects[0], (Integer) objects[1], (Integer) objects[2]);
		}
	}

	public void test_equals_false_month_differs(int y, int m, int d) {
		LocalDate a = LocalDate.of(y, m, d);
		LocalDate b = LocalDate.of(y, m + 1, d);
		assertEquals(a.equals(b), false);
	}

	@Test(/* dataProvider="sampleDates" */)
	public void test_equals_false_day_differs() {
		Object[][] data = provider_sampleDates();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_equals_false_day_differs((Integer) objects[0], (Integer) objects[1], (Integer) objects[2]);
		}
	}

	public void test_equals_false_day_differs(int y, int m, int d) {
		LocalDate a = LocalDate.of(y, m, d);
		LocalDate b = LocalDate.of(y, m, d + 1);
		assertEquals(a.equals(b), false);
	}

	@Test
	public void test_equals_itself_true() {
		assertEquals(TEST_2007_07_15.equals(TEST_2007_07_15), true);
	}

	@Test
	public void test_equals_string_false() {
		assertEquals(TEST_2007_07_15.equals("2007-07-15"), false);
	}

	@Test
	public void test_equals_null_false() {
		assertEquals(TEST_2007_07_15.equals(null), false);
	}

	// -----------------------------------------------------------------------
	// hashCode()
	// -----------------------------------------------------------------------
	@Test(/* dataProvider="sampleDates" */)
	public void test_hashCode() {
		Object[][] data = provider_sampleDates();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_hashCode((Integer) objects[0], (Integer) objects[1], (Integer) objects[2]);
		}
	}

	public void test_hashCode(int y, int m, int d) {
		LocalDate a = LocalDate.of(y, m, d);
		assertEquals(a.hashCode(), a.hashCode());
		LocalDate b = LocalDate.of(y, m, d);
		assertEquals(a.hashCode(), b.hashCode());
	}

	// -----------------------------------------------------------------------
	// toString()
	// -----------------------------------------------------------------------
//	@DataProvider(name = "sampleToString")
	Object[][] provider_sampleToString() {
		return new Object[][] { { 2008, 7, 5, "2008-07-05" }, { 2007, 12, 31, "2007-12-31" },
				{ 999, 12, 31, "0999-12-31" }, { -1, 1, 2, "-0001-01-02" }, { 9999, 12, 31, "9999-12-31" },
				{ -9999, 12, 31, "-9999-12-31" }, { 10000, 1, 1, "+10000-01-01" }, { -10000, 1, 1, "-10000-01-01" },
				{ 12345678, 1, 1, "+12345678-01-01" }, { -12345678, 1, 1, "-12345678-01-01" }, };
	}

	@Test(/* dataProvider="sampleToString" */)
	public void test_toString() {
		Object[][] data = provider_sampleToString();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_toString((Integer) objects[0], (Integer) objects[1], (Integer) objects[2], (String) objects[3]);
		}
	}

	public void test_toString(int y, int m, int d, String expected) {
		LocalDate t = LocalDate.of(y, m, d);
		String str = t.toString();
		assertEquals(str, expected);
	}

	// -----------------------------------------------------------------------
	// format(DateTimeFormatter)
	// -----------------------------------------------------------------------
	@Test
	public void test_format_formatter() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("y M d");
		String t = LocalDate.of(2010, 12, 3).format(f);
		assertEquals(t, "2010 12 3");
	}

	@Test(expected = NullPointerException.class)
	public void test_format_formatter_null() {
		try {
			LocalDate.of(2010, 12, 3).format(null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

}
