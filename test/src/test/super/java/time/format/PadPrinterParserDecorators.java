package java.time.format;

import java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser;
import java.time.format.DateTimeFormatterBuilder.NumberPrinterParser;
import java.time.format.DateTimeFormatterBuilder.PadPrinterParserDecorator;
import java.time.temporal.TemporalField;

import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimePrintContextTestWrapper;
import org.jresearch.threetenbp.gwt.client.format.wrap.NumberPrinterParserTestWrapper;

public class PadPrinterParserDecorators {

	public static Object create(Object printerParser, int padWidth, char padChar) {
		return new PadPrinterParserDecorator((DateTimePrinterParser) printerParser, padWidth, padChar);
	}

	public static boolean print(Object parser, DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return ((DateTimePrinterParser) parser).print((DateTimePrintContext) context.getContext(), buf);
	}

	public static int parse(Object parser, DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return ((DateTimePrinterParser) parser).parse((DateTimeParseContext) context.getContext(), text, position);
	}

}
