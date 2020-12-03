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
package org.jresearch.threetenbp.gwt.client.zone;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.zone.ZoneOffsetTransitionRule;
import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;

public final class TzdbZoneOffsetTransitionRule {

	private TzdbZoneOffsetTransitionRule() {
		// prevent instantiation
	}

	/**
	 * Reads the state from the stream.
	 *
	 * @param in the input stream, not null
	 * @return the created object, not null
	 * @throws IOException if an error occurs
	 */
	static ZoneOffsetTransitionRule readExternal(ByteBuffer in) {
        int data = in.getInt();
        Month month = Month.of(data >>> 28);
        int dom = ((data & (63 << 22)) >>> 22) - 32;
        int dowByte = (data & (7 << 19)) >>> 19;
        DayOfWeek dow = dowByte == 0 ? null : DayOfWeek.of(dowByte);
        int timeByte = (data & (31 << 14)) >>> 14;
        TimeDefinition defn = TimeDefinition.values()[(data & (3 << 12)) >>> 12];
        int stdByte = (data & (255 << 4)) >>> 4;
        int beforeByte = (data & (3 << 2)) >>> 2;
        int afterByte = (data & 3);
        int timeOfDaysSecs = (timeByte == 31 ? in.getInt() : timeByte * 3600);
        ZoneOffset std = (stdByte == 255 ? ZoneOffset.ofTotalSeconds(in.getInt()) : ZoneOffset.ofTotalSeconds((stdByte - 128) * 900));
        ZoneOffset before = (beforeByte == 3 ? ZoneOffset.ofTotalSeconds(in.getInt()) : ZoneOffset.ofTotalSeconds(std.getTotalSeconds() + beforeByte * 1800));
        ZoneOffset after = (afterByte == 3 ? ZoneOffset.ofTotalSeconds(in.getInt()) : ZoneOffset.ofTotalSeconds(std.getTotalSeconds() + afterByte * 1800));
        // only bit of validation that we need to copy from public of() method
        if (dom < -28 || dom > 31 || dom == 0) {
            throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        }
		LocalTime time = LocalTime.ofSecondOfDay(Math.floorMod(timeOfDaysSecs, ZoneOffsetTransitionRule.SECS_PER_DAY));
		int adjustDays = Math.floorDiv(timeOfDaysSecs, ZoneOffsetTransitionRule.SECS_PER_DAY);
        return new ZoneOffsetTransitionRule(month, dom, dow, time, adjustDays, defn, std, before, after);
    }

}
