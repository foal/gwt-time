package org.jresearch.threetenbp.gwt.emu.java.time.zone;

import org.jresearch.threetenbp.gwt.time.client.zone.GwtZoneRuleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GwtTestZoneRuleProvider implements GwtZoneRuleProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(GwtTestZoneRuleProvider.class);

	private static boolean initialized = false;

	@Override
	public boolean isInitialized() {
		return initialized;
	}

	@Override
	public void initialize() {
		LOGGER.debug("Test TZ sync initialization called");
		if (!initialized) {
			LOGGER.debug("Test TZ sync initialization started");
			ZoneRulesProvider provider = new GwtTestZoneRulesProvider();
			if (!initialized) {
				ZoneRulesProvider.registerProvider(provider);
			}
			LOGGER.debug("TZDB sync initialization finished");
			initialized = true;
		}
	}

	@Override
	public String getProviderId() {
		return GwtTestZoneRulesProvider.class.getName();
	}

}
