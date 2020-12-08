package org.jresearch.threetenbp.gwt.client.format.wrap;

import java.time.chrono.Chronology;
import java.time.format.DateTimeParseContexts;
import java.time.format.DecimalStyle;
import java.time.temporal.TemporalField;
import java.util.Locale;

public class DateTimeParseContextTestWrapper {

    private final Object context;

    public DateTimeParseContextTestWrapper(Locale locale, DecimalStyle symbols, Chronology chronology) {
		context = DateTimeParseContexts.create(locale, symbols, chronology);
	}

	public Object getContext() {
		return context;
	}

	public ParsedTestWrapper toParsed() {
		return DateTimeParseContexts.toParsed(context);
	}

	public void setCaseSensitive(boolean caseSensitive) {
		DateTimeParseContexts.setCaseSensitive(context, caseSensitive);
    }

	public Long getParsed(TemporalField field) {
		return DateTimeParseContexts.getParsed(context, field);
	}

	public void setStrict(boolean strict) {
		DateTimeParseContexts.setStrict(context, strict);
	}

	public void setLocale(Locale locale) {
		DateTimeParseContexts.setLocale(context, locale);
	}

	public boolean isStrict() {
		return DateTimeParseContexts.isStrict(context);
	}

	public boolean isCaseSensitive() {
		return DateTimeParseContexts.isCaseSensitive(context);
	}

}
