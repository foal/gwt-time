package org.jresearch.threetenbp.gwt.test.client.format.wrap;

import java.time.format.DateTimePrintContexts;
import java.time.format.DecimalStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class DateTimePrintContextTestWrapper {

    private final Object context;

	public DateTimePrintContextTestWrapper(TemporalAccessor temporal, Locale locale, DecimalStyle symbols) {
		context = DateTimePrintContexts.create(temporal, locale, symbols);
	}

	public Object getContext() {
		return context;
	}

	public void setDateTime(TemporalAccessor temporal) {
		DateTimePrintContexts.setDateTime(context, temporal);
	}

	public void setLocale(Locale locale) {
		DateTimePrintContexts.setLocale(context, locale);
	}

}
