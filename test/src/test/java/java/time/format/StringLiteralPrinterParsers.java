package java.time.format;

import java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser;
import java.time.format.DateTimeFormatterBuilder.StringLiteralPrinterParser;

import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimePrintContextTestWrapper;

//JDK version, see for real code in /src/test/super
public class StringLiteralPrinterParsers {

	public static Object create(String literal) {
		return null;
	}

	public static boolean print(Object parser, DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return false;
	}

	public static int parse(Object parser, DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return 0;
	}

}
