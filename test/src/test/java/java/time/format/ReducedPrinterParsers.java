package java.time.format;

import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser;
import java.time.format.DateTimeFormatterBuilder.ReducedPrinterParser;
import java.time.temporal.TemporalField;

import org.jresearch.threetenbp.gwt.test.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.test.client.format.wrap.DateTimePrintContextTestWrapper;

//JDK version, see for real code in /src/test/super
public class ReducedPrinterParsers {

	public static Object create(TemporalField field, int width, int maxWidth, int baseValue, ChronoLocalDate baseDate) {
		return null;
	}

	public static boolean print(Object parser, DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return false;
	}

	public static int parse(Object parser, DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return 0;
	}

}
