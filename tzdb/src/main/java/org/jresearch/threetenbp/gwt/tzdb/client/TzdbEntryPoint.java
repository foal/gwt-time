package org.jresearch.threetenbp.gwt.tzdb.client;

import org.jresearch.threetenbp.gwt.time.client.Support;
import org.jresearch.threetenbp.gwt.tzdb.client.zone.GwtTzdbZoneRuleProvider;

import com.google.gwt.core.client.EntryPoint;

public class TzdbEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		Support.registerGwtZoneRuleProvider(new GwtTzdbZoneRuleProvider());
	}

}
