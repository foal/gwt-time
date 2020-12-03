package org.jresearch.threetenbp.gwt.client.zone;

import java.nio.ByteBuffer;
import java.time.zone.ZoneRulesProvider;

import org.gwtproject.nio.TypedArrayHelper;
import org.gwtproject.xhr.client.ReadyStateChangeHandler;
import org.gwtproject.xhr.client.XMLHttpRequest;
import org.gwtproject.xhr.client.XMLHttpRequest.ResponseType;
import org.jresearch.threetenbp.gwt.client.Support;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elemental2.core.ArrayBuffer;
import jsinterop.base.Js;

public class GwtTzdbZoneRuleProvider implements GwtZoneRuleProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(GwtTzdbZoneRuleProvider.class);

	private boolean initialized = false;

	@Override
	public boolean isInitialized() {
		return initialized;
	}

	@Override
	public boolean isAsyncInitializeSupported() {
		return true;
	}

	@Override
	public void initialize() {
		LOGGER.debug("TZDB sync initialization called");
		if (!initialized) {
			LOGGER.debug("TZDB sync initialization started");
			String tzData = Support.bundle.tzdbEncoded().getText();
			ArrayBuffer buffer = Support.decodeArrayBuffer(tzData);
			ByteBuffer data = TypedArrayHelper.wrap(buffer);
			ZoneRulesProvider provider = new TzdbZoneRulesProvider(data);
			if (!initialized) {
				ZoneRulesProvider.registerProvider(provider);
			}
			LOGGER.debug("TZDB sync initialization finished");
			initialized = true;
		}
	}

	@Override
	public void initiatedAsyncInitialize() {
		LOGGER.debug("TZDB async initialization called");
		if (!initialized) {
			XMLHttpRequest request = XMLHttpRequest.create();
			request.open("GET", Support.bundle.tzdb().getSafeUri().asString());
			request.setResponseType(ResponseType.ArrayBuffer);
			request.setOnReadyStateChange(new ReadyStateChangeHandler() {
				@SuppressWarnings({ "synthetic-access", "boxing" })
				@Override
				public void onReadyStateChange(XMLHttpRequest xhr) {
					if (xhr.getReadyState() == XMLHttpRequest.DONE) {
						if (xhr.getStatus() == 200) {
							if (!initialized) {
								LOGGER.debug("TZDB async initialization started");
								ArrayBuffer buffer = Js.cast(xhr.getResponseArrayBuffer());
								ByteBuffer data = TypedArrayHelper.wrap(buffer);
								ZoneRulesProvider provider = new TzdbZoneRulesProvider(data);
								if (!initialized) {
									ZoneRulesProvider.registerProvider(provider);
								}
								LOGGER.debug("TZDB async initialization finished");
								initialized = true;
							}
						} else {
							LOGGER.error("Can't load TZDB asynch. Response status: {} {}", xhr.getStatus(), xhr.getStatusText());
						}
					}
				}
			});
			request.send();
		}
	}

	@Override
	public String getProviderId() {
		return TzdbZoneRulesProvider.class.getName();
	}

}
