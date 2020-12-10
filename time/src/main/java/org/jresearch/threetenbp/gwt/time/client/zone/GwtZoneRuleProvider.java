package org.jresearch.threetenbp.gwt.time.client.zone;

public interface GwtZoneRuleProvider {

	String getProviderId();

	boolean isInitialized();

	boolean isAsyncInitializeSupported();

	void initialize();

	void initiatedAsyncInitialize();

}
