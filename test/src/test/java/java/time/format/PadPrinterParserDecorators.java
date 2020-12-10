package java.time.format;

import java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser;
import java.time.format.DateTimeFormatterBuilder.NumberPrinterParser;
import java.time.format.DateTimeFormatterBuilder.PadPrinterParserDecorator;
import java.time.temporal.TemporalField;

import org.jresearch.threetenbp.gwt.test.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.test.client.format.wrap.DateTimePrintContextTestWrapper;
import org.jresearch.threetenbp.gwt.test.client.format.wrap.NumberPrinterParserTestWrapper;

//JDK version, see for real code in /src/test/super
public class PadPrinterParserDecorators {

	public static Object create(Object printerParser, int padWidth, char padChar) {
		return null;
	}

	public static boolean print(Object parser, DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return false;
	}

	public static int parse(Object parser, DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return 0;
	}

}
