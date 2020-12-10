package java.time.format;

import java.time.format.DateTimeFormatterBuilder.FractionPrinterParser;
import java.time.format.DateTimeFormatterBuilder.OffsetIdPrinterParser;
import java.time.temporal.TemporalField;

import org.jresearch.threetenbp.gwt.test.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.test.client.format.wrap.DateTimePrintContextTestWrapper;

//JDK version, see for real code in /src/test/super
public class OffsetIdPrinterParsers {

	public static Object create(String noOffsetText, String pattern) {
		return null;
	}

	public static boolean print(Object parser, DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return false;
	}

	public static int parse(Object parser, DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return 0;
	}

}
