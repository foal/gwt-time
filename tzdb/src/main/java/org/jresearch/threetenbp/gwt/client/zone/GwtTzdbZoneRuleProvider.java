package org.jresearch.threetenbp.gwt.client.zone;

import java.nio.ByteBuffer;
import java.time.zone.ZoneRulesProvider;

import org.gwtproject.core.client.ScriptInjector;
import org.gwtproject.nio.TypedArrayHelper;
import org.gwtproject.xhr.client.ReadyStateChangeHandler;
import org.gwtproject.xhr.client.XMLHttpRequest;
import org.gwtproject.xhr.client.XMLHttpRequest.ResponseType;
import org.jresearch.threetenbp.gwt.client.TzdbJs;
import org.jresearch.threetenbp.gwt.client.loader.TzdbJsBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.core.client.GWT;

import elemental2.core.ArrayBuffer;
import jsinterop.base.Js;

public class GwtTzdbZoneRuleProvider implements GwtZoneRuleProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(GwtTzdbZoneRuleProvider.class);

	private static final TzdbJsBundle bundle = GWT.create(TzdbJsBundle.class);

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
			ScriptInjector.fromString(bundle.base64binary().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
			String tzData = bundle.tzdbEncoded().getText();
			ArrayBuffer buffer = TzdbJs.decodeArrayBuffer(tzData);
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
			request.open("GET", bundle.tzdb().getSafeUri().asString());
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
