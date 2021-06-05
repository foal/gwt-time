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

import java.util.Locale;

import org.jresearch.threetenbp.gwt.emu.java.time.AbstractTest;
import org.jresearch.threetenbp.gwt.emu.java.time.DateTimeException;
import org.jresearch.threetenbp.gwt.emu.java.time.LocalDateTime;
import org.jresearch.threetenbp.gwt.emu.java.time.ZoneId;
import org.jresearch.threetenbp.gwt.emu.java.time.ZonedDateTime;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.IsoChronology;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalAccessor;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TemporalField;

/**
 * Abstract PrinterParser test.
 */
// @Test
public class AbstractTestPrinterParser extends AbstractTest {

	protected DateTimePrintContext printEmptyContext;
	protected DateTimePrintContext printContext;
	protected DateTimeParseContext parseContext;
	protected StringBuilder buf;

	// @BeforeMethod
	@Override
	public void gwtSetUp() throws Exception {
		super.gwtSetUp();
		printEmptyContext = new DateTimePrintContext(EMPTY, Locale.ENGLISH, DecimalStyle.STANDARD);
		ZonedDateTime zdt = LocalDateTime.of(2011, 6, 30, 12, 30, 40, 0).atZone(ZoneId.of("Europe/Paris"));
		printContext = new DateTimePrintContext(zdt, Locale.ENGLISH, DecimalStyle.STANDARD);
		parseContext = new DateTimeParseContext(Locale.ENGLISH, DecimalStyle.STANDARD, IsoChronology.INSTANCE);
		buf = new StringBuilder();
	}

	private static final TemporalAccessor EMPTY = new TemporalAccessor() {
		@Override
		public boolean isSupported(TemporalField field) {
			return true;
		}

		@Override
		public long getLong(TemporalField field) {
			throw new DateTimeException("Mock");
		}
	};
}
