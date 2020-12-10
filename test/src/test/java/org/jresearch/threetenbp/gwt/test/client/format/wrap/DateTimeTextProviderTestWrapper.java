package org.jresearch.threetenbp.gwt.test.client.format.wrap;

import java.time.format.CharLiteralPrinterParsers;
import java.time.format.DateTimeTextProviders;
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.util.Locale;

public class DateTimeTextProviderTestWrapper {

    private final Object provider;

	public DateTimeTextProviderTestWrapper() {
		provider = DateTimeTextProviders.create();
	}

	public Object getProvider() {
		return provider;
	}

	public String getText(TemporalField field, long value, TextStyle style, Locale locale) {
		return DateTimeTextProviders.getText(provider, field, value, style, locale);
	}

}
