package org.jresearch.threetenbp.gwt.client;

import java.time.ZoneId;

import org.jresearch.threetenbp.gwt.client.zone.GwtTzdbZoneRuleProvider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.junit.client.GWTTestCase;

public class GwtTest extends GWTTestCase {

	private static Logger LOGGER = LoggerFactory.getLogger("com.google.gwt.junit.client.GWTTestCase");

	/**
	 * Must refer to a valid module that sources this class.
	 */
	@Override
	public String getModuleName() {
		return "org.jresearch.threetenbp.gwt.module";
	}

	@Test
	public void testTzdbProviderInit() {
		Support.registerGwtZoneRuleProvider(new GwtTzdbZoneRuleProvider());
		ZoneId.of("Europe/Paris");
	}

	@Test
	public void testLogging() {
		String param = "test";
		LOGGER.trace("trace message: {}", param);
		LOGGER.debug("debug message: {}", param);
		LOGGER.error("error message: {}", param);
		LOGGER.info("info message: {}", param);
		LOGGER.warn("warn message: {}", param);
	}
}
