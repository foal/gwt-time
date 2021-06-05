package org.jresearch.threetenbp.gwt.emu.java.time;

import java.util.Locale;

import org.jresearch.threetenbp.gwt.emu.org.jresearch.threetenbp.gwt.time.client.Support;
import org.junit.Test;


@SuppressWarnings({ "static-method", "nls" })
public class GwtSupportTest extends AbstractTest {

	@Test
	public void testGetTimestamp() {
		final float timestamp = Support.getTimestamp();
		assertTrue(timestamp > 0);
	}

	@Test
	public void testGetTimezove() {
		final String timezone = Support.getTimezone();
		assertNotNull(timezone);
	}

	@Test
	public void testDisplayTimeZone() {
		boolean daylight = false;
		String timeZone = "Europe/Prague";
		String style = "short";
		String locale = "de-DE";
		final String result = Support.displayTimeZone(daylight, timeZone, style, locale);
		assertNotNull(result);
		assertTrue("MEZ".equals(result) || timeZone.equals(result));
	}

	@Test
	public void testSupportedLocalesOfDateTimeFormat() {
		Locale[] test = new Locale[] { Locale.forLanguageTag("agq-CM"), Locale.forLanguageTag("de-DE") };
		Locale[] result = Support.supportedLocalesOfDateTimeFormat(test);
		assertNotNull(result);
		assertEquals(2, result.length);
	}

	@Test
	public void testSupportedLocalesOfNumberFormat() {
		Locale[] test = new Locale[] { Locale.forLanguageTag("agq-CM"), Locale.forLanguageTag("de-DE") };
		Locale[] result = Support.supportedLocalesOfNumberFormat(test);
		assertNotNull(result);
		assertEquals(2, result.length);
	}
}
