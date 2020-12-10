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

import java.time.temporal.TemporalQueries;

import org.jresearch.threetenbp.gwt.client.format.wrap.CharLiteralPrinterParserTestWrapper;
import org.junit.Test;

/**
 * Test CharLiteralPrinterParser.
 */
//@Test
public class TestCharLiteralParser extends AbstractTestPrinterParser {

    //@DataProvider(name="success")
    Object[][] data_success() {
        return new Object[][] {
            // match
            {new CharLiteralPrinterParserTestWrapper('a'), true, "a", 0, 1},
            {new CharLiteralPrinterParserTestWrapper('a'), true, "aOTHER", 0, 1},
            {new CharLiteralPrinterParserTestWrapper('a'), true, "OTHERaOTHER", 5, 6},
            {new CharLiteralPrinterParserTestWrapper('a'), true, "OTHERa", 5, 6},

            // no match
            {new CharLiteralPrinterParserTestWrapper('a'), true, "", 0, ~0},
            {new CharLiteralPrinterParserTestWrapper('a'), true, "a", 1, ~1},
            {new CharLiteralPrinterParserTestWrapper('a'), true, "A", 0, ~0},
            {new CharLiteralPrinterParserTestWrapper('a'), true, "b", 0, ~0},
            {new CharLiteralPrinterParserTestWrapper('a'), true, "OTHERbOTHER", 5, ~5},
            {new CharLiteralPrinterParserTestWrapper('a'), true, "OTHERb", 5, ~5},

            // case insensitive
            {new CharLiteralPrinterParserTestWrapper('a'), false, "a", 0, 1},
            {new CharLiteralPrinterParserTestWrapper('a'), false, "A", 0, 1},
        };
    }

	@Test(/* dataProvider="success" */)
	public void test_parse_success() {
		Object[][] data = data_success();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_parse_success((CharLiteralPrinterParserTestWrapper) objects[0], (boolean) objects[1], (String) objects[2], (int) objects[3], (int) objects[4]);
		}
	}
    public void test_parse_success(CharLiteralPrinterParserTestWrapper pp, boolean caseSensitive, String text, int pos, int expectedPos) {
        parseContext.setCaseSensitive(caseSensitive);
        int result = pp.parse(parseContext, text, pos);
        assertEquals(result, expectedPos);
        assertEquals(parseContext.toParsed().query(TemporalQueries.chronology()), null);
        assertEquals(parseContext.toParsed().query(TemporalQueries.zoneId()), null);
    }

    //-----------------------------------------------------------------------
    //@DataProvider(name="error")
    Object[][] data_error() {
        return new Object[][] {
        	//GWT specific
            {new CharLiteralPrinterParserTestWrapper('a'), "a", -1, StringIndexOutOfBoundsException.class},
            {new CharLiteralPrinterParserTestWrapper('a'), "a", 2, StringIndexOutOfBoundsException.class},
        };
    }

	@Test(/* dataProvider="error" */)
	public void test_parse_error() {
		Object[][] data = data_error();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			test_parse_error((CharLiteralPrinterParserTestWrapper) objects[0], (String) objects[1], (int) objects[2], (Class<?>) objects[3]);
		}
	}
    public void test_parse_error(CharLiteralPrinterParserTestWrapper pp, String text, int pos, Class<?> expected) {
        try {
            pp.parse(parseContext, text, pos);
            fail("Expect exception");
        } catch (RuntimeException ex) {
        	//GWT specific
        	assertEquals(expected.getName(), ex.getClass().getName());
            assertEquals(parseContext.toParsed().query(TemporalQueries.chronology()), null);
            assertEquals(parseContext.toParsed().query(TemporalQueries.zoneId()), null);
        }
    }

}
