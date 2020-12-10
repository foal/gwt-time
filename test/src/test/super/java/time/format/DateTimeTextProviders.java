package java.time.format;

import java.time.format.DateTimeFormatterBuilder.CharLiteralPrinterParser;
import java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser;
import java.time.temporal.TemporalField;
import java.util.Locale;

import org.jresearch.threetenbp.gwt.test.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.test.client.format.wrap.DateTimePrintContextTestWrapper;

public class DateTimeTextProviders {

	public static Object create() {
		return DateTimeTextProvider.getInstance();
	}

	public static String getText(Object provider, TemporalField field, long value, TextStyle style, Locale locale) {
		return ((DateTimeTextProvider)provider).getText(field, value, style, locale);
	}

}
