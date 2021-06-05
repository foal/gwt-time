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
package org.jresearch.threetenbp.gwt.emu.java.time.temporal;

import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.DAY_OF_MONTH;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.MONTH_OF_YEAR;

import java.io.IOException;

import org.jresearch.threetenbp.gwt.emu.java.time.AbstractDateTimeTest;
import org.jresearch.threetenbp.gwt.emu.java.time.Clock;
import org.jresearch.threetenbp.gwt.emu.java.time.DateTimeException;
import org.jresearch.threetenbp.gwt.emu.java.time.Instant;
import org.jresearch.threetenbp.gwt.emu.java.time.LocalDate;
import org.jresearch.threetenbp.gwt.emu.java.time.LocalDateTime;
import org.jresearch.threetenbp.gwt.emu.java.time.LocalTime;
import org.jresearch.threetenbp.gwt.emu.java.time.Month;
import org.jresearch.threetenbp.gwt.emu.java.time.MonthDay;
import org.jresearch.threetenbp.gwt.emu.java.time.YearMonth;
import org.jresearch.threetenbp.gwt.emu.java.time.ZoneId;
import org.jresearch.threetenbp.gwt.emu.java.time.ZoneOffset;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.IsoChronology;
import org.jresearch.threetenbp.gwt.emu.java.time.format.DateTimeFormatter;
import org.jresearch.threetenbp.gwt.emu.java.time.format.DateTimeParseException;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.JulianFields;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalAccessor;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalField;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalQueries;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * Test MonthDay.
 */
//@Test
public class TestMonthDay extends AbstractDateTimeTest {

	private MonthDay TEST_07_15;

//    @BeforeMethod
	public void gwtSetUp() throws Exception {
		super.gwtSetUp();
		TEST_07_15 = MonthDay.of(7, 15);
	}

	// -----------------------------------------------------------------------
	@Override
	protected List<TemporalAccessor> samples() {
		TemporalAccessor[] array = { TEST_07_15, };
		return Arrays.asList(array);
	}

	@Override
	protected List<TemporalField> validFields() {
		TemporalField[] array = { DAY_OF_MONTH, MONTH_OF_YEAR, };
		return Arrays.asList(array);
	}

	@Override
	protected List<TemporalField> invalidFields() {
		List<TemporalField> list = new ArrayList<TemporalField>(Arrays.<TemporalField>asList(ChronoField.values()));
		list.removeAll(validFields());
		list.add(JulianFields.JULIAN_DAY);
		list.add(JulianFields.MODIFIED_JULIAN_DAY);
		list.add(JulianFields.RATA_DIE);
		return list;
	}

	// -----------------------------------------------------------------------
	// GWT - no reflection
//    @Test
//    public void test_immutable() {
//        assertImmutable(YearMonth.class);
//    }
//GWT - no serialization
//    @Test
//    public void test_serialization() throws ClassNotFoundException, IOException {
//        assertSerializable(TEST_07_15);
//    }

	// GWT - no serialization
//    @Test
//    public void test_serialization_format() throws ClassNotFoundException, IOException {
//        assertEqualsSerialisedForm(MonthDay.of(9, 16));
//    }

	// -----------------------------------------------------------------------
	void check(MonthDay test, int m, int d) {
		assertEquals(test.getMonth().getValue(), m);
		assertEquals(test.getDayOfMonth(), d);
	}

	// -----------------------------------------------------------------------
	// now()
	// -----------------------------------------------------------------------
	@Test
	public void test_now() {
		MonthDay expected = MonthDay.now(Clock.systemDefaultZone());
		MonthDay test = MonthDay.now();
		for (int i = 0; i < 100; i++) {
			if (expected.equals(test)) {
				return;
			}
			expected = MonthDay.now(Clock.systemDefaultZone());
			test = MonthDay.now();
		}
		assertEquals(test, expected);
	}

	// -----------------------------------------------------------------------
	// now(ZoneId)
	// -----------------------------------------------------------------------
	@Test(expected = NullPointerException.class)
	public void test_now_ZoneId_nullZoneId() {
		try {
			MonthDay.now((ZoneId) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test
	public void test_now_ZoneId() {
		ZoneId zone = ZoneId.of("UTC+01:02:03");
		MonthDay expected = MonthDay.now(Clock.system(zone));
		MonthDay test = MonthDay.now(zone);
		for (int i = 0; i < 100; i++) {
			if (expected.equals(test)) {
				return;
			}
			expected = MonthDay.now(Clock.system(zone));
			test = MonthDay.now(zone);
		}
		assertEquals(test, expected);
	}

	// -----------------------------------------------------------------------
	// now(Clock)
	// -----------------------------------------------------------------------
	@Test
	public void test_now_Clock() {
		Instant instant = LocalDateTime.of(2010, 12, 31, 0, 0).toInstant(ZoneOffset.UTC);
		Clock clock = Clock.fixed(instant, ZoneOffset.UTC);
		MonthDay test = MonthDay.now(clock);
		assertEquals(test.getMonth(), Month.DECEMBER);
		assertEquals(test.getDayOfMonth(), 31);
	}

	@Test(expected = NullPointerException.class)
	public void test_now_Clock_nullClock() {
		try {
			MonthDay.now((Clock) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	@Test
	public void test_factory_intMonth() {
		assertEquals(TEST_07_15, MonthDay.of(Month.JULY, 15));
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_intMonth_dayTooLow() {
		try {
			MonthDay.of(Month.JANUARY, 0);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_intMonth_dayTooHigh() {
		try {
			MonthDay.of(Month.JANUARY, 32);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_factory_intMonth_nullMonth() {
		try {
			MonthDay.of(null, 15);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	@Test
	public void test_factory_ints() {
		check(TEST_07_15, 7, 15);
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_ints_dayTooLow() {
		try {
			MonthDay.of(1, 0);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_ints_dayTooHigh() {
		try {
			MonthDay.of(1, 32);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_ints_monthTooLow() {
		try {
			MonthDay.of(0, 1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_ints_monthTooHigh() {
		try {
			MonthDay.of(13, 1);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	@Test
	public void test_factory_CalendricalObject() {
		assertEquals(MonthDay.from(LocalDate.of(2007, 7, 15)), TEST_07_15);
	}

	@Test(expected = DateTimeException.class)
	public void test_factory_CalendricalObject_invalid_noDerive() {
		try {
			MonthDay.from(LocalTime.of(12, 30));
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_factory_CalendricalObject_null() {
		try {
			MonthDay.from((TemporalAccessor) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// parse()
	// -----------------------------------------------------------------------
	// @DataProvider(name = "goodParseData")
	Object[][] provider_goodParseData() {
		return new Object[][] { { "--01-01", MonthDay.of(1, 1) }, { "--01-31", MonthDay.of(1, 31) },
				{ "--02-01", MonthDay.of(2, 1) }, { "--02-29", MonthDay.of(2, 29) }, { "--03-01", MonthDay.of(3, 1) },
				{ "--03-31", MonthDay.of(3, 31) }, { "--04-01", MonthDay.of(4, 1) }, { "--04-30", MonthDay.of(4, 30) },
				{ "--05-01", MonthDay.of(5, 1) }, { "--05-31", MonthDay.of(5, 31) }, { "--06-01", MonthDay.of(6, 1) },
				{ "--06-30", MonthDay.of(6, 30) }, { "--07-01", MonthDay.of(7, 1) }, { "--07-31", MonthDay.of(7, 31) },
				{ "--08-01", MonthDay.of(8, 1) }, { "--08-31", MonthDay.of(8, 31) }, { "--09-01", MonthDay.of(9, 1) },
				{ "--09-30", MonthDay.of(9, 30) }, { "--10-01", MonthDay.of(10, 1) },
				{ "--10-31", MonthDay.of(10, 31) }, { "--11-01", MonthDay.of(11, 1) },
				{ "--11-30", MonthDay.of(11, 30) }, { "--12-01", MonthDay.of(12, 1) },
				{ "--12-31", MonthDay.of(12, 31) }, };
	}

	@Test(/* dataProvider = "goodParseData" */)
	public void test_factory_parse_success() {
		Object[][] data = provider_goodParseData();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_factory_parse_success((String) objects[0], (MonthDay) objects[1]);
		}
	}

	public void test_factory_parse_success(String text, MonthDay expected) {
		MonthDay monthDay = MonthDay.parse(text);
		assertEquals(monthDay, expected);
	}

	// -----------------------------------------------------------------------
	// @DataProvider(name = "badParseData")
	Object[][] provider_badParseData() {
		return new Object[][] { { "", 0 }, { "-00", 0 }, { "--FEB-23", 2 }, { "--01-0", 5 }, { "--01-3A", 5 }, };
	}

	@Test(/* dataProvider = "badParseData", */ expected = DateTimeParseException.class)
	public void test_factory_parse_fail() {
		Object[][] data = provider_badParseData();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_factory_parse_fail((String) objects[0], (int) objects[1]);
		}
	}

	public void test_factory_parse_fail(String text, int pos) {
		try {
			try {
				MonthDay.parse(text);
				// GWT specific
				fail("Parse should have failed for " + text + " at position " + pos);
			} catch (DateTimeParseException ex) {
				assertEquals(ex.getParsedString(), text);
				assertEquals(ex.getErrorIndex(), pos);
				throw ex;
			}
			fail("Missing exception");
		} catch (DateTimeParseException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	@Test(expected = DateTimeParseException.class)
	public void test_factory_parse_illegalValue_Day() {
		try {
			MonthDay.parse("--06-32");
			fail("Missing exception");
		} catch (DateTimeParseException e) {
			// expected
		}
	}

	@Test(expected = DateTimeParseException.class)
	public void test_factory_parse_invalidValue_Day() {
		try {
			MonthDay.parse("--06-31");
			fail("Missing exception");
		} catch (DateTimeParseException e) {
			// expected
		}
	}

	@Test(expected = DateTimeParseException.class)
	public void test_factory_parse_illegalValue_Month() {
		try {
			MonthDay.parse("--13-25");
			fail("Missing exception");
		} catch (DateTimeParseException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_factory_parse_nullText() {
		try {
			MonthDay.parse(null);
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
		DateTimeFormatter f = DateTimeFormatter.ofPattern("M d");
		MonthDay test = MonthDay.parse("12 3", f);
		assertEquals(test, MonthDay.of(12, 3));
	}

	@Test(expected = NullPointerException.class)
	public void test_factory_parse_formatter_nullText() {
		try {
			DateTimeFormatter f = DateTimeFormatter.ofPattern("M d");
			MonthDay.parse((String) null, f);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_factory_parse_formatter_nullFormatter() {
		try {
			MonthDay.parse("ANY", null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// get(DateTimeField)
	// -----------------------------------------------------------------------
	@Test
	public void test_get_DateTimeField() {
		assertEquals(TEST_07_15.getLong(ChronoField.DAY_OF_MONTH), 15);
		assertEquals(TEST_07_15.getLong(ChronoField.MONTH_OF_YEAR), 7);
	}

	@Test(expected = NullPointerException.class)
	public void test_get_DateTimeField_null() {
		try {
			TEST_07_15.getLong((TemporalField) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_get_DateTimeField_invalidField() {
		try {
			TEST_07_15.getLong(MockFieldNoValue.INSTANCE);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_get_DateTimeField_timeField() {
		try {
			TEST_07_15.getLong(ChronoField.AMPM_OF_DAY);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// get*()
	// -----------------------------------------------------------------------
	// @DataProvider(name = "sampleDates")
	Object[][] provider_sampleDates() {
		return new Object[][] { { 1, 1 }, { 1, 31 }, { 2, 1 }, { 2, 28 }, { 2, 29 }, { 7, 4 }, { 7, 5 }, };
	}

	@Test(/* dataProvider = "sampleDates" */)
	public void test_get() {
		Object[][] data = provider_sampleDates();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_get((int) objects[0], (int) objects[1]);
		}
	}

	public void test_get(int m, int d) {
		MonthDay a = MonthDay.of(m, d);
		assertEquals(a.getMonth(), Month.of(m));
		assertEquals(a.getDayOfMonth(), d);
	}

	// -----------------------------------------------------------------------
	// with(Month)
	// -----------------------------------------------------------------------
	@Test
	public void test_with_Month() {
		assertEquals(MonthDay.of(6, 30).with(Month.JANUARY), MonthDay.of(1, 30));
	}

	@Test
	public void test_with_Month_adjustToValid() {
		assertEquals(MonthDay.of(7, 31).with(Month.JUNE), MonthDay.of(6, 30));
	}

	@Test
	public void test_with_Month_adjustToValidFeb() {
		assertEquals(MonthDay.of(7, 31).with(Month.FEBRUARY), MonthDay.of(2, 29));
	}

	@Test
	public void test_with_Month_noChangeEqual() {
		MonthDay test = MonthDay.of(6, 30);
		assertEquals(test.with(Month.JUNE), test);
	}

	@Test(expected = NullPointerException.class)
	public void test_with_Month_null() {
		try {
			MonthDay.of(6, 30).with((Month) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// withMonth()
	// -----------------------------------------------------------------------
	@Test
	public void test_withMonth() {
		assertEquals(MonthDay.of(6, 30).withMonth(1), MonthDay.of(1, 30));
	}

	@Test
	public void test_withMonth_adjustToValid() {
		assertEquals(MonthDay.of(7, 31).withMonth(6), MonthDay.of(6, 30));
	}

	@Test
	public void test_withMonth_adjustToValidFeb() {
		assertEquals(MonthDay.of(7, 31).withMonth(2), MonthDay.of(2, 29));
	}

	@Test
	public void test_withMonth_int_noChangeEqual() {
		MonthDay test = MonthDay.of(6, 30);
		assertEquals(test.withMonth(6), test);
	}

	@Test(expected = DateTimeException.class)
	public void test_withMonth_tooLow() {
		try {
			MonthDay.of(6, 30).withMonth(0);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_withMonth_tooHigh() {
		try {
			MonthDay.of(6, 30).withMonth(13);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// withDayOfMonth()
	// -----------------------------------------------------------------------
	@Test
	public void test_withDayOfMonth() {
		assertEquals(MonthDay.of(6, 30).withDayOfMonth(1), MonthDay.of(6, 1));
	}

	@Test(expected = DateTimeException.class)
	public void test_withDayOfMonth_invalid() {
		try {
			MonthDay.of(6, 30).withDayOfMonth(31);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test
	public void test_withDayOfMonth_adjustToValidFeb() {
		assertEquals(MonthDay.of(2, 1).withDayOfMonth(29), MonthDay.of(2, 29));
	}

	@Test
	public void test_withDayOfMonth_noChangeEqual() {
		MonthDay test = MonthDay.of(6, 30);
		assertEquals(test.withDayOfMonth(30), test);
	}

	@Test(expected = DateTimeException.class)
	public void test_withDayOfMonth_tooLow() {
		try {
			MonthDay.of(6, 30).withDayOfMonth(0);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	@Test(expected = DateTimeException.class)
	public void test_withDayOfMonth_tooHigh() {
		try {
			MonthDay.of(6, 30).withDayOfMonth(32);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// adjust()
	// -----------------------------------------------------------------------
	@Test
	public void test_adjustDate() {
		MonthDay test = MonthDay.of(6, 30);
		LocalDate date = LocalDate.of(2007, 1, 1);
		assertEquals(test.adjustInto(date), LocalDate.of(2007, 6, 30));
	}

	@Test
	public void test_adjustDate_resolve() {
		MonthDay test = MonthDay.of(2, 29);
		LocalDate date = LocalDate.of(2007, 6, 30);
		assertEquals(test.adjustInto(date), LocalDate.of(2007, 2, 28));
	}

	@Test
	public void test_adjustDate_equal() {
		MonthDay test = MonthDay.of(6, 30);
		LocalDate date = LocalDate.of(2007, 6, 30);
		assertEquals(test.adjustInto(date), date);
	}

	@Test(expected = NullPointerException.class)
	public void test_adjustDate_null() {
		try {
			TEST_07_15.adjustInto((LocalDate) null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// isValidYear(int)
	// -----------------------------------------------------------------------
	@Test
	public void test_isValidYear_june() {
		MonthDay test = MonthDay.of(6, 30);
		assertEquals(test.isValidYear(2007), true);
	}

	@Test
	public void test_isValidYear_febNonLeap() {
		MonthDay test = MonthDay.of(2, 29);
		assertEquals(test.isValidYear(2007), false);
	}

	@Test
	public void test_isValidYear_febLeap() {
		MonthDay test = MonthDay.of(2, 29);
		assertEquals(test.isValidYear(2008), true);
	}

	// -----------------------------------------------------------------------
	// atYear(int)
	// -----------------------------------------------------------------------
	@Test
	public void test_atYear_int() {
		MonthDay test = MonthDay.of(6, 30);
		assertEquals(test.atYear(2008), LocalDate.of(2008, 6, 30));
	}

	@Test
	public void test_atYear_int_leapYearAdjust() {
		MonthDay test = MonthDay.of(2, 29);
		assertEquals(test.atYear(2005), LocalDate.of(2005, 2, 28));
	}

	@Test(expected = DateTimeException.class)
	public void test_atYear_int_invalidYear() {
		try {
			MonthDay test = MonthDay.of(6, 30);
			test.atYear(Integer.MIN_VALUE);
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
		assertEquals(TEST_07_15.query(TemporalQueries.chronology()), IsoChronology.INSTANCE);
		assertEquals(TEST_07_15.query(TemporalQueries.localDate()), null);
		assertEquals(TEST_07_15.query(TemporalQueries.localTime()), null);
		assertEquals(TEST_07_15.query(TemporalQueries.offset()), null);
		assertEquals(TEST_07_15.query(TemporalQueries.precision()), null);
		assertEquals(TEST_07_15.query(TemporalQueries.zone()), null);
		assertEquals(TEST_07_15.query(TemporalQueries.zoneId()), null);
	}

	@Test(expected = NullPointerException.class)
	public void test_query_null() {
		try {
			TEST_07_15.query(null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// compareTo()
	// -----------------------------------------------------------------------
	@Test
	public void test_comparisons() {
		doTest_comparisons_MonthDay(MonthDay.of(1, 1), MonthDay.of(1, 31), MonthDay.of(2, 1), MonthDay.of(2, 29),
				MonthDay.of(3, 1), MonthDay.of(12, 31));
	}

	void doTest_comparisons_MonthDay(MonthDay... localDates) {
		for (int i = 0; i < localDates.length; i++) {
			MonthDay a = localDates[i];
			for (int j = 0; j < localDates.length; j++) {
				MonthDay b = localDates[j];
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
			TEST_07_15.compareTo(null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_isBefore_ObjectNull() {
		try {
			TEST_07_15.isBefore(null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_isAfter_ObjectNull() {
		try {
			TEST_07_15.isAfter(null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// equals()
	// -----------------------------------------------------------------------
	@Test
	public void test_equals() {
		MonthDay a = MonthDay.of(1, 1);
		MonthDay b = MonthDay.of(1, 1);
		MonthDay c = MonthDay.of(2, 1);
		MonthDay d = MonthDay.of(1, 2);

		assertEquals(a.equals(a), true);
		assertEquals(a.equals(b), true);
		assertEquals(a.equals(c), false);
		assertEquals(a.equals(d), false);

		assertEquals(b.equals(a), true);
		assertEquals(b.equals(b), true);
		assertEquals(b.equals(c), false);
		assertEquals(b.equals(d), false);

		assertEquals(c.equals(a), false);
		assertEquals(c.equals(b), false);
		assertEquals(c.equals(c), true);
		assertEquals(c.equals(d), false);

		assertEquals(d.equals(a), false);
		assertEquals(d.equals(b), false);
		assertEquals(d.equals(c), false);
		assertEquals(d.equals(d), true);
	}

	@Test
	public void test_equals_itself_true() {
		assertEquals(TEST_07_15.equals(TEST_07_15), true);
	}

	@Test
	public void test_equals_string_false() {
		assertEquals(TEST_07_15.equals("2007-07-15"), false);
	}

	@Test
	public void test_equals_null_false() {
		assertEquals(TEST_07_15.equals(null), false);
	}

	// -----------------------------------------------------------------------
	// hashCode()
	// -----------------------------------------------------------------------
	@Test(/* dataProvider = "sampleDates" */)
	public void test_hashCode() {
		Object[][] data = provider_sampleDates();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_hashCode((int) objects[0], (int) objects[1]);
		}
	}

	public void test_hashCode(int m, int d) {
		MonthDay a = MonthDay.of(m, d);
		assertEquals(a.hashCode(), a.hashCode());
		MonthDay b = MonthDay.of(m, d);
		assertEquals(a.hashCode(), b.hashCode());
	}

	@Test
	public void test_hashCode_unique() {
		int leapYear = 2008;
		Set<Integer> uniques = new HashSet<Integer>(366);
		for (int i = 1; i <= 12; i++) {
			for (int j = 1; j <= 31; j++) {
				if (YearMonth.of(leapYear, i).isValidDay(j)) {
					assertTrue(uniques.add(MonthDay.of(i, j).hashCode()));
				}
			}
		}
	}

	// -----------------------------------------------------------------------
	// toString()
	// -----------------------------------------------------------------------
	// @DataProvider(name = "sampleToString")
	Object[][] provider_sampleToString() {
		return new Object[][] { { 7, 5, "--07-05" }, { 12, 31, "--12-31" }, { 1, 2, "--01-02" }, };
	}

	@Test(/* dataProvider = "sampleToString" */)
	public void test_toString() {
		Object[][] data = provider_sampleToString();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_toString((int) objects[0], (int) objects[1], (String) objects[2]);
		}
	}

	public void test_toString(int m, int d, String expected) {
		MonthDay test = MonthDay.of(m, d);
		String str = test.toString();
		assertEquals(str, expected);
	}

	// -----------------------------------------------------------------------
	// format(DateTimeFormatter)
	// -----------------------------------------------------------------------
	@Test
	public void test_format_formatter() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("M d");
		String t = MonthDay.of(12, 3).format(f);
		assertEquals(t, "12 3");
	}

	@Test(expected = NullPointerException.class)
	public void test_format_formatter_null() {
		try {
			MonthDay.of(12, 3).format(null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

}
