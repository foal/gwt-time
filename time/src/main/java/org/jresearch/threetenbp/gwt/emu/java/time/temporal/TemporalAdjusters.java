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

import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.*;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit.*;

import java.util.Objects;

import org.jresearch.threetenbp.gwt.emu.java.time.DayOfWeek;

/**
 * Common implementations of {@code TemporalAdjuster}.
 * <p>
 * This class provides common implementations of {@link TemporalAdjuster}. They
 * are especially useful to document the intent of business logic and often link
 * well to requirements. For example, these two pieces of code do the same
 * thing, but the second one is clearer (assuming that there is a static import
 * of this class):
 *
 * <pre>
 * // direct manipulation
 * date.withDayOfMonth(1).plusMonths(1).minusDays(1);
 * // use of an adjuster from this class
 * date.with(lastDayOfMonth());
 * </pre>
 *
 * There are two equivalent ways of using a {@code TemporalAdjuster}. The first
 * is to invoke the method on the interface directly. The second is to use
 * {@link Temporal#with(TemporalAdjuster)}:
 *
 * <pre>
 * // these two lines are equivalent, but the second approach is recommended
 * dateTime = adjuster.adjustInto(dateTime);
 * dateTime = dateTime.with(adjuster);
 * </pre>
 *
 * It is recommended to use the second approach, {@code with(TemporalAdjuster)},
 * as it is a lot clearer to read in code.
 *
 * <h3>Specification for implementors</h3> This is a thread-safe utility class.
 * All returned adjusters are immutable and thread-safe.
 * <p>
 * The JDK 8 ofDateAdjuster(UnaryOperator) method is not backported.
 */
public final class TemporalAdjusters {

	/**
	 * Private constructor since this is a utility class.
	 */
	private TemporalAdjusters() {
	}

	// -----------------------------------------------------------------------
	/**
	 * Returns the "first day of month" adjuster, which returns a new date set to
	 * the first day of the current month.
	 * <p>
	 * The ISO calendar system behaves as follows:<br>
	 * The input 2011-01-15 will return 2011-01-01.<br>
	 * The input 2011-02-15 will return 2011-02-01.
	 * <p>
	 * The behavior is suitable for use with most calendar systems. It is equivalent
	 * to:
	 *
	 * <pre>
	 * temporal.with(DAY_OF_MONTH, 1);
	 * </pre>
	 *
	 * @return the first day-of-month adjuster, not null
	 */
	public static TemporalAdjuster firstDayOfMonth() {
		return (temporal) -> temporal.with(DAY_OF_MONTH, 1);
	}

	/**
	 * Returns the "last day of month" adjuster, which returns a new date set to the
	 * last day of the current month.
	 * <p>
	 * The ISO calendar system behaves as follows:<br>
	 * The input 2011-01-15 will return 2011-01-31.<br>
	 * The input 2011-02-15 will return 2011-02-28.<br>
	 * The input 2012-02-15 will return 2012-02-29 (leap year).<br>
	 * The input 2011-04-15 will return 2011-04-30.
	 * <p>
	 * The behavior is suitable for use with most calendar systems. It is equivalent
	 * to:
	 *
	 * <pre>
	 * long lastDay = temporal.range(DAY_OF_MONTH).getMaximum();
	 * temporal.with(DAY_OF_MONTH, lastDay);
	 * </pre>
	 *
	 * @return the last day-of-month adjuster, not null
	 */
	public static TemporalAdjuster lastDayOfMonth() {
		return (temporal) -> temporal.with(DAY_OF_MONTH, temporal.range(DAY_OF_MONTH).getMaximum());
	}

	/**
	 * Returns the "first day of next month" adjuster, which returns a new date set
	 * to the first day of the next month.
	 * <p>
	 * The ISO calendar system behaves as follows:<br>
	 * The input 2011-01-15 will return 2011-02-01.<br>
	 * The input 2011-02-15 will return 2011-03-01.
	 * <p>
	 * The behavior is suitable for use with most calendar systems. It is equivalent
	 * to:
	 *
	 * <pre>
	 * temporal.with(DAY_OF_MONTH, 1).plus(1, MONTHS);
	 * </pre>
	 *
	 * @return the first day of next month adjuster, not null
	 */
	public static TemporalAdjuster firstDayOfNextMonth() {
		return (temporal) -> temporal.with(DAY_OF_MONTH, 1).plus(1, MONTHS);
	}

	// -----------------------------------------------------------------------
	/**
	 * Returns the "first day of year" adjuster, which returns a new date set to the
	 * first day of the current year.
	 * <p>
	 * The ISO calendar system behaves as follows:<br>
	 * The input 2011-01-15 will return 2011-01-01.<br>
	 * The input 2011-02-15 will return 2011-01-01.<br>
	 * <p>
	 * The behavior is suitable for use with most calendar systems. It is equivalent
	 * to:
	 *
	 * <pre>
	 * temporal.with(DAY_OF_YEAR, 1);
	 * </pre>
	 *
	 * @return the first day-of-year adjuster, not null
	 */
	public static TemporalAdjuster firstDayOfYear() {
		return (temporal) -> temporal.with(DAY_OF_YEAR, 1);
	}

	/**
	 * Returns the "last day of year" adjuster, which returns a new date set to the
	 * last day of the current year.
	 * <p>
	 * The ISO calendar system behaves as follows:<br>
	 * The input 2011-01-15 will return 2011-12-31.<br>
	 * The input 2011-02-15 will return 2011-12-31.<br>
	 * <p>
	 * The behavior is suitable for use with most calendar systems. It is equivalent
	 * to:
	 *
	 * <pre>
	 * long lastDay = temporal.range(DAY_OF_YEAR).getMaximum();
	 * temporal.with(DAY_OF_YEAR, lastDay);
	 * </pre>
	 *
	 * @return the last day-of-year adjuster, not null
	 */
	public static TemporalAdjuster lastDayOfYear() {
		return (temporal) -> temporal.with(DAY_OF_YEAR, temporal.range(DAY_OF_YEAR).getMaximum());
	}

	/**
	 * Returns the "first day of next year" adjuster, which returns a new date set
	 * to the first day of the next year.
	 * <p>
	 * The ISO calendar system behaves as follows:<br>
	 * The input 2011-01-15 will return 2012-01-01.
	 * <p>
	 * The behavior is suitable for use with most calendar systems. It is equivalent
	 * to:
	 *
	 * <pre>
	 * temporal.with(DAY_OF_YEAR, 1).plus(1, YEARS);
	 * </pre>
	 *
	 * @return the first day of next month adjuster, not null
	 */
	public static TemporalAdjuster firstDayOfNextYear() {
		return (temporal) -> temporal.with(DAY_OF_YEAR, 1).plus(1, YEARS);
	}

	// -----------------------------------------------------------------------
	/**
	 * Returns the first in month adjuster, which returns a new date in the same
	 * month with the first matching day-of-week. This is used for expressions like
	 * 'first Tuesday in March'.
	 * <p>
	 * The ISO calendar system behaves as follows:<br>
	 * The input 2011-12-15 for (MONDAY) will return 2011-12-05.<br>
	 * The input 2011-12-15 for (FRIDAY) will return 2011-12-02.<br>
	 * <p>
	 * The behavior is suitable for use with most calendar systems. It uses the
	 * {@code DAY_OF_WEEK} and {@code DAY_OF_MONTH} fields and the {@code DAYS}
	 * unit, and assumes a seven day week.
	 *
	 * @param dayOfWeek
	 *                      the day-of-week, not null
	 * @return the first in month adjuster, not null
	 */
	public static TemporalAdjuster firstInMonth(DayOfWeek dayOfWeek) {
		return TemporalAdjusters.dayOfWeekInMonth(1, dayOfWeek);
	}

	/**
	 * Returns the last in month adjuster, which returns a new date in the same
	 * month with the last matching day-of-week. This is used for expressions like
	 * 'last Tuesday in March'.
	 * <p>
	 * The ISO calendar system behaves as follows:<br>
	 * The input 2011-12-15 for (MONDAY) will return 2011-12-26.<br>
	 * The input 2011-12-15 for (FRIDAY) will return 2011-12-30.<br>
	 * <p>
	 * The behavior is suitable for use with most calendar systems. It uses the
	 * {@code DAY_OF_WEEK} and {@code DAY_OF_MONTH} fields and the {@code DAYS}
	 * unit, and assumes a seven day week.
	 *
	 * @param dayOfWeek
	 *                      the day-of-week, not null
	 * @return the first in month adjuster, not null
	 */
	public static TemporalAdjuster lastInMonth(DayOfWeek dayOfWeek) {
		return TemporalAdjusters.dayOfWeekInMonth(-1, dayOfWeek);
	}

	/**
	 * Returns the day-of-week in month adjuster, which returns a new date in the
	 * same month with the ordinal day-of-week. This is used for expressions like
	 * the 'second Tuesday in March'.
	 * <p>
	 * The ISO calendar system behaves as follows:<br>
	 * The input 2011-12-15 for (1,TUESDAY) will return 2011-12-06.<br>
	 * The input 2011-12-15 for (2,TUESDAY) will return 2011-12-13.<br>
	 * The input 2011-12-15 for (3,TUESDAY) will return 2011-12-20.<br>
	 * The input 2011-12-15 for (4,TUESDAY) will return 2011-12-27.<br>
	 * The input 2011-12-15 for (5,TUESDAY) will return 2012-01-03.<br>
	 * The input 2011-12-15 for (-1,TUESDAY) will return 2011-12-27 (last in
	 * month).<br>
	 * The input 2011-12-15 for (-4,TUESDAY) will return 2011-12-06 (3 weeks before
	 * last in month).<br>
	 * The input 2011-12-15 for (-5,TUESDAY) will return 2011-11-29 (4 weeks before
	 * last in month).<br>
	 * The input 2011-12-15 for (0,TUESDAY) will return 2011-11-29 (last in previous
	 * month).<br>
	 * <p>
	 * For a positive or zero ordinal, the algorithm is equivalent to finding the
	 * first day-of-week that matches within the month and then adding a number of
	 * weeks to it. For a negative ordinal, the algorithm is equivalent to finding
	 * the last day-of-week that matches within the month and then subtracting a
	 * number of weeks to it. The ordinal number of weeks is not validated and is
	 * interpreted leniently according to this algorithm. This definition means that
	 * an ordinal of zero finds the last matching day-of-week in the previous month.
	 * <p>
	 * The behavior is suitable for use with most calendar systems. It uses the
	 * {@code DAY_OF_WEEK} and {@code DAY_OF_MONTH} fields and the {@code DAYS}
	 * unit, and assumes a seven day week.
	 *
	 * @param ordinal
	 *                      the week within the month, unbounded but typically from
	 *                      -5 to 5
	 * @param dayOfWeek
	 *                      the day-of-week, not null
	 * @return the day-of-week in month adjuster, not null
	 */
	public static TemporalAdjuster dayOfWeekInMonth(int ordinal, DayOfWeek dayOfWeek) {
		Objects.requireNonNull(dayOfWeek, "dayOfWeek");
		return new DayOfWeekInMonth(ordinal, dayOfWeek);
	}

	private static final class DayOfWeekInMonth implements TemporalAdjuster {
		private final int ordinal;
		private final int dowValue;

		private DayOfWeekInMonth(int ordinal, DayOfWeek dow) {
			super();
			this.ordinal = ordinal;
			this.dowValue = dow.getValue();
		}

		@Override
		public Temporal adjustInto(Temporal temporal) {
			if (ordinal >= 0) {
				Temporal temp = temporal.with(DAY_OF_MONTH, 1);
				int curDow = temp.get(DAY_OF_WEEK);
				long daysDiff = (dowValue - curDow + 7) % 7;
				daysDiff += (ordinal - 1L) * 7L; // safe from overflow
				return temp.plus(daysDiff, DAYS);
			}
			Temporal temp = temporal.with(DAY_OF_MONTH, temporal.range(DAY_OF_MONTH).getMaximum());
			int curDow = temp.get(DAY_OF_WEEK);
			long daysDiff = (long) dowValue - curDow;
			daysDiff = (daysDiff == 0 ? 0 : (daysDiff > 0 ? daysDiff - 7 : daysDiff));
			daysDiff -= (-ordinal - 1L) * 7L; // safe from overflow
			return temp.plus(daysDiff, DAYS);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * Returns the next day-of-week adjuster, which adjusts the date to the first
	 * occurrence of the specified day-of-week after the date being adjusted.
	 * <p>
	 * The ISO calendar system behaves as follows:<br>
	 * The input 2011-01-15 (a Saturday) for parameter (MONDAY) will return
	 * 2011-01-17 (two days later).<br>
	 * The input 2011-01-15 (a Saturday) for parameter (WEDNESDAY) will return
	 * 2011-01-19 (four days later).<br>
	 * The input 2011-01-15 (a Saturday) for parameter (SATURDAY) will return
	 * 2011-01-22 (seven days later).
	 * <p>
	 * The behavior is suitable for use with most calendar systems. It uses the
	 * {@code DAY_OF_WEEK} field and the {@code DAYS} unit, and assumes a seven day
	 * week.
	 *
	 * @param dayOfWeek
	 *                      the day-of-week to move the date to, not null
	 * @return the next day-of-week adjuster, not null
	 */
	public static TemporalAdjuster next(DayOfWeek dayOfWeek) {
		// GWT specific
		Objects.requireNonNull(dayOfWeek);
		int dowValue = dayOfWeek.getValue();
		return (temporal) -> {
			int calDow = temporal.get(DAY_OF_WEEK);
			int daysDiff = calDow - dowValue;
			return temporal.plus(daysDiff >= 0 ? 7 - daysDiff : -daysDiff, DAYS);
		};
	}

	/**
	 * Returns the next-or-same day-of-week adjuster, which adjusts the date to the
	 * first occurrence of the specified day-of-week after the date being adjusted
	 * unless it is already on that day in which case the same object is returned.
	 * <p>
	 * The ISO calendar system behaves as follows:<br>
	 * The input 2011-01-15 (a Saturday) for parameter (MONDAY) will return
	 * 2011-01-17 (two days later).<br>
	 * The input 2011-01-15 (a Saturday) for parameter (WEDNESDAY) will return
	 * 2011-01-19 (four days later).<br>
	 * The input 2011-01-15 (a Saturday) for parameter (SATURDAY) will return
	 * 2011-01-15 (same as input).
	 * <p>
	 * The behavior is suitable for use with most calendar systems. It uses the
	 * {@code DAY_OF_WEEK} field and the {@code DAYS} unit, and assumes a seven day
	 * week.
	 *
	 * @param dayOfWeek
	 *                      the day-of-week to check for or move the date to, not
	 *                      null
	 * @return the next-or-same day-of-week adjuster, not null
	 */
	public static TemporalAdjuster nextOrSame(DayOfWeek dayOfWeek) {
		// GWT specific
		Objects.requireNonNull(dayOfWeek);
		int dowValue = dayOfWeek.getValue();
		return (temporal) -> {
			int calDow = temporal.get(DAY_OF_WEEK);
			if (calDow == dowValue) {
				return temporal;
			}
			int daysDiff = calDow - dowValue;
			return temporal.plus(daysDiff >= 0 ? 7 - daysDiff : -daysDiff, DAYS);
		};
	}

	/**
	 * Returns the previous day-of-week adjuster, which adjusts the date to the
	 * first occurrence of the specified day-of-week before the date being adjusted.
	 * <p>
	 * The ISO calendar system behaves as follows:<br>
	 * The input 2011-01-15 (a Saturday) for parameter (MONDAY) will return
	 * 2011-01-10 (five days earlier).<br>
	 * The input 2011-01-15 (a Saturday) for parameter (WEDNESDAY) will return
	 * 2011-01-12 (three days earlier).<br>
	 * The input 2011-01-15 (a Saturday) for parameter (SATURDAY) will return
	 * 2011-01-08 (seven days earlier).
	 * <p>
	 * The behavior is suitable for use with most calendar systems. It uses the
	 * {@code DAY_OF_WEEK} field and the {@code DAYS} unit, and assumes a seven day
	 * week.
	 *
	 * @param dayOfWeek
	 *                      the day-of-week to move the date to, not null
	 * @return the previous day-of-week adjuster, not null
	 */
	public static TemporalAdjuster previous(DayOfWeek dayOfWeek) {
		// GWT specific
		Objects.requireNonNull(dayOfWeek);
		int dowValue = dayOfWeek.getValue();
		return (temporal) -> {
			int calDow = temporal.get(DAY_OF_WEEK);
			int daysDiff = dowValue - calDow;
			return temporal.minus(daysDiff >= 0 ? 7 - daysDiff : -daysDiff, DAYS);
		};
	}

	/**
	 * Returns the previous-or-same day-of-week adjuster, which adjusts the date to
	 * the first occurrence of the specified day-of-week before the date being
	 * adjusted unless it is already on that day in which case the same object is
	 * returned.
	 * <p>
	 * The ISO calendar system behaves as follows:<br>
	 * The input 2011-01-15 (a Saturday) for parameter (MONDAY) will return
	 * 2011-01-10 (five days earlier).<br>
	 * The input 2011-01-15 (a Saturday) for parameter (WEDNESDAY) will return
	 * 2011-01-12 (three days earlier).<br>
	 * The input 2011-01-15 (a Saturday) for parameter (SATURDAY) will return
	 * 2011-01-15 (same as input).
	 * <p>
	 * The behavior is suitable for use with most calendar systems. It uses the
	 * {@code DAY_OF_WEEK} field and the {@code DAYS} unit, and assumes a seven day
	 * week.
	 *
	 * @param dayOfWeek
	 *                      the day-of-week to check for or move the date to, not
	 *                      null
	 * @return the previous-or-same day-of-week adjuster, not null
	 */
	public static TemporalAdjuster previousOrSame(DayOfWeek dayOfWeek) {
		// GWT specific
		Objects.requireNonNull(dayOfWeek);
		int dowValue = dayOfWeek.getValue();
		return (temporal) -> {
			int calDow = temporal.get(DAY_OF_WEEK);
			if (calDow == dowValue) {
				return temporal;
			}
			int daysDiff = dowValue - calDow;
			return temporal.minus(daysDiff >= 0 ? 7 - daysDiff : -daysDiff, DAYS);
		};
	}

}
