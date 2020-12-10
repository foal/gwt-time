/*
 * Copyright (c) 2007-present Stephen Colebourne & Michael Nascimento Santos
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
package org.jresearch.threetenbp.gwt.client;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.Test;

/**
 * Test tick clock.
 */
//@Test
public class TestClock_Tick extends AbstractTest {

	private static ZoneId MOSCOW;
	private static ZoneId PARIS;
//	private static Duration AMOUNT;
	private static ZonedDateTime ZDT;
//	private static Instant INSTANT;

	@Override
	public void gwtSetUp() throws Exception {
		super.gwtSetUp();
		MOSCOW = ZoneId.of("Europe/Moscow");
		PARIS = ZoneId.of("Europe/Paris");
//		AMOUNT = Duration.ofSeconds(2);
		ZDT = LocalDateTime.of(2008, 6, 30, 11, 30, 10, 500).atZone(ZoneOffset.ofHours(2));
//		INSTANT = ZDT.toInstant();
	}

	// -----------------------------------------------------------------------
//GWT
//    public void test_isSerializable() throws IOException, ClassNotFoundException {
//        assertSerializable(Clock.tickSeconds(PARIS));
//        assertSerializable(Clock.tickMinutes(MOSCOW));
//        assertSerializable(Clock.tick(Clock.fixed(INSTANT, PARIS), AMOUNT));
//    }

	// -----------------------------------------------------------------------
	public void test_tick_ClockDuration_250millis() {
		for (int i = 0; i < 1000; i++) {
			Clock test = Clock.tick(Clock.fixed(ZDT.withNano(i * 1000000).toInstant(), PARIS), Duration.ofMillis(250));
			assertEquals(test.instant(), ZDT.withNano((i / 250) * 250000000).toInstant());
			assertEquals(test.getZone(), PARIS);
		}
	}

	public void test_tick_ClockDuration_250micros() {
		for (int i = 0; i < 1000; i++) {
			Clock test = Clock.tick(Clock.fixed(ZDT.withNano(i * 1000).toInstant(), PARIS), Duration.ofNanos(250000));
			assertEquals(test.instant(), ZDT.withNano((i / 250) * 250000).toInstant());
			assertEquals(test.getZone(), PARIS);
		}
	}

	public void test_tick_ClockDuration_20nanos() {
		for (int i = 0; i < 1000; i++) {
			Clock test = Clock.tick(Clock.fixed(ZDT.withNano(i).toInstant(), PARIS), Duration.ofNanos(20));
			assertEquals(test.instant(), ZDT.withNano((i / 20) * 20).toInstant());
			assertEquals(test.getZone(), PARIS);
		}
	}

	public void test_tick_ClockDuration_zeroDuration() {
		Clock underlying = Clock.system(PARIS);
		Clock test = Clock.tick(underlying, Duration.ZERO);
		assertSame(test, underlying); // spec says same
	}

	public void test_tick_ClockDuration_1nsDuration() {
		Clock underlying = Clock.system(PARIS);
		Clock test = Clock.tick(underlying, Duration.ofNanos(1));
		assertSame(test, underlying); // spec says same
	}

	@Test(expected = ArithmeticException.class)
	public void test_tick_ClockDuration_maxDuration() {
		try {
			Clock.tick(Clock.systemUTC(), Duration.ofSeconds(Long.MAX_VALUE));
			fail("Missing exception");
		} catch (ArithmeticException e) {
			// expected
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_tick_ClockDuration_subMilliNotDivisible_123ns() {
		try {
			Clock.tick(Clock.systemUTC(), Duration.ofSeconds(0, 123));
			fail("Missing exception");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_tick_ClockDuration_subMilliNotDivisible_999ns() {
		try {
			Clock.tick(Clock.systemUTC(), Duration.ofSeconds(0, 999));
			fail("Missing exception");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_tick_ClockDuration_subMilliNotDivisible_999999999ns() {
		try {
			Clock.tick(Clock.systemUTC(), Duration.ofSeconds(0, 999999999));
			fail("Missing exception");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_tick_ClockDuration_negative1ns() {
		try {
			Clock.tick(Clock.systemUTC(), Duration.ofSeconds(0, -1));
			fail("Missing exception");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_tick_ClockDuration_negative1s() {
		try {
			Clock.tick(Clock.systemUTC(), Duration.ofSeconds(-1));
			fail("Missing exception");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_tick_ClockDuration_nullClock() {
		try {
			Clock.tick(null, Duration.ZERO);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test(expected = NullPointerException.class)
	public void test_tick_ClockDuration_nullDuration() {
		try {
			Clock.tick(Clock.systemUTC(), null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	public void test_tickSeconds_ZoneId() throws Exception {
		Clock test = Clock.tickSeconds(PARIS);
		assertEquals(test.getZone(), PARIS);
		assertEquals(test.instant().getNano(), 0);
		Support.sleep(100);
		assertEquals(test.instant().getNano(), 0);
	}

	@Test(expected = NullPointerException.class)
	public void test_tickSeconds_ZoneId_nullZoneId() {
		try {
			Clock.tickSeconds(null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	public void test_tickMinutes_ZoneId() {
		Clock test = Clock.tickMinutes(PARIS);
		assertEquals(test.getZone(), PARIS);
		Instant instant = test.instant();
		assertEquals(instant.getEpochSecond() % 60, 0);
		assertEquals(instant.getNano(), 0);
	}

	@Test(expected = NullPointerException.class)
	public void test_tickMinutes_ZoneId_nullZoneId() {
		try {
			Clock.tickMinutes(null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -------------------------------------------------------------------------
	public void test_withZone() {
		Clock test = Clock.tick(Clock.system(PARIS), Duration.ofMillis(500));
		Clock changed = test.withZone(MOSCOW);
		assertEquals(test.getZone(), PARIS);
		assertEquals(changed.getZone(), MOSCOW);
	}

	public void test_withZone_same() {
		Clock test = Clock.tick(Clock.system(PARIS), Duration.ofMillis(500));
		Clock changed = test.withZone(PARIS);
		assertSame(test, changed);
	}

	@Test(expected = NullPointerException.class)
	public void test_withZone_null() {
		try {
			Clock.tick(Clock.system(PARIS), Duration.ofMillis(500)).withZone(null);
			fail("Missing exception");
		} catch (NullPointerException e) {
			// expected
		}
	}

	// -----------------------------------------------------------------------
	public void test__equals() {
		Clock a = Clock.tick(Clock.system(PARIS), Duration.ofMillis(500));
		Clock b = Clock.tick(Clock.system(PARIS), Duration.ofMillis(500));
		assertEquals(a.equals(a), true);
		assertEquals(a.equals(b), true);
		assertEquals(b.equals(a), true);
		assertEquals(b.equals(b), true);

		Clock c = Clock.tick(Clock.system(MOSCOW), Duration.ofMillis(500));
		assertEquals(a.equals(c), false);

		Clock d = Clock.tick(Clock.system(PARIS), Duration.ofMillis(499));
		assertEquals(a.equals(d), false);

		assertEquals(a.equals(null), false);
		assertEquals(a.equals("other type"), false);
		assertEquals(a.equals(Clock.systemUTC()), false);
	}

	public void test_hashCode() {
		Clock a = Clock.tick(Clock.system(PARIS), Duration.ofMillis(500));
		Clock b = Clock.tick(Clock.system(PARIS), Duration.ofMillis(500));
		assertEquals(a.hashCode(), a.hashCode());
		assertEquals(a.hashCode(), b.hashCode());

		Clock c = Clock.tick(Clock.system(MOSCOW), Duration.ofMillis(500));
		assertEquals(a.hashCode() == c.hashCode(), false);

		Clock d = Clock.tick(Clock.system(PARIS), Duration.ofMillis(499));
		assertEquals(a.hashCode() == d.hashCode(), false);
	}

	// -----------------------------------------------------------------------
	public void test_toString() {
		Clock test = Clock.tick(Clock.systemUTC(), Duration.ofMillis(500));
		assertEquals(test.toString(), "TickClock[SystemClock[Z],PT0.5S]");
	}

}
