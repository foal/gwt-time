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
package org.jresearch.threetenbp.gwt.emu.java.time.chrono;

import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR;
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
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit.DAYS;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit.MONTHS;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit.WEEKS;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalAdjusters.nextOrSame;

import java.io.Serializable;
import org.jresearch.threetenbp.gwt.emu.java.time.Clock;
import org.jresearch.threetenbp.gwt.emu.java.time.DateTimeException;
import org.jresearch.threetenbp.gwt.emu.java.time.DayOfWeek;
import org.jresearch.threetenbp.gwt.emu.java.time.Instant;
import org.jresearch.threetenbp.gwt.emu.java.time.LocalDate;
import org.jresearch.threetenbp.gwt.emu.java.time.ZoneId;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.ChronoLocalDateTime;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.ChronoZonedDateTime;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.Chronology;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.Era;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.IsoChronology;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.ThaiBuddhistChronology;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.ThaiBuddhistDate;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.ThaiBuddhistEra;
import org.jresearch.threetenbp.gwt.emu.java.time.format.ResolverStyle;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalAccessor;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalField;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * The Thai Buddhist calendar system.
 * <p>
 * This chronology defines the rules of the Thai Buddhist calendar system.
 * This calendar system is primarily used in Thailand.
 * Dates are aligned such that {@code 2484-01-01 (Buddhist)} is {@code 1941-01-01 (ISO)}.
 * <p>
 * The fields are defined as follows:
 * <p><ul>
 * <li>era - There are two eras, the current 'Buddhist' (BE) and the previous era (BEFORE_BE).
 * <li>year-of-era - The year-of-era for the current era increases uniformly from the epoch at year one.
 *  For the previous era the year increases from one as time goes backwards.
 *  The value for the current era is equal to the ISO proleptic-year plus 543.
 * <li>proleptic-year - The proleptic year is the same as the year-of-era for the
 *  current era. For the previous era, years have zero, then negative values.
 *  The value is equal to the ISO proleptic-year plus 543.
 * <li>month-of-year - The ThaiBuddhist month-of-year exactly matches ISO.
 * <li>day-of-month - The ThaiBuddhist day-of-month exactly matches ISO.
 * <li>day-of-year - The ThaiBuddhist day-of-year exactly matches ISO.
 * <li>leap-year - The ThaiBuddhist leap-year pattern exactly matches ISO, such that the two calendars
 *  are never out of step.
 * </ul><p>
 *
 * <h3>Specification for implementors</h3>
 * This class is immutable and thread-safe.
 */
public final class ThaiBuddhistChronology extends Chronology implements Serializable {

    /**
     * Singleton instance of the Buddhist chronology.
     */
    public static final ThaiBuddhistChronology INSTANCE = new ThaiBuddhistChronology();

    /**
     * Serialization version.
     */
    private static final long serialVersionUID = 2775954514031616474L;
    /**
     * Containing the offset to add to the ISO year.
     */
    static final int YEARS_DIFFERENCE = 543;

    /**
     * Restricted constructor.
     */
    private ThaiBuddhistChronology() {
    }

    /**
     * Resolve singleton.
     *
     * @return the singleton instance, not null
     */
    private Object readResolve() {
        return INSTANCE;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the ID of the chronology - 'ThaiBuddhist'.
     * <p>
     * The ID uniquely identifies the {@code Chronology}.
     * It can be used to lookup the {@code Chronology} using {@link #of(String)}.
     *
     * @return the chronology ID - 'ThaiBuddhist'
     * @see #getCalendarType()
     */
    @Override
    public String getId() {
        return "ThaiBuddhist";
    }

    /**
     * Gets the calendar type of the underlying calendar system - 'buddhist'.
     * <p>
     * The calendar type is an identifier defined by the
     * <em>Unicode Locale Data Markup Language (LDML)</em> specification.
     * It can be used to lookup the {@code Chronology} using {@link #of(String)}.
     * It can also be used as part of a locale, accessible via
     * {@link Locale#getUnicodeLocaleType(String)} with the key 'ca'.
     *
     * @return the calendar system type - 'buddhist'
     * @see #getId()
     */
    @Override
    public String getCalendarType() {
        return "buddhist";
    }

    //-----------------------------------------------------------------------
    @Override  // override with covariant return type
    public ThaiBuddhistDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
        return (ThaiBuddhistDate) super.date(era, yearOfEra, month, dayOfMonth);
    }

    @Override  // override with covariant return type
    public ThaiBuddhistDate date(int prolepticYear, int month, int dayOfMonth) {
        return new ThaiBuddhistDate(LocalDate.of(prolepticYear - YEARS_DIFFERENCE, month, dayOfMonth));
    }

    @Override  // override with covariant return type
    public ThaiBuddhistDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
        return (ThaiBuddhistDate) super.dateYearDay(era, yearOfEra, dayOfYear);
    }

    @Override  // override with covariant return type
    public ThaiBuddhistDate dateYearDay(int prolepticYear, int dayOfYear) {
        return new ThaiBuddhistDate(LocalDate.ofYearDay(prolepticYear - YEARS_DIFFERENCE, dayOfYear));
    }

    @Override
    public ThaiBuddhistDate dateEpochDay(long epochDay) {
        return new ThaiBuddhistDate(LocalDate.ofEpochDay(epochDay));
    }

    //-----------------------------------------------------------------------
    @Override  // override with covariant return type
    public ThaiBuddhistDate date(TemporalAccessor temporal) {
        if (temporal instanceof ThaiBuddhistDate) {
            return (ThaiBuddhistDate) temporal;
        }
        return new ThaiBuddhistDate(LocalDate.from(temporal));
    }

    @SuppressWarnings("unchecked")
    @Override  // override with covariant return type
    public ChronoLocalDateTime<ThaiBuddhistDate> localDateTime(TemporalAccessor temporal) {
        return (ChronoLocalDateTime<ThaiBuddhistDate>) super.localDateTime(temporal);
    }

    @SuppressWarnings("unchecked")
    @Override  // override with covariant return type
    public ChronoZonedDateTime<ThaiBuddhistDate> zonedDateTime(TemporalAccessor temporal) {
        return (ChronoZonedDateTime<ThaiBuddhistDate>) super.zonedDateTime(temporal);
    }

    @SuppressWarnings("unchecked")
    @Override  // override with covariant return type
    public ChronoZonedDateTime<ThaiBuddhistDate> zonedDateTime(Instant instant, ZoneId zone) {
        return (ChronoZonedDateTime<ThaiBuddhistDate>) super.zonedDateTime(instant, zone);
    }

    //-----------------------------------------------------------------------
    @Override  // override with covariant return type
    public ThaiBuddhistDate dateNow() {
        return (ThaiBuddhistDate) super.dateNow();
    }

    @Override  // override with covariant return type
    public ThaiBuddhistDate dateNow(ZoneId zone) {
        return (ThaiBuddhistDate) super.dateNow(zone);
    }

    @Override  // override with covariant return type
    public ThaiBuddhistDate dateNow(Clock clock) {
        Objects.requireNonNull(clock, "clock");
        return (ThaiBuddhistDate) super.dateNow(clock);
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the specified year is a leap year.
     * <p>
     * Thai Buddhist leap years occur exactly in line with ISO leap years.
     * This method does not validate the year passed in, and only has a
     * well-defined result for years in the supported range.
     *
     * @param prolepticYear  the proleptic-year to check, not validated for range
     * @return true if the year is a leap year
     */
    @Override
    public boolean isLeapYear(long prolepticYear) {
        return IsoChronology.INSTANCE.isLeapYear(prolepticYear - YEARS_DIFFERENCE);
    }

    @Override
    public int prolepticYear(Era era, int yearOfEra) {
        if (era instanceof ThaiBuddhistEra == false) {
            throw new ClassCastException("Era must be BuddhistEra");
        }
        return (era == ThaiBuddhistEra.BE ? yearOfEra : 1 - yearOfEra);
    }

    @Override
    public ThaiBuddhistEra eraOf(int eraValue) {
        return ThaiBuddhistEra.of(eraValue);
    }

    @Override
    public List<Era> eras() {
        return Arrays.<Era>asList(ThaiBuddhistEra.values());
    }

    //-----------------------------------------------------------------------
    @Override
    public ValueRange range(ChronoField field) {
        switch (field) {
            case PROLEPTIC_MONTH: {
                ValueRange range = PROLEPTIC_MONTH.range();
                return ValueRange.of(range.getMinimum() + YEARS_DIFFERENCE * 12L, range.getMaximum() + YEARS_DIFFERENCE * 12L);
            }
            case YEAR_OF_ERA: {
                ValueRange range = YEAR.range();
                return ValueRange.of(1, -(range.getMinimum() + YEARS_DIFFERENCE) + 1, range.getMaximum() + YEARS_DIFFERENCE);
            }
            case YEAR: {
                ValueRange range = YEAR.range();
                return ValueRange.of(range.getMinimum() + YEARS_DIFFERENCE, range.getMaximum() + YEARS_DIFFERENCE);
            }
        }
        return field.range();
    }

    @Override
    public ThaiBuddhistDate resolveDate(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        if (fieldValues.containsKey(EPOCH_DAY)) {
            return dateEpochDay(fieldValues.remove(EPOCH_DAY));
        }

        // normalize fields
        Long prolepticMonth = fieldValues.remove(PROLEPTIC_MONTH);
        if (prolepticMonth != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                PROLEPTIC_MONTH.checkValidValue(prolepticMonth);
            }
            updateResolveMap(fieldValues, MONTH_OF_YEAR, Math.floorMod(prolepticMonth, 12) + 1);
            updateResolveMap(fieldValues, YEAR, Math.floorDiv(prolepticMonth, 12));
        }

        // eras
        Long yoeLong = fieldValues.remove(YEAR_OF_ERA);
        if (yoeLong != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                YEAR_OF_ERA.checkValidValue(yoeLong);
            }
            Long era = fieldValues.remove(ERA);
            if (era == null) {
                Long year = fieldValues.get(YEAR);
                if (resolverStyle == ResolverStyle.STRICT) {
                    // do not invent era if strict, but do cross-check with year
                    if (year != null) {
                        updateResolveMap(fieldValues, YEAR, (year > 0 ? yoeLong: Math.subtractExact(1, yoeLong)));
                    } else {
                        // reinstate the field removed earlier, no cross-check issues
                        fieldValues.put(YEAR_OF_ERA, yoeLong);
                    }
                } else {
                    // invent era
                    updateResolveMap(fieldValues, YEAR, (year == null || year > 0 ? yoeLong: Math.subtractExact(1, yoeLong)));
                }
            } else if (era.longValue() == 1L) {
                updateResolveMap(fieldValues, YEAR, yoeLong);
            } else if (era.longValue() == 0L) {
                updateResolveMap(fieldValues, YEAR, Math.subtractExact(1, yoeLong));
            } else {
                throw new DateTimeException("Invalid value for era: " + era);
            }
        } else if (fieldValues.containsKey(ERA)) {
            ERA.checkValidValue(fieldValues.get(ERA));  // always validated
        }

        // build date
        if (fieldValues.containsKey(YEAR)) {
            if (fieldValues.containsKey(MONTH_OF_YEAR)) {
                if (fieldValues.containsKey(DAY_OF_MONTH)) {
                    int y = YEAR.checkValidIntValue(fieldValues.remove(YEAR));
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long months = Math.subtractExact(fieldValues.remove(MONTH_OF_YEAR), 1);
                        long days = Math.subtractExact(fieldValues.remove(DAY_OF_MONTH), 1);
                        return date(y, 1, 1).plusMonths(months).plusDays(days);
                    } else {
                        int moy = range(MONTH_OF_YEAR).checkValidIntValue(fieldValues.remove(MONTH_OF_YEAR), MONTH_OF_YEAR);
                        int dom = range(DAY_OF_MONTH).checkValidIntValue(fieldValues.remove(DAY_OF_MONTH), DAY_OF_MONTH);
                        if (resolverStyle == ResolverStyle.SMART && dom > 28) {
                            dom = Math.min(dom, date(y, moy, 1).lengthOfMonth());
                        }
                        return date(y, moy, dom);
                    }
                }
                if (fieldValues.containsKey(ALIGNED_WEEK_OF_MONTH)) {
                    if (fieldValues.containsKey(ALIGNED_DAY_OF_WEEK_IN_MONTH)) {
                        int y = YEAR.checkValidIntValue(fieldValues.remove(YEAR));
                        if (resolverStyle == ResolverStyle.LENIENT) {
                            long months = Math.subtractExact(fieldValues.remove(MONTH_OF_YEAR), 1);
                            long weeks = Math.subtractExact(fieldValues.remove(ALIGNED_WEEK_OF_MONTH), 1);
                            long days = Math.subtractExact(fieldValues.remove(ALIGNED_DAY_OF_WEEK_IN_MONTH), 1);
                            return date(y, 1, 1).plus(months, MONTHS).plus(weeks, WEEKS).plus(days, DAYS);
                        }
                        int moy = MONTH_OF_YEAR.checkValidIntValue(fieldValues.remove(MONTH_OF_YEAR));
                        int aw = ALIGNED_WEEK_OF_MONTH.checkValidIntValue(fieldValues.remove(ALIGNED_WEEK_OF_MONTH));
                        int ad = ALIGNED_DAY_OF_WEEK_IN_MONTH.checkValidIntValue(fieldValues.remove(ALIGNED_DAY_OF_WEEK_IN_MONTH));
                        ThaiBuddhistDate date = date(y, moy, 1).plus((aw - 1) * 7 + (ad - 1), DAYS);
                        if (resolverStyle == ResolverStyle.STRICT && date.get(MONTH_OF_YEAR) != moy) {
                            throw new DateTimeException("Strict mode rejected date parsed to a different month");
                        }
                        return date;
                    }
                    if (fieldValues.containsKey(DAY_OF_WEEK)) {
                        int y = YEAR.checkValidIntValue(fieldValues.remove(YEAR));
                        if (resolverStyle == ResolverStyle.LENIENT) {
                            long months = Math.subtractExact(fieldValues.remove(MONTH_OF_YEAR), 1);
                            long weeks = Math.subtractExact(fieldValues.remove(ALIGNED_WEEK_OF_MONTH), 1);
                            long days = Math.subtractExact(fieldValues.remove(DAY_OF_WEEK), 1);
                            return date(y, 1, 1).plus(months, MONTHS).plus(weeks, WEEKS).plus(days, DAYS);
                        }
                        int moy = MONTH_OF_YEAR.checkValidIntValue(fieldValues.remove(MONTH_OF_YEAR));
                        int aw = ALIGNED_WEEK_OF_MONTH.checkValidIntValue(fieldValues.remove(ALIGNED_WEEK_OF_MONTH));
                        int dow = DAY_OF_WEEK.checkValidIntValue(fieldValues.remove(DAY_OF_WEEK));
                        ThaiBuddhistDate date = date(y, moy, 1).plus(aw - 1, WEEKS).with(nextOrSame(DayOfWeek.of(dow)));
                        if (resolverStyle == ResolverStyle.STRICT && date.get(MONTH_OF_YEAR) != moy) {
                            throw new DateTimeException("Strict mode rejected date parsed to a different month");
                        }
                        return date;
                    }
                }
            }
            if (fieldValues.containsKey(DAY_OF_YEAR)) {
                int y = YEAR.checkValidIntValue(fieldValues.remove(YEAR));
                if (resolverStyle == ResolverStyle.LENIENT) {
                    long days = Math.subtractExact(fieldValues.remove(DAY_OF_YEAR), 1);
                    return dateYearDay(y, 1).plusDays(days);
                }
                int doy = DAY_OF_YEAR.checkValidIntValue(fieldValues.remove(DAY_OF_YEAR));
                return dateYearDay(y, doy);
            }
            if (fieldValues.containsKey(ALIGNED_WEEK_OF_YEAR)) {
                if (fieldValues.containsKey(ALIGNED_DAY_OF_WEEK_IN_YEAR)) {
                    int y = YEAR.checkValidIntValue(fieldValues.remove(YEAR));
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long weeks = Math.subtractExact(fieldValues.remove(ALIGNED_WEEK_OF_YEAR), 1);
                        long days = Math.subtractExact(fieldValues.remove(ALIGNED_DAY_OF_WEEK_IN_YEAR), 1);
                        return date(y, 1, 1).plus(weeks, WEEKS).plus(days, DAYS);
                    }
                    int aw = ALIGNED_WEEK_OF_YEAR.checkValidIntValue(fieldValues.remove(ALIGNED_WEEK_OF_YEAR));
                    int ad = ALIGNED_DAY_OF_WEEK_IN_YEAR.checkValidIntValue(fieldValues.remove(ALIGNED_DAY_OF_WEEK_IN_YEAR));
                    ThaiBuddhistDate date = date(y, 1, 1).plusDays((aw - 1) * 7 + (ad - 1));
                    if (resolverStyle == ResolverStyle.STRICT && date.get(YEAR) != y) {
                        throw new DateTimeException("Strict mode rejected date parsed to a different year");
                    }
                    return date;
                }
                if (fieldValues.containsKey(DAY_OF_WEEK)) {
                    int y = YEAR.checkValidIntValue(fieldValues.remove(YEAR));
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long weeks = Math.subtractExact(fieldValues.remove(ALIGNED_WEEK_OF_YEAR), 1);
                        long days = Math.subtractExact(fieldValues.remove(DAY_OF_WEEK), 1);
                        return date(y, 1, 1).plus(weeks, WEEKS).plus(days, DAYS);
                    }
                    int aw = ALIGNED_WEEK_OF_YEAR.checkValidIntValue(fieldValues.remove(ALIGNED_WEEK_OF_YEAR));
                    int dow = DAY_OF_WEEK.checkValidIntValue(fieldValues.remove(DAY_OF_WEEK));
                    ThaiBuddhistDate date = date(y, 1, 1).plus(aw - 1, WEEKS).with(nextOrSame(DayOfWeek.of(dow)));
                    if (resolverStyle == ResolverStyle.STRICT && date.get(YEAR) != y) {
                        throw new DateTimeException("Strict mode rejected date parsed to a different month");
                    }
                    return date;
                }
            }
        }
        return null;
    }

}
