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

import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit.DAYS;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit.MONTHS;
import static org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoUnit.YEARS;

import java.io.Serializable;
import org.jresearch.threetenbp.gwt.emu.java.time.DateTimeException;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.ChronoPeriod;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.ChronoPeriodImpl;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.Chronology;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.ChronoField;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.Temporal;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalAmount;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalQueries;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalUnit;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * An implementation of {@code ChronoPeriod}.
 */
final class ChronoPeriodImpl
        implements ChronoPeriod, Serializable {

    /**
     * Serialization version.
     */
    private static final long serialVersionUID = 275618735781L;

    private final Chronology chronology;
    private final int years;
    private final int months;
    private final int days;

    public ChronoPeriodImpl(Chronology chronology, int years, int months, int days) {
        this.chronology = chronology;
        this.years = years;
        this.months = months;
        this.days = days;
    }

    //-------------------------------------------------------------------------
    @Override
    public long get(TemporalUnit unit) {
        if (unit == YEARS) {
            return years;
        }
        if (unit == MONTHS) {
            return months;
        }
        if (unit == DAYS) {
            return days;
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
    }

    @Override
    public List<TemporalUnit> getUnits() {
        return Collections.unmodifiableList(Arrays.<TemporalUnit>asList(YEARS, MONTHS, DAYS));
    }

    @Override
    public Chronology getChronology() {
        return chronology;
    }

    //-------------------------------------------------------------------------
    @Override
    public ChronoPeriod plus(TemporalAmount amountToAdd) {
        if (amountToAdd instanceof ChronoPeriodImpl) {
            ChronoPeriodImpl amount = (ChronoPeriodImpl) amountToAdd;
            if (amount.getChronology().equals(getChronology())) {
                return new ChronoPeriodImpl(
                        chronology,
                Math.addExact(years, amount.years),
                Math.addExact(months, amount.months),
                Math.addExact(days, amount.days));
            }
        }
        throw new DateTimeException("Unable to add amount: " + amountToAdd);
    }

    @Override
    public ChronoPeriod minus(TemporalAmount amountToSubtract) {
        if (amountToSubtract instanceof ChronoPeriodImpl) {
            ChronoPeriodImpl amount = (ChronoPeriodImpl) amountToSubtract;
            if (amount.getChronology().equals(getChronology())) {
                return new ChronoPeriodImpl(
                        chronology,
                Math.subtractExact(years, amount.years),
                Math.subtractExact(months, amount.months),
                Math.subtractExact(days, amount.days));
            }
        }
        throw new DateTimeException("Unable to subtract amount: " + amountToSubtract);
    }

    @Override
    public ChronoPeriod multipliedBy(int scalar) {
        return new ChronoPeriodImpl(
                chronology,
                Math.multiplyExact(years, scalar),
                Math.multiplyExact(months, scalar),
                Math.multiplyExact(days, scalar));
    }

    @Override
    public ChronoPeriod normalized() {
        if (chronology.range(ChronoField.MONTH_OF_YEAR).isFixed()) {
            long monthLength = chronology.range(ChronoField.MONTH_OF_YEAR).getMaximum() - chronology.range(ChronoField.MONTH_OF_YEAR).getMinimum() + 1;
            long total = years * monthLength + months;
            int years = Math.toIntExact(total / monthLength);
            int months = Math.toIntExact(total % monthLength);
            return new ChronoPeriodImpl(chronology, years, months, days);
        }
        return this;
    }

    @Override
    public Temporal addTo(Temporal temporal) {
        Objects.requireNonNull(temporal, "temporal");
        Chronology temporalChrono = temporal.query(TemporalQueries.chronology());
        if (temporalChrono != null && chronology.equals(temporalChrono) == false) {
            throw new DateTimeException("Invalid chronology, required: " + chronology.getId() + ", but was: " + temporalChrono.getId());
        }
        if (years != 0) {
            temporal = temporal.plus(years, YEARS);
        }
        if (months != 0) {
            temporal = temporal.plus(months, MONTHS);
        }
        if (days != 0) {
            temporal = temporal.plus(days, DAYS);
        }
        return temporal;
    }

    @Override
    public Temporal subtractFrom(Temporal temporal) {
        Objects.requireNonNull(temporal, "temporal");
        Chronology temporalChrono = temporal.query(TemporalQueries.chronology());
        if (temporalChrono != null && chronology.equals(temporalChrono) == false) {
            throw new DateTimeException("Invalid chronology, required: " + chronology.getId() + ", but was: " + temporalChrono.getId());
        }
        if (years != 0) {
            temporal = temporal.minus(years, YEARS);
        }
        if (months != 0) {
            temporal = temporal.minus(months, MONTHS);
        }
        if (days != 0) {
            temporal = temporal.minus(days, DAYS);
        }
        return temporal;
    }

    //-------------------------------------------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ChronoPeriodImpl) {
            ChronoPeriodImpl other = (ChronoPeriodImpl) obj;
            return years == other.years && months == other.months &&
                    days == other.days && chronology.equals(other.chronology);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return chronology.hashCode() + Integer.rotateLeft(years, 16) + Integer.rotateLeft(months, 8) + days;
    }

    @Override
    public String toString() {
        if (isZero()) {
            return chronology + " P0D";
        } else {
            StringBuilder buf = new StringBuilder();
            buf.append(chronology).append(' ').append('P');
            if (years != 0) {
                buf.append(years).append('Y');
            }
            if (months != 0) {
                buf.append(months).append('M');
            }
            if (days != 0) {
                buf.append(days).append('D');
            }
            return buf.toString();
        }
    }

}
