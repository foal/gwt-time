package java.time.format;

import java.time.chrono.Chronology;
import java.time.temporal.TemporalField;
import java.util.Locale;

import org.jresearch.threetenbp.gwt.client.format.wrap.ParsedTestWrapper;

public class DateTimeParseContexts {

    public static Object create(Locale locale, DecimalStyle symbols, Chronology chronology) {
		return new DateTimeParseContext(locale, symbols, chronology);
	}

	public static ParsedTestWrapper toParsed(Object context) {
		return new ParsedTestWrapper(((DateTimeParseContext)context).toParsed());
	}

	public static void setCaseSensitive(Object context, boolean caseSensitive) {
		((DateTimeParseContext)context).setCaseSensitive(caseSensitive);
    }

	public static Long getParsed(Object context, TemporalField field) {
		return (((DateTimeParseContext)context).getParsed(field));
	}

	public static void setStrict(Object context, boolean strict) {
		((DateTimeParseContext)context).setStrict(strict);
	}

	public static void setLocale(Object context, Locale locale) {
		((DateTimeParseContext)context).setLocale(locale);
	}

	public static boolean isStrict(Object context) {
		return (((DateTimeParseContext)context).isStrict());
	}

	public static boolean isCaseSensitive(Object context) {
		return (((DateTimeParseContext)context).isCaseSensitive());
	}

}
