package java.time.format;

import java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser;
import java.time.format.DateTimeFormatterBuilder.NumberPrinterParser;
import java.time.temporal.TemporalField;

import org.jresearch.threetenbp.gwt.test.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.test.client.format.wrap.DateTimePrintContextTestWrapper;
import org.jresearch.threetenbp.gwt.test.client.format.wrap.NumberPrinterParserTestWrapper;

public class NumberPrinterParsers {

	public static Object create(TemporalField field, int minWidth, int maxWidth, SignStyle signStyle) {
		return new NumberPrinterParser(field, minWidth, maxWidth, signStyle);
	}

	public static boolean print(Object parser, DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return ((DateTimePrinterParser) parser).print((DateTimePrintContext) context.getContext(), buf);
	}

	public static int parse(Object parser, DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return ((DateTimePrinterParser) parser).parse((DateTimeParseContext) context.getContext(), text, position);
	}

	public static NumberPrinterParserTestWrapper withSubsequentWidth(Object parser, int subsequentWidth) {
		return new NumberPrinterParserTestWrapper(((NumberPrinterParser) parser).withSubsequentWidth(subsequentWidth));
	}

}
