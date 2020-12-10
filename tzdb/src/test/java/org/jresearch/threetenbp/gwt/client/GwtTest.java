package org.jresearch.threetenbp.gwt.client;

import java.time.ZoneId;

import org.jresearch.threetenbp.gwt.client.zone.GwtTzdbZoneRuleProvider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GwtTest extends AbstractTest {

	private static Logger LOGGER = LoggerFactory.getLogger(GwtTest.class);

	@Test
	public void testTzdbProviderInit() {
		Support.registerGwtZoneRuleProvider(new GwtTzdbZoneRuleProvider());
		ZoneId.of("Europe/Paris");
	}

}
