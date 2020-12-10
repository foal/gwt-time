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
package org.jresearch.threetenbp.gwt.time.client.temporal;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.temporal.ChronoField.DAY_OF_WEEK;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.IsoFields;
import java.time.temporal.ValueRange;

import org.jresearch.threetenbp.gwt.time.client.AbstractTest;
import org.junit.Test;

/**
 * Test.
 */
//@Test
public class TestIsoFields extends AbstractTest {

	public void test_enum() {
		assertTrue(IsoFields.WEEK_OF_WEEK_BASED_YEAR instanceof Enum);
		assertTrue(IsoFields.WEEK_BASED_YEAR instanceof Enum);
		assertTrue(IsoFields.WEEK_BASED_YEARS instanceof Enum);
	}

	// @DataProvider(name="week")
	Object[][] data_week() {
		return new Object[][] { { LocalDate.of(1969, 12, 29), MONDAY, 1, 1970 },
				{ LocalDate.of(2012, 12, 23), SUNDAY, 51, 2012 }, { LocalDate.of(2012, 12, 24), MONDAY, 52, 2012 },
				{ LocalDate.of(2012, 12, 27), THURSDAY, 52, 2012 }, { LocalDate.of(2012, 12, 28), FRIDAY, 52, 2012 },
				{ LocalDate.of(2012, 12, 29), SATURDAY, 52, 2012 }, { LocalDate.of(2012, 12, 30), SUNDAY, 52, 2012 },
				{ LocalDate.of(2012, 12, 31), MONDAY, 1, 2013 }, { LocalDate.of(2013, 1, 1), TUESDAY, 1, 2013 },
				{ LocalDate.of(2013, 1, 2), WEDNESDAY, 1, 2013 }, { LocalDate.of(2013, 1, 6), SUNDAY, 1, 2013 },
				{ LocalDate.of(2013, 1, 7), MONDAY, 2, 2013 }, };
	}

	// -----------------------------------------------------------------------
	// WEEK_OF_WEEK_BASED_YEAR
	// -----------------------------------------------------------------------
	@Test(/* dataProvider="week" */)
	public void test_WOWBY() {
		Object[][] data = data_week();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_WOWBY((LocalDate) objects[0], (DayOfWeek) objects[1], (int) objects[2], (int) objects[3]);
		}
	}

	public void test_WOWBY(LocalDate date, DayOfWeek dow, int week, int wby) {
		assertEquals(date.getDayOfWeek(), dow);
		assertEquals(IsoFields.WEEK_OF_WEEK_BASED_YEAR.getFrom(date), week);
		assertEquals(date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR), week);
	}

	// -----------------------------------------------------------------------
	// WEEK_BASED_YEAR
	// -----------------------------------------------------------------------
	@Test(/* dataProvider="week" */)
	public void test_WBY() {
		Object[][] data = data_week();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_WBY((LocalDate) objects[0], (DayOfWeek) objects[1], (int) objects[2], (int) objects[3]);
		}
	}

	public void test_WBY(LocalDate date, DayOfWeek dow, int week, int wby) {
		assertEquals(date.getDayOfWeek(), dow);
		assertEquals(IsoFields.WEEK_BASED_YEAR.getFrom(date), wby);
		assertEquals(date.get(IsoFields.WEEK_BASED_YEAR), wby);
	}

	// -----------------------------------------------------------------------
	// parse weeks
	// -----------------------------------------------------------------------
	@Test(/* dataProvider="week" */)
	public void test_parse_weeks() {
		Object[][] data = data_week();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_parse_weeks((LocalDate) objects[0], (DayOfWeek) objects[1], (int) objects[2], (int) objects[3]);
		}
	}

	public void test_parse_weeks(LocalDate date, DayOfWeek dow, int week, int wby) {
		DateTimeFormatter f = new DateTimeFormatterBuilder().appendValue(IsoFields.WEEK_BASED_YEAR).appendLiteral('-')
				.appendValue(IsoFields.WEEK_OF_WEEK_BASED_YEAR).appendLiteral('-').appendValue(DAY_OF_WEEK)
				.toFormatter();
		LocalDate parsed = LocalDate.parse(wby + "-" + week + "-" + dow.getValue(), f);
		assertEquals(parsed, date);
	}

	// -----------------------------------------------------------------------
	public void long_test_loop() {
		// loop round at least one 400 year cycle, including before 1970
		LocalDate date = LocalDate.of(1960, 1, 5); // Tuseday of week 1 1960
		int year = 1960;
		int wby = 1960;
		int weekLen = 52;
		int week = 1;
		while (date.getYear() < 2400) {
			DayOfWeek loopDow = date.getDayOfWeek();
			if (date.getYear() != year) {
				year = date.getYear();
			}
			if (loopDow == MONDAY) {
				week++;
				if ((week == 53 && weekLen == 52) || week == 54) {
					week = 1;
					LocalDate firstDayOfWeekBasedYear = date.plusDays(14).withDayOfYear(1);
					DayOfWeek firstDay = firstDayOfWeekBasedYear.getDayOfWeek();
					weekLen = (firstDay == THURSDAY || (firstDay == WEDNESDAY && firstDayOfWeekBasedYear.isLeapYear())
							? 53
							: 52);
					wby++;
				}
			}
			assertEquals("Failed on " + date + " " + date.getDayOfWeek(),
					IsoFields.WEEK_OF_WEEK_BASED_YEAR.rangeRefinedBy(date), ValueRange.of(1, weekLen));
			assertEquals("Failed on " + date + " " + date.getDayOfWeek(),
					IsoFields.WEEK_OF_WEEK_BASED_YEAR.getFrom(date), week);
			assertEquals("Failed on " + date + " " + date.getDayOfWeek(), date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR),
					week);
			assertEquals("Failed on " + date + " " + date.getDayOfWeek(), IsoFields.WEEK_BASED_YEAR.getFrom(date), wby);
			assertEquals("Failed on " + date + " " + date.getDayOfWeek(), date.get(IsoFields.WEEK_BASED_YEAR), wby);
			date = date.plusDays(1);
		}
	}

	// -----------------------------------------------------------------------
	// quarters between
	// -----------------------------------------------------------------------
	// @DataProvider(name="quartersBetween")
	Object[][] data_quartersBetween() {
		return new Object[][] { { LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 1), 0 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 2), 0 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(2000, 2, 1), 0 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(2000, 3, 1), 0 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(2000, 3, 31), 0 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(2000, 4, 1), 1 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(2000, 4, 2), 1 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(2000, 6, 30), 1 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(2000, 7, 1), 2 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(2000, 10, 1), 3 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(2000, 12, 31), 3 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(2001, 1, 1), 4 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(2002, 1, 1), 8 },

				{ LocalDate.of(2000, 1, 1), LocalDate.of(1999, 12, 31), 0 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(1999, 10, 2), 0 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(1999, 10, 1), -1 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(1999, 7, 2), -1 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(1999, 7, 1), -2 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(1999, 4, 2), -2 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(1999, 4, 1), -3 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(1999, 1, 2), -3 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(1999, 1, 1), -4 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(1998, 12, 31), -4 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(1998, 10, 2), -4 },
				{ LocalDate.of(2000, 1, 1), LocalDate.of(1998, 10, 1), -5 }, };
	}

	@Test(/* dataProvider="quartersBetween" */)
	public void test_quarters_between() {
		Object[][] data = data_quartersBetween();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_quarters_between((LocalDate) objects[0], (LocalDate) objects[1], toLong(objects[2]));
		}
	}

	public void test_quarters_between(LocalDate start, LocalDate end, long expected) {
		assertEquals(IsoFields.QUARTER_YEARS.between(start, end), expected);
	}

	// TODO: more tests
}
