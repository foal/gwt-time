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

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.time.zone.ZoneRulesProvider;
import java.util.Set;

import org.jresearch.threetenbp.gwt.client.format.wrap.ZoneIdPrinterParserTestWrapper;
import org.junit.Test;

/**
 * Test ZonePrinterParser.
 */
//@Test
public class TestZoneIdParser extends AbstractTestPrinterParser {

	private static final String AMERICA_DENVER = "America/Denver";
	private static ZoneId TIME_ZONE_DENVER;

	@Override
	public void gwtSetUp() throws Exception {
		super.gwtSetUp();
		TIME_ZONE_DENVER = ZoneId.of(AMERICA_DENVER);
	}

	// -----------------------------------------------------------------------
	// @DataProvider(name="error")
	Object[][] data_error() {
		return new Object[][] {
			// GWT Specific
				{ new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), null), "hello", -1,
						StringIndexOutOfBoundsException.class },
				{ new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), null), "hello", 6,
							IndexOutOfBoundsException.class }, };
	}

	@Test(/* dataProvider="error" */)
	public void test_parse_error() throws Exception {
		Object[][] data = data_error();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			gwtSetUp();
			test_parse_error((ZoneIdPrinterParserTestWrapper) objects[0], (String) objects[1], (int) objects[2],
					(Class<?>) objects[3]);
		}
	}

	public void test_parse_error(ZoneIdPrinterParserTestWrapper pp, String text, int pos, Class<?> expected) {
		try {
			pp.parse(parseContext, text, pos);
		} catch (RuntimeException ex) {
			// GWT Specific
			assertEquals(expected.getName(), ex.getClass().getName());
			// GWT specific
			assertEquals(parseContext.toParsed().fieldValues().size(), 0);
		}
	}

	// -----------------------------------------------------------------------
	public void test_parse_exactMatch_Denver() throws Exception {
		ZoneIdPrinterParserTestWrapper pp = new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), null);
		int result = pp.parse(parseContext, AMERICA_DENVER, 0);
		assertEquals(result, AMERICA_DENVER.length());
		assertParsed(TIME_ZONE_DENVER);
	}

	public void test_parse_startStringMatch_Denver() throws Exception {
		ZoneIdPrinterParserTestWrapper pp = new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), null);
		int result = pp.parse(parseContext, AMERICA_DENVER + "OTHER", 0);
		assertEquals(result, AMERICA_DENVER.length());
		assertParsed(TIME_ZONE_DENVER);
	}

	public void test_parse_midStringMatch_Denver() throws Exception {
		ZoneIdPrinterParserTestWrapper pp = new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), null);
		int result = pp.parse(parseContext, "OTHER" + AMERICA_DENVER + "OTHER", 5);
		assertEquals(result, 5 + AMERICA_DENVER.length());
		assertParsed(TIME_ZONE_DENVER);
	}

	public void test_parse_endStringMatch_Denver() throws Exception {
		ZoneIdPrinterParserTestWrapper pp = new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), null);
		int result = pp.parse(parseContext, "OTHER" + AMERICA_DENVER, 5);
		assertEquals(result, 5 + AMERICA_DENVER.length());
		assertParsed(TIME_ZONE_DENVER);
	}

	public void test_parse_partialMatch() throws Exception {
		ZoneIdPrinterParserTestWrapper pp = new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), null);
		int result = pp.parse(parseContext, "OTHERAmerica/Bogusville", 5);
		assertEquals(result, -6);
		assertParsed(null);
	}

	// -----------------------------------------------------------------------
	// @DataProvider(name="zones")
	Object[][] populateTestData() {
		Set<String> ids = ZoneRulesProvider.getAvailableZoneIds();
		Object[][] rtnval = new Object[ids.size()][];
		int i = 0;
		for (String id : ids) {
			rtnval[i++] = new Object[] { id, ZoneId.of(id) };
		}
		return rtnval;
	}

	@Test(/* dataProvider = "zones" */)
	public void test_parse_exactMatch() throws Exception {
		Object[][] data = populateTestData();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			gwtSetUp();
			test_parse_exactMatch((String) objects[0], (ZoneId) objects[1]);
		}
	}
	public void test_parse_exactMatch(String parse, ZoneId expected) throws Exception {
		ZoneIdPrinterParserTestWrapper pp = new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), null);
		int result = pp.parse(parseContext, parse, 0);
		assertEquals(result, parse.length());
		assertParsed(expected);
	}

	@Test
	public void test_parse_lowerCase() throws Exception {
		ZoneIdPrinterParserTestWrapper pp = new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), null);
		parseContext.setCaseSensitive(false);
		int result = pp.parse(parseContext, "europe/london", 0);
		assertEquals(result, 13);
		assertParsed(ZoneId.of("Europe/London"));
	}

	// -----------------------------------------------------------------------
	public void test_parse_endStringMatch_utc() throws Exception {
		ZoneIdPrinterParserTestWrapper pp = new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), null);
		int result = pp.parse(parseContext, "OTHERZ", 5);
		assertEquals(result, 6);
		assertParsed(ZoneOffset.UTC);
	}

	public void test_parse_endStringMatch_utc_plus1() throws Exception {
		ZoneIdPrinterParserTestWrapper pp = new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), null);
		int result = pp.parse(parseContext, "OTHER+01:00", 5);
		assertEquals(result, 11);
		assertParsed(ZoneId.of("+01:00"));
	}

	// -----------------------------------------------------------------------
	public void test_parse_midStringMatch_utc() throws Exception {
		ZoneIdPrinterParserTestWrapper pp = new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), null);
		int result = pp.parse(parseContext, "OTHERZOTHER", 5);
		assertEquals(result, 6);
		assertParsed(ZoneOffset.UTC);
	}

	public void test_parse_midStringMatch_utc_plus1() throws Exception {
		ZoneIdPrinterParserTestWrapper pp = new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), null);
		int result = pp.parse(parseContext, "OTHER+01:00OTHER", 5);
		assertEquals(result, 11);
		assertParsed(ZoneId.of("+01:00"));
	}

	// -----------------------------------------------------------------------
//    @DataProvider(name = "fixedZones")
    Object[][] data_fixedZones() {
        return new Object[][] {
                { "+01:00", "+01:00" },
                { "-01:00", "-01:00" },
                { "+12:34:56", "+12:34:56" },
                { "Z", "Z" },
                { "GMT", "GMT" },
                { "GMT+01:00", "GMT+01:00" },
                { "UTC", "UTC" },
                { "UTC-01:00", "UTC-01:00" },
                { "UT", "UT" },
                { "UT+01:00", "UT+01:00" }, };
    }

	@Test(/* dataProvider = "fixedZones" */)
	public void test_parse_fixed() throws Exception {
		Object[][] data = data_fixedZones();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			gwtSetUp();
			test_parse_fixed((String) objects[0], (String) objects[1]);
		}
	}

    public void test_parse_fixed(String input, String expectedZone) throws Exception {
		ZoneIdPrinterParserTestWrapper pp = new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), null);
        int result = pp.parse(parseContext, input, 0);
        assertEquals(result, input.length());
		assertEquals(parseContext.toParsed().query(ZoneId::from), ZoneId.of(expectedZone));
    }

	@Test(/* dataProvider = "fixedZones" */)
	public void test_parse_fixed_byFormatterWithPrefix() throws Exception {
		Object[][] data = data_fixedZones();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			gwtSetUp();
			test_parse_fixed_byFormatterWithPrefix((String) objects[0], (String) objects[1]);
		}
	}

    public void test_parse_fixed_byFormatterWithPrefix(String input, String expectedZone) throws Exception {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMzzz");
        TemporalAccessor parsed = f.parse("12" + input);
		assertEquals(parsed.query(ZoneId::from), ZoneId.of(expectedZone));
    }

	@Test(/* dataProvider = "fixedZones" */)
	public void test_parse_fixed_byFormatterWithSuffixZ() throws Exception {
		Object[][] data = data_fixedZones();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			gwtSetUp();
			test_parse_fixed_byFormatterWithSuffixZ((String) objects[0], (String) objects[1]);
		}
	}

    public void test_parse_fixed_byFormatterWithSuffixZ(String input, String expectedZone) throws Exception {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMzzz'Z'");
        TemporalAccessor parsed = f.parse("12" + input + "Z");
		assertEquals(parsed.query(ZoneId::from), ZoneId.of(expectedZone));
    }

	@Test(/* dataProvider = "fixedZones" */)
	public void test_parse_fixed_byFormatterWithSuffix0() throws Exception {
		Object[][] data = data_fixedZones();
		for (int i = 0; i < data.length; i++) {
			Object[] objects = data[i];
			gwtSetUp();
			test_parse_fixed_byFormatterWithSuffix0((String) objects[0], (String) objects[1]);
		}
	}

    public void test_parse_fixed_byFormatterWithSuffix0(String input, String expectedZone) throws Exception {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMzzz'0'");
        TemporalAccessor parsed = f.parse("12" + input + "0");
		assertEquals(parsed.query(ZoneId::from), ZoneId.of(expectedZone));
    }

    //-------------------------------------------------------------------------
	public void test_toString_id() {
		ZoneIdPrinterParserTestWrapper pp = new ZoneIdPrinterParserTestWrapper(TemporalQueries.zoneId(), "ZoneId()");
		assertEquals(pp.toString(), "ZoneId()");
	}

	private void assertParsed(ZoneId expectedZone) {
		// GWT specific
		assertEquals(parseContext.toParsed().zone(), expectedZone);
	}

}
