package org.jresearch.threetenbp.gwt.emu.java.time;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GwtTest extends AbstractTest {

	private static Logger LOGGER = LoggerFactory.getLogger(GwtTest.class);

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
