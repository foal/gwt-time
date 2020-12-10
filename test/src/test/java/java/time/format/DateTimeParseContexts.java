package java.time.format;

import java.time.chrono.Chronology;
import java.time.temporal.TemporalField;
import java.util.Locale;

import org.jresearch.threetenbp.gwt.test.client.format.wrap.ParsedTestWrapper;

//JDK version, see for real code in /src/test/super
public class DateTimeParseContexts {

    public static Object create(Locale locale, DecimalStyle symbols, Chronology chronology) {
		return null;
	}

	public static ParsedTestWrapper toParsed(Object context) {
		return null;
	}

	public static void setCaseSensitive(Object context, boolean caseSensitive) {
    }

	public static Long getParsed(Object context, TemporalField field) {
		return null;
	}

	public static void setStrict(Object context, boolean strict) {
	}

	public static void setLocale(Object context, Locale locale) {
	}

	public static boolean isStrict(Object context) {
		return false;
	}

	public static boolean isCaseSensitive(Object context) {
		return false;
	}

}
