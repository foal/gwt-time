package org.jresearch.threetenbp.gwt.time.client;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.junit.client.GWTTestCase;

public class GwtTest extends AbstractTest {

	private static Logger LOGGER = LoggerFactory.getLogger(GWTTestCase.class);

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
