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
package org.jresearch.threetenbp.gwt.client.chrono;

import static java.time.temporal.ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH;
import static java.time.temporal.ChronoField.ALIGNED_WEEK_OF_MONTH;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.time.chrono.HijrahDates;
import java.time.chrono.IsoChronology;
import java.time.temporal.TemporalAdjusters;

import org.jresearch.threetenbp.gwt.client.AbstractTest;
import org.junit.Test;

/**
 * Test.
 */
//@Test
public class TestHijrahChronology extends AbstractTest {

	// -----------------------------------------------------------------------
	// Chrono.ofName("Hijrah") Lookup by name
	// -----------------------------------------------------------------------
	@Test
	public void test_chrono_byName() {
		Chronology c = HijrahChronology.INSTANCE;
		Chronology test = Chronology.of("Hijrah");
		assertNotNull("The Hijrah calendar could not be found byName", test);
		assertEquals("ID mismatch", test.getId(), "Hijrah-umalqura");
		assertEquals("Type mismatch", test.getCalendarType(), "islamic-umalqura");
		assertEquals(test, c);
	}

	// -----------------------------------------------------------------------
	// creation, toLocalDate()
	// -----------------------------------------------------------------------
	// @DataProvider(name="samples")
	Object[][] data_samples() {
		return new Object[][] { { HijrahChronology.INSTANCE.date(1, 1, 1), LocalDate.of(622, 7, 19) },
				{ HijrahChronology.INSTANCE.date(1, 1, 2), LocalDate.of(622, 7, 20) },
				{ HijrahChronology.INSTANCE.date(1, 1, 3), LocalDate.of(622, 7, 21) },

				{ HijrahChronology.INSTANCE.date(2, 1, 1), LocalDate.of(623, 7, 8) },
				{ HijrahChronology.INSTANCE.date(3, 1, 1), LocalDate.of(624, 6, 27) },
				{ HijrahChronology.INSTANCE.date(3, 12, 6), LocalDate.of(625, 5, 23) },
				{ HijrahChronology.INSTANCE.date(4, 1, 1), LocalDate.of(625, 6, 16) },
				{ HijrahChronology.INSTANCE.date(4, 7, 3), LocalDate.of(625, 12, 12) },
				{ HijrahChronology.INSTANCE.date(4, 7, 4), LocalDate.of(625, 12, 13) },
				{ HijrahChronology.INSTANCE.date(5, 1, 1), LocalDate.of(626, 6, 5) },
				{ HijrahChronology.INSTANCE.date(1662, 3, 3), LocalDate.of(2234, 4, 3) },
				{ HijrahChronology.INSTANCE.date(1728, 10, 28), LocalDate.of(2298, 12, 03) },
				{ HijrahChronology.INSTANCE.date(1728, 10, 29), LocalDate.of(2298, 12, 04) }, };
	}

	@Test(/* dataProvider = "samples" */)
	public void test_toLocalDate() {
		Object[][] data = data_samples();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_toLocalDate((ChronoLocalDate) objects[0], (LocalDate) objects[1]);
		}
	}

	public void test_toLocalDate(ChronoLocalDate hijrahDate, LocalDate iso) {
		assertEquals(LocalDate.from(hijrahDate), iso);
	}

	@Test(/* dataProvider = "samples" */)
	public void test_fromCalendrical() {
		Object[][] data = data_samples();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_fromCalendrical((ChronoLocalDate) objects[0], (LocalDate) objects[1]);
		}
	}

	public void test_fromCalendrical(ChronoLocalDate hijrahDate, LocalDate iso) {
		assertEquals(HijrahChronology.INSTANCE.date(iso), hijrahDate);
	}

	// @DataProvider(name="badDates")
	Object[][] data_badDates() {
		return new Object[][] { { 1728, 0, 0 },

				{ 1728, -1, 1 }, { 1728, 0, 1 }, { 1728, 14, 1 }, { 1728, 15, 1 },

				{ 1728, 1, -1 }, { 1728, 1, 0 }, { 1728, 1, 32 },

				{ 1728, 12, -1 }, { 1728, 12, 0 }, { 1728, 12, 32 }, };
	}

	@Test(/* dataProvider = "badDates", */ expected = DateTimeException.class)
	public void test_badDates() {
		Object[][] data = data_badDates();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_badDates((int) objects[0], (int) objects[1], (int) objects[2]);
		}
	}

	public void test_badDates(int year, int month, int dom) {
		try {
			HijrahChronology.INSTANCE.date(year, month, dom);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// getLong(field)
	// -----------------------------------------------------------------------
	@Test
	public void test_alignedDayOfWeekInMonth() {
		for (int dom = 1; dom <= 29; dom++) {
			HijrahDate date = HijrahChronology.INSTANCE.date(1728, 10, dom);
			assertEquals(date.getLong(ALIGNED_WEEK_OF_MONTH), ((dom - 1) / 7) + 1);
			assertEquals(date.getLong(ALIGNED_DAY_OF_WEEK_IN_MONTH), ((dom - 1) % 7) + 1);
			date = HijrahDates.plusDays(date, 1);
		}
	}

	// -----------------------------------------------------------------------
	// with(WithAdjuster)
	// -----------------------------------------------------------------------
	@Test
	public void test_adjust1() {
		ChronoLocalDate base = HijrahChronology.INSTANCE.date(1728, 10, 28);
		ChronoLocalDate test = base.with(TemporalAdjusters.lastDayOfMonth());
		assertEquals(test, HijrahChronology.INSTANCE.date(1728, 10, 29));
	}

	@Test
	public void test_adjust2() {
		ChronoLocalDate base = HijrahChronology.INSTANCE.date(1728, 12, 2);
		ChronoLocalDate test = base.with(TemporalAdjusters.lastDayOfMonth());
		assertEquals(test, HijrahChronology.INSTANCE.date(1728, 12, 30));
	}

	// -----------------------------------------------------------------------
	// HijrahDate.with(Local*)
	// -----------------------------------------------------------------------
	@Test
	public void test_adjust_toLocalDate() {
		ChronoLocalDate hijrahDate = HijrahChronology.INSTANCE.date(1726, 1, 4);
		ChronoLocalDate test = hijrahDate.with(LocalDate.of(2012, 7, 6));
		assertEquals(test, HijrahChronology.INSTANCE.date(1433, 8, 16));
	}

	@Test(expected = DateTimeException.class)
	public void test_adjust_toMonth() {
		try {
			ChronoLocalDate hijrahDate = HijrahChronology.INSTANCE.date(1726, 1, 4);
			hijrahDate.with(Month.APRIL);
			fail("Missing exception");
		} catch (DateTimeException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	// LocalDate.with(HijrahDate)
	// -----------------------------------------------------------------------
	@Test
	public void test_LocalDate_adjustToHijrahDate() {
		ChronoLocalDate hijrahDate = HijrahChronology.INSTANCE.date(1728, 10, 29);
		LocalDate test = LocalDate.MIN.with(hijrahDate);
		assertEquals(test, LocalDate.of(2298, 12, 4));
	}

	@Test
	public void test_LocalDateTime_adjustToHijrahDate() {
		ChronoLocalDate hijrahDate = HijrahChronology.INSTANCE.date(1728, 10, 29);
		LocalDateTime test = LocalDateTime.MIN.with(hijrahDate);
		assertEquals(test, LocalDateTime.of(2298, 12, 4, 0, 0));
	}

	// -----------------------------------------------------------------------
	// toString()
	// -----------------------------------------------------------------------
	// @DataProvider(name="toString")
	Object[][] data_toString() {
		return new Object[][] { { HijrahChronology.INSTANCE.date(1, 1, 1), "Hijrah-umalqura AH 1-01-01" },
				{ HijrahChronology.INSTANCE.date(1728, 10, 28), "Hijrah-umalqura AH 1728-10-28" },
				{ HijrahChronology.INSTANCE.date(1728, 10, 29), "Hijrah-umalqura AH 1728-10-29" },
				{ HijrahChronology.INSTANCE.date(1727, 12, 5), "Hijrah-umalqura AH 1727-12-05" },
				{ HijrahChronology.INSTANCE.date(1727, 12, 6), "Hijrah-umalqura AH 1727-12-06" }, };
	}

	@Test(/* dataProvider = "toString" */)
	public void test_toString() {
		Object[][] data = data_toString();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_toString((ChronoLocalDate) objects[0], (String) objects[1]);
		}
	}

	public void test_toString(ChronoLocalDate hijrahDate, String expected) {
		assertEquals(hijrahDate.toString(), expected);
	}

	// -----------------------------------------------------------------------
	// equals()
	// -----------------------------------------------------------------------
	@Test
	public void test_equals_true() {
		assertTrue(HijrahChronology.INSTANCE.equals(HijrahChronology.INSTANCE));
	}

	@Test
	public void test_equals_false() {
		assertFalse(HijrahChronology.INSTANCE.equals(IsoChronology.INSTANCE));
	}

}
