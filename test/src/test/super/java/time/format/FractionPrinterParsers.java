package java.time.format;

import java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser;
import java.time.format.DateTimeFormatterBuilder.FractionPrinterParser;
import java.time.temporal.TemporalField;

import org.jresearch.threetenbp.gwt.test.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.test.client.format.wrap.DateTimePrintContextTestWrapper;

public class FractionPrinterParsers {

	public static Object create(TemporalField field, int minWidth, int maxWidth, boolean decimalPoint) {
		return new FractionPrinterParser(field, minWidth, maxWidth, decimalPoint);
	}

	public static boolean print(Object parser, DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return  ((DateTimePrinterParser)parser).print((DateTimePrintContext) context.getContext(), buf);
	}

	public static int parse(Object parser, DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return ((DateTimePrinterParser)parser).parse((DateTimeParseContext) context.getContext(), text, position);
	}

}
