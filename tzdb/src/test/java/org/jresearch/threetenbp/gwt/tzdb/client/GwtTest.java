package org.jresearch.threetenbp.gwt.tzdb.client;

import java.time.ZoneId;

import org.jresearch.threetenbp.gwt.time.client.Support;
import org.jresearch.threetenbp.gwt.tzdb.client.zone.GwtTzdbZoneRuleProvider;
import org.junit.Test;

public class GwtTest extends AbstractTest {

	@Test
	public void testTzdbProviderInit() {
		Support.registerGwtZoneRuleProvider(new GwtTzdbZoneRuleProvider());
		ZoneId.of("Europe/Paris");
	}

}
