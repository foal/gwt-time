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
package org.jresearch.threetenbp.gwt.client.format;

import static java.time.temporal.ChronoField.AMPM_OF_DAY;
import static java.time.temporal.ChronoField.DAY_OF_WEEK;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;

import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.util.Locale;

import org.jresearch.threetenbp.gwt.client.AbstractTest;
import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimeTextProviderTestWrapper;
import org.junit.Test;

/**
 * Test SimpleDateTimeTextProvider.
 */
//@Test
public class TestSimpleDateTimeTextProvider extends AbstractTest {

    Locale enUS = new Locale("en", "US");
    Locale ptBR = new Locale("pt", "BR");
    Locale frFR = new Locale("fr", "FR");
    Locale yo = new Locale("yo");

//    @BeforeMethod
//    public void setUp() {
//    }

    //-----------------------------------------------------------------------
//    @DataProvider(name = "Text")
    Object[][] data_text() {
        return new Object[][] {
            {DAY_OF_WEEK, 1, TextStyle.SHORT, enUS, "Mon"},
            {DAY_OF_WEEK, 2, TextStyle.SHORT, enUS, "Tue"},
            {DAY_OF_WEEK, 3, TextStyle.SHORT, enUS, "Wed"},
            {DAY_OF_WEEK, 4, TextStyle.SHORT, enUS, "Thu"},
            {DAY_OF_WEEK, 5, TextStyle.SHORT, enUS, "Fri"},
            {DAY_OF_WEEK, 6, TextStyle.SHORT, enUS, "Sat"},
            {DAY_OF_WEEK, 7, TextStyle.SHORT, enUS, "Sun"},

				{ DAY_OF_WEEK, 1, TextStyle.SHORT, ptBR, "seg." },
				{ DAY_OF_WEEK, 2, TextStyle.SHORT, ptBR, "ter." },
				{ DAY_OF_WEEK, 3, TextStyle.SHORT, ptBR, "qua." },
				{ DAY_OF_WEEK, 4, TextStyle.SHORT, ptBR, "qui." },
				{ DAY_OF_WEEK, 5, TextStyle.SHORT, ptBR, "sex." },
				{ DAY_OF_WEEK, 6, TextStyle.SHORT, ptBR, "s\u00E1b." },
				{ DAY_OF_WEEK, 7, TextStyle.SHORT, ptBR, "dom." },

            {DAY_OF_WEEK, 1, TextStyle.FULL, enUS, "Monday"},
            {DAY_OF_WEEK, 2, TextStyle.FULL, enUS, "Tuesday"},
            {DAY_OF_WEEK, 3, TextStyle.FULL, enUS, "Wednesday"},
            {DAY_OF_WEEK, 4, TextStyle.FULL, enUS, "Thursday"},
            {DAY_OF_WEEK, 5, TextStyle.FULL, enUS, "Friday"},
            {DAY_OF_WEEK, 6, TextStyle.FULL, enUS, "Saturday"},
            {DAY_OF_WEEK, 7, TextStyle.FULL, enUS, "Sunday"},

				{ DAY_OF_WEEK, 1, TextStyle.FULL, ptBR, "segunda-feira" },
				{ DAY_OF_WEEK, 2, TextStyle.FULL, ptBR, "ter\u00E7a-feira" },
				{ DAY_OF_WEEK, 3, TextStyle.FULL, ptBR, "quarta-feira" },
				{ DAY_OF_WEEK, 4, TextStyle.FULL, ptBR, "quinta-feira" },
				{ DAY_OF_WEEK, 5, TextStyle.FULL, ptBR, "sexta-feira" },
				{ DAY_OF_WEEK, 6, TextStyle.FULL, ptBR, "s\u00E1bado" },
				{ DAY_OF_WEEK, 7, TextStyle.FULL, ptBR, "domingo" },

            {DAY_OF_WEEK, 1, TextStyle.FULL, yo, "Ọjọ́ Ajé"},
            {DAY_OF_WEEK, 2, TextStyle.FULL, yo, "Ọjọ́ Ìsẹ́gun"},
            {DAY_OF_WEEK, 3, TextStyle.FULL, yo, "Ọjọ́rú"},
            {DAY_OF_WEEK, 4, TextStyle.FULL, yo, "Ọjọ́bọ"},
            {DAY_OF_WEEK, 5, TextStyle.FULL, yo, "Ọjọ́ Ẹtì"},
            {DAY_OF_WEEK, 6, TextStyle.FULL, yo, "Ọjọ́ Àbámẹ́ta"},
            {DAY_OF_WEEK, 7, TextStyle.FULL, yo, "Ọjọ́ Àìkú"},

            {DAY_OF_WEEK, 1, TextStyle.FULL_STANDALONE, yo, "Ajé"},
            {DAY_OF_WEEK, 2, TextStyle.FULL_STANDALONE, yo, "Ìsẹ́gun"},
            {DAY_OF_WEEK, 3, TextStyle.FULL_STANDALONE, yo, "Ọjọ́rú"},
            {DAY_OF_WEEK, 4, TextStyle.FULL_STANDALONE, yo, "Ọjọ́bọ"},
            {DAY_OF_WEEK, 5, TextStyle.FULL_STANDALONE, yo, "Ẹtì"},
            {DAY_OF_WEEK, 6, TextStyle.FULL_STANDALONE, yo, "Àbámẹ́ta"},
            {DAY_OF_WEEK, 7, TextStyle.FULL_STANDALONE, yo, "Àìkú"},

            {MONTH_OF_YEAR, 1, TextStyle.SHORT, enUS, "Jan"},
            {MONTH_OF_YEAR, 2, TextStyle.SHORT, enUS, "Feb"},
            {MONTH_OF_YEAR, 3, TextStyle.SHORT, enUS, "Mar"},
            {MONTH_OF_YEAR, 4, TextStyle.SHORT, enUS, "Apr"},
            {MONTH_OF_YEAR, 5, TextStyle.SHORT, enUS, "May"},
            {MONTH_OF_YEAR, 6, TextStyle.SHORT, enUS, "Jun"},
            {MONTH_OF_YEAR, 7, TextStyle.SHORT, enUS, "Jul"},
            {MONTH_OF_YEAR, 8, TextStyle.SHORT, enUS, "Aug"},
            {MONTH_OF_YEAR, 9, TextStyle.SHORT, enUS, "Sep"},
            {MONTH_OF_YEAR, 10, TextStyle.SHORT, enUS, "Oct"},
            {MONTH_OF_YEAR, 11, TextStyle.SHORT, enUS, "Nov"},
            {MONTH_OF_YEAR, 12, TextStyle.SHORT, enUS, "Dec"},

            {MONTH_OF_YEAR, 1, TextStyle.SHORT, frFR, "janv."},
            {MONTH_OF_YEAR, 2, TextStyle.SHORT, frFR, "f\u00E9vr."},
            {MONTH_OF_YEAR, 3, TextStyle.SHORT, frFR, "mars"},
            {MONTH_OF_YEAR, 4, TextStyle.SHORT, frFR, "avr."},
            {MONTH_OF_YEAR, 5, TextStyle.SHORT, frFR, "mai"},
            {MONTH_OF_YEAR, 6, TextStyle.SHORT, frFR, "juin"},
            {MONTH_OF_YEAR, 7, TextStyle.SHORT, frFR, "juil."},
            {MONTH_OF_YEAR, 8, TextStyle.SHORT, frFR, "ao\u00FBt"},
            {MONTH_OF_YEAR, 9, TextStyle.SHORT, frFR, "sept."},
            {MONTH_OF_YEAR, 10, TextStyle.SHORT, frFR, "oct."},
            {MONTH_OF_YEAR, 11, TextStyle.SHORT, frFR, "nov."},
            {MONTH_OF_YEAR, 12, TextStyle.SHORT, frFR, "d\u00E9c."},

            {MONTH_OF_YEAR, 1, TextStyle.FULL, enUS, "January"},
            {MONTH_OF_YEAR, 2, TextStyle.FULL, enUS, "February"},
            {MONTH_OF_YEAR, 3, TextStyle.FULL, enUS, "March"},
            {MONTH_OF_YEAR, 4, TextStyle.FULL, enUS, "April"},
            {MONTH_OF_YEAR, 5, TextStyle.FULL, enUS, "May"},
            {MONTH_OF_YEAR, 6, TextStyle.FULL, enUS, "June"},
            {MONTH_OF_YEAR, 7, TextStyle.FULL, enUS, "July"},
            {MONTH_OF_YEAR, 8, TextStyle.FULL, enUS, "August"},
            {MONTH_OF_YEAR, 9, TextStyle.FULL, enUS, "September"},
            {MONTH_OF_YEAR, 10, TextStyle.FULL, enUS, "October"},
            {MONTH_OF_YEAR, 11, TextStyle.FULL, enUS, "November"},
            {MONTH_OF_YEAR, 12, TextStyle.FULL, enUS, "December"},

            {MONTH_OF_YEAR, 1, TextStyle.FULL, ptBR, "janeiro"},
            {MONTH_OF_YEAR, 2, TextStyle.FULL, ptBR, "fevereiro"},
            {MONTH_OF_YEAR, 3, TextStyle.FULL, ptBR, "mar\u00E7o"},
            {MONTH_OF_YEAR, 4, TextStyle.FULL, ptBR, "abril"},
            {MONTH_OF_YEAR, 5, TextStyle.FULL, ptBR, "maio"},
            {MONTH_OF_YEAR, 6, TextStyle.FULL, ptBR, "junho"},
            {MONTH_OF_YEAR, 7, TextStyle.FULL, ptBR, "julho"},
            {MONTH_OF_YEAR, 8, TextStyle.FULL, ptBR, "agosto"},
            {MONTH_OF_YEAR, 9, TextStyle.FULL, ptBR, "setembro"},
            {MONTH_OF_YEAR, 10, TextStyle.FULL, ptBR, "outubro"},
            {MONTH_OF_YEAR, 11, TextStyle.FULL, ptBR, "novembro"},
            {MONTH_OF_YEAR, 12, TextStyle.FULL, ptBR, "dezembro"},

            {AMPM_OF_DAY, 0, TextStyle.SHORT, enUS, "AM"},
            {AMPM_OF_DAY, 1, TextStyle.SHORT, enUS, "PM"},

        };
    }

	@Test(/* dataProvider = "Text" */)
	public void browser_test_getText() throws Exception {
		Object[][] data = data_text();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			gwtSetUp();
			test_getText((TemporalField) objects[0], (Number) objects[1], (TextStyle) objects[2],
					(Locale) objects[3], (String) objects[4]);
		}
	}
    public void test_getText(TemporalField field, Number value, TextStyle style, Locale locale, String expected) {
        DateTimeTextProviderTestWrapper tp = new DateTimeTextProviderTestWrapper();
		assertEquals(expected, tp.getText(field, value.longValue(), style, locale));
    }

}
